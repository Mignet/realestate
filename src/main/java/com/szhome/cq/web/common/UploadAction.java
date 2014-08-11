/**
 * Project Name:dxtx_re
 * File Name:UploadAction.java
 * Package Name:com.szhome.cq.web.common
 * Date:2014-5-30����2:53:59
 * Copyright (c) 2014, DXTX All Rights Reserved.
 *
*/

package com.szhome.cq.web.common;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.util.JSONUtils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.plan.commons.Row;
import com.plan.fileupload.utils.FileUtil;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.domain.model.Image;
import com.szhome.cq.ireport.ReportPrint;
import com.szhome.cq.utils.JsonUtil;

/**
 * �ļ��ϴ�
 * Date:     2014-5-30 ����2:53:59 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class UploadAction extends BaseDelegate{
//	private String imageId;			//ͼƬID
	
	
	/**
	 * 
	 * flash���� �ϴ�  ���淽�� 
	 *
	 * @author Joyon
	 * @return
	 * @throws IOException 
	 * @since JDK 1.6
	 */
	public String upload(Row row) throws IOException{
		boolean a1 = false;
		String action = row.getString("a");
//		System.out.println(action);
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//		HttpServletResponse response = ((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();
		HttpServletResponse response = (HttpServletResponse) request.getSession().getAttribute("response");				//ǰ�˷���session�е�Response 
		
//		response.setCharacterEncoding("GBK");
//		response.setHeader("contentType", "text/html");
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		//�ϴ�ͼƬ
		if("uploadavatar".equals(action)){//�ϴ���ʱͼƬ,�����Լ�ʵ��
			//System.out.println(action);
			ServletInputStream sis= request.getInputStream();  
			  
		    int count=0;  
		  
		    byte[] b=new byte[2048];  
		    
		    String basePath = request.getSession().getServletContext().getRealPath("/");
		    String filePath = basePath+"\\upload\\"+"test1.jpg";
		    
		    
		    File file = new File(filePath);
		    FileOutputStream fos = new FileOutputStream(file);
		    if(file.createNewFile()){
		    	 while((count=sis.read(b))!=-1){  
		   		  
		    		 fos.write(b,0,count);  
		 		  
		 		    }  
		 		  
		    	 fos.flush(); 
		    }
		  
			
//			UploadUtil uploadUtil = new UploadUtil();
//			HttpSession session = request.getSession();
//			ServletContext application = session.getServletContext();
//			
//			
//			boolean isOk = uploadUtil.initParam(application,session,request,response,null);
//			if (isOk == false)
//			{
//				System.out.println("ҳ�����" + uploadUtil.getMsg());
//				return "";
//			}
//			//�ϴ�����
//			isOk = uploadUtil.uploadFile("");
//			if (isOk == false)
//			{
//				System.out.println("ҳ�����" + uploadUtil.getMsg());
//				return "";
//			}
//			Map map = uploadUtil.getUploadFiles();
//			if (map == null)
//			{
//				System.out.println("ҳ������ϴ�����ʧ��");
//			}
//			if (map.size() == 0)
//			{
//				System.out.println("ҳ�����û���ϴ�����");
//				return "";
//			}
//			String infoFilePath ="";
//			String basePath = request.getSession().getServletContext().getRealPath("/");
//			//��ȡ�����ļ����ʹ洢�ļ�·��
//			for (Iterator iter = map.keySet().iterator(); iter.hasNext();)
//			{
//				infoFilePath = (String) iter.next();
//				String infoFileName = (String) map.get(infoFilePath);
//				//infoFilePath=infoFilePath.substring(infoFilePath.lastIndexOf("\\")+1);
//				//System.out.println(infoFilePath);
//				//infoFilePath=infoFilePath.substring(infoFilePath.indexOf("webapps")+8);
//				infoFilePath=infoFilePath.replaceAll("\\\\", "/");
//				//System.out.println(basePath+"upload"+infoFilePath);
//				out.print(basePath+"upload"+infoFilePath);
//			}

	}else if("rectavatar".equals(action)){				//���ձ���
		String avatar1 = row.getString("avatar1");//��
		String avatar2 = row.getString("avatar2");//��
		String avatar3 = row.getString("avatar3");//С
		

		Image image = new Image();
		image.setImage_data(getFlashDataDecode(avatar1));
		
		//����ͼƬ
		image = FacadeFactory.getImageFacade().saveImage(image);
		
		//��ͼƬID�赽Session��
		request.getSession().setAttribute("imageId", image.getImg_id());
		
		
		
		String output ="";
		try {
			output = URLDecoder.decode(row.getString("input"));
		}catch(Exception e)
		{
			System.out.println("�������!");
		}
//		
		String[] tmp_input=output.split("@");//input���ݵ����ͺ�uid
		String pathff = request.getSession().getServletContext().getRealPath("/")+ "\\upload\\";
		String imgfilepath=pathff+tmp_input[0];
		String imagepath1=imgfilepath+"/"+tmp_input[1]+"_big.jpg";
		
		//save֮ǰ ���headĿ¼  �����Ǵ������ݿ��е�
		delAllFile(imgfilepath);
		
		 a1=saveFile(imagepath1,getFlashDataDecode(avatar1));
		
		
		
//		if(a1){
//			out.print("<?xml version=\"1.0\" ?><root><face success=\"0\"/></root>");
//		}else{
//			out.print("<?xml version=\"1.0\" ?><root><face success=\"1\"/></root>");
//		}
//		
//		
//		out.flush();
//		out.close();
	}
		

		if(a1){
			return "<?xml version=\"1.0\" ?><root><face success=\"0\"/></root>";
		}else{
			return "<?xml version=\"1.0\" ?><root><face success=\"1\"/></root>";
		}
		//return null;
	}
	
	/**
	 * 
	 * ��ȡ�ϴ��˵�imageId   �ϴ��ɹ�ʱ����session��  imageId
	 *
	 * @author Joyon
	 * @param row
	 * @return
	 * @since JDK 1.6
	 */
	public String getUploadedImageId(Row row){
		
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		Map resultMap = new HashMap();
		resultMap.put("imageId", request.getSession().getAttribute("imageId"));		//�������ȡ����   ɾ��Session��imageId
		request.getSession().removeAttribute("imageId");
		
		
		return JsonUtil.object2json(resultMap);
	}
	
	/**
	 * 
	 * flash����
	 *
	 * @author Joyon
	 * @param src
	 * @return
	 * @since JDK 1.6
	 */
	private byte[] getFlashDataDecode(String src)
	{
		char []s=src.toCharArray();
		int len=s.length;
	    byte[] r = new byte[len / 2];
	    for (int i = 0; i < len; i = i + 2)
	    {
	        int k1 = s[i] - 48;
	        k1 -= k1 > 9 ? 7 : 0;
	        int k2 = s[i + 1] - 48;
	        k2 -= k2 > 9 ? 7 : 0;
	        r[i / 2] = (byte)(k1 << 4 | k2);
	    }
	    return r;
	}
	
	
	/**
	 * 
	 * �����ļ�������������
	 *
	 * @author Joyon
	 * @param path
	 * @param b
	 * @return
	 * @since JDK 1.6
	 */
	public boolean saveFile(String path,byte[]b){
		try{
			FileOutputStream fs = new FileOutputStream(path);
		    fs.write(b, 0, b.length);
		    fs.close();
			return false;
		}catch(Exception e){
		    return true;
		}
	}
	/**
	 * 
	 * ɾ��ָ���ļ����������ļ�
	 *
	 * @author Joyon
	 * @param path �ļ�����������·��
	 * @return
	 * @since JDK 1.6
	 */
	   public static boolean delAllFile(String path) {
	       boolean flag = false;
	       File file = new File(path);
	       if (!file.exists()) {
	         return flag;
	       }
	       if (!file.isDirectory()) {
	         return flag;
	       }
	       String[] tempList = file.list();
	       File temp = null;
	       for (int i = 0; i < tempList.length; i++) {
	          if (path.endsWith(File.separator)) {
	             temp = new File(path + tempList[i]);
	          } else {
	              temp = new File(path + File.separator + tempList[i]);
	          }
	          if (temp.isFile()) {
	             temp.delete();
	          }
	          if (temp.isDirectory()) {
	             delAllFile(path + "/" + tempList[i]);//��ɾ���ļ���������ļ�
	             flag = true;
	          }
	       }
	       return flag;
	     }
	
	/**
	 * 
	 * ��ȡͼƬ
	 *
	 * @author Joyon
	 * @return
	 * @throws IOException
	 * @since JDK 1.6
	 */
	public ModelAndView getImage(Row row){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		//HttpServletResponse response = ((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();
		HttpServletResponse response = (HttpServletResponse) request.getSession().getAttribute("response");
		Image image = FacadeFactory.getImageFacade().getImageByImgId(row.getString("imageId"));
//		ByteArrayInputStream in = new ByteArrayInputStream((byte[]) image.getImage_data());    //��b��Ϊ��������
//		BufferedImage bufferImage = null;
//		try {
//			bufferImage = ImageIO.read(in);
//			OutputStream out = response.getOutputStream();
//			
//			ImageIO.write(bufferImage,"GIF",out);
//			
//			out.flush();
//		} catch (IOException e) {
//			
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//		}     //��in��Ϊ����������ȡͼƬ����image�У�������in����ΪByteArrayInputStream();
		
		   
			
			//��������
			Map parameters = new HashMap();
			parameters.put("image",image.getImage_data());
			
			ModelAndView mv = new ModelAndView(image.getImg_id()+".image",parameters);
			return mv;
		
	}
	
}


