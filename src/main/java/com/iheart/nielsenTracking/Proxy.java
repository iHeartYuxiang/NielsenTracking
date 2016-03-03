package com.iheart.nielsenTracking;



import com.iheart.selenium.web.*;
import com.iheart.proxyLight.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Proxy {
	
	protected int localProxyPort = 5368;
	  protected ProxyLight proxy;

	  // LRU response table. Note: this is not thread-safe.
	  // Use ConcurrentLinkedHashMap instead: http://code.google.com/p/concurrentlinkedhashmap/
	  private LinkedHashMap<String, Response> responseTable = new LinkedHashMap<String, Response>() {
	    protected boolean removeEldestEntry(Map.Entry eldest) {
	      return size() > 100;
	    }
	  };
	  
	  private LinkedHashMap<String, Request> requestTable = new LinkedHashMap<String, Request>() {
		    protected boolean removeEldestEntry(Map.Entry eldest) {
		      return size() > 100;
		    }
		  };

	  public Response fetch() {
	    if (proxy == null) {
	      initProxy();
	    }
	   
	    Response proxyResponse = responseTable.remove(Page.getDriver().getCurrentUrl());

	    return proxyResponse;
	  }

	  public Request fetchRequest() {
		    if (proxy == null) {
		      initProxy();
		    }
		     
		    Request proxyRequest = requestTable.remove(Page.getDriver().getCurrentUrl());

		    return proxyRequest;
		  }
	  
	  public void initProxy() {
	    proxy = new ProxyLight();

	    this.proxy.setPort(localProxyPort);
	    
	    

	    // this response filter adds the intercepted response to the cache
	    this.proxy.getResponseFilters().add(new ResponseFilter() {
	      public void filter(Response response) {
	    	
	        responseTable.put(response.getRequest().getUrl(), response);
	      }
	    });

	    
	    this.proxy.getRequestFilters().add(new RequestFilter() {

			public boolean filter(Request request) {
				//request.getHeaders().put("X-Proxy", "ProxyLight");
				//System.out.println("See request:" + request.getUrl());
				return false;
			}
			
		});
	    
	    //add intercepted request to the cache
	    
	    /*
	    this.proxy.getRequestFilters().add(new RequestFilter() {
	      public boolean filter(Request request) {
	    	 System.out.println("request.url: " + request.getUrl());
	        requestTable.put(request.getUrl(), request);
	        return false;//??
	      }
	    });
	    */

	    // now start the proxy
	    try {
	      this.proxy.start();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }

	 
	  
	  public void initProxy(String browser) {
		    proxy = new ProxyLight();
            
		    if (browser.equalsIgnoreCase("firefox"))
		       this.proxy.setPort(localProxyPort);
		    else if (browser.equalsIgnoreCase("chrome"))
			    this.proxy.setPort(localProxyPort + 1);
		    else 
		    	this.proxy.setPort(localProxyPort + 2);

		    // this response filter adds the intercepted response to the cache
		    this.proxy.getResponseFilters().add(new ResponseFilter() {
		      public void filter(Response response) {
		    	
		        responseTable.put(response.getRequest().getUrl(), response);
		      }
		    });

		    
		    this.proxy.getRequestFilters().add(new RequestFilter() {

				public boolean filter(Request request) {
					request.getHeaders().put("X-Proxy", "ProxyLight");
					//System.out.println("See request:" + request.getUrl());
					//request.dump();
					return false;
				}
				
			});
		    
		    //add intercepted request to the cache
		    
		    /*
		    this.proxy.getRequestFilters().add(new RequestFilter() {
		      public boolean filter(Request request) {
		    	 System.out.println("request.url: " + request.getUrl());
		        requestTable.put(request.getUrl(), request);
		        return false;//??
		      }
		    });
		    */

		    // now start the proxy
		    try {
		      this.proxy.start();
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		  }

		 
	  public static void main(String[] args) {
		  Proxy proxy = new Proxy();
		  proxy.initProxy("chrome");
	        System.out.println("Proxy server is running.."); // Display the string.
	    }
	 

}
