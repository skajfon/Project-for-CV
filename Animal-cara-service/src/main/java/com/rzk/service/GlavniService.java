package com.rzk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rzk.model.NoteForCondition;
import com.rzk.model.Owner;
import com.rzk.model.Pet;
import com.rzk.model.SpecialConndition;
import com.rzk.model.Specy;
import com.rzk.repository.ConditioRepository;
import com.rzk.repository.NoteForPetRepository;
import com.rzk.repository.OwnerRepository;
import com.rzk.repository.PetRepository;
import com.rzk.repository.SpecyRepository;


@Service
public class GlavniService {

@Autowired
ConditioRepository conditionR;
@Autowired
OwnerRepository ownerR;
@Autowired
PetRepository petR;
@Autowired
SpecyRepository specyR;
@Autowired
NoteForPetRepository noteR;

public Specy getSpecy(int ids) {
	return specyR.findById(ids).get();
}
public Owner getOwner(int ido) {
	return ownerR.findById(ido).get();
}
public Pet getPet(int idp) {
	return petR.findById(idp).get();
}
public SpecialConndition getCondition(int idc) {
	return conditionR.findById(idc).get();
}
public Pet addPet(Pet p) {
	return petR.save(p);
}
public SpecialConndition addCondition(SpecialConndition s) {
	return conditionR.save(s);
}
public boolean addConditionToPet(int idp, int idc) {
	Pet p=petR.findById(idp).get();
	SpecialConndition sc=conditionR.findById(idc).get();
	if (sc.getPets().contains(p)) {
		return true;
	}
	p.getSpecialConnditions().add(sc);
	sc.getPets().add(p);
	sc=conditionR.save(sc);
	return sc.getPets().contains(p);
}
public boolean addNoteTopet(String note, int idp, int idc) {
	NoteForCondition no=new NoteForCondition();
	no.setNote(note);
	no.setPet(getPet(idp));
	no.setSpecialConndition(getCondition(idc));
	if (no.getSpecialConndition().getPets().contains(no.getPet())) {
		return noteR.save(no)!=null;
	}
	return false;
}
public boolean addPetToOwner(int idp, int ido) {
	Pet p=getPet(idp);
	Owner o=getOwner(ido);
	if(p==null ||o==null)return false;
	p.setOwner(o);
	return petR.save(p).getOwner().getId()==o.getId();
}
public List<Pet> getPetsForSpecy(int ids) {
	Specy s=specyR.findById(ids).get();
	if (s==null)return null;
	return s.getPets();
}
public boolean changePetName(int idp, String name) {
	Pet p=getPet(idp);
	p.setName(name);
	petR.save(p);
	return petR.save(p).getName().equals(name) ;
}
public List<Pet> GetAllPets() {
	return petR.findAll();
}
public List<NoteForCondition> GetAllNotes() {
	return noteR.findAll();
}











}
