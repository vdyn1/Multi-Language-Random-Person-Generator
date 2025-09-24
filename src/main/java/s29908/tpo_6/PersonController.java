package s29908.tpo_6;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class PersonController {

    private final FakeDataService faker;

    public PersonController(FakeDataService fakeDataService) {
        this.faker = fakeDataService;
    }

    private final List<String> languages = List.of(
            "pl", "de", "en", "fr", "pt", "sk", "sv", "it", "es", "mk", "ie"
    );

    private final List<String> availableFields = List.of(
            "address", "university", "country", "bloodType",
            "phone", "company", "email", "familyStatus"
    );

    @RequestMapping("/generate")
    public String generate(
            @RequestParam(defaultValue = "1") int quantity,
            @RequestParam(defaultValue = "en") String language,
            @RequestParam(required = false) List<String> selectedFields,
            Model model) {

        if (selectedFields == null) {
            selectedFields = List.of();
        }

        selectedFields = selectedFields.stream()
                .map(String::toLowerCase)
                .toList();

        List<Person> personList = faker.generatePeople(quantity, language, selectedFields);
        PersonDTO people = new PersonDTO(personList);

        Locale locale = Locale.forLanguageTag(language);
        ResourceBundle messages = ResourceBundle.getBundle("language", locale);
        Map<String, String> forTranslations = new HashMap<>();

        List<String> allFields = new ArrayList<>(List.of("firstName", "lastName", "birthDate"));
        allFields.addAll(availableFields);

        for (String field : allFields) {
            String key = field.toLowerCase();
            String value = messages.containsKey(key) ? messages.getString(key) : field;
            forTranslations.put(key, value);
        }

        model.addAttribute("people", people);
        model.addAttribute("languages", languages);
        model.addAttribute("fields", availableFields);
        model.addAttribute("quantity", quantity);
        model.addAttribute("language", language);
        model.addAttribute("selectedFields", selectedFields);
        model.addAttribute("translations", forTranslations);

        return "form";
    }
}
