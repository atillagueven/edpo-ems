# 08 Command-Based Communication with Notification Service

Date: 2020-04-12

## Status

Accepted

## Context

Our application includes a notification service that is responsible for informing clients about noteworthy updates in the process. The responsibility of informing the client about updates lies with the respective services. Therefore, we need to define a communication approach between the business services and the notification service.

## Decision

We have decided to use a command-based approach for communication between the business services and the notification service. The business services will send commands to the notification service, which will then execute the corresponding notifications.

For instance, when an order has been shipped, the inventory-service will send a "send-shipping-notification-command" to the notification-service. The notification-service will receive the command and then send the corresponding notification to the client.

Using a command-based approach allows us to clearly define responsibilities and avoid ambiguity. Each service is responsible for its own data and can operate independently of other services. Additionally, commands provide a clear and concise way of communicating specific instructions.

## Consequences

Using a command-based approach requires additional code to define and execute commands. However, this approach allows us to clearly define responsibilities and avoid ambiguity. We need to ensure that commands are structured correctly and that they include all the necessary information for the notification service to execute the corresponding notification.

Overall, we believe that a command-based approach will provide us with a flexible and reliable communication method between the business services and the notification service. This approach allows each service to focus on its specific responsibilities and helps us to avoid confusion about who is responsible for sending notifications.