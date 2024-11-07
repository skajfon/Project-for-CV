package com.rzk.service;


import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rzk.model.Owner;
import com.rzk.model.Permit;
import com.rzk.model.PermitType;
import com.rzk.model.Race;
import com.rzk.model.Specy;
import com.rzk.repository.OwnerRepository;
import com.rzk.repository.PermitRepository;
import com.rzk.repository.PermitTypeRepository;
import com.rzk.repository.RaceRepository;
import com.rzk.repository.SpecisRepository;

@Service
public class GlavniService {

	@Autowired
	SpecisRepository sRep;

	@Autowired
	RaceRepository raceR;
	
	@Autowired
	PermitTypeRepository ptypeRepo;
	
	@Autowired
	OwnerRepository ownerR;
	
	@Autowired
	PermitRepository permitR;

	public Race addRace(Race r) {

		return raceR.save(r);
	}
	public Owner addOwner(Owner o) {

		return ownerR.save(o);
	}	
	public Permit addpermit(int oid,int tid) {
		Owner o=ownerR.findById(oid).get();
		PermitType pt=ptypeRepo.findById(tid).get(); 
		if(o==null||pt==null) {
			return null;
		}
		Permit p=new Permit();
		p.setOwner(o);
		p.setPermitType(pt);
		p.setIssued(new Date());
		return permitR.save(p);
	}
	
	public PermitType addPermitType(int sid) {
		Specy s=sRep.findById(sid).get();
		if(s==null||!s.getNeed_permit()) {
			return null;
		}
		PermitType pt=new PermitType();
		pt.setSpecies(s);
		return ptypeRepo.save(pt);
	}

	public Specy addSpicies(Specy s, int idR) {
		Race r = raceR.findById(idR).get();
		if (r == null) {
			return null;
			// dodaj exception
		}
		s.setRace(r);
		return sRep.save(s);
	}

	public List<Race> getAllRace() {
		return raceR.findAll();
	}

	public List<Specy> getAllSpicies() {
		return sRep.findAll();
	}
	
	
	
	
	public boolean hasPermitFor(int ido, int ids) {
		Specy s=sRep.findById(ids).get();
		if (s==null) {
			return false;
		}
		if(!s.getNeed_permit()) {
			return true;
		}
		if(s.getPermitTypes().isEmpty()) {
			return false;
		}
		Permit p=permitR.findByOwnerAndType(ido, s.getPermitTypes().get(0).getId());
		return p!=null;
	}
	

}
