package com.nihongo.admin.service;

import com.nihongo.admin.entity.CategoryVocabulary;

import java.util.List;
import java.util.Set;

public interface ICategoryVocabularyService {
    List<CategoryVocabulary> findAll();
    List<CategoryVocabulary> getCategoryVocabularyByLevelByLevel(String level);

    Set<String> getAllCategoryVocabulary();

}
