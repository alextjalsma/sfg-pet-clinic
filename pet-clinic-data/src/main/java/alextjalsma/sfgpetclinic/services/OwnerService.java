package alextjalsma.sfgpetclinic.services;

import alextjalsma.sfgpetclinic.model.Owner;

import java.util.Set;

public interface OwnerService {

    Owner findByLAstName(String lastName);

    Owner findById(Long id);

    Owner save(Owner owner);

    Set<Owner> findAll();
}
