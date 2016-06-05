package scripts.fc.api.skills.woodcutting.data.locations;

import java.util.ArrayList;
import java.util.List;

import org.tribot.api.General;

import scripts.fc.api.skills.woodcutting.data.LogType;
import scripts.fc.api.skills.woodcutting.data.locations.impl.EastVarrock;
import scripts.fc.api.skills.woodcutting.data.locations.impl.Edgeville;
import scripts.fc.api.skills.woodcutting.data.locations.impl.LumbridgeChurch;
import scripts.fc.api.skills.woodcutting.data.locations.impl.LumbridgeGeneralStore;
import scripts.fc.api.skills.woodcutting.data.locations.impl.NorthDraynor;
import scripts.fc.api.skills.woodcutting.data.locations.impl.NorthEastDraynor;
import scripts.fc.api.skills.woodcutting.data.locations.impl.NorthFalador;
import scripts.fc.api.skills.woodcutting.data.locations.impl.PortSarim;
import scripts.fc.api.skills.woodcutting.data.locations.impl.Rimmington;
import scripts.fc.api.skills.woodcutting.data.locations.impl.SouthFalador;
import scripts.fc.api.skills.woodcutting.data.locations.impl.VarrockCastle;
import scripts.fc.api.skills.woodcutting.data.locations.impl.VarrockChurch;
import scripts.fc.api.skills.woodcutting.data.locations.impl.WestLumbridge;
import scripts.fc.project_tesla.utils.Utils;

public enum WcLocation
{
	LUMBRIDGE_GENERAL_STORE(new LumbridgeGeneralStore(), 2),
	PORT_SARIM(new PortSarim(), 1),
	NORTH_FALADOR(new NorthFalador(), 5),
	NORTH_DRAYNOR(new NorthDraynor(), 4),
	NORTH_EAST_DRAYNOR(new NorthEastDraynor(), 3),
	RIMMINGTON(new Rimmington(), 5),
	SOUTH_FALADOR(new SouthFalador(), 4),
	EDGEVILLE(new Edgeville(), 2),
	VARROCK_CASTLE(new VarrockCastle(), 2),
	VARROCK_CHURCH(new VarrockChurch(), 1),
	VARROCK_EAST(new EastVarrock(), 5),
	WEST_LUMBRIDGE(new WestLumbridge(), 3),
	LUMBRIDGE_CHURCH(new LumbridgeChurch(), 1);
	
	WoodcuttingLocation loc;
	int weight;
	
	WcLocation(WoodcuttingLocation loc, int weight)
	{
		this.loc = loc;
		this.weight = weight;
	}
	
	public static WoodcuttingLocation getAppropriate(LogType log)
	{
		List<WcLocation> appropriateLocs = new ArrayList<>();
		
		General.println("Getting appropriate location for logType " + log);
		int sumOfWeights = 0;
		
		for(WcLocation loc : values())
		{
			if(loc.loc.getSupported().contains(log) && loc.loc.hasRequirements())
			{
				appropriateLocs.add(loc);
				sumOfWeights += loc.weight;
			}
		}
		
		WoodcuttingLocation[] locs = new WoodcuttingLocation[sumOfWeights];
		int currentIndex = 0;
		for(WcLocation l : appropriateLocs)
		{
			for(int i = currentIndex; i < currentIndex + l.weight; i++)
				locs[i] = l.loc;
			
			currentIndex += l.weight;
		}
		
		return locs[Utils.random(0, locs.length - 1)];
	}
}
