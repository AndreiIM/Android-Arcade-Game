package com.vladghita.bumblebeegame.utils;

import com.badlogic.gdx.Gdx;
import com.vladghita.bumblebeegame.drawables.Bee;
import com.vladghita.bumblebeegame.drawables.Bumblebee;

import com.vladghita.bumblebeegame.BumblebeeGame;

import com.vladghita.bumblebeegame.drawables.Hexagon;
import com.vladghita.bumblebeegame.drawables.HoneyComb;
import com.vladghita.bumblebeegame.pojos.Node;
import com.vladghita.bumblebeegame.pojos.Point;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Vlad on 16.04.2015.
 */
public class GameplayManager {

    private BumblebeeGame game = null;

    private long startMoveTime = 0 ;//= System.nanoTime();
    private long endMoveTime = 0;//= System.nanoTime()-startTime;

    private long totalScore = 0;
    private long turnNumber = 0;
    private  long totalGameTime = 0;
    private Node bumblebeeCurrentNode;

    private boolean gameOver = false;

    public GameplayManager(BumblebeeGame game){
        this.game = game;
    }

    /** Process the next bumblebee move*/
    public boolean processMove(){
        if(game != null){
            Node node = game.getOptimalPath().findOptimalPath(bumblebeeCurrentNode);

            if(node != null) {
                Hexagon currentHex = AppUtilities.getHexFromMapByNode(HoneyComb.hexMap, node);
                game.getBumbleBee().moveTo(AppUtilities.worldToScreen(currentHex.getCenter()));
                bumblebeeCurrentNode = node;

                if(node.isMarginal()){
                    gameOver = true;
                    return false;
                }
            }
            else {
                gameOver = true;
                return false;
            }
        }

        return true;
    }

    /** Do all the initialization needed to start a new game*/
    public void initNewGame(){
        System.out.println("new game");

        this.setGameOver(false);

        //reset score
        startMoveTime = 0;
        endMoveTime = 0;
        totalScore = 0;
        turnNumber = 0;
        totalGameTime = 0;

        //initialize bumblebee position
        Node center = AppUtilities.getNodeFromMapByHex(HoneyComb.nodeMap, BumblebeeGame.HONEYCOMB_CENTER);
        this.setBumblebeeCurrentNode(center);
        game.getBumbleBee().moveTo(AppUtilities.worldToScreen(BumblebeeGame.HONEYCOMB_CENTER.getCenter()));

        //reset the all the nodes set as obstcles
        Iterator it = game.getHoneyComb().nodeMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Node node = (Node) pair.getValue();

            node.setObstacle(false);
        }

        //set initial obstacles (bees)
        game.getBeeList().clear();
        List<Hexagon> allHexagons = new ArrayList<Hexagon>(game.getHoneyComb().hexMap.values());
        List<Hexagon> randomHexagons = AppUtilities.getRandomHexagons(allHexagons, AppConstants.INITIAL_OBSTACLES);
        for(Hexagon hex : randomHexagons){
            Bee bee = new Bee(game);
            bee.create();
            bee.moveTo(AppUtilities.worldToScreen(hex.getCenter()));

            Node node = AppUtilities.getNodeFromMapByHex(HoneyComb.nodeMap, hex);
            node.setObstacle(true);

            game.getBeeList().add(bee);
        }

    }

    public boolean isGameWin(){
       if(gameOver && !bumblebeeCurrentNode.isMarginal())
           return true;

        return false;
    }

    public BumblebeeGame getGame() {
        return game;
    }

    public void setGame(BumblebeeGame game) {
        this.game = game;
    }

    public Node getBumblebeeCurrentNode() {
        return bumblebeeCurrentNode;
    }

    public void setBumblebeeCurrentNode(Node bumblebeeCurrentNode) {
        this.bumblebeeCurrentNode = bumblebeeCurrentNode;
    }


    public long getTotalGameTime() {
        return totalGameTime;
    }

    public void setTotalGameTime(long totalGameTime) {
        this.totalGameTime = totalGameTime;
    }

    public long getStartMoveTime() {
        return startMoveTime;
    }

    public void setStartMoveTime(long startMoveTime) {
        this.startMoveTime = startMoveTime;
    }

    public long getEndMoveTime() {
        return endMoveTime;
    }

    public void setEndMoveTime(long endMoveTime) {
        this.endMoveTime = endMoveTime;
    }


    public long getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(long turnNumber) {
        this.turnNumber = turnNumber;
    }


    public long getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(long totalScore) {
        this.totalScore = totalScore;
    }


    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
