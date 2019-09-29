package twe.testprojects.rentateamtest.user_list;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.LiveData;
import twe.testprojects.rentateamtest.database.UserRepository;
import twe.testprojects.rentateamtest.model.User;

public class UserListPresenter implements UserListContract.Presenter, UserListContract.Model.OnFinishedListener {

    UserListContract.Model mModel;
    UserListContract.View mView;

    UserRepository mUserRepository;

    public UserListPresenter(UserListContract.View mView, Application application) {
        this.mView = mView;
        mModel = new UserListModel();
        mUserRepository = new UserRepository(application);
    }

    @Override
    public void requestDataFromServer() {
        if (mView != null){
            mView.showProgress();
            mModel.getUserList(this, 1);
        }
    }

    @Override
    public void onDestroy() {
        this.mView = null;
    }

    @Override
    public void getMoreData(int pageNo) {
        if (mView != null) {
            mView.showProgress();
        }
        mModel.getUserList(this, pageNo);
    }

    @Override
    public void onFinished(List<User> userArrayList) {
        mUserRepository.deleteAll();

        for (User user: userArrayList){
            mUserRepository.insert(user);
        }
        if (mView != null) {
            mView.hideProgress();
        }

    }

    @Override
    public void onFailure(Throwable throwable) {
        mView.onResponseFailure(throwable);
        if (mView != null) {
            mView.hideProgress();
        }

    }

    public LiveData<List<User>> getAllUsers(){
        return mUserRepository.getAllUsers();
    }

}
