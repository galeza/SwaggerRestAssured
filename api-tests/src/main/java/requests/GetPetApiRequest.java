package requests;

import endpoints.PetApiEndpoint;
import fr.galeza.example.swagger.client.model.Pet;
import org.apache.http.HttpStatus;

import java.lang.reflect.Type;
import java.util.function.Function;

public class GetPetApiRequest extends PetApiEndpoint<GetPetApiRequest, Pet>{

    private String petId;

    public GetPetApiRequest pet(String petId) {
        this.petId = petId;
        return this;
    }

    @Override
    protected Type getModelType() {
        return Pet.class;
    }

    @Override
    public GetPetApiRequest sendRequest() {
        response = getServiceApi()
                .getPetById()
                .petIdPath(petId)
                .execute(Function.identity());

        return this;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_OK;
    }
}
