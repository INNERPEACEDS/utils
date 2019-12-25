package com.wgb.utils.util.spring;

/**
 * 获取spring管理的bean
 * 
 * @author admini
 *
 */
public class BeanFactory {
    /**
     * 获取Spring管理的Bean
     * 
     * @param beanName bean名称
     * @return
     */
    public static Object getBean(String beanName) {
        return SpringContext.getContext().getBean(beanName);
    }
}
