package pet_tests;

import base.BaseTest;
import fr.galeza.example.swagger.client.model.Category;
import fr.galeza.example.swagger.client.model.Pet;
import fr.galeza.example.swagger.client.model.Pet.StatusEnum;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static util.Constants.GLOBAL_MESSAGE;

import org.junit.jupiter.api.Test;
import requests.PostPetApiRequest;
import util.TestUtils;

import java.util.Arrays;

public class PostPetTest extends BaseTest {

    private String category = "cat";

    @Test
    public void postPetTest() {
        Pet petToBeAdded = getTestPet(animal_name, Pet.StatusEnum.AVAILABLE, category, photoUrl);

        Pet createdPet = new PostPetApiRequest()
                .pet(petToBeAdded)
                .sendRequest()
                .assertRequestSuccess()
                .getResponseModel();
        assertAll(GLOBAL_MESSAGE,
                () -> assertThat(createdPet.getId()).isEqualTo(petToBeAdded.getId()),
                () -> assertThat(createdPet.getStatus()).isEqualTo(Pet.StatusEnum.AVAILABLE),
                () -> assertThat(createdPet.getCategory().getName()).isEqualTo(category)
        );

    }


}
