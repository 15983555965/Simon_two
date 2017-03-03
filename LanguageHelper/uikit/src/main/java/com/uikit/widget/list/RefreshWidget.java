package com.uikit.widget.list;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.uikit.R;

/**
 * Created by Administrator on 2017/3/1.
 */

public class RefreshWidget extends RelativeLayout {
    private int[] colors = {R.color.uikit_red, R.color.uikit_aaa, R.color.uikit_black_light, R.color.uikit_pulltorefresh_bg};

    private final Context mContext;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private boolean isCanResfresh;
    private OnRefreshOrLoadmoreListener listener;

    public RefreshWidget(Context context) {
        this(context, null);
    }

    public RefreshWidget(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
        initView();
    }

    private void init() {

    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_refresh_widget, this);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.view_swipe_refresh_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.view_recycler_view);

        initSwipeRefreshLayout();
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            private int lastVisibleItem=-1;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (isCanResfresh) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE
                            && lastVisibleItem + 1 == mRecyclerView.getAdapter().getItemCount() && lastVisibleItem != -1) {
                        setRefreshing(true);
                        if (listener!=null){
                            listener.onLoadMore();
                        }
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager =  recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager){
                    lastVisibleItem = ((LinearLayoutManager)layoutManager).findLastVisibleItemPosition();
                }else if (layoutManager instanceof StaggeredGridLayoutManager) {
                    StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                    int[] ints = new int[staggeredGridLayoutManager.getSpanCount()];
                    staggeredGridLayoutManager.findLastVisibleItemPositions(ints);
                    lastVisibleItem=getMin(ints);
                }
            }

            private int getMin(int[] ints) {
                if (ints==null||ints.length==0){
                    return -1;
                }
                int min=Integer.MAX_VALUE;
                for (int i=0;i<ints.length;i++){
                    int temp = ints[i];
                    if (min>temp){
                        min=temp;
                    }
                }
                return min;
            }
        });
    }

    private void initSwipeRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(colors);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (listener!=null){
                    listener.onRefresh();
                }
            }
        });
    }

    /**
     * // 这句话是为了，第一次进入页面的时候显示加载进度条
     */
    public void initFirstShowProgress() {
        mSwipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
    }

    private void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener onRefreshListener) {
        mSwipeRefreshLayout.setOnRefreshListener(onRefreshListener);
    }
    public void setCanRefresh(boolean isCanResfresh){
        mSwipeRefreshLayout.setRefreshing(false);
        mSwipeRefreshLayout.setEnabled(isCanResfresh);
        this.isCanResfresh=isCanResfresh;
    }
    public boolean isCanRefresh(){
        return isCanResfresh;
    }

    private void setRefreshing(boolean refreshing) {
        mSwipeRefreshLayout.setRefreshing(refreshing);
    }

    public RecyclerView getRecycleView(){
        return mRecyclerView;
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager){
        mRecyclerView.setLayoutManager(layoutManager);
    }
    public void setHasFixedSize(boolean hasFixedSize){
        mRecyclerView.setHasFixedSize(hasFixedSize);

    }

    public void setItemAnimator(RecyclerView.ItemAnimator animator){
        mRecyclerView.setItemAnimator(animator);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        mRecyclerView.setAdapter(adapter);
    }

    public void setOnRefreshOrLoadmoreListener(OnRefreshOrLoadmoreListener listener){
        this.listener=listener;
    }
    public interface OnRefreshOrLoadmoreListener{
        void onRefresh();
        void onLoadMore();
    }

    public void complete(){
        mSwipeRefreshLayout.setRefreshing(false);
    }
}