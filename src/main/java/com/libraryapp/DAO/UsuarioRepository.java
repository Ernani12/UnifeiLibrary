package com.libraryapp.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.libraryapp.entities.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
		
}
