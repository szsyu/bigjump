/*
 *  @fileName               ReflectUtils.java      
 *  @author                 Shawn Yu
 *  @version                1.0
 *  @createdDate            2014-08-30     
 *  @lastUpdateDate         2014-08-30
 *  Modification Log:
 *  *******************************************************************************************************************************
 *  Date               ModifiedBy             Rev.            Description
 *  *******************************************************************************************************************************
 *  2014-10-10         Shawn Yu               1.0             创建
 */
package com.ect.bigjump.utility;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 反射工具类
 * 
 * @author  Shawn Yu
 * @version 1.0
 * @since   2014-08-30
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class ReflectUtils {
	
	/**
	 * 获得超类的参数类型，取第一个参数类型
	 * @param <T> 类型参数
	 * @param clazz 超类类型
	 */
	public static <T> Class<T> getClassGenricType(final Class clazz) {
		return getClassGenricType(clazz, 0);
	}
	
	/**
	 * 根据索引获得超类的参数类型
	 * @param clazz 超类类型
	 * @param index 索引
	 */
	public static Class getClassGenricType(final Class clazz, final int index) {
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class) params[index];
	}
}
