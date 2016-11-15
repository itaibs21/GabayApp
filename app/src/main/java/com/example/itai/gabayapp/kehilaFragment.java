package com.example.itai.gabayapp;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.itai.gabayapp.dummy.DummyContent;
import com.example.itai.gabayapp.dummy.DummyContent.DummyItem;

import java.util.List;

import layout.AddKehilaFragment;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class kehilaFragment extends Fragment {

    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public kehilaFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static kehilaFragment newInstance(int columnCount) {
        kehilaFragment fragment = new kehilaFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kehila_list, container, false);
        RecyclerView recView = (RecyclerView) view.findViewById(R.id.kehilaListView);

        // Set the adapter

        Context context = recView.getContext();

        recView.setLayoutManager(new LinearLayoutManager(context));

        MykehilaRecyclerViewAdapter adapter = new MykehilaRecyclerViewAdapter(getContext(), KehilotContent.instance().kehilaList, mListener);
        recView.setAdapter(adapter);
        //adapter.notifyDataSetChanged();

        FloatingActionButton button = (FloatingActionButton) view.findViewById(R.id.floatingActionButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.addKehilaItem();

            }
        });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(KehilotContent.Kehila item);

        void addKehilaItem();
    }
}
