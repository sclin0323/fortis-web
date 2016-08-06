package com.hoyoung.fortis.command;

public class SingleSideOnCommand {
	private String sessionId;
	/*
	 *  ( 帳號 ) 身份碼
	 */
	private String cn;
	/*
	 * 姓名
	 */
	private String givenName;
	private String userPassword;
	/*
	 * 員工編號
	 */
	private String employeeNumber;
	/*
	 * 職稱
	 */
	private String title;
	/*
	 * 電子郵件 mail 
	 */
	private String mail;
	private String svuserrole;
	private String ou;
	private String svuserclass;
	private String svusertype;
	private String departmentNumber;
	/*
	 * 上層單位名稱
	 */
	private String direkParentDepartment;
	/*
	 * 教職員單位名稱
	 */
	private String description;

	public String getCn() {
		return cn;
	}
	


	public void setCn(String cn) {
		this.cn = cn;
	}
	


	public String getGivenName() {
		return givenName;
	}
	


	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	


	public String getUserPassword() {
		return userPassword;
	}
	


	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	


	public String getEmployeeNumber() {
		return employeeNumber;
	}
	


	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	


	public String getTitle() {
		return title;
	}
	


	public String getDirekParentDepartment() {
		return direkParentDepartment;
	}
	



	public void setDirekParentDepartment(String direkParentDepartment) {
		this.direkParentDepartment = direkParentDepartment;
	}
	



	public String getDescription() {
		return description;
	}
	



	public void setDescription(String description) {
		this.description = description;
	}
	



	public void setTitle(String title) {
		this.title = title;
	}
	


	public String getMail() {
		return mail;
	}
	


	public void setMail(String mail) {
		this.mail = mail;
	}
	


	public String getSvuserrole() {
		return svuserrole;
	}
	


	public void setSvuserrole(String svuserrole) {
		this.svuserrole = svuserrole;
	}
	


	public String getOu() {
		return ou;
	}
	


	public void setOu(String ou) {
		this.ou = ou;
	}
	


	public String getSvuserclass() {
		return svuserclass;
	}
	


	public void setSvuserclass(String svuserclass) {
		this.svuserclass = svuserclass;
	}
	


	public String getSvusertype() {
		return svusertype;
	}
	


	public void setSvusertype(String svusertype) {
		this.svusertype = svusertype;
	}
	


	public String getDepartmentNumber() {
		return departmentNumber;
	}
	


	public void setDepartmentNumber(String departmentNumber) {
		this.departmentNumber = departmentNumber;
	}
	


	public String getSessionId() {
		return sessionId;
	}
	

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}



	@Override
	public String toString() {
		return "SingleSideOnCommand [sessionId=" + sessionId + ", cn=" + cn + ", givenName=" + givenName
				+ ", userPassword=" + userPassword + ", employeeNumber=" + employeeNumber + ", title=" + title
				+ ", mail=" + mail + ", svuserrole=" + svuserrole + ", ou=" + ou + ", svuserclass=" + svuserclass
				+ ", svusertype=" + svusertype + ", departmentNumber=" + departmentNumber + "]";
	}
	
	
	
}
