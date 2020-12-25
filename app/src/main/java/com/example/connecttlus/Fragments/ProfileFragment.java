package com.example.connecttlus.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.connecttlus.Object.User;
import com.example.connecttlus.R;
import com.example.connecttlus.Utils.ApiClient;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static User user;
    public static String idUser;
    public static String userName;
    public static String detailName;
    public static String userPhone;
    public static String idStudent;
    public static String course;
    public static String lop;
    public static String urlavatar="https://viennhagroup.com/file/";
    public static String userAddress;
    public static String userEmail;
    public static String userSex="0";
    public static String dob;
    ImageView imageView;
    Button button, a1;
    Uri imageData;
    TextView txtid,txtname,txtaddress,txtphone,txtcourse,txtlop,txtiduser,txtsex,txtemail,txtdob;
    CircleImageView profile_image;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        anhXa(view);
        idUser = user.getIduser();
        userName = user.getUsername();
        userPhone = user.getPhone();
        idStudent = user.getIdstudent();
        course = user.getCourse();
        lop = user.getLop();
        userAddress = user.getAddress();
        userEmail = user.getEmail();
        userSex = user.getSex();
        if (user.getDateofbirth().length()>10)
            dob = user.getDateofbirth().substring(0,10);
        else
            dob = user.getDateofbirth();
        detailName = user.getDetailname();
        txtid.setText(userName);
        txtname.setText(detailName);
        txtlop.setText(lop);
        txtcourse.setText(course);
        txtphone.setText(userPhone);
        if (user.getAvartar().length()>0)
            ProfileFragment.urlavatar = "https://viennhagroup.com/file/"+user.getAvartar();
        if (userSex.equals("0"))
            txtsex.setText("Nữ");
        else
            txtsex.setText("Nam");
        txtemail.setText(userEmail);
        txtdob.setText(dob);
        txtaddress.setText(userAddress);
        if (!urlavatar.equals("https://viennhagroup.com/file/"))
            Picasso.get().load(urlavatar).into(profile_image);
        return view;
    }

    public void anhXa(View view){
        imageView = view.findViewById(R.id.profile_image);
        txtid = view.findViewById(R.id.txtid);
        txtname = view.findViewById(R.id.txtname);
        txtlop = view.findViewById(R.id.txtclass);
        txtcourse = view.findViewById(R.id.txtkhoas);
        txtphone = view.findViewById(R.id.txtphone);
        txtaddress = view.findViewById(R.id.txtaddress);
        txtsex = view.findViewById(R.id.txtsex);
        txtemail = view.findViewById(R.id.txtemail);
        txtdob = view.findViewById(R.id.txtdob);
        profile_image = view.findViewById(R.id.profile_image);

    }

}