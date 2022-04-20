package com.nihongo.admin.controller;

import com.nihongo.admin.entity.Vocabulary;
import com.nihongo.admin.exception.VocabularyNotFoundException;
import com.nihongo.admin.service.ILevelService;
import com.nihongo.admin.service.IVocabularyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class VocabularyController {
    public static final Logger logger = LogManager.getLogger(VocabularyController.class);
    @Autowired
    private ILevelService levelService;

    @Autowired
    private IVocabularyService vocabularyService;

    @GetMapping("/admin/vocabulary/add")
    public String addVocabulary(Model model) {
        model.addAttribute("vocabulary", new Vocabulary());
        model.addAttribute("listLevel", levelService.findAll());
        return "createVocabulary";
    }

    @PostMapping("/admin/vocabulary/save")
    public String saveVocabulary(@ModelAttribute("vocabulary") Vocabulary vocabulary) throws VocabularyNotFoundException {

        vocabularyService.save(vocabulary);

        String level = vocabulary.getLevel();
        return "redirect:/admin/vocabularies/" + level;

    }

    @GetMapping("/admin/vocabularies/{level}/edit/vocabularyId/{vocabularyId}")
    public String editVocabulary(@PathVariable("vocabularyId") Long vocabularyId, @PathVariable("level") String level, Model model) {
        try {
                logger.info("Find vocabulary by Id start");
                Vocabulary vocabulary = vocabularyService.findById(vocabularyId);
                model.addAttribute("vocabulary", vocabulary);
                model.addAttribute("listLevel", levelService.findAll());
        }catch (VocabularyNotFoundException e){
            logger.error(e.getMessage());
            return "redirect:/admin/vocabularies/" + level;

        }
        return "createVocabulary";

    }

    @GetMapping("/admin/vocabularies/{level}/delete/vocabularyId/{vocabularyId}")
    public String deleteVocabulary(@PathVariable("vocabularyId") Long vocabularyId, @PathVariable("level") String level,Long id, Model model) {
        try {
                Vocabulary vocabulary = vocabularyService.findById(vocabularyId);
                vocabularyService.deleteVocabulary(vocabulary);

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return "redirect:/admin/vocabularies/" + level;
    }
}
