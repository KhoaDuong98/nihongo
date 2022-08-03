package com.nihongo.service;

import com.nihongo.entity.CategoryVocabulary;

import java.util.List;
import java.util.Set;

public interface ICategoryVocabularyService {
    List<CategoryVocabulary> findAll();
    List<CategoryVocabulary> getCategoryVocabularyByLevel(String level);

    Set<String> getAllCategoryVocabulary();

}
