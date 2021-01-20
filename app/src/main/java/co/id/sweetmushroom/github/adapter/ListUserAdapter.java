package co.id.sweetmushroom.github.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import co.id.sweetmushroom.github.R;
import co.id.sweetmushroom.github.model.User;

public class ListUserAdapter extends RecyclerView.Adapter<ListUserAdapter.ListViewHolder> {

    private ArrayList<User> listUser;
    private OnItemClickCallback onItemClickCallback;

    public ListUserAdapter(ArrayList<User> list){
        this.listUser = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view   = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_user, viewGroup, false);
        return new ListViewHolder(view);
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public interface OnItemClickCallback {
        void onItemClicked(User user);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, final int position) {
        User user = listUser.get(position);

        // Proses parsing string to int resource image drawable
        // hal ini dikarenakan terjadi kegagalan ketika load gambar
        // menggunakan glide jika berupa string dengan error path not found.
        String[] nameAvatar = user.getAvatar().split("/");
        String nameValue = nameAvatar[nameAvatar.length-1].replace(".png","").trim();
        int drawableId = holder.itemView.getContext().getResources().getIdentifier(nameValue , "drawable", holder.itemView.getContext().getPackageName());


        Glide.with(holder.itemView.getContext())
                .load(drawableId)
                .apply(new RequestOptions().override(100, 100))
                .into(holder.iv_avatar);

        holder.tv_name.setText(user.getName());
        holder.tv_company.setText(user.getCompany());
        holder.tv_location.setText(user.getLocation());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallback.onItemClicked(listUser.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_avatar;
        TextView tv_name, tv_company, tv_location;

        ListViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_avatar   = itemView.findViewById(R.id.iv_user_avatar);
            tv_name     = itemView.findViewById(R.id.tv_name);
            tv_company  = itemView.findViewById(R.id.tv_company);
            tv_location = itemView.findViewById(R.id.tv_location);
        }
    }


}
