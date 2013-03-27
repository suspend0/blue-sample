package com.sample.blue.account;

import com.google.common.collect.ImmutableList;
import com.sample.blue.TestingDatabaseConfiguration;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.hibernate.SessionFactoryFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.util.List;

public class CustomerDAOTest {
    @Test
    public void testFindAll() throws Exception {
        Environment environment = Mockito.mock(Environment.class);
        TestingDatabaseConfiguration dbConfig = new TestingDatabaseConfiguration();
        dbConfig.setProperty(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        List<Class<?>> classes = ImmutableList.of(Customer.class, Account.class);
        SessionFactory sessionFactory = new SessionFactoryFactory().build(environment, dbConfig, classes);
        CustomerDAO dao = new CustomerDAO(sessionFactory);

        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            dao.findAll();
        } finally {
            session.close();
        }
    }
}
