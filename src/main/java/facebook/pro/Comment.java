package facebook.pro;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

import static facebook.pro.Welcome.frame;


public class Comment {
    private String user;
        private String message;
        private ArrayList<Comment> replies;

        public Comment(String user, String message) {
            this.user = user;
            this.message = message;
            this.replies = new ArrayList<>();
        }

        public void addReply(Comment reply) {
            replies.add(reply);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(user).append(": ").append(message);
            if (!replies.isEmpty()) {
                sb.append("\n  Replies:");
                for (Comment reply : replies) {
                    sb.append("\n    ").append(reply);
                }
            }
            return sb.toString();
        }

        public Iterable<? extends Comment> getReplies() {
            return replies;
        }
    }
