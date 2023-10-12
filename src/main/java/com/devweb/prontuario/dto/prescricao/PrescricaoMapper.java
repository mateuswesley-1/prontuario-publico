package com.devweb.prontuario.dto.prescricao;

import org.modelmapper.ModelMapper;

import com.devweb.prontuario.dto.BaseMapper;
import com.devweb.prontuario.entities.Precricao;
import com.devweb.prontuario.services.MedicamentoService;

public class PrescricaoMapper extends BaseMapper<Precricao, PrescricaoRequestDTO, PrescricaoResponseDTO>{

    Precricao prescricao;
    MedicamentoService medicamentoService;
    
    public PrescricaoMapper(ModelMapper mapper) {
        super(mapper);
    }

    
    public Precricao requestDtoToEntity(PrescricaoRequestDTO dto){
        Precricao prescricao = this.mapper.map(dto, this.prescricao.getClass());
        prescricao.setMedicamento(medicamentoService.getById(dto.getIdMedicamento()));
        return prescricao;
    }

}
    

