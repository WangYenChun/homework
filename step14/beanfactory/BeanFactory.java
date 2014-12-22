package com.coupang.c4.step14.beanfactory;

import java.lang.reflect.InvocationTargetException;

public interface BeanFactory {
	<T> Object getInstance(Class<T> type) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException;
	Object getInstance(String beanName) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
	void addNewBean(BeanInformation beanInformation);
}
