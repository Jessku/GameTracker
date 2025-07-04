--Input data

--PS5/4 games
INSERT INTO GameData (game_name, game_platform) VALUES ('Persona 3: Reload', 'PS5');
INSERT INTO GameData (game_name, game_platform) VALUES ('Elden Ring', 'PS5');
INSERT INTO GameData (game_name, game_platform) VALUES ('Gravity Rush', 'PS4');

--Switch games
INSERT INTO GameData (game_name, game_platform) VALUES ('Triangle Strategy', 'Switch');
INSERT INTO GameData (game_name, game_platform) VALUES ('Pokemon Scarlet', 'Switch');
INSERT INTO GameData (game_name, game_platform) VALUES ('Octopath Traveler', 'Switch');
INSERT INTO GameData (game_name, game_platform) VALUES ('Pokemon Legends: Arceus', 'Switch');

--PC games
INSERT INTO GameData (game_name, game_platform) VALUES ('Date Everything', 'Steam');
INSERT INTO GameData (game_name, game_platform) VALUES ('Kingdom Hearts 1.5 + 2.5', 'Epic Games');
INSERT INTO GameData (game_name, game_platform) VALUES ('Titantfall 2', 'EA Launcher');

--GameCube games
INSERT INTO GameData (game_name, game_platform) VALUES ('Shadow The Hedgehog', 'GameCube');

--PS3 games

--Wii U games

--ItemLists
INSERT INTO ListItems (list_id, game_id, item_status) VALUES (1, 2, 'not_started');