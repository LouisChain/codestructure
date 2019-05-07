package daniel.codestructure.util;

import android.text.Html;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TextUtil {
    private static final String TAG = TextUtil.class.getSimpleName();

    public static String decodeString(String str) {
        try {
            String decodeStr = new String(
                str.getBytes("ISO-8859-1"),
                "UTF-8"
            );
            return Html.fromHtml(decodeStr).toString();
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "decodeString", e);
            return str;
        }
    }

    public static String convertDateToString(String dateString) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date = formatter.parse(dateString);
            DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy HH:mm aa");
            return dateFormat.format(date);
        } catch (Exception e) {
            Log.e(TAG, "convertDateToString", e);
            return dateString;
        }
    }
}
