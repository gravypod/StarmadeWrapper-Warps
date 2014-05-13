package com.gravypod.warps.commands;

import com.gravypod.starmadewrapper.Sector;
import com.gravypod.starmadewrapper.User;
import com.gravypod.starmadewrapper.plugins.commands.Command;
import com.gravypod.warps.Warp;
import com.gravypod.warps.WarpList;

public class WarpCommand extends Command {
	
	private final WarpList list;
	
	public WarpCommand(WarpList list) {
	
		this.list = list;
	}
	
	@Override
	public void run(String username, User user, String... args) {
		Sector playerLocation = user.getSector();
		Warp warp = list.get(playerLocation);
		
		if (warp == null) {
			pm(username, "There is no warp in your location.");
			return;
		}
		
		//if (!warp.faction.equals("all") && !user.getFactions().contains(warp.faction)) { TODO: add faction support
		//	pm(username, "You cannot use this warp, it belongs to another faction.");
		//	return;
		//}
		
		Sector s;
		
		if (warp.start.equals(playerLocation)) {
			s = warp.end;
		} else {
			s = warp.start;
		}
		
		tp(username, s);
		
		pm(user, "You have been teleported to " + s.toString());
		
	}
	
}
