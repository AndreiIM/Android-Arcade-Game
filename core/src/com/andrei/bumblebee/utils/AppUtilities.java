package com.andrei.bumblebee.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.andrei.bumblebee.BumblebeeGame;
import com.andrei.bumblebee.drawables.Hexagon;
import com.andrei.bumblebee.drawables.HoneyComb;
import com.andrei.bumblebee.pojos.Node;
import com.andrei.bumblebee.pojos.Point;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ArrayList;

/**
 * Created by Vlad on 05.04.2015.
 */
public class AppUtilities {

    /**
     * Computes hexagon radius based on screen size and desired number of hexes
     */
    public static double getHexRadius(int screen_width, int margin, int hexagon_count) {
        return (screen_width - 2 * margin) / (2 * hexagon_count * Math.sqrt(1 - Math.pow(Math.sin(Math.toRadians(30)), 2)));
    }

    private static float getSafeScaledWidth(float spriteWidth, float spriteHeight, float ratioFromHexagon){
        return (ratioFromHexagon * BumblebeeGame.GAME_SCALE) * spriteWidth / spriteHeight;
    }

    private static float getSafeScaleHeight(float ratioFromHexagon){
        return (ratioFromHexagon * BumblebeeGame.GAME_SCALE);
    }

    /** safe resize a sprite by specified ammound (relative to a hexagon)*/
    public static void safeResize(Sprite sprite, float amount){
        if(sprite != null)
            sprite.setSize(AppUtilities.getSafeScaledWidth(sprite.getWidth(), sprite.getHeight(), amount), AppUtilities.getSafeScaleHeight(amount));
    }

    /** safe resize a font by specified ammound (relative to screen)*/
    public static void safeResize(BitmapFont font, float amount){
        if(font != null)
            font.setScale(amount * Gdx.graphics.getHeight() / 200);
    }

    /** safe resize a table by specified ammound (relative to screen)*/
    public static void safeResize(Table table, float amount){
        if(table != null)
            table.setScale(amount * Gdx.graphics.getHeight() / 200);
    }

    /**
     * Return screen coordinates of some world coordinates
     */
    public static Point worldToScreen(int x, int y) {
        return new Point(x, Gdx.graphics.getHeight() - y);
    }

    public static Point worldToScreen(Point p) {
        return new Point(p.x, Gdx.graphics.getHeight() - p.y);
    }



