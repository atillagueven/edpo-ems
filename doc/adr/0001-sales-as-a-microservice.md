# 01 Sales as a microservice

Date: 2023-04-12

## Status

Accepted

## Context

The Sales microservice plays a crucial role in the sales process where clients can provide their load profile and request an offer. Based on the profile, a suitable energy management system gets recommended. 
## Decision

We have decided to implement the sales-service as an independent microservice. This decision is based on the fact that the sales service can function on its own and should not be tightly coupled with the other services. This approach will improve the overall stability of the system and make it more scalable and maintainable while also increasing the fault tolerance.

## Consequences

While implementing the executor as a separate microservice will improve the system's functionality, it will require communication with the other services. Therefore, we will need to establish clear communication channels between the Sales microservice and the executor to ensure the seamless flow of data and information. This may require some additional development efforts, but the long-term benefits are worth the investment.