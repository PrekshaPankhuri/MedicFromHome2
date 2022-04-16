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

public class BloodDonorAdapter extends FirebaseRecyclerAdapter<BloodDonorModel,BloodDonorAdapter.BloodDonorViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public BloodDonorAdapter(@NonNull FirebaseRecyclerOptions<BloodDonorModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull BloodDonorViewHolder holder, int position, @NonNull BloodDonorModel model) {
        holder.BloodBank.setText(model.getBloodBank());
        holder.Address.setText(model.getAddress());
        holder.Name.setText(model.getName());
        holder.Number.setText(model.getNumber());
        holder.City.setText(model.getCity());

        holder.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),DonorRegister.class);
                intent.putExtra("HospitalID",model.getID());
                intent.putExtra("HospitalName",model.getName());
                view.getContext().startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public BloodDonorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.blooddonor_list,parent,false);
        return new BloodDonorViewHolder(view);
    }

    public static class BloodDonorViewHolder extends RecyclerView.ViewHolder {

        TextView Name,City,Address,Number,BloodBank;
        Button register;

        public BloodDonorViewHolder(@NonNull View itemView) {
            super(itemView);

            Name=itemView.findViewById(R.id.Name);
            City=itemView.findViewById(R.id.City);
            Address=itemView.findViewById(R.id.Address);
            Number=itemView.findViewById(R.id.Number);
            BloodBank=itemView.findViewById(R.id.bloodBank);
            register=itemView.findViewById(R.id.register);

        }
    }
}
