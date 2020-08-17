package com.cml.filestorage.controller;

import com.cml.filestorage.dto.FileRequestGetDto;
import com.cml.filestorage.dto.FileRequestUploadDto;
import com.cml.filestorage.dto.FileResponseOkDto;
import com.cml.filestorage.dto.FileResponseUploadDto;
import com.cml.filestorage.exception.InvalidInputException;
import com.cml.filestorage.mapper.FileMapper;
import com.cml.filestorage.model.File;
import com.cml.filestorage.service.FileService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
public class FileController {
    private final FileService fileService;
    private final FileMapper fileMapper;

    public FileController(FileService fileService, FileMapper fileMapper) {
        this.fileService = fileService;
        this.fileMapper = fileMapper;
    }

    @PostMapping
    public FileResponseUploadDto uploadFile(
            @RequestBody @Valid FileRequestUploadDto fileRequestUploadDto,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidInputException("both name and size parameters are required");
        }
        File file = fileService.save(fileMapper.map(fileRequestUploadDto));
        return fileMapper.mapResponse(file);
    }

    @DeleteMapping("/{id}")
    public FileResponseOkDto deleteFile(@PathVariable String id) {
        fileService.deleteById(id);
        return new FileResponseOkDto();
    }

    @PostMapping("/{id}/tags")
    public FileResponseOkDto assignTags(@PathVariable String id,
                           @RequestBody List<String> tags) {
        fileService.assignTags(id, tags);
        return new FileResponseOkDto();
    }

    @DeleteMapping("/{id}/tags")
    public FileResponseOkDto removeTags(@PathVariable String id,
                           @RequestBody List<String> tags) {
        fileService.removeTags(id, tags);
        return new FileResponseOkDto();
    }

    @GetMapping
    public FileRequestGetDto getFileList(@RequestParam (defaultValue = "") List<String> tags,
                                         @RequestParam (value = "page",
                                                 required = false,
                                                 defaultValue = "0") Integer page,
                                         @RequestParam (value = "size",
                                                 required = false,
                                                 defaultValue = "10") Integer size) {
        Page<File> fileList = fileService.find(tags, PageRequest.of(page, size));
        return fileMapper.map(fileList);
    }
}
