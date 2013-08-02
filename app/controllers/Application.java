package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

import play.Play;

public class Application extends Controller {

    public static void index() {
    	Post frontPost = Post.find("order by postedAt desc").first();
    	List<Post> olderPosts = Post.find("order by postedAt desc").
    			from(1).fetch(10);
        
    	render(frontPost, olderPosts);
    }
    
    @Before
    public static void addDefaults(){
    	
    	renderArgs.put("blogTitle", Play.configuration.getProperty("blog.title"));
    	renderArgs.put("blogBaseLine", Play.configuration.getProperty("blog.baseline"));
    	
    }
    
    public static void show(Long id){
    	
    	Post post = Post.findById(id);
    	render(post);
    	
    }

}