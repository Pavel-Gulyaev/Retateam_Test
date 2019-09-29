package twe.testprojects.rentateamtest.user_list;

import java.util.List;

import twe.testprojects.rentateamtest.model.User;

public interface UserListContract {
    interface View {
        void onResponseFailure(Throwable throwable);
        void showProgress();
        void hideProgress();

        void onUserItemClick(int position);
    }

    interface Presenter {
        void requestDataFromServer();
        void onDestroy();
        void getMoreData(int pageNo);

    }

    interface Model{
        interface OnFinishedListener {
            void onFinished(List<User> userArrayList);

            void onFailure(Throwable throwable);
        }
        void getUserList(OnFinishedListener callback, int pageNo);
    }
}
