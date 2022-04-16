package coding.insight.cleanuiloginregister;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class BloodDonation extends AppCompatActivity {

    RecyclerView recyclerView;
    BloodDonorAdapter bloodDonorAdapter;
    TextView Status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_donation);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#027876"));
        actionBar.setBackgroundDrawable(colorDrawable);


        recyclerView=findViewById(R.id.bloodDonorHospitalList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<BloodDonorModel> options=new FirebaseRecyclerOptions.Builder<BloodDonorModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference("hospital"),BloodDonorModel.class)
                .build();

        bloodDonorAdapter=new BloodDonorAdapter(options);
        recyclerView.setAdapter(bloodDonorAdapter);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.search,menu);

        MenuItem item=menu.findItem(R.id.search);
        SearchView searchView=(SearchView)item.getActionView();
        searchView.setQueryHint("Search City...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                processSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                processSearch(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void processSearch(String query) {

        String q=query.toLowerCase();

        FirebaseRecyclerOptions<BloodDonorModel> options=new FirebaseRecyclerOptions.Builder<BloodDonorModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("hospital").orderByChild("Search").startAt(q).endAt(q+"\uf8ff"),BloodDonorModel.class)
                .build();

        bloodDonorAdapter=new BloodDonorAdapter(options);
        bloodDonorAdapter.startListening();
        recyclerView.setAdapter(bloodDonorAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        bloodDonorAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        bloodDonorAdapter.stopListening();
    }

    public void onLoginClick(View view){
        startActivity(new Intent(this,HomeScreen.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }

    public void onStatusClick(View view){
        startActivity(new Intent(this,DonorStatus.class));
    }

    public void onRight(View view){
        startActivity(new Intent(getApplicationContext(),BloodRecipient.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
    }

}