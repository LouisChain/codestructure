package daniel.codestructure.data.remote;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import daniel.codestructure.data.listener.OnGetFeedDetailListener;
import daniel.codestructure.data.listener.OnGetNewFeedsListener;
import daniel.codestructure.data.model.Feed;
import daniel.codestructure.data.network.MyStringRequest;
import daniel.codestructure.data.network.MyVolley;

public class FeedsRemoteDataSource {
    private static final String TAG = FeedsRemoteDataSource.class.getSimpleName();
    private static FeedsRemoteDataSource INSTANCE;

    private MyVolley volley;

    public static FeedsRemoteDataSource getInstance(MyVolley volley) {
        if (INSTANCE == null) {
            INSTANCE = new FeedsRemoteDataSource(volley);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public FeedsRemoteDataSource(MyVolley volley) {
        this.volley = volley;
    }

    public void getNewFeeds(final OnGetNewFeedsListener listener) {
        final String URL = "https://www.dropbox.com/s/fy6ny7syutxl1yd/newsfeed.json?dl=1";
        MyStringRequest request = new MyStringRequest(
            StringRequest.Method.GET,
            URL,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        List<Feed> feeds = parseFeeds(response);
                        listener.onSuccess(feeds);
                    } catch (Exception e) {
                        listener.onFailed(e);
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    listener.onFailed(error);
                }
            }
        );
        this.volley.addToRequestQueue(request);
    }

    public void getFeedDetail(final OnGetFeedDetailListener listener) {
        final String URL = "https://www.dropbox.com/s/v83n38kvsm6qw62/detail.json?dl=1";
        MyStringRequest request = new MyStringRequest(
            StringRequest.Method.GET,
            URL,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        Feed feed = parseFeed(response);
                        listener.onSuccess(feed);
                    } catch (Exception e) {
                        listener.onFailed(e);
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    listener.onFailed(error);
                }
            }
        );
        this.volley.addToRequestQueue(request);
    }

    private List<Feed> parseFeeds(String response) throws Exception {
        List<Feed> feeds = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(response);
        JSONArray jsonArray = jsonObject.getJSONArray("items");
        for (int i = 0; i < jsonArray.length(); i++) {
            Feed feed = this.parseFeed(jsonArray.getString(i));
            feeds.add(feed);
        }
        return feeds;
    }

    private Feed parseFeed(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        Feed feed = mapper.readValue(json, Feed.class);
        return feed;
    }
}
