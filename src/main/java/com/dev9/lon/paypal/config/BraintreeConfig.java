package com.dev9.lon.paypal.config;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Environment;

public class BraintreeConfig {

	static final String MERCHANT_ID = "5wdg47wbvv8tc3qf";
	static final String PUBLIC_KEY = "4fxc4qxd4v26ykxg";
	static final String PRIVATE_KEY = "93984f2c3a953b38737a559438ac5a4e";

	// BraintreeGateway gateway = new BraintreeGateway(
	// Environment.SANDBOX,
	// "the_merchant_id",
	// "the_public_key",
	// "the_private_key"
	// );

	public static final BraintreeGateway gateway = new BraintreeGateway(
			Environment.SANDBOX, MERCHANT_ID, PUBLIC_KEY, PRIVATE_KEY);
}
