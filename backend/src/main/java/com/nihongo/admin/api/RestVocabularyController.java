package com.nihongo.admin.api;

import com.nihongo.NihongoApplication;
import com.nihongo.entity.CategoryVocabulary;
import com.nihongo.entity.Vocabulary;
import com.nihongo.exception.VocabularyNotFoundException;
import com.nihongo.service.ILevelService;
import com.nihongo.service.IVocabularyService;
import com.nihongo.service.impl.CategoryVocabularyImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin("http://localhost:3001")

@RestController
@RequestMapping("/admin/api")

public class RestVocabularyController {
    private static final Logger LOGGER = LogManager.getLogger(NihongoApplication.class);

    @Autowired
    private IVocabularyService vocabularyService;
    @Autowired
    CategoryVocabularyImpl categoryVocabularyService;
    @Autowired
    private ILevelService levelService;

//    @GetMapping("/vocabulariess")
//    public ResponseEntity<?> listCategoryVocabulary(@RequestParam Map<String,String> reqParam, Model model) {
//        String level = (String) reqParam.get("level");
//        String categoryVocabulary = reqParam.get("categoryVocabulary");
//        List<Vocabulary> vocabularies = vocabularyService.getVocabularyByLevelAndCategoryVocabulary(level,categoryVocabulary);
//        Map<String, Object> result = new HashMap<String, Object>();
//        try {
//            if (vocabularies.size() > 0) {
//                result.put("vocabularies", vocabularies);
//                return new ResponseEntity<>(result, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//    }

    @GetMapping("/vocabularies")
    public ResponseEntity<?> vocabularyByLevel(@RequestParam Map<String, String> reqParam, Model model) {
        String level = (String) reqParam.get("level");
        String categoryVocabulary = reqParam.get("categoryVocabulary");
        String word = reqParam.get("word");
        int page = Integer.parseInt(reqParam.get("page"));
        Page<Vocabulary> vocabularies = vocabularyService.getVocabularyByLevelAndCategoryVocabulary(level,categoryVocabulary,word, page);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("categoryVocabulary", categoryVocabulary);
        result.put("listLevel", levelService.findAll());
        LOGGER.info("result {}", result);
        try {
            if (vocabularies.getTotalElements() > 0) {
                result.put("vocabularies", vocabularies.getContent());
                result.put("totalPage", vocabularies.getTotalPages());
                result.put("totalElements", vocabularies.getTotalElements());

                LOGGER.info("result {}", result.get("vocabularies"));

                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/vocabulary/check_word")
    public String checkDuplicateWord(@Param("id") Long id, @Param("word") String word) {
        return vocabularyService.isWordUnique(id, word) ? "OK" : "Duplicate";

    }

    @PostMapping("/vocabulary/save")
    public ResponseEntity<Vocabulary> saveVocabulary(@RequestBody Vocabulary vocabulary) {

        return new ResponseEntity<>(vocabularyService.save(vocabulary), HttpStatus.OK);

    }
//    @GetMapping("/vocabulary/search")
//    public ResponseEntity<?> search( @Param("keyword") String keyword, @Param("level") String level,@Param("book") String book) {
//        List<Vocabulary> vocabularyList = vocabularyService.searchVocabulary(keyword,level,book);
//
//        return new ResponseEntity<>(vocabularyList,HttpStatus.OK);
//
//    }

    @GetMapping("/vocabularies/{level}/edit/vocabularyId/{vocabularyId}")
    public ResponseEntity<?> editVocabulary(@PathVariable("vocabularyId") Long vocabularyId, @PathVariable("level") String level, Model model) throws VocabularyNotFoundException {
        Map<String, Object> result = new HashMap<String, Object>();
        Vocabulary vocabulary = vocabularyService.findById(vocabularyId);
        result.put("listLevel", levelService.findAll());
        result.put("listCateVocabulary", categoryVocabularyService.getAllCategoryVocabulary());
        if (vocabulary != null) {
            result.put("vocabulary", vocabulary);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
