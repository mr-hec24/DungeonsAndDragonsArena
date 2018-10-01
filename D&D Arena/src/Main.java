import java.util.Scanner;

public class Main
	{
		static Scanner userInput= new Scanner (System.in);
		static Scanner userInputString;
		static String enter;
		static String userChoice;
		
		static String name;
		static String characterClass;
		static String abilityName;
		static String ability;
		static Weapon weapon;
		static int hp;
		static int armorClass;
		static int speed;
		
		public static void main(String[]args)
		{
			titleScreen();
		}
		
		public static void titleScreen()
		{
			System.out.println("D&D Arena");
			System.out.println("Press enter to start");
			enter = userInput.nextLine();
			int choice;
			
			boolean choosing = true;
			do
				{
					System.out.println("1.) Single player");
					System.out.println("2.) Multiplayer");
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
					System.out.println("1) Barbarian");
					System.out.println("2) Cleric");
					System.out.println("3) Druid");
					System.out.println("4) Rogue");
					System.out.println("5) Wizard");
					System.out.println("6) Warlock");
					System.out.println("7) Sorcerer");
					System.out.println("8) Bard");
					System.out.println("9) Monk");
					System.out.println("10) Paladin");
					
					int choice = userInput.nextInt();
					
					switch (choice)
					{
						case 1:			//Barbarian
								{
									System.out.println("As a Barbarian, you gain the ability Rage, which makes your next turn deal an extra 1d4 of damage.");
									System.out.println("Your weapon is a Battleaxe, which deals 1d10 damage. You have 10 hit points, and your armor class is 12.");
									System.out.println("You can move 4 squares per turn, and your to hit modifier is 2.");
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
									System.out.println("As a Cleric, you gain the ability Heal, which heals 1d6 damage to any ally adjacent to you.");
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
									System.out.println("As a Druid, you gain the ability Vines, which allows you to make one enemy of your choice immobile for one turn");
									System.out.println("Your weapon is a Mace, which deals 1d6 damage. You have 8 hit points, and your armor class is 13.");
									System.out.println("You can move 3 squares per turn.");
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
									System.out.println("You can move 5 squares per turn, and your to hit modifier is 5");
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
									System.out.println("You can move 2 squares per turn, and your to hit modifier is 1.");
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
									System.out.println("As a Cleric, you gain the ability ____, which ____");
									System.out.println("Your weapon is a ___, which deals ___ + _ damage. You have __ hit points, and your armor class is ___.");
									System.out.println("You can move __ squares per turn.");
									System.out.println(" ");
									System.out.println("Would you like to choose this class?");
									System.out.println("1.) Yes");
									System.out.println("2.) No");
									
									choice = userInput.nextInt();
									if (choice == 1)
										{
											choosingClass = false;
											characterClass = "___";
											ability = "___";
											weapon = new Weapon("___", N/A, N/A);
											hp = N/A
											armorClass = N/A;
											speed = N/A;
										}
									break;
								}
						case 10:		//Paladin
								{
									System.out.println("As a Cleric, you gain the ability ____, which ____");
									System.out.println("Your weapon is a ___, which deals ___ + _ damage. You have __ hit points, and your armor class is ___.");
									System.out.println("You can move __ squares per turn.");
									System.out.println(" ");
									System.out.println("Would you like to choose this class?");
									System.out.println("1.) Yes");
									System.out.println("2.) No");
									
									choice = userInput.nextInt();
									if (choice == 1)
										{
											choosingClass = false;
											characterClass = "___";
											ability = "___";
											weapon = new Weapon("___", N/A, N/A);
											hp = N/A
											armorClass = N/A;
											speed = N/A;
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
					String characterName = userInputString.nextLine();
					
					System.out.println("Is " + characterName + " correct?");
					System.out.println("1.) Yes");
					System.out.println("2.) No");
					
					int choice = userInput.nextInt();
					
					if (choice == 1)
						{
							choosingName = false;
						}
				}
			
		}
	}
