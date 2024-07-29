package uz.ksan.partners.backend.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "Person")
public class PersonEntity implements Serializable {                 //implements Serializable нужен для кэширования

    @SequenceGenerator(name="yourSequenceGenerator", allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="yourSequenceGenerator")
    Long id;

    @Column(unique = true, nullable = false)
    String fullName;

    @NonNull
    String gender;

    @NonNull
    String relationType;

    @NonNull
    String location;

    @NonNull
    String interest;
}
