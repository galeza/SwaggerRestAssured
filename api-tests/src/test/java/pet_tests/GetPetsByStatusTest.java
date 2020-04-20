package pet_tests;

import base.BaseTest;
import fr.galeza.example.swagger.client.model.Pet;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import requests.GetPetsByStatusApiRequest;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


public class GetPetsByStatusTest extends BaseTest {


    @Test
    @Tag("Integration")
    public void getPetsByStatusTest() {


        List<Pet> pets = new GetPetsByStatusApiRequest()
                .pet(Pet.StatusEnum.AVAILABLE.toString())
                .sendRequest()
                .assertRequestSuccess()
                .getResponseModel();

        assertThat(pets.isEmpty()).isFalse();


    }
}