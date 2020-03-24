package requests;

import endpoints.PetApiEndpoint;
import fr.galeza.example.swagger.client.model.Pet;
import io.restassured.response.ResponseBody;
import org.apache.http.HttpStatus;

import java.lang.reflect.Type;
import java.util.function.Function;

public class PutPetApiRequest extends PetApiEndpoint<PutPetApiRequest, Pet> {
    private Pet pet;

    public PutPetApiRequest pet(Pet pet) {
        this.pet = pet;
        return this;
    }

    @Override
    protected Type getModelType() {
        return Pet.class;
    }

    @Override
    public PutPetApiRequest sendRequest() {
        response = getServiceApi().updatePet().body(pet)
                .execute(Function.identity());
//        ResponseBody body = response.getBody();
//        System.out.println(body.asString());
        return this;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_OK;
    }
}
