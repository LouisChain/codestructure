package daniel.codestructure.newFeedsLayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import daniel.codestructure.R;
import daniel.codestructure.data.model.Feed;
import daniel.codestructure.data.model.Image;
import daniel.codestructure.util.TextUtil;

public class NewFeedsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = NewFeedsAdapter.class.getSimpleName();
    private static final int FEED_WITH_ONE_IMAGE = 0;
    private static final int FEED_WITH_MANY_IMAGES = 1;

    private Context context;
    private List<Feed> feeds;
    private NewFeedsContract.Presenter presenter;

    public NewFeedsAdapter(Context context,
                           List<Feed> feeds,
                           NewFeedsContract.Presenter presenter) {
        this.context = context;
        this.feeds = feeds;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == FEED_WITH_ONE_IMAGE) {
            View view = LayoutInflater.from(context).inflate(
                R.layout.row_feed_with_one_image,
                parent,
                false
            );
            FeedWithOneImageViewHolder viewHolder = new FeedWithOneImageViewHolder(view);
            return viewHolder;
        } else {
            View view = LayoutInflater.from(context).inflate(
                R.layout.row_feed_with_many_images,
                parent,
                false
            );
            FeedWithManyImagesViewHolder viewHolder = new FeedWithManyImagesViewHolder(view);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Feed feed = this.feeds.get(position);
        int viewType = this.getItemViewType(position);
        if (viewType == FEED_WITH_ONE_IMAGE) {
            FeedWithOneImageViewHolder viewHolder = (FeedWithOneImageViewHolder) holder;
            viewHolder.drawFeed(feed);
        } else {
            FeedWithManyImagesViewHolder viewHolder = (FeedWithManyImagesViewHolder) holder;
            viewHolder.drawFeed(feed);
        }
    }

    @Override
    public int getItemCount() {
        if (this.feeds == null) {
            return 0;
        }
        return this.feeds.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (this.getItemCount() == 0) {
            return 0;
        }
        Feed feed = this.feeds.get(position);
        List<Image> images = feed.getImages();
        if (images == null || images.size() <= 1) {
            return FEED_WITH_ONE_IMAGE;
        }
        return FEED_WITH_MANY_IMAGES;
    }

    public void changeFeeds(List<Feed> feeds) {
        if (this.feeds != null) {
            this.feeds.clear();
            this.feeds.addAll(feeds);
        } else {
            this.feeds = feeds;
        }
        super.notifyDataSetChanged();
    }

    private void setupFeedClickListener(final ConstraintLayout feedLayout, final Feed feed) {
        feedLayout.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.feedOnClicked(feed);
                }
            }
        );
    }

    public class FeedWithOneImageViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.row_feed_layout)
        protected ConstraintLayout rowFeedLayout;
        @BindView(R.id.row_feed_image)
        protected SimpleDraweeView feedImageView;
        @BindView(R.id.row_feed_author)
        protected TextView authorTextView;
        @BindView(R.id.row_feed_title)
        protected TextView titleTextView;
        @BindView(R.id.row_feed_created_at)
        protected TextView createdAtTextView;

        public FeedWithOneImageViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void drawFeed(Feed feed) {
            setupFeedClickListener(this.rowFeedLayout, feed);
            List<Image> images = feed.getImages();
            if (images != null && images.size() > 0) {
                this.feedImageView.setVisibility(View.VISIBLE);
                this.feedImageView.setImageURI(images.get(0).getHref());
            } else if (feed.getAvatar() != null) {
                this.feedImageView.setVisibility(View.VISIBLE);
                this.feedImageView.setImageURI(feed.getAvatar().getHref());
            } else {
                this.feedImageView.setVisibility(View.GONE);
            }
            this.authorTextView.setText(TextUtil.decodeString(feed.getPublisher().getName()));
            this.titleTextView.setText(TextUtil.decodeString(feed.getTitle()));
            this.createdAtTextView.setText(TextUtil.convertDateToString(feed.getPublishedDate()));
        }
    }

    public class FeedWithManyImagesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.row_feed_layout)
        protected ConstraintLayout rowFeedLayout;
        @BindViews({
            R.id.row_feed_image_1,
            R.id.row_feed_image_2,
            R.id.row_feed_image_3,
            R.id.row_feed_image_4
        })
        protected List<SimpleDraweeView> feedImageViews;
        @BindView(R.id.row_feed_author)
        protected TextView authorTextView;
        @BindView(R.id.row_feed_title)
        protected TextView titleTextView;
        @BindView(R.id.row_feed_created_at)
        protected TextView createdAtTextView;

        public FeedWithManyImagesViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void drawFeed(Feed feed) {
            setupFeedClickListener(this.rowFeedLayout, feed);
            List<Image> images = feed.getImages();
            if (images != null && images.size() > 0) {
                final int MAX_IMAGES = Math.min(4, images.size());
                for (int i = 0; i < MAX_IMAGES; i++) {
                    drawImage(this.feedImageViews.get(i), images.get(i));
                }
            }
            this.authorTextView.setText(TextUtil.decodeString(feed.getPublisher().getName()));
            this.titleTextView.setText(TextUtil.decodeString(feed.getTitle()));
            this.createdAtTextView.setText(TextUtil.convertDateToString(feed.getPublishedDate()));
        }

        private void drawImage(SimpleDraweeView feedImageView, Image image) {
            if (image != null) {
                feedImageView.setImageURI(image.getHref());
            }
        }
    }
}
