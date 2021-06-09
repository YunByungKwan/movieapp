package ybk.org.movieapp.ui.comment.di;

import dagger.Subcomponent;
import ybk.org.movieapp.ui.comment.CommentListActivity;

@Subcomponent(modules = {CommentListModule.class})
public interface CommentListComponent {
    @Subcomponent.Factory
    interface Factory {
        CommentListComponent create();
    }

    CommentListActivity inject(CommentListActivity activity);
}
