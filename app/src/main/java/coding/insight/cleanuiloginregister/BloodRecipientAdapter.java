package coding.insight.cleanuiloginregister;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class BloodRecipientAdapter extends FirebaseRecyclerAdapter<BloodRecipientModel,BloodRecipientAdapter.BloodRecipientViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public BloodRecipientAdapter(@NonNull FirebaseRecyclerOptions<BloodRecipientModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull BloodRecipientViewHolder holder, int position, @NonNull BloodRecipientModel model) {

        holder.BloodBank.setText(model.getBloodBank());
        holder.Address.setText(model.getAddress());
        holder.Name.setText(model.getName());
        holder.Number.setText(model.getNumber());
        holder.City.setText(model.getCity());

    }

    @NonNull
    @Override
    public BloodRecipientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.bloodrecipient_list,parent,false);
        return new BloodRecipientViewHolder(view);
    }

    public static class BloodRecipientViewHolder extends RecyclerView.ViewHolder {

        TextView Name,City,Address,Number,BloodBank;

        public BloodRecipientViewHolder(@NonNull View itemView) {
            super(itemView);

            Name=itemView.findViewById(R.id.Name1);
            City=itemView.findViewById(R.id.City1);
            Address=itemView.findViewById(R.id.Address1);
            Number=itemView.findViewById(R.id.Number1);
            BloodBank=itemView.findViewById(R.id.bloodBank1);

        }
    }
}
