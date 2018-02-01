package jo.ar.gen.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jo.util.utils.obj.StringUtils;

public class NameLogic
{
    public static String generatePlaceName(Random r)
    {
        return StringUtils.initialCaptial(getName(r));
    }

    public static String generatePersonalName(Random r)
    {
        return StringUtils.initialCaptial(getName(r)) + " " + StringUtils.initialCaptial(getName(r));
    }

    public static List<String> generateWords(Random r, int num)
    {
        List<String> ret = new ArrayList<String>();
        while (num-- > 0)
        {
            ret.add(getName(r));
        }
        return ret;
    }

    // languages
    static int[] lang_imWordLength = { 19,11,5,1,0,0 };
    static int[] lang_imInitialSyllableTable = { 5,11,8,12 };
    static int[] lang_imFinalSyllableTable = { 17,3,8,8 };
    static int[] lang_imFirstConsonant = { 10,2,13,3,9,10,2,8,2,14,2,4,13,12,6,7,2,2,7,10,7,3,13,21,2,9,13,3,7 };
    static int[] lang_imLastConsonant = { 3,3,22,5,2,3,2,3,2,5,2,4,41,9,9,2,3,3,36,2,2,16,2,4,21,2,3,2,3 };
    static int[] lang_imVowel = { 49,73,40,38,7,9 };
    static String[] lang_imFirstConsonantText = { "b","br","c","ch","d","f","fr","g","gr","h","j","k","l","m","n","p","pl","pr","r","s","sh","st","t","th","tr","v","w","wh","y" };
    static String[] lang_imLastConsonantText = { "c","ck","d","f","ft","gh","ht","l","ld","ll","ly","m","n","nd","ng","ns","nt","p","r","rd","rs","s","ss","st","t","th","w","x","y" };
    static String[] lang_imVowelText = { "a","e","i","o","ou","u" };

    private static void    appendSyllable(StringBuffer buf, Random r, int arr[], String sylbuf[])
    {
        int v, t;

        v = r.nextInt(216);
        t = syslookup(arr, v);
        if (!sylbuf[t].equals(""))
        {
            v++;
        }
        buf.append(sylbuf[t]);
    }
    public static String   getName(Random r)
    {
        StringBuffer    nbuf;
        int leng, type;

        if (r == null)
            r = new Random();
        leng = syslookup(lang_imWordLength, r.nextInt(36)) + 1;//assume names one extra syllable
        nbuf = new StringBuffer();
        type = 0;
        do {
            type = syslookup(
                (type != 0) ?
                        lang_imFinalSyllableTable :
                            lang_imInitialSyllableTable,
                r.nextInt(36));
            if ((type%2) != 0)
            {
                appendSyllable(nbuf, r, lang_imFirstConsonant, lang_imFirstConsonantText);
            }
            appendSyllable(nbuf, r, lang_imVowel, lang_imVowelText);
            if (type>1)
            {
                appendSyllable(nbuf, r, lang_imLastConsonant, lang_imLastConsonantText);
            }
            type = type/2;
        } while (leng-- > 0);
        String s = nbuf.toString();
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
    private static int     syslookup(int arr[], int tot)
    {
        int i;

        //System.out.println("Looking up "+tot+" out of "+totUp(arr));
        i = 0;
        do {
            tot -= arr[i++];
        } while (tot >= 0);
        return(i - 1);
    }

}
