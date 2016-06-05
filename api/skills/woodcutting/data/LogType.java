package scripts.fc.api.skills.woodcutting.data;

import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;

import scripts.fc.api.utils.PriceUtils;
import scripts.fc.missions.tesla_wcer.tasks.TreeProgression;

public enum LogType
{
	NORMAL(1, 1511, "Tree"),
	OAK(15, 1521, "Oak"),
	WILLOW(30, 1519, "Willow"),
	YEW(60, 1515, "Yew");
	
	public int lvl;
	public int itemId;
	public int price;
	public String objName;
	
	LogType(int lvl, int itemId, String name)
	{
		this.lvl = lvl;
		this.itemId = itemId;
		price = PriceUtils.getPrice(itemId);
		objName = name;
	}
	
	public static LogType getAppropriate()
	{
		for(int i = values().length - 1; i >= 0; i--)
		{
			LogType t = values()[i];
			final int WC_LEVEL = Skills.getActualLevel(SKILLS.WOODCUTTING);
			if((t != WILLOW || WC_LEVEL >= TreeProgression.WILLOW_LVL) && t.lvl <= WC_LEVEL)
				return t;
		}
		
		return NORMAL;
	}
	
	public static LogType getBest()
	{
		for(int i = LogType.values().length - 1; i >= 0; i--)
		{
			if(LogType.values()[i].lvl <= Skills.getActualLevel(SKILLS.WOODCUTTING))
				return LogType.values()[i];
		}
		
		return NORMAL;
	}
}
