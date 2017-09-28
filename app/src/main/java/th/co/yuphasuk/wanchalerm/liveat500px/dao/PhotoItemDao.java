package th.co.yuphasuk.wanchalerm.liveat500px.dao;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by streami.t.mobiledeveloper1 on 9/27/2017 AD.
 */

public class PhotoItemDao {

    @SerializedName("id")
    private int id;

    @SerializedName("link")
    private String link;

    @SerializedName("image_url")
    private String imageUrl;

    @SerializedName("caption")
    private String caption;

    @SerializedName("user_id")
    private Integer userId;

    @SerializedName("username")
    private String username;

    @SerializedName("profile_picture")
    private String profilePicture;

    @SerializedName("tags")
    private List<String> tags = new ArrayList<>();

    @SerializedName("created_time")
    private Date createdTime;

    @SerializedName("camera")
    private String camera;

    @SerializedName("lens")
    private String lens;

    @SerializedName("focal_lenght")
    private String focalLenght;

    @SerializedName("iso")
    private String iso;

    @SerializedName("shutter_speed")
    private String shutterSpeed;

    @SerializedName("aperture")
    private String aperture;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getLens() {
        return lens;
    }

    public void setLens(String lens) {
        this.lens = lens;
    }

    public String getFocalLenght() {
        return focalLenght;
    }

    public void setFocalLenght(String focalLenght) {
        this.focalLenght = focalLenght;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getShutterSpeed() {
        return shutterSpeed;
    }

    public void setShutterSpeed(String shutterSpeed) {
        this.shutterSpeed = shutterSpeed;
    }

    public String getAperture() {
        return aperture;
    }

    public void setAperture(String aperture) {
        this.aperture = aperture;
    }
}
