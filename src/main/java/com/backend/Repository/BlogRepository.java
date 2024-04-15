package com.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.models.BlogTable;

public interface BlogRepository extends JpaRepository<BlogTable, Integer> {

}
