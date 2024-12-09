package facebook.pro;

import java.io.Serializable;
import java.util.Random;

public class Post implements Serializable {
    private String postId;
    private String content;
    private String privacy;
    private String userEmail;

    // Constructor
    public Post(String content, String privacy, String userEmail) {
        this.postId = generatePostId(); // Generate a 4-digit ID
        this.content = content;
        this.privacy = privacy;
        this.userEmail = userEmail;
    }

    // Generate a unique 4-digit post ID
    private String generatePostId() {
        return String.format("%04d", new Random().nextInt(10000));
    }

}
