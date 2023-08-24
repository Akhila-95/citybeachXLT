package com.xceptance.loadtest.posters.flows;

import com.amazonaws.services.ec2.model.Address;
import com.xceptance.loadtest.api.flows.Flow;
import com.xceptance.loadtest.posters.actions.checkout.AddressForm;
import com.xceptance.loadtest.posters.actions.checkout.Checkout;
import com.xceptance.loadtest.posters.actions.checkout.CheckoutPayment;
import com.xceptance.loadtest.posters.actions.checkout.CheckoutPlaceOrder;
import com.xceptance.loadtest.posters.actions.checkout.CheckoutShippingAddress;

/**
 * Follows the checkout process, optionally placing an order.
 * 
 * @author Xceptance Software Technologies
 */
public class CheckoutFlow extends Flow
{
	private boolean placeOrder = false;
	
	public CheckoutFlow(boolean placeOrder)
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
    		new CheckoutShippingAddress().run();
    	}
    	else
    		new AddressForm().run();
    		

        return true;
    }
}