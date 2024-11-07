package com.rzk.controler;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rzk.model.Owner;
import com.rzk.model.Race;
import com.rzk.model.Specy;
import com.rzk.service.GlavniService;

import jakarta.validation.Valid;

@RestController
public class GlavniControler {
	@Autowired
	GlavniService gs;



	@GetMapping("/getAllRace")
	public List<Race> getAllRace() {
		return gs.getAllRace();
	}

	@GetMapping("/getAllSpicies")
	public List<Specy> getAllSpicies() {
		return gs.getAllSpicies();
	}

	@PostMapping("/addRace")
	public boolean addRace(@Valid @RequestBody Race r) {
		r.setId(0);
		return gs.addRace(r) != null;
	}

	@PostMapping("/addSpicies/race-id={id}")
	public boolean addRace(@Valid @RequestBody Specy s, @PathVariable int id) {
		s.setId(0);
		s=gs.addSpicies(s, id);
		if(s==null) {
			return false;
		}
		if(s.getNeed_permit()) {
			gs.addPermitType(s.getId());
		}
		return true;
		
	}
	@PostMapping("/addOwner")
	public boolean addOwner(@Valid @RequestBody Owner o) {
		o.setId(0);
		return gs.addOwner(o) != null;
	}
	
	@GetMapping("/addPermit/type-id={tid}/toOwner/owner-id={oid}")
	public boolean addPermit(@PathVariable int oid,@PathVariable int tid) {
		return gs.addpermit(oid, tid) != null;
	}
	@GetMapping("/has/{ido}/permit-for/{ids}")
	public boolean addHasPermitFor(@PathVariable int ido,@PathVariable int ids) {
		return gs.hasPermitFor(ido,ids);
	}

}
