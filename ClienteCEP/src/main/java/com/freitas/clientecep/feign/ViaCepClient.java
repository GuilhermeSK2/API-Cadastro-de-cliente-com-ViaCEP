package com.freitas.clientecep.feign;

import com.freitas.clientecep.models.EnderecoModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "${viacep.url}")
public interface ViaCepClient {

    @GetMapping("/{cep}/json/")
    EnderecoModel consultarCep(@PathVariable("cep") String cep);
}

