package com.example.itai.gabayapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.itai.gabayapp.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link kehilaFragment.OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MykehilaRecyclerViewAdapter extends RecyclerView.Adapter<MykehilaRecyclerViewAdapter.KehilaViewHolder> implements KehilotContent.KehilaListChangeListener{

    private final List<KehilotContent.Kehila> mValues;
    private final kehilaFragment.OnListFragmentInteractionListener mListener;

    Context mContext;

    public MykehilaRecyclerViewAdapter(Context context, List<KehilotContent.Kehila> items, kehilaFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
        mContext = context;
        KehilotContent.instance().registerListener(this);
    }

    @Override
    public void onKehilaListChange() {
        notifyDataSetChanged();
    }

    @Override
    public KehilaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_kehila, parent, false);
        return new KehilaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final KehilaViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).mName);
        holder.mContentView.setText(mValues.get(position).mType);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class KehilaViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public KehilotContent.Kehila mItem;

        public KehilaViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
