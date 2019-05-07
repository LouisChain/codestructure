package daniel.codestructure.data.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MyVolley {
    public static final String TAG = MyVolley.class.getSimpleName();

    private RequestQueue requestQueue;

    public MyVolley(Context context) {
        this.requestQueue = Volley.newRequestQueue(context);
    }

    public <T> void addToRequestQueue(Request<T> request) {
        this.requestQueue.add(request);
    }
}
