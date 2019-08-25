package com.labforward.eln.util.endpoint;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value = DataUtilsControllerEndpoint.class)
public class DataUtilsControllerEndpointTest {

	@Autowired
	private MockMvc mockMvc;
	
	String exampleJson = "{keyword:\"Word\",entry_text:\"Word Words Wor word\"}";

	
	@Test
	public void analyseKeywordTest() throws Exception{
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/eln/api/analyseKeyword")
										.accept(MediaType.APPLICATION_JSON)
										.content(exampleJson)
										.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getContentAsString());
		String expected = "{\"keyword\":\"Word\",\"frequency\":1,\"similarWords\":[\"Words\",\"Wor\",\"word\"]}";

		// {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K Students","steps":["Learn Maven","Import Project","First Example","Second Example"]}

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);		
	}

}
