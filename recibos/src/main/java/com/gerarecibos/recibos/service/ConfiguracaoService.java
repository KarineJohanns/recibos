package com.gerarecibos.recibos.service;

import com.gerarecibos.recibos.model.Configuracao;
import com.gerarecibos.recibos.repository.ConfiguracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConfiguracaoService {

    @Autowired
    private ConfiguracaoRepository configuracaoRepository;

    public Configuracao atualizarEmitente(String nomeEmitente, String cpfEmitente) {
        Configuracao configuracao = configuracaoRepository.findById(1L)
                .orElse(new Configuracao());
        configuracao.setNomeEmitente(nomeEmitente);
        configuracao.setCpfEmitente(cpfEmitente);
        return configuracaoRepository.save(configuracao);
    }

    public Configuracao obterEmitente() {
        return configuracaoRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Configuração não encontrada"));
    }
}