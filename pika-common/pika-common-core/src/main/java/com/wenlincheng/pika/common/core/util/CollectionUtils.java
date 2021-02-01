package com.wenlincheng.pika.common.core.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.util.*;
import java.util.Map.Entry;

/**
 * 集合工具类
 * @author zhulm
 */
@Slf4j
public class CollectionUtils {
	

	/*************
	 * 排序
	 * @author zhuliming
	 *
	 */
	public static enum SortOrder{
		ASC("asc"), //正序
		DESC("desc"); //逆序
		public final String order;
		private SortOrder(String o){
			this.order = o;
		}
		public static final SortOrder parse(final String desc){
			if(ASC.order.equalsIgnoreCase(desc)){
				return ASC;
			}
			return DESC;
		}
	}

	/**********
	 * 如果是null， 则返回空list
	 */
	public static final <T> List<T> emptyListIfNull(final List<T> l){
		return l == null ? new ArrayList<T>(0) : l;		
	}
	
   /**
    * 描述： 根据指定的字段数组对List中的对象进行排序
    * 
    * @param list        待排序记集合
    * @param sortFields  指定排序字段数组
    * @param sortOrder   排序方式
    */
	public static <T> void sortTheList(List<T> list, String[] sortFields, final SortOrder sortOrder){
        if(isEmpty(list) || list.size() < 1 || 
        		sortFields == null || sortFields.length <= 0){
            return;
        }
        final List<BeanComparator> sorts = new ArrayList<BeanComparator>();
        Comparator c = ComparableComparator.getInstance();
        c = ComparatorUtils.nullLowComparator(c);  //允许null
        if (SortOrder.DESC == sortOrder){
            c = ComparatorUtils.reversedComparator(c); //逆序
        }

        String sortField = null ;
        for(int i=0 ; i< sortFields.length ; i++){
            sortField = sortFields[i];
            if(!StringUtils.isEmpty(sortField)) {
                sorts.add(new BeanComparator(sortField,c));
            }
        }
        ComparatorChain multiSort = new ComparatorChain(sorts);
        Collections.sort(list, multiSort);
    }

    /**
     * 描述：根据指定的字段对List中的对象进行排序
     * 
     * @param list       待排序记集合
     * @param sortField  指定排序字段
     * @param sortOrder  排序方式
     * 
     */
    public static <T> void sortTheList(List<T> list, String sortField, final SortOrder sortOrder)
    {
        if(isEmpty(list) || StringUtils.isEmpty(sortField)){
            return;
        }
        String[] sortFields = new String[]{sortField};
        sortTheList(list, sortFields, sortOrder);
    }
    
    /**
	 * 提取集合中的对象的两个属性(通过Getter函数), 组合成Map.
	 * @param collection 来源集合.
	 * @param keyPropertyName 要提取为Map中的Key值的属性名.
	 * @param valuePropertyName 要提取为Map中的Value值的属性名.
	 */
	@SuppressWarnings("unchecked")
	public static Map extractPropsToMap(final Collection collection, final String keyPropertyName,
			final String valuePropertyName) {
		if(isEmpty(collection) || StringUtils.isEmpty(keyPropertyName) 
				|| StringUtils.isEmpty(valuePropertyName)){
			
			return new HashMap();
		}
		Map map = new HashMap(collection.size());
		for (Object obj : collection) {
			try {
				map.put(PropertyUtils.getProperty(obj, keyPropertyName),
						PropertyUtils.getProperty(obj, valuePropertyName));
			} catch (Exception e) {
				throw new RuntimeException("Fail to getProperty of " + keyPropertyName + " from class " + obj.getClass().getSimpleName(), e);
			}
		}

		return map;
	}
	
