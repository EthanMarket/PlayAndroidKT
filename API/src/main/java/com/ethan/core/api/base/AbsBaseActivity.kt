package com.ethan.core.api.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.ethan.core.api.ui.LoadingDialog
import com.ethan.core.api.R
import com.yanzhenjie.sofia.Sofia

/**
 * 抽象基类AbsBaseActivity
 */
open abstract class AbsBaseActivity : AppCompatActivity() {
    //控制状态栏颜色
    protected var isNeedColorStatusBar: Boolean = true
    private var mLoadingView: LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setContentViewId())
        if (isNeedColorStatusBar) {
            Sofia.with(this).statusBarLightFont().statusBarBackgroundAlpha(200)
                .statusBarBackground(getResources().getColor(R.color.colorPrimary));
        }
        initDecorView()
        processLogic()
    }

    protected open fun processLogic() {}


    protected fun showLoadingView() {
        mLoadingView = mLoadingView ?: LoadingDialog(this)
        mLoadingView?.setCanceledOnTouchOutside(false)
        mLoadingView?.show()
    }

    protected fun dismissLoadingView() {
        mLoadingView?.dismiss()
    }

    @LayoutRes
    abstract fun setContentViewId(): Int

    abstract fun initDecorView()

    override fun onDestroy() {
        super.onDestroy()
        dismissLoadingView()
    }

    protected fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }



//    XXPermissions.with(this)
//    //.constantRequest() //可设置被拒绝后继续申请，直到用户授权或者永久拒绝
//    .permission(Permission.Group.STORAGE,
//    arrayOf<String>(Permission.CAMERA),
//    arrayOf<String>(Permission.RECORD_AUDIO))
//    .request(
//    object : OnPermission {
//        override fun hasPermission(granted: List<String>, isAll: Boolean) {
//            if (isAll) {
//                showCloseOpenDialog()
//            } else {
//                //                            ToastUtils.show("获取权限成功，部分权限未正常授予");
//                finish()
//            }
//        }
//
//        override fun noPermission(denied: List<String>, quick: Boolean) {
//            if (quick) {
//                showToast("被永久拒绝授权，请手动授予权限")
//                //如果是被永久拒绝就跳转到应用权限系统设置页面
//                XXPermissions.gotoPermissionSettings(this@HomeActivity)
//            } else {
//                showToast("获取权限失败")
//                finish()
//            }
//        }
//    })
///*        checkPermission(new CheckPermListener() {
//            @Override
//            public void superPermission() {
//                showCloseOpenDialog();
//            }
//        }, R.string.home_permission_camera, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO);*/
//}
}