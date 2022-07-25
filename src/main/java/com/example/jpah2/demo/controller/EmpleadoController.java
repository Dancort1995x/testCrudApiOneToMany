package com.example.jpah2.demo.controller;

import com.example.jpah2.demo.model.data.Empleado;
import com.example.jpah2.demo.model.dto.EmpleadoDto;
import com.example.jpah2.demo.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EmpleadoController {

    private final EmpleadoService service;

    @Autowired
    public EmpleadoController(EmpleadoService service){
        this.service=service;
    }

    @PostMapping(value = "/crearEmpleado")
    public ResponseEntity<EmpleadoDto> addEmpleado(@RequestBody final EmpleadoDto empleadoDto){
        Empleado empleado =service.addEmpleado(Empleado.toDomain(empleadoDto));
        return new ResponseEntity<>(EmpleadoDto.toDomain(empleado), HttpStatus.OK);
    }

    @GetMapping(value = "/empleados")
    public ResponseEntity<List<EmpleadoDto>> getEmpleados(){
        List<Empleado> empleados =service.getEmpleados();
        List<EmpleadoDto> empleadoDtoList = empleados.stream().map(EmpleadoDto::toDomain).collect(Collectors.toList());
        return new ResponseEntity<>(empleadoDtoList, HttpStatus.OK);
    }

    @GetMapping(value = "/empleados/{id}")
    public ResponseEntity<EmpleadoDto> getEmpleado(@PathVariable final Long id){
        Empleado empleado =service.getEmpleado(id);
        return new ResponseEntity<>(EmpleadoDto.toDomain(empleado),HttpStatus.OK);
    }

    @DeleteMapping(value = "/empleados/{id}")
    public ResponseEntity<EmpleadoDto> deleteEmpleado(@PathVariable final Long id){
        Empleado empleado =service.deleteEmpleado(id);
        return new ResponseEntity<>(EmpleadoDto.toDomain(empleado),HttpStatus.OK);
    }

    @PutMapping(value = "/empleados/{id}")
    public ResponseEntity<EmpleadoDto> updateEmpleado(@PathVariable final Long id,
                                                      @RequestBody final EmpleadoDto empleadoDto){
        Empleado empleado = service.updateEmpleado(id,Empleado.toDomain(empleadoDto));
        return new ResponseEntity<>(EmpleadoDto.toDomain(empleado),HttpStatus.OK);
    }
}
