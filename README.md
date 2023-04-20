# EMSai - EDPO Project Group 3

EMSai is an energy management systems provider.
The project was developed as part of the EDPO course at the University of St. Gallen.
Currently the Sales, Inventory and Payment services are implemented, enabeling a client to order a battery for an EMS and have it installed.

## Setup

To start the application, you need to have [Docker](https://www.docker.com/) installed.

run `docker-compose -f docker-compose.yml up` (Camunda Platform) or `docker-compose -f docker-compose-cloud.yml up` (Camunda Cloud) to start the Kafka, Zeebee, Kafdrop, Camunda Tasklist, Elasticsearch, Operate and Zookeeper Containers.

Then start your services individually.
To ensure correct behavior, make sure all the containers are running before starting the services.

## Documentation

The documentation can be found in the [docs](docs/) folder.

## Architecture

### Architectural Decisions
ADR's can be found in the [docs/adr](docs/adr/) folder.

### Overview
![main-flow.png](doc%2Fimages%2Fmain-flow.png)

## Services

### Sales
[Sales](sales/) handles the initial customer request and the sales process.

**Camunda flow**
![sales-process.png](doc%2Fimages%2Fsales-process.png)

### Inventory
[Inventory](inventory/) handles the inventory and the assembly of the order as well as the installation.

**Camunda flow**
![inventory-process.png](doc%2Fimages%2Finventory-process.png)

### Payment
[Payment](payment/) handles the payment process.

**Camunda flow**
![payment-process.png](doc%2Fimages%2Fpayment-process.png)

### Notification
[Notification](notification/) handles the communication with the customer.

### Frontend & API Gateway
[Frontend](frontend/) and [API](api/) handle the frontend of the application.
To run the frontend run `npm install` and `npm run dev` in the frontend folder.
