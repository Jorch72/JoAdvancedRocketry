package jo.ar.gen.data;

import java.util.ArrayList;
import java.util.List;

public class StarBean
{
    public final static int ST_UNKNOWN = -2;
    public final static int ST_UNSET = -1;
    public final static int ST_O = 00;
    public final static int ST_B = 10;
    public final static int ST_A = 20;
    public final static int ST_F = 30;
    public final static int ST_G = 40;
    public final static int ST_K = 50;
    public final static int ST_M = 60;

    public final static int SC_UNKNOWN = -1;
    public final static int SC_UNSET = 0;
    public final static int SC_1A = 1;
    public final static int SC_1B = 2;
    public final static int SC_2 = 3;
    public final static int SC_3 = 4;
    public final static int SC_4 = 5;
    public final static int SC_5 = 6;
    public final static int SC_6 = 7;
    public final static int SC_D = 8;

    // required information
    private String  mName;
    private int     mX;
    private int     mY;
    private int     mTemp = 100;
    private int     mNumPlanets;
    private int     mNumGasGiants;
    private double  mSize = 1.0;
    private List<PlanetBean> mPlanets = new ArrayList<>();

    // extended information
    private int     mStarType;
    private int     mStarClass;
    
    // utilities

    public double getMass()
    {
        return calcMassFromTypeAndClass(mStarType, mStarClass);
    }
    
    private static double calcMassFromTypeAndClass(int _type, int _class)
    {
        int StarOff;
        double StarMult, Diff;

        if (_type >= 69) // M9
            return tableStellarMass[_class - 1][12];
        StarOff = _type / 5;
        if (StarOff > 1)
            StarOff -= 2;
        StarMult = (double) (_type % 5) / 5;
        Diff =
            (tableStellarMass[_class
                - 1][StarOff
                + 1]
                - tableStellarMass[_class
                - 1][StarOff]);
        return (tableStellarMass[_class - 1][StarOff] + Diff * StarMult);
    }

    private static final double tableStellarMass[][] =
        {
            {
                60.0,
                30.0,
                18.0,
                15.0,
                13.0,
                12.0,
                12.0,
                13.0,
                14.0,
                18.0,
                20.0,
                25.0,
                30.0 },
            {
            50.0,
                25.0,
                16.0,
                13.0,
                12.0,
                10.0,
                10.0,
                12.0,
                13.0,
                16.0,
                16.0,
                20.0,
                25.0 },
                {
            30.0,
                20.0,
                14.0,
                11.0,
                10.0,
                8.1,
                8.1,
                10.0,
                11.0,
                14.0,
                14.0,
                16.0,
                18.0 },
                {
            25.0,
                15.0,
                12.0,
                9.0,
                8.0,
                5.0,
                2.5,
                3.2,
                4.0,
                5.0,
                6.3,
                7.4,
                9.2 },
                {
            20.0,
                10.0,
                6.0,
                4.0,
                2.5,
                2.0,
                1.75,
                2.0,
                2.3,
                2.3,
                2.3,
                2.3,
                2.3 },
                {
            18.0,
                6.5,
                3.2,
                2.1,
                1.7,
                1.3,
                1.04,
                0.94,
                0.825,
                0.570,
                0.489,
                0.331,
                0.215 },
                {
            0.8,
                0.8,
                0.8,
                0.8,
                0.8,
                0.8,
                0.6,
                0.528,
                0.430,
                0.330,
                0.154,
                0.104,
                0.058 },
                {
            0.26,
                0.26,
                0.36,
                0.36,
                0.42,
                0.42,
                0.63,
                0.63,
                0.83,
                0.83,
                1.11,
                1.11,
                1.11 }
    };
    private static final double tableStellarLuminosity[][] =
        {
            {
                560000,
                204000,
                107000,
                81000,
                61000,
                51000,
                67000,
                89000,
                97000,
                107000,
                117000,
                129000,
                141000 },
            {
            270000,
                46700,
                15000,
                11700,
                7400,
                5100,
                6100,
                8100,
                11700,
                20400,
                46000,
                89000,
                117000 },
                {
            170000,
                18600,
                2200,
                850,
                600,
                510,
                560,
                740,
                890,
                2450,
                4600,
                14900,
                16200 },
                {
            107000,
                6700,
                280,
                90,
                53,
                43,
                50,
                75,
                95,
                320,
                470,
                2280,
                2690 },
                {
            81000,
                2000,
                156,
                37,
                19,
                12,
                6.5,
                4.9,
                4.67,
                4.67,
                4.67,
                4.67,
                4.67 },
                {
            56000,
                1400,
                90,
                16,
                8.1,
                3.5,
                1.21,
                .67,
                .42,
                .08,
                .04,
                .007,
                .001 },
                {
            .977,
                .977,
                .977,
                .977,
                .977,
                .977,
                .322,
                .186,
                .117,
                .025,
                .011,
                .002,
                .00006 },
                {
            .046,
                .046,
                .005,
                .005,
                .0003,
                .0003,
                .00006,
                .00006,
                .00004,
                .00004,
                .00003,
                .00003,
                .00003 }
    };
    
    // getters and setters

    public List<PlanetBean> getPlanets()
    {
        return mPlanets;
    }

    public void setPlanets(List<PlanetBean> planets)
    {
        mPlanets = planets;
    }

    public String getName()
    {
        return mName;
    }

    public void setName(String name)
    {
        mName = name;
    }

    public int getX()
    {
        return mX;
    }

    public void setX(int x)
    {
        mX = x;
    }

    public int getY()
    {
        return mY;
    }

    public void setY(int y)
    {
        mY = y;
    }

    public int getTemp()
    {
        return mTemp;
    }

    public void setTemp(int temp)
    {
        mTemp = temp;
    }

    public int getNumPlanets()
    {
        return mNumPlanets;
    }

    public void setNumPlanets(int numPlanets)
    {
        mNumPlanets = numPlanets;
    }

    public int getNumGasGiants()
    {
        return mNumGasGiants;
    }

    public void setNumGasGiants(int numGasGiants)
    {
        mNumGasGiants = numGasGiants;
    }

    public int getStarType()
    {
        return mStarType;
    }

    public void setStarType(int starType)
    {
        mStarType = starType;
    }

    public int getStarClass()
    {
        return mStarClass;
    }

    public void setStarClass(int starClass)
    {
        mStarClass = starClass;
    }

    public double getSize()
    {
        return mSize;
    }

    public void setSize(double size)
    {
        mSize = size;
    }


}
