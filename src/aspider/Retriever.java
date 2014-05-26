
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

/**
 * Retriever.java   1.0 27 Nov 2004
 * 
 * Copyright 2004 Ryan Smith
 * All Rights Reserved.
 * 
 */

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.LinkedList;
// io modules
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.PrintWriter; 
import java.io.FileWriter; 
import java.io.File;
// url modules
import java.net.URL;
import java.net.MalformedURLException;
// apache commons httpclient modules
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HostConfiguration;
//import org.apache.commons.httpclient.DefaultMethodRetryHandler;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.URIException;
// Tidy lib for cleaning html so its parsable
import org.w3c.tidy.Tidy;
// logging lib
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
// html parser lib
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.Node;
// codec
import org.apache.commons.codec.DecoderException;

public class Retriever implements Runnable {
	private HttpClient httpClient;
	private HostConfiguration config;
	private GetMethod method = new GetMethod();
	private Tidy tidy = new Tidy(); // obtain a new Tidy instance
	private int id = -1;
	private int maxTimeout = 20000;
	// holder of links that need to be checked for dl or browse
	private Queue links;
	// holder of links downloaded thus far
	private Queue dlLinks;
	// holder of emails colelcted thus far
	private Queue emails;
	private static Log LOG = LogFactory.getLog(RetrieverPool.class); 
	private Parser p = new Parser();
	private final int chunkSize = 2048;
	private NodeList nList;
	private Node[] nArray;
	private ByteArrayInputStream inputStream;
	private String dataBuffer;
	private byte[] responseBuffer;
	private InputStream responseStream;
	// should we only browse internal links?
	private boolean browseInternal = true;
	// this is the start URL of the retrieverPool
	private URL startURL = null;
	// this is the start URL of the retrieverPool
	private String label = null;
	// this is the refer URL
	private URL refererURL = null;
	// this is the location that all files will be downloaded to 
	private String downloadLocation = null;
	//private Integer currentIndex;
	private ArrayList browsableTypes = new ArrayList();
	private ArrayList downloadableTypes;
	// pattern match the emails
	private Pattern emailPattern = Pattern.compile("[A-Za-z0-9_.-]+@[A-Za-z0-9.-]+");
	
	/**
	 * Retriever Constructor
         * @param  none
  	 * @return none 
        */
	public Retriever(HttpClient httpClient, HostConfiguration config, int id, Queue links, Queue dlLinks, Queue emails, URL startURL, String label, String downloadLocation, ArrayList downloadTypes ) {
		this.httpClient = httpClient;
		this.config = config;
		this.id = id;
		this.links = links;
		this.dlLinks = dlLinks;
		this.emails = emails;
		this.startURL = startURL;
		this.label = label;
		this.downloadLocation = downloadLocation;
		this.downloadableTypes = downloadTypes;
		//this.browsableTypes.add("Content-Type: text/html");
		//this.browsableTypes.add("Content-Type: text/xhtml");
		//this.browsableTypes.add("Content-Type: text/xml");
		//this.browsabletypes.add("content-type: text/plain");
		this.browsableTypes.add("Content-Type: text");
	} // end of Retriever class constructor


	/**
	 * run() method  - Adds urls to be scanned to the queue
        * @param  none
  	 * @return none 
        */

