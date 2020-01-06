package alextjalsma.sfgpetclinic.services;

import alextjalsma.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);
}
