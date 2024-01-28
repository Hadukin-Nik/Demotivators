package com.example.demotivators.entities.forPages;

import com.example.demotivators.dao_s.UsersDAO;
import com.example.demotivators.entities.Request;
import com.example.demotivators.entities.User;

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
