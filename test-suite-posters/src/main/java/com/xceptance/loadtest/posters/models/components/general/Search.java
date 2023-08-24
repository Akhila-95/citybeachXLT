package com.xceptance.loadtest.posters.models.components.general;

import com.xceptance.loadtest.api.hpu.LookUpResult;
import com.xceptance.loadtest.api.models.components.Component;

/**
 * Search component.
 * 
 * @author Xceptance Software Technologies
 */
public class Search implements Component
{
	public static final Search instance = new Search();

    @Override
    public LookUpResult locate()
    {
        return Header.instance.locate().byCss(".site-search");
    }

    @Override
    public boolean exists()
    {
        return locate().exists();
    }
    
    public LookUpResult getSearchForm()
    {
    	return locate().byCss("form[name='simpleSearch']");
    }

    public LookUpResult getSearchField()
    {
        return getSearchForm().byCss(".input-button>input");
    }
    
    public LookUpResult getSearchButton()
    {
        return getSearchForm().byCss("button[type='submit'][name='search-button']");
    }
}