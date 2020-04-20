package requests;

import endpoints.PetApiEndpoint;
import fr.galeza.example.swagger.client.model.Pet;
import org.apache.http.HttpStatus;

import java.lang.reflect.Type;
import java.util.List;
import java.util.function.Function;

public class GetPetByStatusApiRequest extends PetApiEndpoint<GetPetByStatusApiRequest, List<Pet>>{

    private String petId;

    public GetPetByStatusApiRequest pet(String petId) {
        this.petId = petId;
        return this;
    }

    @Override
    protected Type getModelType() {
        return Pet.class;
    }

    @Override
    public GetPetByStatusApiRequest sendRequest() {
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
