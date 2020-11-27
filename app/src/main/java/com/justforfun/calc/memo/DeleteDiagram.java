package com.justforfun.calc.memo;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.justforfun.calc.R;

public class DeleteDiagram extends DialogFragment {
    private TextView tvTitle;
    private TextView tvDesc;
    private Button btnYes;
    private Button btnNo;
    private View contentView;
    private onItemClickListener onItemClickListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //对话框的布局
        contentView = super.onCreateView(inflater, container, savedInstanceState);
        if (contentView == null) {
            contentView = inflater.inflate(R.layout.dialog_delete_memo, container, false);
        }
        btnYes = contentView.findViewById(R.id.btn_yes);
        btnNo = contentView.findViewById(R.id.btn_no);
        tvTitle = contentView.findViewById(R.id.tv_title);
        tvDesc = contentView.findViewById(R.id.tv_tip);

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
        return contentView;
    }

    //监听事件接口
    public interface onItemClickListener {
        void onYesClick(View v);

        void onNoClick(View v);
    }

    public void setOnItemClickListener(DeleteDiagram.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setTip(String tip) {
        tvDesc.setText(tip);
    }
}
