package com.rzk.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rzk.modl.Specy;



@FeignClient(name="permit-service")
public interface Permit {
	@GetMapping("/getAllSpicies")
	public List<Specy> getAllSpicies();
	
	@GetMapping("/has/{ido}/permit-for/{ids}")
	public boolean addHasPermitFor(@PathVariable int ido,@PathVariable int ids);
	
}
