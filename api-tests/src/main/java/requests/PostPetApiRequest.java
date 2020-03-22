package requests;

import endpoints.PetApiEndpoint;
import fr.galeza.example.swagger.client.model.Pet;
import org.apache.http.HttpStatus;

import java.lang.reflect.Type;
import java.util.function.Function;

public class PostPetApiRequest extends PetApiEndpoint<PostPetApiRequest, Pet> {
    private Pet pet;

    public PostPetApiRequest pet(Pet pet) {
        this.pet = pet;
        return this;
    }

    @Override
    protected Type getModelType() {
        return Pet.class;
    }

    @Override
    public PostPetApiRequest sendRequest() {
        response = getServiceApi()
                .addPet()
                .body(pet)
                .execute(Function.identity());
        return this;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_OK;
    }
}
