package coding.insight.cleanuiloginregister;

public class PendingDonorModel {
    String DonorName,BloodType,BloodUnits,Date,Time,DonorID,HospitalID;
    PendingDonorModel(){}

    public String getHospitalID() {
        return HospitalID;
    }

    public void setHospitalID(String hospitalID) {
        HospitalID = hospitalID;
    }

    public String getDonorName() {
        return DonorName;
    }

    public String getDonorID() {
        return DonorID;
    }

    public void setDonorID(String donorID) {
        DonorID = donorID;
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

    public PendingDonorModel(String donorName, String bloodType, String bloodUnits, String date, String time){
        DonorName= donorName;
        BloodType= bloodType;
        BloodUnits= bloodUnits;
        Date= date;
        Time=time;

    }
}
