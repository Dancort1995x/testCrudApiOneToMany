package com.example.jpah2.demo.model.data;

import com.example.jpah2.demo.model.dto.DepartamentoDto;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name="departamento")
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean estado;
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "empleado_id")
    @ToString.Exclude
    private List<Empleado> empleadoList;

    public void addEmpleado(Empleado empleado){
        empleadoList.add(empleado);
    }
    public void removeEmpleado(Empleado empleado){
        empleadoList.remove(empleado);
    }

    public static Departamento toDomain(DepartamentoDto departamentoDto){
        Departamento departamento = new Departamento();
        departamento.setName(departamentoDto.getName());
        departamento.setEstado(departamentoDto.isEstado());
        return departamento;
    }
}
