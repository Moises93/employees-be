package employees.domain;

import java.util.List;

public class DummyResponse {
     String status;
     private List<Employee> data;
  
     
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Employee> getData() {
		return data;
	}
	public void setData(List<Employee> data) {
		this.data = data;
	}
	 
     
}
