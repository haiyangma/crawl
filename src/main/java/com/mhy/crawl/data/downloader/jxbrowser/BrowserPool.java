package com.mhy.crawl.data.downloader.jxbrowser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 */
class BrowserPool implements Closeable{
	private Logger logger = LoggerFactory.getLogger(getClass());

	private int capacity = 5;

	private BlockingQueue<BrowserWrapper> quene ;

	private Map<Integer,BrowserWrapper> inUse;

	private boolean isReady = false;


	public BrowserPool(int capacity) {
		this.capacity = capacity;
		quene = new LinkedBlockingQueue<BrowserWrapper>(capacity);
		for(int i = 0;i<capacity;i++){
			BrowserWrapper browserWrapper = new BrowserWrapper(this,i);
			quene.add(browserWrapper);
		}
		inUse = new HashMap<Integer, BrowserWrapper>();
		this.isReady = true;
	}

	public BrowserWrapper borrowOne(){
		synchronized (quene) {
			if(!quene.isEmpty()){
				BrowserWrapper one = quene.poll();
				inUse.put(one.getName(),one);
				return one;
            }
		}
		return null;
	}

	public void returnOne(BrowserWrapper browserWrapper){
		synchronized (inUse){
			inUse.remove(browserWrapper.getName());
			quene.add(browserWrapper);
		}
	}


	public void close() throws IOException {
		for(BrowserWrapper browserWrapper : quene){
			browserWrapper.dispose();
		}
		System.exit(0);
	}
}
