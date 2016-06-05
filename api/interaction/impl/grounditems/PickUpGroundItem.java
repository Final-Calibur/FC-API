package scripts.fc.api.interaction.impl.grounditems;

import org.tribot.api.DynamicClicking;

import scripts.fc.api.interaction.GroundItemInteraction;

public class PickUpGroundItem extends GroundItemInteraction
{

	public PickUpGroundItem(String name)
	{
		super("Take", name);
	}

	@Override
	protected boolean interact()
	{
		return DynamicClicking.clickRSGroundItem(groundItem, "Take");
	}

}
