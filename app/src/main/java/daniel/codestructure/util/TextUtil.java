package daniel.codestructure.util;

import android.text.Html;
import android.util.Log;

import java.io.UnsupportedEncodingException;

public class TextUtil {
    private static final String TAG = TextUtil.class.getSimpleName();

    public static String decodeString(String str) {
        try {
            String decodeStr = new String(str.getBytes("ISO-8859-1"), "UTF-8");
            return Html.fromHtml(decodeStr).toString();
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "decodeString", e);
            return str;
        }
    }
}
