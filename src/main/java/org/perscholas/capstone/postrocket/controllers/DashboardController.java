package org.perscholas.capstone.postrocket.controllers;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.capstone.postrocket.models.GeneratedPost;
import org.perscholas.capstone.postrocket.models.Request;
import org.perscholas.capstone.postrocket.models.User;
import org.perscholas.capstone.postrocket.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class DashboardController {

    private final RequestService requestService;

    private final GeneratedPostService postService;


    @Autowired
    private UserServiceImpl userServiceImpl;

    public DashboardController(RequestService requestService, GeneratedPostService postService) {
        this.requestService = requestService;
        this.postService = postService;
    }

    @GetMapping("/dashboard")
    public String showDashboardPage(@AuthenticationPrincipal UserDetails userDetails, ModelMap map, @RequestParam(defaultValue = "false") boolean sortByTitleAsc) {
        return getDashboardData(userDetails, map, sortByTitleAsc);
    }

    @PostMapping("/dashboard/events/{postId}")
    public String updatePost(@PathVariable("postId") long postId, @ModelAttribute GeneratedPost post, @AuthenticationPrincipal UserDetails userDetails, ModelMap map, @RequestParam("_method") String method)
    {
        if (method.equals("EDIT")) {
            try {
                GeneratedPost postToUpdate = postService.findGeneratedPostById(postId);

                if (post != null) {
                    postToUpdate.setPost(post.getNewValue());
                    postService.saveGeneratedPost(postToUpdate);
                    map.addAttribute("isSuccess", true);
                }
            } catch (Exception e) {
                map.addAttribute("errorMessage", "Update failed!");
            }
        } else if (method.equals("DELETE")) {
            try {
                if (post != null) {
                    postService.deleteGeneratedPost(postId);
                    map.addAttribute("isSuccess", true);
                }
            } catch (Exception e) {
                map.addAttribute("errorMessage", "Delete failed!");
            }
        }

        return getDashboardData(userDetails, map, true);

    }

    private String getDashboardData(UserDetails userDetails, ModelMap map, boolean ascendingOrder) {
        if(userDetails != null) {
            String username = userDetails.getUsername();
            User user = userServiceImpl.getUserByEmail(username);
            map.addAttribute("user", user);

            List<Request> requests;

            if (ascendingOrder) {
                requests = requestService.getRequestsByUserIdAsc(userServiceImpl.getUserByEmail(user.getEmail()).getId());
            } else {
                requests = requestService.getRequestsByUserIdDesc(userServiceImpl.getUserByEmail(user.getEmail()).getId());
            }
            map.addAttribute("requests", requests);

            return "dashboard";
        } else {
            return "home";
        }
    }
}
