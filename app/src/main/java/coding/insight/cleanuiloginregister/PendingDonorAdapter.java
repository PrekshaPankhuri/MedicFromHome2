package coding.insight.cleanuiloginregister;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class PendingDonorAdapter extends FirebaseRecyclerAdapter<PendingDonorModel,PendingDonorAdapter.PendingDonorViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public PendingDonorAdapter(@NonNull FirebaseRecyclerOptions<PendingDonorModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PendingDonorAdapter.PendingDonorViewHolder holder, int position, @NonNull PendingDonorModel model){


        holder.DonorName.setText(model.getDonorName());
        holder.BloodType.setText(model.getBloodType());
        holder.Date.setText(model.getDate());
        holder.Time.setText(model.getTime());
        holder.BloodUnits.setText(model.getBloodUnits());


        holder.Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(v.getContext(),AcceptingDonor.class);
                intent.putExtra("DonorID",model.getDonorID());
                intent.putExtra("HospitalID",model.getHospitalID());
                intent.putExtra("DonorName",model.getDonorName());
                intent.putExtra("BloodType",model.getBloodType());
                intent.putExtra("BloodUnits",model.getBloodUnits());
                intent.putExtra("Date",model.getDate());
                intent.putExtra("Time",model.getTime());
                v.getContext().startActivity(intent);

            }
        });


    }

    @NonNull
    @Override
    public PendingDonorAdapter.PendingDonorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.pendingdonor_list,parent,false);
        return new PendingDonorAdapter.PendingDonorViewHolder(view);
    }

    public static class PendingDonorViewHolder extends RecyclerView.ViewHolder {

        TextView DonorName,BloodType,Time,Date,BloodUnits;
        Button Accept;

        public PendingDonorViewHolder(@NonNull View itemView) {
            super(itemView);

            DonorName=itemView.findViewById(R.id.DonorName);
            BloodType=itemView.findViewById(R.id.bloodType);
            Time=itemView.findViewById(R.id.donatetime);
            Date=itemView.findViewById(R.id.DonationDate);
            BloodUnits=itemView.findViewById(R.id.bloodunit);
            Accept=itemView.findViewById(R.id.accept);

        }
    }
}


