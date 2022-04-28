package com.nihongo.admin.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.nihongo.admin.entity.CategoryVocabulary;
import com.nihongo.admin.entity.Vocabulary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.nihongo.admin.entity.Level;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class LevelRepoitoryTesst {
	
	@Autowired
	private CategoryVocabularyRepository repo;
	
	

	@Test
	public void testCreateLevel() {
		List<CategoryVocabulary> vocabularies = (List<CategoryVocabulary>) repo.findAll();
		Set<String> test = new HashSet<>();

		for (CategoryVocabulary i : vocabularies){
			test.add(i.getName());
		}
		System.out.println(test.toString());

	}

}
