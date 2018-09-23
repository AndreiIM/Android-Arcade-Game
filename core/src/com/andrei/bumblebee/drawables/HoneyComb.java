package com.andrei.bumblebee.drawables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.andrei.bumblebee.BumblebeeGame;
import com.andrei.bumblebee.pojos.Node;
import com.andrei.bumblebee.pojos.Point;
import com.andrei.bumblebee.utils.AppConstants;
import com.andrei.bumblebee.utils.AppUtilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.lang.Math;
import java.util.Map;

/**
 * Created by Andrei on 31/03/2015.
 */
public class HoneyComb extends GenericDrawable {

    private int radius;
    private int size;
    private Point origin;

    public static Map<String,Hexagon> hexMap = new HashMap<String,Hexagon>();
    public static List<Node> marginalNode= new ArrayList<Node>();
    public static Map<String ,Node> nodeMap = new HashMap<String,Node>();

    public HoneyComb(){
    }

    private HoneyComb(Point origin, int size, int radius) {
        this.radius = radius;
        this.origin = origin;
        this.size = size;
    }

    public void createWithDefaults(){
        this.radius = (int) AppUtilities.getHexRadius(Gdx.graphics.getWidth(), AppConstants.HONEYCOMB_SCREEN_MARGIN, AppConstants.HONEYCOMB_MAX_HEX);
        //this.origin = new Point(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2 + (2 * this.radius * AppConstants.HONEYCOMB_MAX_HEX / 3));
        this.origin = new Point(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        this.size = AppConstants.HONEYCOMB_MAX_HEX;

        //this.size = 3;
        //set the game scale as the circumdiameter of the hexagon
        BumblebeeGame.GAME_SCALE = 2 * this.radius;
        System.out.println("GAME_SCALE set to "+BumblebeeGame.GAME_SCALE);

        create();
    }

    @Override
    public void create() {
        createHexGridLoop(origin, size, radius);
        batch = new SpriteBatch();
    }

    @Override
    public void render () {
        batch.begin();
        drawHexGridLoop();
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        disposeHexGridLoop();
    }

    private void createHexGridLoop(Point origin, int size, int radius) {
        double ang30 = Math.toRadians(30);
        double xOff = Math.cos(ang30) * (radius);
        double yOff = Math.sin(ang30) * (radius);
        int half = size / 2;

        //System.out.println(origin.x + " " + origin.y);

        for (int row = 0; row < size; row++) {
            int cols = size - Math.abs(row - half) + 4;
            if(cols >= AppConstants.HONEYCOMB_MAX_HEX+ 3) cols -= 4;
             else if(cols >= AppConstants.HONEYCOMB_MAX_HEX + 1) cols -= 2;

            for (int col = 0; col < cols; col++) {
                int x = (int) (origin.x + xOff * (col * 2 + 1 - cols));
                int y = (int) (origin.y + yOff * (row - half) * 3);

                int computedIndexX = AppConstants.HONEYCOMB_HEX_CENTER_INDEX_X == 0 ? row-AppConstants.HONEYCOMB_HEX_CENTER_INDEX_X : row;
                int computedIndexY = AppConstants.HONEYCOMB_HEX_CENTER_INDEX_Y == 0 ? col-AppConstants.HONEYCOMB_HEX_CENTER_INDEX_Y : col;
                Hexagon hex = new Hexagon(x, y, radius, computedIndexX, computedIndexY);
                Node node = new Node(computedIndexX, computedIndexY);

                //check if hex is a marginal one
                if (row == 0 || row == size - 1 || col == 0 || (x == AppConstants.MARGIN_1_X || x == AppConstants.MARGIN_2_X || (x == AppConstants.MARGIN_3_X && col == AppConstants.MARGIN_1_Y))){
                  node.setMarginal(true);
                  marginalNode.add(node);
                }

                hexMap.put(hex.getIndexX() + "," + hex.getIndexY(), hex);
                nodeMap.put(node.getIndexX() + "," + node.getIndexY(), node);
            }
        }

        //set the honeycomb center
        BumblebeeGame.HONEYCOMB_CENTER = hexMap.get(AppConstants.HONEYCOMB_HEX_CENTER_INDEX_X + "," + AppConstants.HONEYCOMB_HEX_CENTER_INDEX_Y);

    }



    private void drawHexGridLoop() {

        Iterator it = hexMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Hexagon hex = (Hexagon) pair.getValue();
            hex.render();
        }
    }

    private void disposeHexGridLoop(){

        Iterator it = hexMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Hexagon hex = (Hexagon) pair.getValue();
            hex.dispose();
        }
    }

    public Point getOrigin() {
        return origin;
    }

    public void setOrigin(Point origin) {
        this.origin = origin;
    }

}
