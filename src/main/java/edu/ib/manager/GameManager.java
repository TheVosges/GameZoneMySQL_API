package edu.ib.manager;

import edu.ib.repo.GameRepo;
import edu.ib.repo.UserRepo;
import edu.ib.repo.entity.Game;
import edu.ib.repo.entity.User;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameManager {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private GameRepo gameRepo;
    private UserRepo userRepo;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public GameManager(GameRepo gameRepo, UserRepo userRepo) {
        this.gameRepo = gameRepo;
        this.userRepo = userRepo;
    }

    public void addGame(String game_name) {
        Game game = new Game(game_name);
        gameRepo.save(game);
    }

    public void removeGame(Long game_id) {
        //gameRepo.findById(game_id);
        //gameRepo.delete(gameRepo.findById(game_id)); //todo
    }

    public void addUserGames(Long game_id, Long logged_user_id) {

        Game game = gameRepo.findById(game_id).get();
        User user = userRepo.findById(logged_user_id).get();
        List<Integer> columns = em
                .createNativeQuery("SELECT add_game_to_user(:user_iden, :game_iden);")
                .setParameter("user_iden", user.getId_user()).setParameter("game_iden", game.getId_game())
                .getResultList();
    }

    //tego chyba nie przetestowalismy w konsoli czy dziala wgl
    public Iterable<Game> selectUserGames(Long user_id) {

//        List<Integer> userGamesIds = em
//                .createNativeQuery("SELECT * FROM users_games where id_user = ?;")
//                .setParameter(1, user_id)
//                .getResultList();

//        List <Integer> list = jdbcTemplate.queryForList("SELECT id_game FROM users_games where id_user = 1;", Integer.class);

        List<BigInteger> list = em
                .createNativeQuery("SELECT id_game FROM users_games where id_user = ?;")
                .setParameter(1, user_id)
                .getResultList();

//        List<Object[]> results = list.getResultList();

        ArrayList<Game> userGames = new ArrayList<>();
        Game userGame;

        System.out.println(list.get(0));

        int len = list.size();

        for (BigInteger game_id : list) {
//tutaj mozna dac foreach findgamebyid i wpakowac w arrayliste userGames
//            Integer game_int = list.get(i);
            Optional<Game> game = findGameById(Long.parseLong(String.valueOf(game_id.toString())));
            userGames.add(game.get());
//
//            userGame = new Game(String.valueOf(em.createNativeQuery("SELECT name FROM games where id_game = :v_id_game);")
//                    .setParameter(":v_id_game", game_id).getFirstResult()));
           // userGames.add(userGame);
//           // userGames.add(game);
        }
        return userGames;
    }

    public Optional<Game> findGameById(Long id) {
        return gameRepo.findById(id);
    }

    public Iterable<Game> findAllGames() {
        return gameRepo.findAll();
    }

    public Game findGameByName(String game_name) {
        Iterable<Game> games = gameRepo.findAll();
        for (Game game: games){
            if (game.getName().equals(game_name)){
                return game;
            }
        }
        return null;
    }

//        public UserDTO save(UserDTO user){
//            return userRepo.save(user);
//        }

}
