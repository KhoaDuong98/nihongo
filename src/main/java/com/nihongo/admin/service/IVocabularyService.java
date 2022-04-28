package com.nihongo.admin.service;

import com.nihongo.admin.entity.Vocabulary;
import com.nihongo.admin.exception.VocabularyNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IVocabularyService {
    List<Vocabulary> findAll();
    List<Vocabulary> getVocabularyByLevel(String level);
    List<Vocabulary> getVocabularyByLevelAndCategoryVocabulary(String level,String categoryVocabulary);

    Vocabulary save(Vocabulary vocabulary);
    Vocabulary findById(Long vocabularyId) throws VocabularyNotFoundException;
    Vocabulary getVocabularyByWord(String word);
    boolean isWordUnique(Long id,String word);

    void deleteVocabulary(Vocabulary vocabulary);

}
