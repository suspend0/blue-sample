package com.sample.blue;

import com.google.inject.*;
import com.sample.blue.account.Account;
import com.sample.blue.account.AccountModule;
import com.sample.blue.account.Customer;
import com.sample.blue.account.CustomerResource;
import com.sample.blue.hello.HelloResource;
import com.sample.blue.hello.TemplateHealthCheck;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import com.yammer.dropwizard.hibernate.HibernateBundle;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

public class BlueService extends Service<BlueConfiguration> {
    public static void main(String[] args) throws Exception {
        new BlueService().run(args);
    }

    public static final boolean GUICE = false;

    private final HibernateBundle<BlueConfiguration> hibernate;

    public BlueService() {
        this(new HibernateBundle<BlueConfiguration>(Customer.class, Account.class) {
            @Override
            public DatabaseConfiguration getDatabaseConfiguration(BlueConfiguration configuration) {
                return configuration.getDatabaseConfiguration();
            }
        });
    }

    BlueService(HibernateBundle<BlueConfiguration> hibernate) {
        this.hibernate = hibernate;
    }

    @Override
    public void initialize(Bootstrap<BlueConfiguration> bootstrap) {
        bootstrap.setName("blue");
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(BlueConfiguration configuration,
                    Environment environment) {
        // Hello
        final String template = configuration.getTemplate();
        final String defaultName = configuration.getDefaultName();
        environment.addResource(new HelloResource(template, defaultName));
        environment.addHealthCheck(new TemplateHealthCheck(template));

        // Account
        if (GUICE) {
            Injector account = Guice.createInjector(new AccountModule(), new ServiceModule());
            environment.addResource(account.getInstance(CustomerResource.class));
        } else {
            AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
            ctx.getBeanFactory().registerResolvableDependency(SessionFactory.class, new SpringHibernate());
            ctx.register(SpringConfig.class);
            ctx.refresh();
            environment.addResource(ctx.getBean(CustomerResource.class));
        }
    }

    private class ServiceModule implements Module {
        @Override
        public void configure(Binder binder) {
            // provider methods only
        }

        @Provides
        public SessionFactory provideSessionFactory() {
            return hibernate.getSessionFactory();
        }
    }

    @Configuration
    @ComponentScan("com.sample.blue.account")
    public static class SpringConfig {
    }

    private final class SpringHibernate implements ObjectFactory{
        @Override
        public Object getObject() throws BeansException {
            return hibernate.getSessionFactory();
        }
    }
}