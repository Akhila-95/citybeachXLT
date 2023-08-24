package com.xceptance.loadtest.posters.models.components.general;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.xceptance.loadtest.api.hpu.LookUpResult;
import com.xceptance.loadtest.api.models.components.Component;
import com.xceptance.loadtest.api.util.Context;
import com.xceptance.loadtest.api.util.DataUtils;

public class WishList  implements Component
{
	public static final WishList instance = new WishList();

    @Override
    public LookUpResult locate()
    {
        return Header.instance.locate().byCss("li>.wishlist-link");
    }

    @Override
    public boolean exists()
    {
        return locate().exists();
    }

    public boolean isEmpty()
    {
        return getQuantity() == 0;
    }

    public int getLineItemCount()
    {
        return Context.get().data.cartLineItemCount;
    }

    public LookUpResult getQuantityElement()
    {
        return locate().byCss(".wishlist-count");
    }

    public int getQuantity()
    {
    	return DataUtils.toInt(getQuantityElement().asserted().first().asText()==""?"0":getQuantityElement().asserted().first().asText());
    }

    public void updateQuantity(final int newWishlistQuantity)
    {
        final HtmlElement qty = getQuantityElement().first();
        qty.setTextContent(String.valueOf(newWishlistQuantity));

        Context.get().data.wishlistcount = newWishlistQuantity;
    }

    public HtmlElement getWishlistLink()
    {
        return locate().byCss("li>.wishlist-link").single();
    }
}
