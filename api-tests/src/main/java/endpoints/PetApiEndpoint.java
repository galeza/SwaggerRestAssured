package endpoints;

import base.BaseEndpoint;
import base.RestService;
import configuration.ConfigurationBuilder;
import fr.galeza.example.swagger.client.api.PetApi;

public abstract class PetApiEndpoint<E, M> extends BaseEndpoint<E, M> implements RestService<PetApi> {
    @Override
    public PetApi getServiceApi() {
        return PetApi.pet(new ConfigurationBuilder().getRequestSpecBuilder());
    }
}
