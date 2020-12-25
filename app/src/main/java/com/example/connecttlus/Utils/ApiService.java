package com.example.connecttlus.Utils;

import com.example.connecttlus.Object.getActivity;
import com.example.connecttlus.Object.getNotify;
import com.example.connecttlus.Object.getDoc;
import com.example.connecttlus.Object.getND;
import com.example.connecttlus.Object.getUser;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public interface ApiService {
    String api = "admin_api.php";

    @FormUrlEncoded
    @POST(api)
    Call<getNotify> getDSNotify(@Field("event") String event, @Field("page") int page, @Field("record") int record);

    @FormUrlEncoded
    @POST(api)
    Call<getDoc> getDSMonhoc(@Field("event") String event, @Field("page") int page, @Field("record") int record);

    @FormUrlEncoded
    @POST(api)
    Call<getND> getDSNoiDung(@Field("event") String event, @Field("page") int page, @Field("record") int record, @Field("mamon") String mamon);

    @FormUrlEncoded
    @POST(api)
    Call<getUser> login(@Field("event") String event, @Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST(api)
    Call<getActivity> getDSactivity(@Field("event") String event, @Field("page") int page, @Field("record") int record);
}