	public void run() {
		// adding a new url to be spidered
		try {
			// once a thread is created , you can pass it new methods to run against.
			while ( ! Thread.currentThread().isInterrupted() ) {
				this.responseBuffer = new byte[0];
				this.dataBuffer = new String();
				LOG.info("id: " + id + " getFirstItem.....");
				this.refererURL = (URL)links.getFirstItem();
				this.method.setURI(new URI(this.refererURL.getPath(),false));
				// set the connection timeout
				this.method.getParams().setParameter("http.socket.timeout", new Integer(this.maxTimeout));
				// 
				this.method.setFollowRedirects(true);
				// handle cookie policy here....
				this.method.getParams().setCookiePolicy(CookiePolicy.DEFAULT);
				// Set up a default handler for retries
				//this.method.setMethodRetryHandler(new DefaultMethodRetryHandler());
				// Internally the parameter collections will be linked together
				// by performing the following operations: 
				//config.getParams().setDefaults(httpClient.getParams());
				//method.getParams().setDefaults(config.getParams());
				//if ( this.method.getURI().toString().length() > 0 ) {
				LOG.info(id + " - about to executeMethod on " + this.method.getURI());
				// execute the method
				httpClient.executeMethod(this.config, this.method);
				LOG.info(id + " - got executed");
				// Handle all http errors here.....
				if ( this.method.getStatusCode() == 404 ) {
					LOG.warn(this.method.getURI() + " is a " + this.method.getStatusCode() + " error - skipping.." );
				}	
				else {
					// check to see if the request is one we want to download....
					if ( isMethodDownloadable( ) ) {
						//this.responseBuffer = this.method.getResponseBody();
						this.responseStream = this.method.getResponseBodyAsStream();
						this.responseBuffer = this.getBytesToEndOfStream(this.responseStream);
						this.downloadFile();
						System.out.println(id + " - done downloading url.");
					} 
					// next check to see if the request is text or browsable,
					// so we can parse it and grab more urls to visit or download
					if ( isMethodBrowsable( ) ) {
						if ( this.responseBuffer.length == 0 ) {
							//this.responseBuffer = this.method.getResponseBody();
							this.responseStream = this.method.getResponseBodyAsStream();
							this.responseBuffer = this.getBytesToEndOfStream(this.responseStream);
						}
						this.inputStream = new ByteArrayInputStream( this.responseBuffer );
						LOG.info( "Browsing id: " + id + " status: " + this.method.getStatusText() );
						this.parseHTML( );
						LOG.info(id + " - done fetching and parsing.");
					} else {
						LOG.info("id: " + id + " method is not browsable, no bother parsing for links." + this.method.getURI());
					}
				} // end of error handler
				this.method.releaseConnection();
				LOG.info("id: " + id + " - connection released.");
			} // end of while
		
		} catch (InterruptedException e) {
			// no more items left in queue to get
			System.err.println("Interrupted Exception called...: " + e.getMessage());
			//e.printStackTrace();
		} catch (URIException e) {
			// URI cannot be parsed
			System.err.println("Gotten link cannt be parsed into a URI: " + e.getMessage());
			e.printStackTrace();
		} catch (HttpException e) {
			System.err.println("Fatal protocol violation: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Failed to IO - download file: " + e.getMessage());
			e.printStackTrace();
		} finally {
			// always release the connection after we're done 
			this.method.releaseConnection();
			LOG.info("id: " + id + " - connection released.");
		} // end of finally
	}// end of run method
	
	/**
	 * getBytesToEndOfStream() method - determines if the method is browsable
     * @param  an inputstream
  	 * @return an array of bytes
     */	
	public byte[] getBytesToEndOfStream(/*Buffered*/InputStream in) {
		int val;
		try {
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream(this.chunkSize);
			while ((val=in.read()) != -1)
				byteStream.write(val);
			return byteStream.toByteArray();
		}
		catch (Exception e) { 
			e.printStackTrace(); 
		}
		return null;
	}
	
	/**
	 * isMethodBrowsable() method - determines if the method is browsable
        * @param  none
  	 * @return true if a method is browsable, false otherwise
        */	
	public boolean isMethodBrowsable(  ) {
		// if the contentType string is a text/ascii type 
		// AND it meets the block/allow link rules, return true
		//return true;
		//else:
		//if ( url.contentType is ascii ) {
		//String contentType = this.method.getResponseHeader("Content-type").toExternalForm();
		String contentType = this.method.getResponseHeader("Content-type").toExternalForm().trim();
		LOG.info("id: " + id +  " isMethodBrowsable: content-type: " + contentType + " for method url: " + this.method.getPath() );
		//if ( this.browsableTypes.indexOf(contentType) != -1 ) {
		for (int i = 0; i < this.browsableTypes.size(); i++) {
			// if the method's content type matches an accesptable content type, browse it.
			if ( contentType.toLowerCase().indexOf( ((String)this.browsableTypes.get(i)).toLowerCase() ) != -1 ) { 
				if ( this.browseInternal ) {
					if ( this.isURLInternal( this.refererURL ) ) {
						return true;
					}
					else {
						LOG.info("url is not internal: " + this.refererURL );
						return false;
					}
				} // end of if
				else {
					return true;
				} // end of else
			} // end of if
		} //end of for
		return false;
	} // end of method
	

