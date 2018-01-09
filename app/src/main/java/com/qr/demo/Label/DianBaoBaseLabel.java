package com.qr.demo.Label;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.qr.demo.R;
import com.qr.print.PrintPP_CPCL;

/**
 * Created by chenlei on 2018/1/7.
 */

public class DianBaoBaseLabel extends BaseLabel {


    private Context mContext;

    @Override
    protected void printMiddle() {

    }

    @Override
    protected void pageSetup() {

    }

    protected DianBaoBaseLabel(PrintPP_CPCL iPrinter, Context mContext) {
        super(iPrinter);
        this.mContext = mContext;
    }

    @Override
    protected void printTop() {

        iPrinter.drawText(topPadding + pageWidth / 2 - 11 * fontPoint2 / 2, 0, pageWidth, fontPoint2,
                "中国铁路沈阳局集团公司", 2, 0, 0, false, false);

        iPrinter.drawText(topPadding + pageWidth - 4 * fontPoint2 - 8, 0, pageWidth, fontPoint2,
                "客统—1", 2, 0, 0, false, false);

        int keyunLeft = pageWidth / 2 - 7 * fontPoint3 / 2;

        iPrinter.drawText(keyunLeft, topPadding + fontPoint2 + 8, pageWidth, fontPoint3,
                "列　车　电　报", 3, 0, 0, false, false);

        iPrinter.drawLine(2, keyunLeft, topPadding + fontPoint3 + fontPoint2 + 8 * 2, keyunLeft + 7 * fontPoint3, topPadding + fontPoint3 + fontPoint2 + 8 * 2, true);//第一联横线1
        iPrinter.drawLine(2, keyunLeft, topPadding + fontPoint3 + fontPoint2 + 8 * 3, keyunLeft + 7 * fontPoint3, topPadding + fontPoint3 + fontPoint2 + 8 * 3, true);//第一联横线1

        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.teilu);
        iPrinter.drawGraphic(0, topPadding, 80, 80, bitmap);

        printTopPading = topPadding + fontPoint3 + fontPoint2 + 8 * 4 - fontPoint2;

    }

}
