package com.nihongo.admin.service;

import com.nihongo.admin.entity.Level;
import com.nihongo.admin.repository.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelService implements  ILevelService{
    @Autowired
    LevelRepository repo;

    @Override
    public List<Level> findAll() {
        return (List<Level>) repo.findAll();
    }
}
