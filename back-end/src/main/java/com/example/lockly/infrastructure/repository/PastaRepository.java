package com.example.lockly.infrastructure.repository;

import com.example.lockly.infrastructure.repository.entities.PastaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PastaRepository extends JpaRepository<PastaEntity, Integer> {
    @Query("SELECT fld FROM Pasta fld WHERE fld.usuarioEntity.id = :usuarioId")
    List<PastaEntity> listarPorUsuario(Integer usuarioId);

    Optional<PastaEntity> findByNome(String name);
}
