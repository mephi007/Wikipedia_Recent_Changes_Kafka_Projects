package org.example.springboot.repository;

import org.example.springboot.entity.WikipediaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikipediaDataRepository extends JpaRepository<WikipediaData, Long> {
}
