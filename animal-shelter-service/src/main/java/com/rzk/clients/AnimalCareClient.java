package com.rzk.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.rzk.modl.NoteForCondition;
import com.rzk.modl.Pet;
import com.rzk.modl.SpecialConndition;



@FeignClient(name="animal-care-service")
public interface AnimalCareClient {

	@GetMapping("/getPetsFor/specy={ids}")
	public List<Pet> GetPestForSpecy(@PathVariable int ids);
	
	@GetMapping("/getPet/{id}")
	public Pet getPet(@PathVariable int id);
	
	@GetMapping("add-pet/{idp}/to-owner/{ido}")
	public boolean OwnerAdopdtPet(@PathVariable int idp,@PathVariable int ido);
	
	@PostMapping("/SetNameToPet/id={idp}")
	public boolean SetPetName(@RequestBody String name,@PathVariable int idp);
	
	@GetMapping("/getAllNotesForPet/{idp}")
	public List<NoteForCondition> getAllNoteForPet(@PathVariable int idp);
	
	@GetMapping("/getAllConditionForPet/{idp}")
	public List<SpecialConndition> getAllConditionForPet(@PathVariable int idp);
}
