package com.xceptance.loadtest.posters.models.components.WishList;

import com.xceptance.loadtest.api.hpu.LookUpResult;
import com.xceptance.loadtest.api.models.components.Component;
import com.xceptance.loadtest.api.models.pages.Page;
import com.xceptance.loadtest.posters.models.components.cart.CheckoutButton;

public class WishListRemoveButton implements Component
{
    public final static WishListRemoveButton instance = new WishListRemoveButton();

    @Override
    public LookUpResult locate()
    {
        return Page.find().byXPath("//button[@class='remove-from-wishlist']");
    }

    @Override
    public boolean exists()
    {
        return locate().exists();
    }
}