	/**
	 * 提取集合中的指定属性，并将属性值作为key，obj作为value
	 * @param collection		集合
	 * @param keyPropertyName  属性名
	 * @return map
	 */
	public static Map extractConllectionToMap(final Collection collection, final String keyPropertyName){
		if(isEmpty(collection) || StringUtils.isEmpty(keyPropertyName)){
			return new HashMap();
		}
		
		Map map = new HashMap(collection.size());
		for(Object obj : collection){
			try {
				map.put(PropertyUtils.getProperty(obj,keyPropertyName), obj);
			} catch (Exception e) {
				throw new RuntimeException("Fail to getProperty of " + keyPropertyName + " from class " + obj.getClass().getSimpleName(), e);
			}
		}
		return map;
	}
	
	/**
	 * 将list集合转化为Map
	 * key 为List中指定的属性
	 * value 为key所对应的list
	 * @param collection		集合
	 * @param keyPropertyName	key
	 * @return
	 */
	public static <K,V> Map<K,List<V>> convertListToMap(final Collection<? extends V> collection, final String keyPropertyName)
	{
		final Map<K,List<V>> result = new HashMap<K,List<V>>();
		if(isEmpty(collection)){
			return result;
		}
		try {
			for(V obj:collection){
				@SuppressWarnings("unchecked")
				K k = (K) PropertyUtils.getProperty(obj, keyPropertyName);
				List<V> list = result.get(k);
				if(list == null){
					list = new LinkedList<V>();
				}
				list.add(obj);
				result.put(k, list);
			}
		} catch (Exception e) {
			throw new RuntimeException("Fail to getProperty of " + keyPropertyName + " from class " 
					+ collection.iterator().next().getClass().getSimpleName(), e);
		}
		return result;
	}
	
	
	/**
	 * 提取集合中指定属性值及其出现的次数，
	 * 并以属性值作为key，出现的次数作为value封装成Map返回
	 * @param collection		待处理集合
	 * @param propertyName	属性名称
	 * @return
	 */
	public static Map<Object,Integer> extractConllectionPropCount(final Collection collection, final String propertyName){
		if(isEmpty(collection) || StringUtils.isEmpty(propertyName)){
			return Collections.emptyMap();
		}
		List list = extractDuplicateProps(collection, propertyName);
		Map<Object,Integer> map = new HashMap<Object,Integer>();
		if(isNotEmpty(list)){
			for(Object obj:list){
				Integer count = map.get(obj);
				if(count == null) {
					map.put(obj, 1);
				} else {
					map.put(obj, count.intValue() + 1);
				}
			}
		}
		return map;
	}
	
	
	/**
	 * 提取集合中的对象的一个属性(通过Getter函数), 组合成List.
	 * <p>
	 *   使用Set 去除重复的属性值
	 * </p>
	 * @param collection 	来源集合.
	 * @param propertyName 要提取的属性名.
	 */
	@SuppressWarnings("unchecked")
	public static List extractUniqueProps(final Collection collection, final String propertyName) {
		if(isEmpty(collection) || StringUtils.isEmpty(propertyName)){
			return Collections.emptyList();
		}
		
		List list = new ArrayList(collection.size());
		Set set = new HashSet(collection.size());
		for (Object obj : collection) {
			if(obj == null){
				continue;
			}
			try {
				set.add(PropertyUtils.getProperty(obj, propertyName));
			} catch (Exception e) {
				throw new RuntimeException("Fail to getProperty of " + propertyName + " from class " + obj.getClass().getSimpleName(), e);
			}
		}
		list.addAll(set);
		return list;
	}
	
	
	/**
	 * 提取集合中的对象的一个属性(通过Getter函数), 组合成List.
	 * <p>
	 *   使用Set 去除重复的属性值
	 * </p>
	 * @param collection 	来源集合.
	 * @param propertyName 要提取的属性名. extractPropSetFromCollection
	 */
	@SuppressWarnings("unchecked")
	public static List extractDuplicateProps(final Collection collection, final String propertyName) {
		if(isEmpty(collection) || StringUtils.isEmpty(propertyName)){
			return new ArrayList(0);
		}
		return extractDuplicateProps(collection.toArray(), propertyName);
	}
	
