package cn.yuanfengshan.base.update.listener;


import cn.yuanfengshan.base.update.UpdateAppBean;

/**
 * version 1.0
 * Created by jiiiiiin on 2018/4/1.
 */

public interface IUpdateDialogFragmentListener {
    /**
     * 当默认的更新提示框被用户点击取消的时候调用
     * @param updateApp updateApp
     */
    void onUpdateNotifyDialogCancel(UpdateAppBean updateApp);
}
