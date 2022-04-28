package com.nihongo.admin.service.impl;

import com.nihongo.admin.entity.Vocabulary;
import com.nihongo.admin.exception.VocabularyNotFoundException;
import com.nihongo.admin.repository.VocabularyRepository;
import com.nihongo.admin.service.IVocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VocabularyServiceImpl implements IVocabularyService {
    @Autowired
    private VocabularyRepository repo;

    @Override
    public List<Vocabulary> findAll() {
        return (List<Vocabulary>) repo.findAll();
    }

    @Override
    public List<Vocabulary> getVocabularyByLevel(String level) {
        return repo.getVocabularyByLevel(level);
    }

    @Override
    public List<Vocabulary> getVocabularyByLevelAndCategoryVocabulary(String level, String categoryVocabulary) {
        return repo.findVocabularyByLevelAndCategoryVocabulary(level,categoryVocabulary);
    }

    @Override
    public Vocabulary save(Vocabulary vocabulary) {

        return repo.save(vocabulary);
    }

    @Override
    public Vocabulary findById(Long vocabularyId) throws VocabularyNotFoundException {
        try {
            return repo.findById(vocabularyId).get();
        }catch (NoSuchElementException e){
            throw new VocabularyNotFoundException("Could not find any Vocabulary with " + vocabularyId);
        }
    }

    @Override
    public Vocabulary getVocabularyByWord(String word) {
        return repo.findVocabularyByWord(word);
    }
    @Override
    public boolean isWordUnique(Long id,String word){
        Vocabulary vocabulary = repo.findVocabularyByWord(word);
        if(vocabulary == null) return  true;
        boolean isCreate = (id == null);
        if(isCreate){
            if(vocabulary != null) return false;
        }else {
            if(vocabulary.getId() != id) return false;
        }
        return true;
    }

    @Override
    public void deleteVocabulary(Vocabulary vocabulary) {
        repo.delete(vocabulary);
    }


}
