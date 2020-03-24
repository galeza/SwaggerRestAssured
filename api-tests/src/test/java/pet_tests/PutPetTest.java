package pet_tests;

import base.BaseTest;
import fr.galeza.example.swagger.client.model.Category;
import fr.galeza.example.swagger.client.model.Pet;
import org.junit.jupiter.api.Test;
import requests.PostPetApiRequest;
import requests.PutPetApiRequest;
import util.TestUtils;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class PutPetTest extends BaseTest {

    private String category = "dog";
    private String photoUrl = "http://foo.bar.com/1";
    private String animal_name = "test";

    @Test
    public void updatePetNameAndStatus() {
        Pet petToBeAddedWithAvailableStatus = getTestPet(animal_name, Pet.StatusEnum.AVAILABLE, category, photoUrl);

        Pet createdPet = new PostPetApiRequest()
                .pet(petToBeAddedWithAvailableStatus)
                .sendRequest()
                .assertRequestSuccess()
                .getResponseModel();

        assertThat(createdPet.getId()).isEqualTo(petToBeAddedWithAvailableStatus.getId());
        createdPet.setName("Changed_name");
        createdPet.setStatus(Pet.StatusEnum.SOLD);
        Pet updatedPet = new PutPetApiRequest().pet(createdPet).sendRequest().assertRequestSuccess().getResponseModel();
        assertThat(createdPet.getName()).isEqualTo(updatedPet.getName());
        assertThat(updatedPet.getStatus()).isEqualTo(Pet.StatusEnum.SOLD);
    }


}
