package com.xceptance.loadtest.posters.tests;

import com.xceptance.loadtest.api.tests.LoadTestCase;
import com.xceptance.loadtest.api.util.Context;
import com.xceptance.loadtest.posters.actions.catalog.OpenWishList;
import com.xceptance.loadtest.posters.flows.NavigateCategoriesFlow;
import com.xceptance.loadtest.posters.flows.NavigateToProductPageFlow;
import com.xceptance.loadtest.posters.flows.NavigateTotheProductListPage;
import com.xceptance.loadtest.posters.flows.VisitFlow;
import com.xceptance.loadtest.posters.flows.WishListFlow;

public class TWishlist extends LoadTestCase
{
    /**
     * {@inheritDoc}
     */
    @Override
    public void test() throws Throwable
    {
    	// Visits the landing page and leave immediately
    	 new VisitFlow().run();

         // Determine the number of times to descent from the top categories into the catalog
                 final int rounds = Context.configuration().fullBrowseFlow.value;
                 // Browse available categories
                 new NavigateCategoriesFlow().run();
                 // Browse the resulting pages and open product detail pages
                 new NavigateTotheProductListPage().run();
                 new OpenWishList().run();
                 new WishListFlow().run();
    }
}
