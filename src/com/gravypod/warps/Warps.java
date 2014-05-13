package com.gravypod.warps;

import com.gravypod.starmadewrapper.Server;
import com.gravypod.starmadewrapper.plugins.Plugin;
import com.gravypod.starmadewrapper.plugins.commands.CommandManager;
import com.gravypod.warps.commands.AddWarpCommand;
import com.gravypod.warps.commands.DelWarpCommand;
import com.gravypod.warps.commands.WarpCommand;

public class Warps extends Plugin {
	
	private WarpList list;
	
	@Override
	public void onDisable(Server server) {
	
		list.save();
	}
	
	@Override
	public void onEnable(Server server) {
	
		CommandManager manager = getAssistant().getPluginManager().getCommandManager();
		list = new WarpList(getDataDir());
		manager.registerCommand("warp", new WarpCommand(list));
		
		manager.registerCommand("addwarp", new AddWarpCommand(list));
		
		manager.registerCommand("delwarp", new DelWarpCommand(list));
		
	}
	
}
