package com.anik.armr.rentmate.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.anik.armr.rentmate.R;
import com.anik.armr.rentmate.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        binding.signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone = binding.phoneET.getText().toString().trim();
                startActivity(new Intent(LoginActivity.this, PhoneVerifyActivity.class).putExtra("phone", phone));
            }
        });
    }
}
