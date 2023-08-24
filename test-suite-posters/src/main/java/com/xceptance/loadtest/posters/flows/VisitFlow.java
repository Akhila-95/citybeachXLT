package com.xceptance.loadtest.posters.flows;

import com.xceptance.loadtest.api.data.Account;
import com.xceptance.loadtest.api.flows.Flow;
import com.xceptance.loadtest.api.util.Context;
import com.xceptance.loadtest.posters.actions.Homepage;

import junit.framework.Assert;

/**
 * Visits the given (home) page.
 * 
 * @author Xceptance Software Technologies
 */
public class VisitFlow extends Flow
{
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean execute() throws Throwable
    {
    	
        new Homepage(Context.configuration().isProd==true?Context.configuration().produrl:Context.configuration().siteUrlHomepage).run();

        return true;
    }
}