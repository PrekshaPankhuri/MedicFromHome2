package coding.insight.cleanuiloginregister;

public class DonorStatusModel {

    String userID,DonorName,BloodType,BloodUnits,Date,Time,HospitalName;

    DonorStatusModel(){}

    public DonorStatusModel(String userID, String donorName, String bloodType, String bloodUnits, String date, String time, String hospitalName) {
        this.userID = userID;
        DonorName = donorName;
        BloodType = bloodType;
        BloodUnits = bloodUnits;
        Date = date;
        Time = time;
        HospitalName = hospitalName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDonorName() {
        return DonorName;
    }

    public void setDonorName(String donorName) {
        DonorName = donorName;
    }

    public String getBloodType() {
        return BloodType;
    }

    public void setBloodType(String bloodType) {
        BloodType = bloodType;
    }

    public String getBloodUnits() {
        return BloodUnits;
    }

    public void setBloodUnits(String bloodUnits) {
        BloodUnits = bloodUnits;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getHospitalName() {
        return HospitalName;
    }

    public void setHospitalName(String hospitalName) {
        HospitalName = hospitalName;
    }
}
