package com.example.loginsignupver1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private EditText password;
    private EditText fullname;
    private EditText email;
    private EditText phone;
    private EditText address;
    private EditText dob;
    private EditText gender;
    private Button register;
    private Button login;
    private TextView textView;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        password=findViewById(R.id.password);
        fullname=findViewById(R.id.fullname);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        address=findViewById(R.id.address);
        dob=findViewById(R.id.dob);
        gender=findViewById(R.id.gender);
        register=findViewById(R.id.register);
        login=findViewById(R.id.login);
        textView=findViewById(R.id.text_view);
        Gson gson=new GsonBuilder().serializeNulls().create();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://35.197.153.192:3000/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login() {
        String mEmailPhone=email.getText().toString();
        String mPass=password.getText().toString();
        User_Login user_login=new User_Login(mEmailPhone,mPass);
        Call<Login_Result> call=jsonPlaceHolderApi.login(user_login);
        call.enqueue(new Callback<Login_Result>() {
            @Override
            public void onResponse(Call<Login_Result> call, Response<Login_Result> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(MainActivity.this,"Deo thanh cong",Toast.LENGTH_SHORT).show();
                    return;
                }
                final String tokken=response.body().getToken();
                Toast.makeText(MainActivity.this,"thanh cong token: ",Toast.LENGTH_SHORT).show();
                Map<String, String> headers=new HashMap<>();
                headers.put("Authorization",tokken);
                Call<ListTour> call1=jsonPlaceHolderApi.getTour(140,1,"name",false,headers);
                call1.enqueue(new Callback<ListTour>() {
                    @Override
                    public void onResponse(Call<ListTour> call, Response<ListTour> response) {
                        if(!response.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this,"Deo thanh cong khi get tour",Toast.LENGTH_SHORT).show();
                        }
                        int total=response.body().getTotal();
                        List<Tour> tourList=response.body().getTourList();
                        for(Tour temp:tourList)
                        {
                            String content="";
                            content+="ID: "+temp.getId()+"\n";
                            content+="Name: "+temp.getName()+"\n\n";
                            textView.append(content);
                        }
                        Toast.makeText(MainActivity.this," thanh cong khi get tour,tokken: "+tokken+" so tour: "+total,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ListTour> call, Throwable t) {
                        Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });


            }

            @Override
            public void onFailure(Call<Login_Result> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void register() {
        String mPassword=password.getText().toString();
        String mFullname=fullname.getText().toString();
        String mEmail=email.getText().toString();
        String mPhone=phone.getText().toString();
        String mAddress=address.getText().toString();
        String mDob=dob.getText().toString();
        int mGender=Integer.parseInt(gender.getText().toString());

        User_Register user_register=new User_Register(mPassword,mFullname,mEmail,mPhone,mAddress,mDob,mGender);
        Call<User_Register> call=jsonPlaceHolderApi.register(user_register);
        call.enqueue(new Callback<User_Register>() {
            @Override
            public void onResponse(Call<User_Register> call, Response<User_Register> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(MainActivity.this,"Deo thanh cong",Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(MainActivity.this,"Thanh cong code: "+response.code(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User_Register> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
