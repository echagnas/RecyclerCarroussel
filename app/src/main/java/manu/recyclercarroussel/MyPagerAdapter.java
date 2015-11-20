package manu.recyclercarroussel;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by emmanuelchagnas on 20/11/15.
 */
public class MyPagerAdapter extends PagerAdapter{

    private Context context;
    private int nbItems;

    public MyPagerAdapter(Context context, int nbItems){
        this.context = context;
        this.nbItems = nbItems;
    }

    @Override
    public int getCount() {
        return nbItems;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {


        TextView textView = new TextView(context);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(30);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setText(String.valueOf(position));

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        if (position % 2 == 0) {
            layout.setBackgroundColor(context.getResources().getColor(R.color.colorRecyclerItem1));
        } else {
            layout.setBackgroundColor(context.getResources().getColor(R.color.colorRecyclerItem2));
        }
        layout.setLayoutParams(layoutParams);
        layout.addView(textView);

        container.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);

    }

}
