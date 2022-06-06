package edu.ib.api;

import edu.ib.manager.GroupManager;
import edu.ib.manager.UserManager;
import edu.ib.repo.entity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    public Iterable<Group> getAllGroups() {
        return groupManager.findAllGroups();
    }

    @GetMapping("/group/user/")
    public Iterable<Group> getAllGroupsOfUser(@RequestParam long user_id) {
        return groupManager.findAllGroupsByUserId(user_id);
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
