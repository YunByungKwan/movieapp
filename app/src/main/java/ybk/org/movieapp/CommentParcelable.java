package ybk.org.movieapp;

import android.os.Parcel;
import android.os.Parcelable;

public class CommentParcelable implements Parcelable {

    String userId;

    String registerTime;

    float ratingCount;

    String comment;

    int recommendCount;

    public CommentParcelable(String userId, String registerTime,
                             float ratingCount, String comment, int recommendCount) {
        this.userId = userId;
        this.registerTime = registerTime;
        this.ratingCount = ratingCount;
        this.comment = comment;
        this.recommendCount = recommendCount;
    }

    public CommentParcelable(Parcel src) {
        userId = src.readString();
        registerTime = src.readString();
        ratingCount = src.readFloat();
        comment = src.readString();
        recommendCount = src.readInt();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public CommentParcelable createFromParcel(Parcel src) {
            return new CommentParcelable(src);
        }

        public CommentParcelable[] newArray(int size) {
            return new CommentParcelable[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userId);
        dest.writeString(registerTime);
        dest.writeFloat(ratingCount);
        dest.writeString(comment);
        dest.writeInt(recommendCount);
    }
}
