package uz.ksan.partners.backend.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import uz.ksan.partners.backend.model.PersonEntity;
import uz.ksan.partners.backend.repository.PersonRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Primary
@Transactional
public class PersonServiceImpl implements uz.ksan.partners.backend.service.PersonService{
    private PersonRepository repository;

    @Override
    public List<PersonEntity> findByFilters(String gender, String relationType, String location, String interest) {
        return repository.findByFilters(gender, relationType, location, interest);
    }

    @Override
    @Cacheable(value = "PersonService::findByRelationType", key = "#relationType")
    public List<PersonEntity> findPersonByRelationType(String relationType) {
        return repository.findPersonByRelationType(relationType);
    }

    @Override
    @Cacheable("persons")               //добавляет данные в кэщ
    @CachePut("persons")                //обновлянт кэш
    public List<PersonEntity> findAllPersons() {
        return repository.findAll();
    }

    @Override
    @Cacheable(value = "saveperson", key = "#person")       //добавляет данные в кэщ
    @CachePut                                               //обновлянт кэш
    public PersonEntity savePerson(PersonEntity person) {
        return repository.save(person);
    }

    @Override
    @Caching(put = {
            @CachePut(value = "PersonService::findByRelationType", key = "#person.relationType")        //обновляет кэш
    })
    public PersonEntity updatePerson(PersonEntity person) {
        return repository.save(person);
    }

    @Override
    public PersonEntity findPersonByFullName(String fullName) {
        return repository.findByFullName(fullName);
    }

    @Override
    @CacheEvict("persons")                  //удаляет из кэща
    public void deletePerson(String fullName) {
        repository.deleteByFullName(fullName);
    }
}
