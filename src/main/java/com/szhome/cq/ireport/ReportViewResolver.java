/**
 * Project Name:dxtx_re
 * File Name:ReportViewResolver.java
 * Package Name:com.szhome.cq.ireport
 * Date:2014-6-9ÏÂÎç6:41:01
 * Copyright (c) 2014, DXTX All Rights Reserved.
 *
*/

package com.szhome.cq.ireport;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractCachingViewResolver;

public class ReportViewResolver extends AbstractCachingViewResolver  {
    private String reportSuffix;//ºó×º
    private View reportView;
    private View imageView;
    private String imageSuffix;
   
	@Override
    protected View loadView(String viewName, Locale locale) throws Exception {
        View view = null;
        if (viewName.endsWith(this.getReportSuffix())){
            view =this.getReportView();
        }else if(viewName.endsWith(this.getImageSuffix())){
        	view =this.getImageView();
        }
        return view;
    }
    public String getReportSuffix() {
        return reportSuffix;
    }
    public void setReportSuffix(String reportSuffix) {
        this.reportSuffix = reportSuffix;
    }
    public View getReportView() {
        return reportView;
    }
    public void setReportView(View reportView) {
        this.reportView = reportView;
    }
	public View getImageView() {
		return imageView;
	}
	public void setImageView(View imageView) {
		this.imageView = imageView;
	}
    
	public String getImageSuffix() {
		return imageSuffix;
	}
	public void setImageSuffix(String imageSuffix) {
		this.imageSuffix = imageSuffix;
	}
}

