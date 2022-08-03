package com.nihongo.controller;

import com.nihongo.entity.Vocabulary;
import com.nihongo.exception.VocabularyNotFoundException;
import com.nihongo.service.ILevelService;
import com.nihongo.service.IVocabularyService;
import com.nihongo.service.impl.CategoryVocabularyImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class VocabularyController {
    public static final Logger logger = LogManager.getLogger(VocabularyController.class);
    @Autowired
    private ILevelService levelService;
    @Autowired
    CategoryVocabularyImpl categoryVocabularyService;

    @Autowired
    private IVocabularyService vocabularyService;

    @GetMapping("/admin/vocabularies/{level}")
    public String listCategoryVocabulary(@PathVariable("level") String level, Model model){
        model.addAttribute("level", level);
        model.addAttribute("listCateVocabularies",categoryVocabularyService.getCategoryVocabularyByLevel(level));


        return "category_vocabulary";
    }

    @GetMapping("/admin/vocabularies/{categoryVocabulary}/{level}/page/{pageNumber}")
    public String vocabularyByLevel(@PathVariable("level") String level,@PathVariable("categoryVocabulary") String categoryVocabulary, Model model,@PathVariable("pageNumber") int pageNumber){
        model.addAttribute("level", level);
        model.addAttribute("categoryVocabulary", categoryVocabulary);

        model.addAttribute("listLevel", levelService.findAll());

//        model.addAttribute("vocabularies", vocabularyService.getVocabularyByLevelAndCategoryVocabulary(level,categoryVocabulary,pageNumber));


        return "vocabulary";
    }


    @GetMapping("/admin/vocabulary/add")
    public String addVocabulary(Model model) {
        model.addAttribute("vocabulary", new Vocabulary());
        model.addAttribute("listLevel", levelService.findAll());
        model.addAttribute("listCateVocabulary",categoryVocabularyService.getAllCategoryVocabulary());

        return "createVocabulary";
    }

    @PostMapping("/admin/vocabulary/save")
    public String saveVocabulary(@ModelAttribute("vocabulary") Vocabulary vocabulary, RedirectAttributes redirectAttributes) throws VocabularyNotFoundException {

        vocabularyService.save(vocabulary);

        String level = vocabulary.getLevel();
        String categoryVocabulary = vocabulary.getCategoryVocabulary();
        redirectAttributes.addFlashAttribute("message","Save successful");
        return "redirect:/admin/vocabularies/"+ categoryVocabulary + "/" + level;

    }

    @GetMapping("/admin/vocabularies/{level}/edit/vocabularyId/{vocabularyId}")
    public String editVocabulary(@PathVariable("vocabularyId") Long vocabularyId, @PathVariable("level") String level, Model model) {
        try {
                logger.info("Find vocabulary by Id start");
                Vocabulary vocabulary = vocabularyService.findById(vocabularyId);
                model.addAttribute("vocabulary", vocabulary);
                model.addAttribute("listLevel", levelService.findAll());
                model.addAttribute("listCateVocabulary", categoryVocabularyService.getAllCategoryVocabulary());

        }catch (VocabularyNotFoundException e){
            logger.error(e.getMessage());
            return "redirect:/admin/vocabularies/" + level;

        }
        return "createVocabulary";

    }

    @GetMapping("/admin/vocabularies/{level}/delete/vocabularyId/{vocabularyId}")
    public String deleteVocabulary(@PathVariable("vocabularyId") Long vocabularyId, @PathVariable("level") String level,Long id, Model model) {
        Vocabulary vocabulary = new Vocabulary();
        try {
            vocabulary = vocabularyService.findById(vocabularyId);
                vocabularyService.deleteVocabulary(vocabulary);

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        String categoryVocabulary = vocabulary.getCategoryVocabulary();

        return "redirect:/admin/vocabularies/"+ categoryVocabulary + "/" + level;
    }
}
