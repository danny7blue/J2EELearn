package bean;

import java.util.Date;

public class TimeBean {
	public TimeBean() {
		
	}
	public String getTime() {
		return new Date().toString();
	}
}
