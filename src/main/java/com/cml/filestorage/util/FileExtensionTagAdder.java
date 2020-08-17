package com.cml.filestorage.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class FileExtensionTagAdder {
    private Map<String, String> filesExtensionMap;

    {
        filesExtensionMap = new HashMap<>();
        filesExtensionMap.put(".mp3", "audio");
        filesExtensionMap.put(".wav", "audio");
        filesExtensionMap.put(".avi", "video");
        filesExtensionMap.put(".mpeg", "video");
        filesExtensionMap.put(".flv", "video");
        filesExtensionMap.put(".doc", "document");
        filesExtensionMap.put(".docx", "document");
        filesExtensionMap.put(".txt", "document");
        filesExtensionMap.put(".png", "image");
        filesExtensionMap.put(".jpeg", "image");
        filesExtensionMap.put(".bmp", "image");

    }

    public Optional<String> getExtensionTag(String fileName) {
        String tagValue = null;
        for (Map.Entry<String, String> entry : filesExtensionMap.entrySet()) {
            if (fileName.endsWith(entry.getKey())) {
                tagValue = entry.getValue();
            }
        }
        return Optional.of(tagValue);
    }
}
