package com.backend.Repository;

import com.backend.models.ReviewTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewTable,Integer> {
}
