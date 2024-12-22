package facebook.pro;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

import static facebook.pro.Welcome.frame;

public class Reaction implements pageLayOut {

    private ArrayList<String> likedUsers; // List of users who liked the post
    private ArrayList<Comment> comments; // List of comments (with replies)
    private ArrayList<String> tags; // List of tagged usernames

    public Reaction() {
        this.likedUsers = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.tags = new ArrayList<>();
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    // Function to like the post
    public void addLike(String username) {
        if (!likedUsers.contains(username)) {
            likedUsers.add(username);
            JLabel label = new JLabel(username + " liked the post! Total likes: " + likedUsers.size());
            frame.add(label);
            System.out.println(username + " liked the post! Total likes: " + likedUsers.size());
        } else {
            JLabel label = new JLabel(username + " liked the post! Total likes: " + likedUsers.size());
            frame.add(label);
            System.out.println(username + " has already liked this post.");
        }
    }

    public int getLikeCount() {
        return likedUsers.size();
    }

    // Function to display likes
    public void displayLikes() {
        System.out.println("Total likes: " + likedUsers.size());
        if (!likedUsers.isEmpty()) {
            System.out.println("Liked by: " + String.join(", ", likedUsers));
        }
    }

    SeePosts post = new SeePosts();

    // Function to add a comment
    public void addComment(String user, String message) {
        Comment comment = new Comment(user, message);
        comments.add(comment);
        System.out.println("Comment added by " + user + ": " + message);

    }

    // Function to display all comments and their replies
    public void displayComments() {
        if (comments.isEmpty()) {
            System.out.println("No comments yet.");
        } else {
            System.out.println("Comments:");
            for (int i = 0; i < comments.size(); i++) {
                System.out.println("Comment #" + (i + 1) + " - " + comments.get(i));
            }
        }
    }

    // Function to add a reply to a specific comment
    public void addReply(int commentIndex, String user, String replyMessage) {
        if (commentIndex < 0 || commentIndex >= comments.size()) {
            System.out.println("Invalid comment index.");
        } else {
            Comment parentComment = comments.get(commentIndex);
            parentComment.addReply(new Comment(user, replyMessage));
            System.out.println("Reply added by " + user + " to comment #" + (commentIndex + 1));

        }
    }


    // Function to tag a user
    public void addTag(String username) {
        if (!tags.contains(username)) {
            tags.add(username);
            System.out.println(username + " has been tagged.");
        } else {
            System.out.println(username + " is already tagged.");
        }
    }

    // Display all tags
    public void displayTags() {
        if (tags.isEmpty()) {
            System.out.println("No tags yet.");
        } else {
            System.out.println("Tagged users: " + String.join(", ", tags));
        }
    }




    // Inner class for comments and replies
//    public static class Comment {
//        private String user;
//        private String message;
//        private ArrayList<Comment> replies;
//
//        public Comment(String user, String message) {
//            this.user = user;
//            this.message = message;
//            this.replies = new ArrayList<>();
//        }
//
//        public void addReply(Comment reply) {
//            replies.add(reply);
//        }
//
//        @Override
//        public String toString() {
//            StringBuilder sb = new StringBuilder();
//            sb.append(user).append(": ").append(message);
//            if (!replies.isEmpty()) {
//                sb.append("\n  Replies:");
//                for (Comment reply : replies) {
//                    sb.append("\n    ").append(reply);
//                }
//            }
//            return sb.toString();
//        }
//
//        public Iterable<? extends Comment> getReplies() {
//            return replies;
//        }
//    }
}