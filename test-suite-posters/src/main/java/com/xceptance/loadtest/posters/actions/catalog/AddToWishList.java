package com.xceptance.loadtest.posters.actions.catalog;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.junit.Assert;

import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import com.xceptance.loadtest.api.actions.PageAction;
import com.xceptance.loadtest.api.util.AjaxUtils;
import com.xceptance.loadtest.api.util.HttpRequest;
import com.xceptance.loadtest.api.util.RandomUtils;
import com.xceptance.loadtest.api.validators.Validator;
import com.xceptance.loadtest.posters.models.pages.catalog.ProductDetailPage;
import com.xceptance.loadtest.posters.models.pages.catalog.ProductListingPage;

public class AddToWishList extends PageAction<AddToWishList>
{
    private HtmlElement Element;

    @Override
    public void precheck()
    {
    	Element = RandomUtils.randomEntry(ProductListingPage.instance.productGrid.getFilteredProducts());
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() throws Exception
    {
    	String pid=Element.getAttribute("data-pid");
    	//Assert.assertEquals("Expected pid", pid);
   	 final List<NameValuePair> parm = new ArrayList<NameValuePair>();
	 parm.add(new NameValuePair("pid", pid));
     HttpRequest req1 = new HttpRequest()
				.XHR()
				.url("/on/demandware.store/Sites-CityBeachAustralia-Site/default/Wishlist-AddProduct")
                .POST()             
			    .postParams(parm);
     WebResponse response=req1.fire();
    // Assert.assertEquals("Wish list responce",response.getContentAsString());
 	JSONObject addToWishlist = AjaxUtils.convertToJson(response.getContentAsString());
 	if( !addToWishlist.getString("msg").contains("item is already in the wishlist and will not be added again"))
 	{
 		Assert.assertEquals("This item has been successfully added to your wishlist.", addToWishlist.getString("msg"));
 	}
        //loadPageByUrlClick(url);
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
