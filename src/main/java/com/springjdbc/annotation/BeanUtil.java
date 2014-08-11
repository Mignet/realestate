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
	 * ���Կ�������� Դ����ΪMAP�����������Դ�Сд���п���
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
	 * ͨ������,���Class�����������ĸ���ķ��Ͳ���������.
	 * ���޷��ҵ�, ����Object.class.
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
	 * ͨ������,��ö���Classʱ�����ĸ���ķ��Ͳ���������.
	 * ���޷��ҵ�, ����Object.class.
	 * 
	 * ��public UserDao extends HibernateDao<User,Long>
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
	 * ��ʾÿ������ֵ
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
	 * �����ַ������͵�ֵת��Ϊ int �ͻ��� double ��
	 * @param value
	 * @return
	 */
	private static Object coverBigDecimalValue(Class clazz,Object value) throws Exception {
		//���Խ���
		try {
			if (clazz.getName().equals("double")) { //double��
				return Double.parseDouble(String.valueOf(value));
			} else if (clazz.getName().equals("int")) { // int ��
				return Integer.parseInt(String.valueOf(value));
			} else if (clazz.getName().equals("java.util.Date")) { // java.util.Date ��
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

