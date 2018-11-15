package com.tourye.zhong.views.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tourye.zhong.R;

/**
 * Created by longlongren on 2018/8/21.
 * <p>
 * introduce:修改姓名弹框
 */

public class ModifyNameDialog extends Dialog implements View.OnClickListener {

    private final Context mContext;
    private ImageView mImgDialogModifyClose;
    private TextView mTvDialogModifyTitle;
    private EditText mEdtDialogModifyName;
    private TextView mTvDialogModifySave;
    private RenameCallback mRenameCallback;

    public void setRenameCallback(RenameCallback renameCallback) {
        mRenameCallback = renameCallback;
    }

    public ModifyNameDialog(@NonNull Context context) {
        super(context);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setContentView(R.layout.dialog_modify_name);

        mContext = context;

        mImgDialogModifyClose = (ImageView) findViewById(R.id.img_dialog_modify_close);
        mTvDialogModifyTitle = (TextView) findViewById(R.id.tv_dialog_modify_title);
        mEdtDialogModifyName = (EditText) findViewById(R.id.edt_dialog_modify_name);
        mTvDialogModifySave = (TextView) findViewById(R.id.tv_dialog_modify_save);
        mImgDialogModifyClose.setOnClickListener(this);
        mTvDialogModifySave.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_dialog_modify_close:
                dismiss();
                break;
            case R.id.tv_dialog_modify_save:
                String name = mEdtDialogModifyName.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(mContext, "昵称不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                mRenameCallback.rename(name);
                dismiss();
                break;
        }
    }

    public interface RenameCallback{
        public void rename(String name);
    }

}
