package s29908.tpo_6;

import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class FakeDataService {

    public List<Person> generatePeople(int quantity, String language, List<String> fields) {
        Faker faker = new Faker(new Locale(language));
        List<Person> result = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            Person person = new Person(
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.date().birthdayLocalDate()
            );

            if (fields == null) fields = List.of();

            if (fields.contains("address")) person.setAddress(faker.address().fullAddress());
            if (fields.contains("university")) person.setUniversity(faker.university().name());
            if (fields.contains("country")) person.setCountry(faker.country().name());
            if (fields.contains("bloodtype")) person.setBloodType(faker.bloodtype().bloodGroup());
            if (fields.contains("phone")) person.setPhone(faker.phoneNumber().phoneNumber());
            if (fields.contains("company")) person.setCompany(faker.company().name());
            if (fields.contains("email")) person.setEmail(faker.internet().emailAddress());
            if (fields.contains("familystatus")) person.setFamilyStatus(faker.demographic().maritalStatus());

            result.add(person);
        }

        return result;
    }
}
