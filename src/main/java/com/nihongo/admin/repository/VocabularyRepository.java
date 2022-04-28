package com.nihongo.admin.repository;

import com.nihongo.admin.entity.Vocabulary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VocabularyRepository extends CrudRepository<Vocabulary,Long> {

    public List<Vocabulary> getVocabularyByLevel(String level);

    Vocabulary findVocabularyByWord(String word);

    public List<Vocabulary> findVocabularyByLevelAndCategoryVocabulary(String level,String categoryVocabulary);


}
