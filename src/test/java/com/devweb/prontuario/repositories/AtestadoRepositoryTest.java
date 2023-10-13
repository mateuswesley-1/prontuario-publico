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

import java.util.Optional;

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
    void findAllShouldNotReturnEmpty() {
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
    void EntityShouldBePresentWhenIdRight() {
        // Given
        String descricao = faker.lorem ().sentence ( 5 );
        AtestadoRequestDTO atestadoRequestDTO = new AtestadoRequestDTO ();
        atestadoRequestDTO.setDescricao ( descricao );
        atestadoRequestDTO.setDuracao ( faker.number ().numberBetween ( 1, 30 ) );
        Atestado expected = this.mapper.requestDtoToEntity(atestadoRequestDTO, Atestado.class);
        this.underTest.save ( expected );
        Pageable pageable = PageRequest.of(1,10);
        String expectedId = this.underTest.findAll(pageable).getContent ()
                .stream ()
                .filter ( e -> e.getDescricao ().equals ( descricao ) )
                .map (Atestado::getId)
                .findFirst ().orElseThrow ();

        // When
        Optional<Atestado> result = this.underTest.findById ( expectedId );

        // Then
        assertThat (result).isPresent ().hasValueSatisfying ( a -> assertThat ( a.getId () ).isEqualTo ( expectedId ) );

    }

    @Test
    void EntityShouldNotBePresentWhenIdWrong() {
        // Given
        String wrongId = "id";
        // When
        Optional<Atestado> result = this.underTest.findById ( wrongId );
        // Then
        assertThat ( result ).isNotPresent ();
    }

    @Test
    void EntityShouldNotBePresentWhenDeleted() {
// Given
        String descricao = faker.lorem ().sentence ( 5 );
        AtestadoRequestDTO atestadoRequestDTO = new AtestadoRequestDTO ();
        atestadoRequestDTO.setDescricao ( descricao );
        atestadoRequestDTO.setDuracao ( faker.number ().numberBetween ( 1, 30 ) );
        Atestado expected = this.mapper.requestDtoToEntity(atestadoRequestDTO, Atestado.class);
        this.underTest.save ( expected );
        Pageable pageable = PageRequest.of(1,10);
        String expectedId = this.underTest.findAll(pageable).getContent ()
                .stream ()
                .filter ( e -> e.getDescricao ().equals ( descricao ) )
                .map (Atestado::getId)
                .findFirst ().orElseThrow ();

        // When
        this.underTest.delete ( expectedId );
        Optional<Atestado> result = this.underTest.findById ( expectedId );

        // Then
        assertThat (result).isNotPresent ();
    }




}