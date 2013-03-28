package com.sample.blue;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@SuppressWarnings("FieldCanBeLocal")
public class BlueConfiguration extends Configuration {
    @Valid
    @NotNull
    @JsonProperty
    private DatabaseConfiguration database = new DatabaseConfiguration();

    public DatabaseConfiguration getDatabaseConfiguration() {
        return database;
    }

    public void setDatabaseConfiguration(DatabaseConfiguration database) {
        this.database = database;
    }

    // ====
    @NotEmpty
    @JsonProperty
    private String template;

    public String getTemplate() {
        return template;
    }

    // ====
    @NotEmpty
    @JsonProperty
    private String defaultName = "Stranger";

    public String getDefaultName() {
        return defaultName;
    }
}