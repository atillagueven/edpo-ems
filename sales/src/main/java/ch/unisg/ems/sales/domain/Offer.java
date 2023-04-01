package ch.unisg.ems.sales.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name="OfferEntity")
public class Offer {

    @Id
//  @GeneratedValue(generator = "uuid2")
//  @GenericGenerator(name = "uuid2", strategy = "uuid2")
    protected String id; // = UUID.randomUUID().toString();
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER  )
    protected Customer customer = new Customer();


    public String getId() {
        return id;
    }

    @JsonProperty("orderId")
    public void setId(String id) {
        this.id = id;
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