	/**
	 * isMethodDownloadable() method -  determines if the method is downloadable
        * @param  none
  	 * @return true if a method is downloadable, false otherwise
        */	

	public boolean isMethodDownloadable( ) {
		String contentType = this.method.getResponseHeader("Content-type").toExternalForm().trim();
		for ( int i = 0; i < this.downloadableTypes.size(); i++ ) {
			//if ( ((String)this.downloadableTypes.get(i)).toLowerCase().indexOf( contentType.toLowerCase() ) != -1 ) {
			if ( contentType.toLowerCase().indexOf(((String)this.downloadableTypes.get(i)).toLowerCase()) != -1 ) {
				LOG.info("Downloading matching content-type: " + contentType + " for method url: " + this.method.getPath() );
				return true;
				// need to add support for downloading pattern matching for a download file like java files or py files
				//if ( this.matchingLink != null ) {
				//	return true;
				//} else {
				//	return false;
				//}
			} // end if
		} // end of for
		LOG.info("NOT Downloading contentType:" + contentType + " this.downloadableTypes.size(): " +this.downloadableTypes.size() );
		return false;
	}
	
	/**
	 * downloadFile() method - downloads a file
        * @param  none
  	 * @return none
        */	
	public void downloadFile( )	{
		// dlLink will be a URL and dlLocation is a path 
		// where the file will be saved
		try {
			// Downloading this link only if we have a file name, we wont try to download
			// if there is no file name, even though 'foldername/' is a valid url,
			// we should find a way to get the actual index.html file instead of 
			// default file accosicated with the folder/.  so for now, we dont download those types.
			// If one day you want to support this, the problem would be whats the file name you want to save as?
			if ( this.method.getURI().getName().trim().length() > 0 ) {
				LOG.info("id: " + id +  " saving to location: " + this.downloadLocation + "/" + this.label + "/" + this.refererURL.getHost() + "/" + this.method.getURI().getPath() );
				File dlLoc =  new File( this.downloadLocation + "/" + this.label + "/" + this.refererURL.getHost() + "/" + this.method.getURI().getPath() );
				// create the directory to save the file
				if ( !dlLoc.exists() )	{ 
					dlLoc.mkdirs(); 
					LOG.info("id: " + id + " New Directory structure created: " + dlLoc.toString());
				} else {
					LOG.debug("id: " + id + " Directory already exists: " + dlLoc.toString());
				}
				// check to make sure the direcory exists.
				if ( dlLoc.exists() ) {				
					LOG.info("Downloading: " + this.method.getURI().toString() );
					// create a file object to be used for writing the byte array of data.
					File dlFile =  new File(dlLoc,this.method.getURI().getName());
					// create a file stream for writing to the file object
					FileOutputStream fw = new FileOutputStream(dlFile);
					// write the data 
					fw.write( this.responseBuffer );
					fw.close();
					LOG.debug("id: " + id + " saved file : " + dlLoc.toString());
					// add this url to the list of downloaded links since we downloaded it
					this.dlLinks.addItem( this.refererURL );
				} else {
					LOG.info("id: " + id + " Could not create directory structure : " + dlLoc.toString());
				}
			} // end of if
			else {
				LOG.info("id: " + id + " there was no file name found to save... skipping. " + this.method.getURI().getPath());
			}
		} catch (IOException e) {
			//
			LOG.error("IO Error, couldnt download the file: - " + e.getMessage() );
			e.printStackTrace();
		}
	} // end of download method
	
