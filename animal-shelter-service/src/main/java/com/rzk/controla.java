package com.rzk;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rzk.clients.AnimalCareClient;
import com.rzk.clients.Permit;
import com.rzk.modl.Pet;
import com.rzk.modl.Specy;

import jakarta.ws.rs.Path;

@RestController
public class controla {
	@Autowired
	Permit p;
	
	@Autowired
	AnimalCareClient ac;
	
	@GetMapping("/owner/{ido}/addopts/{idp}")
	public boolean hasPermitfor(@PathVariable int ido,@PathVariable int idp) {
		
		
		return false;
	}
	@GetMapping("/getAllSpecy")
	public List<Specy> hasPermitfor() {
		return p.getAllSpicies();
	}
	@GetMapping("/getAllPetsForSpecy/id={ids}")
	public List<Pet> getPetsForSpecy(@PathVariable int ids) {
		return ac.GetPestForSpecy(ids);
	}
	
	@PostMapping("/owner/{ido}/addopt/{idp}")
	public String postMethodName(@RequestBody String name,@PathVariable int ido,@PathVariable int idp) {
		if (ido==1)return "id koji ste uneli ne pripada vlasniku vec azilu molimo unesite odgovarajuci.";
		Pet pet=ac.getPet(idp);
		if(pet==null)return "pet id ne postoji.";
		if(p.addHasPermitFor(ido,pet.getSpecy().getId() )) {
			if(ac.OwnerAdopdtPet(pet.getId(), ido)) {
				ac.SetPetName(name, pet.getId());
				return "uspesno je usvojen ljubimac :"+name;
			}else {return "doslo je do greske probajte ponovo";}
		}else {return "vlasnik nema potrebene dozvole kako bi usvoijo ljubimca.";}
	}
	
	@GetMapping("GetPetMedicalHistory/{idp}")
	public Object[] getMethodName(@PathVariable int idp) {
		Object[]l=new Object[3];
		l[0]=ac.getPet(idp);
		l[1]=ac.getAllConditionForPet(idp);
		l[1]=ac.getAllNoteForPet(idp);
		return l;
	}
	
	
	
	
}
