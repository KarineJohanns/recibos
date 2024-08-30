package com.gerarecibos.recibos.repository;

import com.gerarecibos.recibos.model.Configuracao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfiguracaoRepository extends JpaRepository<Configuracao, Long> {
    // Métodos customizados, se necessário
}