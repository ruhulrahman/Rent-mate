package com.anik.armr.rentmate.Activity;

import android.os.Bundle;

import com.anik.armr.rentmate.Fragments.Owner.OwnerHomeFragment;
import com.anik.armr.rentmate.Fragments.Owner.OwnerProfileFragment;
import com.anik.armr.rentmate.Fragments.Owner.OwnerRentHistoryFragment;
import com.anik.armr.rentmate.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;

public class OwnerMainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_main);

        init();
        replaceFragment(new OwnerHomeFragment());
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.owner_navigation_home:
                    replaceFragment(new OwnerHomeFragment());
                    return true;
                case R.id.owner_navigation_rent_history:
                    replaceFragment(new OwnerRentHistoryFragment());
                    return true;
                case R.id.owner_navigation_profile:
                    replaceFragment(new OwnerProfileFragment());
                    return true;
            }
            return false;
        }
    };

    private void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.owner_frame_layout,fragment);
        fragmentTransaction.commit();
    }

    private void init() {
        bottomNavigationView = findViewById(R.id.owner_nav_view);
        firebaseAuth = FirebaseAuth.getInstance();
    }

}
