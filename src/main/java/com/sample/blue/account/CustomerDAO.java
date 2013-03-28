package com.sample.blue.account;

import com.yammer.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

@Repository
public class CustomerDAO extends AbstractDAO<Customer> {
    @Inject
    public CustomerDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Customer> findAll() {
        return list(criteria());
    }
}
