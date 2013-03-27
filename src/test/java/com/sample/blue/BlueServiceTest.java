package com.sample.blue;

import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.hibernate.HibernateBundle;
import org.testng.annotations.Test;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;

public class BlueServiceTest {
    @Test
    public void serviceRunsWithoutError() throws Exception {
        @SuppressWarnings("unchecked")
        HibernateBundle<BlueConfiguration> hibernate = mock(HibernateBundle.class, RETURNS_DEEP_STUBS);

        BlueService service = new BlueService(hibernate);
        Environment environment = mock(Environment.class);
        BlueConfiguration config = new BlueConfiguration();

        service.run(config, environment);
    }
}