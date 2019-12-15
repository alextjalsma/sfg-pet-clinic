package alextjalsma.sfgpetclinic.services;

import alextjalsma.sfgpetclinic.model.Owner;
import alextjalsma.sfgpetclinic.model.Vet;

import java.util.Set;

public interface VetService {

    Vet findById(Long id);

    Vet save(Vet vet);

    Set<Vet> findAll();
}
