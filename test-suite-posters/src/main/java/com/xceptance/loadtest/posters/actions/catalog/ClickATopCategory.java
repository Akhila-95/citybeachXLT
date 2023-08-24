package com.xceptance.loadtest.posters.actions.catalog;

import org.junit.Assert;

import com.gargoylesoftware.htmlunit.WaitingRefreshHandler;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.xceptance.loadtest.api.actions.PageAction;
import com.xceptance.loadtest.api.validators.Validator;
import com.xceptance.loadtest.posters.models.pages.catalog.ProductListingPage;
import com.xceptance.loadtest.posters.models.pages.checkout.ShippingAddressPage;
import com.xceptance.loadtest.posters.models.pages.general.GeneralPages;

/**
 * Selects a top category from the navigation menu.
 * 
 * @author Xceptance Software Technologies
 */
public class ClickATopCategory extends PageAction<ClickATopCategory>
{
	@Override
	public void precheck()
	{
		super.precheck();

		// Validate that we have the shipping address form
	}
    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() throws Exception
    {
    	HtmlElement categoryLink = GeneralPages.instance.navigation.getTopCategories();
        //loadPageByClick(categoryLink);
       String Topcategory= categoryLink.getAttribute("href");
       HtmlElement subcategory=  GeneralPages.instance.navigation.Subcategory(Topcategory);
       if (ProductListingPage.instance.is() == false)
       {
    	   getWebClient().setRefreshHandler(new WaitingRefreshHandler());
    	   loadPageByClick(subcategory);
       }
    	//loadPage("https://dev.citybeach.com/au/womens/accessories/sale/");
       
        // In case this is does not really end up somewhere useful, open a category
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
