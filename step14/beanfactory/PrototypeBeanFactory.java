package com.coupang.c4.step14.beanfactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * Created by wangyenchun on 2014. 12. 18..
 */
public class PrototypeBeanFactory implements BeanFactory{
    HashMap<String, BeanInformation> map = new HashMap<>();

    @Override
    public <T> T getInstance(Class<T> type) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Constructor declaredConstructor = type.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        return (T) declaredConstructor.newInstance();
    }

    @Override
    public Object getInstance(String beanName) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> forName = null;
        forName = Class.forName(map.get(beanName).getFullBeanName());
        Constructor declaredConstructor = forName.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        return declaredConstructor.newInstance();
    }

    @Override
    public void addNewBean(BeanInformation beanInformation) {
        map.put(beanInformation.getBeanName(), beanInformation);
    }
}
