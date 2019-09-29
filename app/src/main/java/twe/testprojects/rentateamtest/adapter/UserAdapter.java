package twe.testprojects.rentateamtest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import twe.testprojects.rentateamtest.R;
import twe.testprojects.rentateamtest.model.User;
import twe.testprojects.rentateamtest.user_list.UserListActivity;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private UserListActivity userListActivity;
    private List<User> userList;

    public UserAdapter (UserListActivity userListActivity, List<User> userList){
        this.userList = userList;
        this.userListActivity = userListActivity;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        User user = userList.get(position);
        holder.tvUserName.setText(user.getName());
        holder.tvUserSurname.setText(user.getSurname());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userListActivity.onUserItemClick(position);
            }
        });

    }

    public void setData(List<User> userList){
        this.userList = userList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvUserName;

        public TextView tvUserSurname;

        public MyViewHolder(View itemView){
            super(itemView);

            tvUserName = itemView.findViewById(R.id.tv_user_name);
            tvUserSurname = itemView.findViewById(R.id.tv_user_surname);
        }
    }
}
