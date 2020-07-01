## Programming Challenge: 5-in-a-Row

5-in-a-Row, a variation of the famous Connect Four game, is a two-player connection game
in which the players first choose a color and then take turns dropping colored discs from the
top into a nine-column, six-row vertically suspended grid. The pieces fall straight down,
occupying the next available space within the column. The objective of the game is to be the
first to form a horizontal, vertical, or diagonal line of five of one's own discs.

### Running Application
```
mvn spring-boot:run
```

The application will be running at http://localhost:8091

### Playing The Game
In order to play the game, you will need to open 2 browser windows or tabs. 
Both players must click Start Game, and enter this name. 

As the game progresses, the game state messages will appear to the other connected user.

### Further Details 
Java application uses Spring Boot Messaging Stomp Web Sockets
HTML front end using stomp-websockets and sockjs-client

### Author 
Ruaidhri Hallinan
