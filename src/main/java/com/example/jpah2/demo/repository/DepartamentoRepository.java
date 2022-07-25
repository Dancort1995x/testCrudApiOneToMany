package com.example.jpah2.demo.repository;

import com.example.jpah2.demo.model.data.Departamento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepository extends CrudRepository<Departamento,Long> {
}
