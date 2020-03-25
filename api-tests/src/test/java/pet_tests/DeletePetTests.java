package pet_tests;

import base.BaseTest;
import fr.galeza.example.swagger.client.model.Category;
import fr.galeza.example.swagger.client.model.Pet;
import org.junit.jupiter.api.Test;

import requests.DeletePetApiRequest;
import requests.PostPetApiRequest;
import util.TestUtils;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class DeletePetTests extends BaseTest {

    private String category = "cat";

    @Test
    public void deletePetTest() {
        Pet petToBeDeleted = getTestPet(animal_name, Pet.StatusEnum.AVAILABLE, category, photoUrl);

        Pet createdPet = new PostPetApiRequest()
                .pet(petToBeDeleted)
                .sendRequest()
                .assertRequestSuccess()
                .getResponseModel();

        assertThat(createdPet.getId()).isEqualTo(petToBeDeleted.getId());

        Pet deletedPet = new DeletePetApiRequest()
                .pet(createdPet)
                .sendRequest()
                .assertRequestSuccess()
                .getResponseModel();
        //TODO refactor!
        System.out.println("Pet was deleted: " + deletedPet.getId());
       // assertThat(deletedPet.getId()).isEqualTo(petToBeDeleted.getId());
    }
}
