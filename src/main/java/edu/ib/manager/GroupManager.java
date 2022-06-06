package edu.ib.manager;

import edu.ib.repo.GroupRepo;
import edu.ib.repo.UserRepo;
import edu.ib.repo.entity.Game;
import edu.ib.repo.entity.Group;
import edu.ib.repo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Service
public class GroupManager {

    @PersistenceContext
    private EntityManager em;

    private GroupRepo groupRepo;
    private UserRepo userRepo;

    @Autowired
    public GroupManager(GroupRepo groupRepo, UserRepo userRepo) {
        this.groupRepo = groupRepo;
        this.userRepo = userRepo;
    }

    public Optional<Group> findGroupById(Long id){
        return groupRepo.findById(id);
    }

    public Iterable<Group> findAllGroups(){
        return groupRepo.findAll();
    }
    
    public Iterable<Group> findAllGroupsByUserId(Long userId){
        Iterable<Group> groups = groupRepo.findAll();
        List <Group> usersGroups = new ArrayList<>();
        for (Group group: groups){
            Set <User> groupUsers = group.getUsers();
            for (User user: groupUsers){
                if (user.getId_user().equals(userId)) usersGroups.add(group);
            }
        }
        return usersGroups;
    }

    public Group save(Group group){
        return groupRepo.save(group);
    }

    public void deleteGroupById(Long id){
        groupRepo.deleteById(id);
    }

    public Group patch(Group group){

        Group old = findGroupById(group.getId_group()).orElseThrow(IllegalArgumentException::new);

        if (group.getName() != null) {
            old.setName(group.getName());
        }
        if (group.getUsers() != null) {
            old.setUsers(group.getUsers());
        }
        if (group.getUsers() != null) {
            old.setUsers(group.getUsers());
        }

        return groupRepo.save(old);
    }

    public void addGroup(String name, int pic_id, Long user_id){
        User user = userRepo.findById(user_id).get();
        Set<User> users = new HashSet<>() {
            {
                add(user);
            }};
        Group group1 = new Group(name, pic_id, users);
        groupRepo.save(group1);
    }

    public void addUserToGroup(long user_id, long group_id) {

            List<Integer> columns = em
                    .createNativeQuery("SELECT add_user_to_group(:user_iden, :group_iden);")
                    .setParameter("group_iden", group_id).setParameter("user_iden", user_id)
                    .getResultList();

        }


}
