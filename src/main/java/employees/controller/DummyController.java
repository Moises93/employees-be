package employees.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import employees.business.DummyService;

@RestController
public class DummyController {
    
	private final DummyService dummyService;
	
	
	public DummyController(DummyService dummyService) {
	    this.dummyService = dummyService;
	  }
	
	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public ResponseEntity<Object> getEmployees() {
		String employees = dummyService.employees();
	    if (employees != null) {
	      return new ResponseEntity<>(employees, HttpStatus.OK);
	    }
	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    
    }
}
