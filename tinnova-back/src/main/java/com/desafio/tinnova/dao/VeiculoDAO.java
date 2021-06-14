package com.desafio.tinnova.dao;

import com.desafio.tinnova.model.Veiculo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class VeiculoDAO extends AbstractJpaDAO<Veiculo> {

    public VeiculoDAO(){
        super();
        setClazz(Veiculo.class);
    }
}
