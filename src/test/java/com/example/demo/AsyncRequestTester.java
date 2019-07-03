package com.example.demo;


import com.kbh.elk.app.ELKApplication;
import com.kbh.elk.app.presentation.ELKController;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,classes = ELKApplication.class)
public class AsyncRequestTester {

	private MockMvc mockMvc;

	@MockBean
	ELKController elkController;

	@Before
	public void setUp(){
		mockMvc = MockMvcBuilders.standaloneSetup(elkController).build();
	}

	@Test
	public void asyncRequest() throws Exception{
		for (int i = 0; i < 10000; i++) {
			mockMvc.perform(get("/book/{bookIdx}",1))
					.andExpect(status().isOk())
					.andDo(print());
		}
	}

	@Test
	public void randomRequest() throws Exception{

		List<String> url = new ArrayList<>();
		url.add("http://localhost:8080/book/{bookIdx}");
		url.add("http://localhost:8080/bookStore/{bookStoreIdx}");

		for (int i = 0; i < 8000; i++) {
			sleep(RandomUtils.nextInt(1000,3000));
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getForEntity(url.get(
					RandomUtils.nextInt(0,2))
					,String.class
					,RandomUtils.nextInt(1,9)
			);
		}
	}

}