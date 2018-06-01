==================BattleShip Game===================

Steps to Run the BattleShip Application:
1) Configure the INPUTFILE.NAME and INPUTFILE.PATH properties in config.properties file under src/main/resources.
2) Run BattleShipClient(com.battleship.client) as Java Application to run the app.

Modules:
com.battleship.client=Starting point of application
com.battleship.validation = To handle input validations
com.battleship.service = To handle business logic
com.battleship.util= To handle utility functions such as reading from file.
com.battleship.model= contains all models

Models:
BattleField:contains battleField information such as width, height and noOfShips in each battleField
Player:Contains player information such as name and List of Ships
Ship:Contains shipHeight, shipWidth, shipType and List of ShipCells
ShipCell:contains health and position of shipCell

OOP Modelling:
Player<>-----Ship<>----ShipCell

Sample Input:
5 E
2
Q 1 1 A1 B2
P 2 1 D4 C3
A1 B2 B2 B3
A1 B2 B3 A1 D1 E1 D4 D4 D5 D5

Sample Output:
0 [main] INFO com.battleship.service.BattleShipService  - Player-1 fires a missile with target A1 which got miss
1 [main] INFO com.battleship.service.BattleShipService  - Player-2 fires a missile with target A1 which got hit
1 [main] INFO com.battleship.service.BattleShipService  - Player-2 fires a missile with target B2 which got miss
1 [main] INFO com.battleship.service.BattleShipService  - Player-1 fires a missile with target B2 which got hit
1 [main] INFO com.battleship.service.BattleShipService  - Player-1 fires a missile with target B2 which got hit
1 [main] INFO com.battleship.service.BattleShipService  - Player-1 fires a missile with target B3 which got miss
1 [main] INFO com.battleship.service.BattleShipService  - Player-2 fires a missile with target B3 which got miss
1 [main] INFO com.battleship.service.BattleShipService  - Player-1 has no more missiles left to launch.
1 [main] INFO com.battleship.service.BattleShipService  - Player-2 fires a missile with target A1 which got hit
2 [main] INFO com.battleship.service.BattleShipService  - Player-2 fires a missile with target D1 which got miss
2 [main] INFO com.battleship.service.BattleShipService  - Player-1 has no more missiles left to launch.
2 [main] INFO com.battleship.service.BattleShipService  - Player-2 fires a missile with target E1 which got miss
2 [main] INFO com.battleship.service.BattleShipService  - Player-1 has no more missiles left to launch.
2 [main] INFO com.battleship.service.BattleShipService  - Player-2 fires a missile with target D4 which got hit
2 [main] INFO com.battleship.service.BattleShipService  - Player-2 fires a missile with target D4 which got miss
2 [main] INFO com.battleship.service.BattleShipService  - Player-1 has no more missiles left to launch.
2 [main] INFO com.battleship.service.BattleShipService  - Player-2 fires a missile with target D5 which got hit
2 [main] INFO com.battleship.client.BattleShipClient  - Player-2 won the game