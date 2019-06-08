package com.example.finalassesment;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        //For updating the hamburger icon
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.nav_calories:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new GoalsFragment()).commit();
                break;
            case R.id.nav_notes:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NotesFragment()).commit();
                break;
            case R.id.nav_personal_goals:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new GoalsFragment()).commit();
                break;
            case R.id.nav_sports:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SportsFragment()).commit();
                break;
            case R.id.nav_spotify:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new GoalsFragment()).commit();
                break;
            case R.id.nav_today_schedule:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new GoalsFragment()).commit();
                break;
            case R.id.nav_weekly_schedule:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new GoalsFragment()).commit();
                break;


        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        //gravitycompat.END als de drawer aan de rechterkant is
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
