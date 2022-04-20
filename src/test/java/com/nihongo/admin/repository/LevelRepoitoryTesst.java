package com.nihongo.admin.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.nihongo.admin.entity.Vocabulary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.nihongo.admin.entity.Level;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class LevelRepoitoryTesst {
	
	@Autowired
	private VocabularyRepository repo;
	
	

	@Test
	public void testCreateLevel() {
		Vocabulary vocabulary = repo.findById(1L).get();
		vocabulary.setWord("一家");
		vocabulary.setExample("asd");
		System.out.println(vocabulary.getLevel());
		repo.save(vocabulary);
	}

}
