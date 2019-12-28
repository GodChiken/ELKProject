package com.example.demo;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kbh.elk.app.ELKApplication;
import com.kbh.elk.app.config.dto.BookDTO;
import com.kbh.elk.app.config.dto.BookStoreDTO;
import com.kbh.elk.app.presentation.ELKController;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,classes = ELKApplication.class)
public class AsyncRequestTester {

	private MockMvc mockMvc;
	private MockRestServiceServer mockServer;
	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private WebApplicationContext context;

	@MockBean
	ELKController elkController;

	@Before
	public void setUp(){
		mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
		mockServer = MockRestServiceServer.createServer(new RestTemplate());
	}

	@Test
	public void asyncRequest() throws Exception{
		for (int i = 0; i < 10000; i++) {
			mockMvc.perform(get("/book/{bookIdx}",1))
					.andExpect(status().isOk())
					.andDo(mvcResult -> {mvcResult.getResponse().getContentAsString();});
		}
	}

	@Test
	public void randomRequest() throws Exception{

		List<String> url = new ArrayList<>();
		url.add("http://localhost:8080/book/{bookIdx}");
		url.add("http://localhost:8080/bookStore/{bookStoreIdx}");

		for (int i = 0; i < 12000; i++) {
			sleep(RandomUtils.nextInt(1000,3000));
			RestTemplate restTemplate = new RestTemplate();

			restTemplate.getForEntity(url.get(
					RandomUtils.nextInt(0,2)), BookDTO.class,RandomUtils.nextInt(1,12)
			);
			MockRestServiceServer.bindTo(restTemplate).bufferContent().build();
		}
		BookDTO bookDTO = new BookDTO();
		BookStoreDTO bookStoreDTO = new BookStoreDTO();
		/*mockServer..expect(requestTo(new URI("http://localhost:8080/bookStore/1")))
				.andExpect(method(HttpMethod.GET))
				.andRespond(withStatus(HttpStatus.OK)
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.body(mapper.writeValueAsString(bookStoreDTO)))						;
		mockServer.verify();*/
	}

}