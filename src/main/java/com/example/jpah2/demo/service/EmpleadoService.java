package com.example.jpah2.demo.service;

import com.example.jpah2.demo.Exception.ElementNotFoundException;
import com.example.jpah2.demo.model.data.Empleado;
import com.example.jpah2.demo.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EmpleadoService {

    private final EmpleadoRepository repository;

    @Autowired
    public EmpleadoService(EmpleadoRepository repository){
        this.repository = repository;
    }
    public Empleado addEmpleado(Empleado empleado){
        return  repository.save(empleado);
    }

    public List<Empleado> getEmpleados(){
        return StreamSupport
                .stream(repository.findAll().spliterator(),false)
                .collect(Collectors.toList());
    }

    public Empleado getEmpleado(Long id){
        return repository.findById(id).orElseThrow(()-> new ElementNotFoundException(id));
    }
    public Empleado deleteEmpleado(Long id){
        Empleado empleado = getEmpleado(id);
        repository.delete(empleado);
        return empleado;
    }

    @Transactional
    public Empleado updateEmpleado(Long id,Empleado empleado){
        Empleado empleadoUpdate = getEmpleado(id);
        empleadoUpdate.setNombre(empleado.getNombre());
        empleadoUpdate.setEspecialidad(empleado.getEspecialidad());
        empleadoUpdate.setSalario(empleado.getSalario());
        return empleadoUpdate;
    }

}
