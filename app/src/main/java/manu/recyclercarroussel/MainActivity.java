package manu.recyclercarroussel;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public static final int DELAY = 2000;
    public static final int VIEWPAGER_NB_ITEMS = 10;

    private ViewPager viewPager;
    private Handler mHandler;
    private boolean isScrolling = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //================> RECYCLER VIEW

        //Recycler view
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        //Layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        //Adapter
        MyRecyclerAdapter recyclerAdapter = new MyRecyclerAdapter(this);
        recyclerView.setAdapter(recyclerAdapter);

        //================> VIEW PAGER

        //View pager
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch(state){
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        //Log.d("VIEW PAGER", "State SCROLL_STATE_DRAGGING");
                        stopScrolling();
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        //Log.d("VIEW PAGER", "State SCROLL_STATE_IDLE");
                        startScrolling();
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING:
                        //Log.d("VIEW PAGER", "State SCROLL_STATE_SETTLING");
                        break;
                }
            }
        });

        //View pager adapter
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(viewPager, this, VIEWPAGER_NB_ITEMS);
        viewPager.setAdapter(pagerAdapter);

        //Handler to change page automatically
        mHandler = new Handler();
    }

    @Override
    protected void onResume() {
        super.onResume();

        startScrolling();
    }

    @Override
    protected void onPause() {
        super.onPause();

        stopScrolling();
    }

    //TIMER
    Runnable mRunnable = new Runnable()
    {
        private int currentPage = 0;

        @Override
        public void run()
        {
            currentPage = viewPager.getCurrentItem() + 1;
            viewPager.setCurrentItem(currentPage++, true);

            mHandler.postDelayed( mRunnable , DELAY );
        }
    };

    private void startScrolling(){
        if(!isScrolling) {
            isScrolling = true;
            //START TIMER
            mHandler.postDelayed(mRunnable, DELAY);
        }
    }

    private void stopScrolling(){
        if(isScrolling) {
            isScrolling = false;
            //STOP TIMER
            mHandler.removeCallbacks(mRunnable);
        }
    }
}
