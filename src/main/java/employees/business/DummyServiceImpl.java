package employees.business;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class DummyServiceImpl implements DummyService{
	
	private final String endpoint;
	
   public DummyServiceImpl(
		   @Value("${employees.endpoint}") String endpoint) {
		    this.endpoint = endpoint;

   }
	
	
	@Override
	public String employees() {
		    RestTemplate restTemplate = new RestTemplate();
		    ResponseEntity<String> entityResponse = null;
		    try {
		      entityResponse = restTemplate.getForEntity(endpoint, String.class);
		      return entityResponse.getBody();  
		    
		    } catch (RestClientException e) {
		    	return("Error when consume employees service");
		    }
		    
		
	}

}
