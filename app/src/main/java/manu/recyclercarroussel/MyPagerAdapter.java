package manu.recyclercarroussel;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
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

    public MyPagerAdapter(final ViewPager pager, Context context, int nbItems){
        this.context = context;
        this.nbItems = nbItems + 2;

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int pageCount = getCount();
                if (position == 0){
                    pager.setCurrentItem(pageCount-2,false);
                } else if (position == pageCount-1){
                    pager.setCurrentItem(1,false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
