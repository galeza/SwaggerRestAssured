package pet_tests;
import fr.galeza.example.swagger.client.model.Pet;
import org.junit.jupiter.api.Test;
import requests.GetPetApiRequest;
import static org.assertj.core.api.Assertions.assertThat;


public class GetPetTests {
    @Test
    public void getPetTest() {
        Pet pet = new GetPetApiRequest()
                .pet("123")
                .sendRequest()
                .assertRequestSuccess()
                .getResponseModel();

        assertThat(pet.getId()).isEqualTo(123L);
    }
}
