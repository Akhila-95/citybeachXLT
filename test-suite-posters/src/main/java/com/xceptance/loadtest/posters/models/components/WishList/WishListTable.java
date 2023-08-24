package com.xceptance.loadtest.posters.models.components.WishList;

import com.xceptance.loadtest.api.hpu.LookUpResult;
import com.xceptance.loadtest.api.models.components.Component;
import com.xceptance.loadtest.api.models.pages.Page;
import com.xceptance.loadtest.posters.models.components.cart.CartTable;

public class WishListTable  implements Component
{
	public static final WishListTable instance = new WishListTable();

    @Override
    public LookUpResult locate()
    {
        return Page.find().byCss(".wishlist-product-grid");
    }

    @Override
    public boolean exists()
    {
        return locate().exists();
    }
}