package org.springframework.beans;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import javax.sql.DataSource;

public class SpringBeans {

	/**
	 * 基础容器，源码阅读Debug入口
	 *
	 */
	@Test
	public void test() {
		//xml资源路径，test优先在test 的resources下找
		String path = "beans.xml";
		//创建 DefaultListableBeanFactory工厂，这就是spring的基础容器（另一个是高级容器）
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		//创建beanDefinition阅读器
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
		//beanDefinition注册流程
		beanDefinitionReader.loadBeanDefinitions(path);
		//bean实例创建流程
		DataSource dataSource = (DataSource) beanFactory.getBean("dataSource");

		System.out.println(dataSource);
	}

	@Test
	public void test1(){

	}
}