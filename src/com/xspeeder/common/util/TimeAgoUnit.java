package com.xspeeder.common.util;

public class TimeAgoUnit {

	private long diff;
	private String unit;
	
	public TimeAgoUnit( String punit, double pdiff )
	{
		diff = (long)pdiff;
		unit = punit;
	}
	
	public long getDiff() {
		return diff;
	}
	public void setDiff(long diff) {
		this.diff = diff;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
}
