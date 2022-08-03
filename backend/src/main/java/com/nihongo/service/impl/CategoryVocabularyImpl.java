package com.nihongo.service.impl;

import com.nihongo.entity.CategoryVocabulary;
import com.nihongo.repository.CategoryVocabularyRepository;
import com.nihongo.service.ICategoryVocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<CategoryVocabulary> getCategoryVocabularyByLevel(String level) {
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
