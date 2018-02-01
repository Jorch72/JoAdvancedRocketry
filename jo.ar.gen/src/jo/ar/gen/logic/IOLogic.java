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
import jo.util.utils.obj.DoubleUtils;
import jo.util.utils.obj.IntegerUtils;
import jo.util.utils.xml.XMLEditUtils;
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
            star.setSize(DoubleUtils.parseDouble(XMLUtils.getAttribute(s, "size")));
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
                planet.setGravitationalMultiplier(readInteger(p, "gravitationalMultiplier", 100));
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
    
    public static void writePlanetDefs(GalaxyBean galaxy, File planetDefsFile) throws IOException
    {
        Document doc = writePlanetDefsToXML(galaxy);
        XMLUtils.writeFile(doc, planetDefsFile);
    }
    
    public static Document writePlanetDefsToXML(GalaxyBean galaxy)
    {
        Document doc = XMLUtils.newDocument();
        Node g = XMLEditUtils.addElement(doc, "galaxy");
        for (StarBean star : galaxy.getStars())
        {
            Node s = XMLEditUtils.addElement(g, "star");
            writeAttrString(s, "name", star.getName());
            writeAttrInt(s, "x", star.getX(), 999999);
            writeAttrInt(s, "y", star.getY(), 999999);
            writeAttrInt(s, "temp", star.getTemp(), -1);
            XMLEditUtils.addAttribute(s, "size", String.valueOf(star.getSize()));
            writeAttrInt(s, "numPlanets", star.getNumPlanets(), -1);
            writeAttrInt(s, "numGasGiants", star.getNumGasGiants(), -1);
            for (PlanetBean planet : star.getPlanets())
            {
                Node p = XMLEditUtils.addElement(s, "planet");
                writeAttrString(p, "name", planet.getName());
                writeAttrInt(p, "DIMID", planet.getDimId(), 0);
                writeAttrInt(p, "dimMapping", planet.getDimMapping(), 0);
                writeAttrString(p, "customIcon", planet.getCustomIcon());
                writeBoolean(p, "isKnown", planet.isKnown(), false);
                writeBoolean(p, "hasRings", planet.isRings(), false);
                writeBoolean(p, "GasGiant", planet.isGasGiant(), false);
                for (String gas : planet.getGas())
                    XMLEditUtils.addTextTag(p, "gas", gas);
                writeEleString(p, "skyColor", planet.getSkyColor());
                writeEleString(p, "fogColor", planet.getFogColor());
                writeEleInt(p, "atmosphereDensity", planet.getAtmosphereDensity(), 100);
                writeEleInt(p, "gravitationalMultiplier", planet.getGravitationalMultiplier(), 100);
                writeEleInt(p, "orbitalDistance", planet.getOrbitalDistance(), 100);
                writeEleInt(p, "orbitalTheta", planet.getOrbitalTheta(), 0);
                writeEleInt(p, "orbitalPhi", planet.getOrbitalPhi(), 0);
                writeEleInt(p, "rotationalPeriod", planet.getRotationalPeriod(), 24000);
                writeEleString(p, "biomeIds", planet.getBiomeIds());
                for (String a : planet.getArtifacts())
                    XMLEditUtils.addTextTag(p, "artifact", a);
                if (planet.getOres().size() > 0)
                {
                    Node os = XMLEditUtils.addElement(p, "OreGen");
                    for (OreBean ore : planet.getOres())
                    {
                        Node o = XMLEditUtils.addElement(os, "ore");
                        writeAttrString(o, "block", ore.getBlock());
                        writeAttrInt(o, "meta", ore.getMeta(), 0);
                        writeAttrInt(o, "mminHeighteta", ore.getMinHeight(), 0);
                        writeAttrInt(o, "maxHeight", ore.getMaxHeight(), 0);
                        writeAttrInt(o, "clumpSize", ore.getClumpSize(), 0);
                        writeAttrInt(o, "chancePerChunk", ore.getChancePerChunk(), 0);
                    }
                }
            }
        }
        return doc;
    }

    private static void writeAttrString(Node n, String name, String val)
    {
        if (val != null)
            XMLEditUtils.addAttribute(n, name, val);
    }

    private static void writeEleString(Node n, String name, String val)
    {
        if (val != null)
            XMLEditUtils.addTextTag(n, name, val);
    }

    private static void writeEleInt(Node n, String name, int val, int def)
    {
        if (val != def)
            XMLEditUtils.addTextTag(n, name, String.valueOf(val));
    }
    
    private static void writeAttrInt(Node n, String name, int val, int def)
    {
        if (val != def)
            XMLEditUtils.addAttribute(n, name, String.valueOf(val));
    }
    
    private static void writeBoolean(Node n, String name, boolean val, boolean def)
    {
        if (val != def)
            XMLEditUtils.addTextTag(n, name, val ? "True" : "False");            
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
