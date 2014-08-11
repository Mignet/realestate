package com.szhome.cq.utils.domaingenerate;



/**
 * 生成文件时需要的固定格式的字符串.以 #{1}这种格式的字符串需要被替换成具体的数据.
 * #{1} ------- typeName               POJO属性的数据类型
 * #{2} ------- propertyName           POJO属性的名称
 * #{3} ------- get functionName       POJO属性的get函数名称
 * #{4} ------- set functionName       POJO属性的set函数名称
 * #{5} ------- tableName              POJO的Annocation中的对应的表明
 * #{6} ------- className			   生成的POJO的类名
 * #{7} ------- 					   构造函数参数
 * #{8} ------- 					   构造函体
 * 
 * 
 * 
 *  @author	Mignet
 */

public interface Inform {

  /**
   * 生成的java文件所包含的导入日期模板
   */
	String IMPORT_STRING_DATE = "import java.util.Date;\n";

	/**
	 *生成的java文件所包含的导入Blob模板 
	 */
	String IMPORT_STRING_BLOB = "import java.sql.Blob;\n";

	/**
	 *生成的java文件所包含的导入Clob模板 
	 */
	String IMPORT_STRING_CLOB = "import java.sql.Clob;\n";

	/**
	 *生成的java文件所包含的固定的 导入信息 模板 
	 */
	String IMPORT_STRING =  "import org.springframework.context.annotation.Scope;\n"+
						   "import org.springframework.stereotype.Component;\n" +
						   "\n" +
						   "import com.springjdbc.annotation.BaseDomain;\n" +
						   "import com.springjdbc.annotation.Entity;\n" +
						   "import com.springjdbc.annotation.Id;\n" +
						   "import com.springjdbc.annotation.Table;\n" +
						   "\n";

	/**
	 * 生成的java文件中， 类的annotation 信息 模板
	 */
	String ANNOTATION_STRING = "@Scope(\"prototype\")\n"+
							  "@Component\n"+
							  "@Entity\n"+
							  "@Table(name = \"#{5}\")\n";

	/**
	 *  生成的java文件中， 类的 head 信息 模板
	 */
	String CLASS_HEAD_STRING = "public class #{6} extends BaseDomain<#{6}> {\n";

	/**
	 * 生成的java文件中， 类的 结束 信息 模板
	 */
	String CLASS_END_STRING = "}";

	/**
	 * 生成的java文件中， 类的 所有的字段的注释 模板
	 */
	String COMMENT_STRING ="	/**#{1} */\n";
	
	/**
	 * 生成的java文件中， 类的 所有的字段 模板
	 */
	String PRIVATE_STRING ="	private #{1} #{2};\n";

	/**
	 * 生成的java文件中， 类的 所有的 get方法 模板
	 */
	String GET_FUNC_STRING = "	public #{1} get#{3}() {\n" +
						   "		return #{2};\n" +
						   "	}\n\n";

	/**
	 * 生成的java文件中， 类的 所有的 set方法 模板
	 */
	String SET_FUNC_STRING ="	public void set#{4}(#{1} #{2}) {\n" +
						  "		this.#{2} = #{2};\n" +
						  "	}\n\n";

	/**
	 * 标志主键字段 的 annotation 模板
	 */
	String ID_STRING = "	@Id\n";

	/**
	 * 缺省的构造函数模板
	 */
	String DEFAULT_CONSTRUCT = "	public #{6}(){\n" +
							  "		super();\n" +
							  "		this.t = #{6}.class;\n" +
							  "	}\n\n" ;

	/**
	 * 含有主键的构造函数模板
	 */
	String PRIMARY_KEY_CONSTRUCT ="	public #{6}(#{7}){\n" +
								"		super();\n" +
								"#{8}" +
								"	}\n\n";

	/**
	 * 构造函数参数模板
	 */
	String CONSTRUCT_VARIANT_TYPE = "#{1} #{2},";

	/**
	 * 构造函数设置模板
	 */
	String CONSTRUCT_VAR = "this.#{2} = #{2};\n";

}


