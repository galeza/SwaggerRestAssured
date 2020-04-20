package pet_tests;

import base.BaseTest;
import fr.galeza.example.swagger.client.model.Pet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import requests.PostPetApiRequest;
import requests.PutPetApiRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static util.Constants.GLOBAL_MESSAGE;
import org.junit.jupiter.api.Test;

@DisplayName("Update pet status: From PENDING to AVAILABLE and SOLD")
public class PutPetTest extends BaseTest {

    private String category = "dog";


    @ParameterizedTest
    @EnumSource(
            value = Pet.StatusEnum.class,
            names = {"PENDING"},
            mode = EnumSource.Mode.EXCLUDE)
    @DisplayName("Wrapped assertions in assertAll must all pass but each failure will be reported separately")
    public void updatePetNameAndStatusTest(Pet.StatusEnum status) {
        Pet petToBeAddedWithAvailableStatus = getTestPet(animal_name, Pet.StatusEnum.PENDING, category, photoUrl);

        Pet createdPet = new PostPetApiRequest()
                .pet(petToBeAddedWithAvailableStatus)
                .sendRequest()
                .assertRequestSuccess()
                .getResponseModel();

        assertThat(createdPet.getId()).isEqualTo(petToBeAddedWithAvailableStatus.getId());
        createdPet.setName("Changed_name");
        createdPet.setStatus(status);
        Pet updatedPet = new PutPetApiRequest().pet(createdPet).sendRequest().assertRequestSuccess().getResponseModel();
        assertAll(GLOBAL_MESSAGE,
                () -> assertThat(createdPet.getName()).isEqualTo(updatedPet.getName()),
                () -> assertThat(updatedPet.getStatus()).isEqualTo(status)
        );
    }


}
