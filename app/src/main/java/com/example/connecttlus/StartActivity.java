package com.example.connecttlus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceFragment;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.connecttlus.Fragments.ProfileFragment;
import com.example.connecttlus.Object.User;
import com.example.connecttlus.Object.getUser;
import com.example.connecttlus.Utils.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartActivity extends AppCompatActivity {

    String pass, user;
    User userlg;
    String event = "login";
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor sEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        TextView version = findViewById(R.id.txtversion);
        version.setText("Phiên bản 1.0");
        sharedPreferences = getSharedPreferences("slogin", MODE_PRIVATE);
        sEditor = sharedPreferences.edit();
        user = sharedPreferences.getString("user","");
        pass = sharedPreferences.getString("pass","");
        ApiClient.getApiService().login(event,user,pass).enqueue(new Callback<getUser>() {
            @Override
            public void onResponse(Call<getUser> call, Response<getUser> response) {
               if (response.body().getEvent()!=0){
                   sEditor.putInt("islogin",1);
                   ProfileFragment.user = response.body().getUser();
                   /*ProfileFragment.idUser = userlg.getIduser();
                   ProfileFragment.userName = userlg.getUsername();
                   ProfileFragment.userPhone = userlg.getPhone();
                   ProfileFragment.idStudent = userlg.getIdstudent();
                   ProfileFragment.course = userlg.getCourse();
                   ProfileFragment.lop = userlg.getLop();
                   ProfileFragment.userAddress = userlg.getAddress();
                   ProfileFragment.userEmail = userlg.getEmail();
                   ProfileFragment.userSex = userlg.getSex();
                   ProfileFragment.dob = userlg.getDateofbirth();
                   ProfileFragment.detailName = userlg.getDetailname();
                   if (userlg.getAvartar().length()>0)
                       ProfileFragment.urlavatar = "https://viennhagroup.com/file/"+userlg.getAvartar();*/
               }
               else
                   sEditor.putInt("islogin",0);
               sEditor.commit();
            }

            @Override
            public void onFailure(Call<getUser> call, Throwable t) {

            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        }, 1500);
    }
}