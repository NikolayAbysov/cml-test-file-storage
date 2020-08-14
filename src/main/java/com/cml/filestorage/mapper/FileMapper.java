package com.cml.filestorage.mapper;

import com.cml.filestorage.dto.FileRequestGetDto;
import com.cml.filestorage.dto.FileUploadDto;
import com.cml.filestorage.model.File;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FileMapper {
    public File map(FileUploadDto fileUploadDto) {
        File file = new File();
        file.setName(fileUploadDto.getName());
        file.setSize(fileUploadDto.getSize());
        return file;
    }

    public List<FileRequestGetDto> map(List<File> fileList) {
        List<FileRequestGetDto> dtoList = new ArrayList<>();
        for (File file : fileList) {
            dtoList.add(map(file));
        }
        return dtoList;
    }

    private FileRequestGetDto map(File file) {
        FileRequestGetDto dto = new FileRequestGetDto();
        dto.setId(file.getId());
        dto.setName(file.getName());
        dto.setSize(file.getSize());
        dto.setTagList(file.getTagList());
        return dto;
    }
}
