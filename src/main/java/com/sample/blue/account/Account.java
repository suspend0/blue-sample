package com.sample.blue.account;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Oliver Gierke
 */
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Customer customer;

    @Temporal(TemporalType.DATE)
    private Date expiryDate;

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }
}
