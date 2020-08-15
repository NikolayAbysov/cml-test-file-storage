package com.cml.filestorage.service.impl;

import com.cml.filestorage.exception.FileDoesNotExistsException;
import com.cml.filestorage.exception.InvalidInputException;
import com.cml.filestorage.exception.TagDoesNotExistsException;
import com.cml.filestorage.model.File;
import com.cml.filestorage.repository.ElasticFileRepository;
import com.cml.filestorage.service.FileService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {

    private final ElasticFileRepository fileRepository;

    public FileServiceImpl(ElasticFileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public File save(File file) {
        if (file.getSize() <= 0 || file.getName().length() == 0) {
            throw new InvalidInputException("File has incorrect name or size");
        }
        return fileRepository.save(file);
    }

    @Override
    public void deleteById(String id) {
        Optional<File> fileOptional = fileRepository.findById(id);
        if (fileOptional.isPresent()) {
            fileRepository.deleteById(id);
        } else {
            throw new FileDoesNotExistsException("file not found");
        }
    }

    @Override
    public File assignTags(String id, List<String> tagList) {
        Optional<File> fileOptional = fileRepository.findById(id);
        File file;
        if (fileOptional.isPresent()) {
            file = fileOptional.get();
            file.getTagList().addAll(tagList);
            fileRepository.deleteById(id);
            fileRepository.save(file);
            return file;
        }
        throw new FileDoesNotExistsException("file not found");
    }

    @Override
    public File removeTags(String id, List<String> tagList) {
        Optional<File> fileOptional = fileRepository.findById(id);
        File file;
        if (fileOptional.isPresent()) {
            file = fileOptional.get();
            if (file.getTagList().containsAll(tagList)) {
                file.getTagList().removeAll(tagList);
                fileRepository.deleteById(id);
                fileRepository.save(file);
                return file;
            } else {
                throw new TagDoesNotExistsException("tag not found on file");
            }

        }
        throw new FileDoesNotExistsException("file not found");
    }

    @Override
    public List<File> find(List<String> tagList) {
        if (tagList.isEmpty()) {
            return fileRepository.findAll();
        }
        return fileRepository.getFilesByTagListIn(tagList);
    }
}
