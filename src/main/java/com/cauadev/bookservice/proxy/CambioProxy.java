package com.cauadev.bookservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cauadev.bookservice.response.Cambio;


@FeignClient(name = "cambio-service", url = "localhost:8086")
public interface CambioProxy {
	
	@GetMapping("/cambio/{amount}/{from}/{to}")
	public Cambio getCambio(
			@PathVariable Double amount,
			@PathVariable String from,
			@PathVariable String to);

}
