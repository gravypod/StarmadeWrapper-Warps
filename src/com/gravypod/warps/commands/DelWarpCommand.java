package com.gravypod.warps.commands;

import com.gravypod.starmadewrapper.Sector;
import com.gravypod.starmadewrapper.User;
import com.gravypod.starmadewrapper.plugins.commands.Command;
import com.gravypod.warps.WarpList;

public class DelWarpCommand extends Command {
	
	private final WarpList list;
	
	public DelWarpCommand(WarpList list) {
	
		this.list = list;
	}
	
	@Override
	public void run(String username, User user, String... args) {
	
		if (!isAdmin(username)) {
			pm(username, "You are not allowed to use this command.");
			return;
		}
		
		if (args.length != 3) {
			pm(username, "Wrong args!");
			pm(username, getHelp());
			return;
		}
		Sector s;
		
		try {
			s = new Sector(args[0], args[1], args[2]);
		} catch (Exception e) {
			e.printStackTrace();
			pm(username, "The coordinates input are not correct, check your arguments.");
			return;
		}
		
		list.remove(s);
		
	}
	
	@Override
	public String getHelp() {
	
		return "!delwarp (x) (y) (z): Delete a warp.";
	}
	
}
