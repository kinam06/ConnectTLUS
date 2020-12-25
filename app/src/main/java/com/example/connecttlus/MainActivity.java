package com.example.connecttlus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.connecttlus.Fragments.DocFragment;
import com.example.connecttlus.Fragments.HomeFragment;
import com.example.connecttlus.Fragments.NotiFragment;
import com.example.connecttlus.Fragments.ProfileFragment;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;



public class MainActivity extends AppCompatActivity {

    private static Context context;
    private int i;
    TextView tb,tbus;
    public static BottomNavigationView bnavigation;
    ImageView imageView;
    Fragment fragment1 = new HomeFragment();
    Fragment fragment2 = new DocFragment();
    Fragment fragment3 = new NotiFragment();
    Fragment fragment4 = new ProfileFragment();
    FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment3;
    public static BadgeDrawable badgeDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        imageView = findViewById(R.id.profile_image);
        tbus = findViewById(R.id.btnlg);
        bnavigation = findViewById(R.id.bnavigation);
        bnavigation.setSelectedItemId(R.id.item3);
        badgeDrawable = bnavigation.getOrCreateBadge(R.id.item3);
        newFragments(fm);
        SharedPreferences sharedPreferences = getSharedPreferences("slogin", MODE_PRIVATE);
        i = sharedPreferences.getInt("islogin",0);
        if (i != 0) {
            newLogedInFragments(fm);
            tbus.setVisibility(View.GONE);
        }
        bnavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item1:
                        if (i!=0) {
                            fm.beginTransaction().hide(active).show(fragment1).commit();
                            active = fragment1;
                            tb.setText("Hoạt động sinh viên");
                        }
                        else
                        {
                            Intent login = new Intent(MainActivity.this,LoginActivity.class);
                            startActivityForResult(login,999);
                        }
                        return true;
                    case R.id.item2:
                        if (i!=0) {
                            fm.beginTransaction().hide(active).show(fragment2).commit();
                            active = fragment2;
                            tb.setText("Danh sách môn học");
                        }
                        else
                        {
                            Intent login = new Intent(MainActivity.this,LoginActivity.class);
                            startActivityForResult(login,999);
                        }
                        return true;
                    case R.id.item3:
                        fm.beginTransaction().hide(active).show(fragment3).commit();
                        active = fragment3;
                        badgeDrawable.setVisible(false);
                        tb.setText("Thông báo");
                        return true;
                    case R.id.item4:
                        if (i!=0) {
                            fm.beginTransaction().hide(active).show(fragment4).commit();
                            active = fragment4;
                            tb.setText("Thông tin sinh viên");
                        }
                        else
                        {
                            Intent login = new Intent(MainActivity.this,LoginActivity.class);
                            startActivityForResult(login,999);
                        }
                        return true;
                }
                return false;
            }
        });
        tb=(TextView) findViewById(R.id.txttb);
        tb.setText("Thông báo");
    }

    public void newLogedInFragments(FragmentManager fm){
        fm.beginTransaction().add(R.id.fct, fragment4, "4").hide(fragment4).commit();
        fm.beginTransaction().add(R.id.fct, fragment1, "1").hide(fragment1).commit();
        fm.beginTransaction().add(R.id.fct, fragment2, "2").hide(fragment2).commit();
    }
    public void newFragments(FragmentManager fm){
        fm.beginTransaction().add(R.id.fct,fragment3, "3").commit();
    }
    public void setCurrentFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = ((MainActivity)context).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fct, fragment).commitAllowingStateLoss();
    }

    public void login(View view) {
        Intent login = new Intent(MainActivity.this,LoginActivity.class);
        startActivityForResult(login,999);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==999 && resultCode==RESULT_OK) {
            tbus.setVisibility(View.INVISIBLE);
            i=1;
            newLogedInFragments(fm);
            bnavigation.setSelectedItemId(R.id.item4);
        }
    }
    public void logout(View view) {
        StartActivity.sEditor.remove("user");
        StartActivity.sEditor.remove("pass");
        StartActivity.sEditor.putInt("islogin",0);
        StartActivity.sEditor.commit();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
        tbus.setVisibility(View.VISIBLE);
        Toast.makeText(this,"Đăng xuất thành công!", Toast.LENGTH_SHORT).show();
    }
}