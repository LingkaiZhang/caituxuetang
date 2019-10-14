package com.yuanin.fuliclub.view;

import android.app.AlertDialog;
import android.content.Context;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.util.DensityUtil;


/**
 * @author huangxin
 * @date 2015/11/10
 * @desc 通用dialog
 */
public class GeneralDialog {
    private AlertDialog dialog;
    private View view;
    private TextView tvTips, tvTitle;
    private Button btnCancel, btnConfirm;
    private LinearLayout llMain;
    private View viewLineOne;


    public GeneralDialog(Context context, boolean isCancelable, String title, String tips, String leftStr, String rigthStr,
                         View.OnClickListener leftListener, View.OnClickListener rightListener) {
        view = LayoutInflater.from(context).inflate(R.layout.dialog_genaral, null);
        dialog = new AlertDialog.Builder(context).create();
        dialog.setCancelable(isCancelable);
        dialog.setView(new EditText(context));
        dialog.show();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER); // 非常重要：设置对话框弹出的位置
        window.setContentView(view);
        window.setWindowAnimations(R.style.BottomDialog); // 添加动画
        initViews(context);
        if (title.length() > 0) {
            tvTitle.setText(title);
        }  else {
            tvTitle.setVisibility(View.GONE);
            viewLineOne.setVisibility(View.GONE);
        }

        tvTips.setText(Html.fromHtml(tips));
        btnCancel.setText(leftStr);
        btnConfirm.setText(rigthStr);
        btnCancel.setOnClickListener(leftListener);
        btnConfirm.setOnClickListener(rightListener);
    }


    private void initViews(Context context) {
        tvTips = (TextView) view.findViewById(R.id.tvTips);
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        btnCancel = (Button) view.findViewById(R.id.btnCancel);
        btnConfirm = (Button) view.findViewById(R.id.btnConfirm);
        llMain = (LinearLayout) view.findViewById(R.id.llMain);
        viewLineOne = view.findViewById(R.id.view_line_one);
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) llMain.getLayoutParams();
        lp.width = StaticMembers.SCREEN_WIDTH - DensityUtil.dip2px(context,50);
        llMain.setLayoutParams(lp);
    }

    public void dismiss() {
        dialog.dismiss();
    }


}
