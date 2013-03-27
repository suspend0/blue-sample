package com.sample.blue;

import com.google.common.collect.Lists;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.hibernate.SessionFactoryFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.mockito.Mockito;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.List;

/**
 * Every method is run within a transaction which is rolled back.
 */
public abstract class AbstractDAOTest {
    private final List<Class<?>> entities;
    protected SessionFactory sessionFactory;

    protected AbstractDAOTest(Class<?> entity, Class<?>... others) {
        this.entities = Lists.asList(entity, others);
    }

    @BeforeClass
    public final void setup() throws ClassNotFoundException {
        Environment environment = Mockito.mock(Environment.class);
        TestingDatabaseConfiguration dbConfig = new TestingDatabaseConfiguration();
        dbConfig.setProperty(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        sessionFactory = new SessionFactoryFactory().build(environment, dbConfig, entities);

        Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(SessionFactory.class).toInstance(sessionFactory);
            }
        }).injectMembers(this);
    }

    @BeforeMethod
    public final void begin() {
        sessionFactory.getCurrentSession().beginTransaction();
    }

    @AfterMethod
    public final void end() {
        sessionFactory.getCurrentSession().getTransaction().rollback();
    }
}
