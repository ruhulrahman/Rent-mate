package com.anik.armr.rentmate.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.anik.armr.rentmate.R;
import com.anik.armr.rentmate.databinding.ActivityPhoneVerifyBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

import es.dmoral.toasty.Toasty;

public class PhoneVerifyActivity extends AppCompatActivity {
    private ActivityPhoneVerifyBinding binding;
    private String code;
    private String mVerificationId;
    private String mResendToken;
    private String phoneNumber;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_phone_verify);

        phoneNumber = getIntent().getStringExtra("phone");

        binding.verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                code = binding.verificationET.getText().toString().trim();
                if(code.length() == 6){
                    signInWithPhoneAuthCredential(code);
                }
            }
        });

        sendOTP();

    }

    private void sendOTP() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+88"+phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential credential) {
            String code = credential.getSmsCode();
            if(code != null){
                binding.verificationET.setText(code);
                signInWithPhoneAuthCredential(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toasty.error(PhoneVerifyActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCodeSent(@NonNull String verificationId,
                @NonNull PhoneAuthProvider.ForceResendingToken token) {

            // Save verification ID and resending token so we can use them later
            mVerificationId = verificationId;
            //mResendToken = token;
        }
    };



    private void signInWithPhoneAuthCredential(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = task.getResult().getUser();
                    Intent intent = new Intent(PhoneVerifyActivity.this, MainActivity.class);
                    intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });
    }
}
