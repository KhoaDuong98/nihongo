package com.nihongo.admin.controller;


import com.nihongo.admin.repository.VocabularyRepository;
import com.nihongo.admin.service.impl.CategoryVocabularyImpl;
import com.nihongo.admin.service.impl.LevelServiceImpl;
import com.nihongo.admin.service.impl.VocabularyServiceImpl;
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
    LevelServiceImpl levelService;

    @GetMapping("/admin")
    public String index(Model model){

        model.addAttribute("listLevel", levelService.findAll());

        return "index";
    }
    @GetMapping("/test")
    public String test(Model model){


        return "category_vocabulary";
    }


}
