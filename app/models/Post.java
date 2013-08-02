package models;

import java.util.*;

import javax.persistence.*;
import play.db.jpa.*;

@Entity
public class Post extends Model{

	public String title;
	public Date postedAt;
	
	@Lob
	public String content;
	
	@ManyToOne
	public User author;
	
	@OneToMany(mappedBy  ="post", cascade= CascadeType.ALL)
	public List<Comment> comments= new ArrayList<Comment>();
	

	public Post(String title, String content, User author) {
		this.title = title;
		this.content = content;
		this.author = author;
		
		this.postedAt = new Date();
	}
	
   
	public Post addComment(String author, String content){
		
		Comment newComment= new Comment(this, author, content).save();
		
		this.comments.add(newComment);

		this.save();
		return this;
	}
	
}
