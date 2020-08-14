package com.cml.filestorage.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FileRequestGetDto {
    private int total;
    private List<FileDto> list;
}
