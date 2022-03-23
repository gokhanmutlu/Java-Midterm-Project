package posts;

import java.util.Date;

public class TextPost extends BasePost {

    private String text;

    public TextPost(String text, Date date, Location loc, String taggedFriends) {
        super(date, loc,taggedFriends);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    // it gets the information about post.
    public void getPost(){
        System.out.println(getText());
        System.out.println("Date: " + getDate());
        System.out.println("posts.Location:" + getLoc());
        if (!getTaggedFriends().equals("")) // if there is a no tagged friend. it pass that.
            System.out.println("Friends tagged in this post: " + getTaggedFriends());
        System.out.println("-----------------------");

    }


}
