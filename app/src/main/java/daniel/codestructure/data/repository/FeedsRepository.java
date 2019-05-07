package daniel.codestructure.data.repository;

import java.util.List;

import daniel.codestructure.data.listener.OnGetFeedDetailListener;
import daniel.codestructure.data.listener.OnGetNewFeedsListener;
import daniel.codestructure.data.model.Feed;
import daniel.codestructure.data.remote.FeedsRemoteDataSource;

public class FeedsRepository {
    private static final String TAG = FeedsRepository.class.getSimpleName();

    private static FeedsRepository INSTANCE = null;

    private final FeedsRemoteDataSource feedsRemoteDataSource;

    public static FeedsRepository getInstance(FeedsRemoteDataSource dataSource) {
        if (INSTANCE == null) {
            INSTANCE = new FeedsRepository(dataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public FeedsRepository(FeedsRemoteDataSource dataSource) {
        this.feedsRemoteDataSource = dataSource;
    }

    public void getNewFeeds(final OnGetNewFeedsListener listener) {
        this.feedsRemoteDataSource.getNewFeeds(
            new OnGetNewFeedsListener() {
                @Override
                public void onSuccess(List<Feed> result) {
                    listener.onSuccess(result);
                }

                @Override
                public void onFailed(Throwable error) {
                    listener.onFailed(error);
                }
            }
        );
    }

    public void getFeedDetail(final OnGetFeedDetailListener listener) {
        this.feedsRemoteDataSource.getFeedDetail(
            new OnGetFeedDetailListener() {
                @Override
                public void onSuccess(Feed result) {
                    listener.onSuccess(result);
                }

                @Override
                public void onFailed(Throwable error) {
                    listener.onFailed(error);
                }
            }
        );
    }
}
