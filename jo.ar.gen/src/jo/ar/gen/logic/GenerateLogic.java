package jo.ar.gen.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jo.ar.gen.data.GalaxyBean;
import jo.ar.gen.data.GenerationParams;
import jo.ar.gen.data.StarBean;

public class GenerateLogic
{
    public static void generateSystems(GenerationParams params, GalaxyBean galaxy)
    {
        Random r = new Random();
        if (params.getSeed() != 0L)
            r.setSeed(params.getSeed());
        for (int gx = -params.getGenerateRadius(); gx <= params.getGenerateRadius(); gx++)
            for (int gy = -params.getGenerateRadius(); gy <= params.getGenerateRadius(); gy++)
            {
                if ((gx > -params.getOmitRadius()) && (gx < params.getOmitRadius()) && (gy > -params.getOmitRadius()) && (gy < params.getOmitRadius()))
                    continue;
                List<StarBean> stars = getStarsWithin(galaxy, gx*params.getGridSize(), gy*params.getGridSize(), params.getGridSize(), params.getGridSize());
                galaxy.getStars().removeAll(stars);
                generateWithin(galaxy, params, r, gx, gy);
            }
    }

    private static void generateWithin(GalaxyBean galaxy,
            GenerationParams params, Random r, int gx, int gy)
    {
        List<StarBean> stars = generateStars(params, r, gx, gy);
        generatePlanets(params, r, stars);
        galaxy.getStars().addAll(stars);
    }

    private static void generatePlanets(GenerationParams params,
            Random r, List<StarBean> stars)
    {
        for (int i = 0; i < stars.size(); i++)
        {
            StarBean s = stars.get(i);
            s.setNumPlanets(D(r, 2) - 2);
            s.setNumGasGiants(D(r, 1)/2);
        }        
    }

    private static List<StarBean> generateStars(GenerationParams params,
            Random r, int gx, int gy)
    {
        List<StarBean> stars = new ArrayList<>();
        int v = D(r, 2);
        int[] rolls = new int[2];
        StarBean s = new StarBean();
        generateStar(params, s, r, stars, rolls);
        if (v >= 8)
        {
            s = new StarBean();
            generateStar(params, s, r, stars, rolls);
            if (v >= 12)
            {
                s = new StarBean();
                generateStar(params, s, r, stars, rolls);
            }
        }
        // fill out details
        for (int i = 0; i < stars.size(); i++)
        {
            s = stars.get(i);
            if (i == 0)
            {
                s.setName(NameLogic.generatePlaceName(r));
                if (stars.size() > 1)
                    s.setName(s.getName()+"A");
                s.setX(gx*params.getGridSize() + r.nextInt(params.getGridSize()));
                s.setY(gy*params.getGridSize() + r.nextInt(params.getGridSize()));
            }
            else
            {
                StarBean primary = stars.get(0);
                if (i == 1)
                {
                    s.setName(primary.getName().substring(0, primary.getName().length() - 1)+"B");
                    s.setX(primary.getX() + r.nextInt(params.getGridSize()/2));
                    s.setY(primary.getY() + r.nextInt(params.getGridSize()/2));
                }
                else if (i == 2)
                {
                    s.setName(primary.getName().substring(0, primary.getName().length() - 1)+"C");
                    s.setX(primary.getX() + r.nextInt(params.getGridSize()/3));
                    s.setY(primary.getY() + r.nextInt(params.getGridSize()/3));
                }
            }
            s.setSize(s.getMass());
        }
        return stars;
    }

    private static void generateStar(GenerationParams params, StarBean s, Random r, List<StarBean> others, int[] rolls)
    {
        if (others.size() == 0)
            generateStarPrimary(params, s, r, others, rolls);
        else
            generateStarSecondary(params, s, r, others, rolls);
        others.add(s);
    }

    private static void generateStarPrimary(GenerationParams params, StarBean s, Random r, List<StarBean> others, int[] rolls)
    {
        int dm;
        if (params.isMostlyHabitable())
            dm = 5;
        else
            dm = 0;
        int typeRoll = D(r, 2) + dm;
        if (typeRoll < 2)
            typeRoll = 2;
        else if (typeRoll > 12)
            typeRoll = 12;
        switch (typeRoll)
        {
            case 2 :
                s.setStarType(StarBean.ST_A);
                break;
            case 3 :
            case 4 :
            case 5 :
            case 6 :
            case 7 :
                s.setStarType(StarBean.ST_M);
                break;
            case 8 :
                s.setStarType(StarBean.ST_K);
                break;
            case 9 :
            case 10 :
                s.setStarType(StarBean.ST_G);
                break;
            case 11 :
            case 12 :
                s.setStarType(StarBean.ST_F);
                break;
        }
        int classRoll = D(r, 2) + dm;
        if (classRoll < 2)
            classRoll = 2;
        else if (classRoll > 12)
            classRoll = 12;
        switch (classRoll)
        {
            case 2 :
                s.setStarClass(StarBean.SC_2);
                break;
            case 3 :
                s.setStarClass(StarBean.SC_3);
                break;
            case 4 :
                s.setStarClass(StarBean.SC_4);
                break;
            case 5 :
            case 6 :
            case 7 :
            case 8 :
            case 9 :
            case 10 :
            case 11 :
            case 12 :
                s.setStarClass(StarBean.SC_5);
                break;
        }
        s.setStarType(s.getStarType() + r.nextInt(10));
        rolls[0] = typeRoll;
        rolls[1] = classRoll;
    }

    private static void generateStarSecondary(GenerationParams params, StarBean s, Random r, List<StarBean> mw, int[] rolls)
    {
        int typeRoll;

        typeRoll = D(r, 2) + rolls[0];
        if (typeRoll < 2)
            typeRoll = 2;
        else if (typeRoll > 12)
            typeRoll = 12;
        switch (typeRoll)
        {
            case 2 :
                s.setStarType(StarBean.ST_A);
                break;
            case 3 :
            case 4 :
                s.setStarType(StarBean.ST_F);
                break;
            case 5 :
            case 6 :
                s.setStarType(StarBean.ST_G);
                break;
            case 7 :
            case 8 :
                s.setStarType(StarBean.ST_K);
                break;
            case 9 :
            case 10 :
            case 11 :
            case 12 :
                s.setStarType(StarBean.ST_M);
                break;
        }
        int classRoll = D(r, 2) + rolls[1];
        if (classRoll < 2)
            classRoll = 2;
        else if (classRoll > 12)
            classRoll = 12;
        switch (classRoll)
        {
            case 2 :
                s.setStarClass(StarBean.SC_2);
                break;
            case 3 :
                s.setStarClass(StarBean.SC_3);
                break;
            case 4 :
                s.setStarClass(StarBean.SC_4);
                break;
            case 5 :
            case 6 :
            case 7 :
            case 8 :
            case 9 :
            case 10 :
            case 11 :
                s.setStarClass(StarBean.SC_5);
                break;
            case 12 :
                s.setStarClass(StarBean.SC_D);
                break;
        }
        s.setStarType(s.getStarType() + r.nextInt(10));
    }

    private static List<StarBean> getStarsWithin(GalaxyBean galaxy, int x, int y, int width,
            int height)
    {
        List<StarBean> stars = new ArrayList<>();
        for (StarBean star : galaxy.getStars())
            if ((star.getX() >= x) && (star.getX() < x + width) && (star.getY() >= y) && (star.getY() < y + height))
                stars.add(star);
        return stars;
    }
    
    private static int D(Random r, int num)
    {
        int tot = num;
        while (num-- > 0)
            tot += r.nextInt(6);
        return tot;
    }
}
