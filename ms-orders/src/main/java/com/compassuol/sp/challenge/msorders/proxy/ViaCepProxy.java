package com.compassuol.sp.challenge.msorders.proxy;

import com.compassuol.sp.challenge.msorders.dto.ViaCepAddressDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "feign-cep", url = "http://viacep.com.br/ws")
public interface ViaCepProxy {
    @GetMapping("/{postalCode}/json/")
    public ViaCepAddressDTO getViaCepAddress(@PathVariable("postalCode") String postalCode);
}
