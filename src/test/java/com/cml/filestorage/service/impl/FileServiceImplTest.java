package com.cml.filestorage.service.impl;

import com.cml.filestorage.exception.FileDoesNotExistsException;
import com.cml.filestorage.exception.InvalidInputException;
import com.cml.filestorage.model.File;
import com.cml.filestorage.repository.ElasticFileRepository;
import lombok.NoArgsConstructor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

@SpringBootTest
@NoArgsConstructor
public class FileServiceImplTest {
    private File mockFile;
    private List<String> mockTagAddList;
    private List<String> mockTagRemoveList;

    @Mock
    private ElasticFileRepository fileRepository;
    @InjectMocks
    private FileServiceImpl fileService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        mockFile = new File();
        mockFile.setId("150i7nMB0fZPhytVdG84");
        mockFile.setName("Benio");
        mockFile.setSize(256L);
        List<String> tags = new ArrayList<>();
        tags.add("tag1");
        tags.add("tag4");
        mockFile.setTagList(tags);

        mockTagAddList = List.of("tag2", "tag3");
        mockTagRemoveList = List.of("tag4");
    }

    @Test
    public void saveOk() {
        Mockito.when(fileRepository.save(Mockito.any()))
                .thenReturn(mockFile);
        File expected = new File("150i7nMB0fZPhytVdG84",
                "Benio", 256L, List.of("tag1", "tag4"));
        File actual = fileService.save(mockFile);
        assertEquals(expected, actual);
    }

    @Test(expected = InvalidInputException.class)
    public void saveNotOk() {
        File file = new File();
        file.setSize(0L);
        fileService.save(file);
    }

    @Test(expected = FileDoesNotExistsException.class)
    public void deleteByIdNotOk() {
        fileService.deleteById("");
    }

    @Test
    public void assignTagsOk() {
        Optional<File> mockOptional = Optional.of(mockFile);
        Mockito.when(fileRepository.findById(Mockito.any())).thenReturn(mockOptional);
        File expected = new File("150i7nMB0fZPhytVdG84",
                "Benio", 256L, List.of("tag1", "tag4", "tag2", "tag3"));
        File actual = fileService.assignTags("150i7nMB0fZPhytVdG84", mockTagAddList);
        assertEquals(expected, actual);
    }

    @Test
    public void removeTagsOk() {
        Optional<File> mockOptional = Optional.of(mockFile);
        Mockito.when(fileRepository.findById(Mockito.any())).thenReturn(mockOptional);
        File expected = new File("150i7nMB0fZPhytVdG84",
                "Benio", 256L, List.of("tag1"));
        File actual = fileService.removeTags("150i7nMB0fZPhytVdG84", mockTagRemoveList);
        assertEquals(expected, actual);
    }
}
