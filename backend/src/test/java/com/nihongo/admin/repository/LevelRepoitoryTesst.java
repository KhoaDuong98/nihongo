package com.nihongo.admin.repository;

import static org.assertj.core.api.Assertions.assertThat;


import com.nihongo.entity.Vocabulary;
import com.nihongo.repository.VocabularyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class LevelRepoitoryTesst {
	
	@Autowired
	private VocabularyRepository repo;


	@Test
	public void testCreateLevel() {


		Pageable pageable = PageRequest.of(0,3);
		Page<Vocabulary> page = repo.findVocabularyByLevelAndCategoryVocabulary("N2","Tango",pageable);
		List<Vocabulary> vocabularies = page.getContent();

		for (Vocabulary i : vocabularies){
			System.out.println(i.getWord());
		}

	}

}
