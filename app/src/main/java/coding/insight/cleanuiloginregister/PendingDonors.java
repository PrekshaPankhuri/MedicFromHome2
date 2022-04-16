package coding.insight.cleanuiloginregister;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class PendingDonors extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseAuth fauth;
    PendingDonorAdapter pendingDonorAdapter;
    String HospitalName,ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_donors);

        ActionBar actionBar=getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        recyclerView=findViewById(R.id.pendingDonorlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SharedPreferences sh = getSharedPreferences("MedicPreference",MODE_PRIVATE);

        HospitalName = sh.getString("HospitalName","RNT Hospital");
        ID = sh.getString("HospitalID","fMsXldiMcwdSgizVNpDEy3c1xWH3");
        FirebaseRecyclerOptions<PendingDonorModel> options=new FirebaseRecyclerOptions.Builder<PendingDonorModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference("PendingDonors").orderByChild("HospitalName").equalTo(HospitalName),PendingDonorModel.class)
                .build();

        pendingDonorAdapter=new PendingDonorAdapter(options);
        recyclerView.setAdapter(pendingDonorAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        pendingDonorAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        pendingDonorAdapter.stopListening();
    }

    public void onLoginClick(View view) {
        onBackPressed();
    }
}