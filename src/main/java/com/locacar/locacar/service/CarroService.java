package com.locacar.locacar.service;

import com.locacar.locacar.repository.CarroRepository;
import com.locacar.locacar.entity.Carro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CarroService {

    @Autowired
    private CarroRepository repository;

    @Autowired
    public void setRepository(CarroRepository repository) {
        this.repository = repository;
    }

    public List<Carro> findAll(){
        return  repository.findAll();
    }

    public void save(Carro c){
        repository.save(c);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public Carro getById(Long id){
        return  repository.getOne(id);
    }

    public List<Carro> findByVeiculo (String nome){
        return repository.findByVeiculo(nome);
    }

    public List<Carro> findByDecada (Integer ano) {
        return repository.findByfabricacao(ano);
    }

    public List<Carro> findByMarca (String marca) {
        return repository.findBymarca(marca);
    }

    public List<Carro> findByVendido (Boolean vendido) {
        return repository.findByvendido(vendido);
    }

    public List<Carro> findBydataasc (Date date) {
        return repository.findBydataasc(date);
    }


    public List<Carro> findBydatadesc (Date date) {
        return repository.findBydatadesc(date);
    }
}
