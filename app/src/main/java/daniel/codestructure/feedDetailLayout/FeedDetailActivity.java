package daniel.codestructure.feedDetailLayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import butterknife.ButterKnife;
import daniel.codestructure.R;
import daniel.codestructure.baseMVP.Injection;
import daniel.codestructure.data.model.Feed;
import daniel.codestructure.util.ActivityUtils;
import daniel.codestructure.util.Config;
import daniel.codestructure.util.TextUtil;

public class FeedDetailActivity extends AppCompatActivity {
    private static final String TAG = FeedDetailActivity.class.getSimpleName();

    private FeedDetailFragment fragment;
    private FeedDetailContract.Presenter presenter;
    private Feed feed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_feed_detail);
        ButterKnife.bind(this);
        this.initData();
        this.drawComponentView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {
        this.feed = (Feed) super.getIntent().getSerializableExtra(Config.Extras.FEED);
    }

    private void drawComponentView() {
        super.setTitle(TextUtil.decodeString(this.feed.getTitle()));
        super.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.drawNewFeedsFragment();
        this.createPresenter();
    }

    private void drawNewFeedsFragment() {
        if (this.fragment == null) {
            this.fragment = FeedDetailFragment.getInstance();
        }
        ActivityUtils.replaceFragmentToActivity(
            super.getSupportFragmentManager(),
            this.fragment,
            R.id.feed_detail_container
        );
    }

    private void createPresenter() {
        this.presenter = new FeedDetailPresenter(
            this.fragment,
            Injection.provideFeedsRepository(super.getApplicationContext()),
            this.feed
        );
    }
}
