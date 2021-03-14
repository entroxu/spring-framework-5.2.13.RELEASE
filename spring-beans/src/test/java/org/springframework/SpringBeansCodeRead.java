package org.springframework;

import org.junit.jupiter.api.Test;
import org.springframework.beans.DataSource;
import org.springframework.beans.UserDao;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import java.lang.reflect.Field;

public class SpringBeansCodeRead {

	/**
	 * 早期 XmlBeanFactory
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 */
	@Test
	public void test1() throws NoSuchFieldException, IllegalAccessException {
		//xml资源路径，test优先在test 的resources下找
		String path = "spring/beans.xml";
		//创建 XmlBeanFactory，这就是spring的基础容器（另一个是高级容器）
		XmlBeanFactory xmlBeanFactory  = new XmlBeanFactory(new ClassPathResource(path));

		DataSource dataSource =  xmlBeanFactory.getBean(DataSource.class);
		System.out.println("注入结果 dataSource.url:"+dataSource);
	}


	/**
	 * 基础容器，源码阅读Debug入口
	 *
	 */
	@Test
	public void test() throws NoSuchFieldException, IllegalAccessException {
		//xml资源路径，test优先在test 的resources下找
		String path = "spring/beans.xml";
		//创建 DefaultListableBeanFactory工厂，这就是spring的基础容器（另一个是高级容器）
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		//创建beanDefinition阅读器
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
		//beanDefinition注册流程 #######源码阅读入口#########
		beanDefinitionReader.loadBeanDefinitions(path);
		//bean实例创建流程

		//UserService userService = beanFactory.getBean(UserService.class);
		DataSource dataSource = (DataSource) beanFactory.getBean("dataSource");
		//懒加载，第二个次获取才会加入map中
		DataSource dataSource1 = (DataSource) beanFactory.getBean("dataSource");
		//scope="prototype"会多次实例化
		beanFactory.getBean(UserDao.class);
		beanFactory.getBean(UserDao.class);

		Field field = dataSource.getClass().getDeclaredField("url");
		field.setAccessible(true);
		String url = (String)field.get(dataSource);
		System.out.println("注入结果 dataSource.url:"+url);
	}





}