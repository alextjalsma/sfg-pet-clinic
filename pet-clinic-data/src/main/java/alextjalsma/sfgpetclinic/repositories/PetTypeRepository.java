package alextjalsma.sfgpetclinic.repositories;

import alextjalsma.sfgpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
