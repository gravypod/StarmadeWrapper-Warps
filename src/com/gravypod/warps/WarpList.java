package com.gravypod.warps;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.esotericsoftware.yamlbeans.YamlConfig;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.YamlWriter;
import com.gravypod.starmadewrapper.Sector;

public class WarpList {
	
	private final HashMap<Sector, Warp> warps = new HashMap<Sector, Warp>();
	private final File warpFolder;
	private final YamlConfig config = new YamlConfig();
	
	public WarpList(File warpFolder) {
	
		this.warpFolder = warpFolder;
		config.setClassTag("Warp", Warp.class);
		config.setClassTag("Sector", Sector.class);
		load();
	}
	
	/**
	 * Get a warp associated with a sector
	 * @param s - Sector to find a warp associated with
	 * @return
	 */
	public Warp get(Sector s) {
	
		return warps.get(s);
	}
	
	/**
	 * Add a warp overriding associations with the old sectors
	 * @param w
	 */
	public void add(Warp w) throws WarpOverrideException {
	
		
		if (warps.containsKey(w.start) || warps.containsKey(w.end)) {
			throw new WarpOverrideException();
		}
		warps.put(w.start, w);
		warps.put(w.end, w);
		saveWarp(w);
	}
	
	
	
	public void remove(Sector s) {
	
		Warp w = warps.get(s);
		
		if (w == null) {
			return;
		}
		
		warps.remove(w.start);
		warps.remove(w.end);
		
		getWarpFile(w).delete();
	}
	
	public void save() {
	
		for (Warp w : new ArrayList<Warp>(warps.values())) {
			saveWarp(w);
		}
		
	}
	
	private void saveWarp(Warp w) {
	
		try {
			YamlWriter writer = new YamlWriter(new FileWriter(getWarpFile(w)), config);
			writer.write(w);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void load() {
	
		FilenameFilter filder = new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
			
				return name.endsWith("yml");
			}
		};
		
		for (String f : warpFolder.list(filder)) {
			File file = new File(warpFolder, f);
			if (file.isDirectory() || !file.canRead() || !file.canWrite()) {
				continue;
			}
			loadWarpFile(file);
		}
		
	}
	
	public void loadWarpFile(File f) {
	
		try {
			YamlReader reader = new YamlReader(new FileReader(f));
			Warp warp = reader.read(Warp.class);
			warps.put(warp.start, warp);
			warps.put(warp.end, warp);
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public File getWarpFile(Warp warp) {
	
		return new File(warpFolder, warp.toString() + ".yml");
	}
	
}
