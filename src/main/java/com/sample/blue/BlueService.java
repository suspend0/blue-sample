package com.sample.blue;

import com.sample.blue.hello.HelloResource;
import com.sample.blue.hello.TemplateHealthCheck;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

public class BlueService extends Service<BlueConfiguration> {
    public static void main(String[] args) throws Exception {
        new BlueService().run(args);
    }

    @Override
    public void initialize(Bootstrap<BlueConfiguration> bootstrap) {
        bootstrap.setName("blue");
    }

    @Override
    public void run(BlueConfiguration configuration,
                    Environment environment) {
        final String template = configuration.getTemplate();
        final String defaultName = configuration.getDefaultName();
        environment.addResource(new HelloResource(template, defaultName));
        environment.addHealthCheck(new TemplateHealthCheck(template));
    }

}