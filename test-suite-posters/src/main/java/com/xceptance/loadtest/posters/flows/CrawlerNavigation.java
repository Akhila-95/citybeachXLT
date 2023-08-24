package com.xceptance.loadtest.posters.flows;

import java.net.URL;

import com.xceptance.loadtest.api.flows.Flow;
import com.xceptance.loadtest.api.util.Context;
import com.xceptance.loadtest.api.util.SafetyBreak;
import com.xceptance.loadtest.posters.actions.Homepage;
import com.xceptance.loadtest.posters.actions.catalog.ConfigureProductVariation;
import com.xceptance.loadtest.posters.actions.catalog.Crawler;
import com.xceptance.loadtest.posters.models.pages.catalog.ProductDetailPage;

public class CrawlerNavigation  extends Flow {
	
	  @Override
	    public boolean execute() throws Throwable
	    {
		  String baseurl=Context.configuration().isProd==true?Context.configuration().produrl:Context.configuration().siteUrlHomepage;
		   final  URL url=new URL(baseurl);
	        new Crawler(url).run();

	        return true;
	    }

}
