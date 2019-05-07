package daniel.codestructure.feedDetailLayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import daniel.codestructure.R;
import daniel.codestructure.data.model.Markup;
import daniel.codestructure.data.model.Section;
import daniel.codestructure.util.TextUtil;

public class SectionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = SectionsAdapter.class.getSimpleName();

    private Context context;
    private List<Section> sections;
    private FeedDetailContract.Presenter presenter;

    public SectionsAdapter(Context context,
                           List<Section> sections,
                           FeedDetailContract.Presenter presenter) {
        this.context = context;
        this.sections = sections;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(this.context).inflate(
                R.layout.row_section_without_image,
                parent,
                false
            );
            SectionWithoutImageViewHolder viewHolder = new SectionWithoutImageViewHolder(view);
            return viewHolder;
        } else {
            View view = LayoutInflater.from(this.context).inflate(
                R.layout.row_section_with_image,
                parent,
                false
            );
            SectionWithImageViewHolder viewHolder = new SectionWithImageViewHolder(view);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = this.getItemViewType(position);
        if (viewType == 1) {
            SectionWithoutImageViewHolder viewHolder = (SectionWithoutImageViewHolder) holder;
            viewHolder.drawSection(this.sections.get(position));
        } else {
            SectionWithImageViewHolder viewHolder = (SectionWithImageViewHolder) holder;
            viewHolder.drawSection(this.sections.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if (this.sections == null) {
            return 0;
        }
        return this.sections.size();
    }

    @Override
    public int getItemViewType(int position) {
        Section section = this.sections.get(position);
        return section.getSectionType();
    }

    public void changeSections(List<Section> sections) {
        this.sections = sections;
        super.notifyDataSetChanged();
    }

    private void drawHtmlToTextView(TextView textView, String text) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            textView.setText(
                Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
            );
        } else {
            textView.setText(Html.fromHtml(text));
        }
    }

    public class SectionWithoutImageViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.row_section_text)
        protected TextView sectionContentTextView;

        public SectionWithoutImageViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void drawSection(Section section) {
            String text = section.getContent().getText();
            if (!TextUtils.isEmpty(text)) {
                text = TextUtil.decodeString(text);
                List<Markup> markups = section.getContent().getMarkups();
                if (markups != null && markups.size() > 0) {
                    for (Markup markup : markups) {
                        if (markup.getMarkupType() == 1) {
                            int start = markup.getStart();
                            int end = markup.getEnd();
                            String text1 = text.substring(0, start);
                            String text2 = text.substring(start, end);
                            String text3 = text.substring(end);
                            text = text1 + "<b>" + text2 + "</b>" + text3;
                        }
                    }
                }
                drawHtmlToTextView(this.sectionContentTextView, text);
            }
        }
    }

    public class SectionWithImageViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.row_section_image)
        protected SimpleDraweeView sectionImageView;
        @BindView(R.id.row_section_text)
        protected TextView sectionContentTextView;

        public SectionWithImageViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void drawSection(Section section) {
            String url = section.getContent().getHref();
            if (section.getSectionType() == 2) {
                url = section.getContent().getPreviewImage().getHref();
            }
            if (!TextUtils.isEmpty(url)) {
                this.sectionImageView.setImageURI(url);
            }
            String text = section.getContent().getCaption();
            text = TextUtil.decodeString(text);
            if (!TextUtils.isEmpty(text)) {
                drawHtmlToTextView(this.sectionContentTextView, text);
            }
        }
    }
}