	/**
	 * isURLInternal() method - determines if a URL is an internal URL
        * @param  url
  	 * @return true if the url is an internal url, false otherwise
        */	
	public boolean isURLInternal(URL url)
	{
		//LOG.info("GIVEN      URL IS :  " + url.getHost());
		//LOG.info("HTTP CLIENT URL IS:  " + this.httpClient.getHostConfiguration().getHost());
		if (this.startURL.getHost().toLowerCase().equals(url.getHost().toLowerCase()) )
			return true;
		else
			return false;
	}

	
	/**
	 * parseHTML() method - parse the urls and links from an HTML file
        * @param  none
  	 * @return none
        */	
	// We're using the parser just to get the HREFs
	// We should also use it to e.g. respect <META NOFOLLOW>
	public void parseHTML( )
	{
		// This function should do 2 things:
		// > parse the databuffer for any links: <a, <area, <frame, <iframe	
		// > parse the dataBuffer for any email addresses
		this.dataBuffer = this.cleanHTMLData( );
		this.processNodeTags( parseHTMLData( this.dataBuffer, "A" ).toNodeArray() );
		this.processNodeTags( parseHTMLData( this.dataBuffer, "AREA" ).toNodeArray() );
		this.processNodeTags( parseHTMLData( this.dataBuffer, "IFRAME" ).toNodeArray() );
		this.processNodeTags( parseHTMLData( this.dataBuffer, "FRAME" ).toNodeArray() );
		this.parseEmails( this.dataBuffer );
	} // end of method
	
	
	/**
	 * parseEmails() method - parse the emails from a data buffer
        * @param  data buffer
  	 * @return none
        */	
	public void parseEmails( String dataBuffer ) {
		Matcher m = this.emailPattern.matcher( dataBuffer );
		while ( m.find() ) {
			this.emails.addItem( m.group() );
		}
	} // end of parseEmails
	
	
	/**
	 * cleanHTMLData() method - clean the HTML data
        * @param  none
  	 * @return clean html data string
        */	
	public String cleanHTMLData( ) {
		this.tidy.setXmlOut(false); // according to the example, this should return html
								// set to true to get xml
		this.tidy.setXHTML(true); // set desired config options using tidy setters 
		try {
			this.tidy.setErrout(new PrintWriter(new FileWriter("tidy-error."+id+".out"), true));
		} catch ( IOException e ) {
			//
			LOG.error("id: " + id + " IO Error, couldnt set tidy html error message: - " + e.getMessage() );
			e.printStackTrace();
		}
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		this.tidy.parse(this.inputStream, outStream);
		return new String(outStream.toByteArray());
		//System.out.println("cleaned up data buffer is:" + dataBuffer);
	} // end of method
	
	/**
	 * parseHTMLData() method - parse the html tags from a data buffer
         * @param  data buffer, tag name
  	 * @return  a NodeList
        */	
	public NodeList parseHTMLData(String dataBuffer, String tagName) {
		try {
			p.setInputHTML(dataBuffer);
			// Create a tag name to filter on, we want A tags for now
			// parse all tags that match the filter
			return p.extractAllNodesThatMatch( new TagNameFilter(tagName) );
		} catch ( ParserException e ) {
			LOG.error("id: " + id + " Parse HTML Error, couldnt parse the html from: - " + e.getMessage() );
			e.printStackTrace();
			return new NodeList();
		}
	} // end of method

	/**
	 * processNodeTags() method - process nodes
         * @param  an Array of Nodes
  	 * @return none
        */	
	public void processNodeTags(Node[] nArray) {
		for (int i = 0; i < nArray.length; i++) {
			//LOG.info("Node toString    is: " + nArray[i].toString()) ;
			//LOG.info("Node getText     is: " + nArray[i].getText()) ;
			//LOG.info("Node toPTString  is: " + nArray[i].toPlainTextString()) ;
			//LOG.info("Node toString    is: " + nArray[i].toString()) ;
			//LOG.info("Node toHtml      is: " + nArray[i].toHtml()) ;
			//Convert node to a url string, then convery url to abs url
			//String rawUrl = nArray[i].toString().split(" : ",1)[1].split(";",1)[0];
			String[] s = nArray[i].toString().split(";")[0].split(" ");
			//LOG.info("String list      is: " + s[s.length-1]) ;
			//LOG.info("String list      is: " + s[1]) ;
			//String rawUrl = s[s.length-1];	
			//LOG.info("Raw Url          is: " + rawUrl) ;
			try {
				// create an absolute path
				this.links.addItem( new URL(this.refererURL,s[s.length-1]) );
			} catch ( MalformedURLException e ) {
				LOG.error("id: " + id + " Malformed URL, couldnt create a new url to add to link list." + e.getMessage());
				e.printStackTrace();
			}
		} // end of for loop	
	} // end of method

} // end of Retriever class
