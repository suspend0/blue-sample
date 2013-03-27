package com.sample.blue.account;

import com.sample.blue.AbstractDAOTest;
import junit.framework.Assert;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.Collections;

public class CustomerDAOTest extends AbstractDAOTest {
    @Inject
    CustomerDAO dao;

    public CustomerDAOTest() {
        super(Customer.class, Account.class);
    }

    @Test
    public void testFindAll() throws Exception {
        //noinspection AssertEqualsBetweenInconvertibleTypes
        Assert.assertEquals(Collections.emptyList(), dao.findAll());
    }
}
