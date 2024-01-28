package demotivators.entities.forPages;

import demotivators.dao_s.UsersDAO;
import demotivators.entities.Comment;
import demotivators.entities.User;

public class CommentWithUser extends Comment {
    private User user;
    public CommentWithUser(Comment comment) {
        super(comment.getCommentId(),comment.getMemId(),comment.getUserId(),comment.getDate(),comment.getText(),comment.getLikes(),comment.getDislikes());

        user = UsersDAO.findUser(comment.getUserId());
    }

    public User getUser() {
        return user;
    }
}
