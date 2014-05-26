/**
  *
  * Copyright (c) 2004, Ryan Smith
  * All rights reserved.
  *
  * Redistribution and use in source and binary forms, with or without modification, 
  * are permitted provided that the following conditions are met:
  *
  *  * Redistributions of source code must retain the above copyright notice, 
  *    this list of conditions and the following disclaimer.
  *  * Redistributions in binary form must reproduce the above copyright notice, 
  *    this list of conditions and the following disclaimer in the documentation 
  *    and/or other materials provided with the distribution.
  *  * Neither the name of the AB Software Foundation nor the names of its contributors 
  *    may be used to endorse or promote products derived from this software without 
  *    specific prior written permission.
  *
  *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY 
  *  EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF 
  *  MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL 
  *  THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
  *  SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, 
  *  PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
  *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, 
  *  STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT 
  *  OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
  */

/**
 * SpiderCommand.java   1.0 27 Nov 2004
 * 
 * Copyright 2004 Ryan Smith
 * All Rights Reserved.
 * 
 */

package aspider;

import jargs.gnu.CmdLineParser;
import java.util.ArrayList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SpiderCommand  {
	private static final Log LOG = LogFactory.getLog(SpiderCommand.class);
	private static long startTime;
	private static long endTime;
        private static String start_url = null;
        private static String search_label = "";
        private static String config;
        private static String downloadtypes;
        private static Integer max_threads;
        private static Integer max_links;
        private static String download_location;
        private static Boolean debug;
	// set this list to be the availabe download mime types
	private static ArrayList downloadTypes = new ArrayList();

	 /**  SpiderCommandParser class */    
	 private static class SpiderCommandParser extends CmdLineParser {

        public static final Option DEBUG = new
            CmdLineParser.Option.BooleanOption('D',"debug");

        //public static final Option CONFIG = new
        //    CmdLineParser.Option.StringOption('c',"config");
        
		public static final Option LABEL = new
            CmdLineParser.Option.StringOption('l',"label");

        public static final Option SITE = new
            CmdLineParser.Option.StringOption('s',"site");

        //public static final Option NUMBER = new
        //    CmdLineParser.Option.DoubleOption('f',"fraction");
		
        public static final Option LINKS = new
            CmdLineParser.Option.IntegerOption('m',"maxlinks");
        
		public static final Option THREADS = new
            CmdLineParser.Option.IntegerOption('t',"maxthreads");
        
		public static final Option LOCATION = new
            CmdLineParser.Option.StringOption('d',"downloadlocation");
		
		public static final Option TYPES = new
            CmdLineParser.Option.StringOption('T',"downloadtypes");

	/** 
	* SpiderCommandParser Default Constructor
	* Creates a new instance of SpiderCommandParser
	* @param  none
	*/ 
	public SpiderCommandParser() {
            super();
            addOption(DEBUG);
            addOption(LABEL);
            //addOption(CONFIG);
            addOption(SITE);
            addOption(LINKS);
            addOption(THREADS);
            addOption(LOCATION);
            addOption(TYPES);
        }
    }


	 /** Displays welcome message 	 
         * @param  none
	  * @return none 
	  */
	private static void welcome() {
        System.out.println("\t*** Welcome to Base HTTP Spider v1.0a ***");
        System.out.println("This spider will traverse through a user specified website and download ");
        System.out.println("any matching given mime types.  Default behavior is to collect any email matched ");
        System.out.println("along the way and save all visited links, downloaded links and emails to a results file.");
	}

	 /** Displays the usage 
	  * @param  none
	  * @return none 
	  */
    private static void printUsage() {
		welcome();
        System.out.println("usage: prog  ");
		System.out.println("\t{-s,--site} 'http://url.to.spider/path/' - manditory field , this is the url you wish to spider. ");
		System.out.println("\t[{-l,--label} label_name ] - specify a label (folder name) to save all the results to.");
        System.out.println("\t[{-m,--maxlinks} num_links ]  - an integer representing the number of links you wish to examine for parse or download. ");
		System.out.println("\t[{-t,--maxthreads} num_threads ] - an integer representing the number of threads you wish to spawn. ");
		System.out.println("\t[{-d,--downloadlocation} ./dir/path/ ] - specify a path to save all the results to, default is '.' ");
		System.out.println("\t[{-T,--downloadtypes} 'audio,text,xml,video,pdf' - a list of mime types seperated by ',' ] ");
		//System.out.println("{-c,--config} ./conf/file.conf ");
    }
    
    /**
    * main() runs the SpiderCommand class
    * @param  An Arrays of Strings. 
    * @return none.   
    */
    public static void main( String[] args ) {
        SpiderCommandParser mySpider = new SpiderCommandParser();
        try {
            mySpider.parse(args);
        }
        catch ( CmdLineParser.UnknownOptionException e ) {
            System.err.println(e.getMessage());
            printUsage();
            System.exit(2);
        }
        catch ( CmdLineParser.IllegalOptionValueException e ) {
            System.err.println(e.getMessage());
            printUsage();
            System.exit(3);
        }

        CmdLineParser.Option[] allOptions =
            new CmdLineParser.Option[] { SpiderCommandParser.DEBUG,
                                         SpiderCommandParser.LABEL,
                                         SpiderCommandParser.SITE,
                                         //SpiderCommandParser.CONFIG,
                                         SpiderCommandParser.LOCATION,
                                         SpiderCommandParser.LINKS,
                                         SpiderCommandParser.THREADS,
                                         SpiderCommandParser.TYPES,
											};
		// print out the options
        for ( int j = 0; j<allOptions.length; ++j ) {
            LOG.debug(allOptions[j].longForm() + ": " + mySpider.getOptionValue(allOptions[j]));
			if ( allOptions[j].longForm().equals("site") ) {
				start_url = (String)mySpider.getOptionValue(allOptions[j]);
			} else if ( allOptions[j].longForm().equals("label") ) {
				search_label = (String)mySpider.getOptionValue(allOptions[j]);
			} else if ( allOptions[j].longForm().equals("debug") ) {
				debug = (Boolean)mySpider.getOptionValue(allOptions[j]);
			} else if ( allOptions[j].longForm().equals("downloadlocation") ) {
				download_location = (String)mySpider.getOptionValue(allOptions[j]);
			} else if ( allOptions[j].longForm().equals("maxlinks") ) {
				max_links = (Integer)mySpider.getOptionValue(allOptions[j]);
			} else if ( allOptions[j].longForm().equals("maxthreads") ) {
				max_threads = (Integer)mySpider.getOptionValue(allOptions[j]);
			} else if ( allOptions[j].longForm().equals("downloadtypes") ) {
				downloadtypes = (String)mySpider.getOptionValue(allOptions[j]);
				if ( downloadtypes != null ) {
					String[] downloadTypeList = downloadtypes.split(",");
					for (int i = 0; i < downloadTypeList.length; i++) {
						if (downloadTypeList[i].trim().length() > 0) {
							downloadTypes.add(downloadTypeList[i].trim());
						} // end of if
					}
					if ( downloadTypeList.length == 0 ) {
						downloadTypes.add(downloadtypes.trim());
					}
				}
			//} else if ( allOptions[j].longForm().equals("config") ) {
			//	config = (String)mySpider.getOptionValue(allOptions[j]);
			}
        }
		
		// print out the command line args
        String[] otherArgs = mySpider.getRemainingArgs();
        LOG.debug("remaining args: ");
        for ( int i = 0; i<otherArgs.length; ++i ) {
            LOG.debug(otherArgs[i]);
        }
		if ( (start_url == null) ) {
			LOG.info("You need to specify at least a site to run the spider.");
			printUsage();
			System.exit(4);
		} else {
			// start timing the spider
			startTime = System.currentTimeMillis();                        
			Spider runningSpider = new Spider(start_url,search_label,max_threads,max_links,download_location,downloadTypes);
			runningSpider.run();
            endTime = System.currentTimeMillis();
            LOG.info("Finished.  Elapsed Time: " +  ((endTime - startTime) / 1000.0 ) + " seconds");
            LOG.info("******* RESULTS *******" );	
		}
        System.exit(0);
    }
	
}
