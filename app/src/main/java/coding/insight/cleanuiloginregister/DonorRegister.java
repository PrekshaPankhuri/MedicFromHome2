package coding.insight.cleanuiloginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Objects;

public class DonorRegister extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button DateButton,Book;
    TextView DateTextView,DonorName,BloodType,BloodUnits,Time;
    String HospitalID,HospitalName;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_register);

        HospitalID=getIntent().getStringExtra("HospitalID");
        HospitalName=getIntent().getStringExtra("HospitalName");

        DateButton = findViewById(R.id.DateButton);
        DateTextView = findViewById(R.id.Date);
        DonorName=findViewById(R.id.donorName);
        BloodType=findViewById(R.id.bloodType);
        BloodUnits=findViewById(R.id.bloodUnits);
        Time=findViewById(R.id.timedata3);
        Book=findViewById(R.id.BookButton);
        firebaseAuth=FirebaseAuth.getInstance();

        DateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleDateButton();
            }
        });

        Book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date=DateTextView.getText().toString();
                String time=Time.getText().toString();
                String donorName=DonorName.getText().toString();
                String bloodType=BloodType.getText().toString();
                String bloodUnits=BloodUnits.getText().toString();

                String userID= Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
                String t=Calendar.getInstance().getTime().toString();
                String DonorID=t+userID;

                databaseReference= FirebaseDatabase.getInstance().getReference("PendingDonors").child(DonorID);

                HashMap donorInfo=new HashMap();

                donorInfo.put("DonorID",DonorID);
                donorInfo.put("UserID",userID);
                donorInfo.put("HospitalID",HospitalID);
                donorInfo.put("Date",date);
                donorInfo.put("Time",time);
                donorInfo.put("DonorName",donorName);
                donorInfo.put("HospitalName",HospitalName);
                donorInfo.put("BloodType",bloodType);
                donorInfo.put("BloodUnits",bloodUnits);

                databaseReference.updateChildren(donorInfo).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        startActivity(new Intent(getApplicationContext(),DonorStatus.class));
                    }
                });

            }
        });

    }

    private void handleDateButton() {

        Calendar calendar = Calendar.getInstance();

        int YEAR=calendar.get(Calendar.YEAR);
        int MONTH=calendar.get(Calendar.MONTH);
        int DATE=calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String DateString=dayOfMonth+" "+month+" "+year;
                DateTextView.setText(DateString);
            }
        },YEAR,MONTH,DATE);
        datePickerDialog.show();

    }

    public void onLoginClick(View view){
        startActivity(new Intent(this,BloodDonation.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}