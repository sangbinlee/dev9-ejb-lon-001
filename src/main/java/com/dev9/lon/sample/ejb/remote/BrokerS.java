package com.dev9.lon.sample.ejb.remote;

import jakarta.ejb.Remote;

@Remote
public interface BrokerS {
	void buyStock(String symbol, int quantity);

	void sellStock(String symbol, int quantity);
}
