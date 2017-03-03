package com.uikit.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import android.widget.BaseAdapter;


/**
 * Created by Administrator on 2017/1/19.
 */

public abstract class BaseAppAdapter<T> extends BaseAdapter {
    public Context mContext;
    private List<T> mList;

    public BaseAppAdapter(Context context) {
        mList = new ArrayList<>();
        mContext = context;
    }

    public BaseAppAdapter(Context context, List<T> list) {
        mContext = context;
        mList = list;
    }

    public void add(T t) {
        add(-1, t);
    }

    /**
     * 增加Item
     *
     * @param t
     */
    public void add(int index, T t) {
        if (index >= 0) {
            mList.add(index, t);
        } else {
            mList.add(t);
        }
        notifyDataSetChanged();
    }

    /**
     * 替换Item
     *
     * @param t
     */
    public void replace(int index, T t) {
        if (index >= 0) {
            mList.set(index, t);
        }
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
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        if (mList.size() > position) {
            return mList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = createItemView(mContext);
        }
        onBindView(convertView, position);
        return convertView;
    }


    protected abstract View createItemView(Context mContext);

    protected abstract void onBindView(View itemView, int position);


}
