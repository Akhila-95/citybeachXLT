package com.xceptance.loadtest.posters.flows;

import com.xceptance.loadtest.api.flows.Flow;
import com.xceptance.loadtest.posters.models.pages.WishList.RemoveItemFromWishlist;

public class WishListFlow extends Flow
{
	
	public WishListFlow()
	{
		
	}
	
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean execute() throws Throwable
    {
    	// Enter checkout
    	new RemoveItemFromWishlist().run();
        return true;
    }
}
