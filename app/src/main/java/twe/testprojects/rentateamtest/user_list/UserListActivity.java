package twe.testprojects.rentateamtest.user_list;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import twe.testprojects.rentateamtest.R;
import twe.testprojects.rentateamtest.adapter.UserAdapter;
import twe.testprojects.rentateamtest.info.InfoFragment;
import twe.testprojects.rentateamtest.model.User;
import twe.testprojects.rentateamtest.user_datails.UserDetailsActivity;

public class UserListActivity extends AppCompatActivity implements UserListContract.View{

    private RecyclerView mRecyclerView;
    private UserAdapter mUserAdapter;
    private ProgressBar mProgressBar;
    private List<User> mUserList;
    UserListPresenter mUserListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(getString(R.string.users));

        initUI();

        mUserListPresenter = new UserListPresenter(this, this.getApplication());
        mUserListPresenter.requestDataFromServer();
        LiveData<List<User>> liveData = mUserListPresenter.getAllUsers();
        liveData.observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> userList) {
                mUserList.addAll(userList);
                mUserAdapter.setData(userList);

            }
        });

    }

    private void initUI() {
        final Fragment fragment = InfoFragment.newInstance();

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.navigation_info:

                                if (mRecyclerView.getVisibility() == View.VISIBLE) {
                                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                    fragmentTransaction.add(R.id.fl_content, fragment);
                                    fragmentTransaction.commit();
                                    mRecyclerView.setVisibility(View.INVISIBLE);
                                }

                                break;
                            case R.id.navigation_home:
                                if (mRecyclerView.getVisibility() == View.INVISIBLE){
                                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                    fragmentTransaction.remove(fragment);
                                    fragmentTransaction.commit();
                                    mRecyclerView.setVisibility(View.VISIBLE);
                                }

                        }
                        return true;
                    }
                });
        mRecyclerView = findViewById(R.id.rv_user_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProgressBar = findViewById(R.id.pb_loading);
        mUserList = new ArrayList<>();

        mUserAdapter = new UserAdapter(this, new ArrayList<User>());

        mRecyclerView.setAdapter(mUserAdapter);

    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(this, getString(R.string.communication_error), Toast.LENGTH_LONG).show();
        try{
            mUserList.addAll(mUserListPresenter.getAllUsers().getValue());
        } catch (NullPointerException e) {

        }

    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onUserItemClick(int position) {
        Intent detailIntent = UserDetailsActivity.newIntent(getApplicationContext(), mUserList.get(position));
        startActivity(detailIntent);
    }

}
