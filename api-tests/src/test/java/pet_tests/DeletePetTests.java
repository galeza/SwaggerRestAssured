package pet_tests;

import fr.galeza.example.swagger.client.model.Category;
import fr.galeza.example.swagger.client.model.Pet;
import org.junit.jupiter.api.Test;

import requests.DeletePetApiRequest;
import requests.PostPetApiRequest;
import util.TestUtils;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class DeletePetTests {
    @Test
    public void deletePetTest() {
        Pet petToBeDeleted = getTestPet();

        Pet createdPet = new PostPetApiRequest()
                .pet(petToBeDeleted)
                .sendRequest()
                .assertRequestSuccess()
                .getResponseModel();
        System.out.println(petToBeDeleted.getId());
        assertThat(createdPet.getId()).isEqualTo(petToBeDeleted.getId());

        Pet deletedPet = new DeletePetApiRequest()
                .pet(createdPet)
                .sendRequest()
                .assertRequestSuccess()
                .getResponseModel();
        System.out.println("Pet was deleted: " + deletedPet.getId());
       // assertThat(deletedPet.getId()).isEqualTo(petToBeDeleted.getId());
    }

    private Pet getTestPet() {
        return new Pet().id(TestUtils.nextId()).name("alex").status(Pet.StatusEnum.AVAILABLE)
                .category(new Category().id(TestUtils.nextId()).name("dog"))
                .photoUrls(Arrays.asList("http://foo.bar.com/1"));
    }
}
