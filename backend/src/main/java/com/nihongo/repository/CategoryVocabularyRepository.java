package com.nihongo.repository;

import com.nihongo.entity.CategoryVocabulary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryVocabularyRepository extends CrudRepository<CategoryVocabulary,Long> {
    List<CategoryVocabulary> findCategoryVocabularyByLevel(String level);


}
