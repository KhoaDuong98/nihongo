package com.nihongo.admin.service.impl;

import com.nihongo.admin.entity.Level;
import com.nihongo.admin.repository.LevelRepository;
import com.nihongo.admin.service.ILevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelServiceImpl implements ILevelService {
    @Autowired
    LevelRepository repo;

    @Override
    public List<Level> findAll() {
        return (List<Level>) repo.findAll();
    }
}
