package com.qr.demo.Label;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import com.qr.demo.R;
import com.qr.print.PrintPP_CPCL;

/**
 * Created by chenlei on 2018/1/7.
 */

public class KeYunBaseLabel extends BaseLabel {


    private Context mContext;

    protected KeYunBaseLabel(PrintPP_CPCL iPrinter, Context mContext) {
        super(iPrinter);
        this.mContext = mContext;
    }

    @Override
    protected void printTop(int position) {

        iPrinter.drawText(topPadding + pageWidth / 2 - 11 * fontPoint2 / 2, position + 0, pageWidth, fontPoint2,
                "中国铁路沈阳局集团公司", 2, 0, 0, false, false);

        iPrinter.drawText(topPadding + pageWidth - 4 * fontPoint2 - 8, position + 0, pageWidth, fontPoint2,
                "客统—1", 2, 0, 0, false, false);

        int keyunLeft = pageWidth / 2 - 7 * fontPoint3 / 2;

        iPrinter.drawText(keyunLeft, position + topPadding + fontPoint2 + 8, pageWidth, fontPoint3,
                "客　运　记　录", 3, 0, 0, false, false);

        iPrinter.drawLine(2, keyunLeft, position + topPadding + fontPoint3 + fontPoint2 + 8 * 2, keyunLeft + 7 * fontPoint3, topPadding + fontPoint3 + fontPoint2 + 8 * 2, true);//第一联横线1
        iPrinter.drawLine(2, keyunLeft, position + topPadding + fontPoint3 + fontPoint2 + 8 * 3, keyunLeft + 7 * fontPoint3, topPadding + fontPoint3 + fontPoint2 + 8 * 3, true);//第一联横线1

        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.teilu);

        // 获得图片的宽高
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        // 设置想要的大小
        int newWidth = 100;
        int newHeight = 80;
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        // 得到新的图片
        Bitmap newbm = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix,
                true);

        iPrinter.drawGraphic(24, position + topPadding, newbm.getWidth(), newbm.getHeight(), newbm);

        printTopPading = topPadding + fontPoint3 + fontPoint2 + 8 * 4 - fontPoint2 + position;
    }

    @Override
    protected void printMiddle() {

    }

    @Override
    protected void pageSetup() {

    }
}
