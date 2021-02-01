package com.wenlincheng.pika.common.core.util;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyConfig extends PropertyPlaceholderConfigurer {

	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactoryToProcess,
			Properties props) throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		propertiesMap = new HashMap<>();
		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			propertiesMap.put(keyStr, props.getProperty(keyStr));
		}
	}

	private static Map<String, String> propertiesMap;

	public static String getContextProperty(String name) {
		return propertiesMap.get(name).trim();
	}
}
