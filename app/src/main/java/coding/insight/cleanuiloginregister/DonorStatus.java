package coding.insight.cleanuiloginregister;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class DonorStatus extends AppCompatActivity {

    RecyclerView recyclerView;
    DonorStatusAdapter donorStatusAdapter;
    FirebaseAuth fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_status);

        recyclerView=findViewById(R.id.bloodDonorStatus);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fauth=FirebaseAuth.getInstance();

        String id= Objects.requireNonNull(fauth.getCurrentUser()).getUid();

        FirebaseRecyclerOptions<DonorStatusModel> options=new FirebaseRecyclerOptions.Builder<DonorStatusModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference("PendingDonors").orderByChild("UserID").equalTo(id),DonorStatusModel.class)
                .build();

        donorStatusAdapter=new DonorStatusAdapter(options);
        recyclerView.setAdapter(donorStatusAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        donorStatusAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        donorStatusAdapter.stopListening();
    }

    public void onLoginClick(View view){
        startActivity(new Intent(this,BloodDonation.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }

}