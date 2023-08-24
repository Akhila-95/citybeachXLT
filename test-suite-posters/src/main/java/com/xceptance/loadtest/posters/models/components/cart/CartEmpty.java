package com.xceptance.loadtest.posters.models.components.cart;

import com.xceptance.loadtest.api.hpu.LookUpResult;
import com.xceptance.loadtest.api.models.components.Component;
import com.xceptance.loadtest.api.models.pages.Page;

/**
 * Cart empty indicator component.
 * 
 * @author Xceptance Software Technologies
 */
public class CartEmpty implements Component
{
	public static final CartEmpty instance = new CartEmpty();

    @Override
    public LookUpResult locate()
    {
    	return Page.find().byXPath("//div[@class='container cart-empty']/descendant::h1[contains(text(),'Your Shopping Cart is Empty')]");
    }

    @Override
    public boolean exists()
    {
        return locate().exists();
    }
}