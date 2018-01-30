package jo.ar.gen.cmd;

import java.io.File;

public class GenerateSystems
{
    private String[] mArgs;
    private File     mConfigDir;
    private File     mGalaxyFile;
    
    public GenerateSystems(String[] args)
    {
        mArgs = args;
    }
    
    public void run()
    {
        parseArgs();
        setup();
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
    }
    
    public static void main(String[] argv)
    {
        GenerateSystems app = new GenerateSystems(argv);
        app.run();
    }
}
