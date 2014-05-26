
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
 * Spider.java   1.0 27 Nov 2004
 * 
 * Copyright 2004 Ryan Smith
 * All Rights Reserved.
 * 
 */

package aspider;

import java.util.*;
import java.io.*;
import java.net.URL;
import java.net.MalformedURLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

class Spider {
	private RetrieverPool retrieverPool = null;
	private Queue links;
	private Queue dlLinks;
	private Queue emails;
	private String startUrl = null;
	private URL startURL;
	private int maxThreads = -1;
	private int maxLinks = -1;
	private String spiderLabel;
	private String downloadLink = null;
	private String downloadLocation = null;
	private ArrayList downloadTypes;
	/** Log object for this class. */
    private static final Log  LOG = LogFactory.getLog(Spider.class);

	/**
       * Spider() Constructor
       * @param  start url
       */	
	public Spider( String startUrl )
	{
		//super();
		this.startUrl = startUrl;
		LOG.info("startUrl is:" + this.startUrl);
		defaults();
		setQueueSize();
		debugInfo();
	}
	/**
       * Spider() Constructor
       * @param  start url, label, max # if threads, max # of links, download location, download types
       */	
	public Spider(String startUrl, String spiderLabel, Integer maxThreads, Integer maxLinks, String downloadLocation, ArrayList downloadTypes ) {
		this(startUrl);
		if ( maxThreads != null && maxThreads.intValue() > 0 )
			this.maxThreads = maxThreads.intValue();
		if ( maxLinks != null && maxLinks.intValue() > 0 )
			this.maxLinks = maxLinks.intValue();
		if ( spiderLabel != null ) 
			this.spiderLabel = spiderLabel;
		if ( downloadLocation != null ) 
			this.downloadLocation = downloadLocation;
		this.downloadTypes = downloadTypes;
		setQueueSize();
		debugInfo();
	}

	/**
       * setQueueSize() Sets the size of the queue
       * @param  none
	* @return none
       */	
	private void setQueueSize() {
		// the number of browsable links should be fixed 
		this.links = new Queue(this.maxLinks);
		// zero queue size means unlimited 
		this.dlLinks = new Queue(0);
		// zero queue size means no limit
		this.emails = new Queue(0);
	}
	
	/**
       * debugInfo() Displays debugging information
       * @param  none
	* @return none
       */	
	private void debugInfo() {
		if (LOG.isDebugEnabled()) {
			try {
				LOG.debug("Java version: " + System.getProperty("java.version"));
				LOG.debug("Java vendor: " + System.getProperty("java.vendor"));
				LOG.debug("Java class path: " + System.getProperty("java.class.path"));
				LOG.debug("Operating system name: " + System.getProperty("os.name"));
				LOG.debug("Operating system architecture: " + System.getProperty("os.arch"));
				LOG.debug("Operating system version: " + System.getProperty("os.version"));
				/*
				Provider[] providers = Security.getProviders();
				for (int i = 0; i < providers.length; i++) {
					Provider provider = providers[i];
					LOG.debug(provider.getName() + " " + provider.getVersion()
					   + ": " + provider.getInfo());   
				}
				*/
			} catch(SecurityException ignore) {
			}
		}
	}
	
	
	/**
       * defaults() Load the Spider's default values
       * @param  none
	* @return none
       */	
	private void defaults() {
		this.maxThreads = 3;
		this.maxLinks = 200;
		this.downloadLocation = ".";
		this.spiderLabel = "";
		this.downloadTypes = new ArrayList();
	}
	
	/**
       * initStartUrl() Inititates the startign url of the Spider
       * @param  none
	* @return true if url is defined, false otherwise
       */	
	public boolean initStartUrl()
	{
		if ( this.startUrl != null ) {
			try {
				this.startURL = new URL(this.startUrl);
				//LOG.info("" + startURL.getAuthority());
				//LOG.info(startURL.getContent());
				// how do you print int types???
				//LOG.info(startURL.getDefaultPort());
				LOG.info("getFile     : " + this.startURL.getFile());
				LOG.info("getHost     : " + this.startURL.getHost());
				LOG.info("getPath     : " + this.startURL.getPath());
				// how do you print int types
				//LOG.info(startURL.getPort());
				LOG.info("getProtocol : " + this.startURL.getProtocol());
				LOG.info("getQuery    : " + this.startURL.getQuery());
				LOG.info("getRef      : " + this.startURL.getRef());
				LOG.info("getUserInfo : " + this.startURL.getUserInfo());
				// initialise the thread queue with the start url 
				this.links.addItem(this.startURL);
				//this.addItem(this.startURL);
				return true;
			} catch (MalformedURLException e) {
				LOG.error("url: "+this.startUrl+" is a malformed url." + e.getMessage());
				e.printStackTrace();
				return false;
			}
		}
		else {
			LOG.warn("No startURL has been defined, ")	;
			return false;
		}
	}

	/**
        * run() The Spider
        * @param  none
	* @return none
       */	
	public void run()
	{
		if ( initStartUrl() ) {
			this.retrieverPool = new RetrieverPool(this.links,this.dlLinks,this.emails,this.startURL,this.spiderLabel,this.maxThreads,this.maxLinks,this.downloadLocation,this.downloadTypes);
			// start the retrievers
			this.retrieverPool.start();
			// when the above method finishes ... ^^^^^ ... then we are done spidering...
			// shutdown...
			LOG.info("Shutdown....");
			this.retrieverPool.shutdown();
			// and return all the results found to the user
			//this.URLs = this.URLdict.keys();
			//this.Emails = this.EmailDict.keys();
			this.logResults();
		} // end of if
		LOG.info("---------------- END -------------------");
	} // end of run method
	
	
	/**
        * logResults() Log the results produced by running the Spider
        * @param  none
	* @return none
       */	
	public void logResults()
	{
		LOG.info("Printing out RESULTS....");
		LOG.info("--------- VISITED LINKS ----------------");
		this.links.print();
		LOG.info("--------- DOWNLOADED FILES -------------");
		this.dlLinks.print();
		LOG.info("--------- EMAILS COLLECTED -------------");
		this.emails.print();
		//this.print();
	}
	
}
