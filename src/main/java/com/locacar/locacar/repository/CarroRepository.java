package com.locacar.locacar.repository;

import com.locacar.locacar.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {

    @Query(value = "select * from Carro v where v.veiculo like %?1%", nativeQuery = true)
    List<Carro> findByVeiculo(String nome);

    @Query(value = "select * from Carro a where a.fabricacao like %?1%", nativeQuery = true)
    List<Carro> findByfabricacao(Integer ano);

    @Query(value = "select * from Carro m where m.marca like %?1%", nativeQuery = true)
    List<Carro> findBymarca(String marca);

    @Query(value = "select * from Carro d order by d.created asc ", nativeQuery = true)
    List<Carro> findBydataasc(Date date);

    @Query(value = "select * from Carro d order by d.created desc ", nativeQuery = true)
    List<Carro> findBydatadesc(Date date);

    @Query(value = "select * from Carro v where v.vendido = ?1", nativeQuery = true)
    List<Carro> findByvendido(Boolean vendido);
}
