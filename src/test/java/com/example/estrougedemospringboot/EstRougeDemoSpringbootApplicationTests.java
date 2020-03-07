package com.example.estrougedemospringboot;

import com.example.estrougedemospringboot.api.v1.work.WorkController;
import com.example.estrougedemospringboot.models.StatusEnum;
import com.example.estrougedemospringboot.models.Work;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EstRougeDemoSpringbootApplicationTests {

    @Autowired
    private WorkController workController;

    private MockMvc mockMvc;

    // This object will be magically initialized by the initFields method below.
    private JacksonTester<Work> jsonWork;

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        this.mockMvc = MockMvcBuilders.standaloneSetup(workController).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
    }

    @Test
    public void canGetByPage() throws Exception {
        int sizePage = 3;
        mockMvc.perform(get("/api/v1/works?page=0&size=" + sizePage + "&sort=id,DESC").contentType(MediaType.APPLICATION_JSON)) // Thực hiện GET REQUEST
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(sizePage));

    }

    @Test
    public void canCreateNewWork() throws Exception {
        // when
        mockMvc.perform(post("/api/v1/works/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonWork.write(new Work(null, "Developer", StatusEnum.PLANNING,new Date(),new Date())).getJson()))
                .andExpect(status().isOk());

    }

    @Test
    public void canUpdateTheWork() throws Exception {
        mockMvc.perform(put("/api/v1/works/8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonWork.write(new Work(null, "Developer Updated", StatusEnum.PLANNING,new Date(),new Date())).getJson()))
                .andExpect(status().isOk());

    }

    @Test
    public void canDeleteTheWork() throws Exception {
        mockMvc.perform(delete("/api/v1/works/8")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}
