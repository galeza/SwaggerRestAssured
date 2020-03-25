package base;

import fr.galeza.example.swagger.client.model.Category;
import fr.galeza.example.swagger.client.model.Pet;
import util.TestUtils;

import java.util.Arrays;

public class BaseTest {

    protected String photoUrl = "http://foo.bar.com/1";
    protected String animal_name = "test";

    protected Pet getTestPet(String animal_name, Pet.StatusEnum status, String petCategory, String photoUrl) {
        return new Pet().id(TestUtils.nextId()).name(animal_name).status(status)
                .category(new Category().id(TestUtils.nextId()).name(petCategory))
                .photoUrls(Arrays.asList(photoUrl));
    }
}
