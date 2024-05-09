package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Assistant;

@Repository
public interface AssistantRepository extends JpaRepository<Assistant, Integer> {
}
