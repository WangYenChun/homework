package com.coupang.c4.step14.beanfactory;

import com.sun.deploy.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * Created by wangyenchun on 2014. 12. 18..
 */
public class ScopedBeanFactory implements BeanFactory{
    HashMap<String, BeanFactory> map = new HashMap<>();

    @Override
    public <T> T getInstance(Class<T> type) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return (T) map.get(type.getSimpleName()).getInstance(type);
    }

    @Override
    public Object getInstance(String beanName) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return map.get(beanName).getInstance(beanName);
    }

    @Override
    public void addNewBean(BeanInformation beanInformation) {
        switch (beanInformation.getScope()) {
            case SINGLETON:
                BeanFactory beanFactory = new SingletonBeanFactory();
                map.put(beanInformation.getBeanName(), beanFactory);
                beanFactory.addNewBean(beanInformation);
                break;
            case PROTOTYPE:
                beanFactory = new PrototypeBeanFactory();
                map.put(beanInformation.getBeanName(), beanFactory);
                beanFactory.addNewBean(beanInformation);
                break;
        }
    }
}
