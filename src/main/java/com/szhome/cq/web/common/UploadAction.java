/**
 * Project Name:dxtx_re
 * File Name:UploadAction.java
 * Package Name:com.szhome.cq.web.common
 * Date:2014-5-30下午2:53:59
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
 * 文件上传
 * Date:     2014-5-30 下午2:53:59 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class UploadAction extends BaseDelegate{
//	private String imageId;			//图片ID
	
	
	/**
	 * 
	 * flash请求 上传  保存方法 
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
		HttpServletResponse response = (HttpServletResponse) request.getSession().getAttribute("response");				//前端放入session中的Response 
		
//		response.setCharacterEncoding("GBK");
//		response.setHeader("contentType", "text/html");
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		//上传图片
		if("uploadavatar".equals(action)){//上传临时图片,可以自己实现
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
//				System.out.println("页面出错：" + uploadUtil.getMsg());
//				return "";
//			}
//			//上传附件
//			isOk = uploadUtil.uploadFile("");
//			if (isOk == false)
//			{
//				System.out.println("页面出错：" + uploadUtil.getMsg());
//				return "";
//			}
//			Map map = uploadUtil.getUploadFiles();
//			if (map == null)
//			{
//				System.out.println("页面出错：上传附件失败");
//			}
//			if (map.size() == 0)
//			{
//				System.out.println("页面出错：没有上传附件");
//				return "";
//			}
//			String infoFilePath ="";
//			String basePath = request.getSession().getServletContext().getRealPath("/");
//			//获取附件文件名和存储文件路径
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

	}else if("rectavatar".equals(action)){				//拍照保存
		String avatar1 = row.getString("avatar1");//大
		String avatar2 = row.getString("avatar2");//中
		String avatar3 = row.getString("avatar3");//小
		

		Image image = new Image();
		image.setImage_data(getFlashDataDecode(avatar1));
		
		//保存图片
		image = FacadeFactory.getImageFacade().saveImage(image);
		
		//把图片ID设到Session中
		request.getSession().setAttribute("imageId", image.getImg_id());
		
		
		
		String output ="";
		try {
			output = URLDecoder.decode(row.getString("input"));
		}catch(Exception e)
		{
			System.out.println("解码错误!");
		}
//		
		String[] tmp_input=output.split("@");//input传递的类型和uid
		String pathff = request.getSession().getServletContext().getRealPath("/")+ "\\upload\\";
		String imgfilepath=pathff+tmp_input[0];
		String imagepath1=imgfilepath+"/"+tmp_input[1]+"_big.jpg";
		
		//save之前 清空head目录  数据是存在数据库中的
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
	 * 获取上传了的imageId   上传成功时存在session中  imageId
	 *
	 * @author Joyon
	 * @param row
	 * @return
	 * @since JDK 1.6
	 */
	public String getUploadedImageId(Row row){
		
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		Map resultMap = new HashMap();
		resultMap.put("imageId", request.getSession().getAttribute("imageId"));		//在下面获取过后   删除Session中imageId
		request.getSession().removeAttribute("imageId");
		
		
		return JsonUtil.object2json(resultMap);
	}
	
	/**
	 * 
	 * flash解码
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
	 * 保存文件到服务器本地
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
	 * 删除指定文件夹下所有文件
	 *
	 * @author Joyon
	 * @param path 文件夹完整绝对路径
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
	             delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
	             flag = true;
	          }
	       }
	       return flag;
	     }
	
	/**
	 * 
	 * 获取图片
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
//		ByteArrayInputStream in = new ByteArrayInputStream((byte[]) image.getImage_data());    //将b作为输入流；
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
//		}     //将in作为输入流，读取图片存入image中，而这里in可以为ByteArrayInputStream();
		
		   
			
			//报表设置
			Map parameters = new HashMap();
			parameters.put("image",image.getImage_data());
			
			ModelAndView mv = new ModelAndView(image.getImg_id()+".image",parameters);
			return mv;
		
	}
	
}


