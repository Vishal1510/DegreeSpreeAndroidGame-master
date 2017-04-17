package com.jvn.degreespree.Utils;


/**
 * Created by vishal on 10/10/15.
 */
public class ScreenUtils {
    private static String TAG = "ScreenUtils";
    private static float logicalDensity = 1;

    /*
    Converts pixels to density pixels to make view work on multiple screen sizes.
     */
    public static int convertToDP(int px) {
        int converted = (int) Math.ceil(px*logicalDensity);
        return converted;
    }

    public static void setConversionRate(float density) {
        logicalDensity = density;
    }
}
