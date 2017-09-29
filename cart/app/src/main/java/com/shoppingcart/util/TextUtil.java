package com.shoppingcart.util;

/**
 * Created by Gajanan on 28-09-2017.
 */

public class TextUtil {

    public static boolean isEmpty(String text) {
        if (text == null) {
            return true;
        }
        return text.isEmpty();
    }
}
