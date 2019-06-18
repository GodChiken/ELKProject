package com.example.demo;


import com.kbh.elk.app.ELKApplication;
import com.kbh.elk.app.entity.Book;
import com.kbh.elk.app.presentation.ELKController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ELKApplication.class)
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

}