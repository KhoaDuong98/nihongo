package com.nihongo.service;

import com.nihongo.entity.Vocabulary;
import com.nihongo.helper.ExcelHelper;
import com.nihongo.repository.VocabularyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {
    @Autowired
    VocabularyRepository repository;
    public void save(MultipartFile file) {
        try {
            List<Vocabulary> vocabularies = ExcelHelper.excelToVocabularies(file.getInputStream());
            repository.saveAll(vocabularies);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
    public List<Vocabulary> getAllVocabularis() {
        return (List<Vocabulary>) repository.findAll();
    }
}
