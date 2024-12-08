package facebook.pro;
import java.io.Serializable;
import java.util.UUID;

public class Post implements Serializable {
    private String postId;
    private String content;
    private String privacy;
    private String currentUserEmail;

    // Constructor
    public Post(String content, String privacy, String currentUserEmail) {
        // Generate a unique post ID using UUID
        this.postId = UUID.randomUUID().toString();
        this.content = content;
        this.privacy = privacy;
        this.currentUserEmail = currentUserEmail;
    }


}
