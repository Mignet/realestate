package com.szhome.cq.delegate;

import java.util.Map;

import com.plan.commons.Row;

/**
 * 
 * @author fengyc
 *
 */
public class SeqDelegate extends BaseDelegate {

	/**
	 * 查询主键列表（分页）
	 * 
	 * @param row 前端页面传递进来的查询参数，包含分页信息
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getSeqList(Row row) throws Exception {

		return super.query("plat_sequence_manager", row);
		
	}
	
	public Row getSeqById(Row row) throws Exception {
		
		return getPlanSupportDao().findRow(row, "plat_sequence_manager");
				
	}
	
	/**
	 * 新建主键
	 * @param row 前端页面传递进来的需插入到表的数据
	 * @return 保存的数据
	 * @throws Exception
	 */
	public Row saveSeq(Row row) throws Exception {
		
		return getPlanSupportDao().save("plat_sequence_manager", row);
		
	}
	
	/**
	 * 更新主键
	 * @param row 更新数据
	 * @return 更新条数
	 * @throws Exception
	 */
	public int updateSeq(Row row) throws Exception {
		
		String[] seq_ids = row.getString("seq_ids").split(",");
		return super.update("plat_sequence_manager", seq_ids, row);
		
	}
	
	/**
	 * 删除主键
	 * @param row 包含主键记录的主键值数组
	 * @return 删除数
	 * @throws Exception
	 */
	public int deleteSeq(Row row) throws Exception {
		
		String[] seq_ids = row.getString("seq_ids").split(",");
		return super.delete("plat_sequence_manager", seq_ids);
		
	}
}
