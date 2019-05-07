package daniel.codestructure.feedDetailLayout;

import daniel.codestructure.baseMVP.BasePresenter;
import daniel.codestructure.baseMVP.BaseView;
import daniel.codestructure.data.model.Feed;

public interface FeedDetailContract {
    interface View extends BaseView<Presenter> {
        void showLoadingDialog();

        void hideLoadingDialog();

        void drawFeedDetail(Feed feed);

        void showError();

        void hideErrorDialog();
    }

    interface Presenter extends BasePresenter {
    }
}
