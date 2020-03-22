package configuration;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import util.Constants;


public class ConfigurationBuilder {


//TODO
    public RequestSpecBuilder getRequestSpecBuilder() {
//        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
//        requestSpecBuilder.setConfig(getConfiguration());
//        requestSpecBuilder.setBody(userTimingRequest);
//        requestSpecBuilder.setContentType(ContentType.JSON);
//        //getFilters(requestSpecBuilder);
//        return requestSpecBuilder.build();
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(Constants.BASE_URL);
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecBuilder.setAccept(ContentType.JSON);
        return requestSpecBuilder;

    }
}
