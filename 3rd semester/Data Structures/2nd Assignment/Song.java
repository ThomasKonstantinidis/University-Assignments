public class Song implements Comparable<Song>{
	private int id;
	private String title;
	private int likes;

	
	
	public Song( int id, String title, int likes){
		this.id = id;
		this.title=title;
		this.likes=likes;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
		public void setTitle(String title) {
		this.title = title;
	}
		public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	
	public int compareTo(Song son) {
		
		if(this.getLikes() > son.getLikes()){
			return 1;
		} else if(this.getLikes() == son.getLikes()) {
			if( this.getTitle().compareTo(son.getTitle()) > 0 ){
				return -1;
			} else {
				return 1;
			}
		}
		
		return -1;
	}

	
}
