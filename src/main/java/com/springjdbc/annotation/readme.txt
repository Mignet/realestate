spring_jdbc_anonotation �������˵����


1��applicationContext.xml �ļ��� �����������bean������

  	<!-- ����sql����Ӧ��xml�ļ���Ŀ¼ -->
	<bean id="xmlSqlParse" class="com.springjdbc.annotation.XmlSqlParse" >
		<property name="xmlSqlFileList">
			<list>
				<value>classpath:com/springapp/dao/xml/*.xml</value>
			</list>
		</property>	
	</bean>

2)applicationContext.xml ����ŵ���Ŀ¼�£�������Ҫ�޸�Դ����


�汾1.1.8����˵����
��� ��ҳ�ܼ�¼��bug

�汾1.1.9 ����˵����
��СcopyProperties ������ʹ����;

�汾1.2.0 ����˵��:
save() �� update() ����ת���� save(T t) ��update(T t) ,�����״̬model����

�汾1.2.2 ����˵����
��� ���� �洢���̺����ݿ� ������ �����ӿ�

�汾1.2.3 ����˵����
��ɷ��͵��õ�6���ӿ�

�汾1.2.4 ����˵����
1���Ż����ô洢���̺ͺ����Ľӿ�
2��ȥ������������save��update�ӿ�

�汾1.2.5 ����˵����
1�����ӵ���ķ�ҳ��ѯ�ӿ�
2���޸Ŀ������Խӿ�
 
 �汾1.2.6 ����˵�� 2009-6-13��
1)�Ż�get ��  delete �ӿ�

�汾1.2.7 ����˵�� 2009-6-19
1�������������½ӿڣ��ɴ���where sql

�汾1.2.8 ����˵�� 2009-6-30 
1) ���ӵ���������������ѯ

�汾1.2.9 ����˵��2009-7-3
1) �޸�BeanUtil���copyProperties�����������MAP ����ֵ������javabean �� double �� int �޷�����������

�汾1.3.0 ����˵��2009-7-6 
1) ������������sql �ӿڣ��ýӿ���Ҫ���ã�ֻ�����ڴ������ڱ�֮��������ݸ��µ�ҵ���߼�

�汾1.3.1 ����˵��2009-7-6
1���̳п���ͨ��sql����ѯ�Ľӿڣ�������Ƕ�׵�SQL��䲢���޷�������XML�е�ҵ���߼�

�汾1.3.2 ����˵��2009-7-17
1) �ļ���ʽ����ȫ��ת��Ϊutf-8

�汾1.3.7 ����˵��2009-10-21
1) ���ӻ�ȡSPRING applicationcontent ��

�汾1.4.1 ����˵�� 2009-11-18
1)���� BASEDAO ��
2������ BASEPAGEDAO ��
3������BaseFunctionCallDao �� BaseProcedureCallDao ��
4���������  Action +Facade + DAO ����ģʽ���������� Action+Facade+ BaseDomain ģʽ
  








 







