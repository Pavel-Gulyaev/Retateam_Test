package twe.testprojects.rentateamtest.user_datails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


import androidx.appcompat.app.AppCompatActivity;
import twe.testprojects.rentateamtest.R;
import twe.testprojects.rentateamtest.model.User;

public class UserDetailsActivity extends AppCompatActivity {
    private User user;
    private ImageView mImageView;
    private TextView tvUserName;
    private TextView tvUserSurname;
    private TextView tvUserEmail;

    public static final String KEY_USER_NAME = "name";
    public static final String KEY_USER_SURNAME = "surname";
    public static final String KEY_USER_EMAIL = "email";
    public static final String KEY_USER_AVATAR = "avatar";

    public static Intent newIntent(Context context, User user) {
        Intent intent = new Intent(context, UserDetailsActivity.class);
        intent.putExtra(KEY_USER_NAME, user.getName());
        intent.putExtra(KEY_USER_SURNAME, user.getSurname());
        intent.putExtra(KEY_USER_EMAIL, user.getEmail());
        intent.putExtra(KEY_USER_AVATAR, user.getAvatar());
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        initUI();

        Intent mIntent = getIntent();
        user = new User(0,
                mIntent.getStringExtra(KEY_USER_EMAIL),
                mIntent.getStringExtra(KEY_USER_NAME),
                mIntent.getStringExtra(KEY_USER_SURNAME),
                mIntent.getStringExtra(KEY_USER_AVATAR));

        setDataToViews();
    }

    private void initUI() {

        mImageView = findViewById(R.id.iv_avatar);
        tvUserName = findViewById(R.id.tv_name);
        tvUserSurname = findViewById(R.id.tv_surname);
        tvUserEmail = findViewById(R.id.tv_email);
    }

    private void setDataToViews(){
        tvUserName.setText(user.getName());
        tvUserSurname.setText(user.getSurname());
        tvUserEmail.setText(user.getEmail());
        setAvatar();
    }

    private void setAvatar(){
        Glide.with(this)
                .load(user.getAvatar())
                .apply(new RequestOptions().placeholder(R.drawable.ic_place_holder).error(R.drawable.ic_place_holder))
                .into(mImageView);
    }
}
