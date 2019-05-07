package daniel.codestructure.feedDetailLayout;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import daniel.codestructure.R;
import daniel.codestructure.data.model.Feed;
import daniel.codestructure.data.model.Section;
import daniel.codestructure.util.TextUtil;

public class FeedDetailFragment extends Fragment implements FeedDetailContract.View {
    private static final String TAG = FeedDetailFragment.class.getSimpleName();
    private static FeedDetailFragment INSTANCE;

    @BindView(R.id.fragment_feed_detail_title)
    protected TextView titleTextView;
    @BindView(R.id.fragment_feed_detail_created_at)
    protected TextView createdAtTextView;
    @BindView(R.id.fragment_feed_detail_description)
    protected TextView descriptionTextView;
    @BindView(R.id.fragment_feed_detail_sections)
    protected RecyclerView sectionsRecyclerView;

    private FeedDetailContract.Presenter presenter;
    private ProgressDialog progressDialog;
    private AlertDialog alertDialog;
    private SectionsAdapter adapter;

    public static FeedDetailFragment getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FeedDetailFragment();
        }
        return INSTANCE;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed_detail, container, false);
        ButterKnife.bind(this, view);
        this.setupRecyclerView();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.presenter.start();
    }

    @Override
    public void setPresenter(FeedDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showLoadingDialog() {
        this.progressDialog = new ProgressDialog(super.getContext());
        this.progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        this.progressDialog.show();
        this.progressDialog.setCancelable(false);
    }

    @Override
    public void hideLoadingDialog() {
        if (this.progressDialog != null) {
            this.progressDialog.dismiss();
        }
    }

    @Override
    public void drawFeedDetail(Feed feed) {
        this.titleTextView.setText(TextUtil.decodeString(feed.getTitle()));
        this.descriptionTextView.setText(TextUtil.decodeString(feed.getDescription()));
        this.createdAtTextView.setText(
            TextUtil.decodeString(TextUtil.convertDateToString(feed.getPublishedDate()))
        );
        this.drawSections(feed.getSections());
    }

    @Override
    public void showError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(super.getActivity());
        builder.setTitle(R.string.warning);
        builder.setCancelable(true);
        builder.setMessage(R.string.internet_error);
        builder.setPositiveButton(
            R.string.ok,
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    presenter.start();
                }
            }
        );
        builder.setNegativeButton(
            R.string.cancel,
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            }
        );
        this.alertDialog = builder.create();
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                alertDialog.show();
            }
        });
    }

    @Override
    public void hideErrorDialog() {
        if (this.alertDialog != null) {
            Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    alertDialog.dismiss();
                }
            });
        }
    }

    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(
            super.getActivity(),
            LinearLayoutManager.VERTICAL,
            false
        );
        this.sectionsRecyclerView.setLayoutManager(layoutManager);
        this.sectionsRecyclerView.setHasFixedSize(false);
    }

    private void drawSections(List<Section> sections) {
        this.adapter = new SectionsAdapter(super.getActivity(), sections, this.presenter);
        this.sectionsRecyclerView.setAdapter(this.adapter);
    }
}
