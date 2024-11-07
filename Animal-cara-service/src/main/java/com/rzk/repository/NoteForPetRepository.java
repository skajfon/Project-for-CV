package com.rzk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rzk.model.NoteForCondition;

public interface NoteForPetRepository extends JpaRepository<NoteForCondition, Integer> {

}
