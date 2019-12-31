package alextjalsma.sfgpetclinic.repositories;

import alextjalsma.sfgpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
