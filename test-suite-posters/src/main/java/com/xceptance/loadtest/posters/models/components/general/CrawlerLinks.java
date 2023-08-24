package com.xceptance.loadtest.posters.models.components.general;

import com.xceptance.loadtest.api.hpu.LookUpResult;
import com.xceptance.loadtest.api.models.components.Component;
import com.xceptance.loadtest.api.models.pages.Page;

public class CrawlerLinks implements Component {
	public static final CrawlerLinks instance = new CrawlerLinks();

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
