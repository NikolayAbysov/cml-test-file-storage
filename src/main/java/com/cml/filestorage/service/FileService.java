package com.cml.filestorage.service;

import com.cml.filestorage.model.File;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FileService {
    File save(File file);

    void deleteById(String id);

    File assignTags(String id, List<String> tagList);

    File removeTags(String id, List<String> tagList);

    Page<File> find(List<String> tagList, Pageable pageable);
}
