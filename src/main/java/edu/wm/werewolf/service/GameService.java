package edu.wm.werewolf.service;

import org.springframework.beans.factory.annotation.Autowired;

import edu.wm.werewolf.dao.IGameDAO;
import edu.wm.werewolf.domain.Game;

public class GameService {
	
	@Autowired private IGameDAO gameDAO;
	
	public void createGame(Game game) {
		gameDAO.createGame(game);
	
	}
	
	public boolean checkGame(){
		return gameDAO.checkGame();
	}
	
	public boolean checkDay(){
		return gameDAO.checkDay();
	}
	
	public void changeDay(){
		gameDAO.changeDay();
	}
	
	public String getNews(){
		return gameDAO.getNews();
	}
	
	public void changeNews(String news){
		gameDAO.changeNews(news);
	}

}
