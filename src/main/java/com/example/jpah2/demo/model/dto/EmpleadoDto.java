package com.example.jpah2.demo.model.dto;

import com.example.jpah2.demo.model.data.Empleado;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

@Data
public class EmpleadoDto {
    private Long id;
    private String nombre;
    private double salario;
    private String especialidad;
    private LocalDate fecha;
    private PlainDepartamentoDto plainDepartamentoDto;

    public static EmpleadoDto toDomain(Empleado empleado){
        EmpleadoDto empleadoDto = new EmpleadoDto();
        empleadoDto.setId(empleado.getId());
        empleadoDto.setNombre(empleado.getNombre());
        empleadoDto.setSalario(empleado.getSalario());
        empleadoDto.setEspecialidad(empleado.getEspecialidad());
        empleadoDto.setFecha(empleado.getFecha());
        if(Objects.nonNull(empleado.getDepartamento())){
            empleadoDto.setPlainDepartamentoDto(PlainDepartamentoDto.toDomain(empleado.getDepartamento()));
        }
        return empleadoDto;
    }

}
