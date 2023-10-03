# Tiroler-Kistl
Tiroler Kistl, also known as Kistl Tirolese, is a wood board game based on dice and lucky.

## Rules of the game
Each player start with 6 little sticks and the first player who remove all of his sticks win. \
The board consists of a box with six positions, numbered from one to six. The sixth position is a hole and any stick inserted ends up in the box and is out of play. In the other positions the stick remains stuck and can be removed. \
A player rolls the dice until he runs out of sticks or draws one from the board. If he draws one, he passes the die to the next player. \
In the first round each player rolls the die only once. \
When you roll the die, you check the result with the positions on the board. If the position is empty you can insert a stick, otherwise you draw the one present. \
 \
The number of players ranges from 2 to 6

## Why this project
After I played some games with my friends, I wondered how much the order of the players affected the probability of winning. So I decided to simulate it.

## About the java class
The core of the project is the <b>Game</b> class. This class simulate multiple game and keeps track of the results. \
The <b>ThreadGame</b> class allow to run multiple games in parallel. \
The <b>Runner</b> class manage the thread, run the games and print the results.

## The results
I simulate 2'000'000'000 games for each number of player, so in total i simulate 10'000'000'000 games. \
Regardless of the number of players, the first player has the highest chance of winning, followed by the second and so on. \
The following table shows the probability of winning for each player in relation to the total number of players. \
Number of playes | 2 | 3 | 4 | 5 | 6 
:---: | :---: | :---: | :---: | :---: | :---: 
P1 | 56.141% | 39.633% | 30.879% | 25.393% | 21.653%
P2 | 43.859% | 32.562% | 26.114% | 22.022% | 19.097%
P3 | - | 27.804% | 22.784% | 19.425% | 17.093%
P4 | - | - | 20.223% | 17.395% | 15.382%
P5 | - | - | - | 15.765% | 13.964%
P6 | - | - | - | - | 12.811% 

For the data, in the release section there is an excel file I use to analyze the data generated with the code.
