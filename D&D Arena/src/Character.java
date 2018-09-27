
public class Character
	{
		private String name;
		private String characterClass;
		private String classAbilityName;
		private String classAbility;
		private Weapon weapon;
		private int hitPoints;
		private int armorClass;
		private int speed;
		
		public Character(String n, String c, String can, String ca, Weapon w, int h, int a, int sp)
		{
			name = n;
			characterClass = c;
			classAbilityName = can;
			classAbility = ca;
			weapon = w;
			hitPoints = h;
			armorClass = a;
			speed = sp;
		}

		public int rollDamage()
		{
			int roll = (int)(Math.random()*this.getWeapon().getDamage())+1;
			
			return roll;
		}
		
		public int rollToHit()
		{
			int roll = (int)(Math.random()*20)+ 1 + this.getWeapon().getToHitModifier();
			
			return roll;
		}
		
		public void takeDamage(int damage)
		{
			this.hitPoints -= damage;
		}
		
		public void heal(int hitpoints)
		{
			this.hitPoints += hitpoints;
		}
		
		
		
		
		
		
		//GETTERS AND SETTERS
		public String getName()
			{
				return name;
			}

		public void setName(String name)
			{
				this.name = name;
			}

		public String getCharacterClass()
			{
				return characterClass;
			}

		public void setCharacterClass(String characterClass)
			{
				this.characterClass = characterClass;
			}

		public String getClassAbilityName()
			{
				return classAbilityName;
			}

		public void setClassAbilityName(String classAbilityName)
			{
				this.classAbilityName = classAbilityName;
			}

		public String getClassAbility()
			{
				return classAbility;
			}

		public void setClassAbility(String classAbility)
			{
				this.classAbility = classAbility;
			}

		public Weapon getWeapon()
			{
				return weapon;
			}

		public void setWeapon(Weapon weapon)
			{
				this.weapon = weapon;
			}

		public int getHitPoints()
			{
				return hitPoints;
			}

		public void setHitPoints(int hitPoints)
			{
				this.hitPoints = hitPoints;
			}

		public int getArmorClass()
			{
				return armorClass;
			}

		public void setArmorClass(int armorClass)
			{
				this.armorClass = armorClass;
			}

		public int getSpeed()
			{
				return speed;
			}

		public void setSpeed(int speed)
			{
				this.speed = speed;
			}
		
	}
