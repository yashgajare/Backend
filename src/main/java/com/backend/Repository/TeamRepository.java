package com.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.models.TeamTable;

public interface TeamRepository extends JpaRepository<TeamTable, Integer> {

}
