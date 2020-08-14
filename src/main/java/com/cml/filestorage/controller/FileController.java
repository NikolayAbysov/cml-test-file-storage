package com.cml.filestorage.controller;

import com.cml.filestorage.dto.FileRequestGetDto;
import com.cml.filestorage.dto.FileTagsDto;
import com.cml.filestorage.dto.FileUploadDto;
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
    public void uploadFile(@RequestBody FileUploadDto fileUploadDto) {
        fileService.save(fileMapper.map(fileUploadDto));
    }

    @DeleteMapping("/{Id}")
    public void deleteFile(@PathVariable String Id){
        fileService.deleteById(Long.parseLong(Id));
    }

    @PostMapping("/{Id}/tags")
    public void assignTags(@PathVariable String Id,
                           @RequestBody FileTagsDto fileTagsDto) {
        File file = fileService.getFileById(Long.parseLong(Id));
        fileService.assignTags(file, fileTagsDto.getTagList());
    }

    @DeleteMapping("/{Id}/tags")
    public void removeTags(@PathVariable String Id,
                           @RequestBody FileTagsDto fileTagsDto) {
        File file = fileService.getFileById(Long.parseLong(Id));
        fileService.removeTags(file, fileTagsDto.getTagList());
    }

    @GetMapping()
    public List<FileRequestGetDto> getFileList(@RequestParam List<String> tagList) {
        List<File> fileList = fileService.findByTagList(tagList);
        return fileMapper.map(fileList);
    }
}
