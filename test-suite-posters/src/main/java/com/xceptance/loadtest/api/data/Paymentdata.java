package com.xceptance.loadtest.api.data;

import com.xceptance.loadtest.api.configuration.annotations.Property;

public class Paymentdata {
	
	 /** Payment state data. */
    @Property(key = "stateData", required = true)
    public String Statedata;

}
