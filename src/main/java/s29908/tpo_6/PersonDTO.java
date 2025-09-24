package s29908.tpo_6;

import java.util.List;

public class PersonDTO {
    private List<Person> people;

    public PersonDTO(List<Person> people) {
        this.people = people;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }
}
