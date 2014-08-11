/**
 * 
 */ 
package com.szhome.cq.business; 

import java.util.ArrayList;
import java.util.List;

import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.IAuditFacade;

import junit.framework.TestCase;

public class AuditFacadeTest  extends TestCase {
	  private IAuditFacade iaf = null;
	
	  public void setUp() {
	    iaf = FacadeFactory.getAuditFacade();
	  }

	  public void testAutoIncreate(){
//		 iaf.getGobalSeqID();
	  }

}
 

