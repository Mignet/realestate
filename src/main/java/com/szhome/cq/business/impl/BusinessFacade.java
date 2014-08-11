package com.szhome.cq.business.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.szhome.commons.log.LogUtil;
import com.szhome.cq.business.IBusinessFacade;
import com.szhome.cq.domain.model.BusScanner;

/**
 * ÒµÎñfacade
 * Date:     2014-07-25 ÏÂÎç3:55:39 <br/>
 * @author   Sam
 * @version  
 * @since    JDK 1.6	 
 */

@Component
@Transactional
@Scope("prototype")
public class BusinessFacade implements IBusinessFacade {

	@Autowired
	BusScanner bscan;

	@Override
	public void saveBusScanner(BusScanner bs) throws Exception {
		String scanner_id = bscan.getSeqId();
		if(bs != null){
			bs.setScanner_id(scanner_id);
			bscan.save(bs);
		}
	}

	@Override
	@Transactional
	public void batch_saveBusScanners(Map<String, Object> paramMap)
			throws Exception {
		List<Map<String,Object>> resLst = (List<Map<String,Object>>)paramMap.get("recLst");
		String bus_id = paramMap.get("bus_id").toString();
		String directory = paramMap.get("directory").toString();
		String extension = paramMap.get("extension").toString();
		if(resLst != null)
			try {
				for(int i=0;i<resLst.size();i++){
					Map<String,Object> tempMap = resLst.get(i);
					BusScanner bs = new BusScanner();
					String scanner_id = bscan.getSeqId();
					String receival_id = tempMap.get("RECEIVAL_ID").toString();
					Map<String,Object> pmap = new HashMap<String,Object>();
					pmap.put("receival_id", receival_id);
					pmap.put("bus_id", bus_id);
					BusScanner bs2 = selectBusScannersby(pmap);
					if(bs2 != null){
						bs2.setB_deleteflag("0");
						bs2.setSca_extension(extension);
						bs2.setDirectory(directory+"/"+receival_id);
						bscan.update(bs2);
						continue;
					}
					bs.setScanner_id(scanner_id);
					bs.setReceival_id(receival_id);
					bs.setB_deleteflag("0");
					bs.setBus_id(bus_id);
					bs.setSca_extension(extension);
					bs.setDirectory(directory+"/"+receival_id);
					bscan.save(bs);
				}
			} catch (Exception e) {
				LogUtil.error("batch_saveBusScanners:"+e.getMessage(), e);
				throw e;
			}
	}

	@Override
	public BusScanner selectBusScannersby(Map<String, Object> paramMap) {
		BusScanner bs = bscan.queryDomainBykey("BusScanner.selectBusScannersby", paramMap);
		return bs;
	}
}

