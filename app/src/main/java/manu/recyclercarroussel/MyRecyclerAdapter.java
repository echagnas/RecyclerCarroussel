package manu.recyclercarroussel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by emmanuelchagnas on 20/11/15.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private Context context;

    public MyRecyclerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);

        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position % 2 == 0) {
            holder.layout.setBackgroundColor(context.getResources().getColor(R.color.colorRecyclerItem1));
        } else {
            holder.layout.setBackgroundColor(context.getResources().getColor(R.color.colorRecyclerItem2));
        }
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    /**
     * View holder
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        private View layout;

        public ViewHolder(View layout) {
            super(layout);

            this.layout = layout;
        }
    }
}
