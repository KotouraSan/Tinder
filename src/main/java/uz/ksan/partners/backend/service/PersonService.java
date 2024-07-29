package uz.ksan.partners.backend.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.ksan.partners.backend.model.PersonEntity;

import java.util.List;

public interface PersonService {
    List<PersonEntity> findAllPersons();

    PersonEntity savePerson(PersonEntity person);
    PersonEntity updatePerson(PersonEntity person);
    PersonEntity findPersonByFullName(String fullName);
    void deletePerson(String fullName);
    List<PersonEntity> findPersonByRelationType(String relationType);

    @Query("SELECT p FROM PersonEntity p WHERE " +
            "(:gender IS NULL OR p.gender = :gender) AND " +
            "(:relationType IS NULL OR p.relationType = :relationType) AND " +
            "(:location IS NULL OR p.location = :location) AND " +
            "(:interest IS NULL OR p.interest = :interest) " )
    List<PersonEntity> findByFilters(@Param("gender") String gender,
                                     @Param("relationType") String relationType,
                                     @Param("location") String location,
                                     @Param("interest") String interest);


}
