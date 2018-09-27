
public class Weapon
	{
		private String name;
		private int damage;
		private int toHitModifier;
		private int range;
		
		public Weapon(String n, int dmg, int hitMod, int r)
		{
			name = n;
			damage = dmg;
			toHitModifier = hitMod;
			range = r;
		}

		public String getName()
			{
				return name;
			}

		public void setName(String name)
			{
				this.name = name;
			}

		public int getDamage()
			{
				return damage;
			}

		public void setDamage(int damage)
			{
				this.damage = damage;
			}

		public int getToHitModifier()
			{
				return toHitModifier;
			}

		public void setToHitModifier(int toHitModifier)
			{
				this.toHitModifier = toHitModifier;
			}

		public int getRange()
			{
				return range;
			}

		public void setRange(int range)
			{
				this.range = range;
			}

	}
