package com.xceptance.loadtest.posters.actions.catalog;

import com.xceptance.loadtest.api.actions.PageAction;
import com.xceptance.loadtest.api.util.Context;
import com.xceptance.loadtest.api.validators.Validator;
import com.xceptance.loadtest.posters.models.pages.WishList.WishListPage;
import com.xceptance.loadtest.posters.models.pages.general.GeneralPages;

public class OpenWishList extends PageAction<OpenWishList>
{
    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() throws Exception
    {
    	  String baseurl=Context.configuration().isProd==true?Context.configuration().produrl:Context.configuration().siteUrlHomepage;
    	loadPage(baseurl+"wishlist");
    	
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void postValidate() throws Exception
    {
        Validator.validatePageSource();
        GeneralPages.instance.validate();
        WishListPage.instance.validate();
    }
}
