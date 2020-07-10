package com.example.postswithrecyclerview;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.postswithrecyclerview.utils.CommonUtils;
import com.example.postswithrecyclerview.utils.DividerItemDecoration;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity implements SportAdapter.Callback {
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    SportAdapter mSportAdapter;
    LinearLayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setUp();
    }
    private void setUp() {
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        Drawable dividerDrawable = ContextCompat.getDrawable(this, R.drawable.divider_drawable);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(dividerDrawable));
        mSportAdapter = new SportAdapter(new ArrayList<Sport>());
        prepareDemoContent();
    }
    private void prepareDemoContent() {
        CommonUtils.showLoading(MainActivity.this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //prepare data and show loading
                CommonUtils.hideLoading();
                ArrayList<Sport> mSports = new ArrayList<>();
                String[] sportsList = MainActivity.this.getResources().getStringArray(R.array.sports_title);
                String[] sportsInfo = MainActivity.this.getResources().getStringArray(R.array.sports_info);
                String[] sportsImage = MainActivity.this.getResources().getStringArray(R.array.sports_images);
                for (int i = 0; i < sportsList.length; i++) {
                    mSports.add(new Sport(sportsImage[i], sportsInfo[i], "News", sportsList[i]));
                }
                mSportAdapter.addItems(mSports);
                mRecyclerView.setAdapter(mSportAdapter);
            }
        }, 2000);
    }
    @Override
    public void onEmptyViewRetryClick() {
        prepareDemoContent();
    }
}