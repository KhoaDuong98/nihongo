package com.nihongo.admin.service.impl;

import com.nihongo.admin.entity.CategoryVocabulary;
import com.nihongo.admin.entity.Vocabulary;
import com.nihongo.admin.repository.CategoryVocabularyRepository;
import com.nihongo.admin.service.ICategoryVocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.expression.Lists;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CategoryVocabularyImpl implements ICategoryVocabularyService {
    @Autowired
    CategoryVocabularyRepository repo;

    @Override
    public List<CategoryVocabulary> findAll() {
        return (List<CategoryVocabulary>) repo.findAll();
    }

    @Override
    public List<CategoryVocabulary> getCategoryVocabularyByLevelByLevel(String level) {
        return repo.findCategoryVocabularyByLevel(level);
    }

    @Override
    public Set<String> getAllCategoryVocabulary() {
        List<CategoryVocabulary> categories = (List<CategoryVocabulary>) repo.findAll();
        Set<String> listCateVocabulary = new HashSet<>();
        for (CategoryVocabulary i : categories){
            listCateVocabulary.add(i.getName());
        }

        return listCateVocabulary;
    }

}
