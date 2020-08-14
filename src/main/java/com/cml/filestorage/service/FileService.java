package com.cml.filestorage.service;

import com.cml.filestorage.model.File;

import java.io.FileNotFoundException;
import java.util.List;

public interface FileService {
    File save(File file);

    void deleteById(String id);

    File assignTags(String id, List<String> tagList);

    File removeTags(String id, List<String> tagList);

    List<File> findByTagList(List<String> tagList);
}
