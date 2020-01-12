package alextjalsma.sfgpetclinic.bootstrap;

import alextjalsma.sfgpetclinic.model.*;
import alextjalsma.sfgpetclinic.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();
        if (count == 0) {
            loadData();
        }
    }

        private void loadData () {
            PetType dog = new PetType();
            dog.setName("dog");
            PetType savedDogPetType = petTypeService.save(dog);

            PetType cat = new PetType();
            cat.setName("cat");
            PetType savedCatPetType = petTypeService.save(cat);

            Speciality radiology = new Speciality();
            radiology.setDescription("Radiology");
            Speciality savedRadiology = specialtyService.save(radiology);

            Speciality surgery = new Speciality();
            surgery.setDescription("Surgery");
            Speciality savedSurgery = specialtyService.save(surgery);

            Speciality dentistry = new Speciality();
            dentistry.setDescription("dentistry");
            Speciality savedDentistry = specialtyService.save(dentistry);
            Owner owner1 = new Owner();
            owner1.setFirstName("Michael");
            owner1.setLastName("Weston");
            owner1.setAddress("Street 1");
            owner1.setCity("Miami");
            owner1.setTelephone("123456789");

            Pet mikesPet = new Pet();
            mikesPet.setPetType(savedDogPetType);
            mikesPet.setOwner(owner1);
            mikesPet.setName("Rosco");
            mikesPet.setBirthDate(LocalDate.now());
            owner1.getPets().add(mikesPet);

            ownerService.save(owner1);

            Owner owner2 = new Owner();
            owner2.setFirstName("Fiona");
            owner2.setLastName("Glenanne");
            owner2.setAddress("Street 10");
            owner2.setCity("Miami");
            owner2.setTelephone("1234567890");

            Pet fianosPet = new Pet();
            fianosPet.setPetType(savedCatPetType);
            fianosPet.setOwner(owner2);
            fianosPet.setName("Juast cat");
            fianosPet.setBirthDate(LocalDate.now());
            owner2.getPets().add(fianosPet);

            ownerService.save(owner2);

            Visit catVisit = new Visit();
            catVisit.setPet(fianosPet);
            catVisit.setDate(LocalDate.now());
            catVisit.setDescription("Sneezy kitty");

            visitService.save(catVisit);

            System.out.println("Loaded Owners....");

            Vet vet1 = new Vet();
            vet1.setFirstName("Sam");
            vet1.setLastName("Axe");
            vet1.getSpecialities().add(savedRadiology);

            vetService.save(vet1);

            Vet vet2 = new Vet();
            vet2.setFirstName("Jessie");
            vet2.setLastName("Porter");
            vet2.getSpecialities().add(savedSurgery);

            vetService.save(vet2);

            System.out.println("Loaded Vets....");
        }
    }
