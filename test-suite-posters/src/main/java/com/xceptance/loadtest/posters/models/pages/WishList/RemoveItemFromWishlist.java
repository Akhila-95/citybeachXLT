package com.xceptance.loadtest.posters.models.pages.WishList;

import org.eclipse.jetty.util.ajax.JSON;
import org.json.JSONObject;
import org.junit.Assert;

import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.xceptance.loadtest.api.actions.PageAction;
import com.xceptance.loadtest.api.util.Context;
import com.xceptance.loadtest.api.util.HttpRequest;
import com.xceptance.loadtest.api.validators.Validator;

public class RemoveItemFromWishlist extends PageAction<RemoveItemFromWishlist>
{
	@Override
	public void precheck()
	{
		super.precheck();
		
		Assert.assertTrue("Expected checkout button exists and is clickable", WishListPage.instance.wishListRemoveButton.exists());		
	}
	
	@Override
	protected void doExecute() throws Exception
	{
		
	    		HtmlElement element=WishListPage.instance.wishListRemoveButton.locate().asserted("Expected Remove button").random();
	    			    		String Url=element.getAttribute("data-url");
	    	     HttpRequest req1 = new HttpRequest()
	    					.XHR()
	    					.url(Url)
	    	                .GET();             
	    				    
	    	     WebResponse response=req1.fire();
	    	     Assert.assertEquals(true,new JSONObject(response.getContentAsString()).get("success"));
	    	     String baseurl=Context.configuration().isProd==true?Context.configuration().produrl:Context.configuration().siteUrlHomepage;
	    	     loadPage(baseurl+"wishlist");
	    	     
	    	     
	}

	@Override
	protected void postValidate() throws Exception
	{
        Validator.validatePageSource();
        // Validate that it is the shipping address page
       // WishListPage.instance.validate();
	}
}
