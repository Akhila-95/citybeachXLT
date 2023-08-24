package com.xceptance.loadtest.posters.actions.catalog;

import java.util.List;

import org.json.JSONObject;
import org.junit.Assert;

import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.xceptance.loadtest.api.actions.PageAction;
import com.xceptance.loadtest.api.util.Context;
import com.xceptance.loadtest.api.util.HttpRequest;
import com.xceptance.loadtest.api.util.RandomUtils;
import com.xceptance.loadtest.api.validators.Validator;
import com.xceptance.loadtest.posters.models.pages.catalog.ProductDetailPage;
import com.xceptance.loadtest.posters.models.pages.catalog.ProductListingPage;

public class QuickView  extends PageAction<QuickView>
{
    private HtmlElement element;
    private String url;

    @Override
    public void precheck()
    {
    	
    	element = ProductListingPage.instance.productGrid.getFilteredProductsQuick();
    	url=element.getAttribute("href");
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
     Assert.assertEquals(true,url.contains( new JSONObject(response.getContentAsString()).getJSONObject("product").getString("masterPid")));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void postValidate() throws Exception
    {
    	Validator.validatePageSource();
    	
        //ProductDetailPage.instance.validate();
    }
}
