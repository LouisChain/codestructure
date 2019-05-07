package daniel.codestructure.newFeedsLayout;

import android.util.Log;

import java.util.List;

import daniel.codestructure.data.listener.OnGetNewFeedsListener;
import daniel.codestructure.data.model.Feed;
import daniel.codestructure.data.repository.FeedsRepository;

public class NewFeedsPresenter implements NewFeedsContract.Presenter {
    private static final String TAG = NewFeedsPresenter.class.getSimpleName();

    private NewFeedsContract.View view;
    private FeedsRepository repository;

    public NewFeedsPresenter(NewFeedsContract.View view, FeedsRepository repository) {
        this.view = view;
        this.repository = repository;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        this.view.hideErrorDialog();
        this.view.showLoadingDialog();
        this.repository.getNewFeeds(
            new OnGetNewFeedsListener() {
                @Override
                public void onSuccess(List<Feed> result) {
                    view.hideLoadingDialog();
                    view.drawNewFeeds(result);
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

    @Override
    public void feedOnClicked(Feed feed) {
        this.view.openFeedDetailLayout(feed);
    }
}
