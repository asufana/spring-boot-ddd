package com.github.asufana.ddd.utils;

import org.springframework.beans.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;

@Component
//http://stackoverflow.com/questions/28365154/autowired-not-working-in-a-class-entity
public class SpringBeans implements ApplicationContextAware {
    
    private static final String ERR_MSG = "Spring utility class not initialized";
    
    private static ApplicationContext context;
    
    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
    
    public static <T> T find(final Class<T> clazz) {
        if (context == null) {
            throw new IllegalStateException(ERR_MSG);
        }
        return context.getBean(clazz);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> T find(final String name) {
        if (context == null) {
            throw new IllegalStateException(ERR_MSG);
        }
        return (T) context.getBean(name);
    }
}
