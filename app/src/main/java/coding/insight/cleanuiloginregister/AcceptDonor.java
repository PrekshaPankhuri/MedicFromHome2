package coding.insight.cleanuiloginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AcceptDonor extends AppCompatActivity {
    Button AcceptDonor,BookDonor;
    String HospitalName,HospitalID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_donor);

        AcceptDonor = findViewById(R.id.PendingDonor);
        BookDonor = findViewById(R.id.BookedDonor);
        HospitalName = getIntent().getStringExtra("HospitalName");
        HospitalID = getIntent().getStringExtra("ID");

        AcceptDonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AcceptDonor.this,PendingDonors.class);
                intent.putExtra("HospitalName",HospitalName);
                intent.putExtra("ID",HospitalID);
                startActivity(intent);

            }
        });
        BookDonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AcceptDonor.this, BookedDonors.class);
                intent.putExtra("HospitalName",HospitalName);
                intent.putExtra("HospitalID",HospitalID);
                startActivity(intent);

            }
        });
    }

    public void onLoginClick(View view) {
        onBackPressed();
    }
}