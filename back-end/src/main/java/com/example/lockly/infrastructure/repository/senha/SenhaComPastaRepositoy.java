package com.example.lockly.infrastructure.repository.senha;

import com.example.lockly.infrastructure.repository.entities.senha.SenhaComPastaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SenhaComPastaRepositoy extends JpaRepository<SenhaComPastaEntity, Integer> {
    @Query("SELECT pswwf FROM SenhaComPasta pswwf WHERE pswwf.nome = :nome")
    Optional<SenhaComPastaEntity> consultarPorNome(@Param("nome") String nome);

    @Query("SELECT pswwf FROM SenhaComPasta pswwf WHERE pswwf.usuarioEntity.id = :idUsuario")
    List<SenhaComPastaEntity> consultarPorUsuario(@Param("idUsuario") Integer idUsuario);

    @Query("SELECT pswwf FROM SenhaComPasta pswwf WHERE pswwf.pastaEntity.id = :idPasta")
    List<SenhaComPastaEntity> consultarPorPasta(@Param("idPasta") Integer idPasta);

}

