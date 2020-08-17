package com.cml.filestorage.handler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class CustomErrorResponse {
    private boolean success;
    private String error;
}
