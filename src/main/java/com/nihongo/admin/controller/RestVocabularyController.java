package com.nihongo.admin.controller;

import com.nihongo.admin.service.IVocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestVocabularyController {
    @Autowired
    private IVocabularyService vocabularyService;

    @Autowired
    MessageSource messageSource;

    @PostMapping("/admin/vocabulary/check_word")
    public String checkDuplicateWord(@Param("id") Long id,@Param("word") String word){
        return vocabularyService.isWordUnique(id,word) ? "OK" : "Duplicate";

    }
}
