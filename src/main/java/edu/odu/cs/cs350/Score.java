package edu.odu.cs.cs350;

import java.io.File;
import java.util.StringTokenizer;
import java.util.Vector;

import com.googlecode.concurrenttrees.radix.node.concrete.DefaultCharArrayNodeFactory;
import com.googlecode.concurrenttrees.suffix.ConcurrentSuffixTree;
import com.googlecode.concurrenttrees.suffix.SuffixTree;

/**
 *
 * @author holloway.hannah
 */
public class Score {
private Double rawScore;
private Double mean;
private Double zscore;
private Double standardDev;
public Score()
{
    rawScore=0.0;
    mean=0.0;
    zscore=0.0;
    standardDev=0.0;
}

public void score(Student s1,Student s2)
{
       SuffixTree<Integer> tree = new ConcurrentSuffixTree<Integer>(new DefaultCharArrayNodeFactory());
       System.out.println(s1.getName()+" "+s2.getName());
       double d=0.0;
       JavaParser p=new JavaParser();
       Vector<File> v1=s1.getFileList();
       Vector<File> v2=s2.getFileList();
       StringBuffer sb1=new StringBuffer();
       StringBuffer sb2=new StringBuffer();
       
       for(int k=0;k<v1.size();k++)
       {
         File sf1=v1.get(k);
         //String str1=p.test(sf1.getSource());
         //System.out.println(sf1.getSource());
         sb1.append(p.parse(sf1));
       }
        for(int l=0;l<v2.size();l++)
        {
         File sf2=v2.get(l);
         sb2.append(p.parse(sf2)); 
        }
        String str1=sb1.toString();
        //System.out.println(str1);
        if(str1.endsWith(":"))
        {
         str1=str1.substring(0,str1.length()-1);
        }
        String str2=sb2.toString();
        //System.out.println(str2);        
        if(str2.endsWith(":"))
        {
         str2=str2.substring(0,str2.length()-1);
        }  
        
        StringTokenizer st=new StringTokenizer(str1,":");
        StringBuffer p1=new StringBuffer();
        StringBuffer p2=new StringBuffer();
        int count=1;
        while(st.hasMoreElements())
        {
            String val=st.nextToken(":");
            String code=st.nextToken(":");
            tree.put(val,val.length());
            p1.append(val);
        }
        st=new StringTokenizer(str2,":");
        while(st.hasMoreElements())
        {
            String val=st.nextToken(":");
            String code=st.nextToken(":");
            p2.append(val);
            if(tree.getValueForExactKey(val)!=null)
            {
            d=d+tree.getValueForExactKey(val);
            }
            //System.out.println();
            //System.out.println("Value for "+val+" (exact match): " + tree.getValueForExactKey(val));
            //if(tmap.containsKey(val))
            //{
            // d=d+Double.parseDouble(code);
            //}
        }        

rawScore=d;
}

    /**
     * @return the rawScore
     */
    public Double getRawScore() {
        return rawScore;
    }

    /**
     * @param rawScore the rawScore to set
     */
    public void setRawScore(Double rawScore) {
        this.rawScore = rawScore;
    }

    /**
     * @return the mean
     */
    public Double getMean() {
        return mean;
    }

    /**
     * @param avg the mean to set
     */
    public void setMean(Double mean) {
        this.mean = mean;
    }

    /**
     * @return the zscore
     */
    public Double getZscore() {
        return zscore;
    }

    /**
     * @param zscore the zscore to set
     */
    public void setZscore(Double zscore) {
        this.zscore = zscore;
    }

    /**
     * @return the standardDev
     */
    public Double getStandardDev() {
        return standardDev;
    }

    /**
     * @param standardDev the standardDev to set
     */
    public void setStandardDev(Double standardDev) {
        this.standardDev = standardDev;
    }
}