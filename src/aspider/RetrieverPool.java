
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


package aspider;
import java.net.*;
import java.io.*;
import java.io.IOException;
import java.util.*;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpVersion;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * RetrieverPool .java   1.0 27 Nov 2004
 * An example that performs GETs from multiple threads.
 * 
 * Copyright 2004 Ryan Smith
 * All Rights Reserved.
 * 
 */


import java.util.LinkedList;

public class RetrieverPool {
	private HttpClient httpClient;
	private HostConfiguration hostConfig;
	private Thread[] threadPool;
	private Queue links;
	private Queue dlLinks;
	private Queue emails;
	//private LinkedList links;
	
	private ArrayList downloadTypes;
	//private Integer current_index = -1;
	
	private URL startURL = null;
	private String label = null;
	private String downloadLocation = null;
	private int maxUrls;
	private int maxDLLinks;
	// create log facility for logging
	private static Log LOG = LogFactory.getLog(RetrieverPool.class);
    /**
     * Constructor for RetrieverPool
     */
    public RetrieverPool(Queue links, Queue dlLinks, Queue emails, URL startURL, String label, int maxThreads, int maxUrls, String downloadLocation, ArrayList downloadTypes ) {
		// assign the base domain
		this.startURL = startURL;
		this.label = label;
		this.maxUrls = maxUrls;
		this.links = links;
		this.dlLinks = dlLinks;
		this.emails = emails;
		this.downloadLocation = downloadLocation;
		this.downloadTypes = downloadTypes;
		// create the hostConfig for the http client
		this.hostConfig = new HostConfiguration();
		this.hostConfig.setHost(this.startURL.getHost(), this.startURL.getPort(), this.startURL.getProtocol());
		this.hostConfig.getParams().setParameter("http.protocol.version", HttpVersion.HTTP_1_0);
		// create the http client
        HttpClient httpClient = new HttpClient(new MultiThreadedHttpConnectionManager());
		httpClient.getParams().setParameter("http.protocol.version", HttpVersion.HTTP_1_1);
		httpClient.getParams().setParameter("http.socket.timeout", new Integer(20000));
		httpClient.getParams().setParameter("http.connection.timeout", new Integer(10000));
		httpClient.getParams().setParameter("http.connection-msnsger.timeout", new Integer(10000));
		httpClient.getParams().setParameter("http.protocol.content-charset", "UTF-8");
		httpClient.getParams().setParameter("http.protocol.max-redirects", new Integer(10));
		httpClient.getParams().setParameter("http.useragent", new String("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.6) Gecko/20040206 Firefox/0.8"));
		//httpClient.setTimeout(new Integer(20000));
		// create the retriever thread list.
        threadPool = new Thread[maxThreads];
        for (int i = 0; i < threadPool.length; i++) {
			threadPool[i] = new Thread( new Retriever(httpClient, this.hostConfig, i + 1, links, dlLinks, emails, startURL, label, downloadLocation, downloadTypes) );
        }
    }
	
	/**
	 * Start() method - start the retriever pool thread
         * @param  none
  	 * @return none 
        */
	public void start() {
		for (int j = 0; j < threadPool.length; j++) {
			// if i have a thread that isnt alive (running) and 
			// i have more links to process.....
			if ( ! threadPool[j].isAlive() ) {
				LOG.info(j + " starting a new thread since we have links to visit");
				threadPool[j].start();
			} // end of if thread is not runniing and more links left.....
		} // end of for loop
		boolean finished = false;
		while ( ! finished ) {
			//  check the status of each thread.....
			// If all threads !isAlive() and links.isEmpty then exit
			// Or if maxLinks is reached, then exit
			if ( threadPool.length == links.getWaitCount() ) {
				finished = true;
			} // end of if
			for (int j = 0; j < threadPool.length; j++) {
				if ( finished ) {
					LOG.info("Were finished so Thread is interrupted and finishing......");
					threadPool[j].interrupt();
				}
			} // end of for loop
		} // end of while 
		LOG.info("Now we are exiting the RetrieverPool start method loop...");
	} // end of start method
	
	/**
	 * shutdown() method - shutdown the retriever pool thread
         * @param  none
  	 * @return none 
        */
	public void shutdown()
	{
		// signal each thread that its time to finish when the current job is done.
		for (int j = 0; j < threadPool.length; j++) {
			try {
				threadPool[j].join();
			} catch( InterruptedException e ) {
				threadPool[j].interrupt();
			}
		}
		//httpClient.shutdown();
	} // end of shutdown method

} // end of RetrieverPool class

