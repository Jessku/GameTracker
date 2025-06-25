DROP TABLE IF EXISTS UserData;
CREATE TABLE UserData
(
	user_id SERIAL PRIMARY KEY,
	user_name VARCHAR(255) NOT NULL,
	user_password VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS GameData;
CREATE TABLE GameData
(
	user_id SERIAL,
	game_id SERIAL PRIMARY KEY,
	game_name VARCHAR(255) NOT NULL,
	game_platform VARCHAR(255),
	game_status VARCHAR(1) NOT NULL,
	FOREIGN KEY (user_id) REFERENCES UserData(user_id)
);