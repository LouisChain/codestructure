package daniel.codestructure.feedDetailLayout;

import android.util.Log;

import daniel.codestructure.data.listener.OnGetFeedDetailListener;
import daniel.codestructure.data.model.Feed;
import daniel.codestructure.data.repository.FeedsRepository;

public class FeedDetailPresenter implements FeedDetailContract.Presenter {
    private static final String TAG = FeedDetailPresenter.class.getSimpleName();

    private FeedDetailContract.View view;
    private FeedsRepository repository;
    private Feed feed;

    public FeedDetailPresenter(FeedDetailContract.View view,
                               FeedsRepository repository,
                               Feed feed) {
        this.view = view;
        this.repository = repository;
        this.view.setPresenter(this);
        this.feed = feed;
    }

    @Override
    public void start() {
        this.view.showLoadingDialog();
        this.repository.getFeedDetail(
            new OnGetFeedDetailListener() {
                @Override
                public void onSuccess(Feed result) {
                    view.hideLoadingDialog();
                    feed = result;
                    view.drawFeedDetail(feed);
                }

                @Override
                public void onFailed(Throwable error) {
                    view.hideLoadingDialog();
                    view.showError();
                    Log.e(TAG, "start", error);
                }
            }
        );
    }
}
