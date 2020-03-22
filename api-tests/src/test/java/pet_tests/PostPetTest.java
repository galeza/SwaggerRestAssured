package pet_tests;

import fr.galeza.example.swagger.client.model.Category;
import fr.galeza.example.swagger.client.model.Pet;
import fr.galeza.example.swagger.client.model.Pet.StatusEnum;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import requests.PostPetApiRequest;
import util.TestUtils;

import java.util.Arrays;

public class PostPetTest {

    @Test
    public void postPetTest() {
        Pet petToBeAdded = getTestPet();

        Pet createdPet = new PostPetApiRequest()
                .pet(petToBeAdded)
                .sendRequest()
                .assertRequestSuccess()
                .getResponseModel();

        assertThat(createdPet.getId()).isEqualTo(petToBeAdded.getId());
    }

    private Pet getTestPet() {
        return new Pet().id(TestUtils.nextId()).name("alex").status(StatusEnum.AVAILABLE)
                .category(new Category().id(TestUtils.nextId()).name("dog"))
                .photoUrls(Arrays.asList("http://foo.bar.com/1"));
    }
}
