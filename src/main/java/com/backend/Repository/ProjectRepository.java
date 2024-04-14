package com.backend.Repository;

import com.backend.Models.ProjectTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<ProjectTable,Integer> {
}
