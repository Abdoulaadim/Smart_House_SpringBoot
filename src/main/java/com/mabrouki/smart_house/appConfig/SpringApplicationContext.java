package com.mabrouki.smart_house.appConfig;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringApplicationContext implements ApplicationContextAware {

    private static ApplicationContext CONTEXT;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        CONTEXT = applicationContext;
    }


    //recuperer ou instance de classe  on peut utiliser cet function

    public static Object getBean(String beanName) {

        return CONTEXT.getBean(beanName);
    }

}
