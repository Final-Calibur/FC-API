package scripts.fc.api.skills.fishing;

import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;

public enum FishType
{
	SHRIMPS(1, 303, -1),
	LOBSTER(40, 301, -1),
	SWORDFISH(50, 311, -1);
	
	public int reqLvl;
	public int equipId;
	public int fishId;
	
	FishType(int reqLvl, int equipId, int fishId)
	{
		this.reqLvl = reqLvl;
		this.equipId = equipId;
		this.fishId = fishId;
	}
	
	public static FishType getAppropriate()
	{
		final int LVL = Skills.getActualLevel(SKILLS.FISHING);
		
		for(int i = values().length - 1; i >= 0; i--)
			if(values()[i].reqLvl <= LVL)
				return values()[i];
		
		return null;
	}
}
