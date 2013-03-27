package com.sample.blue;

import com.yammer.dropwizard.db.DatabaseConfiguration;

public class TestingDatabaseConfiguration extends DatabaseConfiguration {
    public TestingDatabaseConfiguration() {
        setDriverClass("org.hsqldb.jdbc.JDBCDriver");
        setUrl("jdbc:hsqldb:mem:hib");
        setUser("sa");
        setPassword("");
    }
}
