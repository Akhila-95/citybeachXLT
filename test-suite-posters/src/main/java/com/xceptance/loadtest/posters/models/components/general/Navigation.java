package com.xceptance.loadtest.posters.models.components.general;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.xceptance.loadtest.api.hpu.LookUpResult;
import com.xceptance.loadtest.api.models.components.Component;
import com.xceptance.loadtest.api.models.pages.Page;
import com.xceptance.loadtest.api.util.Context;

/**
 * Navigation component.
 * 
 * @author Xceptance Software Technologies
 */
public class Navigation implements Component
{
	public static final Navigation instance = new Navigation();
    private String Topcategory="";
    @Override
    public LookUpResult locate()
    {
        return Header.instance.locate().byCss(".header-menu-block");
    }

    @Override
    public boolean exists()
    {
        return locate().exists();
    }

    public HtmlElement getTopCategories()
    {
    	HtmlElement topcategory= filterinvalidLinks(locate().byCss("li.level-1-menu.drop-down-menus>a")).random();
    	return topcategory;
    }
    public HtmlElement Subcategory(String Topcategory )
    {
    	HtmlElement subcategory;
    	String xpath="";
    	if(Topcategory.contains("boardsports"))
    	{
    	 //xpath="li.level-2-menu>a";
    		xpath="li.level-3-menu>a";
    	 subcategory= filterinvalidLinks(locate().byCss(xpath)).random();
    	}
    	else
    	{
    		
    		 xpath="li.level-3-menu>a[href *= '"+Topcategory+"']";
        	//Assert.assertEquals("test xapth Subcategory", xpath);
        	 subcategory= filterinvalidLinks(locate().byCss(xpath)).random();
    	}
    	return subcategory;
        
    }
    public LookUpResult getCategories()
    {
    	Assert.assertEquals("Subcategory", Topcategory);
        return filterinvalidLinks(locate().byCss("li.level-3-menu>a"));
    }

    private LookUpResult filterinvalidLinks(final LookUpResult links)
    {
        return links.filter(Page.VALIDLINKS)
                        .discard(Context.configuration().filterCategoryUrls.unweightedList(),
                        e -> e.getAttribute("href"));
    }
    
}