package com.cml.filestorage.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileDto {
    private String id;
    private String name;
    private Long size;
    private List<String> tags;
}
