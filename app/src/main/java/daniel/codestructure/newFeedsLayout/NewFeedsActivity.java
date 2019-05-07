package daniel.codestructure.newFeedsLayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import daniel.codestructure.R;
import daniel.codestructure.baseMVP.Injection;
import daniel.codestructure.util.ActivityUtils;

public class NewFeedsActivity extends AppCompatActivity {
    private static final String TAG = NewFeedsActivity.class.getSimpleName();

    private NewFeedsFragment fragment;
    private NewFeedsContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_new_feeds);
        ButterKnife.bind(this);
        this.drawComponentView();
    }

    private void drawComponentView() {
        super.setTitle(super.getString(R.string.new_feeds));
        this.drawNewFeedsFragment();
        this.createPresenter();
    }

    private void drawNewFeedsFragment() {
        if (this.fragment == null) {
            this.fragment = NewFeedsFragment.getInstance();
        }
        ActivityUtils.replaceFragmentToActivity(
            super.getSupportFragmentManager(),
            this.fragment,
            R.id.new_feeds_container
        );
    }

    private void createPresenter() {
        this.presenter = new NewFeedsPresenter(
            this.fragment,
            Injection.provideFeedsRepository(super.getApplicationContext())
        );
    }
}
