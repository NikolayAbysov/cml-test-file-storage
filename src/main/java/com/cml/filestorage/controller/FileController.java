package com.cml.filestorage.controller;

import com.cml.filestorage.dto.FileRequestGetDto;
import com.cml.filestorage.dto.FileResponseOkDto;
import com.cml.filestorage.dto.FileResponseUploadDto;
import com.cml.filestorage.dto.FileRequestUploadDto;
import com.cml.filestorage.mapper.FileMapper;
import com.cml.filestorage.model.File;
import com.cml.filestorage.service.FileService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public FileResponseUploadDto uploadFile(@RequestBody FileRequestUploadDto fileRequestUploadDto) {
        File file = fileService.save(fileMapper.map(fileRequestUploadDto));
        return fileMapper.mapResponse(file);
    }

    @DeleteMapping("/{Id}")
    public FileResponseOkDto deleteFile(@PathVariable String Id){
        fileService.deleteById(Id);
        return new FileResponseOkDto();
    }

    @PostMapping("/{Id}/tags")
    public FileResponseOkDto assignTags(@PathVariable String Id,
                           @RequestBody List<String> tags) {
        fileService.assignTags(Id, tags);
        return new FileResponseOkDto();
    }

    @DeleteMapping("/{Id}/tags")
    public FileResponseOkDto removeTags(@PathVariable String Id,
                           @RequestBody List<String> tags) {
        fileService.removeTags(Id, tags);
        return new FileResponseOkDto();
    }

    @GetMapping
    public FileRequestGetDto getFileList(@RequestParam (defaultValue = "") List<String> tags) {
        List<File> fileList = fileService.findByTagList(tags);
        return fileMapper.map(fileList);
    }
}
