package com.anik.armr.rentmate.Activity;

import android.os.Bundle;

import com.anik.armr.rentmate.Fragments.Renter.RenterHomeFragment;
import com.anik.armr.rentmate.Fragments.Renter.RenterRentListFragment;
import com.anik.armr.rentmate.Fragments.Renter.RenterShortListFragment;
import com.anik.armr.rentmate.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;

public class RenterMainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rentar_main);

        init();
        replaceFragment(new RenterHomeFragment());
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.renter_navigation_home:
                    replaceFragment(new RenterHomeFragment());
                    return true;
                case R.id.renter_navigation_rent_list:
                    replaceFragment(new RenterRentListFragment());
                    return true;
                case R.id.renter_navigation_shortlist:
                    replaceFragment(new RenterShortListFragment());
                    return true;
            }
            return false;
        }
    };

    private void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.renter_frame_layout,fragment);
        fragmentTransaction.commit();
    }

    private void init() {
        bottomNavigationView = findViewById(R.id.renter_nav_view);
        firebaseAuth = FirebaseAuth.getInstance();
    }

}
