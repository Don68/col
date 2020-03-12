
/*
Part of Cabonline's Code Challenge
by Håkan Perman
*/
public class DomainNameHandler {
	private String longPath;
	private String shortDomainName;
	private String shortScheme;

	public static void main(String args[]) {
		String longHostName;
//		String longURL = "pelleochlisasblogg/semesterresan.aspx"; //example 1
		String longURL = "http://www.laughingout-loud.com/FelisSilvestrisCatus.aspx"; //example 2

		DomainNameHandler dnh = new DomainNameHandler();
//		dnh.setShortScheme(""); //example 1
		dnh.setShortScheme("http");	//example 2
//		dnh.setShortDomainName("short.it.is"); //example 1
		dnh.setShortDomainName("lol.ca"); //example 2
		longHostName = dnh.getLongHostname(longURL);
		
		System.out.println("longURL=" + longURL);			
		System.out.println("longHostName=" + longHostName);
		System.out.println("longPath=" + dnh.getLongPath());		
		System.out.println("shortDomainName=" + dnh.getShortDomainName());
		System.out.println("shortPath=" + dnh.getShortPath(longURL, longHostName));
		System.out.println("shortURL=" + dnh.getShortURL(longURL));		
	}


	/*
	Example: Returns "semesterresan.aspx" in "pelleochlisasblogg.se/semesterresan.aspx"
	*/	
	public String getLongPath() {
		return longPath;
	}	


	/* 
	Example: Returns "short.it.is"
	*/
	public String getShortDomainName() {
		return shortDomainName;
	}
		
	
	/* 
	Returns scheme (protocol), normally "http" or "https"
	*/
	public String getShortScheme() {
		return shortScheme;
	}	

	
	/* 
	Example: Sets "semesterresan.aspx"
	*/
	public void setLongPath(String longPath) {
		this.longPath = longPath;
	}	


	/* 
	Example: Sets "short.it.is"
	*/
	public void setShortDomainName(String shortDomainName) {
		this.shortDomainName = shortDomainName;
	}
	
		
	/* 
	Sets scheme (protocol), normally "http" or "https"
	*/
	public void setShortScheme(String shortScheme) {
		this.shortScheme = shortScheme;
	}


	/*
	Example: Returns "pelleochlisasblogg.se" in "pelleochlisasblogg.se/semesterresan.aspx"
	*/
	private String getLongHostname(String longURL) {
		int dbleSlashDex; //Dex stands for index
		int singlSlashDex;
		String longHostname;

		dbleSlashDex = longURL.indexOf("//");

		if (dbleSlashDex != -1) {
			singlSlashDex = longURL.indexOf("/", dbleSlashDex + 2); //len of "//" is 2

			if (singlSlashDex == -1) { //so look for backslash
				singlSlashDex = longURL.indexOf("\\", dbleSlashDex + 2); //"\\" is escape + single slash

				if (singlSlashDex == -1) { //so URL w. domain name only
					longHostname = longURL.substring(dbleSlashDex + 2);
				}
				else { //single slash found
					longHostname = longURL.substring(dbleSlashDex + 2, singlSlashDex);
				}
			}
			else { //single slash found
				longHostname = longURL.substring(dbleSlashDex + 2, singlSlashDex);
			}
		}
		else { //URL w. no explicitly specified scheme (protocol) assumed
			singlSlashDex = longURL.indexOf("/");

			if (singlSlashDex == -1) { //so look for backslash
				singlSlashDex = longURL.indexOf("\\"); //escape + single slash

				if (singlSlashDex == -1) { //so URL w. domain name only
					longHostname = longURL;
				}
				else {
					longHostname = longURL.substring(0, singlSlashDex);
				}
			}
			else { //single slash found
				longHostname = longURL.substring(0, singlSlashDex);
			}
		}
		if (singlSlashDex == -1) { //so URL w. domain name only
			setLongPath(""); //no long path
		}
		else {
			setLongPath(longURL.substring(singlSlashDex + 1));		
		}		
		return longHostname;
	}
	

	/*
	Example: Returns "sem" in "pelleochlisasblogg.se/semesterresan.aspx" 
	*/
	private String getShortPath(String longURL, String longHostname) {
		String longPath; //part of old, long URL
		String shortPath; //part of new, short URL

		longPath = getLongPath();
		shortPath = longPath.substring(0, 3);
		return shortPath;
	}


	/* 
	Example: Returns "short.it.is/sem"
	*/
	private String getShortURL(String longURL) {
		String pre; //scheme (protocol) + "://" (example: "http://", but may be empty string)
		String shortPath; //part of new, short URL
		String shortURL;
		String shortScheme;
		
		shortScheme = getShortScheme();
		
		if (shortScheme.equals("")) {
			pre = "";
		}
		else {
			pre = shortScheme + "://";
		}
		shortPath = getShortPath(longURL, getLongHostname(longURL));
		shortURL = pre + shortDomainName + "/" + shortPath;
		return shortURL;
	}


} //end class