package com.cml.filestorage.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "files")
public class File {
    @Id
    private String id;
    private String name;
    private Long size;
    private List<String> tagList;
}
