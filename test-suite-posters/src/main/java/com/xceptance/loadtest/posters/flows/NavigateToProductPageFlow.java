package com.xceptance.loadtest.posters.flows;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import com.xceptance.loadtest.api.actions.PageAction;
import com.xceptance.loadtest.api.flows.Flow;
import com.xceptance.loadtest.api.util.Context;
import com.xceptance.loadtest.api.util.SafetyBreak;
import com.xceptance.loadtest.posters.actions.catalog.AddToWishList;
import com.xceptance.loadtest.posters.actions.catalog.ClickProductDetails;
import com.xceptance.loadtest.posters.actions.catalog.QuickView;
import com.xceptance.loadtest.posters.models.pages.catalog.LandingPage;
import com.xceptance.loadtest.posters.models.pages.catalog.ProductDetailPage;
import com.xceptance.loadtest.posters.models.pages.catalog.ProductListingPage;
import com.xceptance.loadtest.posters.models.pages.search.SearchNoResultPage;

/**
 * Navigates product listing page(s) to arrive at a product page.
 * 
 * @author Xceptance Software Technologies
 */
public class NavigateToProductPageFlow extends Flow
{
    private final SafetyBreak breaker = new SafetyBreak(5);
    boolean Searchorder=false;
    public NavigateToProductPageFlow(boolean Search)
	{
		this.Searchorder = Search;
	}
	
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean execute() throws Throwable
    {
        while (!breaker.reached())
        {
        	
            if (ProductListingPage.instance.is())
            {
                // Apply product listing page actions to the results on this PLP
               // new ManipulateProductListingPageFlow().run();

                // View product's details if the option is available
                if (ProductListingPage.instance.is())
                {
                	viewProduct();
                	
                }
            }

            if (ProductDetailPage.instance.is())
            {
                // Ended up on PDP, so all is good
                break;
            }
            else if (LandingPage.instance.is())
            {
                // Landing page, nothing else to do here
                break;
            }
            else if (SearchNoResultPage.instance.is())
            {
            	
            	// No result page because there was no product, nothing else to do here
            	break;
            }
            else
            {
                Assert.fail("Browsing flow ended on unknown page.");
            }
        }

        return true;
    }

    /**
     * Opens product detail page.
     * 
     * @throws Throwable
     */
    private boolean viewProduct() throws Throwable
    {
        // Don't view more product than we actually have
        final int itemCount = ProductListingPage.instance.itemCount.getItemCount();
        
        if(itemCount==0)
        {
        	if(Searchorder)
        	{
        		return true;
        	}
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
            if (new ClickProductDetails().runIfPossible().isPresent() == false)
            {
                break;
            }
        }
        return true;
    }
}