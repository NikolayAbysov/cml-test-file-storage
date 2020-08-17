package com.cml.filestorage.repository;
import com.cml.filestorage.model.File;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticFileRepository extends ElasticsearchRepository<File, String> {
    Optional<File> findById(String id);

    @Query("{\"query_string\": {\"query\": \"?0\"}}")
    Page<File> searchInTagList(String keyword, Pageable pageable);

    Page<File> findAll(Pageable pageable);

    boolean existsById(String id);
}
