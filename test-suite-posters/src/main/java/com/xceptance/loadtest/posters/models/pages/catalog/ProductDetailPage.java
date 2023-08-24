package com.xceptance.loadtest.posters.models.pages.catalog;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.xceptance.common.util.RegExUtils;
import com.xceptance.loadtest.api.models.pages.Page;
import com.xceptance.loadtest.posters.models.pages.general.GeneralPages;

/**
 * Represents a product detail page.
 *
 * @author Xceptance Software Technologies
 */
public class ProductDetailPage extends GeneralPages
{
	public static final ProductDetailPage instance = new ProductDetailPage();
	
    @Override
    public void validate()
    {
        super.validate();

        Assert.assertTrue("Product detail page validation failed", Page.find().byId("sidebar").exists());
        Assert.assertTrue("Product detail page validation failed", Page.find().byCss(".detail-block>div>h1.product-name").exists());
    }

    @Override
    public boolean is()
    {
    	return super.is() &&
    			Page.find().byId("sidebar").exists() &&
    			Page.find().byCss(".detail-block>div>h1.product-name").exists(); 
    }
    
    public boolean isProductAvailable()
    {
    	// NOTE
    	// This is not implemented for the posters demo shop. At the moment all products are always in stock.
    	// There could be some dedicated in stock and availability logic here which needs to be handled.
    	return true;
    }
    
    public List<HtmlElement> getVariationAttributes()
    {
    	// Returns all configurable variation attributes
    	return Page.find().byXPath("//div[@class= 'attribute']").all();
    }
    public HtmlElement GetPID()
    {
    	// Returns all configurable variation attributes
    	return Page.find().byXPath("//button[contains(text(),'Add to Bag')]").first();
    }
    public int Getcolourcount()
    {
    	// Returns all configurable variation attributes
    	 int count= Page.find().byCss(".color-attribute>span>.selectable").count();
    	
    	 return count;
    }
    public int getSizeElementCount()
    {
    	// Returns all configurable variation attributes
    	 int count= Page.find().byId("size-1").count();
    	 return count;
    }
    public HtmlElement GetColour()
    {
    	// Returns all configurable variation attributes
    	 HtmlElement element= Page.find().byCss(".color-attribute>span>.selectable").random();
    	
    	 return element;
    }
    public int GetNumberofNonOutofstock()
    {
    	// Returns all configurable variation attributes
    	 int count= Page.find().byXPath("(//select[@id='size-1']/option[  not( contains(@class, 'Out-Of-Stock'))])[position()>1]").count();
    	
    	 return count;
    }
    public HtmlElement Getoption()
    {
    	// Returns all configurable variation attributes
    	HtmlElement element= Page.find().byXPath("(//select[@id='size-1']/option[  not( contains(@class, 'Out-Of-Stock'))])[position()>1]").random();
    	
    	 return element;
    }
    
    public HtmlElement getAddToCartButton()
    {
    	// Returns add to cart button
    	return Page.find().byXPath("//button[@class='add-to-cart btn btn-primary']").asserted("Failed to find add to cart button for given product item").single();
    }
    
    public String getProductId()
    {
        String productId = Page.find().byXPath("//button[@class='add-to-cart btn btn-primary']").asserted("Expected add to cart button").single().getAttribute("data-pid");
        
        Assert.assertTrue("Expected valid productId", !StringUtils.isBlank(productId));
        
        return productId;
    }
    
    public String getSelectedSize()
    {
        String selectedSize = Page.find().byId("size-1").asserted("Expected size attribute").single().getAttribute("value");
        Assert.assertTrue("Expected valid size attribute that is selected", !StringUtils.isBlank(selectedSize));
        return selectedSize;
    }
    
}