	public static Long[] extractIdPropsArray(final Collection collection, final String idPropertyName) {
		if(isEmpty(collection) || StringUtils.isEmpty(idPropertyName)){
			return new Long[0];
		}
		final Long[] array = new Long[collection.size()];
		int index = -1;
		for (Object obj : collection) {
			if(obj == null){
				array[++index] = 0L;
				continue;
			}
			try {
				array[++index] = (Long)PropertyUtils.getProperty(obj, idPropertyName);
			} catch (Exception e) {
				throw new RuntimeException("Fail to getProperty of " + idPropertyName + " from class " + obj.getClass().getSimpleName(), e);
			}
		}
		return array;
	}
	
	/**
	 * 提取集合中的对象的一个属性(通过Getter函数), 组合成List.
	 * <p>
	 *   使用Set 去除重复的属性值
	 * </p>
	 * @param collection 	来源集合.
	 * @param propertyName 要提取的属性名. extractPropSetFromCollection
	 * @param maxCount: 表示需要的最大个数
	 */
	public static List extractDuplicateProps(final Collection collection, final String propertyName, int maxCount)
	{
		if(isEmpty(collection) || StringUtils.isEmpty(propertyName) || maxCount < 1){
			return new ArrayList(0);
		}
		final List list = new ArrayList(Math.min(collection.size(),   maxCount));
		for (final Object obj : collection) {
			if(obj == null){
				continue;
			}
			try {
				list.add(PropertyUtils.getProperty(obj, propertyName));
				if(-- maxCount < 1){
					return list;
				}
			} catch (Exception e) {
				throw new RuntimeException("Fail to getProperty of " + propertyName + " from class " + obj.getClass().getSimpleName(), e);
			}
		}
		return list;
	}
		
	public static List extractDuplicateProps(final Object[] collection, final String propertyName) {
		if(ArrayUtils.isEmpty(collection) || StringUtils.isEmpty(propertyName)){
			return new ArrayList(0);
		}
		List list = new ArrayList(collection.length);
		for (Object obj : collection) {
			if(obj == null){
				continue;
			}
			try {
				list.add(PropertyUtils.getProperty(obj, propertyName));
			} catch (Exception e) {
				throw new RuntimeException("Fail to getProperty of " + propertyName + " from class " + obj.getClass().getSimpleName(), e);
			}
		}
		return list;
	}
	
	/**
	 * 提取集合中的对象的一个属性(通过Getter函数), 组合成由分割符分隔的字符串.
	 * @param collection 来源集合.
	 * @param propertyName 要提取的属性名.
	 * @param separator 分隔符.
	 */
	public static String extractToString(final Collection collection, final String propertyName, final String separator) {
		List list = extractDuplicateProps(collection, propertyName);
		return StringUtils.join(list, separator);
	}

	/**
	 * 转换Collection所有元素(通过toString())为String, 中间以 separator分隔。
	 */
	public static String convertToString(final Collection collection, final String separator) {
		if(isEmpty(collection)){
			return StringUtils.EMPTY;
		}
		return StringUtils.join(collection, separator);
	}

	/**
	 * 转换Collection所有元素(通过toString())为String, 每个元素的前面加入prefix，后面加入postfix，如<div>mymessage</div>。
	 */
	public static String convertToString(final Collection collection, final String prefix, final String postfix) {
		if(isEmpty(collection)){
			return StringUtils.EMPTY;
		}
		StringBuilder builder = new StringBuilder();
		for (Object o : collection) {
			builder.append(prefix).append(o).append(postfix);
		}
		return builder.toString();
	}

	/**
	 * 判断是否为空.
	 */
	public static boolean isEmpty(Collection collection) {
		return (collection == null || collection.isEmpty());
	}
	
	/**
	 * 判断是不为空.
	 */
	public static boolean isNotEmpty(Collection collection) {
		return (collection != null && !collection.isEmpty());
	}

	/**
	 * 取得Collection的第一个元素，如果collection为空返回null.
	 */
	public static <T> T getFirst(Collection<T> collection) {
		if (isEmpty(collection)) {
			return null;
		}

		return collection.iterator().next();
	}

