package com.nihongo.admin.controller;


import com.nihongo.admin.repository.VocabularyRepository;
import com.nihongo.admin.service.LevelService;
import com.nihongo.admin.service.VocabularyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class HomeController {
    public static final Logger logger = LogManager.getLogger(VocabularyController.class);

    @Autowired
    LevelService levelService;
    @Autowired
    VocabularyService vocabularyService;

    @Autowired
    VocabularyRepository vocabularyRepository;
    @GetMapping("/admin")
    public String index(Model model){

        model.addAttribute("listLevel", levelService.findAll());

        return "index";
    }
    @GetMapping("/test")
    public String test(Model model){


        return "test";
    }
    @GetMapping("/admin/vocabularies/{level}")
    public String vocabularyByLevel(@PathVariable("level") String level, Model model){
        model.addAttribute("level", level);

        model.addAttribute("listLevel", levelService.findAll());

        model.addAttribute("vocabularies", vocabularyService.getVocabularyByLevel(level));


        return "vocabulary";
    }

}
