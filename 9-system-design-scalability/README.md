# System Design and Scalability

Completed tasks:

![0%](https://progress-bar.xyz/0)

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
  
</details>

<hr/>

## 2. Social Network

How would you design the data structures for a very large social network like Facebook or LinkedIn? Describe how you would design an
algorithm to show the shortest path between two people (e.g., Me -> Bob -> Susan -> Jason -> You).

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
