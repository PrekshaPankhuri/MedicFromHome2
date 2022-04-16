package coding.insight.cleanuiloginregister;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class DonorStatusAdapter extends FirebaseRecyclerAdapter<DonorStatusModel,DonorStatusAdapter.DonorStatusViewAdapter> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public DonorStatusAdapter(@NonNull FirebaseRecyclerOptions<DonorStatusModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull DonorStatusViewAdapter holder, int position, @NonNull DonorStatusModel model) {

        holder.BloodUnits.setText(model.getBloodUnits());
        holder.HospitalName.setText(model.getHospitalName());
        holder.DonorName.setText(model.getDonorName());
        holder.BloodType.setText(model.getBloodType());
        holder.Date.setText(model.getDate());
        holder.Time.setText(model.getTime());

    }

    @NonNull
    @Override
    public DonorStatusViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.donorstatus_list,parent,false);
        return new DonorStatusViewAdapter(view);
    }

    public static class DonorStatusViewAdapter extends RecyclerView.ViewHolder {

        TextView DonorName,HospitalName,BloodType,BloodUnits,Time,Date;

        public DonorStatusViewAdapter(@NonNull View itemView) {
            super(itemView);

            DonorName=itemView.findViewById(R.id.donorname1);
            HospitalName=itemView.findViewById(R.id.hospital1);
            BloodType=itemView.findViewById(R.id.bloodtype1);
            BloodUnits=itemView.findViewById(R.id.bloodunits1);
            Date=itemView.findViewById(R.id.date1);
            Time=itemView.findViewById(R.id.time1);

        }
    }
}
