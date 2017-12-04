package sv.practice.object;

import java.io.Serializable;

public class Student implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String fname;
	private String lname;
	private String bd;
	private String email;
	
	public Student() {
		
	}
	public Student(int id, String fname, String lname, String bd, String email) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.bd = bd;
		this.email = email;
	}
	public Student( String fname, String lname, String bd, String email) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.bd = bd;
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getBd() {
		return bd;
	}
	public void setBd(String bd) {
		this.bd = bd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", fname=" + fname + ", lname=" + lname + ", bd=" + bd + ", email=" + email + "]";
	}
	
	
	
}
