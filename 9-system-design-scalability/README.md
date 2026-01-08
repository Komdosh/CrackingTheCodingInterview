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

[**Full Description and Arch Notes**](Task9.2.md)

![Task 9.2 - Social Network Connections](https://github.com/Komdosh/CrackingTheCodingInterview/blob/3db4fb42e49c19df2c514c6869068b074f73a9bb/9-system-design-scalability/Task%209.2%20-%20Social%20Network%20Connections%20SD.jpeg)

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
