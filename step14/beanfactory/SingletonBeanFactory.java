package com.coupang.c4.step14.beanfactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class SingletonBeanFactory implements BeanFactory{
    HashMap<Class<?>, Object> map = new HashMap<>();
    HashMap<String, BeanInformation> beanMap = new HashMap<>();

    public <T> T getInstance(Class<T> type) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Constructor<T> declaredConstructor = null;
        declaredConstructor = type.getDeclaredConstructor();
        return (T) checkInstance(type, declaredConstructor);
    }

    public Object getInstance(String beanName) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<?> forName = Class.forName(beanMap.get(beanName).getFullBeanName());
        Constructor declaredConstructor = forName.getDeclaredConstructor();
        return checkInstance(forName, declaredConstructor);
    }

    private Object checkInstance(Class<?> clazz, Constructor constructor) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        if(map.containsKey(clazz)) { return map.get(clazz); }
        constructor.setAccessible(true);
        Object instance = constructor.newInstance();
        map.put(clazz, instance);
        return instance;
    }

    @Override
    public void addNewBean(BeanInformation beanInformation) {
        beanMap.put(beanInformation.getBeanName(), beanInformation);
    }
}
