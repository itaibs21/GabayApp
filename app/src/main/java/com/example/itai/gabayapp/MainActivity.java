package com.example.itai.gabayapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolBar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolBar);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actions, menu);
        return true;
    }


    public void launchProperActivity() {
        mUser = mAuth.getCurrentUser();

        if(mUser == null) {
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
        }

        if(KehilotContent.instance().activeKehila == null) {
            Intent kehilaIntent = new Intent(this, ChooseKehila.class);
            startActivity(kehilaIntent);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        launchProperActivity();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id== R.id.logout) {
            mAuth.signOut();
            recreate();
        }
        return true;
    }
}
