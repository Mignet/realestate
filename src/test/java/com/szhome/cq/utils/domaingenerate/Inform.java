package com.szhome.cq.utils.domaingenerate;



/**
 * �����ļ�ʱ��Ҫ�Ĺ̶���ʽ���ַ���.�� #{1}���ָ�ʽ���ַ�����Ҫ���滻�ɾ��������.
 * #{1} ------- typeName               POJO���Ե���������
 * #{2} ------- propertyName           POJO���Ե�����
 * #{3} ------- get functionName       POJO���Ե�get��������
 * #{4} ------- set functionName       POJO���Ե�set��������
 * #{5} ------- tableName              POJO��Annocation�еĶ�Ӧ�ı���
 * #{6} ------- className			   ���ɵ�POJO������
 * #{7} ------- 					   ���캯������
 * #{8} ------- 					   ���캯��
 * 
 * 
 * 
 *  @author	Mignet
 */

public interface Inform {

  /**
   * ���ɵ�java�ļ��������ĵ�������ģ��
   */
	String IMPORT_STRING_DATE = "import java.util.Date;\n";

	/**
	 *���ɵ�java�ļ��������ĵ���Blobģ�� 
	 */
	String IMPORT_STRING_BLOB = "import java.sql.Blob;\n";

	/**
	 *���ɵ�java�ļ��������ĵ���Clobģ�� 
	 */
	String IMPORT_STRING_CLOB = "import java.sql.Clob;\n";

	/**
	 *���ɵ�java�ļ��������Ĺ̶��� ������Ϣ ģ�� 
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
	 * ���ɵ�java�ļ��У� ���annotation ��Ϣ ģ��
	 */
	String ANNOTATION_STRING = "@Scope(\"prototype\")\n"+
							  "@Component\n"+
							  "@Entity\n"+
							  "@Table(name = \"#{5}\")\n";

	/**
	 *  ���ɵ�java�ļ��У� ��� head ��Ϣ ģ��
	 */
	String CLASS_HEAD_STRING = "public class #{6} extends BaseDomain<#{6}> {\n";

	/**
	 * ���ɵ�java�ļ��У� ��� ���� ��Ϣ ģ��
	 */
	String CLASS_END_STRING = "}";

	/**
	 * ���ɵ�java�ļ��У� ��� ���е��ֶε�ע�� ģ��
	 */
	String COMMENT_STRING ="	/**#{1} */\n";
	
	/**
	 * ���ɵ�java�ļ��У� ��� ���е��ֶ� ģ��
	 */
	String PRIVATE_STRING ="	private #{1} #{2};\n";

	/**
	 * ���ɵ�java�ļ��У� ��� ���е� get���� ģ��
	 */
	String GET_FUNC_STRING = "	public #{1} get#{3}() {\n" +
						   "		return #{2};\n" +
						   "	}\n\n";

	/**
	 * ���ɵ�java�ļ��У� ��� ���е� set���� ģ��
	 */
	String SET_FUNC_STRING ="	public void set#{4}(#{1} #{2}) {\n" +
						  "		this.#{2} = #{2};\n" +
						  "	}\n\n";

	/**
	 * ��־�����ֶ� �� annotation ģ��
	 */
	String ID_STRING = "	@Id\n";

	/**
	 * ȱʡ�Ĺ��캯��ģ��
	 */
	String DEFAULT_CONSTRUCT = "	public #{6}(){\n" +
							  "		super();\n" +
							  "		this.t = #{6}.class;\n" +
							  "	}\n\n" ;

	/**
	 * ���������Ĺ��캯��ģ��
	 */
	String PRIMARY_KEY_CONSTRUCT ="	public #{6}(#{7}){\n" +
								"		super();\n" +
								"#{8}" +
								"	}\n\n";

	/**
	 * ���캯������ģ��
	 */
	String CONSTRUCT_VARIANT_TYPE = "#{1} #{2},";

	/**
	 * ���캯������ģ��
	 */
	String CONSTRUCT_VAR = "this.#{2} = #{2};\n";

}


