--How the game list would print to the user <UserListItem>
SELECT l.list_name, i.item_id, g.game_name, g.game_platform, i.item_status
FROM ListItems i
INNER JOIN GameData g ON i.game_id = g.game_id
INNER JOIN ListData l ON i.list_id = l.list_id
INNER JOIN UserData u ON l.user_id = u.user_id
WHERE u.user_id = 1
ORDER BY i.item_added_at;

--SELECT * FROM ListItems

SELECT l.list_id, l.list_name 
FROM ListData l
INNER JOIN UserData u ON l.user_id = u.user_id
WHERE u.user_id = 2
ORDER BY l.list_id
--SELECT * FROM UserData
-- SELECT * FROM ListData