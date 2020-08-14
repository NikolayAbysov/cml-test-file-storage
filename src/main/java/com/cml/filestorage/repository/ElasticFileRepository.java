package com.cml.filestorage.repository;

import com.cml.filestorage.model.File;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ElasticFileRepository extends ElasticsearchRepository<File, Long> {
    File getFileById(Long id);

    File update(File file);

    List<File> getFilesByTagListIn(List<String> tagList);
}
