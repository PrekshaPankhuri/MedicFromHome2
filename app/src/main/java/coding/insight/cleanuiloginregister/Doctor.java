package coding.insight.cleanuiloginregister;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Doctor extends AppCompatActivity {

    String Id,Name;
    RecyclerView recyclerView;
    DoctorSelectAdapter doctorSelectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#027876"));
        actionBar.setBackgroundDrawable(colorDrawable);

        SharedPreferences sh = getSharedPreferences("MedicPreference",MODE_PRIVATE);

        Id=sh.getString("HospitalID","fMsXldiMcwdSgizVNpDEy3c1xWH3");
        Name=sh.getString("HospitalName","RNT Hospital");
        recyclerView=findViewById(R.id.DoctorList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<DoctorSelectModel> options=new FirebaseRecyclerOptions.Builder<DoctorSelectModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference("doctors").orderByChild("Hospital").equalTo(Name),DoctorSelectModel.class)
                .build();

        doctorSelectAdapter=new DoctorSelectAdapter(options);
        recyclerView.setAdapter(doctorSelectAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        doctorSelectAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        doctorSelectAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.search,menu);

        MenuItem item=menu.findItem(R.id.search);
        SearchView searchView=(SearchView)item.getActionView();
        searchView.setQueryHint("Search Problem...");

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

        FirebaseRecyclerOptions<DoctorSelectModel> options=new FirebaseRecyclerOptions.Builder<DoctorSelectModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference("doctors").orderByChild("Search").startAt(q).endAt(q+"\uf8ff"),DoctorSelectModel.class)
                .build();

        doctorSelectAdapter=new DoctorSelectAdapter(options);
        doctorSelectAdapter.startListening();
        recyclerView.setAdapter(doctorSelectAdapter);

    }

    public void onLoginClick(View view){
        startActivity(new Intent(this,Booking.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }

}