package com.xceptance.loadtest.posters.models.components.WishList;

import com.xceptance.loadtest.api.hpu.LookUpResult;
import com.xceptance.loadtest.api.models.components.Component;
import com.xceptance.loadtest.api.models.pages.Page;
import com.xceptance.loadtest.posters.models.components.cart.CartEmpty;

public class WishListEmpty implements Component
{
	public static final WishListEmpty instance = new WishListEmpty();

    @Override
    public LookUpResult locate()
    {
    	return Page.find().byXPath("//div[@class='row empty-text-block']/descendant::p[contains(text(),'This list is empty.')]");
    }

    @Override
    public boolean exists()
    {
        return locate().exists();
    }
}
