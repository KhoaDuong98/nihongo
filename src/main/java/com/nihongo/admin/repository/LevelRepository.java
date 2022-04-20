package com.nihongo.admin.repository;

import com.nihongo.admin.entity.Level;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends CrudRepository<Level,Long> {
}
