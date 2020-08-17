package com.cml.filestorage.controller;

import com.cml.filestorage.dto.FileDto;
import com.cml.filestorage.dto.FileRequestGetDto;
import com.cml.filestorage.exception.InvalidInputException;
import com.cml.filestorage.mapper.FileMapper;
import com.cml.filestorage.model.File;
import com.cml.filestorage.service.FileService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.List;
import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = FileController.class)
class FileControllerTest {
    private File mockFile;

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FileMapper fileMapper;
    @MockBean
    private FileService fileService;

    @Before
    void setUp() {
        FileDto fileDto = new FileDto();
        fileDto.setId("150i7nMB0fZPhytVdG84");
        fileDto.setName("Benio");
        fileDto.setSize(256L);
        fileDto.setTags(List.of("tag3"));

        FileRequestGetDto fileRequestGetDto = new FileRequestGetDto();
        fileRequestGetDto.setTotal(1);
        fileRequestGetDto.setList(List.of(fileDto));

        mockFile = new File();
        mockFile.setId("150i7nMB0fZPhytVdG84");
        mockFile.setSize(256L);
        mockFile.setName("Benio");
        mockFile.setTagList(List.of("tag3"));
    }

    @Test
    void uploadFileOk() throws Exception {
        Mockito.when(fileService.save(Mockito.any())).thenReturn(mockFile);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/file")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Benio\", \"size\":\"256\"}")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void uploadFileNotOk() throws Exception {
        Mockito.when(fileService.save(Mockito.any()))
                .thenThrow(new InvalidInputException("File has incorrect name or size"));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/file")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Benio\", \"size\":\"-256\"}")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    void deleteFileOk() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/file/150i7nMB0fZPhytVdG84")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void assignTagsOk() throws Exception {
        Mockito.when(fileService.assignTags(Mockito.any(), Mockito.anyList()))
                .thenReturn(mockFile);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/file/KKt48XMBFb4XKQ4EMG8w/tags")
                .accept(MediaType.APPLICATION_JSON)
                .content("[\"tag1\", \"tag2\", \"tag3\"]")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void removeTagsOk() throws Exception {
        Mockito.when(fileService.removeTags(Mockito.any(), Mockito.anyList()))
                .thenReturn(mockFile);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/file/KKt48XMBFb4XKQ4EMG8w/tags")
                .accept(MediaType.APPLICATION_JSON)
                .content("[\"tag1\", \"tag2\", \"tag3\"]")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}
