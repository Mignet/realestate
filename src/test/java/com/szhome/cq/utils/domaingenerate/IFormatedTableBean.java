package com.szhome.cq.utils.domaingenerate;

/**
 * 被格式化后的表结构的信息
 * @author Mignet
 *
 */
public interface IFormatedTableBean {
	/**
     * 返回所有生成java文件时所需要的数据.
     */
	String  getOutPutString();
	/**
     * 返回被生成java文件时的存放路径.
     */
	String getFileSavePath();
}

