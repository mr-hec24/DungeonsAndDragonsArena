
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
		private int row;
		private int collumn;
		
		public Character(String n, String c, String can, String ca, Weapon w, int h, int a, int sp, int r, int col)
		{
			name = n;
			characterClass = c;
			classAbilityName = can;
			classAbility = ca;
			weapon = w;
			hitPoints = h;
			armorClass = a;
			speed = sp;
			row = r;
			collumn = col;
		}
		

		
		public int rollForDamage()
		{
			int damage = (int)(Math.random()*this.getWeapon().getDamage())+1;
			return damage;
		}
		
		public String dealDamage(Character c)
		{
			int damage = rollForDamage();
			c.takeDamage(damage);
			
			return this.getName() + " dealt " + damage + " damage to " + c.getName() + ".";
		}
		
		public boolean hits(Character c)
		{
			int roll = (int)(Math.random()*20)+ 1 + this.getWeapon().getToHitModifier();
			if (roll > c.getArmorClass())
				{	
					
					return true;
				}
			else
				{
					return false;
				}
		}
		
		public String rollToHit(Character c)
		{
			int roll = (int)(Math.random()*20)+ 1 + this.getWeapon().getToHitModifier();
			if (roll > c.getArmorClass())
				{	
					
					return this.getName() + " hit " + c.getName() + " with " + this.getName() + "'s " + this.getWeapon().getName() + ". " + dealDamage(c);
				}
			else
				{
					return this.getName() + " came up to attack " + c.getName() +" with " + this.getName() + "'s " + this.getWeapon().getName() + " but " + this.getName() + " missed.";
				}
		}
		
		public void takeDamage(int damage)
		{
			this.hitPoints -= damage;
		}
		
		public void getHealed(int hitpoints)
		{
			this.hitPoints += hitpoints;
		}
		
		public String rage(Character c)
		{
			int extraDamage = (int)(Math.random()*4)+1;
			
			c.takeDamage(extraDamage);
			
			return "Due to your rage, you dealt " + extraDamage + " more damage.";
		}
		
		public String heal(Character c1)
		{
			int heal = (int)(Math.random()*6)+1;
			
			c1.getHealed(heal);
			
			return c1.getName() + " was healed " + heal + " damage.";
		}
		
		public String vines(Character c)
		{
			c.setSpeed(0);
			
			return c.getName() + " can't move for it's next turn.";
		}
		
		public String casting(Character c)
		{
			String[] spells = {"Fire Ball","Ice Shards","Lightning Bolt","Hail Storm"};
			int randomSpell = (int)(Math.random()*spells.length); 
			
			int roll = (int)(Math.random()*20) + 1;
			if (roll > c.getArmorClass())
				{	
					int damage = (int)(Math.random()*6)+1;
			
					return "You cast " + spells[randomSpell] + " to " + c.getName() + " which dealt " + damage + " damage.";
				}
			else
			
				{
					return "You attempted to cast " + spells[randomSpell] + " to " + c.getName() + ", but you missed."; 
				}
			
		}
		

		
//		public void useAbility()
//		{
//			switch(this.getClassAbilityName())
//			{
//				case "Rage":
//						{
//							rage();
//							break;
//						}
//				case "Heal":
//						{
//							
//							break;
//						}
//				case "Vines":
//						{
//							break;
//						}
//				case "Sneak":
//						{
//							break;
//						}
//				case "Casting":
//						{
//							break;
//						}
//				case "Summon Dead":
//						{
//							break;
//						}
//				case "Evade":
//						{
//							break;
//						}
//				case "Perform":
//						{
//							break;
//						}
//				case "Martial Arts":
//						{
//							break;
//						}
//				case "Smite":
//						{
//							break;
//						}
//			}
//		}
		
		
		
		
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

		public int getRow()
			{
				return row;
			}

		public void setRow(int row)
			{
				this.row = row;
			}

		public int getCollumn()
			{
				return collumn;
			}

		public void setCollumn(int collumn)
			{
				this.collumn = collumn;
			}
		
	}
