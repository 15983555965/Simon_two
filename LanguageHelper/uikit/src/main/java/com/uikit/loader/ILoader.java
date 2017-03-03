package com.uikit.loader;

/**数据加载接口
 * Created by Administrator on 2017/1/19.
 */

public interface ILoader {
    void onRefresh();

    void onLoadMore();

    void onFinish();
}
