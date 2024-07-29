package uz.ksan.partners.backend.controller;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import uz.ksan.partners.backend.model.PersonEntity;
import uz.ksan.partners.backend.service.PersonService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Transactional
@RequestMapping("/api/person")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PersonController {

    private final PersonService service;

    @GetMapping("/filter")
    public List<PersonEntity> findByFilters(@RequestParam(required = false) String gender,
                                            @RequestParam(required = false) String relationType,
                                            @RequestParam(required = false) String location,
                                            @RequestParam(required = false) String interest){
        return service.findByFilters(gender, relationType, location, interest);
    }

    @GetMapping("/relationType/{relationType}")
    public List<PersonEntity> findPersonByRelationType(@PathVariable String relationType) {
        return service.findPersonByRelationType(relationType);
    }

    @GetMapping
    public List<PersonEntity> findAllPersons(){
        return service.findAllPersons();
    }

    @PostMapping("/saveAcc")
    public PersonEntity saveAcc(@RequestBody PersonEntity entity){
        return service.savePerson(entity);
    }

    @GetMapping("/{fullName}")
    public PersonEntity findPersonByFullName(@PathVariable String fullName){
        return service.findPersonByFullName(fullName);
    }

    @PutMapping("/updatePerson")
    public PersonEntity updatePerson(@RequestBody PersonEntity entity){
        return service.updatePerson(entity);
    }

    @DeleteMapping("deletePerson/{fullName}")
    public void deletePerson(@PathVariable String fullName){
        service.deletePerson(fullName);
    }
}
