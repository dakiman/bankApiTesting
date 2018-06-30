package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Address {
//    @JsonIgnore
    public long Id;
    public String Street;
    public String City;
    public String Country;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (Street != null ? !Street.equals(address.Street) : address.Street != null) return false;
        if (City != null ? !City.equals(address.City) : address.City != null) return false;
        return Country != null ? Country.equals(address.Country) : address.Country == null;
    }

    @Override
    public int hashCode() {
        int result = Street != null ? Street.hashCode() : 0;
        result = 31 * result + (City != null ? City.hashCode() : 0);
        result = 31 * result + (Country != null ? Country.hashCode() : 0);
        return result;
    }
}
