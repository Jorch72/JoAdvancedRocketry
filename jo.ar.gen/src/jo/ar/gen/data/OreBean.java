package jo.ar.gen.data;

public class OreBean
{
    private String  mBlock; // the name or id of the block
    private int     mMeta; // optional attribute to specify the meta value of the block
    private int     mMinHeight; // minimum height at which to spawn the ore (between 1 and maxHeight)
    private int     mMaxHeight; // maximum height at which to spawn the ore (between minHeight and 255)
    private int     mClumpSize; // amount of ores to generate in each clump
    private int     mChancePerChunk; // maximum number of clumps that can be spawned in a given chunk
    
    public String getBlock()
    {
        return mBlock;
    }
    public void setBlock(String block)
    {
        mBlock = block;
    }
    public int getMeta()
    {
        return mMeta;
    }
    public void setMeta(int meta)
    {
        mMeta = meta;
    }
    public int getMinHeight()
    {
        return mMinHeight;
    }
    public void setMinHeight(int minHeight)
    {
        mMinHeight = minHeight;
    }
    public int getMaxHeight()
    {
        return mMaxHeight;
    }
    public void setMaxHeight(int maxHeight)
    {
        mMaxHeight = maxHeight;
    }
    public int getClumpSize()
    {
        return mClumpSize;
    }
    public void setClumpSize(int clumpSize)
    {
        mClumpSize = clumpSize;
    }
    public int getChancePerChunk()
    {
        return mChancePerChunk;
    }
    public void setChancePerChunk(int chancePerChunk)
    {
        mChancePerChunk = chancePerChunk;
    }
}
