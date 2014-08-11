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
	 * ��ѯ�����б���ҳ��
	 * 
	 * @param row ǰ��ҳ�洫�ݽ����Ĳ�ѯ������������ҳ��Ϣ
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
	 * �½�����
	 * @param row ǰ��ҳ�洫�ݽ���������뵽�������
	 * @return ���������
	 * @throws Exception
	 */
	public Row saveSeq(Row row) throws Exception {
		
		return getPlanSupportDao().save("plat_sequence_manager", row);
		
	}
	
	/**
	 * ��������
	 * @param row ��������
	 * @return ��������
	 * @throws Exception
	 */
	public int updateSeq(Row row) throws Exception {
		
		String[] seq_ids = row.getString("seq_ids").split(",");
		return super.update("plat_sequence_manager", seq_ids, row);
		
	}
	
	/**
	 * ɾ������
	 * @param row ����������¼������ֵ����
	 * @return ɾ����
	 * @throws Exception
	 */
	public int deleteSeq(Row row) throws Exception {
		
		String[] seq_ids = row.getString("seq_ids").split(",");
		return super.delete("plat_sequence_manager", seq_ids);
		
	}
}
