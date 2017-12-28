package com.qr.demo.adapter;

import android.content.Context;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.qr.demo.R;
import com.qr.demo.common.CommonLeftTextRightIconModel;
import com.qr.demo.common.CommonTextEditTextModel;
import com.qr.demo.view.NewTitleView;
import com.qr.demo.view.TitleEditAlignLeftView;

import java.util.List;

public class ContractNewCommonAdapter extends BaseAdapter {

    private Context context;

    private List<CommonModel> models;

    public ContractNewCommonAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return models == null ? 0 : models.size();
    }

    @Override
    public Object getItem(int i) {
        return models.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View contentView, ViewGroup viewGroup) {

        MyViewHodler holder = null;
        CommonModel model = models.get(i);
        holder = new MyViewHodler();
        contentView = onCreateViewHolder(model.type, holder);
        holder = (MyViewHodler) contentView.getTag();

        onBindViewHolder(model.type, holder, model, i);

        return contentView;
    }

    private View onCreateViewHolder(String type,
                                    MyViewHodler holder) {
        View contentView;

        if (type.equals(CommonModel.TYPE_TEXT_ARROW)) {//带箭头的item
            contentView = View.inflate(context, R.layout.item_new_common_text_arrow, null);
            holder.titleView = contentView.findViewById(R.id.title);
            holder.type = type;
        } else if (type.equals(CommonModel.TYPE_TEXT_EDITTEXT)) {  //带输入框的item
            contentView = View.inflate(context, R.layout.item_new_common_text_edittext, null);
            holder.textEdit = contentView.findViewById(R.id.text_edit);
            holder.type = type;
        } else if (type.equals(CommonModel.TYPE_BUTTON)) {  //button
            contentView = View.inflate(context, R.layout.item_new_common_button, null);
            holder.button = contentView.findViewById(R.id.button);
            holder.type = type;
        } else {
            contentView = View.inflate(context, R.layout.null_layout, null);
        }
        contentView.setTag(holder);

        return contentView;
    }

    private void onBindViewHolder(String type, MyViewHodler holder,
                                  final CommonModel m, final int position) {

        if (type.equals(CommonModel.TYPE_TEXT_EDITTEXT)) {
            createTxtEditView(holder, m, position);
        } else if (type.equals(CommonModel.TYPE_TEXT_ARROW)) {
            createTxtArrowView(holder, m, position);
        } else if (type.equals(CommonModel.TYPE_BUTTON)) {
            createButtonView(holder, m, position);
        } else {

        }
    }

    private void createButtonView(MyViewHodler holder, final CommonModel m, final int position) {

        holder.button.setText(m.title);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onclick(position, m);
                }
            }
        });
    }

//    private void createLineView(final MyViewHodler holder, final CommonModel m) {
//
//        CommonLineModel model = m.getLineModel();
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT);
//        params.height = model.lineHeight;
//        holder.line.setLayoutParams(params);
//        holder.line.setBackgroundResource(model.lineColor);
//    }


    private void createTxtArrowView(MyViewHodler holder,
                                    final CommonModel m, final int position) {
        holder.titleView.setTitle(m.title);
        holder.titleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onclick(position, m);
                }
            }
        });
        if (m.showArrow) {
            holder.titleView.showArrow();
        } else {
            holder.titleView.hindArrow();
        }

        if (!TextUtils.isEmpty(m.getDiscrption())) {
            holder.titleView.setDiscrption(m.getDiscrption());
        } else {
            holder.titleView.setDiscrption("");
        }
    }

    private void createTxtEditView(final MyViewHodler holder,
                                   final CommonModel m, final int position) {
        holder.textEdit.setTitle(m.title);
        final CommonTextEditTextModel editTextModel = m.getEditTextModel();

        if (editTextModel.getMinLines() > 0) {
            holder.textEdit.getEditText().setMinLines(editTextModel.getMinLines());
            holder.textEdit.getEditText().setSingleLine(false);
        } else {
            holder.textEdit.getEditText().setLines(1);
            holder.textEdit.getEditText().setSingleLine(true);
            holder.textEdit.getEditText().setEllipsize(TextUtils.TruncateAt.END);
        }

        //设置输入类型为数字
        if (editTextModel.inputType.equals(CommonTextEditTextModel.INPUT_TYPE_NUM)) {
            holder.textEdit.getEditText().setInputType(InputType.TYPE_CLASS_NUMBER);

            holder.rightIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if (!TextUtils.isEmpty(editTextModel.editTextStr)) {
//                        CallPhoneDialog callPhoneDialog = new CallPhoneDialog(context,
//                                editTextModel.editTextStr);
//                        callPhoneDialog.tipShow(null);
//                    }
                }
            });
        }

        holder.textEdit
                .setGetEditTxtListener(new TitleEditAlignLeftView.GetEditTxtListener() {
                    @Override
                    public void callBackTxt(String str) {
                        CommonTextEditTextModel textEditTextModel = m.getEditTextModel();
                        if (textEditTextModel != null) {
                            textEditTextModel.editTextStr = str;
                        }

                        if (editTextModel.inputType.equals(CommonTextEditTextModel.INPUT_TYPE_NUM)) {
                            holder.rightIcon.setSelected(!TextUtils.isEmpty(str));
                        }
                    }
                });

        if (!TextUtils.isEmpty(m.getEditTextModel().editTextStr)) {
            holder.textEdit.setText(m.getEditTextModel().editTextStr);
        } else {
            if (!TextUtils.isEmpty(m.getEditTextModel().editTextHide)) {
                holder.textEdit.setHint(m.getEditTextModel().editTextHide);
            } else {
                holder.textEdit.setHint("请输入");
            }
        }
    }

    public void setDatas(List<CommonModel> contractOriginalModels) {
        this.models = contractOriginalModels;
    }

    public class MyViewHodler {
        String type;
        TextView title;
        TextView button;
        ImageView rightIcon;
        TitleEditAlignLeftView textEdit;
        NewTitleView titleView;
    }

    CommonListener listener;

    public void setListener(CommonListener listener) {
        this.listener = listener;
    }

    public interface CommonListener {
        public void onclick(int position, CommonModel model);
    }
}
