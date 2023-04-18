# 06 Standalone Frontend Application

Date: 2023-04-16

## Status

Accepted

## Context

Our system's frontend application is responsible for providing clients with an interface to interact with the system, such as creating/accepting offers and making payments.

## Decision

We have decided to create a separate service for the frontend using Vue.js. This decision was made based on the following reasons:

-   Easier maintenance: By separating the frontend application into its own service, we can focus on maintaining and updating it independently from the backend services.
-   Improved user experience: A dedicated frontend service allows us to create a consistent user experience for both clients and engineers, regardless of which backend service they are interacting with.
-   Improved scalability: A dedicated frontend service can help us scale the system more easily, allowing us to add more frontend instances as needed.
-   Improved security: Separating the frontend application from the backend services can improve the system's security posture, as it reduces the attack surface for potential security breaches.

## Consequences

As a result of this decision, we anticipate the following consequences:

-   Increased development complexity: Introducing a new service for the frontend can add development complexity, requiring additional resources and potentially new integration challenges.
-   Improved system performance: Separating the frontend application into its own service can improve the system's performance, as it allows us to scale the frontend independently of the backend services.