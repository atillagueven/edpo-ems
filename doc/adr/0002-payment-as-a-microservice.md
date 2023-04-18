# 02 Payment as a microservice 

Date: 2023-04-12

## Status

Accepted

## Context

The payment service is responsible for processing payments in our system. It involves sensitive user data and requires a higher level of security than other services due to the risk of fraud and financial loss. Additionally, the payment industry is subject to frequent changes and updates due to evolving standards and regulations, which results in higher code volatility.

## Decision

We have decided to implement the payment service as a separate service in our system. This decision has been made to address the security and code volatility concerns related to the payment service. By implementing it as a separate service, we can ensure that sensitive user data is protected with stronger security measures, that the payment service can be updated quickly and efficiently without affecting other services, and that the payment service can be scaled independently to ensure fast and reliable payment processing.

## Consequences

-   Additional development effort and deployment complexity will be required to implement the separate payment service
-  Additional communication between services
-   Additional resources and costs may be incurred to maintain the separate payment service
-   Improved security measures will ensure that user data is protected, reducing the risk of fraud and financial loss