package com.dev9.lon.sample.ejb.local;

import jakarta.ejb.Local;

@Local
public interface BrokerS {
	void buyStock(String symbol, int quantity);

	void sellStock(String symbol, int quantity);
}
