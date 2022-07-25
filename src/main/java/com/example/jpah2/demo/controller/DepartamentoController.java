package com.example.jpah2.demo.controller;

import com.example.jpah2.demo.model.data.Departamento;
import com.example.jpah2.demo.model.dto.DepartamentoDto;
import com.example.jpah2.demo.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DepartamentoController {

    private final DepartamentoService service;

    @Autowired
    public DepartamentoController(DepartamentoService service){
        this.service=service;
    }

    @PostMapping(value="/createDepartamento")
    public ResponseEntity<DepartamentoDto> addDepartamento(@RequestBody final DepartamentoDto departamentoDto){
        Departamento departamento = service.addDepartamento(Departamento.toDomain(departamentoDto));
        return new ResponseEntity<>(DepartamentoDto.toDomain(departamento), HttpStatus.OK);
    }

    @GetMapping(value="/departamentos")
    public ResponseEntity<List<DepartamentoDto>> getDepartamentos(){
        List<Departamento> departamentos = service.getDepartamentos();
        List<DepartamentoDto> departamentosDto = departamentos.stream().map(DepartamentoDto::toDomain).collect(Collectors.toList());
        return new ResponseEntity<>(departamentosDto,HttpStatus.OK);
    }

    @GetMapping(value = "/departamentos/{id}")
    public ResponseEntity<DepartamentoDto> getDepartamento(@PathVariable final Long id){
        Departamento departamento =service.getDepartamento(id);
        return new ResponseEntity<>(DepartamentoDto.toDomain(departamento), HttpStatus.OK);
    }
    @DeleteMapping(value = "/departamentos/{id}")
    public ResponseEntity<DepartamentoDto> deleteDepartamento(@PathVariable final Long id){
        Departamento departamento = service.deleteDepartamento(id);
        return new ResponseEntity<>(DepartamentoDto.toDomain(departamento),HttpStatus.OK);
    }
    @PutMapping(value = "/departamentos/{id}")
    public ResponseEntity<DepartamentoDto> updateDepartamento(@PathVariable final Long id,@RequestBody final DepartamentoDto departamentoDto){
        Departamento departamento = service.updateDepartamento(id,Departamento.toDomain(departamentoDto));
        return new ResponseEntity<>(DepartamentoDto.toDomain(departamento),HttpStatus.OK);

    }

    @PostMapping(value = "/departamentos/{departamentoId}/empleados/{empleadoId}/add")
    public ResponseEntity<DepartamentoDto> addEmpleadoToDepartamento(@PathVariable final Long departamentoId,@PathVariable final Long empleadoId ){
        Departamento departamento = service.addEmpleadoToDepartamento(departamentoId,empleadoId);
        return new ResponseEntity<>(DepartamentoDto.toDomain(departamento),HttpStatus.OK);
    }

    @DeleteMapping(value ="/departamentos/{departamentoId}/empleados/{empleadoId}/remove" )
    public ResponseEntity<DepartamentoDto> removeEmpleadoToDepartamento(@PathVariable final Long departamentoId,@PathVariable final Long empleadoId ){
        Departamento departamento = service.removeEmpleadofromDepartamento(departamentoId,empleadoId);
        return new ResponseEntity<>(DepartamentoDto.toDomain(departamento),HttpStatus.OK);
    }

}