    /*
1) Deseneaza o linie orizontala in dreapta fiecarui punct si extinde-o catre infinit

2) Numara de cate ori linie intersecteaza laturile poligonului

3) Un punct este in interiorul unui poligon daca numarul intersectiilor este impar sau un punct
   este pe latura unui poligon.Daca aceste conditie nu sunt adevarate atunci el este in
   afara poligonului

 */
    // Avand 3 puncte colineare p, q, r, functia verifica daca
    // punctul q este pe segmentul de linie pr
    private static boolean checkIfPointIsOnSegment(Point p, Point q, Point r) {
        if (q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) &&
                q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y))
            return true;
        return false;
    }

    // Pentru gasirea orientarii segmentului (p, q, r).
    // Valorile functiei
    // 0 --> p, q si r sunt colineare
    // 1 --> In sensul acelor de ceasornic
    // 2 --> Invers acelor de ceasornic
    private static Integer checkLineOrientaion(Point p, Point q, Point r) {
        int value = (q.y - p.y) * (r.x - q.x) -
                (q.x - p.x) * (r.y - q.y);

        if (value == AppConstants.COLLINEAR_SEGMENT) return AppConstants.COLLINEAR_SEGMENT;
        return (value > AppConstants.COLLINEAR_SEGMENT) ? AppConstants.CLOCKWISE_SEGMENT : AppConstants.COUNTERCLOCKWISE_SEGMENT;
    }

    // Functia intoarce true daca segmentul 'p1q1'
    // si 'p2q2' se intersecteaza.
    private static Boolean checkIfTwoLinesIntersect(Point p1, Point q1, Point p2, Point q2) {
        // Gaseste cele patru orientari
        int o1 = checkLineOrientaion(p1, q1, p2);
        int o2 = checkLineOrientaion(p1, q1, q2);
        int o3 = checkLineOrientaion(p2, q2, p1);
        int o4 = checkLineOrientaion(p2, q2, q1);

        // Caz general
        if (o1 != o2 && o3 != o4)
            return true;

        // Caz special
        // p1, q1 si p2 sunt colineare si p2 este pe segmentul p1q1
        if (o1 == 0 && checkIfPointIsOnSegment(p1, p2, q1)) return true;

        // p1, q1 si  p2 sunt colineare si q2 este pe segmentul p1q1
        if (o2 == 0 && checkIfPointIsOnSegment(p1, q2, q1)) return true;

        // p2, q2 si p1 sunt colineare si p1 este pe segmentul p2q2
        if (o3 == 0 && checkIfPointIsOnSegment(p2, p1, q2)) return true;

        // p2, q2 si q1 sunt colineare q1 este pe segmentul p2q2
        if (o4 == 0 && checkIfPointIsOnSegment(p2, q1, q2)) return true;

        return false; // Nu cade in nici un caz de mai sus
    }

    //Intoarce adevarat daca punctul p este in interiorul polygonul polygon[] cu n noduri
    public static Boolean checkIfPointISInsidePolygon(Point polygon[], Point p) {
        // Trebuie sa existe cel putin 3 noduri in polygon[]
        if (polygon.length < 3) return false;

        //Creaza un punct pentru segmentul incepand de la p catre infinit
        Point extreme = new Point(AppConstants.INFINITE, p.y);

        //Numara intersectiile
        int count = 0, i = 0;
        do {
            int next = (i + 1) % polygon.length;

            //Verifica daca segmentul de dreapta incepand de la "p" pana la "infinit"
            //se intersecteaza cu segmentul de dreapta incepand de la polygon[i]  catre polygon[next]
            if (checkIfTwoLinesIntersect(polygon[i], polygon[next], p, extreme)) {
                //Daca punctul 'p' este colinear cu segmentul de linie  'i-next'
                //atunci verifica daca este pe segment.Daca da intoarce true altfel intoarce false
                if (checkLineOrientaion(polygon[i], p, polygon[next]) == 0)
                    return checkIfPointIsOnSegment(polygon[i], p, polygon[next]);

                count++;
            }
            i = next;
        } while (i != 0);

        // Intoarce true daca este impar si false par
        return (count % 2 == 1 ? true : false);
    }

    ///drumul minim////////////
    public static Point findPath(List<Point> hexagonList, List<Point> obstaclesList, Point initial) {

       // System.out.println(listPoints.size());
        //punctele de plecare
        int X_initial = initial.x;
        int Y_initial = initial.y;

        int maxx = 0;

        //vectorii de directie
        //int dx[] = {-21, 20, 10, -11, -11, 10, 0};
        //int dy[] = {0, 0, -18,-18, 18, 18, 0};

        int dx[] = {0,-21,-11,+10,+20,+10,-11,0};
        int dy[] = {0,+0,-18,-18,+0,+18,+18,0};

        int dx1[] = {0,-10,-21,-10,+11,21,11,0};
        int dy1[] = {0,-18,0,-18,-18,0,+18,0};


        //coada unde retin punctele
        int coadaX[] = new int[1010*1010+10];
        int coadaY[] = new int[1010*1010+10];

        int[][] A = new int[1010][1010];
        int p, u;

        //construiesc matricea A
        //o initializez cu -1

        //List<Hexagon> hexagonList = new ArrayList<Hexagon>();
        //getHexagonsCenterPoints()


        for (int i = 0; i <= 1000; ++i) {
            for (int j = 0; j <= 1000; ++j) {
                A[i][j] = -100;
            }
        }

        //-100 nu luam in calcul
        //-1 e drum
        //-2 e obstacol
       // System.out.println("Lista hexagoane");
        for (int k = 0; k < hexagonList.size(); ++k){
             A[hexagonList.get(k).x][hexagonList.get(k).y] = -1;
            //System.out.println(hexagonList.get(k).x + " " + hexagonList.get(k).y);
        }


        //System.out.println("Lista obstacole");
        for (int k = 0; k < obstaclesList.size(); ++k) {

            //lista de obstacole
            Point P = obstaclesList.get(k);
            //System.out.println(P.x + " " +P.y);

            int xx = P.x/* + AppConstants.MIN_PATH_CONST*/;
            int yy = P.y/* + AppConstants.MIN_PATH_CONST*/;

                A[xx][yy] = -100;
        }

        A[X_initial][Y_initial] = 1;

        p = 0;
        u = 0;
        coadaX[p] = X_initial;
        coadaY[p] = Y_initial;

        int X = X_initial;
        int Y = Y_initial;

        while (p <= u) {

            //scoate din coada
            int Qx = coadaX[p];
            int Qy = coadaY[p];
            if (Qx <= 0 || Qx >= 1000 || Qy <= 0 || Qy >= 1000)
                break;

            if (u>=1010*1010) break;

            p++;
            if (Qy == 328 || Qy == 292 || Qy == 256 || Qy == 364 || Qy == 400) {
                //ma duc pe cele 7 directii
                if (Qx <= 0 || Qx >= 1000 || Qy <= 0 || Qy >= 1000)
                    break;

                if (u>=1010*1010) break;

                for (int k = 1; k <= 6; ++k) {
                    int Px = Qx + dx[k];
                    int Py = Qy + dy[k];
                    if (Px <= 0 || Px >= 1000 || Py <= 0 || Py >= 1000)
                        break;

                    //System.out.println(Px + " " + Py);
                    if (A[Px][Py] == -1) {
                        A[Px][Py] = A[Qx][Qy] + 1;
                        u++;
                        coadaX[u] = Px;
                        coadaY[u] = Py;

                    }

                }
            }
            else {

                if (Qx <= 0 || Qx >= 1000 || Qy <= 0 || Qy >= 1000)
                    break;

                if (u>=1010*1010) break;

                for (int k = 1; k <= 6; ++k) {
                    int Px = Qx + dx1[k];
                    int Py = Qy + dy1[k];

                    if (Px <= 0 || Px >= 1000 || Py <= 0 || Py >= 1000)
                        break;
                    //System.out.println(Px + " " + Py);
                    if (A[Px][Py] == -1) {
                        A[Px][Py] = A[Qx][Qy] + 1;
                        u++;
                        coadaX[u] = Px;
                        coadaY[u] = Py;

                    }

                }
            }
        }

        //System.out.println("Lista");
        //for (int i =0 ;i<hexagonList.size();++i){
        //    System.out.println( A[hexagonList.get(i).x][hexagonList.get(i).y]);
        //}

       // System.out.println(A[130][418]);
        //System.out.println(A[16][328]);
        //System.out.println(A[130][238]);
        //System.out.println(A[223][328]);

        if (Y_initial == 328 || Y_initial == 292 || Y_initial == 256 || Y_initial == 364 || Y_initial == 400) {
            for (int k = 1; k <= 6; ++k)
                if (A[X_initial + dx[k]][Y_initial + dy[k]] > 1) {
                    Point point = new Point(X_initial + dx[k], Y_initial + dy[k]);
                    //System.out.println((i + dx[k] - Const) + " " + (j + dy[k] - Const));
                    return point;
                }
        }
        else{
            for (int k = 1; k <= 6; ++k)
                if (A[X_initial + dx1[k]][Y_initial + dy1[k]] > 1) {
                    Point point = new Point(X_initial + dx1[k], Y_initial + dy1[k]);
                    //System.out.println((i + dx[k] - Const) + " " + (j + dy[k] - Const));
                    return point;
                }
        }



        //nu se mai poate deplasa
        //GAME OVER
        Point point = new Point(-1,-1);
        return point;

    }

    /**
     * Generates N number of points using the cross product
     *
     */
    public static List<Point> generatePointsBetweenLine(Point firstLinePoint,Point secondLinePoint,Integer number){

        int k = 0,range = 0,dxc = 0,dyc = 0,dxl = 0, dyl = 0 ;
        Random r = new Random();
        if(firstLinePoint.x > secondLinePoint.x)
            range = firstLinePoint.x + firstLinePoint.y;
        else
            range = secondLinePoint.x + secondLinePoint.y;
        List<Point> pointList = new ArrayList<Point>();
        while (k<number)
        {
            Point point = new Point(r.nextInt(range), r.nextInt(range));

            dxc = point.x - firstLinePoint.x;
            dyc = point.y - firstLinePoint.y;

            dxl = secondLinePoint.x - firstLinePoint.x;
            dyl = secondLinePoint.y - firstLinePoint.y;

           int crossProduct = dxc * dyl - dyc * dxl;

           if(crossProduct == 0){
               pointList.add(k,point);
               k++;
           }

        }
        return pointList;
    }
    /**
     * Generates N number of random objects
     * Reservoir Sampling Algorithm
     */
    public static List<Hexagon> getRandomHexagons(List<Hexagon> sourceElements, Integer howMany){
        if(sourceElements == null)
            throw new RuntimeException("sourceElements is null in getRandomPoints!");

        Map<Integer, Hexagon> returnedElements = new HashMap<Integer, Hexagon>(howMany);

        //exclude point representing center of hexagon (this is the bumblebee position)
        Iterator<Hexagon> iter = sourceElements.iterator();
        while (iter.hasNext()) {
            Hexagon hex = iter.next();

            if(hex.equals(BumblebeeGame.HONEYCOMB_CENTER)){
                iter.remove();
            }
        }

        Random randomNr = new Random();

        for(int j = 0; j < howMany; j++){

            int number = randomNr.nextInt(sourceElements.size() - 1);

            //this number was already generated, try another
            while(returnedElements.get(number) != null){
                number = randomNr.nextInt(sourceElements.size() - 1);
            }

            returnedElements.put(number, sourceElements.get(number));

        }
        return new ArrayList<Hexagon>(returnedElements.values());
    }

    /** get the list of points representing the center points of hexagons from the list passed as argument*/
    public static List<Point> getHexagonsCenterPoints(Map<String,Hexagon> hexMap){
        if(hexMap == null)
            throw new RuntimeException("hexMap is null in getHexagonsCenterPoints!");

        List<Point> centerList = new ArrayList<Point>();

        Iterator it = hexMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Hexagon hex = (Hexagon) pair.getValue();
            centerList.add(hex.getCenter());
        }

        return centerList;
    }

    /** get a hex from the map by giving Node indices*/
    public static Hexagon getHexFromMapByNode(Map<String,Hexagon> hexMap, Node node){

        return  hexMap.get(node.getIndexX() + "," +node.getIndexY());

    }

    public static Node getNodeFromMap(Map<String,Node> nodeMap,int indexX, int indexY){

        return nodeMap.get(indexX+ "," + indexY);
    }

    public static Node getNodeFromMapByHex(Map<String,Node> nodeMap, Hexagon hex){

        return  nodeMap.get(hex.getIndexX() + "," +hex.getIndexY());

    }
}
