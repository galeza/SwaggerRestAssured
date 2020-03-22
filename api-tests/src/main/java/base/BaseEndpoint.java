package base;

import io.restassured.response.Response;

import java.lang.reflect.Type;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("unchecked")
public abstract class BaseEndpoint<E, M> {
    protected Response response;

    protected abstract Type getModelType();

    public abstract E sendRequest();

    protected abstract int getSuccessStatusCode();

    public M getResponseModel() {
        assertThatResponseIsNotNull();
        return response.as(getModelType());
    }

    public E assertRequestSuccess() {
        return assertStatusCode(getSuccessStatusCode());
    }

    public E assertStatusCode(int statusCode) {
        assertThatResponseIsNotNull();
        assertThat(response.getStatusCode()).as("Status code").isEqualTo(statusCode);
        return (E) this;
    }

    protected void assertThatResponseIsNotNull() {
        assertThat(response).as("Request response").isNotNull();
    }
}
