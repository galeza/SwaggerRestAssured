package requests;

import endpoints.PetApiEndpoint;
import fr.galeza.example.swagger.client.model.Pet;
import org.apache.http.HttpStatus;

import java.lang.reflect.Type;
import java.util.List;
import java.util.function.Function;

public class GetPetsByStatusApiRequest extends PetApiEndpoint<GetPetsByStatusApiRequest, List<Pet>>{

    private String petStatus;

    public GetPetsByStatusApiRequest pet(String petStatus) {
        this.petStatus = petStatus;
        return this;
    }

    @Override
    protected Type getModelType() {
        return List.class;
    }

    @Override
    public GetPetsByStatusApiRequest sendRequest() {
        response = getServiceApi()
                .findPetsByStatus()
                .statusQuery(petStatus)
                .execute(Function.identity());

        return this;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_OK;
    }
}
