package com.xceptance.loadtest.posters.tests;

import com.xceptance.loadtest.api.tests.LoadTestCase;
import com.xceptance.loadtest.posters.flows.CrawlerNavigation;
import com.xceptance.loadtest.posters.flows.VisitFlow;
public class TCrawler  extends LoadTestCase {
	/**
     * {@inheritDoc}
     */
    @Override
    public void test() throws Throwable
    {
	// Start at the landing page
    new VisitFlow().run();
    // Determine the number of times to descent from the top categories into the catalog
    // Browse available categories
    new CrawlerNavigation().run();

    }
}
