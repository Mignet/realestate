spring_jdbc_anonotation 组件配置说明：


1）applicationContext.xml 文件中 必须包含以下bean的声明

  	<!-- 所有sql语句对应的xml文件根目录 -->
	<bean id="xmlSqlParse" class="com.springjdbc.annotation.XmlSqlParse" >
		<property name="xmlSqlFileList">
			<list>
				<value>classpath:com/springapp/dao/xml/*.xml</value>
			</list>
		</property>	
	</bean>

2)applicationContext.xml 必须放到根目录下，否则需要修改源代码


版本1.1.8发布说明：
解决 分页总记录数bug

版本1.1.9 发布说明：
缩小copyProperties 方法的使用用途

版本1.2.0 发布说明:
save() 和 update() 方法转换成 save(T t) 和update(T t) ,解决有状态model问题

版本1.2.2 发布说明：
完成 调用 存储过程和数据库 函数的 两个接口

版本1.2.3 发布说明：
完成泛型调用的6个接口

版本1.2.4 发布说明：
1）优化调用存储过程和函数的接口
2）去掉存在隐含的save和update接口

版本1.2.5 发布说明：
1）增加单表的分页查询接口
2）修改拷贝属性接口
 
 版本1.2.6 发布说明 2009-6-13：
1)优化get 和  delete 接口

版本1.2.7 发布说明 2009-6-19
1）增加批量更新接口，可传入where sql

版本1.2.8 发布说明 2009-6-30 
1) 增加单个对象按条件语句查询

版本1.2.9 发布说明2009-7-3
1) 修复BeanUtil类的copyProperties方法，解决将MAP 对象值拷贝到javabean 中 double 和 int 无法拷贝的问题

版本1.3.0 发布说明2009-7-6 
1) 新增批量更新sql 接口，该接口需要慎用，只能用于大批量在表之间进行数据更新的业务逻辑

版本1.3.1 发布说明2009-7-6
1）继承开放通过sql语句查询的接口，用于有嵌套的SQL语句并且无法分离在XML中的业务逻辑

版本1.3.2 发布说明2009-7-17
1) 文件格式编码全部转换为utf-8

版本1.3.7 发布说明2009-10-21
1) 增加获取SPRING applicationcontent 类

版本1.4.1 发布说明 2009-11-18
1)增加 BASEDAO 类
2）增加 BASEPAGEDAO 类
3）增加BaseFunctionCallDao 和 BaseProcedureCallDao 类
4）建议采用  Action +Facade + DAO 三层模式，而不采用 Action+Facade+ BaseDomain 模式
  








 







