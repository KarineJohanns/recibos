package com.gerarecibos.recibos.controller;

import com.gerarecibos.recibos.DTO.ConfiguracaoDto;
import com.gerarecibos.recibos.model.Configuracao;
import com.gerarecibos.recibos.service.ConfiguracaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/configuracao")
public class ConfiguracaoController {

    @Autowired
    private ConfiguracaoService configuracaoService;

    @GetMapping("/emitente")
    public ResponseEntity<Configuracao> obterEmitente() {
        Configuracao emitente = configuracaoService.obterEmitente();
        return ResponseEntity.ok(emitente);
    }

    @PostMapping("/emitente")
    public ResponseEntity<Configuracao> atualizarEmitente(@RequestBody ConfiguracaoDto configuracaoDto) {
        Configuracao configuracao = configuracaoService.atualizarEmitente(configuracaoDto.getNomeEmitente(), configuracaoDto.getCpfEmitente());
        return ResponseEntity.ok(configuracao);
    }
}