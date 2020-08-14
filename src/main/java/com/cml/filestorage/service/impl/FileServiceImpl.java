package com.cml.filestorage.service.impl;

import com.cml.filestorage.model.File;
import com.cml.filestorage.repository.ElasticFileRepository;
import com.cml.filestorage.service.FileService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    private final ElasticFileRepository fileRepository;

    public FileServiceImpl(ElasticFileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public File save(File file) {
        return fileRepository.save(file);
    }

    @Override
    public File getFileById(Long id) {
        return fileRepository.getFileById(id);
    }

    @Override
    public void deleteById(Long id) {
        fileRepository.deleteById(id);
    }

    @Override
    public File assignTags(File file, List<String> tagList) {
        file.getTagList().addAll(tagList);
        return fileRepository.update(file);
    }

    @Override
    public File removeTags(File file, List<String> tagList) {
        file.getTagList().removeAll(tagList);
        return fileRepository.update(file);
    }

    @Override
    public List<File> findByTagList(List<String> tagList) {
        return fileRepository.getFilesByTagListIn(tagList);
    }
}
