package coding.insight.cleanuiloginregister;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class BookedDonorsAdapter extends FirebaseRecyclerAdapter<BookedDonorsModel,BookedDonorsAdapter.BookedDonorsViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public BookedDonorsAdapter(@NonNull FirebaseRecyclerOptions<BookedDonorsModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull BookedDonorsAdapter.BookedDonorsViewHolder holder, int position, @NonNull BookedDonorsModel model) {

        holder.Date.setText(model.getDate());
        holder.BloodType.setText(model.getBloodType());
        holder.DonorName.setText(model.getDonorName());
        holder.BloodUnits.setText(model.getBloodUnits());
        holder.Time.setText(model.getTime());

    }

    @NonNull
    @Override
    public BookedDonorsAdapter.BookedDonorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.final_donor_list,parent,false);
        return new BookedDonorsAdapter.BookedDonorsViewHolder(view);
    }

    public static class BookedDonorsViewHolder extends RecyclerView.ViewHolder {

        TextView BloodType,BloodUnits,Date,Time,DonorName;

        public BookedDonorsViewHolder(@NonNull View itemView) {
            super(itemView);

            DonorName=itemView.findViewById(R.id.donorName1);
            BloodType=itemView.findViewById(R.id.Bbtype);
            Time=itemView.findViewById(R.id.dTime1);
            Date=itemView.findViewById(R.id.DonorDate1);
            BloodUnits=itemView.findViewById(R.id.bbunits);

        }
    }
}
