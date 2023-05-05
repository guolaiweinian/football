package com.liaoquanfeng.football.util;

import cn.hutool.core.convert.Convert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author pihao
 * @date 2021/11/30 13:51
 * @description bean属性复制工具类
 */
@Slf4j
public class BeanCopyUtils {

    public static final Converter DATE_CONVERTER = (value, cls, method) -> {
        if (Objects.nonNull(value) && !Objects.equals(cls, value.getClass())) {
            if (Objects.equals(cls, Timestamp.class)) {
                if (value instanceof Date) {
                    return new Timestamp(((Date)value).getTime());
                }
            } else if (Objects.equals(cls, java.sql.Date.class)) {
                if (value instanceof Date) {
                    return new Timestamp(((Date)value).getTime());
                }
            } else if (Objects.equals(cls, Date.class)) {
                if (value instanceof Timestamp) {
                    return new Timestamp(((Timestamp)value).getTime());
                } else if (value instanceof java.sql.Date) {
                    return new Timestamp(((java.sql.Date)value).getTime());
                }
            }
        }
        return value;
    };

    /**
     * 复制属性
     * 
     * @param source
     * @param target
     * @param <R>
     * @param <T>
     */
    public static <T, R> R copy(T source, R target) {
        if (Objects.nonNull(source) && Objects.nonNull(target)) {
            BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), false);
            copier.copy(source, target, null);
        }
        return target;
    }

    /**
     * 复制属性，当源对象为空时返回null
     *
     * @param source
     * @param target
     * @param <R>
     * @param <T>
     */
    public static <T, R> R nonNullCopy(T source, R target) {
        if (Objects.nonNull(source) && Objects.nonNull(target)) {
            BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), false);
            copier.copy(source, target, null);
            return target;
        }
        return null;
    }

    /**
     * 复制属性
     * 
     * @param source
     * @param target
     * @param converter
     * @param <R>
     * @param <T>
     */
    public static <T, R> R copy(T source, R target, Converter converter) {
        if (Objects.nonNull(source) && Objects.nonNull(target)) {
            BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), true);
            copier.copy(source, target, converter);
        }
        return target;
    }

    /**
     * @Description: 拷贝集合中的bean属性，并返回集合，若源对象为空则返回null
     * @param sources
     * @param initializeFunction
     * @return
     */
    public static <T, R> List<R> nonNullCopy(List<T> sources, Supplier<R> initializeFunction) {
        if (Objects.nonNull(sources)) {
            return sources.stream().map(source -> BeanCopyUtils.copy(source, initializeFunction.get()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    /**
     * @Description: 拷贝集合中的bean属性，并返回集合
     * @param sources
     * @param initializeFunction
     * @return
     */
    public static <T, R> List<R> copy(List<T> sources, Supplier<R> initializeFunction) {
        if (Objects.nonNull(sources)) {
            return sources.stream().map(source -> BeanCopyUtils.copy(source, initializeFunction.get()))
                .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    /**
     * @author: liaoqf
     * @description: map转bean
     * @param source
     * @param newClass
     * @date: 2021/11/30 23:13
     * @return
     */
    public static <E> E  mapToBean(Map source, Class<E> newClass) {
        if(Objects.nonNull(source)){
            E newInstance = null;
            try{
                newInstance = newClass.newInstance();
                BeanInfo beanInfo = Introspector.getBeanInfo(newInstance.getClass());
                PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
                for (PropertyDescriptor property : propertyDescriptors) {
                    Method setter = property.getWriteMethod();
                    if (setter != null) {
//                    Class<?> sourceClass = setter.get);
                        Object param = source.get(property.getName());
                        if(param != null){
                            if(Date.class == property.getPropertyType()){
                                setter.invoke(newInstance, Convert.toDate(param));
                            } else if(Short.class == property.getPropertyType()){
                                setter.invoke(newInstance, Convert.toShort(param));
                            } else if(Integer.class == property.getPropertyType()){
                                setter.invoke(newInstance, Convert.toInt(param));
                            } else if(Long.class == property.getPropertyType()){
                                setter.invoke(newInstance, Convert.toLong(param));
                            } else if(Double.class == property.getPropertyType()){
                                setter.invoke(newInstance, Convert.toDouble(param));
                            } else if(Float.class == property.getPropertyType()){
                                setter.invoke(newInstance, Convert.toFloat(param));
                            } else if(Boolean.class == property.getPropertyType()){
                                setter.invoke(newInstance, Convert.toBool(param));
                            } else if(BigDecimal.class == property.getPropertyType()){
                                setter.invoke(newInstance, Convert.toBigDecimal(param));
                            } else {
                                if(param instanceof Map){
                                    setter.invoke(newInstance, mapToBean((Map) param, property.getPropertyType()));
                                } else{
                                    setter.invoke(newInstance, param);
                                }
                            }
                        }
                    }
                }
            } catch (Exception e){
                log.warn("Map转bean异常：", e);
            }
            return newInstance;
        }
        return null;
    }


    /**
     * @author: liaoqf
     * @description: 数组map转bean
     * @param source
     * @param target
     * @date: 2021/11/30 23:17
     * @return
     */
    public static <E> List<E> ListMapToBean(List<Map> source, Class<E> target){
        try {
            // 建立目标新数组
            List<E> results = new ArrayList<>();
            // 循环拷贝数据到新数组
            for (Map map : source){
                // 新建数组的子元素
                E result = (E) mapToBean(map, target.newInstance().getClass());
                results.add(result);
            }
            return results;
        } catch (Exception e) {
            log.warn("Map转bean异常：", e);
        }
        return null;
    }

    /**
     * @author: liaoqf
     * @description: bean转map
     * @param bean
     * @date: 2021/11/30 23:16
     * @return
     */
    public static Map<String,Object> beanToMap(Object bean) throws Exception {
        Map<String,Object> map = new HashMap<>();
        //获取JavaBean的描述器
        BeanInfo b = Introspector.getBeanInfo(bean.getClass(),Object.class);
        //获取属性描述器
        PropertyDescriptor[] pds = b.getPropertyDescriptors();
        //对属性迭代
        for (PropertyDescriptor pd : pds) {
            //属性名称
            String propertyName = pd.getName();
            //属性值,用getter方法获取
            Method m = pd.getReadMethod();
            //用对象执行getter方法获得属性值
            Object properValue = m.invoke(bean);

            //把属性名-属性值 存到Map中
            map.put(propertyName, properValue);
        }
        return map;
    }
}
