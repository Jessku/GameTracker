# GameTracker
This application will allow users to add games to a list and mark their progress on them

# Setup
- You will see query files *'GameTracker.sql'* and *'GameData.sql'* in this repository. In your database application of choice you will run both queries in the exact order. These files as is are made with PSQL syntax so you may have to edit some of the lines to fit your database's syntax (It is HIGHLY RECCOMENDED that you create a specific database for this data).
- The *'ConnectionManager.java'* file stores database credentials. Edit this file to fit your database's credentials where necessary. 
- Then you'd open the *'GameTracker'* folder in an IDE of your choice (This project was made using VSCode btw) and run the *Main.java* file. **DO NOT RUN THE App.java file**
