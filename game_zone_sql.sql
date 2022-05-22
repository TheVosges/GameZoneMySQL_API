create database game_zone;
use game_zone;

select * from users;

select * from groups_database;
select * from groups_users;

select * from games;
select * from users_games;


SET GLOBAL FOREIGN_KEY_CHECKS=0;

DELIMITER ;
drop procedure add_game;
DELIMITER //
CREATE PROCEDURE add_game(in user_iden long, in game_iden long)
BEGIN

	INSERT into users_games(id_user, id_game) values (user_iden, game_iden);

END; //

DELIMITER //
create function add_game_to_user(user_iden long, game_iden long)
returns long
DETERMINISTIC
begin

   INSERT into users_games(id_user, id_game) values (user_iden, game_iden);

   return 1;
end; //


DELIMITER //
create function add_user_to_group(user_iden long, group_iden long)
returns long
DETERMINISTIC
begin

   INSERT into groups_users(id_group, id_user) values (group_iden, user_iden);

   return 1;
end; //


DELIMITER //
create function check_login(v_email varchar(150), v_password  varchar(150))
returns long
DETERMINISTIC
begin

   declare result_local long;
   
   set result_local = (select id_user from users u where u.email=v_email and u.password=v_password);

   return result_local;
end; //

DELIMITER ;
