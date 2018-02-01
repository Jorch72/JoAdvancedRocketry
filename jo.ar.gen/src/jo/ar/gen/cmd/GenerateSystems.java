package jo.ar.gen.cmd;

import java.io.File;
import java.io.IOException;

import org.w3c.dom.Document;

import jo.ar.gen.data.GalaxyBean;
import jo.ar.gen.data.GenerationParams;
import jo.ar.gen.data.StarBean;
import jo.ar.gen.logic.GenerateLogic;
import jo.ar.gen.logic.IOLogic;
import jo.util.utils.io.FileUtils;
import jo.util.utils.xml.XMLUtils;

public class GenerateSystems
{
    private String[] mArgs;
    private File     mConfigDir;
    private File     mGalaxyFile;
    private boolean  mReadOnly = true;
    private GenerationParams    mParams = new GenerationParams();
    
    public GenerateSystems(String[] args)
    {
        mArgs = args;
    }
    
    public void run()
    {
        parseArgs();
        setup();
        try
        {
            GalaxyBean galaxy = IOLogic.readPlanetDefs(mGalaxyFile);
            GenerateLogic.generateSystems(mParams, galaxy);
            if (!mReadOnly)
            {
                File backup = new File(mConfigDir, "planetDefs.xml.bak");
                FileUtils.copy(mGalaxyFile, backup);
                IOLogic.writePlanetDefs(galaxy, mGalaxyFile);
            }
            else
            {
                for (StarBean star : galaxy.getStars())
                    System.out.println("/advancedRocketry star generate "+star.getName()+" "+star.getTemp()
                    +" "+star.getX()+" "+star.getY()+"");
            }
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    private void setup()
    {
        if (mConfigDir == null)
        {
            System.err.println("Need to set configuration dir with --dir parameter");
            System.exit(1);
        }
        if (!mConfigDir.exists())
        {
            System.err.println("Path '"+mConfigDir+"' does not exist.");
            System.exit(1);
        }
        if (!mConfigDir.isDirectory())
        {
            System.err.println("Path '"+mConfigDir+"' is not a directory.");
            System.exit(1);
        }
        mGalaxyFile = new File(mConfigDir, "planetDefs.xml");
        if (!mGalaxyFile.exists())
        {
            System.err.println("Path '"+mGalaxyFile+"' does not exist.");
            System.exit(1);
        }
        if (!mGalaxyFile.isFile())
        {
            System.err.println("Path '"+mGalaxyFile+"' is not a file.");
            System.exit(1);
        }
    }
    
    private void parseArgs()
    {
        for (int i = 0; i < mArgs.length; i++)
            if ("--dir".equals(mArgs[i]))
                mConfigDir = new File(mArgs[++i]);
            else if ("--gridSize".equals(mArgs[i]))
                mParams.setGridSize(Integer.parseInt(mArgs[++i]));
            else if ("--gridRadius".equals(mArgs[i]))
                mParams.setGenerateRadius(Integer.parseInt(mArgs[++i]));
            else if ("--seed".equals(mArgs[i]))
                mParams.setSeed(Long.parseLong(mArgs[++i]));
            else if ("--mostlyHabitable".equals(mArgs[i]))
                mParams.setMostlyHabitable(true);
            else if ("--randomHabitable".equals(mArgs[i]))
                mParams.setMostlyHabitable(false);
            else if ("-ro".equals(mArgs[i]) || "--readOnly".equals(mArgs[i]))
                mReadOnly = true;
            else if ("-rw".equals(mArgs[i]) || "--readWrite".equals(mArgs[i]))
                mReadOnly = false;
    }
    
    public static void main(String[] argv)
    {
        GenerateSystems app = new GenerateSystems(argv);
        app.run();
    }
}
