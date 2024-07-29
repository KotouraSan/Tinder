
package uz.ksan.partners.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import uz.ksan.partners.backend.model.PersonEntity;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/feed")
@RequiredArgsConstructor
public class FeedController {

    private final RestTemplate restTemplate;

    @GetMapping("/short-relations")
    public List<PersonEntity> consumeShortRelations() {
        String url = "http://localhost:8080/api/person/relationType/Short";
        PersonEntity[] response = restTemplate.getForObject(url, PersonEntity[].class);
        return Arrays.asList(response);
    }

    @GetMapping("/filter")
    public List<PersonEntity> consumeFilteredRelations(             //to get optional filtered data from PersonEntity List
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String relationType,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String interest){

        StringBuilder url = new StringBuilder("http://localhost:8080/api/person/filter?");
        if (gender != null) url.append("gender=").append(gender).append("&");
        if (relationType != null) url.append("relationType=").append(relationType).append("&");
        if (location != null) url.append("location=").append(location).append("&");
        if (interest != null) url.append("interest=").append(interest).append("&");
        // Remove trailing '&' if present
        if (url.charAt(url.length() - 1) == '&') {
            url.setLength(url.length() - 1);
        }

        PersonEntity[] response = restTemplate.getForObject(url.toString(), PersonEntity[].class);
        return Arrays.asList(response);
    }

}

