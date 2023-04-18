# 03 Inventory as a microservice

Date: 2023-04-12

## Status

Accepted

## Context

The Inventory Service plays a crucial role in assembling orders and organizing the installment process with the client. However, it's currently part of a larger monolithic system, making it difficult to maintain and scale.

## Decision

After careful consideration, we have decided to implement the Inventory Service as its own microservice. By doing so, we can improve fault tolerance and scalability, as well as increase the system's maintainability.

## Consequences

This decision will require some changes to the architecture, but ultimately, it will enable us to make improvements more easily in the future. The new microservice will be able to scale independently, which will improve overall system performance. However, this change may require additional resources and time in the short term to ensure a smooth transition.


