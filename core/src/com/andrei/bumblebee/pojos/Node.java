package com.andrei.bumblebee.pojos;
import com.andrei.bumblebee.drawables.Hexagon;
import com.andrei.bumblebee.drawables.HoneyComb;
import com.andrei.bumblebee.utils.AppUtilities;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by AndreiIM on 7/13/2015.
 */
public class Node implements Comparable<Node> {

    private int indexX;
    private int indexY;
    private List<Node> neighbours;

    private int costOfMovement;
    private int costToGoal;
    private Node parent;

    private boolean obstacle = false;
    private boolean marginal = false;

    private final int EVEN_ERROR =2;
    private final int ODD_ERROR =9;

    public Node(int indexX, int indexY){
        this.indexX = indexX;
        this.indexY = indexY;
        this.neighbours = new ArrayList<Node>();

    }
    public List<Node> getNeighborhood(int indexX,int indexY){
        neighbours.clear();
        if(indexX % 2 == 0) {
            neighbours.add(AppUtilities.getNodeFromMap(HoneyComb.nodeMap, indexX+1,indexY+1));
            neighbours.add(AppUtilities.getNodeFromMap(HoneyComb.nodeMap,indexX+1,indexY));
            neighbours.add(AppUtilities.getNodeFromMap(HoneyComb.nodeMap,indexX,indexY-1));
            neighbours.add(AppUtilities.getNodeFromMap(HoneyComb.nodeMap,indexX,indexY+1));
           /* if(indexX == EVEN_ERROR){
                neighbours.add(AppUtilities.getNodeFromMap(HoneyComb.nodeMap,indexX-1,indexY));
                neighbours.add(AppUtilities.getNodeFromMap(HoneyComb.nodeMap,indexX-1,indexY-1));
            }else{*/
                neighbours.add(AppUtilities.getNodeFromMap(HoneyComb.nodeMap,indexX-1,indexY));
                neighbours.add(AppUtilities.getNodeFromMap(HoneyComb.nodeMap,indexX-1,indexY+1));
            //}
        }else{
            neighbours.add(AppUtilities.getNodeFromMap(HoneyComb.nodeMap,indexX+1,indexY));
            neighbours.add(AppUtilities.getNodeFromMap(HoneyComb.nodeMap,indexX,indexY-1));
            neighbours.add(AppUtilities.getNodeFromMap(HoneyComb.nodeMap,indexX,indexY+1));
            neighbours.add(AppUtilities.getNodeFromMap(HoneyComb.nodeMap,indexX+1,indexY-1));
           /* if(indexX == ODD_ERROR){
                neighbours.add(AppUtilities.getNodeFromMap(HoneyComb.nodeMap,indexX-1,indexY));
                neighbours.add(AppUtilities.getNodeFromMap(HoneyComb.nodeMap,indexX-1,indexY+1));
            }else{*/
                neighbours.add(AppUtilities.getNodeFromMap(HoneyComb.nodeMap,indexX-1,indexY));
                neighbours.add(AppUtilities.getNodeFromMap(HoneyComb.nodeMap,indexX-1,indexY-1));
            //}
        }
        return neighbours;
    }

    public int getTotalCost() {
        return this.costOfMovement + this.costToGoal;
    }

    @Override
    public int compareTo(Node other) {
        int otherCost = other.getTotalCost();
        // ascending order
        return this.getTotalCost() - otherCost;
    }

    @Override
    public boolean equals(Object other) {
        boolean isEqual = false;

        if (other instanceof Node) {
            Node castOther = (Node) other;
            if ((this.indexX != castOther.getIndexX())
                    || (this.indexY != castOther.getIndexY())) {
            } else {
                isEqual = true;
            }

        }

        return isEqual;
    }

    public int getCostOfMovement() {
        return costOfMovement;
    }

    public void setCostOfMovement(int costOfMovement) {
        this.costOfMovement = costOfMovement;
    }

    public int getCostToGoal() {
        return costToGoal;
    }

    public void setCostToGoal(int costToGoal) {
        this.costToGoal = costToGoal;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean isObstacle() {
        return obstacle;
    }

    public void setObstacle(boolean obstacle) {
        this.obstacle = obstacle;
    }

    public boolean isMarginal() {
        return marginal;
    }

    public void setMarginal(boolean marginal) {
        this.marginal = marginal;
    }

    public int getIndexY() {
        return indexY;
    }

    public void setIndexY(int indexY) {
        this.indexY = indexY;
    }

    public int getIndexX() {
        return indexX;
    }

    public void setIndexX(int indexX) {
        this.indexX = indexX;
    }

    public List<Node> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(List<Node> neighbours) {
        this.neighbours = neighbours;
    }

}
