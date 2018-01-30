package jo.ar.gen.data;

import java.util.ArrayList;
import java.util.List;

public class GalaxyBean
{
    private List<StarBean> mStars = new ArrayList<>();

    public List<StarBean> getStars()
    {
        return mStars;
    }

    public void setStars(List<StarBean> stars)
    {
        mStars = stars;
    }
}
