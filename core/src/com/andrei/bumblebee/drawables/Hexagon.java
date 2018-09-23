package com.andrei.bumblebee.drawables;

/**
 * Created by Andrei on 25/03/2015.
 */
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.andrei.bumblebee.pojos.Point;
import java.util.List;
import java.util.ArrayList;

public class Hexagon extends GenericDrawable{

    PolygonSprite poly;
    PolygonSpriteBatch polyBatch;
    Texture textureSolid;
    ShapeRenderer shapeRenderer;

    private static final long serialVersionUID = 1L;
    private static final int SIDES = 6;
    private static final int ROTATION = 90;
    private static final Color FILL_COLOR = new Color(0xA36D0FFF);
    private static final Color SHAPE_COLOR = new Color(0xE8E858FF);

    private Point[] points = new Point[SIDES];

    private Hexagon parent;

    private Point center;
    private int radius;

    private int indexX;
    private int indexY;

    private float vertices[];

    public Hexagon(Point center, int radius) {
        this.center = center;
        this.radius = radius;


        updatePoints();
        create();
    }

    public Hexagon(int x, int y, int radius) {
        this(new Point(x, y), radius);
        create();
    }

    public Hexagon(Point center, int radius, int indexX, int indexY){

        this.center = center;
        this.radius = radius;
        this.indexX = indexX;
        this.indexY = indexY;
        updatePoints();

        create();
    }


    public Hexagon(int x, int y, int radius, int indexX, int indexY){
        this(new Point(x, y), radius, indexX, indexY);
        create();
    }

    public Hexagon(){
        create();
    }

    @Override
    public void create () {
        //filled polygon
        polyBatch = new PolygonSpriteBatch();
        Pixmap pix = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pix.setColor(FILL_COLOR);
        pix.fill();
        textureSolid = new Texture(pix);
        PolygonRegion polyReg = new PolygonRegion(new TextureRegion(textureSolid),
                new float[] {      // Four vertices
                        points[0].x, points[0].y,          // Vertex 0
                        points[1].x, points[1].y,          // Vertex 1
                        points[2].x, points[2].y,          // Vertex 2
                        points[3].x, points[3].y,          // Vertex 3
                        points[4].x, points[4].y,          // Vertex 4
                        points[5].x, points[5].y           // Vertex 5
                }, new short[] {
                0, 1, 2,         // Two triangles using vertex indices.
                0, 2, 3,          // Take care of the counter-clockwise direction.
                0, 3, 5,
                3, 4, 5
        });
        poly = new PolygonSprite(polyReg);
        poly.setOrigin(100, 100);
        polyBatch = new PolygonSpriteBatch();

        //shaped polygon
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setColor(SHAPE_COLOR);
        vertices = new float[]{ points[0].x, points[0].y,
                                points[1].x, points[1].y,
                                points[2].x, points[2].y,
                                points[3].x, points[3].y,
                                points[4].x, points[4].y,
                                points[5].x, points[5].y   };
    }




    @Override
    public void render () {
        polyBatch.begin();
        poly.draw(polyBatch);
        polyBatch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.polygon(vertices);
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        polyBatch.dispose();
        textureSolid.dispose();
        shapeRenderer.dispose();
    }


    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;

        updatePoints();
    }

    public void setCenter(Point center) {
        this.center = center;

        updatePoints();
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(int x, int y) {
        setCenter(new Point(x, y));
    }

    private double findAngle(double fraction) {
        return fraction * Math.PI * 2 + Math.toRadians((ROTATION + 180) % 360);
    }

    private Point findPoint(double angle) {
        int x = (int) (center.x + Math.cos(angle) * radius);
        int y = (int) (center.y + Math.sin(angle) * radius);

        return new Point(x, y);
    }

    protected void updatePoints() {
        for (int p = 0; p < SIDES; p++) {
            double angle = findAngle((double) p / SIDES);
            Point point = findPoint(angle);
            points[p] = point;
        }
    }

    public Point[] getPoints() {
        return points;
    }

    public void setPoints(Point[] points) {
        this.points = points;
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
}

