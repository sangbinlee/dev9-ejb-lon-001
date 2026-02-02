package com.dev9.lon.sample.ejb;

import com.dev9.lon.sample.ejb.remote.BrokerS;

import jakarta.ejb.Stateless;

@Stateless
public class BrokerSBean implements BrokerS {
	@Override
	public void buyStock(String symbol, int quantity) {
		// Implementation of buying stock
		System.out.println("Bought " + quantity + " shares of " + symbol);
	}

	@Override
	public void sellStock(String symbol, int quantity) {
		// Implementation of selling stock
		System.out.println("Sold " + quantity + " shares of " + symbol);
	}
}
