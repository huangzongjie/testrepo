package upload;

import java.sql.*;
import java.util.*;
import java.text.*;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

import bean.*;

/*
 ��ݿ�ҵ��Bean
 */

public class Custom {
	private boolean flag = false;
	private Connection con = null;
	private ResultSet rs = null;
	private PreparedStatement pstmt = null;
	private PreparedStatement pstmt1 = null;
	private PreparedStatement pstmt2 = null;
	private PreparedStatement pstmt3 = null;
	private PreparedStatement pstmt4 = null;
	private PreparedStatement pstmt5 = null;
	private PreparedStatement pstmt6 = null;
	private PreparedStatement pstmt7 = null;
	private PreparedStatement pstmt8 = null;
	private PreparedStatement pstmt9 = null;
	public static Logger crmLog = Logger.getLogger("crmLog");

	public Custom() {

	}

	public String selectBentaiCustomsql(int page, String table_head,
			String tj_name1, String tj_name2, String tj_name3, String tj_name4,
			String tj_v1, String tj_v2, String tj_v3, String tj_v4,
			String taxis_name, String taxis_v, String status) {
		int size = 0;
		int sizepa = 0;
		String sql = "";
		String where = "";
		String tj_v11 = "0";
		String tj_v21 = "0";
		try {
			sql = "select count(*) " + "from cana_custom  " + " where 1=1 ";
			if ((!tj_v1.equals("")) && (tj_v1 != null)) {
				if (tj_v11.equals("0")) {
					String tj_v1_per = "";
					for (int i = 0; i < tj_v1.length(); i++) {
						tj_v1_per += tj_v1.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name1
							+ " like '%" + tj_v1_per + "%'";
				} else if (tj_v11.equals("1")) {
					where = where + " and " + table_head + "bom_info."
							+ tj_name1 + " like '" + tj_v1 + "%'";
				} else if (tj_v11.equals("2")) {
					where = where + " and " + table_head + "bom_info."
							+ tj_name1 + " like '%" + tj_v1 + "%'";
				} else if (tj_v11.equals("3")) {
					where = where + " and " + table_head + "bom_info."
							+ tj_name1 + " not like '%" + tj_v1 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name1
							+ "='" + tj_v1 + "'";
				}
			}
			if ((!tj_v2.equals("")) && (tj_v2 != null)) {
				if (tj_v21.equals("0")) {
					String tj_v2_per = "";
					for (int i = 0; i < tj_v2.length(); i++) {
						tj_v2_per += tj_v2.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name2
							+ " like '%" + tj_v2_per + "%'";
				} else if (tj_v21.equals("1")) {
					where = where + " and " + table_head + "bom_info."
							+ tj_name2 + " like '" + tj_v2 + "%'";
				} else if (tj_v21.equals("2")) {
					where = where + " and " + table_head + "bom_info."
							+ tj_name2 + " like '%" + tj_v2 + "%'";
				} else if (tj_v21.equals("3")) {
					where = where + " and " + table_head + "bom_info."
							+ tj_name2 + " not like '%" + tj_v2 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name2
							+ "='" + tj_v2 + "'";
				}
			}
			if ((!tj_v3.equals("")) && (tj_v3 != null)) {
				where = where + " and " + table_head + "custom." + tj_name3
						+ "='" + tj_v3 + "'";
			}
			if ((!tj_v4.equals("")) && (tj_v4 != null)) {
				where = where + " and " + table_head + "custom." + tj_name4
						+ "='" + tj_v4 + "'";
			}

			if ((!status.equals("")) && (status != null)) {
				where = where + " and " + table_head + "custom.status='"
						+ status + "'";
			}

			String orderby = "";
			sql = sql + where + orderby;
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				size = rs.getInt(1);
				sizepa = (int) size / page;
				if (size % page > 0) {
					sizepa = sizepa + 1;
				}
			}

			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog.error("Error in method select_singlesql() of Custom,error:"
					+ ex.getMessage() + ",sql:" + sql);
			// System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
			return sizepa + "+" + size;
		}

	}

	public Collection selectBentaiCustom(int step, int page, String table_head,
			String tj_name1, String tj_name2, String tj_name3, String tj_name4,
			String tj_v1, String tj_v2, String tj_v3, String tj_v4,
			String tj_v11, String tj_v21, String tj_v31, String tj_v41,
			String taxis_name, String taxis_v, String status) {
		Collection coll = new ArrayList();
		int tip = step * (page - 1);
		String sql = "";

		try {
			sql = "select * from cana_custom ";
			String where = " where id>0 ";
			if ((!tj_v1.equals("")) && (tj_v1 != null)) {
				if (tj_v11.equals("0")) {
					String tj_v1_per = "";
					for (int i = 0; i < tj_v1.length(); i++) {
						tj_v1_per += tj_v1.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name1
							+ " like '%" + tj_v1_per + "%'";
				} else if (tj_v11.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name1
							+ " like '" + tj_v1 + "%'";
				} else if (tj_v11.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name1
							+ " like '%" + tj_v1 + "%'";
				} else if (tj_v11.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name1
							+ " not like '%" + tj_v1 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name1
							+ "='" + tj_v1 + "'";
				}
			}
			if ((!tj_v2.equals("")) && (tj_v2 != null)) {
				if (tj_v21.equals("0")) {
					String tj_v2_per = "";
					for (int i = 0; i < tj_v2.length(); i++) {
						tj_v2_per += tj_v2.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name2
							+ " like '%" + tj_v2_per + "%'";
				} else if (tj_v21.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name2
							+ " like '" + tj_v2 + "%'";
				} else if (tj_v21.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name2
							+ " like '%" + tj_v2 + "%'";
				} else if (tj_v21.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name2
							+ " not like '%" + tj_v2 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name2
							+ "='" + tj_v2 + "'";
				}
			}
			if ((!tj_v3.equals("")) && (tj_v3 != null)) {
				if (tj_v31.equals("0")) {
					String tj_v3_per = "";
					for (int i = 0; i < tj_v3.length(); i++) {
						tj_v3_per += tj_v3.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name3
							+ " like '%" + tj_v3_per + "%'";
				} else if (tj_v31.equals("1")) {
					where = where + " and " + table_head + "bom_info.l1 like '"
							+ tj_v3 + "%'";
				} else if (tj_v31.equals("2")) {
					where = where + " and " + table_head
							+ "bom_info.l1 like '%" + tj_v3 + "%'";
				} else if (tj_v31.equals("3")) {
					where = where + " and " + table_head
							+ "bom_info.l1 not like '%" + tj_v3 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name3
							+ "='" + tj_v3 + "'";
				}
			}
			if ((!tj_v4.equals("")) && (tj_v4 != null)) {
				if (tj_v41.equals("0")) {
					String tj_v4_per = "";
					for (int i = 0; i < tj_v4.length(); i++) {
						tj_v4_per += tj_v4.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name4
							+ " like '%" + tj_v4_per + "%'";
				} else if (tj_v41.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name4
							+ " like '" + tj_v4 + "%'";
				} else if (tj_v41.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name4
							+ " like '%" + tj_v4 + "%'";
				} else if (tj_v41.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name4
							+ " not like '%" + tj_v4 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name4
							+ "='" + tj_v4 + "'";
				}
			}

			if ((!status.equals("")) && (status != null)) {
				where = where + " and " + table_head + "custom.status='"
						+ status + "'";
			}
			String orderby = " order by " + table_head + "custom.area1 asc";
			sql = sql + where + orderby;
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			if (tip <= 0) {
				rs.beforeFirst();
			} else if (!rs.absolute(tip)) {
				rs.beforeFirst();
			}
			for (int i = 1; rs.next() && i <= step; i++) {
				CustomShow CShow = new CustomShow();
				CShow.setid(rs.getInt("id"));
				CShow.setname(rs.getString("name"));
				CShow.setarea1(rs.getString("area1"));
				CShow.setarea2(rs.getString("area2"));
				CShow.setlinkman(rs.getString("linkman"));
				CShow.setaddress(rs.getString("address"));
				CShow.setzip(rs.getInt("zip"));
				CShow.setindustry(rs.getString("industry"));
				coll.add(CShow);
			}

			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog.error("Error in method select() of Bom_info,error:"
					+ ex.getMessage() + ",sql:" + sql);
			// System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {

			}

			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {

			}
			return coll;
		}
	}

	public Collection post_detail(String printid) {
		Collection coll = new ArrayList();
		String sql = " select * from cana_post_list where id=" + printid
				+ " order by customid  ";
		try {
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PostShow pShow = new PostShow();
				pShow.setCustomid(rs.getString("customid"));
				pShow.setName(rs.getString("name"));
				pShow.setAddress(rs.getString("address"));
				pShow.setZip(rs.getString("zip"));
				pShow.setLinkman(rs.getString("linkman"));
				pShow.setId(rs.getInt("id"));
				coll.add(pShow);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			crmLog.error("Error in method post_detail() of Custom,error:"
					+ e.getMessage() + ",sql:" + sql);
		}
		return coll;
	}

	public Collection post_list() {
		Collection coll = new ArrayList();
		String sql = "select c1.id as id,c1.recorder as recorder,p_time,times "
				+ "from ( select distinct id,recorder,max(record_time) as p_time "
				+ " from cana_post_list cpl " + " group by id,recorder "
				+ ")c1 " + " inner join( "
				+ " select id,count(customid) as times "
				+ " from cana_post_list " + " group by id "
				+ " ) c on c1.id=c.id " + " order by c1.id desc ";
		try {
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PostShow pShow = new PostShow();
				pShow.setId(rs.getInt("id"));
				pShow.setRecorder(rs.getString("recorder"));
				pShow.setRecord_time(rs.getString("p_time"));
				pShow.setTimes(rs.getString("times"));
				coll.add(pShow);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			crmLog.error("Error in method post_list() of Custom,error:"
					+ e.getMessage() + ",sql:" + sql);
		}
		return coll;
	}

	public int maxPostid() {
		int id = 0;
		String sql = "select case when max(id) is null then 0 else max(id) end id from cana_post_list";
		try {
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				id = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			crmLog.error("Error in method maxPostid() of Custom,error:"
					+ e.getMessage() + ",sql:" + sql);
		}
		return id;
	}

	public String allPost(String tj_v1, String tj_v2, String tj_v3,
			String tj_v4, String tj_v11, String tj_v21, String tj_v31,
			String tj_v41, String printid, String userid, String ddate) {
		String sqlNews = "";
		String sql = "";
		String table_head = "cana_";
		String where = "";
		try {
			sql = "insert into cana_post_list(id,customid,name,address,linkman,zip,recorder,record_time) ";
			sql = sql + " select " + printid
					+ ",customid,name,address,linkman,zip," + userid + ",'"
					+ ddate + "' from cana_custom where id>0 ";
			if ((!tj_v1.equals("")) && (tj_v1 != null)) {
				if (tj_v11.equals("0")) {
					String tj_v1_per = "";
					for (int i = 0; i < tj_v1.length(); i++) {
						tj_v1_per += tj_v1.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head
							+ "custom.name like '%" + tj_v1_per + "%'";
				} else if (tj_v11.equals("1")) {
					where = where + " and " + table_head
							+ "bom_info.name like '" + tj_v1 + "%'";
				} else if (tj_v11.equals("2")) {
					where = where + " and " + table_head
							+ "bom_info.name like '%" + tj_v1 + "%'";
				} else if (tj_v11.equals("3")) {
					where = where + " and " + table_head
							+ "bom_info.name not like '%" + tj_v1 + "%'";
				} else {
					where = where + " and " + table_head + "custom.name='"
							+ tj_v1 + "'";
				}
			}
			if ((!tj_v2.equals("")) && (tj_v2 != null)) {
				if (tj_v21.equals("0")) {
					String tj_v2_per = "";
					for (int i = 0; i < tj_v2.length(); i++) {
						tj_v2_per += tj_v2.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head
							+ "custom.address like '%" + tj_v2_per + "%'";
				} else if (tj_v21.equals("1")) {
					where = where + " and " + table_head
							+ "bom_info.address like '" + tj_v2 + "%'";
				} else if (tj_v21.equals("2")) {
					where = where + " and " + table_head
							+ "bom_info.address like '%" + tj_v2 + "%'";
				} else if (tj_v21.equals("3")) {
					where = where + " and " + table_head
							+ "bom_info.address not like '%" + tj_v2 + "%'";
				} else {
					where = where + " and " + table_head + "custom.address='"
							+ tj_v2 + "'";
				}
			}
			if ((!tj_v3.equals("")) && (tj_v3 != null)) {
				if (tj_v31.equals("0")) {
					String tj_v3_per = "";
					for (int i = 0; i < tj_v3.length(); i++) {
						tj_v3_per += tj_v3.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head
							+ "custom.area1 like '%" + tj_v3_per + "%'";
				} else if (tj_v31.equals("1")) {
					where = where + " and " + table_head + "bom_info.l1 like '"
							+ tj_v3 + "%'";
				} else if (tj_v31.equals("2")) {
					where = where + " and " + table_head
							+ "bom_info.l1 like '%" + tj_v3 + "%'";
				} else if (tj_v31.equals("3")) {
					where = where + " and " + table_head
							+ "bom_info.l1 not like '%" + tj_v3 + "%'";
				} else {
					where = where + " and " + table_head + "custom.area1='"
							+ tj_v3 + "'";
				}
			}
			if ((!tj_v4.equals("")) && (tj_v4 != null)) {
				if (tj_v41.equals("0")) {
					String tj_v4_per = "";
					for (int i = 0; i < tj_v4.length(); i++) {
						tj_v4_per += tj_v4.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head
							+ "custom.area2 like '%" + tj_v4_per + "%'";
				} else if (tj_v41.equals("1")) {
					where = where + " and " + table_head
							+ "custom.area2 like '" + tj_v4 + "%'";
				} else if (tj_v41.equals("2")) {
					where = where + " and " + table_head
							+ "custom.area2 like '%" + tj_v4 + "%'";
				} else if (tj_v41.equals("3")) {
					where = where + " and " + table_head
							+ "custom.area2 not like '%" + tj_v4 + "%'";
				} else {
					where = where + " and " + table_head + "custom.area2='"
							+ tj_v4 + "'";
				}
			}
			sql = sql + where;
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			int result = pstmt.executeUpdate();
			if (result > 0) {
				sqlNews = "success";
			} else {
				sqlNews = "b";
			}
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
			crmLog.info("Success allPost of Custom,sql:" + sql);
		} catch (Exception e) {
			crmLog.error("Error in method allPost() of Custom,error:"
					+ e.getMessage() + ",sql:" + sql);
		}
		return sqlNews;

	}

	public String deletePost(String customid, String printid, String user) {
		String sqlNews = "";
		String sql = "";
		try {
			sql = "delete from cana_post_list where id=" + printid
					+ " and customid=" + customid;
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			int result = pstmt.executeUpdate();
			if (result > 0) {
				sqlNews = "success";
			} else {
				sqlNews = "b";
			}
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
			crmLog.info("Success delete of Custom,operator:" + user + ",sql:"
					+ sql);
		} catch (Exception e) {
			crmLog.error("Error in method deletePost() of Custom,error:"
					+ e.getMessage() + ",sql:" + sql);
		}
		return sqlNews;
	}

	public String insertPost(String customid, String printid, String user,
			String ddate) {
		String sqlNews = "";
		String sql = "";
		try {
			sql = "insert into cana_post_list(id,customid,name,address,linkman,zip,recorder,record_time) select "
					+ printid
					+ ",id,name,address,linkman,zip,'"
					+ user
					+ "','"
					+ ddate + "' from cana_custom where id=" + customid;
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			int result = pstmt.executeUpdate();
			if (result > 0) {
				sqlNews = "success";
			} else {
				sqlNews = "b";
			}
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
			crmLog.info("Success insertPost of Custom,operator:" + user
					+ ",sql:" + sql);
		} catch (Exception e) {
			crmLog.error("Error in method insertPost() of Custom,error:"
					+ e.getMessage() + ",sql:" + sql);
		}
		return sqlNews;
	}

	public String update_water(Water_map_show CShow) {
		String sql = "";
		String sqlnews = "";
		try {
			sql = "update public_cana_water set cjdate=?,ntu=?,color=?,ph=?,cl=?,cod=?,available=? where id=? ";
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, CShow.getCjdate());
			pstmt.setString(2, CShow.getNtu());
			pstmt.setString(3, CShow.getColor());
			pstmt.setString(4, CShow.getPh());
			pstmt.setString(5, CShow.getCl());
			pstmt.setString(6, CShow.getCod());
			pstmt.setString(7, CShow.getAvailable());
			pstmt.setInt(8, CShow.getId());
			int result = pstmt.executeUpdate();
			if (result > 0) {
				sqlnews = "success";
			} else {
				sqlnews = "b";
			}
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (Exception e) {

		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
		}
		return sqlnews;
	}

	public Water_map_show select_water(String cid) {
		String sql = "";
		Water_map_show wms = new Water_map_show();
		try {
			sql = "select * from public_cana_water where id=" + cid;
			con = DbConnectionManager.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			// pstmt.setString(1,cid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				wms.setId(rs.getInt("id"));
				wms.setCjdate(rs.getString("cjdate"));
				wms.setName(rs.getString("name"));
				wms.setAddress(rs.getString("address"));
				wms.setNtu(rs.getString("ntu"));
				wms.setColor(rs.getString("color"));
				wms.setPh(rs.getString("ph"));
				wms.setCl(rs.getString("cl"));
				wms.setCod(rs.getString("cod"));
				wms.setAvailable(rs.getString("available"));
			}
		} catch (Exception e) {
			crmLog.error("Error in method select_water() of Custom,error:"
					+ e.getMessage() + ",sql:" + sql);
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
		}
		return wms;
	}

	public String customMap(String customid) {
		String sqlNews = "";
		String sql = "";
		try {
			sql = " SELECT * FROM cana_custom  cc inner join public_cana_water pcw on cc.customid=pcw.customid  where convert(nvarchar,cc.customid)=? ";
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, customid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				sqlNews = "yes";
			} else {
				sqlNews = "no";
			}
			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog.error("Error in method customMap() of Custom,error:"
					+ ex.getMessage() + ",sql:" + sql);
			// System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
			return sqlNews;
		}
	}

	public CustomShow select_single_custom(String customid) {
		CustomShow CShow = new CustomShow();
		String sql = "";
		try {
			sql = " SELECT * FROM cana_custom where customid=? ";
			String where = " ";

			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, customid);
			rs = pstmt.executeQuery();
			if (rs.next()) {

				CShow.setid(rs.getInt("id"));
				CShow.setcustomid(rs.getInt("customid"));
				CShow.setserial_num(rs.getString("serial_num"));
				CShow.setname(rs.getString("name"));
				CShow.setname_pinyin(rs.getString("name_pinyin"));
				CShow.setarea1(rs.getString("area1"));
				CShow.setarea2(rs.getString("area2"));
				CShow.setarea3(rs.getString("area3"));
				CShow.setlinkman(rs.getString("linkman"));
				CShow.settel(rs.getString("tel"));
				CShow.setmobile(rs.getString("mobile"));
				CShow.setfax(rs.getString("fax"));
				CShow.setwebsite(rs.getString("website"));
				CShow.setemail(rs.getString("email"));
				CShow.setkind(rs.getString("kind"));
				CShow.setaddress(rs.getString("address"));
				CShow.setzip(rs.getInt("zip"));
				// CShow.setseller(rs.getInt("seller"));
				CShow.setseller(rs.getString("seller"));
				CShow.setfiliale(rs.getString("filiale"));
				CShow.setshare(rs.getString("share"));
				CShow.sethotspot(rs.getString("hotspot"));
				CShow.sethotspot_explain(rs.getString("hotspot_explain"));
				CShow.setvalue_evaluate(rs.getString("value_evaluate"));
				CShow.setcredit(rs.getString("credit"));
				CShow.setindustry(rs.getString("industry"));
				CShow.setrelation(rs.getString("relation"));
				CShow.sethuman_size(rs.getString("human_size"));
				CShow.setresource(rs.getString("resource"));
				CShow.setremark(rs.getString("remark"));
				CShow.setrecorder(rs.getInt("recorder"));
				CShow.setrecorder_time(rs.getString("recorder_time"));
				CShow.setupdater(rs.getInt("updater"));
				CShow.setupdate_time(rs.getString("update_time"));
				CShow.setself_field1(rs.getString("self_field1"));
				CShow.setself_field2(rs.getString("self_field2"));
				CShow.setself_field3(rs.getString("self_field3"));
				CShow.setself_field4(rs.getString("self_field4"));
				CShow.setself_field5(rs.getString("self_field5"));
				CShow.setself_field6(rs.getString("self_field6"));
				CShow.setself_field7(rs.getString("self_field7"));
				CShow.setself_field8(rs.getString("self_field8"));
				CShow.setstatus(rs.getInt("status"));
				CShow.setlanguage_ver(rs.getString("language_ver"));
			}

			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog.error("Error in method select() of Custom,error:"
					+ ex.getMessage() + ",sql:" + sql);
			// System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
			return CShow;
		}
	}

	public String applyAuditing(String checknum, String auditname,
			String auditdate, String auditing_remark) {
		String sqlnews = "fail";
		String sql = "";
		String sql1 = "";
		String sql4log = "";
		try {
			sql = "update cana_check_water set apply_auditing=?,apply_auditing_time=?,apply_auditing_remarking=? where check_num=? ";
			sql4log = "update cana_check_water set auditing=?,auditing_time=?,auditing_remark=? where check_num="
					+ checknum;
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, auditname);
			pstmt.setString(2, auditdate);
			pstmt.setString(3, auditing_remark);
			pstmt.setString(4, checknum);
			int result = pstmt.executeUpdate();
			if (result > 0) {
				sqlnews = "success";
			} else {
				sqlnews = "b";
			}
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
			crmLog.info("Success appluAuditing of Custom,sql:" + sql4log);
		} catch (SQLException ex) {
			crmLog.error("Error in method appluAuditing() of Custom,error"
					+ ex.getMessage() + ",sql:" + sql);
			sqlnews = "Error!" + ex;
			// ex.printStackTrace();
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
		}
		return sqlnews;

	}

	public String updateBasicInfo(Water_map_show CShow) {
		String sqlnews = "fail";
		String sql = "";
		try {
			sql = "update cana_check_water set address_taking=?,recorder=?,recorder_time=?,project_address=?,filter_status=?,products_remarking=?,test_cl_water=?,test_cl_filter=?,test_cl_pure=?,filter_type=?,person_taking=?,date_taking=?  where check_num=? and customid=? ";

			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, CShow.getAddress_taking());
			pstmt.setString(2, CShow.getRecorder());
			pstmt.setString(3, CShow.getRecorder_time());
			pstmt.setString(4, CShow.getProject_address());
			pstmt.setString(5, CShow.getFilter_status());
			pstmt.setString(6, CShow.getProducts_remarking());
			pstmt.setString(7, CShow.getTest_cl_water());
			pstmt.setString(8, CShow.getTest_cl_filter());
			pstmt.setString(9, CShow.getTest_cl_pure());
			pstmt.setString(10, CShow.getFilter_type());
			pstmt.setString(11, CShow.getPerson_taking());
			pstmt.setString(12, CShow.getDate_taking());
			pstmt.setString(13, CShow.getCheck_num());
			pstmt.setString(14, CShow.getCustomid());
			int result = pstmt.executeUpdate();
			if (result > 0) {
				sqlnews = "success";
			} else {
				sqlnews = "b";
			}
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
			crmLog.info("Success updateBasicInfo of Custom,sql:" + sql);
		} catch (SQLException ex) {
			crmLog.error("Error in method updateBasicInfo() of Custom,error"
					+ ex.getMessage() + ",sql:" + sql);
			sqlnews = "Error!" + ex;
			// ex.printStackTrace();
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
		}
		return sqlnews;

	}

	public String updateAuditing(String checknum, String auditname,
			String auditdate, String auditing, String auditing_remark) {
		String sqlnews = "fail";
		String sql = "";
		String sql1 = "";
		String sql4log = "";
		try {
			sql = "update cana_check_water set auditing=?,auditing_time=?,auditing_remark=? where check_num=? ";
			sql4log = "update cana_check_water set auditing=?,auditing_time=?,auditing_remark=? where check_num="
					+ checknum;
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, auditing + "(" + auditname + ")");
			pstmt.setString(2, auditdate);
			pstmt.setString(3, auditing_remark);
			pstmt.setString(4, checknum);
			int result = pstmt.executeUpdate();
			if (result > 0) {
				sqlnews = "success";
			} else {
				sqlnews = "b";
			}
			pstmt.close();
			sql1 = "update cana_check_water set cjdate=? from cana_check_water ccw "
					+ "  where ccw.check_num=?";
			pstmt = con.prepareStatement(sql1,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, auditdate.substring(0, 10));
			pstmt.setString(2, checknum);
			int rt = pstmt.executeUpdate();
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
			crmLog.info("Success updateAuditing of Custom,sql:" + sql4log);
		} catch (SQLException ex) {
			crmLog.error("Error in method updateAuditing() of Custom,error"
					+ ex.getMessage() + ",sql:" + sql);
			sqlnews = "Error!" + ex;
			// ex.printStackTrace();
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
		}
		return sqlnews;

	}

	public String reAuditing(String checknum) {
		String sqlnews = "fail";
		String sql = "";
		String sql1 = "";
		String sql4log = "";
		try {
			sql = "update cana_check_water set auditing='null' where check_num=? ";
			sql4log = "update cana_check_water set auditing=? where check_num="
					+ checknum;
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, checknum);
			int result = pstmt.executeUpdate();
			if (result > 0) {
				sqlnews = "success";
			} else {
				sqlnews = "b";
			}
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
			crmLog.info("Success updateAuditing of Custom,sql:" + sql4log);
		} catch (SQLException ex) {
			crmLog.error("Error in method updateAuditing() of Custom,error"
					+ ex.getMessage() + ",sql:" + sql);
			sqlnews = "Error!" + ex;
			// ex.printStackTrace();
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
		}
		return sqlnews;

	}
	
	public String deleteCheckAll(String checknum) {
		String sqlnews = "fail";
		String sql = "";
		String sql4log = "";
		try {
			sql = "delete from cana_check_water where check_num=? ";
			sql4log = "delete from cana_check_water check_num=" + checknum;
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, checknum);
			int result = pstmt.executeUpdate();
			if (result > 0) {
				sqlnews = "sucess";
			} else {
				sqlnews = "b";
			}
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
			crmLog.info("Success deleteCheckAll of Custom,sql:" + sql4log);
		} catch (SQLException ex) {
			crmLog.error("Error in method deleteChckAll() of Custom,error"
					+ ex.getMessage() + ",sql:" + sql);
			sqlnews = "Error!" + ex;
			// ex.printStackTrace();
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
		}
		return sqlnews;
	}

	public String deleteCheck(String checknum, String customid) {
		String sqlnews = "fail";
		String sql = "";
		String sql4log = "";
		try {
			sql = "delete from cana_check_water where check_num=? and customid=?";
			sql4log = "delete from cana_check_water check_num=" + checknum
					+ " and customid=" + customid;
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, checknum);
			pstmt.setString(2, customid);
			int result = pstmt.executeUpdate();
			if (result > 0) {
				sqlnews = "sucess";
			} else {
				sqlnews = "b";
			}
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
			crmLog.info("Success deleteCheck of Custom,sql:" + sql4log);
		} catch (SQLException ex) {
			crmLog.error("Error in method deleteCheck() of Custom,error"
					+ ex.getMessage() + ",sql:" + sql);
			sqlnews = "Error!" + ex;
			// ex.printStackTrace();
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
		}
		return sqlnews;
	}

	public String select_singlesql(int page, String check_num) {
		int size = 0;
		int sizepa = 0;
		String sql = "";
		try {
			sql = "select count(*) "
					+ "from cana_check_water cc "
					+ " inner join public_cana_water pcw on cc.customid=pcw.customid "
					+ " where check_num= '" + check_num + "' ";

			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				size = rs.getInt(1);
				sizepa = (int) size / page;
				if (size % page > 0) {
					sizepa = sizepa + 1;
				}
			}

			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog.error("Error in method select_singlesql() of Custom,error:"
					+ ex.getMessage() + ",sql:" + sql);
			// System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
			return sizepa + "+" + size;
		}

	}

	public String updateSingleCheck(Water_map_show CShow) {
		String sqlnews = "fail";
		String sql = "";
		try {
			sql = "update cana_check_water set operator=?,operator_time=?,Filter_status=?,products_remarking=?,test_cl_water=?,test_cl_filter=?,test_cl_pure=?,taking_example_remarking=?,example_name=?,used_aim=?,person_taking=?  "
					+ "	from cana_check_water  " + " where check_num=?  ";
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, CShow.getOperator());
			pstmt.setString(2, CShow.getOperator_time());
			pstmt.setString(3, CShow.getFilter_status());
			pstmt.setString(4, CShow.getProducts_remarking());
			pstmt.setString(5, CShow.getTest_cl_water());
			pstmt.setString(6, CShow.getTest_cl_filter());
			pstmt.setString(7, CShow.getTest_cl_pure());
			pstmt.setString(8, CShow.getTaking_example_remarking());
			pstmt.setString(9, CShow.getExample_name());
			pstmt.setString(10, CShow.getUsed_aim());
			pstmt.setString(11, CShow.getPerson_taking());
			pstmt.setString(12, CShow.getCheck_num());

			int result = pstmt.executeUpdate();
			if (result > 0) {
				sqlnews = "sucess";
			} else {
				sqlnews = "b";
			}
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
			crmLog.info("Success updatecheckWater of Custom,sql:" + sql);
		} catch (Exception e) {
			crmLog.error("Fail updatecheckWater of Custom,sql:" + sql
					+ "error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
		}
		return sqlnews;
	}

	public String updatecheckWater(Water_map_show CShow) {
		String sqlnews = "fail";
		String sql = "";
		try {
			sql = "update cana_check_water set cjdate=?,ntu=?,color=?,ph=?,cl=?,cod=?,smell=?,tds=?,available=?,filter_ntu=?,filter_color=?,filter_ph=?,filter_cl=?,filter_cod=?,filter_smell=?,filter_available=?,filter_tds=?,operator=?,operator_time=?,back_stand1=?,back_stand11=?,back_stand2=?,back_stand21=?,back_stand3=?,back_stand31=?,self_field1=?,self_field2=?,self_field3=? "
					+ "	from cana_check_water ccw "
					+ " where check_num=? and ccw.customid=? ";
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, CShow.getCjdate());
			pstmt.setString(2, CShow.getNtu());
			pstmt.setString(3, CShow.getColor());
			pstmt.setString(4, CShow.getPh());
			pstmt.setString(5, CShow.getCl());
			pstmt.setString(6, CShow.getCod());
			pstmt.setString(7, CShow.getSmell());
			pstmt.setString(8, CShow.getTds());
			pstmt.setString(9, CShow.getAvailable());
			pstmt.setString(10, CShow.getFntu());
			pstmt.setString(11, CShow.getFcolor());
			pstmt.setString(12, CShow.getFph());
			pstmt.setString(13, CShow.getFcl());
			pstmt.setString(14, CShow.getFcod());
			pstmt.setString(15, CShow.getFilter_smell());
			pstmt.setString(16, CShow.getFilter_available());
			pstmt.setString(17, CShow.getFilter_tds());
			pstmt.setString(18, CShow.getOperator());
			pstmt.setString(19, CShow.getOperator_time());
			pstmt.setString(20, CShow.getBack_stand1());
			pstmt.setString(21, CShow.getBack_stand11());
			pstmt.setString(22, CShow.getBack_stand2());
			pstmt.setString(23, CShow.getBack_stand21());
			pstmt.setString(24, CShow.getBack_stand3());
			pstmt.setString(25, CShow.getBack_satnd31());
			pstmt.setString(26, CShow.getSelf_field1());
			pstmt.setString(27, CShow.getSelf_field2());
			pstmt.setString(28, CShow.getSelf_field3());
			pstmt.setString(29, CShow.getCheck_num());
			pstmt.setString(30, CShow.getCustomid());
			int result = pstmt.executeUpdate();
			if (result > 0) {
				sqlnews = "sucess";
			} else {
				sqlnews = "b";
			}
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
			crmLog.info("Success updatecheckWater of Custom,sql:" + sql);
		} catch (Exception e) {
			crmLog.error("Fail updatecheckWater of Custom,sql:" + sql
					+ "error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
		}
		return sqlnews;
	}

	public String select_audit_info(String check_num) {
		String reInfo = "";
		String sql = "";
		try {
			sql = "select max(recorder) as recorder,max(recorder_time) as recorder_time,max(apply_auditing) as auditing,max(apply_auditing_remarking) as auditing_remark,max(apply_auditing_time) as auditing_time "
					+ " from cana_check_water "
					+ " where check_num='"
					+ check_num + "'";
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				reInfo = reInfo + rs.getString("recorder");
				reInfo = reInfo + ";";
				reInfo = reInfo + rs.getString("recorder_time");
				reInfo = reInfo + ";";
				reInfo = reInfo + rs.getString("auditing");
				reInfo = reInfo + ";";
				reInfo = reInfo + rs.getString("auditing_time");
				reInfo = reInfo + ";";
				reInfo = reInfo + rs.getString("auditing_remark");
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception ex) {
			crmLog.error("fail select_check_info of Custom,sql:" + sql);
		}
		return reInfo;
	}

	public String select_check_info(String check_num) {
		String reInfo = "";
		String sql = "";
		try {
			sql = "select max(recorder) as recorder,max(recorder_time) as recorder_time,max(auditing) as auditing,max(auditing_remark) as auditing_remark,max(operator) as operator,max(operator_time) as operator_time,max(auditing_time) as auditing_time "
					+ " from cana_check_water "
					+ " where check_num='"
					+ check_num + "'";
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				reInfo = reInfo + rs.getString("recorder");
				reInfo = reInfo + ";";
				reInfo = reInfo + rs.getString("recorder_time");
				reInfo = reInfo + ";";
				reInfo = reInfo + rs.getString("operator");
				reInfo = reInfo + ";";
				reInfo = reInfo + rs.getString("operator_time");
				reInfo = reInfo + ";";
				reInfo = reInfo + rs.getString("auditing");
				reInfo = reInfo + ";";
				reInfo = reInfo + rs.getString("auditing_time");
				reInfo = reInfo + ";";
				reInfo = reInfo + rs.getString("auditing_remark");
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception ex) {
			crmLog.error("fail select_check_info of Custom,sql:" + sql);
		}
		return reInfo;
	}

	public Collection select_single(int pageshow, int pa, String check_num) {
		Collection coll = new ArrayList();
		String sql = "";
		int tip = pageshow * (pa - 1);
		try {
			sql = "select check_num,used_aim,example_name,person_taking,filter_type,department_taking,name,auditing_time,address_taking as address,customid,ccw.date_taking as date_taking,auditing,cjdate,ntu,color,ph,project_address as others,cl,cod,smell,tds,filter_ntu,filter_color,filter_ph,filter_cl,filter_cod,filter_smell,filter_tds,available,filter_available,example_type,filter_status,taking_example_remarking,test_cl_water,test_cl_filter,test_cl_pure,products_remarking,project_address,products_type,apply_auditing,apply_auditing_time,apply_auditing_remarking,self_field1,self_field2,self_field3,self_field4,operator,back_stand1,back_stand11,back_stand2,back_stand21,back_stand3,back_stand31  "
					+ " from cana_check_water ccw "
					+ " where check_num='"
					+ check_num + "' ";
			sql = sql + " order by id asc ";
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			if (tip <= 0) {
				rs.beforeFirst();
			} else if (!rs.absolute(tip)) {
				rs.beforeFirst();
			}
			for (int i = 0; rs.next() && i < pageshow; i++) {
				Water_map_show CShow = new Water_map_show();
				CShow.setCheck_num(rs.getString("check_num"));
				CShow.setDepartment_taking(rs.getString("department_taking"));
				CShow.setName(rs.getString("name"));
				CShow.setBuilding(rs.getString("auditing_time"));
				CShow.setAddress_taking(rs.getString("address"));
				CShow.setCustomid(rs.getString("customid"));
				CShow.setDate_taking(rs.getString("date_taking"));
				CShow.setUsed_aim(rs.getString("used_aim"));
				CShow.setExample_name(rs.getString("example_name"));
				CShow.setPerson_taking(rs.getString("person_taking"));
				CShow.setDate_taking(rs.getString("date_taking"));
				CShow.setFilter_type(rs.getString("filter_type"));
				CShow.setAuditing(rs.getString("auditing"));
				CShow.setOthers(rs.getString("others"));
				CShow.setCjdate(rs.getString("cjdate"));
				CShow.setNtu(rs.getString("ntu"));
				CShow.setColor(rs.getString("color"));
				CShow.setPh(rs.getString("ph"));
				CShow.setCl(rs.getString("cl"));
				CShow.setCod(rs.getString("cod"));
				CShow.setSmell(rs.getString("smell"));
				CShow.setTds(rs.getString("tds"));
				CShow.setFntu(rs.getString("filter_ntu"));
				CShow.setFcolor(rs.getString("filter_color"));
				CShow.setFcl(rs.getString("filter_cl"));
				CShow.setFph(rs.getString("filter_ph"));
				CShow.setFcod(rs.getString("filter_cod"));
				CShow.setFilter_smell(rs.getString("filter_smell"));
				CShow.setFilter_tds(rs.getString("filter_tds"));
				CShow.setAvailable(rs.getString("available"));
				CShow.setFilter_available(rs.getString("filter_available"));
				CShow.setExample_type(rs.getString("example_type"));
				CShow.setFilter_status(rs.getString("filter_status"));
				CShow.setTaking_example_remarking(rs
						.getString("taking_example_remarking"));
				CShow.setTest_cl_filter(rs.getString("test_cl_filter"));
				CShow.setTest_cl_water(rs.getString("test_cl_water"));
				CShow.setTest_cl_pure(rs.getString("test_cl_pure"));
				CShow.setProducts_remarking(rs.getString("products_remarking"));
				CShow.setProject_address(rs.getString("others"));
				CShow.setProducts_type(rs.getString("products_type"));
				CShow.setSelf_field1(rs.getString("self_field1"));
				CShow.setSelf_field2(rs.getString("self_field2"));
				CShow.setSelf_field3(rs.getString("self_field3"));
				CShow.setSelf_field4(rs.getString("self_field4"));
				CShow.setApply_auditing(rs.getString("apply_auditing"));
				CShow.setApply_auditing_time(rs
						.getString("apply_auditing_time"));
				CShow.setApply_auditing_remarking(rs
						.getString("apply_auditing_remarking"));
				CShow.setOperator(rs.getString("operator"));
				CShow.setBack_stand1(rs.getString("back_stand1"));
				CShow.setBack_stand11(rs.getString("back_stand11"));
				CShow.setBack_stand2(rs.getString("back_stand2"));
				CShow.setBack_stand21(rs.getString("back_stand21"));
				CShow.setBack_stand3(rs.getString("back_stand3"));
				CShow.setBack_satnd31(rs.getString("back_stand31"));
				coll.add(CShow);
			}
			rs.close();
			pstmt.close();
			con.close();

		} catch (Exception e) {
			crmLog.error("Error in method select_single() of Custom,error:"
					+ e.getMessage() + ",sql:" + sql);
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}

		}
		return coll;

	}

	public String searchPointCustomsql(int st, String table_head,
			String tj_name1, String tj_name2, String tj_name3, String tj_name4,
			String tj_name5, String tj_v1, String tj_v2, String tj_v3,
			String tj_v4, String tj_v5, String tj_v11, String tj_v21,
			String tj_v31, String tj_v41, String tj_v51, String taxis_name,
			String taxis_v, String status) {
		int size = 0;
		int sizepa = 0;
		String sql = "";
		String tj_v1_per = "";
		try {
			sql = "select count(*) " + " from public_cana_water  "
					+ " where 1=1 ";
			if (tj_v1 != null && !tj_v1.equals("")) {
				if (tj_v11.equals("0")) {
					sql = sql + " and " + tj_name1 + " like '%" + tj_v1 + "%'";
				} else if (tj_v11.equals("1")) {
					sql = sql + " and " + tj_name1 + " like '" + tj_v1 + "%'";
				} else if (tj_v11.equals("3")) {
					for (int i = 0; i < tj_v1.length(); i++) {
						tj_v1_per += tj_v1.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					sql = sql + " and " + tj_name1 + " like '%" + tj_v1_per
							+ "%'";
				} else {
					sql = sql + " and " + tj_name1 + "='" + tj_v1 + "'";
				}

			}
			if (tj_v2 != null && !tj_v2.equals("")) {
				tj_v2 = StringUtils.toChinese(tj_v2);
				if (tj_v2.equals("��ʵ�ͻ�")) {
					sql = sql + " ";
				} else {
					sql = sql + "and water_type='" + tj_v2 + "'";
				}
			}

			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				size = rs.getInt(1);
				sizepa = (int) size / st;
				if (size % st > 0) {
					sizepa = sizepa + 1;
				}
			}

			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog
					.error("Error in method checkWaterSearchsql() of Custom,error:"
							+ ex.getMessage() + ",sql:" + sql);
			// System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
			return sizepa + "+" + size;
		}

	}

	public Collection searchPointCustom(int pageshow, int pa,
			String table_head, String tj_name1, String tj_name2,
			String tj_name3, String tj_name4, String tj_name5, String tj_v1,
			String tj_v2, String tj_v3, String tj_v4, String tj_v5,
			String tj_v11, String tj_v21, String tj_v31, String tj_v41,
			String tj_v51, String taxis_name, String taxis_v, String status) {
		Collection coll = new ArrayList();
		String sql = "";
		String tj_v1_per = "";
		int tip = pageshow * (pa - 1);
		try {
			sql = "select case when  customid='' then '0000' else customid end as customid ,name as name,address "
					+ "from public_cana_water  " + " where 1=1 ";
			if (tj_v1 != null && !tj_v1.equals("")) {
				if (tj_v11.equals("0")) {
					for (int i = 0; i < tj_v1.length(); i++) {
						tj_v1_per += tj_v1.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					sql = sql + " and " + tj_name1 + " like '%" + tj_v1_per
							+ "%'";
				} else if (tj_v11.equals("1")) {
					sql = sql + " and " + tj_name1 + " like '" + tj_v1 + "%'";
				} else if (tj_v11.equals("3")) {
					for (int i = 0; i < tj_v1.length(); i++) {
						tj_v1_per += tj_v1.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					sql = sql + " and " + tj_name1 + " like '%" + tj_v1_per
							+ "%'";
				} else {
					sql = sql + " and " + tj_name1 + "='" + tj_v1 + "'";
				}

			}
			if (tj_v2 != null && !tj_v2.equals("")) {
				tj_v2 = StringUtils.toChinese(tj_v2);
				if (tj_v2.equals("0")) {
					// sql=sql+" and water_type is null ";
				} else if (tj_v2.equals("1")) {
					sql = sql + " and water_type='�����Ƶ�'";
				} else if (tj_v2.equals("2")) {
					sql = sql + " and water_type='���ҽԺ'";
				} else if (tj_v2.equals("3")) {
					sql = sql + " and water_type='�׼�д��¥'";
				} else if (tj_v2.equals("4")) {
					sql = sql + " and water_type='�ߵ�סլ'";
				} else {
					sql = sql + " and water_type='���˿ͻ�'";
				}

			}

			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			if (tip <= 0) {
				rs.beforeFirst();
			} else if (!rs.absolute(tip)) {
				rs.beforeFirst();
			}
			for (int i = 0; rs.next() && i < pageshow; i++) {
				Water_map_show CShow = new Water_map_show();
				CShow.setName(rs.getString("name"));
				CShow.setAddress(rs.getString("address"));
				CShow.setCustomid(rs.getString("customid"));
				coll.add(CShow);
			}
		} catch (Exception e) {
			crmLog.error("Error in method searchPointCustom() of Custom,error:"
					+ e.getMessage() + ",sql:" + sql);
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}

		}
		return coll;

	}

	public String addcheckWater(Water_map_show CShow) {
		String flag = "fail";
		String sql = "insert into cana_check_water(department_taking,address_taking,customid,auditing,check_num,example_name,used_aim,date_taking,person_taking,filter_type,name,recorder,recorder_time,products_type,project_address,taking_example_remarking,self_field1,self_field2,self_field3,self_field4,filter_status,products_remarking,test_cl_water,test_cl_filter,test_cl_pure,dealers) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, CShow.getDepartment_taking());
			pstmt.setString(2, CShow.getAddress_taking());
			pstmt.setString(3, CShow.getCustomid());
			pstmt.setString(4, CShow.getAuditing());
			pstmt.setString(5, CShow.getCheck_num());
			pstmt.setString(6, CShow.getExample_name());
			pstmt.setString(7, CShow.getUsed_aim());
			pstmt.setString(8, CShow.getDate_taking());
			pstmt.setString(9, CShow.getPerson_taking());
			pstmt.setString(10, CShow.getFilter_type());
			pstmt.setString(11, CShow.getName());
			pstmt.setString(12, CShow.getRecorder());
			pstmt.setString(13, CShow.getRecorder_time());
			pstmt.setString(14, CShow.getProducts_type());
			pstmt.setString(15, CShow.getProject_address());
			pstmt.setString(16, CShow.getTaking_example_remarking());
			pstmt.setString(17, CShow.getSelf_field1());
			pstmt.setString(18, CShow.getSelf_field2());
			pstmt.setString(19, CShow.getSelf_field3());
			pstmt.setString(20, CShow.getSelf_field4());
			pstmt.setString(21, CShow.getFilter_status());
			pstmt.setString(22, CShow.getProducts_remarking());
			pstmt.setString(23, CShow.getTest_cl_water());
			pstmt.setString(24, CShow.getTest_cl_filter());
			pstmt.setString(25, CShow.getTest_cl_pure());
			pstmt.setString(26, CShow.getDealers());
			int result = pstmt.executeUpdate();
			if (result > 0) {
				flag = "sucess";
			} else {
				flag = "fail";
			}
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
			crmLog
					.info("success in method addcheckWater() of Custom ,sql=insert into cana_check_water(department_taking,address_taking,customid,auditing,check_num,example_name,used_aim,date_taking,person_taking,filter_type,name) values("
							+ CShow.getDepartment_taking()
							+ ","
							+ CShow.getAddress_taking()
							+ ","
							+ CShow.getCustomid()
							+ ","
							+ CShow.getAuditing()
							+ ","
							+ CShow.getCheck_num()
							+ ","
							+ CShow.getExample_name()
							+ ","
							+ CShow.getUsed_aim()
							+ ","
							+ CShow.getDate_taking()
							+ ","
							+ CShow.getPerson_taking()
							+ ","
							+ CShow.getFilter_type()
							+ ","
							+ CShow.getName()
							+ ")");
		} catch (Exception e) {
			crmLog.error("Error in method addcheckWater() of Custom,error:"
					+ e.getMessage() + ",sql:" + sql);
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
		}
		return flag;

	}

	public String searCheckWatersql(int st, String table_head, String tj_name1,
			String tj_name2, String tj_name3, String tj_name4, String tj_name5,
			String tj_v1, String tj_v2, String tj_v3, String tj_v4,
			String tj_v5, String tj_v11, String tj_v21, String tj_v31,
			String tj_v41, String tj_v51, String taxis_name, String taxis_v,
			String status, String dealers) {
		int size = 0;
		int sizepa = 0;
		String sql = "";
		try {
			sql = "select count(*) "
					+ "from cana_check_water ccw "
					+ "inner join "
					+ " (select check_num,max(customid) as customid "
					+ " from cana_check_water "
					+ " group by check_num) ccw1 on ccw.check_num=ccw1.check_num and ccw.customid=ccw1.customid "
					+ " left join public_cana_water pcw on ccw.customid=pcw.customid "
					+ " where 1=1 ";

			if (dealers != null && !"".equals(dealers.trim())) {
				sql = sql + " and ccw.dealers='" + dealers + "' ";
			}

			if (tj_v3 != null && !tj_v3.equals("")) {
				sql = sql + " and check_num='" + tj_v3 + "' ";
			}
			if (tj_v11 != null && !tj_v11.equals("")) {
				if (tj_v1.equals("1")) {
					if (tj_name1.equals("--")) {
						sql = sql + " and ccw.name='" + tj_v11 + "' ";
					} else {
						sql = sql + " and ccw.name like '%" + tj_v11 + "%' ";
					}
				} else {
					if (tj_name1.equals("--")) {
						sql = sql + " and ccw.customid='" + tj_v11 + "' ";
					} else {
						sql = sql + " and ccw.customid like '%" + tj_v11
								+ "%' ";
					}
				}

			}
			if (tj_v2 != null && !tj_v2.equals("")) {
				if (tj_v21 != null && tj_v21.equals("--")) {
					sql = sql + " and ccw.address='" + tj_v2 + "' ";
				} else {
					sql = sql + " and ccw.address like '%" + tj_v2 + "%' ";
				}
			}
			if (tj_v4 != null && !tj_v4.equals("")) {
				if (tj_v5 != null && !tj_v5.equals("")) {
					sql = sql + " and ccw.customid='" + tj_v5 + "'";
				} else {
					sql = sql + " and 1=0 ";
				}
			}

			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				size = rs.getInt(1);
				sizepa = (int) size / st;
				if (size % st > 0) {
					sizepa = sizepa + 1;
				}
			}

			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog
					.error("Error in method checkWaterSearchsql() of Custom,error:"
							+ ex.getMessage() + ",sql:" + sql);
			// System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
			return sizepa + "+" + size;
		}

	}

	public Collection searCheckWater(int pageshow, int pa, String table_head,
			String tj_name1, String tj_name2, String tj_name3, String tj_name4,
			String tj_name5, String tj_v1, String tj_v2, String tj_v3,
			String tj_v4, String tj_v5, String tj_v11, String tj_v21,
			String tj_v31, String tj_v41, String tj_v51, String taxis_name,
			String taxis_v, String status, String dealers) {
		Collection coll = new ArrayList();
		String sql = "";
		int tip = pageshow * (pa - 1);
		try {
			sql = "select ccw.check_num as check_num,ccw.name as name ,ccw.address_taking as address,ccw.customid as customid,ccw.date_taking as date_taking,ccw.auditing as auditing,ccw.department_taking,cjdate,ntu,color,ph,cl,cod,filter_ntu,filter_color,filter_ph,filter_cl,filter_cod,test_cl_water,test_cl_filter,test_cl_pure "
					+ " from cana_check_water ccw "
					+ "inner join "
					+ " (select check_num,max(customid) as customid "
					+ " from cana_check_water "
					+ " group by check_num)ccw1 on ccw.check_num=ccw1.check_num and ccw.customid=ccw1.customid "
					+ " where 1=1 ";

			if (dealers != null && !"".equals(dealers.trim())) {
				sql = sql + " and ccw.dealers='" + dealers + "' ";
			}

			if (tj_v3 != null && !tj_v3.equals("")) {
				sql = sql + " and ccw.check_num='" + tj_v3 + "' ";
			}
			if (tj_v11 != null && !tj_v11.equals("")) {
				if (tj_v1.equals("1")) {
					if (tj_name1.equals("--")) {
						sql = sql + " and ccw.name='" + tj_v11 + "' ";
					} else {
						sql = sql + " and ccw.name like '%" + tj_v11 + "%' ";
					}
				} else {
					if (tj_name1.equals("--")) {
						sql = sql + " and ccw.customid='" + tj_v11 + "' ";
					} else {
						sql = sql + " and ccw.customid like '%" + tj_v11
								+ "%' ";
					}
				}

			}
			if (tj_v2 != null && !tj_v2.equals("")) {
				if (tj_v21 != null && tj_v21.equals("--")) {
					sql = sql + " and ccw.address_taking='" + tj_v2 + "' ";
				} else {
					sql = sql + " and ccw.address_taking like '%" + tj_v2
							+ "%' ";
				}
			}
			if (tj_v4 != null && !tj_v4.equals("")) {

				if (tj_v5 != null && !tj_v5.equals("")) {
					sql = sql + " and ccw.customid='" + tj_v5 + "'";
				} else {
					sql = sql + " and 1=0 ";
				}
			}
			sql = sql + " order by  date_taking desc";
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			if (tip <= 0) {
				rs.beforeFirst();
			} else if (!rs.absolute(tip)) {
				rs.beforeFirst();
			}
			for (int i = 0; rs.next() && i < pageshow; i++) {
				Water_map_show CShow = new Water_map_show();
				CShow.setCheck_num(rs.getString("check_num"));
				CShow.setName(rs.getString("name"));
				CShow.setAddress_taking(rs.getString("address"));
				CShow.setCustomid(rs.getString("customid"));
				CShow.setDate_taking(rs.getString("date_taking"));
				CShow.setCjdate(rs.getString("cjdate"));
				CShow.setAuditing(rs.getString("auditing"));
				CShow.setNtu(rs.getString("ntu"));
				CShow.setColor(rs.getString("color"));
				CShow.setPh(rs.getString("ph"));
				CShow.setCl(rs.getString("cl"));
				CShow.setCod(rs.getString("cod"));
				CShow.setTest_cl_water(rs.getString("test_cl_water"));
				CShow.setTest_cl_filter(rs.getString("test_cl_filter"));
				CShow.setTest_cl_pure(rs.getString("test_cl_pure"));
				CShow.setDepartment_taking(rs.getString("department_taking"));
				coll.add(CShow);
			}
		} catch (Exception e) {
			crmLog.error("Error in method searchNoWaterMap() of Custom,error:"
					+ e.getMessage() + ",sql:" + sql);
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}

		}
		return coll;

	}

	public String searchNoWaterMapsql(int st, String language_v,
			String tj_name1, String tj_name2, String tj_name3, String tj_v1,
			String tj_v2, String tj_v3, String tj_v11, String tj_v21,
			String tj_v31, String tj_where_s, String taxis_name,
			String taxis_v, String status) {
		int size = 0;
		int sizepa = 0;
		String sql = "";
		try {
			sql = "select count(*) "
					+ "from water_no_map wnm "
					+ " left join public_cana_water pcw on wnm.district=pcw.district and wnm.town=pcw.town and wnm.road=pcw.road and wnm.nong=pcw.nong and wnm.number=pcw.num "
					+ " and wnm.others=pcw.others and wnm.name=pcw.name "
					+ " where pcw.district is null ";
			if (tj_v3 != null && !tj_v3.equals("")) {
				sql = sql + " and wnm.name like '%" + tj_v3 + "%' ";
			}
			if (tj_v2 != null && !tj_v2.equals("")) {
				if (tj_v21 != null && tj_v21.equals("--")) {
					sql = sql
							+ " and wnm.district+(case when wnm.town is null then '' else wnm.town end)+(case when wnm.road is null then '' else wnm.road end)+(case when wnm.nong is null then '' else wnm.nong end)+(case when wnm.number is null then '' else wnm.number end)+(case when wnm.others is null then '' else wnm.others end) ='"
							+ tj_v2 + "' ";
				} else {
					sql = sql
							+ " and wnm.district+(case when wnm.town is null then '' else wnm.town end)+(case when wnm.road is null then '' else wnm.road end)+(case when wnm.nong is null then '' else wnm.nong end)+(case when wnm.number is null then '' else wnm.number end)+(case when wnm.others is null then '' else wnm.others end) like '%"
							+ tj_v2 + "%' ";
				}
			}
			if (tj_v11 != null && !tj_v11.equals("")) {
				if (StringUtils.toChinese(tj_v11).equals("�����Ƶ�")) {
					sql = sql + " and wnm.type='�����Ƶ�' ";
				} else if (StringUtils.toChinese(tj_v11).equals("���ҽԺ")) {
					sql = sql + " and wnm.type='���ҽԺ' ";
				} else if (StringUtils.toChinese(tj_v11).equals("�׼�д��¥")) {
					sql = sql + " and wnm.type='�׼�д��¥' ";
				} else if (StringUtils.toChinese(tj_v11).equals("�ߵ�סլ")) {
					sql = sql + " and wnm.type='�ߵ�סլ' ";
				} else if (StringUtils.toChinese(tj_v11).equals("������")) {
					sql = sql + " and wnm.type='������' ";
				}
			}
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				size = rs.getInt(1);
				sizepa = (int) size / st;
				if (size % st > 0) {
					sizepa = sizepa + 1;
				}
			}

			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog
					.error("Error in method searchNoWaterMapsql() of Custom,error:"
							+ ex.getMessage() + ",sql:" + sql);
			// System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
			return sizepa + "+" + size;
		}

	}

	public Collection searchNoWaterMap(int pageshow, int pa, String language_v,
			String tj_name1, String tj_name2, String tj_name3, String tj_v1,
			String tj_v2, String tj_v3, String tj_v11, String tj_v21,
			String tj_v31, String tj_where_s, String taxis_name,
			String taxis_v, String status) {
		Collection coll = new ArrayList();
		String sql = "";
		int tip = pageshow * (pa - 1);
		try {
			sql = "select wnm.name as name,wnm.district+(case when wnm.town is null then '' else wnm.town end)+(case when wnm.road is null then '' else wnm.road end)+(case when wnm.nong is null then '' else wnm.nong end)+(case when wnm.number is null then '' else wnm.number end)+(case when wnm.others is null then '' else wnm.others end) as address,wnm.type as type "
					+ "from water_no_map wnm "
					+ " left join public_cana_water pcw on wnm.district=pcw.district and wnm.town=pcw.town and wnm.road=pcw.road and wnm.nong=pcw.nong and wnm.number=pcw.num "
					+ " and wnm.others=pcw.others and wnm.name=pcw.name "
					+ " where pcw.district is null ";
			if (tj_v3 != null && !tj_v3.equals("")) {
				sql = sql + " and wnm.name like '%" + tj_v3 + "%' ";
			}
			if (tj_v2 != null && !tj_v2.equals("")) {
				if (tj_v21 != null && tj_v21.equals("--")) {
					sql = sql
							+ " and wnm.district+(case when wnm.town is null then '' else wnm.town end)+(case when wnm.road is null then '' else wnm.road end)+(case when wnm.nong is null then '' else wnm.nong end)+(case when wnm.number is null then '' else wnm.number end)+(case when wnm.others is null then '' else wnm.others end) ='"
							+ tj_v2 + "' ";
				} else {
					sql = sql
							+ " and wnm.district+(case when wnm.town is null then '' else wnm.town end)+(case when wnm.road is null then '' else wnm.road end)+(case when wnm.nong is null then '' else wnm.nong end)+(case when wnm.number is null then '' else wnm.number end)+(case when wnm.others is null then '' else wnm.others end) like '%"
							+ tj_v2 + "%' ";
				}
			}
			if (tj_v11 != null && !tj_v11.equals("")) {
				if (StringUtils.toChinese(tj_v11).equals("�����Ƶ�")) {
					sql = sql + " and wnm.type='�����Ƶ�' ";
				} else if (StringUtils.toChinese(tj_v11).equals("���ҽԺ")) {
					sql = sql + " and wnm.type='���ҽԺ' ";
				} else if (StringUtils.toChinese(tj_v11).equals("�׼�д��¥")) {
					sql = sql + " and wnm.type='�׼�д��¥' ";
				} else if (StringUtils.toChinese(tj_v11).equals("�ߵ�סլ")) {
					sql = sql + " and wnm.type='�ߵ�סլ' ";
				} else if (StringUtils.toChinese(tj_v11).equals("������")) {
					sql = sql + " and wnm.type='������' ";
				}
			}
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Water_map_show CShow = new Water_map_show();
				CShow.setName(rs.getString("name"));
				CShow.setAddress(rs.getString("address"));
				CShow.setType(rs.getString("type"));
				coll.add(CShow);
			}
		} catch (Exception e) {
			crmLog.error("Error in method searchNoWaterMap() of Custom,error:"
					+ e.getMessage() + ",sql:" + sql);
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}

		}
		return coll;

	}

	public ArrayList searchNearPoint(String deviceid, String time, String miles) {
		ArrayList ls = new ArrayList();
		String searchList = "";
		String longitude = "";
		String latitude = "";
		String submittime = time.substring(0, 4) + time.substring(5, 7)
				+ time.substring(8, 10) + time.substring(11, 13)
				+ time.substring(14, 16) + time.substring(17, 19);
		String sql1 = "select distinct longitude,latitude from cana_gps_car where deviceid=? and submit_time=?";

		String sql = "select public_cana_water.* "
				+ " from public_cana_water where dbo.fnGetGPSPOPDistance(?,?,cast(longitude as decimal(18,6)),cast(latitude as decimal(18,6)))<=?";
		try {
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql1,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, deviceid);
			pstmt.setString(2, submittime);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				longitude = rs.getString("longitude");
				latitude = rs.getString("latitude");
			}
			rs.close();
			pstmt.close();
			if (!longitude.equals("")) {
				pstmt = con.prepareStatement(sql,
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				double xposition = Double.parseDouble(longitude);
				double yposition = Double.parseDouble(latitude);

				pstmt.setDouble(1, xposition);
				pstmt.setDouble(2, yposition);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Water_map_show CShow = new Water_map_show();
					CShow.setLongitude(rs.getString("longitude"));
					CShow.setName(rs.getString("name"));
					CShow.setAddress(rs.getString("address"));
					CShow.setTown(rs.getString("town"));
					CShow.setRoad(rs.getString("road"));
					CShow.setNong(rs.getString("nong"));
					CShow.setNum(rs.getString("num"));
					CShow.setCjzone(rs.getString("cjzone"));
					CShow.setCjdate(rs.getString("cjdate"));
					CShow.setNtu(rs.getString("ntu"));
					CShow.setColor(rs.getString("color"));
					CShow.setPh(rs.getString("ph"));
					CShow.setCl(rs.getString("cl"));
					CShow.setCod(rs.getString("cod"));
					CShow.setCustomid(rs.getString("customid"));
					ls.add(searchList);

				}
			}
		} catch (Exception e) {
			crmLog.error("Error in method searchLatestPoint() of Custom,error:"
					+ e.getMessage() + ",sql:" + sql);
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}

		}
		return ls;
	}

	public String searchWaterPointsql(int st, String language_v,
			String comprise1, String comprise2, String comprise3, String tj_v1,
			String tj_v2, String tj_v3, String tj_v4, String tj_v5,
			String tj_v31, String tj_v41, String tj_v51, String tj_where,
			String taxis_name, String taxis_v, String status) {
		int size = 0;
		int sizepa = 0;
		String sql = "";
		String searchLaton = "";
		try {
			if (tj_v3.equals("")) {
				if (tj_v2.equals("0") || tj_v2.equals("")) {

					sql = "select count(*) from  public_cana_water pcw  where water_type is not null and water_type<>'' ";

					if (comprise3 != null && !comprise3.equals("")) {
						sql = sql + " and city='" + comprise3 + "'";
					}
					if (tj_v1 != null && !tj_v1.equals("--")) {
						sql = sql + " and district='" + tj_v1 + "'";
					}
					if (comprise2 != null && !comprise2.equals("")) {
						sql = sql + " and pcw.road like '%" + comprise2 + "%'";
					}
					if (tj_v31 != null && !tj_v31.equals("")) {
						if (tj_v31.equals("�����û�")) {
							sql = sql + " and water_type='�����û�'";
						} else if (tj_v31.equals("ȫ��")) {
							sql = sql + " and 1=1 ";
						} else {
							sql = sql + " and water_type='" + tj_v31 + "' ";
						}
					}
				} else {
					searchLaton = latonConvert(comprise3, comprise2);
					sql = " select count(*) "
							+ "  from public_cana_water pcw  "
							+ " where  water_type is not null and water_type <> '' and dbo.fnGetGPSPOPDistance(?,?,cast(geo_longitude as decimal(18,6)),cast(geo_latitude as decimal(18,6)))<= ";
					if (tj_v2.equals("1")) {
						sql = sql + "5000 ";
					}
					if (tj_v2.equals("2")) {
						sql = sql + "10000 ";
					}
					if (tj_v2.equals("3")) {
						sql = sql + "20000 ";
					}
					if (tj_v31 != null && !tj_v31.equals("")) {
						if (tj_v31.equals("�����û�")) {
							sql = sql + " and water_type is null ";
						} else if (tj_v31.equals("ȫ��")) {
							sql = sql + " and 1=1 ";
						} else {
							sql = sql + " and water_type='" + tj_v31 + "' ";
						}
					} else {
						sql = sql + " and water_type is null ";
					}

				}
				con = DbConnectionManager.getConnection();
				pstmt = con.prepareStatement(sql,
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				if (!tj_v2.equals("0")) {
					String[] pointInfo = searchLaton.split(";");
					String[] latonInfo = pointInfo[1].split(",");
					pstmt.setString(1, latonInfo[0]);
					pstmt.setString(2, latonInfo[1]);
				}
			} else {
				if (tj_v51.equals("waterpoint")) {
					sql = "select count(*) "
							+ " from  "
							+ "  public_cana_water pcw "
							+ " where  pcw.water_type is not null and water_type<>'' and dbo.fnGetGPSPOPDistance(?,?,cast(geo_longitude as decimal(18,6)),cast(geo_latitude as decimal(18,6)))<= ";
				} else {
					sql = "select longitude,pcw.name as name,pcw.address as address,cjdate,ntu,color,ph,cl,cod,pcw.customid "
							+ " from  "
							+ "  public_cana_water pcw  "
							+ " where  pcw.water_type is not null  and water_type<>'' and dbo.fnGetGPSPOPDistance(?,?,cast(geo_longitude as decimal(18,6)),cast(geo_latitude as decimal(18,6)))<= ";
				}
				if (!tj_v5.equals("")) {
					sql = sql + tj_v5;
				}
				if (tj_v31 != null && !tj_v31.equals("")) {
					if (tj_v31.equals("�����û�")) {
						sql = sql + " and water_type is null ";
					} else if (tj_v31.equals("ȫ��")) {
						sql = sql + " and 1=1 ";
					} else {
						sql = sql + " and water_type='" + tj_v31 + "' ";
					}
				} else {
					sql = sql + " and water_type is null ";
				}
				con = DbConnectionManager.getConnection();
				pstmt = con.prepareStatement(sql,
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				pstmt.setString(1, tj_v3);
				pstmt.setString(2, tj_v4);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				size = rs.getInt(1);
				sizepa = (int) size / st;
				if (size % st > 0) {
					sizepa = sizepa + 1;
				}
			}

			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog
					.error("Error in method searchWaterPointsql() of Custom,error:"
							+ ex.getMessage() + ",sql:" + sql);
			// System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
			return sizepa + "+" + size;
		}
	}

	public String searchPointsql(int st, String language_v, String comprise1,
			String comprise2, String comprise3, String comprise4, String tj_v1,
			String tj_v2, String tj_v3, String tj_v4, String tj_v5,
			String tj_v31, String tj_v41, String tj_v51, String tj_where,
			String taxis_name, String taxis_v, String status) {
		int size = 0;
		int sizepa = 0;
		String sql = "";
		String searchLaton = "";
		try {
			if (tj_v3.equals("")) {
				if (tj_v2.equals("0") || tj_v2.equals("")) {
					sql = "select count(*) from cana_custom cc inner join public_cana_water pcw on cc.customid=pcw.customid where 1=1 ";
					if (comprise3 != null && !comprise3.equals("")) {
						sql = sql + " and city='" + comprise3 + "'";
					}
					if (comprise4 != null && !comprise4.equals("")) {
						sql = sql + " and cc.name='" + comprise4 + "'";
					}
					if (tj_v1 != null && !tj_v1.equals("--")) {
						sql = sql + " and district='" + tj_v1 + "'";
					}
					if (comprise2 != null && !comprise2.equals("")) {
						sql = sql + " and pcw.road like '%" + comprise2 + "%'";
					}
					if (comprise1 != null && !comprise1.equals("")) {
						if (comprise1.equals("�Ϻ��ͻ�")) {
							sql = sql + " and cc.area1='�Ϻ�'";
						}
						if (comprise1.equals("������")) {
							sql = sql + " and cc.kind like '%������%'";
						}
						if (comprise1.equals("VIP�ͻ�")) {
							sql = sql + " and cc.kind like '%VIP%'";
						}
						if (comprise1.equals("VIP���ͻ�")) {
							sql = sql
									+ " and pcw.customid in(select power from cana_user_member)";
						}
					}
					if (tj_v31 != null && !tj_v31.equals("")) {
						if (tj_v31.equals("0")) {
							sql = sql + " and cjdate <>'' ";
						} else if (tj_v31.equals("1")) {
							sql = sql
									+ " and filter_ntu is not null and filter_ntu<>''";
						}
					}

				} else {
					searchLaton = latonConvert(comprise3, comprise2);
					sql = " select count(*) "
							+ " from cana_custom cu "
							+ " inner join public_cana_water pcw on pcw.customid=cu.customid "
							+ " where  dbo.fnGetGPSPOPDistance(?,?,cast(geo_longitude as decimal(18,6)),cast(geo_latitude as decimal(18,6)))<= ";
					if (tj_v2.equals("1")) {
						sql = sql + "500 ";
					}
					if (tj_v2.equals("2")) {
						sql = sql + "1000 ";
					}
					if (tj_v2.equals("3")) {
						sql = sql + "5000 ";
					}
					if (comprise4 != null && !comprise4.equals("")) {
						sql = sql + " and cc.name='" + comprise4 + "'";
					}
					if (comprise1 != null && !comprise1.equals("")) {
						if (comprise1.equals("�Ϻ��ͻ�")) {
							sql = sql + " and cc.area1='�Ϻ�'";
						}
						if (comprise1.equals("������")) {
							sql = sql + " and cc.kind like '%������%'";
						}
						if (comprise1.equals("VIP�ͻ�")) {
							sql = sql + " and cc.kind like '%VIP%'";
						}
						if (comprise1.equals("VIP���ͻ�")) {
							sql = sql
									+ " and pcw.customid in(select power from cana_user_member)";
						}
					}
					if (tj_v31 != null && !tj_v31.equals("")) {
						if (tj_v31.equals("0")) {
							sql = sql + " and cjdate <>'' ";
						} else if (tj_v31.equals("1")) {
							sql = sql
									+ " and filter_ntu is not null and filter_ntu<>'' ";
						}
					}
				}
				con = DbConnectionManager.getConnection();
				pstmt = con.prepareStatement(sql,
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				if (!tj_v2.equals("0")) {
					String[] pointInfo = searchLaton.split(";");
					String[] latonInfo = pointInfo[1].split(",");
					pstmt.setString(1, latonInfo[0]);
					pstmt.setString(2, latonInfo[1]);
				}
			} else {
				if (tj_v51.equals("waterpoint")) {
					sql = "select count(*)"
							+ " from public_cana_water pcw "
							+ " where  pcw.water_type is not null and pcw.water_type<>''  and dbo.fnGetGPSPOPDistance(?,?,cast(geo_longitude as decimal(18,6)),cast(geo_latitude as decimal(18,6)))<= ";
				} else {
					sql = "select count(*) "
							+ " from cana_custom cu "
							+ " inner join public_cana_water pcw on pcw.customid=cu.customid "
							+ " where  dbo.fnGetGPSPOPDistance(?,?,cast(geo_longitude as decimal(18,6)),cast(geo_latitude as decimal(18,6)))<= ";
				}
				if (!tj_v5.equals("")) {
					sql = sql + tj_v5;
				}
				con = DbConnectionManager.getConnection();
				pstmt = con.prepareStatement(sql,
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				pstmt.setString(1, tj_v3);
				pstmt.setString(2, tj_v4);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				size = rs.getInt(1);
				sizepa = (int) size / st;
				if (size % st > 0) {
					sizepa = sizepa + 1;
				}
			}

			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog.error("Error in method searchPointsql() of Custom,error:"
					+ ex.getMessage() + ",sql:" + sql);
			// System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
			return sizepa + "+" + size;
		}
	}

	public String searchWaterPoint(int st, int page, String language_v,
			String comprise1, String comprise2, String comprise3, String tj_v1,
			String tj_v2, String tj_v3, String tj_v4, String tj_v5,
			String tj_v31, String tj_v41, String tj_v51, String tj_where,
			String taxis_name, String taxis_v, String status) {
		String coll = "";
		String sql = "";
		int tip = st * (page - 1);
		String searchLaton = "";
		try {
			if (tj_v3.equals("")) {
				searchLaton = latonConvert(comprise3, comprise2);
				if (tj_v2.equals("0") || tj_v2.equals("")) {

					sql = "select longitude,pcw.name,(case when district is null or district='' then '' else district end)+(case when town is null or town='' then '' else town end )+(case when road is null or road='' then '' else road end)+(case when nong is null or nong='' then '' else nong end)+(case when num is null or num='' then '' else num end)+(case when cjzone is null or cjzone='' then '' else cjzone end) as address,cjdate,ntu,color,ph,cl,cod,pcw.customid as customid,pcw.geo_longitude as geo_longitude,pcw.geo_latitude as geo_latitude,pcw.id as cid from  public_cana_water pcw  where water_type is not null ";

					if (comprise3 != null && !comprise3.equals("")) {
						sql = sql + " and city='" + comprise3 + "'";
					}
					if (tj_v1 != null && !tj_v1.equals("--")) {
						sql = sql + " and district='" + tj_v1 + "'";
					}
					if (comprise2 != null && !comprise2.equals("")) {
						sql = sql + " and pcw.road like '%" + comprise2 + "%'";
					}
					if (tj_v31 != null && !tj_v31.equals("")) {
						if (tj_v31.equals("�����û�")) {
							sql = sql + " and water_type='�����û�' ";
						} else if (tj_v31.equals("ȫ��")) {
							sql = sql + " and 1=1 ";
						} else {
							sql = sql + " and water_type='" + tj_v31 + "' ";
						}
					} else {
						sql = sql + " and water_type is null ";
					}
				} else {

					sql = "select longitude,pcw.name as name,(case when district is null or district='' then '' else district end)+(case when town is null or town='' then '' else town end )+(case when road is null or road='' then '' else road end)+(case when nong is null or nong='' then '' else nong end)+(case when num is null or num='' then '' else num end)+(case when cjzone is null or cjzone='' then '' else cjzone end) as address,cjdate,ntu,color,ph,cl,cod,pcw.customid as customid,pcw.geo_longitude as geo_longitude,pcw.geo_latitude as geo_latitude,pcw.id as cid "
							+ " from public_cana_water pcw  "
							+ " where  pcw.cjdate<>'' and dbo.fnGetGPSPOPDistance(?,?,cast(geo_longitude as decimal(18,6)),cast(geo_latitude as decimal(18,6)))<= ";

					if (tj_v2.equals("1")) {
						sql = sql + "5000 ";
					}
					if (tj_v2.equals("2")) {
						sql = sql + "10000 ";
					}
					if (tj_v2.equals("3")) {
						sql = sql + "20000 ";
					}
					if (tj_v31 != null && !tj_v31.equals("")) {
						if (tj_v31.equals("�����û�")) {
							sql = sql + " and water_type='�����û�' ";
						} else if (tj_v31.equals("ȫ��")) {
							sql = sql + " and 1=1 ";
						} else {
							sql = sql + " and water_type='" + tj_v31 + "' ";
						}
					} else {
						sql = sql + " and water_type is null ";
					}
				}
				con = DbConnectionManager.getConnection();
				pstmt = con.prepareStatement(sql,
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);

				String[] pointInfo = searchLaton.split(";");
				if (!tj_v2.equals("0")) {
					String[] latonInfo = pointInfo[1].split(",");
					pstmt.setString(1, latonInfo[0]);
					pstmt.setString(2, latonInfo[1]);
				}
				rs = pstmt.executeQuery();
				if (tip <= 0) {
					rs.beforeFirst();
				} else if (!rs.absolute(tip)) {
					rs.absolute(tip);
				}
				for (int i = 0; rs.next() && i < st; i++) {
					coll = coll + rs.getString("longitude");
					coll = coll + ",";
					coll = coll + rs.getString("name");
					coll = coll + ",";
					coll = coll + rs.getString("address");
					coll = coll + ",";
					coll = coll + rs.getString("cjdate");
					coll = coll + ",";
					coll = coll + rs.getString("ntu");
					coll = coll + ",";
					coll = coll + rs.getString("color");
					coll = coll + ",";
					coll = coll + rs.getString("ph");
					coll = coll + ",";
					coll = coll + rs.getString("cl");
					coll = coll + ",";
					coll = coll + rs.getString("cod");
					coll = coll + ",";
					coll = coll + rs.getString("customid");
					coll = coll + ",";
					coll = coll + rs.getString("geo_longitude");
					coll = coll + ",";
					coll = coll + rs.getString("geo_latitude");
					coll = coll + ",";
					String sid = rs.getInt("cid") + "";
					coll = coll + sid;
					coll = coll + ";";
				}
				if (coll.equals("")) {
					coll = coll + ";";
				}
				if (searchLaton.equals("")) {
					coll = coll + "|";
				} else {
					coll = coll + "|" + pointInfo[0] + "|" + pointInfo[2] + "|"
							+ pointInfo[3];
				}

			} else {
				if (tj_v3.equals("no")) {
					String realStr = getRealLaton(tj_v41, 1);
					String realArr = realStr.substring(0, realStr.length() - 1);
					String[] realgeoLaton = realArr.split(",");
					tj_v3 = realgeoLaton[1];
					tj_v4 = realgeoLaton[0];
				}
				if (tj_v51.equals("waterpoint")) {
					sql = "select longitude,pcw.name as name,(case when district is null or district='' then '' else district end)+(case when town is null or town='' then '' else town end )+(case when road is null or road='' then '' else road end)+(case when nong is null or nong='' then '' else nong end)+(case when num is null or num='' then '' else num end)+(case when cjzone is null or cjzone='' then '' else cjzone end) as address,cjdate,ntu,color,ph,cl,cod,pcw.customid as customid,pcw.geo_longitude as geo_longitude,pcw.geo_latitude as geo_latitude "
							+ " from public_cana_water pcw  "
							+ " where pcw.water_type is not null and pcw.water_type<>'' and  dbo.fnGetGPSPOPDistance(?,?,cast(geo_longitude as decimal(18,6)),cast(geo_latitude as decimal(18,6)))<= ";

				}
				if (!tj_v5.equals("")) {
					sql = sql + tj_v5;
				}
				if (tj_v31 != null && !tj_v31.equals("")) {
					if (tj_v31.equals("�����û�")) {
						sql = sql + " and water_type='�����û�' ";
					} else if (tj_v31.equals("ȫ��")) {
						sql = sql + " and 1=1 ";
					} else {
						sql = sql + " and water_type='" + tj_v31 + "' ";
					}
				} else {
					sql = sql + " and water_type is null ";
				}
				sql = sql
						+ " order by dbo.fnGetGPSPOPDistance(?,?,cast(geo_longitude as decimal(18,6)),cast(geo_latitude as decimal(18,6)))";
				con = DbConnectionManager.getConnection();
				pstmt = con.prepareStatement(sql,
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				pstmt.setString(1, tj_v3);
				pstmt.setString(2, tj_v4);
				pstmt.setString(3, tj_v3);
				pstmt.setString(4, tj_v4);
				rs = pstmt.executeQuery();
				rs = pstmt.executeQuery();
				if (tip <= 0) {
					rs.beforeFirst();
				} else if (!rs.absolute(tip)) {
					rs.beforeFirst();
				}
				for (int i = 1; rs.next() && i < st; i++) {
					coll = coll + rs.getString("longitude");
					coll = coll + ",";
					coll = coll + rs.getString("name");
					coll = coll + ",";
					coll = coll + rs.getString("address");
					coll = coll + ",";
					coll = coll + rs.getString("cjdate");
					coll = coll + ",";
					coll = coll + rs.getString("ntu");
					coll = coll + ",";
					coll = coll + rs.getString("color");
					coll = coll + ",";
					coll = coll + rs.getString("ph");
					coll = coll + ",";
					coll = coll + rs.getString("cl");
					coll = coll + ",";
					coll = coll + rs.getString("cod");
					coll = coll + ",";
					coll = coll + rs.getString("customid");
					coll = coll + ",";
					coll = coll + rs.getString("geo_longitude");
					coll = coll + ",";
					coll = coll + rs.getString("geo_latitude");
					coll = coll + ";";
				}
				if (coll.equals("")) {
					coll = coll + ";";
				}
				coll = coll + "|" + tj_v41 + "|����λ��" + "|" + "������ַ";
			}
			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog.error("Error in method searchPointsql() of Custom,error:"
					+ ex.getMessage() + ",sql:" + sql);
			// System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}

			return coll;
		}
	}

	public String searchPoint(int st, int page, String language_v,
			String comprise1, String comprise2, String comprise3,
			String comprise4, String tj_v1, String tj_v2, String tj_v3,
			String tj_v4, String tj_v5, String tj_v31, String tj_v41,
			String tj_v51, String tj_where, String taxis_name, String taxis_v,
			String status) {
		String coll = "";
		String sql = "";
		int tip = st * (page - 1);
		String searchLaton = "";
		try {
			if (tj_v3.equals("")) {
				searchLaton = latonConvert(comprise3, comprise2);
				if (tj_v2.equals("0") || tj_v2.equals("")) {
					if (tj_where.equals("��׼�ͻ�")) {
						sql = "select longitude,cc.name as name ,(case when district is null or district='' then '' else district end)+(case when town is null or town='' then '' else town+'��' end )+(case when road is null or road='' then '' else road end)+(case when nong is null or nong='' then '' else nong+'Ū' end)+(case when num is null or num='' then '' else num end)+(case when cjzone is null or cjzone='' then '' else cjzone end) as address,cjdate,ntu,color,ph,cl,cod,pcw.customid as customid,pcw.geo_longitude as geo_longitude,pcw.geo_latitude as geo_latitude,filter_ntu,filter_color,filter_ph,filter_cl,filter_cod,apply_time,filter_type,water_used from cana_custom cc inner join public_cana_water pcw on cc.customid=pcw.customid where 1=1 ";
					} else {
						sql = "select longitude,cc.name as name ,cc.address as address,cjdate,ntu,color,ph,cl,cod,pcw.customid as customid,pcw.geo_longitude as geo_longitude,pcw.geo_latitude as geo_latitude,filter_ntu,filter_color,filter_ph,filter_cl,filter_cod,apply_time,filter_type,water_used from cana_custom cc inner join public_cana_water pcw on cc.customid=pcw.customid where 1=1 ";
					}
					if (comprise3 != null && !comprise3.equals("")) {
						sql = sql + " and city='" + comprise3 + "'";
					}
					if (comprise4 != null && !comprise4.equals("")) {
						sql = sql + " and cc.name='" + comprise4 + "'";
					}
					if (tj_v1 != null && !tj_v1.equals("--")) {
						sql = sql + " and district='" + tj_v1 + "'";
					}
					if (comprise2 != null && !comprise2.equals("")) {
						sql = sql + " and pcw.road like '%" + comprise2 + "%'";
					}
					if (comprise1 != null && !comprise1.equals("")) {
						if (comprise1.equals("�Ϻ��ͻ�")) {
							sql = sql + " and cc.area1='�Ϻ�'";
						}
						if (comprise1.equals("������")) {
							sql = sql + " and cc.kind like '%������%'";
						}
						if (comprise1.equals("VIP�ͻ�")) {
							sql = sql + " and cc.kind like '%VIP%'";
						}
						if (comprise1.equals("VIP���ͻ�")) {
							sql = sql
									+ " and pcw.customid in(select power from cana_user_member)";
						}
					}
					if (tj_v31 != null && !tj_v31.equals("")) {
						if (tj_v31.equals("0")) {
							sql = sql + " and cjdate <>'' ";
						} else if (tj_v31.equals("1")) {
							sql = sql + " and filter_ntu<>'' ";
						}
					}

				} else {

					if (tj_where.equals("��׼�ͻ�")) {
						sql = "select longitude,cu.name as name,(case when district is null or district='' then '' else district end)+(case when town is null or town='' then '' else town end )+(case when road is null or road='' then '' else road end)+(case when nong is null or nong='' then '' else nong end)+(case when num is null or num='' then '' else num end)+(case when cjzone is null or cjzone='' then '' else cjzone end) as address,cjdate,ntu,color,ph,cl,cod,pcw.customid as customid,pcw.geo_longitude as geo_longitude,pcw.geo_latitude as geo_latitude,filter_ntu,filter_color,filter_ph,filter_cl,filter_cod,apply_time,filter_type,water_used "
								+ " from"
								+ "  public_cana_water pcw  "
								+ " where  geo_longitude is not null and dbo.fnGetGPSPOPDistance(?,?,cast(geo_longitude as decimal(18,6)),cast(geo_latitude as decimal(18,6)))<= ";
					} else {
						sql = "select longitude, name, address,cjdate,ntu,color,ph,cl,cod,pcw.customid as customid,pcw.geo_longitude as geo_longitude,pcw.geo_latitude as geo_latitude,filter_ntu,filter_color,filter_ph,filter_cl,filter_cod,apply_time,filter_type,water_used "
								+ " from "
								+ " public_cana_water pcw  "
								+ " where  pcw.geo_longitude is not null and dbo.fnGetGPSPOPDistance(?,?,cast(geo_longitude as decimal(18,6)),cast(geo_latitude as decimal(18,6)))<= ";
					}
					if (tj_v2.equals("1")) {
						sql = sql + "500 ";
					}
					if (tj_v2.equals("2")) {
						sql = sql + "1000 ";
					}
					if (tj_v2.equals("3")) {
						sql = sql + "5000 ";
					}

					if (comprise1 != null && !comprise1.equals("")) {
						if (comprise1.equals("�Ϻ��ͻ�")) {
							sql = sql + " and cu.area1='�Ϻ�'";
						}
						if (comprise4 != null && !comprise4.equals("")) {
							sql = sql + " and cc.name='" + comprise4 + "'";
						}
						if (comprise1.equals("������")) {
							sql = sql + " and cu.kind like '%������%'";
						}
						if (comprise1.equals("VIP�ͻ�")) {
							sql = sql + " and cu.kind like '%VIP%'";
						}
						if (comprise1.equals("VIP���ͻ�")) {
							sql = sql
									+ " and pcw.customid in(select power from cana_user_member)";
						}
					}
					if (tj_v31 != null && !tj_v31.equals("")) {
						if (tj_v31.equals("0")) {
							sql = sql + " and cjdate <>'' ";
						} else if (tj_v31.equals("1")) {
							sql = sql + " and filter_ntu<>'' ";
						}
					}
				}
				con = DbConnectionManager.getConnection();
				pstmt = con.prepareStatement(sql,
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);

				String[] pointInfo = searchLaton.split(";");
				if (!tj_v2.equals("0")) {
					String[] latonInfo = pointInfo[1].split(",");
					pstmt.setString(1, latonInfo[0]);
					pstmt.setString(2, latonInfo[1]);
				}
				rs = pstmt.executeQuery();
				if (tip <= 0) {
					rs.beforeFirst();
				} else if (!rs.absolute(tip)) {
					rs.beforeFirst();
				}
				for (int i = 1; rs.next() && i < st; i++) {
					coll = coll + rs.getString("longitude");
					coll = coll + ",";
					coll = coll + rs.getString("name");
					coll = coll + ",";
					coll = coll + rs.getString("address");
					coll = coll + ",";
					coll = coll + rs.getString("cjdate");
					coll = coll + ",";
					coll = coll + rs.getString("ntu");
					coll = coll + ",";
					coll = coll + rs.getString("color");
					coll = coll + ",";
					coll = coll + rs.getString("ph");
					coll = coll + ",";
					coll = coll + rs.getString("cl");
					coll = coll + ",";
					coll = coll + rs.getString("cod");
					coll = coll + ",";
					coll = coll + rs.getString("customid");
					coll = coll + ",";
					coll = coll + rs.getString("geo_longitude");
					coll = coll + ",";
					coll = coll + rs.getString("geo_latitude");
					coll = coll + ",";
					coll = coll + rs.getString("filter_ntu");

					coll = coll + ",";
					coll = coll + rs.getString("filter_color");
					coll = coll + ",";
					coll = coll + rs.getString("filter_ph");
					coll = coll + ",";
					coll = coll + rs.getString("filter_cl");
					coll = coll + ",";
					coll = coll + rs.getString("filter_cod");
					coll = coll + ",";
					coll = coll + rs.getString("apply_time");
					coll = coll + ",";
					coll = coll + rs.getString("filter_type");

					coll = coll + ",";
					coll = coll + rs.getString("water_used");
					coll = coll + ";";
				}
				if (coll.equals("")) {
					coll = coll + ";";
				}
				if (searchLaton.equals("")) {
					coll = coll + "|";
				} else {
					coll = coll + "|" + pointInfo[0] + "|" + pointInfo[2] + "|"
							+ pointInfo[3];
				}

			} else {
				if (tj_v3.equals("no")) {
					String realStr = getRealLaton(tj_v41, 1);
					String realArr = realStr.substring(0, realStr.length() - 1);
					String[] realgeoLaton = realArr.split(",");
					tj_v3 = realgeoLaton[1];
					tj_v4 = realgeoLaton[0];
				}
				if (tj_v51.equals("waterpoint")) {
					sql = "select longitude,pcw.name as name,(case when district is null or district='' then '' else district end)+(case when town is null or town='' then '' else town end )+(case when road is null or road='' then '' else road end)+(case when nong is null or nong='' then '' else nong end)+(case when num is null or num='' then '' else num end)+(case when cjzone is null or cjzone='' then '' else cjzone end) as address, address,cjdate,ntu,color,ph,cl,cod,pcw.customid as customid,pcw.geo_longitude as geo_longitude,pcw.geo_latitude as geo_latitude,filter_ntu,filter_color,filter_ph,filter_cl,filter_cod,apply_time,filter_type,water_used "
							+ " from  "
							+ "  public_cana_water pcw "
							+ " where   water_type<>'' and water_type is not null  and dbo.fnGetGPSPOPDistance(?,?,cast(geo_longitude as decimal(18,6)),cast(geo_latitude as decimal(18,6)))<= ";

				} else {
					if (tj_where.equals("��׼�ͻ�")) {
						sql = "select longitude,cu.name as name,(case when district is null or district='' then '' else district+'��' end)+(case when town is null or town='' then '' else town+'��' end )+(case when road is null or road='' then '' else road end)+(case when nong is null or nong='' then '' else nong+'Ū' end)+(case when num is null or num='' then '' else num+'��' end)+(case when cjzone is null or cjzone='' then '' else cjzone end) as address,cjdate,ntu,color,ph,cl,cod,pcw.customid as customid,pcw.geo_longitude as geo_longitude,pcw.geo_latitude as geo_latitude,filter_ntu,filter_color,filter_ph,filter_cl,filter_cod,apply_time,filter_type,water_used "
								+ " from cana_custom cu "
								+ " inner join public_cana_water pcw on pcw.customid=cu.customid "
								+ " where  dbo.fnGetGPSPOPDistance(?,?,cast(geo_longitude as decimal(18,6)),cast(geo_latitude as decimal(18,6)))<= ";
					} else {
						sql = "select longitude,cu.name as name,cu.address as address,cjdate,ntu,color,ph,cl,cod,pcw.customid as customid,pcw.geo_longitude as geo_longitude,pcw.geo_latitude as geo_latitude,filter_ntu,filter_color,filter_ph,filter_cl,filter_cod,apply_time,filter_type,water_used "
								+ " from cana_custom cu "
								+ " inner join public_cana_water pcw on pcw.customid=cu.customid "
								+ " where  dbo.fnGetGPSPOPDistance(?,?,cast(geo_longitude as decimal(18,6)),cast(geo_latitude as decimal(18,6)))<= ";
					}
				}
				if (!tj_v5.equals("")) {
					sql = sql + tj_v5;
				}
				sql = sql
						+ " order by dbo.fnGetGPSPOPDistance(?,?,cast(geo_longitude as decimal(18,6)),cast(geo_latitude as decimal(18,6)))";
				con = DbConnectionManager.getConnection();
				pstmt = con.prepareStatement(sql,
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				pstmt.setString(1, tj_v3);
				pstmt.setString(2, tj_v4);
				pstmt.setString(3, tj_v3);
				pstmt.setString(4, tj_v4);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					coll = coll + rs.getString("longitude");
					coll = coll + ",";
					coll = coll + rs.getString("name");
					coll = coll + ",";
					coll = coll + rs.getString("address");
					coll = coll + ",";
					coll = coll + rs.getString("cjdate");
					coll = coll + ",";
					coll = coll + rs.getString("ntu");
					coll = coll + ",";
					coll = coll + rs.getString("color");
					coll = coll + ",";
					coll = coll + rs.getString("ph");
					coll = coll + ",";
					coll = coll + rs.getString("cl");
					coll = coll + ",";
					coll = coll + rs.getString("cod");
					coll = coll + ",";
					coll = coll + rs.getString("customid");
					coll = coll + ",";
					coll = coll + rs.getString("geo_longitude");
					coll = coll + ",";
					coll = coll + rs.getString("geo_latitude");
					coll = coll + ",";
					coll = coll + rs.getString("filter_ntu");
					coll = coll + ",";
					coll = coll + rs.getString("filter_color");
					coll = coll + ",";
					coll = coll + rs.getString("filter_ph");
					coll = coll + ",";
					coll = coll + rs.getString("filter_cl");
					coll = coll + ",";
					coll = coll + rs.getString("filter_cod");
					coll = coll + ",";
					coll = coll + rs.getString("apply_time");
					coll = coll + ",";
					coll = coll + rs.getString("filter_type");
					coll = coll + ",";
					coll = coll + rs.getString("water_used");
					coll = coll + ";";
				}
				if (coll.equals("")) {
					coll = coll + ";";
				}
				coll = coll + "|" + tj_v41 + "|����λ��" + "|" + "������ַ";
			}
			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog.error("Error in method searchPointsql() of Custom,error:"
					+ ex.getMessage() + ",sql:" + sql);
			// System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}

			return coll;
		}
	}

	public String latonConvert(String city, String keyword) {
		String sqlReturn = "";
		String context = "";
		String mCenter = "";
		String scale = "";
		String address = "";
		int i = 0;
		String[] xc = new String[500];
		String[] xu = new String[500];
		String[] xd = new String[500];
		try {
			MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
			HttpClient httpclient = new HttpClient(connectionManager);
			// PostMethod postMethod=new PostMethod();
			String keysearch = java.net.URLEncoder.encode(keyword, "GBK");
			String cityId = java.net.URLEncoder.encode(city, "GBK");
			String url = "";
			url = "http://mapx.mapbar.com/search/getPoiByKeyword.jsp?"
					+ "keyword=" + keysearch + "&city=" + cityId
					+ "&customer=2";
			GetMethod getMethod = new GetMethod(url);
			// PostMethod postMethod = new
			// PostMethod("http://mapx.mapbar.com/search/getPoiByKeyword.jsp");
			int statusCode = httpclient.executeMethod(getMethod);
			byte[] responseBody = getMethod.getResponseBody();
			String code = getMethod.getResponseCharSet();
			context = new String(responseBody, code);
			getMethod.releaseConnection();
			try {
				String xa[] = context.split("<strlatlon>");
				String xv[] = new String[xa.length];
				for (i = 1; i < xa.length; i++) {
					String xb[] = xa[i].split("</strlatlon>");
					xv[i] = xb[0];
				}
				xc = context.split("<address>");
				xu = new String[xc.length];
				for (i = 1; i < xc.length; i++) {
					xd = xc[i].split("</address>");
					xu[i] = xd[0];
					for (int j = 1; j < xu.length; j++) {
						if (!xu[j].equals("")
								&& (xu[j].indexOf(keyword) > -1 || keyword
										.indexOf(xu[j]) > -1)) {
							mCenter = xv[j];
							address = xu[j];
						}
					}
				}
			} catch (Exception e) {
				e.getMessage();
			}
			if (mCenter.equals("")) {
				String cenStr[] = context.split("<center>");
				String rcStr[] = cenStr[1].split("</center>");
				mCenter = rcStr[0];
				address = "����λ�����ĵ�";
			}
			if (scale.equals("")) {
				String scaleStr[] = context.split("<scale>");
				String scaStr[] = scaleStr[1].split("</scale>");
				scale = scaStr[0];
			}
			MultiThreadedHttpConnectionManager connectionManager1 = new MultiThreadedHttpConnectionManager();
			HttpClient httpclient1 = new HttpClient(connectionManager1);
			String mLaton[] = mCenter.split(",");
			String url1 = "http://geocode.mapbar.com/decode/getEncode.jsp?latlon="
					+ mLaton[1] + "," + mLaton[0] + "&customer=2";
			GetMethod getMethod1 = new GetMethod(url1);
			// PostMethod postMethod = new
			// PostMethod("http://mapx.mapbar.com/search/getPoiByKeyword.jsp");
			int statusCode1 = httpclient1.executeMethod(getMethod1);
			byte[] responseBody1 = getMethod1.getResponseBody();
			String code1 = getMethod1.getResponseCharSet();
			String context1 = new String(responseBody1, code1);
			getMethod1.releaseConnection();
			String xa[] = context1.split("<latlon>");
			// if(xa.length!=request_i+1){System.out.println("��Ŀ���ԣ�");}
			String xv[] = new String[xa.length];
			for (i = 1; i < xa.length; i++) {
				String xb[] = xa[i].split("</latlon>");
				xv[i] = xb[0];
			}
			sqlReturn = xv[1] + ";" + mCenter + ";" + scale + ";" + address;
		} catch (Exception e) {
			crmLog.error("Error in method latonConvert() of Custom,error:"
					+ e.getMessage());
		}
		return sqlReturn;
	}

	public String searchNoMapsql(int st, String language_v, String tj_name1,
			String tj_name2, String tj_name3, String tj_v1, String tj_v2,
			String tj_v3, String tj_v11, String tj_v21, String tj_v31,
			String tj_where, String taxis_name, String taxis_v, String status) {
		int size = 0;
		int sizepa = 0;
		String sql = "";
		try {
			sql = "select count(*) from cana_custom cc left join public_cana_water pcw on cc.customid=pcw.customid where pcw.customid is  null and language_ver=?";
			String where = " ", where1 = "";
			if ((!tj_v1.equals("")) && (tj_v1 != null)) {
				if (tj_v11.equals("0")) {
					String tj_v1_per = "";
					for (int i = 0; i < tj_v1.length(); i++) {
						tj_v1_per += tj_v1.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name1 + " like '%" + tj_v1_per
							+ "%'";
				} else if (tj_v11.equals("1")) {
					where = where + " and " + tj_name1 + " like '" + tj_v1
							+ "%'";
				} else if (tj_v11.equals("2")) {
					where = where + " and " + tj_name1 + " like '%" + tj_v1
							+ "%'";
				} else if (tj_v11.equals("3")) {
					where = where + " and " + tj_name1 + " not like '%" + tj_v1
							+ "%'";
				} else {
					where = where + " and " + tj_name1 + "='" + tj_v1 + "'";
				}
			}
			if ((!tj_v2.equals("")) && (tj_v2 != null)) {
				if (tj_v21.equals("0")) {
					String tj_v2_per = "";
					for (int i = 0; i < tj_v2.length(); i++) {
						tj_v2_per += tj_v2.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name2 + " like '%" + tj_v2_per
							+ "%'";
				} else if (tj_v21.equals("1")) {
					where = where + " and " + tj_name2 + " like '" + tj_v2
							+ "%'";
				} else if (tj_v21.equals("2")) {
					where = where + " and " + tj_name2 + " like '%" + tj_v2
							+ "%'";
				} else if (tj_v21.equals("3")) {
					where = where + " and " + tj_name2 + " not like '%" + tj_v2
							+ "%'";
				} else {
					where = where + " and " + tj_name2 + "='" + tj_v2 + "'";
				}
			}
			if ((!tj_v3.equals("")) && (tj_v3 != null)) {
				if (tj_v31.equals("0")) {
					String tj_v3_per = "";
					for (int i = 0; i < tj_v3.length(); i++) {
						tj_v3_per += tj_v3.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name3 + " like '%" + tj_v3_per
							+ "%'";
				} else if (tj_v31.equals("1")) {
					where = where + " and " + tj_name3 + " like '" + tj_v3
							+ "%'";
				} else if (tj_v31.equals("2")) {
					where = where + " and " + tj_name3 + " like '%" + tj_v3
							+ "%'";
				} else if (tj_v31.equals("3")) {
					where = where + " and " + tj_name3 + " not like '%" + tj_v3
							+ "%'";
				} else {
					where = where + " and " + tj_name3 + "='" + tj_v3 + "'";
				}
			}
			if ((!status.equals("")) && (status != null)) {
				where = where + " and status='" + status + "'";
			}
			if ((!tj_where.equals("")) && (tj_where != null)) {
				where = where + tj_where;
			}
			sql = sql + where;
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, language_v);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				size = rs.getInt(1);
				sizepa = (int) size / st;
				if (size % st > 0) {
					sizepa = sizepa + 1;
				}
			}

			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog.error("Error in method searchNoMapsql() of Custom,error:"
					+ ex.getMessage() + ",sql:" + sql);
			// System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
			return sizepa + "+" + size;
		}
	}

	public Collection searchNoMap(int step, int page, String language_v,
			String tj_name1, String tj_name2, String tj_name3, String tj_v1,
			String tj_v2, String tj_v3, String tj_v11, String tj_v21,
			String tj_v31, String tj_where, String taxis_name, String taxis_v,
			String status) {
		Collection coll = new ArrayList();
		int tip = step * (page - 1);
		String sql = "";
		try {
			sql = "select cc.* from cana_custom cc left join public_cana_water pcw on cc.customid=pcw.customid where pcw.customid is  null and language_ver=?";
			String where = " ", where1 = "";
			if ((!tj_v1.equals("")) && (tj_v1 != null)) {
				if (tj_v11.equals("0")) {
					String tj_v1_per = "";
					for (int i = 0; i < tj_v1.length(); i++) {
						tj_v1_per += tj_v1.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name1 + " like '%" + tj_v1_per
							+ "%'";
				} else if (tj_v11.equals("1")) {
					where = where + " and " + tj_name1 + " like '" + tj_v1
							+ "%'";
				} else if (tj_v11.equals("2")) {
					where = where + " and " + tj_name1 + " like '%" + tj_v1
							+ "%'";
				} else if (tj_v11.equals("3")) {
					where = where + " and " + tj_name1 + " not like '%" + tj_v1
							+ "%'";
				} else {
					where = where + " and " + tj_name1 + "='" + tj_v1 + "'";
				}
			}
			if ((!tj_v2.equals("")) && (tj_v2 != null)) {
				if (tj_v21.equals("0")) {
					String tj_v2_per = "";
					for (int i = 0; i < tj_v2.length(); i++) {
						tj_v2_per += tj_v2.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name2 + " like '%" + tj_v2_per
							+ "%'";
				} else if (tj_v21.equals("1")) {
					where = where + " and " + tj_name2 + " like '" + tj_v2
							+ "%'";
				} else if (tj_v21.equals("2")) {
					where = where + " and " + tj_name2 + " like '%" + tj_v2
							+ "%'";
				} else if (tj_v21.equals("3")) {
					where = where + " and " + tj_name2 + " not like '%" + tj_v2
							+ "%'";
				} else {
					where = where + " and " + tj_name2 + "='" + tj_v2 + "'";
				}
			}
			if ((!tj_v3.equals("")) && (tj_v3 != null)) {
				if (tj_v31.equals("0")) {
					String tj_v3_per = "";
					for (int i = 0; i < tj_v3.length(); i++) {
						tj_v3_per += tj_v3.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name3 + " like '%" + tj_v3_per
							+ "%'";
				} else if (tj_v31.equals("1")) {
					where = where + " and " + tj_name3 + " like '" + tj_v3
							+ "%'";
				} else if (tj_v31.equals("2")) {
					where = where + " and " + tj_name3 + " like '%" + tj_v3
							+ "%'";
				} else if (tj_v31.equals("3")) {
					where = where + " and " + tj_name3 + " not like '%" + tj_v3
							+ "%'";
				} else {
					where = where + " and " + tj_name3 + "='" + tj_v3 + "'";
				}
			}
			if ((!status.equals("")) && (status != null)) {
				where = where + " and status='" + status + "'";
			}
			if ((!tj_where.equals("")) && (tj_where != null)) {
				where = where + tj_where;
			}
			String orderby = " order by id desc";
			if ((!taxis_v.equals("")) && (taxis_v != null)) {
				orderby = " order by cc." + taxis_name + " " + taxis_v;
			}
			sql = sql + where + orderby;
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, language_v);
			rs = pstmt.executeQuery();
			if (tip <= 0) {
				rs.beforeFirst();
			} else if (!rs.absolute(tip)) {
				rs.beforeFirst();
			}
			for (int i = 1; rs.next() && i <= step; i++) {
				CustomShow CShow = new CustomShow();
				CShow.setid(rs.getInt("id"));
				CShow.setcustomid(rs.getInt("customid"));
				CShow.setserial_num(rs.getString("serial_num"));
				CShow.setname(rs.getString("name"));
				CShow.setname_pinyin(rs.getString("name_pinyin"));
				CShow.setarea1(rs.getString("area1"));
				CShow.setarea2(rs.getString("area2"));
				CShow.setarea3(rs.getString("area3"));
				CShow.setlinkman(rs.getString("linkman"));
				CShow.settel(rs.getString("tel"));
				CShow.setmobile(rs.getString("mobile"));
				CShow.setfax(rs.getString("fax"));
				CShow.setwebsite(rs.getString("website"));
				CShow.setemail(rs.getString("email"));
				CShow.setkind(rs.getString("kind"));
				CShow.setaddress(rs.getString("address"));
				CShow.setzip(rs.getInt("zip"));
				// CShow.setseller(rs.getInt("seller"));
				CShow.setseller(rs.getString("seller"));
				CShow.setfiliale(rs.getString("filiale"));
				CShow.setshare(rs.getString("share"));
				CShow.sethotspot(rs.getString("hotspot"));
				CShow.sethotspot_explain(rs.getString("hotspot_explain"));
				CShow.setvalue_evaluate(rs.getString("value_evaluate"));
				CShow.setcredit(rs.getString("credit"));
				CShow.setindustry(rs.getString("industry"));
				CShow.setrelation(rs.getString("relation"));
				CShow.sethuman_size(rs.getString("human_size"));
				CShow.setresource(rs.getString("resource"));
				CShow.setremark(rs.getString("remark"));
				CShow.setrecorder(rs.getInt("recorder"));
				CShow.setrecorder_time(rs.getString("recorder_time"));
				CShow.setupdater(rs.getInt("updater"));
				CShow.setupdate_time(rs.getString("update_time"));
				CShow.setself_field1(rs.getString("self_field1"));
				CShow.setself_field2(rs.getString("self_field2"));
				CShow.setself_field3(rs.getString("self_field3"));
				CShow.setself_field4(rs.getString("self_field4"));
				CShow.setself_field5(rs.getString("self_field5"));
				CShow.setself_field6(rs.getString("self_field6"));
				CShow.setself_field7(rs.getString("self_field7"));
				CShow.setself_field8(rs.getString("self_field8"));
				CShow.setstatus(rs.getInt("status"));
				CShow.setlanguage_ver(rs.getString("language_ver"));
				coll.add(CShow);
			}

			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog.error("Error in method selectsqlanalyse() of Custom,error:"
					+ ex.getMessage() + ",sql:" + sql);
			// System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
			return coll;
		}
	}

	public String getLaton() {
		String sqlNews = "";
		String laton = "";
		String sql = "select  longitude from public_cana_water pcw  where geo_longitude is null ";
		String sql2 = "update public_cana_water set geo_latitude=?,geo_longitude=? where longitude=?";
		int i = 0;
		con = DbConnectionManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				laton = laton + rs.getString("longitude");
				laton = laton + ",";
				i++;
			}
			rs.close();
			pstmt.close();
			if (laton.length() > 0) {
				String latStr = laton.substring(0, laton.length() - 1);
				String realLaton = getRealLaton(latStr, i);
				pstmt = con.prepareStatement(sql2,
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				if (realLaton != null && !realLaton.equals("")) {
					String[] lStr = realLaton.split(";");
					String[] mapStr = laton.split(",");
					for (int j = 0; j < lStr.length; j++) {
						String[] lonStr = lStr[j].split(",");
						pstmt.setString(1, lonStr[0]);
						pstmt.setString(2, lonStr[1]);
						pstmt.setString(3, mapStr[j]);
						pstmt.addBatch();
					}
					int[] results = pstmt.executeBatch();
				}
				pstmt.close();
			}
			con.close();
		} catch (Exception ex) {
			crmLog.error("Error in method getLaton() of Custom,error:"
					+ ex.getMessage() + ",sql:" + sql);
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
		}
		return sqlNews;
	}

	public String getRealLaton(String laton, int pointnum) {
		String gLaton = "";
		try {
			MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
			HttpClient httpclient = new HttpClient(connectionManager);
			String url = "http://geocode.mapbar.com/decode/getLatLon.jsp";
			// System.out.println(url);
			PostMethod postMethod = new PostMethod(url);
			// PostMethod postMethod = new
			// PostMethod("http://mapx.mapbar.com/search/getPoiByKeyword.jsp");
			NameValuePair[] data = { new NameValuePair("latlon", laton),
					new NameValuePair("customer", "1") };
			postMethod.setRequestBody(data);
			int statusCode = httpclient.executeMethod(postMethod);
			byte[] responseBody = postMethod.getResponseBody();
			String code = postMethod.getResponseCharSet();
			String context = new String(responseBody, code);
			postMethod.releaseConnection();
			int request_i = pointnum;// ������
			int i = 0;
			String xa[] = context.split("<lat>");
			// if(xa.length!=request_i+1){System.out.println("��Ŀ���ԣ�");}
			String xv[] = new String[xa.length];
			for (i = 1; i < xa.length; i++) {
				String xb[] = xa[i].split("</lat>");
				xv[i] = xb[0];
			}
			String xc[] = context.split("<lon>");
			// if(xa.length!=request_i+1){System.out.println("��Ŀ���ԣ�");}
			String xx[] = new String[xc.length];
			for (i = 1; i < xc.length; i++) {
				String xb[] = xc[i].split("</lon>");
				xx[i] = xb[0];
			}

			for (i = 1; i < xv.length; i++) {
				gLaton = gLaton + xv[i];
				gLaton = gLaton + ",";
				gLaton = gLaton + xx[i];
				gLaton = gLaton + ";";
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return gLaton;

	}

	public String httpProcess(String laton, int pointnum) {
		String gLaton = "";
		try {
			MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
			HttpClient httpclient = new HttpClient(connectionManager);
			String url = "http://geocode.mapbar.com/decode/getEncode.jsp?latlon="
					+ laton + "&customer=1";
			// System.out.println(url);
			GetMethod getMethod = new GetMethod(url);
			// PostMethod postMethod = new
			// PostMethod("http://mapx.mapbar.com/search/getPoiByKeyword.jsp");
			int statusCode = httpclient.executeMethod(getMethod);
			byte[] responseBody = getMethod.getResponseBody();
			String code = getMethod.getResponseCharSet();
			String context = new String(responseBody, code);
			getMethod.releaseConnection();
			int request_i = pointnum;// ������
			int i = 0;
			String xa[] = context.split("<latlon>");
			// if(xa.length!=request_i+1){System.out.println("��Ŀ���ԣ�");}
			String xv[] = new String[xa.length];
			for (i = 1; i < xa.length; i++) {
				String xb[] = xa[i].split("</latlon>");
				xv[i] = xb[0];
			}
			for (i = 1; i < xv.length; i++) {
				gLaton = gLaton + xv[i];
				gLaton = gLaton + ",";
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return gLaton;

		// String cenStr[]=context.split("<center>");
		// String rcStr[]=cenStr[1].split("</center>");
		// String sStr[]=context.split("<scale>");
		// cStr=rcStr[0];
		// String snum[]=sStr[1].split("</scale>");
		// scale=Integer.parseInt(snum[0]);
	}

	public String httpRealProcess(String laton, int pointnum) {
		String gLaton = "";
		try {
			MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
			HttpClient httpclient = new HttpClient(connectionManager);
			String url = "http://geocode.mapbar.com/decode/getEncode.jsp?latlon="
					+ laton + "&customer=2";
			// System.out.println(url);
			GetMethod getMethod = new GetMethod(url);
			// PostMethod postMethod = new
			// PostMethod("http://mapx.mapbar.com/search/getPoiByKeyword.jsp");
			int statusCode = httpclient.executeMethod(getMethod);
			byte[] responseBody = getMethod.getResponseBody();
			String code = getMethod.getResponseCharSet();
			String context = new String(responseBody, code);
			getMethod.releaseConnection();
			int request_i = pointnum;// ������
			int i = 0;
			String xa[] = context.split("<latlon>");
			// if(xa.length!=request_i+1){System.out.println("��Ŀ���ԣ�");}
			String xv[] = new String[xa.length];
			for (i = 1; i < xa.length; i++) {
				String xb[] = xa[i].split("</latlon>");
				xv[i] = xb[0];
			}
			for (i = 1; i < xv.length; i++) {
				gLaton = gLaton + xv[i];
				gLaton = gLaton + ",";
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return gLaton;

		// String cenStr[]=context.split("<center>");
		// String rcStr[]=cenStr[1].split("</center>");
		// String sStr[]=context.split("<scale>");
		// cStr=rcStr[0];
		// String snum[]=sStr[1].split("</scale>");
		// scale=Integer.parseInt(snum[0]);
	}

	public String searchAllPoint(String laton) {
		String searchLaton = "";
		String lt[] = laton.split(",");
		double xposition = Double.parseDouble(lt[0]);
		double yposition = Double.parseDouble(lt[1]);
		String sql = "";
		return searchLaton;
	}

	public ArrayList searchLatestPoint(String laton) {
		ArrayList ls = new ArrayList();
		String searchList = "";
		String searchLaton = "";
		String lt[] = laton.split(",");
		double xposition = Double.parseDouble(lt[0]);
		double yposition = Double.parseDouble(lt[1]);
		String sql = "select distinct top 5 car_number,driver_name,cgc.deviceid as deviceid,car_type,submit_time,dbo.fnGetGPSPOPDistance(?,?,cast(longitude as decimal(18,6)),cast(latitude as decimal(18,6))) as distance,longitude,latitude,speed "
				+ " from cana_car_info cci "
				+ " inner join cana_gps_car cgc on cci.deviceid=cgc.deviceid "
				+ "inner join( "
				+ " select deviceid,max(submit_time) as latest_time "
				+ " from cana_gps_car "
				+ " group by deviceid) cg1 on cg1.latest_time=cgc.submit_time and cg1.deviceid=cgc.deviceid  order by distance asc";
		try {
			con = DbConnectionManager.getGpsDbConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setDouble(1, xposition);
			pstmt.setDouble(2, yposition);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				searchList = searchList + rs.getString("car_number");
				searchList = searchList + ",";
				searchList = searchList + rs.getString("driver_name");
				searchList = searchList + ",";
				searchList = searchList + rs.getString("deviceid");
				searchList = searchList + ",";
				searchList = searchList + rs.getString("car_type");
				searchList = searchList + ",";
				String timeFormat = rs.getString("submit_time").substring(0, 4)
						+ "��" + rs.getString("submit_time").substring(4, 6)
						+ "��" + rs.getString("submit_time").substring(6, 8)
						+ "��" + rs.getString("submit_time").substring(8, 10)
						+ "ʱ" + rs.getString("submit_time").substring(10, 12)
						+ "��" + rs.getString("submit_time").substring(12, 14)
						+ "��";
				searchList = searchList + timeFormat;
				searchList = searchList + ",";
				searchList = searchList + rs.getString("distance");
				searchList = searchList + ",";
				searchList = searchList + rs.getString("speed");
				searchList = searchList + ";";
				searchLaton = searchLaton + rs.getString("latitude");
				searchLaton = searchLaton + ",";
				searchLaton = searchLaton + rs.getString("longitude");
				searchLaton = searchLaton + ";";

			}
			ls.add(searchList);
			searchLaton = searchLaton + yposition + "," + xposition + ";";
			ls.add(searchLaton);
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			crmLog.error("Error in method searchLatestPoint() of Custom,error:"
					+ e.getMessage() + ",sql:" + sql);
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}

		}
		return ls;
	}

	public ArrayList searchgps(String car_number, String deviceid,
			String tj_where) {
		String latonStr = "";
		String carGps = "";
		int pointnum = 0;
		String now = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar
				.getInstance().getTime());
		ArrayList ls = new ArrayList();
		String sql = "";
		sql = "select cci.car_number,car_order,car_id,c.deviceid as deviceid,car_type,submit_time,latitude,longitude,map_laton,speed,driver_name,mobile_number "
				+ "  from cana_car_info cci "
				+ " inner join ( "
				+ " select distinct cgc.deviceid as deviceid,submit_time,latitude,longitude,map_laton,speed "
                +" from cana_gps_car cgc "
				+ " inner join( "
				+ " select deviceid,max(submit_time) as latest_time "
				+ " from cana_gps_car where status in ('06','07')  group by deviceid) cg1 on cg1.latest_time=cgc.submit_time and cg1.deviceid=cgc.deviceid "
				+ " where 1=1  and status in ('06','07') ) c on c.deviceid=cci.deviceid ";
		// sql="select distinct car_number,car_id,cgc.deviceid as
		// deviceid,car_type,submit_time,latitude,longitude,map_laton,speed,driver_name,mobile_number
		// "
		// +" from cana_car_info cci "
		// +" inner join cana_gps_car cgc on cci.deviceid=cgc.deviceid "
		// +"inner join( "
		// +" select deviceid,max(submit_time) as latest_time "
		// + " from cana_gps_car "
		// +" group by deviceid) cg1 on cg1.latest_time=cgc.submit_time and
		// cg1.deviceid=cgc.deviceid where 1=1 and status='06' ";
		if (!car_number.equals("")) {
			sql = sql + " and  car_number='" + car_number + "'";
		}
		if (!deviceid.equals("")) {
			sql = sql + " and  cgc.deviceid='" + deviceid + "'";
		}
		if (!tj_where.equals("")) {
			sql = sql + " and  cci.car_id in " + tj_where;
		}
		sql = sql + "order by cci.car_order ";
		try {
			con = DbConnectionManager.getGpsDbConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pointnum++;
				latonStr = latonStr + rs.getString("latitude");
				latonStr = latonStr + ",";
				latonStr = latonStr + rs.getString("longitude");
				latonStr = latonStr + ";";
				carGps = carGps + rs.getString("car_number");
				carGps = carGps + ",";
				carGps = carGps + rs.getString("car_id");
				carGps = carGps + ",";
				carGps = carGps + rs.getString("deviceid");
				carGps = carGps + ",";
				carGps = carGps + rs.getString("car_type");
				carGps = carGps + ",";
				String daytime = rs.getString("submit_time").substring(0, 4)
						+ "��" + rs.getString("submit_time").substring(4, 6)
						+ "��" + rs.getString("submit_time").substring(6, 8)
						+ "��" + rs.getString("submit_time").substring(8, 10)
						+ "ʱ" + rs.getString("submit_time").substring(10, 12)
						+ "��" + rs.getString("submit_time").substring(12, 14)
						+ "��";
				carGps = carGps + daytime;
				carGps = carGps + ",";
				if (Integer.parseInt(now.substring(6, 8)) > Integer.parseInt(rs
						.getString("submit_time").substring(6, 8))
						|| Integer.parseInt(now.substring(8))
								- Integer.parseInt(rs.getString("submit_time")
										.substring(8)) > 1000) {
					carGps = carGps + "0";
				} else {
					carGps = carGps + rs.getString("speed");
				}
				carGps = carGps + ",";
				carGps = carGps + rs.getString("longitude");
				carGps = carGps + ",";
				carGps = carGps + rs.getString("latitude");
				carGps = carGps + ",";
				carGps = carGps + rs.getString("driver_name");
				carGps = carGps + ",";
				carGps = carGps + rs.getString("mobile_number");
				carGps = carGps + ";";
			}
			rs.close();
			pstmt.close();
			con.close();
			ls.add(latonStr);
			ls.add(carGps);
		} catch (Exception e) {
			crmLog.error("Error in method searchgps() of Custom,error:"
					+ e.getMessage() + ",sql:" + sql);
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
		}
		return ls;
	}

	public String timeDelay(String time1, String time2) {
		String timeDiffer = "";
		String timeHour = "";
		String timeMinute = "";
		String timeSecond = "";
		int start1 = Integer.parseInt(time2.substring(0, 2));
		int start2 = Integer.parseInt(time2.substring(2, 4));
		int start3 = Integer.parseInt(time2.substring(4, 6));
		int end1 = Integer.parseInt(time1.substring(0, 2));
		int end2 = Integer.parseInt(time1.substring(2, 4));
		int end3 = Integer.parseInt(time1.substring(4, 6));
		if (end3 < start3) {
			end2 = end2 - 1;
			end3 = 60 + end3 - start3;
		} else {
			end3 = end3 - start3;
		}
		if (end2 < start2) {
			end1 = end1 - 1;
			end2 = 60 + end2 - start2;
		} else {
			end2 = end2 - start2;
		}
		if (end1 - start1 > 0) {
			timeHour = end1 - start1 + "Сʱ";
		}
		if (end2 > 10) {
			timeMinute = end2 + "��";
		} else {
			timeMinute = "";
		}
		if (end3 > 0) {
			timeSecond = end3 + "��";
		}
		if (timeMinute.equals("")) {
			timeDiffer = "";
		} else {
			timeDiffer = timeHour + timeMinute + timeSecond;
		}
		return timeDiffer;
	}

	public String addgps(GpsShow CShow) {
		String flag = "fail";
		String sql = "insert into cana_gps_car(deviceid,longitude,latitude,submit_time,map_laton,status,isStart,speed) values(?,?,?,?,?,?,?,?)";
		try {
			con = DbConnectionManager.getGpsDbConnection();
			pstmt = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, CShow.getDeviceId());
			pstmt.setString(2, CShow.getLongitude());
			pstmt.setString(3, CShow.getLatitude());
			pstmt.setString(4, CShow.getSubmit_time());
			pstmt.setString(5, CShow.getMap_laton());
			pstmt.setString(6, CShow.getStatus());
			pstmt.setString(7, CShow.getIsStart());
			pstmt.setString(8, CShow.getSpeed());
			int result = pstmt.executeUpdate();
			if (result > 0) {
				flag = "sucess";
			} else {
				flag = "fail";
			}
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
			crmLog.info("success in method addgps() of Custom ,sql=insert into cana_gps_car(deviceid,longitude,latitude,submit_time,map_laton,status,isStart,speed) values("
							+ CShow.getDeviceId()
							+ ","
							+ CShow.getLongitude()
							+ ","
							+ CShow.getLatitude()
							+ ","
							+ CShow.getSubmit_time()
							+ ","
							+ CShow.getMap_laton()
							+ ","
							+ CShow.getStatus()
							+ ","
							+ CShow.getIsStart()
							+ ","
							+ CShow.getSpeed()
							+ ")");
		} catch (Exception e) {
			crmLog.error("Error in method addgps() of Custom,error:"+ e.getMessage() + ",sql:" + sql);
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
		}
		return flag;
	}

	public List select_map(String custom_id) {
		List coll = new ArrayList();
		String sql = "";
		try {
			sql = "SELECT * FROM public_cana_water where valid=1 and customid=?";
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, custom_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Water_map_show CShow = new Water_map_show();
				CShow.setLongitude(rs.getString("longitude"));
				CShow.setName(rs.getString("name"));
				CShow.setAddress(rs.getString("address"));
				CShow.setTown(rs.getString("town"));
				CShow.setRoad(rs.getString("road"));
				CShow.setNong(rs.getString("nong"));
				CShow.setNum(rs.getString("num"));
				CShow.setCjzone(rs.getString("cjzone"));
				CShow.setCjdate(rs.getString("cjdate"));
				CShow.setNtu(rs.getString("ntu"));
				CShow.setColor(rs.getString("color"));
				CShow.setPh(rs.getString("ph"));
				CShow.setCl(rs.getString("cl"));
				CShow.setCod(rs.getString("cod"));
				CShow.setApplytime(rs.getString("apply_time"));
				CShow.setFiltertype(rs.getString("filter_type"));
				CShow.setUsedtime(rs.getString("water_used"));
				CShow.setFntu(rs.getString("filter_ntu"));
				CShow.setFcolor(rs.getString("filter_color"));
				CShow.setFph(rs.getString("filter_ph"));
				CShow.setFcl(rs.getString("filter_cl"));
				CShow.setFcod(rs.getString("filter_cod"));
				CShow.setCustomid(rs.getString("customid"));
				coll.add(CShow);
			}

			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog.error("Error in method select() of Custom,error:"
					+ ex.getMessage() + ",sql:" + sql);
			// System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
			return coll;
		}
	}

	public String selectmap(String customid) {
		String result = "0";
		String sql = "select count(*) as cou from public_cana_water where valid=1 and customid=?";
		try {
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, customid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getInt("cou") > 0) {
					result = "1";
				}
			}
			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (Exception e) {
			crmLog.error("Error in method selectmap() of Custom,error:"
					+ e.getMessage() + ",sql:" + sql);
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
		}

		return result;
	}

	public String updatemap(Water_map_show CShow) {
		String sqlNews = "fail";
		String sql = "";
		String sql4log = "";
		try {
			sql = "update public_cana_water set longitude=?,name=?,address=?,town=?,road=?,nong=?,num=?,cjzone=?,cjdate=?,ntu=?,color=?,ph=?,cl=?,cod=?,district=?,building=?,recorder=?,water_type=?,recorder_time=?,filter_ntu=?,filter_color=?,filter_ph=?,filter_cl=?,filter_cod=?,apply_time=?,filter_type=?,water_used=? where customid=?";
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, StringUtils.toChinese(CShow.getLongitude()));
			pstmt.setString(2, StringUtils.toChinese(CShow.getName()));
			pstmt.setString(3, StringUtils.toChinese(CShow.getAddress()));
			pstmt.setString(4, StringUtils.toChinese(CShow.getTown()));
			pstmt.setString(5, StringUtils.toChinese(CShow.getRoad()));
			pstmt.setString(6, StringUtils.toChinese(CShow.getNong()));
			pstmt.setString(7, StringUtils.toChinese(CShow.getNum()));
			pstmt.setString(8, StringUtils.toChinese(CShow.getCjzone()));
			pstmt.setString(9, StringUtils.toChinese(CShow.getCjdate()));
			pstmt.setString(10, CShow.getNtu());
			pstmt.setString(11, CShow.getColor());
			pstmt.setString(12, CShow.getPh());
			pstmt.setString(13, CShow.getCl());
			pstmt.setString(14, CShow.getCod());
			pstmt.setString(15, StringUtils.toChinese(CShow.getDistrict()));
			pstmt.setString(16, StringUtils.toChinese(CShow.getBuilding()));
			pstmt.setString(17, CShow.getRecorder());
			pstmt.setString(18, StringUtils.toChinese(CShow.getType()));
			pstmt.setString(19, CShow.getRecorder_time());
			pstmt.setString(20, CShow.getFntu());
			pstmt.setString(21, CShow.getFcolor());
			pstmt.setString(22, CShow.getFph());
			pstmt.setString(23, CShow.getFcl());
			pstmt.setString(24, CShow.getFcod());
			pstmt.setString(25, StringUtils.toChinese(CShow.getApplytime()));
			pstmt.setString(26, CShow.getFiltertype());
			pstmt.setString(27, StringUtils.toChinese(CShow.getUsedtime()));
			pstmt.setString(28, CShow.getCustomid());
			int result = pstmt.executeUpdate();
			if (result > 0) {
				sqlNews = "sucess";
			} else {
				sqlNews = "fail";
			}
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;

		} catch (Exception ex) {
			crmLog.error("Error in method updatemap() of Custom,error:"
					+ ex.getMessage() + ",sql:" + sql);
			flag = false;
			// ex.printStackTrace();
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {
			}
		}
		return sqlNews;
	}

	public String addmap(Water_map_show CShow) {
		String sqlNews = "fail";
		String sql = "";
		String sql4log = "";
		try {
			sql = "insert into public_cana_water(longitude,name,address,town,road,nong,num,cjzone,cjdate,ntu,color,ph,cl,cod,valid,customid,district,building,recorder,water_type,recorder_time,filter_ntu,filter_color,filter_ph,filter_cl,filter_cod,apply_time,filter_type,water_used)  values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, StringUtils.toChinese(CShow.getLongitude()));
			pstmt.setString(2, StringUtils.toChinese(CShow.getName()));
			pstmt.setString(3, StringUtils.toChinese(CShow.getAddress()));
			pstmt.setString(4, StringUtils.toChinese(CShow.getTown()));
			pstmt.setString(5, StringUtils.toChinese(CShow.getRoad()));
			pstmt.setString(6, StringUtils.toChinese(CShow.getNong()));
			pstmt.setString(7, StringUtils.toChinese(CShow.getNum()));
			pstmt.setString(8, StringUtils.toChinese(CShow.getCjzone()));
			pstmt.setString(9, StringUtils.toChinese(CShow.getCjdate()));
			pstmt.setString(10, CShow.getNtu());
			pstmt.setString(11, CShow.getColor());
			pstmt.setString(12, CShow.getPh());
			pstmt.setString(13, CShow.getCl());
			pstmt.setString(14, CShow.getCod());
			pstmt.setString(15, "1");
			pstmt.setString(16, CShow.getCustomid());
			pstmt.setString(17, StringUtils.toChinese(CShow.getDistrict()));
			pstmt.setString(18, StringUtils.toChinese(CShow.getBuilding()));
			pstmt.setString(19, CShow.getRecorder());
			if (CShow.getType() != null && CShow.getType().equals("personal")) {
				pstmt.setString(20, "�����û�");
			} else {
				pstmt.setString(20, CShow.getType());
			}

			pstmt.setString(21, CShow.getRecorder_time());
			pstmt.setString(22, CShow.getFntu());
			pstmt.setString(23, CShow.getFcolor());
			pstmt.setString(24, CShow.getFph());
			pstmt.setString(25, CShow.getFcl());
			pstmt.setString(26, CShow.getFcod());
			pstmt.setString(27, StringUtils.toChinese(CShow.getApplytime()));
			pstmt.setString(28, CShow.getFiltertype());
			pstmt.setString(29, StringUtils.toChinese(CShow.getUsedtime()));
			int result = pstmt.executeUpdate();
			if (result > 0) {
				sqlNews = "sucess";
			} else {
				sqlNews = "fail";
			}
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;

		} catch (Exception ex) {
			crmLog.error("Error in method addmap() of Custom,error:"
					+ ex.getMessage() + ",sql:" + sql);
			flag = false;
			// ex.printStackTrace();
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
		}
		return sqlNews;
	}

	public String selectsql(int st, String language_v, String table_head,
			String tj_name1, String tj_name2, String tj_name3, String tj_name4,
			String tj_name5, String tj_v1, String tj_v2, String tj_v3,
			String tj_v4, String tj_v5, String tj_v11, String tj_v21,
			String tj_v31, String tj_v41, String tj_v51, String tj_where,
			String taxis_name, String taxis_v, String status) {
		int size = 0;
		int sizepa = 0;
		String sql = "";
		try {
			sql = "SELECT COUNT(*) FROM " + table_head
					+ "custom where language_ver=?";
			String where = " ";
			if ((!tj_v1.equals("")) && (tj_v1 != null)) {
				if (tj_v11.equals("0")) {
					String tj_v1_per = "";
					for (int i = 0; i < tj_v1.length(); i++) {
						tj_v1_per += tj_v1.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name1 + " like '%" + tj_v1_per
							+ "%'";
				} else if (tj_v11.equals("1")) {
					where = where + " and " + tj_name1 + " like '" + tj_v1
							+ "%'";
				} else if (tj_v11.equals("2")) {
					where = where + " and " + tj_name1 + " like '%" + tj_v1
							+ "%'";
				} else if (tj_v11.equals("3")) {
					where = where + " and " + tj_name1 + " not like '%" + tj_v1
							+ "%'";
				} else {
					where = where + " and " + tj_name1 + "='" + tj_v1 + "'";
				}
			}
			if ((!tj_v2.equals("")) && (tj_v2 != null)) {
				if (tj_v21.equals("0")) {
					String tj_v2_per = "";
					for (int i = 0; i < tj_v2.length(); i++) {
						tj_v2_per += tj_v2.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name2 + " like '%" + tj_v2_per
							+ "%'";
				} else if (tj_v21.equals("1")) {
					where = where + " and " + tj_name2 + " like '" + tj_v2
							+ "%'";
				} else if (tj_v21.equals("2")) {
					where = where + " and " + tj_name2 + " like '%" + tj_v2
							+ "%'";
				} else if (tj_v21.equals("3")) {
					where = where + " and " + tj_name2 + " not like '%" + tj_v2
							+ "%'";
				} else {
					where = where + " and " + tj_name2 + "='" + tj_v2 + "'";
				}
			}
			if ((!tj_v3.equals("")) && (tj_v3 != null)) {
				if (tj_v31.equals("0")) {
					String tj_v3_per = "";
					for (int i = 0; i < tj_v3.length(); i++) {
						tj_v3_per += tj_v3.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name3 + " like '%" + tj_v3_per
							+ "%'";
				} else if (tj_v31.equals("1")) {
					where = where + " and " + tj_name3 + " like '" + tj_v3
							+ "%'";
				} else if (tj_v31.equals("2")) {
					where = where + " and " + tj_name3 + " like '%" + tj_v3
							+ "%'";
				} else if (tj_v31.equals("3")) {
					where = where + " and " + tj_name3 + " not like '%" + tj_v3
							+ "%'";
				} else {
					where = where + " and " + tj_name3 + "='" + tj_v3 + "'";
				}
			}
			if ((!tj_v4.equals("")) && (tj_v4 != null)) {
				if (tj_v41.equals("0")) {
					String tj_v4_per = "";
					for (int i = 0; i < tj_v4.length(); i++) {
						tj_v4_per += tj_v4.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name4 + " like '%" + tj_v4_per
							+ "%'";
				} else if (tj_v41.equals("1")) {
					where = where + " and " + tj_name4 + " like '" + tj_v4
							+ "%'";
				} else if (tj_v41.equals("2")) {
					where = where + " and " + tj_name4 + " like '%" + tj_v4
							+ "%'";
				} else if (tj_v41.equals("3")) {
					where = where + " and " + tj_name4 + " not like '%" + tj_v4
							+ "%'";
				} else {
					where = where + " and " + tj_name4 + "='" + tj_v4 + "'";
				}
			}
			if ((!tj_v5.equals("")) && (tj_v5 != null)) {
				if (tj_v51.equals("0")) {
					String tj_v5_per = "";
					for (int i = 0; i < tj_v5.length(); i++) {
						tj_v5_per += tj_v5.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name5 + " like '%" + tj_v5_per
							+ "%'";
				} else if (tj_v51.equals("1")) {
					where = where + " and " + tj_name5 + " like '" + tj_v5
							+ "%'";
				} else if (tj_v51.equals("2")) {
					where = where + " and " + tj_name5 + " like '%" + tj_v5
							+ "%'";
				} else if (tj_v51.equals("3")) {
					where = where + " and " + tj_name5 + " not like '%" + tj_v5
							+ "%'";
				} else {
					where = where + " and " + tj_name5 + "='" + tj_v5 + "'";
				}
			}
			if ((!status.equals("")) && (status != null)) {
				where = where + " and status='" + status + "'";
			}
			if ((!tj_where.equals("")) && (tj_where != null)) {
				where = where + tj_where;
			}
			sql = sql + where;
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, language_v);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				size = rs.getInt(1);
				sizepa = (int) size / st;
				if (size % st > 0) {
					sizepa = sizepa + 1;
				}
			}

			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog.error("Error in method selectsql() of Custom,error:"
					+ ex.getMessage() + ",sql:" + sql);
			// System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
			return sizepa + "+" + size;
		}
	}

	public Collection select(int step, int page, String language_v,
			String table_head, String tj_name1, String tj_name2,
			String tj_name3, String tj_name4, String tj_name5, String tj_v1,
			String tj_v2, String tj_v3, String tj_v4, String tj_v5,
			String tj_v11, String tj_v21, String tj_v31, String tj_v41,
			String tj_v51, String tj_where, String taxis_name, String taxis_v,
			String status) {
		Collection coll = new ArrayList();
		int tip = step * (page - 1);
		String sql = "";
		try {
			sql = "SELECT * FROM " + table_head + "custom where language_ver=?";
			String where = " ";
			if ((!tj_v1.equals("")) && (tj_v1 != null)) {
				if (tj_v11.equals("0")) {
					String tj_v1_per = "";
					for (int i = 0; i < tj_v1.length(); i++) {
						tj_v1_per += tj_v1.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name1 + " like '%" + tj_v1_per
							+ "%'";
				} else if (tj_v11.equals("1")) {
					where = where + " and " + tj_name1 + " like '" + tj_v1
							+ "%'";
				} else if (tj_v11.equals("2")) {
					where = where + " and " + tj_name1 + " like '%" + tj_v1
							+ "%'";
				} else if (tj_v11.equals("3")) {
					where = where + " and " + tj_name1 + " not like '%" + tj_v1
							+ "%'";
				} else {
					where = where + " and " + tj_name1 + "='" + tj_v1 + "'";
				}
			}
			if ((!tj_v2.equals("")) && (tj_v2 != null)) {
				if (tj_v21.equals("0")) {
					String tj_v2_per = "";
					for (int i = 0; i < tj_v2.length(); i++) {
						tj_v2_per += tj_v2.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name2 + " like '%" + tj_v2_per
							+ "%'";
				} else if (tj_v21.equals("1")) {
					where = where + " and " + tj_name2 + " like '" + tj_v2
							+ "%'";
				} else if (tj_v21.equals("2")) {
					where = where + " and " + tj_name2 + " like '%" + tj_v2
							+ "%'";
				} else if (tj_v21.equals("3")) {
					where = where + " and " + tj_name2 + " not like '%" + tj_v2
							+ "%'";
				} else {
					where = where + " and " + tj_name2 + "='" + tj_v2 + "'";
				}
			}
			if ((!tj_v3.equals("")) && (tj_v3 != null)) {
				if (tj_v31.equals("0")) {
					String tj_v3_per = "";
					for (int i = 0; i < tj_v3.length(); i++) {
						tj_v3_per += tj_v3.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name3 + " like '%" + tj_v3_per
							+ "%'";
				} else if (tj_v31.equals("1")) {
					where = where + " and " + tj_name3 + " like '" + tj_v3
							+ "%'";
				} else if (tj_v31.equals("2")) {
					where = where + " and " + tj_name3 + " like '%" + tj_v3
							+ "%'";
				} else if (tj_v31.equals("3")) {
					where = where + " and " + tj_name3 + " not like '%" + tj_v3
							+ "%'";
				} else {
					where = where + " and " + tj_name3 + "='" + tj_v3 + "'";
				}
			}
			if ((!tj_v4.equals("")) && (tj_v4 != null)) {
				if (tj_v41.equals("0")) {
					String tj_v4_per = "";
					for (int i = 0; i < tj_v4.length(); i++) {
						tj_v4_per += tj_v4.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name4 + " like '%" + tj_v4_per
							+ "%'";
				} else if (tj_v41.equals("1")) {
					where = where + " and " + tj_name4 + " like '" + tj_v4
							+ "%'";
				} else if (tj_v41.equals("2")) {
					where = where + " and " + tj_name4 + " like '%" + tj_v4
							+ "%'";
				} else if (tj_v41.equals("3")) {
					where = where + " and " + tj_name4 + " not like '%" + tj_v4
							+ "%'";
				} else {
					where = where + " and " + tj_name4 + "='" + tj_v4 + "'";
				}
			}
			if ((!tj_v5.equals("")) && (tj_v5 != null)) {
				if (tj_v51.equals("0")) {
					String tj_v5_per = "";
					for (int i = 0; i < tj_v5.length(); i++) {
						tj_v5_per += tj_v5.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name5 + " like '%" + tj_v5_per
							+ "%'";
				} else if (tj_v51.equals("1")) {
					where = where + " and " + tj_name5 + " like '" + tj_v5
							+ "%'";
				} else if (tj_v51.equals("2")) {
					where = where + " and " + tj_name5 + " like '%" + tj_v5
							+ "%'";
				} else if (tj_v51.equals("3")) {
					where = where + " and " + tj_name5 + " not like '%" + tj_v5
							+ "%'";
				} else {
					where = where + " and " + tj_name5 + "='" + tj_v5 + "'";
				}
			}
			if ((!status.equals("")) && (status != null)) {
				where = where + " and status='" + status + "'";
			}
			if ((!tj_where.equals("")) && (tj_where != null)) {
				where = where + tj_where;
			}
			String orderby = " order by id desc";
			if ((!taxis_v.equals("")) && (taxis_v != null)) {
				orderby = " order by " + taxis_name + " " + taxis_v;
			}
			sql = sql + where + orderby;
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, language_v);
			rs = pstmt.executeQuery();
			if (tip <= 0) {
				rs.beforeFirst();
			} else if (!rs.absolute(tip)) {
				rs.beforeFirst();
			}
			for (int i = 1; rs.next() && i <= step; i++) {
				CustomShow CShow = new CustomShow();
				CShow.setid(rs.getInt("id"));
				CShow.setcustomid(rs.getInt("customid"));
				CShow.setserial_num(rs.getString("serial_num"));
				CShow.setname(rs.getString("name"));
				CShow.setname_pinyin(rs.getString("name_pinyin"));
				CShow.setarea1(rs.getString("area1"));
				CShow.setarea2(rs.getString("area2"));
				CShow.setarea3(rs.getString("area3"));
				CShow.setlinkman(rs.getString("linkman"));
				CShow.settel(rs.getString("tel"));
				CShow.setmobile(rs.getString("mobile"));
				CShow.setfax(rs.getString("fax"));
				CShow.setwebsite(rs.getString("website"));
				CShow.setemail(rs.getString("email"));
				CShow.setkind(rs.getString("kind"));
				CShow.setaddress(rs.getString("address"));
				CShow.setzip(rs.getInt("zip"));
				// CShow.setseller(rs.getInt("seller"));
				CShow.setseller(rs.getString("seller"));
				CShow.setfiliale(rs.getString("filiale"));
				CShow.setshare(rs.getString("share"));
				CShow.sethotspot(rs.getString("hotspot"));
				CShow.sethotspot_explain(rs.getString("hotspot_explain"));
				CShow.setvalue_evaluate(rs.getString("value_evaluate"));
				CShow.setcredit(rs.getString("credit"));
				CShow.setindustry(rs.getString("industry"));
				CShow.setrelation(rs.getString("relation"));
				CShow.sethuman_size(rs.getString("human_size"));
				CShow.setresource(rs.getString("resource"));
				CShow.setremark(rs.getString("remark"));
				CShow.setrecorder(rs.getInt("recorder"));
				CShow.setrecorder_time(rs.getString("recorder_time"));
				CShow.setupdater(rs.getInt("updater"));
				CShow.setupdate_time(rs.getString("update_time"));
				CShow.setself_field1(rs.getString("self_field1"));
				CShow.setself_field2(rs.getString("self_field2"));
				CShow.setself_field3(rs.getString("self_field3"));
				CShow.setself_field4(rs.getString("self_field4"));
				CShow.setself_field5(rs.getString("self_field5"));
				CShow.setself_field6(rs.getString("self_field6"));
				CShow.setself_field7(rs.getString("self_field7"));
				CShow.setself_field8(rs.getString("self_field8"));
				CShow.setstatus(rs.getInt("status"));
				CShow.setlanguage_ver(rs.getString("language_ver"));
				CShow.setcomplaints_remark(rs.getString("complaints_remark"));
				CShow.setmail_date(rs.getString("mail_date"));
				CShow.settel2(rs.getString("tel2"));
				CShow.setmobile2(rs.getString("mobile2"));
				CShow.setmobile3(rs.getString("mobile3"));
				CShow.setself_field9(rs.getString("self_field9"));
				CShow.setself_field10(rs.getString("self_field10"));
				CShow.setself_field11(rs.getString("self_field11"));
				CShow.setself_field12(rs.getString("self_field12"));
				CShow.setself_field13(rs.getString("self_field13"));
				CShow.setself_field14(rs.getString("self_field14"));
				CShow.setself_field15(rs.getString("self_field15"));
				CShow.setself_field16(rs.getString("self_field16"));
				CShow.setself_field17(rs.getString("self_field17"));
				CShow.setself_field18(rs.getString("self_field18"));
				CShow.setself_field19(rs.getString("self_field19"));
				CShow.setself_field20(rs.getString("self_field20"));
				CShow.setself_field21(rs.getString("self_field21"));
				CShow.setself_field22(rs.getString("self_field22"));
				CShow.setself_field23(rs.getString("self_field23"));
				CShow.setself_field24(rs.getString("self_field24"));
				CShow.setself_field25(rs.getString("self_field25"));

				CShow.setself_field26(rs.getString("self_field26"));
				CShow.setself_field27(rs.getString("self_field27"));
				CShow.setself_field28(rs.getString("self_field28"));
				CShow.setself_field29(rs.getString("self_field29"));
				CShow.setself_field30(rs.getString("self_field30"));
				CShow.setself_field31(rs.getString("self_field31"));
				CShow.setself_field32(rs.getString("self_field32"));
				CShow.setself_field33(rs.getString("self_field33"));
				CShow.setself_field34(rs.getString("self_field34"));
				CShow.setself_field35(rs.getString("self_field35"));
				CShow.setself_field36(rs.getString("self_field36"));
				CShow.setself_field37(rs.getString("self_field37"));
				CShow.setself_field38(rs.getString("self_field38"));
				
				CShow.setself_field39(rs.getString("self_field39"));
				CShow.setself_field40(rs.getString("self_field40"));
				CShow.setself_field41(rs.getString("self_field41"));
				CShow.setself_field42(rs.getString("self_field42"));
				CShow.setself_field43(rs.getString("self_field43"));
				
				CShow.setself_field44(rs.getInt("self_field44"));
				CShow.setself_field45(rs.getString("self_field45"));
				CShow.setself_field46(rs.getString("self_field46"));
				CShow.setself_field47(rs.getString("self_field47"));
				CShow.setself_field48(rs.getString("self_field48"));
				CShow.setself_field49(rs.getString("self_field49"));
				CShow.setself_field50(rs.getString("self_field50"));
				CShow.setself_field51(rs.getString("self_field51"));
				CShow.setself_field52(rs.getString("self_field52"));
				CShow.setself_field53(rs.getString("self_field53"));
				CShow.setself_field54(rs.getString("self_field54"));
				CShow.setself_field55(rs.getString("self_field55"));
				CShow.setself_field56(rs.getString("self_field56"));
				CShow.setself_field57(rs.getString("self_field57"));
				CShow.setself_field58(rs.getString("self_field58"));
				CShow.setself_field59(rs.getString("self_field59"));
				CShow.setself_field60(rs.getString("self_field60"));
				CShow.setself_field61(rs.getString("self_field61"));
				CShow.setself_field62(rs.getString("self_field62"));
				CShow.setself_field63(rs.getString("self_field63"));
				CShow.setself_field64(rs.getString("self_field64"));
				CShow.setself_field65(rs.getString("self_field65"));
				CShow.setself_field66(rs.getString("self_field66"));
				CShow.setself_field67(rs.getString("self_field67"));
				CShow.setself_field68(rs.getString("self_field68"));
				CShow.setself_field69(rs.getString("self_field69"));
				CShow.setself_field70(rs.getString("self_field70"));

				coll.add(CShow);
			}

			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog.error("Error in method select() of Custom,error:"
					+ ex.getMessage() + ",sql:" + sql);
			// System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
			return coll;
		}
	}

	public String selectsql_com_list(int st, String language_v,
			String table_head, String tj_name1, String tj_name2,
			String tj_name3, String tj_name4, String tj_name5, String tj_name6,
			String tj_v1, String tj_v2, String tj_v3, String tj_v4,
			String tj_v5, String tj_v6, String tj_v11, String tj_v21,
			String tj_v31, String tj_v41, String tj_v51, String tj_v61,
			String tj_where, String taxis_name, String taxis_v, String status,
			String dealers) {
		int size = 0;
		int sizepa = 0;
		String sql = "";
		try {
			String str_begindate = "";
			sql = "SELECT COUNT(distinct " + table_head
					+ "custom.customid) FROM " + table_head + "custom ";
			String where = " where " + table_head + "custom.id>0 ";
			String table_sub = "";
			if ((!tj_v1.equals("")) && (tj_v1 != null)) {
				String[] arr = StringUtils.arr(tj_name1, "+", "", 2);
				table_sub = "," + table_head + arr[0] + " ";
				where = " where " + table_head + "custom.customid="
						+ table_head + arr[0] + ".customid ";
				if (tj_v11.equals("0")) {
					String tj_v1_per = "";
					for (int i = 0; i < tj_v1.length(); i++) {
						tj_v1_per += tj_v1.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + " like '%" + tj_v1_per + "%'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + " like '%" + tj_v1_per + "%'";
					}
				} else if (tj_v11.equals("1")) {
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + " like '" + tj_v1 + "%'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + " like '" + tj_v1 + "%'";
					}
				} else if (tj_v11.equals("2")) {
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + " like '%" + tj_v1 + "%'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + " like '%" + tj_v1 + "%'";
					}
				} else if (tj_v11.equals("3")) {
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + " not like '%" + tj_v1 + "%'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + " not like '%" + tj_v1 + "%'";
					}
				} else {
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + "='" + tj_v1 + "'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + "='" + tj_v1 + "'";
					}
				}
			}
			if ((!tj_v2.equals("")) && (tj_v2 != null)) {
				if (tj_v21.equals("0")) {
					String tj_v2_per = "";
					for (int i = 0; i < tj_v2.length(); i++) {
						tj_v2_per += tj_v2.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name2
							+ " like '%" + tj_v2_per + "%'";
				} else if (tj_v21.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name2
							+ " like '" + tj_v2 + "%'";
				} else if (tj_v21.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name2
							+ " like '%" + tj_v2 + "%'";
				} else if (tj_v21.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name2
							+ " not like '%" + tj_v2 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name2
							+ "='" + tj_v2 + "'";
				}
			}
			if ((!tj_v3.equals("")) && (tj_v3 != null)) {
				if (tj_v31.equals("0")) {
					String tj_v3_per = "";
					for (int i = 0; i < tj_v3.length(); i++) {
						tj_v3_per += tj_v3.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name3
							+ " like '%" + tj_v3_per + "%'";
				} else if (tj_v31.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name3
							+ " like '" + tj_v3 + "%'";
				} else if (tj_v31.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name3
							+ " like '%" + tj_v3 + "%'";
				} else if (tj_v31.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name3
							+ " not like '%" + tj_v3 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name3
							+ "='" + tj_v3 + "'";
				}
			}
			if ((!tj_v4.equals("")) && (tj_v4 != null)) {
				if (tj_v41.equals("0")) {
					String tj_v4_per = "";
					for (int i = 0; i < tj_v4.length(); i++) {
						tj_v4_per += tj_v4.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name4
							+ " like '%" + tj_v4_per + "%'";
				} else if (tj_v41.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name4
							+ " like '" + tj_v4 + "%'";
				} else if (tj_v41.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name4
							+ " like '%" + tj_v4 + "%'";
				} else if (tj_v41.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name4
							+ " not like '%" + tj_v4 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name4
							+ "='" + tj_v4 + "'";
				}
			}
			if ((!tj_v5.equals("")) && (tj_v5 != null)) {
				if (tj_v51.equals("0")) {
					String tj_v5_per = "";
					for (int i = 0; i < tj_v5.length(); i++) {
						tj_v5_per += tj_v5.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name5
							+ " like '%" + tj_v5_per + "%'";
				} else if (tj_v51.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name5
							+ " like '" + tj_v5 + "%'";
				} else if (tj_v51.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name5
							+ " like '%" + tj_v5 + "%'";
				} else if (tj_v51.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name5
							+ " not like '%" + tj_v5 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name5
							+ "='" + tj_v5 + "'";
				}
			}
			if ((!status.equals("")) && (status != null)) {
				where = where + " and " + table_head + "custom.status='"
						+ status + "'";
			}
			if ((!tj_where.equals("")) && (tj_where != null)) {
				where = where + tj_where;
			}

			// �����жϵ�ǰ�û��Ƿ��Ǿ����̵��˻�������ǵĻ�����ô����һ����ѯ���������򲻼��������
			if (dealers != null && !"".equals(dealers.trim())) {
				where += "  and dealers='" + dealers + "' ";
			}

			sql = sql + table_sub + where;
			if (tj_v6 != null && tj_v6.length() != 0) {
				if (tj_v6.indexOf("+") == -1) {
					where += " and cana_custom.customid in (select distinct customid from cana_service where orientation like '%"
							+ tj_v6 + "%' " + str_begindate + ") ";
				} else {
					where += " and cana_custom.customid in (select distinct customid from cana_service where 1=1 ";
					String[] temp = tj_v6.split("\\+");
					for (int index = 0; index < temp.length; index++) {
						where += " and orientation like '%" + temp[index]
								+ "%' ";
					}
					where += str_begindate + ")";
				}
			}

			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				size = rs.getInt(1);
				sizepa = (int) size / st;
				if (size % st > 0) {
					sizepa = sizepa + 1;
				}
			}

			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog
					.error("Error in method selectsql_com_list() of Custom,error:"
							+ ex.getMessage() + ",sql:" + sql);
			// System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
			return sizepa + "+" + size + "+" + sql;
		}
	}
	
	
	public String selectsql_com_list(int st, String language_v,
			String table_head, String tj_name1, String tj_name2,
			String tj_name3, String tj_name4, String tj_name5, String tj_name6,
			String tj_v1, String tj_v2, String tj_v3, String tj_v4,
			String tj_v5, String tj_v6, String tj_v11, String tj_v21,
			String tj_v31, String tj_v41, String tj_v51, String tj_v61,
			String tj_where, String taxis_name, String taxis_v, String status,
			String dealers,String userid,String have_power,String have_power_sql) {
		int size = 0;
		int sizepa = 0;
		String sql = "";
		try {
			String str_begindate = "";
			sql = "SELECT COUNT(distinct " + table_head
					+ "custom.customid) FROM " + table_head + "custom ";
			String where = " where " + table_head + "custom.id>0 ";
			String table_sub = "";
			if ((!tj_v1.equals("")) && (tj_v1 != null)) {
				String[] arr = StringUtils.arr(tj_name1, "+", "", 2);
				table_sub = "," + table_head + arr[0] + " ";
				where = " where " + table_head + "custom.customid="
						+ table_head + arr[0] + ".customid ";
				if (tj_v11.equals("0")) {
					String tj_v1_per = "";
					for (int i = 0; i < tj_v1.length(); i++) {
						tj_v1_per += tj_v1.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + " like '%" + tj_v1_per + "%'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + " like '%" + tj_v1_per + "%'";
					}
				} else if (tj_v11.equals("1")) {
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + " like '" + tj_v1 + "%'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + " like '" + tj_v1 + "%'";
					}
				} else if (tj_v11.equals("2")) {
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + " like '%" + tj_v1 + "%'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + " like '%" + tj_v1 + "%'";
					}
				} else if (tj_v11.equals("3")) {
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + " not like '%" + tj_v1 + "%'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + " not like '%" + tj_v1 + "%'";
					}
				} else {
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + "='" + tj_v1 + "'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + "='" + tj_v1 + "'";
					}
				}
			}
			if ((!tj_v2.equals("")) && (tj_v2 != null)) {
				if (tj_v21.equals("0")) {
					String tj_v2_per = "";
					for (int i = 0; i < tj_v2.length(); i++) {
						tj_v2_per += tj_v2.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name2
							+ " like '%" + tj_v2_per + "%'";
				} else if (tj_v21.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name2
							+ " like '" + tj_v2 + "%'";
				} else if (tj_v21.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name2
							+ " like '%" + tj_v2 + "%'";
				} else if (tj_v21.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name2
							+ " not like '%" + tj_v2 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name2
							+ "='" + tj_v2 + "'";
				}
			}
			if ((!tj_v3.equals("")) && (tj_v3 != null)) {
				if (tj_v31.equals("0")) {
					String tj_v3_per = "";
					for (int i = 0; i < tj_v3.length(); i++) {
						tj_v3_per += tj_v3.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name3
							+ " like '%" + tj_v3_per + "%'";
				} else if (tj_v31.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name3
							+ " like '" + tj_v3 + "%'";
				} else if (tj_v31.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name3
							+ " like '%" + tj_v3 + "%'";
				} else if (tj_v31.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name3
							+ " not like '%" + tj_v3 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name3
							+ "='" + tj_v3 + "'";
				}
			}
			if ((!tj_v4.equals("")) && (tj_v4 != null)) {
				if (tj_v41.equals("0")) {
					String tj_v4_per = "";
					for (int i = 0; i < tj_v4.length(); i++) {
						tj_v4_per += tj_v4.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name4
							+ " like '%" + tj_v4_per + "%'";
				} else if (tj_v41.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name4
							+ " like '" + tj_v4 + "%'";
				} else if (tj_v41.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name4
							+ " like '%" + tj_v4 + "%'";
				} else if (tj_v41.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name4
							+ " not like '%" + tj_v4 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name4
							+ "='" + tj_v4 + "'";
				}
			}
			if ((!tj_v5.equals("")) && (tj_v5 != null)) {
				if (tj_v51.equals("0")) {
					String tj_v5_per = "";
					for (int i = 0; i < tj_v5.length(); i++) {
						tj_v5_per += tj_v5.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name5
							+ " like '%" + tj_v5_per + "%'";
				} else if (tj_v51.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name5
							+ " like '" + tj_v5 + "%'";
				} else if (tj_v51.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name5
							+ " like '%" + tj_v5 + "%'";
				} else if (tj_v51.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name5
							+ " not like '%" + tj_v5 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name5
							+ "='" + tj_v5 + "'";
				}
			}
			
			if (tj_v6 != null && tj_v6.length() != 0) {
				if (tj_v6.indexOf("+") == -1) {
					where += " and cana_custom.customid in (select distinct customid from cana_service where orientation like '%"
							+ tj_v6 + "%' " + str_begindate + ") ";
				} else {
					where += " and cana_custom.customid in (select distinct customid from cana_service where 1=1 ";
					String[] temp = tj_v6.split("\\+");
					for (int index = 0; index < temp.length; index++) {
						where += " and orientation like '%" + temp[index]
								+ "%' ";
					}
					where += str_begindate + ")";
				}
			}
			
			if ((!status.equals("")) && (status != null)) {
				where = where + " and " + table_head + "custom.status='"
						+ status + "'";
			}
			if ((!tj_where.equals("")) && (tj_where != null)) {
				where = where + tj_where;
			}

			// �����жϵ�ǰ�û��Ƿ��Ǿ����̵��˻�������ǵĻ�����ô����һ����ѯ���������򲻼��������
			if (dealers != null && !"".equals(dealers.trim())) {
				where += "  and dealers='" + dealers + "' ";
			}
			
			

			sql = sql + table_sub + where;
			
			if(have_power!=null&&have_power.indexOf(userid)==-1){
				sql+=" and ( cana_custom.kind<>'����ʵ�ͻ�' or (cana_custom.kind='����ʵ�ͻ�' and (cana_custom.recorder="+userid+" or cana_custom.seller like '%"+userid+"%')) ) ";
			}
			

			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				size += rs.getInt(1);
			}
			sizepa = (int) size / st;
			if (size % st > 0) {
				sizepa = sizepa + 1;
			}

			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog
					.error("Error in method selectsql_com_list() of Custom,error:"
							+ ex.getMessage() + ",sql:" + sql);
			// System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
			return sizepa + "+" + size + "+" + sql;
		}
	}

	public String selectsql_com_list(int st, String language_v,
			String table_head, String tj_name1, String tj_name2,
			String tj_name3, String tj_name4, String tj_name5, String tj_name6,
			String tj_v1, String tj_v2, String tj_v3, String tj_v4,
			String tj_v5, String tj_v6, String tj_v11, String tj_v21,
			String tj_v31, String tj_v41, String tj_v51, String tj_v61,
			String tj_where, String taxis_name, String taxis_v, String status,
			String fuwubutnoorder, String dealers) {
		int size = 0;
		int sizepa = 0;
		String sql = "";
		try {
			String str_begindate = "";
			sql = "SELECT COUNT(distinct " + table_head
					+ "custom.customid) FROM " + table_head + "custom ";
			String where = " where " + table_head + "custom.id>0 ";
			String table_sub = "";
			if ((!tj_v1.equals("")) && (tj_v1 != null)) {
				String[] arr = StringUtils.arr(tj_name1, "+", "", 2);
				table_sub = "," + table_head + arr[0] + " ";
				where = " where " + table_head + "custom.customid="
						+ table_head + arr[0] + ".customid ";
				if (tj_v11.equals("0")) {
					String tj_v1_per = "";
					for (int i = 0; i < tj_v1.length(); i++) {
						tj_v1_per += tj_v1.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + " like '%" + tj_v1_per + "%'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + " like '%" + tj_v1_per + "%'";
					}
				} else if (tj_v11.equals("1")) {
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + " like '" + tj_v1 + "%'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + " like '" + tj_v1 + "%'";
					}
				} else if (tj_v11.equals("2")) {
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + " like '%" + tj_v1 + "%'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + " like '%" + tj_v1 + "%'";
					}
				} else if (tj_v11.equals("3")) {
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + " not like '%" + tj_v1 + "%'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + " not like '%" + tj_v1 + "%'";
					}
				} else {
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + "='" + tj_v1 + "'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + "='" + tj_v1 + "'";
					}
				}
			}
			if ((!tj_v2.equals("")) && (tj_v2 != null)) {
				if (tj_v21.equals("0")) {
					String tj_v2_per = "";
					for (int i = 0; i < tj_v2.length(); i++) {
						tj_v2_per += tj_v2.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name2
							+ " like '%" + tj_v2_per + "%'";
				} else if (tj_v21.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name2
							+ " like '" + tj_v2 + "%'";
				} else if (tj_v21.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name2
							+ " like '%" + tj_v2 + "%'";
				} else if (tj_v21.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name2
							+ " not like '%" + tj_v2 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name2
							+ "='" + tj_v2 + "'";
				}
			}
			if ((!tj_v3.equals("")) && (tj_v3 != null)) {
				if (tj_v31.equals("0")) {
					String tj_v3_per = "";
					for (int i = 0; i < tj_v3.length(); i++) {
						tj_v3_per += tj_v3.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name3
							+ " like '%" + tj_v3_per + "%'";
				} else if (tj_v31.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name3
							+ " like '" + tj_v3 + "%'";
				} else if (tj_v31.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name3
							+ " like '%" + tj_v3 + "%'";
				} else if (tj_v31.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name3
							+ " not like '%" + tj_v3 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name3
							+ "='" + tj_v3 + "'";
				}
			}
			if ((!tj_v4.equals("")) && (tj_v4 != null)) {
				if (tj_v41.equals("0")) {
					String tj_v4_per = "";
					for (int i = 0; i < tj_v4.length(); i++) {
						tj_v4_per += tj_v4.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name4
							+ " like '%" + tj_v4_per + "%'";
				} else if (tj_v41.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name4
							+ " like '" + tj_v4 + "%'";
				} else if (tj_v41.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name4
							+ " like '%" + tj_v4 + "%'";
				} else if (tj_v41.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name4
							+ " not like '%" + tj_v4 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name4
							+ "='" + tj_v4 + "'";
				}
			}
			if ((!tj_v5.equals("")) && (tj_v5 != null)) {
				if (tj_v51.equals("0")) {
					String tj_v5_per = "";
					for (int i = 0; i < tj_v5.length(); i++) {
						tj_v5_per += tj_v5.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name5
							+ " like '%" + tj_v5_per + "%'";
				} else if (tj_v51.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name5
							+ " like '" + tj_v5 + "%'";
				} else if (tj_v51.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name5
							+ " like '%" + tj_v5 + "%'";
				} else if (tj_v51.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name5
							+ " not like '%" + tj_v5 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name5
							+ "='" + tj_v5 + "'";
				}
			}
			if ((!status.equals("")) && (status != null)) {
				where = where + " and " + table_head + "custom.status='"
						+ status + "'";
			}
			if ((!tj_where.equals("")) && (tj_where != null)) {
				where = where + tj_where;
			}

			sql = sql + table_sub + where;
			sql += " and cana_custom.customid in (select distinct customid from cana_service) and cana_custom.customid not in (select distinct customid from cana_order) ";
			// �����жϵ�ǰ�û��Ƿ��Ǿ����̵��˻�������ǵĻ�����ô����һ����ѯ���������򲻼��������
			if (dealers != null && !"".equals(dealers.trim())) {
				sql += "  and dealers='" + dealers + "' ";
			}
			if (tj_v6 != null && tj_v6.length() != 0) {
				if (tj_v6.indexOf("+") == -1) {
					where += " and cana_custom.customid in (select distinct customid from cana_service where orientation like '%"
							+ tj_v6 + "%' " + str_begindate + ") ";
				} else {
					where += " and cana_custom.customid in (select distinct customid from cana_service where 1=1 ";
					String[] temp = tj_v6.split("\\+");
					for (int index = 0; index < temp.length; index++) {
						where += " and orientation like '%" + temp[index]
								+ "%' ";
					}
					where += str_begindate + ")";
				}
			}

			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				size = rs.getInt(1);
				sizepa = (int) size / st;
				if (size % st > 0) {
					sizepa = sizepa + 1;
				}
			}

			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog
					.error("Error in method selectsql_com_list() of Custom,error:"
							+ ex.getMessage() + ",sql:" + sql);
			// System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
			return sizepa + "+" + size + "+" + sql;
		}
	}
	
	
	public String selectsql_com_list(int st, String language_v,
			String table_head, String tj_name1, String tj_name2,
			String tj_name3, String tj_name4, String tj_name5, String tj_name6,
			String tj_v1, String tj_v2, String tj_v3, String tj_v4,
			String tj_v5, String tj_v6, String tj_v11, String tj_v21,
			String tj_v31, String tj_v41, String tj_v51, String tj_v61,
			String tj_where, String taxis_name, String taxis_v, String status,
			String fuwubutnoorder, String dealers, String userid,String have_power,String have_power_sql) {
		int size = 0;
		int sizepa = 0;
		String sql = "";
		try {
			String str_begindate = "";
			sql = "SELECT COUNT(distinct " + table_head
					+ "custom.customid) FROM " + table_head + "custom ";
			String where = " where " + table_head + "custom.id>0 ";
			String table_sub = "";
			if ((!tj_v1.equals("")) && (tj_v1 != null)) {
				String[] arr = StringUtils.arr(tj_name1, "+", "", 2);
				table_sub = "," + table_head + arr[0] + " ";
				where = " where " + table_head + "custom.customid="
						+ table_head + arr[0] + ".customid ";
				if (tj_v11.equals("0")) {
					String tj_v1_per = "";
					for (int i = 0; i < tj_v1.length(); i++) {
						tj_v1_per += tj_v1.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + " like '%" + tj_v1_per + "%'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + " like '%" + tj_v1_per + "%'";
					}
				} else if (tj_v11.equals("1")) {
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + " like '" + tj_v1 + "%'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + " like '" + tj_v1 + "%'";
					}
				} else if (tj_v11.equals("2")) {
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + " like '%" + tj_v1 + "%'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + " like '%" + tj_v1 + "%'";
					}
				} else if (tj_v11.equals("3")) {
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + " not like '%" + tj_v1 + "%'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + " not like '%" + tj_v1 + "%'";
					}
				} else {
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + "='" + tj_v1 + "'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + "='" + tj_v1 + "'";
					}
				}
			}
			if ((!tj_v2.equals("")) && (tj_v2 != null)) {
				if (tj_v21.equals("0")) {
					String tj_v2_per = "";
					for (int i = 0; i < tj_v2.length(); i++) {
						tj_v2_per += tj_v2.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name2
							+ " like '%" + tj_v2_per + "%'";
				} else if (tj_v21.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name2
							+ " like '" + tj_v2 + "%'";
				} else if (tj_v21.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name2
							+ " like '%" + tj_v2 + "%'";
				} else if (tj_v21.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name2
							+ " not like '%" + tj_v2 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name2
							+ "='" + tj_v2 + "'";
				}
			}
			if ((!tj_v3.equals("")) && (tj_v3 != null)) {
				if (tj_v31.equals("0")) {
					String tj_v3_per = "";
					for (int i = 0; i < tj_v3.length(); i++) {
						tj_v3_per += tj_v3.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name3
							+ " like '%" + tj_v3_per + "%'";
				} else if (tj_v31.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name3
							+ " like '" + tj_v3 + "%'";
				} else if (tj_v31.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name3
							+ " like '%" + tj_v3 + "%'";
				} else if (tj_v31.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name3
							+ " not like '%" + tj_v3 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name3
							+ "='" + tj_v3 + "'";
				}
			}
			if ((!tj_v4.equals("")) && (tj_v4 != null)) {
				if (tj_v41.equals("0")) {
					String tj_v4_per = "";
					for (int i = 0; i < tj_v4.length(); i++) {
						tj_v4_per += tj_v4.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name4
							+ " like '%" + tj_v4_per + "%'";
				} else if (tj_v41.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name4
							+ " like '" + tj_v4 + "%'";
				} else if (tj_v41.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name4
							+ " like '%" + tj_v4 + "%'";
				} else if (tj_v41.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name4
							+ " not like '%" + tj_v4 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name4
							+ "='" + tj_v4 + "'";
				}
			}
			if ((!tj_v5.equals("")) && (tj_v5 != null)) {
				if (tj_v51.equals("0")) {
					String tj_v5_per = "";
					for (int i = 0; i < tj_v5.length(); i++) {
						tj_v5_per += tj_v5.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name5
							+ " like '%" + tj_v5_per + "%'";
				} else if (tj_v51.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name5
							+ " like '" + tj_v5 + "%'";
				} else if (tj_v51.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name5
							+ " like '%" + tj_v5 + "%'";
				} else if (tj_v51.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name5
							+ " not like '%" + tj_v5 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name5
							+ "='" + tj_v5 + "'";
				}
			}
			
			where += " and cana_custom.customid in (select distinct customid from cana_service) and cana_custom.customid not in (select distinct customid from cana_order) ";
			// �����жϵ�ǰ�û��Ƿ��Ǿ����̵��˻�������ǵĻ�����ô����һ����ѯ���������򲻼��������
			if (dealers != null && !"".equals(dealers.trim())) {
				where += "  and dealers='" + dealers + "' ";
			}
			if (tj_v6 != null && tj_v6.length() != 0) {
				if (tj_v6.indexOf("+") == -1) {
					where += " and cana_custom.customid in (select distinct customid from cana_service where orientation like '%"
							+ tj_v6 + "%' " + str_begindate + ") ";
				} else {
					where += " and cana_custom.customid in (select distinct customid from cana_service where 1=1 ";
					String[] temp = tj_v6.split("\\+");
					for (int index = 0; index < temp.length; index++) {
						where += " and orientation like '%" + temp[index]
								+ "%' ";
					}
					where += str_begindate + ")";
				}
			}
			
			if ((!status.equals("")) && (status != null)) {
				where = where + " and " + table_head + "custom.status='"
						+ status + "'";
			}
			if ((!tj_where.equals("")) && (tj_where != null)) {
				where = where + tj_where;
			}
			
//			if(have_power!=null&&have_power.indexOf(userid)==-1){
//				where+=have_power_sql;
//			}
			
			sql = sql + table_sub + where;
			
			if(have_power!=null&&have_power.indexOf(userid)==-1){
				sql+=" and ( cana_custom.kind<>'����ʵ�ͻ�' or (cana_custom.kind='����ʵ�ͻ�' and (cana_custom.recorder="+userid+" or cana_custom.seller like '%"+userid+"%')) ) ";
			}
			

			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				size += rs.getInt(1);
			}
			sizepa = (int) size / st;
			if (size % st > 0) {
				sizepa = sizepa + 1;
			}

			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog
					.error("Error in method selectsql_com_list() of Custom,error:"
							+ ex.getMessage() + ",sql:" + sql);
			// System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
			return sizepa + "+" + size + "+" + sql;
		}
	}
	

	public Collection select_com_list(int step, int page, String language_v,
			String table_head, String tj_name1, String tj_name2,
			String tj_name3, String tj_name4, String tj_name5, String tj_name6,
			String tj_v1, String tj_v2, String tj_v3, String tj_v4,
			String tj_v5, String tj_v6, String tj_v11, String tj_v21,
			String tj_v31, String tj_v41, String tj_v51, String tj_v61,
			String tj_where, String taxis_name, String taxis_v, String status,
			String dealers) {
		Collection coll = new ArrayList();
		int tip = step * (page - 1);
		String sql = "";
		try {
			String str_begindate = "";
			sql = "SELECT " + table_head + "custom.* FROM " + table_head
					+ "custom ";
			String where = " where " + table_head + "custom.id>0 ";
			String table_sub = "";
			if ((!tj_v1.equals("")) && (tj_v1 != null)) {
				String[] arr = StringUtils.arr(tj_name1, "+", "", 2);
				table_sub = "," + table_head + arr[0] + " ";
				where = " where " + table_head + "custom.customid="
						+ table_head + arr[0] + ".customid ";
				if (tj_v11.equals("0")) {
					String tj_v1_per = "";
					for (int i = 0; i < tj_v1.length(); i++) {
						tj_v1_per += tj_v1.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + " like '%" + tj_v1_per + "%'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + " like '%" + tj_v1_per + "%'";
					}
				} else if (tj_v11.equals("1")) {
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + " like '" + tj_v1 + "%'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + " like '" + tj_v1 + "%'";
					}
				} else if (tj_v11.equals("2")) {
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + " like '%" + tj_v1 + "%'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + " like '%" + tj_v1 + "%'";
					}
				} else if (tj_v11.equals("3")) {
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + " not like '%" + tj_v1 + "%'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + " not like '%" + tj_v1 + "%'";
					}
				} else {
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + "='" + tj_v1 + "'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + "='" + tj_v1 + "'";
					}
				}
			}
			if ((!tj_v2.equals("")) && (tj_v2 != null)) {
				if (tj_v21.equals("0")) {
					String tj_v2_per = "";
					for (int i = 0; i < tj_v2.length(); i++) {
						tj_v2_per += tj_v2.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name2
							+ " like '%" + tj_v2_per + "%'";
				} else if (tj_v21.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name2
							+ " like '" + tj_v2 + "%'";
				} else if (tj_v21.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name2
							+ " like '%" + tj_v2 + "%'";
				} else if (tj_v21.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name2
							+ " not like '%" + tj_v2 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name2
							+ "='" + tj_v2 + "'";
				}
			}
			if ((!tj_v3.equals("")) && (tj_v3 != null)) {
				if (tj_v31.equals("0")) {
					String tj_v3_per = "";
					for (int i = 0; i < tj_v3.length(); i++) {
						tj_v3_per += tj_v3.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name3
							+ " like '%" + tj_v3_per + "%'";
				} else if (tj_v31.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name3
							+ " like '" + tj_v3 + "%'";
				} else if (tj_v31.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name3
							+ " like '%" + tj_v3 + "%'";
				} else if (tj_v31.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name3
							+ " not like '%" + tj_v3 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name3
							+ "='" + tj_v3 + "'";
				}
			}
			if ((!tj_v4.equals("")) && (tj_v4 != null)) {
				if (tj_v41.equals("0")) {
					String tj_v4_per = "";
					for (int i = 0; i < tj_v4.length(); i++) {
						tj_v4_per += tj_v4.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name4
							+ " like '%" + tj_v4_per + "%'";
				} else if (tj_v41.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name4
							+ " like '" + tj_v4 + "%'";
				} else if (tj_v41.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name4
							+ " like '%" + tj_v4 + "%'";
				} else if (tj_v41.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name4
							+ " not like '%" + tj_v4 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name4
							+ "='" + tj_v4 + "'";
				}
			}
			if ((!tj_v5.equals("")) && (tj_v5 != null)) {
				if (tj_v51.equals("0")) {
					String tj_v5_per = "";
					for (int i = 0; i < tj_v5.length(); i++) {
						tj_v5_per += tj_v5.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name5
							+ " like '%" + tj_v5_per + "%'";
				} else if (tj_v51.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name5
							+ " like '" + tj_v5 + "%'";
				} else if (tj_v51.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name5
							+ " like '%" + tj_v5 + "%'";
				} else if (tj_v51.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name5
							+ " not like '%" + tj_v5 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name5
							+ "='" + tj_v5 + "'";
				}
			}

			if (tj_v6 != null && tj_v6.length() != 0) {
				if (tj_v6.indexOf("+") == -1) {
					where += " and cana_custom.customid in (select distinct customid from cana_service where orientation like '%"
							+ tj_v6 + "%' " + str_begindate + ") ";
				} else {
					where += " and cana_custom.customid in (select distinct customid from cana_service where 1=1 ";
					String[] temp = tj_v6.split("\\+");
					for (int index = 0; index < temp.length; index++) {
						where += " and orientation like '%" + temp[index]
								+ "%' ";
					}
					where += str_begindate + ")";
				}
			}

			if ((!status.equals("")) && (status != null)) {
				where = where + " and " + table_head + "custom.status='"
						+ status + "'";
			}
			if ((!tj_where.equals("")) && (tj_where != null)) {
				where = where + tj_where;
			}
			String orderby = " order by " + table_head + "custom.customid desc";
			if ((!taxis_v.equals("")) && (taxis_v != null)) {
				orderby = " order by " + table_head + "custom." + taxis_name
						+ " " + taxis_v;
			}

			// �����жϵ�ǰ�û��Ƿ��Ǿ����̵��˻�������ǵĻ�����ô����һ����ѯ���������򲻼��������
			if (dealers != null && !"".equals(dealers.trim())) {
				where += "  and dealers='" + dealers + "' ";
			}

			sql = sql + table_sub + where + orderby;
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			if (tip <= 0) {
				rs.beforeFirst();
			} else if (!rs.absolute(tip)) {
				rs.beforeFirst();
			}
			for (int i = 1; rs.next() && i <= step; i++) {
				CustomShow CShow = new CustomShow();
				CShow.setid(rs.getInt("id"));
				CShow.setcustomid(rs.getInt("customid"));
				CShow.setserial_num(rs.getString("serial_num"));
				CShow.setname(rs.getString("name"));
				CShow.setname_pinyin(rs.getString("name_pinyin"));
				CShow.setarea1(rs.getString("area1"));
				CShow.setarea2(rs.getString("area2"));
				CShow.setarea3(rs.getString("area3"));
				CShow.setlinkman(rs.getString("linkman"));
				CShow.settel(rs.getString("tel"));
				CShow.setmobile(rs.getString("mobile"));
				CShow.setfax(rs.getString("fax"));
				CShow.setwebsite(rs.getString("website"));
				CShow.setemail(rs.getString("email"));
				CShow.setkind(rs.getString("kind"));
				CShow.setaddress(rs.getString("address"));
				CShow.setzip(rs.getInt("zip"));
				// CShow.setseller(rs.getInt("seller"));
				CShow.setseller(rs.getString("seller"));
				CShow.setfiliale(rs.getString("filiale"));
				CShow.setshare(rs.getString("share"));
				CShow.sethotspot(rs.getString("hotspot"));
				CShow.sethotspot_explain(rs.getString("hotspot_explain"));
				CShow.setvalue_evaluate(rs.getString("value_evaluate"));
				CShow.setcredit(rs.getString("credit"));
				CShow.setindustry(rs.getString("industry"));
				CShow.setrelation(rs.getString("relation"));
				CShow.sethuman_size(rs.getString("human_size"));
				CShow.setresource(rs.getString("resource"));
				CShow.setremark(rs.getString("remark"));
				CShow.setrecorder(rs.getInt("recorder"));
				CShow.setrecorder_time(rs.getString("recorder_time"));
				CShow.setupdater(rs.getInt("updater"));
				CShow.setupdate_time(rs.getString("update_time"));
				CShow.setself_field1(rs.getString("self_field1"));
				CShow.setself_field2(rs.getString("self_field2"));
				CShow.setself_field3(rs.getString("self_field3"));
				CShow.setself_field4(rs.getString("self_field4"));
				CShow.setself_field5(rs.getString("self_field5"));
				CShow.setself_field6(rs.getString("self_field6"));
				CShow.setself_field7(rs.getString("self_field7"));
				CShow.setself_field8(rs.getString("self_field8"));
				CShow.setstatus(rs.getInt("status"));
				CShow.setlanguage_ver(rs.getString("language_ver"));
				CShow.setcomplaints_remark(rs.getString("complaints_remark"));
				CShow.setmail_date(rs.getString("mail_date"));
				CShow.settel2(rs.getString("tel2"));
				CShow.setmobile2(rs.getString("mobile2"));
				CShow.setmobile3(rs.getString("mobile3"));
				CShow.setself_field9(rs.getString("self_field9"));
				CShow.setself_field10(rs.getString("self_field10"));
				CShow.setself_field11(rs.getString("self_field11"));
				CShow.setself_field12(rs.getString("self_field12"));
				CShow.setself_field13(rs.getString("self_field13"));
				CShow.setself_field14(rs.getString("self_field14"));
				CShow.setself_field15(rs.getString("self_field15"));
				CShow.setself_field16(rs.getString("self_field16"));
				CShow.setself_field17(rs.getString("self_field17"));
				CShow.setself_field18(rs.getString("self_field18"));
				coll.add(CShow);
			}

			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog.error("Error in method select_com_list() of Custom,error:"
					+ ex.getMessage() + ",sql:" + sql);
			// System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
			return coll;
		}
	}
	
	
	public Collection select_com_list(int step, int page, String language_v,
			String table_head, String tj_name1, String tj_name2,
			String tj_name3, String tj_name4, String tj_name5, String tj_name6,
			String tj_v1, String tj_v2, String tj_v3, String tj_v4,
			String tj_v5, String tj_v6, String tj_v11, String tj_v21,
			String tj_v31, String tj_v41, String tj_v51, String tj_v61,
			String tj_where, String taxis_name, String taxis_v, String status,
			String dealers,String userid,String have_power,String have_power_sql) {
		Collection coll = new ArrayList();
		int tip = step * (page - 1);
		String sql = "";
		try {
			String str_begindate = "";
			sql = "SELECT " + table_head + "custom.* FROM " + table_head
					+ "custom ";
			String where = " where " + table_head + "custom.id>0 ";
			String table_sub = "";
			if ((!tj_v1.equals("")) && (tj_v1 != null)) {
				String[] arr = StringUtils.arr(tj_name1, "+", "", 2);
				table_sub = "," + table_head + arr[0] + " ";
				where = " where " + table_head + "custom.customid="
						+ table_head + arr[0] + ".customid ";
				if (tj_v11.equals("0")) {
					String tj_v1_per = "";
					for (int i = 0; i < tj_v1.length(); i++) {
						tj_v1_per += tj_v1.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + " like '%" + tj_v1_per + "%'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + " like '%" + tj_v1_per + "%'";
					}
				} else if (tj_v11.equals("1")) {
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + " like '" + tj_v1 + "%'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + " like '" + tj_v1 + "%'";
					}
				} else if (tj_v11.equals("2")) {
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + " like '%" + tj_v1 + "%'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + " like '%" + tj_v1 + "%'";
					}
				} else if (tj_v11.equals("3")) {
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + " not like '%" + tj_v1 + "%'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + " not like '%" + tj_v1 + "%'";
					}
				} else {
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + "='" + tj_v1 + "'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + "='" + tj_v1 + "'";
					}
				}
			}
			if ((!tj_v2.equals("")) && (tj_v2 != null)) {
				if (tj_v21.equals("0")) {
					String tj_v2_per = "";
					for (int i = 0; i < tj_v2.length(); i++) {
						tj_v2_per += tj_v2.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name2
							+ " like '%" + tj_v2_per + "%'";
				} else if (tj_v21.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name2
							+ " like '" + tj_v2 + "%'";
				} else if (tj_v21.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name2
							+ " like '%" + tj_v2 + "%'";
				} else if (tj_v21.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name2
							+ " not like '%" + tj_v2 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name2
							+ "='" + tj_v2 + "'";
				}
			}
			if ((!tj_v3.equals("")) && (tj_v3 != null)) {
				if (tj_v31.equals("0")) {
					String tj_v3_per = "";
					for (int i = 0; i < tj_v3.length(); i++) {
						tj_v3_per += tj_v3.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name3
							+ " like '%" + tj_v3_per + "%'";
				} else if (tj_v31.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name3
							+ " like '" + tj_v3 + "%'";
				} else if (tj_v31.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name3
							+ " like '%" + tj_v3 + "%'";
				} else if (tj_v31.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name3
							+ " not like '%" + tj_v3 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name3
							+ "='" + tj_v3 + "'";
				}
			}
			if ((!tj_v4.equals("")) && (tj_v4 != null)) {
				if (tj_v41.equals("0")) {
					String tj_v4_per = "";
					for (int i = 0; i < tj_v4.length(); i++) {
						tj_v4_per += tj_v4.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name4
							+ " like '%" + tj_v4_per + "%'";
				} else if (tj_v41.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name4
							+ " like '" + tj_v4 + "%'";
				} else if (tj_v41.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name4
							+ " like '%" + tj_v4 + "%'";
				} else if (tj_v41.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name4
							+ " not like '%" + tj_v4 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name4
							+ "='" + tj_v4 + "'";
				}
			}
			if ((!tj_v5.equals("")) && (tj_v5 != null)) {
				if (tj_v51.equals("0")) {
					String tj_v5_per = "";
					for (int i = 0; i < tj_v5.length(); i++) {
						tj_v5_per += tj_v5.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name5
							+ " like '%" + tj_v5_per + "%'";
				} else if (tj_v51.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name5
							+ " like '" + tj_v5 + "%'";
				} else if (tj_v51.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name5
							+ " like '%" + tj_v5 + "%'";
				} else if (tj_v51.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name5
							+ " not like '%" + tj_v5 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name5
							+ "='" + tj_v5 + "'";
				}
			}

			if (tj_v6 != null && tj_v6.length() != 0) {
				if (tj_v6.indexOf("+") == -1) {
					where += " and cana_custom.customid in (select distinct customid from cana_service where orientation like '%"
							+ tj_v6 + "%' " + str_begindate + ") ";
				} else {
					where += " and cana_custom.customid in (select distinct customid from cana_service where 1=1 ";
					String[] temp = tj_v6.split("\\+");
					for (int index = 0; index < temp.length; index++) {
						where += " and orientation like '%" + temp[index]
								+ "%' ";
					}
					where += str_begindate + ")";
				}
			}

			if ((!status.equals("")) && (status != null)) {
				where = where + " and " + table_head + "custom.status='"
						+ status + "'";
			}
			if ((!tj_where.equals("")) && (tj_where != null)) {
				where = where + tj_where;
			}
			String orderby = " order by " + table_head + "custom.customid desc";
			if ((!taxis_v.equals("")) && (taxis_v != null)) {
				orderby = " order by " + table_head + "custom." + taxis_name
						+ " " + taxis_v;
			}

			// �����жϵ�ǰ�û��Ƿ��Ǿ����̵��˻�������ǵĻ�����ô����һ����ѯ���������򲻼��������
			if (dealers != null && !"".equals(dealers.trim())) {
				where += "  and dealers='" + dealers + "' ";
			}
			
//			if(have_power!=null&&have_power.indexOf(userid)==-1){
////				where +=" and cana_custom.kind<>'����ʵ�ͻ�' union all select * from cana_custom where status='0' and kind='����ʵ�ͻ�' and (recorder="+userid+" or seller like '%"+userid+"%') ";
//				where+=have_power_sql;
//			}

			sql = sql + table_sub + where ;
			
			if(have_power!=null&&have_power.indexOf(userid)==-1){
				sql+=" and ( cana_custom.kind<>'����ʵ�ͻ�' or (cana_custom.kind='����ʵ�ͻ�' and (cana_custom.recorder="+userid+" or cana_custom.seller like '%"+userid+"%')) ) ";
			}
			
			sql+=orderby;
			
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			if (tip <= 0) {
				rs.beforeFirst();
			} else if (!rs.absolute(tip)) {
				rs.beforeFirst();
			}
			for (int i = 1; rs.next() && i <= step; i++) {
				CustomShow CShow = new CustomShow();
				CShow.setid(rs.getInt("id"));
				CShow.setcustomid(rs.getInt("customid"));
				CShow.setserial_num(rs.getString("serial_num"));
				CShow.setname(rs.getString("name"));
				CShow.setname_pinyin(rs.getString("name_pinyin"));
				CShow.setarea1(rs.getString("area1"));
				CShow.setarea2(rs.getString("area2"));
				CShow.setarea3(rs.getString("area3"));
				CShow.setlinkman(rs.getString("linkman"));
				CShow.settel(rs.getString("tel"));
				CShow.setmobile(rs.getString("mobile"));
				CShow.setfax(rs.getString("fax"));
				CShow.setwebsite(rs.getString("website"));
				CShow.setemail(rs.getString("email"));
				CShow.setkind(rs.getString("kind"));
				CShow.setaddress(rs.getString("address"));
				CShow.setzip(rs.getInt("zip"));
				// CShow.setseller(rs.getInt("seller"));
				CShow.setseller(rs.getString("seller"));
				CShow.setfiliale(rs.getString("filiale"));
				CShow.setshare(rs.getString("share"));
				CShow.sethotspot(rs.getString("hotspot"));
				CShow.sethotspot_explain(rs.getString("hotspot_explain"));
				CShow.setvalue_evaluate(rs.getString("value_evaluate"));
				CShow.setcredit(rs.getString("credit"));
				CShow.setindustry(rs.getString("industry"));
				CShow.setrelation(rs.getString("relation"));
				CShow.sethuman_size(rs.getString("human_size"));
				CShow.setresource(rs.getString("resource"));
				CShow.setremark(rs.getString("remark"));
				CShow.setrecorder(rs.getInt("recorder"));
				CShow.setrecorder_time(rs.getString("recorder_time"));
				CShow.setupdater(rs.getInt("updater"));
				CShow.setupdate_time(rs.getString("update_time"));
				CShow.setself_field1(rs.getString("self_field1"));
				CShow.setself_field2(rs.getString("self_field2"));
				CShow.setself_field3(rs.getString("self_field3"));
				CShow.setself_field4(rs.getString("self_field4"));
				CShow.setself_field5(rs.getString("self_field5"));
				CShow.setself_field6(rs.getString("self_field6"));
				CShow.setself_field7(rs.getString("self_field7"));
				CShow.setself_field8(rs.getString("self_field8"));
				CShow.setstatus(rs.getInt("status"));
				CShow.setlanguage_ver(rs.getString("language_ver"));
				CShow.setcomplaints_remark(rs.getString("complaints_remark"));
				CShow.setmail_date(rs.getString("mail_date"));
				CShow.settel2(rs.getString("tel2"));
				CShow.setmobile2(rs.getString("mobile2"));
				CShow.setmobile3(rs.getString("mobile3"));
				CShow.setself_field9(rs.getString("self_field9"));
				CShow.setself_field10(rs.getString("self_field10"));
				CShow.setself_field11(rs.getString("self_field11"));
				CShow.setself_field12(rs.getString("self_field12"));
				CShow.setself_field13(rs.getString("self_field13"));
				CShow.setself_field14(rs.getString("self_field14"));
				CShow.setself_field15(rs.getString("self_field15"));
				CShow.setself_field16(rs.getString("self_field16"));
				CShow.setself_field17(rs.getString("self_field17"));
				CShow.setself_field18(rs.getString("self_field18"));
				coll.add(CShow);
			}

			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog.error("Error in method select_com_list() of Custom,error:"
					+ ex.getMessage() + ",sql:" + sql);
			// System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
			return coll;
		}
	}

	public Collection select_com_list(int step, int page, String language_v,
			String table_head, String tj_name1, String tj_name2,
			String tj_name3, String tj_name4, String tj_name5, String tj_name6,
			String tj_v1, String tj_v2, String tj_v3, String tj_v4,
			String tj_v5, String tj_v6, String tj_v11, String tj_v21,
			String tj_v31, String tj_v41, String tj_v51, String tj_v61,
			String tj_where, String taxis_name, String taxis_v, String status,
			String wufubutnoorder, String dealers) {
		Collection coll = new ArrayList();
		int tip = step * (page - 1);
		String sql = "";
		try {
			String str_begindate = "";
			sql = "SELECT " + table_head + "custom.* FROM " + table_head
					+ "custom ";
			String where = " where " + table_head + "custom.id>0 ";
			String table_sub = "";
			if ((!tj_v1.equals("")) && (tj_v1 != null)) {
				String[] arr = StringUtils.arr(tj_name1, "+", "", 2);
				table_sub = "," + table_head + arr[0] + " ";
				where = " where " + table_head + "custom.customid="
						+ table_head + arr[0] + ".customid ";
				if (tj_v11.equals("0")) {
					String tj_v1_per = "";
					for (int i = 0; i < tj_v1.length(); i++) {
						tj_v1_per += tj_v1.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + " like '%" + tj_v1_per + "%'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + " like '%" + tj_v1_per + "%'";
					}
				} else if (tj_v11.equals("1")) {
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + " like '" + tj_v1 + "%'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + " like '" + tj_v1 + "%'";
					}
				} else if (tj_v11.equals("2")) {
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + " like '%" + tj_v1 + "%'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + " like '%" + tj_v1 + "%'";
					}
				} else if (tj_v11.equals("3")) {
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + " not like '%" + tj_v1 + "%'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + " not like '%" + tj_v1 + "%'";
					}
				} else {
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + "='" + tj_v1 + "'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + "='" + tj_v1 + "'";
					}
				}
			}
			if ((!tj_v2.equals("")) && (tj_v2 != null)) {
				if (tj_v21.equals("0")) {
					String tj_v2_per = "";
					for (int i = 0; i < tj_v2.length(); i++) {
						tj_v2_per += tj_v2.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name2
							+ " like '%" + tj_v2_per + "%'";
				} else if (tj_v21.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name2
							+ " like '" + tj_v2 + "%'";
				} else if (tj_v21.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name2
							+ " like '%" + tj_v2 + "%'";
				} else if (tj_v21.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name2
							+ " not like '%" + tj_v2 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name2
							+ "='" + tj_v2 + "'";
				}
			}
			if ((!tj_v3.equals("")) && (tj_v3 != null)) {
				if (tj_v31.equals("0")) {
					String tj_v3_per = "";
					for (int i = 0; i < tj_v3.length(); i++) {
						tj_v3_per += tj_v3.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name3
							+ " like '%" + tj_v3_per + "%'";
				} else if (tj_v31.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name3
							+ " like '" + tj_v3 + "%'";
				} else if (tj_v31.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name3
							+ " like '%" + tj_v3 + "%'";
				} else if (tj_v31.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name3
							+ " not like '%" + tj_v3 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name3
							+ "='" + tj_v3 + "'";
				}
			}
			if ((!tj_v4.equals("")) && (tj_v4 != null)) {
				if (tj_v41.equals("0")) {
					String tj_v4_per = "";
					for (int i = 0; i < tj_v4.length(); i++) {
						tj_v4_per += tj_v4.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name4
							+ " like '%" + tj_v4_per + "%'";
				} else if (tj_v41.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name4
							+ " like '" + tj_v4 + "%'";
				} else if (tj_v41.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name4
							+ " like '%" + tj_v4 + "%'";
				} else if (tj_v41.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name4
							+ " not like '%" + tj_v4 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name4
							+ "='" + tj_v4 + "'";
				}
			}
			if ((!tj_v5.equals("")) && (tj_v5 != null)) {
				if (tj_v51.equals("0")) {
					String tj_v5_per = "";
					for (int i = 0; i < tj_v5.length(); i++) {
						tj_v5_per += tj_v5.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name5
							+ " like '%" + tj_v5_per + "%'";
				} else if (tj_v51.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name5
							+ " like '" + tj_v5 + "%'";
				} else if (tj_v51.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name5
							+ " like '%" + tj_v5 + "%'";
				} else if (tj_v51.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name5
							+ " not like '%" + tj_v5 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name5
							+ "='" + tj_v5 + "'";
				}
			}

			if (tj_v6 != null && tj_v6.length() != 0) {
				if (tj_v6.indexOf("+") == -1) {
					where += " and cana_custom.customid in (select distinct customid from cana_service where orientation like '%"
							+ tj_v6 + "%' " + str_begindate + ") ";
				} else {
					where += " and cana_custom.customid in (select distinct customid from cana_service where 1=1 ";
					String[] temp = tj_v6.split("\\+");
					for (int index = 0; index < temp.length; index++) {
						where += " and orientation like '%" + temp[index]
								+ "%' ";
					}
					where += str_begindate + ")";
				}
			}

			if ((!status.equals("")) && (status != null)) {
				where = where + " and " + table_head + "custom.status='"
						+ status + "'";
			}
			if ((!tj_where.equals("")) && (tj_where != null)) {
				where = where + tj_where;
			}
			where += " and cana_custom.customid in (select distinct customid from cana_service) and cana_custom.customid not in (select distinct customid from cana_order) ";

			String orderby = " order by " + table_head + "custom.customid desc";
			if ((!taxis_v.equals("")) && (taxis_v != null)) {
				orderby = " order by " + table_head + "custom." + taxis_name
						+ " " + taxis_v;
			}

			// �����жϵ�ǰ�û��Ƿ��Ǿ����̵��˻�������ǵĻ�����ô����һ����ѯ���������򲻼��������
			if (dealers != null && !"".equals(dealers.trim())) {
				where += "  and dealers='" + dealers + "' ";
			}

			sql = sql + table_sub + where + orderby;
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			if (tip <= 0) {
				rs.beforeFirst();
			} else if (!rs.absolute(tip)) {
				rs.beforeFirst();
			}
			for (int i = 1; rs.next() && i <= step; i++) {
				CustomShow CShow = new CustomShow();
				CShow.setid(rs.getInt("id"));
				CShow.setcustomid(rs.getInt("customid"));
				CShow.setserial_num(rs.getString("serial_num"));
				CShow.setname(rs.getString("name"));
				CShow.setname_pinyin(rs.getString("name_pinyin"));
				CShow.setarea1(rs.getString("area1"));
				CShow.setarea2(rs.getString("area2"));
				CShow.setarea3(rs.getString("area3"));
				CShow.setlinkman(rs.getString("linkman"));
				CShow.settel(rs.getString("tel"));
				CShow.setmobile(rs.getString("mobile"));
				CShow.setfax(rs.getString("fax"));
				CShow.setwebsite(rs.getString("website"));
				CShow.setemail(rs.getString("email"));
				CShow.setkind(rs.getString("kind"));
				CShow.setaddress(rs.getString("address"));
				CShow.setzip(rs.getInt("zip"));
				// CShow.setseller(rs.getInt("seller"));
				CShow.setseller(rs.getString("seller"));
				CShow.setfiliale(rs.getString("filiale"));
				CShow.setshare(rs.getString("share"));
				CShow.sethotspot(rs.getString("hotspot"));
				CShow.sethotspot_explain(rs.getString("hotspot_explain"));
				CShow.setvalue_evaluate(rs.getString("value_evaluate"));
				CShow.setcredit(rs.getString("credit"));
				CShow.setindustry(rs.getString("industry"));
				CShow.setrelation(rs.getString("relation"));
				CShow.sethuman_size(rs.getString("human_size"));
				CShow.setresource(rs.getString("resource"));
				CShow.setremark(rs.getString("remark"));
				CShow.setrecorder(rs.getInt("recorder"));
				CShow.setrecorder_time(rs.getString("recorder_time"));
				CShow.setupdater(rs.getInt("updater"));
				CShow.setupdate_time(rs.getString("update_time"));
				CShow.setself_field1(rs.getString("self_field1"));
				CShow.setself_field2(rs.getString("self_field2"));
				CShow.setself_field3(rs.getString("self_field3"));
				CShow.setself_field4(rs.getString("self_field4"));
				CShow.setself_field5(rs.getString("self_field5"));
				CShow.setself_field6(rs.getString("self_field6"));
				CShow.setself_field7(rs.getString("self_field7"));
				CShow.setself_field8(rs.getString("self_field8"));
				CShow.setstatus(rs.getInt("status"));
				CShow.setlanguage_ver(rs.getString("language_ver"));
				CShow.setcomplaints_remark(rs.getString("complaints_remark"));
				CShow.setmail_date(rs.getString("mail_date"));
				CShow.settel2(rs.getString("tel2"));
				CShow.setmobile2(rs.getString("mobile2"));
				CShow.setmobile3(rs.getString("mobile3"));
				CShow.setself_field9(rs.getString("self_field9"));
				CShow.setself_field10(rs.getString("self_field10"));
				CShow.setself_field11(rs.getString("self_field11"));
				CShow.setself_field12(rs.getString("self_field12"));
				CShow.setself_field13(rs.getString("self_field13"));
				CShow.setself_field14(rs.getString("self_field14"));
				CShow.setself_field15(rs.getString("self_field15"));
				CShow.setself_field16(rs.getString("self_field16"));
				CShow.setself_field17(rs.getString("self_field17"));
				CShow.setself_field18(rs.getString("self_field18"));
				coll.add(CShow);
			}

			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog.error("Error in method select_com_list() of Custom,error:"
					+ ex.getMessage() + ",sql:" + sql);
			// System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
			return coll;
		}
	}
	
	public Collection select_com_list(int step, int page, String language_v,
			String table_head, String tj_name1, String tj_name2,
			String tj_name3, String tj_name4, String tj_name5, String tj_name6,
			String tj_v1, String tj_v2, String tj_v3, String tj_v4,
			String tj_v5, String tj_v6, String tj_v11, String tj_v21,
			String tj_v31, String tj_v41, String tj_v51, String tj_v61,
			String tj_where, String taxis_name, String taxis_v, String status,
			String wufubutnoorder, String dealers,String userid,String have_power,String have_power_sql) {
		Collection coll = new ArrayList();
		int tip = step * (page - 1);
		String sql = "";
		try {
			String str_begindate = "";
			sql = "SELECT " + table_head + "custom.* FROM " + table_head
					+ "custom ";
			String where = " where " + table_head + "custom.id>0 ";
			String table_sub = "";
			if ((!tj_v1.equals("")) && (tj_v1 != null)) {
				String[] arr = StringUtils.arr(tj_name1, "+", "", 2);
				table_sub = "," + table_head + arr[0] + " ";
				where = " where " + table_head + "custom.customid="
						+ table_head + arr[0] + ".customid ";
				if (tj_v11.equals("0")) {
					String tj_v1_per = "";
					for (int i = 0; i < tj_v1.length(); i++) {
						tj_v1_per += tj_v1.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + " like '%" + tj_v1_per + "%'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + " like '%" + tj_v1_per + "%'";
					}
				} else if (tj_v11.equals("1")) {
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + " like '" + tj_v1 + "%'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + " like '" + tj_v1 + "%'";
					}
				} else if (tj_v11.equals("2")) {
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + " like '%" + tj_v1 + "%'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + " like '%" + tj_v1 + "%'";
					}
				} else if (tj_v11.equals("3")) {
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + " not like '%" + tj_v1 + "%'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + " not like '%" + tj_v1 + "%'";
					}
				} else {
					where = where + " and " + table_head + arr[0] + "."
							+ arr[1] + "='" + tj_v1 + "'";
					if ("service".equals(arr[0]) && "begindate".equals(arr[1])) {
						str_begindate += " and " + table_head + arr[0] + "."
								+ arr[1] + "='" + tj_v1 + "'";
					}
				}
			}
			if ((!tj_v2.equals("")) && (tj_v2 != null)) {
				if (tj_v21.equals("0")) {
					String tj_v2_per = "";
					for (int i = 0; i < tj_v2.length(); i++) {
						tj_v2_per += tj_v2.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name2
							+ " like '%" + tj_v2_per + "%'";
				} else if (tj_v21.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name2
							+ " like '" + tj_v2 + "%'";
				} else if (tj_v21.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name2
							+ " like '%" + tj_v2 + "%'";
				} else if (tj_v21.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name2
							+ " not like '%" + tj_v2 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name2
							+ "='" + tj_v2 + "'";
				}
			}
			if ((!tj_v3.equals("")) && (tj_v3 != null)) {
				if (tj_v31.equals("0")) {
					String tj_v3_per = "";
					for (int i = 0; i < tj_v3.length(); i++) {
						tj_v3_per += tj_v3.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name3
							+ " like '%" + tj_v3_per + "%'";
				} else if (tj_v31.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name3
							+ " like '" + tj_v3 + "%'";
				} else if (tj_v31.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name3
							+ " like '%" + tj_v3 + "%'";
				} else if (tj_v31.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name3
							+ " not like '%" + tj_v3 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name3
							+ "='" + tj_v3 + "'";
				}
			}
			if ((!tj_v4.equals("")) && (tj_v4 != null)) {
				if (tj_v41.equals("0")) {
					String tj_v4_per = "";
					for (int i = 0; i < tj_v4.length(); i++) {
						tj_v4_per += tj_v4.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name4
							+ " like '%" + tj_v4_per + "%'";
				} else if (tj_v41.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name4
							+ " like '" + tj_v4 + "%'";
				} else if (tj_v41.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name4
							+ " like '%" + tj_v4 + "%'";
				} else if (tj_v41.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name4
							+ " not like '%" + tj_v4 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name4
							+ "='" + tj_v4 + "'";
				}
			}
			if ((!tj_v5.equals("")) && (tj_v5 != null)) {
				if (tj_v51.equals("0")) {
					String tj_v5_per = "";
					for (int i = 0; i < tj_v5.length(); i++) {
						tj_v5_per += tj_v5.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name5
							+ " like '%" + tj_v5_per + "%'";
				} else if (tj_v51.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name5
							+ " like '" + tj_v5 + "%'";
				} else if (tj_v51.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name5
							+ " like '%" + tj_v5 + "%'";
				} else if (tj_v51.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name5
							+ " not like '%" + tj_v5 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name5
							+ "='" + tj_v5 + "'";
				}
			}

			if (tj_v6 != null && tj_v6.length() != 0) {
				if (tj_v6.indexOf("+") == -1) {
					where += " and cana_custom.customid in (select distinct customid from cana_service where orientation like '%"
							+ tj_v6 + "%' " + str_begindate + ") ";
				} else {
					where += " and cana_custom.customid in (select distinct customid from cana_service where 1=1 ";
					String[] temp = tj_v6.split("\\+");
					for (int index = 0; index < temp.length; index++) {
						where += " and orientation like '%" + temp[index]
								+ "%' ";
					}
					where += str_begindate + ")";
				}
			}

			if ((!status.equals("")) && (status != null)) {
				where = where + " and " + table_head + "custom.status='"
						+ status + "'";
			}
			if ((!tj_where.equals("")) && (tj_where != null)) {
				where = where + tj_where;
			}
			where += " and cana_custom.customid in (select distinct customid from cana_service) and cana_custom.customid not in (select distinct customid from cana_order) ";

			String orderby = " order by " + table_head + "custom.customid desc";
			if ((!taxis_v.equals("")) && (taxis_v != null)) {
				orderby = " order by " + table_head + "custom." + taxis_name
						+ " " + taxis_v;
			}

			// �����жϵ�ǰ�û��Ƿ��Ǿ����̵��˻�������ǵĻ�����ô����һ����ѯ���������򲻼��������
			if (dealers != null && !"".equals(dealers.trim())) {
				where += "  and dealers='" + dealers + "' ";
			}
			
//			if(have_power!=null&&have_power.indexOf(userid)==-1){
////				where +=" and cana_custom.kind<>'����ʵ�ͻ�' union all select * from cana_custom where status='0' and kind='����ʵ�ͻ�' and (recorder="+userid+" or seller like '%"+userid+"%') ";
//				where+=have_power_sql;
//			}

			sql = sql + table_sub + where ;
			
			
			if(have_power!=null&&have_power.indexOf(userid)==-1){
				sql+=" and ( cana_custom.kind<>'����ʵ�ͻ�' or (cana_custom.kind='����ʵ�ͻ�' and (cana_custom.recorder="+userid+" or cana_custom.seller like '%"+userid+"%')) ) ";
			}
			
			sql+=orderby;
			
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			if (tip <= 0) {
				rs.beforeFirst();
			} else if (!rs.absolute(tip)) {
				rs.beforeFirst();
			}
			for (int i = 1; rs.next() && i <= step; i++) {
				CustomShow CShow = new CustomShow();
				CShow.setid(rs.getInt("id"));
				CShow.setcustomid(rs.getInt("customid"));
				CShow.setserial_num(rs.getString("serial_num"));
				CShow.setname(rs.getString("name"));
				CShow.setname_pinyin(rs.getString("name_pinyin"));
				CShow.setarea1(rs.getString("area1"));
				CShow.setarea2(rs.getString("area2"));
				CShow.setarea3(rs.getString("area3"));
				CShow.setlinkman(rs.getString("linkman"));
				CShow.settel(rs.getString("tel"));
				CShow.setmobile(rs.getString("mobile"));
				CShow.setfax(rs.getString("fax"));
				CShow.setwebsite(rs.getString("website"));
				CShow.setemail(rs.getString("email"));
				CShow.setkind(rs.getString("kind"));
				CShow.setaddress(rs.getString("address"));
				CShow.setzip(rs.getInt("zip"));
				// CShow.setseller(rs.getInt("seller"));
				CShow.setseller(rs.getString("seller"));
				CShow.setfiliale(rs.getString("filiale"));
				CShow.setshare(rs.getString("share"));
				CShow.sethotspot(rs.getString("hotspot"));
				CShow.sethotspot_explain(rs.getString("hotspot_explain"));
				CShow.setvalue_evaluate(rs.getString("value_evaluate"));
				CShow.setcredit(rs.getString("credit"));
				CShow.setindustry(rs.getString("industry"));
				CShow.setrelation(rs.getString("relation"));
				CShow.sethuman_size(rs.getString("human_size"));
				CShow.setresource(rs.getString("resource"));
				CShow.setremark(rs.getString("remark"));
				CShow.setrecorder(rs.getInt("recorder"));
				CShow.setrecorder_time(rs.getString("recorder_time"));
				CShow.setupdater(rs.getInt("updater"));
				CShow.setupdate_time(rs.getString("update_time"));
				CShow.setself_field1(rs.getString("self_field1"));
				CShow.setself_field2(rs.getString("self_field2"));
				CShow.setself_field3(rs.getString("self_field3"));
				CShow.setself_field4(rs.getString("self_field4"));
				CShow.setself_field5(rs.getString("self_field5"));
				CShow.setself_field6(rs.getString("self_field6"));
				CShow.setself_field7(rs.getString("self_field7"));
				CShow.setself_field8(rs.getString("self_field8"));
				CShow.setstatus(rs.getInt("status"));
				CShow.setlanguage_ver(rs.getString("language_ver"));
				CShow.setcomplaints_remark(rs.getString("complaints_remark"));
				CShow.setmail_date(rs.getString("mail_date"));
				CShow.settel2(rs.getString("tel2"));
				CShow.setmobile2(rs.getString("mobile2"));
				CShow.setmobile3(rs.getString("mobile3"));
				CShow.setself_field9(rs.getString("self_field9"));
				CShow.setself_field10(rs.getString("self_field10"));
				CShow.setself_field11(rs.getString("self_field11"));
				CShow.setself_field12(rs.getString("self_field12"));
				CShow.setself_field13(rs.getString("self_field13"));
				CShow.setself_field14(rs.getString("self_field14"));
				CShow.setself_field15(rs.getString("self_field15"));
				CShow.setself_field16(rs.getString("self_field16"));
				CShow.setself_field17(rs.getString("self_field17"));
				CShow.setself_field18(rs.getString("self_field18"));
				coll.add(CShow);
			}

			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog.error("Error in method select_com_list() of Custom,error:"
					+ ex.getMessage() + ",sql:" + sql);
			// System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
			return coll;
		}
	}

	public String selectsqlanalyse(int st, String language_v,
			String table_head, String filiale, String seller,
			String time_from_to, String time1, String time2, String tj_name1,
			String tj_name2, String tj_name3, String tj_v1, String tj_v2,
			String tj_v3, String tj_v11, String tj_v21, String tj_v31,
			String tj_where, String taxis_name, String taxis_v, String status,
			String dealers) {
		int size = 0;
		int sizepa = 0;
		String sql = "";
		try {
			sql = "SELECT COUNT(*) FROM " + table_head
					+ "custom where language_ver=?";
			String where = " ", where1 = "";
			if ((!tj_v1.equals("")) && (tj_v1 != null)) {
				if (tj_v11.equals("0")) {
					String tj_v1_per = "";
					for (int i = 0; i < tj_v1.length(); i++) {
						tj_v1_per += tj_v1.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name1 + " like '%" + tj_v1_per
							+ "%'";
				} else if (tj_v11.equals("1")) {
					where = where + " and " + tj_name1 + " like '" + tj_v1
							+ "%'";
				} else if (tj_v11.equals("2")) {
					where = where + " and " + tj_name1 + " like '%" + tj_v1
							+ "%'";
				} else if (tj_v11.equals("3")) {
					where = where + " and " + tj_name1 + " not like '%" + tj_v1
							+ "%'";
				} else {
					where = where + " and " + tj_name1 + "='" + tj_v1 + "'";
				}
			}
			if ((!tj_v2.equals("")) && (tj_v2 != null)) {
				if (tj_v21.equals("0")) {
					String tj_v2_per = "";
					for (int i = 0; i < tj_v2.length(); i++) {
						tj_v2_per += tj_v2.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name2 + " like '%" + tj_v2_per
							+ "%'";
				} else if (tj_v21.equals("1")) {
					where = where + " and " + tj_name2 + " like '" + tj_v2
							+ "%'";
				} else if (tj_v21.equals("2")) {
					where = where + " and " + tj_name2 + " like '%" + tj_v2
							+ "%'";
				} else if (tj_v21.equals("3")) {
					where = where + " and " + tj_name2 + " not like '%" + tj_v2
							+ "%'";
				} else {
					where = where + " and " + tj_name2 + "='" + tj_v2 + "'";
				}
			}
			if ((!tj_v3.equals("")) && (tj_v3 != null)) {
				if (tj_v31.equals("0")) {
					String tj_v3_per = "";
					for (int i = 0; i < tj_v3.length(); i++) {
						tj_v3_per += tj_v3.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name3 + " like '%" + tj_v3_per
							+ "%'";
				} else if (tj_v31.equals("1")) {
					where = where + " and " + tj_name3 + " like '" + tj_v3
							+ "%'";
				} else if (tj_v31.equals("2")) {
					where = where + " and " + tj_name3 + " like '%" + tj_v3
							+ "%'";
				} else if (tj_v31.equals("3")) {
					where = where + " and " + tj_name3 + " not like '%" + tj_v3
							+ "%'";
				} else {
					where = where + " and " + tj_name3 + "='" + tj_v3 + "'";
				}
			}
			if ((!filiale.equals("")) && (filiale != null)) {
				where1 = "";
				String[] filialearr = StringUtils.arr(filiale, "+", "0", 20);
				for (int j = 0; j < 20; j++) {
					if (j == 0) {
						where1 = " and (filiale like '%" + filialearr[j] + "%'";
					} else if (!filialearr[j].equals("0")) {
						where1 = where1 + " or filiale like '%" + filialearr[j]
								+ "%'";
					} else {
						break;
					}
				}
				where = where + where1 + ")";
			}
			if ((!seller.equals("")) && (!seller.equals("all"))
					&& (seller != null)) {
				where1 = "";
				String[] sellerarr = StringUtils.arr(seller, "+", "0", 20);
				for (int j = 0; j < 20; j++) {
					if (j == 0) {
						where1 = " and (seller='" + sellerarr[j] + "'";
					} else if (!sellerarr[j].equals("0")) {
						where1 = where1 + " or seller='" + sellerarr[j] + "'";
					} else {
						break;
					}
				}
				where = where + where1 + ")";
			}
			if ((!time1.equals("")) && (time1 != null)) {
				where = where + " and " + time_from_to + ">='" + time1 + "'";
			}
			if ((!time2.equals("")) && (time2 != null)) {
				where = where + " and " + time_from_to + "<='" + time2 + "'";
			}
			if ((!status.equals("")) && (status != null)) {
				where = where + " and status='" + status + "'";
			}

			if (dealers != null && !"".equals(dealers.trim())) {
				where += " and dealers='" + dealers + "' ";
			}

			if ((!tj_where.equals("")) && (tj_where != null)) {
				where = where + tj_where;
			}
			sql = sql + where;
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, language_v);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				size = rs.getInt(1);
				sizepa = (int) size / st;
				if (size % st > 0) {
					sizepa = sizepa + 1;
				}
			}

			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog.error("Error in method selectsqlanalyse() of Custom,error:"
					+ ex.getMessage() + ",sql:" + sql);
			// System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
			return sizepa + "+" + size;
		}
	}

	public String selectsqlanalyse_new(int st, String language_v,
			String table_head, String filiale, String seller,
			String time_from_to, String time1, String time2, String tj_name1,
			String tj_name2, String tj_name3, String tj_v1, String tj_v2,
			String tj_v3, String tj_v11, String tj_v21, String tj_v31,
			String tj_where, String taxis_name, String taxis_v, String status,
			String dealers, String diudanyuanyin, String lianxijilu,String have_introduction) {
		int size = 0;
		int sizepa = 0;
		String sql = "";
		try {
			sql = "SELECT COUNT(*) FROM " + table_head
					+ "custom where language_ver=?";
			String where = " ", where1 = "";
			if ((!tj_v1.equals("")) && (tj_v1 != null)) {
				if (tj_v11.equals("0")) {
					String tj_v1_per = "";
					for (int i = 0; i < tj_v1.length(); i++) {
						tj_v1_per += tj_v1.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name1 + " like '%" + tj_v1_per
							+ "%'";
				} else if (tj_v11.equals("1")) {
					where = where + " and " + tj_name1 + " like '" + tj_v1
							+ "%'";
				} else if (tj_v11.equals("2")) {
					where = where + " and " + tj_name1 + " like '%" + tj_v1
							+ "%'";
				} else if (tj_v11.equals("3")) {
					where = where + " and " + tj_name1 + " not like '%" + tj_v1
							+ "%'";
				} else {
					where = where + " and " + tj_name1 + "='" + tj_v1 + "'";
				}
			}
			if ((!tj_v2.equals("")) && (tj_v2 != null)) {
				if (tj_v21.equals("0")) {
					String tj_v2_per = "";
					for (int i = 0; i < tj_v2.length(); i++) {
						tj_v2_per += tj_v2.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name2 + " like '%" + tj_v2_per
							+ "%'";
				} else if (tj_v21.equals("1")) {
					where = where + " and " + tj_name2 + " like '" + tj_v2
							+ "%'";
				} else if (tj_v21.equals("2")) {
					where = where + " and " + tj_name2 + " like '%" + tj_v2
							+ "%'";
				} else if (tj_v21.equals("3")) {
					where = where + " and " + tj_name2 + " not like '%" + tj_v2
							+ "%'";
				} else {
					where = where + " and " + tj_name2 + "='" + tj_v2 + "'";
				}
			}
			if ((!tj_v3.equals("")) && (tj_v3 != null)) {
				if (tj_v31.equals("0")) {
					String tj_v3_per = "";
					for (int i = 0; i < tj_v3.length(); i++) {
						tj_v3_per += tj_v3.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name3 + " like '%" + tj_v3_per
							+ "%'";
				} else if (tj_v31.equals("1")) {
					where = where + " and " + tj_name3 + " like '" + tj_v3
							+ "%'";
				} else if (tj_v31.equals("2")) {
					where = where + " and " + tj_name3 + " like '%" + tj_v3
							+ "%'";
				} else if (tj_v31.equals("3")) {
					where = where + " and " + tj_name3 + " not like '%" + tj_v3
							+ "%'";
				} else {
					where = where + " and " + tj_name3 + "='" + tj_v3 + "'";
				}
			}
			if ((!filiale.equals("")) && (filiale != null)) {
				where1 = "";
				String[] filialearr = StringUtils.arr(filiale, "+", "0", 20);
				for (int j = 0; j < 20; j++) {
					if (j == 0) {
						where1 = " and (filiale like '%" + filialearr[j] + "%'";
					} else if (!filialearr[j].equals("0")) {
						where1 = where1 + " or filiale like '%" + filialearr[j]
								+ "%'";
					} else {
						break;
					}
				}
				where = where + where1 + ")";
			}
			if ((!seller.equals("")) && (!seller.equals("all"))
					&& (seller != null)) {
				where1 = "";
				String[] sellerarr = StringUtils.arr(seller, "+", "0", 20);
				for (int j = 0; j < 20; j++) {
					if (j == 0) {
						where1 = " and (seller='" + sellerarr[j] + "'";
					} else if (!sellerarr[j].equals("0")) {
						where1 = where1 + " or seller='" + sellerarr[j] + "'";
					} else {
						break;
					}
				}
				where = where + where1 + ")";
			}
			if ((!time1.equals("")) && (time1 != null)) {
				where = where + " and " + time_from_to + ">='" + time1 + "'";
			}
			if ((!time2.equals("")) && (time2 != null)) {
				where = where + " and " + time_from_to + "<='" + time2 + "'";
			}
			if ((!status.equals("")) && (status != null)) {
				where = where + " and status='" + status + "'";
			}

			if (dealers != null && !"".equals(dealers.trim())) {
				where += " and dealers='" + dealers + "' ";
			}

			if ((!tj_where.equals("")) && (tj_where != null)) {
				where = where + tj_where;
			}
			sql = sql + where;

			if (diudanyuanyin != null && !"".equals(diudanyuanyin.trim())) {
				sql += " and customid in (select distinct customid from cana_link where self_field10 like '%"
						+ diudanyuanyin + "%' ) ";
			}
			if ("kong".equals(lianxijilu)) {
				sql += " and customid not in ( select distinct customid from cana_link where status=0 ) ";
			} else if ("bukong".equals(lianxijilu)) {
				sql += " and customid in (select distinct customid from cana_link where status=0 ) ";
			}
			
			if("��".equals(have_introduction)){
				sql+=" and credit is not null and credit<>'' ";
			}else if("��".equals(have_introduction)){
				sql+=" and (credit is null or credit='' ) ";
			}

			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, language_v);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				size = rs.getInt(1);
				sizepa = (int) size / st;
				if (size % st > 0) {
					sizepa = sizepa + 1;
				}
			}

			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog.error("Error in method selectsqlanalyse() of Custom,error:"
					+ ex.getMessage() + ",sql:" + sql);
			// System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
			return sizepa + "+" + size;
		}
	}

	
	public String selectsqlanalyse_new2(int st, String language_v,
			String table_head, String filiale, String seller,
			String time_from_to, String time1, String time2, String tj_name1,
			String tj_name2, String tj_name3, String tj_v1, String tj_v2,
			String tj_v3, String tj_v11, String tj_v21, String tj_v31,
			String tj_where, String taxis_name, String taxis_v, String status,
			String dealers, String diudanyuanyin, String lianxijilu,String have_introduction,
			String userid,String have_power,String have_power_sql) {
		int size = 0;
		int sizepa = 0;
		String sql = "";
		try {
			sql = "SELECT COUNT(*) FROM " + table_head
					+ "custom where language_ver=?";
			String where = " ", where1 = "";
			if ((!tj_v1.equals("")) && (tj_v1 != null)) {
				if (tj_v11.equals("0")) {
					String tj_v1_per = "";
					for (int i = 0; i < tj_v1.length(); i++) {
						tj_v1_per += tj_v1.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					if(tj_name1.equals("self_field49")){
						where=where+" and self_field49='��' and self_field48 like '"+tj_v1_per+"'";
					}else{
					where = where + " and " + tj_name1 + " like '%" + tj_v1_per+ "%'";
					}
							
				} else if (tj_v11.equals("1")) {
					where = where + " and " + tj_name1 + " like '" + tj_v1
							+ "%'";
				} else if (tj_v11.equals("2")) {
					where = where + " and " + tj_name1 + " like '%" + tj_v1
							+ "%'";
				} else if (tj_v11.equals("3")) {
					where = where + " and " + tj_name1 + " not like '%" + tj_v1
							+ "%'";
				} else {
					if(tj_name1.equals("self_field49")){
						where=where+" and self_field49='��' and self_field48 ='"+tj_v1+"'";
					}else{
					where = where + " and " + tj_name1 + "='" + tj_v1 + "'";
					}
				}
			}
			if ((!tj_v2.equals("")) && (tj_v2 != null)) {
				if (tj_v21.equals("0")) {
					String tj_v2_per = "";
					for (int i = 0; i < tj_v2.length(); i++) {
						tj_v2_per += tj_v2.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name2 + " like '%" + tj_v2_per
							+ "%'";
				} else if (tj_v21.equals("1")) {
					where = where + " and " + tj_name2 + " like '" + tj_v2
							+ "%'";
				} else if (tj_v21.equals("2")) {
					where = where + " and " + tj_name2 + " like '%" + tj_v2
							+ "%'";
				} else if (tj_v21.equals("3")) {
					where = where + " and " + tj_name2 + " not like '%" + tj_v2
							+ "%'";
				} else {
					where = where + " and " + tj_name2 + "='" + tj_v2 + "'";
				}
			}
			if ((!tj_v3.equals("")) && (tj_v3 != null)) {
				if (tj_v31.equals("0")) {
					String tj_v3_per = "";
					for (int i = 0; i < tj_v3.length(); i++) {
						tj_v3_per += tj_v3.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name3 + " like '%" + tj_v3_per
							+ "%'";
				} else if (tj_v31.equals("1")) {
					where = where + " and " + tj_name3 + " like '" + tj_v3
							+ "%'";
				} else if (tj_v31.equals("2")) {
					where = where + " and " + tj_name3 + " like '%" + tj_v3
							+ "%'";
				} else if (tj_v31.equals("3")) {
					where = where + " and " + tj_name3 + " not like '%" + tj_v3
							+ "%'";
				} else {
					where = where + " and " + tj_name3 + "='" + tj_v3 + "'";
				}
			}
			if ((!filiale.equals("")) && (filiale != null)) {
				where1 = "";
				String[] filialearr = StringUtils.arr(filiale, "+", "0", 20);
				for (int j = 0; j < 20; j++) {
					if (j == 0) {
						where1 = " and (filiale like '%" + filialearr[j] + "%'";
					} else if (!filialearr[j].equals("0")) {
						where1 = where1 + " or filiale like '%" + filialearr[j]
								+ "%'";
					} else {
						break;
					}
				}
				where = where + where1 + ")";
			}
			if ((!seller.equals("")) && (!seller.equals("all"))
					&& (seller != null)) {
				where1 = "";
				String[] sellerarr = StringUtils.arr(seller, "+", "0", 20);
				for (int j = 0; j < 20; j++) {
					if (j == 0) {
						where1 = " and (seller='" + sellerarr[j] + "'";
					} else if (!sellerarr[j].equals("0")) {
						where1 = where1 + " or seller='" + sellerarr[j] + "'";
					} else {
						break;
					}
				}
				where = where + where1 + ")";
			}
			if ((!time1.equals("")) && (time1 != null)) {
				where = where + " and " + time_from_to + ">='" + time1 + "'";
			}
			if ((!time2.equals("")) && (time2 != null)) {
				where = where + " and " + time_from_to + "<='" + time2 + "'";
			}
			if ((!status.equals("")) && (status != null)) {
				where = where + " and status='" + status + "'";
			}

			if (dealers != null && !"".equals(dealers.trim())) {
				where += " and dealers='" + dealers + "' ";
			}

			if ((!tj_where.equals("")) && (tj_where != null)) {
				where = where + tj_where;
			}
			sql = sql + where;

			if (diudanyuanyin != null && !"".equals(diudanyuanyin.trim())) {
				sql += " and customid in (select distinct customid from cana_link where self_field10 like '%"
						+ diudanyuanyin + "%' ) ";
			}
			if ("kong".equals(lianxijilu)) {
				sql += " and customid not in ( select distinct customid from cana_link where status=0 ) ";
			} else if ("bukong".equals(lianxijilu)) {
				sql += " and customid in (select distinct customid from cana_link where status=0 ) ";
			}
			
			if("��".equals(have_introduction)){
				sql+=" and credit is not null and credit<>'' ";
			}else if("��".equals(have_introduction)){
				sql+=" and (credit is null or credit='' ) ";
			}
			
//			if(have_power!=null&&have_power.indexOf(userid)==-1){
//				sql+=have_power_sql;
//			}
			
			if(have_power!=null&&have_power.indexOf(userid)==-1){
				sql+=" and ( kind<>'����ʵ�ͻ�' or (kind='����ʵ�ͻ�' and (recorder="+userid+" or seller like '%"+userid+"%')) ) ";
			}

			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, language_v);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				size += rs.getInt(1);
			}
			sizepa = (int) size / st;
			if (size % st > 0) {
				sizepa = sizepa + 1;
			}

			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog.error("Error in method selectsqlanalyse() of Custom,error:"
					+ ex.getMessage() + ",sql:" + sql);
			// System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
			return sizepa + "+" + size;
		}
	}
	
	
	public String selectsqlanalyse_yingshou(int st, String language_v,
			String table_head, String filiale, String seller,
			String time_from_to, String time1, String time2, String tj_name1,
			String tj_name2, String tj_name3, String tj_v1, String tj_v2,
			String tj_v3, String tj_v11, String tj_v21, String tj_v31,
			String tj_where, String taxis_name, String taxis_v, String status,
			String dealers,String userid,String where_order) {
		int size = 0;
		int sizepa = 0;
		String sql = "";
		try {
			sql = "SELECT COUNT(distinct customid) FROM " + table_head
					+ "custom where language_ver=?";
			String where = " ", where1 = "";
			if ((!tj_v1.equals("")) && (tj_v1 != null)) {
				if (tj_v11.equals("0")) {
					String tj_v1_per = "";
					for (int i = 0; i < tj_v1.length(); i++) {
						tj_v1_per += tj_v1.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name1 + " like '%" + tj_v1_per
							+ "%'";
				} else if (tj_v11.equals("1")) {
					where = where + " and " + tj_name1 + " like '" + tj_v1
							+ "%'";
				} else if (tj_v11.equals("2")) {
					where = where + " and " + tj_name1 + " like '%" + tj_v1
							+ "%'";
				} else if (tj_v11.equals("3")) {
					where = where + " and " + tj_name1 + " not like '%" + tj_v1
							+ "%'";
				} else {
					where = where + " and " + tj_name1 + "='" + tj_v1 + "'";
				}
			}
			if ((!tj_v2.equals("")) && (tj_v2 != null)) {
				if (tj_v21.equals("0")) {
					String tj_v2_per = "";
					for (int i = 0; i < tj_v2.length(); i++) {
						tj_v2_per += tj_v2.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name2 + " like '%" + tj_v2_per
							+ "%'";
				} else if (tj_v21.equals("1")) {
					where = where + " and " + tj_name2 + " like '" + tj_v2
							+ "%'";
				} else if (tj_v21.equals("2")) {
					where = where + " and " + tj_name2 + " like '%" + tj_v2
							+ "%'";
				} else if (tj_v21.equals("3")) {
					where = where + " and " + tj_name2 + " not like '%" + tj_v2
							+ "%'";
				} else {
					where = where + " and " + tj_name2 + "='" + tj_v2 + "'";
				}
			}
			if ((!tj_v3.equals("")) && (tj_v3 != null)) {
				if (tj_v31.equals("0")) {
					String tj_v3_per = "";
					for (int i = 0; i < tj_v3.length(); i++) {
						tj_v3_per += tj_v3.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name3 + " like '%" + tj_v3_per
							+ "%'";
				} else if (tj_v31.equals("1")) {
					where = where + " and " + tj_name3 + " like '" + tj_v3
							+ "%'";
				} else if (tj_v31.equals("2")) {
					where = where + " and " + tj_name3 + " like '%" + tj_v3
							+ "%'";
				} else if (tj_v31.equals("3")) {
					where = where + " and " + tj_name3 + " not like '%" + tj_v3
							+ "%'";
				} else {
					where = where + " and " + tj_name3 + "='" + tj_v3 + "'";
				}
			}
			if ((!filiale.equals("")) && (filiale != null)) {
				where1 = "";
				String[] filialearr = StringUtils.arr(filiale, "+", "0", 20);
				for (int j = 0; j < 20; j++) {
					if (j == 0) {
						where1 = " and (filiale like '%" + filialearr[j] + "%'";
					} else if (!filialearr[j].equals("0")) {
						where1 = where1 + " or filiale like '%" + filialearr[j]
								+ "%'";
					} else {
						break;
					}
				}
				where = where + where1 + ")";
			}
			if ((!seller.equals("")) && (!seller.equals("all"))
					&& (seller != null)) {
				where1 = "";
				String[] sellerarr = StringUtils.arr(seller, "+", "0", 20);
				for (int j = 0; j < 20; j++) {
					if (j == 0) {
						where1 = " and (seller='" + sellerarr[j] + "'";
					} else if (!sellerarr[j].equals("0")) {
						where1 = where1 + " or seller='" + sellerarr[j] + "'";
					} else {
						break;
					}
				}
				where = where + where1 + ")";
			}
			if ((!time1.equals("")) && (time1 != null)) {
				where = where + " and " + time_from_to + ">='" + time1 + "'";
			}
			if ((!time2.equals("")) && (time2 != null)) {
				where = where + " and " + time_from_to + "<='" + time2 + "'";
			}
			if ((!status.equals("")) && (status != null)) {
				where = where + " and status='" + status + "'";
			}

			if (dealers != null && !"".equals(dealers.trim())) {
				where += " and dealers='" + dealers + "' ";
			}

			if ((!tj_where.equals("")) && (tj_where != null)) {
				where = where + tj_where;
			}
			sql = sql + where;
			sql+=" and customid in (select distinct customid from cana_yingshou where qimo>yishou "+
" union  "+
" select distinct customid  from cana_order where "+  
" self_field16 is not null and self_field16<>'' and recorder_time>='2012-07-19' and status='0' and auditing like '%ͬ��%' "+ where_order +
" group by shoukuan,orderid,customid having sum(pro_total_prices)>shoukuan) " ;

			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, language_v);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				size += rs.getInt(1);
			}
			sizepa = (int) size / st;
			if (size % st > 0) {
				sizepa = sizepa + 1;
			}

			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog.error("Error in method selectsqlanalyse() of Custom,error:"
					+ ex.getMessage() + ",sql:" + sql);
			// System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
			return sizepa + "+" + size;
		}
	}

	
	public Collection selectanalyse(int step, int page, String language_v,
			String table_head, String filiale, String seller,
			String time_from_to, String time1, String time2, String tj_name1,
			String tj_name2, String tj_name3, String tj_v1, String tj_v2,
			String tj_v3, String tj_v11, String tj_v21, String tj_v31,
			String tj_where, String taxis_name, String taxis_v, String status,
			String dealers) {
		Collection coll = new ArrayList();
		int tip = step * (page - 1);
		String sql = "";
		try {
			sql = "SELECT * FROM " + table_head + "custom where language_ver=?";
			String where = " ", where1 = "";
			if ((!tj_v1.equals("")) && (tj_v1 != null)) {
				if (tj_v11.equals("0")) {
					String tj_v1_per = "";
					for (int i = 0; i < tj_v1.length(); i++) {
						tj_v1_per += tj_v1.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name1 + " like '%" + tj_v1_per
							+ "%'";
				} else if (tj_v11.equals("1")) {
					where = where + " and " + tj_name1 + " like '" + tj_v1
							+ "%'";
				} else if (tj_v11.equals("2")) {
					where = where + " and " + tj_name1 + " like '%" + tj_v1
							+ "%'";
				} else if (tj_v11.equals("3")) {
					where = where + " and " + tj_name1 + " not like '%" + tj_v1
							+ "%'";
				} else {
					where = where + " and " + tj_name1 + "='" + tj_v1 + "'";
				}
			}
			if ((!tj_v2.equals("")) && (tj_v2 != null)) {
				if (tj_v21.equals("0")) {
					String tj_v2_per = "";
					for (int i = 0; i < tj_v2.length(); i++) {
						tj_v2_per += tj_v2.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name2 + " like '%" + tj_v2_per
							+ "%'";
				} else if (tj_v21.equals("1")) {
					where = where + " and " + tj_name2 + " like '" + tj_v2
							+ "%'";
				} else if (tj_v21.equals("2")) {
					where = where + " and " + tj_name2 + " like '%" + tj_v2
							+ "%'";
				} else if (tj_v21.equals("3")) {
					where = where + " and " + tj_name2 + " not like '%" + tj_v2
							+ "%'";
				} else {
					where = where + " and " + tj_name2 + "='" + tj_v2 + "'";
				}
			}
			if ((!tj_v3.equals("")) && (tj_v3 != null)) {
				if (tj_v31.equals("0")) {
					String tj_v3_per = "";
					for (int i = 0; i < tj_v3.length(); i++) {
						tj_v3_per += tj_v3.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name3 + " like '%" + tj_v3_per
							+ "%'";
				} else if (tj_v31.equals("1")) {
					where = where + " and " + tj_name3 + " like '" + tj_v3
							+ "%'";
				} else if (tj_v31.equals("2")) {
					where = where + " and " + tj_name3 + " like '%" + tj_v3
							+ "%'";
				} else if (tj_v31.equals("3")) {
					where = where + " and " + tj_name3 + " not like '%" + tj_v3
							+ "%'";
				} else {
					where = where + " and " + tj_name3 + "='" + tj_v3 + "'";
				}
			}
			if ((!filiale.equals("")) && (filiale != null)) {
				where1 = "";
				String[] filialearr = StringUtils.arr(filiale, "+", "0", 20);
				for (int j = 0; j < 20; j++) {
					if (j == 0) {
						where1 = " and (filiale like '%" + filialearr[j] + "%'";
					} else if (!filialearr[j].equals("0")) {
						where1 = where1 + " or filiale like '%" + filialearr[j]
								+ "%'";
					} else {
						break;
					}
				}
				where = where + where1 + ")";
			}
			if ((!seller.equals("")) && (!seller.equals("all"))
					&& (seller != null)) {
				where1 = "";
				String[] sellerarr = StringUtils.arr(seller, "+", "0", 20);
				for (int j = 0; j < 20; j++) {
					if (j == 0) {
						where1 = " and (seller='" + sellerarr[j] + "'";
					} else if (!sellerarr[j].equals("0")) {
						where1 = where1 + " or seller='" + sellerarr[j] + "'";
					} else {
						break;
					}
				}
				where = where + where1 + ")";
			}
			if ((!time1.equals("")) && (time1 != null)) {
				where = where + " and " + time_from_to + ">='" + time1 + "'";
			}
			if ((!time2.equals("")) && (time2 != null)) {
				where = where + " and " + time_from_to + "<='" + time2 + "'";
			}
			if ((!status.equals("")) && (status != null)) {
				where = where + " and status='" + status + "'";
			}

			if (dealers != null && !"".equals(dealers.trim())) {
				where += " and dealers='" + dealers + "' ";
			}
			if ((!tj_where.equals("")) && (tj_where != null)) {
				where = where + tj_where;
			}
			String orderby = " order by id desc";
			if ((!taxis_v.equals("")) && (taxis_v != null)) {
				orderby = " order by " + taxis_name + " " + taxis_v;
			}
			sql = sql + where + orderby;
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, language_v);
			rs = pstmt.executeQuery();
			if (tip <= 0) {
				rs.beforeFirst();
			} else if (!rs.absolute(tip)) {
				rs.beforeFirst();
			}
			for (int i = 1; rs.next() && i <= step; i++) {
				CustomShow CShow = new CustomShow();
				CShow.setid(rs.getInt("id"));
				CShow.setcustomid(rs.getInt("customid"));
				CShow.setserial_num(rs.getString("serial_num"));
				CShow.setname(rs.getString("name"));
				CShow.setname_pinyin(rs.getString("name_pinyin"));
				CShow.setarea1(rs.getString("area1"));
				CShow.setarea2(rs.getString("area2"));
				CShow.setarea3(rs.getString("area3"));
				CShow.setlinkman(rs.getString("linkman"));
				CShow.settel(rs.getString("tel"));
				CShow.setmobile(rs.getString("mobile"));
				CShow.setfax(rs.getString("fax"));
				CShow.setwebsite(rs.getString("website"));
				CShow.setemail(rs.getString("email"));
				CShow.setkind(rs.getString("kind"));
				CShow.setaddress(rs.getString("address"));
				CShow.setzip(rs.getInt("zip"));
				// CShow.setseller(rs.getInt("seller"));
				CShow.setseller(rs.getString("seller"));
				CShow.setfiliale(rs.getString("filiale"));
				CShow.setshare(rs.getString("share"));
				CShow.sethotspot(rs.getString("hotspot"));
				CShow.sethotspot_explain(rs.getString("hotspot_explain"));
				CShow.setvalue_evaluate(rs.getString("value_evaluate"));
				CShow.setcredit(rs.getString("credit"));
				CShow.setindustry(rs.getString("industry"));
				CShow.setrelation(rs.getString("relation"));
				CShow.sethuman_size(rs.getString("human_size"));
				CShow.setresource(rs.getString("resource"));
				CShow.setremark(rs.getString("remark"));
				CShow.setrecorder(rs.getInt("recorder"));
				CShow.setrecorder_time(rs.getString("recorder_time"));
				CShow.setupdater(rs.getInt("updater"));
				CShow.setupdate_time(rs.getString("update_time"));
				CShow.setself_field1(rs.getString("self_field1"));
				CShow.setself_field2(rs.getString("self_field2"));
				CShow.setself_field3(rs.getString("self_field3"));
				CShow.setself_field4(rs.getString("self_field4"));
				CShow.setself_field5(rs.getString("self_field5"));
				CShow.setself_field6(rs.getString("self_field6"));
				CShow.setself_field7(rs.getString("self_field7"));
				CShow.setself_field8(rs.getString("self_field8"));
				CShow.setstatus(rs.getInt("status"));
				CShow.setlanguage_ver(rs.getString("language_ver"));
				CShow.setself_field25(rs.getString("self_field25"));
				CShow.setself_field26(rs.getString("self_field26"));
				CShow.setself_field27(rs.getString("self_field27"));
				CShow.setself_field28(rs.getString("self_field28"));
				CShow.setself_field29(rs.getString("self_field29"));
				CShow.setself_field30(rs.getString("self_field30"));
				CShow.setself_field31(rs.getString("self_field31"));
				CShow.setself_field32(rs.getString("self_field32"));
				CShow.setself_field33(rs.getString("self_field33"));
				CShow.setself_field34(rs.getString("self_field34"));
				CShow.setself_field35(rs.getString("self_field35"));
				CShow.setself_field36(rs.getString("self_field36"));
				CShow.setself_field37(rs.getString("self_field37"));
				CShow.setself_field38(rs.getString("self_field38"));
				
				CShow.setself_field39(rs.getString("self_field39"));
				CShow.setself_field40(rs.getString("self_field40"));
				CShow.setself_field41(rs.getString("self_field41"));
				CShow.setself_field42(rs.getString("self_field42"));
				CShow.setself_field43(rs.getString("self_field43"));
				coll.add(CShow);
			}

			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog.error("Error in method selectsqlanalyse() of Custom,error:"
					+ ex.getMessage() + ",sql:" + sql);
			// System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
			return coll;
		}
	}

	public Collection selectanalyse_new(int step, int page, String language_v,
			String table_head, String filiale, String seller,
			String time_from_to, String time1, String time2, String tj_name1,
			String tj_name2, String tj_name3, String tj_v1, String tj_v2,
			String tj_v3, String tj_v11, String tj_v21, String tj_v31,
			String tj_where, String taxis_name, String taxis_v, String status,
			String dealers, String diudanyuanyin, String lianxijilu,String have_introduction) {
		Collection coll = new ArrayList();
		int tip = step * (page - 1);
		String sql = "";
		try {
			sql = "SELECT * FROM " + table_head + "custom where language_ver=?";
			String where = " ", where1 = "";
			if ((!tj_v1.equals("")) && (tj_v1 != null)) {
				if (tj_v11.equals("0")) {
					String tj_v1_per = "";
					for (int i = 0; i < tj_v1.length(); i++) {
						tj_v1_per += tj_v1.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name1 + " like '%" + tj_v1_per
							+ "%'";
				} else if (tj_v11.equals("1")) {
					where = where + " and " + tj_name1 + " like '" + tj_v1
							+ "%'";
				} else if (tj_v11.equals("2")) {
					where = where + " and " + tj_name1 + " like '%" + tj_v1
							+ "%'";
				} else if (tj_v11.equals("3")) {
					where = where + " and " + tj_name1 + " not like '%" + tj_v1
							+ "%'";
				} else {
					where = where + " and " + tj_name1 + "='" + tj_v1 + "'";
				}
			}
			if ((!tj_v2.equals("")) && (tj_v2 != null)) {
				if (tj_v21.equals("0")) {
					String tj_v2_per = "";
					for (int i = 0; i < tj_v2.length(); i++) {
						tj_v2_per += tj_v2.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name2 + " like '%" + tj_v2_per
							+ "%'";
				} else if (tj_v21.equals("1")) {
					where = where + " and " + tj_name2 + " like '" + tj_v2
							+ "%'";
				} else if (tj_v21.equals("2")) {
					where = where + " and " + tj_name2 + " like '%" + tj_v2
							+ "%'";
				} else if (tj_v21.equals("3")) {
					where = where + " and " + tj_name2 + " not like '%" + tj_v2
							+ "%'";
				} else {
					where = where + " and " + tj_name2 + "='" + tj_v2 + "'";
				}
			}
			if ((!tj_v3.equals("")) && (tj_v3 != null)) {
				if (tj_v31.equals("0")) {
					String tj_v3_per = "";
					for (int i = 0; i < tj_v3.length(); i++) {
						tj_v3_per += tj_v3.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name3 + " like '%" + tj_v3_per
							+ "%'";
				} else if (tj_v31.equals("1")) {
					where = where + " and " + tj_name3 + " like '" + tj_v3
							+ "%'";
				} else if (tj_v31.equals("2")) {
					where = where + " and " + tj_name3 + " like '%" + tj_v3
							+ "%'";
				} else if (tj_v31.equals("3")) {
					where = where + " and " + tj_name3 + " not like '%" + tj_v3
							+ "%'";
				} else {
					where = where + " and " + tj_name3 + "='" + tj_v3 + "'";
				}
			}
			if ((!filiale.equals("")) && (filiale != null)) {
				where1 = "";
				String[] filialearr = StringUtils.arr(filiale, "+", "0", 20);
				for (int j = 0; j < 20; j++) {
					if (j == 0) {
						where1 = " and (filiale like '%" + filialearr[j] + "%'";
					} else if (!filialearr[j].equals("0")) {
						where1 = where1 + " or filiale like '%" + filialearr[j]
								+ "%'";
					} else {
						break;
					}
				}
				where = where + where1 + ")";
			}
			if ((!seller.equals("")) && (!seller.equals("all"))
					&& (seller != null)) {
				where1 = "";
				String[] sellerarr = StringUtils.arr(seller, "+", "0", 20);
				for (int j = 0; j < 20; j++) {
					if (j == 0) {
						where1 = " and (seller='" + sellerarr[j] + "'";
					} else if (!sellerarr[j].equals("0")) {
						where1 = where1 + " or seller='" + sellerarr[j] + "'";
					} else {
						break;
					}
				}
				where = where + where1 + ")";
			}
			if ((!time1.equals("")) && (time1 != null)) {
				where = where + " and " + time_from_to + ">='" + time1 + "'";
			}
			if ((!time2.equals("")) && (time2 != null)) {
				where = where + " and " + time_from_to + "<='" + time2 + "'";
			}
			if ((!status.equals("")) && (status != null)) {
				where = where + " and status='" + status + "'";
			}

			if (dealers != null && !"".equals(dealers.trim())) {
				where += " and dealers='" + dealers + "' ";
			}
			if ((!tj_where.equals("")) && (tj_where != null)) {
				where = where + tj_where;
			}

			if (diudanyuanyin != null && !"".equals(diudanyuanyin.trim())) {
				where += " and customid in (select distinct customid from cana_link where self_field10 like '%"
						+ diudanyuanyin + "%' ) ";
			}
			if ("kong".equals(lianxijilu)) {
				where += " and customid not in ( select distinct customid from cana_link where status=0 ) ";
			} else if ("bukong".equals(lianxijilu)) {
				where += " and customid in (select distinct customid from cana_link where status=0 ) ";
			}
			
			if("��".equals(have_introduction)){
				sql+=" and credit is not null and credit<>'' ";
			}else if("��".equals(have_introduction)){
				sql+=" and (credit is null or credit='' ) ";
			}

			String orderby = " order by id desc";
			if ((!taxis_v.equals("")) && (taxis_v != null)) {
				orderby = " order by " + taxis_name + " " + taxis_v;
			}
			sql = sql + where + orderby;
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, language_v);
			rs = pstmt.executeQuery();
			if (tip <= 0) {
				rs.beforeFirst();
			} else if (!rs.absolute(tip)) {
				rs.beforeFirst();
			}
			for (int i = 1; rs.next() && i <= step; i++) {
				CustomShow CShow = new CustomShow();
				CShow.setid(rs.getInt("id"));
				CShow.setcustomid(rs.getInt("customid"));
				CShow.setserial_num(rs.getString("serial_num"));
				CShow.setname(rs.getString("name"));
				CShow.setname_pinyin(rs.getString("name_pinyin"));
				CShow.setarea1(rs.getString("area1"));
				CShow.setarea2(rs.getString("area2"));
				CShow.setarea3(rs.getString("area3"));
				CShow.setlinkman(rs.getString("linkman"));
				CShow.settel(rs.getString("tel"));
				CShow.setmobile(rs.getString("mobile"));
				CShow.setfax(rs.getString("fax"));
				CShow.setwebsite(rs.getString("website"));
				CShow.setemail(rs.getString("email"));
				CShow.setkind(rs.getString("kind"));
				CShow.setaddress(rs.getString("address"));
				CShow.setzip(rs.getInt("zip"));
				// CShow.setseller(rs.getInt("seller"));
				CShow.setseller(rs.getString("seller"));
				CShow.setfiliale(rs.getString("filiale"));
				CShow.setshare(rs.getString("share"));
				CShow.sethotspot(rs.getString("hotspot"));
				CShow.sethotspot_explain(rs.getString("hotspot_explain"));
				CShow.setvalue_evaluate(rs.getString("value_evaluate"));
				CShow.setcredit(rs.getString("credit"));
				CShow.setindustry(rs.getString("industry"));
				CShow.setrelation(rs.getString("relation"));
				CShow.sethuman_size(rs.getString("human_size"));
				CShow.setresource(rs.getString("resource"));
				CShow.setremark(rs.getString("remark"));
				CShow.setrecorder(rs.getInt("recorder"));
				CShow.setrecorder_time(rs.getString("recorder_time"));
				CShow.setupdater(rs.getInt("updater"));
				CShow.setupdate_time(rs.getString("update_time"));
				CShow.setself_field1(rs.getString("self_field1"));
				CShow.setself_field2(rs.getString("self_field2"));
				CShow.setself_field3(rs.getString("self_field3"));
				CShow.setself_field4(rs.getString("self_field4"));
				CShow.setself_field5(rs.getString("self_field5"));
				CShow.setself_field6(rs.getString("self_field6"));
				CShow.setself_field7(rs.getString("self_field7"));
				CShow.setself_field8(rs.getString("self_field8"));
				CShow.setstatus(rs.getInt("status"));
				CShow.setlanguage_ver(rs.getString("language_ver"));
				CShow.setself_field25(rs.getString("self_field25"));
				CShow.setself_field26(rs.getString("self_field26"));
				CShow.setself_field27(rs.getString("self_field27"));
				CShow.setself_field28(rs.getString("self_field28"));
				CShow.setself_field29(rs.getString("self_field29"));
				CShow.setself_field30(rs.getString("self_field30"));
				CShow.setself_field31(rs.getString("self_field31"));
				CShow.setself_field32(rs.getString("self_field32"));
				CShow.setself_field33(rs.getString("self_field33"));
				CShow.setself_field34(rs.getString("self_field34"));
				CShow.setself_field35(rs.getString("self_field35"));
				CShow.setself_field36(rs.getString("self_field36"));
				CShow.setself_field37(rs.getString("self_field37"));
				CShow.setself_field38(rs.getString("self_field38"));
				
				CShow.setself_field39(rs.getString("self_field39"));
				CShow.setself_field40(rs.getString("self_field40"));
				CShow.setself_field41(rs.getString("self_field41"));
				CShow.setself_field42(rs.getString("self_field42"));
				CShow.setself_field43(rs.getString("self_field43"));
				
				CShow.setself_field44(rs.getInt("self_field44"));
				CShow.setself_field45(rs.getString("self_field45"));
				CShow.setself_field46(rs.getString("self_field46"));
				CShow.setself_field47(rs.getString("self_field47"));
				CShow.setself_field48(rs.getString("self_field48"));
				CShow.setself_field49(rs.getString("self_field49"));
				coll.add(CShow);
			}

			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog.error("Error in method selectsqlanalyse() of Custom,error:"
					+ ex.getMessage() + ",sql:" + sql);
			 System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
			return coll;
		}
	}
	
	
	public Collection selectanalyse_new(int step, int page, String language_v,
			String table_head, String filiale, String seller,
			String time_from_to, String time1, String time2, String tj_name1,
			String tj_name2, String tj_name3, String tj_v1, String tj_v2,
			String tj_v3, String tj_v11, String tj_v21, String tj_v31,
			String tj_where, String taxis_name, String taxis_v, String status,
			String dealers, String diudanyuanyin, String lianxijilu,String have_introduction,
			String userid,String have_power,String have_power_sql) {
		Collection coll = new ArrayList();
		int tip = step * (page - 1);
		String sql = "";
		try {
			sql = "SELECT * FROM " + table_head + "custom where language_ver=?";
			String where = " ", where1 = "";
			if ((!tj_v1.equals("")) && (tj_v1 != null)) {
				if (tj_v11.equals("0")) {
					String tj_v1_per = "";
					for (int i = 0; i < tj_v1.length(); i++) {
						tj_v1_per += tj_v1.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					if(tj_name1.equals("self_field49")){
						where=where+" and self_field49='��' and self_field48 like '"+tj_v1_per+"'";
					}else{
					where = where + " and " + tj_name1 + " like '%" + tj_v1_per+ "%'";
					}
					
				} else if (tj_v11.equals("1")) {
					where = where + " and " + tj_name1 + " like '" + tj_v1
							+ "%'";
				} else if (tj_v11.equals("2")) {
					where = where + " and " + tj_name1 + " like '%" + tj_v1
							+ "%'";
				} else if (tj_v11.equals("3")) {
					where = where + " and " + tj_name1 + " not like '%" + tj_v1
							+ "%'";
				} else {
					if(tj_name1.equals("self_field49")){
						where=where+" and self_field49='��' and self_field48='"+tj_v1+"'";
					}else{
					where = where + " and " + tj_name1 + "='" + tj_v1 + "'";
					}
				}
			}
			if ((!tj_v2.equals("")) && (tj_v2 != null)) {
				if (tj_v21.equals("0")) {
					String tj_v2_per = "";
					for (int i = 0; i < tj_v2.length(); i++) {
						tj_v2_per += tj_v2.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name2 + " like '%" + tj_v2_per
							+ "%'";
				} else if (tj_v21.equals("1")) {
					where = where + " and " + tj_name2 + " like '" + tj_v2
							+ "%'";
				} else if (tj_v21.equals("2")) {
					where = where + " and " + tj_name2 + " like '%" + tj_v2
							+ "%'";
				} else if (tj_v21.equals("3")) {
					where = where + " and " + tj_name2 + " not like '%" + tj_v2
							+ "%'";
				} else {
					where = where + " and " + tj_name2 + "='" + tj_v2 + "'";
				}
			}
			if ((!tj_v3.equals("")) && (tj_v3 != null)) {
				if (tj_v31.equals("0")) {
					String tj_v3_per = "";
					for (int i = 0; i < tj_v3.length(); i++) {
						tj_v3_per += tj_v3.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name3 + " like '%" + tj_v3_per
							+ "%'";
				} else if (tj_v31.equals("1")) {
					where = where + " and " + tj_name3 + " like '" + tj_v3
							+ "%'";
				} else if (tj_v31.equals("2")) {
					where = where + " and " + tj_name3 + " like '%" + tj_v3
							+ "%'";
				} else if (tj_v31.equals("3")) {
					where = where + " and " + tj_name3 + " not like '%" + tj_v3
							+ "%'";
				} else {
					where = where + " and " + tj_name3 + "='" + tj_v3 + "'";
				}
			}
			if ((!filiale.equals("")) && (filiale != null)) {
				where1 = "";
				String[] filialearr = StringUtils.arr(filiale, "+", "0", 20);
				for (int j = 0; j < 20; j++) {
					if (j == 0) {
						where1 = " and (filiale like '%" + filialearr[j] + "%'";
					} else if (!filialearr[j].equals("0")) {
						where1 = where1 + " or filiale like '%" + filialearr[j]
								+ "%'";
					} else {
						break;
					}
				}
				where = where + where1 + ")";
			}
			if ((!seller.equals("")) && (!seller.equals("all"))
					&& (seller != null)) {
				where1 = "";
				String[] sellerarr = StringUtils.arr(seller, "+", "0", 20);
				for (int j = 0; j < 20; j++) {
					if (j == 0) {
						where1 = " and (seller='" + sellerarr[j] + "'";
					} else if (!sellerarr[j].equals("0")) {
						where1 = where1 + " or seller='" + sellerarr[j] + "'";
					} else {
						break;
					}
				}
				where = where + where1 + ")";
			}
			if ((!time1.equals("")) && (time1 != null)) {
				where = where + " and " + time_from_to + ">='" + time1 + "'";
			}
			if ((!time2.equals("")) && (time2 != null)) {
				where = where + " and " + time_from_to + "<='" + time2 + "'";
			}
			if ((!status.equals("")) && (status != null)) {
				where = where + " and status='" + status + "'";
			}

			if (dealers != null && !"".equals(dealers.trim())) {
				where += " and dealers='" + dealers + "' ";
			}
			if ((!tj_where.equals("")) && (tj_where != null)) {
				where = where + tj_where;
			}

			if (diudanyuanyin != null && !"".equals(diudanyuanyin.trim())) {
				where += " and customid in (select distinct customid from cana_link where self_field10 like '%"
						+ diudanyuanyin + "%' ) ";
			}
			if ("kong".equals(lianxijilu)) {
				where += " and customid not in ( select distinct customid from cana_link where status=0 ) ";
			} else if ("bukong".equals(lianxijilu)) {
				where += " and customid in (select distinct customid from cana_link where status=0 ) ";
			}
			
			if("��".equals(have_introduction)){
				sql+=" and credit is not null and credit<>'' ";
			}else if("��".equals(have_introduction)){
				sql+=" and (credit is null or credit='' ) ";
			}

			String orderby = " order by id desc";
			if ((!taxis_v.equals("")) && (taxis_v != null)) {
				orderby = " order by " + taxis_name + " " + taxis_v;
			}
			
//			if(have_power!=null&&have_power.indexOf(userid)==-1){
////				where +=" and cana_custom.kind<>'����ʵ�ͻ�' union all select * from cana_custom where status='0' and kind='����ʵ�ͻ�' and (recorder="+userid+" or seller like '%"+userid+"%') ";
//				where+=have_power_sql;
//			}
			
			sql = sql + where ;
			
			if(have_power!=null&&have_power.indexOf(userid)==-1){
				sql+=" and ( kind<>'����ʵ�ͻ�' or (kind='����ʵ�ͻ�' and (recorder="+userid+" or seller like '%"+userid+"%')) ) ";
			}
			
			sql+=orderby;
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, language_v);
			rs = pstmt.executeQuery();
			if (tip <= 0) {
				rs.beforeFirst();
			} else if (!rs.absolute(tip)) {
				rs.beforeFirst();
			}
			for (int i = 1; rs.next() && i <= step; i++) {
				CustomShow CShow = new CustomShow();
				CShow.setid(rs.getInt("id"));
				CShow.setcustomid(rs.getInt("customid"));
				CShow.setserial_num(rs.getString("serial_num"));
				CShow.setname(rs.getString("name"));
				CShow.setname_pinyin(rs.getString("name_pinyin"));
				CShow.setarea1(rs.getString("area1"));
				CShow.setarea2(rs.getString("area2"));
				CShow.setarea3(rs.getString("area3"));
				CShow.setlinkman(rs.getString("linkman"));
				CShow.settel(rs.getString("tel"));
				CShow.setmobile(rs.getString("mobile"));
				CShow.setfax(rs.getString("fax"));
				CShow.setwebsite(rs.getString("website"));
				CShow.setemail(rs.getString("email"));
				CShow.setkind(rs.getString("kind"));
				CShow.setaddress(rs.getString("address"));
				CShow.setzip(rs.getInt("zip"));
				// CShow.setseller(rs.getInt("seller"));
				CShow.setseller(rs.getString("seller"));
				CShow.setfiliale(rs.getString("filiale"));
				CShow.setshare(rs.getString("share"));
				CShow.sethotspot(rs.getString("hotspot"));
				CShow.sethotspot_explain(rs.getString("hotspot_explain"));
				CShow.setvalue_evaluate(rs.getString("value_evaluate"));
				CShow.setcredit(rs.getString("credit"));
				CShow.setindustry(rs.getString("industry"));
				CShow.setrelation(rs.getString("relation"));
				CShow.sethuman_size(rs.getString("human_size"));
				CShow.setresource(rs.getString("resource"));
				CShow.setremark(rs.getString("remark"));
				CShow.setrecorder(rs.getInt("recorder"));
				CShow.setrecorder_time(rs.getString("recorder_time"));
				CShow.setupdater(rs.getInt("updater"));
				CShow.setupdate_time(rs.getString("update_time"));
				CShow.setself_field1(rs.getString("self_field1"));
				CShow.setself_field2(rs.getString("self_field2"));
				CShow.setself_field3(rs.getString("self_field3"));
				CShow.setself_field4(rs.getString("self_field4"));
				CShow.setself_field5(rs.getString("self_field5"));
				CShow.setself_field6(rs.getString("self_field6"));
				CShow.setself_field7(rs.getString("self_field7"));
				CShow.setself_field8(rs.getString("self_field8"));
				CShow.setstatus(rs.getInt("status"));
				CShow.setlanguage_ver(rs.getString("language_ver"));
				CShow.setself_field25(rs.getString("self_field25"));
				CShow.setself_field26(rs.getString("self_field26"));
				CShow.setself_field27(rs.getString("self_field27"));
				CShow.setself_field28(rs.getString("self_field28"));
				CShow.setself_field29(rs.getString("self_field29"));
				CShow.setself_field30(rs.getString("self_field30"));
				CShow.setself_field31(rs.getString("self_field31"));
				CShow.setself_field32(rs.getString("self_field32"));
				CShow.setself_field33(rs.getString("self_field33"));
				CShow.setself_field34(rs.getString("self_field34"));
				CShow.setself_field35(rs.getString("self_field35"));
				CShow.setself_field36(rs.getString("self_field36"));
				CShow.setself_field37(rs.getString("self_field37"));
				CShow.setself_field38(rs.getString("self_field38"));
				
				CShow.setself_field39(rs.getString("self_field39"));
				CShow.setself_field40(rs.getString("self_field40"));
				CShow.setself_field41(rs.getString("self_field41"));
				CShow.setself_field42(rs.getString("self_field42"));
				CShow.setself_field43(rs.getString("self_field43"));
				
				CShow.setself_field44(rs.getInt("self_field44"));
				CShow.setself_field45(rs.getString("self_field45"));
				CShow.setself_field46(rs.getString("self_field46"));
				CShow.setself_field47(rs.getString("self_field47"));
				CShow.setself_field48(rs.getString("self_field48"));
				CShow.setself_field49(rs.getString("self_field49"));
				coll.add(CShow);
			}

			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog.error("Error in method selectsqlanalyse() of Custom,error:"
					+ ex.getMessage() + ",sql:" + sql);
			 System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
			return coll;
		}
	}
	
	
	public Collection selectanalyse_yingshou(String begindate,int step, int page, String language_v,
			String table_head, String filiale, String seller,
			String time_from_to, String time1, String time2, String tj_name1,
			String tj_name2, String tj_name3, String tj_v1, String tj_v2,
			String tj_v3, String tj_v11, String tj_v21, String tj_v31,
			String tj_where, String taxis_name, String taxis_v, String status,
			String dealers,String userid,String where_order) {
		Collection coll = new ArrayList();
		int tip = step * (page - 1);
		String sql = "";
		try {
			sql = "SELECT distinct customid FROM " + table_head + "custom where language_ver=?";
			String where = " ", where1 = "";
			if ((!tj_v1.equals("")) && (tj_v1 != null)) {
				if (tj_v11.equals("0")) {
					String tj_v1_per = "";
					for (int i = 0; i < tj_v1.length(); i++) {
						tj_v1_per += tj_v1.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name1 + " like '%" + tj_v1_per
							+ "%'";
				} else if (tj_v11.equals("1")) {
					where = where + " and " + tj_name1 + " like '" + tj_v1
							+ "%'";
				} else if (tj_v11.equals("2")) {
					where = where + " and " + tj_name1 + " like '%" + tj_v1
							+ "%'";
				} else if (tj_v11.equals("3")) {
					where = where + " and " + tj_name1 + " not like '%" + tj_v1
							+ "%'";
				} else {
					where = where + " and " + tj_name1 + "='" + tj_v1 + "'";
				}
			}
			if ((!tj_v2.equals("")) && (tj_v2 != null)) {
				if (tj_v21.equals("0")) {
					String tj_v2_per = "";
					for (int i = 0; i < tj_v2.length(); i++) {
						tj_v2_per += tj_v2.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name2 + " like '%" + tj_v2_per
							+ "%'";
				} else if (tj_v21.equals("1")) {
					where = where + " and " + tj_name2 + " like '" + tj_v2
							+ "%'";
				} else if (tj_v21.equals("2")) {
					where = where + " and " + tj_name2 + " like '%" + tj_v2
							+ "%'";
				} else if (tj_v21.equals("3")) {
					where = where + " and " + tj_name2 + " not like '%" + tj_v2
							+ "%'";
				} else {
					where = where + " and " + tj_name2 + "='" + tj_v2 + "'";
				}
			}
			if ((!tj_v3.equals("")) && (tj_v3 != null)) {
				if (tj_v31.equals("0")) {
					String tj_v3_per = "";
					for (int i = 0; i < tj_v3.length(); i++) {
						tj_v3_per += tj_v3.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name3 + " like '%" + tj_v3_per
							+ "%'";
				} else if (tj_v31.equals("1")) {
					where = where + " and " + tj_name3 + " like '" + tj_v3
							+ "%'";
				} else if (tj_v31.equals("2")) {
					where = where + " and " + tj_name3 + " like '%" + tj_v3
							+ "%'";
				} else if (tj_v31.equals("3")) {
					where = where + " and " + tj_name3 + " not like '%" + tj_v3
							+ "%'";
				} else {
					where = where + " and " + tj_name3 + "='" + tj_v3 + "'";
				}
			}
			if ((!filiale.equals("")) && (filiale != null)) {
				where1 = "";
				String[] filialearr = StringUtils.arr(filiale, "+", "0", 20);
				for (int j = 0; j < 20; j++) {
					if (j == 0) {
						where1 = " and (filiale like '%" + filialearr[j] + "%'";
					} else if (!filialearr[j].equals("0")) {
						where1 = where1 + " or filiale like '%" + filialearr[j]
								+ "%'";
					} else {
						break;
					}
				}
				where = where + where1 + ")";
			}
			if ((!seller.equals("")) && (!seller.equals("all"))
					&& (seller != null)) {
				where1 = "";
				String[] sellerarr = StringUtils.arr(seller, "+", "0", 20);
				for (int j = 0; j < 20; j++) {
					if (j == 0) {
						where1 = " and (seller='" + sellerarr[j] + "'";
					} else if (!sellerarr[j].equals("0")) {
						where1 = where1 + " or seller='" + sellerarr[j] + "'";
					} else {
						break;
					}
				}
				where = where + where1 + ")";
			}
			if ((!time1.equals("")) && (time1 != null)) {
				where = where + " and " + time_from_to + ">='" + time1 + "'";
			}
			if ((!time2.equals("")) && (time2 != null)) {
				where = where + " and " + time_from_to + "<='" + time2 + "'";
			}
			if ((!status.equals("")) && (status != null)) {
				where = where + " and status='" + status + "'";
			}

			if (dealers != null && !"".equals(dealers.trim())) {
				where += " and dealers='" + dealers + "' ";
			}
			if ((!tj_where.equals("")) && (tj_where != null)) {
				where = where + tj_where;
			}
			String orderby = " order by id desc";
			if ((!taxis_v.equals("")) && (taxis_v != null)) {
				orderby = " order by " + taxis_name + " " + taxis_v;
			}
			sql = sql + where  ;
			sql+=" and customid in (select distinct customid from cana_yingshou where qimo>yishou "+
			" union  "+
			" select distinct customid  from cana_order where "+  
			" self_field16 is not null  and self_field16<>'' and recorder_time>='2012-07-19' and status='0' and auditing like '%ͬ��%' "+ where_order+
			" group by shoukuan,orderid,customid having sum(pro_total_prices)>shoukuan) " ;
			sql+=orderby;
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, language_v);
			rs = pstmt.executeQuery();
			if (tip <= 0) {
				rs.beforeFirst();
			} else if (!rs.absolute(tip)) {
				rs.beforeFirst();
			}
			Custom_charge cc=new Custom_charge();
			for (int i = 1; rs.next() && i <= step; i++) {
				String customid=rs.getString("customid");
				Bean bean=cc.getCustomMoney(customid, begindate, con);
				coll.add(bean);
			}
			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog.error("Error in method selectsqlanalyse() of Custom,error:"
					+ ex.getMessage() + ",sql:" + sql);
			 System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
			return coll;
		}
	}

	public String selectsqlanalyse_nobuy(int st, String language_v,
			String table_head, String filiale, String seller,
			String time_from_to, String time1, String time2, String tj_name1,
			String tj_name2, String tj_name3, String tj_v1, String tj_v2,
			String tj_v3, String tj_v11, String tj_v21, String tj_v31,
			String tj_where, String taxis_name, String taxis_v, String status) {
		int size = 0;
		int sizepa = 0;
		String sql = "";
		try {
			sql = "SELECT COUNT(*) FROM "
					+ table_head
					+ "custom where language_ver=? and "
					+ "(self_field3<>'' or value_evaluate<>'' or credit<>'' or relation<>'' or human_size<>'' or hotspot_explain not like '--%')";
			String where = " ", where1 = "";
			if ((!tj_v1.equals("")) && (tj_v1 != null)) {
				if (tj_v11.equals("0")) {
					String tj_v1_per = "";
					for (int i = 0; i < tj_v1.length(); i++) {
						tj_v1_per += tj_v1.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name1 + " like '%" + tj_v1_per
							+ "%'";
				} else if (tj_v11.equals("1")) {
					where = where + " and " + tj_name1 + " like '" + tj_v1
							+ "%'";
				} else if (tj_v11.equals("2")) {
					where = where + " and " + tj_name1 + " like '%" + tj_v1
							+ "%'";
				} else if (tj_v11.equals("3")) {
					where = where + " and " + tj_name1 + " not like '%" + tj_v1
							+ "%'";
				} else {
					where = where + " and " + tj_name1 + "='" + tj_v1 + "'";
				}
			}
			if ((!tj_v2.equals("")) && (tj_v2 != null)) {
				if (tj_v21.equals("0")) {
					String tj_v2_per = "";
					for (int i = 0; i < tj_v2.length(); i++) {
						tj_v2_per += tj_v2.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name2 + " like '%" + tj_v2_per
							+ "%'";
				} else if (tj_v21.equals("1")) {
					where = where + " and " + tj_name2 + " like '" + tj_v2
							+ "%'";
				} else if (tj_v21.equals("2")) {
					where = where + " and " + tj_name2 + " like '%" + tj_v2
							+ "%'";
				} else if (tj_v21.equals("3")) {
					where = where + " and " + tj_name2 + " not like '%" + tj_v2
							+ "%'";
				} else {
					where = where + " and " + tj_name2 + "='" + tj_v2 + "'";
				}
			}
			if ((!tj_v3.equals("")) && (tj_v3 != null)) {
				if (tj_v31.equals("0")) {
					String tj_v3_per = "";
					for (int i = 0; i < tj_v3.length(); i++) {
						tj_v3_per += tj_v3.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name3 + " like '%" + tj_v3_per
							+ "%'";
				} else if (tj_v31.equals("1")) {
					where = where + " and " + tj_name3 + " like '" + tj_v3
							+ "%'";
				} else if (tj_v31.equals("2")) {
					where = where + " and " + tj_name3 + " like '%" + tj_v3
							+ "%'";
				} else if (tj_v31.equals("3")) {
					where = where + " and " + tj_name3 + " not like '%" + tj_v3
							+ "%'";
				} else {
					where = where + " and " + tj_name3 + "='" + tj_v3 + "'";
				}
			}
			if ((!filiale.equals("")) && (filiale != null)) {
				where1 = "";
				String[] filialearr = StringUtils.arr(filiale, "+", "0", 20);
				for (int j = 0; j < 20; j++) {
					if (j == 0) {
						where1 = " and (filiale like '%" + filialearr[j] + "%'";
					} else if (!filialearr[j].equals("0")) {
						where1 = where1 + " or filiale like '%" + filialearr[j]
								+ "%'";
					} else {
						break;
					}
				}
				where = where + where1 + ")";
			}
			if ((!seller.equals("")) && (!seller.equals("all"))
					&& (seller != null)) {
				where1 = "";
				String[] sellerarr = StringUtils.arr(seller, "+", "0", 20);
				for (int j = 0; j < 20; j++) {
					if (j == 0) {
						where1 = " and (seller='" + sellerarr[j] + "'";
					} else if (!sellerarr[j].equals("0")) {
						where1 = where1 + " or seller='" + sellerarr[j] + "'";
					} else {
						break;
					}
				}
				where = where + where1 + ")";
			}
			if ((!time1.equals("")) && (time1 != null)) {
				where = where + " and " + time_from_to + ">='" + time1 + "'";
			}
			if ((!time2.equals("")) && (time2 != null)) {
				where = where + " and " + time_from_to + "<='" + time2 + "'";
			}
			if ((!status.equals("")) && (status != null)) {
				where = where + " and status='" + status + "'";
			}
			if ((!tj_where.equals("")) && (tj_where != null)) {
				where = where + tj_where;
			}
			sql = sql + where;
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, language_v);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				size = rs.getInt(1);
				sizepa = (int) size / st;
				if (size % st > 0) {
					sizepa = sizepa + 1;
				}
			}

			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog
					.error("Error in method selectsqlanalyse_nobuy() of Custom,error:"
							+ ex.getMessage() + ",sql:" + sql);
			// System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
			return sizepa + "+" + size;
		}
	}

	public Collection selectanalyse_nobuy(int step, int page,
			String language_v, String table_head, String filiale,
			String seller, String time_from_to, String time1, String time2,
			String tj_name1, String tj_name2, String tj_name3, String tj_v1,
			String tj_v2, String tj_v3, String tj_v11, String tj_v21,
			String tj_v31, String tj_where, String taxis_name, String taxis_v,
			String status) {
		Collection coll = new ArrayList();
		int tip = step * (page - 1);
		String sql = "";
		try {
			sql = "SELECT * FROM "
					+ table_head
					+ "custom where language_ver=? and "
					+ "(self_field3<>'' or value_evaluate<>'' or credit<>'' or relation<>'' or human_size<>'' or hotspot_explain not like '--%')";
			String where = " ", where1 = "";
			if ((!tj_v1.equals("")) && (tj_v1 != null)) {
				if (tj_v11.equals("0")) {
					String tj_v1_per = "";
					for (int i = 0; i < tj_v1.length(); i++) {
						tj_v1_per += tj_v1.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name1 + " like '%" + tj_v1_per
							+ "%'";
				} else if (tj_v11.equals("1")) {
					where = where + " and " + tj_name1 + " like '" + tj_v1
							+ "%'";
				} else if (tj_v11.equals("2")) {
					where = where + " and " + tj_name1 + " like '%" + tj_v1
							+ "%'";
				} else if (tj_v11.equals("3")) {
					where = where + " and " + tj_name1 + " not like '%" + tj_v1
							+ "%'";
				} else {
					where = where + " and " + tj_name1 + "='" + tj_v1 + "'";
				}
			}
			if ((!tj_v2.equals("")) && (tj_v2 != null)) {
				if (tj_v21.equals("0")) {
					String tj_v2_per = "";
					for (int i = 0; i < tj_v2.length(); i++) {
						tj_v2_per += tj_v2.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name2 + " like '%" + tj_v2_per
							+ "%'";
				} else if (tj_v21.equals("1")) {
					where = where + " and " + tj_name2 + " like '" + tj_v2
							+ "%'";
				} else if (tj_v21.equals("2")) {
					where = where + " and " + tj_name2 + " like '%" + tj_v2
							+ "%'";
				} else if (tj_v21.equals("3")) {
					where = where + " and " + tj_name2 + " not like '%" + tj_v2
							+ "%'";
				} else {
					where = where + " and " + tj_name2 + "='" + tj_v2 + "'";
				}
			}
			if ((!tj_v3.equals("")) && (tj_v3 != null)) {
				if (tj_v31.equals("0")) {
					String tj_v3_per = "";
					for (int i = 0; i < tj_v3.length(); i++) {
						tj_v3_per += tj_v3.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name3 + " like '%" + tj_v3_per
							+ "%'";
				} else if (tj_v31.equals("1")) {
					where = where + " and " + tj_name3 + " like '" + tj_v3
							+ "%'";
				} else if (tj_v31.equals("2")) {
					where = where + " and " + tj_name3 + " like '%" + tj_v3
							+ "%'";
				} else if (tj_v31.equals("3")) {
					where = where + " and " + tj_name3 + " not like '%" + tj_v3
							+ "%'";
				} else {
					where = where + " and " + tj_name3 + "='" + tj_v3 + "'";
				}
			}
			if ((!filiale.equals("")) && (filiale != null)) {
				where1 = "";
				String[] filialearr = StringUtils.arr(filiale, "+", "0", 20);
				for (int j = 0; j < 20; j++) {
					if (j == 0) {
						where1 = " and (filiale like '%" + filialearr[j] + "%'";
					} else if (!filialearr[j].equals("0")) {
						where1 = where1 + " or filiale like '%" + filialearr[j]
								+ "%'";
					} else {
						break;
					}
				}
				where = where + where1 + ")";
			}
			if ((!seller.equals("")) && (!seller.equals("all"))
					&& (seller != null)) {
				where1 = "";
				String[] sellerarr = StringUtils.arr(seller, "+", "0", 20);
				for (int j = 0; j < 20; j++) {
					if (j == 0) {
						where1 = " and (seller='" + sellerarr[j] + "'";
					} else if (!sellerarr[j].equals("0")) {
						where1 = where1 + " or seller='" + sellerarr[j] + "'";
					} else {
						break;
					}
				}
				where = where + where1 + ")";
			}
			if ((!time1.equals("")) && (time1 != null)) {
				where = where + " and " + time_from_to + ">='" + time1 + "'";
			}
			if ((!time2.equals("")) && (time2 != null)) {
				where = where + " and " + time_from_to + "<='" + time2 + "'";
			}
			if ((!status.equals("")) && (status != null)) {
				where = where + " and status='" + status + "'";
			}
			if ((!tj_where.equals("")) && (tj_where != null)) {
				where = where + tj_where;
			}
			String orderby = " order by id desc";
			if ((!taxis_v.equals("")) && (taxis_v != null)) {
				orderby = " order by " + taxis_name + " " + taxis_v;
			}
			sql = sql + where + orderby;
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, language_v);
			rs = pstmt.executeQuery();
			if (tip <= 0) {
				rs.beforeFirst();
			} else if (!rs.absolute(tip)) {
				rs.beforeFirst();
			}
			for (int i = 1; rs.next() && i <= step; i++) {
				CustomShow CShow = new CustomShow();
				CShow.setid(rs.getInt("id"));
				CShow.setcustomid(rs.getInt("customid"));
				CShow.setserial_num(rs.getString("serial_num"));
				CShow.setname(rs.getString("name"));
				CShow.setname_pinyin(rs.getString("name_pinyin"));
				CShow.setarea1(rs.getString("area1"));
				CShow.setarea2(rs.getString("area2"));
				CShow.setarea3(rs.getString("area3"));
				CShow.setlinkman(rs.getString("linkman"));
				CShow.settel(rs.getString("tel"));
				CShow.setmobile(rs.getString("mobile"));
				CShow.setfax(rs.getString("fax"));
				CShow.setwebsite(rs.getString("website"));
				CShow.setemail(rs.getString("email"));
				CShow.setkind(rs.getString("kind"));
				CShow.setaddress(rs.getString("address"));
				CShow.setzip(rs.getInt("zip"));
				// CShow.setseller(rs.getInt("seller"));
				CShow.setseller(rs.getString("seller"));
				CShow.setfiliale(rs.getString("filiale"));
				CShow.setshare(rs.getString("share"));
				CShow.sethotspot(rs.getString("hotspot"));
				CShow.sethotspot_explain(rs.getString("hotspot_explain"));
				CShow.setvalue_evaluate(rs.getString("value_evaluate"));
				CShow.setcredit(rs.getString("credit"));
				CShow.setindustry(rs.getString("industry"));
				CShow.setrelation(rs.getString("relation"));
				CShow.sethuman_size(rs.getString("human_size"));
				CShow.setresource(rs.getString("resource"));
				CShow.setremark(rs.getString("remark"));
				CShow.setrecorder(rs.getInt("recorder"));
				CShow.setrecorder_time(rs.getString("recorder_time"));
				CShow.setupdater(rs.getInt("updater"));
				CShow.setupdate_time(rs.getString("update_time"));
				CShow.setself_field1(rs.getString("self_field1"));
				CShow.setself_field2(rs.getString("self_field2"));
				CShow.setself_field3(rs.getString("self_field3"));
				CShow.setself_field4(rs.getString("self_field4"));
				CShow.setself_field5(rs.getString("self_field5"));
				CShow.setself_field6(rs.getString("self_field6"));
				CShow.setself_field7(rs.getString("self_field7"));
				CShow.setself_field8(rs.getString("self_field8"));
				CShow.setstatus(rs.getInt("status"));
				CShow.setlanguage_ver(rs.getString("language_ver"));
				coll.add(CShow);
			}

			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog
					.error("Error in method selectsqlanalyse_nobuy() of Custom,error:"
							+ ex.getMessage() + ",sql:" + sql);
			// System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
			return coll;
		}
	}

	public Collection selectgroupby(String table_head, String filiale,
			String seller, String time_from_to, String time1, String time2,
			String tj_name1, String tj_name2, String tj_name3, String tj_v1,
			String tj_v2, String tj_v3, String tj_v11, String tj_v21,
			String tj_v31, String tj_where, String groupname, String status) {
		Collection coll = new ArrayList();
		String sql = "";
		try {
			sql = "SELECT count(*) as num," + groupname
					+ " as groupname1 FROM " + table_head + "custom where id>0";
			String where = " ", where1 = "";
			if ((!tj_v1.equals("")) && (tj_v1 != null)) {
				if (tj_v11.equals("0")) {
					String tj_v1_per = "";
					for (int i = 0; i < tj_v1.length(); i++) {
						tj_v1_per += tj_v1.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name1 + " like '%" + tj_v1_per
							+ "%'";
				} else if (tj_v11.equals("1")) {
					where = where + " and " + tj_name1 + " like '" + tj_v1
							+ "%'";
				} else if (tj_v11.equals("2")) {
					where = where + " and " + tj_name1 + " like '%" + tj_v1
							+ "%'";
				} else if (tj_v11.equals("3")) {
					where = where + " and " + tj_name1 + " not like '%" + tj_v1
							+ "%'";
				} else {
					where = where + " and " + tj_name1 + "='" + tj_v1 + "'";
				}
			}
			if ((!tj_v2.equals("")) && (tj_v2 != null)) {
				if (tj_v21.equals("0")) {
					String tj_v2_per = "";
					for (int i = 0; i < tj_v2.length(); i++) {
						tj_v2_per += tj_v2.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name2 + " like '%" + tj_v2_per
							+ "%'";
				} else if (tj_v21.equals("1")) {
					where = where + " and " + tj_name2 + " like '" + tj_v2
							+ "%'";
				} else if (tj_v21.equals("2")) {
					where = where + " and " + tj_name2 + " like '%" + tj_v2
							+ "%'";
				} else if (tj_v21.equals("3")) {
					where = where + " and " + tj_name2 + " not like '%" + tj_v2
							+ "%'";
				} else {
					where = where + " and " + tj_name2 + "='" + tj_v2 + "'";
				}
			}
			if ((!tj_v3.equals("")) && (tj_v3 != null)) {
				if (tj_v31.equals("0")) {
					String tj_v3_per = "";
					for (int i = 0; i < tj_v3.length(); i++) {
						tj_v3_per += tj_v3.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + tj_name3 + " like '%" + tj_v3_per
							+ "%'";
				} else if (tj_v31.equals("1")) {
					where = where + " and " + tj_name3 + " like '" + tj_v3
							+ "%'";
				} else if (tj_v31.equals("2")) {
					where = where + " and " + tj_name3 + " like '%" + tj_v3
							+ "%'";
				} else if (tj_v31.equals("3")) {
					where = where + " and " + tj_name3 + " not like '%" + tj_v3
							+ "%'";
				} else {
					where = where + " and " + tj_name3 + "='" + tj_v3 + "'";
				}
			}
			if ((!filiale.equals("")) && (filiale != null)) {
				where1 = "";
				String[] filialearr = StringUtils.arr(filiale, "+", "0", 20);
				for (int j = 0; j < 20; j++) {
					if (j == 0) {
						where1 = " and (filiale like '%" + filialearr[j] + "%'";
					} else if (!filialearr[j].equals("0")) {
						where1 = where1 + " or filiale like '%" + filialearr[j]
								+ "%'";
					} else {
						break;
					}
				}
				where = where + where1 + ")";
			}
			if ((!seller.equals("")) && (!seller.equals("all"))
					&& (seller != null)) {
				where1 = "";
				String[] sellerarr = StringUtils.arr(seller, "+", "0", 20);
				for (int j = 0; j < 20; j++) {
					if (j == 0) {
						where1 = " and (seller='" + sellerarr[j] + "'";
					} else if (!sellerarr[j].equals("0")) {
						where1 = where1 + " or seller='" + sellerarr[j] + "'";
					} else {
						break;
					}
				}
				where = where + where1 + ")";
			}
			if ((!time1.equals("")) && (time1 != null)) {
				where = where + " and " + time_from_to + ">='" + time1 + "'";
			}
			if ((!time2.equals("")) && (time2 != null)) {
				where = where + " and " + time_from_to + "<='" + time2 + "'";
			}
			if ((!status.equals("")) && (status != null)) {
				where = where + " and status='" + status + "'";
			}
			if ((!tj_where.equals("")) && (tj_where != null)) {
				where = where + tj_where;
			}
			String groupby = " Group by " + groupname;
			sql = sql + where + groupby;
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GroupShow CShow = new GroupShow();
				CShow.setnum(rs.getInt("num"));
				CShow.setgroupname1(rs.getString("groupname1"));
				coll.add(CShow);
			}

			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog.error("Error in method selectgroupby() of Custom,error:"
					+ ex.getMessage() + ",sql:" + sql);
			// System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
			return coll;
		}
	}

	public Collection select_field(String language_v, String table_head) {
		Collection coll = new ArrayList();
		// CustomFieldShow CShowField
		// =(CustomFieldShow)servlet.loadDatabase.hsm.get("customfield");
		String sql = "";
		try {
			sql = "SELECT * FROM " + table_head
					+ "custom_field where language_ver=?";
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, language_v);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CustomFieldShow CShowField = new CustomFieldShow();
				CShowField.setcustomid(rs.getString("customid"));
				CShowField.setserial_num(rs.getString("serial_num"));
				CShowField.setname(rs.getString("name"));
				CShowField.setname_pinyin(rs.getString("name_pinyin"));
				CShowField.setarea1(rs.getString("area1"));
				CShowField.setarea2(rs.getString("area2"));
				CShowField.setarea3(rs.getString("area3"));
				CShowField.setlinkman(rs.getString("linkman"));
				CShowField.settel(rs.getString("tel"));
				CShowField.setmobile(rs.getString("mobile"));
				CShowField.setfax(rs.getString("fax"));
				CShowField.setwebsite(rs.getString("website"));
				CShowField.setemail(rs.getString("email"));
				CShowField.setkind(rs.getString("kind"));
				CShowField.setaddress(rs.getString("address"));
				CShowField.setzip(rs.getString("zip"));
				CShowField.setseller(rs.getString("seller"));
				CShowField.setfiliale(rs.getString("filiale"));
				CShowField.setshare(rs.getString("share"));
				CShowField.sethotspot(rs.getString("hotspot"));
				CShowField.sethotspot_explain(rs.getString("hotspot_explain"));
				CShowField.setvalue_evaluate(rs.getString("value_evaluate"));
				CShowField.setcredit(rs.getString("credit"));
				CShowField.setindustry(rs.getString("industry"));
				CShowField.setrelation(rs.getString("relation"));
				CShowField.sethuman_size(rs.getString("human_size"));
				CShowField.setresource(rs.getString("resource"));
				CShowField.setremark(rs.getString("remark"));
				CShowField.setrecorder(rs.getString("recorder"));
				CShowField.setrecorder_time(rs.getString("recorder_time"));
				CShowField.setupdater(rs.getString("updater"));
				CShowField.setupdate_time(rs.getString("update_time"));
				CShowField.setself_field1(rs.getString("self_field1"));
				CShowField.setself_field2(rs.getString("self_field2"));
				CShowField.setself_field3(rs.getString("self_field3"));
				CShowField.setself_field4(rs.getString("self_field4"));
				CShowField.setself_field5(rs.getString("self_field5"));
				CShowField.setself_field6(rs.getString("self_field6"));
				CShowField.setself_field7(rs.getString("self_field7"));
				CShowField.setself_field8(rs.getString("self_field8"));
				CShowField.setstatus(rs.getString("status"));
				CShowField.setlanguage_ver(rs.getString("language_ver"));
				CShowField.setcomplaints_remark(rs
						.getString("complaints_remark"));
				CShowField.setmail_date(rs.getString("mail_date"));
				CShowField.settel2(rs.getString("tel2"));
				CShowField.setmobile2(rs.getString("mobile2"));
				CShowField.setmobile3(rs.getString("mobile3"));
				CShowField.setself_field9(rs.getString("self_field9"));
				CShowField.setself_field10(rs.getString("self_field10"));
				CShowField.setself_field11(rs.getString("self_field11"));
				CShowField.setself_field12(rs.getString("self_field12"));
				CShowField.setself_field13(rs.getString("self_field13"));
				CShowField.setself_field14(rs.getString("self_field14"));
				CShowField.setself_field15(rs.getString("self_field15"));
				CShowField.setself_field16(rs.getString("self_field16"));
				CShowField.setself_field17(rs.getString("self_field17"));
				CShowField.setself_field18(rs.getString("self_field18"));
				CShowField.setself_field19(rs.getString("self_field19"));
				CShowField.setself_field20(rs.getString("self_field20"));
				CShowField.setdealers(rs.getString("dealers"));
				CShowField.setself_field21(rs.getString("self_field21"));
				CShowField.setself_field22(rs.getString("self_field22"));
				CShowField.setself_field23(rs.getString("self_field23"));
				CShowField.setself_field24(rs.getString("self_field24"));
				CShowField.setself_field25(rs.getString("self_field25"));
				CShowField.setself_field26(rs.getString("self_field26"));
				CShowField.setself_field27(rs.getString("self_field27"));
				CShowField.setself_field28(rs.getString("self_field28"));
				CShowField.setself_field29(rs.getString("self_field29"));
				CShowField.setself_field30(rs.getString("self_field30"));
				CShowField.setself_field31(rs.getString("self_field31"));
				CShowField.setself_field32(rs.getString("self_field32"));
				CShowField.setself_field33(rs.getString("self_field33"));
				CShowField.setself_field34(rs.getString("self_field34"));
				CShowField.setself_field35(rs.getString("self_field35"));
				CShowField.setself_field36(rs.getString("self_field36"));
				CShowField.setself_field37(rs.getString("self_field37"));
				CShowField.setself_field38(rs.getString("self_field38"));
				
				CShowField.setself_field39(rs.getString("self_field39"));
				CShowField.setself_field40(rs.getString("self_field40"));
				CShowField.setself_field41(rs.getString("self_field41"));
				CShowField.setself_field42(rs.getString("self_field42"));
				CShowField.setself_field43(rs.getString("self_field43"));
				
				CShowField.setself_field44(rs.getString("self_field44"));
				CShowField.setself_field45(rs.getString("self_field45"));
				CShowField.setself_field46(rs.getString("self_field46"));
				CShowField.setself_field47(rs.getString("self_field47"));
				CShowField.setself_field48(rs.getString("self_field48"));
				CShowField.setself_field49(rs.getString("self_field49"));
				CShowField.setself_field50(rs.getString("self_field50"));
				CShowField.setself_field51(rs.getString("self_field51"));
				CShowField.setself_field52(rs.getString("self_field52"));
				CShowField.setself_field53(rs.getString("self_field53"));
				CShowField.setself_field54(rs.getString("self_field54"));
				CShowField.setself_field55(rs.getString("self_field55"));
				CShowField.setself_field56(rs.getString("self_field56"));
				CShowField.setself_field57(rs.getString("self_field57"));
				CShowField.setself_field58(rs.getString("self_field58"));
				CShowField.setself_field59(rs.getString("self_field59"));
				CShowField.setself_field60(rs.getString("self_field60"));
				CShowField.setself_field61(rs.getString("self_field61"));
				CShowField.setself_field62(rs.getString("self_field62"));
				CShowField.setself_field63(rs.getString("self_field63"));
				CShowField.setself_field64(rs.getString("self_field64"));
				CShowField.setself_field65(rs.getString("self_field65"));
				CShowField.setself_field66(rs.getString("self_field66"));
				CShowField.setself_field67(rs.getString("self_field67"));
				CShowField.setself_field68(rs.getString("self_field68"));
				CShowField.setself_field69(rs.getString("self_field69"));
				CShowField.setself_field70(rs.getString("self_field70"));

				
				coll.add(CShowField);
			}

			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog.error("Error in method selectsql_self1() of Custom,error:"
					+ ex.getMessage() + ",sql:" + sql);
			// System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}

			return coll;
		}
	}

	/**
	 * �˷��������¿ͻ���������Ϣself_field51~self_field55,self_field64
	 * @param language_v
	 * @param table_head
	 * @param CShow
	 * @return
	 */
	public boolean updateCustom(String language_v, String table_head,
			CustomShow CShow) {
		String sql = "";
		String sql4log = "";
		try {
			sql = "update "
					+ table_head
					+ "custom set serial_num=?,name=?,name_pinyin=?,area1=?,area2=?,area3=?,linkman=?,tel=?,mobile=?,fax=?,website=?,email=?,kind=?,address=?,zip=?,seller=?,filiale=?,share=?,hotspot=?,hotspot_explain=?,value_evaluate=?,credit=?,industry=?,relation=?,human_size=?,resource=?,remark=?,updater=?,update_time=?,self_field3=?,self_field4=?,self_field5=?,self_field6=?,self_field7=?,self_field8=?,complaints_remark=?,mail_date=?,tel2=?,mobile2=?,mobile3=?,self_field11=?,self_field12=?,self_field13=?,self_field14=?,self_field15=?,self_field16=?,self_field17=?,self_field18=?,self_field19=?,self_field20=?,self_field21=?,self_field22=?,self_field23=?,self_field24=?,self_field25=?,self_field26=?,self_field27=?,self_field28=?,self_field29=?,self_field30=?,self_field31=?,self_field32=?,self_field33=?,self_field34=?,self_field35=?,self_field36=?,self_field37=?,self_field38=?,self_field39=?,self_field40=?,self_field41=?,self_field42=?,self_field43=?,self_field44=?,self_field45=?,self_field46=?,self_field47=?,self_field48=?,self_field49=?,self_field50=?,self_field56=?,self_field57=?,self_field58=?,self_field59=?,self_field60=?,self_field61=?,self_field62=?,self_field63=?,self_field65=?,self_field66=?,self_field67=?,self_field68=?,self_field69=?,self_field70=? where customid=?";
			sql4log = updateConvert(sql, CShow);
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, StringUtils.toChinese(CShow.getserial_num()));
			pstmt.setString(2, CShow.getname());
			pstmt.setString(3, StringUtils.toChinese(CShow.getname_pinyin()));
			pstmt.setString(4, CShow.getarea1());
			pstmt.setString(5, StringUtils.toChinese(CShow.getarea2()));
			pstmt.setString(6, StringUtils.toChinese(CShow.getarea3()));
			pstmt.setString(7, StringUtils.toChinese(CShow.getlinkman()));
			pstmt.setString(8, StringUtils.toChinese(CShow.gettel()));
			pstmt.setString(9, StringUtils.toChinese(CShow.getmobile()));
			pstmt.setString(10, StringUtils.toChinese(CShow.getfax()));
			pstmt.setString(11, StringUtils.toChinese(CShow.getwebsite()));
			pstmt.setString(12, CShow.getemail());
			pstmt.setString(13, StringUtils.toChinese(CShow.getkind()));
			pstmt.setString(14, CShow.getaddress());
			pstmt.setInt(15, CShow.getzip());
			// pstmt.setInt(16, CShow.getseller());
			pstmt.setString(16, CShow.getseller());
			pstmt.setString(17, StringUtils.toChinese(CShow.getfiliale()));
			pstmt.setString(18, StringUtils.toChinese(CShow.getshare()));
			pstmt.setString(19, StringUtils.toChinese(CShow.gethotspot()));
			pstmt.setString(20, StringUtils.toChinese(CShow
					.gethotspot_explain()));
			pstmt.setString(21, StringUtils
					.toChinese(CShow.getvalue_evaluate()));
			pstmt.setString(22, StringUtils.toChinese(CShow.getcredit()));
			pstmt.setString(23, StringUtils.toChinese(CShow.getindustry()));
			pstmt.setString(24, StringUtils.toChinese(CShow.getrelation()));
			pstmt.setString(25, StringUtils.toChinese(CShow.gethuman_size()));
			pstmt.setString(26, StringUtils.toChinese(CShow.getresource()));
			pstmt.setString(27, StringUtils.toChinese(CShow.getremark()));
			pstmt.setInt(28, CShow.getupdater());
			pstmt.setString(29, StringUtils.toChinese(CShow.getupdate_time()));
			pstmt.setString(30, StringUtils.toChinese(CShow.getself_field3()));
			pstmt.setString(31, StringUtils.toChinese(CShow.getself_field4()));
			pstmt.setString(32, StringUtils.toChinese(CShow.getself_field5()));
			pstmt.setString(33, StringUtils.toChinese(CShow.getself_field6()));
			pstmt.setString(34, StringUtils.toChinese(CShow.getself_field7()));
			pstmt.setString(35, StringUtils.toChinese(CShow.getself_field8()));
			pstmt.setString(36, StringUtils.toChinese(CShow
					.getcomplaints_remark()));
			pstmt.setString(37, StringUtils.toChinese(CShow.getmail_date()));
			pstmt.setString(38, StringUtils.toChinese(CShow.gettel2()));
			pstmt.setString(39, StringUtils.toChinese(CShow.getmobile2()));
			pstmt.setString(40, StringUtils.toChinese(CShow.getmobile3()));
			pstmt.setString(41, StringUtils.toChinese(CShow.getself_field11()));
			pstmt.setString(42, StringUtils.toChinese(CShow.getself_field12()));
			pstmt.setString(43, StringUtils.toChinese(CShow.getself_field13()));
			pstmt.setString(44, (CShow.getself_field14()));
			pstmt.setString(45, (CShow.getself_field15()));
			pstmt.setString(46, (CShow.getself_field16()));
			pstmt.setString(47, (CShow.getself_field17()));
			pstmt.setString(48, (CShow.getself_field18()));
			pstmt.setString(49, (CShow.getself_field19()));
			pstmt.setString(50, (CShow.getself_field20()));
			pstmt.setString(51, CShow.getself_field21());
			pstmt.setString(52, CShow.getself_field22());
			pstmt.setString(53, CShow.getself_field23());
			pstmt.setString(54, CShow.getself_field24());
			pstmt.setString(55, CShow.getself_field25());
			pstmt.setString(56, StringUtils.toChinese(CShow.getself_field26()));
			pstmt.setString(57, StringUtils.toChinese(CShow.getself_field27()));
			pstmt.setString(58, StringUtils.toChinese(CShow.getself_field28()));
			pstmt.setString(59, StringUtils.toChinese(CShow.getself_field29()));
			pstmt.setString(60, StringUtils.toChinese(CShow.getself_field30()));
			pstmt.setString(61, StringUtils.toChinese(CShow.getself_field31()));
			pstmt.setString(62, StringUtils.toChinese(CShow.getself_field32()));
			pstmt.setString(63, StringUtils.toChinese(CShow.getself_field33()));
			pstmt.setString(64, StringUtils.toChinese(CShow.getself_field34()));
			pstmt.setString(65, StringUtils.toChinese(CShow.getself_field35()));
			pstmt.setString(66, StringUtils.toChinese(CShow.getself_field36()));
			pstmt.setString(67, StringUtils.toChinese(CShow.getself_field37()));
			pstmt.setString(68, StringUtils.toChinese(CShow.getself_field38()));
			
			pstmt.setString(69, StringUtils.toChinese(CShow.getself_field39()));
			pstmt.setString(70, StringUtils.toChinese(CShow.getself_field40()));
			pstmt.setString(71, StringUtils.toChinese(CShow.getself_field41()));
			pstmt.setString(72, StringUtils.toChinese(CShow.getself_field42()));
			pstmt.setString(73, StringUtils.toChinese(CShow.getself_field43()));
			
			pstmt.setInt(74, (CShow.getself_field44()));
			pstmt.setString(75, StringUtils.toChinese(CShow.getself_field45()));
			pstmt.setString(76, StringUtils.toChinese(CShow.getself_field46()));
			pstmt.setString(77, StringUtils.toChinese(CShow.getself_field47()));
			pstmt.setString(78, StringUtils.toChinese(CShow.getself_field48()));
			pstmt.setString(79, StringUtils.toChinese(CShow.getself_field49()));
			pstmt.setString(80, StringUtils.toChinese(CShow.getself_field50()));
			pstmt.setString(81, StringUtils.toChinese(CShow.getself_field56()));
			pstmt.setString(82, StringUtils.toChinese(CShow.getself_field57()));
			pstmt.setString(83, StringUtils.toChinese(CShow.getself_field58()));
			pstmt.setString(84, StringUtils.toChinese(CShow.getself_field59()));
			pstmt.setString(85, StringUtils.toChinese(CShow.getself_field60()));
			pstmt.setString(86, StringUtils.toChinese(CShow.getself_field61()));
			pstmt.setString(87, StringUtils.toChinese(CShow.getself_field62()));
			pstmt.setString(88, StringUtils.toChinese(CShow.getself_field63()));
			pstmt.setString(89, StringUtils.toChinese(CShow.getself_field65()));
			pstmt.setString(90, StringUtils.toChinese(CShow.getself_field66()));
			pstmt.setString(91, StringUtils.toChinese(CShow.getself_field67()));
			pstmt.setString(92, StringUtils.toChinese(CShow.getself_field68()));
			pstmt.setString(93, StringUtils.toChinese(CShow.getself_field69()));
			pstmt.setString(94, StringUtils.toChinese(CShow.getself_field70()));


			
			pstmt.setInt(95, CShow.getcustomid());
			int result = pstmt.executeUpdate();
			if (result > 0) {
				flag = true;
			} else {
				flag = false;
			}
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
			crmLog.info("Success updatecustom of Custom,operator:"
					+ CShow.getupdater() + ",sql:" + sql4log);
		} catch (Exception ex) {
			crmLog.error("Error in method updatecustom() of Custom,operator:"
					+ CShow.getupdater() + ",error:" + ex.getMessage()
					+ ",sql:" + sql);
			flag = false;
			// ex.printStackTrace();
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
		}
		return flag;
	}
	
	public boolean updateCustomApproveInfo(String language_v, String table_head, CustomShow CShow) {
		String sql = "";
		String sql4log = "";
		try {
			sql = "update "
					+ table_head
					+ "custom set self_field51=?,self_field52=?,self_field53=?,self_field54=?,self_field55=?,self_field64='N' where customid=?";
			sql4log = updateConvert(sql, CShow);
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			pstmt.setString(1, StringUtils.toChinese(CShow.getself_field51()));
			pstmt.setString(2, StringUtils.toChinese(CShow.getself_field52()));
			pstmt.setString(3, StringUtils.toChinese(CShow.getself_field53()));
			pstmt.setString(4, StringUtils.toChinese(CShow.getself_field54()));
			pstmt.setString(5, StringUtils.toChinese(CShow.getself_field55()));

			
			pstmt.setInt(6, CShow.getcustomid());
			int result = pstmt.executeUpdate();
			if (result > 0) {
				flag = true;
			} else {
				flag = false;
			}
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
			crmLog.info("Success updateCustomApproveInfo of Custom,operator:"
					+ CShow.getupdater() + ",sql:" + sql4log);
		} catch (Exception ex) {
			crmLog.error("Error in method updateCustomApproveInfo() of Custom,operator:"
					+ CShow.getupdater() + ",error:" + ex.getMessage()
					+ ",sql:" + sql);
			flag = false;
			// ex.printStackTrace();
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
		}
		return flag;
	}
	
	
	public boolean approveCustom(String language_v, String table_head, String userid, int customid, boolean approve) {
		String sql = "";
		String sql4log = "";
		try {
			sql = "update "
					+ table_head
					+ "custom set self_field64=? where customid=?";
			sql4log = "update "
					+ table_head
					+ "custom set self_field64='"+(approve?"Y":"N")+" where customid="+customid;
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			pstmt.setString(1, approve?"Y":"N");
			pstmt.setInt(2, customid);
			
			int result = pstmt.executeUpdate();
			if (result > 0) {
				flag = true;
			} else {
				flag = false;
			}
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
			crmLog.info("Success approveCustom of Custom,operator:"
					+ userid + ",sql:" + sql4log);
		} catch (Exception ex) {
			crmLog.error("Error in method approveCustom() of Custom,operator:"
					+ userid + ",error:" + ex.getMessage()
					+ ",sql:" + sql);
			flag = false;
			// ex.printStackTrace();
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
		}
		return flag;
	}

	public String addCustom(String language_v, String table_head,
			CustomShow CShow) {
		String SqlNews = "sucess";
		String sql = "";
		String sql4log = "";
		try {
			sql = "insert into "
					+ table_head
					+ "custom(serial_num,name,name_pinyin,area1,area2,area3,linkman,tel,mobile,fax,website,email,kind,address,zip,seller,filiale,share,hotspot,hotspot_explain,value_evaluate,credit,industry,relation,human_size,resource,remark,recorder,recorder_time,self_field3,self_field4,self_field5,self_field6,self_field7,self_field8,customid,language_ver,complaints_remark,mail_date,tel2,mobile2,mobile3,self_field11,self_field13,self_field14,self_field15,self_field16,self_field17,self_field18,self_field19,self_field20,dealers,self_field21,self_field22,self_field23,self_field24,self_field25,self_field26,self_field27,self_field28,self_field29,self_field30,self_field31,self_field32,self_field33,self_field34,self_field35,self_field36,self_field37,self_field38,self_field39,self_field40,self_field41,self_field42,self_field43,self_field44,self_field45,self_field46,self_field47,self_field48,self_field49,self_field50,self_field51,self_field52,self_field53,self_field54,self_field55,self_field56,self_field57,self_field58,self_field59,self_field60,self_field61,self_field62,self_field63,self_field64,self_field65,self_field66,self_field67,self_field68,self_field69,self_field70)"
					+ "values"
					+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			sql4log = insertConvert(sql, CShow);
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, StringUtils.toChinese(CShow.getserial_num()));
			pstmt.setString(2, StringUtils.toChinese(CShow.getname()));
			pstmt.setString(3, StringUtils.toChinese(CShow.getname_pinyin()));
			pstmt.setString(4, CShow.getarea1());
			pstmt.setString(5, StringUtils.toChinese(CShow.getarea2()));
			pstmt.setString(6, StringUtils.toChinese(CShow.getarea3()));
			pstmt.setString(7, StringUtils.toChinese(CShow.getlinkman()));
			pstmt.setString(8, StringUtils.toChinese(CShow.gettel()));
			pstmt.setString(9, StringUtils.toChinese(CShow.getmobile()));
			pstmt.setString(10, StringUtils.toChinese(CShow.getfax()));
			pstmt.setString(11, StringUtils.toChinese(CShow.getwebsite()));
			pstmt.setString(12, CShow.getemail());
			pstmt.setString(13, StringUtils.toChinese(CShow.getkind()));
			pstmt.setString(14, CShow.getaddress());
			pstmt.setInt(15, CShow.getzip());
			// pstmt.setInt(16, CShow.getseller());
			pstmt.setString(16, CShow.getseller());
			pstmt.setString(17, StringUtils.toChinese(CShow.getfiliale()));
			pstmt.setString(18, StringUtils.toChinese(CShow.getshare()));
			pstmt.setString(19, StringUtils.toChinese(CShow.gethotspot()));
			pstmt.setString(20, StringUtils.toChinese(CShow
					.gethotspot_explain()));
			pstmt.setString(21, StringUtils
					.toChinese(CShow.getvalue_evaluate()));
			pstmt.setString(22, StringUtils.toChinese(CShow.getcredit()));
			pstmt.setString(23, StringUtils.toChinese(CShow.getindustry()));
			pstmt.setString(24, StringUtils.toChinese(CShow.getrelation()));
			pstmt.setString(25, StringUtils.toChinese(CShow.gethuman_size()));
			pstmt.setString(26, StringUtils.toChinese(CShow.getresource()));
			pstmt.setString(27, StringUtils.toChinese(CShow.getremark()));
			pstmt.setInt(28, CShow.getrecorder());
			pstmt
					.setString(29, StringUtils.toChinese(CShow
							.getrecorder_time()));
			pstmt.setString(30, StringUtils.toChinese(CShow.getself_field3()));
			pstmt.setString(31, StringUtils.toChinese(CShow.getself_field4()));
			pstmt.setString(32, StringUtils.toChinese(CShow.getself_field5()));
			pstmt.setString(33, StringUtils.toChinese(CShow.getself_field6()));
			pstmt.setString(34, StringUtils.toChinese(CShow.getself_field7()));
			pstmt.setString(35, StringUtils.toChinese(CShow.getself_field8()));
			pstmt.setInt(36, CShow.getcustomid());
			pstmt.setString(37, language_v);
			pstmt.setString(38, StringUtils.toChinese(CShow
					.getcomplaints_remark()));
			pstmt.setString(39, StringUtils.toChinese(CShow.getmail_date()));
			pstmt.setString(40, StringUtils.toChinese(CShow.gettel2()));
			pstmt.setString(41, StringUtils.toChinese(CShow.getmobile2()));
			pstmt.setString(42, StringUtils.toChinese(CShow.getmobile3()));
			pstmt.setString(43, StringUtils.toChinese(CShow.getself_field11()));
			pstmt.setString(44, StringUtils.toChinese(CShow.getself_field13()));
			pstmt.setString(45, CShow.getself_field14());
			pstmt.setString(46, CShow.getself_field15());
			pstmt.setString(47, CShow.getself_field16());
			pstmt.setString(48, CShow.getself_field17());
			pstmt.setString(49, CShow.getself_field18());
			pstmt.setString(50, CShow.getself_field19());
			pstmt.setString(51, CShow.getself_field20());
			pstmt.setString(52, CShow.getdealers());
			pstmt.setString(53, CShow.getself_field21());
			pstmt.setString(54, CShow.getself_field22());
			pstmt.setString(55, CShow.getself_field23());
			pstmt.setString(56, CShow.getself_field24());
			pstmt.setString(57, CShow.getself_field25());

			pstmt.setString(58, StringUtils.toChinese(CShow.getself_field26()));
			pstmt.setString(59, StringUtils.toChinese(CShow.getself_field27()));
			pstmt.setString(60, StringUtils.toChinese(CShow.getself_field28()));
			pstmt.setString(61, StringUtils.toChinese(CShow.getself_field29()));
			pstmt.setString(62, StringUtils.toChinese(CShow.getself_field30()));
			pstmt.setString(63, StringUtils.toChinese(CShow.getself_field31()));
			pstmt.setString(64, StringUtils.toChinese(CShow.getself_field32()));
			pstmt.setString(65, StringUtils.toChinese(CShow.getself_field33()));
			pstmt.setString(66, StringUtils.toChinese(CShow.getself_field34()));
			pstmt.setString(67, StringUtils.toChinese(CShow.getself_field35()));
			pstmt.setString(68, StringUtils.toChinese(CShow.getself_field36()));
			pstmt.setString(69, StringUtils.toChinese(CShow.getself_field37()));
			pstmt.setString(70, StringUtils.toChinese(CShow.getself_field38()));
			
			pstmt.setString(71, StringUtils.toChinese(CShow.getself_field39()));
			pstmt.setString(72, StringUtils.toChinese(CShow.getself_field40()));
			pstmt.setString(73, StringUtils.toChinese(CShow.getself_field41()));
			pstmt.setString(74, StringUtils.toChinese(CShow.getself_field42()));
			pstmt.setString(75, StringUtils.toChinese(CShow.getself_field43()));
			
			pstmt.setInt(76, (CShow.getself_field44()));
			pstmt.setString(77, StringUtils.toChinese(CShow.getself_field45()));
			pstmt.setString(78, StringUtils.toChinese(CShow.getself_field46()));
			pstmt.setString(79, StringUtils.toChinese(CShow.getself_field47()));
			pstmt.setString(80, StringUtils.toChinese(CShow.getself_field48()));
			pstmt.setString(81, StringUtils.toChinese(CShow.getself_field49()));
			pstmt.setString(82, StringUtils.toChinese(CShow.getself_field50()));
			pstmt.setString(83, StringUtils.toChinese(CShow.getself_field51()));
			pstmt.setString(84, StringUtils.toChinese(CShow.getself_field52()));
			pstmt.setString(85, StringUtils.toChinese(CShow.getself_field53()));
			pstmt.setString(86, StringUtils.toChinese(CShow.getself_field54()));
			pstmt.setString(87, StringUtils.toChinese(CShow.getself_field55()));
			pstmt.setString(88, StringUtils.toChinese(CShow.getself_field56()));
			pstmt.setString(89, StringUtils.toChinese(CShow.getself_field57()));
			pstmt.setString(90, StringUtils.toChinese(CShow.getself_field58()));
			pstmt.setString(91, StringUtils.toChinese(CShow.getself_field59()));
			pstmt.setString(92, StringUtils.toChinese(CShow.getself_field60()));
			pstmt.setString(93, StringUtils.toChinese(CShow.getself_field61()));
			pstmt.setString(94, StringUtils.toChinese(CShow.getself_field62()));
			pstmt.setString(95, StringUtils.toChinese(CShow.getself_field63()));
			pstmt.setString(96, StringUtils.toChinese(CShow.getself_field64()));
			pstmt.setString(97, StringUtils.toChinese(CShow.getself_field65()));
			pstmt.setString(98, StringUtils.toChinese(CShow.getself_field66()));
			pstmt.setString(99, StringUtils.toChinese(CShow.getself_field67()));
			pstmt.setString(100, StringUtils.toChinese(CShow.getself_field68()));
			pstmt.setString(101, StringUtils.toChinese(CShow.getself_field69()));
			pstmt.setString(102, StringUtils.toChinese(CShow.getself_field70()));


			int result = pstmt.executeUpdate();
			if (result > 0) {
				SqlNews = "sucess";
			} else {
				SqlNews = "b";
			}
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
			crmLog.info("Success addcustom of custom,operator:"
					+ CShow.getupdater() + ",sql:" + sql4log);
		} catch (SQLException ex) {
			SqlNews = "Error!" + ex;
			// crmLog.error("Error in method addcustom() of
			// custom,operator:"+CShow.getupdater()+",error:"+ex.getMessage()+",sql:"+sql);
			ex.printStackTrace();
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
		}
		return SqlNews;
	}

	public String delete(String language_v, String table_head, String customid,
			int re_userid, String ddate) {
		String SqlNews = "sucess";
		String sql = "";
		String sql2 = "";
		String sql3 = "";
		String sql4 = "";
		String sql5 = "";
		String sql6 = "";
		String sql7 = "";
		String sql8 = "";// String sql9="";
		String sql4log = "";
		try {
			sql = "update "
					+ table_head
					+ "custom set status=1-status,updater=?,update_time=? where customid=?";
			sql2 = "update cana_link set status=1-status,updater=" + re_userid
					+ ",update_time='" + ddate + "' where customid=" + customid;
			sql3 = "update cana_linkman set status=1-status,updater="
					+ re_userid + ",update_time='" + ddate
					+ "' where customid=" + customid;
			sql4 = "update cana_order set status=1-status,updater=" + re_userid
					+ ",update_time='" + ddate + "' where customid=" + customid;
			sql5 = "update cana_service set status=1-status,updater="
					+ re_userid + ",update_time='" + ddate
					+ "' where customid=" + customid;
			sql6 = "update cana_order_charge set status=1-status,updater="
					+ re_userid + ",update_time='" + ddate
					+ "' where customid=" + customid;
			sql7 = "update cana_document set status=1-status,updater="
					+ re_userid + ",update_time='" + ddate
					+ "' where customid=" + customid;
			sql8 = "update cana_prodcuts_service set status=1-status,updater="
					+ re_userid + ",update_time='" + ddate
					+ "' where customid=" + customid;
			// sql9="update cana_check_water set
			// status=1-status,updater="+re_userid+",update_time='"+ddate+"'
			// where customid="+customid;
			sql4log = "update " + table_head
					+ "custom set status=1-status,updater=" + re_userid
					+ ",update_time=" + ddate + " where customid=" + customid;
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setInt(1, re_userid);
			pstmt.setString(2, ddate);
			pstmt.setString(3, customid);
			int result = pstmt.executeUpdate();
			if (result > 0) {
				SqlNews = "sucess";
			} else {
				SqlNews = "b";
			}
			pstmt = con.prepareStatement(sql2);
			pstmt.executeUpdate();
			pstmt = con.prepareStatement(sql3);
			pstmt.executeUpdate();
			pstmt = con.prepareStatement(sql4);
			pstmt.executeUpdate();
			pstmt = con.prepareStatement(sql5);
			pstmt.executeUpdate();
			pstmt = con.prepareStatement(sql6);
			pstmt.executeUpdate();
			pstmt = con.prepareStatement(sql7);
			pstmt.executeUpdate();
			pstmt = con.prepareStatement(sql8);
			pstmt.executeUpdate();
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
			crmLog.info("Success delete of Custom,operator:" + re_userid
					+ ",sql:" + sql4log);
		} catch (SQLException ex) {
			crmLog.error("Error in method delete() of Custom,operator:"
					+ re_userid + "error:" + ex.getMessage() + ",sql:" + sql);
			SqlNews = "Error!" + ex;
			// ex.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
		}
		return SqlNews;
	}

	public String changeseller(String language_v, String table_head,
			String customid, String seller_new, int re_userid) {
		String SqlNews = "sucess";
		String sql = "";
		String sql4log = "";
		try {
			sql = "update " + table_head
					+ "custom set seller=? where customid=?";
			sql4log = "update " + table_head + "custom set seller="
					+ seller_new + " where customid=" + customid;
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, seller_new);
			pstmt.setString(2, customid);
			int result = pstmt.executeUpdate();
			if (result > 0) {
				SqlNews = "sucess";
			} else {
				SqlNews = "b";
			}
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
			crmLog.info("Success changeseller of Custom,operator:" + re_userid
					+ ",sql:" + sql4log);
		} catch (SQLException ex) {
			crmLog.error("Error in method changeseller() of Custom,operator:"
					+ re_userid + ",error:" + ex.getMessage() + ",sql:" + sql);
			SqlNews = "Error!" + ex;
			// ex.printStackTrace();
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
		}
		return SqlNews;
	}

	public String changeprice(String language_v, String table_head,
			String customid, String self_field1, String self_field2,
			String self_field8, String self_field9, String self_field10,
			String self_field12, int userid) {
		String SqlNews = "sucess", sql = "";
		String sql4log = "";
		try {
			sql = "update "
					+ table_head
					+ "custom set self_field1=?,self_field2=?,self_field8=?,self_field9=?,self_field10=?,self_field12=? where customid=?";
			sql4log = "update " + table_head + "custom set self_field1="
					+ self_field1 + ",self_field2=" + self_field2
					+ ",self_field8=" + self_field8 + ",self_field9="
					+ self_field9 + ",self_field10=" + self_field10
					+ ",self_field12=" + self_field12 + " where customid="
					+ customid;
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, self_field1);
			pstmt.setString(2, self_field2);
			pstmt.setString(3, self_field8);
			pstmt.setString(4, StringUtils.toChinese(self_field9));
			pstmt.setString(5, StringUtils.toChinese(self_field10));
			pstmt.setString(6, StringUtils.toChinese(self_field12));
			pstmt.setString(7, customid);
			int result = pstmt.executeUpdate();
			if (result > 0) {
				SqlNews = "sucess";
			} else {
				SqlNews = "b";
			}
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
			crmLog.info("Success changeprice of Custom,operator:" + userid
					+ ",sql:" + sql4log);
		} catch (SQLException ex) {
			crmLog
					.error("Error in method changeprice() of Custom_tailor,operator:"
							+ userid
							+ ",error:"
							+ ex.getMessage()
							+ ",sql:"
							+ sql);
			SqlNews = "Error!" + ex;
			// ex.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
		}
		return SqlNews;
	}
	
	
	public String changeprice2(String language_v, String table_head,
			String customid, String self_field1, String self_field2,
			String self_field8, String self_field9, String self_field10,
			String self_field12,String self_field47, int userid) {
		String SqlNews = "sucess", sql = "";
		String sql4log = "";
		try {
			sql = "update "
					+ table_head
					+ "custom set self_field1=?,self_field2=?,self_field8=?,self_field9=?,self_field10=?,self_field12=?,self_field47=? where customid=?";
			sql4log = "update " + table_head + "custom set self_field1="
					+ self_field1 + ",self_field2=" + self_field2
					+ ",self_field8=" + self_field8 + ",self_field9="
					+ self_field9 + ",self_field10=" + self_field10
					+ ",self_field12=" + self_field12 +  ",self_field47=" + self_field47+" where customid="
					+ customid;
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, self_field1);
			pstmt.setString(2, self_field2);
			pstmt.setString(3, self_field8);
			pstmt.setString(4, StringUtils.toChinese(self_field9));
			pstmt.setString(5, StringUtils.toChinese(self_field10));
			pstmt.setString(6, StringUtils.toChinese(self_field12));
			pstmt.setString(7, StringUtils.toChinese(self_field47));
			pstmt.setString(8, customid);
			int result = pstmt.executeUpdate();
			if (result > 0) {
				SqlNews = "sucess";
			} else {
				SqlNews = "b";
			}
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
			crmLog.info("Success changeprice of Custom,operator:" + userid
					+ ",sql:" + sql4log);
		} catch (SQLException ex) {
			crmLog
					.error("Error in method changeprice() of Custom_tailor,operator:"
							+ userid
							+ ",error:"
							+ ex.getMessage()
							+ ",sql:"
							+ sql);
			SqlNews = "Error!" + ex;
			// ex.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
		}
		return SqlNews;
	}

	public String combination(String table_head, String old_customid,
			String new_customid, int userid) {
		String SqlNews = "";
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		String sql4 = "";
		String sql5 = "";
		String sql6 = "";
		String sql7 = "";
		String sql8 = "";
		String sql9 = "";
		try {
			/*
			 * //�ҳ����к��ֶ�customid�ı� SELECT b.name as TableName,a.name as
			 * columnname From syscolumns a INNER JOIN sysobjects b ON a.id=b.id
			 * AND b.type='U' AND a.name='customid'
			 */
			// sql1="delete from "+table_head+"custom where
			// customid='"+old_customid+"'";
			sql1 = "update " + table_head
					+ "custom set status=1 where customid='" + old_customid
					+ "'";
			sql2 = "update " + table_head + "linkman set customid='"
					+ new_customid + "' where customid='" + old_customid + "'";
			sql3 = "update " + table_head + "project set customid='"
					+ new_customid + "' where customid='" + old_customid + "'";
			sql4 = "update " + table_head + "link set customid='"
					+ new_customid + "' where customid='" + old_customid + "'";
			sql5 = "update " + table_head + "order set customid='"
					+ new_customid + "' where customid='" + old_customid + "'";
			sql6 = "update " + table_head + "service set customid='"
					+ new_customid + "' where customid='" + old_customid + "'";
			sql7 = "update " + table_head + "document set customid='"
					+ new_customid + "' where customid='" + old_customid + "'";
			sql8 = "update " + table_head + "fare set customid='"
					+ new_customid + "' where customid='" + old_customid + "'";
			sql9 = "update " + table_head + "products_service set customid='"
					+ new_customid + "' where customid='" + old_customid + "'";
			con = DbConnectionManager.getConnection();
			PreparedStatement pstmt1 = con.prepareStatement(sql1,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			PreparedStatement pstmt2 = con.prepareStatement(sql2,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			PreparedStatement pstmt3 = con.prepareStatement(sql3,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			PreparedStatement pstmt4 = con.prepareStatement(sql4,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			PreparedStatement pstmt5 = con.prepareStatement(sql5,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			PreparedStatement pstmt6 = con.prepareStatement(sql6,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			PreparedStatement pstmt7 = con.prepareStatement(sql7,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			PreparedStatement pstmt8 = con.prepareStatement(sql8,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			PreparedStatement pstmt9 = con.prepareStatement(sql9,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			int result = pstmt1.executeUpdate();
			pstmt2.executeUpdate();
			pstmt3.executeUpdate();
			pstmt4.executeUpdate();
			pstmt5.executeUpdate();
			pstmt6.executeUpdate();
			pstmt7.executeUpdate();
			pstmt8.executeUpdate();
			pstmt9.executeUpdate();
			if (result > 0) {
				SqlNews = "sucess";
			} else {
				SqlNews = "b";
			}
			pstmt1.close();
			pstmt2.close();
			pstmt3.close();
			pstmt4.close();
			pstmt5.close();
			pstmt6.close();
			pstmt7.close();
			pstmt8.close();
			pstmt9.close();
			pstmt1 = null;
			pstmt2 = null;
			pstmt3 = null;
			pstmt4 = null;
			pstmt5 = null;
			pstmt6 = null;
			pstmt7 = null;
			pstmt8 = null;
			pstmt9 = null;
			con.close();
			con = null;
			crmLog.info("Success combination of Custom,operator:" + userid
					+ ",sql:" + sql1 + "," + sql2 + "," + sql3 + "," + sql4
					+ "," + sql5 + "," + sql6 + "," + sql7 + "," + sql8 + ","
					+ sql9);
		} catch (SQLException ex) {
			SqlNews = "Error!" + ex;
			crmLog.error("Success combination of Custom,operator:" + userid
					+ "error:" + ex.getMessage() + ",sql:" + sql1 + "," + sql2
					+ "," + sql3 + "," + sql4 + "," + sql5 + "," + sql6 + ","
					+ sql7 + "," + sql8 + "," + sql9);
			// ex.printStackTrace();
		} finally {

			try {
				if (pstmt1 != null) {
					pstmt1.close();
					pstmt1 = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt2 != null) {
					pstmt2.close();
					pstmt2 = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt3 != null) {
					pstmt3.close();
					pstmt3 = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt4 != null) {
					pstmt4.close();
					pstmt4 = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt5 != null) {
					pstmt5.close();
					pstmt5 = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt6 != null) {
					pstmt6.close();
					pstmt6 = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt7 != null) {
					pstmt7.close();
					pstmt7 = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt8 != null) {
					pstmt8.close();
					pstmt8 = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt9 != null) {
					pstmt9.close();
					pstmt9 = null;
				}
			} catch (Exception e) {

			}
			try {
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {

			}
		}
		return SqlNews;
	}

	public String selectcount(String table_head, String filiale, String seller,
			String time_from_to, String time1, String time2, String tj_name1,
			String tj_name2, String tj_name3, String tj_v1, String tj_v2,
			String tj_v3, String tj_v11, String tj_v21, String tj_v31,
			String tj_where, String status) {
		String num = "";
		String sql = "";
		try {
			sql = "SELECT count(*) as num FROM " + table_head + "custom";
			String sqlsub3 = " where " + table_head + "custom.id>0";
			String where = " ", where1 = "";
			int k = 0;
			if ((!tj_v1.equals("")) && (tj_v1 != null)) {
				if (tj_v11.equals("0")) {
					String tj_v1_per = "";
					for (int i = 0; i < tj_v1.length(); i++) {
						tj_v1_per += tj_v1.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name1
							+ " like '%" + tj_v1_per + "%'";
				} else if (tj_v11.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name1
							+ " like '" + tj_v1 + "%'";
				} else if (tj_v11.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name1
							+ " like '%" + tj_v1 + "%'";
				} else if (tj_v11.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name1
							+ " not like '%" + tj_v1 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name1
							+ "='" + tj_v1 + "'";
				}
			}
			if ((!tj_v2.equals("")) && (tj_v2 != null)) {
				if (tj_v21.equals("0")) {
					String tj_v2_per = "";
					for (int i = 0; i < tj_v2.length(); i++) {
						tj_v2_per += tj_v2.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name2
							+ " like '%" + tj_v2_per + "%'";
				} else if (tj_v21.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name2
							+ " like '" + tj_v2 + "%'";
				} else if (tj_v21.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name2
							+ " like '%" + tj_v2 + "%'";
				} else if (tj_v21.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name2
							+ " not like '%" + tj_v2 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name2
							+ "='" + tj_v2 + "'";
				}
			}
			if ((!tj_v3.equals("")) && (tj_v3 != null)) {
				if (tj_v31.equals("0")) {
					String tj_v3_per = "";
					for (int i = 0; i < tj_v3.length(); i++) {
						tj_v3_per += tj_v3.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name3
							+ " like '%" + tj_v3_per + "%'";
				} else if (tj_v31.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name3
							+ " like '" + tj_v3 + "%'";
				} else if (tj_v31.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name3
							+ " like '%" + tj_v3 + "%'";
				} else if (tj_v31.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name3
							+ " not like '%" + tj_v3 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name3
							+ "='" + tj_v3 + "'";
				}
			}
			if ((!filiale.equals("")) && (filiale != null)) {
				where1 = "";
				String[] filialearr = StringUtils.arr(filiale, "+", "0", 20);
				for (int j = 0; j < 20; j++) {
					if (j == 0) {
						where1 = " and (" + table_head
								+ "custom.filiale like '%" + filialearr[j]
								+ "%'";
						k++;
					} else if (!filialearr[j].equals("0")) {
						where1 = where1 + " or " + table_head
								+ "custom.filiale like '%" + filialearr[j]
								+ "%'";
						k++;
					} else {
						break;
					}
				}
				where = where + where1 + ")";
			}
			if ((!seller.equals("")) && (!seller.equals("all"))
					&& (seller != null)) {
				where1 = "";
				String[] sellerarr = StringUtils.arr(seller, "+", "0", 20);
				for (int j = 0; j < 20; j++) {
					if (j == 0) {
						where1 = " and (" + table_head + "custom.seller='"
								+ sellerarr[j] + "'";
						k++;
					} else if (!sellerarr[j].equals("0")) {
						where1 = where1 + " or " + table_head
								+ "custom.seller='" + sellerarr[j] + "'";
						k++;
					} else {
						break;
					}
				}
				where = where + where1 + ")";
			}
			if ((!time1.equals("")) && (time1 != null)) {
				where = where + " and " + table_head + "custom." + time_from_to
						+ ">='" + time1 + "'";
			}
			if ((!time2.equals("")) && (time2 != null)) {
				where = where + " and " + table_head + "custom." + time_from_to
						+ "<='" + time2 + "'";
			}
			if ((!status.equals("")) && (status != null)) {
				where = where + " and " + table_head + "custom.status='"
						+ status + "'";
			}
			if ((!tj_where.equals("")) && (tj_where != null)) {
				where = where + tj_where;
			}
			String groupby = " ";
			sql = sql + sqlsub3 + where + groupby;
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				num = rs.getInt("num") + "";
			}

			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog.error("Error in method selectcount() of Custom,error:"
					+ ex.getMessage() + ",sql:" + sql);
			// System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {

			}

			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {

			}
			return num;
		}
	}

	public String selectsum_month(String table_head, String filiale,
			String seller, String time_from_to, String time1, String time2,
			String tj_name1, String tj_name2, String tj_name3, String tj_v1,
			String tj_v2, String tj_v3, String tj_v11, String tj_v21,
			String tj_v31, String tj_where, String sumname, String status) {
		String summation = "";
		String sql = "";
		try {
			sql = "SELECT sum(" + sumname + ") as summation FROM " + table_head
					+ "custom";
			String sqlsub3 = " where " + table_head + "custom.id>0";
			String where = " ", where1 = "";
			int k = 0;
			if ((!tj_v1.equals("")) && (tj_v1 != null)) {
				if (tj_v11.equals("0")) {
					String tj_v1_per = "";
					for (int i = 0; i < tj_v1.length(); i++) {
						tj_v1_per += tj_v1.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name1
							+ " like '%" + tj_v1_per + "%'";
				} else if (tj_v11.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name1
							+ " like '" + tj_v1 + "%'";
				} else if (tj_v11.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name1
							+ " like '%" + tj_v1 + "%'";
				} else if (tj_v11.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name1
							+ " not like '%" + tj_v1 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name1
							+ "='" + tj_v1 + "'";
				}
			}
			if ((!tj_v2.equals("")) && (tj_v2 != null)) {
				if (tj_v21.equals("0")) {
					String tj_v2_per = "";
					for (int i = 0; i < tj_v2.length(); i++) {
						tj_v2_per += tj_v2.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name2
							+ " like '%" + tj_v2_per + "%'";
				} else if (tj_v21.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name2
							+ " like '" + tj_v2 + "%'";
				} else if (tj_v21.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name2
							+ " like '%" + tj_v2 + "%'";
				} else if (tj_v21.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name2
							+ " not like '%" + tj_v2 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name2
							+ "='" + tj_v2 + "'";
				}
			}
			if ((!tj_v3.equals("")) && (tj_v3 != null)) {
				if (tj_v31.equals("0")) {
					String tj_v3_per = "";
					for (int i = 0; i < tj_v3.length(); i++) {
						tj_v3_per += tj_v3.substring(i, i + 1) + "%";
						if (i > 10) {
							break;
						}
					}
					where = where + " and " + table_head + "custom." + tj_name3
							+ " like '%" + tj_v3_per + "%'";
				} else if (tj_v31.equals("1")) {
					where = where + " and " + table_head + "custom." + tj_name3
							+ " like '" + tj_v3 + "%'";
				} else if (tj_v31.equals("2")) {
					where = where + " and " + table_head + "custom." + tj_name3
							+ " like '%" + tj_v3 + "%'";
				} else if (tj_v31.equals("3")) {
					where = where + " and " + table_head + "custom." + tj_name3
							+ " not like '%" + tj_v3 + "%'";
				} else {
					where = where + " and " + table_head + "custom." + tj_name3
							+ "='" + tj_v3 + "'";
				}
			}
			if ((!filiale.equals("")) && (filiale != null)) {
				where1 = "";
				String[] filialearr = StringUtils.arr(filiale, "+", "0", 20);
				for (int j = 0; j < 20; j++) {
					if (j == 0) {
						where1 = " and (" + table_head
								+ "custom.filiale like '%" + filialearr[j]
								+ "%'";
						k++;
					} else if (!filialearr[j].equals("0")) {
						where1 = where1 + " or " + table_head
								+ "custom.filiale like '%" + filialearr[j]
								+ "%'";
						k++;
					} else {
						break;
					}
				}
				where = where + where1 + ")";
			}
			if ((!seller.equals("")) && (!seller.equals("all"))
					&& (seller != null)) {
				where1 = "";
				String[] sellerarr = StringUtils.arr(seller, "+", "0", 20);
				for (int j = 0; j < 20; j++) {
					if (j == 0) {
						where1 = " and (" + table_head + "custom.seller='"
								+ sellerarr[j] + "'";
						k++;
					} else if (!sellerarr[j].equals("0")) {
						where1 = where1 + " or " + table_head
								+ "custom.seller='" + sellerarr[j] + "'";
						k++;
					} else {
						break;
					}
				}
				where = where + where1 + ")";
			}
			if ((!time1.equals("")) && (time1 != null)) {
				where = where + " and " + table_head + "custom." + time_from_to
						+ ">='" + time1 + "'";
			}
			if ((!time2.equals("")) && (time2 != null)) {
				where = where + " and " + table_head + "custom." + time_from_to
						+ "<='" + time2 + "'";
			}
			if ((!status.equals("")) && (status != null)) {
				where = where + " and " + table_head + "custom.status='"
						+ status + "'";
			}
			if ((!tj_where.equals("")) && (tj_where != null)) {
				where = where + tj_where;
			}
			String groupby = " ";
			sql = sql + sqlsub3 + where + groupby;
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				summation = rs.getString("summation");
			}
			if ((summation == null) || (summation.equals(""))) {
				summation = "0";
			}
			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog.error("Error in method selectsum_month() of Custom,error:"
					+ ex.getMessage() + ",sql:" + sql);
			// System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {

			}

			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {

			}
			return summation;
		}
	}

	public static String updateConvert(String sql, CustomShow CShow) {
		String csql = "";
		int setIndex = sql.indexOf("set");
		int whereIndex = sql.indexOf("where");
		String setStr = "";
		String whereStr = "";
		String beforeStr = sql.substring(0, setIndex + 3);
		ArrayList setList = new ArrayList();
		ArrayList whereList = new ArrayList();
		try {
			if (whereIndex > -1) {
				setStr = sql.substring(setIndex + 4, whereIndex - 1);
				whereStr = sql.substring(whereIndex + 6);
			} else {
				setStr = sql.substring(setIndex + 1);
			}
			if (setStr.indexOf(",") > -1) {
				String[] setarray = new String[100];

				setarray = setStr.split(",");
				for (int i = 0; i < setarray.length; i++) {
					if (setarray[i] != null && !setarray.equals(" ")) {
						int equIndex = setarray[i].indexOf("=");
						setList.add(setarray[i].substring(0, equIndex));
					}
				}
			} else {
				int equIndex = setStr.indexOf("=");
				setList.add(setStr.substring(0, equIndex));
			}
			if (whereStr != null && !whereStr.equals("")) {
				if (whereStr.indexOf(" and ") > -1) {
					String[] wherearray = new String[100];
					wherearray = whereStr.split(" and ");
					for (int i = 0; i < wherearray.length; i++) {
						if (wherearray[i] != null && !wherearray.equals("")) {
							int equIndex = wherearray[i].indexOf("=");
							whereList.add(wherearray[i].substring(0, equIndex));
						}
					}
				} else {
					int equIndex = whereStr.indexOf("=");
					whereList.add(whereStr.substring(0, equIndex));
				}
			}
			for (int j = 0; j < setList.size(); j++) {
				String ss = CShow.getvalue(setList.get(j).toString().trim());
				String strvalue = StringUtils.toChinese(ss);
				beforeStr = beforeStr + " " + setList.get(j).toString() + "="
						+ strvalue + ",";
			}
			int setnum = whereList.size();
			whereStr = " where ";
			if (setnum > 0) {
				for (int k = 0; k < setnum; k++) {
					String ww = CShow.getvalue(whereList.get(k).toString()
							.trim());
					;
					String strvalue = StringUtils.toChinese(ww);
					whereStr = whereStr + whereList.get(k).toString() + "="
							+ strvalue + ",";
				}
			}
			int beforeStrlen = beforeStr.length();
			int whereStrlen = whereStr.length();
			if (beforeStr.charAt(beforeStrlen - 1) == ',') {
				beforeStr = beforeStr.substring(0, beforeStrlen - 1);
			}
			if (whereStr.charAt(whereStrlen - 1) == ',') {
				whereStr = whereStr.substring(0, whereStrlen - 1);
			}
			csql = beforeStr + whereStr;
		} catch (Exception ex) {
			ex.printStackTrace();
			// System.out.println(ex.getStackTrace());
		}
		return csql;
	}

	public static String insertConvert(String sql, CustomShow CShow) {
		String csql = "";
		// int intoIndex=sql.;
		int valueIndex = sql.indexOf("values");
		try {
			String insert_field = sql.substring(0, valueIndex);
			String sql_field = sql.substring(insert_field.indexOf('(') + 1,
					insert_field.indexOf(')'));
			String insert_before = sql.substring(0, valueIndex + 5);
			// String
			// sql_value=insert_value.substring(insert_value.indexOf('(')+1,insert_value.indexOf(')')-1);
			ArrayList fieldList = new ArrayList();
			// ArrayList valueList=new ArrayList();
			if (sql_field.indexOf(',') > -1) {
				String[] insertArray = new String[100];
				insertArray = sql_field.split(",");
				for (int i = 0; i < insertArray.length; i++) {
					fieldList.add(insertArray[i].trim());
				}
			} else {
				fieldList.add(sql_field.trim());
			}
			insert_before = insert_before + " (";
			for (int j = 0; j < fieldList.size(); j++) {
				String value = CShow.getvalue(fieldList.get(j).toString()
						.trim());
				String strvalue = StringUtils.toChinese(value);
				insert_before = insert_before + strvalue + ",";
			}
			csql = insert_before.substring(0, insert_before.length() - 1) + ")";
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return csql;
	}

	public boolean checkParameter(String para) // ���˷Ƿ��ַ�
	{
		int flag = 0;
		flag += para.indexOf("'") + 1;
		flag += para.indexOf(";") + 1;
		flag += para.indexOf("1=1") + 1;
		flag += para.indexOf("|") + 1;
		flag += para.indexOf("<") + 1;
		flag += para.indexOf(">") + 1;
		if (flag != 0) {
			System.out.println("�ύ�˷Ƿ��ַ�!!!");
			return false;
		}
		return true;
	}

	// �õ����л�Ա�ͻ��������ַ���Էֺŷָ�
	public String getMemberMails() {
		StringBuffer result = new StringBuffer("");
		Connection conn = DbConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select email from cana_custom where kind='��Ա' and status=0 ";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String email = rs.getString("email");
				if (email != null && !"".equals(email.trim())
						&& !"null".equalsIgnoreCase(email.trim())) {
					if (email.indexOf("@") != -1) {
						result.append(email).append(";");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String emails = result.toString();
		if (";".equals(emails.substring(emails.length() - 1))) {
			emails = emails.substring(0, emails.length() - 1);
		}
		return emails;
	}
	
	//�жϵ�ַ��ϵͳ���Ƿ��Ѿ�����
	public int isAddressSame(Connection conn,String address,List list){
		int result=0;
		String sql="select * from cana_custom where status='0' ";
		if(address!=null&&address.trim().length()>0){
			sql+=" and address='"+address+"' ";
		}else{
			return 0;
		}
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				list.add(rs.getInt("customid"));
				result=1;
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {rs.close();rs = null;}
				if (pstmt != null) {pstmt.close();pstmt = null;}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	//�ж�������ϵͳ���Ƿ��Ѿ�����
	public int isNameSame(Connection conn,String name,List list){
		int result=0;
		String sql="select * from cana_custom where status='0' ";
		if(name!=null&&name.trim().length()>0){
			sql+=" and name='"+name+"' ";
		}else{
			return 0;
		}
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				list.add(rs.getInt("customid"));
				result=1;
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {rs.close();rs = null;}
				if (pstmt != null) {pstmt.close();pstmt = null;}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	//�ж��ֻ���ϵͳ���Ƿ��Ѿ�����
	public int isMobileSame(Connection conn,String mobile,List list){
		int result=0;
		String sql="select * from cana_custom where status='0' ";
		if(mobile!=null&&mobile.trim().length()>0){
			sql+=" and mobile='"+mobile+"' ";
		}else{
			return 0;
		}
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				list.add(rs.getInt("customid"));
				result=1;
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {rs.close();rs = null;}
				if (pstmt != null) {pstmt.close();pstmt = null;}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	//��cana_same_custom���в���һ����¼
	public int saveSameCustom(String old_custom,int new_custom){
		int result=1;
		Connection conn=DbConnectionManager.getConnection();
		PreparedStatement pstmt=null;
		String sql="update cana_custom set self_field43=? where customid in ("+old_custom+") ";
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, new_custom+"");
			result*=pstmt.executeUpdate();
			
			sql="update cana_custom set self_field43=? where customid=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, old_custom);
			pstmt.setInt(2, new_custom);
			result*=pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if (pstmt != null) {pstmt.close();pstmt = null;}
				if (conn != null) {conn.close();conn = null;}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public double[] getCustomJifen(String customid){
		double[] results = new double[2];
		StringBuffer sql = new StringBuffer();
		try {
			sql.append(" SELECT CUSTOM_ID, SUM(OLDJIFEN) OLDJIFEN_SUM, SUM(NEWJIFEN) NEWJIFEN_SUM ");
			sql.append("   FROM (SELECT CUSTOM_ID, ");
			sql.append("                CASE OLD_OR_NEW_FLAG ");
			sql.append("                  WHEN 0 THEN ");
			sql.append("                   1 * BUILD_OR_CONSUME * SUM(JIFEN_VALUE + 0) ");
			sql.append("                  ELSE ");
			sql.append("                   0 ");
			sql.append("                END OLDJIFEN, ");
			sql.append("                CASE OLD_OR_NEW_FLAG ");
			sql.append("                  WHEN 1 THEN ");
			sql.append("                   1 * BUILD_OR_CONSUME * SUM(JIFEN_VALUE + 0) ");
			sql.append("                  ELSE ");
			sql.append("                   0 ");
			sql.append("                END NEWJIFEN ");
			sql.append("           FROM CANA_JIFEN  ");
			sql.append("           where custom_id='"+customid+"' and status='0' ");
			sql.append("          GROUP BY CUSTOM_ID, OLD_OR_NEW_FLAG, BUILD_OR_CONSUME) T ");
			sql.append("  GROUP BY T.CUSTOM_ID ");
			
			
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				double oldjifen = rs.getDouble("OLDJIFEN_SUM");
				double newjifen = rs.getDouble("NEWJIFEN_SUM");
				results[0] = oldjifen;
				results[1] = newjifen;
				
				break;
			}
			
			rs.close();
			rs = null;
			pstmt.close();
			pstmt = null;
			con.close();
			con = null;
		} catch (SQLException ex) {
			crmLog.error("Error in method getCustomJifen() of Custom,error:"
					+ ex.getMessage() + ",sql:" + sql);
			// System.out.println(ex.getMessage()+"666");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {

			}
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {

			}

			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {

			}
			return results;
		}

	}
	
	public void markCustomApproved(String customid, boolean approved){
		con=DbConnectionManager.getConnection();
		String sql="update cana_custom set self_field63='"+(approved?"Y":"N")+"' from cana_custom where customid=? ";
		try{
			if(customid!=null){
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, customid);
				pstmt.executeUpdate();
			}
		}catch(SQLException e){
			crmLog.error("Error in method deleteCustomPriceById() of CustomPrice,error:"+e.getMessage()+",sql:"+sql);
		}finally{
			closeResource(rs,pstmt,con);
		}
	}
	
	public void markCustomApprovedBatch(List<String> customids, boolean approved){
		for(String customid : customids ){
			markCustomApproved(customid, approved);
		}
	}
	
	public boolean isCustomApproved(String customid){
		con=DbConnectionManager.getConnection();
		boolean approved = false;
		String sql="select self_field63,self_field64 from cana_custom where customid=? ";
		try{
			if(customid!=null){
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, customid);
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					String self_field63 = rs.getString(1);
					String self_field64 = rs.getString(2);
					
					if(!"N".equals(self_field63)&&"Y".equals(self_field64)){
						approved = true;
					}
				}
			}
		}catch(SQLException e){
			crmLog.error("Error in method deleteCustomPriceById() of CustomPrice,error:"+e.getMessage()+",sql:"+sql);
		}finally{
			closeResource(rs,pstmt,con);
		}
		
		return approved;
	}
	
	
	public List<CustomShow> getToApproveCustoms(){
		con=DbConnectionManager.getConnection();
		List<CustomShow> customs = new ArrayList<CustomShow>();
		String sql=" select customid,name,self_field64 from cana_custom where status='0' and filiale like '%�����г���%' and self_field64<>'Y' ";
		try{
				pstmt=con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					CustomShow cs = new CustomShow();
					cs.setcustomid(rs.getInt("customid"));
					cs.setname(rs.getString("name"));
					cs.setself_field64(rs.getString("self_field64"));
					
					customs.add(cs);
				}
		}catch(SQLException e){
			crmLog.error("Error in method getToApproveCustoms() of CustomPrice,error:"+e.getMessage()+",sql:"+sql);
		}finally{
			closeResource(rs,pstmt,con);
		}
		
		return customs;
	}

	
	private void closeResource(ResultSet rs,PreparedStatement pstmt,Connection conn){
		try{
			if(rs!=null){
				rs.close();
				rs=null;
			}
			if(pstmt!=null){
				pstmt.close();pstmt=null;
			}
			if(conn!=null){
				conn.close();conn=null;
			}
		}catch(Exception e){
			crmLog.error("Error in method closeResource() of CustomPrice,error:" + e.getMessage());
		}
	}
	
	
	public static void main(String[] args) {
		System.out.println(new Custom().getMemberMails());
	}

}