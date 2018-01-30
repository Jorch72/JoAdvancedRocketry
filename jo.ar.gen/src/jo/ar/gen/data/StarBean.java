package jo.ar.gen.data;

import java.util.ArrayList;
import java.util.List;

public class StarBean
{
    private String  mName;
    private int     mX;
    private int     mY;
    private int     mTemp;
    private int     mNumPlanets;
    private int     mNumGasGiants;
    
    private List<PlanetBean> mPlanets = new ArrayList<>();

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


}
