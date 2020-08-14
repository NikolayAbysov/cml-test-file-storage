package com.cml.filestorage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import java.util.List;

import static org.springframework.data.elasticsearch.annotations.FieldType.Long_Range;
import static org.springframework.data.elasticsearch.annotations.FieldType.Text;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "tag")
public class File {
    @Id
    private Long id;
    @Field(type = Text)
    private String name;
    @Field(type = Long_Range)
    private Long size;
    @Field(type = FieldType.Keyword)
    private List<String> tagList;
}
