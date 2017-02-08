package upload;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.BaseFile;
import bean.DbConnectionManager;

public class Upload {
	private Connection con = null;
	private ResultSet rs = null;
	private PreparedStatement pstmt = null;
//	public static Logger crmLog = Logger.getLogger("crmLog");
	public String insertPost(BaseFile baseFile) {
		String sqlNews = "";
		String sql = "";
		try {
			sql = "insert into base_file(object_rrn,org_rrn,is_active,created,lock_version,material_id,file_name,erp_name) values(object_rrn.nextval,139420,'Y',sysdate,1,?,?,?)";
			con = DbConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, baseFile.getMaterialId());
			pstmt.setString(2, baseFile.getFileName());
			pstmt.setString(3, baseFile.getErpName());
			
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
//			crmLog.info("Success insertPost of Custom,operator:" + user
//					+ ",sql:" + sql);
		} catch (Exception e) {
			System.out.print(e);
//			crmLog.error("Error in method insertPost() of Custom,error:"
//					+ e.getMessage() + ",sql:" + sql);
		}
		return sqlNews;
	}
	public List<BaseFile> getBaseFiles(String materialId) {
		String sql = "";
		PreparedStatement stmt = null;  
		ResultSet res = null;  
		List<BaseFile> baseFiles= new ArrayList<BaseFile>();
		try {
			sql = "select object_rrn,created,material_id,file_name from base_file where material_id =?";
			con = DbConnectionManager.getConnection();
//			pstmt = con.prepareStatement(sql,
//					ResultSet.TYPE_SCROLL_INSENSITIVE,
//					ResultSet.CONCUR_READ_ONLY);
			
			 stmt = con.prepareStatement(sql);
//	         res = stmt.executeQuery(sql);  
	         stmt.setString(1, materialId);  
	         res = stmt.executeQuery();
			while(res.next())  
            {  
				BaseFile baseFile = new BaseFile();
				Integer objectRrn = res.getInt("object_rrn");
                Date created = res.getDate("created");
                String fileName = res.getString("file_name");
                
                baseFile.setObjectRrn(objectRrn);
                baseFile.setCreated(created);
                baseFile.setMaterialId(materialId);
				baseFile.setFileName(fileName);
				
                baseFiles.add(baseFile);
            }  
			pstmt = null;
			con.close();
			con = null;
		} catch (Exception e) {
			System.out.print(e);
		}
		return baseFiles;
	}
}
