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


/*
 * Queue.java
 *
 * Created on November 27, 2004, 1:46 PM
 */

package aspider;

import java.net.URL;
import java.io.*;
import java.util.LinkedList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Queue extends LinkedList {
	// the current size of the list
	private int size;
	// max numbers of items that can be added to the list
	private int max;
	// the current Item returned by getFirstItem()
	private int currentIndex = 0;
	//
	private boolean allowDuplicates = false;
	// keeps track of the number of threads waiting for the items to fill up in the queue
	private int waitCount = 0;

	/** Log object for this class. */
	private static final Log  LOG = LogFactory.getLog(Queue.class);
	
	 
	/**
	* initialize the queue with max items allowed
       * Queue() Default Constructor
	* @param  max number of urls allowed.  
       */
	public Queue(int maxsize) {		
		this.size = 0;
		this.max = maxsize;	
	}	

	/**
       * addItem() Adds a URL object to the list, 
	* and also adds it to the log. 
       * @param  It passes in a URL object.  
	* @return none 
	 */
	public synchronized void addItem(Object item) {
		if ( ! isFull() ) {
			if ( ( ! contains(item) && ! allowDuplicates ) || ( allowDuplicates ) ) {
				// do not add to the front of the list......add to the end of the list.
				add(item);
				LOG.info("*Adding item:" + item);
				size++;
				notifyAll(); // notify any threads waiting for the queue to fill up
			} else {
				LOG.debug("Item :" + item + ", is a duplicate.");
			} // end of else
		} // end of if
		else {
			LOG.debug("The List is Full, did not add item: " + item.toString() );
		} // end of else
	}
	
	/**
       * getFirstItem() This method returns the first in the queue
	* or throws an InterruptedException . 
       * @param  URL object. 
       * @return none
       * @throws InterruptedException 
       */
	public synchronized Object getFirstItem() throws InterruptedException 
		//QueueEmptyException
	{
		while ( isEmpty() ) {
			LOG.info("Now waiting for queue to fill up...");
			waitCount++;
			wait();
			waitCount--;
			LOG.info("Done waiting for queue to fill up...");
		}
		return get(currentIndex++);	
	}

	/**
       * getWaitCount() gets the wait count
       * @param  none	
       * @return wait count 
       */
	public synchronized int getWaitCount() {
		return waitCount;
	}
	
	/**
       * print() Prints out the items in the list.
       * @param  none
       * @return none
       */
	public void print() 
	{
		for ( int i = 0; i < size; i++ ) 
		{
			LOG.info( "Item is: " + get(i).toString() );
		}	
		LOG.info("size of the Queue is: " + size );
	}	

	/**
       * resetToFirstItem() This method reinitializes
	* the current index value to 0. 
       * @param  none 
       * @return none 
       */
	public synchronized void resetToFirstItem()
	{
		currentIndex = 0;
	}
	
	/**
       * isFull() This method is used to determine if the queue is full 
	* the current index value to 0. 
       * @param  none 
       * @return true if queue is full, false otherwise 
       */	
	public synchronized boolean isFull()
	{	
		// if max is set to zero , then we are never full...
		if ( this.max == 0 ) {
			return false;
		}
		return (this.size == this.max);
	}
	
	
	/**
       * isEmpty() This method is used to determines if the queue is empty 
       * @param  none 
       * @return true if the queue is empty, false otherwise 
       */	
	public synchronized boolean isEmpty()
	{	
		if (this.currentIndex > this.size) {
			LOG.error("This current index:" + this.currentIndex + " exceeds the number of list elements : " + this.size +" logical error - criitical." );
		}
		return (this.currentIndex >= this.size);
	}

	/**
       * test() This method is the unit test 
       * @param  none
       * @return none. 
       */		

	public static void test()
	{
		Queue q = new Queue(3);		
		q.addItem("Rodrigo");	
		q.addItem("Rodrigo");
		q.addItem("Ryan");
		q.addItem("YoDawg");
		q.addItem("Tom");
		q.print();
	}
	
	/**
       * main() This method is the main method
       * @param  Commmandline Options. 
       * @return none. 
       */		
	public static void main( String[] args)
	{
		test();		
	}	


} // end of the Queue class


