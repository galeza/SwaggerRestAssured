package pet_tests;
import base.BaseTest;
import fr.galeza.example.swagger.client.model.Pet;
import org.junit.jupiter.api.Test;
import requests.GetPetApiRequest;
import requests.PostPetApiRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static util.Constants.GLOBAL_MESSAGE;


public class GetPetTests extends BaseTest {

        private String category = "cat";

    @Test
    public void getPetTest() {
        Pet petToBeCreated = getTestPet(animal_name, Pet.StatusEnum.AVAILABLE, category, photoUrl);

        Pet createdPet = new PostPetApiRequest()
                .pet(petToBeCreated)
                .sendRequest()
                .assertRequestSuccess()
                .getResponseModel();

        assertThat(createdPet.getId()).isEqualTo(petToBeCreated.getId());

        Pet pet = new GetPetApiRequest()
                .pet(String.valueOf(createdPet.getId()))
                .sendRequest()
                .assertRequestSuccess()
                .getResponseModel();

        assertAll(GLOBAL_MESSAGE,
                () -> assertThat(createdPet.getId()).isEqualTo(createdPet.getId()),
                () -> assertThat(createdPet.getName()).isEqualTo(createdPet.getName()),
                () -> assertThat(createdPet.getPhotoUrls()).isEqualTo(createdPet.getPhotoUrls()),
                () -> assertThat(createdPet.getTags()).isEqualTo(createdPet.getTags()),
                () -> assertThat(createdPet.getStatus()).isEqualTo(Pet.StatusEnum.AVAILABLE),
                () -> assertThat(createdPet.getCategory().getName()).isEqualTo(category)
        );

    }
}
