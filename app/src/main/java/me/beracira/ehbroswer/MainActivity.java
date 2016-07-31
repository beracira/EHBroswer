package me.beracira.ehbroswer;

import android.graphics.Color;
import android.media.tv.TvContentRating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);

//        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        String[] temp = new String[100];

//        for (int i = 0; i < 100; ++i) {
//            temp[i] = "http://csclub.uwaterloo.ca/~z283chen/" + Integer.toString(i % 10) + ".jpg";
//        }

        adapter = new MyAdapter(temp);
        recyclerView.setAdapter(adapter);

    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private String[] dataSet;

        public MyAdapter(String[] dataSet) {
            this.dataSet = dataSet;
        }

        @Override
        public int getItemCount() {
            return dataSet.length;
        }


        // Create new views (invoked by the layout manager)
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.my_text_view, parent, false);
            // set the view's size, margins, paddings and layout parameters
//            ...
            Log.d("debug", v.toString());
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
//            if (holder.textView != null)
//                holder.textView.setText(dataSet[position]);
            if (holder.imageView != null) {
                BitmapWorkerTask bitmapWorkerTask = new BitmapWorkerTask(holder.imageView);
                bitmapWorkerTask.execute(dataSet[position]);
            }
        }



        public class ViewHolder extends RecyclerView.ViewHolder {
            public CardView cardView;
            public TextView textView;
            public ImageView imageView;
            public ViewHolder(View v) {
                super(v);
                cardView = (CardView) v.findViewById(R.id.card_view);
                textView = (TextView) v.findViewById(R.id.tv);
                imageView = (ImageView) v.findViewById(R.id.iv);
            }
        }


    }

}
