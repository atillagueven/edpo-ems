package ch.unisg.ems.sales.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Customer {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    private String name;
    private String email;

    public String getName() {
        return name;
    }
    public Customer setName(String name) {
        this.name = name;
        return this;
    }
    public String getEmail() {
        return email;
    }
    public Customer setEmail(String address) {
        this.email = address;
        return this;
    }

}

