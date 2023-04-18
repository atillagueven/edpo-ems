# 05 Single API Gateway

Date: 2020-03-31
## Status

Accepted

## Context

We are designing a microservices architecture for our application, which will involve several backend services and a frontend application that will interact with these services. We need to decide whether to use a single API Gateway for all incoming requests, or whether to have the frontend application call each service directly.

## Decision

We have decided to use a single API Gateway for all incoming requests in our microservices architecture. This decision was made based on the following factors:

-   Simplification of communication between the frontend and backend services, reducing coupling and improving maintainability.
-   Provision of an extra layer of security through firewalling incoming traffic and protecting the backend microservices from unauthorized access or malicious attacks.
-   Handling of tasks such as load balancing and caching, leading to better performance and scalability.
-   Facilitation of monitoring and logging, by having all requests go through a central entry point.
-   Promotion of consistency and standardization across the different microservices, ensuring that all services follow the same guidelines and best practices.

## Consequences

The use of a single API Gateway in our microservices architecture will have the following consequences:

-   The API Gateway will become a central point of failure, and we will need to ensure that it is highly available and scalable..
-   The implementation of the API Gateway will require additional development effort and maintenance compared to a direct frontend-to-service communication model.
-   The API Gateway will require careful design and configuration to ensure that it does not become a bottleneck or a performance issue for our services.

Overall, we believe that the benefits of using a single API Gateway in our microservices architecture outweigh the potential drawbacks, and we are confident that we can implement and maintain a reliable and efficient gateway for our application