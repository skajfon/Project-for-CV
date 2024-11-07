package com.rzk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rzk.model.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {

}
