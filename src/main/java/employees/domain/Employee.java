package employees.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Employee {
	
	String id;
	String employeeName;
	String employeeSalary;
	String employeeAge;
	String profileImage;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeSalary() {
		return employeeSalary;
	}
	public void setEmployeeSalary(String employeeSalary) {
		this.employeeSalary = employeeSalary;
	}
	public String getEmployeeAge() {
		return employeeAge;
	}
	public void setEmployeeAge(String employeeAge) {
		this.employeeAge = employeeAge;
	}
	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	
	
}
