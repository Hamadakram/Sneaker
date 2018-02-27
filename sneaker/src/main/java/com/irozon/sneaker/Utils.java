package com.irozon.sneaker;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.Window;

/**
 * Created by hammad.akram on 2/27/18.
 */

class Utils {

    /**
     * Returns status bar height.
     *
     * @return
     */
    static int getStatusBarHeight(Activity activity) {
        Rect rectangle = new Rect();
        Window window = activity.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
        int statusBarHeight = rectangle.top;
        int contentViewTop = window.findViewById(Window.ID_ANDROID_CONTENT).getTop();
        int titleBarHeight = contentViewTop - statusBarHeight;

        return statusBarHeight;
    }

    static int convertToDp(Context context, float sizeInDp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (sizeInDp * scale + 0.5f);
    }

    static void customView(Context context, View v, int backgroundColor, int cornerRadius) {
        int radiusInDP = convertToDp(context, cornerRadius);
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadii(new float[] { radiusInDP, radiusInDP, radiusInDP, radiusInDP, radiusInDP, radiusInDP, radiusInDP, radiusInDP });
        shape.setColor(backgroundColor);
        v.setBackground(shape);
    }
}