	/**
	 * 获取Collection的最后一个元素 ，如果collection为空返回null.
	 */
	public static <T> T getLast(Collection<T> collection) {
		if (isEmpty(collection)) {
			return null;
		}

		//当类型为List时，直接取得最后一个元素 。
		if (collection instanceof List) {
			List<T> list = (List<T>) collection;
			return list.get(list.size() - 1);
		}

		//其他类型通过iterator滚动到最后一个元素.
		Iterator<T> iterator = collection.iterator();
		while (true) {
			T current = iterator.next();
			if (!iterator.hasNext()) {
				return current;
			}
		}
	}
	
	/**
	 * 返回a+b的新List.
	 */
	public static <T> List<T> union(final Collection<T> a, final Collection<T> b) {
		List<T> result = new ArrayList<T>(a);
		result.addAll(b);
		return result;
	}

	/**
	 * 返回a-b的新List.
	 */
	public static <T> List<T> subtract(final Collection<T> a, final Collection<T> b) {
		List<T> list = new ArrayList<T>(a);
		for (T element : b) {
			list.remove(element);
		}

		return list;
	}

	/**
	 * 返回a与b的交集的新List.
	 */
	public static <T> List<T> intersection(Collection<T> a, Collection<T> b) {
		List<T> list = new ArrayList<T>();

		for (T element : a) {
			if (b.contains(element)) {
				list.add(element);
			}
		}
		return list;
	}
	//======================================================
	// Arrays
	//======================================================
	
	/**
	 * 查询重复元素
	 * @param array
	 * @return
	 */
	public static List<Long> findDuplicateElement(long[] array){
		final List<Long> duplicateSet = new ArrayList<Long>();
		final Set<Long> set = new HashSet<Long>();
		for(long e : array){
			if(!set.add(e)){
				duplicateSet.add(e);
			}
		}
		return duplicateSet;
	}
	
	/**
	 * 移除元素
	 * @param array
	 * @param element
	 * @return
	 */
	public static long[] removeElement(long[] array, long element){
		if(ArrayUtils.isEmpty(array)){
			return ArrayUtils.EMPTY_LONG_ARRAY;
		}
		List<Long> list = new ArrayList<Long>();
		for(long arrayEle : array){
			if(arrayEle != element){
				list.add(arrayEle);
			}
		}
		return ArrayUtils.toPrimitive(list.toArray(new Long[list.size()]));
	}
	
	/****************
	 * 获取子集合
	 * @param list
	 * @param limit
	 * @param offset
	 * @return
	 */
	public static final <T> List<T> subList(List<T> list, final int limit, final int offset){
		if(list == null || (limit + offset) < 1){
			return new ArrayList<T>(0);	
		}
		final int start = (offset > 0) ? offset : 0;
		final int end = (list.size() < (start + limit)) ? list.size() : (start + limit);
		if(end > start) {
			final List<T> subList =  list.subList(start, end);
			final List<T> result = new ArrayList<T>(subList.size());
			result.addAll(subList);
			return result;
		}else{
			return new ArrayList<T>(0);	
		}
	}
	
	public static final <T> List<T> subListByRandom(List<T> list, final int limit){
		if(list == null){
			return new ArrayList<T>(0);	
		}		
		if(list.size() <= limit){
			return list;
		}
		int start = (int)(System.currentTimeMillis() % list.size());
		if(start > list.size() - limit){
			start = list.size() - limit;
		}
		return list.subList(start, start + limit);
	}	
	
