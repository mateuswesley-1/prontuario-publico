package com.devweb.prontuario.repositories;

import com.devweb.prontuario.BaseTestContainers;
import com.devweb.prontuario.dto.atestado.AtestadoMapper;
import com.devweb.prontuario.dto.atestado.AtestadoRequestDTO;
import com.devweb.prontuario.entities.Atestado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.assertThat;

class AtestadoRepositoryTest extends BaseTestContainers{
    private AtestadoRepository underTest;

    private AtestadoMapper mapper;
    @BeforeEach
    void setUp() {
        this.underTest = new AtestadoRepository(
                BaseTestContainers.getJbdcTemplate ()
        );
        this.mapper = new AtestadoMapper ( new ModelMapper () );
    }
    @Test
    void findAll() {
        //Given
        AtestadoRequestDTO atestadoRequestDTO = new AtestadoRequestDTO ();
        atestadoRequestDTO.setDescricao ( faker.lorem ().sentence ( 5 ) );
        atestadoRequestDTO.setDuracao ( faker.number ().numberBetween ( 1, 30 ) );
        Atestado atestado = this.mapper.requestDtoToEntity(atestadoRequestDTO, Atestado.class);
        Pageable pageable = PageRequest.of(1,1);

        this.underTest.save ( atestado );

        //When
        Page<Atestado> page = this.underTest.findAll (pageable);

        //Then
        assertThat(page.getContent ()).isNotEmpty ();
    }
    @Test
    void findById() {
    }

    @Test
    void save() {
        //Given
        //When
        //Then
    }

    @Test
    void delete() {
        //Given
        //When
        //Then
    }

    @Test
    void nomeTabela() {
        //Given
        //When
        //Then
    }

    @Test
    void classFields() {
        //Given
        //When
        //Then
    }

    @Test
    void getRowMapper() {
        //Given
        //When
        //Then
    }
}