package coding.insight.cleanuiloginregister;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.ReceiverCallNotAllowedException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class BloodRecipient extends AppCompatActivity {

    RecyclerView recyclerView;
    BloodRecipientAdapter bloodRecipientAdapter;
    String yes="Yes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#027876"));
        actionBar.setBackgroundDrawable(colorDrawable);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_recipient);

        recyclerView=findViewById(R.id.BloodRecipient);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<BloodRecipientModel> options=new FirebaseRecyclerOptions.Builder<BloodRecipientModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference("hospital").orderByChild("BloodBank").equalTo(yes),BloodRecipientModel.class)
                .build();

        bloodRecipientAdapter=new BloodRecipientAdapter(options);
        recyclerView.setAdapter(bloodRecipientAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        bloodRecipientAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        bloodRecipientAdapter.stopListening();
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

        FirebaseRecyclerOptions<BloodRecipientModel> options=new FirebaseRecyclerOptions.Builder<BloodRecipientModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("hospital").orderByChild("Search").startAt(q).endAt(q+"\uf8ff"),BloodRecipientModel.class)
                .build();

        bloodRecipientAdapter=new BloodRecipientAdapter(options);
        bloodRecipientAdapter.startListening();
        recyclerView.setAdapter(bloodRecipientAdapter);

    }

    public void onLoginClick(View view){
        startActivity(new Intent(this,BloodDonation.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }

}