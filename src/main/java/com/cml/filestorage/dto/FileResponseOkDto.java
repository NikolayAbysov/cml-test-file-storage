package com.cml.filestorage.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileResponseOkDto {
    private String success;

    public FileResponseOkDto() {
        this.success = "true";
    }
}
