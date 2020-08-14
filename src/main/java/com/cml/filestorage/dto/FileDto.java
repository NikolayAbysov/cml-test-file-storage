package com.cml.filestorage.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FileDto {
    private String id;
    private String name;
    private Long size;
    private List<String> tags;
}
