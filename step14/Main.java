package com.coupang.c4.step14;

import com.coupang.c4.step14.beanfactory.AgentBeanFactory;
import com.coupang.c4.step14.beanfactory.ScopedBeanFactory;
import com.coupang.c4.step14.beans.Sample1;
import com.coupang.c4.step14.beans.Sample2;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


public class Main {
	public static void main(String[] args) {
		String propertyPath = "/com/coupang/c4/step14/bean-definitions.properties";
		try {
			AgentBeanFactory agentBeanFactory = new AgentBeanFactory(new ScopedBeanFactory(), propertyPath);

			Sample1 instance1 = null;
			instance1 = agentBeanFactory.getInstance(Sample1.class);
			System.out.println("instance1 != null -" +  (instance1 != null));

			Object instance2 = null;
			instance2 = agentBeanFactory.getInstance("Sample2");
			System.out.println("instance1 != null -" + (instance2 != null));

			System.out.println("Sample2.class.isAssignableFrom(instance2.getClass()) - " +
					Sample2.class.isAssignableFrom(instance2.getClass()));
			System.out.println("instance2 instanceof Sample2 - " + (instance2 instanceof Sample2));

			System.out.println("prototype instance -" + agentBeanFactory.getInstance("Sample2"));
			System.out.println("prototype instance -" + agentBeanFactory.getInstance("Sample2"));
			System.out.println("singleton instance -" + agentBeanFactory.getInstance("Sample1"));
			System.out.println("singleton instance -" + agentBeanFactory.getInstance("Sample1"));

		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}


