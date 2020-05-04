package employees.business;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import employees.domain.DummyResponse;
import employees.domain.Employee;

public class DummyServiceImplTest {
	
	@InjectMocks
	private DummyServiceImpl dummyService = new DummyServiceImpl(null);
	private DummyResponse dummyResponse = new DummyResponse();
	
	@Before
	public void initialData() throws Exception {
		Employee employeeTest = new Employee();
		employeeTest.setEmployeeAge("2");
		employeeTest.setEmployeeName("manuel");
		employeeTest.setEmployeeSalary("1000 $");
		employeeTest.setId("1");
		employeeTest.setProfileImage("developer");
		dummyResponse.setStatus("success");
		List<Employee> data = new ArrayList<>();
		data.add(employeeTest);
		dummyResponse.setData(data);	
		
	}
	
	
	@Test
	public void testCreateCsv() {		
		dummyService.createCsv(dummyResponse);
	}
}
