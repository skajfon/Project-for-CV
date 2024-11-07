package com.rzk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rzk.model.Permit;


public interface PermitRepository extends JpaRepository<Permit, Integer> {
	
	@Query( "select p from Permit p where p.owner.id=:ido and p.permitType.id=:idt " )
	public Permit findByOwnerAndType(int ido,int idt);

}
