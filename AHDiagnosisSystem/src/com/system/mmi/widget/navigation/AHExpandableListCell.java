package com.system.mmi.widget.navigation;

import java.util.ArrayList;

public class AHExpandableListCell {

	private int id;
	private String title;
	private String des;
	private int imageId;
	private ArrayList<AHExpandableListCell> children;
	
	public AHExpandableListCell(int id, int imageId, String title, String[] children){
		this.id = id;
		this.title = title;
		this.imageId = imageId;
		wrapChildenData(children);
	}
	
	public AHExpandableListCell(int id, int image, String title){
		this.id = id;
		this.title = title;
		this.imageId = image;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public int getImageId(){
		return this.imageId;
	}
	
	public ArrayList<AHExpandableListCell> getChildren(){
		return this.children;
	}
	
	public int getChildrenSize(){
		if(this.children != null){
			return this.children.size();
		}
		return -1;
	}
	
	private void wrapChildenData(String[] data){
		children = new ArrayList<AHExpandableListCell>();
		for (int i = 0; i < data.length; i++) {
			AHExpandableListCell child = new AHExpandableListCell(i, -1, data[i]);
			children.add(child);
		}
	}
}
