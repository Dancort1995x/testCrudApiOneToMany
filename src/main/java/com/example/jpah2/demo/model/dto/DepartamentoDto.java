package com.example.jpah2.demo.model.dto;

import com.example.jpah2.demo.model.data.Departamento;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class DepartamentoDto {
    private Long id;
    private String name;
    private boolean estado;
    private List<EmpleadoDto> empleadoDtoList = new ArrayList<>();

    public static DepartamentoDto toDomain(Departamento departamento){
        DepartamentoDto departamentoDto = new DepartamentoDto();
        departamentoDto.setId(departamento.getId());
        departamentoDto.setName(departamento.getName());
        departamentoDto.setEstado(departamento.isEstado());
        if(Objects.nonNull(departamento.getEmpleadoList())){
            departamentoDto.setEmpleadoDtoList(departamento.getEmpleadoList().stream().map(EmpleadoDto::toDomain).collect(Collectors.toList()));
        }
        return departamentoDto;
    }

}
