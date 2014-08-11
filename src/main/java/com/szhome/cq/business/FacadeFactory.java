package com.szhome.cq.business;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;

import com.springjdbc.annotation.ApplicationContextUtils;
import com.szhome.cq.domain.PublicDomain;

/**
 * 门面工厂
 * @author Mignet
 * 
 */
public class FacadeFactory {
	
	private static ApplicationContext ac = ApplicationContextUtils
			.getApplicationContext();

	public static PublicDomain getPublicDomain() {
		return (PublicDomain) ac.getBean("publicDomain");
	}
	
	
	public static ApplicationContext getApplicationContext() {
		return ac;
	}
	
	@SuppressWarnings("unchecked")
	public static <T>  T getSpecialService(Class<T> t) {
		return (T) ac.getBean(StringUtils.uncapitalize( t.getSimpleName()));
	}

	public static IMenuFacade getMenuFacade() {
		return (IMenuFacade)ac.getBean("menuFacade");
	}
	
	public static IRoleManageFacade getRoleManageFacade() {
		return (IRoleManageFacade)ac.getBean("roleManageFacade");
	}
	//2014-03-10 15:32 Add
	public static IUserRoleManageFacade getUserRoleManageFacade() {
		return (IUserRoleManageFacade)ac.getBean("userRoleManageFacade");
	}
	//2014-03-13 18:32 Add
	public static IParcelFacade getParcelFacade() {
		return (IParcelFacade)ac.getBean("parcelFacade");
	}
	
	//2014-03-27 15:08 Add
	public static IHouseFacade getHouseFacade() {
		return (IHouseFacade)ac.getBean("houseFacade");
	}
	
	//2014-03-20 下午5:16 Add
	public static IBookFacade getBookFacade() {
		return (IBookFacade)ac.getBean("bookFacade");
	}
	
	//2014-04-10 上午9:16 Add
	public static IAttachFacade getAttachFacade() {
		return (IAttachFacade)ac.getBean("attachFacade");
	}
	
	//2014-04-10 上午9:16 Add
	public static IPreadviceFacade getPreadviceFacade() {
		return (IPreadviceFacade)ac.getBean("preadviceFacade");
	}
	
	//2014-04-10 上午9:16 Add
	public static IDissentFacade getDissentFacade() {
		return (IDissentFacade)ac.getBean("dissentFacade");
	}
	
	//2014-04-10 上午9:16 Add
	public static IEasementFacade getEasementFacade() {
		return (IEasementFacade)ac.getBean("easementFacade");
	}
	
	//2014-04-10 上午9:16 Add
	public static IOwnershipFacade getOwnershipFacade() {
		return (IOwnershipFacade)ac.getBean("ownershipFacade");
	}
	
	//2014-04-18 上午9:16 Add
	public static IPropertyRightFacade getPropertyRightFacade() {
		return (IPropertyRightFacade)ac.getBean("propertyRightFacade");
	}
	
	//2014-04-21 上午10:48 Add
	public static IPrivateRealFacade getPrivateRealFacade() {
		return (IPrivateRealFacade)ac.getBean("privateRealFacade");
	}
	
	//2014-07-25 上午11:33 Add
	public static IBusinessFacade getBusinessFacade() {
		return (IBusinessFacade)ac.getBean("businessFacade");
	}
	
	public static ILogFacade getLogFacade() {
		return (ILogFacade)ac.getBean("logFacade");
	}
	public static IWorkflowFacade getWorkflowFacade() {
		return (IWorkflowFacade)ac.getBean("workflowFacade");
	}
	public static IAuditFacade getAuditFacade() {
		return (IAuditFacade)ac.getBean("auditFacade");
	}
	/*public static ILoginFacade getLoginFacade() {
		return (ILoginFacade)ac.getBean("loginFacade");
	}*/
	public static IIdentifierFacade getIdentifierFacade() {
		return (IIdentifierFacade)ac.getBean("identifierFacade");
	}
	public static IApplicantFacade getApplicantFacade() {
		return (IApplicantFacade)ac.getBean("applicantFacade");
	} 
	public static IWorkwindowFacade getWorkwindowFacade() {
		return (IWorkwindowFacade)ac.getBean("workwindowFacade");
	} 
	public static ICommonFacade getCommonFacade() {
		return (ICommonFacade)ac.getBean("commonFacade");
	} 

	public static IDictFacade getDictFacade() {
		return (IDictFacade)ac.getBean("dictFacade"); 
	} 
	public static IReportFacade getReportFacade() {
		return (IReportFacade)ac.getBean("reportFacade");
	} 
	public static IRegisterFacade getRegisterFacade() {
		return (IRegisterFacade)ac.getBean("registerFacade");
	} 

	
	public static IExaminationFacade getExaminationFacade() {
		return (IExaminationFacade)ac.getBean("examinationFacade");
	} 

	public static IRecmaterialFacade getRecmaterialFacade() {
		return (IRecmaterialFacade)ac.getBean("recmaterialFacade");
	} 
	public static IRecMatConfigureFacade getRecMatConfigureFacade() {
		return (IRecMatConfigureFacade)ac.getBean("recMatConfigureFacade");
	}
	public static ICertificateFacade getCertificateFacade() {
		return (ICertificateFacade)ac.getBean("certificateFacade");
	}
	public static IOwnershipShiftRegFacade getOwnershipShiftRegFacade() {
		return (IOwnershipShiftRegFacade)ac.getBean("ownershipShiftRegFacade");
	}
	public static IConfigurFacade getConfigurFacade() {
		return (IConfigurFacade)ac.getBean("configurFacade");
	}
	public static IMortgageFacade getMortgageFacade() {
		return (IMortgageFacade)ac.getBean("mortgageFacade");
	}
	public static IChangeRecordFacade getChangeRecordFacade() {
		return (IChangeRecordFacade)ac.getBean("changeRecordFacade");
	}

	public static IPresaleFacade getPresaleFacade() {
		return (IPresaleFacade)ac.getBean("presaleFacade");
	}
	
	public static IUserManageFacade getUserManageFacade() {
		return (IUserManageFacade)ac.getBean("userManageFacade");
	}

	public static IMonitorFacade getMonitorFacade(){
		return (IMonitorFacade)ac.getBean("monitorFacade");
	}
	public static IQualityinspectionFacade getQualityinspectionFacade(){
		return (IQualityinspectionFacade)ac.getBean("qualityinspectionFacade");
	}
	public static IImageFacade getImageFacade(){
		return (IImageFacade)ac.getBean("imageFacade");
	}
	
	public static IFileRelFacade getFileRelFacade(){
		return (IFileRelFacade)ac.getBean("fileRelFacade");
	}

	public static IBaseDataFacade getBaseDataFacade() {
		return (IBaseDataFacade)ac.getBean("baseDataFacade");
	}
}

