package com.xceptance.loadtest.posters.actions.catalog;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.util.Base64;
import org.json.JSONObject;
import org.junit.Assert;

import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.util.KeyDataPair;
import com.xceptance.loadtest.api.actions.AjaxAction;
import com.xceptance.loadtest.api.events.EventLogger;
import com.xceptance.loadtest.api.hpu.HPU;
import com.xceptance.loadtest.api.util.AjaxUtils;
import com.xceptance.loadtest.api.util.Context;
import com.xceptance.loadtest.api.util.FormUtils;
import com.xceptance.loadtest.api.util.HttpRequest;
import com.xceptance.loadtest.posters.models.pages.catalog.ProductDetailPage;
import com.xceptance.xlt.api.util.XltRandom;
import com.xceptance.loadtest.posters.models.pages.general.GeneralPages;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.jfree.data.json.impl.JSONArray;

/**
 * Chooses a random product configuration.
 * 
 * @author Xceptance Software Technologies
 */
public class ConfigureProductVariation extends AjaxAction<ConfigureProductVariation>
{
	public String PID;
	private HtmlElement Addtocart;
	private HtmlElement Colourelement;
	private String colour;
	private String OldPid;
	private String selectedSize;
	 private int previousCartQuantity;
	 private int colourcount;
	 private int sizecount;
	 private int sizewithoutoutofstock=0; 
	 private boolean isResponseSizeAvailable = false;
	
