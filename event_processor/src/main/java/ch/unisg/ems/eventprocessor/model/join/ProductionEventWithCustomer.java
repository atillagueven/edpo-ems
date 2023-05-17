package ch.unisg.ems.eventprocessor.model.join;

import ch.unisg.ems.eventprocessor.model.Customer;
import ch.unisg.ems.eventprocessor.model.EntityProductionEvent;
import ch.unisg.ems.eventprocessor.serialization.ProductionEvent;

public class ProductionEventWithCustomer {

    private EntityProductionEvent entityProductionEvent;
    private Customer customer;

    public ProductionEventWithCustomer(EntityProductionEvent entityProductionEvent, Customer customer) {
        this.entityProductionEvent = entityProductionEvent;
        this.customer = customer;
    }

    public EntityProductionEvent getEntityProductionEvent() {
        return entityProductionEvent;
    }

    public void setEntityProductionEvent(EntityProductionEvent entityProductionEvent) {
        this.entityProductionEvent = entityProductionEvent;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "{" + " scoreEvent='" + getEntityProductionEvent() + "'" + ", player='" + getCustomer() + "'" + "}";
    }
}
