# EDPO Project group 3

## Setup

To start the application, you need to have [Docker](https://www.docker.com/) installed.

run `docker-compose up` to start the Kafka, Zeebee, Kafdrop, Camunda Tasklist, Elasticsearch, Operate and Zookeeper Contaqiners.

Then start your services individually.

Alternatively you can run `docker-compose-all up ` to start all services at once. (To be implemented)) 

## Services

### Sales

[Sales](sales/) handles the initial customer request and the sales process.

### Inventory

[Inventory](inventory/) handles the inventory and the assembly of the order as well as the installation.

### Payment

[Payment](payment/) handles the payment process.

### Notification

[Notification](notification/) handles the communication with the customer.

### Frontend & API

[Frontend](frontend/) and [API](api/) handle the frontend of the application.
