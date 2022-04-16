package coding.insight.cleanuiloginregister;

public class BloodDonorModel {

    String Name,Number,City,Email,Address,BloodBank,ID;

    BloodDonorModel(){}

    public BloodDonorModel(String name, String number, String city, String email, String address, String bloodBank, String ID) {
        Name = name;
        Number = number;
        City = city;
        Email = email;
        Address = address;
        BloodBank = bloodBank;
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getBloodBank() {
        return BloodBank;
    }

    public void setBloodBank(String bloodBank) {
        BloodBank = bloodBank;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
