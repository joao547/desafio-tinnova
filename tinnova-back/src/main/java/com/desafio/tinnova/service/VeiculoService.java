package com.desafio.tinnova.service;

import com.desafio.tinnova.controller.dto.VeiculoDTO;
import com.desafio.tinnova.model.Veiculo;

import java.io.IOException;
import java.util.List;

public interface VeiculoService {

    Veiculo create(VeiculoDTO veiculoDTO) throws IOException;

    Veiculo update(VeiculoDTO veiculoDTO, Long id) throws IOException;

    Veiculo update(Veiculo veiculo);

    List<Veiculo> getAll();

    Veiculo getById(Long id);

    void delete(Long id);
}
