package com.example.jpah2.demo.model.dto;

import com.example.jpah2.demo.model.data.Departamento;
import lombok.Data;

@Data
public class PlainDepartamentoDto {
    private Long id;
    private String name;
    private boolean estado;

    public static PlainDepartamentoDto toDomain(Departamento departamento){
        PlainDepartamentoDto plainDepartamentoDto = new PlainDepartamentoDto();
        plainDepartamentoDto.setId(departamento.getId());
        plainDepartamentoDto.setName(departamento.getName());
        plainDepartamentoDto.setEstado(departamento.isEstado());
        return plainDepartamentoDto;
    }
}
