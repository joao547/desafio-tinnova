package com.desafio.tinnova.service.impl;

import com.desafio.tinnova.controller.dto.VeiculoDTO;
import com.desafio.tinnova.controller.exception.MarcaException;
import com.desafio.tinnova.dao.VeiculoDAO;
import com.desafio.tinnova.model.Veiculo;
import com.desafio.tinnova.service.VeiculoService;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Service
public class VeiculoServiceImpl implements VeiculoService {

    private final VeiculoDAO veiculoDAO;

    public VeiculoServiceImpl(VeiculoDAO veiculoDAO) {
        this.veiculoDAO = veiculoDAO;
    }

    @Override
    public Veiculo create(VeiculoDTO veiculoDTO) throws IOException {
        Veiculo veiculo = createVeiculo(veiculoDTO);
        return veiculoDAO.save(veiculo);
    }

    @Override
    public Veiculo update(VeiculoDTO veiculoDTO, Long id) throws IOException {
        Veiculo veiculo = createVeiculoUpdate(veiculoDTO);
        veiculo.setId(id);
        return veiculoDAO.merge(veiculo);
    }

    @Override
    public Veiculo update(Veiculo veiculo){
        veiculo.setUpdated(new Date(System.currentTimeMillis()));
        return veiculoDAO.merge(veiculo);
    }

    @Override
    public List<Veiculo> getAll() {
        return veiculoDAO.findAll();
    }

    @Override
    public Veiculo getById(Long id) {
        return veiculoDAO.findOne(id);
    }

    @Override
    public void delete(Long id) {
        veiculoDAO.deleteById(id);
    }

    private Veiculo createVeiculo(VeiculoDTO veiculoDTO) throws IOException {
        if (!checkMarcaIsValid(veiculoDTO.getMarca())){
            throw new MarcaException("Marca não registrada na base");
        }
        return Veiculo.builder()
                .veiculo(veiculoDTO.getVeiculo())
                .ano(veiculoDTO.getAno())
                .created(new Date(System.currentTimeMillis()))
                .descricao(veiculoDTO.getDescricao())
                .marca(StringUtils.capitalize(veiculoDTO.getMarca()))
                .updated(new Date(System.currentTimeMillis()))
                .vendido(false).build();
    }

    private Veiculo createVeiculoUpdate(VeiculoDTO veiculoDTO) throws IOException {
        if (!checkMarcaIsValid(veiculoDTO.getMarca())){
            throw new MarcaException("Marca não registrada na base");
        }
        return Veiculo.builder()
                .veiculo(veiculoDTO.getVeiculo())
                .ano(veiculoDTO.getAno())
                .created(veiculoDTO.getCreated())
                .descricao(veiculoDTO.getDescricao())
                .marca(StringUtils.capitalize(veiculoDTO.getMarca()))
                .updated(new Date(System.currentTimeMillis()))
                .vendido(veiculoDTO.isVendido()).build();
    }

    private boolean checkMarcaIsValid(String marca) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get("base/marcas-carros.csv"));
        CSVReader csvReader = new CSVReaderBuilder(reader)
                .withSkipLines(1)
                .build();

        List<String[]> linhas = csvReader.readAll();
        for (String[] linha : linhas)
            for (String coluna : linha)
                if (coluna.split(";")[1].equals(marca.toUpperCase()))
                    return true;
        return false;
    }
}