	/********************
	 * 转成set集合
	 * @param ids
	 * @return
	 */
	public static final Set<Long> toSet(final List<Long> ids){
		final Set<Long> set = new HashSet<Long>();
		if(CollectionUtils.isEmpty(ids)){
			return set;
		}
		for(long id: ids){
			set.add(id);
		}
		return set;
	}
	public static final Set<Long> toSet(final long[] ids){
		final Set<Long> set = new HashSet<Long>();
		if(ArrayUtils.isEmpty(ids)){
			return set;
		}
		for(long id: ids){
			set.add(id);
		}
		return set;
	}
	public static final Set<String> toSet(final char[] types){
		final Set<String> set = new HashSet<String>();
		if(ArrayUtils.isEmpty(types)){
			return set;
		}
		for(char type: types){
			set.add(String.valueOf(type));
		}
		return set;
	}
	public static final Set<Long> toSet(final Long[] ids){
		Set<Long> set = new HashSet<Long>();
		if(ArrayUtils.isEmpty(ids)){
			return set;
		}
		for(long id: ids){
			set.add(id);
		}
		return set;
	}
	public static final Long[] toArray(final long[] ids){
		if(ArrayUtils.isEmpty(ids)){
			return new Long[0];
		}
		final Long[] array = new Long[ids.length];
		for(int i=0; i<ids.length; i++){
			array[i] = ids[i];
		}
		return array;
	}
	public static final Set<String> toStringSet(final List<String> values){
		final Set<String> set = new HashSet<String>();
		if(values == null || values.size() < 1){
			return set;
		}
		for(final String v: values){
			set.add(v);
		}
		return set;
	}
	public static final <T> List<T> toList(final Set<T> objs){
		List<T> set = new ArrayList<T>(objs.size());
		if(CollectionUtils.isEmpty(objs)){
			return set;
		}
		for(T obj: objs){
			set.add(obj);
		}
		return set;
	}
	public static final Set<Integer> toSet(final int[] types){
		Set<Integer> set = new HashSet<Integer>();
		if(ArrayUtils.isEmpty(types)){
			return set;
		}
		for(int type: types){
			set.add(type);
		}
		return set;
	}
	public static final Set<String> toSet(final String[]... typeArrays){
		Set<String> set = new HashSet<String>();
		if(ArrayUtils.isEmpty(typeArrays)){
			return set;
		}
		for(final String[] types : typeArrays){
			if(ArrayUtils.isEmpty(types)){
				continue;
			}
			for(String type: types){
				set.add(type);
			}
		}
		return set;
	}
	
	/***********
	 * 找到第一个对应的值 
	 * @param collection
	 * @param v
	 * @param ingoreCaseSensitive
	 * @return
	 */
	public static String findFirstStringValue(final String[] collection, final String v, 
			final boolean ingoreCaseSensitive){
		if(collection == null || collection.length < 1 || v == null){
			return null;
		}
		for(final String obj : collection){
			if(ingoreCaseSensitive){
				if(v.equalsIgnoreCase(obj)){
					return obj;
				}
			} else{
				if(v.equals(obj)){
					return obj;
				}
			}
		}
		return null;
	}
	
	/**
	 * 提取集合中的指定属性对应的值， 不存在的话返回null
	 * @param collection		集合
	 * @param keyPropertyName  属性名
	 * @return map
	 */
	public static <T2> T2 findObjectById(final Collection<T2> collection, final String keyPropertyName, final long key){
		if(collection == null || collection.size() < 1){
			return null;
		}
		for(final T2 obj : collection){
			try {
				if((Long)PropertyUtils.getProperty(obj, keyPropertyName) == key){
					return obj;
				}
			} catch (Exception e) {
				throw new RuntimeException("Fail to getProperty of " + keyPropertyName + " from class " + obj.getClass().getSimpleName(), e);
			}
		}
		return null;
	}
	public static <T2> T2 findObjectById(final Collection<T2> collection, final String keyPropertyName, final String key){
		if(collection == null || collection.size() < 1){
			return null;
		}
		final Map<String, T2> map = CollectionUtils.extractConllectionToMap(collection, keyPropertyName);
		return map.get(key);
	}

