package com.aulateste.aulateste.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aulateste.aulateste.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
