package com.cml.filestorage.dto;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileRequestUploadDto {
    @NotNull
    private String name;
    @NotNull
    private Long size;
}