	/***************
	 * 计算列表id相等的元素个数
	 * @param collection
	 * @param keyPropertyName
	 * @param key
	 * @return
	 */
	public static <T2> int calculateObjectCountById(final Collection<T2> collection, final String keyPropertyName, final long key){
		if(collection == null || collection.size() < 1){
			return 0;
		}
		int count = 0;
		for(T2 obj : collection)
		{
			try {
				if((Long)PropertyUtils.getProperty(obj,keyPropertyName) == key){
					count ++;
				}
			} catch (Exception e) {
				throw new RuntimeException("Fail to getProperty of " + keyPropertyName + " from class " + obj.getClass().getSimpleName(), e);
			}
		}
		return count;
	}

	/***************
	 * 批量查询
	 * @param collection
	 * @param keyPropertyName
	 * @param key
	 * @return
	 */
	public static <T2> List<T2> findObjectsById(final Collection<T2> collection, final String keyPropertyName, final long key){
		if(collection == null || collection.size() < 1){
			return new ArrayList<T2>(0);
		}
		final List<T2> result = new LinkedList<T2>();
		for(final T2 obj : collection){
			try {
				if(obj != null && (Long)PropertyUtils.getProperty(obj, keyPropertyName) == key){
					result.add(obj);
				}
			} catch (Exception e) {
				throw new RuntimeException("Fail to getProperty of " + keyPropertyName + " from class " + obj.getClass().getSimpleName(), e);
			}
		}
		return result;
	}
	
	public static <T2> List<T2> findObjectsByIds(final Collection<T2> collection, final String keyPropertyName, final Long[] keys){
		if(collection == null || collection.size() < 1 || ArrayUtils.isEmpty(keys)){
			return new ArrayList<T2>(0);
		}
		final Set<Long> keysSet = CollectionUtils.toSet(keys);
		final List<T2> result = new LinkedList<T2>();
		for(final T2 obj : collection){
			try {
				if(obj != null && keysSet.contains((Long)PropertyUtils.getProperty(obj, keyPropertyName))){
					result.add(obj);
				}
			} catch (Exception e) {
				throw new RuntimeException("Fail to getProperty of " + keyPropertyName
						+ " from class " + obj.getClass().getSimpleName(), e);
			}
		}
		return result;
	}

	public static <T2> List<T2> findObjectsByIds(final Collection<T2> collection, final String keyPropertyName, List<String> keys){
		if(collection == null || collection.size() < 1 || CheckUtils.isEmpty(keys)){
			return new ArrayList<T2>(0);
		}
		final Map<String, T2> map = CollectionUtils.extractConllectionToMap(collection, keyPropertyName);
		final List<T2> result = new LinkedList<T2>();
		for(String key : keys){
			T2 obj=map.get(key);
			try {
				if(obj != null){
					result.add(obj);
				}
			} catch (Exception e) {
				throw new RuntimeException("Fail to getProperty of " + keyPropertyName
						+ " from class " + obj.getClass().getSimpleName(), e);
			}
		}
		return result;
	}

	public static <T> List<T> findObjectsByIds(final Map<Long, T> collectionMap, final List<Long> keys){
		if(collectionMap == null || collectionMap.size() < 1 || CollectionUtils.isEmpty(keys)){
			return new ArrayList<T>(0);
		}
		final List<T> result = new LinkedList<T>();
		for(final Long key : keys){
			final T t = collectionMap.get(key);
			if(t != null){
				result.add(t);
			}
		}
		return result;
	}
	
	/***********************
	 * 将列表按照id排序
	 * @param list
	 * @param idKeyName
	 * @param ids
	 * @return
	 */
	public static <T> List<T> sortListByIds(final List<T> list, final String idKeyName, final List<Long> ids)
	{
		if(CollectionUtils.isEmpty(list)){
			return new ArrayList<T>(0);
		}
		final Map<Long, List<T>> map = CollectionUtils.extractConllectionToMapById(list, idKeyName);
		final List<T> result = new ArrayList<T>(list.size());
		for(final Long id: ids)
		{
			final List<T> subList = map.get(id);
			if(subList != null){
				result.addAll(subList);
			}
		}
		return result;
	}
	
