--Drops
DROP TABLE IF EXISTS ListItems;
DROP TABLE IF EXISTS ListData;
DROP TABLE IF EXISTS GameData;
DROP TABLE IF EXISTS UserData;
DROP TYPE IF EXISTS status_enum;

CREATE TYPE status_enum AS ENUM ('not_started', 'in_progress', 'completed');


CREATE TABLE UserData
(
	user_id SERIAL PRIMARY KEY,
	user_name VARCHAR(255) NOT NULL,
	user_password VARCHAR(255) NOT NULL
);


CREATE TABLE GameData
(
	game_id SERIAL PRIMARY KEY,
	--user_id INTEGER,
	game_name VARCHAR(255) NOT NULL,
	game_platform VARCHAR(255)
	--FOREIGN KEY (user_id) REFERENCES UserData(user_id) ON DELETE CASCADE
);


CREATE TABLE ListData
(
	list_id SERIAL PRIMARY KEY,
	user_id INTEGER,
	FOREIGN KEY (user_id) REFERENCES UserData(user_id) ON DELETE CASCADE
	
);


CREATE TABLE ListItems
(
	li_id SERIAL PRIMARY KEY,
	list_id INTEGER,
	game_id INTEGER,
	li_status status_enum NOT NULL,
	li_added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	li_inprogress_at TIMESTAMP,
	li_completed_at TIMESTAMP,
	FOREIGN KEY (list_id) REFERENCES ListData (list_id) ON DELETE CASCADE,
	FOREIGN KEY (game_id) REFERENCES GameData (game_id) ON DELETE CASCADE
	
);

INSERT INTO UserData (user_name, user_password) VALUES ('UserName', 'Password'); --Adjust these as you like for testing