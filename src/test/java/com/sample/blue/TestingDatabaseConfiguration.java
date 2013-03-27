package com.sample.blue;

import com.google.common.collect.Maps;
import com.yammer.dropwizard.db.DatabaseConfiguration;

import java.util.HashMap;

public class TestingDatabaseConfiguration extends DatabaseConfiguration {
    public TestingDatabaseConfiguration() {
        setDriverClass("org.hsqldb.jdbc.JDBCDriver");
        setUrl("jdbc:hsqldb:mem:hib");
        setUser("sa");
        setPassword("");
        setProperty("hibernate.hbm2ddl.auto", "create-drop");
    }

    public void setProperty(String name, String value) {
        HashMap<String, String> props = Maps.newHashMap(getProperties());
        props.put(name, value);
        setProperties(props);
    }
}
