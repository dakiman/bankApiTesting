package client;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class RestClient {
    private String route;

    public RestClient() {

    }

    public RestClient(String route) {
        this.route = route;
    }

    public Response get() {
        return when().get(route);
    }

    public Response get(String id) {
        return when().get(route + id);
    }


    public Response post(Object data) {
        return  given().
                    body(data).
                when().
                    post(this.route);
    }

    public Response put(Object data, String id) {
        return  given().
                    body(data).
                when().
                    put(this.route + id);
    }

    public Response delete(String id) {
        return  when().
                    delete(route + id);
    }

    public Response post(Object data, String route) {
        return  given().
                body(data).
                when().
                post(route);
    }

    public Response put(Object data, String id, String route) {
        return  given().
                body(data).
                when().
                put(route + id);
    }

    public Response delete(String id, String route) {
        return  when().
                delete(route + id);
    }




}
