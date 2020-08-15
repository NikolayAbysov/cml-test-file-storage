package com.cml.filestorage.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileRequestGetDto {
    private int total;
    private List<FileDto> list;
}
