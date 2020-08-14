package com.cml.filestorage.repository;

import com.cml.filestorage.model.File;
import java.util.List;
import java.util.Optional;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticFileRepository extends ElasticsearchRepository<File, String> {
    Optional<File> findById(String id);

    List<File> getFilesByTagListIn(List<String> tagList);

    List<File> findAll();
}
