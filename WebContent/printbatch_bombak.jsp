<%@ page language="java" import="java.util.*,java.text.*,javax.naming.*,java.sql.*,
com.graly.framework.activeentity.client.ADManager,com.graly.erp.wip.model.ManufactureOrder,
com.graly.framework.core.exception.ClientException,
javax.naming.NamingException" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style media=print>   
.Noprint{display:none;}   
.PageNext{page-break-after: always;} 

</style>   
 
<style>   
.ke   
{   


}   
.tabp   
{   
border-color: #000000 #000000 #000000 #000000;   
border-style: solid;   
border-top-width: 1px;   
border-right-width: 1px;   
border-bottom-width: 1px;   
border-left-width: 1px;   
}   

.NOPRINT {   
font-family: "宋体";   
font-size: 9pt;   
}   

</style>
   
 
</head>   
 
<body class="style_report" style=" margin:0px;">   
<center class="Noprint" >   
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
//String moRrns="77336741";

System.out.println(moRrns);
String[] moRrnArr= moRrns.split(",");
for(String moRrn:moRrnArr) {
	

	//List<ManufactureOrder> mos=null;
	//ManufactureOrder mo =new ManufactureOrder();
	//mo.setObjectRrn(moRrn);
	//props.setProperty("java.naming.factory.initial","org.jnp.interfaces.NamingContextFactory");
	//props.setProperty("java.naming.provider.url", "192.168.0.193:1099");
	//try {
	//	InitialContext context = new InitialContext(props);
	//	ADManager adManager = (ADManager) context.lookup("ERPwell/ADManagerBean/remote");
	//	mo = (ManufactureOrder)adManager.getEntity(mo); 
	//} catch (NamingException e) {
	//	e.printStackTrace();
	//}

//border-collapse: collapse; empty-cells: show; 
%>  
<table cellpadding="0" border="1" align="center" width="100%" height="29.5cm"  style="" class="ke" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
			<td colspan="26" height="20" class="xl65" width="718">
				上海开能环保设备股份有限公司
			</td>
		</tr>
		<tr>
			<td colspan="26" height="20" class="xl65" width="718">
				领料单
			</td>
		</tr>
		<tr>
			<td colspan="24" height="18" class="xl107" width="599">
				<span class="font9">打印日期</span><span class="font7">:</span>
			</td>
			<td colspan="2" class="xl106" width="119">
				2016-6-17
			</td>
		</tr>
		<tr>
			<td colspan="5" height="43" class="xl67" width="189">
				产品编号：04140012W
			</td>
			<td colspan="2" class="xl105" width="75">
				产品名称：
			</td>
			<td colspan="19" class="xl67" width="454">
				BNT4650(F)软水阀（白+1寸直+欧标+上集散+外置流量计+45度快插排污接头）/四套装
			</td>
		</tr>
		<tr>
			<td colspan="26" height="18" class="xl67" width="718">
				<span class="font6">备注</span><span class="font5">:</span>
			</td>
		</tr>
		<tr>
			<td colspan="26" height="58" class="xl67" width="718">
			</td>
		</tr>
		<tr>
			<td colspan="5" height="18" class="xl67" width="189">
				工作令：AWTM060939
			</td>
			<td colspan="2" class="xl67" width="75">
				数量：
			</td>
			<td colspan="4" class="xl67" width="75">
				40
			</td>
			<td colspan="5" class="xl66" width="75">
				申领车间：
			</td>
			<td colspan="3" class="xl67" width="74">
				控制阀车间
			</td>
			<td colspan="7" class="xl66" width="230">
				&nbsp;年 &nbsp; 月
  &nbsp; 日
			</td>
		</tr>
		<tr>
			<td colspan="2" rowspan="2" height="36" class="xl69" >
				物料编号
			</td>
			<td colspan="6" rowspan="2" class="xl69" >
				名称
			</td>
			<td colspan="2" rowspan="2" class="xl69" >
				批次类型
			</td>
			<td colspan="2" rowspan="2" class="xl69" >
				单位
			</td>
			<td colspan="2" rowspan="2" class="xl69" width="30">
				单耗
			</td>
			<td colspan="2" rowspan="2" class="xl69" width="37">
				数量
			</td>
			<td colspan="4" class="xl75" width="111">
				实领数量
			</td>
			<td colspan="4" class="xl75" width="74">
				型号/数量确认
			</td>
			<td colspan="2" rowspan="2" class="xl69" width="119">
				备注
			</td>
		</tr>
		<tr>
			<td height="18" class="xl78" width="37">
				日
			</td>
			<td colspan="2" class="xl79" width="37">
				日
			</td>
			<td class="xl78" width="37">
				日
			</td>
			<td colspan="2" class="xl75" width="37">
				OK
			</td>
			<td colspan="2" class="xl75" width="37">
				NO
			</td>
		</tr>
		
		
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
		sf.append(moRrn);
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
			 out.println("<td  colspan='2' class='xl84' >"+lotType+"</td>");
			 out.println("<td  colspan='2' class='xl81'>"+rs.getString("MB_UOM_ID")+"</td>");
			 out.println("<td  colspan='2' class='xl81' >"+rs.getBigDecimal("MB_QTY_UNIT")+"</td>");
			 out.println("<td  colspan='2' class='xl86' >"+rs.getBigDecimal("MO_QTY_PRODUCT")+"</td>");
			 out.println("<td  class='xl88' ></td>");
			 out.println("<td  colspan='2' class='xl89' ></td>");
			 out.println("<td  class='xl88' ></td>");
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
		<tr>
			<td colspan="8" height="18" class="xl102" width="288">
				首件外观检验:
			</td>
			<td colspan="2" class="xl102" width="30">
			</td>
			<td colspan="2" class="xl102" width="29">
			</td>
			<td colspan="2" class="xl102" width="30">
			</td>
			<td colspan="2" class="xl102" width="37">
			</td>
			<td class="xl101" width="37">
			</td>
			<td colspan="2" class="xl102" width="37">
			</td>
			<td class="xl101" width="37">
			</td>
			<td colspan="2" class="xl102" width="37">
			</td>
			<td colspan="2" class="xl102" width="37">
			</td>
			<td colspan="2" class="xl102" width="119">
			</td>
		</tr>
		<tr>
			<td colspan="8" height="18" class="xl102" width="288">
				首件功能测试:
			</td>
			<td colspan="2" class="xl102" width="30">
			</td>
			<td colspan="2" class="xl102" width="29">
			</td>
			<td colspan="2" class="xl102" width="30">
			</td>
			<td colspan="2" class="xl102" width="37">
			</td>
			<td class="xl101" width="37">
			</td>
			<td colspan="2" class="xl102" width="37">
			</td>
			<td class="xl101" width="37">
			</td>
			<td colspan="2" class="xl102" width="37">
			</td>
			<td colspan="2" class="xl102" width="37">
			</td>
			<td colspan="2" class="xl102" width="119">
			</td>
		</tr>
		<tr>
			<td height="18" class="xl67" width="59">
				发料员：
			</td>
			<td colspan="2" class="xl68" width="59">
			</td>
			<td class="xl67" width="59">
				领料员：
			</td>
			<td colspan="2" class="xl68" width="59">
			</td>
			<td colspan="3" class="xl67" width="59">
				财务主管:
			</td>
			<td colspan="4" class="xl68" width="59">
			</td>
			<td colspan="2" class="xl67" width="59">
				记帐
			</td>
			<td colspan="3" class="xl68" width="58">
			</td>
			<td colspan="3" class="xl67" width="58">
				领料部门主管
			</td>
			<td colspan="2" class="xl68" width="58">
			</td>
			<td colspan="2" class="xl67" width="59">
				制单：
			</td>
			<td class="xl68" width="72">
			</td>
		</tr>
	</tbody>
</table>
<div class="PageNext"></div>   
  <%   }
%>
</body>   
</html>  