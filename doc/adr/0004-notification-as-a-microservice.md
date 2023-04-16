# 04 Notification as a microservice

Date: 2023-04-12

## Status

Accepted

## Context

Our system needs to communicate with various external parties such as clients and engineers. Currently, there is code duplication for handling notifications in different services.

## Decision

We have decided to create a dedicated Notification Service to handle all external communication from our system. This service will be responsible for sending notifications to clients, engineers, and other external parties.

Reasons for this decision include:

-   Reducing code duplication: By having a single service dedicated to handling notifications, we can avoid duplicating code across different services.
-   Improved scalability: As our system grows and more services require notification functionality, a dedicated Notification Service will make it easier to scale.
-   Flexibility: By isolating notification functionality in its own service, we can make changes to this functionality without affecting other parts of the system.

## Consequences

As a result of this decision, we expect the following consequences:

-   Increased efficiency: The Notification Service will handle all external communication, reducing the load on other services and improving overall system performance.
-   Improved maintenance: Having a dedicated service for notifications will make it easier to maintain and update this functionality.
-   Increased complexity: The addition of a new service will introduce some complexity to the system, requiring additional resources and potential integration challenges.



