package coding.insight.cleanuiloginregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Form extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button DateButton,Book;
    TextView DateTextView;
    EditText Describe,Problem,DoctorType,Time,UserName;
    DatabaseReference pending;
    FirebaseAuth fauth;
    String Name,ID,Hospital,Specification,Expertise,DoctorTime,Number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        changeStatusBarColor();

        ID=getIntent().getStringExtra("DoctorID");
        Name=getIntent().getStringExtra("DoctorName");
        Hospital=getIntent().getStringExtra("Hospital");
        Specification=getIntent().getStringExtra("Specification");
        Expertise=getIntent().getStringExtra("Expertise");
        DoctorTime=getIntent().getStringExtra("DoctorTime");
        Number=getIntent().getStringExtra("Number");

        DateButton = findViewById(R.id.DateButton);
        DateTextView = findViewById(R.id.Date);
        Book=findViewById(R.id.SubButton);
        Describe=findViewById(R.id.describeProblem);
        DoctorType=findViewById(R.id.doctortypedata2);
        Problem=findViewById(R.id.problemdata2);
        Time=findViewById(R.id.timedata2);
        UserName=findViewById(R.id.userNameData);
        fauth=FirebaseAuth.getInstance();

        Time.setText(DoctorTime);
        DoctorType.setText(Specification);
        Problem.setText(Expertise);
        Time.setEnabled(false);
        DoctorType.setEnabled(false);
        Problem.setEnabled(false);


        DateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleDateButton();
            }
        });


        Book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String describe=Describe.getText().toString();
                String date=DateTextView.getText().toString().trim();
                String userName=UserName.getText().toString();

                String userId=fauth.getCurrentUser().getUid();

                String t=Calendar.getInstance().getTime().toString();
                String id=t+userId;

                String stat="Pending";


                pending=FirebaseDatabase.getInstance().getReference("PendingAppointments").child(id);
                HashMap appInfo=new HashMap();
                appInfo.put("AppID",id);
                appInfo.put("T",t);
                appInfo.put("DoctorID",ID);
                appInfo.put("UserName",userName);
                appInfo.put("DoctorName",Name);
                appInfo.put("HospitalName",Hospital);
                appInfo.put("DoctorType",Specification);
                appInfo.put("Problem",Expertise);
                appInfo.put("Number",Number);
                appInfo.put("Time",DoctorTime);
                appInfo.put("UserID",userId);
                appInfo.put("ProblemDescription",describe);
                appInfo.put("Date",date);
                appInfo.put("Status",stat);

                pending.updateChildren(appInfo).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(Form.this, "Look for status of appointment on Appointment Status.", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),Status.class);
                        startActivity(intent);
                    }
                });

            }
        });

    }


    /*private void getName(String userId) {

        DatabaseReference ref=FirebaseDatabase.getInstance().getReference("users");
        Query query=ref.orderByChild("ID").equalTo(userId);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    UserName=snapshot.child("Name").getValue(String.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }*/

    private void handleDateButton(){
        Calendar calendar = Calendar.getInstance();

        int YEAR=calendar.get(Calendar.YEAR);
        int MONTH=calendar.get(Calendar.MONTH);
        int DATE=calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String DateString=year+" "+month+" "+dayOfMonth;
                DateTextView.setText(DateString);
            }
        },YEAR,MONTH,DATE);
        datePickerDialog.show();

    }


    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public void onLoginClick(View view){
        startActivity(new Intent(Form.this,Doctor.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}