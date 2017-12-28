package com.qr.demo.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * toast工具
 */
public class ToastUtils {
    public static Toast sToast;
    public static Toast sCenterToast;

    public static void show(Context context, String msg) {
        if (context == null) {
            return;
        }
        if (sToast != null) {
            sToast.cancel();
        }
        sToast = Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT);
        sToast.show();
    }

    public static void show(Context context, String msg, int xOffset, int yOffset) {
        if (context == null) {
            return;
        }
        if (sToast != null) {
            sToast.cancel();
        }
        sToast = Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT);
        sToast.setGravity(sToast.getGravity(),
                sToast.getXOffset() + xOffset,
                sToast.getYOffset() + yOffset);
        sToast.show();
    }

    public static void show(Context context, int resID) {
        if (context == null) {
            return;
        }
        if (sToast != null) {
            sToast.cancel();
        }
        Context ctx = context.getApplicationContext();
        sToast = Toast.makeText(ctx, ctx.getString(resID), Toast.LENGTH_SHORT);
        sToast.show();
    }

    public static void show(Context context, String msg, int duration) {
        if (context == null) {
            return;
        }
        if (sToast != null) {
            sToast.cancel();
        }
        sToast = Toast.makeText(context.getApplicationContext(), msg, duration);
        sToast.show();
    }

    public static void showCenter(Context context, int resID) {
        if (context == null) {
            return;
        }
        if (sCenterToast != null) {
            sCenterToast.cancel();
        }
        sCenterToast = Toast.makeText(context.getApplicationContext(), resID, Toast.LENGTH_SHORT);
        sCenterToast.setGravity(Gravity.CENTER, 0, 0);
        sCenterToast.show();
    }

    public static void showCenter(Context context, String msg) {
        if (context == null) {
            return;
        }
        if (sCenterToast != null) {
            sCenterToast.cancel();
        }
        sCenterToast = Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT);
        sCenterToast.setGravity(Gravity.CENTER, 0, 0);
        sCenterToast.show();
    }

}
