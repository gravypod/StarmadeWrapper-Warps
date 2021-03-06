package com.gravypod.warps.commands;

import com.gravypod.starmadewrapper.Sector;
import com.gravypod.starmadewrapper.User;
import com.gravypod.starmadewrapper.plugins.commands.Command;
import com.gravypod.warps.Warp;
import com.gravypod.warps.WarpList;
import com.gravypod.warps.WarpOverrideException;

public class AddWarpCommand extends Command {
	
	private final WarpList list;
	
	public AddWarpCommand(WarpList list) {
	
		this.list = list;
	}
	
	// TODO: add faction support
	@Override
	public void run(String username, User user, String... args) {
	
		if (!isAdmin(username)) {
			pm(username, "You are not allowed to use this command.");
			return;
		}
		
		if (args.length != 6) { // TODO: allow the importing of saved sectors
								// into these denoted sectors (EX: import a warp
								// gate into here)
			pm(username, "Wrong args!");
			pm(username, getHelp());
			return;
		}
		
		Warp w = new Warp();
		
		try {
			w.start = new Sector(args[0], args[1], args[2]);
		} catch (Exception e) {
			e.printStackTrace();
			pm(username, "The coordinates input are not correct, check your arguments.");
			return;
		}
		
		try {
			w.end = new Sector(args[3], args[4], args[5]);
		} catch (Exception e) {
			e.printStackTrace();
			pm(username, "The coordinates input are not correct, check your arguments.");
			return;
		}
		
		w.faction = "all";
		
		try {
			list.add(w);
		} catch (WarpOverrideException e) {
			
			pm(username, "A warp already exists in one of those sectors");
			return;
		}
		pm(username, "Warp " + w.toString() + " has been created.");
	}
	
	@Override
	public String getHelp() {
	
		return "!addwarp (x) (y) (z) (x) (y) (z): Start and end coordinates";
	}
	
}
