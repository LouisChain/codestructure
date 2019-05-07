package daniel.codestructure.data.network;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.StringRequest;

import daniel.codestructure.util.Config;

public class MyStringRequest extends StringRequest {
    private static final String TAG = MyStringRequest.class.getSimpleName();

    public MyStringRequest(int method,
                           String url,
                           Response.Listener<String> listener,
                           Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    @Override
    public String getBodyContentType() {
        return "application/json; charset=utf-8";
    }

    @Override
    public Request<?> setRetryPolicy(RetryPolicy retryPolicy) {
        retryPolicy = new DefaultRetryPolicy(
            Config.Network.TIMEOUT,
            Config.Network.MAX_NUMBER_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        return super.setRetryPolicy(retryPolicy);
    }
}
