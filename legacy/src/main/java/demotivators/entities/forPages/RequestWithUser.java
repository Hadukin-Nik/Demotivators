package demotivators.entities.forPages;

import demotivators.dao_s.UsersDAO;
import demotivators.entities.Request;
import demotivators.entities.User;

public class RequestWithUser extends Request {
    private User sender;

    public RequestWithUser(Request req) {
        super(req.getToUserId(), req.getFromUserId(), req.getRequestId(), req.getText(), req.getCreationDate(), req.getUpdateDate(), req.isWatched(), req.isApproved());

        sender = UsersDAO.findUser(req.getFromUserId());
    }


    public User getSender() {
        return sender;
    }
}
