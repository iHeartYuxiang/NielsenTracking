package com.iheart.proxyLight;

public interface ResponseFilter {
   /**
   * Filter response
   * @param response
   */
  public void filter(Response response);
}
