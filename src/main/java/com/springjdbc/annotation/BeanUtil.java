package com.springjdbc.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.szhome.cq.utils.DateUtils;


public final class BeanUtil {
	private final static Log logger = LogFactory.getLog(BeanUtil.class);
	private BeanUtil() {
	}

	static {
		ConvertUtils.register(new DateConverter(), java.util.Date.class);
	}

	/**
	 * 属性拷贝，如果 源对象为MAP，则不区分属性大小写进行拷贝
	 * 
	 * @param dest
	 * @param src
	 */
	public static void copyProperties(Object dest, Object src) {
		if (src instanceof Map) {
			Map srcMap = (Map) src;
			copyPropertiesMapToObject(dest, srcMap);
		} else {
			try {
				BeanUtils.copyProperties(dest, src);
			} catch (Exception ex) {
				throw new AnnotationExceptoin("Exception:BeanUtils.copyProperties():" + ex.getMessage());
			}
		}

	}
	/**
	 * 通过反射,获得Class定义中声明的父类的泛型参数的类型.
	 * 如无法找到, 返回Object.class.
	 * eg.
	 * public UserDao extends HibernateDao<User>
	 *
	 * @param clazz The class to introspect
	 * @return the first generic declaration, or Object.class if cannot be determined
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getSuperClassGenricType(final Class clazz) {
		return getSuperClassGenricType(clazz, 0);
	}

	/**
	 * 通过反射,获得定义Class时声明的父类的泛型参数的类型.
	 * 如无法找到, 返回Object.class.
	 * 
	 * 如public UserDao extends HibernateDao<User,Long>
	 *
	 * @param clazz clazz The class to introspect
	 * @param index the Index of the generic ddeclaration,start from 0.
	 * @return the index generic declaration, or Object.class if cannot be determined
	 */
	@SuppressWarnings("unchecked")
	public static Class getSuperClassGenricType(final Class clazz, final int index) {

		Type genType = clazz.getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			logger.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
			return Object.class;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (index >= params.length || index < 0) {
			logger.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: "
					+ params.length);
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			logger.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
			return Object.class;
		}

		return (Class) params[index];
	}
	

	/**
	 * 显示每个属性值
	 * 
	 * @param t
	 * @return
	 */
	public static String toString(Object obj) {
		return ToStringBuilder.reflectionToString(obj, ToStringStyle.DEFAULT_STYLE);
	}

	private static void copyPropertiesMapToObject(Object dest, Map src) {
		Field[] destField = AnnotationSqlUtil.getObjectAllField(dest.getClass());
		Set<String> keySet = src.keySet();
		for (String name : keySet) {
			for (Field df : destField) {
				String fieldName = df.getName();
				if (name.equalsIgnoreCase(fieldName)) {
					String firstLetter = fieldName.substring(0, 1).toUpperCase();
					String setMethodName = "set" + firstLetter + fieldName.substring(1);
					try {
						Method setMethod = dest.getClass().getMethod(setMethodName, new Class[] { df.getType() });
						setMethod.invoke(dest, new Object[] {coverBigDecimalValue(df.getType(),src.get(name)) });
					} catch (Exception ex) {
						throw new AnnotationExceptoin("Exception:BeanUtils.copyPropertiesMapToObject():"
								+ ex.getMessage()+" field:"+df.getType()+" "+fieldName+" "+src.get(name));
					}
				}
			}
		}

	}

	/**
	 * 将非字符串类型的值转换为 int 型或者 double 型
	 * @param value
	 * @return
	 */
	private static Object coverBigDecimalValue(Class clazz,Object value) throws Exception {
		//尝试解析
		try {
			if (clazz.getName().equals("double")) { //double型
				return Double.parseDouble(String.valueOf(value));
			} else if (clazz.getName().equals("int")) { // int 型
				return Integer.parseInt(String.valueOf(value));
			} else if (clazz.getName().equals("java.util.Date")) { // java.util.Date 型
				String str = String.valueOf(value);
				if(str.trim().length()==8){
					return DateUtils.string2Date(str, DateUtils.DATE_FORMAT_DAY);
				}else{
					return DateUtils.string2Date(str, DateUtils.DATE_FORMAT_DEFAULT);
				}
			}else{
				return value;
			}
		} catch (Exception e) {
			return value;
		}

	}

}

