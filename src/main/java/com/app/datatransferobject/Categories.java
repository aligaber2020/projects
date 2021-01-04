package com.app.datatransferobject;

import java.util.List;

public class Categories {
	
private List<CategoryDTO> fruites;
private List<CategoryDTO> vegetables; 
private List<CategoryDTO> others;

public List<CategoryDTO> getFruites() {
	return fruites;
}
public void setFruites(List<CategoryDTO> fruites) {
	this.fruites = fruites;
}
public List<CategoryDTO> getVegetables() {
	return vegetables;
}
public void setVegetables(List<CategoryDTO> vegetables) {
	this.vegetables = vegetables;
}
public List<CategoryDTO> getOthers() {
	return others;
}
public void setOthers(List<CategoryDTO> others) {
	this.others = others;
}


}
