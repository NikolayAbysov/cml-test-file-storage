package com.cml.filestorage.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FileRequestUploadDto {
    @NotNull(message = "file name is mandatory")
    private String name;
    @NotNull(message = "file size is mandatory")
    private Long size;
}
