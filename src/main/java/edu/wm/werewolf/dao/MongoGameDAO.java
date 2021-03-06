package edu.wm.werewolf.dao;

import java.net.UnknownHostException;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoURI;

import edu.wm.werewolf.domain.Game;

public class MongoGameDAO implements IGameDAO{
	private static final Logger logger = LoggerFactory.getLogger(MongoGameDAO.class);
	//@Autowired private MongoClient mongo;
	//@Autowired private MongoURI mongoURI;
	
	//URI mongoURI = new URI(System.getenv("MONGOHQ_URL"));

	MongoURI mongoURI = new MongoURI(System.getenv("MONGOHQ_URL"));
	
	@Override
	public void createGame(Game game) {
		// TODO Auto-generated method stub
		logger.info("in create game mongogamedao");
		
		
		DB db = null;
		try {
			db = mongoURI.connectDB();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        db.authenticate(mongoURI.getUsername(), mongoURI.getPassword());
		
		//DB db = mongo.getDB("Werewolf");
		DBCollection games = db.getCollection("Game");
		BasicDBObject document = new BasicDBObject();
		document.put("dayNightFreq", game.getDayNightFreq());
		document.put("createdDate", game.getCreatedDate());
		document.put("isDay", game.isDay());
		document.put("news", game.getNews());
		games.insert(document);
		
	}

	@Override
	public boolean checkGame() {
		
		DB db = null;
		try {
			db = mongoURI.connectDB();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        db.authenticate(mongoURI.getUsername(), mongoURI.getPassword());
		
		//DB db = mongo.getDB("Werewolf");
		DBCollection games = db.getCollection("Game");
		
		DBCursor cursor = games.find();

		if (cursor.hasNext()) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean checkDay() {
		DB db = null;
		try {
			db = mongoURI.connectDB();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        db.authenticate(mongoURI.getUsername(), mongoURI.getPassword());
		
		//DB db = mongo.getDB("Werewolf");
		DBCollection games = db.getCollection("Game");
		
		DBCursor cursor = games.find();

		DBObject gameFound = cursor.next();
		
		return ((Boolean) gameFound.get("isDay")).booleanValue();
	}

	@Override
	public void changeDay() {
		// TODO Auto-generated method stub
		DB db = null;
		try {
			db = mongoURI.connectDB();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        db.authenticate(mongoURI.getUsername(), mongoURI.getPassword());
		
		//DB db = mongo.getDB("Werewolf");
		DBCollection games = db.getCollection("Game");
		
		DBCursor cursor = games.find();

		DBObject gameFound = cursor.next();
		ObjectId gameId = (ObjectId) gameFound.get("_id");
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("_id", gameId);
		
		BasicDBObject dayDoc = new BasicDBObject();
		dayDoc.put("isDay", !checkDay());
		
		BasicDBObject updateObj = new BasicDBObject();
		updateObj.put("$set", dayDoc);

		games.update(searchQuery, updateObj);
	}

	@Override
	public String getNews() {
		DB db = null;
		try {
			db = mongoURI.connectDB();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        db.authenticate(mongoURI.getUsername(), mongoURI.getPassword());
		
		//DB db = mongo.getDB("Werewolf");
		DBCollection games = db.getCollection("Game");
		
		DBCursor cursor = games.find();

		DBObject gameFound = cursor.next();
		
		return ((String) gameFound.get("news")).toString();
	}

	@Override
	public void changeNews(String news) {
		DB db = null;
		try {
			db = mongoURI.connectDB();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        db.authenticate(mongoURI.getUsername(), mongoURI.getPassword());
		
		//DB db = mongo.getDB("Werewolf");
		DBCollection games = db.getCollection("Game");
		
		DBCursor cursor = games.find();

		DBObject gameFound = cursor.next();
		ObjectId gameId = (ObjectId) gameFound.get("_id");
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("_id", gameId);
		
		BasicDBObject dayDoc = new BasicDBObject();
		dayDoc.put("news", news);
		
		BasicDBObject updateObj = new BasicDBObject();
		updateObj.put("$set", dayDoc);

		games.update(searchQuery, updateObj);
		
	}

}
