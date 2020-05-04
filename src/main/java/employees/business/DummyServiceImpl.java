package employees.business;


import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Builder;
import com.google.gson.Gson;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

import employees.domain.DummyResponse;
import employees.utils.SftpUtils;

@Service
public class DummyServiceImpl implements DummyService{
	
	  private static final Logger LOGGER = LoggerFactory
		      .getLogger(DummyServiceImpl.class);
	  
	private final String endpoint;
	
   public DummyServiceImpl(
		   @Value("${employees.endpoint}") String endpoint) {
		    this.endpoint = endpoint;

   }
	
	
	@Override
	public String employees() {
		    RestTemplate restTemplate = new RestTemplate();
		    ResponseEntity<DummyResponse> entityResponse = null;
		    try {
		      entityResponse = restTemplate.getForEntity(endpoint, DummyResponse.class);
		      createCsv(entityResponse.getBody());
		      putFileToSftp();
		      return entityResponse.getBody().toString();  
		    
		    } catch (RestClientException e) {
		    	return("Error when consume employees service");
		    }
		    
		
	}
	
	public void createCsv(DummyResponse dummyResponse) {

		String json = new Gson().toJson(dummyResponse.getData());
		
		try {
			JsonNode jsonTree = new ObjectMapper().readTree(json);
			Builder csvSchemaBuilder = CsvSchema.builder();
			JsonNode firstObject = jsonTree.elements().next();
			firstObject.fieldNames().forEachRemaining(csvSchemaBuilder::addColumn);
			CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();
			exportCsvFile(jsonTree, csvSchema);
		} catch (JsonProcessingException e) {
			LOGGER.error("context createCsv", e);
		} 
		
		
		
	}


	private void exportCsvFile(JsonNode jsonTree, CsvSchema csvSchema) {
		CsvMapper csvMapper = new CsvMapper();
		try {
			csvMapper.writerFor(JsonNode.class)
			  .with(csvSchema)
			  .writeValue(new File("src/main/resources/file.csv"), jsonTree);
		} catch (IOException e) {
			LOGGER.error("context exportCsvFile", e);
		}
	}
    
	private void putFileToSftp() {
		SftpUtils sftUtils = new SftpUtils();
		try {
			sftUtils.putFile("src/main/resources/file.csv");
		} catch (SftpException | JSchException e) {
			LOGGER.error("context putFileToSftp", e);
		} 
	}

}
