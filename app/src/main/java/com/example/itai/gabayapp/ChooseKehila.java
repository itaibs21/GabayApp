package com.example.itai.gabayapp;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.itai.gabayapp.dummy.DummyContent;

import layout.AddKehilaFragment;

public class ChooseKehila extends AppCompatActivity implements kehilaFragment.OnListFragmentInteractionListener, AddKehilaFragment.OnAddKehilaListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_kehila);

        getSupportFragmentManager().beginTransaction().add(R.id.fragment2, kehilaFragment.newInstance(25)).commit();


    }

    public void onListFragmentInteraction(KehilotContent.Kehila item) {
        KehilotContent.instance().activeKehila = item;
        finish();
    }

    @Override
    public void addKehilaItem() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment2, AddKehilaFragment.newInstance("", "")).commit();
    }

    @Override
    public void addKehilaDone() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment2, kehilaFragment.newInstance(25)).commit();
    }
}
