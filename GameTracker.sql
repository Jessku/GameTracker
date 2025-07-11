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
	game_name VARCHAR(255) NOT NULL,
	game_platform VARCHAR(255)
);


CREATE TABLE ListData
(
	list_id SERIAL PRIMARY KEY,
	user_id INTEGER,
	list_name VARCHAR(255),
	FOREIGN KEY (user_id) REFERENCES UserData(user_id) ON DELETE CASCADE
	
);


CREATE TABLE ListItems
(
	item_id SERIAL PRIMARY KEY,
	list_id INTEGER,
	game_id INTEGER, 
	item_status status_enum DEFAULT 'not_started',
	item_added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	item_inprogress_at TIMESTAMP,
	item_completed_at TIMESTAMP,
	FOREIGN KEY (list_id) REFERENCES ListData (list_id) ON DELETE CASCADE,
	FOREIGN KEY (game_id) REFERENCES GameData (game_id) ON DELETE CASCADE
	
);

INSERT INTO UserData (user_name, user_password) VALUES ('UserName', 'Password'); --Adjust these as you like for testing
INSERT INTO ListData (user_id, list_name) VALUES (1, 'New List');