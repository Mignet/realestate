<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.szhome.cq.utils.*"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.net.URLDecoder"%>


<%!
String imagepath1="";
//�༭ҳ���а��� camera.swf �� HTML ����
public String renderHtml(String id,String basePath,String input)
{
	String outinput="";
	try {
	outinput = URLDecoder.decode(input);
	}catch(Exception e)
	{
		System.out.println("�������!");
	}
	String[] tmp_input=outinput.split("@");//input���ݵ����ͺ�uid
	//System.out.println(input+","+tmp_input.length);
	
	String uc_api =URLEncoder.encode(basePath+"common/upload!upload.action");
	
	//input = "head";
	//String uc_api = basePath+"upload/avatar.action";
	System.out.println("uc_api:"+uc_api);
	String urlCameraFlash = "camera.swf?nt=1&inajax=1&appid=1&input="+input+"&uploadSize=1000&ucapi="+uc_api;
	System.out.println(urlCameraFlash);
	urlCameraFlash = "<script src=\"common.js?B6k\" type=\"text/javascript\"></script><script type=\"text/javascript\">document.write(AC_FL_RunContent(\"width\",\"450\",\"height\",\"253\",\"scale\",\"exactfit\",\"src\",\""+urlCameraFlash+"\",\"id\",\"mycamera\",\"name\",\"mycamera\",\"quality\",\"high\",\"bgcolor\",\"#ffffff\",\"wmode\",\"transparent\",\"menu\",\"false\",\"swLiveConnect\",\"true\",\"allowScriptAccess\",\"always\"));</script>";
	System.out.println(urlCameraFlash);
	return urlCameraFlash;
}
public String getFileExt(String fileName) {
    // ����ȡ������չ������ֻ����λ������html���ļ�������λ
    // extName = fileName.substring(fileName.length() - 3, fileName.length()); //��չ��
    int dotindex = fileName.lastIndexOf(".");
    String extName = fileName.substring(dotindex, fileName.length());
    extName = extName.toLowerCase(); //��ΪСд
    return extName;
}
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
%>

<%
String uploadtype= request.getParameter("uploadtype");
//ͼƬ�ϴ�����:ͷ��,logo,ͼƬ�ֱ��Ӧ����:head,logo,picû�д��ݣ�Ĭ��ΪPIC
if("".equals(uploadtype))
{
	uploadtype="pic";
}
//���ղü��õ�ͼƬ���λ��
String uid=request.getParameter("uid");
	imagepath1=uploadtype+"/"+uid+"_big.jpg";
	
String action= request.getParameter("a");
String personType = request.getParameter("personType");
//String input=row.getString("input");
String path = request.getContextPath();
String infoFilePath="";
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
if(action==null){
	%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<%@ include file="/base/taglibs.jsp"%>
    <%@ include file="/base/prefix.jsp"%>
	<style>
	body{
		margin: 0;
		border: 0;
	}
	</style>
	<script type="text/javascript">
	var basePath = "<%=basePath%>";
	//�����ɰ�Ťʱ���÷���
	function updateavatar() {
		//�ڵ���� ��  ���ļ�����ͼƬɾ��
		var personType = "<%=personType%>";//���������˻��Ǵ�����  
		var img1="<%=imagepath1%>";
		//var imageId='<%=session.getAttribute("imageId")%>';
		var imageId = getImageId();		//��ȡ�ϴ���imageId�Ӻ�̨��ȡ
		//alert(personType+imageId);
		//alert(img1);
		if(window.opener.document.getElementById("imgpath1"))
		{
			window.opener.document.getElementById("imgpath1").value=img1;
			window.opener.document.getElementById("showimg1").src="upload/"+img1;
		}
		
		//����ͷ��
		if(window.opener.setImage){
			window.opener.setImage(imageId,personType);
		}
		window.close();
	}
	
	//ͨ��ajax�Ӻ�̨ȡ�ñ��δ���session�е� imageId
	function getImageId(){
		var imageId;
		$.ajax({
			url:basePath+"common/upload!getUploadedImageId.action?time="+new Date(),
			dataType:"json",
			async:false,
			success:function(data){
				imageId = data.imageId;
				//alert(data.imageId);
			}
			
		});
		return imageId;
	}
	
	</script>
	</head>
	<body>
	<%
	out.print(renderHtml("5",basePath,URLEncoder.encode(uploadtype+"@"+uid)));
	%>
	</body></html>
	<%
}
%>
