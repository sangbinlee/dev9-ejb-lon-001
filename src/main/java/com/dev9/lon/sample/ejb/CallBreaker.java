package com.dev9.lon.sample.ejb;

import jakarta.ejb.Remote;

@Remote
public interface CallBreaker {
    String sayHello(String name);
}
