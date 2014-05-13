package com.gravypod.warps;

import com.gravypod.starmadewrapper.Sector;


public class Warp {
	public Sector start, end;
	public String faction;
	
	@Override
	public String toString() {
		return faction + "_" + start.toString() + "-" + end.toString();
	}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Warp)) {
			return super.equals(obj);
		}
		Warp w = (Warp) obj;
		return w.start.equals(start) && w.end.equals(end) && w.faction.equals(faction);
	}
}
