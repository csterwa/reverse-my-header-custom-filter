package com.vmware.scg.extensions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class ReverseMyHeaderGatewayFilterFactory
  extends AbstractGatewayFilterFactory<Object> {

  private static final Logger LOGGER = LoggerFactory.getLogger(ReverseMyHeaderGatewayFilterFactory.class);

  @Override
  public GatewayFilter apply(Object config) {
     return (exchange, chain) ->
     {
		 reverseHeader(exchange.getRequest().getHeaders(), exchange.getResponse().getHeaders());

		 return chain.filter(exchange);
     };
  }

  private void reverseHeader(HttpHeaders requestHeaders, HttpHeaders responseHeaders) {
	  requestHeaders.getOrEmpty("X-Reverse-Me")
           .stream()
           .map(value -> new StringBuilder(value).reverse().toString())
           .forEach(reversed -> {
		       responseHeaders.add("X-Reversed", reversed);
		       LOGGER.info("Added X-Reversed header with value " + reversed);
		     });
  }
}

