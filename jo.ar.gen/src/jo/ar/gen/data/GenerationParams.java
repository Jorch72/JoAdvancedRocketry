package jo.ar.gen.data;

public class GenerationParams
{
    private int mGridSize = 100;
    private int mGenerateRadius = 5;
    private int mOmitRadius = 1;
    private long mSeed = 0L;
    private boolean mMostlyHabitable = true;

    public int getGridSize()
    {
        return mGridSize;
    }

    public void setGridSize(int gridSize)
    {
        mGridSize = gridSize;
    }

    public int getGenerateRadius()
    {
        return mGenerateRadius;
    }

    public void setGenerateRadius(int gridRadius)
    {
        mGenerateRadius = gridRadius;
    }

    public int getOmitRadius()
    {
        return mOmitRadius;
    }

    public void setOmitRadius(int omitRadius)
    {
        mOmitRadius = omitRadius;
    }

    public long getSeed()
    {
        return mSeed;
    }

    public void setSeed(long seed)
    {
        mSeed = seed;
    }

    public boolean isMostlyHabitable()
    {
        return mMostlyHabitable;
    }

    public void setMostlyHabitable(boolean mostlyHabitable)
    {
        mMostlyHabitable = mostlyHabitable;
    }
}
