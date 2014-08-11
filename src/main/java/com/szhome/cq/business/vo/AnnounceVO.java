/**
 * Project Name:dxtx_re
 * File Name:ParticpantsVO.java
 * Package Name:com.szhome.cq.business.vo
 * Date:2014-1-3ÉÏÎç11:48:58
 * Copyright (c) 2014, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.szhome.cq.business.vo;

import com.szhome.cq.domain.model.Announcement;
import com.szhome.cq.domain.model.ExamFirst;
import com.szhome.cq.domain.model.ExamForth;
import com.szhome.cq.domain.model.ExamSecond;
import com.szhome.cq.domain.model.ExamThird;

/**
 * ClassName:ParticpantsVO <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-1-3 ÉÏÎç11:48:58 <br/>
 * @author   xuzz
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class AnnounceVO {
	
     private Announcement annouce;
     private ExamFirst examf;
     private ExamSecond exams;
     private ExamThird examt;
     private ExamForth examfo;
	public Announcement getAnnouce() {
		return annouce;
	}
	public void setAnnouce(Announcement annouce) {
		this.annouce = annouce;
	}
	public ExamFirst getExamf() {
		return examf;
	}
	public void setExamf(ExamFirst examf) {
		this.examf = examf;
	}
	public ExamSecond getExams() {
		return exams;
	}
	public void setExams(ExamSecond exams) {
		this.exams = exams;
	}
	public ExamThird getExamt() {
		return examt;
	}
	public void setExamt(ExamThird examt) {
		this.examt = examt;
	}
	public ExamForth getExamfo() {
		return examfo;
	}
	public void setExamfo(ExamForth examfo) {
		this.examfo = examfo;
	}
     
     
}


