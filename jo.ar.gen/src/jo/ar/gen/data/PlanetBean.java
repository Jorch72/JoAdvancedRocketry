package jo.ar.gen.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlanetBean
{
    private String  mName;
    private String  mCustomIcon;
    private boolean mKnown;
    private boolean mRings;
    private boolean mGasGiant;
    private Set<String> mGas = new HashSet<>();
    private Set<String> mArtifacts = new HashSet<>();
    private String  mFogColor;
    private String  mSkyColor;
    private int     mAtmosphereDensity;
    private int     mGravitationalMultiplier;
    private int     mOrbitalDistance;
    private int     mOrbitalTheta;
    private int     mOrbitalPhi;
    private int     mRotationalPeriod;
    private String  mBiomeIds;
    private int     mDimId;
    private int     mDimMapping;
    private List<OreBean> mOres = new ArrayList<>();

    public String getName()
    {
        return mName;
    }

    public void setName(String name)
    {
        mName = name;
    }

    public boolean isKnown()
    {
        return mKnown;
    }

    public void setKnown(boolean known)
    {
        mKnown = known;
    }

    public boolean isRings()
    {
        return mRings;
    }

    public void setRings(boolean rings)
    {
        mRings = rings;
    }

    public boolean isGasGiant()
    {
        return mGasGiant;
    }

    public void setGasGiant(boolean gasGiant)
    {
        mGasGiant = gasGiant;
    }

    public Set<String> getGas()
    {
        return mGas;
    }

    public void setGas(Set<String> gas)
    {
        mGas = gas;
    }

    public String getFogColor()
    {
        return mFogColor;
    }

    public void setFogColor(String fogColor)
    {
        mFogColor = fogColor;
    }

    public String getSkyColor()
    {
        return mSkyColor;
    }

    public void setSkyColor(String skyColor)
    {
        mSkyColor = skyColor;
    }

    public int getAtmosphereDensity()
    {
        return mAtmosphereDensity;
    }

    public void setAtmosphereDensity(int atmosphereDensity)
    {
        mAtmosphereDensity = atmosphereDensity;
    }

    public int getGravitationalMultiplier()
    {
        return mGravitationalMultiplier;
    }

    public void setGravitationalMultiplier(int gravitationalMultiplier)
    {
        mGravitationalMultiplier = gravitationalMultiplier;
    }

    public int getOrbitalDistance()
    {
        return mOrbitalDistance;
    }

    public void setOrbitalDistance(int orbitalDistance)
    {
        mOrbitalDistance = orbitalDistance;
    }

    public int getOrbitalTheta()
    {
        return mOrbitalTheta;
    }

    public void setOrbitalTheta(int orbitalTheta)
    {
        mOrbitalTheta = orbitalTheta;
    }

    public int getOrbitalPhi()
    {
        return mOrbitalPhi;
    }

    public void setOrbitalPhi(int orbitalPhi)
    {
        mOrbitalPhi = orbitalPhi;
    }

    public int getRotationalPeriod()
    {
        return mRotationalPeriod;
    }

    public void setRotationalPeriod(int rotationalPeriod)
    {
        mRotationalPeriod = rotationalPeriod;
    }

    public String getBiomeIds()
    {
        return mBiomeIds;
    }

    public void setBiomeIds(String biomeIds)
    {
        mBiomeIds = biomeIds;
    }

    public int getDimId()
    {
        return mDimId;
    }

    public void setDimId(int dimId)
    {
        mDimId = dimId;
    }

    public int getDimMapping()
    {
        return mDimMapping;
    }

    public void setDimMapping(int dimMapping)
    {
        mDimMapping = dimMapping;
    }

    public String getCustomIcon()
    {
        return mCustomIcon;
    }

    public void setCustomIcon(String customIcon)
    {
        mCustomIcon = customIcon;
    }

    public Set<String> getArtifacts()
    {
        return mArtifacts;
    }

    public void setArtifacts(Set<String> artifacts)
    {
        mArtifacts = artifacts;
    }

    public List<OreBean> getOres()
    {
        return mOres;
    }

    public void setOres(List<OreBean> ores)
    {
        mOres = ores;
    }
}
