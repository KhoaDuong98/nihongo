package com.nihongo.repository;

import com.nihongo.entity.Vocabulary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VocabularyRepository extends PagingAndSortingRepository<Vocabulary, Long> {
//    @Query("select v from Vocabulary v where v.level =:level and v.categoryVocabulary=:book order by v.vocabulary_id")
//    List<Vocabulary> findVocabularyByLevelAndCategoryVocabulary(String level, String book);

    Vocabulary findVocabularyByWord(String word);

    @Query("select v from Vocabulary v where v.level =:level and v.categoryVocabulary=:book order by v.vocabulary_id")
    Page<Vocabulary> findVocabularyByLevelAndCategoryVocabulary(String level, String book, Pageable pageable);

    @Query("SELECT v from  Vocabulary v where lower(CONCAT  (v.word, ' ', v.kanJ, ' ', v.mean, ' ', v.read)) LIKE %:keyword% and v.level=:level and v.categoryVocabulary=:book")
    Page<Vocabulary> search(String keyword, String level, String book, Pageable pageable);
}
