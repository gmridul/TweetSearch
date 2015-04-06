package package_TS;

public class Tweet {
	int EventID;
	String[] Content;
	int Score;
	
	public Tweet(int EventID , String[] Content , int Score) {
		this.EventID = EventID;
		this.Content = Content;
		this.Score = Score;
	}
	
	public int getEventID() {
		return EventID;
	}

	public String[] getContent() {
		return Content;
	}

	public int getScore() {
		return Score;
	}
	
	public void setScore(int Score) {
		this.Score = Score;
	}
}
