package com.example.connecttlus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.connecttlus.Fragments.NotiFragment;
import com.example.connecttlus.Fragments.ProfileFragment;
import com.example.connecttlus.Object.User;
import com.example.connecttlus.Object.getUser;
import com.example.connecttlus.Utils.ApiClient;

import java.io.Serializable;
import java.util.prefs.Preferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText us,pw;
    ImageView loginback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginback=(ImageView) findViewById(R.id.loginback);
        us=(EditText) findViewById(R.id.lid);
        pw=(EditText) findViewById(R.id.lpw);
        loginback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void login(View view) {
        ApiClient.getApiService().login("login",us.getText().toString().trim(),pw.getText().toString()).enqueue(new Callback<getUser>() {
            @Override
            public void onResponse(Call<getUser> call, Response<getUser> response) {
                if (response.body().getEvent()==1){
                    StartActivity.sEditor.putString("user",us.getText().toString().trim());
                    StartActivity.sEditor.putString("pass",pw.getText().toString());
                    StartActivity.sEditor.commit();
                    ProfileFragment.user = response.body().getUser();
                    /*ProfileFragment.idUser = user.getIduser();
                    ProfileFragment.userName = user.getUsername();
                    ProfileFragment.userPhone = user.getPhone();
                    ProfileFragment.idStudent = user.getIdstudent();
                    ProfileFragment.course = user.getCourse();
                    ProfileFragment.lop = user.getLop();
                    ProfileFragment.userAddress = user.getAddress();
                    ProfileFragment.userEmail = user.getEmail();
                    ProfileFragment.userSex = user.getSex();
                    ProfileFragment.dob = user.getDateofbirth();
                    ProfileFragment.detailName = user.getDetailname();
                    if (user.getAvartar().length()>0)
                        ProfileFragment.urlavatar = "https://viennhagroup.com/file/"+user.getAvartar();*/
                    setResult(RESULT_OK);
                    Toast.makeText(getApplicationContext(),"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                    Toast.makeText(getApplicationContext(),"Sai tên đăng nhập hoặc mật khẩu",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<getUser> call, Throwable t) {

            }
        });
    }
}