package models;

import java.util.Date;

public class Transaction {
    public Integer Id;
    public Integer AccountFromId;
    public String AccountFromClientName;
    public Integer AccountToId;
    public String AccountToClientName;
    public Integer TransactionTypeId;
    public String TransactionTypeName;
    public Integer TransactionStatusId;
    public String TransactionStatusName;
    public Integer Sum;
    public Date Date;
}
