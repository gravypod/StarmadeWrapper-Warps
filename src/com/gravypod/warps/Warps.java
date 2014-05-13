package com.gravypod.warps;

import com.gravypod.starmadewrapper.Server;
import com.gravypod.starmadewrapper.plugins.Plugin;
import com.gravypod.starmadewrapper.plugins.commands.CommandManager;
import com.gravypod.warps.commands.WarpCommand;

public class Warps extends Plugin {
	
	private WarpList list;
	
	@Override
	public void onDisable(Server server) {
		CommandManager manager = getAssistant().getPluginManager().getCommandManager();
		list = new WarpList(getDataDir());
		manager.registerCommand("warp", new WarpCommand(list));
	}
	
	public void onEnable(Server server) {
		list.save();
	}
	
}
