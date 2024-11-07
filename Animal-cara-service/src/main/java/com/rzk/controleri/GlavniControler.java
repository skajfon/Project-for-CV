package com.rzk.controleri;

import org.springframework.web.bind.annotation.RestController;

import com.rzk.model.NoteForCondition;
import com.rzk.model.Pet;
import com.rzk.model.SpecialConndition;
import com.rzk.model.Specy;
import com.rzk.service.GlavniService;

import jakarta.ws.rs.Path;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class GlavniControler {
@Autowired
GlavniService gs;
	

	@PostMapping("/addPet/specy={ids}")
	public boolean addPet(@RequestBody Pet p,@PathVariable int ids) {
		p.setId(0);
		p.setSpecy(gs.getSpecy(ids));
		p.setOwner(gs.getOwner(1));
		return gs.addPet(p) != null;
	}
	@PostMapping("/CreateCondition")
	public boolean addCondition(@RequestBody String name) {
		SpecialConndition s=new SpecialConndition();
		s.setName(name);
		return gs.addCondition(s) != null;
	}
	
	@PostMapping("/SetNameToPet/id={idp}")
	public boolean SetPetName(@RequestBody String name,@PathVariable int idp) {
		return gs.changePetName(idp,name);
	}
	
	@GetMapping("add-pet/{idp}/condition/{idc}")
	public boolean addConditionToPet(@PathVariable int idp,@PathVariable int idc) {
		
		return gs.addConditionToPet(idp,idc);
	}
	
	@PostMapping("/for-pet/{idp}/and-condition/{idc}/add-note")
	public boolean addNoteToConditionOfPet(@RequestBody String note,@PathVariable int idp, @PathVariable int idc) {
		return gs.addNoteTopet(note,idp,idc);
	}
	@GetMapping("add-pet/{idp}/to-owner/{ido}")
	public boolean OwnerAdopdtPet(@PathVariable int idp,@PathVariable int ido) {
		return gs.addPetToOwner(idp,ido);
	}
	
	@GetMapping("/get-spisies-for-pet/{idp}")
	public Specy getSpecyForPet(@PathVariable int idp) {
		return gs.getPet(idp).getSpecy();
	}
	
	@GetMapping("/getPetsFor/specy={ids}")
	public List<Pet> GetPestForSpecy(@PathVariable int ids) {
		return gs.getPetsForSpecy(ids);
	}
	
	@GetMapping("/getPet/{id}")
	public Pet getPet(@PathVariable int id) {
		return gs.getPet(id);
	}
	@GetMapping("/getAllpets")
	public List<Pet> getAllPet() {
		return gs.GetAllPets();
	}
	@GetMapping("/getAllNotes")
	public List<NoteForCondition> getAllNote() {
		return gs.GetAllNotes();
	}
	@GetMapping("/getAllNotesForPet/{idp}")
	public List<NoteForCondition> getAllNoteForPet(@PathVariable int idp) {
		return gs.getPet(idp).getNoteForConditions();
	}

	@GetMapping("/getAllConditionForPet/{idp}")
	public List<SpecialConndition> getAllConditionForPet(@PathVariable int idp) {
		return gs.getPet(idp).getSpecialConnditions();
	}
	
	
	
	
	
}
