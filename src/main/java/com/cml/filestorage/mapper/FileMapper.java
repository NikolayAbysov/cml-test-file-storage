package com.cml.filestorage.mapper;

import com.cml.filestorage.dto.FileDto;
import com.cml.filestorage.dto.FileRequestGetDto;
import com.cml.filestorage.dto.FileRequestUploadDto;
import com.cml.filestorage.dto.FileResponseUploadDto;
import com.cml.filestorage.model.File;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class FileMapper {
    public File map(FileRequestUploadDto fileRequestUploadDto) {
        File file = new File();
        file.setName(fileRequestUploadDto.getName());
        file.setSize(fileRequestUploadDto.getSize());
        file.setTagList(new ArrayList<>());
        return file;
    }

    public FileRequestGetDto map(List<File> fileList) {
        FileRequestGetDto dto = new FileRequestGetDto();
        dto.setList(new ArrayList<>());
        for (File file : fileList) {
            dto.getList().add(map(file));
        }
        dto.setTotal(dto.getList().size());
        return dto;
    }

    private FileDto map(File file) {
        FileDto dto = new FileDto();
        dto.setId(file.getId());
        dto.setName(file.getName());
        dto.setSize(file.getSize());
        dto.setTags(file.getTagList());
        return dto;
    }

    public FileResponseUploadDto mapResponse(File file) {
        FileResponseUploadDto dto = new FileResponseUploadDto();
        dto.setId(file.getId());
        return dto;
    }
}
