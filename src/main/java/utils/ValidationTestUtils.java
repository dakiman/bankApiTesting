package utils;

import client.RestClient;
import io.restassured.response.Response;

import java.lang.reflect.Field;
import java.util.Arrays;

public class ValidationTestUtils {
    private String route;
    private RestClient restClient;

    public ValidationTestUtils() {
        restClient = new RestClient();
    }

    public ValidationTestUtils(String route) {
        this.route = route;
        restClient = new RestClient(route);
    }

    private void changeFieldValue(String fieldName, Object obj, String value) {
        try {
            Class aClass = obj.getClass();
            Field field = aClass.getField(fieldName);
            field.set(obj, value);
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    private String getFieldType(String fieldName, Object obj) {
        String str = "INVALID TYPE";
        try {
            Class aClass = obj.getClass();
            Field field = aClass.getField(fieldName);
            str = field.getType().toString();

        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        str = str.replace("class java.lang.", "");
        return str.toLowerCase();
    }

    private String getModelType(Object obj)
    {
        String modelType = obj.getClass().toString();
        System.out.println(modelType);
        modelType = modelType.replace("class models.", "");
        return modelType.toLowerCase();
    }


    /*
      OVERLOADED METHODS OVERRIDING OBJECTS DEFAULTS
     */

    public void fieldIsRequired(String fieldName, Object data) {
        fieldIsRequired(fieldName, data, this.route);
    }

    public void fieldMaximumLength(String fieldName, int max, Object data) {
        fieldMaximumLength(fieldName, max, data, this.route);
    }

    public void fieldMinimumLength(String fieldName, int min, Object data) {
        fieldMinimumLength(fieldName, min, data, this.route);
    }

    public void fieldMinimumValue(String fieldName, int min, int max, Object data) {
        fieldMinimumValue(fieldName, min, max, data, this.route);
    }

    public void fieldIsRequired(String fieldName, Object data, String route) {
        changeFieldValue(fieldName, data, null);
        Response res = restClient.post(data, route);
        AssertionUtils.assertValidationErrorMessage(res, getModelType(data) + "Model."+ fieldName, "The " + fieldName + " field is required.");
    }

    public void fieldMaximumLength(String fieldName, int max, Object data, String route) {
        String type = getFieldType(fieldName, data);
        changeFieldValue(fieldName, data, RandomUtils.randomString(max + 1));
        Response res = restClient.post(data, route);
        AssertionUtils.assertValidationErrorMessage(res, getModelType(data) + "Model."+ fieldName, "The field " + fieldName + " must be a "+ type +" with a maximum length of " + max + ".");
    }

    public void fieldMinimumLength(String fieldName, int min, Object data, String route) {
        changeFieldValue(fieldName, data, RandomUtils.randomString(min - 1));
        Response res = restClient.post(data, route);
        AssertionUtils.assertValidationErrorMessage(res, getModelType(data) + "Model."+ fieldName, "The " + fieldName + " must be at least " + min + " characters.");
    }


    public void fieldMinimumValue(String fieldName, int min, int max, Object data, String route) {
        changeFieldValue(fieldName, data, String.valueOf(min - 1));
        Response res = restClient.post(data, route);
        AssertionUtils.assertValidationErrorMessage(res, getModelType(data) + "Model."+ fieldName, "The field " + fieldName + " must be between " + min + " and "+ max +".");
    }

}
