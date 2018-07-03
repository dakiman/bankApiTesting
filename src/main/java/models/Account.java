package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Account {
    public Integer Id;
    public Integer ClientId;
    public String ClientName;
    public Integer AccountTypeId;
    public String AccountTypeName;
    public String AllowedDebt;
    public String Balance;
    public Boolean IsActive;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (ClientId != account.ClientId) return false;
        if (AccountTypeId != account.AccountTypeId) return false;
        if (AllowedDebt != null ? !AllowedDebt.equals(account.AllowedDebt) : account.AllowedDebt != null) return false;
        return IsActive != null ? IsActive.equals(account.IsActive) : account.IsActive == null;
    }

    @Override
    public int hashCode() {
        int result = ClientId;
        result = 31 * result + AccountTypeId;
        result = 31 * result + (AllowedDebt != null ? AllowedDebt.hashCode() : 0);
        result = 31 * result + (IsActive != null ? IsActive.hashCode() : 0);
        return result;
    }
}
