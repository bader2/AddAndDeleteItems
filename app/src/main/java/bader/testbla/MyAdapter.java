package bader.testbla;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.FooViewHolder> {
    private ArrayList<FooObject> mDataset;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class FooViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CardView mCard;
        public TextView topText;
        public Button delete;
        public FooViewHolder(CardView v) {
            super(v);
            mCard = v;
            topText = (TextView) v.findViewById(R.id.top_text);
            delete = (Button) v.findViewById(R.id.delete);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<FooObject> myDataset) {
        mDataset = myDataset;
    }

    private int i;

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.FooViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        final View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.foo_card, parent, false);
        ((TextView) v.findViewById(R.id.bottom_text)).setText("Made By FooFactory " + i);
        i++;
        FooViewHolder vh = new FooViewHolder((CardView) v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(FooViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.topText.setText(mDataset.get(position).name);
        holder.delete.setTag(holder);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = ((FooViewHolder) view.getTag()).getAdapterPosition();
                mDataset.remove(pos);
                notifyItemRemoved(pos);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void add(FooObject foo) {
        mDataset.add(foo);
        notifyItemInserted(mDataset.size() - 1);
    }
}