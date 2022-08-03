package com.nihongo.service.impl;

import com.nihongo.entity.Vocabulary;
import com.nihongo.exception.VocabularyNotFoundException;
import com.nihongo.repository.VocabularyRepository;
import com.nihongo.service.IVocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VocabularyServiceImpl implements IVocabularyService {
	public static final int LIMIT = 10;
	@Autowired
	private VocabularyRepository repo;

	@Override
	public List<Vocabulary> findAll() {
		return (List<Vocabulary>) repo.findAll();
	}

//    @Override
//    public List<Vocabulary> getVocabularyByLevelAndCategoryVocabulary(String level, String book) {
//        return repo.findVocabularyByLevelAndCategoryVocabulary(level,book);
//    }

	@Override
	public Vocabulary save(Vocabulary vocabulary) {

		return repo.save(vocabulary);
	}

	@Override
	public Vocabulary findById(Long vocabularyId) throws VocabularyNotFoundException {
		try {
			return repo.findById(vocabularyId).get();
		} catch (NoSuchElementException e) {
			throw new VocabularyNotFoundException("Could not find any Vocabulary with " + vocabularyId);
		}
	}

	@Override
	public Vocabulary getVocabularyByWord(String word) {
		return repo.findVocabularyByWord(word);
	}

	@Override
	public boolean isWordUnique(Long id, String word) {
		Vocabulary vocabulary = repo.findVocabularyByWord(word);
		if (vocabulary == null)
			return true;
		boolean isCreate = (id == null);
		if (isCreate) {
			if (vocabulary != null)
				return false;
		} else {
			if (vocabulary.getId() != id)
				return false;
		}
		return true;
	}

	@Override
	public void deleteVocabulary(Vocabulary vocabulary) {
		repo.delete(vocabulary);
	}

	@Override
	public Page<Vocabulary> getVocabularyByLevelAndCategoryVocabulary(String level, String categoryVocabulary,
			String word, int pageNumber) {
		Pageable pageable = PageRequest.of((int) (pageNumber - 1), LIMIT);
		if (word != null && word.length() > 0) {
			return repo.search(word, level, categoryVocabulary, pageable);

		}
		return repo.findVocabularyByLevelAndCategoryVocabulary(level, categoryVocabulary, pageable);

	}

}
