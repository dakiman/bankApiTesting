package models;

import java.util.Date;

public class Transaction {
    public int Id;
    public int AccountFromId;
    public String AccountFromClientName;
    public int AccountToId;
    public String AccountToClientName;
    public int TransactionTypeId;
    public String TransactionTypeName;
    public int TransactionStatusId;
    public String TransactionStatusName;
    public int Sum;
    public Date Date;
}
