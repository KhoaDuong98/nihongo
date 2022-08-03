package com.nihongo.service;

import com.nihongo.entity.Vocabulary;
import com.nihongo.exception.VocabularyNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IVocabularyService {
    List<Vocabulary> findAll();

    Vocabulary save(Vocabulary vocabulary);
    Vocabulary findById(Long vocabularyId) throws VocabularyNotFoundException;
    Vocabulary getVocabularyByWord(String word);
    boolean isWordUnique(Long id,String word);

    void deleteVocabulary(Vocabulary vocabulary);

    Page<Vocabulary> getVocabularyByLevelAndCategoryVocabulary(String level, String categoryVocabulary,String word, int pageNumber);

//    List<Vocabulary> searchVocabulary(String word,String level, String book);

}
