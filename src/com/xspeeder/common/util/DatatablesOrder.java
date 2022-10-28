package com.xspeeder.common.util;

public class DatatablesOrder {

	public int col_idx;
	public String col_name;
	public String dir;
	
	public DatatablesOrder()
	{
		
	}
	
	public DatatablesOrder(String col_name, String dir)
	{
		this.col_name = col_name;
		this.dir = dir;
	}
}
