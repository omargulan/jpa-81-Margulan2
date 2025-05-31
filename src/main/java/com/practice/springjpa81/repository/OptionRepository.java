package com.practice.springjpa81.repository;

import com.practice.springjpa81.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Long> {
}
