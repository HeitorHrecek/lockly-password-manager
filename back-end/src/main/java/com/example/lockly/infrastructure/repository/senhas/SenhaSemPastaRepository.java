package com.example.lockly.infrastructure.repository.senhas;

import com.example.lockly.infrastructure.repository.entities.senha.SenhaSemPastaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SenhaSemPastaRepository extends JpaRepository<SenhaSemPastaEntity, Integer> {

    @Query("SELECT pswtf FROM SenhaSemPasta pswtf WHERE pswtf.usuarioEntity.id = :idUsuario")
    List<SenhaSemPastaEntity> listarPorUsuario(Integer idUsuario);

    Optional<SenhaSemPastaEntity> findByNome(String name);
}
