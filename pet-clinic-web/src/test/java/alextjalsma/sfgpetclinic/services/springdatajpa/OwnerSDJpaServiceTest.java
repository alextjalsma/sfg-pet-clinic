package alextjalsma.sfgpetclinic.services.springdatajpa;

import alextjalsma.sfgpetclinic.model.Owner;
import alextjalsma.sfgpetclinic.repositories.OwnerRepository;
import alextjalsma.sfgpetclinic.repositories.PetRepository;
import alextjalsma.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    private static final Long ID = 1L;
    private static final String LAST_NAME = "Smith";

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService service;

    Owner returnOwner;

    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().id(ID).lastName(LAST_NAME).build();
    }

    @Test
    void findByLastName() {
        // Given
        when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);

        // When
        Owner smith = service.findByLastName(LAST_NAME);

        // Then
        assertEquals(LAST_NAME, smith.getLastName());
        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findAll() {
        // Given
        Set<Owner> ownerSet = new HashSet<>();
        ownerSet.add(Owner.builder().id(ID).build());
        ownerSet.add(Owner.builder().id(2L).build());

        when(ownerRepository.findAll()).thenReturn(ownerSet);

        // When
        Set<Owner> owners = service.findAll();

        // Then
        assertNotNull(owners);
        assertEquals(2, owners.size());
        verify(ownerRepository).findAll();
    }

    @Test
    void findById() {
        // Given
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));

        // When
        Owner owner = service.findById(ID);

        // Then
        assertNotNull(owner);
        assertEquals(ID, owner.getId());
        verify(ownerRepository).findById(anyLong());
    }

    @Test
    void findByIdNotFound() {
        // Given
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When
        Owner owner = service.findById(ID);

        // Then
        assertNull(owner);
        verify(ownerRepository).findById(anyLong());
    }

    @Test
    void save() {
        // Given
        Owner ownerToSave = Owner.builder().id(1L).build();

        when(ownerRepository.save(any())).thenReturn(returnOwner);

        // When
        Owner savedOwner = service.save(ownerToSave);

        // Then
        assertNotNull(savedOwner);
        verify(ownerRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnOwner);

        //default is 1 times
        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(ID);

        verify(ownerRepository).deleteById(any());
    }
}