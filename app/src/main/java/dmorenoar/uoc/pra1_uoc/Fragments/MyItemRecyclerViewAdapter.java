package dmorenoar.uoc.pra1_uoc.Fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import dmorenoar.uoc.pra1_uoc.Fragments.ItemFragment.OnListFragmentInteractionListener;
import dmorenoar.uoc.pra1_uoc.Models.Home;
import dmorenoar.uoc.pra1_uoc.R;

import java.util.List;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<Home> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyItemRecyclerViewAdapter(List<Home> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.cityName.setText(mValues.get(position).getCityName());
        holder.cityImage.setImageBitmap(mValues.get(position).getCityImage());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(mValues.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView cityName;
        public final ImageView cityImage;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            cityName = (TextView) view.findViewById(R.id.cityName);
            cityImage = (ImageView) view.findViewById(R.id.cityImage);
        }
    }
}
