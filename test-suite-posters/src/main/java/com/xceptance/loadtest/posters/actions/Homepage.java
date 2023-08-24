package com.xceptance.loadtest.posters.actions;

import java.net.URL;

import org.apache.commons.net.util.Base64;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;

import com.xceptance.loadtest.api.actions.PageAction;
import com.xceptance.loadtest.api.validators.Validator;
import com.xceptance.loadtest.posters.models.pages.general.HomePage;

/**
 * Opens the start (home) page.
 *
 * @author Xceptance Software Technologies
 */
public class Homepage extends PageAction<Homepage>
{
    /**
     * Start page URL string.
     */
    private final String urlString;

    /**
     * Creates the action.
     *
     * @param urlString The start page URL as string
     */
    public Homepage(final String urlString)
    {
    	
    	this.getWebClient().getOptions().setJavaScriptEnabled(false);
        this.urlString = urlString;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() throws Exception
    {
    	final String userPass = "storefront" + ":" + "cbstorefront";
        final String userPassBase64 = Base64.encodeBase64String(userPass.getBytes());
        this.getWebClient().addRequestHeader("Authorization", "Basic " + userPassBase64);
        // Load the page
        loadPage(new URL(urlString));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void postValidate() throws Exception
    {
        // Validate the page load
        Validator.validatePageSource();

        // Home page validation
        HomePage.instance.validate();

        // Validate that cart is empty and no user is logged in
        //Assert.assertTrue("Cart not empty", HomePage.instance.miniCart.isEmpty());
        //Assert.assertTrue("User is logged on", HomePage.instance.user.isNotLoggedIn());
    }
}