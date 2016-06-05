package scripts.fc.api.interaction;

import org.tribot.api.interfaces.Positionable;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Login;
import org.tribot.api2007.Login.STATE;
import org.tribot.api2007.PathFinding;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.util.DPathNavigator;

public abstract class EntityInteraction
{	
	private static final int DISTANCE_THRESHOLD = 9;
	
	protected String name;
	protected int id;
	protected String action;
	protected int searchDistance;
	protected Positionable position;
	private boolean canReach;
	protected boolean checkPath;
	private boolean walkToPos = true;
	
	public EntityInteraction(String action, String name, int searchDistance)
	{
		this.action = action;
		this.name = name;
		this.searchDistance = searchDistance;
	}
	
	public EntityInteraction(String action, String name, int searchDistance, boolean checkPath)
	{
		this.action = action;
		this.name = name;
		this.searchDistance = searchDistance;
		this.checkPath = checkPath;
	}
	
	public EntityInteraction(String action, int id, int searchDistance)
	{
		this.action = action;
		this.id = id;
		this.searchDistance = searchDistance;
	}
	
	public EntityInteraction(String action, Positionable position)
	{
		this.action = action;
		this.position = position;
	}
	
	public EntityInteraction(String action, String name)
	{
		this.action = action;
		this.name = name;
	}
	
	public boolean execute()
	{
		if(Login.getLoginState() != STATE.INGAME)
			return false;
		
		if(position == null)
			findEntity();
		if(position != null)
		{
			prepareInteraction();
			return interact();
		}
		
		return false;
	}
	
	protected abstract boolean interact();
	
	protected abstract void findEntity();
	
	protected void prepareInteraction()
	{	
		canReach = checkPath ? PathFinding.canReach(position, this instanceof ObjectInteraction) : true;
		
		if(!canReach || (Player.getPosition().distanceTo(position) >= DISTANCE_THRESHOLD && !Player.isMoving()))
			walkToPosition();
		
		if(!position.getPosition().isOnScreen())
		{
			Camera.turnToTile(position);
			
			if(!position.getPosition().isOnScreen())
				walkToPosition();
		}
	}
	
	private void walkToPosition()
	{
		if(!walkToPos)
			return;
		
		if(!canReach)
		{
			DPathNavigator dPath = new DPathNavigator();
			dPath.setMaxDistance(75);
			dPath.traverse(position);	
		}
		else
			Walking.blindWalkTo(position);		
	}
	
	public void setWalkToPos(boolean bool)
	{
		walkToPos = bool;
	}
	
	public void setCheckPath(boolean bool)
	{
		checkPath = bool;
	}
	
}
