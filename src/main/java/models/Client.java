package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Client {
    public Integer Id;
    public String Name;
    public Long AddressId;
    public Long ClientTypeId;
    @JsonIgnore
    public String ClientTypeName;
    public Boolean IsActive;
    public Address Address;

    public Client() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (Name != null ? !Name.equals(client.Name) : client.Name != null) return false;
        if (ClientTypeName != null ? !ClientTypeName.equals(client.ClientTypeName) : client.ClientTypeName != null)
            return false;
        if (IsActive != null ? !IsActive.equals(client.IsActive) : client.IsActive != null) return false;
        return Address != null ? Address.equals(client.Address) : client.Address == null;
    }

    @Override
    public int hashCode() {
        int result = Name != null ? Name.hashCode() : 0;
        result = 31 * result + (ClientTypeName != null ? ClientTypeName.hashCode() : 0);
        result = 31 * result + (IsActive != null ? IsActive.hashCode() : 0);
        result = 31 * result + (Address != null ? Address.hashCode() : 0);
        return result;
    }
}
