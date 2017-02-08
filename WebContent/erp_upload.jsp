<%@ page language="java" import="java.util.*,java.text.*,javax.naming.*,
bean.*,upload.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style media=print>   
</style>   
 
<style>   

</style>   
 
</head>   

<body >   
 <form action="${pageContext.request.contextPath}/upload/UploadHandleServlet" enctype="multipart/form-data" method="post">
  <!--         上传用户：<input type="text" name="username"><br/>-->
        上传文件1：<input type="file" name="file1"><br/>
 <!--       上传文件2：<input type="file" name="file2"><br/> -->
        <input type="submit" value="提交">
    </form>
    
    <table>
    <tr>
    	<td>物料ID</td>
    	<td>上传名称</td>
    	<td>创建时间</td>
    </tr>
 <%
Upload upload = new Upload();
 List<BaseFile> baseFiles=  upload.getBaseFiles("123");
 for(BaseFile baseFile :baseFiles){
	 out.println("<tr>");
	 out.println("<td>"+baseFile.getMaterialId()+"</td>");
	 out.println("<td>"+baseFile.getFileName() +"</td>");
	 out.println("<td>"+baseFile.getCreated()+"</td>");
	 out.println("</tr>");	 
 }
%>  
     
    
    </table>
  </body>
</html> 