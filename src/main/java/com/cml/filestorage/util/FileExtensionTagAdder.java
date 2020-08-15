package com.cml.filestorage.util;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class FileExtensionTagAdder {
    private static Map<String, String> filesExtensionMap;

    static {
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

    public static String getExtensionTag(String fileName) {
        for (Map.Entry<String, String> entry : filesExtensionMap.entrySet()) {
            if (fileName.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }
}
