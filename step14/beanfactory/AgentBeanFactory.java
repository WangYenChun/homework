package com.coupang.c4.step14.beanfactory;

import com.coupang.c4.ResourceUtil;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wangyenchun on 2014. 12. 16..
 */
public class AgentBeanFactory implements BeanFactory {
    private BeanFactory beanFactory;

    public  AgentBeanFactory(BeanFactory beanFactory, String propertyPath) throws IOException {
        //properties 파일 parsing
        System.out.println("propertyPath - " + propertyPath);
        this.beanFactory = beanFactory;
        InputStream inputStream = ResourceUtil.resourceAsInputStream(propertyPath);
        BeanInformation[] beanInformations = loadBeans(inputStream);
        for (BeanInformation beanInformation : beanInformations) {
            this.addNewBean(beanInformation);
        }
    }

    @Override
    public <T> T getInstance(Class<T> type) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return (T) beanFactory.getInstance(type);
    }

    @Override
    public Object getInstance(String beanName) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return beanFactory.getInstance(beanName);
    }

    @Override
    public void addNewBean(BeanInformation beanInformation) {
        this.beanFactory.addNewBean(beanInformation);
    }

    BeanInformation[] loadBeans(InputStream inputStream) throws IOException {
        String[] lines = ResourceUtil.readFully(inputStream);
        List<BeanInformation> result = new ArrayList<BeanInformation>();
        if (lines != null) {
            for (String line : lines) {
                BeanInformation bean = new BeanInformation();
                String[] parsed = line.split("=");
                bean.setBeanName(parsed[0]);
                bean.setFullBeanName(parsed[1]);
                bean.setScope(Scope.valueOf(parsed[2]));
                result.add(bean);
            }
        }
        return result.toArray(new BeanInformation[0]);
    }
}
