package com.dev9.lon.sample.ejb;

import com.dev9.lon.sample.ejb.remote.BrokerS;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class HelloServiceBean implements CallBreaker {
	@Override
	public String sayHello(String name) {
		return "Hello, " + name + "!";
	}

	@EJB
	private BrokerS broker;

	public void executeTrade() {
		broker.buyStock("AAPL", 10);
	}

}
