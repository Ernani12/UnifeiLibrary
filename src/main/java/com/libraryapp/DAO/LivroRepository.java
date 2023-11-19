package com.libraryapp.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.libraryapp.entities.Livro;

@Repository
public interface LivroRepository extends CrudRepository<Livro, Long> {

}
