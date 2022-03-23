package posts;

import java.util.Date;

public class VideoPost extends TextPost {

    private String videoFileName;
    private int videoDuration;
    private int maxVideoLength = 10;

    public VideoPost(String text, Date date, Location loc, String taggedFriends, String videoFileName, int videoDuration) {
        super(text, date, loc ,taggedFriends);
        this.videoFileName = videoFileName;
        if (videoDuration <= 10)
            this.videoDuration = videoDuration;
        else
            this.videoDuration = maxVideoLength;
    }

    public String getVideoFileName() {
        return videoFileName;
    }

    //if the video duration is more than 10 minutes, it assigns 10 minutes.
    public void setVideoFileName(String videoFileName) {
        if (videoDuration <= 10)
            this.videoDuration = videoDuration;
        else
            this.videoDuration = maxVideoLength;
    }

    public int getVideoDuration() {
        return videoDuration;
    }

    public void setVideoDuration(int videoDuration) {
        this.videoDuration = videoDuration;
    }

    // it gets the information about post.
    public void getPost(){
        System.out.println(getText());
        System.out.println("Date: " + getDate());
        System.out.println("posts.Location:" + getLoc());
        if (!getTaggedFriends().equals("")) // if there is a no tagged friend. it pass that.
            System.out.println("Friends tagged in this post: " + getTaggedFriends());
        System.out.println("Video: " + getVideoFileName());
        System.out.println("Video duration: " + getVideoDuration() + " minute(s)");
        System.out.println("-----------------------");

    }
}
