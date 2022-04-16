package coding.insight.cleanuiloginregister;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AcceptingDonor extends AppCompatActivity {
    String DonorName,BloodType,BloodUnits,Date,Time,DonorID,HospitalID;
    EditText donorname,bloodtype,bloodunits,date,time;
    Button confirm;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accepting_donor);

        ActionBar actionBar=getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        DonorName=getIntent().getStringExtra("DonorName");
        DonorID=getIntent().getStringExtra("DonorID");
        HospitalID=getIntent().getStringExtra("HospitalID");
        BloodType=getIntent().getStringExtra("BloodType");
        BloodUnits=getIntent().getStringExtra("BloodUnits");
        Date=getIntent().getStringExtra("Date");
        Time=getIntent().getStringExtra("Time");

        donorname=findViewById(R.id.donorname);
        bloodtype=findViewById(R.id.btype);
        bloodunits=findViewById(R.id.bunits);
        date=findViewById(R.id.date);
        time=findViewById(R.id.time);
        confirm=findViewById(R.id.Confirm);

        donorname.setText(DonorName);
        bloodtype.setText(BloodType);
        date.setText(Date);
        time.setText(Time);
        bloodunits.setText(BloodUnits);
        donorname.setEnabled(false);
        bloodtype.setEnabled(false);
        date.setEnabled(false);
        time.setEnabled(false);
        bloodunits.setEnabled(false);

        String stat="Booked";

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref= FirebaseDatabase.getInstance().getReference("BookedDonors").child(DonorID);
                HashMap book=new HashMap();
                book.put("DonorName", DonorName);
                book.put("BloodType",BloodType);
                book.put("DonorID",DonorID);
                book.put("HospitalID",HospitalID);
                book.put("Date",Date);
                book.put("Time",Time);
                book.put("BloodUnits",BloodUnits);
                book.put("Status",stat);

                ref.updateChildren(book).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        deletePending(DonorID);
                        Intent intent=new Intent(getApplicationContext(),BookedDonors.class);
                        intent.putExtra("HospitalID",HospitalID);
                        startActivity(intent);
                    }
                });
            }
        });

    }
    private void deletePending(String DonorID) {
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("PendingDonors")
                .child(DonorID);
        databaseReference.removeValue();
    }

    public void onLoginClick(View view) {
        onBackPressed();
    }
}