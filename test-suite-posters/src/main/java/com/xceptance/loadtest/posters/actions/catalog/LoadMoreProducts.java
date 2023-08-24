package com.xceptance.loadtest.posters.actions.catalog;

import static org.junit.Assert.assertEquals;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.xceptance.loadtest.api.actions.PageAction;
import com.xceptance.loadtest.api.util.HttpRequest;
import com.xceptance.loadtest.api.util.RandomUtils;
import com.xceptance.loadtest.api.validators.Validator;
import com.xceptance.loadtest.posters.models.pages.catalog.ProductDetailPage;
import com.xceptance.loadtest.posters.models.pages.catalog.ProductListingPage;
import com.xceptance.loadtest.posters.models.pages.general.GeneralPages;

public class LoadMoreProducts extends PageAction<LoadMoreProducts>
{
    private HtmlElement etmlElement;
    String url;

    @Override
    public void precheck()
    {
    	etmlElement = ProductListingPage.instance.productGrid.GetLoadmoreButton();
    	url=etmlElement.getAttribute("data-url");
    	// Sanity check that there is at least one URL
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() throws Exception
    {
    	         HttpRequest req1 = new HttpRequest()
					.url(url)
	                .GET(); 
    	         WebResponse response=req1.fire();
    	         //String count=String.format("%,d", itemCount);
                Assert.assertEquals("OK", response.getStatusMessage());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    protected void postValidate() throws Exception
    {
    	Validator.validatePageSource();
    	GeneralPages.instance.validate();
    }
}
