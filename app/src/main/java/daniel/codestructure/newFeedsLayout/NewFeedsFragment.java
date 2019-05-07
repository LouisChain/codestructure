package daniel.codestructure.newFeedsLayout;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import daniel.codestructure.R;
import daniel.codestructure.data.model.Feed;
import daniel.codestructure.feedDetailLayout.FeedDetailActivity;
import daniel.codestructure.util.Config;

public class NewFeedsFragment extends Fragment implements NewFeedsContract.View {
    private static final String TAG = NewFeedsFragment.class.getSimpleName();
    private static NewFeedsFragment INSTANCE;

    @BindView(R.id.fragment_new_feeds_recycler_view)
    protected RecyclerView newFeedsRecyclerView;

    private NewFeedsContract.Presenter presenter;
    private ProgressDialog progressDialog;
    private AlertDialog alertDialog;
    private NewFeedsAdapter adapter;

    public static NewFeedsFragment getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NewFeedsFragment();
        }
        return INSTANCE;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_feeds, container, false);
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
    public void setPresenter(NewFeedsContract.Presenter presenter) {
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
    public void drawNewFeeds(List<Feed> feeds) {
        if (this.adapter == null) {
            this.adapter = new NewFeedsAdapter(super.getActivity(), feeds, this.presenter);
            this.newFeedsRecyclerView.setAdapter(this.adapter);
        } else {
            this.adapter.changeFeeds(feeds);
        }
    }

    @Override
    public void openFeedDetailLayout(Feed feed) {
        Intent intent = new Intent(super.getActivity(), FeedDetailActivity.class);
        intent.putExtra(Config.Extras.FEED, feed);
        super.startActivity(intent);
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
        this.alertDialog.show();
    }

    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(
            super.getActivity(),
            LinearLayoutManager.VERTICAL,
            false
        );
        this.newFeedsRecyclerView.setLayoutManager(layoutManager);
        this.newFeedsRecyclerView.setHasFixedSize(false);
    }
}
