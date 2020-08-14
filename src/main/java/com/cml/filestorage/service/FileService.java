package com.cml.filestorage.service;

import com.cml.filestorage.model.File;

import java.util.List;

public interface FileService {
    File save(File file);

    File getFileById(Long id);

    void deleteById(Long id);

    File assignTags(File file, List<String> tagList);

    File removeTags(File file, List<String> tagList);

    List<File> findByTagList(List<String> tagList);
}
