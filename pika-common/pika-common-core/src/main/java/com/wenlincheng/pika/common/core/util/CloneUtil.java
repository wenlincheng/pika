/**
 * 
 */
package com.wenlincheng.pika.common.core.util;


import com.wenlincheng.pika.common.core.exception.PikaException;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static com.wenlincheng.pika.common.core.exception.SystemErrorCodeEnum.*;


/**
 * 对象复制工具类
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
public class CloneUtil {
	
	/**
	 * 复制对象
	 *
	 * @param obj 对象
	 * @return T
	 */
	public static <T> T cloneObject(T obj)
	{
		try {
			if(obj instanceof Collection || obj instanceof Set || obj instanceof Long 
					|| obj instanceof Integer || obj instanceof String ){
				return obj;
			}
			final T result = (T)obj.getClass().newInstance();
			PropertyUtils.copyProperties(result, obj);
			return result;
		} catch (Exception e) {
			throw PikaException.construct(CLONE_OBJECT_FAIL, e).appendMsg(obj.getClass().getName()).build();
		}
	}

	/**
	 * 批量复制对象
	 * 
	 * @param objs 对象列表
	 * @return java.util.List<T>
	 */
	public static <T> List<T> cloneObjects(final List<T> objs)
	{
		if(CollectionUtils.isEmpty(objs)){
			return new ArrayList<T>(0);
		}
		final List<T> result = new ArrayList<T>(objs.size());
		for(final T o : objs)
		{
			if(o instanceof Collection || o instanceof Set){
				result.add(o);
			}else{
				result.add((T) CloneUtil.cloneObject(o));
			}
		}
		return result;
	}
}
