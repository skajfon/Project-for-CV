package com.rzk.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.rzk.model.Pet;

public interface PetRepository  extends JpaRepositoryImplementation<Pet, Integer>{

}
