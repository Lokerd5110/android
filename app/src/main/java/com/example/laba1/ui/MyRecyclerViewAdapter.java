package com.example.laba1.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import com.example.laba1.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends
        RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    private List<String> mDataset;
    private List<String> mDataset1;
    private List<String> mDataset2;
    private List<String> mDataset3;
    public List<String> dashboard = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public TextView mTextView1;

        public MyViewHolder(View v) {
            super(v);
            mTextView = v.findViewById(R.id.my_text_view);
            mTextView1 = v.findViewById(R.id.my_text_view1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("Click", "on item click");
                    String[] arg = new String[4];

                    int pos = getBindingAdapterPosition();
                    arg[0] = MyRecyclerViewAdapter.this.mDataset.get(pos);
                    arg[1] = MyRecyclerViewAdapter.this.mDataset1.get(pos);
                    arg[2] = MyRecyclerViewAdapter.this.mDataset2.get(pos);
                    arg[3] = MyRecyclerViewAdapter.this.mDataset3.get(pos);

                    Bundle bundle = new Bundle();
                    bundle.putStringArray("MyArg", arg);

                    Navigation.findNavController(itemView).navigate(R.id.from_list_to_inspect, bundle);
                }
            });
        }
    }
    public MyRecyclerViewAdapter(List<String> myDataset,List<String> myDataset1,List<String> myDataset2,List<String> myDataset3) {
        mDataset = myDataset;
        mDataset1 = myDataset1;
        mDataset2 = myDataset2;
        mDataset3 = myDataset3;
    }
    @Override
    public MyRecyclerViewAdapter.MyViewHolder
    onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mTextView.setText("Название: " + mDataset2.get(position));
        holder.mTextView1.setText("Автор: " + mDataset.get(position));
    }
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}