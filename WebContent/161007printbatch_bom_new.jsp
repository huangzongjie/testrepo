<%@ page language="java" import="java.util.*,java.text.*,javax.naming.*,java.sql.*,
com.graly.framework.activeentity.client.ADManager,com.graly.erp.wip.model.ManufactureOrder,
com.graly.framework.core.exception.ClientException,
javax.naming.NamingException" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>  
@media print{   
.toolbar{display:none;}   
}   
.toolbar{border:1px solid #6A9BFA;background:#E8F7E8;}   
.paging{page-break-after :always}   
td{font-size:12px;color:#000000;border: 1px solid black;}   
</style>  
 
</head>   
 
<body class="style_report" >   
<center class="toolbar" >   
<p>   
<OBJECT id=WebBrowser classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height=0 width=0>   
</OBJECT>   
<input type=button value=打印 onclick=document.all.WebBrowser.ExecWB(6,1)>   
<input type=button value=直接打印 onclick=document.all.WebBrowser.ExecWB(6,6)>   
<input type=button value=页面设置 onclick=document.all.WebBrowser.ExecWB(8,1)>   
</p>   
<p> <input type=button value=打印预览 onclick=document.all.WebBrowser.ExecWB(7,1)>   
<br/>   
</p>   
<hr align="center" width="90%" size="1" noshade>   
</center> 
<%
String moRrns=request.getParameter("moRrns");
System.out.println(moRrns);
String[] moRrnArr= moRrns.split(",");
Properties props = new Properties();
List<ManufactureOrder> mos=null;
props.setProperty("java.naming.factory.initial","org.jnp.interfaces.NamingContextFactory");
props.setProperty("java.naming.provider.url", "192.168.0.193:1099");
try {
	InitialContext context = new InitialContext(props);
	ADManager adManager = (ADManager) context.lookup("ERPwell/ADManagerBean/remote");
	 mos = adManager.getEntityList(139420L, ManufactureOrder.class,Integer.MAX_VALUE,"object_rrn in ("+moRrns+")","docId"); 
	try {
		System.out.println(mos.get(0).getDocId());
	} catch (Exception e) {
		e.printStackTrace();
	}

} catch (NamingException e) {
	e.printStackTrace();
}


int i = moRrnArr.length;
int j=0;
 
for(ManufactureOrder mo:mos) {
	++j;
 
%>  
<table width="756px" cellpadding="0" cellspacing="0" style="border: none;border-collapse:collapse" >
 <THEAD style="display:table-header-group;font-weight:bold">  
	 	<%
	 	//	if(j ==1){
	 		//	 out.println("<tr align='center'><td style='border: 0px;' colspan='26'><h2 style='margin-top: 0px;margin-bottom: 0px'>上海开能环保设备股份有限公司</h2></td></tr>");
	 	//	}
	 	
	 	
	 	%> 
	 	<tr align='center'><td style='border: 0px;' colspan='26'><h2 style='margin-top: 0px;margin-bottom: 0px'>上海开能环保设备股份有限公司</h2></td></tr>
		<!-- <tr>
			<td colspan="26" height="20" class="xl65" width="718">
				上海开能环保设备股份有限公司
			</td>
		</tr> -->
		<tr align="center" width="756px">
			<td colspan="26" style="border: 0px">
				<h2 style="margin-top: 0px;margin-bottom: 0px" >领料单</h2> 
			</td>
		</tr>
		<tr width="756px">
			<td colspan="26" style="border: 0px" align="right" >
				<span class="font9">打印日期:</span><span class="font7"><%=new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime())%></span>
			</td>
			 
		</tr>
		<tr width="756px">
			<td colspan="5" style="border: 0px">
				产品编号：<%=mo.getMaterialId()%>
			</td>
			<td colspan="21" style="border: 0px">
				产品名称：<%=mo.getMaterialName()%>
			</td>
		</tr>
		<tr>
			<td colspan="26" style="border: 0px">
				<span class="font6">备注:</span><span class="font5"></span>
				<%=mo.getComments()%>
			</td>
		</tr>
		<tr>
			<td colspan="5" style="border: 0px">
				工作令：<%=mo.getDocId()%>
			</td>
			<td colspan="2" style="border: 0px">
				数量：
			</td>
			<td colspan="4" style="border: 0px">
				<%=mo.getQtyProduct()%>
			</td>
			<td colspan="5" style="border: 0px">
				申领车间：
			</td>
			<td colspan="3" style="border: 0px">
				<%=mo.getWorkCenterId()%>
			</td>
			<td colspan="7" style="border: 0px">
				&nbsp;年 &nbsp; 月&nbsp; 日
			</td>
		</tr>
		<tr width="756px">
			<td colspan="2" rowspan="2" width="100px">
				物料编号
			</td>
			<td colspan="6" rowspan="2" width="350px">
				名称
			</td>
			<td colspan="1" rowspan="2" width="20px">
				批次类型
			</td>
			<td colspan="1" rowspan="2" width="20px">
				单位
			</td>
			<td colspan="2" rowspan="2"  >
				单耗
			</td>
			<td colspan="2" rowspan="2" >
				数量
			</td>
			<td colspan="6"	width="48mm">
				实领数量
			</td>
			<td colspan="4" width="32mm">
				型号/数量确认
			</td>
			<td colspan="2" rowspan="2" width="16mm">
				备注
			</td>
		</tr>
		<tr>
			<td colspan="2" width="16mm">
				日
			</td>
			<td colspan="2" width="16mm">
				日
			</td>
			<td colspan="2" width="16mm">
				日
			</td>
			<td colspan="2" width="16mm">
				OK
			</td>
			<td colspan="2" width="16mm">
				NO
			</td>
		</tr>
</THEAD>  	
		
<%
Connection conn= null;
Statement stmt=null;
		ResultSet rs=null;
		try{
Class.forName("oracle.jdbc.driver.OracleDriver");
conn =DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.229:1521:erpdb","erpwell","hzzerp");
	stmt=conn.createStatement();
		
		StringBuffer sf = new StringBuffer();
		sf.append(" SELECT TREE.* ");
		sf.append(" From ");
		sf.append(" ( ");
		sf.append(" SELECT  level,rs.* ");
		sf.append(" FROM ");
		sf.append("   ( ");
		sf.append("   SELECT ");
		sf.append("     LPAD('',(MB.PATH_LEVEL-1)*6,' ')|| ");
		sf.append("     RPAD('└',(MB.PATH_LEVEL-1)*2,' ')|| ");
		sf.append("     M.MATERIAL_ID                M_MATERIAL_ID, ");
		sf.append("     MO.USER_CREATED                MO_USER_CREATED, ");
		sf.append("     MO.QTY_PRODUCT                MO_QTY_PRODUCT, ");
		sf.append("     MB.QTY_BOM                  MB_QTY_BOM, ");
		sf.append("     M.NAME                    M_NAME, ");
		sf.append("     M.LOT_TYPE                  M_LOT_TYPE, ");
		sf.append("     MB.PATH_LEVEL                MB_PATH_LEVEL, ");
		sf.append("     MB.DESCRIPTION                MB_COMMENTS, ");
		sf.append("     MB.MATERIAL_RRN                MB_MATERIAL_RRN, ");
		sf.append("     MB.MATERIAL_PARENT_RRN            MB_MATERIAL_PARENT_RRN, ");
		sf.append("     MB.UOM_ID                  MB_UOM_ID, ");
		sf.append("     MB.QTY_UNIT                  MB_QTY_UNIT, ");
		sf.append("     MB.DESCRIPTION                MB_DESCRIPTION ");
		sf.append("   FROM   ");
		sf.append("     WIP_MO      MO, ");
		sf.append("     WIP_MO_BOM    MB, ");
		sf.append("     PDM_MATERIAL  M ");
		sf.append("   WHERE  MO.OBJECT_RRN =  ");
		sf.append(mo.getObjectRrn());
		sf.append("     and MB.MO_RRN = MO.OBJECT_RRN ");
		sf.append("     and MB.MATERIAL_PARENT_RRN not in ( ");
		sf.append("                       select MA.OBJECT_RRN from PDM_MATERIAL MA  ");
		sf.append("                       where MA.MATERIAL_ID in ('07010004','07010006','07010013', ");
		sf.append("                       '07010014','07010042','07010044','07010045','07010053', ");
		sf.append("                       '07010058','07010062') ");
		sf.append("                       ) ");
		sf.append("     and not ( MB.PATH_LEVEL > 1 and MB.MATERIAL_PARENT_RRN in ( ");
		sf.append("                       select MAT.OBJECT_RRN from PDM_MATERIAL MAT  ");
		sf.append("                       where  MAT.MATERIAL_ID like '0750%' ");
		sf.append("                         or MAT.MATERIAL_ID like '22018%' ");
		sf.append("                         or MAT.MATERIAL_ID like '22050%' ");
		sf.append("                         or MAT.MATERIAL_ID like '0215%' ");
		sf.append("                       )) ");
		sf.append("     and (MB.MATERIAL_RRN in ( select MAT1.OBJECT_RRN from PDM_MATERIAL MAT1  ");
		sf.append("                   where MAT1.MATERIAL_ID in ('17090016')) OR MB.PATH_LEVEL = 1 OR MB.MATERIAL_RRN not in ( ");
		sf.append("                   select MAT.OBJECT_RRN from PDM_MATERIAL MAT  ");
		sf.append("                   where MAT.MATERIAL_ID like '1709%'                         ");
		sf.append("                   )) ");
		sf.append("     and  M.OBJECT_RRN = MB.MATERIAL_RRN ");
		sf.append("  ");
		sf.append("   ) rs ");
		sf.append(" START WITH rs.MB_path_level = 1 ");
		sf.append(" CONNECT BY PRIOR rs.MB_material_rrn = rs.MB_material_parent_rrn) TREE ");
		sf.append(" Where TREE.MB_PATH_LEVEL > 0  ");
		
rs=stmt.executeQuery(sf.toString());
	while(rs.next())
	    {
			String lotType= "";
			 if(rs.getString("M_LOT_TYPE")!=null && !"".equals(rs.getString("M_LOT_TYPE"))){
				 lotType=rs.getString("M_LOT_TYPE").substring(0,1);
			 }
			 out.println("<tr>");
			 out.println("<td  colspan='2' height='18' class='xl81' >"+rs.getString("M_MATERIAL_ID")+"</td>");
			 out.println("<td  colspan='6' class='xl81' >"+rs.getString("M_NAME") +"</td>");
			 out.println("<td  colspan='1' class='xl84' >"+lotType+"</td>");
			 out.println("<td  colspan='1' class='xl81'>"+rs.getString("MB_UOM_ID")+"</td>");
			 out.println("<td  colspan='2' class='xl81' >"+rs.getBigDecimal("MB_QTY_UNIT")+"</td>");
			 out.println("<td  colspan='2' class='xl86' >"+rs.getBigDecimal("MO_QTY_PRODUCT")+"</td>");
			 out.println("<td  colspan='2' ></td>");
			 out.println("<td  colspan='2' class='xl89' ></td>");
			 out.println("<td  colspan='2' ></td>");
			 out.println("<td  colspan='2' class='xl89' ></td>");
			 out.println("<td  colspan='2' class='xl89' ></td>");
			 out.println("<td  colspan='2' class='xl81' ></td>");
			 out.println("</tr>");	 
	    }

	}catch(Exception e){
	out.println(e);
	rs.close();
	conn.close();
	stmt.close();
	}
rs.close();
	conn.close();
	stmt.close();
 
%>
<TFOOT style="display:table-footer-group;font-weight:bold"> 
		<tr>
			<td colspan="8">首件外观检验:</td>
			<td colspan="1"></td>
			<td colspan="1"></td>
			<td colspan="2"></td>
			<td colspan="2"></td>
			<td colspan="2"></td>
			<td colspan="2"></td>
 			<td colspan="2"></td>
 			<td colspan="2"></td>
 			<td colspan="2"></td>
 			<td colspan="2"></td>
		</tr>
		<tr>
			<td colspan="8">首件功能测试:</td>
			<td colspan="1"></td>
			<td colspan="1"></td>
			<td colspan="2"></td>
			<td colspan="2"></td>
			<td colspan="2"></td>
			<td colspan="2"></td>
 			<td colspan="2"></td>
 			<td colspan="2"></td>
 			<td colspan="2"></td>
 			<td colspan="2"></td>
		</tr>
		<tr>

			<td colspan="4" style="border: 0px">
			发料员:
			</td>
			<td colspan="2" style="border: 0px">
			领料员:
			</td>
			<td colspan="4" style="border: 0px">
			财务主管:
			</td>
			<td colspan="4" style="border: 0px">
			记帐
			</td>
			<td colspan="8" style="border: 0px">
			领料部门主管
			</td>
			<td colspan="6" style="border: 0px">
			制单：
			</td>
		</tr>
</TFOOT>
</table>
<%
	if(i !=j){
		 out.println("<DIV STYLE='page-break-before:always'></DIV>");
	}
%> 

  <%   }
%>
</body>   
</html>  