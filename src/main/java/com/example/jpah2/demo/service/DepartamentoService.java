package com.example.jpah2.demo.service;

import com.example.jpah2.demo.Exception.ElementNotFoundException;
import com.example.jpah2.demo.Exception.EmpleadoIsAlreadyAssignedException;
import com.example.jpah2.demo.model.data.Departamento;
import com.example.jpah2.demo.model.data.Empleado;
import com.example.jpah2.demo.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DepartamentoService {

    private final DepartamentoRepository repository;
    private final EmpleadoService empleadoService;

    @Autowired
    public DepartamentoService(DepartamentoRepository repository,EmpleadoService empleadoService){
        this.repository = repository;
        this.empleadoService = empleadoService;
    }

    public Departamento addDepartamento(Departamento departamento){
        return repository.save(departamento);
    }

    public List<Departamento> getDepartamentos(){
        return StreamSupport
                .stream(repository.findAll().spliterator(),false)
                .collect(Collectors.toList());
    }

    public Departamento getDepartamento(Long id){
        return repository.findById(id).orElseThrow(()->
                new ElementNotFoundException(id));
    }

    public Departamento deleteDepartamento(Long id){
        Departamento departamento = getDepartamento(id);
        repository.delete(departamento);
        return departamento;
    }

    @Transactional
    public Departamento updateDepartamento(Long id, Departamento departamento){
        Departamento departamentoUpdate = getDepartamento(id);
        departamentoUpdate.setName(departamento.getName());
        departamentoUpdate.setEstado(departamento.isEstado());
        return departamentoUpdate;
    }

    @Transactional
    public Departamento addEmpleadoToDepartamento(Long departamentoId,Long empleadoId){
        Departamento departamento = getDepartamento(departamentoId);
        Empleado empleado = empleadoService.getEmpleado(empleadoId);
        if(Objects.nonNull(empleado.getDepartamento())){
            throw new EmpleadoIsAlreadyAssignedException(empleadoId,empleado.getDepartamento().getId());
        }
        departamento.addEmpleado(empleado);
        empleado.setDepartamento(departamento);
        return departamento;
    }

    @Transactional
    public Departamento removeEmpleadofromDepartamento(Long departamentoId,Long empleadoId){
        Departamento departamento = getDepartamento(departamentoId);
        Empleado empleado = empleadoService.getEmpleado(empleadoId);
        departamento.removeEmpleado(empleado);
        return departamento;
    }


}
