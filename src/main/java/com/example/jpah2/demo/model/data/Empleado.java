package com.example.jpah2.demo.model.data;

import com.example.jpah2.demo.model.dto.EmpleadoDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name="empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private double salario;
    private String especialidad;
    private LocalDate fecha;
    @ManyToOne
    private Departamento departamento;


    public static Empleado toDomain(EmpleadoDto empleadoDto){
        Empleado empleado = new Empleado();
        empleado.setNombre(empleadoDto.getNombre());
        empleado.setSalario(empleadoDto.getSalario());
        empleado.setEspecialidad(empleadoDto.getEspecialidad());
        empleado.setFecha(empleadoDto.getFecha());
        return empleado;
    }

}
