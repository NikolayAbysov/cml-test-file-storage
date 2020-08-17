package com.cml.filestorage.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FileRequestUploadDto {
    @NotNull
    private String name;
    @NotNull
    private Long size;
}
