package Ewok.Render;

public class Article {
	public String title;
	public String date;
	public String press;
	public String content;
	
	@Override
	public String toString(){
		return "Title: " + title + "\r\n" + "Date: " + date + "\r\n" + "Press: " + press + "\r\n" + "Content: \r\n" + content;	 
	}
}
