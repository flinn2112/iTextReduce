/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itextreduce;
import com.hcc_medical.mavenproject1.* ;
import com.itextpdf.licensekey.LicenseKey;
import java.io.BufferedReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author frank
 */
public class ITextReduce {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String strIn  = null ;
        String strOut = null ;
         String usage = "java -jar ITextReduce"
                 + " -in FULLPATH_TO_BIG_PDF -out FULLPATH_TO_REDUCED_PDF";
         
        if( args.length < 2 ){
          System.out.println(usage);
        } 
         
        for(int i=0;i<args.length;i++) {
                if ("-in".equals(args[i])) {
                  strIn = args[i+1];
                  i++;
                } else if ("-out".equals(args[i])) {
                  strOut = args[i+1];
                  i++;
                } 
        }
    
   
      if( null == strOut || strOut.isEmpty()){
          strOut = strIn +  ".min.pdf" ; 
      }
      System.out.println("in: " + strIn + " out: " + strOut);
        /*
        String strSrc = System.getProperty("user.dir") 
                 + File.separator + "in.pdf" ;
        String strDst = System.getProperty("user.dir") 
                 + File.separator + "out.pdf" ;
        */
        //Load License for iText
        String strLFile = System.getProperty("user.dir") 
                 + File.separator + "itextkey1519992068869_0.xml" ;
         System.out.println("reducePDF");
         System.out.println(strLFile) ;
         //LicenseKey.loadLicenseFile(strLFile) ;
         //System.out.println(LicenseKey.getLicenseeInfo()) ;
        //int i =  com.hcc_medical.mavenproject1.mainStuff.test(f);

      //      com.hcc_medical.mavenproject1.mainStuff.reducePdf(strIn, strOut);
       ITextReduce.processFile("X:\\SharedDownloads\\Projekte\\hcc\\hcc_search\\dist\\logs\\reviewResultPart.txt") ; 
        System.out.println("END");
    }
    
    
    
    
    private static void processLine(String strLine, String strTargetDir){
        String[] rParts = null ;
        String strOut = null ;
        rParts = strLine.split(";") ;
        System.out.println(strLine);
        if( null != rParts[0] && !rParts[0].isEmpty()){
            strOut = rParts[0] + ".min.pdf"  ;
            com.hcc_medical.mavenproject1.mainStuff.reducePdf(rParts[0], strOut);
        }
        //com.hcc_medical.mavenproject1.mainStuff.reducePdf(strIn, strOut);
    }
    
    private static void processFile(String strPath){
    FileInputStream fis = null ;
      BufferedReader br = null ;
      StringBuilder sb = new StringBuilder() ;
      String strTmp = new String() ;
      
      
      try {
          fis = new FileInputStream(new File(strPath));
          br = new BufferedReader(new InputStreamReader(fis, "UTF-8")) ;
          System.out.println("processCfgFile - opened: " + strPath );
        } catch (Exception ex) {
          // at least on windows, some temporary files raise this exception with an "access denied" message
          // checking if the file can be read doesn't help    
            System.err.println("processCfgFile - error: " + ex.toString());
            return ;
        }

        try{             
             while ((strTmp = br.readLine()) != null) {
                  if(strTmp.startsWith("#")){ //a comment line
                       
                    }else{
                      ITextReduce.processLine(strTmp, null);
                  }
                   
             }
             br.close();
             fis.close();
        } catch (java.io.IOException ex){
           System.err.println(ex.toString());
        }finally{
           
           
        }     
     }
    
    
}
