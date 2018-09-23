package com.vladghita.bumblebeegame.utils;

import com.vladghita.bumblebeegame.drawables.Hexagon;
import com.vladghita.bumblebeegame.drawables.HoneyComb;
import com.vladghita.bumblebeegame.pojos.Node;
import com.vladghita.bumblebeegame.pojos.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by AndreiIM on 7/9/2015.
 */
public class PathFinder {

    private final List<Node> openList;
    private final List<Node> closedList;
    private List<Node> neighbors;
    private Node current;

    private final Integer DIAG_MOVE =14;
    private final Integer STRH_MOVE =10;

    private Node bestPoint;
    private boolean found;
    private Integer pathCost;

    public PathFinder() {
        openList = new ArrayList<Node>();
        closedList = new ArrayList<Node>();
        neighbors = new ArrayList<Node>();
        found = false;
    }

    public Node findOptimalPath(Node initial) {
        Iterator it = HoneyComb.marginalNode.iterator();
        while (it.hasNext()) {
            Node node = (Node)it.next();

            if (node.isObstacle()) {
                it.remove();
            }
        }

        for (Node node : HoneyComb.marginalNode) {
            if (node.isObstacle()) {
                HoneyComb.marginalNode.remove(node);
            }
        }
        Node start = initial;
        int min = AppConstants.INFINITE;
        Node bestPlace = null;
        for (Node nodeDestination : HoneyComb.marginalNode) {
            current = new Node((int) initial.getIndexX(), (int) initial.getIndexY());

            openList.clear();
            closedList.clear();

            current.setCostOfMovement(0);
            current.setCostToGoal(0);
            openList.add(current);

            while (!openList.isEmpty()) {

                current = openList.get(0);

                if (current.equals(nodeDestination)) {
                    found = true;
                    break;
                } else {
                    neighbors.clear();
                    openList.remove(current);
                    closedList.add(current);

                    neighbors =  current.getNeighborhood(current.getIndexX(), current.getIndexY());

                    for (Node neighbour : neighbors) {
                        if (neighbour != null && !neighbour.isObstacle()) {

                            int nextStepCost = current.getCostOfMovement() + this.calculateNodeCost(current, neighbour);
                            if (nextStepCost < neighbour.getCostOfMovement()) {
                                if (isInList(openList, neighbour))
                                    openList.remove(neighbour);
                                if (isInList(closedList, neighbour))
                                    closedList.remove(neighbour);
                            }
                            if (!isInList(openList, neighbour) && !isInList(closedList, neighbour)) {
                                neighbour.setCostOfMovement(nextStepCost);
                                neighbour.setCostToGoal(this.calculateTravelCost(neighbour, nodeDestination));
                                openList.add(neighbour);
                                neighbour.setParent(current);
                                Collections.sort(openList);
                            }
                        }
                    }
                }
            }
            if (found) {
                returnBestPointTowardsGoal(current,start);
                if(pathCost<min) {
                    bestPlace = bestPoint;
                    min =  pathCost;
                    found = false;
                }
            }
        }

        return bestPlace;
    }

    public int calculateNodeCost(Node startPosition, Node destination) {

       if (Math.abs(startPosition.getIndexX() - destination.getIndexX()) == 0 &&
                (Math.abs(startPosition.getIndexY() - (destination.getIndexY() - 1)) == 0 || Math.abs(startPosition.getIndexY() - (destination.getIndexY() + 1)) == 0))
            return STRH_MOVE;
      else
            return DIAG_MOVE;
    }

    public int calculateTravelCost(Node startPosition, Node destination) {
        int diffX = Math.abs(destination.getIndexX() - startPosition.getIndexX());
        int diffY = Math.abs(destination.getIndexY() - startPosition.getIndexY());

        if(diffX > diffY)
            return DIAG_MOVE*diffY + STRH_MOVE*(diffX-diffY);
        else
            return DIAG_MOVE*diffX + STRH_MOVE*(diffY-diffX);


    }

    private boolean isInList(List<Node> nodeList, Node node) {
        boolean inList = false;

        for (Node n : nodeList) {
            if (n.equals(node)) {
                inList = true;
                break;
            }
        }

        return inList;
    }

    private void returnBestPointTowardsGoal(Node destination,Node start) {
        Node parent = destination;
        Node nextPoint = null;
        Node child = null;
        int cost = 0;

        while (parent.getParent()!= null) {
            cost += calculateNodeCost(parent.getParent(),parent);
            if(parent.getParent().getIndexY() == start.getIndexY() && parent.getParent().getIndexX() == start.getIndexX() )
                child = parent;
            parent = parent.getParent();


        }

        setPathCost(cost);
        setBestPoint(child);
    }



    public Integer getPathCost() {
        return pathCost;
    }

    public void setPathCost(Integer pathCost) {
        this.pathCost = pathCost;
    }


    public Node getBestPoint() {
        return bestPoint;
    }

    public void setBestPoint(Node bestPoint) {
        this.bestPoint = bestPoint;
    }

}