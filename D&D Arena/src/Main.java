import java.util.Scanner;
import java.util.ArrayList;

public class Main
	{
		//LOOK THROUGH ALL OF THE STATIC VARIABLES AND SEE WHICH ONES YOU DONT NEED!!
		
		static Scanner userInput= new Scanner (System.in);
		static Scanner userInputString;
		static String enter;
		static String userChoice;
		
		static boolean canMoveUp = false;
		static boolean canMoveDown = false;
		static boolean canMoveLeft = false;
		static boolean canMoveRight = false;
		static boolean canAttack = false;
		static boolean canUseAbility = false;
		static boolean attacked = false;
		
		static Character turn;
		static Character[] enemiesInRange;
		static boolean hasCharacterInSquare;
		static boolean playerGoesFirst;
		static boolean playing;
		static boolean enemyInRange;
		static boolean playerInRange;
		static boolean usedAbility = false;
		static boolean evading = false;
		static boolean advantage = false;
		static boolean attackTwice = false;
		static Character target;
		static int squaresLeft;
		
		static String name;
		static String characterClass;
		static String abilityName;
		static String ability;
		static Weapon weapon;
		static int hp;
		static int armorClass;
		static int speed;
		
		static ArrayList<Character> players = new ArrayList<Character>();
		static ArrayList<Character> enemies = new ArrayList<Character>();
		static Character[][] arena;
		
		public static void main(String[]args)
		{
			titleScreen();
			askForMapLevel();
			generateEnemies();
			showTheRules();
			gamePlay();
//			printArena();
		}
		
		public static void gamePlay()
		{
			playing = true;
			determineWhoGoesFirstOrSecond();
			printArena();
			
			if (playerGoesFirst == true)
				{
					System.out.println(" ");
					while (playing)
						{
							playersTurn();
							if (players.get(0).getHitPoints() <= 0)
								{
									System.out.println("YOU HAVE DIED! GAME OVER!");
									break;
								}
							else if (enemies.get(0).getHitPoints() <= 0)
								{
									System.out.println("YOU HAVE DEFEATED THE ENEMY!");
									break;
								}
							
							enemysTurn();
							if (players.get(0).getHitPoints() <= 0)
								{
									System.out.println("YOU HAVE DIED! GAME OVER!");
									break;
								}
							else if (enemies.get(0).getHitPoints() <= 0)
								{
									System.out.println("YOU HAVE DEFEATED THE ENEMY!");
									break;
								}
						}
				}
			else
				{
					System.out.println(" ");
					while (playing == true)
						{
							enemysTurn();
							if (players.get(0).getHitPoints() <= 0)
								{
									System.out.println("YOU HAVE DIED! GAME OVER!");
									break;
								}
							else if (enemies.get(0).getHitPoints() <= 0)
								{
									System.out.println("YOU HAVE DEFEATED THE ENEMY!");
									break;
								}
							
							playersTurn();
							if (players.get(0).getHitPoints() <= 0)
								{
									System.out.println("YOU HAVE DIED! GAME OVER!");
									break;
								}
							else if (enemies.get(0).getHitPoints() <= 0)
								{
									System.out.println("YOU HAVE DEFEATED THE ENEMY!");
									break;
								}
						}
				}
		}

		public static void determineWhoGoesFirstOrSecond()
		{
			arena[0][0] = players.get(0);
			arena[arena.length-1][arena[arena.length-1].length-1] = enemies.get(0);
			
			System.out.println("Press enter to roll to see who goes first or second.");
			Scanner userInput2 = new Scanner(System.in);
			String enter = userInput2.nextLine();
			
			int playerRoll = (int)(Math.random()*20)+1;
			int enemyRoll = (int)(Math.random()*20)+1;
			
			if (playerRoll >= enemyRoll)
				{
					System.out.println("You rolled a " + playerRoll + " which beats the enemy's " + enemyRoll);
					System.out.println("Would you like to go first, or second?");
					System.out.println("{1} First");
					System.out.println("{2} Second");
					int userChoice = userInput.nextInt();
					if (userChoice == 1)
						{
							System.out.println("You have chosen to go first.");
							playerGoesFirst = true;
						}
					else
						{
							System.out.println("You have chosen to go second, please wait patiently.");
							playerGoesFirst = false;
						}
					
				}
			else
				{
					System.out.println("You rolled a " + playerRoll + " which doesn't beat the enemy's " + enemyRoll);
					int randomChoice = (int)(Math.random()*2);
					if (randomChoice == 0)
						{
							System.out.println("The enemy has chosen to go second. So you are up.");
							playerGoesFirst = true;
						}
					else
						{
							System.out.println("The enemy has chosen to go first. So please wait patiently.");
							playerGoesFirst = false;
						}
				}
		}
		
		public static void checkIfPlayerIsInRange()
		{
			if (players.get(0).getRow() == enemies.get(0).getRow()) // Checks to see if the Enemy is in the same Row
				{
					
					if (enemies.get(0).getCollumn() == players.get(0).getCollumn() - enemies.get(0).getWeapon().getRange())
						{
							playerInRange = true;
						}
					else if (enemies.get(0).getCollumn() == players.get(0).getCollumn() + enemies.get(0).getWeapon().getRange())
						{
							playerInRange = true;
						}
					else
						{
							playerInRange = false;
						}
				}
			else if (players.get(0).getCollumn() == enemies.get(0).getCollumn()) // Checks if the Enemy is in the same Collumn
				{
					if (enemies.get(0).getRow() == players.get(0).getRow() - enemies.get(0).getWeapon().getRange())
						{
							playerInRange = true;
						}
					else if (enemies.get(0).getRow() == players.get(0).getRow() + enemies.get(0).getWeapon().getRange())
						{
							playerInRange = true;
						}
					else
						{
							playerInRange = false;
						}
				}
			else
				{
					playerInRange = false;
				}
		}
		
		public static void checkIfEnemyIsInRange(Character c)
		{
			enemiesInRange = new Character[enemies.size()];
			int i = 0;
			
			for (Character e : enemies)
				{
					if (c.getRow() == e.getRow()) // Checks to see if the Enemy is in the same Row
						{
							
							if (c.getCollumn() == e.getCollumn() - c.getWeapon().getRange())
								{
									enemyInRange = true;
									enemiesInRange[i] = e;
								}
							else if (c.getCollumn() == e.getCollumn() + c.getWeapon().getRange())
								{
									enemyInRange = true;
									enemiesInRange[i] = e;
								}
							else
								{
									enemyInRange = false;
								}
						}
					else if (c.getCollumn() == e.getCollumn()) // Checks if the Enemy is in the same Collumn
						{
							if (c.getRow() == e.getRow() - c.getWeapon().getRange())
								{
									enemyInRange = true;
									enemiesInRange[i] = e;
								}
							else if (c.getRow() == e.getRow() + c.getWeapon().getRange())
								{
									enemyInRange = true;
									enemiesInRange[i] = e;
								}
							else
								{
									enemyInRange = false;
								}
						}
					else
						{
							enemyInRange = false;
						}
				}

		}
		
		public static void showPlayerChoice(Character c)
		{
			int i = 1;
			
			System.out.println(" ");
			System.out.println(c.getRow() != 0? "{"+i+"} Up": "{1} YOU CAN'T MOVE UP");
			i++;
			System.out.println(c.getRow() != arena.length-1? "{"+i+"} Down": "{2} YOU CAN'T MOVE DOWN");
			i++;
			System.out.println(c.getCollumn() != 0? "{3} Left": "{"+i+"} YOU CAN'T MOVE LEFT");
			i++;
			System.out.println(c.getCollumn() != arena[0].length-1? "{"+i+"} Right": "{4} YOU CAN'T MOVE RIGHT");
			i++;
			
			canMoveUp = c.getRow() != 0? true : false;
			canMoveDown = c.getRow() != arena.length-1? true : false;
			canMoveLeft = c.getCollumn() != 0? true : false;
			canMoveRight = c.getCollumn() != arena[0].length-1? true : false;
			
			checkIfEnemyIsInRange(c);
			if (enemyInRange == true)
				{
					for (Character e : enemiesInRange)
						{
							System.out.println("{" + i + "} Attack " + e.getName());
							i++;
						}
					canAttack = true;
					
					if (usedAbility == false && (c.getClassAbilityName().equals("Rage") || c.getClassAbilityName().equals("Casting") || c.getClassAbilityName().equals("Perform") || c.getClassAbilityName().equals("Martial Arts") || c.getClassAbilityName().equals("Smite")))
						{
							System.out.println("{" + i + "} Use " + c.getClassAbilityName());
							i++;
							canUseAbility = true;
						}
				}
			System.out.println("{"+i+"} End Turn");
		}
		
		public static void lastMovePlayer(Character c, int userChoice)
		{
			switch (enemiesInRange.length)
			{
				case 0:
						{
							if (userChoice == 1)
								{
									squaresLeft = 0;
								}
							break;
						}
				case 1:
						{
							if (userChoice == 1 && canAttack == true)
								{
									System.out.println(c.rollToHit(enemiesInRange[0]));
								}
							else if (userChoice == 2 && canUseAbility == true)
								{
									useAbility(turn);
									usedAbility = true;
								}
							else if (userChoice == 3)
								{
									squaresLeft = 0;
								}
							break;
						}
				case 2:
						{
							if (userChoice == 1 && canAttack == true)
								{
									System.out.println(c.rollToHit(enemiesInRange[0]));
								}
							else if (userChoice == 2 && canAttack == true)
								{
									System.out.println(c.rollToHit(enemiesInRange[1]));
								}
							else if (userChoice == 3 && canUseAbility == true)
								{
									useAbility(turn);
									usedAbility = true;
								}
							else if (userChoice == 4)
								{
									squaresLeft = 0;
								}
							break;
						}
				case 3:
						{
							if (userChoice == 1 && canAttack == true)
								{
									System.out.println(c.rollToHit(enemiesInRange[0]));
								}
							else if (userChoice == 2 && canAttack == true)
								{
									System.out.println(c.rollToHit(enemiesInRange[1]));
								}
							else if (userChoice == 3 && canAttack == true)
								{
									System.out.println(c.rollToHit(enemiesInRange[2]));
								}
							else if (userChoice == 4 && canUseAbility == true)
								{
									useAbility(turn);
									usedAbility = true;
								}
							else if (userChoice == 5)
								{
									squaresLeft = 0;
								}
							break;
						}
				case 4:
						{
							if (userChoice == 1 && canAttack == true)
								{
									System.out.println(c.rollToHit(enemiesInRange[0]));
								}
							else if (userChoice == 2 && canAttack == true)
								{
									System.out.println(c.rollToHit(enemiesInRange[1]));
								}
							else if (userChoice == 3 && canAttack == true)
								{
									System.out.println(c.rollToHit(enemiesInRange[2]));
								}
							else if (userChoice == 4 && canAttack == true)
								{
									System.out.println(c.rollToHit(enemiesInRange[3]));
								}
							else if (userChoice == 5 && canUseAbility == true)
								{
									useAbility(turn);
									usedAbility = true;
								}
							else if (userChoice == 6)
								{
									squaresLeft = 0;
								}
							break;
						}
			}
		}
		
		public static void movePlayer(Character c)
		{
			boolean moving = true;
			
			int userChoice = userInput.nextInt();
			if (userChoice == 1 && canMoveUp == true)
				{
					while (moving)
						{
							System.out.println("How many spaces would like to move up?");
							System.out.println("Available Movement Left: " + squaresLeft);
							int squaresMove = userInput.nextInt();
							if (c.getRow() - squaresMove >= 0)
								{
									squaresLeft -= squaresMove;
									c.setRow(c.getRow() - squaresMove);
									moving = false;
								}
							else
								{
									System.out.println("You can't move that far. Try a smaller number.");
								}
						}
				}
			else if (userChoice == 2 && canMoveDown == true)
				{
					while (moving)
						{
							System.out.println("How many spaces would like to move down?");
							System.out.println("Available Movement Left: " + squaresLeft);
							int squaresMove = userInput.nextInt();
							if (c.getRow() - squaresMove < arena.length-1)
								{
									squaresLeft -= squaresMove;
									c.setRow(c.getRow() + squaresMove);
									moving = false;
								}
							else
								{
									System.out.println("You can't move that far. Try a smaller number.");
								}
						}	
				}
			else if (userChoice == 3 && canMoveLeft == true)
				{
					while (moving)
						{
							System.out.println("How many spaces would like to move left?");
							System.out.println("Available Movement Left: " + squaresLeft);
							int squaresMove = userInput.nextInt();
							if (c.getCollumn() - squaresMove >= 0)
								{
									squaresLeft -= squaresMove;
									c.setCollumn(c.getCollumn() - squaresMove);
									moving = false;
								}
							else
								{
									System.out.println("You can't move that far. Try a smaller number.");
								}
						}
				}
			else if (userChoice == 4 && canMoveRight == true)
				{
					while (moving)
						{
							System.out.println("How many spaces would like to move right?");
							System.out.println("Available Movement Left: " + squaresLeft);
							int squaresMove = userInput.nextInt();
							if (c.getCollumn() - squaresMove < arena.length-1)
								{
									squaresLeft -= squaresMove;
									c.setCollumn(c.getCollumn() + squaresMove);
									moving = false;
								}
							else
								{
									System.out.println("You can't move that far. Try a smaller number.");
								}
						}
				}
			else if (usedAbility == true)
				{
					
				}
			else if (userChoice > 4)
				{
					switch (enemiesInRange.length)
					{
						case 0:
								{
									break;
								}
						case 1:
								{
									if (userChoice == 5 && canAttack == true)
										{
											System.out.println(c.rollToHit(enemiesInRange[0]));
											squaresLeft = 0;
										}
									else if (userChoice == 6 && canUseAbility == true)
										{
											useAbility(turn);
											usedAbility = true;
										}
									else if (userChoice == 7)
										{
											squaresLeft = 0;
										}
									break;
								}
						case 2:
								{
									if (userChoice == 5 && canAttack == true)
										{
											System.out.println(c.rollToHit(enemiesInRange[0]));
											squaresLeft = 0;
										}
									else if (userChoice == 6 && canAttack == true)
										{
											System.out.println(c.rollToHit(enemiesInRange[1]));
											squaresLeft = 0;
										}
									else if (userChoice == 7 && canUseAbility == true)
										{
											useAbility(turn);
											usedAbility = true;
										}
									else if (userChoice == 8)
										{
											squaresLeft = 0;
										}
									break;
								}
						case 3:
								{
									if (userChoice == 5 && canAttack == true)
										{
											System.out.println(c.rollToHit(enemiesInRange[0]));
											squaresLeft = 0;
										}
									else if (userChoice == 6 && canAttack == true)
										{
											System.out.println(c.rollToHit(enemiesInRange[1]));
											squaresLeft = 0;
										}
									else if (userChoice == 7 && canAttack == true)
										{
											System.out.println(c.rollToHit(enemiesInRange[2]));
											squaresLeft = 0;
										}
									else if (userChoice == 8 && canUseAbility == true)
										{
											useAbility(turn);
											usedAbility = true;
										}
									else if (userChoice == 9)
										{
											squaresLeft = 0;
										}
									break;
								}
						case 4:
								{
									if (userChoice == 5 && canAttack == true)
										{
											System.out.println(c.rollToHit(enemiesInRange[0]));
											squaresLeft = 0;
										}
									else if (userChoice == 6 && canAttack == true)
										{
											System.out.println(c.rollToHit(enemiesInRange[1]));
											squaresLeft = 0;
										}
									else if (userChoice == 7 && canAttack == true)
										{
											System.out.println(c.rollToHit(enemiesInRange[2]));
											squaresLeft = 0;
										}
									else if (userChoice == 8 && canAttack == true)
										{
											System.out.println(c.rollToHit(enemiesInRange[3]));
											squaresLeft = 0;
										}
									else if (userChoice == 9 && canUseAbility == true)
										{
											useAbility(turn);
											usedAbility = true;
										}
									else if (userChoice == 10)
										{
											squaresLeft = 0;
										}
									break;
								}
					}
				}
			else
				{
					System.out.println("YOU CAN'T DO THAT! TRY AGAIN!");
				}
		}
		
		public static void determineWhetherToContinueOrToStop(Character c)
		{
			if (squaresLeft > 0)
				{
					System.out.println(" ");
					System.out.println("Where else would you like to move?");
				}
			else
				{
					int i = 1;
					
					if (attacked == false)
						{
							checkIfEnemyIsInRange(c);
							for (Character e : enemiesInRange)
								{
									System.out.println("{" + i + "} Attack " + e.getName());
									i++;
								}
							canAttack = true;
						}

					else if (usedAbility == false && (c.getClassAbilityName().equals("Heal") || c.getClassAbilityName().equals("Vines") || c.getClassAbilityName().equals("Sneak") || c.getClassAbilityName().equals("Summon Dead") || c.getClassAbilityName().equals("Evade")))
						{
							System.out.println("{" + i + "} Use " + c.getClassAbilityName());
							i++;
						}
					System.out.println("{"+i+"} End Turn");
					
					int userChoice = userInput.nextInt();
					
					lastMovePlayer(c, userChoice);
				}
		}
		
		public static void playersTurn()
		{
			for (Character c: players)
				{
					turn = c;
					
					System.out.println(" ");
					System.out.println("Your base speed is " + c.getSpeed() + ".");
					squaresLeft = c.getSpeed();
					usedAbility = false;
					attackTwice = false;
					System.out.println("Where would you like to move?");
			
					while (squaresLeft > 0)
						{
							enemyInRange = false;
							showPlayerChoice(c); //Gives the player choices of where to move, whether it can attack or use an ability, or to end turn
							movePlayer(c);		//Basically does whatever the player has chosen to do
							determineWhetherToContinueOrToStop(c);	//Determines whether the player still has the option of attacking, or to use an ability
						}
				}	
		}
		
		public static void enemysTurn() // Change this so that it goes through all of the enemies in the enemies ArrayList
		{
			squaresLeft = enemies.get(0).getSpeed();
			evading = false;
			
			while (squaresLeft > 0)
				{
					int randomDirection = (int)(Math.random()*4);
					int randomMovement = (int)(Math.random()*squaresLeft) + 1;
					
					checkIfPlayerIsInRange();
					
					if (playerInRange == true)
						{
							System.out.println(enemies.get(0).rollToHit(players.get(0)));
							break;
						}
					
					if (randomDirection == 0 && enemies.get(0).getRow() != 0 && enemies.get(0).getRow() - randomMovement >= 0)
						{
							squaresLeft -= randomMovement;
							enemies.get(0).setRow(enemies.get(0).getRow()-randomMovement);
						}
					else if (randomDirection == 1 && enemies.get(0).getRow() != arena.length-1 && enemies.get(0).getRow() + randomMovement < arena.length)
						{
							squaresLeft -= randomMovement;
							enemies.get(0).setRow(enemies.get(0).getRow()+randomMovement);
						}
					else if (randomDirection == 2 && enemies.get(0).getCollumn() != 0 && enemies.get(0).getCollumn() - randomMovement >= 0)
						{
							squaresLeft -= randomMovement;
							enemies.get(0).setCollumn(enemies.get(0).getCollumn()-randomMovement);
						}
					else if (randomDirection == 3 && enemies.get(0).getCollumn() != arena[0].length-1 && enemies.get(0).getCollumn() + randomMovement < arena[0].length)
						{
							squaresLeft -= randomMovement;
							enemies.get(0).setCollumn(enemies.get(0).getCollumn()+randomMovement);
						}
					
					checkIfPlayerIsInRange();
					
					if (playerInRange == true)
						{
							System.out.println(enemies.get(0).rollToHit(players.get(0)));
							break;
						}
				}
			System.out.println("Enemy Has Moved To " + enemies.get(0).getRow() + ", " + enemies.get(0).getCollumn());
			
		}
		
		public static void useAbility(Character c) //YOU NEED TO WORK ON THIS! IT"S ALMOST DONE
		{
			switch (c.getClassAbilityName())
			{
				case "Rage":
						{
							System.out.println(c.rage(enemies.get(0)));
							break;
						}
				case "Heal":
						{
							System.out.println("Who would you like to heal?");
							int i = 1;
							for (Character options: players)
								{
									System.out.println("{" +i+ "} " + options.getName());
									i++;
								}
							int userChoice = userInput.nextInt();
							
							System.out.println(players.get(0).heal(players.get(userChoice-1)));
							break;
						}
				case "Vines":
						{
							System.out.println("Who would you like to use Vines on?");
							int i = 1;
							for (Character options: enemies)
								{
									System.out.println("{" + i + "} " + options.getName());
									i++;
								}
							int userChoice = userInput.nextInt();
							
							System.out.println(players.get(0).vines(enemies.get(userChoice-1)));
							break;
						}
				case "Sneak":
						{
							squaresLeft = 1;
							usedAbility = true;
							break;
						}
				case "Casting":
						{
							System.out.println("Who would you like to Cast a Spell on?");
							int i = 1;
							for (Character options: enemies)
								{
									System.out.println("{" + i + "} " + options.getName());
									i++;
								}
							int userChoice = userInput.nextInt();
							
							System.out.println(players.get(0).casting(enemies.get(userChoice-1)));
							break;
						}
				case "Summon Dead":
						{
							//I REALLY DONT WANT TO WORK ON THIS...
							break;
						}
				case "Evade":
						{
							System.out.println("If the enemy attacks you on your next turn, you have 75% chance of evading it.");
							evading = true;
							break;
						}
				case "Perform":
						{
							System.out.println("Who would you like to Perform to?");
							int i = 1;
							for (Character options: players)
								{
									System.out.println("{" + i + "} " + options.getName());
									i++;
								}
							int userChoice = userInput.nextInt();
							
							System.out.println(players.get(userChoice-1).getName() + " has advantage on it's next attack.");
							break;
						}
				case "Martial Arts":
						{
							attackTwice = true;
							System.out.println("You will be able to attack twice this turn.");
							break;
						}
				case "Smite":
						{
							// FINISH WORKING ON THE ABILITIES!!!!!
							break;
						}
			}
		}
		
		public static void printArena() //WORK ON THIS AS WELL! (Probably change it to a single Character[]. Hopefully that would make it easier))
		{
			wait(3);
			
			System.out.println("_________________________");
			System.out.println("|     |     |     |     |");
			System.out.println("| " + arena[0][0].getName().substring(0,3) + " |     |     |     |");
			System.out.println("|_____|_____|_____|_____|");
			System.out.println("|     |     |     |     |");
			System.out.println("|     |     |     |     |");
			System.out.println("|_____|_____|_____|_____|");
			System.out.println("|     |     |     |     |");
			System.out.println("|     |     |     |     |");
			System.out.println("|_____|_____|_____|_____|");
			System.out.println("|     |     |     |     |");
			System.out.println("|     |     |     | " + enemies.get(0).getName().substring(0,3) + " |");
			System.out.println("|_____|_____|_____|_____|");
			
			wait(3);
		}
		
//		public static boolean determineIfSquareHasCharacter(int row, int col)
//		{
//			if (arena[row][col].hashCode() == 0)
//				{
//					hasCharacterInSquare = true;
//					return true;
//				}
//			else
//				{
//					hasCharacterInSquare = false;
//					return false;
//				}
//		}
//		
//		
//		public static void printArena()
//		{
//			
//			
//			for (int row = 0; row < arena.length; row++)
//				{
//					for (int col = 0; col < arena[row].length; col++)
//						{
//							
//							if (determineIfSquareHasCharacter(row, col) == false)
//								{
//									System.out.println("______");
//									System.out.println("|     ");
//									System.out.println("|_____");
//								}
//							else
//								{
//									System.out.println("______");
//									System.out.println("|  " + arena[row][col].getName().substring(0,1) + "  ");
//									System.out.println("|_____");
//								}
//						}
//				}
//			
//		}	
			
//			switch (arena.length)
//			{
//				case 4:
//						{
//							System.out.println("_________________________");
//							System.out.println("|     |     |     |     |");
//							System.out.println("|  " + arena[0][0].getName().substring(0,1) + "  |  " + arena[0][1].getName().substring(0,1) + "  |  " + arena[0][2].getName().substring(0,1) + "  |  " + arena[0][3].getName().substring(0,1) + "  |");
//							System.out.println("|_____|_____|_____|_____|");
//							System.out.println("|     |     |     |     |");
//							System.out.println("|  " + arena[1][0].getName().substring(0,1) + "  |  " + arena[1][1].getName().substring(0,1) + "  |  " + arena[1][2].getName().substring(0,1) + "  |  " + arena[1][3].getName().substring(0,1) + "  |");
//							System.out.println("|_____|_____|_____|_____|");
//							System.out.println("|     |     |     |     |");
//							System.out.println("|  " + arena[2][0].getName().substring(0,1) + "  |  " + arena[2][1].getName().substring(0,1) + "  |  " + arena[2][2].getName().substring(0,1) + "  |  " + arena[2][3].getName().substring(0,1) + "  |");
//							System.out.println("|_____|_____|_____|_____|");
//							System.out.println("|     |     |     |     |");
//							System.out.println("|  " + arena[3][0].getName().substring(0,1) + "  |  " + arena[3][1].getName().substring(0,1) + "  |  " + arena[3][2].getName().substring(0,1) + "  |  " + arena[3][3].getName().substring(0,1) + "  |");
//							System.out.println("|_____|_____|_____|_____|");
//							break;
//						}
//				case 5:
//						{
//							System.out.println("_______________________________");
//							System.out.println("|     |     |     |     |     |");
//							System.out.println("|  " + arena[0][0].getName().substring(0,1) + "  |  " + arena[0][1].getName().substring(0,1) + "  |  " + arena[0][2].getName().substring(0,1) + "  |  " + arena[0][3].getName().substring(0,1) + "  |  " + arena[0][4].getName().substring(0,1) + "  |");
//							System.out.println("|_____|_____|_____|_____|_____|");
//							System.out.println("|     |     |     |     |     |");
//							System.out.println("|  " + arena[1][0].getName().substring(0,1) + "  |  " + arena[1][1].getName().substring(0,1) + "  |  " + arena[1][2].getName().substring(0,1) + "  |  " + arena[1][3].getName().substring(0,1) + "  |  " + arena[1][4].getName().substring(0,1) + "  |");
//							System.out.println("|_____|_____|_____|_____|_____|");
//							System.out.println("|     |     |     |     |     |");
//							System.out.println("|  " + arena[2][0].getName().substring(0,1) + "  |  " + arena[2][1].getName().substring(0,1) + "  |  " + arena[2][2].getName().substring(0,1) + "  |  " + arena[2][3].getName().substring(0,1) + "  |  " + arena[2][4].getName().substring(0,1) + "  |");
//							System.out.println("|_____|_____|_____|_____|_____|");
//							System.out.println("|     |     |     |     |     |");
//							System.out.println("|  " + arena[3][0].getName().substring(0,1) + "  |  " + arena[3][1].getName().substring(0,1) + "  |  " + arena[3][2].getName().substring(0,1) + "  |  " + arena[3][3].getName().substring(0,1) + "  |  " + arena[3][4].getName().substring(0,1) + "  |");
//							System.out.println("|_____|_____|_____|_____|_____|");
//							System.out.println("|     |     |     |     |     |");
//							System.out.println("|  " + arena[4][0].getName().substring(0,1) + "  |  " + arena[4][1].getName().substring(0,1) + "  |  " + arena[4][2].getName().substring(0,1) + "  |  " + arena[4][3].getName().substring(0,1) + "  |  " + arena[4][4].getName().substring(0,1) + "  |");
//							System.out.println("|_____|_____|_____|_____|_____|");
//							break;
//						}
//				case 6:
//						{
//							break;
//						}
//				case 7:
//						{
//							break;
//						}
//				case 8:
//						{
//							break;
//						}
//				case 10:
//						{
//							break;
//						}
//			}
//		}
		
		public static void titleScreen()
		{
			System.out.println("D&D Arena");
			System.out.println("Press enter to start");
			enter = userInput.nextLine();
			int choice;
			
			boolean choosing = true;
			do
				{
					System.out.println("{1} Single player");
					System.out.println("{2} Multiplayer");
					choice = userInput.nextInt();
					
					userChoice = choice == 1? "Single Player" : "Multiplayer";
					System.out.println(choice == 1? "Ok": " This addition is not available in this version yet. Check back later for the update.");
					if (choice == 1)
						{
							choosing = false;
						}
				}
			while (choosing);
			
			if (choice == 1)
				{
					singlePlayer();
				}
		}
		
		public static void singlePlayer()
		{
			createCharacter();
		}
		
		public static void createCharacter()
		{	
			System.out.println("Character Creation");
			boolean choosingClass = true;
			while(choosingClass)
				{
					System.out.println("Choose a class:");
					System.out.println("{1} Barbarian");
					System.out.println("{2} Cleric");
					System.out.println("{3} Druid");
					System.out.println("{4} Rogue");
					System.out.println("{5} Wizard");
					System.out.println("{6} Warlock");
					System.out.println("{7} Ranger");
					System.out.println("{8} Bard");
					System.out.println("{9} Monk");
					System.out.println("{10} Paladin");
					
					int choice = userInput.nextInt();
					
					switch (choice)
					{
						case 1:			//Barbarian
								{
									System.out.println("As a Barbarian, you gain the ability Rage, which makes your next turn deal an extra 1d4 of damage.");
									System.out.println("Your weapon is a Battleaxe, which deals 1d10 damage. You have 10 hit points, and your armor class is 12.");
									System.out.println("You can move 4 squares per turn and your to hit modifier is 2.");
									System.out.println(" ");
									System.out.println("Would you like to choose this class?");
									System.out.println("1.) Yes");
									System.out.println("2.) No");
									
									choice = userInput.nextInt();
									if (choice == 1)
										{
											choosingClass = false;
											characterClass = "Barabrian";
											abilityName = "Rage";
											ability = "Deal an extra 1d4 damage on your next turn.";
											weapon = new Weapon("Battleaxe", 10, 2, 1);
											hp = 10;
											armorClass = 12;
											speed = 5;
										}
									
									break;
								}
						case 2:			//Cleric
								{
									System.out.println("As a Cleric, you gain the ability Heal, which heals 1d6 damage to an ally of your choice.");
									System.out.println("Your weapon is a Mace, which deals 1d6 damage. You have 8 hit points, and your armor class is 14.");
									System.out.println("You can move 3 squares per turn and your to hit modifier is 2.");
									System.out.println(" ");
									System.out.println("Would you like to choose this class?");
									System.out.println("1.) Yes");
									System.out.println("2.) No");
									
									choice = userInput.nextInt();
									if (choice == 1)
										{
											choosingClass = false;
											characterClass = "Cleric";
											abilityName = "Heal";
											ability = "Heal 1d6 damage to all adjacent allies.";
											weapon = new Weapon("Mace", 6, 2, 1);
											hp = 8;
											armorClass = 14;
											speed = 3;
										}
									break;
								}
						case 3:			//Druid
								{
									System.out.println("As a Druid, you gain the ability Vines, which allows you to make one enemy of your choice immobile for one turn.");
									System.out.println("Your weapon is a Mace, which deals 1d6 damage. You have 8 hit points, and your armor class is 13.");
									System.out.println("You can move 3 squares per turn and your to hit modifier is 2.");
									System.out.println(" ");
									System.out.println("Would you like to choose this class?");
									System.out.println("1.) Yes");
									System.out.println("2.) No");
									
									choice = userInput.nextInt();
									if (choice == 1)
										{
											choosingClass = false;
											characterClass = "Druid";
											abilityName = "Vines";
											ability = "A Enemy of your choice is immobile for one turn.";
											weapon = new Weapon("Mace", 6, 2, 1);
											hp = 8;
											armorClass = 13;
											speed = 3;
										}
									break;
								}
						case 4:			//Rogue
								{
									System.out.println("As a Rogue, you gain the ability Sneak, which allows you to move 2 squares after you attack.");
									System.out.println("Your weapon is a Dagger, which deals 1d4 damage. You have 10 hit points, and your armor class is 10.");
									System.out.println("You can move 5 squares per turn and your to hit modifier is 5");
									System.out.println(" ");
									System.out.println("Would you like to choose this class?");
									System.out.println("1.) Yes");
									System.out.println("2.) No");
									
									choice = userInput.nextInt();
									if (choice == 1)
										{
											choosingClass = false;
											characterClass = "Rogue";
											abilityName = "Sneak";
											ability = "You can move 2 squares after you attack.";
											weapon = new Weapon("Dagger", 4, 5, 1);
											hp = 10;
											armorClass = 10;
											speed = 5;
										}
									break;
								}
						case 5:			//Wizard
								{
									System.out.println("As a Wizard, you gain the ability Casting, which allows you to cast a random spell at an Enemy and deal 1d6 damage.");
									System.out.println("Your weapon is a Shortsword, which deals 1d5 damage. You have 5 hit points, and your armor class is 14.");
									System.out.println("You can move 2 squares per turn and your to hit modifier is 1.");
									System.out.println(" ");
									System.out.println("Would you like to choose this class?");
									System.out.println("1.) Yes");
									System.out.println("2.) No");
									
									choice = userInput.nextInt();
									if (choice == 1)
										{
											choosingClass = false;
											characterClass = "Wizard";
											abilityName = "Casting";
											ability = "You cast a random speel at an Enemy, dealing 1d6 damage.";
											weapon = new Weapon("Shortword", 5, 1, 1);
											hp = 5;
											armorClass = 14;
											speed = 2;
										}
									break;
								}
						case 6:			//Warlock
								{
									System.out.println("As a Warlock, you gain the ability Summon Dead, which allows you to summon a Zombie that has 2 HP and 5 AC.");
									System.out.println("Your weapon is a Crossbow, which deals 1d6 damage. You have 6 hit points, and your armor class is 12.");
									System.out.println("You can move 2 squares per turn and your to hit modifier is 2");
									System.out.println(" ");
									System.out.println("Would you like to choose this class?");
									System.out.println("1.) Yes");
									System.out.println("2.) No");
									
									choice = userInput.nextInt();
									if (choice == 1)
										{
											choosingClass = false;
											characterClass = "Warlock";
											abilityName = "Summon Dead";
											ability = "Summons a 2 HP, 5 AC Zombie in an adjacent square.";
											weapon = new Weapon("Crossbow", 6, 2, 2);
											hp = 6;
											armorClass = 12;
											speed = 2;
										}
									break;
								}
						case 7:			//Ranger
								{
									System.out.println("As a Ranger, you gain the ability Evade, which gives you a 75% chance of evading an incoming attack of an Enemy.");
									System.out.println("Your weapon is a Bow (with arrows), which deals 1d4 damage. You have 9 hit points, and your armor class is 10.");
									System.out.println("You can move 2 squares per turn and your to hit modifier is 5.");
									System.out.println(" ");
									System.out.println("Would you like to choose this class?");
									System.out.println("1.) Yes");
									System.out.println("2.) No");
									
									choice = userInput.nextInt();
									if (choice == 1)
										{
											choosingClass = false;
											characterClass = "Ranger";
											abilityName = "Evade";
											ability = "75% chance of evading an incoming attack of an Enemy.";
											weapon = new Weapon("Bow", 4, 5, 2);
											hp = 9;
											armorClass = 10;
											speed = 2;
										}
									break;
								}
						case 8:			//Bard
								{
									System.out.println("As a Bard, you gain the ability Perform, which gives one of your allies advantage on their attack.");
									System.out.println("Your weapon is a Longsword, which deals 1d10 damage. You have 7 hit points, and your armor class is 11.");
									System.out.println("You can move 4 squares per turn and your to hit modifier is 2.");
									System.out.println(" ");
									System.out.println("Would you like to choose this class?");
									System.out.println("1.) Yes");
									System.out.println("2.) No");
									
									choice = userInput.nextInt();
									if (choice == 1)
										{
											choosingClass = false;
											characterClass = "Bard";
											abilityName = "Perform";
											ability = "One of your allies advantage on their attack.";
											weapon = new Weapon("Longsword", 10, 2, 1);
											hp = 7;
											armorClass = 11;
											speed = 4;
										}
									break;
								}
						case 9:			//Monk
								{
									System.out.println("As a Monk, you gain the ability Martial Arts, which allows you to attack again in a turn with your weapon.");
									System.out.println("Your weapon is a Javelin, which deals 1d8 damage. You have 6 hit points, and your armor class is 10.");
									System.out.println("You can move 2 squares per turn and your to hit modifier is 2.");
									System.out.println(" ");
									System.out.println("Would you like to choose this class?");
									System.out.println("1.) Yes");
									System.out.println("2.) No");
									
									choice = userInput.nextInt();
									if (choice == 1)
										{
											choosingClass = false;
											characterClass = "Monk";
											abilityName = "Martial Arts";
											ability = "Allows you to attack again with weapon.";
											weapon = new Weapon("Javelin", 8, 2, 2);
											hp = 6;
											armorClass = 10;
											speed = 2;
										}
									break;
								}
						case 10:		//Paladin
								{
									System.out.println("As a Paladin, you gain the ability Smite, which deals 1d8 damage to one of your Enemies.");
									System.out.println("Your weapon is a Greatsword, which deals 1d6 damage. You have 8 hit points, and your armor class is 14.");
									System.out.println("You can move 3 squares per turn and your to hit modifier is 2.");
									System.out.println(" ");
									System.out.println("Would you like to choose this class?");
									System.out.println("1.) Yes");
									System.out.println("2.) No");
									
									choice = userInput.nextInt();
									if (choice == 1)
										{
											choosingClass = false;
											characterClass = "Paladin";
											abilityName = "Smite";
											ability = "Deal 1d8 damage to one of your Enemies";
											weapon = new Weapon("Greatword", 6, 2, 1);
											hp = 8;
											armorClass = 14;
											speed = 3;
										}
									break;
								}
					}
				}
			boolean choosingName = true;
			
			while (choosingName)
				{
					System.out.println("Name Your Character:");
					
					userInputString = new Scanner(System.in);
					name = userInputString.nextLine();
					
					System.out.println("Is " + name + " correct?");
					System.out.println("1.) Yes");
					System.out.println("2.) No");
					
					int choice = userInput.nextInt();
					
					if (choice == 1)
						{
							choosingName = false;
						}
				}
			
			players.add(new Character(name, characterClass, abilityName, ability, weapon, hp, armorClass, speed, 0, 0));
			
			System.out.println(name + " has been added to the campaign!");
		}
	
		public static void askForMapLevel()
		{
			boolean choosingMap = true;
			
			while (choosingMap == true)
				{
					System.out.println("Choose a map level");
					System.out.println("{1} Simple (4 x 4 Grid)");
					System.out.println("{2} Easy (5 x 5 Grid)");
					System.out.println("{3} Medium (6 x 6 Grid)");
					System.out.println("{4} Hard (7 x 7 Grid)");
					System.out.println("{5} Epic (8 x 8 Grid)");
					System.out.println("{6} Legendary (10 x 10 Grid)");
					
					int mapChoice = userInput.nextInt();
					
					switch (mapChoice)
					{
						case 1:
								{
									choosingMap = false;
									arena = new Character[4][4];
									break;
								}
						case 2:
								{
									choosingMap = false;
									arena = new Character[5][5];
									break;
								}
						case 3:
								{
									choosingMap = false;
									arena = new Character[6][6];
									break;
								}
						case 4:
								{
									choosingMap = false;
									arena = new Character[7][7];
									break;
								}
						case 5:
								{
									choosingMap = false;
									arena = new Character[8][8];
									break;
								}
						case 6:
								{
									choosingMap = false;
									arena = new Character[10][10];
									break;
								}
						default:
								{
									System.out.println("Try again.");
									break;
								}
					}
				}

		}
	
		public static void generateEnemies()
		{
			String[] name = {"Hector", "Jeffer", "Ethan", "Wilson", "Andrew Jackson", "Zeke", "Coe", "Kyle"};
			
			for (Character c: players)
				{
					int randomName = (int)(Math.random()*8);
					int randomClass = (int)(Math.random()*10)+1;
					
					switch (randomClass)
					{
						case 1:			//Barbarian
								{
									characterClass = "Barabrian";
									abilityName = "Rage";
									ability = "Deal an extra 1d4 damage on your next turn.";
									weapon = new Weapon("Battleaxe", 10, 2, 1);
									hp = 10;
									armorClass = 12;
									speed = 5;
									break;
								}
						case 2:			//Cleric
								{
									characterClass = "Cleric";
									abilityName = "Heal";
									ability = "Heal 1d6 damage to all adjacent allies.";
									weapon = new Weapon("Mace", 6, 2, 1);										
									hp = 8;
									armorClass = 14;
									speed = 3;
									break;
								}
						case 3:			//Druid
								{
									characterClass = "Druid";
									abilityName = "Vines";
									ability = "A Enemy of your choice is immobile for one turn.";
									weapon = new Weapon("Mace", 6, 2, 1);
									hp = 8;
									armorClass = 13;
									speed = 3;
									break;
								}
						case 4:			//Rogue
								{
									characterClass = "Rogue";
									abilityName = "Sneak";
									ability = "You can move 2 squares after you attack.";
									weapon = new Weapon("Dagger", 4, 5, 1);
									hp = 10;
									armorClass = 10;
									speed = 5;
									break;
								}
						case 5:			//Wizard
								{
									characterClass = "Wizard";
									abilityName = "Casting";
									ability = "You cast a random speel at an Enemy, dealing 1d6 damage.";
									weapon = new Weapon("Shortword", 5, 1, 1);
									hp = 5;
									armorClass = 14;
									speed = 2;
									break;
								}
						case 6:			//Warlock
								{
									characterClass = "Warlock";
									abilityName = "Summon Dead";
									ability = "Summons a 2 HP, 5 AC Zombie in an adjacent square.";
									weapon = new Weapon("Crossbow", 6, 2, 2);
									hp = 6;
									armorClass = 12;
									speed = 2;
									break;
								}
						case 7:			//Ranger
								{
									characterClass = "Ranger";
									abilityName = "Evade";
									ability = "75% chance of evading an incoming attack of an Enemy.";
									weapon = new Weapon("Bow", 4, 5, 2);
									hp = 9;
									armorClass = 10;
									speed = 2;
									break;
								}
						case 8:			//Bard
								{
									characterClass = "Bard";
									abilityName = "Perform";
									ability = "One of your allies advantage on their attack.";
									weapon = new Weapon("Longsword", 10, 2, 1);
									hp = 7;
									armorClass = 11;
									speed = 4;
									break;
								}
						case 9:			//Monk
								{
									characterClass = "Monk";
									abilityName = "Martial Arts";
									ability = "Allows you to attack again with weapon.";
									weapon = new Weapon("Javelin", 8, 2, 2);
									hp = 6;
									armorClass = 10;
									speed = 2;
									break;
								}
						case 10:		//Paladin
								{
									characterClass = "Paladin";
									abilityName = "Smite";
									ability = "Deal 1d8 damage to one of your Enemies";
									weapon = new Weapon("Greatword", 6, 2, 1);
									hp = 8;
									armorClass = 14;
									speed = 3;
									break;
								}
					}
					
					enemies.add(new Character(name[randomName], characterClass, abilityName, ability, weapon, hp, armorClass, speed, /*arena.length-1*/0, 2/*arena[arena.length-1].length-1*/));
				}
		}
	
		public static void showTheRules()
		{
			System.out.println("In D&D Arena, you are in an arena with an Enemy. The objective is to be the last person standing.");
			System.out.println("We start off by deteriming who goes first by rolling a 20 sided die.");
			System.out.println("The person with the highest roll chooses whether to go first or second.");
			System.out.println(" ");
			System.out.println("On your turn, you can do these in this order:");
			System.out.println("Move");
			System.out.println("Use Ability");
			System.out.println("Attack");
			System.out.println(" ");
			System.out.println("You can only move the number of squares equal to your Speed.");
			System.out.println("Once you attack, you aren't able to move again (unless an ability says otherwise).");
			System.out.println(" ");
			System.out.println("You gain different abilities depending on your Class");
			System.out.println("Different abilities give you different tactics to use.");
			System.out.println("You aren't forced to use your ability on your turn though.");
			System.out.println(" ");
			System.out.println("You can only attack Enemies who are in range of your weapon.");
			System.out.println("Your weapon depends on the Class you chose.");
			System.out.println(" ");
			System.out.println("After your turn, it's the enemy's turn, so be wise on what you want to do.");
			System.out.println("The game will be starting shortly");
			
			System.out.println(" ");
			System.out.print("Creating Dungeon");
			for (int i = 0; i < 10; i++)
				{
					try
						{
							Thread.sleep(1000);
							System.out.print(".");
						} catch (InterruptedException e)
						{
							
							e.printStackTrace();
						}
				}
			System.out.println(" ");
		}
		
		public static void wait(int time)
		{
			for (int i = 0; i < time; i++)
				{
					try
						{
							Thread.sleep(1000);
						} catch (InterruptedException e)
						{
							e.printStackTrace();
						}
				}
		}
	
	}
