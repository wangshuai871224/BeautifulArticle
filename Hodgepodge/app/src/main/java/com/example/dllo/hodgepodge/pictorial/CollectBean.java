package com.example.dllo.hodgepodge.pictorial;

import android.os.Parcel;
import android.os.Parcelable;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by shuaiwang on 16/12/26.
 */
@Table("collect")                   // 包裹化, intent跳转就可以传实体类了
public class CollectBean implements Parcelable {
    // 指定自增，每个对象需要有一个主键
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    int id;

    private String itemUrl, title, subTitle, imageUrl, webUrl;

    public CollectBean() {

    }

    public CollectBean(String itemUrl, String title, String subTitle, String imageUrl, String webUrl) {
        this.itemUrl = itemUrl;
        this.imageUrl = imageUrl;
        this.subTitle = subTitle;
        this.title = title;
        this.webUrl = webUrl;
    }

    protected CollectBean(Parcel in) {
        id = in.readInt();
        itemUrl = in.readString();
        title = in.readString();
        subTitle = in.readString();
        imageUrl = in.readString();
        webUrl = in.readString();
    }

    public static final Creator<CollectBean> CREATOR = new Creator<CollectBean>() {
        @Override
        public CollectBean createFromParcel(Parcel in) {
            return new CollectBean(in);
        }

        @Override
        public CollectBean[] newArray(int size) {
            return new CollectBean[size];
        }
    };

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(itemUrl);
        parcel.writeString(title);
        parcel.writeString(subTitle);
        parcel.writeString(imageUrl);
        parcel.writeString(webUrl);
    }
}
