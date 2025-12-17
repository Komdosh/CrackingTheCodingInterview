# System Design and Scalability

Completed tasks:

![12%](https://progress-bar.xyz/12)

Note: I’ve slightly changed the rules for this section. The book presents problems with specific implementations, but I decided to move a bit away from that approach and instead treat the problems as system design tasks.

## 1. Stock Data

Imagine you are building some sort of service that will be called by up to 1,000 client applications to get simple end-of day stock price
information (open, close, high, low). You may assume that you already have the data, and you can store it in any format you wish. How would
you design the client-facing service that provides the information to client applications? You are responsible for the development, rollout,
and ongoing monitoring and maintenance of the feed. Describe the different methods you considered and why you would recommend your approach.
Your service can use any technologies you wish, and can distribute the information to the client applications in any mechanism you choose.

<details>
<summary>Solution</summary>
  
<details>
<summary>Requirements</summary>

### Functional Requirements:
- Provide end-of-day stock price data: open, high, low, close. For clients.
- Queries: 
  - Single ticker, most recent trading day
  - Historical data for the ticker (date range)
- Secure access
- Rate Limiting and Quota per client
- Push notifications about new data

### Non-Functional Requirements:
- Availbility: ≥ 99.95%
- Latency: P95 < 200 ms per request
- Scalability: Up to ~1,000 clients; burst up to ~1,000 RPS
- Freshness: New data visible within < 2 minutes of publish
- Security: HTTPS, authz, encrypted storage, audit logs
- Backward compatibility: API versioning with deprecation policy
- Observability: Metrics, logs, tracing, alerting
- Maintainability: CI/CD, automated testing, zero-downtime deploys
- Cost efficiency: Caching and autoscaling to minimize compute/storage cost

### Out of scope:
- Data importing, it is provided by external service
- Real-time / intraday quotes or streaming
- Market data ingestion from exchanges / upstream sources
- Batch / multi-ticker requests
- Analytics (indicators, forecasting, etc.)
- Full multi-language client SDKs
- Licensing / commercial agreements for market data
</details>

<details>
<summary>Workload Estimation</summary>

### Assumptions:
- ~1000 client applications
- Each client queries 1 ticker at a time
- Data requested once per day and per ticker
- After day (nightly) automated jobs with many requests
- Time window for automated tasks ~ 2 hours after the data in the end of the day publication
- API provides historical ranges, so some clients may request multiple days occasionally (slightly bigger payload but still one request per call)

### Load
- 1-2 requests per client per day ~ 1k-2k requests per day
- ETL jobs can requests 5-10 times, so ~ 5k-10k requests per day
- Let's assume that worst case will be 50 requests per client per day ~50k per day
  
#### Peak Request Rate
Let's assume that 80% (20/80 rule) is happens with 2 hour peak window
```
 50000*0.8 / 2 hours ~ 5.5k req/hour ~ 92 req / min ~ 1.5 RPS
```
We should also mention a margin for retries, client bursts and unexpected growth. For rough calculations let's take 1k RPS.
This will ensures scalability headroom (long-term usage / resilience).

#### Response Payload Size
- Client requests single ticker + historical range (ex: 30 trading days)
- Each record ~ 100 bytes (ticker; open, high, low, close prices; timestamp; some additional meta)
- 30 days * 100 bytes ~ 3kb
- JSON formatting overhead + meta
- ~ 5kb of response in total

```
~5 KB × 5,555 requests/hour ≈ ~27.7 MB/hour (negligible) - peak hourly data transfer
```
for 1k RPS limit
```
5 KB × 1000 req/sec ≈ 5 MB/sec ≈ ~18 GB/hour
```
Read-heavy system (need to think about CDN/Caching)

#### Storage
- Assume that we have 10k traded tickers globally
- about 252 trading days per year (exchange has a weekends and holidays)
- 7 years of data retention
```
10000*252*7 ~ 17.64M of records
17.64M * 100 bytes (record size) ~ 1.7Gb (wow, small)
```
Even with `x5` safety factor (indexes, meta, replicas) it will be less than 10Gb
So it is a very small footprint (looks like SQL or NoSQL doesn't matter with this volume).

</details>

![Task 9.1 - System Design](https://github.com/Komdosh/CrackingTheCodingInterview/blob/main/9-system-design-scalability/Task%209.1%20-%20Ticker%20Data%20SD.jpeg?raw=true)

<details>
<summary>API</summary>
  
#### Base URL: /api/v1 - for maintability and backward compatibility in future

#### Prices REST API

```
GET /prices/{ticker}?start_date=&end_date -- if no dates provided, latest will be fetched
Headers: JWT (user session)
Response Body:
{
  ticker: '',
  data: [{ date: 'DD-MM-YYYY', open: 0, high: 0, low: 0, close: 0 }],
  meta: {},
  page: {} -- if needed
}
```

#### Subscription API

```
POST wss://{DOMAIN_BASE_URL}/subscribe
Headers: JWT (user session)
Request Payload:
{
  action: 'subscribe'
  tickers: [{MANY_TICKERS}]
}
Responses Flow (response by one ticker)
{
  ticker: '',
  data: { date: 'DD-MM-YYYY', open: 0, high: 0, low: 0, close: 0 },
  meta: {}
}
```
  
</details>

<details>
<summary>Arch Notes</summary>

- There is no need to separate the cache. We can rely on DB caching because the data volume is small. Introducing a separate cache would increase cost unnecessarily.
- Runtime: Let's use Kubernetes, as it is the most popular solution. It provides simple service configuration, service discovery, HPA, etc.
- The API Gateway can be nginx Ingress. It could also be Kong or any cloud-native solution, but for Kubernetes, we’ll use nginx.
- For the database, we’ll use a relational DB, as the data size is small (<10 GB over 7 years).
- For temporary storage of external data, we’ll use Apache Kafka to support retries, partitioning (for future high load), and a dead-letter queue.
- For the notification service, we’ll use RabbitMQ, as it supports many subscribers and can hold the latest records with TTL, effectively acting like a cache.
- For observability, since we are on Kubernetes, we can leverage service mesh or ambient mesh solutions like istio or fluentd.
- Every service is stateless, so it can be horizontally scaled over time or in response to bursts of requests.
  
</details>

<details>
<summary>Security, Observability, Maintability, Cost Efficiency</summary>
  
#### Security:
- HTTPS + OAuth/API keys for client auth
- DB encrypted at rest
- RBAC & audit logs for subscriptions

#### Observability:
- Metrics: request count, latency, queue lag
- Logs & tracing (correlation IDs)
- Alerts for SLA breaches
- Kubernetes HPA, liveness/readiness probes

#### Maintainability:

- Stateless API & dispatcher -> easy scaling
- CI/CD with canary/blue-green deploys
- API versioning for backward compatibility
- Retry + DLQ for ingestion & notifications

#### Cost Efficiency:

- Auto-scaling REST API & dispatcher
- Message queue smooths ingestion spikes
- Right-sized Kubernetes resources
  
</details>

<details>
<summary>Scaling Load x10</summary>
  
- REST API: Horizontal pods, HPA, DB read replicas.
- WebSocket Dispatcher: Horizontal scaling, connection broker, broadcast messages for shared tickers.
- Data Ingestion: Multiple queue consumers, batch DB writes.
- Message Queue: Add partitions/topics to handle throughput.
  
</details>
  
</details>

<hr/>

## 2. Social Network

How would you design the data structures for a very large social network like Facebook or LinkedIn? Describe how you would design an
algorithm to show the shortest path between two people (e.g., Me -> Bob -> Susan -> Jason -> You).

<details>
<summary>Solution</summary>

<details>
<summary>Requirements</summary>

### Functional Requirements

- User Profiles:
    - Store user details: name, ID, email, work history, education, etc.
    - Support profile updates and privacy settings
- Friendship / Connections:
    - Users can send/accept connection requests
    - Users can see their connections/friends
    - Given two users, find the **shortest chain of connections** between them
      - Example: `Me -> Bob -> Susan -> Jason -> You`
- Query Capabilities
    - Efficiently query connections (direct and indirect)
    - Possibly support degree of separation queries (e.g., friends of friends)

### Non-Functional Requirements

- Must handle billions of users and trillions of connections.
- Support horizontal scaling (sharding or partitioning the graph).
- Querying the shortest path should be fast (sub-second if possible for reasonable distances)
- Read-heavy system: connections are queried more often than updated
- High availability, ideally 99.99% uptime, because users expect social graph access anytime
- Eventual consistency is acceptable for connection requests
- Strong consistency for sensitive operations like blocking users
- Graph data can be massive; data structures must be optimized for memory/storage
- Respect privacy settings (e.g., do not show paths through private connections)

 
### Out of scope

- Notify users when someone connects with their friends (can affect graph traversal priorities).
- Support degrees of separation queries (e.g., "show me people within 2 hops").
- Support friend recommendations based on graph traversal
- Handle real-time updates to the social graph

</details>

<details>
<summary>Workload Estimation</summary>

### Assumptions

- Total users: ~1 billion (Facebook/LinkedIn scale)
- Average connections per user: ~200 (friends/connections)
- Client applications: 1000 internal/external apps querying the service
- Requests per client: queries shortest path between two users
- Peak usage: mostly during daytime hours (~2-hour peak)
- Historical queries: occasionally, clients may query past snapshots (slightly bigger payloads)
- Graph updates: friend requests/acceptances happen throughout the day

### Load

- Assume 1-2 requests per client per day -> 1k–2k requests/day
- Some internal jobs may query multiple shortest paths for analytics or recommendations -> 5k–10k requests/day
- Each client could perform up to 50 shortest-path queries/day -> 50k requests/day

### Peak Request Rate

- Assume 80% of requests happen during a 2-hour peak window:

```
50000 * 0.8 / 2 hours ≈ 20000 req/hour ≈ 333 req/min ≈ 5.5 RPS
```

- Add margin for retries, bursts, and growth -> target 1000 RPS for scalability headroom

### Response Payload Size

- Each response: sequence of user IDs/names along the path
- Average path length: ~4 connections (Me -> Bob -> Susan -> Jason -> You)
- Each node info: ~200 bytes (ID, name, profile snippet)
- Total payload: 4 * 200 bytes ≈ 800 bytes
- With JSON formatting/overhead: ~1 KB per request

```
1 KB * 5,5 RPS ≈ 5.5 kb/s ≈ 20 MB/hour
```

- For 1000 RPS limit:

```
1 KB * 1000 rps ≈ 1 MB/sec ≈ 3.6 GB/hour (Peak Data Transfer)
```

- Very manageable for network bandwidth, but cache can help speed up repeated queries

### Storage

- Total users: 1B
- Average connections: 200
- Total edges: 1B × 200 / 2 ≈ 100B edges (divide by 2 to avoid duplicate counting)
- Store edges as (user_id_1, user_id_2) tuples: 16 bytes + indexing/meta ~ 32 bytes per edge

```
100B edges × 32 bytes ≈ 3.2 TB
```

- Add replica/fault tolerance factor ×3 -> ~10 TB for graph storage
- Vertices: 1B × 200 bytes (user info summary) ≈ 200 GB

</details>


<details>
<summary>API</summary>
  
  
</details>

<details>
<summary>Arch Notes</summary>

  
</details>

<details>
<summary>Security, Observability, Maintability, Cost Efficiency</summary>
  

  
</details>

<details>
<summary>Scaling Load x10</summary>
  
  
</details>
  
</details>

<hr/>

## 3. Web Crawler

If you were designing a web crawler, how would you avoid getting into infinite loops?

<hr/>

## 4. Duplicate URLs

You have 10 billion URLs. How do you detect the duplicate documents? In this case, assume "duplicate" means that the URLs are identical.

<hr/>

## 5. Cache

Imagine a web server for a simplified search engine. This system has 100 machines to respond to search queries, which may then call out
using processSearch ( string query) to another cluster of machines to actually get the result. The machine which responds to a given query
is chosen at random, so you cannot guarantee that the same machine will always respond to the same request. The method processSearch is very
expensive. Design a caching mechanism for the most recent queries. Be sure to explain how you would update the cache when data changes.

<hr/>

## 6. Sales Rank

A large eCommerce company wishes to list the best-selling products, overall and by category. For example, one product might be the #1056th
best-selling product overall, but the #13th best-selling product under "Sports Equipment", and the #24th best-selling product under
"Safety.". Describe how you would design this system.

<hr/>

## 7. Personal Financial Manager

Explain how you would design a personal financial manager (like Mint.com). This system would connect to your bank accounts, analyze your
spending habits, and make recommendations.

<hr/>

## 8. Pastebin

Design a system like Pastebin, where a user can enter a piece of text and get a randomly generated URL to access it.

<hr/>
