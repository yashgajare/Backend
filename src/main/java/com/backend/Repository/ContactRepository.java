package com.backend.Repository;

import com.backend.models.ContactTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<ContactTable,Integer> {
}
