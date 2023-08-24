package com.xceptance.loadtest.posters.models.components.WishList;

import com.xceptance.loadtest.api.hpu.LookUpResult;
import com.xceptance.loadtest.api.models.components.Component;
import com.xceptance.loadtest.api.models.pages.Page;
import com.xceptance.loadtest.posters.models.components.cart.CartBanner;

public class WishListBanner implements Component
{
	public static final WishListBanner instance = new WishListBanner();

    @Override
    public LookUpResult locate()
    {
        return Page.find().byCss(".page-heading");
    }

    @Override
    public boolean exists()
    {
        return locate().exists();
    }
}