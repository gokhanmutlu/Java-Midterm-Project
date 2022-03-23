package posts;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BasePost {

    Location loc;
    private String taggedFriends;
    private Date date;


    public BasePost(Date date, Location loc, String taggedFriends ) {
        this.taggedFriends = taggedFriends;
        this.date = date;
        this.loc = loc;
    }

    public String getTaggedFriends() {
        return taggedFriends;
    }

    public void setTaggedFriends(String taggedFriends) {
        this.taggedFriends = taggedFriends;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public String getLoc() {
        return  loc.latitude+ " " + loc.longitude;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public void getPost(){

    }
}

