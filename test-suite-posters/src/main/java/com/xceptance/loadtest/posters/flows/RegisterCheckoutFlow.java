package com.xceptance.loadtest.posters.flows;

import com.xceptance.loadtest.api.flows.Flow;
import com.xceptance.loadtest.posters.actions.checkout.Checkout;
import com.xceptance.loadtest.posters.actions.checkout.RegisterShippingAdress;
import com.xceptance.loadtest.posters.actions.checkout.registerOrder;

public class RegisterCheckoutFlow extends Flow
{
	private boolean placeOrder = false;
	
	public RegisterCheckoutFlow(boolean placeOrder)
	{
		this.placeOrder = placeOrder;
	}
	
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean execute() throws Throwable
    {
    	// Enter checkout
    	new Checkout().run();

    	// Provide shipping address
    	
    	// Provide payment details
    	
    	
    	// Place the order
    	if(placeOrder)
    	{
    		new RegisterShippingAdress().run();
    	}
    	else
    		new registerOrder().run();
    		

        return true;
    }
}