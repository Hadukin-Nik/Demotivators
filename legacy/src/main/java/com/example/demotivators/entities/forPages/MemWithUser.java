package com.example.demotivators.entities.forPages;

import com.example.demotivators.dao_s.UsersDAO;
import com.example.demotivators.entities.Mem;
import com.example.demotivators.entities.User;

public class MemWithUser extends Mem {
    private User user;

    public MemWithUser(Mem mem) {
        super(mem.getMemId(), mem.getUserId(), mem.getPicture(), mem.getDescription(), mem.getTags(), mem.getCreationDate(), mem.getUpdateDate(), mem.isCommentsAllowed());

        user = UsersDAO.findUser(mem.getUserId());
    }

    public User getUser() {
        return user;
    }
}
