package scripts.fc.api.skills.woodcutting.data.locations;

import org.tribot.api2007.WebWalking;

import scripts.fc.api.generic.FCConditions;

public abstract class BasicWoodcuttingLocation extends WoodcuttingLocation
{

	@Override
	public boolean goTo()
	{
		return WebWalking.walkTo(centerTile.getPosition(), FCConditions.withinDistanceOfTile(centerTile, 8), 600);
	}
	
	@Override
	public boolean isDepositBox()
	{
		return false;
	}

	@Override
	public boolean goToBank()
	{
		return WebWalking.walkToBank();
	}

	@Override
	public boolean hasRequirements()
	{
		return true;
	}
}
