package com.uikit.adapter.recyclleradapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/6.
 */
public abstract class BaseAppRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {
    public Context mContext;
    private List<T> mList;

    public BaseAppRecyclerViewAdapter(Context context) {
        List<T> list = new ArrayList<T>();
        mContext = context;
        mList = list;
    }

    public BaseAppRecyclerViewAdapter(Context context, List<T> list) {
        mContext = context;
        mList = list;
    }

    /**
     * 增加Item
     *
     * @param t
     */
    public void add(int index, T t) {
        mList.add(index, t);
        notifyDataSetChanged();
    }

    /**
     * 替换Item
     *
     * @param t
     */
    public void replace(int index, T t) {
        mList.set(index, t);
        notifyDataSetChanged();
    }

    /**
     * 增加Item
     *
     * @param t
     */
    public void add(T t) {
        mList.add(t);
        notifyDataSetChanged();
    }

    /**
     * 增加List、
     *
     * @param list
     */
    public void addAll(List<T> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * 移除item
     *
     * @param t
     * @return
     */
    public boolean remove(T t) {
        boolean remove = mList.remove(t);
        notifyDataSetChanged();
        return remove;
    }

    /**
     * 移除list
     *
     * @param list
     * @return
     */
    public boolean removeAll(List<T> list) {
        boolean b = mList.removeAll(list);
        notifyDataSetChanged();
        return b;
    }

    /**
     * 获取List
     *
     * @return
     */
    public List<T> getList() {
        return mList;
    }

    /**
     * 设置List
     *
     * @param list
     */
    public void setList(List<T> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * 刷新数据
     *
     * @param list
     */
    public void refresh(List<T> list) {
        setList(list);
    }

    /**
     * 加载更多
     *
     * @param list
     */
    public void loadMore(List<T> list) {
        addAll(list);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseRecyclerViewHolder(createItemView(mContext, viewType));
    }


    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        onBindView(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    protected abstract View createItemView(Context mContext, int viewType);

    protected abstract void onBindView(View itemView, int position);

}
