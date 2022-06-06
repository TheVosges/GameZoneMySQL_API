package edu.ib;

import edu.ib.repo.*;
//import edu.ib.repo.entity.Followers;
import edu.ib.repo.entity.Game;
import edu.ib.repo.entity.Group;
import edu.ib.repo.entity.User;

//import edu.ib.repo.entity.UserGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Component
public class Start {

    @PersistenceContext
    private EntityManager em;

    private final UserRepo userRepo;
   // private final UserGameRepo userGameRepo;
    private final GameRepo gameRepo;
    private final GroupRepo groupRepo;
    private int logged_user;

    @Autowired
    public Start(UserRepo userRepo, GroupRepo groupRepo, GameRepo gameRepo) {//UserGameRepo userGameRepo
        this.userRepo = userRepo;
       // this.userGameRepo = userGameRepo;
        this.gameRepo = gameRepo;
        this.groupRepo = groupRepo;
        this.logged_user = 0;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runExample() {
        User user1 = new User("test@gmail.com", "test123");
        User user2 = new User("test@yacho.com", "example123");
        User user3 = new User("test@wp.com", "admin123");
        User user4 = new User("t", "t");

        userRepo.save(user1);
        userRepo.save(user2);
        userRepo.save(user3);
        userRepo.save(user4);

//        UserGame userGame = new UserGame(exampleList);
//        userGameRepo.save(userGame);
//
//        Followers userFollowers = new Followers(exampleList);
//        followersRepo.save(userFollowers);

        Game game = new Game("ForkNight");
        gameRepo.save(game);


        Set<User> users = new HashSet<>() {
            {
                add(user1);
                add(user2);
            }};

        Group group1 = new Group("Test", 2, users);

        groupRepo.save(group1);

        Boolean runnning = Boolean.TRUE;
        int user_id = 0;
        while (runnning){
            if (user_id != 0){
                System.out.println("Jesteś zalowogwany jako użytkownik " + user_id);
            }
            if (this.logged_user != 0 ) System.out.println("Jesteś zalogowany jako: " + this.logged_user);

            System.out.println("Menu \n" +
                    "1. Zarejestruj użytkownika \n" +
                    "2. Zaloguj się \n" +
                    "3. Wyświetl użytkowników \n" +
                    "4. Dodaj grupę \n" +
                    "5. Dodaj gre \n" +
                    "6. Przypisz gre do uzytkwnika \n" +
                    "7. Pokaz gry do uzytkwnika  \n"
          //          "8. Pokaz tabele gier uzytkwnkow \n"

                    );
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));
            try {
                int option = Integer.parseInt(reader.readLine());
                switch (option){
                    case 1:
                        addUser();
                        break;
                    case 2:
                        System.out.print("Podaj email: ");
                        String email = reader.readLine();
                        System.out.print("Podaj hasło: ");
                        String password = reader.readLine();
//                        String email = "test@gmail.com";
//                        String password = "test123";
                        loginUser(email, password);
                        System.out.println(logged_user);
                        break;
                    case 3:
                        showAllUsers();
                        break;
                    case 4:
                        addGroup();
                        break;
                    case 5:
                        addGame();
                        break;
                    case 6:
                        addUserGames();
                        break;
                    case 7:
                        selectUserGames();
                        break;
//                    case 8:
//                        showAllUserGames();
//                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

    public void loginUser(String v_email,String v_password){
        try {
            List<Integer> columns = em
                    .createNativeQuery("SELECT check_login(:v_email, :v_password);")
                    .setParameter("v_email", v_email).setParameter("v_password", v_password)
                    .getResultList();
//            return columns.get(0);
            this.logged_user = Integer.parseInt(String.valueOf(columns.get(0)));
        } catch (Exception e){
//            return 0;
            this.logged_user = 0;
        }
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

    public void addGroup(){
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            System.out.print("Podaj id użytkownika: ");
            String group_id = reader.readLine();
            User user = userRepo.findById((long)  this.logged_user).get();
            Group group = groupRepo.findById(Long.parseLong(group_id)).get();
            List<Integer> columns = em
                    .createNativeQuery("SELECT add_user_to_group(:group_iden, :user_iden);")
                    .setParameter("group_iden", group.getId_group()).setParameter("user_iden", user.getId_user())
                    .getResultList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createGroup(){
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            System.out.print("Podaj nazwę grupy: ");
            String groupName = reader.readLine();
            System.out.print("Podaj id obrazka: ");
            int pictureId = Integer.parseInt(reader.readLine());
            User user = userRepo.findById((long)  this.logged_user).get();


            Set<User> users = new HashSet<>() {
                {
                    add(user);
                }};

            Group group1 = new Group(groupName, pictureId, users);

            groupRepo.save(group1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getLogged_user() {
        return logged_user;
    }

    public void showAllUsers(){
        Iterable<User> all = userRepo.findAll();
        all.forEach(System.out::println);
    }

    public void addGame(){
        //TODO zwykly insert
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(System.in));

        try {
            System.out.println("Podaj nazwe gry: ");
            String game_name = bufferedReader.readLine();
            Game game = new Game(game_name);
            gameRepo.save(game);
        }catch(IOException e) {e.printStackTrace();}
    }


    public void addUserGames(){
        //TODO zwykly insert
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(System.in));

        try {
            System.out.println("Podaj id gry: ");
            String game_id = bufferedReader.readLine();
            Game game = gameRepo.findById(Long.parseLong(game_id)).get();
            User user = userRepo.findById((long)  this.logged_user).get();


            List<Integer> columns = em
                    .createNativeQuery("SELECT add_game_to_user(:user_iden, :game_iden);")
                    .setParameter("user_iden", user.getId_user()).setParameter("game_iden", game.getId_game())
                    .getResultList();

        }catch(IOException e) {e.printStackTrace();}
    }


    public void selectUserGames(){
        //TODO zwykly select z tabeli z grami na podstawie id usera i tabeli z relacja gra-user
        // 1 wyciagnac wszystkie id gier z games_users dla danego usera
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(System.in));

        try {
            System.out.println("Podaj id usera: ");
            String user_id = bufferedReader.readLine();
            List<Long> userGamesIds = em
                    .createNativeQuery("SELECT id_game FROM users_games where id_user =  ?;")
                    .setParameter(1, user_id)
                    .getResultList();
            System.out.println("id gier uzytkownika: ");
            userGamesIds.forEach(System.out::println);

// 2 wydrukowac
            Game userGame;
            for (long game_id: userGamesIds) {
                userGame = new Game(String.valueOf(em.createNativeQuery("SELECT name FROM games where id_game = :v_id_game);")
                       .setParameter(":v_id_game", game_id).getFirstResult()));
                System.out.println(userGame.getName());

            }



        }
        catch (IOException e){e.printStackTrace();}
    }

}

 //   public void showAllUserGames(){
//       // Iterable<UserGame> all = userGameRepo.findAll();
//      //  all.forEach(System.out::println);
//
////
////        List<Integer> userGames = em
////                .createNativeQuery("SELECT user_id FROM users_games;")
////                .getResultList();
////        System.out.println("id uzytkownikow ktorzy maja gry: ");
////        userGames.forEach(System.out::println);
//    }