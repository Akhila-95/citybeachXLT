package com.xceptance.loadtest.posters.flows;

import org.junit.Assert;

import com.xceptance.loadtest.api.actions.PageAction;
import com.xceptance.loadtest.api.flows.Flow;
import com.xceptance.loadtest.api.util.Context;
import com.xceptance.loadtest.posters.actions.catalog.AddToWishList;
import com.xceptance.loadtest.posters.models.pages.catalog.ProductListingPage;

public class NavigateTotheProductListPage extends Flow
{
    

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean execute() throws Throwable
    {
       
            if (ProductListingPage.instance.is())
            {
                // Apply product listing page actions to the results on this PLP
               // new ManipulateProductListingPageFlow().run();
                // View product's details if the option is available
                if (ProductListingPage.instance.is())
                {
                	
                    viewProductDetails();
                    
                }
            }
            else
            {
            	 final int itemCount = ProductListingPage.instance.itemCount.getItemCount();
                 if(itemCount==0)
                 {
                 	Assert.fail("No Products Found for this Categories");
                 }
                Assert.fail("Browsing flow ended on unknown page.");
            }
        

        return true;
    }

    /**
     * Opens product detail page.
     * 
     * @throws Throwable
     */
    private void viewProductDetails() throws Throwable
    {
        // Don't view more product than we actually have
        final int itemCount = ProductListingPage.instance.itemCount.getItemCount();
        if(itemCount==0)
        {
        	Assert.fail("No Products Found for this Categories");
        }
        // We might view several products
        int viewCount = Context.configuration().productViewCount.random();
        viewCount = viewCount > itemCount ? itemCount : viewCount;

        // Keep the context
        final PageAction<?> currentAction = Context.getCurrentAction();
        for (int i = 0; i < viewCount; i++)
        {
            if (i != 0)
            {
                // If we are not doing this for the first time, emulate a page back in the browser or opening of multiple tabs
                Context.resetCurrentAction(currentAction);
            }

            // Try to open PDP if filter does not discard the product page
            if (new AddToWishList().runIfPossible().isPresent() == false)
            {
                break;
            }
        }
    }
}
