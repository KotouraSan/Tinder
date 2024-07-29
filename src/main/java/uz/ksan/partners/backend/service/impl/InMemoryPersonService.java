package uz.ksan.partners.backend.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uz.ksan.partners.backend.model.PersonEntity;
import uz.ksan.partners.backend.repository.PersonRepository;
import uz.ksan.partners.backend.service.PersonService;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class InMemoryPersonService implements PersonService {

    private final PersonRepository repository;

    @Override
    public List<PersonEntity> findByFilters(String gender, String relationType, String location, String interest) {
        return List.of();
    }

    @Override
    public List<PersonEntity> findPersonByRelationType(String relationType) {
        return List.of();
    }

    @Override
    public List<PersonEntity> findAllPersons() {
        return repository.findAll();
    }

    @Override
    public PersonEntity savePerson(PersonEntity person) {
        return repository.save(person);
    }

    @Override
    public PersonEntity updatePerson(PersonEntity person) {
        return repository.save(person);
    }

    @Override
    public PersonEntity findPersonByFullName(String fullName) {
        return repository.findByFullName(fullName);
    }

    @Override
    public void deletePerson(String fullName) {
        repository.deleteByFullName(fullName);
    }
}