    /**
     * {@inheritDoc}
     */
    @Override
    public void precheck()
    {
    	//Get all variation attributes
    	 Addtocart = ProductDetailPage.instance.GetPID();
         colourcount= ProductDetailPage.instance.Getcolourcount();
         PID=Addtocart.getAttribute("data-pid");
     	 OldPid=PID;
         sizecount=ProductDetailPage.instance.getSizeElementCount();
         if(colourcount==0 && sizecount!=0)
         {
        	 sizewithoutoutofstock=ProductDetailPage.instance.GetNumberofNonOutofstock();
        	 if(sizewithoutoutofstock==0)
        	 {
        		 sizewithoutoutofstock=1; 
        		// Assert.fail("This product("+PID +") is out of stock!");
        	 }
//        	 else
//        	 {
//        		 Colourelement=ProductDetailPage.instance.Getoption();
//        		 selectedSize=Colourelement.getAttribute("data-attr-value");
//        	 }
        	 
         }
        if(colourcount>=1)
        {
    	  Colourelement= ProductDetailPage.instance.GetColour();
    	  colour=Colourelement.getAttribute("data-attr-value");
        }
    	
    	//Assert.assertEquals("colourdefault", colourdefault);
    	
        //Retrieve current quantity
        previousCartQuantity = GeneralPages.instance.miniCart.getQuantity();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() throws Exception
    {
    	 if(sizewithoutoutofstock==1)
    	 {
    		 EventLogger.BROWSE.warn("This product is out of stock!", PID);
    		// Assert.fail("This product("+PID +") is out of stock!");
    	 }
    	 else
    	 {
    		 configureSelectVariationAttributes();
    	 }
    	// Handle all configurations with select attribute
    	

    	// Handle all configurations with input elements
    	//configureInputVariationAttributes();
    }
    
    private void configureSelectVariationAttributes() throws Exception {
    	if (sizecount == 0) {
    	AddtoCart(PID);
    	} else {
    	boolean IsSizeAvailable=UpdateProductColor();
    	//Assert.fail("Is Size:"+IsSizeAvailable);
    	if(IsSizeAvailable)
    	{
    	PID = UpdateProductSize();
    	AddtoCart(PID);
    	}
    	}
    	// Send product update request for the selected option
    	}
   
    private boolean UpdateProductColor() throws Exception {
	
	Assert.assertTrue("Expected PID to be contained in onchange attribute", !StringUtils.isBlank(PID));
	final String userPass = "storefront" + ":" + "cbstorefront";
    final String userPassBase64 = Base64.encodeBase64String(userPass.getBytes());
    this.getWebClient().addRequestHeader("Authorization", "Basic " + userPassBase64);
	// get select options based on color selection
	WebResponse colorresponse = new HttpRequest()
            .GET()
			//.url("/on/demandware.store/Sites-CityBeachAustralia-Site/default/Product-Variation")
            .url("/on/demandware.store/Sites-CityBeachAustralia-Site/default/Product-Variation")
			.param("dwvar_"+PID+"_color",colour).XHR()  						
            .param("pid", PID)
			    .param("quantity", "1")
			.fire();
	//Assert.assertEquals("Responce", colorresponse.getContentAsString());
	
	 if(sizecount>0)
	 {
		 org.json.JSONArray jsonarray=new JSONObject(colorresponse.getContentAsString()).getJSONObject("product").getJSONArray("variationAttributes").getJSONObject(1).getJSONArray("values");
	 List<String> filteredOptions = new ArrayList<>();
	 for (int i = 0; i < jsonarray.length(); i++) {
         JSONObject obj = jsonarray.getJSONObject(i);
         if (obj.getBoolean("selectable")) {
        	 filteredOptions.add(obj.getString("id"));
         }
     }
	// Assert.assertEquals("Responce", filtered);
	 if (filteredOptions.size() == 0)
	 {
	 EventLogger.BROWSE.warn("Size is not available for this Product!", PID);
	 return false;
	 //Assert.fail("Size is not available for this Product");
	 }
	 else
	 selectedSize = filteredOptions.get(XltRandom.nextInt(0, filteredOptions.size() - 1));
	 }
	 return true;



	 }
    
    private String UpdateProductSize() throws Exception
    {
    	
    	Assert.assertTrue("Expected PID to be contained in onchange attribute", !StringUtils.isBlank(PID));
    	// Get size of selected option
    	Assert.assertTrue("Expected title attribute containing colour", !StringUtils.isBlank(colour));
    	Assert.assertTrue("Expected title attribute containing size", !StringUtils.isBlank(selectedSize));
    	//Addtocart.click();
    	// Request the AddtoCart
    	WebResponse response = new HttpRequest()
                                .GET()
    							//.url("/on/demandware.store/Sites-CityBeachAustralia-Site/default/Product-Variation")
    							.url("/on/demandware.store/Sites-CityBeachAustralia-Site/default/Product-Variation")
    							.param("dwvar_"+PID+"_color",colour)    						
    							.param("dwvar_"+PID+"_size", selectedSize)
    	                        .param("pid", PID)
   							    .param("quantity", "1")
   							    //.assertJSONObject("Expected json response :'", true, json -> json.getJSONObject("product").has("id"))
   							.fire();
    	
    	if(response.getContentAsString().startsWith("{"))
    	{
    	PID=new JSONObject(response.getContentAsString()).getJSONObject("product").getString("id");
    	}
    	else
    	{
    		Assert.assertEquals("Responce :", response.getContentAsString());
    	}
    //	Assert.assertEquals("Responce ", PID+";"+OldPid);
    	//Updating new Pid after Size selection
    	
    	if(OldPid==PID)
    	{
    		Assert.assertEquals("Responce ", response.getContentAsString());
    	}
    	return PID;
    }
    private void AddtoCart(String PID) throws Exception
    {
    	//Assert.assertEquals("Product added to cart" , PID);
    	 final List<NameValuePair> addToCartParams = new ArrayList<NameValuePair>();
         addToCartParams.add(new NameValuePair("pid", PID));
         addToCartParams.add(new NameValuePair("quantity", "1"));
         HttpRequest req = new HttpRequest()
					.url("/on/demandware.store/Sites-CityBeachAustralia-Site/default/Cart-AddProduct")
                    .POST()          
				    .postParams(addToCartParams);
         WebResponse response1=req.fire();
         if(response1.getStatusCode()==200)
         {
             // Assert.assertEquals("Responce", response1.getContentAsString());
     	     JSONObject addToCartJson = AjaxUtils.convertToJson(response1.getContentAsString());
     	     if(addToCartJson.getString("message").contains("cannot be added to the cart"))
     	     {
     		    EventLogger.BROWSE.warn("This product is out of stock!", PID);
     		   //Context.get().data.IsOutofstock=true;
     	     }
     	    else
     	    {
               // Handle error in add to cart response
              
    	       //Assert.assertEquals("test responce ",response1.getContentAsString());
    	       int numItems=new JSONObject(response1.getContentAsString()).getJSONObject("cart").getInt("numItems");
    	       Assert.assertTrue("Cart quantity did not change", addToCartJson.has("cart") && (numItems > previousCartQuantity));
               //Assert.assertEquals("test responce",  response1.getContentAsString());
    	       GeneralPages.instance.miniCart.updateQuantity(numItems);
    	       // Increase total add to cart count if successful
                Context.get().data.totalAddToCartCount++;
     	     }
         }
         else
         {
        	 Assert.fail("Responce Message :"+response1.getStatusMessage()); 
         }
    	
    }
    /**
     * {@inheritDoc}
     */
    @Override
    protected void postValidate() throws Exception
    {
    	Assert.assertTrue("Expected PID  containing ProductId", !StringUtils.isBlank(PID));
    	// Nothing to validate
    }
}