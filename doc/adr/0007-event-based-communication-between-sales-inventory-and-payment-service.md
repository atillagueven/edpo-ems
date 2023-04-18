# 02 Event-Based Communication Between Sales, Inventory, and Payment Services

Date: 2020-04-12

## Status

Accepted

## Context

Our application consists of multiple business-relevant services, including sales, inventory, and payment. We need to ensure that these services can coordinate their tasks efficiently and reliably.

## Decision

We have decided to use an event-based approach for communication between sales, inventory, and payment services. Each service publishes events when it completes a task, and other services can subscribe to these events to perform their own tasks.

For instance, when a client approves an offer, the sales-service publishes an "order-placed-event" on the "ems-sales" topic. The inventory-service subscribes to this topic and takes responsibility for correctly assembling the order and initiating the delivery process. Once the order has been shipped, the inventory-service publishes an "order-shipped-event", which the payment-service subscribes to in order to initiate payment processing.

Using an event-based approach allows us to decouple services and avoid a central orchestrator. Each service is responsible for its own data and can operate independently of other services. Additionally, events are asynchronous, which allows services to operate at their own pace and can help us to handle spikes in traffic more effectively.

## Consequences

Using an event-based approach requires careful planning and design to ensure that events are properly defined and published. We need to ensure that events are structured correctly, have the necessary metadata, and are published in a timely and reliable manner. Additionally, we need to ensure that services are able to handle events that they subscribe to and that they can recover from missed events.

Overall, we believe that an event-based approach will provide us with a flexible and reliable communication method between sales, inventory, and payment services. This approach allows each service to focus on its specific responsibilities and helps us to avoid a central orchestrator.