	/*********
	 * 转换成key为long型 的ID 的map， 统一id的对象存到list
	 * @param list
	 * @param idKeyName
	 * @return
	 */
	public static final <T> Map<Long, List<T>> extractConllectionToMapById(final List<T> list, final String idKeyName)
	{
		if(CollectionUtils.isEmpty(list)){
			return new HashMap<Long, List<T>>();
		}
		final Map<Long, List<T>> map = new HashMap<Long, List<T>>();
		for(final T t : list){
			try {
				final Long id = (Long)PropertyUtils.getProperty(t, idKeyName);
				List<T> subList = map.get(id);
				if(subList == null){
					subList = new LinkedList<T>();
				}
				subList.add(t);
				map.put(id, subList);
			} catch (Exception e) {
				throw new RuntimeException("Fail to getProperty of " + idKeyName + " from class " + t.getClass().getSimpleName(), e);
			}
		}	
		return map;
	}
	
	public static <T> List<T> sortListByIds(final List<T> list, final String idKeyName, final Long[] ids)
	{
		if(CollectionUtils.isEmpty(list)){
			return new ArrayList<T>(0);
		}
		final Map<Long, List<T>> map = CollectionUtils.extractConllectionToMapById(list, idKeyName);
		final List<T> result = new ArrayList<T>(list.size());
		for(final Long id: ids)
		{
			final List<T> subList = map.get(id);
			if(subList != null){
				result.addAll(subList);
			}
		}
		return result;
	}
	
	/*********************
	 * 将单个元素放到map中对应list元素的尾部， 没有则新建
	 * @param map
	 * @param key
	 * @param object
	 * @return
	 */
	public static final<T2> Map<String, List<T2>> putObject2MapList(Map<String, List<T2>> map, String key, T2 object){
		List<T2> list = null;
		if(map == null){
			map = new HashMap<String, List<T2>>();
		}else{
			list = map.get(key);
		}
		if(list == null){
			list = new LinkedList<T2>();
		}
		list.add(object);
		map.put(key, list);
		return map;		
	}
	
	/****************
	 * 计算总量， key对应的属性类型为long
	 * @param list
	 * @param countPropertyName
	 * @return
	 */
	public static final <T> long calculateLongTotalCount(final List<T> list, final String countPropertyName)
	{
		if(CollectionUtils.isEmpty(list)){
			return 0;
		}
		//计算总量
		long totalCount = 0;
		for(T t: list){
			try {
				totalCount += (Long)(PropertyUtils.getProperty(t, countPropertyName));
			} catch (Exception e) {
				throw new RuntimeException("Fail to getProperty of " + countPropertyName + " from class " + list.get(0).getClass().getSimpleName(), e);
			}
		}
		return totalCount;
	}

	/****************
	 * 计算总量， key对应的属性类型为int
	 * @param list
	 * @param countPropertyName
	 * @return
	 */
	public static final <T> int calculateIntegerTotalCount(final List<T> list, final String countPropertyName)
	{
		if(CollectionUtils.isEmpty(list)){
			return 0;
		}
		//计算总量
		int totalCount = 0;
		for(T t: list){
			try {
				totalCount += (Integer)(PropertyUtils.getProperty(t, countPropertyName));
			} catch (Exception e) {
				throw new RuntimeException("Fail to getProperty of " + countPropertyName + " from class " + list.get(0).getClass().getSimpleName(), e);
			}
		}
		return totalCount;
	}
	
	/*********************
	 * 从列表中的元素中提取id元素， 并过滤掉参数中的removedIds列表
	 * @param collection
	 * @param idPropertyName
	 * @param removedIds
	 * @return
	 */
	public static final <T> Set<Long> extractDuplicatePropsByRemoveExcludeIds(final List<T> collection, 
			final String idPropertyName, final Long[] removedIds)
	{
		if(CollectionUtils.isEmpty(collection)){
			return new HashSet<Long>(0);
		}
		final Set<Long> removedIdSet = CollectionUtils.toSet(removedIds);
		final Set<Long> list = new HashSet<Long>();
		for (final T obj : collection) {
			if(obj == null){
				continue;
			}
			try {
				final Long id = (Long)PropertyUtils.getProperty(obj, idPropertyName);
				if(! removedIdSet.contains(id)){
					list.add(id);
				}
			} catch (Exception e) {
				throw new RuntimeException("Fail to getProperty of " + idPropertyName + " from class " + obj.getClass().getSimpleName(), e);
			}
		}
		return list;
	}
	
