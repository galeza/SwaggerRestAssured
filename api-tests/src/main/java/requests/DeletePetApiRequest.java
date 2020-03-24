package requests;

import endpoints.PetApiEndpoint;
import fr.galeza.example.swagger.client.model.Pet;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import org.apache.http.HttpStatus;

import java.lang.reflect.Type;
import java.util.function.Function;

public class DeletePetApiRequest extends PetApiEndpoint<DeletePetApiRequest, Pet> {

    private Pet pet;

    public DeletePetApiRequest pet(Pet pet) {
        this.pet = pet;
        return this;
    }

    @Override
    protected Type getModelType() {
        return Pet.class;
    }

    @Override
    public DeletePetApiRequest sendRequest() {
        response = getServiceApi().deletePet().petIdPath(pet.getId()).execute(Function.identity());
//        ResponseBody body = response.getBody();
//        System.out.println(body.asString());
        JsonPath jsonPath = response.jsonPath();
        String deletedPetId = jsonPath.get("message");
        System.out.println("deletedPetId " + deletedPetId);
        //pet.setId(Long. parseLong(deletedPetId));
        return this;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_OK;
    }
}
