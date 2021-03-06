package service;

import client.RestClient;
import io.restassured.response.Response;
import models.Transaction;

public class TransactionService {
    RestClient restClient = new RestClient("transactions/");

    public Response addTransaction(Transaction transaction) {
        return restClient.post(transaction);
    }
}

