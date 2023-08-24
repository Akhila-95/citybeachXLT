package com.xceptance.loadtest.posters.flows;

import com.xceptance.loadtest.api.flows.Flow;
import com.xceptance.loadtest.posters.actions.checkout.AddressForm;
import com.xceptance.loadtest.posters.actions.checkout.Checkout;
import com.xceptance.loadtest.posters.actions.checkout.CheckoutShippingAddress;
import com.xceptance.loadtest.posters.actions.checkout.PickupInStore;

public class PickupInStoreFlow extends Flow
{
	
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean execute() throws Throwable
    {
    	// Enter checkout
    	new Checkout().run();

    	// Provide shipping Store details
    	
    	// Provide payment details
    	
    	
    	// Place the order
    	
    		new PickupInStore().run();
        return true;
    }
}
