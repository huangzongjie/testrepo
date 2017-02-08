<%@ page language="java" import="java.util.*,javax.naming.*,
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
.tdp   
{   
border-bottom: 1 solid #000000;   
border-left: 1 solid #000000;   
border-right: 0 solid #ffffff;   
border-top: 0 solid #ffffff;   
}   
.tabp   
{   
border-color: #000000 #000000 #000000 #000000;   
border-style: solid;   
border-top-width: 2px;   
border-right-width: 2px;   
border-bottom-width: 1px;   
border-left-width: 1px;   
}   
.NOPRINT {   
font-family: "宋体";   
font-size: 9pt;   
}   
 
</style>   
 
</head>   
 
<body >   
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
String moRrn=request.getParameter("moRrn");
System.out.println(moRrn);
Properties props = new Properties();
List<ManufactureOrder> mos=null;
props.setProperty("java.naming.factory.initial","org.jnp.interfaces.NamingContextFactory");
props.setProperty("java.naming.provider.url", "192.168.0.230:1099");
try {
	InitialContext context = new InitialContext(props);
	ADManager adManager = (ADManager) context.lookup("ERPwell/ADManagerBean/remote");
	 mos = adManager.getEntityList(139420L, ManufactureOrder.class,Integer.MAX_VALUE,"object_rrn in ("+moRrn+")",null); 
	try {
		System.out.println("xxxxxx");
		System.out.println(mos.get(0).getDocId());
	} catch (Exception e) {
		e.printStackTrace();
	}

} catch (NamingException e) {
	e.printStackTrace();
}
if(mos!=null){
    for(ManufactureOrder mo : mos) {
%>  
<table border="1" align="center"  cellpadding="0" cellspacing="0" width="23cm" style="width:23cm;height:13cm">
		<tr align="center">
			<td colspan="7" height="27" class="xl65" width="717">
				<b>生产任务单</b>
			</td>
		</tr>
		<tr>
			<td colspan="5" height="19" class="xl66" width="530">
				&nbsp;
			</td>
			<td colspan="2" class="xl66" width="187">
				BJ/CN 026-01
			</td>
		</tr>
		<tr>
			<td colspan="5" height="19" class="xl66" width="530">
				生产性质:□成品&nbsp;&nbsp;&nbsp;□半成品
			</td>
			<td colspan="2" class="xl66" width="187">
				工作令编号：<%=mo.getDocId()%>
			</td>
		</tr>
		<tr>
			<td height="89" class="xl73" width="113">
				物料编号
			</td>
			<td class="xl73" width="151">
				<%=mo.getMaterialId()%>
			</td>
			<td class="xl73" width="93">
				产品名称
			</td>
			<td colspan="4" class="xl74" width="360">
				<%=mo.getMaterialName()%>
			</td>
		</tr>
		<tr>
			<td height="32" class="xl73" width="113">
				重量
			</td>
			<td class="xl73" width="151">
				<%=mo.getMaterial().getWeight()==null?"":mo.getMaterial().getWeight()%>
			</td>
			<td class="xl73" width="93">
				体积
			</td>
			<td class="xl67" width="104">
				<%=mo.getMaterial().getVolume()==null?"":mo.getMaterial().getVolume()%>
			</td>
			<td colspan="2" class="xl74" width="154">
			</td>
			<td class="xl73" width="102">
			</td>
		</tr>
		<tr>
			<td height="39" class="xl73" width="113">
				生产数量
			</td>
			<td colspan="2" class="xl74" width="244">
				<%=mo.getQtyProduct()%>
			</td>
			<td class="xl73" width="104">
				完工数量
			</td>
			<td colspan="3" class="xl74" width="256">
			</td>
		</tr>
		<tr>
			<td height="46" class="xl73" width="113">
				客户名称
			</td>
			<td colspan="2" class="xl74" width="244">
				<%=mo.getCustomerName()%>
			</td>
			<td class="xl73" width="104">
				订单编号
			</td>
			<td colspan="3" class="xl74" width="256">
				<%=mo.getOrderId()%>
			</td>
		</tr>
		<tr>
			<td height="33" class="xl73" width="113">
				生产起止日期
			</td>
			<td colspan="6" class="xl68" width="604">
				2016年6月3日 至 2016年6月24日
			</td>
		</tr>
		<tr>
			<td height="46" class="xl73" width="113">
				生产班组
			</td>
			<td colspan="6" class="xl68" width="604">
				☑软水机车间 □净水机 □饮水机 □阀车间 □缠绕车间<br />
□部件车间 □吹塑车间 □注塑车间 □仓库+采购车间 □切割车间□自动化车间
			</td>
		</tr>
		<tr>
			<td height="35" class="xl73" width="113">
				入库
			</td>
			<td class="xl73" width="151">
				☑总仓&nbsp; □待品库
			</td>
			<td class="xl73" width="93">
				质量检验员
			</td>
			<td colspan="4" class="xl74" width="360">
			</td>
		</tr>
		<tr>
			<td height="40" class="xl73" width="113">
				备注
			</td>
			<td colspan="6" class="xl68" width="604">
			</td>
		</tr>
		<tr>
			<td height="47" class="xl77" width="113">
				是否打印客户订单编号
			</td>
			<td class="xl72">
				□是&nbsp;&nbsp; □否
			</td>
			<td class="xl72">
			</td>
			<td class="xl71" width="104">
				是否打印客户的编号
			</td>
			<td colspan="2" class="xl71" width="154">
				&nbsp;&nbsp;&nbsp; □是&nbsp;&nbsp;
  □否
			</td>
			<td class="xl72">
			</td>
		</tr>
</table>
<div class="PageNext"></div>   
 <%   }
   } %>
</body>   
</html>  