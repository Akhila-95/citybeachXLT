package com.xceptance.loadtest.posters.models.pages.WishList;

import com.xceptance.loadtest.posters.models.components.WishList.*;
import com.xceptance.loadtest.posters.models.components.cart.CartEmpty;
import com.xceptance.loadtest.posters.models.components.cart.CartTable;
import com.xceptance.loadtest.posters.models.components.cart.CheckoutButton;
import com.xceptance.loadtest.posters.models.pages.cart.CartPage;
import com.xceptance.loadtest.posters.models.pages.general.GeneralPages;

public class WishListPage extends GeneralPages
{
    public static final WishListPage instance = new WishListPage();

    public final WishListBanner wishListBanner = WishListBanner.instance;
    
    public final WishListEmpty wishListEmpty = WishListEmpty.instance;
    
    public final WishListTable wishListTable = WishListTable.instance;
    
    public final WishListRemoveButton wishListRemoveButton = WishListRemoveButton.instance;
    
    @Override
    public void validate()
    {
    
        //super.validate();

        validate(has(wishListBanner), hasOneOf(wishListEmpty, wishListTable));
    }

    @Override
    public boolean is()
    {
        return super.is() && matches(has(wishListBanner), hasOneOf(wishListEmpty, wishListTable));
    }
    
    public void validateIsNotEmpty()
    {
    	validate(hasNot(wishListEmpty));
    }

    public void validateIsEmpty()
    {
    	validate(has(wishListEmpty));
    }
}