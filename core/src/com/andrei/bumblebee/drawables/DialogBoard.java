package com.andrei.bumblebee.drawables;

/**
 * Created by Vlad on 20/08/2015.
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.andrei.bumblebee.pojos.Point;
import com.andrei.bumblebee.utils.AppConstants;
import com.andrei.bumblebee.utils.AppUtilities;

public class DialogBoard extends GenericDrawable{

    PolygonSprite poly;
    PolygonSpriteBatch polyBatch;
    Texture textureSolid;
    ShapeRenderer shapeRenderer;

    private BitmapFont font;

    public static enum Position{ Position_Top, Position_Bottom };

    private Position position = Position.Position_Bottom;
    private int linesOfText = 4;  //number of lines of text (used to compute the board height)

    private static final int SIDES = 4;
    private static final Color FILL_COLOR = new Color(0xCED6C164);
    private static final Color SHAPE_COLOR = new Color(0xE8E858FF);
    private static final Color FONT_COLOR = new Color(0x000000FF);

    private Point[] points = new Point[SIDES];

    private float vertices[];

    @Override
    public void create () {
        font = new BitmapFont(Gdx.files.internal("data/" + AppConstants.FONT_JOKERMAN + ".fnt"));
        font.setColor(FONT_COLOR);
        AppUtilities.safeResize(font, 0.2f);

        if(position == Position.Position_Bottom){
            points[0] = new Point(0, 0);
            points[1] = new Point(0, computeBoardHeight());
            points[2] = new Point(Gdx.graphics.getWidth(), computeBoardHeight());
            points[3] = new Point(Gdx.graphics.getWidth(), 0);
        } else {
            points[0] = new Point(0, Gdx.graphics.getHeight() - computeBoardHeight());
            points[1] = new Point(0, Gdx.graphics.getHeight());
            points[2] = new Point(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            points[3] = new Point(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() - computeBoardHeight());
        }

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
                }, new short[] {
                0, 1, 2,         // Two triangles using vertex indices.
                0, 2, 3          // Take care of the counter-clockwise direction.
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
                                points[3].x, points[3].y    };
    }

    @Override
    public void render () {
        polyBatch.begin();
        poly.draw(polyBatch);
        if(position == Position.Position_Bottom){
            font.draw(polyBatch, "dialog board here", 5, computeBoardHeight());
        } else {
            font.draw(polyBatch, "dialog board here", 5, Gdx.graphics.getHeight());
        }
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
        font.dispose();
    }

    public int computeBoardHeight(){
        return linesOfText * (int) font.getLineHeight();
    }
}

