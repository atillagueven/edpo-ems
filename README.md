# EMSai - EDPO Project Group 3

## Documentation

The documentation can be found in the [docs](docs/) folder. 

ADR's can be found in the [docs/adr](docs/adr/) folder.

## Setup

To start the application, you need to have [Docker](https://www.docker.com/) installed.

run `docker-compose -f docker-compose.yml up` (Camunda Platform) or `docker-compose -f docker-compose-cloud.yml up` (Camunda Cloud) to start the Kafka, Zeebee, Kafdrop, Camunda Tasklist, Elasticsearch, Operate and Zookeeper Containers.

Then start your services individually.


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

To run the frontend run `npm install` and `npm run dev` in the frontend folder.