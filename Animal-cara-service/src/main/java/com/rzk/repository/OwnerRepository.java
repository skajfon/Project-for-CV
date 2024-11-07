package com.rzk.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.rzk.model.Owner;

public interface OwnerRepository extends JpaRepositoryImplementation<Owner, Integer> {

}
