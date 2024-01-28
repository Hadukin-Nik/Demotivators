package com.example.demotivators.helper_s;

import com.example.demotivators.dao_s.FriendshipRequestsDAO;
import com.example.demotivators.dao_s.MemesDAO;
import com.example.demotivators.entities.Comment;
import com.example.demotivators.entities.Mem;
import com.example.demotivators.entities.forPages.CommentWithUser;
import com.example.demotivators.entities.forPages.MemWithUser;
import com.example.demotivators.entities.forPages.RequestWithUser;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyHelper {
    public static Cookie getSpecificCookie(Cookie[] cookies, String name) {
        if(cookies == null) return null;

        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals(name))
                return cookies[i];

        }

        return(null);
    }

    public static String getAStringBeforePattern(String root_buf, String scheme) {
        StringBuilder sb = new StringBuilder();

        String root = "";
        for(int i = 0; i < root_buf.length(); i++) {

            int j = 0;
            boolean ok = true;
            while(j < scheme.length() && j + i < root_buf.length()) {
                if(root_buf.charAt(j + i) != scheme.charAt(j)) break;
                if(j + 1 == scheme.length()) ok = false;

                j++;
            }

            if(!ok) {
                root = sb.toString();
            } else {
                sb.append(root_buf.charAt(i));
            }
        }

        return root;
    }

    public static String getAStringAfterPattern(String root_buf, String scheme) {
        StringBuilder sb = new StringBuilder();

        String root = "";
        for(int i = 0; i < root_buf.length(); i++) {
            int j = 0;
            boolean ok = true;

            while(j < scheme.length() && i + j < root_buf.length()) {
                if(root_buf.charAt(j + i) != scheme.charAt(j)) break;
                if(j + 1 == scheme.length()) ok = false;

                j++;
            }

            if(!ok) {
                while(i + j < root_buf.length()) {
                    sb.append(root_buf.charAt(i + j));
                    j++;
                }

                return sb.toString();
            }
        }

        return root;
    }


    public static List<MemWithUser> toAltMemes(List<Mem> mems) {
        List<MemWithUser> ans = new ArrayList<>();

        for (var i : mems) {
            ans.add(new MemWithUser(i));
        }
        return ans;
    }

    public static MemWithUser toAltMem(Mem meme)  {
        return new MemWithUser(meme);
    }

    public static List<CommentWithUser> toAltComments(List<Comment> comments) {
        List<CommentWithUser> ans = new ArrayList<>();

        for(var i : comments){
            ans.add(new CommentWithUser(i));
        }

        return ans;
    }

    public static List<MemWithUser> friendsScroll(int userId) {
        Set<Integer> friendsSet = new HashSet<>();

        List<MemWithUser> mems = toAltMemes(MemesDAO.getMemes());

        List<RequestWithUser> friends = FriendshipRequestsDAO.getApprovedRequestsWithUser(userId);

        for(var i : friends) {
            friendsSet.add(i.getFromUserId());
            friendsSet.add(i.getToUserId());
        }

        List<MemWithUser> ans = new ArrayList<>();

        for(var i : mems) {
            if(friendsSet.contains(i.getUserId())) ans.add(i);
        }

        return ans;
    }

    public static int getId(String urlAfterWebDomain) {
        Pattern p = Pattern.compile("[0-9]+");

        Matcher m = p.matcher(urlAfterWebDomain);

        int memId = -1;

        if(m.find()) {
            memId = Integer.parseInt(m.group());
        }
        return memId;
    }
}
