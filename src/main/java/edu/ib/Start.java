package edu.ib;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class Start {

    private UserRepo userRepo;
    private UserGameRepo userGameRepo;
    private FollowersRepo followersRepo;
    private GroupRepo groupRepo;

    @Autowired
    public Start(UserRepo userRepo, UserGameRepo userGameRepo, FollowersRepo followersRepo, GroupRepo groupRepo) {
        this.userRepo = userRepo;
        this.userGameRepo = userGameRepo;
        this.followersRepo = followersRepo;
        this.groupRepo = groupRepo;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runExample() {
//        User user1 = new User("test@gmail.com", "test123");
//        User user2 = new User("test@yacho.com", "example123");
//        User user3 = new User("test@wp.com", "admin123");
//
//
//        userRepo.save(user1);
//        userRepo.save(user2);
//        userRepo.save(user3);
//
//        List<Long> exampleList = new ArrayList<>();
//        exampleList.add(1L);
//        exampleList.add(2L);
//        exampleList.add(3L);
//
//        UserGame userGame = new UserGame(exampleList);
//        userGameRepo.save(userGame);
//
//        Followers userFollowers = new Followers(exampleList);
//        followersRepo.save(userFollowers);
//
//
//        Set<User> users = new HashSet<>() {
//            {
//                add(user1);
//                add(user2);
//            }};
//
//        Group group1 = new Group("Grupa tesotwa", 2L, users);
//
//        groupRepo.save(group1);

        Boolean runnning = Boolean.TRUE;
        int user_id = 0;
        while (runnning){
            if (user_id != 0){
                System.out.println("Jesteś zalowogwany jako użytkownik " + user_id);
            }
            System.out.println("Menu \n" +
                    "1. Zarejestruj użytkownika \n" +
                    "2. Zaloguj się \n" +
                    "3. Wyświetl użytkowników"
                    );
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));
            try {
                int option = Integer.parseInt(reader.readLine());
                switch (option){
                    case 1:
                        addUser();
                    case 2:
                        System.out.print("Podaj email: ");
                        String email = reader.readLine();
                        System.out.print("Podaj hasło: ");
                        String password = reader.readLine();
//                        loginUser(email, password);
                    case 3:
                        showAllUsers();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

    public void loginUser(String email, String password){

    }

    public void addUser(){
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            System.out.print("Podaj email: ");
            String email = reader.readLine();
            System.out.print("Podaj hasło: ");
            String password = reader.readLine();
            User user = new User(email, password);
            userRepo.save(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAllUsers(){
        Iterable<User> all = userRepo.findAll();
        all.forEach(System.out::println);
    }


}
