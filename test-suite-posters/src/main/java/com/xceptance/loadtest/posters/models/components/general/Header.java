package com.xceptance.loadtest.posters.models.components.general;

import com.xceptance.loadtest.api.hpu.LookUpResult;
import com.xceptance.loadtest.api.models.components.Component;
import com.xceptance.loadtest.api.models.pages.Page;

/**
 * Header component
 * 
 * @author Xceptance Software Technologies
 */
public class Header implements Component
{
	public static final Header instance = new Header();

    @Override
    public LookUpResult locate()
    {
        return Page.find().byCss("header");
    }

    @Override
    public boolean exists()
    {
        return locate().exists();
    }
}