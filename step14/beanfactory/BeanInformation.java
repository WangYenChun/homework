package com.coupang.c4.step14.beanfactory;

/**
 * Created by wangyenchun on 2014. 12. 16..
 */
public class BeanInformation {
    private String beanName;
    private String fullBeanName;
    private Scope scope;

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getFullBeanName() {
        return fullBeanName;
    }

    public void setFullBeanName(String fullBeanName) {
        this.fullBeanName = fullBeanName;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }
}
