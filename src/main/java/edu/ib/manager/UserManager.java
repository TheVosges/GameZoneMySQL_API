package edu.ib.manager;

import edu.ib.repo.UserRepo;
import edu.ib.repo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


@Service
public class UserManager {


    private UserRepo userRepo;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public UserManager(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Integer loginUser(String v_email,String v_password){
        try {
            List<Integer> columns = em
                    .createNativeQuery("SELECT check_login(:v_email, :v_password);")
                    .setParameter("v_email", v_email).setParameter("v_password", v_password)
                    .getResultList();
            return Integer.parseInt(String.valueOf(columns.get(0)));
//            this.logged_user = Integer.parseInt(String.valueOf(columns.get(0)));
        } catch (Exception e){
            return 0;
//            this.logged_user = 0;
        }
    }

    public void addUser(String email, String password){
            User user = new User(email, password);
            userRepo.save(user);
    }


    public Iterable<User> findAll(){
        return userRepo.findAll();
    }
//
//    public User save(UserDTO user){
//        return userRepo.save(user);
//    }

}
