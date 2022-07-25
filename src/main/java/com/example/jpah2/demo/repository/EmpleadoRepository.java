package com.example.jpah2.demo.repository;

import com.example.jpah2.demo.model.data.Empleado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends CrudRepository<Empleado,Long> {
}
