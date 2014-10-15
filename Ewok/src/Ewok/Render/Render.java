package Ewok.Render;

import Ewok.RegionFilter.HTMLContent;


/*
  @ Project : Ewok
  @ File Name : Parser.java
  @ Date : 2014-09-29
  @ Author : Kiheung Park
*/

public abstract class Render extends Thread{
	abstract public Article render(String targetedURL) throws NonTargetException; // Interface
	
	private String targetedURL;
	private Article curResult;
	
	public void setParam(String targetURL){
		this.targetedURL = targetURL;
		curResult = null;
	}
	
	public Article getResult(){
		return curResult; 
	}
	
	//for thread.
	public void run(){
		try {
			curResult = render(this.targetedURL);
		} catch (NonTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			curResult = null;
		}
	}
}
