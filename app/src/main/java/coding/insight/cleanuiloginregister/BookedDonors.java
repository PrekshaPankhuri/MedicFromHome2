package coding.insight.cleanuiloginregister;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class BookedDonors extends AppCompatActivity {
    String HospitalName,ID;
    RecyclerView recyclerView;
    BookedDonorsAdapter bookedDonorsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_donors);

        ActionBar actionBar=getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        SharedPreferences sh = getSharedPreferences("MedicPreference",MODE_PRIVATE);

        ID = sh.getString("HospitalID","fMsXldiMcwdSgizVNpDEy3c1xWH3");
        Log.e("Booked Donor",ID);

        recyclerView=findViewById(R.id.finalDonorlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<BookedDonorsModel> options=new FirebaseRecyclerOptions.Builder<BookedDonorsModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference("BookedDonors").orderByChild("HospitalID").equalTo(ID),BookedDonorsModel.class)
                .build();

        bookedDonorsAdapter=new BookedDonorsAdapter(options);
        recyclerView.setAdapter(bookedDonorsAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        bookedDonorsAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        bookedDonorsAdapter.stopListening();
    }

    public void onLoginClick(View view) {
    onBackPressed();
    }
}

