package com.cml.filestorage.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileRequestUploadDto {
    private String name;
    private Long size;
}
