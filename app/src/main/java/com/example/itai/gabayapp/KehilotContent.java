package com.example.itai.gabayapp;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by itai on 29/10/16.
 */

public class KehilotContent {

    KehilaListChangeListener mListListener = null;

    Kehila activeKehila = null;

    public List<Kehila> kehilaList;

    private static KehilotContent mKehilotContent = null;

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    public void registerListener(KehilaListChangeListener listener) {
        mListListener = listener;
    }

    public KehilotContent() {
        kehilaList = new ArrayList<>();

        ValueEventListener kehilotListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap<String, Kehila> kehilaMap = (HashMap<String, Kehila>) dataSnapshot.getValue();
                for (DataSnapshot rawKehila : dataSnapshot.getChildren()) {
                    kehilaList.add(rawKehila.getValue(Kehila.class));
                }

                if(mListListener != null) {
                    mListListener.onKehilaListChange();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mDatabase.child("Kehilot").addListenerForSingleValueEvent(kehilotListener);
    }
    static public KehilotContent instance() {
        if (mKehilotContent == null) {
            mKehilotContent = new KehilotContent();
        }
        return mKehilotContent;
    }

    public enum KehilaType {
        YEMEN,
        ASHKENAZ,
        EDOT_MIZRAH
    }

    interface KehilaListChangeListener {
        void onKehilaListChange();
    }
    public static class Kehila {
        String mName;

        String mType;

        String key;

        public Kehila() {}

        public Kehila (String name, String type) {
            mName = name;
            mType = type;
        }
    }

    public void addKehila(Kehila kehila) {
        DatabaseReference ref = mDatabase.child("Kehilot").push();
        kehila.key = ref.getKey();

        mDatabase.child("Kehilot").child(ref.getKey()).setValue(kehila);
        //mDatabase.child()
    }
}
