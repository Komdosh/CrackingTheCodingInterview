# System Design and Scalability

Completed tasks:

![24%](https://progress-bar.xyz/24)

Note: I’ve slightly changed the rules for this section. The book presents problems with specific implementations, but I decided to move a bit away from that approach and instead treat the problems as system design tasks.

## 1. Stock Data

Imagine you are building some sort of service that will be called by up to 1,000 client applications to get simple end-of day stock price
information (open, close, high, low). You may assume that you already have the data, and you can store it in any format you wish. How would
you design the client-facing service that provides the information to client applications? You are responsible for the development, rollout,
and ongoing monitoring and maintenance of the feed. Describe the different methods you considered and why you would recommend your approach.
Your service can use any technologies you wish, and can distribute the information to the client applications in any mechanism you choose.

<details>
<summary>Solution</summary>

[**Full Description and Arch Notes**](Task9.1.md)
  
![Task 9.1 - System Design](https://github.com/Komdosh/CrackingTheCodingInterview/blob/main/9-system-design-scalability/Task%209.1%20-%20Ticker%20Data%20SD.jpeg?raw=true)
  
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

![Task 9.2 - Social Network Connections](https://github.com/Komdosh/CrackingTheCodingInterview/blob/3db4fb42e49c19df2c514c6869068b074f73a9bb/9-system-design-scalability/Task%209.2%20-%20Social%20Network%20Connections%20SD.jpeg)

<details>
<summary>API</summary>

#### Base URL: /api/v1 - for maintability and backward compatibility in future

#### Connections API

Retrieve path from one user to another
```
GET /connections/shortest_path?from_user_id={id}&to_user_id={id}&max_depth={optional}
Headers: JWT (user session)
Response Body:
{
  from_user: { id: '', name: '' },
  to_user: { id: '', name: '' },
  path: [
    { id: '', name: '' },  // first connection
    { id: '', name: '' },  // second connection
    ...
  ],
  meta: {
    path_length: 4,
    query_time_ms: 15
  }
}
```

Retrieve direct connections for user
```
GET /connections/{user_id}?depth={optional}
Headers: JWT (user session)
Response Body:
{
  user: { id: '', name: '' },
  connections: [
    { id: '', name: '' },
    { id: '', name: '' },
    ...
  ],
  meta: {
    total_connections: 200,
    depth: 1
  }
}
```
  
</details>

<details>
<summary>Arch Notes</summary>

- We separate User Connections and Shortest Path into two microservices
- Both services share the same DB to ensure data consistency and avoid duplication
- A queue is used for:
   - Asynchronous graph updates to decouple write-heavy events from reads
   - Supports retries, partitioning, and future scaling for high load
- Each microservice has its own Redis cache: Cache is optional but highly recommended due to expensive BFS traversal and read-heavy workload
- Distributed Graph DB (JanusGraph with Cassandra persistent layer) is used
  - Supports sharding, replications and parallel traversal
  - Capable of handling billions of nodes and edges
  
</details>

<details>
<summary>Security, Observability, Maintability, Cost Efficiency</summary>

#### Security

- HTTPS + JWT for client authentication
- Graph DB encrypted at rest and in transit
- RBAC & audit logs for sensitive operations (e.g., path queries, connection updates)
- Rate limiting at API Gateway to prevent abuse

#### Observability

- Metrics: request count, latency, cache hit/miss ratio, queue lag
- Logs and tracing with correlation IDs / Request ID across microservices
- Alerts for SLA breaches, high cache miss rates, or DB shard lag
- Kubernetes HPA with liveness/readiness probes

#### Maintainability

* Stateless microservices - easy horizontal scaling
* CI/CD with canary / blue-green deployments
* API versioning (`/api/v1`) for backward compatibility
* Retry + Dead-Letter Queue (DLQ) for graph update events
* Separation of concerns: Shortest Path vs User Connections service

#### Cost Efficiency

- Auto-scaling REST API servers and microservices based on load
- Caching frequently requested paths and user connections reduces Graph DB load
- Message queue smooths bursts of graph updates and cache invalidation events
- Right-sized Kubernetes resources to avoid overprovisioning
  
</details>

<details>
<summary>Scaling Load x10</summary>

- Horizontal scaling via Kubernetes pods with HPA
- Graph DB read replicas to distribute query load for read-heavy operations
- Redis cluster with sharding to handle larger request volume
- Add partitions/topics to increase throughput
- Multiple consumers to process cache invalidation and graph update events concurrently
- Ensure DLQ and retries scale with higher load
- Add additional shards for nodes and edges. Multiregion?
- Distribute traversals across shards for shortest-path queries
- Replicate hot partitions to reduce cross-shard traversal latency
- Scale horizontally to handle 10x incoming requests
- Maintain rate limiting and circuit breakers to protect backend
- Increase metric granularity and sampling for high throughput
- Ensure alerting thresholds scale with higher request volume
  
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