	/*******************
	 * 将元素列表转化成数组
	 * @param objs
	 * @return
	 */
	public static final <T> List<T> toList(final T... objs){
		if(ArrayUtils.isEmpty(objs) || (objs.length==1 && objs[0] == null)){
			return new ArrayList<T>(0);
		}
		final List<T> result = new ArrayList<T>(objs.length);
		for(final T obj : objs){
			if(obj != null){
				result.add(obj);
			}
		}
		return result;
	}
	
	public static final <T> List<T> toListWithMaxCount(int count, final T... objs){
		if(ArrayUtils.isEmpty(objs) || (objs.length==1 && objs[0] == null)){
			return new ArrayList<T>(0);
		}
		final List<T> result = new ArrayList<T>(objs.length);
		for(final T obj : objs){
			if(obj != null){
				result.add(obj);
				if(-- count < 1){
					break;
				}
			}
		}
		return result;
	}
	
	/*******************
	 * 获取非null的元素列表
	 * @param list
	 * @return
	 */
	public static final <T> List<T> filterNullElements(final List<T> list){
		if(list == null){
			return new ArrayList<T>(0);
		}
		final List<T> result = new ArrayList<T>(list.size());
		for(final T t : list){
			if(t != null){
				result.add(t);
			}
		}
		return result;
	}
	
	/***************
	 * 从一个随机的下标取subListCount个元素， 将all看成是循环列表， 不够再从0开始取
	 * @param all
	 * @param subListCount
	 * @return
	 */
	public static final <T> List<T> getRandomSubListFromCycleList(final List<T> all, final int subListCount)
	{
		//伪随机取其中几个
		if(all == null || all.size() <= subListCount){
			return all;
		}else{
			final int index = (int)(System.currentTimeMillis() % all.size()); //起始下标
			final List<T> sublist = new ArrayList<T>(subListCount);
			final List<T> tailSubList = CollectionUtils.subList(all, subListCount, index);
			if(tailSubList.size() > 0){
				sublist.addAll(tailSubList);
			}
			if(sublist.size() < subListCount){
				sublist.addAll(all.subList(0, (subListCount - sublist.size())));
			}
			return sublist;
		}
	}
	
	/**************
	 * 是否包含某一个元素
	 * @param array
	 * @param v
	 * @return
	 */
	public static final boolean containValueByArray(final String[] array, final String v, final boolean isIngoreCase)
	{
		if(ArrayUtils.isEmpty(array)){
			return false;
		}
		for(final String t : array){
			if(t == null){
				continue;
			}
			if(isIngoreCase){
				if(t.equalsIgnoreCase(v)){
					return true;
				}
			}else{
				if(t.equals(v)){
					return true;
				}
			}
		}
		return false;
	}
	
	/*******
	 * 转化成string
	 * @param values
	 * @return
	 */
	public static final <T> String toString(final List<T> values){
		final StringBuilder sb = new StringBuilder("");
		if(values == null || values.size() < 1){
			return sb.toString();
		}
		for(final T v: values){
			if(sb.length() > 0){
				sb.append(",");
			}
			sb.append(ReflectionToStringBuilder.toString(v));
		}
		return sb.toString();
	}
	
	public static final <T> String toString(final Map<String, T> values){
		final StringBuilder sb = new StringBuilder("");
		if(values == null || values.size() < 1){
			return sb.toString();
		}
		for(final Entry<String, T> v: values.entrySet()){
			if(sb.length() > 0){
				sb.append(",");
			}
			sb.append("[" + v.getKey() + "=" + ReflectionToStringBuilder.toString(v.getValue()) + "]");
		}
		return sb.toString();
	}
}
