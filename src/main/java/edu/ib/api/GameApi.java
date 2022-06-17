package edu.ib.api;
import edu.ib.manager.GameManager;
import edu.ib.repo.entity.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class GameApi {

    private GameManager gameManager;

    @Autowired
    public GameApi(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @GetMapping("/game/all")
    public Iterable<Game> getAllGames() {
        return gameManager.findAllGames();
    }

    @GetMapping("/game/find")
    public Game getGameByName(@RequestParam String name) {
        return gameManager.findGameByName(name);
    }

    @GetMapping("/game")
    public Optional<Game> getGameById(@RequestParam Long id) {
        return gameManager.findGameById(id);
    }

    @GetMapping("/usersgames/all")
    public Iterable<Game> getAllGames(@RequestParam Long user_id) {
        return gameManager.selectUserGames(user_id);
    }

    @PostMapping("/usersgames/add")
    public void addUsersGame(@RequestParam Long game_id, Long user_id) {
        gameManager.addUserGames(game_id, user_id);
    }

    @PostMapping("/admin/game")
    public void addGame(@RequestParam String game_name) {
        gameManager.addGame(game_name);
    }

    @DeleteMapping("/usersgames/delete")
    public void deleteUsersGame(@RequestParam Long game_id, Long user_id) {
        gameManager.deleteUserGames(game_id, user_id);
    }

//    @PutMapping("/admin/game")
//    public Game updateGame(@RequestBody Game game){
//        return gameManager.save(game);
//    }

//    @DeleteMapping("/admin/game")
//    public void deleteGroup(@RequestParam Long id){
//        groupManager.deleteGroupById(id);
//    }

//    @PatchMapping("/admin/order")
//    public Group patchGroup(@RequestBody Group Group) {
//        return groupManager.patch(Group);
//    }

}
