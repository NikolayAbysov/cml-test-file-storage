package com.cml.filestorage.service.impl;

import com.cml.filestorage.exception.FileDoesNotExistsException;
import com.cml.filestorage.exception.InvalidInputException;
import com.cml.filestorage.exception.TagException;
import com.cml.filestorage.model.File;
import com.cml.filestorage.repository.ElasticFileRepository;
import com.cml.filestorage.service.FileService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final ElasticFileRepository fileRepository;

    @Override
    public File save(File file) {
        if (file.getSize() <= 0) {
            throw new InvalidInputException("File has incorrect size");
        }
        if (file.getName().length() == 0) {
            throw new InvalidInputException("File has incorrect name");
        }
        return fileRepository.save(file);
    }

    @Override
    public void deleteById(String id) {
        if (fileRepository.existsById(id)) {
            fileRepository.deleteById(id);
        } else {
            throw new FileDoesNotExistsException("file not found");
        }
    }

    @Override
    public File assignTags(String id, List<String> tagList) {
        List<String> listWithoutDuplicates = new ArrayList<>(new HashSet<>(tagList));
        if (listWithoutDuplicates.size() != tagList.size()) {
            throw new TagException("duplication tags found");
        }

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
                throw new TagException("tag not found on file");
            }
        }
        throw new FileDoesNotExistsException("file not found");
    }

    @Override
    public Page<File> find(List<String> tagList, Pageable pageable) {
        if (tagList.isEmpty()) {
            return fileRepository.findAll(pageable);
        }

        StringBuilder stringBuilder = new StringBuilder();
        StringJoiner stringJoiner = new StringJoiner(" AND ");
        for (String tag : tagList) {
            stringBuilder.append("tagList:");
            stringBuilder.append(tag);
            stringJoiner.add(stringBuilder.toString());
            stringBuilder.setLength(0);
        }
        return fileRepository.searchInTagList("(" + stringJoiner.toString() + ")", pageable);
    }

    @Override
    public File getById(String id) {
        return fileRepository.findById(id)
                .orElseThrow(() -> new FileDoesNotExistsException("file not found"));
    }
}
