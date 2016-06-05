package scripts.fc.api.skills.woodcutting.data.locations.impl;

import java.util.Arrays;
import java.util.List;

import org.tribot.api.interfaces.Positionable;
import org.tribot.api2007.types.RSTile;

import scripts.fc.api.skills.woodcutting.data.LogType;
import scripts.fc.api.skills.woodcutting.data.locations.BasicWoodcuttingLocation;

public class NorthFalador extends BasicWoodcuttingLocation
{

	@Override
	public List<LogType> getSupported()
	{
		return Arrays.asList(LogType.NORMAL, LogType.OAK);
	}

	@Override
	public String getName()
	{
		return "North Falador";
	}

	@Override
	public Positionable centerTile()
	{
		return new RSTile(2954, 3408, 0);
	}

	@Override
	public int getRadius()
	{
		return 15;
	}

}
