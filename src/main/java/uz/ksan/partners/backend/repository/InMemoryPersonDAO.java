package uz.ksan.partners.backend.repository;

import uz.ksan.partners.backend.model.PersonEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class InMemoryPersonDAO {
    private final List<PersonEntity> persons = new ArrayList<>();


    public List<PersonEntity> findPersonByRelationType(String relationType) {
        return persons;
    }

    public List<PersonEntity> findAllPersons() {
        return persons;
    }

    public PersonEntity savePerson(PersonEntity person) {
        persons.add(person);
        return person;
    }

    public PersonEntity findPersonByFullName(String fullName) {
        return persons.stream()
                .filter(element -> element
                        .getFullName()
                        .equals(fullName))
                .findFirst()
                .orElse(null);

    }

    public PersonEntity updatePerson(PersonEntity person) {
        var personindex = IntStream.range(0, persons.size())
                .filter(index -> persons.get(index).getFullName().equals(person.getFullName()))
                .findFirst()
                .orElse(-1);
        if (personindex > -1){
            persons.set(personindex, person);
            return person;
        }
        return null;
    }

    public void deletePerson(String fullName) {
        var person = findPersonByFullName(fullName);
        if (person != null) {
            persons.remove(person);
        }
    }
}
