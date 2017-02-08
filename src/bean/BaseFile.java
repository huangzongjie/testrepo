package bean;

import java.util.Date;

public class BaseFile {
	
	    private int objectRrn;
	    private int orgRrn;
	    private String isActive;
	    private Date created;
	    private int lockVersion;
	    private String materialId;
	    private String fileName;
	    private String erpName;
		public int getObjectRrn() {
			return objectRrn;
		}
		public void setObjectRrn(int objectRrn) {
			this.objectRrn = objectRrn;
		}
		public int getOrgRrn() {
			return orgRrn;
		}
		public void setOrgRrn(int orgRrn) {
			this.orgRrn = orgRrn;
		}
		public String getIsActive() {
			return isActive;
		}
		public void setIsActive(String isActive) {
			this.isActive = isActive;
		}
		public Date getCreated() {
			return created;
		}
		public void setCreated(Date created) {
			this.created = created;
		}
		public int getLockVersion() {
			return lockVersion;
		}
		public void setLockVersion(int lockVersion) {
			this.lockVersion = lockVersion;
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public String getErpName() {
			return erpName;
		}
		public void setErpName(String erpName) {
			this.erpName = erpName;
		}
		public String getMaterialId() {
			return materialId;
		}
		public void setMaterialId(String materialId) {
			this.materialId = materialId;
		}
}