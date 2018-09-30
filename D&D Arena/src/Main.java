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
									System.out.println("Your weapon is a Battleaxe, which deals 1d10 + 2 damage. You have 10 hit points, and your armor class is 12.");
									System.out.println("You can move 5 squares per turn.");
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
									System.out.println("Your weapon is a Mace, which deals 1d6 + 2 damage. You have 8 hit points, and your armor class is 14.");
									System.out.println("You can move 3 squares per turn.");
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
											weapon = new Weapon("Mace", 6, 2);
											hp = 8;
											armorClass = 14;
											speed = 3;
										}
									break;
								}
						case 3:			//Druid
								{
									System.out.println("As a Druid, you gain the ability Shapeshift, which allows you to change your form into an owlbear.");
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
											abilityName = "";
											ability = "___";
											weapon = new Weapon("___", N/A, N/A);
											hp = N/A
											armorClass = N/A;
											speed = N/A;
										}
									break;
								}
						case 4:			//Rogue
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
						case 5:			//Wizard
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
						case 6:			//Warlock
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
						case 7:			//Sorcerer
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
						case 8:			//Bard
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
