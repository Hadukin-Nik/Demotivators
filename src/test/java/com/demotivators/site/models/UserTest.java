package com.demotivators.site.models;

import ch.qos.logback.classic.LoggerContext;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private final Long ID = 12L;
    @Test
    public void givenSingletonScope_whenSetName_thenEqualNames() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("scopes.xml");

        User personSingletonA = (User) applicationContext.getBean("personSingleton");
        User personSingletonB = (User) applicationContext.getBean("personSingleton");

        personSingletonA.setId(ID);
        assertEquals(ID, personSingletonB.getId());
        ((AbstractApplicationContext) applicationContext).close();
    }

}