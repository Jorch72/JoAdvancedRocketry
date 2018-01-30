package jo.ar.gen.cmd;

import java.io.File;

public class GenerateSystems
{
    private String[] mArgs;
    private File     mConfigDir;
    
    public GenerateSystems(String[] args)
    {
        mArgs = args;
    }
    
    public void run()
    {
        parseArgs();
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
