package posts;

import java.util.Date;

public class ImagePost extends TextPost {
    private String imageFileName;
    private String imageResolution;


    public ImagePost(String text, Date date, Location loc, String taggedFriends, String imageFileName, String imageResolution) {
        super(text, date, loc, taggedFriends);
        this.imageFileName = imageFileName;
        this.imageResolution = imageResolution;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String getImageResolution() {
        return imageResolution;
    }

    public void setImageResolution(String imageResolution) {
        this.imageResolution = imageResolution;
    }

    // it gets the information about post.
    public void getPost() {
        System.out.println(getText());
        System.out.println("Date: " + getDate());
        System.out.println("posts.Location:" + getLoc());
        if (!getTaggedFriends().equals("")) // if there is a no tagged friend. it pass that.
            System.out.println("Friends tagged in this post: " + getTaggedFriends());
        System.out.println("Image: " + getImageFileName());
        System.out.println("Image resolution: " + getImageResolution());
        System.out.println("-----------------------");

    }
}

