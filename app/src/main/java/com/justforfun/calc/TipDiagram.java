package com.justforfun.calc;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class TipDiagram extends DialogFragment {
    private TextView tvTip;
    private Button btnYes;
    private Button btnNo;
    private View mRootView;
    private onItemClickListener onItemClickListener;

    private void avoidHintColor(View view) {
        if (view instanceof TextView)
            ((TextView) view).setHighlightColor(getResources().getColor(android.R.color.transparent));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //对话框的布局
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.dialog_user_tip, container, false);
        }
        tvTip = mRootView.findViewById(R.id.tv_tip);
        btnYes = mRootView.findViewById(R.id.btn_yes);
        btnNo = mRootView.findViewById(R.id.btn_no);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                ((TextView) widget).setHighlightColor(getResources().getColor(android.R.color.transparent));
                UserPrivacyActivity.actionStart(getActivity());
            }

            //去除连接下划线
            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(0xFFFF6100);
                ds.setUnderlineText(false);
                ds.clearShadowLayer();
            }
        };
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                ((TextView) widget).setHighlightColor(getResources().getColor(android.R.color.transparent));
                PrivacyActivity.actionStart(getActivity());
            }

            //去除连接下划线
            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(0xFFFF6100);
                ds.setUnderlineText(false);
                ds.clearShadowLayer();
            }
        };
        SpannableString spannableString = new SpannableString(getResources().getText(R.string.dialog_tip));
        spannableString.setSpan(clickableSpan, 35, 41, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(clickableSpan1, 42, 48, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvTip.setText(spannableString);
        tvTip.setMovementMethod(LinkMovementMethod.getInstance());

        //绑定监听事件
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //在activity里面处理点击事件
                onItemClickListener.onYesClick(v);
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //在activity里面处理点击事件
                onItemClickListener.onNoClick(v);
            }
        });
        return mRootView;
    }

    //监听事件接口
    public interface onItemClickListener {
        void onYesClick(View v);

        void onNoClick(View v);
    }

    public void setOnItemClickListener(TipDiagram.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
