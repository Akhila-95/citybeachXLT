package com.xceptance.loadtest.posters.flows;

import org.junit.Assert;

import com.xceptance.loadtest.api.flows.Flow;
import com.xceptance.loadtest.api.util.Context;
import com.xceptance.loadtest.posters.actions.catalog.ClickACategory;
import com.xceptance.loadtest.posters.actions.catalog.ClickATopCategory;
import com.xceptance.loadtest.posters.models.pages.catalog.ProductListingPage;

/**
 * Opens a (top) category.
 * 
 * @author Xceptance Software Technologies
 */
public class NavigateCategoriesFlow extends Flow
{
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean execute() throws Throwable
    {
            new ClickATopCategory().run();
        return true;
    }
}