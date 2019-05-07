package daniel.codestructure.newFeedsLayout;

import java.util.List;

import daniel.codestructure.baseMVP.BasePresenter;
import daniel.codestructure.baseMVP.BaseView;
import daniel.codestructure.data.model.Feed;

public interface NewFeedsContract {
    interface View extends BaseView<Presenter> {
        void showLoadingDialog();

        void hideLoadingDialog();

        void drawNewFeeds(List<Feed> feeds);

        void openFeedDetailLayout(Feed feed);

        void showError();

        void hideErrorDialog();
    }

    interface Presenter extends BasePresenter {
        void feedOnClicked(Feed feed);
    }
}
