package twe.testprojects.rentateamtest.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import twe.testprojects.rentateamtest.model.UserListResponse;

public interface UserApiInterface {

    @GET("users")
    Call<UserListResponse> getUserResponse(@Query("page")int PageNo);

}
