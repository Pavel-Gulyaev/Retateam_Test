package twe.testprojects.rentateamtest.user_list;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import twe.testprojects.rentateamtest.model.UserListResponse;
import twe.testprojects.rentateamtest.network.UserApiInterface;
import static twe.testprojects.rentateamtest.network.UserApiClient.getClient;

public class UserListModel implements UserListContract.Model {

    @Override
    public void getUserList(final OnFinishedListener callback, int pageNo) {

        UserApiInterface retrofitApi = getClient().create(UserApiInterface.class);
        Call<UserListResponse> call = retrofitApi.getUserResponse(pageNo);

        call.enqueue(new Callback<UserListResponse>() {
            @Override
            public void onResponse(Call<UserListResponse> call, Response<UserListResponse> response) {
                callback.onFinished(response.body().getData());
            }

            @Override
            public void onFailure(Call<UserListResponse> call, Throwable t) {
                callback.onFailure(t);
            }
        });

    }

}
