package com.locacar.locacar.repository;

import com.locacar.locacar.entity.Carro;
import com.locacar.locacar.util.CarroCreator;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Test for Carro Repository")
@Log4j2
class CarroRepositoryTest {

    @Autowired
    private CarroRepository carroRepository;

    @Test
    @DisplayName("Save persist car when sucessful")
    void save_PersistCarro_WhenSucessful() {
        Carro carroToBeSaved = CarroCreator.createCarToBeSaved();
        Carro savedCarro =this.carroRepository.save(carroToBeSaved);
        Assertions.assertThat(savedCarro).isNotNull();
        Assertions.assertThat(savedCarro.getId()).isNotNull();
        Assertions.assertThat(savedCarro.getVeiculo()).isEqualTo(carroToBeSaved.getVeiculo());
    }


    @Test
    @DisplayName("Save update car when sucessful")
    void save_UpdateCarro_WhenSucessful() {
        Carro carroToBeSaved = CarroCreator.createCarToBeSaved();
        Carro savedCarro =this.carroRepository.save(carroToBeSaved);
        savedCarro.setVeiculo("Fox");
        Carro carroUpdate = this.carroRepository.save(savedCarro);

        log.info(carroUpdate.getVeiculo());

        Assertions.assertThat(carroUpdate).isNotNull();
        Assertions.assertThat(carroUpdate.getId()).isNotNull();
        Assertions.assertThat(carroUpdate.getVeiculo()).isEqualTo(savedCarro.getVeiculo());
    }

    @Test
    @DisplayName("Save delete car when sucessful")
    void delete_RemoveCarro_WhenSucessful() {
        Carro carroToBeSaved = CarroCreator.createCarToBeSaved();
        Carro savedCarro =this.carroRepository.save(carroToBeSaved);

        this.carroRepository.delete(savedCarro);

        Optional<Carro> carroOptional = this.carroRepository.findById(savedCarro.getId());

        Assertions.assertThat(carroOptional.isEmpty());
    }

    @Test
    @DisplayName("Find by name returns List of car when sucessful")
    void findByName_ReturnListOfCar_WhenSucessful() {
        Carro carroToBeSaved = CarroCreator.createCarToBeSaved();
        Carro savedCarro =this.carroRepository.save(carroToBeSaved);

        String veiculo = savedCarro.getVeiculo();

        this.carroRepository.findByVeiculo(veiculo);

         List<Carro> carros = this.carroRepository.findByVeiculo(veiculo);

        Assertions.assertThat(carros).isNotEmpty().contains(savedCarro);
    }

    @Test
    @DisplayName("Find by name returns empty List when no car is found")
    void findByName_ReturnEmptyList_WhenCarIsNotFound() {
        List<Carro> carros = this.carroRepository.findByVeiculo("Corsa");

        Assertions.assertThat(carros).isEmpty();
    }
}