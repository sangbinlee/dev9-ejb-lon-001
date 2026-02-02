package com.dev9.lon.dao;

import jakarta.ejb.Remote;

@Remote
public interface CallBreaker {
	String breakCall(String input);
}
