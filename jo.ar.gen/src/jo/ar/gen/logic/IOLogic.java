package jo.ar.gen.logic;

import java.io.File;
import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import jo.ar.gen.data.GalaxyBean;
import jo.ar.gen.data.OreBean;
import jo.ar.gen.data.PlanetBean;
import jo.ar.gen.data.StarBean;
import jo.util.utils.obj.BooleanUtils;
import jo.util.utils.obj.IntegerUtils;
import jo.util.utils.xml.XMLUtils;

public class IOLogic
{
    public static GalaxyBean readPlanetDefs(File planetDefsFile) throws IOException
    {
        Document doc = XMLUtils.readFile(planetDefsFile);
        if (doc == null)
            return null;
        GalaxyBean galaxy = new GalaxyBean();
        for (Node s : XMLUtils.findNodes(doc, "galaxy/star"))
        {
            StarBean star = new StarBean();
            galaxy.getStars().add(star);
            star.setName(XMLUtils.getAttribute(s, "name"));
            star.setX(IntegerUtils.parseInt(XMLUtils.getAttribute(s, "x")));
            star.setY(IntegerUtils.parseInt(XMLUtils.getAttribute(s, "y")));
            star.setTemp(IntegerUtils.parseInt(XMLUtils.getAttribute(s, "temp")));
            star.setNumPlanets(IntegerUtils.parseInt(XMLUtils.getAttribute(s, "numPlanets")));
            star.setNumGasGiants(IntegerUtils.parseInt(XMLUtils.getAttribute(s, "numGasGiants")));
            for (Node p : XMLUtils.findNodes(s, "planet"))
            {
                PlanetBean planet = new PlanetBean();
                star.getPlanets().add(planet);
                planet.setName(XMLUtils.getAttribute(p, "name"));
                planet.setDimId(IntegerUtils.parseInt(XMLUtils.getAttribute(p, "DIMID")));
                planet.setDimMapping(IntegerUtils.parseInt(XMLUtils.getAttribute(p, "dimMapping")));
                planet.setCustomIcon(XMLUtils.getAttribute(p, "customIcon"));
                planet.setKnown(readBoolean(p, "isKnown", false));
                planet.setRings(readBoolean(p, "hasRings", false));
                planet.setGasGiant(readBoolean(p, "GasGiant", false));
                for (Node g : XMLUtils.findNodes(p, "gas"))
                    planet.getGas().add(XMLUtils.getText(g));
                planet.setSkyColor(readString(p, "skyColor", null));
                planet.setFogColor(readString(p, "fogColor", null));
                planet.setAtmosphereDensity(readInteger(p, "atmosphereDensity", 100));
                planet.setOrbitalDistance(readInteger(p, "orbitalDistance", 100));
                planet.setOrbitalTheta(readInteger(p, "orbitalTheta", 0));
                planet.setOrbitalPhi(readInteger(p, "orbitalPhi", 0));
                planet.setRotationalPeriod(readInteger(p, "rotationalPeriod", 24000));
                planet.setBiomeIds(readString(p, "biomeIds", null));
                planet.setGasGiant(readBoolean(p, "GasGiant", false));
                for (Node a : XMLUtils.findNodes(p, "artifact"))
                    planet.getArtifacts().add(XMLUtils.getText(a));
                for (Node o : XMLUtils.findNodes(p, "OreGen/ore"))
                {
                    OreBean ore = new OreBean();
                    planet.getOres().add(ore);
                    ore.setBlock(XMLUtils.getAttribute(o, "block"));
                    ore.setMeta(IntegerUtils.parseInt(XMLUtils.getAttribute(o, "meta")));
                    ore.setMinHeight(IntegerUtils.parseInt(XMLUtils.getAttribute(o, "minHeight")));
                    ore.setMaxHeight(IntegerUtils.parseInt(XMLUtils.getAttribute(o, "maxHeight")));
                    ore.setClumpSize(IntegerUtils.parseInt(XMLUtils.getAttribute(o, "clumpSize")));
                    ore.setChancePerChunk(IntegerUtils.parseInt(XMLUtils.getAttribute(o, "chancePerChunk")));
                }
            }
        }
        return galaxy;
    }
    
    private static boolean readBoolean(Node n, String name, boolean def)
    {
        return BooleanUtils.parseBoolean(readString(n, name, String.valueOf(def)));
    }
    
    private static int readInteger(Node n, String name, int def)
    {
        return IntegerUtils.parseInt(readString(n, name, String.valueOf(def)));
    }
    
    private static String readString(Node n, String name, String def)
    {
        Node nn = XMLUtils.findFirstNode(n, name);
        if (nn != null)
            return XMLUtils.getText(nn).trim();
        return def;
    }
}
