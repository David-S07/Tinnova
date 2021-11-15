package com.locacar.locacar.util;

import com.locacar.locacar.entity.Carro;

import java.util.Date;

public class CarroCreator {

    public static Carro createCarToBeSaved() {
        return Carro.builder().veiculo("Nivus").marca("Volkswagen").fabricacao(2021).
                descricao("20 km, ar condicionado").vendido(false).created(new Date(13/11/2021)).build();
    }

    public static Carro createValidCar() {
        return Carro.builder().veiculo("Nivus").marca("Volkswagen").fabricacao(2021).id(1L).
                descricao("20 km, ar condicionado").vendido(false).created(new Date(13/11/2021)).build();
    }

    public static Carro createValidUpdatCar() {
        return Carro.builder().veiculo("Nivus 2").marca("Volkswagen").fabricacao(2021).id(1L).
                descricao("20 km, ar condicionado").vendido(false).created(new Date(13/11/2021)).build();
    }
}
