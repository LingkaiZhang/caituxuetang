package com.yuanin.fuliclub.base;

import com.mvvm.base.AbsLifecycleFragment;
import com.mvvm.base.AbsViewModel;

/**
 * <p>类说明</p>
 *
 * @author lingkai  星期一 2019/10/14
 * @version :
 * @name :
 */
public class BaseNormalFragment<T extends AbsViewModel> extends AbsLifecycleFragment<T> {

    @Override
    public int getLayoutResId() {
        return 0;
    }

    @Override
    protected void lazyLoad() {
        getRemoteData();
    }
}
