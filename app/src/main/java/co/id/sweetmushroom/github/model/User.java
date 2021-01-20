package co.id.sweetmushroom.github.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String username;
    private String name;
    private String location;
    private String repository;
    private String company;
    private String followers;
    private String following;
    private String avatar;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(this.username);
        dest.writeString(this.name);
        dest.writeString(this.location);
        dest.writeString(this.repository);
        dest.writeString(this.company);
        dest.writeString(this.followers);
        dest.writeString(this.following);
        dest.writeString(this.avatar);

    }

    public User (){

    }

    private User (Parcel in){
        username    = in.readString();
        name        = in.readString();
        location    = in.readString();
        repository  = in.readString();
        company     = in.readString();
        followers   = in.readString();
        following   = in.readString();
        avatar      = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }
        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
