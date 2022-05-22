package edu.ib.api;

import edu.ib.manager.GroupManager;
import edu.ib.manager.UserManager;
import edu.ib.repo.entity.Group;
import edu.ib.repo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class GroupApi {

    private GroupManager groupManager;
    private UserManager userManager;

    @Autowired
    public GroupApi(GroupManager groupManager) {
        this.groupManager = groupManager;
    }

    @GetMapping("/group/all")
    public Iterable<Group> getAllOrders() {
        return groupManager.findAllGroups();
    }

    @GetMapping("/group")
    public Optional<Group> getGroupById(@RequestParam Long id) {
        return groupManager.findGroupById(id);
    }

    @PostMapping("/admin/group")
    public void addGroup(@RequestParam long user_id, @RequestParam String name, @RequestParam int pic_id) {
        groupManager.addGroup(name, pic_id, user_id);
    }

    @PostMapping("/admin/group/user")
    public void addUserToGroup(@RequestParam long user_id, @RequestParam int group_id) {
        groupManager.addUserToGroup(user_id, group_id);
    }


}
