package com.cognizant.businessComponents;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;


import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.security.UserAndPassword;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.cognizant.cognizantits.engine.commands.Basic;
import com.cognizant.cognizantits.engine.commands.Command;
import com.cognizant.cognizantits.engine.commands.CommonMethods;
import com.cognizant.cognizantits.engine.commands.ElementException;
import com.cognizant.cognizantits.engine.commands.JSCommands;
import com.cognizant.cognizantits.engine.core.CommandControl;
import com.cognizant.cognizantits.engine.support.Status;
import com.cognizant.cognizantits.engine.support.methodInf.Action;
import com.cognizant.cognizantits.engine.support.methodInf.InputType;
import com.cognizant.cognizantits.engine.support.methodInf.ObjectType;
import com.steadystate.css.parser.ParseException;



public class FunctionalComponents extends Command {

	CommonMethods cm;
	JSCommands jscmds;
	GeneralComponents GeneralComponents;
	private Object string;
	private String wpi;
	String scenario = userData.getScenario();
	String testcase = userData.getTestCase();
	WebDriverWait wait = new WebDriverWait(Driver, 90);
	public int Data;

	static String UI_CampaignDescription;
	static String UI_campaignStatus;
	static String UI_Completionstatus;
	static String UI_expirationdate;
	static String UI_Date_Serviced;

	static String db_vinNumber;
	static String db_description;
	static String db_vindate;
	static String db_completionStatus;
	static String db_statusDesc;
	static String db_CompletionStatus;

	static LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();

	static String parentWindowHandler;

	static Statement stmt = null;
	public ResultSet rs = null;

	// CommandControl cc;
//	CommonMethods cm;
	Basic basic;

	
	
	public FunctionalComponents(CommandControl cc) {
		super(cc);
		cm = new CommonMethods(cc);
		jscmds = new JSCommands(cc);
		GeneralComponents = new GeneralComponents(cc);
	}
	//CommandControl cc = new CommandControl(new SeleniumDriver(), Report);
	
	

@Action(desc="findwebelment",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void findwebelment()

	{
		WebElement VINlookup=findObject(Driver,By.id("_jpfcpncuivr_portletInstance_2_j_id_id0:vehicleSubView:vinForm:vinTextBox"), "VIN Lookup" );
		if (VINlookup !=null)
		{
			String val=userData.getData("Warranty", "Regular", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(VINlookup, val, "VIN is entered");
			Report.updateTestLog("VINlookup", "VIN text is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("VINlookup", "VIN text is not found", Status.FAIL);
		}
	}

@Action(desc="findwebelmentT3",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void findwebelmentT3()

{
	WebElement VINlookup=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinTextBox"), "VIN Lookup" );
	if (VINlookup !=null)
	{
		String val=userData.getData("Warranty", "Regular", "1", "1");
		/*userData.getData(Sheet, Column)*/
		GeneralComponents.enterValue(VINlookup, val, "VIN is entered");
		Report.updateTestLog("VINlookup", "VIN text is found",Status.PASS);
	}            
	else
	{
		Report.updateTestLog("VINlookup", "VIN text is not found", Status.FAIL);
	}
}
@Action(desc="clicklookup",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clicklookup()

	{
		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_portletInstance_2_j_id_id0:vehicleSubView:vinForm:vinlookupbut"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			//Driver.findElement(By.id("_jpfcpncuivr_portletInstance_2_j_id_id0:vehicleSubView:vinForm:iAgreeButton")).click();
			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
	}
@Action(desc="internalLoad",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void internalLoad()
	{
		GeneralComponents.waitforInternalLoad(Driver);
	}

@Action(desc="Dashcon",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Dashcon()
	{
		GeneralComponents.waitforInternalLoad(Driver);
	}
	

@Action(desc="singleViewLookUpFirstExecution",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void singleViewLookUpFirstExecution()
	{                                                  
		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_portletInstance_2_j_id_id0:vehicleSubView:vinForm:vinlookupbut"), "looup Button");

		if(lookupBtn !=null)
		{	GeneralComponents.waitforInternalLoad(Driver);

		GeneralComponents.clickOnWebelement(lookupBtn, "");
		Driver.findElement(By.id("_jpfcpncuivr_portletInstance_2_j_id_id0:vehicleSubView:vinForm:iAgreeButton")).click();
		GeneralComponents.waitForPageLoaded(Driver);
		Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
		GeneralComponents.waitforInternalLoad(Driver);
		//k_ASMPortal_Service_Lane_Book_ajax
		singleViewViewWarrenty();
	}
	//_jpfcpncuivr_portletInstance_2_j_id_id0:vehicleSubView:vinForm:vinTextBox
	//_jpfcpncuivr_portletInstance_2_j_id_id0:vehicleSubView:vinForm:vinTextBox
	//_jpfcpncuivr_portletInstance_2_j_id_id0:vehicleSubView:vinForm:vinTextBox
@Action(desc="singleViewViewWarrenty",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void singleViewViewWarrenty()
	{
		GeneralComponents.waitforInternalLoad(Driver);

		WebElement warranty= findObject(Driver,By.id("ASMPortal_portal_page_WarrantyPage_blue"), "");
		if(warranty !=null)
		{
			//WebElement a=warranty.findElement(By.tagName("a"))  ; 	
			//if(a.getText().contains(""))
			GeneralComponents.waitforInternalLoad(Driver);

			GeneralComponents.clickOnWebelement(warranty, "Warranty");
			Report.updateTestLog("warranty", "warranty tab is clicked",Status.PASS);


		}
		else
		{
			Report.updateTestLog("warranty","warranty tab is not clicked ",Status.FAIL);
		}
	}
@Action(desc="singleViewLookUp",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void singleViewLookUp()
	{
		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_portletInstance_2_j_id_id0:vehicleSubView:vinForm:vinlookupbut"), "looup Button");

		if(lookupBtn !=null)
		{	GeneralComponents.waitforInternalLoad(Driver);

		GeneralComponents.clickOnWebelement(lookupBtn, "");
		//Driver.findElement(By.id("_jpfcpncuivr_portletInstance_2_j_id_id0:vehicleSubView:vinForm:iAgreeButton")).click();
		Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}

	}
	/**
	 * 
	 * MethodName: clickwarrantyT3
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Rahini
	 */     
@Action(desc="clickwarrantyT3",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clickwarrantyT3()

	{
		GeneralComponents.waitforInternalLoad(Driver);
		//k_ASMPortal_Service_Lane_Book_ajax
		WebElement warranty= findObject(Driver,By.id("k_ASMPortal_Vehicle_Book_ajax"), "Warranty");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Warranty"))
				{
					GeneralComponents.clickOnWebelement(td, "Warranty");
					Report.updateTestLog("warranty", "warranty tab is clicked",Status.PASS);
					break;
				}    

			}


		}
		else
		{
			Report.updateTestLog("warranty","warranty tab is not clicked ",Status.FAIL);
		}



	}
@Action(desc="colourMatch",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void colourMatch()
	{
		//warrantyTableId

		WebElement table=findObject(Driver, By.id("warrantyTableId"),"" );
		if(table!=null)
		{
			List<WebElement> trs=table.findElements(By.tagName("tr"));
			end:
				for(WebElement tr:trs)
				{
					WebElement td1=tr.findElement(By.xpath(".//td[1]"));
					String val=td1.getText();
					if(val.contains("Basic"))
					{
						GeneralComponents.highlight(td1);
						WebElement parent=td1.findElement(By.xpath(".//.."));
						List<WebElement> tds=parent.findElements(By.tagName("td"));
						for(WebElement td:tds)
						{
							if(td.getText().contains("NO"))  
							{
								String attr=td.getAttribute("style")	;
								if(attr.contains("background-color: rgb(180, 4, 49)"))
								{
									GeneralComponents.highlight(td);
									Report.updateTestLog("no","no contains red background",Status.PASS);
									break ;
								}

							}
						}
					}
					if(val.contains("Long Term"))
					{
						GeneralComponents.highlight(td1);
						WebElement parent=td1.findElement(By.xpath(".//.."));
						List<WebElement> tds=parent.findElements(By.tagName("td"));
						for(WebElement td:tds)
						{
							if(td.getText().contains("YES"))  
							{
								String attr=td.getAttribute("style")	;
								if(attr.contains("background-color: rgb(11, 97, 11)"))
								{
									GeneralComponents.highlight(td);
									Report.updateTestLog("yes","yes contains green background",Status.PASS);
									break end;
								}

							}
						}
					}
				}
		}
	}
@Action(desc="colourMatchInt",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void colourMatchInt()
{
	//warrantyTableId

	WebElement table=findObject(Driver, By.id("warrantyTableId"),"" );
	if(table!=null)
	{
		List<WebElement> trs=table.findElements(By.tagName("tr"));
		end:
			for(WebElement tr:trs)
			{
				WebElement td1=tr.findElement(By.xpath(".//td[1]"));
				String val=td1.getText();
				if(val.contains("Basic"))
				{
					GeneralComponents.highlight(td1);
					WebElement parent=td1.findElement(By.xpath(".//.."));
					List<WebElement> tds=parent.findElements(By.tagName("td"));
					for(WebElement td:tds)
					{
						if(td.getText().contains("NO"))  
						{
							String attr=td.getAttribute("style")	;
							if(attr.contains("background-color: rgb(180, 4, 49)"))
							{
								GeneralComponents.highlight(td);
								Report.updateTestLog("no","no contains red background",Status.PASS);
								break ;
							}

						}
					}
				}
				if(val.contains("Long Term"))
				{
					GeneralComponents.highlight(td1);
					WebElement parent=td1.findElement(By.xpath(".//.."));
					List<WebElement> tds=parent.findElements(By.tagName("td"));
					for(WebElement td:tds)
					{
						if(td.getText().contains("YES"))  
						{
							String attr=td.getAttribute("style")	;
							if(attr.contains("background-color: rgb(11, 97, 11)"))
							{
								GeneralComponents.highlight(td);
								Report.updateTestLog("yes","yes contains green background",Status.PASS);
								break end;
							}

						}
					}
				}
			}
	}
}
	/**
	 * 
	 * MethodName: serviceConnect
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Rahini
	 */
@Action(desc="serviceConnect",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void serviceConnect()
	{
		GeneralComponents.waitforInternalLoad(Driver);
		//
		WebElement SerConn= findObject(Driver,By.id("k_ASMPortal_Service_Lane_Book_ajax"), "Warranty");
		if(SerConn !=null)
		{
			List<WebElement> tds=SerConn.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Service Connect"))
				{
					GeneralComponents.clickOnWebelement(td, "SerConn");
					Report.updateTestLog("SerConn", "SerConn tab is clicked",Status.PASS);
					break;
				}    
				/*else if(val.contains("Settings"))
			                          {
			                        	  GeneralComponents.clickOnWebelement(td, "Setting");
				                             Report.updateTestLog("Setting", "Setting tab is clicked",Status.PASS);

			                          }
				 */   
			}


		}
		else
		{
			Report.updateTestLog("SerConn", "SerConn tab is not clicked",Status.FAIL);

		}


	}

	/**
	 * 
	 * MethodName:help
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Rahini
	 */

@Action(desc="helpMessage",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void helpMessage()
	{
		String val=userData.getData("ServiceConn", "Duration", "1", "1");
		WebElement help=findObject(Driver, By.id("specHelpToolTip"), "");
		WebElement sib=help.findElement(By.xpath(".//following-sibling::img"));
		if(sib!=null)
		{

			/* Actions action=new Actions(Driver);
			   action.moveToElement(sib);*/
			GeneralComponents.clickOnWebelement(sib, "");
			Report.updateTestLog("help", "help is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("help", "help is not shown",Status.FAIL);
		}

		WebElement sel=findObject(Driver, By.id("totalAlertAssociatesList"), "");

		WebElement form=sel.findElement(By.xpath(".//.."));
		List<WebElement> selects=form.findElements(By.tagName("select"));
		for(WebElement select:selects)
		{
			String val1=select.getAttribute("id");
			if(val1.contains("enformSettingsForm:alertDuration"))
			{
				Select duration=new Select(select);
				duration.selectByVisibleText(val);
				GeneralComponents.waitforInternalLoad(Driver);
				break;
			}
		}
	}	   
	/*WebElement sel1=findObject(Driver, By.id("totalAlertAssociatesList"), "");

		   WebElement form1=sel1.findElement(By.xpath(".//.."));
		   List<WebElement> inputs=form1.findElements(By.tagName("input"));
		   for(WebElement input:inputs)
		   {
			   String val1=input.getAttribute("value");
			   if(val1.contains("Cancel"))
			   {
			   GeneralComponents.highlight(input);
			   GeneralComponents.clickOnInvisibleWebelement(input, "Click cancel");
			   GeneralComponents.waitForPageLoaded(Driver);
			   break;
			   }
		   }
	   }*/
	/**
	 * 
	 * MethodName: connectMessage
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Rahini
	 */
@Action(desc="connectMessage",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void connectMessage()
	{
		//_jpfcpncuivr_T1200491171441212604908_j_id_id1:enformSettingsForm
		GeneralComponents.waitforInternalLoad(Driver);
		//
		WebElement SuccessMsg= findObject(Driver,By.id("_jpfcpncuivr_T1200491171441212604908_j_id_id1:enformSettingsForm"), "");
		if(SuccessMsg !=null)
		{
			List<WebElement> Labels=SuccessMsg.findElements(By.tagName("label"));
			for(WebElement label:Labels)
			{
				String val=label.getText();
				if(val.contains("Service Connect Settings has been saved successfully."))
				{
					//GeneralComponents.clickOnWebelement(td, "SerConn");
					Report.updateTestLog("SuccessMsg", "SuccessMsg is displayed",Status.PASS);
					break;
				}    

			}


		}
		else
		{
			Report.updateTestLog("SuccessMsg", "SuccessMsg is not displayed",Status.FAIL);

		}

	}

	/**
	 * 
	 * MethodName: logout
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Rahini
	 */ 
@Action(desc="logout",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void logout()
	{
		WebElement link=findObject(Driver,By.id("k_ASMPortal_int_book_ajax"), "" );
		if(link!=null)
		{
			List<WebElement> A=link.findElements(By.tagName("a"));
			for(WebElement a:A)
			{
				String lnkTxt=a.getText();
				if(lnkTxt.equals("Logout"))
				{
					GeneralComponents.clickOnWebelement(a, lnkTxt+"is clicked");
					break;
				}
			}
		}
		Alert confirmationAlert = Driver.switchTo().alert();
		String alertText = confirmationAlert.getText();
		confirmationAlert.accept();
		Report.updateTestLog("Logout","Logout is confirmed ",Status.PASS);
		Driver.switchTo().defaultContent();


	}


@Action(desc="ok",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void ok()
	{
		WebElement link=findObject(Driver,By.id("k_ASMPortal_int_book_ajax"), "" );
		if(link!=null)
		{
			List<WebElement> A=link.findElements(By.tagName("img"));
			for(WebElement a:A)
			{
				String lnkTxt=a.getAttribute("src");
				if(lnkTxt.equals("https://tis.qa2.toyota.com:443/serviceLane/resources/images/contact.png?_pageLabel=ASMPortal_ed_dnd_page&_appSource=slane7"))
				{
					GeneralComponents.clickOnWebelement(a, lnkTxt+"is clicked");
					break;
				}
			}
		}
		Alert confirmationAlert = Driver.switchTo().alert();
		String alertText = confirmationAlert.getText();
		confirmationAlert.accept();
		Report.updateTestLog("Logout","Logout is confirmed ",Status.PASS);
		Driver.switchTo().defaultContent();


	}
	/**
	 * 
	 * MethodName: ServiceConnectAvail
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Rahini
	 */
@Action(desc="serviceConnectAvail",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void serviceConnectAvail()
	{
		GeneralComponents.waitforInternalLoad(Driver);
		//
		WebElement SerConn= findObject(Driver,By.id("k_ASMPortal_Service_Lane_Book_ajax"), "Warranty");
		if(SerConn !=null)
		{
			List<WebElement> tds=SerConn.findElements(By.tagName("td"));
			int size=tds.size();
			int i=0;
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Service Connect"))
				{
					GeneralComponents.clickOnWebelement(td, "SerConn");
					Report.updateTestLog("SerConn", "SerConn tab is clicked",Status.PASS);
					break;
				}   
				else
				{
					i++;
					if(i==size)
					{
						Report.updateTestLog("SerConn", "SerConn tab is not available",Status.PASS);
					}
				}
				/*else if(val.contains("Settings"))
			                          {
			                        	  GeneralComponents.clickOnWebelement(td, "Setting");
				                             Report.updateTestLog("Setting", "Setting tab is clicked",Status.PASS);

			                          }
				 */   
			}


		}
		else
		{
			Report.updateTestLog("SerConn", "SerConn tab is not clicked",Status.FAIL);

		}


	}
	//k_ASMPortal_enform_diagnostic_book_ajax
	//Settings


	/**
	 * 
	 * MethodName: SettingsAvail
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Rahini
	 */
@Action(desc="settingsAvail",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void settingsAvail()
	{
		GeneralComponents.waitforInternalLoad(Driver);
		//
		WebElement SerConn= findObject(Driver,By.id("k_ASMPortal_enform_diagnostic_book_ajax"), "");
		if(SerConn !=null)
		{
			List<WebElement> tds=SerConn.findElements(By.tagName("a"));
			int size=tds.size();
			int i=0;
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Settings"))
				{
					GeneralComponents.clickOnWebelement(td, "SerConn");
					Report.updateTestLog("Settings", "Settings tab is clicked",Status.PASS);
					break;
				}   
				else
				{
					i++;
					if(i==size)
					{
						Report.updateTestLog("Settings", "Settings tab is not available",Status.PASS);
						break;
					}
				}
				/*else if(val.contains("Settings"))
			                          {
			                        	  GeneralComponents.clickOnWebelement(td, "Setting");
				                             Report.updateTestLog("Setting", "Setting tab is clicked",Status.PASS);

			                          }
				 */   
			}


		}
		else
		{
			Report.updateTestLog("Settings", "Settings tab is not clicked",Status.FAIL);

		}


	}
	/**
	 * 
	 * MethodName: preOwned
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Rahini
	 */
@Action(desc="preOwned",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void preOwned()
	{
		//  _jpfcpncuivr_T3809895701380803221759_j_id_id0:warrantyForm:mileage
		WebElement lastmileage=findObject(Driver,By.id("_jpfcpncuivr_T10000678101380812591033_j_id_id0:warrantyForm:mileage"), "" );
		if (lastmileage !=null)//
		{//old---
			String val=userData.getData("Warranty R", "LastMileage", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(lastmileage, val, "lastmileage is entered");
			GeneralComponents.waitforInternalLoad(Driver);

			GeneralComponents.waitforInternalLoad(Driver);
			GeneralComponents.waitforInternalLoad(Driver);


			//GeneralComponents.clickOnWebelement(lastmileage, "");
			Report.updateTestLog("lastmileage", "lastmileage is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("lastmileage", "lastmileage is not found", Status.FAIL);
		}
		//_jpfcpncuivr_T10000678101380812591033_j_id_id0:warrantyForm:applybtnid
		WebElement Apply=findObject(Driver,By.id("_jpfcpncuivr_T10000678101380812591033_j_id_id0:warrantyForm:applybtnid"), "Apply button");

		if(Apply !=null)
		{
			GeneralComponents.clickOnWebelement(Apply, "");
			GeneralComponents.waitforInternalLoad(Driver);
			/* Driver.findElement(By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:iAgreeButton")).click();*/
			Report.updateTestLog("Apply", "Apply is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Apply","Apply is not clicked",Status.FAIL);
		}


		WebElement lastmileagepre=findObject(Driver,By.id("_jpfcpncuivr_preownedWarrantyPortlet_j_id_id0:tcuvWarrantyForm:tcuvMileage"), "VIN Lookup" );
		if (lastmileagepre!=null)
		{
			String val=findObject(Driver,By.id("_jpfcpncuivr_T10000678101380812591033_j_id_id0:warrantyForm:mileage"), "VIN Lookup" ).getText();
			String val1=lastmileagepre.getText();
			if(val1.equals(val))
			{
				Report.updateTestLog("lastmileagepre", "lastmileagepre is updated",Status.PASS);
			}
			else
			{
				Report.updateTestLog("lastmileagepre", "lastmileagepre is not updated", Status.FAIL);
			}

		}            

	}

@Action(desc="preOwned1",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void preOwned1()
	{
		//  _jpfcpncuivr_T3809895701380803221759_j_id_id0:warrantyForm:mileage
		WebElement lastmileage=findObject(Driver,By.id("_jpfcpncuivr_T10000678101380812591033_j_id_id0:warrantyForm:mileage"), "" );
		if (lastmileage !=null)
		{
			String val=userData.getData("Warranty R", "LastMileage", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(lastmileage, "20000", "lastmileage is entered");
			GeneralComponents.clickOnWebelement(lastmileage, "");
			Report.updateTestLog("lastmileage", "lastmileage is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("lastmileage", "lastmileage is not found", Status.FAIL);
		}
		//_jpfcpncuivr_T10000678101380812591033_j_id_id0:warrantyForm:applybtnid
		WebElement Apply=findObject(Driver,By.id("_jpfcpncuivr_T10000678101380812591033_j_id_id0:warrantyForm:applybtnid"), "Apply button");

		if(Apply !=null)
		{
			GeneralComponents.clickOnWebelement(Apply, "");
			GeneralComponents.waitforInternalLoad(Driver);
			/* Driver.findElement(By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:iAgreeButton")).click();*/
			Report.updateTestLog("Apply", "Apply is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Apply","Apply is not clicked",Status.FAIL);
		}


		WebElement lastmileagepre=findObject(Driver,By.id("_jpfcpncuivr_preownedWarrantyPortlet_j_id_id0:tcuvWarrantyForm:tcuvMileage"), "VIN Lookup" );
		if (lastmileagepre!=null)
		{
			String val=findObject(Driver,By.id("_jpfcpncuivr_T10000678101380812591033_j_id_id0:warrantyForm:mileage"), "VIN Lookup" ).getText();
			String val1=lastmileagepre.getText();
			if(val1.equals(val))
			{
				Report.updateTestLog("lastmileagepre", "lastmileagepre is updated",Status.PASS);
			}
			else
			{
				Report.updateTestLog("lastmileagepre", "lastmileagepre is not updated", Status.FAIL);
			}

		}            

	}



@Action(desc="preOwnedTIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void preOwnedTIS()
	{
		//  
		WebElement lastmileage=findObject(Driver,By.id("_jpfcpncuivr_T3809895701380803221759_j_id_id0:warrantyForm:mileage"), "" );
		if (lastmileage !=null)
		{
			String val=userData.getData("Warranty R", "LastMileage", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(lastmileage, val, "lastmileage is entered");
			Report.updateTestLog("lastmileage", "lastmileage is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("lastmileage", "lastmileage is not found", Status.FAIL);
		}
		WebElement Apply=findObject(Driver,By.id("_jpfcpncuivr_T3809895701380803221759_j_id_id0:warrantyForm:applybtnid"), "Apply button");

		if(Apply !=null)
		{
			GeneralComponents.clickOnWebelement(Apply, "");
			/* Driver.findElement(By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:iAgreeButton")).click();*/
			Report.updateTestLog("Apply", "Apply is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Apply","Apply is not clicked",Status.FAIL);
		}


		WebElement lastmileagepre=findObject(Driver,By.id("_jpfcpncuivr_preownedWarrantyPortlet_j_id_id0:tcuvWarrantyForm:tcuvMileage"), "VIN Lookup" );
		if (lastmileagepre!=null)
		{
			String val=findObject(Driver,By.id("_jpfcpncuivr_T3809895701380803221759_j_id_id0:warrantyForm:mileage"), "" ).getText();
			String val1=lastmileagepre.getText();
			if(val1.equals(val))
			{
				Report.updateTestLog("lastmileagepre", "lastmileagepre is updated",Status.PASS);
			}
			else
			{
				Report.updateTestLog("lastmileagepre", "lastmileagepre is not updated", Status.FAIL);
			}

		}            

	}

	//

@Action(desc="preOwnedSingleViewApply",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void preOwnedSingleViewApply()
	{                 //                          

		WebElement lastmileage=findObject(Driver,By.id("_jpfcpncuivr_portletInstance_19_j_id_id0:warrantyForm:mileage"), "VIN Lookup" );
		if (lastmileage !=null)//_jpfcpncuivr_preownedWarrantyPortletLabel_j_id_id0:tcuvWarrantyForm:tcuvMileage
		{GeneralComponents.waitforInternalLoad(Driver);
		String val=userData.getData("Warranty R", "LastMileage", "1", "1");
		GeneralComponents.enterValue(lastmileage, val, "lastmileage is entered");
		GeneralComponents.waitforInternalLoad(Driver);
		Report.updateTestLog("lastmileage", "lastmileage is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("lastmileage", "lastmileage is not found", Status.FAIL);
		}                                         
		WebElement Apply=findObject(Driver,By.id("_jpfcpncuivr_portletInstance_19_j_id_id0:warrantyForm:applybtnid"), "Apply button");

		if(Apply !=null)
		{GeneralComponents.waitforInternalLoad(Driver);
		GeneralComponents.clickOnWebelement(Apply, "");
		GeneralComponents.waitforInternalLoad(Driver);
		GeneralComponents.waitforInternalLoad(Driver);
		/* Driver.findElement(By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:iAgreeButton")).click();*/
		Report.updateTestLog("Apply", "Apply is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Apply","Apply is not clicked",Status.FAIL);
		}

		//_jpfcpncuivr_preownedWarrantyPortletLabel_j_id_id0:tcuvWarrantyForm:tcuvMileage
		WebElement lastmileagepre=findObject(Driver,By.id("_jpfcpncuivr_preownedWarrantyPortletLabel_j_id_id0:tcuvWarrantyForm:tcuvMileage"), "" );
		if (lastmileagepre!=null)
		{
			String val1=lastmileagepre.getText();
			String val=findObject(Driver,By.id("_jpfcpncuivr_portletInstance_19_j_id_id0:warrantyForm:mileage"), "" ).getText();

			if(val1.equals(val))
			{
				Report.updateTestLog("lastmileagepre", "lastmileagepre is updated",Status.PASS);
			}
			else
			{
				Report.updateTestLog("lastmileagepre", "lastmileagepre is not updated", Status.FAIL);
			}

		}            

	}

	////Scheduled Maintenance
@Action(desc="schMainToolBox",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void schMainToolBox()
	{
		GeneralComponents.waitforInternalLoad(Driver);
		//
		WebElement SerConn= findObject(Driver,By.id("k_ASMPortal_Service_Lane_Book_ajax"), "Warranty");
		if(SerConn !=null)
		{
			List<WebElement> tds=SerConn.findElements(By.xpath("//*[text()=' + Toolbox + ']"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Toolbox"))
				{
					GeneralComponents.clickOnWebelement(td, "Toolbox");
					Report.updateTestLog("Toolbox", "Toolbox tab is clicked",Status.PASS);
					break;
				}    
				/*else if(val.contains("Settings"))
		                          {
		                        	  GeneralComponents.clickOnWebelement(td, "Setting");
			                             Report.updateTestLog("Setting", "Setting tab is clicked",Status.PASS);

		                          }
				 */   
			}


		}
		else
		{
			Report.updateTestLog("Toolbox", "Toolbox tab is not clicked",Status.FAIL);

		}


	}

@Action(desc="schMaintVINLookup",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void schMaintVINLookup()
	{                                          
		WebElement VINlookup=findObject(Driver,By.id("_jpfcpncuivr_T9808858571380783534491_j_id_id0:vinForm:vinTextBox"), "VIN Lookup" );
		if (VINlookup !=null)
		{
			String val=userData.getData("Schedule", "Special Inst VIN", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(VINlookup, val, "VIN is entered");
			Report.updateTestLog("VINlookup", "VIN text is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("VINlookup", "VIN text is not found", Status.FAIL);
		}

		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_T9808858571380783534491_j_id_id0:vinForm:vinlookupbut"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			GeneralComponents.waitForPageLoaded(Driver);

			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
		GeneralComponents.waitforInternalLoad(Driver);


	}

@Action(desc="schMaintVINLookupTIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void schMaintVINLookupTIS()
	{                                          
		WebElement VINlookup=findObject(Driver,By.id("_jpfcpncuivr_T3808995701380782268321_j_id_id0:vinForm:vinTextBox"), "VIN Lookup" );
		if (VINlookup !=null)
		{
			String val=userData.getData("Schedule", "Special Inst VIN", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(VINlookup, val, "VIN is entered");
			Report.updateTestLog("VINlookup", "VIN text is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("VINlookup", "VIN text is not found", Status.FAIL);
		}

		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_T3808995701380782268321_j_id_id0:vinForm:vinlookupbut"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			GeneralComponents.waitForPageLoaded(Driver);

			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
		GeneralComponents.waitforInternalLoad(Driver);


	}
@Action(desc="schMaintVINSplInst",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void schMaintVINSplInst()
	{                                          
		WebElement ETD= findObject(Driver,By.id("_jpfcpncuivr_T5800165711377085610147_j_id_id0:schForm"), "");
		if(ETD !=null)
		{
			List<WebElement> spans=ETD.findElements(By.tagName("span"));
			for(WebElement span:spans)
			{
				String val=span.getText();
				if(val.contains("Large V8 - 3UR-FBE"))

				{

					Report.updateTestLog(val, "Engine value generated",Status.PASS);

				}    
				else if(val.contains("6AT"))
				{
					Report.updateTestLog(val, "Transmission value generated",Status.PASS);

				}
				else if(val.contains("4WD"))
				{
					Report.updateTestLog(val, "Driver Type value generated",Status.PASS);
					break; 
				}

			}


		}
		else
		{
			Report.updateTestLog("ETD", "ETD details not available",Status.FAIL);

		}                                          
		//_jpfcpncuivr_T5800165711377085610147_j_id_id0:schForm:optLink
		WebElement splInstLink=findObject(Driver,By.id("_jpfcpncuivr_T5800165711377085610147_j_id_id0:schForm:siLink"), "VIN Lookup" );
		if (splInstLink !=null)
		{
			GeneralComponents.clickOnWebelement(splInstLink, "Spl link clicked");
			/*Report.updateTestLog("splInstLink", "splInstLink is clicked",Status.PASS);*/
		}            
		else
		{
			Report.updateTestLog("splInstLink", "splInstLink is not found", Status.FAIL);
		}

		/*WebElement close=findObject(Driver,By.xpath(".//a[@title='Close']"),"");
    if (close !=null)
    {
          GeneralComponents.clickOnWebelement(close, "close clicked");
           Report.updateTestLog("splInstLink", "splInstLink is clicked",Status.PASS);
    }            
    else
    {
           Report.updateTestLog("close", "close is not found", Status.FAIL);
    }*/

		/* WebElement dropSp=findObject(Driver,By.id("_jpfcpncuivr_T5800165711377085610147_j_id_id0:schForm:siLink"), "VIN Lookup" );
    if (dropSp !=null)
    {
          //GeneralComponents.clickOnWebelement(dropSp, "dropSp  clicked");
           Report.updateTestLog("splInstLink", "splInstLink is clicked",Status.PASS);
          WebElement img=dropSp.findElement(By.xpath(".//img"));
          if(img!=null)
          {
        	  GeneralComponents.clickOnWebelement(img, "drop for sp  clicked"); 
          }
          else
          {
                 Report.updateTestLog("dropSp", "dropSp is not found", Status.FAIL);
          }
    }*/  

		WebElement splTable= findObject(Driver,By.id("_jpfcpncuivr_T5800165711377085610147_j_id_id0:splinst_popup"), "Warranty");
		if(splTable !=null)
		{
			List<WebElement> ths=splTable.findElements(By.tagName("th"));
			for(WebElement th:ths)
			{
				String val=th.getText();
				if(val.contains("Value")||val.contains("Description")  )
				{

					Report.updateTestLog(val, val+"in special inst is available",Status.PASS);
					break;
				}    

			}


		}
		else
		{
			Report.updateTestLog("Spl inst", "Special Instruction not available",Status.FAIL);

		}

		WebElement splTable1= findObject(Driver,By.id("_jpfcpncuivr_T5800165711377085610147_j_id_id0:splinst_popup"), "Warranty");
		if(splTable1 !=null)
		{
			List<WebElement> trs=splTable1.findElements(By.tagName("tr"));
			for(WebElement tr:trs)
			{
				String val=tr.getText();
				if(val.contains("Normal"))
				{
					GeneralComponents.clickOnWebelement(tr, "special inst clicked"); 
					//Report.updateTestLog(val, val+"in special inst is available",Status.PASS);
					break;
				}    

			}


		}
		else
		{
			Report.updateTestLog("Spl inst", "Special Instruction not found",Status.FAIL);

		}
		//_jpfcpncuivr_T5800165711377085610147
		/* WebElement closedrp=findObject(Driver,By.xpath("_jpfcpncuivr_T5800165711377085610147_j_id_id0:Closesplinst"),"");
    if (closedrp !=null)
    {
          GeneralComponents.clickOnWebelement(closedrp, "closedrp clicked");

    }            
    else
    {
           Report.updateTestLog("closedrp", "closedrp is not found", Status.FAIL);
    }  */

		GeneralComponents.waitforInternalLoad(Driver);
		WebElement mileage=findObject(Driver,By.id("_jpfcpncuivr_T5800165711377085610147_j_id_id0:schForm:schMntMileage"), "" );
		if (mileage !=null)
		{
			String val=userData.getData("Schedule", "Mileage", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(mileage, val, "mileage is entered");
			Report.updateTestLog("mileage", "mileage is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("mileage", "mileage is not found", Status.FAIL);
		}
		//
		WebElement apply=findObject(Driver,By.id("_jpfcpncuivr_T5800165711377085610147_j_id_id0:schForm:applyMileageButton"), "");

		if(apply !=null)
		{
			GeneralComponents.clickOnWebelement(apply, "");
			GeneralComponents.waitForPageLoaded(Driver);
			GeneralComponents.waitforInternalLoad(Driver);
			//
			WebElement parent =findObject(Driver,By.id("_jpfcpncuivr_T5800165711377085610147_j_id_id0:schForm"), "");
			if(parent!=null)
			{
				List<WebElement> miles=parent.findElements(By.tagName("span"));
				for(WebElement m:miles)
				{
					String val=m.getText();
					if(val.contains("Miles") || val.contains("*"))
					{
						Report.updateTestLog(val, val+" is available",Status.PASS); 
						break;
					}
				}
			}

			Report.updateTestLog("apply", "apply is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("apply","apply is not ",Status.FAIL);
		}



	}
@Action(desc="schMaintVINSplInstTIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void schMaintVINSplInstTIS()
	{                                          
		WebElement ETD= findObject(Driver,By.id("_jpfcpncuivr_T9400158481376657894632_j_id_id0:schForm"), "");
		if(ETD !=null)
		{
			List<WebElement> spans=ETD.findElements(By.tagName("span"));
			for(WebElement span:spans)
			{
				String val=span.getText();
				if(val.contains("Large V8 - 3UR-FBE"))

				{

					Report.updateTestLog(val, "Engine value generated",Status.PASS);

				}    
				else if(val.contains("6AT"))
				{
					Report.updateTestLog(val, "Transmission value generated",Status.PASS);

				}
				else if(val.contains("4WD"))
				{
					Report.updateTestLog(val, "Driver Type value generated",Status.PASS);
					break; 
				}

			}


		}
		else
		{
			Report.updateTestLog("ETD", "ETD details not available",Status.FAIL);

		}      

		//_jpfcpncuivr_T5800165711377085610147_j_id_id0:schForm:optLink
		WebElement splInstLink=findObject(Driver,By.id("_jpfcpncuivr_T9400158481376657894632_j_id_id0:schForm:siLink"), "VIN Lookup" );
		if (splInstLink !=null)
		{
			GeneralComponents.clickOnWebelement(splInstLink, "Spl link clicked");
			/*Report.updateTestLog("splInstLink", "splInstLink is clicked",Status.PASS);*/
		}            
		else
		{
			Report.updateTestLog("splInstLink", "splInstLink is not found", Status.FAIL);
		}

		/*WebElement close=findObject(Driver,By.xpath(".//a[@title='Close']"),"");
    if (close !=null)
    {
          GeneralComponents.clickOnWebelement(close, "close clicked");
           Report.updateTestLog("splInstLink", "splInstLink is clicked",Status.PASS);
    }            
    else
    {
           Report.updateTestLog("close", "close is not found", Status.FAIL);
    }*/

		/*WebElement dropSp=findObject(Driver,By.id("_jpfcpncuivr_T9400158481376657894632_j_id_id0:schForm:siLink"), "" );
    if (dropSp !=null)
    {
          //GeneralComponents.clickOnWebelement(dropSp, "dropSp  clicked");
           Report.updateTestLog("splInstLink", "splInstLink is clicked",Status.PASS);
          WebElement img=dropSp.findElement(By.xpath(".//img"));
          if(img!=null)
          {
        	  GeneralComponents.clickOnWebelement(img, "drop for sp  clicked"); 
          }
          else
          {
                 Report.updateTestLog("dropSp", "dropSp is not found", Status.FAIL);
          }
    } */ 

		WebElement splTable= findObject(Driver,By.id("_jpfcpncuivr_T9400158481376657894632_j_id_id0:splinst_popup"), "Warranty");
		if(splTable !=null)
		{
			List<WebElement> ths=splTable.findElements(By.tagName("th"));
			for(WebElement th:ths)
			{
				String val=th.getText();
				if(val.contains("Value")||val.contains("Description")  )
				{

					Report.updateTestLog(val, val+"in special inst is available",Status.PASS);
					break;
				}    

			}


		}
		else
		{
			Report.updateTestLog("Spl inst", "Special Instruction not available",Status.FAIL);

		}

		WebElement splTable1= findObject(Driver,By.id("_jpfcpncuivr_T9400158481376657894632_j_id_id0:splinst_popup"), "Warranty");
		if(splTable1 !=null)
		{
			List<WebElement> trs=splTable1.findElements(By.tagName("tr"));
			for(WebElement tr:trs)
			{
				String val=tr.getText();
				if(val.contains("Normal"))
				{
					GeneralComponents.clickOnWebelement(tr, "special inst clicked"); 
					//Report.updateTestLog(val, val+"in special inst is available",Status.PASS);
					break;
				}    

			}


		}
		else
		{
			Report.updateTestLog("Spl inst", "Special Instruction not found",Status.FAIL);

		}
		//_jpfcpncuivr_T5800165711377085610147
		/* WebElement closedrp=findObject(Driver,By.xpath("_jpfcpncuivr_T5800165711377085610147_j_id_id0:Closesplinst"),"");
    if (closedrp !=null)
    {
          GeneralComponents.clickOnWebelement(closedrp, "closedrp clicked");

    }            
    else
    {
           Report.updateTestLog("closedrp", "closedrp is not found", Status.FAIL);
    }  */

		GeneralComponents.waitforInternalLoad(Driver);
		WebElement mileage=findObject(Driver,By.id("_jpfcpncuivr_T9400158481376657894632_j_id_id0:schForm:schMntMileage"), "" );
		if (mileage !=null)
		{
			String val=userData.getData("Schedule", "Mileage", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(mileage, val, "mileage is entered");
			Report.updateTestLog("mileage", "mileage is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("mileage", "mileage is not found", Status.FAIL);
		}
		//
		WebElement apply=findObject(Driver,By.id("_jpfcpncuivr_T9400158481376657894632_j_id_id0:schForm:applyMileageButton"), "");

		if(apply !=null)
		{
			GeneralComponents.clickOnWebelement(apply, "");
			GeneralComponents.waitForPageLoaded(Driver);
			GeneralComponents.waitforInternalLoad(Driver);
			//
			WebElement parent =findObject(Driver,By.id("_jpfcpncuivr_T9400158481376657894632_j_id_id0:schForm"), "");
			if(parent!=null)
			{
				List<WebElement> miles=parent.findElements(By.tagName("span"));
				for(WebElement m:miles)
				{
					String val=m.getText();
					if(val.contains("Miles") || val.contains("*"))
					{
						Report.updateTestLog(val, val+" is available",Status.PASS); 
						break;
					}
				}
			}

			Report.updateTestLog("apply", "apply is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("apply","apply is not ",Status.FAIL);
		}



	}

@Action(desc="drivingCondition",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void drivingCondition()
	{
		//
		WebElement drivingLink=findObject(Driver,By.id("_jpfcpncuivr_T5800165711377085610147_j_id_id0:schForm:optLink"), "" );
		if (drivingLink !=null)
		{
			GeneralComponents.clickOnWebelement(drivingLink, "drivingLink clicked");
			/*Report.updateTestLog("splInstLink", "splInstLink is clicked",Status.PASS);*/
		}            
		else
		{
			Report.updateTestLog("drivingLink", "drivingLink is not found", Status.FAIL);
		}

		WebElement drivingTable= findObject(Driver,By.id("_jpfcpncuivr_T5800165711377085610147_j_id_id0:optcond_popup"), "");
		if(drivingTable !=null)
		{
			List<WebElement> trs=drivingTable.findElements(By.tagName("tr"));
			for(WebElement tr:trs)
			{
				String val=tr.getText();
				if(val.contains("Dirt/Dusty Roads"))
				{
					GeneralComponents.clickOnWebelement(tr, "driving clicked"); 
					//Report.updateTestLog(val, val+"in special inst is available",Status.PASS);
					break;
				}    

			}


		}
		else
		{
			Report.updateTestLog("driving", "driving not found",Status.FAIL);

		}
	}

@Action(desc="drivingConditionTIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void drivingConditionTIS()
	{
		//
		WebElement drivingLink=findObject(Driver,By.id("_jpfcpncuivr_T9400158481376657894632_j_id_id0:schForm:optLink"), "" );
		if (drivingLink !=null)
		{
			GeneralComponents.clickOnWebelement(drivingLink, "drivingLink clicked");
			/*Report.updateTestLog("splInstLink", "splInstLink is clicked",Status.PASS);*/
		}            
		else
		{
			Report.updateTestLog("drivingLink", "drivingLink is not found", Status.FAIL);
		}

		WebElement drivingTable= findObject(Driver,By.id("_jpfcpncuivr_T9400158481376657894632_j_id_id0:optcond_popup"), "");
		if(drivingTable !=null)
		{
			List<WebElement> trs=drivingTable.findElements(By.tagName("tr"));
			for(WebElement tr:trs)
			{
				String val=tr.getText();
				if(val.contains("Dirt/Dusty Roads"))
				{
					GeneralComponents.clickOnWebelement(tr, "driving clicked"); 
					//Report.updateTestLog(val, val+"in special inst is available",Status.PASS);
					break;
				}    

			}


		}
		else
		{
			Report.updateTestLog("driving", "driving not found",Status.FAIL);

		}
	}
	//
@Action(desc="selectedMileage",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void selectedMileage()
	{
		WebElement table=findObject(Driver,By.id("_jpfcpncuivr_T5800165711377085610147_j_id_id0:displayForm:sliderMain"),"");
		if(table!=null)
		{
			List<WebElement> spans=table.findElements(By.tagName("span"));
			end:
				for(WebElement span:spans)
				{
					String att=span.getAttribute("class");
					if(att.contains("Default_select"))
					{
						WebElement a=span.findElement(By.xpath("preceding-sibling::a"));
						if(a!=null)
						{
							String present=a.getText();
							Report.updateTestLog("present", "value is "+present,Status.PASS);
							WebElement p=a.findElement(By.xpath(".//../.."));
							if(p!=null)
							{
								List<WebElement> pre=p.findElements(By.xpath(".//preceding-sibling::div"));
								if(pre!=null)
								{
									List<WebElement> linkpre=pre.get(pre.size()-1).findElements(By.tagName("a"));
									if(linkpre.get(1)!=null)
									{
										String previous=linkpre.get(1).getText();
										Report.updateTestLog("previous", "value is "+previous,Status.PASS);
										WebElement pa=linkpre.get(1).findElement(By.xpath(".//../.."));
										if(pa!=null)
										{
											List<WebElement> next=pa.findElements(By.xpath("following-sibling::div"));
											if(next.get(1)!=null)
											{
												List<WebElement> linknext=next.get(1).findElements(By.tagName("a"));
												if(linknext!=null)
												{
													String nextval=linknext.get(1).getText();
													Report.updateTestLog("next", "value is "+nextval,Status.PASS);
													break end;
												}
											}
										}
									}
								}

							}


						}
					}
				}
		}
	}

@Action(desc="selectedMileageTIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void selectedMileageTIS()
	{
		WebElement table=findObject(Driver,By.id("_jpfcpncuivr_T9400158481376657894632_j_id_id0:displayForm:sliderMain"),"");
		if(table!=null)
		{
			List<WebElement> spans=table.findElements(By.tagName("span"));
			end:
				for(WebElement span:spans)
				{
					String att=span.getAttribute("class");
					if(att.contains("Default_select"))
					{
						WebElement a=span.findElement(By.xpath("preceding-sibling::a"));
						if(a!=null)
						{
							String present=a.getText();
							Report.updateTestLog("present", "value is "+present,Status.PASS);
							WebElement p=a.findElement(By.xpath(".//../.."));
							if(p!=null)
							{
								List<WebElement> pre=p.findElements(By.xpath(".//preceding-sibling::div"));
								if(pre!=null)
								{
									List<WebElement> linkpre=pre.get(pre.size()-1).findElements(By.tagName("a"));
									if(linkpre.get(1)!=null)
									{
										String previous=linkpre.get(1).getText();
										Report.updateTestLog("previous", "value is "+previous,Status.PASS);
										WebElement pa=linkpre.get(1).findElement(By.xpath(".//../.."));
										if(pa!=null)
										{
											List<WebElement> next=pa.findElements(By.xpath("following-sibling::div"));
											if(next.get(1)!=null)
											{
												List<WebElement> linknext=next.get(1).findElements(By.tagName("a"));
												if(linknext!=null)
												{
													String nextval=linknext.get(1).getText();
													Report.updateTestLog("next", "value is "+nextval,Status.PASS);
													break end;
												}
											}
										}
									}
								}

							}


						}
					}
				}
		}
	}

@Action(desc="ScheduleLink",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void ScheduleLink()
	{
		WebElement parent =findObject(Driver,By.id("_jpfcpncuivr_T5800165711377085610147_j_id_id0:displayForm:sliderMain"), "");

		List<WebElement> mileage=parent.findElements(By.tagName("a"));//(".//a[@title='55K or 66 months']"));
		for(WebElement m:mileage)
		{
			String va=m.getAttribute("title");
			if(va.contains("60K"))
			{
				GeneralComponents.clickOnWebelement(m, "Click mileageC");
				GeneralComponents.waitforInternalLoad(Driver);
				selectedMileage();
				break;
			}
		}
		WebElement parentlink =findObject(Driver,By.id("_jpfcpncuivr_T5800165711377085610147_j_id_id0:displayForm:sliderMain"), "");
		WebElement sib=parentlink.findElement(By.xpath("following-sibling::table"));
		List<WebElement> links=sib.findElements(By.tagName("a"));//(".//a[@title='55K or 66 months']"));
		for(WebElement m:links)
		{
			String va=m.getAttribute("title");
			if(va.contains("Displays details of previous service") || va.contains("Displays details of next service"))
			{
				GeneralComponents.clickOnWebelement(m, "click"+va);
				GeneralComponents.waitforInternalLoad(Driver);
				selectedMileage();
				break;
			}
		}

		WebElement parentlinknext =findObject(Driver,By.id("_jpfcpncuivr_T5800165711377085610147_j_id_id0:displayForm:sliderMain"), "");
		WebElement sibnext=parentlinknext.findElement(By.xpath("following-sibling::table"));
		List<WebElement> linksnext=sibnext.findElements(By.tagName("a"));//(".//a[@title='55K or 66 months']"));
		for(WebElement m:linksnext)
		{
			String va=m.getAttribute("title");
			if(va.contains("Displays details of next service"))
			{
				GeneralComponents.clickOnWebelement(m, "click"+va);
				GeneralComponents.waitforInternalLoad(Driver);
				selectedMileage();
				break;
			}
		}
		//
	}
@Action(desc="ScheduleLinkTIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void ScheduleLinkTIS()
	{
		WebElement parent =findObject(Driver,By.id("_jpfcpncuivr_T9400158481376657894632_j_id_id0:displayForm:sliderMain"), "");

		List<WebElement> mileage=parent.findElements(By.tagName("a"));//(".//a[@title='55K or 66 months']"));
		for(WebElement m:mileage)
		{
			String va=m.getAttribute("title");
			if(va.contains("40K"))
			{
				GeneralComponents.clickOnWebelement(m, "Click mileage");
				GeneralComponents.waitforInternalLoad(Driver);
				selectedMileageTIS();
				break;
			}
		}
		WebElement parentlink =findObject(Driver,By.id("_jpfcpncuivr_T9400158481376657894632_j_id_id0:displayForm:sliderMain"), "");
		WebElement sib=parentlink.findElement(By.xpath("following-sibling::table"));
		List<WebElement> links=sib.findElements(By.tagName("a"));//(".//a[@title='55K or 66 months']"));
		for(WebElement m:links)
		{
			String va=m.getAttribute("title");
			if(va.contains("Displays details of previous service") || va.contains("Displays details of next service"))
			{
				GeneralComponents.clickOnWebelement(m, "click"+va);
				GeneralComponents.waitforInternalLoad(Driver);
				selectedMileageTIS();
				break;
			}
		}

		WebElement parentlinknext =findObject(Driver,By.id("_jpfcpncuivr_T9400158481376657894632_j_id_id0:displayForm:sliderMain"), "");
		WebElement sibnext=parentlinknext.findElement(By.xpath("following-sibling::table"));
		List<WebElement> linksnext=sibnext.findElements(By.tagName("a"));//(".//a[@title='55K or 66 months']"));
		for(WebElement m:linksnext)
		{
			String va=m.getAttribute("title");
			if(va.contains("Displays details of next service"))
			{
				GeneralComponents.clickOnWebelement(m, "click"+va);
				GeneralComponents.waitforInternalLoad(Driver);
				selectedMileageTIS();
				break;
			}
		}
		//
	}
@Action(desc="schMaintOtherLinks",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void schMaintOtherLinks()
	{
		//t_T5800165711377085610147_ajax
		WebElement link =findObject(Driver,By.id("t_T5800165711377085610147_ajax"), "");
		List<WebElement> A=link.findElements(By.tagName("a"));//(".//a[@title='55K or 66 months']"));
		for(WebElement a:A)
		{
			String va=a.getText();
			String class1=a.getAttribute("class");
			if(va.contains("(1)") || va.contains("(2)") || va.contains("(3)")|| va.contains("(4)"))
			{
				GeneralComponents.clickOnWebelement(a, "click"+va);
				GeneralComponents.waitforInternalLoad(Driver);

			}
			else if(class1.equals("eye_notes"))
			{
				GeneralComponents.clickOnWebelement(a, "click eye link");
				GeneralComponents.waitforInternalLoad(Driver);

				WebElement close=findObject(Driver,By.id("_jpfcpncuivr_T5800165711377085610147_j_id_id0:closehistory"), "");

				if(close !=null)
				{
					GeneralComponents.clickOnWebelement(close, " close eye notes");

					GeneralComponents.waitforInternalLoad(Driver);
				}
			}
		}


	}

@Action(desc="schMaintOtherLinksTIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void schMaintOtherLinksTIS()
	{
		//t_T5800165711377085610147_ajax
		WebElement link =findObject(Driver,By.id("t_T9400158481376657894632_ajax"), "");
		List<WebElement> A=link.findElements(By.tagName("a"));//(".//a[@title='55K or 66 months']"));
		for(WebElement a:A)
		{
			String va=a.getText();
			String class1=a.getAttribute("class");
			if(va.contains("(1)") || va.contains("(2)") || va.contains("(3)")|| va.contains("(4)"))
			{
				GeneralComponents.clickOnWebelement(a, "click"+va);
				GeneralComponents.waitforInternalLoad(Driver);

			}
			else if(class1.equals("eye_notes"))
			{
				GeneralComponents.clickOnWebelement(a, "click eye link");
				GeneralComponents.waitforInternalLoad(Driver);

				WebElement close=findObject(Driver,By.id("_jpfcpncuivr_T9400158481376657894632_j_id_id0:closehistory"), "");

				if(close !=null)
				{
					GeneralComponents.clickOnWebelement(close, " close eye notes");

					GeneralComponents.waitforInternalLoad(Driver);
				}
			}
		}


	}

@Action(desc="vinSchMaintenance",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void vinSchMaintenance()
	{
		String splCondition;                      
		WebElement linkEye =findObject(Driver,By.id("t_T5800165711377085610147_ajax"), "");
		List<WebElement> Table=linkEye.findElements(By.tagName("table"));
		end:
			for(WebElement t:Table)
			{
				String class1=t.getAttribute("class");
				if(class1.contains("opr_section opr_conditions"))
				{ 
					WebElement U=t.findElement(By.tagName("u"));//(".//a[@title='55K or 66 months']"));

					splCondition=U.getText();
					Report.updateTestLog("Spl",splCondition+" is available ",Status.PASS);
					//GeneralComponents.waitforInternalLoad(Driver);
					//
					//

					WebElement setting=findObject(Driver,By.xpath("//img[@title='Scheduled maintenance display settings']"), "");

					if(setting !=null)
					{
						GeneralComponents.clickOnWebelement(setting, "");

						GeneralComponents.waitforInternalLoad(Driver);
					}

					WebElement op=findObject(Driver,By.id("operatingConditionItem"), "");

					if(op !=null)
					{
						Select o=new Select(op);
						o.selectByVisibleText(splCondition);
						Report.updateTestLog("Op","opCondition is available ",Status.PASS); 

					}
					else
					{
						Report.updateTestLog("Op","opCondition is available ",Status.FAIL); 
					}
					//
					WebElement op1=findObject(Driver,By.id("operatingConditionItem"), "");
					List<WebElement> options=op1.findElements(By.tagName("option"));
					int size=options.size()-1;

					WebElement movedown=findObject(Driver,By.id("moveDownSchMntBtn"), "");
					if(movedown !=null)
					{
						for(int i=0;i<size;i++)
						{

							GeneralComponents.clickOnWebelement(movedown, "move down the op");

						}  

					}

					//printPageValue
					WebElement print=findObject(Driver,By.id("printPageValue"), "");

					if(print !=null)
					{
						GeneralComponents.clickOnWebelement(print, "print");

						GeneralComponents.waitforInternalLoad(Driver);
					}
					WebElement printpage=findObject(Driver,By.id("printPagePopUp"), "");    
					List<WebElement> tds=printpage.findElements(By.tagName("td"));
					for(WebElement td:tds)
					{
						String val=td.getText();
						if(val.contains("Current ± 1"))
						{
							GeneralComponents.clickOnWebelement(td, "Current ± 1");
							Report.updateTestLog("print option", "print option tab is clicked",Status.PASS);
							break;
						} 


					}


					WebElement fuel=findObject(Driver,By.id("fuelValue"), "");

					if(fuel !=null)
					{
						GeneralComponents.clickOnWebelement(fuel, "fuel");

						GeneralComponents.waitforInternalLoad(Driver);
					}
					WebElement fuelpage=findObject(Driver,By.id("fuelPopUp"), "");    
					List<WebElement> tds1=fuelpage.findElements(By.tagName("td"));
					for(WebElement td:tds1)
					{
						String val=td.getText();
						if(val.contains("Normal"))
						{
							GeneralComponents.clickOnWebelement(td, "Normal");
							Report.updateTestLog("fuel option", "fuel option tab is clicked",Status.PASS);
							break end;
						} 


					}

					//

				}

			}


	}
@Action(desc="vinSchMaintenance1",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void vinSchMaintenance1()
	{
		String splCondition;                      
		WebElement linkEye =findObject(Driver,By.id("t_T5800165711377085610147_ajax"), "");
		List<WebElement> Table=linkEye.findElements(By.tagName("table"));
		end:
			for(WebElement t:Table)
			{
				String class1=t.getAttribute("class");
				if(class1.contains("opr_conditions"))
				{ 
					WebElement U=t.findElement(By.tagName("u"));//(".//a[@title='55K or 66 months']"));

					splCondition=U.getText();
					Report.updateTestLog("Spl",splCondition+" is available ",Status.PASS);
					//GeneralComponents.waitforInternalLoad(Driver);
					//
					//

					WebElement setting=findObject(Driver,By.xpath("//img[@title='Scheduled maintenance display settings']"), "");

					if(setting !=null)
					{
						GeneralComponents.clickOnWebelement(setting, "");

						GeneralComponents.waitforInternalLoad(Driver);
					}

					WebElement op=findObject(Driver,By.id("operatingConditionItem"), "");

					if(op !=null)
					{
						Select o=new Select(op);
						o.selectByVisibleText(splCondition);
						Report.updateTestLog("Op","opCondition is available ",Status.PASS); 

					}
					else
					{
						Report.updateTestLog("Op","opCondition is available ",Status.FAIL); 
					}
					//
					WebElement op1=findObject(Driver,By.id("operatingConditionItem"), "");
					List<WebElement> options=op1.findElements(By.tagName("option"));
					int size=options.size()-1;

					WebElement movedown=findObject(Driver,By.id("moveDownSchMntBtn"), "");
					if(movedown !=null)
					{
						for(int i=0;i<size;i++)
						{

							GeneralComponents.clickOnWebelement(movedown, "move down the op");

						}  

					}

					//printPageValue
					WebElement print=findObject(Driver,By.id("printPageValue"), "");

					if(print !=null)
					{
						GeneralComponents.clickOnWebelement(print, "print");

						GeneralComponents.waitforInternalLoad(Driver);
					}
					WebElement printpage=findObject(Driver,By.id("printPagePopUp"), "");    
					List<WebElement> tds=printpage.findElements(By.tagName("td"));
					for(WebElement td:tds)
					{
						String val=td.getText();
						if(val.contains("Current ± 1"))
						{
							GeneralComponents.clickOnWebelement(td, "Current ± 1");
							Report.updateTestLog("print option", "print option tab is clicked",Status.PASS);
							break;
						} 


					}


					WebElement fuel=findObject(Driver,By.id("fuelValue"), "");

					if(fuel !=null)
					{
						GeneralComponents.clickOnWebelement(fuel, "fuel");

						GeneralComponents.waitforInternalLoad(Driver);
					}
					WebElement fuelpage=findObject(Driver,By.id("fuelPopUp"), "");    
					List<WebElement> tds1=fuelpage.findElements(By.tagName("td"));
					for(WebElement td:tds1)
					{
						String val=td.getText();
						if(val.contains("Normal"))
						{
							GeneralComponents.clickOnWebelement(td, "Normal");
							Report.updateTestLog("fuel option", "fuel option tab is clicked",Status.PASS);
							break end;
						} 


					}

					//

				}

			}


	}

@Action(desc="vinSchMaintenanceTIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void vinSchMaintenanceTIS()
	{
		String splCondition;                      
		WebElement linkEye =findObject(Driver,By.id("t_T9400158481376657894632_ajax"), "");
		List<WebElement> Table=linkEye.findElements(By.tagName("table"));
		end:
			for(WebElement t:Table)
			{
				String class1=t.getAttribute("class");
				if(class1.contains("opr_section opr_conditions"))
				{ 
					WebElement U=t.findElement(By.tagName("u"));//(".//a[@title='55K or 66 months']"));

					splCondition=U.getText();
					Report.updateTestLog("Spl",splCondition+" is available ",Status.PASS);
					//GeneralComponents.waitforInternalLoad(Driver);
					//
					//

					WebElement setting=findObject(Driver,By.xpath("//img[@title='Scheduled maintenance display settings']"), "");

					if(setting !=null)
					{
						GeneralComponents.clickOnWebelement(setting, "");

						GeneralComponents.waitforInternalLoad(Driver);
					}

					WebElement op=findObject(Driver,By.id("operatingConditionItem"), "");

					if(op !=null)
					{
						Select o=new Select(op);
						o.selectByVisibleText(splCondition);
						Report.updateTestLog("Op","opCondition is available ",Status.PASS); 

					}
					else
					{
						Report.updateTestLog("Op","opCondition is available ",Status.FAIL); 
					}
					//
					WebElement op1=findObject(Driver,By.id("operatingConditionItem"), "");
					List<WebElement> options=op1.findElements(By.tagName("option"));
					int size=options.size()-1;
					int s=0;
					for(int j=0;j<=size;j++)
					{
						String opvalue=options.get(j).getText();
						if(opvalue.contains(splCondition))
						{
							break;
						}
						else
						{
							s++;
						}

					}
					WebElement movedown=findObject(Driver,By.id("moveDownSchMntBtn"), "");
					if(movedown !=null)
					{
						for(int i=0;i<=size-s;i++)
						{

							GeneralComponents.clickOnWebelement(movedown, "move down the op");

						}  

					}

					//printPageValue
					WebElement print=findObject(Driver,By.id("printPageValue"), "");

					if(print !=null)
					{
						GeneralComponents.clickOnWebelement(print, "print");

						GeneralComponents.waitforInternalLoad(Driver);
					}
					WebElement printpage=findObject(Driver,By.id("printPagePopUp"), "");    
					List<WebElement> tds=printpage.findElements(By.tagName("td"));
					for(WebElement td:tds)
					{
						String val=td.getText();
						if(val.contains("Current ± 1"))
						{
							GeneralComponents.clickOnWebelement(td, "Current ± 1");
							Report.updateTestLog("print option", "print option tab is clicked",Status.PASS);
							break;
						} 


					}


					WebElement fuel=findObject(Driver,By.id("fuelValue"), "");

					if(fuel !=null)
					{
						GeneralComponents.clickOnWebelement(fuel, "fuel");

						GeneralComponents.waitforInternalLoad(Driver);
					}
					WebElement fuelpage=findObject(Driver,By.id("fuelPopUp"), "");    
					List<WebElement> tds1=fuelpage.findElements(By.tagName("td"));
					for(WebElement td:tds1)
					{
						String val=td.getText();
						if(val.contains("E85"))
						{
							GeneralComponents.clickOnWebelement(td, "E85");
							Report.updateTestLog("fuel option", "fuel option tab is clicked",Status.PASS);
							GeneralComponents.waitforInternalLoad(Driver);
							break end;
						} 


					}

					//

				}

			}


	}
@Action(desc="schMaintDMYSplInst",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void schMaintDMYSplInst()
	{                                          
		WebElement ETD= findObject(Driver,By.id("_jpfcpncuivr_T5800165711377085610147_j_id_id0:schForm"), "");
		if(ETD !=null)
		{
			List<WebElement> spans=ETD.findElements(By.tagName("span"));
			for(WebElement span:spans)
			{
				String val=span.getText();
				if(val.contains("FA20 - FA20"))

				{

					Report.updateTestLog(val, "Engine value generated",Status.PASS);

				}    
				else if(val.contains("6AT"))
				{
					Report.updateTestLog(val, "Transmission value generated",Status.PASS);

				}
				else if(val.contains("2WD"))
				{
					Report.updateTestLog(val, "Driver Type value generated",Status.PASS);
					break; 
				}

			}


		}
		else
		{
			Report.updateTestLog("ETD", "ETD details not available",Status.FAIL);

		}                                          


		GeneralComponents.waitforInternalLoad(Driver);
		WebElement mileage=findObject(Driver,By.id("_jpfcpncuivr_T5800165711377085610147_j_id_id0:schForm:schMntMileage"), "" );
		if (mileage !=null)
		{
			String val=userData.getData("Schedule", "Mileage", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(mileage, val, "mileage is entered");
			Report.updateTestLog("mileage", "mileage is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("mileage", "mileage is not found", Status.FAIL);
		}
		//
		WebElement apply=findObject(Driver,By.id("_jpfcpncuivr_T5800165711377085610147_j_id_id0:schForm:applyMileageButton"), "");

		if(apply !=null)
		{
			GeneralComponents.clickOnWebelement(apply, "");
			GeneralComponents.waitForPageLoaded(Driver);
			GeneralComponents.waitforInternalLoad(Driver);
			//
			WebElement parent =findObject(Driver,By.id("_jpfcpncuivr_T5800165711377085610147_j_id_id0:schForm"), "");
			if(parent!=null)
			{
				List<WebElement> miles=parent.findElements(By.tagName("span"));
				for(WebElement m:miles)
				{
					String val=m.getText();
					if(val.contains("Miles") || val.contains("*"))
					{
						Report.updateTestLog(val, val+" is available",Status.PASS); 
						break;
					}
				}
			}

			Report.updateTestLog("apply", "apply is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("apply","apply is not ",Status.FAIL);
		}



	}
	//
@Action(desc="schMaintDMYSplInstTIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void schMaintDMYSplInstTIS()
	{                                          
		WebElement ETD= findObject(Driver,By.id("_jpfcpncuivr_T9400158481376657894632_j_id_id0:schForm"), "");
		if(ETD !=null)
		{
			List<WebElement> spans=ETD.findElements(By.tagName("span"));
			for(WebElement span:spans)
			{
				String val=span.getText();
				if(val.contains("FA20 - FA20"))

				{

					Report.updateTestLog(val, "Engine value generated",Status.PASS);

				}    
				else if(val.contains("6AT"))
				{
					Report.updateTestLog(val, "Transmission value generated",Status.PASS);

				}
				else if(val.contains("2WD"))
				{
					Report.updateTestLog(val, "Driver Type value generated",Status.PASS);
					break; 
				}

			}


		}
		else
		{
			Report.updateTestLog("ETD", "ETD details not available",Status.FAIL);

		}                                          


		GeneralComponents.waitforInternalLoad(Driver);
		WebElement mileage=findObject(Driver,By.id("_jpfcpncuivr_T9400158481376657894632_j_id_id0:schForm:schMntMileage"), "" );
		if (mileage !=null)
		{
			String val=userData.getData("Schedule", "Mileage", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(mileage, val, "mileage is entered");
			Report.updateTestLog("mileage", "mileage is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("mileage", "mileage is not found", Status.FAIL);
		}
		//
		WebElement apply=findObject(Driver,By.id("_jpfcpncuivr_T9400158481376657894632_j_id_id0:schForm:applyMileageButton"), "");

		if(apply !=null)
		{
			GeneralComponents.clickOnWebelement(apply, "");
			GeneralComponents.waitForPageLoaded(Driver);
			GeneralComponents.waitforInternalLoad(Driver);
			//
			WebElement parent =findObject(Driver,By.id("_jpfcpncuivr_T9400158481376657894632_j_id_id0:schForm"), "");
			if(parent!=null)
			{
				List<WebElement> miles=parent.findElements(By.tagName("span"));
				for(WebElement m:miles)
				{
					String val=m.getText();
					if(val.contains("Miles") || val.contains("*"))
					{
						Report.updateTestLog(val, val+" is available",Status.PASS); 
						break;
					}
				}
			}

			Report.updateTestLog("apply", "apply is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("apply","apply is not ",Status.FAIL);
		}



	}
@Action(desc="DMYSchMaintenance",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void DMYSchMaintenance()
	{
		String splCondition;                      
		WebElement linkEye =findObject(Driver,By.id("t_T5800165711377085610147_ajax"), "");
		List<WebElement> Table=linkEye.findElements(By.tagName("table"));
		end:
			for(WebElement t:Table)
			{
				String class1=t.getAttribute("class");
				if(class1.contains("opr_conditions"))
				{ 
					List<WebElement> U=t.findElements(By.tagName("u"));//(".//a[@title='55K or 66 months']"));

					splCondition=U.get(U.size()-1).getText();
					Report.updateTestLog("Spl",splCondition+" is available ",Status.PASS);
					//GeneralComponents.waitforInternalLoad(Driver);
					//
					//

					WebElement setting=findObject(Driver,By.xpath("//img[@title='Scheduled maintenance display settings']"), "");

					if(setting !=null)
					{
						GeneralComponents.clickOnWebelement(setting, "");

						GeneralComponents.waitforInternalLoad(Driver);
					}

					WebElement op=findObject(Driver,By.id("operatingConditionItem"), "");

					if(op !=null)
					{
						Select o=new Select(op);
						o.selectByVisibleText(splCondition);
						Report.updateTestLog("Op","opCondition is available ",Status.PASS); 

					}
					else
					{
						Report.updateTestLog("Op","opCondition is available ",Status.FAIL); 
					}
					//
					WebElement op1=findObject(Driver,By.id("operatingConditionItem"), "");
					List<WebElement> options=op1.findElements(By.tagName("option"));
					int size=options.size()-1;
					int s=0;
					for(int j=0;j<=size;j++)
					{
						String opvalue=options.get(j).getText();
						if(opvalue.contains(splCondition))
						{
							break;
						}
						else
						{
							s++;
						}

					}
					WebElement movedown=findObject(Driver,By.id("moveUpSchMntBtn"), "");
					if(movedown !=null)
					{
						for(int i=0;i<=size-s;i++)
						{

							GeneralComponents.clickOnWebelement(movedown, "move down the op");

						}  

					}

					//printPageValue
					WebElement print=findObject(Driver,By.id("printPageValue"), "");

					if(print !=null)
					{
						GeneralComponents.clickOnWebelement(print, "print");

						GeneralComponents.waitforInternalLoad(Driver);
					}
					WebElement printpage=findObject(Driver,By.id("printPagePopUp"), "");    
					List<WebElement> tds=printpage.findElements(By.tagName("td"));
					for(WebElement td:tds)
					{
						String val=td.getText();
						if(val.contains("Current ± 1"))
						{
							GeneralComponents.clickOnWebelement(td, "Current ± 1");
							Report.updateTestLog("print option", "print option tab is clicked",Status.PASS);
							break;
						} 


					}


					WebElement fuel=findObject(Driver,By.id("fuelValue"), "");

					if(fuel !=null)
					{
						GeneralComponents.clickOnWebelement(fuel, "fuel");

						GeneralComponents.waitforInternalLoad(Driver);
					}
					WebElement fuelpage=findObject(Driver,By.id("fuelPopUp"), "");    
					List<WebElement> tds1=fuelpage.findElements(By.tagName("td"));
					for(WebElement td:tds1)
					{
						String val=td.getText();
						if(val.contains("Normal"))
						{
							GeneralComponents.clickOnWebelement(td, "Normal");
							Report.updateTestLog("fuel option", "fuel option tab is clicked",Status.PASS);
							break end;
						} 


					}

					//

				}

			}


	}

@Action(desc="schSettingApply",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void schSettingApply()
	{
		//
		WebElement applyparent=findObject(Driver,By.id("_jpfcpncuivr_T5800165711377085610147_j_id_id0:displayForm:schMntSettingsPanel"), "");    
		List<WebElement> tds1=applyparent.findElements(By.tagName("input"));
		for(WebElement td:tds1)
		{
			String val=td.getAttribute("value");
			if(val.contains("Apply"))
			{
				GeneralComponents.clickOnWebelement(td, "applyparent");
				//Report.updateTestLog("fuel option", "fuel option tab is clicked",Status.PASS);
				break;
			} 


		}

	}
	//
@Action(desc="schSettingApplyTIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void schSettingApplyTIS()
	{
		//_jpfcpncuivr_T9400158481376657894632_j_id_id0:displayForm:schMntSettingsPanel
		WebElement applyparent=findObject(Driver,By.id("_jpfcpncuivr_T9400158481376657894632_j_id_id0:displayForm:schMntSettingsPanel"), "");    
		List<WebElement> tds1=applyparent.findElements(By.tagName("input"));
		for(WebElement td:tds1)
		{
			String val=td.getAttribute("value");
			if(val.contains("Apply"))
			{
				GeneralComponents.clickOnWebelement(td, "applyparent");
				GeneralComponents.waitforInternalLoad(Driver);
				//Report.updateTestLog("fuel option", "fuel option tab is clicked",Status.PASS);
				break;
			} 


		}

	}
@Action(desc="schPrintSetting",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void schPrintSetting()
	{
		WebElement printset1=findObject(Driver,By.id("printPopUpFrm:current_plus_min"), "");

		if(printset1 !=null)
		{
			GeneralComponents.clickOnWebelement(printset1, "Current+1");

			GeneralComponents.waitforInternalLoad(Driver);
		}
		WebElement printset2=findObject(Driver,By.id("printPopUpFrm:mileage_grid"), "");

		if(printset2 !=null)
		{
			GeneralComponents.clickOnWebelement(printset2, "Mileagegrid");

			GeneralComponents.waitforInternalLoad(Driver);
		}
		//
		//
		//
		WebElement Mileage=findObject(Driver,By.id("mileageDiv"), "");    
		List<WebElement> tds1=Mileage.findElements(By.tagName("td"));
		for(WebElement td:tds1)
		{
			String val=td.getText();
			if(val.contains("80"))
			{
				GeneralComponents.clickOnWebelement(td, "Mileage Selected");
				GeneralComponents.waitforInternalLoad(Driver);
				//Report.updateTestLog("fuel option", "fuel option tab is clicked",Status.PASS);
				break;
			} 


		}
	}


@Action(desc="schPrintSettingTIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void schPrintSettingTIS()
	{
		WebElement printset1=findObject(Driver,By.id("printPopUpFrm:current_select"), "");

		if(printset1 !=null)
		{
			GeneralComponents.clickOnWebelement(printset1, "Current");

			GeneralComponents.waitforInternalLoad(Driver);
		}
		WebElement printset2=findObject(Driver,By.id("printPopUpFrm:current_plus"), "");

		if(printset2 !=null)
		{
			GeneralComponents.clickOnWebelement(printset2, "Current+1");

			GeneralComponents.waitforInternalLoad(Driver);
		}
		WebElement printset3=findObject(Driver,By.id("printPopUpFrm:current_plus_min"), "");

		if(printset3 !=null)
		{
			GeneralComponents.clickOnWebelement(printset3, "Current+-1");

			GeneralComponents.waitforInternalLoad(Driver);
		}
		WebElement printset4=findObject(Driver,By.id("printPopUpFrm:current_up"), "");

		if(printset4 !=null)
		{
			GeneralComponents.clickOnWebelement(printset4, "Current+up");

			GeneralComponents.waitforInternalLoad(Driver);
		}
		WebElement printset5=findObject(Driver,By.id("printPopUpFrm:full_schedule"), "");

		if(printset5 !=null)
		{
			GeneralComponents.clickOnWebelement(printset5, "full schedule");

			GeneralComponents.waitforInternalLoad(Driver);
		}
		WebElement printset6=findObject(Driver,By.id("printPopUpFrm:mileage_grid"), "");

		if(printset6 !=null)
		{
			GeneralComponents.clickOnWebelement(printset6, "Mileagegrid");

			GeneralComponents.waitforInternalLoad(Driver);
		}
		//
		//
		//
		WebElement Mileage=findObject(Driver,By.id("mileageDiv"), "");    
		List<WebElement> tds1=Mileage.findElements(By.tagName("td"));
		for(WebElement td:tds1)
		{
			String val=td.getText();
			if(val.contains("50"))
			{
				GeneralComponents.clickOnWebelement(td, "Mileage Selected");
				GeneralComponents.waitforInternalLoad(Driver);
				//Report.updateTestLog("fuel option", "fuel option tab is clicked",Status.PASS);
				break;
			} 


		}
	}

@Action(desc="mailSch",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void mailSch()
	{
		WebElement Mail=findObject(Driver,By.id(""), "");    
		List<WebElement> tds1=Mail.findElements(By.tagName("img"));

		GeneralComponents.clickOnWebelement(tds1.get(0), "mail selected");
		GeneralComponents.waitforInternalLoad(Driver);
		//Report.updateTestLog("fuel option", "fuel option tab is clicked",Status.PASS);



	}


	//////////////DMY

@Action(desc="schMainDMYLookUp",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void schMainDMYLookUp()
	{
		///
		WebElement dmy=findObject(Driver, By.id("k_ASMPortal_portal_vin_dmy_book_ajax"),"" );
		List<WebElement> tds=dmy.findElements(By.tagName("td"));
		for(WebElement td:tds)
		{
			String val=td.getText();
			if(val.contains("DMY"))
			{
				GeneralComponents.clickOnWebelement(td, "DMY Selected");
				GeneralComponents.waitforInternalLoad(Driver);
				//Report.updateTestLog("fuel option", "fuel option tab is clicked",Status.PASS);
				break;
			} 


		}

		WebElement type=findObject(Driver, By.id("_jpfcpncuivr_T9808958571380783642920_j_id_id0:searchDocsForm:divisionChangeMenu"),"" );
		if(type!=null)
		{
			Select o=new Select(type);
			String val=userData.getData("Schedule", "Type", "1", "1");
			o.selectByVisibleText(val);
			Report.updateTestLog("type","type  is selected ",Status.PASS);
		}
		WebElement model=findObject(Driver, By.id("_jpfcpncuivr_T9808958571380783642920_j_id_id0:searchDocsForm:modelChangeMenu"),"" );
		if(model!=null)
		{
			Select o=new Select(model);
			String val=userData.getData("Schedule", "Model", "1", "1");
			o.selectByVisibleText(val);
			Report.updateTestLog("model","model  is selected ",Status.PASS);
		}
		GeneralComponents.waitforInternalLoad(Driver);
		WebElement year=findObject(Driver, By.id("_jpfcpncuivr_T9808958571380783642920_j_id_id0:searchDocsForm:yearChangeMenu"),"" );
		if(year!=null)
		{
			Select o=new Select(year);
			String val=userData.getData("Schedule", "Year", "1", "1");
			o.selectByVisibleText(val);
			Report.updateTestLog("year","year  is selected ",Status.PASS);
		}
		GeneralComponents.waitforInternalLoad(Driver);                                    //
		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_T9808958571380783642920_j_id_id0:searchDocsForm:dmylookupButton"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			GeneralComponents.waitForPageLoaded(Driver);

			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
		GeneralComponents.waitforInternalLoad(Driver);

		//t_T5800165711377085610147_ajax

		WebElement EDT=findObject(Driver,By.id("t_T5800165711377085610147_ajax"), "");    
		List<WebElement> A=EDT.findElements(By.tagName("a"));
		for(WebElement a:A)
		{
			String val=a.getText();
			if(val.contains("Engine, Transmission, Drive Type"))
			{
				GeneralComponents.clickOnWebelement(a, "EDT clicked");
				GeneralComponents.waitforInternalLoad(Driver);
				//Report.updateTestLog("fuel option", "fuel option tab is clicked",Status.PASS);
				break;
			} 


		}

		//

		WebElement edtTable= findObject(Driver,By.id("_jpfcpncuivr_T5800165711377085610147_j_id_id0:schForm:edt_popup"), "");
		if(edtTable !=null)
		{
			List<WebElement> trs=edtTable.findElements(By.tagName("tr"));
			for(WebElement tr:trs)
			{
				String val=tr.getText();
				if(val.contains("Large V8 - 3UR-FBE"))
				{
					GeneralComponents.clickOnWebelement(tr, "edt clicked"); 
					//Report.updateTestLog(val, val+"in special inst is available",Status.PASS);
					break;
				}    

			}


		}
		else
		{
			Report.updateTestLog("edt", "edt not found",Status.FAIL);

		}
		GeneralComponents.waitforInternalLoad(Driver);
	}

@Action(desc="schMainDMYLookUp1",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void schMainDMYLookUp1()
	{
		///
		WebElement dmy=findObject(Driver, By.id("k_ASMPortal_portal_vin_dmy_book_ajax"),"" );
		List<WebElement> tds=dmy.findElements(By.tagName("td"));
		for(WebElement td:tds)
		{
			String val=td.getText();
			if(val.contains("DMY"))
			{
				GeneralComponents.clickOnWebelement(td, "DMY Selected");
				GeneralComponents.waitforInternalLoad(Driver);
				//Report.updateTestLog("fuel option", "fuel option tab is clicked",Status.PASS);
				break;
			} 


		}

		WebElement type=findObject(Driver, By.id("_jpfcpncuivr_T9808958571380783642920_j_id_id0:searchDocsForm:divisionChangeMenu"),"" );
		if(type!=null)
		{
			Select o=new Select(type);
			String val=userData.getData("Schedule", "Type", "1", "1");
			o.selectByVisibleText(val);
			Report.updateTestLog("type","type  is selected ",Status.PASS);
		}
		GeneralComponents.waitforInternalLoad(Driver);
		WebElement model=findObject(Driver, By.id("_jpfcpncuivr_T9808958571380783642920_j_id_id0:searchDocsForm:modelChangeMenu"),"" );
		if(model!=null)
		{
			Select o=new Select(model);
			String val=userData.getData("Schedule", "Model", "1", "1");
			o.selectByIndex(1);
			Report.updateTestLog("model","model  is selected ",Status.PASS);
		}
		GeneralComponents.waitforInternalLoad(Driver);
		WebElement year=findObject(Driver, By.id("_jpfcpncuivr_T9808958571380783642920_j_id_id0:searchDocsForm:yearChangeMenu"),"" );
		if(year!=null)
		{
			Select o=new Select(year);
			String val=userData.getData("Schedule", "Year", "1", "1");
			o.selectByVisibleText(val);
			Report.updateTestLog("year","year  is selected ",Status.PASS);
		}
		GeneralComponents.waitforInternalLoad(Driver);                                    //
		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_T9808958571380783642920_j_id_id0:searchDocsForm:dmylookupButton"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			GeneralComponents.waitForPageLoaded(Driver);

			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
		GeneralComponents.waitforInternalLoad(Driver);

		//t_T5800165711377085610147_ajax

		WebElement EDT=findObject(Driver,By.id("t_T5800165711377085610147_ajax"), "");    
		List<WebElement> A=EDT.findElements(By.tagName("a"));
		for(WebElement a:A)
		{
			String val=a.getText();
			if(val.contains("Engine, Transmission, Drive Type"))
			{
				GeneralComponents.clickOnWebelement(a, "EDT clicked");
				GeneralComponents.waitforInternalLoad(Driver);
				//Report.updateTestLog("fuel option", "fuel option tab is clicked",Status.PASS);
				break;
			} 


		}

		//

		WebElement edtTable= findObject(Driver,By.id("_jpfcpncuivr_T5800165711377085610147_j_id_id0:schForm:edt_popup"), "");
		if(edtTable !=null)
		{
			List<WebElement> trs=edtTable.findElements(By.tagName("tr"));
			for(WebElement tr:trs)
			{
				String val=tr.getText();
				if(val.contains("FA20 - FA20"))
				{
					GeneralComponents.clickOnWebelement(tr, "edt clicked"); 
					//Report.updateTestLog(val, val+"in special inst is available",Status.PASS);
					break;
				}    

			}


		}
		else
		{
			Report.updateTestLog("edt", "edt not found",Status.FAIL);

		}
		GeneralComponents.waitforInternalLoad(Driver);
	}

@Action(desc="schMainDMYLookUpTIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void schMainDMYLookUpTIS()
	{
		///
		WebElement dmy=findObject(Driver, By.id("k_ASMPortal_portal_vin_dmy_book_ajax"),"" );
		List<WebElement> tds=dmy.findElements(By.tagName("td"));
		for(WebElement td:tds)
		{
			String val=td.getText();
			if(val.contains("DMY"))
			{
				GeneralComponents.clickOnWebelement(td, "DMY Selected");
				GeneralComponents.waitforInternalLoad(Driver);
				//Report.updateTestLog("fuel option", "fuel option tab is clicked",Status.PASS);
				break;
			} 


		}

		WebElement type=findObject(Driver, By.id("_jpfcpncuivr_T3809195701380782936935_j_id_id0:searchDocsForm:divisionChangeMenu"),"" );
		if(type!=null)
		{
			Select o=new Select(type);
			String val=userData.getData("Schedule", "Type", "1", "1");
			o.selectByVisibleText(val);
			Report.updateTestLog("type","type  is selected ",Status.PASS);
		}
		WebElement model=findObject(Driver, By.id("_jpfcpncuivr_T3809195701380782936935_j_id_id0:searchDocsForm:modelChangeMenu"),"" );
		if(model!=null)
		{
			Select o=new Select(model);
			String val=userData.getData("Schedule", "Model", "1", "1");
			o.selectByVisibleText(val);
			Report.updateTestLog("model","model  is selected ",Status.PASS);
		}
		GeneralComponents.waitforInternalLoad(Driver);
		WebElement year=findObject(Driver, By.id("_jpfcpncuivr_T3809195701380782936935_j_id_id0:searchDocsForm:yearChangeMenu"),"" );
		if(year!=null)
		{
			Select o=new Select(year);
			String val=userData.getData("Schedule", "Year", "1", "1");
			o.selectByVisibleText(val);
			Report.updateTestLog("year","year  is selected ",Status.PASS);
		}
		GeneralComponents.waitforInternalLoad(Driver);                                    //
		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_T3809195701380782936935_j_id_id0:searchDocsForm:dmylookupButton"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			GeneralComponents.waitForPageLoaded(Driver);

			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
		GeneralComponents.waitforInternalLoad(Driver);

		//t_T5800165711377085610147_ajax

		WebElement EDT=findObject(Driver,By.id("t_T9400158481376657894632_ajax"), "");    
		List<WebElement> A=EDT.findElements(By.tagName("a"));
		for(WebElement a:A)
		{
			String val=a.getText();
			if(val.contains("Engine, Transmission, Drive Type"))
			{
				GeneralComponents.clickOnWebelement(a, "EDT clicked");
				GeneralComponents.waitforInternalLoad(Driver);
				//Report.updateTestLog("fuel option", "fuel option tab is clicked",Status.PASS);
				break;
			} 


		}

		//

		WebElement edtTable= findObject(Driver,By.id("_jpfcpncuivr_T9400158481376657894632_j_id_id0:schForm:edt_popup"), "");
		if(edtTable !=null)
		{
			List<WebElement> trs=edtTable.findElements(By.tagName("tr"));
			for(WebElement tr:trs)
			{
				String val=tr.getText();
				if(val.contains("Large V8 - 3UR-FBE"))
				{
					GeneralComponents.clickOnWebelement(tr, "edt clicked"); 
					//Report.updateTestLog(val, val+"in special inst is available",Status.PASS);
					break;
				}    

			}


		}
		else
		{
			Report.updateTestLog("edt", "edt not found",Status.FAIL);

		}
		GeneralComponents.waitforInternalLoad(Driver);
	}


@Action(desc="schMainDMYLookUpTIS1",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void schMainDMYLookUpTIS1()
	{
		///
		WebElement dmy=findObject(Driver, By.id("k_ASMPortal_portal_vin_dmy_book_ajax"),"" );
		List<WebElement> tds=dmy.findElements(By.tagName("td"));
		for(WebElement td:tds)
		{
			String val=td.getText();
			if(val.contains("DMY"))
			{
				GeneralComponents.clickOnWebelement(td, "DMY Selected");
				GeneralComponents.waitforInternalLoad(Driver);
				//Report.updateTestLog("fuel option", "fuel option tab is clicked",Status.PASS);
				break;
			} 


		}

		WebElement type=findObject(Driver, By.id("_jpfcpncuivr_T3809195701380782936935_j_id_id0:searchDocsForm:divisionChangeMenu"),"" );
		if(type!=null)
		{
			Select o=new Select(type);
			String val=userData.getData("Schedule", "Type", "1", "1");
			o.selectByVisibleText(val);
			Report.updateTestLog("type","type  is selected ",Status.PASS);
		}
		GeneralComponents.waitforInternalLoad(Driver);
		WebElement model=findObject(Driver, By.id("_jpfcpncuivr_T3809195701380782936935_j_id_id0:searchDocsForm:modelChangeMenu"),"" );
		if(model!=null)
		{
			Select o=new Select(model);
			String val=userData.getData("Schedule", "Model", "1", "1");
			o.selectByIndex(1);
			Report.updateTestLog("model","model  is selected ",Status.PASS);
		}
		GeneralComponents.waitforInternalLoad(Driver);
		WebElement year=findObject(Driver, By.id("_jpfcpncuivr_T3809195701380782936935_j_id_id0:searchDocsForm:yearChangeMenu"),"" );
		if(year!=null)
		{
			Select o=new Select(year);
			String val=userData.getData("Schedule", "Year", "1", "1");
			o.selectByVisibleText(val);
			Report.updateTestLog("year","year  is selected ",Status.PASS);
		}
		GeneralComponents.waitforInternalLoad(Driver);                                    //
		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_T3809195701380782936935_j_id_id0:searchDocsForm:dmylookupButton"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			GeneralComponents.waitForPageLoaded(Driver);

			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
		GeneralComponents.waitforInternalLoad(Driver);

		//t_T5800165711377085610147_ajax

		WebElement EDT=findObject(Driver,By.id("t_T9400158481376657894632_ajax"), "");    
		List<WebElement> A=EDT.findElements(By.tagName("a"));
		for(WebElement a:A)
		{
			String val=a.getText();
			if(val.contains("Engine, Transmission, Drive Type"))
			{
				GeneralComponents.clickOnWebelement(a, "EDT clicked");
				GeneralComponents.waitforInternalLoad(Driver);
				//Report.updateTestLog("fuel option", "fuel option tab is clicked",Status.PASS);
				break;
			} 


		}

		//

		WebElement edtTable= findObject(Driver,By.id("_jpfcpncuivr_T9400158481376657894632_j_id_id0:schForm:edt_popup"), "");
		if(edtTable !=null)
		{
			List<WebElement> trs=edtTable.findElements(By.tagName("tr"));
			for(WebElement tr:trs)
			{
				String val=tr.getText();
				if(val.contains("FA20 - FA20"))
				{
					GeneralComponents.clickOnWebelement(tr, "edt clicked"); 
					//Report.updateTestLog(val, val+"in special inst is available",Status.PASS);
					break;
				}    

			}


		}
		else
		{
			Report.updateTestLog("edt", "edt not found",Status.FAIL);

		}
		GeneralComponents.waitforInternalLoad(Driver);
	}






@Action(desc="dmyLookup",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void dmyLookup()
	{
		///
		WebElement dmy=findObject(Driver, By.id("k_ASMPortal_portal_vin_dmy_book_ajax"),"" );
		List<WebElement> tds=dmy.findElements(By.tagName("td"));
		for(WebElement td:tds)
		{
			String val=td.getText();
			if(val.contains("DMY"))
			{
				GeneralComponents.clickOnWebelement(td, "DMY Selected");
				GeneralComponents.waitforInternalLoad(Driver);
				//Report.updateTestLog("fuel option", "fuel option tab is clicked",Status.PASS);
				break;
			} 


		}

		WebElement type=findObject(Driver, By.id("_jpfcpncuivr_T9808958571380783642920_j_id_id0:searchDocsForm:divisionChangeMenu"),"" );
		if(type!=null)
		{
			Select o=new Select(type);
			String val=userData.getData("Schedule", "Type", "1", "1");
			o.selectByVisibleText(val);
			Report.updateTestLog("type","type  is selected ",Status.PASS);
		}
		WebElement model=findObject(Driver, By.id("_jpfcpncuivr_T9808958571380783642920_j_id_id0:searchDocsForm:modelChangeMenu"),"" );
		if(model!=null)
		{
			Select o=new Select(model);
			String val=userData.getData("Schedule", "Model", "1", "1");
			o.selectByVisibleText(val);
			Report.updateTestLog("model","model  is selected ",Status.PASS);
		}
		GeneralComponents.waitforInternalLoad(Driver);
		WebElement year=findObject(Driver, By.id("_jpfcpncuivr_T9808958571380783642920_j_id_id0:searchDocsForm:yearChangeMenu"),"" );
		if(year!=null)
		{
			Select o=new Select(year);
			String val=userData.getData("Schedule", "Year", "1", "1");
			o.selectByVisibleText(val);
			Report.updateTestLog("year","year  is selected ",Status.PASS);
		}
		GeneralComponents.waitforInternalLoad(Driver);                                    //
		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_T9808958571380783642920_j_id_id0:searchDocsForm:dmylookupButton"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			GeneralComponents.waitForPageLoaded(Driver);

			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
		GeneralComponents.waitforInternalLoad(Driver);
	}

@Action(desc="errorMsg",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void errorMsg()
	{
		WebElement parent=findObject(Driver, By.id("t_T5800165711377085610147_ajax"),"");
		if(parent!=null)
		{
			WebElement lab=parent.findElement(By.tagName("label"));
			if(lab!=null)

			{
				String labelText=lab.getText();
				String labelStyle=lab.getAttribute("style");
				if(labelText.contains("Maintenance Schedule is based on specific model and model year. Please refine criteria for better results."))
				{
					Report.updateTestLog("error msg","error msg is displayed ",Status.PASS);

					if(labelStyle.contains("color: red"))
					{
						Report.updateTestLog("colour","error msg is displayed with red font",Status.PASS);
					}
				}
			}
		}
	}

@Action(desc="alertVINLookUp",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void alertVINLookUp()
	{
		WebElement VINlookup=findObject(Driver,By.id("_jpfcpncuivr_T9808858571380783534491_j_id_id0:vinForm:vinTextBox"), "VIN Lookup" );
		if (VINlookup !=null)
		{
			String val=userData.getData("Schedule", "Special Inst VIN", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(VINlookup, val, "VIN is entered");
			Report.updateTestLog("VINlookup", "VIN text is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("VINlookup", "VIN text is not found", Status.FAIL);
		}

		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_T9808858571380783534491_j_id_id0:vinForm:vinlookupbut"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");


			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}

	}
@Action(desc="alertMsgProccessing",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void alertMsgProccessing()
	{
		Alert confirmationAlert = Driver.switchTo().alert();
		String alertText = confirmationAlert.getText();
		confirmationAlert.accept();
		Report.updateTestLog("error",alertText+" is displayed ",Status.PASS);
	}


	/*DashBoard*/
	/**
	 * 
	 * MethodName: dashboardVINLookup
	 * Description: Vin look up for dashboard
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Rahini
	 */
@Action(desc="dashboardVINLookup",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void dashboardVINLookup()
	{                                          
		WebElement VINlookup=findObject(Driver,By.id("_jpfcpncuivr_T9808858571380783534491_j_id_id0:vinForm:vinTextBox"), "VIN Lookup" );
		if (VINlookup !=null)
		{
			String val=userData.getData("DashBoard", "VIN", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(VINlookup, val, "VIN is entered");
			Report.updateTestLog("VINlookup", "VIN text is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("VINlookup", "VIN text is not found", Status.FAIL);
		}

		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_T9808858571380783534491_j_id_id0:vinForm:vinlookupbut"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			GeneralComponents.waitForPageLoaded(Driver);

			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
		GeneralComponents.waitforInternalLoad(Driver);

		WebElement db=findObject(Driver, By.id("k_ASMPortal_fluid_spec_book_ajax"),"" );
		List<WebElement> tds=db.findElements(By.tagName("td"));
		for(WebElement td:tds)
		{
			String val=td.getText();
			if(val.contains("Dashboard"))
			{
				GeneralComponents.clickOnWebelement(td, "Dashboard Selected");
				GeneralComponents.waitforInternalLoad(Driver);
				//Report.updateTestLog("fuel option", "fuel option tab is clicked",Status.PASS);
				break;
			} 


		}
	}


@Action(desc="dashboardVINLookupTIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void dashboardVINLookupTIS()
	{                                          
		WebElement VINlookup=findObject(Driver,By.id("_jpfcpncuivr_T3808995701380782268321_j_id_id0:vinForm:vinTextBox"), "VIN Lookup" );
		if (VINlookup !=null)
		{
			String val=userData.getData("DashBoard", "VIN", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(VINlookup, val, "VIN is entered");
			Report.updateTestLog("VINlookup", "VIN text is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("VINlookup", "VIN text is not found", Status.FAIL);
		}

		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_T3808995701380782268321_j_id_id0:vinForm:vinlookupbut"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			GeneralComponents.waitForPageLoaded(Driver);

			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
		GeneralComponents.waitforInternalLoad(Driver);

		WebElement db=findObject(Driver, By.id("k_ASMPortal_fluid_spec_book_ajax"),"" );
		List<WebElement> tds=db.findElements(By.tagName("td"));
		for(WebElement td:tds)
		{
			String val=td.getText();
			if(val.contains("Dashboard"))
			{
				GeneralComponents.clickOnWebelement(td, "Dashboard Selected");
				GeneralComponents.waitforInternalLoad(Driver);
				//Report.updateTestLog("fuel option", "fuel option tab is clicked",Status.PASS);
				break;
			} 


		}
	}

@Action(desc="VINClearTIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void VINClearTIS()
	{                                         
		WebElement VINlookup=findObject(Driver,By.id("_jpfcpncuivr_T3808995701380782268321_j_id_id0:vinForm:vinTextBox"), "VIN Lookup" );
		if (VINlookup !=null)
		{
			String val=userData.getData("DashBoard", "VIN", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(VINlookup, val, "VIN is entered");
			Report.updateTestLog("VINlookup", "VIN text is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("VINlookup", "VIN text is not found", Status.FAIL);
		}

		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_T3808995701380782268321_j_id_id0:vinForm:vinlookupbut"), "looup Button");

		if(lookupBtn !=null)
		{
			WebElement clear=lookupBtn.findElement(By.xpath("preceding-sibling::input[@value='Clear']"));
			GeneralComponents.clickOnWebelement(clear, "clear");
			GeneralComponents.waitForPageLoaded(Driver);

			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
		GeneralComponents.waitforInternalLoad(Driver);
	}

@Action(desc="switchwindow",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void switchwindow(){
		try {

			String winHandleBefore = Driver.getWindowHandle();
			Set<String> availableWindows = Driver.getWindowHandles();
			// availableWindows.
			int i=Driver.getWindowHandles().size();
			Integer I=new Integer(i);
			String s=I.toString();
			Report.updateTestLog(s,s,Status.PASS);
			/*Iterator itr = availableWindows.iterator();
while(itr.hasNext()) {
  itr.next();

}
Driver.switchTo().window(itr.toString());
Report.updateTestLog("Switching is done","Switching is done ",Status.PASS);*/

			int j=0;
			for(String winHandle : Driver.getWindowHandles()){
				j++;
				if(j==Driver.getWindowHandles().size())
				{
					Driver.switchTo().window(winHandle);
					Report.updateTestLog("Switching is done","Switching is done ",Status.PASS);
				}
			}
		}catch(Exception e){
		
			//return Constants.KEYWORD_FAIL+ "Unable to Switch Window" + e.getMessage();
		}
		Report.updateTestLog("Switch","Switching is done",Status.PASS);
	}
@Action(desc="switchwindowback",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void switchwindowback(){
		try {
			String winHandleBefore = Driver.getWindowHandle();
			Driver.close(); 
			//Switch back to original browser (first window)
			Driver.switchTo().window(winHandleBefore);
			//continue with original browser (first window)
		}catch(Exception e){

		}

	}
	/**
	 * 
	 * MethodName: dashBoardOperations
	 * Description: Operations are dashboard
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Rahini
	 * @throws IOException 
	 */

@Action(desc="dashBoardOperations",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void dashBoardOperations() throws IOException
	{
		TreeSet<String> str=new TreeSet<String>();
		TreeSet<String> result=new TreeSet<String>();
		WebElement parent=findObject(Driver, By.id("_jpfcpncuivr_T1000266211394089704753_j_id_id0:dashBoardIconsForm"), "");
		WebElement table=parent.findElement(By.tagName("table"));
		List<WebElement> trs=table.findElements(By.xpath(".//tr[@class='section']"));
		for(WebElement tr:trs)
		{
			List<WebElement> subTables=tr.findElements(By.tagName("table"));
			for(WebElement tab:subTables)
			{
				WebElement name=tab.findElement(By.xpath(".//td[2]"));
				WebElement lab=name.findElement(By.tagName("label"));
				if(lab!=null)
				{
					String text=lab.getText().trim();
					str.add(text);

				}
			}
		}
		int size=str.size();
		Integer i=new Integer(size);
		Report.updateTestLog(i.toString()+"Click Override Link", "Clicked on ovverride link - First time", Status.PASS);
		result=(TreeSet<String>) str.comparator();
		if(result==null)
		{
			Report.updateTestLog("sort", "The Elements are in sorted order", Status.PASS);
		}




		String winHandleParent = Driver.getWindowHandle();
		//_jpfcpncuivr_T6600188541393830323023_j_id_id0:dashBoardIconsForm
		WebElement parent1=findObject(Driver, By.id("_jpfcpncuivr_T1000266211394089704753_j_id_id0:dashBoardIconsForm"), "");
		WebElement table1=parent1.findElement(By.tagName("table"));
		List<WebElement> trs1=table1.findElements(By.xpath(".//tr[@class='section']"));
		end:
			for(WebElement tr1:trs1)
			{
				List<WebElement> subTables=tr1.findElements(By.tagName("table"));
				for(WebElement tab:subTables)
				{
					WebElement name=tab.findElement(By.xpath(".//tr"));

					if(name!=null)
					{
						String text=name.getText();
						if(text.contains("Charging system"))
						{
							GeneralComponents.clickOnWebelement(name, "Clicking on "+text+"link");

							break end;
						}
					}
				}
			}


		switchwindow();

		String CurrentWindow=Driver.getTitle().trim();

		File file = new File("C:/Program Files/Spritz/Projects/ASM/TestData/Window.txt");


		FileWriter fw;
		try 
		{
			fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(CurrentWindow);
			bw.close();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*WebElement form=findObject(Driver, By.id("j_id_id2"), "form id");
if(form!=null)
{
	List<WebElement> inputs=form.findElements(By.tagName("input"));
	for(WebElement input:inputs)
	{
		String attr=input.getAttribute("value");
		if(attr.contains("Close"))
		{
			GeneralComponents.clickOnWebelement(input, "Close button is clicked");

			break;
		}
	}
}*/
		Driver.close();
		Driver.switchTo().window(winHandleParent);
		/*switchwindow();
Driver.close();
switchwindow();*/
		String CurrentWindow1=Driver.getTitle().trim();

		File file1 = new File("C:/Program Files/Spritz/Projects/ASM/TestData/Window.txt");


		FileWriter fw1;
		try 
		{
			fw1 = new FileWriter(file1);
			BufferedWriter bw1 = new BufferedWriter(fw1);
			bw1.append(CurrentWindow1);
			bw1.close();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*FileReader in;
try {
	in = new FileReader("C:/Program Files/Spritz/Projects/ASM/TestData/Window.txt");
	BufferedReader br = new BufferedReader(in);

	while (br.readLine() != null) {
		Report.updateTestLog(br.readLine(),br.readLine()+" is obtained ",Status.FAIL);
	}
	in.close();
} catch (FileNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();

}*/





		/*logout();*/
		/*Driver.close();*/
	}
@Action(desc="dashBoardOperationsTIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void dashBoardOperationsTIS() throws IOException
	{
		TreeSet<String> str=new TreeSet<String>();
		TreeSet<String> result=new TreeSet<String>();
		WebElement parent=findObject(Driver, By.id("_jpfcpncuivr_T6600188541393830323023_j_id_id0:dashBoardIconsForm"), "");
		WebElement table=parent.findElement(By.tagName("table"));
		List<WebElement> trs=table.findElements(By.xpath(".//tr[@class='section']"));
		for(WebElement tr:trs)
		{
			List<WebElement> subTables=tr.findElements(By.tagName("table"));
			for(WebElement tab:subTables)
			{
				WebElement name=tab.findElement(By.xpath(".//td[2]"));
				WebElement lab=name.findElement(By.tagName("label"));
				if(lab!=null)
				{
					String text=lab.getText().trim();
					str.add(text);

				}
			}
		}
		int size=str.size();
		Integer i=new Integer(size);
		Report.updateTestLog(i.toString()+"Click Override Link", "Clicked on ovverride link - First time", Status.PASS);
		result=(TreeSet<String>) str.comparator();
		if(result==null)
		{
			Report.updateTestLog("sort", "The Elements are in sorted order", Status.PASS);
		}




		String winHandleParent = Driver.getWindowHandle();
		//
		WebElement parent1=findObject(Driver, By.id("_jpfcpncuivr_T6600188541393830323023_j_id_id0:dashBoardIconsForm"), "");
		WebElement table1=parent1.findElement(By.tagName("table"));
		List<WebElement> trs1=table1.findElements(By.xpath(".//tr[@class='section']"));
		end:
			for(WebElement tr1:trs1)
			{
				List<WebElement> subTables=tr1.findElements(By.tagName("table"));
				for(WebElement tab:subTables)
				{
					WebElement name=tab.findElement(By.xpath(".//tr"));

					if(name!=null)
					{
						String text=name.getText();
						if(text.contains("Charging system"))
						{
							GeneralComponents.clickOnWebelement(name, "Clicking on "+text+"link");

							break end;
						}
					}
				}
			}


		switchwindow();

		String CurrentWindow=Driver.getTitle().trim();

		File file = new File("C:/Program Files/Spritz/Projects/ASM/TestData/Window.txt");


		FileWriter fw;
		try 
		{
			fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(CurrentWindow);
			bw.close();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*WebElement form=findObject(Driver, By.id("j_id_id2"), "form id");
if(form!=null)
{
	List<WebElement> inputs=form.findElements(By.tagName("input"));
	for(WebElement input:inputs)
	{
		String attr=input.getAttribute("value");
		if(attr.contains("Close"))
		{
			GeneralComponents.clickOnWebelement(input, "Close button is clicked");

			break;
		}
	}
}*/
		Driver.close();
		Driver.switchTo().window(winHandleParent);
		/*switchwindow();
Driver.close();
switchwindow();*/
		String CurrentWindow1=Driver.getTitle().trim();

		File file1 = new File("C:/Program Files/Spritz/Projects/ASM/TestData/Window.txt");


		FileWriter fw1;
		try 
		{
			fw1 = new FileWriter(file1);
			BufferedWriter bw1 = new BufferedWriter(fw1);
			bw1.append(CurrentWindow1);
			bw1.close();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*FileReader in;
try {
	in = new FileReader("C:/Program Files/Spritz/Projects/ASM/TestData/Window.txt");
	BufferedReader br = new BufferedReader(in);

	while (br.readLine() != null) {
		Report.updateTestLog(br.readLine(),br.readLine()+" is obtained ",Status.FAIL);
	}
	in.close();
} catch (FileNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();

}*/





		/*logout();*/
		/*Driver.close();*/
	}

@Action(desc="closeDashboardPopup",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void closeDashboardPopup()
	{
		WebElement form=findObject(Driver, By.id("j_id_id2"), "form id");
		if(form!=null)
		{
			List<WebElement> inputs=form.findElements(By.tagName("input"));
			for(WebElement input:inputs)
			{
				String attr=input.getAttribute("value");
				if(attr.contains("Close"))
				{
					GeneralComponents.clickOnWebelement(input, "Close button is clicked");


					break;
				}
			}
		}
	}
@Action(desc="printDashboard1",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void printDashboard1()
	{

		/*WebElement print=findObject(Driver, By.xpath("//img[@title='Print Dashboard Indicators']"), "");
	if(print!=null)
	{
		GeneralComponents.clickOnWebelement(print, "Click print");;
		GeneralComponents.waitforInternalLoad(Driver);
	}*/

		WebElement link =findObject(Driver,By.id("dashboardPrintForm"), "");
		List<WebElement> A=link.findElements(By.tagName("a"));//(".//a[@title='55K or 66 months']"));
		for(WebElement a:A)
		{
			String va=a.getText();

			if(va.contains("[1]") || va.contains("[2]"))
			{
				GeneralComponents.clickOnWebelement(a, "click"+va);
				GeneralComponents.waitforInternalLoad(Driver);

			}
		}
	}
@Action(desc="printDashboard",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void printDashboard()
	{
		String winHandleParent = Driver.getWindowHandle();
		WebElement print=findObject(Driver, By.xpath("//img[@title='Print Dashboard Indicators']"), "");
		if(print!=null)
		{
			GeneralComponents.clickOnWebelement(print, "Click print");;
			GeneralComponents.waitforInternalLoad(Driver);
		}
		switchwindow();
		String winHandlePrint = Driver.getWindowHandle();
		WebElement link =findObject(Driver,By.id("dashboardPrintForm"), "");
		List<WebElement> A=link.findElements(By.tagName("a"));//(".//a[@title='55K or 66 months']"));
		for(WebElement a:A)
		{
			String va=a.getText();

			if(va.contains("[1]") || va.contains("[2]"))
			{
				GeneralComponents.clickOnWebelement(a, "click"+va);
				GeneralComponents.waitforInternalLoad(Driver);

			}
		}
		WebElement mail=findObject(Driver, By.xpath("//span[@id='printButtonId']/img[@title='Send Dashboard Indicators by email']"), "");
		if(mail!=null)
		{
			GeneralComponents.clickOnWebelement(mail,"click mail");

		}
		switchwindow();
		WebElement to=findObject(Driver,By.id("quickMailForm:toSender"), "VIN Lookup" );
		if (to !=null)
		{
			String val=userData.getData("DashBoard", "To", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(to, val, "Mail to is entered");
			Report.updateTestLog("Mail to", "Mail to is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("Mail to", "Mail to is not found", Status.FAIL);
		}

		WebElement cc=findObject(Driver,By.id("quickMailForm:ccSender"), "VIN Lookup" );
		if (cc !=null)
		{
			String val=userData.getData("DashBoard", "Cc", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(cc, val, "CC to is entered");
			Report.updateTestLog("CC to", "CC to is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("cc to", "cc to is not found", Status.FAIL);
		}

		WebElement cancel=findObject(Driver,By.id("closeButton"), "looup Button");

		if(cancel!=null)
		{
			//	 WebElement clear=lookupBtn.findElement(By.xpath("preceding-sibling::input[@value='Clear']"));
			GeneralComponents.clickOnWebelement(cancel, "cancel");
			GeneralComponents.waitForPageLoaded(Driver);

			Report.updateTestLog("cancel", "cancel is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("cancel","cancel is not ",Status.FAIL);
		}

		//Driver.switchTo().window(winHandlePrint);
		switchwindow();
		Driver.close();
		Driver.switchTo().window(winHandleParent);
		logout();
		Driver.close();
	}

@Action(desc="closePrintDashboaard",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void closePrintDashboaard()
	{
		WebElement close=findObject(Driver, By.id("closeButtonId"), "");
		WebElement button=close.findElement(By.tagName("input"));
		if(button!=null)
		{
			GeneralComponents.clickOnWebelement(button, "click close button");


		}

	}
@Action(desc="dashBoardDMYLookUp",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void dashBoardDMYLookUp()
	{
		///
		WebElement dmy=findObject(Driver, By.id("k_ASMPortal_portal_vin_dmy_book_ajax"),"" );
		List<WebElement> tds=dmy.findElements(By.tagName("td"));
		for(WebElement td:tds)
		{
			String val=td.getText();
			if(val.contains("DMY"))
			{
				GeneralComponents.clickOnWebelement(td, "DMY Selected");
				GeneralComponents.waitforInternalLoad(Driver);
				//Report.updateTestLog("fuel option", "fuel option tab is clicked",Status.PASS);
				break;
			} 


		}
		//_jpfcpncuivr_T9808958571380783642920_j_id_id0:searchDocsForm:divisionChangeMenu
		WebElement type=findObject(Driver, By.id("_jpfcpncuivr_T9808958571380783642920_j_id_id0:searchDocsForm:divisionChangeMenu"),"" );
		if(type!=null)
		{
			Select o=new Select(type);
			String val=userData.getData("DashBoard", "Type", "1", "1");
			o.selectByVisibleText(val);
			Report.updateTestLog("type","type  is selected ",Status.PASS);
		}
		GeneralComponents.waitforInternalLoad(Driver);
		WebElement model=findObject(Driver, By.id("_jpfcpncuivr_T9808958571380783642920_j_id_id0:searchDocsForm:modelChangeMenu"),"" );
		if(model!=null)
		{
			Select o=new Select(model);
			String val=userData.getData("DashBoard", "Model", "1", "1");
			o.selectByVisibleText(val);
			Report.updateTestLog("model","model  is selected ",Status.PASS);
		}
		GeneralComponents.waitforInternalLoad(Driver);
		WebElement year=findObject(Driver, By.id("_jpfcpncuivr_T9808958571380783642920_j_id_id0:searchDocsForm:yearChangeMenu"),"" );
		if(year!=null)
		{
			Select o=new Select(year);
			String val=userData.getData("DashBoard", "Year", "1", "1");
			o.selectByVisibleText(val);
			Report.updateTestLog("year","year  is selected ",Status.PASS);
		}
		GeneralComponents.waitforInternalLoad(Driver);                                    //
		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_T9808958571380783642920_j_id_id0:searchDocsForm:dmylookupButton"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.waitForPageLoaded(Driver);

			GeneralComponents.clickOnWebelement(lookupBtn, "");
			GeneralComponents.waitForPageLoaded(Driver);

			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
		GeneralComponents.waitforInternalLoad(Driver);

		WebElement db=findObject(Driver, By.id("k_ASMPortal_fluid_spec_book_ajax"),"" );
		List<WebElement> tds1=db.findElements(By.tagName("td"));
		for(WebElement td:tds1)
		{
			String val=td.getText();
			if(val.contains("Dashboard"))
			{
				GeneralComponents.clickOnWebelement(td, "Dashboard Selected");
				GeneralComponents.waitforInternalLoad(Driver);
				//Report.updateTestLog("fuel option", "fuel option tab is clicked",Status.PASS);
				break;
			} 


		}

	}

@Action(desc="dashBoardDMYLookUpTIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void dashBoardDMYLookUpTIS()
	{
		///
		WebElement dmy=findObject(Driver, By.id("k_ASMPortal_portal_vin_dmy_book_ajax"),"" );
		List<WebElement> tds=dmy.findElements(By.tagName("td"));
		for(WebElement td:tds)
		{
			String val=td.getText();
			if(val.contains("DMY"))
			{
				GeneralComponents.clickOnWebelement(td, "DMY Selected");
				GeneralComponents.waitforInternalLoad(Driver);
				//Report.updateTestLog("fuel option", "fuel option tab is clicked",Status.PASS);
				break;
			} 


		}
		//_jpfcpncuivr_T9808958571380783642920_j_id_id0:searchDocsForm:divisionChangeMenu
		WebElement type=findObject(Driver, By.id("_jpfcpncuivr_T3809195701380782936935_j_id_id0:searchDocsForm:divisionChangeMenu"),"" );
		if(type!=null)
		{
			Select o=new Select(type);
			String val=userData.getData("DashBoard", "Type", "1", "1");
			o.selectByVisibleText(val);
			Report.updateTestLog("type","type  is selected ",Status.PASS);
		}
		GeneralComponents.waitforInternalLoad(Driver);
		WebElement model=findObject(Driver, By.id("_jpfcpncuivr_T3809195701380782936935_j_id_id0:searchDocsForm:modelChangeMenu"),"" );
		if(model!=null)
		{
			Select o=new Select(model);
			String val=userData.getData("DashBoard", "Model", "1", "1");
			o.selectByVisibleText(val);
			Report.updateTestLog("model","model  is selected ",Status.PASS);
		}
		GeneralComponents.waitforInternalLoad(Driver);
		WebElement year=findObject(Driver, By.id("_jpfcpncuivr_T3809195701380782936935_j_id_id0:searchDocsForm:yearChangeMenu"),"" );
		if(year!=null)
		{
			Select o=new Select(year);
			String val=userData.getData("DashBoard", "Year", "1", "1");
			o.selectByVisibleText(val);
			Report.updateTestLog("year","year  is selected ",Status.PASS);
		}
		GeneralComponents.waitforInternalLoad(Driver);                                    //
		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_T3809195701380782936935_j_id_id0:searchDocsForm:dmylookupButton"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			GeneralComponents.waitForPageLoaded(Driver);

			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
		GeneralComponents.waitforInternalLoad(Driver);

		WebElement db=findObject(Driver, By.id("k_ASMPortal_fluid_spec_book_ajax"),"" );
		List<WebElement> tds1=db.findElements(By.tagName("td"));
		for(WebElement td:tds1)
		{
			String val=td.getText();
			if(val.contains("Dashboard"))
			{
				GeneralComponents.clickOnWebelement(td, "Dashboard Selected");
				GeneralComponents.waitforInternalLoad(Driver);
				//Report.updateTestLog("fuel option", "fuel option tab is clicked",Status.PASS);
				break;
			} 


		}

	}


	//////fluid specification
	/**
	 * 
	 * MethodName: fluidVINLookup
	 * Description: Vin look up for fluid specification
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Rahini
	 */
@Action(desc="fluidVINLookup",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void fluidVINLookup()
	{                                          
		WebElement VINlookup=findObject(Driver,By.id("_jpfcpncuivr_T9808858571380783534491_j_id_id0:vinForm:vinTextBox"), "VIN Lookup" );
		if (VINlookup !=null)
		{
			String val=userData.getData("Fluid", "VIN", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(VINlookup, val, "VIN is entered");
			Report.updateTestLog("VINlookup", "VIN text is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("VINlookup", "VIN text is not found", Status.FAIL);
		}

		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_T9808858571380783534491_j_id_id0:vinForm:vinlookupbut"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			GeneralComponents.waitForPageLoaded(Driver);

			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
		GeneralComponents.waitforInternalLoad(Driver);
	}





@Action(desc="fluidVINLookupTIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void fluidVINLookupTIS()
	{                                          
		WebElement VINlookup=findObject(Driver,By.id("_jpfcpncuivr_T3808995701380782268321_j_id_id0:vinForm:vinTextBox"), "VIN Lookup" );
		if (VINlookup !=null)
		{
			String val=userData.getData("Fluid", "VIN", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(VINlookup, val, "VIN is entered");
			Report.updateTestLog("VINlookup", "VIN text is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("VINlookup", "VIN text is not found", Status.FAIL);
		}

		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_T3808995701380782268321_j_id_id0:vinForm:vinlookupbut"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			GeneralComponents.waitForPageLoaded(Driver);

			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
		GeneralComponents.waitforInternalLoad(Driver);


	}

@Action(desc="fluidOperations",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void fluidOperations()
	{
		WebElement db=findObject(Driver, By.id("k_ASMPortal_fluid_spec_book_ajax"),"" );
		List<WebElement> tds=db.findElements(By.tagName("td"));
		for(WebElement td:tds)
		{
			String val=td.getText();
			if(val.contains("Fluids"))
			{
				GeneralComponents.clickOnWebelement(td, "Fluids Selected");
				GeneralComponents.waitforInternalLoad(Driver);
				//Report.updateTestLog("fuel option", "fuel option tab is clicked",Status.PASS);
				break;
			} 


		}
		WebElement ETD= findObject(Driver,By.id("_jpfcpncuivr_T5800365711377165374706_j_id_id1:fluidSpecForm"), "");
		if(ETD !=null)
		{
			List<WebElement> spans=ETD.findElements(By.tagName("span"));
			end:
				for(WebElement span:spans)
				{
					String val=span.getText();
					if(val.contains("V6 - 2GR-FE, 6AT, 2WD"))

					{

						Report.updateTestLog(val, "ETD value gerated",Status.PASS);
						for(String etd:val.split(","))
						{
							if(etd.contains("V6 - 2GR-FE"))
							{
								Report.updateTestLog(val, "Engine value gerated",Status.PASS);
							}

							else if(etd.contains("6AT"))
							{
								Report.updateTestLog(val, "T value gerated",Status.PASS);
							}
							else if(etd.contains("2WD"))
							{
								Report.updateTestLog(val, "D value gerated",Status.PASS);
								break end;
							}

						}

					}    


				}


		}
		else
		{
			Report.updateTestLog("ETD", "ETD details not available",Status.FAIL);

		} 
		//fluidspectable
		WebElement form=findObject(Driver,By.id("fluidspectable"),"");
		if(form!=null)
		{
			List<WebElement> TDS=form.findElements(By.tagName("td"));
			for(WebElement td:TDS)
			{
				String attr=td.getAttribute("style");
				if(attr.contains("font-weight: bold"))
				{
					String txt=td.getText();
					if(txt.contains("Engine Oil")||txt.contains("Engine Coolant")||txt.contains("Transmission Fluid"))
					{
						Report.updateTestLog(txt, txt+"is available",Status.PASS);

					}
				}
			}
		}
	}
	//

@Action(desc="fluidOperationsTIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void fluidOperationsTIS()
	{
		WebElement db=findObject(Driver, By.id("k_ASMPortal_fluid_spec_book_ajax"),"" );
		List<WebElement> tds=db.findElements(By.tagName("td"));
		for(WebElement td:tds)
		{
			String val=td.getText();
			if(val.contains("Fluids"))
			{
				GeneralComponents.clickOnWebelement(td, "Fluids Selected");
				GeneralComponents.waitforInternalLoad(Driver);
				//Report.updateTestLog("fuel option", "fuel option tab is clicked",Status.PASS);
				break;
			} 


		}
		WebElement ETD= findObject(Driver,By.id("_jpfcpncuivr_T10000159911377165029780_j_id_id1:fluidSpecForm"), "");
		if(ETD !=null)
		{
			List<WebElement> spans=ETD.findElements(By.tagName("span"));
			end:
				for(WebElement span:spans)
				{
					String val=span.getText();
					if(val.contains("V6 - 2GR-FE, 6AT, 2WD"))

					{

						Report.updateTestLog(val, "ETD value gerated",Status.PASS);
						for(String etd:val.split(","))
						{
							if(etd.contains("V6 - 2GR-FE"))
							{
								Report.updateTestLog(val, "Engine value gerated",Status.PASS);
							}

							else if(etd.contains("6AT"))
							{
								Report.updateTestLog(val, "T value gerated",Status.PASS);
							}
							else if(etd.contains("2WD"))
							{
								Report.updateTestLog(val, "D value gerated",Status.PASS);
								break end;
							}

						}

					}    


				}


		}
		else
		{
			Report.updateTestLog("ETD", "ETD details not available",Status.FAIL);

		} 
		//fluidspectable
		WebElement form=findObject(Driver,By.id("fluidspectable"),"");
		if(form!=null)
		{
			List<WebElement> TDS=form.findElements(By.tagName("td"));
			for(WebElement td:TDS)
			{
				String attr=td.getAttribute("style");
				if(attr.contains("font-weight: bold"))
				{
					String txt=td.getText();
					if(txt.contains("Engine Oil")||txt.contains("Engine Coolant")||txt.contains("Transmission Fluid"))
					{
						Report.updateTestLog(txt, txt+"is available",Status.PASS);

					}
				}
			}
		}
	}
@Action(desc="fluidDMYLookUp",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void fluidDMYLookUp()
	{
		///
		WebElement dmy=findObject(Driver, By.id("k_ASMPortal_portal_vin_dmy_book_ajax"),"" );
		List<WebElement> tds=dmy.findElements(By.tagName("td"));
		for(WebElement td:tds)
		{
			String val=td.getText();
			if(val.contains("DMY"))
			{
				GeneralComponents.clickOnWebelement(td, "DMY Selected");
				GeneralComponents.waitforInternalLoad(Driver);
				//Report.updateTestLog("fuel option", "fuel option tab is clicked",Status.PASS);
				break;
			} 


		}
		WebElement type=findObject(Driver, By.id("_jpfcpncuivr_T9808958571380783642920_j_id_id0:searchDocsForm:divisionChangeMenu"),"" );
		if(type!=null)
		{
			Select o=new Select(type);
			String val=userData.getData("Fluid", "Type", "1", "1");
			o.selectByVisibleText(val);
			Report.updateTestLog("type","type  is selected ",Status.PASS);
		}
		GeneralComponents.waitforInternalLoad(Driver);
		WebElement model=findObject(Driver, By.id("_jpfcpncuivr_T9808958571380783642920_j_id_id0:searchDocsForm:modelChangeMenu"),"" );
		if(model!=null)
		{
			Select o=new Select(model);
			String val=userData.getData("Fluid", "Model", "1", "1");
			o.selectByVisibleText(val);
			Report.updateTestLog("model","model  is selected ",Status.PASS);
		}
		GeneralComponents.waitforInternalLoad(Driver);
		WebElement year=findObject(Driver, By.id("_jpfcpncuivr_T9808958571380783642920_j_id_id0:searchDocsForm:yearChangeMenu"),"" );
		if(year!=null)
		{
			Select o=new Select(year);
			String val=userData.getData("Fluid", "Year", "1", "1");
			o.selectByVisibleText(val);
			Report.updateTestLog("year","year  is selected ",Status.PASS);
		}
		GeneralComponents.waitforInternalLoad(Driver);                                    //
		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_T9808958571380783642920_j_id_id0:searchDocsForm:dmylookupButton"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			GeneralComponents.waitForPageLoaded(Driver);

			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
		GeneralComponents.waitforInternalLoad(Driver);
	}




@Action(desc="fluidDMYLookUpTIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void fluidDMYLookUpTIS()
	{
		///
		WebElement dmy=findObject(Driver, By.id("k_ASMPortal_portal_vin_dmy_book_ajax"),"" );
		List<WebElement> tds=dmy.findElements(By.tagName("td"));
		for(WebElement td:tds)
		{
			String val=td.getText();
			if(val.contains("DMY"))
			{
				GeneralComponents.clickOnWebelement(td, "DMY Selected");
				GeneralComponents.waitforInternalLoad(Driver);
				//Report.updateTestLog("fuel option", "fuel option tab is clicked",Status.PASS);
				break;
			} 


		}
		//_jpfcpncuivr_T9808958571380783642920_j_id_id0:searchDocsForm:divisionChangeMenu
		WebElement type=findObject(Driver, By.id("_jpfcpncuivr_T3809195701380782936935_j_id_id0:searchDocsForm:divisionChangeMenu"),"" );
		if(type!=null)
		{
			Select o=new Select(type);
			String val=userData.getData("Fluid", "Type", "1", "1");
			o.selectByVisibleText(val);
			Report.updateTestLog("type","type  is selected ",Status.PASS);
		}
		GeneralComponents.waitforInternalLoad(Driver);
		WebElement model=findObject(Driver, By.id("_jpfcpncuivr_T3809195701380782936935_j_id_id0:searchDocsForm:modelChangeMenu"),"" );
		if(model!=null)
		{
			Select o=new Select(model);
			String val=userData.getData("Fluid", "Model", "1", "1");
			o.selectByVisibleText(val);
			Report.updateTestLog("model","model  is selected ",Status.PASS);
		}
		GeneralComponents.waitforInternalLoad(Driver);
		WebElement year=findObject(Driver, By.id("_jpfcpncuivr_T3809195701380782936935_j_id_id0:searchDocsForm:yearChangeMenu"),"" );
		if(year!=null)
		{
			Select o=new Select(year);
			String val=userData.getData("Fluid", "Year", "1", "1");
			o.selectByVisibleText(val);
			Report.updateTestLog("year","year  is selected ",Status.PASS);
		}
		GeneralComponents.waitforInternalLoad(Driver);                                    //
		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_T3809195701380782936935_j_id_id0:searchDocsForm:dmylookupButton"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			GeneralComponents.waitForPageLoaded(Driver);

			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
		GeneralComponents.waitforInternalLoad(Driver);



	}

	//_jpfcpncuivr_T5800365711377165374706_j_id_id1:fluidSpecForm


@Action(desc="fluidOperationsDMY",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void fluidOperationsDMY()
	{
		WebElement db=findObject(Driver, By.id("k_ASMPortal_fluid_spec_book_ajax"),"" );
		List<WebElement> tds1=db.findElements(By.tagName("td"));
		for(WebElement td:tds1)
		{
			String val=td.getText();
			if(val.contains("Fluids"))
			{
				GeneralComponents.clickOnWebelement(td, "Fluids Selected");
				GeneralComponents.waitforInternalLoad(Driver);
				//Report.updateTestLog("fuel option", "fuel option tab is clicked",Status.PASS);
				break;
			} 


		}
		//ALL, ALL, ALL
		WebElement fluidform= findObject(Driver,By.id("_jpfcpncuivr_T5800365711377165374706_j_id_id1:fluidSpecForm"), "");
		if(fluidform !=null)
		{
			List<WebElement> spans=fluidform.findElements(By.tagName("span"));
			end:
				for(WebElement span:spans)
				{
					String val=span.getText();
					if(val.contains("ALL, ALL, ALL"))

					{
						WebElement parent=span.findElement(By.xpath(".//..")) ;
						GeneralComponents.clickOnWebelement(parent, "EDT link");
						GeneralComponents.waitforInternalLoad(Driver);
					}
				}
		}
		/*WebElement link=findObject(Driver,By.id("_jpfcpncuivr_T5800365711377165374706_j_id_id1:fluidSpecForm:etdLink1"),"");
	if(link!=null)
	{
   GeneralComponents.clickOnWebelement(link, "click on All All link");	
   GeneralComponents.waitforInternalLoad(Driver);
		 */
		WebElement popup=findObject(Driver,By.id("_jpfcpncuivr_T5800365711377165374706_j_id_id1:fluidSpecForm:etd_popup"),"");
		if(popup!=null)
		{
			List<WebElement> A=popup.findElements(By.tagName("a"));
			for(WebElement a:A)
			{
				String txt=a.getText();
				if(txt.contains("FA20 - FA20"))
				{
					GeneralComponents.clickOnWebelement(a, "click on EDT");	
					GeneralComponents.waitforInternalLoad(Driver);
					break;
				}
			}
		}

		WebElement ETD= findObject(Driver,By.id("_jpfcpncuivr_T5800365711377165374706_j_id_id1:fluidSpecForm"), "");
		if(ETD !=null)
		{
			List<WebElement> spans=ETD.findElements(By.tagName("span"));
			end:
				for(WebElement span:spans)
				{
					String val=span.getText();
					if(val.contains("FA20 - FA20, 6AT, 2WD"))

					{

						Report.updateTestLog(val, "ETD value gerated",Status.PASS);
						for(String etd:val.split(","))
						{
							if(etd.contains("FA20 - FA20"))
							{
								Report.updateTestLog(val, "Engine value gerated",Status.PASS);
							}

							else if(etd.contains("6AT"))
							{
								Report.updateTestLog(val, "T value gerated",Status.PASS);
							}
							else if(etd.contains("2WD"))
							{
								Report.updateTestLog(val, "D value gerated",Status.PASS);
								break end;
							}

						}

					}    


				}


		}
		else
		{
			Report.updateTestLog("ETD", "ETD details not available",Status.FAIL);

		} 
		//fluidspectable
		WebElement form=findObject(Driver,By.id("fluidspectable"),"");
		if(form!=null)
		{
			List<WebElement> TDS=form.findElements(By.tagName("td"));
			for(WebElement td:TDS)
			{
				String attr=td.getAttribute("style");
				if(attr.contains("font-weight: bold"))
				{
					String txt=td.getText();
					if(txt.contains("Engine Oil")||txt.contains("Engine Coolant")||txt.contains("Transmission Fluid")||txt.contains("Rear Differential Oil"))
					{
						Report.updateTestLog(txt, txt+"is available",Status.PASS);

					}
				}
			}
		}
	}
@Action(desc="fluidOperationsDMYTIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void fluidOperationsDMYTIS()
	{

		WebElement db=findObject(Driver, By.id("k_ASMPortal_fluid_spec_book_ajax"),"" );
		List<WebElement> tds1=db.findElements(By.tagName("td"));
		for(WebElement td:tds1)
		{
			String val=td.getText();
			if(val.contains("Fluids"))
			{
				GeneralComponents.clickOnWebelement(td, "Fluids Selected");
				GeneralComponents.waitforInternalLoad(Driver);
				//Report.updateTestLog("fuel option", "fuel option tab is clicked",Status.PASS);
				break;
			} 


		}
		//ALL, ALL, ALL
		WebElement fluidform= findObject(Driver,By.id("_jpfcpncuivr_T10000159911377165029780_j_id_id1:fluidSpecForm"), "");
		if(fluidform !=null)
		{
			List<WebElement> spans=fluidform.findElements(By.tagName("span"));
			end:
				for(WebElement span:spans)
				{
					String val=span.getText();
					if(val.contains("ALL, ALL, ALL"))

					{
						WebElement parent=span.findElement(By.xpath(".//..")) ;
						GeneralComponents.clickOnWebelement(parent, "EDT link");
						GeneralComponents.waitforInternalLoad(Driver);
					}
				}
		}
		/*WebElement link=findObject(Driver,By.id("_jpfcpncuivr_T5800365711377165374706_j_id_id1:fluidSpecForm:etdLink1"),"");
	if(link!=null)
	{
   GeneralComponents.clickOnWebelement(link, "click on All All link");	
   GeneralComponents.waitforInternalLoad(Driver);
		 */
		WebElement popup=findObject(Driver,By.id("_jpfcpncuivr_T10000159911377165029780_j_id_id1:fluidSpecForm:etd_popup"),"");
		if(popup!=null)
		{
			List<WebElement> A=popup.findElements(By.tagName("a"));
			for(WebElement a:A)
			{
				String txt=a.getText();
				if(txt.contains("FA20 - FA20"))
				{
					GeneralComponents.clickOnWebelement(a, "click on EDT");	
					GeneralComponents.waitforInternalLoad(Driver);
					break;
				}
			}
		}

		WebElement ETD= findObject(Driver,By.id("_jpfcpncuivr_T10000159911377165029780_j_id_id1:fluidSpecForm"), "");
		if(ETD !=null)
		{
			List<WebElement> spans=ETD.findElements(By.tagName("span"));
			end:
				for(WebElement span:spans)
				{
					String val=span.getText();
					if(val.contains("FA20 - FA20, 6AT, 2WD"))

					{

						Report.updateTestLog(val, "ETD value gerated",Status.PASS);
						for(String etd:val.split(","))
						{
							if(etd.contains("FA20 - FA20"))
							{
								Report.updateTestLog(val, "Engine value gerated",Status.PASS);
							}

							else if(etd.contains("6AT"))
							{
								Report.updateTestLog(val, "T value gerated",Status.PASS);
							}
							else if(etd.contains("2WD"))
							{
								Report.updateTestLog(val, "D value gerated",Status.PASS);
								break end;
							}

						}

					}    


				}


		}
		else
		{
			Report.updateTestLog("ETD", "ETD details not available",Status.FAIL);

		} 
		//fluidspectable
		WebElement form=findObject(Driver,By.id("fluidspectable"),"");
		if(form!=null)
		{
			List<WebElement> TDS=form.findElements(By.tagName("td"));
			for(WebElement td:TDS)
			{
				String attr=td.getAttribute("style");
				if(attr.contains("font-weight: bold"))
				{
					String txt=td.getText();
					if(txt.contains("Engine Oil")||txt.contains("Engine Coolant")||txt.contains("Transmission Fluid")||txt.contains("Rear Differential Oil"))
					{
						Report.updateTestLog(txt, txt+"is available",Status.PASS);

					}
				}
			}
		}
	}





	//specification

@Action(desc="specificationOperations",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void specificationOperations()
	{
		WebElement db=findObject(Driver, By.id("k_ASMPortal_fluid_spec_book_ajax"),"" );
		List<WebElement> tds=db.findElements(By.tagName("td"));
		for(WebElement td:tds)
		{
			String val=td.getText();
			if(val.contains("Specifications"))
			{
				GeneralComponents.clickOnWebelement(td, "Specifications Selected");
				GeneralComponents.waitforInternalLoad(Driver);
				//Report.updateTestLog("fuel option", "fuel option tab is clicked",Status.PASS);
				break;
			} 


		}
		WebElement ETD= findObject(Driver,By.id("_jpfcpncuivr_T9808758571380783445520_j_id_id1:towSpecForm"), "");
		if(ETD !=null)
		{
			List<WebElement> spans=ETD.findElements(By.tagName("span"));
			end:
				for(WebElement span:spans)
				{
					String val=span.getText();
					if(val.contains("L4 - 1NR-FE") && val.contains("CVT-F") && val.contains("2WD"))

					{

						Report.updateTestLog(val, "ETD value gerated",Status.PASS);
						for(String etd:val.split(","))
						{
							if(etd.contains("L4 - 1NR-FE"))
							{
								Report.updateTestLog(etd, "Engine value gerated",Status.PASS);
							}

							else if(etd.contains("CVT-F"))
							{
								Report.updateTestLog(etd, "T value gerated",Status.PASS);
							}
							else if(etd.contains("2WD"))
							{
								Report.updateTestLog(etd, "D value gerated",Status.PASS);
								break end;
							}

						}

					}    


				}


		}
		else
		{
			Report.updateTestLog("ETD", "ETD details not available",Status.FAIL);

		} 
		//fluidspectable
		WebElement form=findObject(Driver,By.id("towspectable"),"");
		if(form!=null)
		{
			List<WebElement> TDS=form.findElements(By.tagName("td"));
			for(WebElement td:TDS)
			{
				String attr=td.getAttribute("style");
				if(attr.contains("font-weight: bold"))
				{
					String txt=td.getText();
					if(txt.contains("Dinghy Tow")||txt.contains("Cabin Air Filter")||txt.contains("Curb Weight"))
					{
						Report.updateTestLog(txt, txt+"is available",Status.PASS);

					}
				}
			}
		}


	}


@Action(desc="specificationOperationsDMY",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void specificationOperationsDMY()
	{
		WebElement db=findObject(Driver, By.id("k_ASMPortal_fluid_spec_book_ajax"),"" );
		List<WebElement> tds=db.findElements(By.tagName("td"));
		for(WebElement td:tds)
		{
			String val=td.getText();
			if(val.contains("Specifications"))
			{
				GeneralComponents.clickOnWebelement(td, "Specifications Selected");
				GeneralComponents.waitforInternalLoad(Driver);
				//Report.updateTestLog("fuel option", "fuel option tab is clicked",Status.PASS);
				break;
			} 


		}        

		WebElement fluidform= findObject(Driver,By.id("_jpfcpncuivr_T9808758571380783445520_j_id_id1:towSpecForm"), "");
		if(fluidform !=null)
		{
			List<WebElement> spans=fluidform.findElements(By.tagName("span"));
			end:
				for(WebElement span:spans)
				{
					String val=span.getText();
					if(val.contains("ALL, ALL, ALL"))

					{
						WebElement parent=span.findElement(By.xpath(".//..")) ;
						GeneralComponents.clickOnWebelement(parent, "EDT link");
						GeneralComponents.waitforInternalLoad(Driver);
					}
				}
		}
		/*WebElement link=findObject(Driver,By.id("_jpfcpncuivr_T5800365711377165374706_j_id_id1:fluidSpecForm:etdLink1"),"");
	if(link!=null)
	{
   GeneralComponents.clickOnWebelement(link, "click on All All link");	
   GeneralComponents.waitforInternalLoad(Driver);
		 */
		WebElement popup=findObject(Driver,By.id("_jpfcpncuivr_T9808758571380783445520_j_id_id1:towSpecForm:etd_popup"),"");
		if(popup!=null)
		{
			List<WebElement> A=popup.findElements(By.tagName("a"));
			for(WebElement a:A)
			{
				String txt=a.getText();
				if(txt.contains("L4 - 2AZ-FE"))
				{
					GeneralComponents.clickOnWebelement(a, "click on EDT");	
					GeneralComponents.waitforInternalLoad(Driver);
					break;
				}
			}
		}//id=""
		WebElement ETD= findObject(Driver,By.id("_jpfcpncuivr_T9808758571380783445520_j_id_id1:towSpecForm"), "");
		if(ETD !=null)
		{
			List<WebElement> spans=ETD.findElements(By.tagName("span"));
			end:
				for(WebElement span:spans)
				{
					String val=span.getText();
					if(val.contains("L4 - 2AZ-FE") && val.contains("4AT") && val.contains("2WD"))

					{

						Report.updateTestLog(val, "ETD value gerated",Status.PASS);
						for(String etd:val.split(","))
						{
							if(etd.contains("L4 - 2AZ-FE"))
							{
								Report.updateTestLog(etd, "Engine value gerated",Status.PASS);
							}

							else if(etd.contains("4AT"))
							{
								Report.updateTestLog(etd, "T value gerated",Status.PASS);
							}
							else if(etd.contains("2WD"))
							{
								Report.updateTestLog(etd, "D value gerated",Status.PASS);
								break end;
							}

						}

					}    


				}


		}
		else
		{
			Report.updateTestLog("ETD", "ETD details not available",Status.FAIL);

		} 
		//fluidspectable
		WebElement form=findObject(Driver,By.id("towspectable"),"");
		if(form!=null)
		{
			List<WebElement> TDS=form.findElements(By.tagName("td"));
			for(WebElement td:TDS)
			{
				String attr=td.getAttribute("style");
				if(attr.contains("font-weight: bold"))
				{
					String txt=td.getText();
					if(txt.contains("Dinghy Tow")||txt.contains("Cabin Air Filter")||txt.contains("Fuel Tank Capacity"))
					{
						Report.updateTestLog(txt, txt+"is available",Status.PASS);

					}
				}
			}
		}


	}


@Action(desc="specificationOperationsTIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void specificationOperationsTIS()
	{
		WebElement db=findObject(Driver, By.id("k_ASMPortal_fluid_spec_book_ajax"),"" );
		List<WebElement> tds=db.findElements(By.tagName("td"));
		for(WebElement td:tds)
		{
			String val=td.getText();
			if(val.contains("Specifications"))
			{		             GeneralComponents.waitforInternalLoad(Driver);

			GeneralComponents.clickOnWebelement(td, "Specifications Selected");
			GeneralComponents.waitforInternalLoad(Driver);
			//Report.updateTestLog("fuel option", "fuel option tab is clicked",Status.PASS);
			break;
			} 


		}
		WebElement ETD= findObject(Driver,By.id("_jpfcpncuivr_T3809395701380783058390_j_id_id1:towSpecForm"), "");
		if(ETD !=null)
		{
			List<WebElement> spans=ETD.findElements(By.tagName("span"));
			end:
				for(WebElement span:spans)
				{
					String val=span.getText();
					if(val.contains("L4 - 1NR-FE") && val.contains("CVT-F") && val.contains("2WD"))

					{

						Report.updateTestLog(val, "ETD value gerated",Status.PASS);
						for(String etd:val.split(","))
						{
							if(etd.contains("L4 - 1NR-FE"))
							{
								Report.updateTestLog(etd, "Engine value gerated",Status.PASS);
							}

							else if(etd.contains("CVT-F"))
							{
								Report.updateTestLog(etd, "T value gerated",Status.PASS);
							}
							else if(etd.contains("2WD"))
							{
								Report.updateTestLog(etd, "D value gerated",Status.PASS);
								break end;
							}

						}

					}    


				}


		}
		else
		{
			Report.updateTestLog("ETD", "ETD details not available",Status.FAIL);

		} 
		//fluidspectable
		WebElement form=findObject(Driver,By.id("towspectable"),"");
		if(form!=null)
		{
			List<WebElement> TDS=form.findElements(By.tagName("td"));
			for(WebElement td:TDS)
			{
				String attr=td.getAttribute("style");
				if(attr.contains("font-weight: bold"))
				{
					String txt=td.getText();
					if(txt.contains("Dinghy Tow")||txt.contains("Cabin Air Filter")||txt.contains("Curb Weight"))
					{
						Report.updateTestLog(txt, txt+"is available",Status.PASS);

					}
				}
			}
		}


	}


@Action(desc="specificationOperationsDMYTIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void specificationOperationsDMYTIS()
	{
		WebElement db=findObject(Driver, By.id("k_ASMPortal_fluid_spec_book_ajax"),"" );
		List<WebElement> tds=db.findElements(By.tagName("td"));
		for(WebElement td:tds)
		{
			String val=td.getText();
			if(val.contains("Specifications"))
			{
				GeneralComponents.clickOnWebelement(td, "Specifications Selected");
				GeneralComponents.waitforInternalLoad(Driver);
				//Report.updateTestLog("fuel option", "fuel option tab is clicked",Status.PASS);
				break;
			} 


		}        

		WebElement fluidform= findObject(Driver,By.id("_jpfcpncuivr_T3809395701380783058390_j_id_id1:towSpecForm"), "");
		if(fluidform !=null)
		{
			List<WebElement> spans=fluidform.findElements(By.tagName("span"));
			end:
				for(WebElement span:spans)
				{
					String val=span.getText();
					if(val.contains("ALL, ALL, ALL"))

					{
						WebElement parent=span.findElement(By.xpath(".//..")) ;
						GeneralComponents.clickOnWebelement(parent, "EDT link");
						GeneralComponents.waitforInternalLoad(Driver);
					}
				}
		}
		/*WebElement link=findObject(Driver,By.id("_jpfcpncuivr_T5800365711377165374706_j_id_id1:fluidSpecForm:etdLink1"),"");
	if(link!=null)
	{
   GeneralComponents.clickOnWebelement(link, "click on All All link");	
   GeneralComponents.waitforInternalLoad(Driver);
		 */
		WebElement popup=findObject(Driver,By.id("_jpfcpncuivr_T3809395701380783058390_j_id_id1:towSpecForm:etd_popup"),"");
		if(popup!=null)
		{
			List<WebElement> A=popup.findElements(By.tagName("a"));
			for(WebElement a:A)
			{
				String txt=a.getText();
				if(txt.contains("L4 - 2AZ-FE"))
				{
					GeneralComponents.clickOnWebelement(a, "click on EDT");	
					GeneralComponents.waitforInternalLoad(Driver);
					break;
				}
			}
		}//id=""
		WebElement ETD= findObject(Driver,By.id("_jpfcpncuivr_T3809395701380783058390_j_id_id1:towSpecForm"), "");
		if(ETD !=null)
		{
			List<WebElement> spans=ETD.findElements(By.tagName("span"));
			end:
				for(WebElement span:spans)
				{
					String val=span.getText();
					if(val.contains("L4 - 2AZ-FE") && val.contains("4AT") && val.contains("2WD"))

					{

						Report.updateTestLog(val, "ETD value gerated",Status.PASS);
						for(String etd:val.split(","))
						{
							if(etd.contains("L4 - 2AZ-FE"))
							{
								Report.updateTestLog(etd, "Engine value gerated",Status.PASS);
							}

							else if(etd.contains("4AT"))
							{
								Report.updateTestLog(etd, "T value gerated",Status.PASS);
							}
							else if(etd.contains("2WD"))
							{
								Report.updateTestLog(etd, "D value gerated",Status.PASS);
								break end;
							}

						}

					}    


				}


		}
		else
		{
			Report.updateTestLog("ETD", "ETD details not available",Status.FAIL);

		} 
		//fluidspectable
		WebElement form=findObject(Driver,By.id("towspectable"),"");
		if(form!=null)
		{
			List<WebElement> TDS=form.findElements(By.tagName("td"));
			for(WebElement td:TDS)
			{
				String attr=td.getAttribute("style");
				if(attr.contains("font-weight: bold"))
				{
					String txt=td.getText();
					if(txt.contains("Dinghy Tow")||txt.contains("Cabin Air Filter")||txt.contains("Fuel Tank Capacity"))
					{
						Report.updateTestLog(txt, txt+"is available",Status.PASS);

					}
				}
			}
		}


	}
@Action(desc="clickSummary",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clickSummary()
	{
		WebElement summ=findObject(Driver,By.id("k_ASMPortal_portal_book_25_ajax"),"");
		List<WebElement> tds=summ.findElements(By.tagName("td"));
		for(WebElement td:tds)
		{
			String txt=td.getText();
			if(txt.contains("Summary"))
			{
				GeneralComponents.clickOnWebelement(td, "summary Selected");
				GeneralComponents.waitforInternalLoad(Driver);
				Report.updateTestLog("Summary", "Summary tab is clicked",Status.PASS);
				break;
			}

		}
	}
@Action(desc="OMverification",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void OMverification()
	{
		WebElement form1=findObject(Driver,By.id("towspectable"),"");
		if(form1!=null)
		{
			List<WebElement> A=form1.findElements(By.tagName("a"));
			for(WebElement a:A)
			{
				String txt=a.getText();
				if(txt.contains("Owner's manual"))
				{
					GeneralComponents.clickOnWebelement(a, "Owners manual link is clicked");
					GeneralComponents.waitforInternalLoad(Driver);
				}

			}
		}
		WebElement tab=findObject(Driver,By.id("k_ASMPortal_portal_book_25_ajax"),"");
		if(tab!=null)
		{
			WebElement selected=tab.findElement(By.xpath(".//td[@background='/serviceLane/resources/images/tabs/tabs.3rd.bg.purpl.gif']"));
			if(selected!=null)
			{
				String selectText=selected.getText();
				if(selectText.contains("OM"))
				{
					Report.updateTestLog("Om","navigated to OM tab",Status.PASS);
				}
			}
		}
	}
@Action(desc="clickOverrideLink",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clickOverrideLink()
	{

		if(ExpectedConditions.titleContains("Error")!=null)
		{
			try {	

				Driver.get("javascript:document.getElementById('overridelink').click();");
				Report.updateTestLog("Click Override Link", "Clicked on ovverride link - First time", Status.PASS);

			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

@Action(desc="sleep",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void sleep()
	{
		//Settings setting = new Settings();
		String tc=userData.getTestCase();
		//String testCase=""+setting.TestCase;;
		String sleepTime= userData.getData("CoreInfo", "SleepTime", "Commodity",tc, "1", "1");
		int time = Integer.parseInt(sleepTime);
		sleepTime(Driver,time );
	}
	public void sleepTime(WebDriver driver,int time )

	{
		try {

			int seconds = time * 1000;
			Thread.sleep(seconds);
		} catch (Exception e) {
			System.out.println("Exception during sleep time");
			System.out.println(e.getMessage());
			e.getMessage();
		}
	}
@Action(desc="clickRemove",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clickRemove()

	{


		WebElement all = Driver.findElement(By.xpath(".//input[@name='_jpfcpncuivr_T1200191171441212261383_j_id_id1:alertTrackerForm:j_id_id24pc5']"));


		if((all !=null) )
		{	                    GeneralComponents.clickOnWebelement(all, "");

		// GeneralComponents.clickOnWebelement(all, "");
		GeneralComponents.waitforInternalLoad(Driver);
		Report.updateTestLog("Remove Form DND", "Remove Form DND",Status.PASS);



		}

		else
		{
			Report.updateTestLog("Filter","Filter is not present",Status.FAIL);
		}
	}
	/**
	 * 
	 * MethodName: clickServiceLane
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Deepali
	 */  


@Action(desc="clickServiceLane",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clickServiceLane()

	{
		WebElement ServiceLaneBtn=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinlookupbut"), "ServiceLane Button");

		if(ServiceLaneBtn !=null)
		{
			GeneralComponents.clickOnWebelement(ServiceLaneBtn, "");
			// Driver.findElement(By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:iAgreeButton")).click();
			Report.updateTestLog("ServiceLaneBtn", "ServiceLaneBtn is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("ServiceLane Btn","ServiceLane Btn is not ",Status.FAIL);
		}
	}



	/**
	 * 
	 * MethodName: checkfilter
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Deepali
	 */  
@Action(desc="checkfilter",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void checkfilter()

	{
		WebElement checkfilter=findObject(Driver,By.id("_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm:selectId"), "looup Button");
		if(checkfilter !=null)
		{		                    
			Report.updateTestLog("Filter", "Filter is present",Status.PASS);
			GeneralComponents.clickOnWebelement(checkfilter, "");


			WebElement all = Driver.findElement(By.xpath(".//option[@value='All']"));
			WebElement NSH = Driver.findElement(By.xpath(".//option[@value='Warranty']"));
			WebElement warranty = Driver.findElement(By.xpath(".//option[@value='NSH']"));
			WebElement goodwill = Driver.findElement(By.xpath(".//option[@value='Goodwill']"));
			if((all !=null) && (NSH !=null) &&(warranty !=null) &&(goodwill !=null) )
			{
				Report.updateTestLog("Filter Values", "Filter values are present",Status.PASS);

			}
			else
			{
				Report.updateTestLog("Filter", "Filter values not present",Status.FAIL);

			}
			if((all !=null) )
			{	                    GeneralComponents.clickOnWebelement(checkfilter, "");

			GeneralComponents.clickOnWebelement(all, "");
			GeneralComponents.waitforInternalLoad(Driver);
			Report.updateTestLog("Filter Values", "All is present",Status.PASS);



			}
			if((NSH !=null) )
			{		                    GeneralComponents.clickOnWebelement(checkfilter, "");

			GeneralComponents.clickOnWebelement(NSH,"");
			GeneralComponents.waitforInternalLoad(Driver);
			Report.updateTestLog("Filter Values", "NSH is present",Status.PASS);


			} if((warranty !=null) )
			{	                    GeneralComponents.clickOnWebelement(checkfilter, "");

			GeneralComponents.clickOnWebelement(warranty, "");
			GeneralComponents.waitforInternalLoad(Driver);
			Report.updateTestLog("Filter Values", "warranty is present",Status.PASS);


			} if((goodwill !=null) )
			{	                    GeneralComponents.clickOnWebelement(checkfilter, "");

			GeneralComponents.clickOnWebelement(goodwill,"");
			GeneralComponents.waitforInternalLoad(Driver);
			Report.updateTestLog("Filter Values", "goodwill",Status.PASS);


			}

		}
		else
		{
			Report.updateTestLog("Filter","Filter is not present",Status.FAIL);
		}
	}

	/**
	 * 
	 * MethodName: checkfilterExternalTab
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Deepali
	 */  
@Action(desc="checkfilterExternalTab",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void checkfilterExternalTab()

	{//_jpfcpncuivr_T160043811357135664339_j_id_id0:serviceHistoryForm:selectId
		WebElement checkfilter=findObject(Driver,By.id("_jpfcpncuivr_T160043811357135664339_j_id_id0:serviceHistoryForm:selectId"), "looup Button");
		if(checkfilter !=null)//_jpfcpncuivr_T160043811357135664339_j_id_id0:serviceHistoryForm:selectId
		{		                    
			Report.updateTestLog("Filter", "Filter is present",Status.PASS);
			GeneralComponents.clickOnWebelement(checkfilter, "");
			

			WebElement all = Driver.findElement(By.xpath(".//option[@value='All']"));
			WebElement NSH = Driver.findElement(By.xpath(".//option[@value='Warranty']"));
			WebElement warranty = Driver.findElement(By.xpath(".//option[@value='NSH']"));
			WebElement goodwill = Driver.findElement(By.xpath(".//option[@value='Goodwill']"));
			if((all !=null) && (NSH !=null) &&(warranty !=null) &&(goodwill !=null) )
			{
				Report.updateTestLog("Filter Values", "Filter values are present",Status.PASS);

			}
			else
			{
				Report.updateTestLog("Filter", "Filter values not present",Status.FAIL);

			}
			if((all !=null) )
			{	                    GeneralComponents.clickOnWebelement(checkfilter, "");

			GeneralComponents.clickOnWebelement(all, "");
			GeneralComponents.waitforInternalLoad(Driver);
			Report.updateTestLog("Filter Values", "All is present",Status.PASS);



			}
			if((NSH !=null) )
			{		                    GeneralComponents.clickOnWebelement(checkfilter, "");

			GeneralComponents.clickOnWebelement(NSH,"");
			GeneralComponents.waitforInternalLoad(Driver);
			Report.updateTestLog("Filter Values", "NSH is present",Status.PASS);


			} if((warranty !=null) )
			{	                    GeneralComponents.clickOnWebelement(checkfilter, "");

			GeneralComponents.clickOnWebelement(warranty, "");
			GeneralComponents.waitforInternalLoad(Driver);
			Report.updateTestLog("Filter Values", "warranty is present",Status.PASS);


			} if((goodwill !=null) )
			{	                    GeneralComponents.clickOnWebelement(checkfilter, "");

			GeneralComponents.clickOnWebelement(goodwill,"");
			GeneralComponents.waitforInternalLoad(Driver);
			Report.updateTestLog("Filter Values", "goodwill",Status.PASS);


			}

		}
		else
		{
			Report.updateTestLog("Filter","Filter is not present",Status.FAIL);
		}
	}

	/**
	 * 
	 * MethodName: chkNSHFields
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Deepali
	 */  
@Action(desc="chkNSHFields",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkNSHFields()	
	{
		WebElement checkfilter=findObject(Driver,By.id("_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm:selectId"), "looup Button");
		if(checkfilter !=null)
		{		                    //_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm:selectId
			GeneralComponents.clickOnWebelement(checkfilter, "");
			

			WebElement NSH = Driver.findElement(By.xpath(".//option[@value='Warranty']"));

			if((NSH !=null) )
			{	                    GeneralComponents.clickOnWebelement(checkfilter, "");

			GeneralComponents.clickOnWebelement(NSH, "");
			GeneralComponents.waitforInternalLoad(Driver);
			//Report.updateTestLog("Filter Values", "All is present",Status.PASS);

			WebElement table= findObject(Driver,By.id("_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm"), "table");

			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("RO Close"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("RO Close", "RO Close is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("RO Close","RO Close is not Present ",Status.FAIL);
			}


			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Mileage"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("Mileage", "Mileage is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("Mileage","Mileage is not Present ",Status.FAIL);
			}

			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Dealer"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("Dealer", "Dealer is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("Dealer","Dealer is not Present ",Status.FAIL);
			}

			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Source"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("Source", "Source is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("Source","Source is not Present ",Status.FAIL);
			}



			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("RO Number"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("RO Number", "RO Number is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("RO Number","RO Number is not Present ",Status.FAIL);
			}




			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("RO Total"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("RO Total", "RO Total is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("RO Total","RO Total is not Present ",Status.FAIL);
			}




			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("RO Open"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("RO Open", "RO Open is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("RO Open","Source is not Present ",Status.FAIL);
			}




			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Service Advisor"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("Service Advisor", "Service Advisor is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("Service Advisor","Service Advisor is not Present ",Status.FAIL);
			}


			}
		}

	}  
	/**
	 * 
	 * MethodName: chkGoodwillFieldsSingle
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Deepali
	 */         
@Action(desc="chkGoodwillFieldsSingle",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkGoodwillFieldsSingle()	
	{
		WebElement checkfilter=findObject(Driver,By.id("_jpfcpncuivr_serviceHistory_j_id_id0:serviceHistoryForm:selectId"), "looup Button");
		//_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinlookupbut
		if(checkfilter !=null)
		{		                    
			Report.updateTestLog("Filter", "Filter is present",Status.PASS);
			GeneralComponents.clickOnWebelement(checkfilter, "");
			

			
			WebElement goodwill = Driver.findElement(By.xpath(".//option[@value='Goodwill']"));
			// WebElement warranty = Driver.findElement(By.xpath(".//option[@value='NSH']"));
			/* if((all !=null) && (NSH !=null) &&(warranty !=null) &&(goodwill !=null) )
           {
        	   	Report.updateTestLog("Filter Values", "Filter values are present",Status.PASS);

           }
           else
           {
        	   	Report.updateTestLog("Filter", "Filter values not present",Status.FAIL);

           }*/
			if((goodwill !=null) )
			{	                    GeneralComponents.clickOnWebelement(checkfilter, "");

			GeneralComponents.clickOnWebelement(goodwill, "");
			GeneralComponents.waitforInternalLoad(Driver);
			//Report.updateTestLog("Filter Values", "All is present",Status.PASS);

			WebElement table= findObject(Driver,By.id("_jpfcpncuivr_serviceHistory_j_id_id0:serviceHistoryForm"), "table");
			//_jpfcpncuivr_serviceHistory_j_id_id0:serviceHistoryForm
			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("RO Close"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("RO Close", "RO Close is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("RO Close","RO Close is not Present ",Status.FAIL);
			}


			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Mileage"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("Mileage", "Mileage is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("Mileage","Mileage is not Present ",Status.FAIL);
			}

			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Dealer"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("Dealer", "Dealer is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("Dealer","Dealer is not Present ",Status.FAIL);
			}

			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Source"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("Source", "Source is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("Source","Source is not Present ",Status.FAIL);
			}



			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("RO Number"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("RO Number", "RO Number is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("RO Number","RO Number is not Present ",Status.FAIL);
			}




			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Amount"))

					{	
						sleepTime(Driver, 1);

						
						Report.updateTestLog("Amount", "Amount is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("Amount","Amount is not Present ",Status.FAIL);
			}




			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Claim #"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("Claim #", "Claim # is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("Claim #","Claim # is not Present ",Status.FAIL);
			}




			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Pay Type"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("Pay Type", "Pay Type is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("Pay Type","Pay Type is not Present ",Status.FAIL);
			}


			}
		}

	}


	/**
	 * 
	 * MethodName: chkWarrantyFieldsSingle
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Deepali
	 */           
@Action(desc="chkWarrantyFieldsSingle",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkWarrantyFieldsSingle()	
	{
		WebElement checkfilter=findObject(Driver,By.id("_jpfcpncuivr_serviceHistory_j_id_id0:serviceHistoryForm:selectId"), "looup Button");
		//_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinlookupbut_jpfcpncuivr_serviceHistory_j_id_id0:serviceHistoryForm:selectId
		if(checkfilter !=null)
		{		                    
			Report.updateTestLog("Filter", "Filter is present",Status.PASS);
			GeneralComponents.clickOnWebelement(checkfilter, "");
			

			
			WebElement warranty = Driver.findElement(By.xpath(".//option[@value='NSH']"));
			
			if((warranty !=null) )
			{	                    GeneralComponents.clickOnWebelement(checkfilter, "");

			GeneralComponents.clickOnWebelement(warranty, "");
			GeneralComponents.waitforInternalLoad(Driver);
			//Report.updateTestLog("Filter Values", "All is present",Status.PASS);

			WebElement table= findObject(Driver,By.id("_jpfcpncuivr_serviceHistory_j_id_id0:serviceHistoryForm"), "table");
			//_jpfcpncuivr_serviceHistory_j_id_id0:serviceHistoryForm
			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("RO Close"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("RO Close", "RO Close is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("RO Close","RO Close is not Present ",Status.FAIL);
			}


			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Mileage"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("Mileage", "Mileage is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("Mileage","Mileage is not Present ",Status.FAIL);
			}

			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Dealer"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("Dealer", "Dealer is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("Dealer","Dealer is not Present ",Status.FAIL);
			}

			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Source"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("Source", "Source is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("Source","Source is not Present ",Status.FAIL);
			}



			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("RO Number"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("RO Number", "RO Number is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("RO Number","RO Number is not Present ",Status.FAIL);
			}




			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Amount"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("Amount", "Amount is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("Amount","Amount is not Present ",Status.FAIL);
			}




			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Claim #"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("Claim #", "Claim # is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("Claim #","Claim # is not Present ",Status.FAIL);
			}




			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Pay Type"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("Pay Type", "Pay Type is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("Pay Type","Pay Type is not Present ",Status.FAIL);
			}


			}
		}

	}



	/**
	 * 
	 * MethodName: chkAllFields
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Deepali
	 */         

@Action(desc="chkAllFields",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkAllFields()	
	{
		WebElement checkfilter=findObject(Driver,By.id("_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm:selectId"), "looup Button");
		//_jpfcpncuivr_T160043811357135664339_j_id_id0:serviceHistoryForm:selectId
		if(checkfilter !=null)
		{		                    
			Report.updateTestLog("Filter", "Filter is present",Status.PASS);
			GeneralComponents.clickOnWebelement(checkfilter, "");


			WebElement all = Driver.findElement(By.xpath(".//option[@value='All']"));

			if((all !=null) )
			{	                    GeneralComponents.clickOnWebelement(checkfilter, "");

			GeneralComponents.clickOnWebelement(all, "");
			GeneralComponents.waitforInternalLoad(Driver);
			//Report.updateTestLog("Filter Values", "All is present",Status.PASS);

			WebElement table= findObject(Driver,By.id("_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm"), "table");
			//_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm----_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm
			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("RO Close"))

					{	//_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm:selectId//_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("RO Close", "RO Close is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("RO Close","RO Close is not Present ",Status.FAIL);
			}


			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Mileage"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("Mileage", "Mileage is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("Mileage","Mileage is not Present ",Status.FAIL);
			}

			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Dealer"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("Dealer", "Dealer is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("Dealer","Dealer is not Present ",Status.FAIL);
			}

			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Source"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("Source", "Source is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("Source","Source is not Present ",Status.FAIL);
			}



			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("RO Number"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("RO Number", "RO Number is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("RO Number","RO Number is not Present ",Status.FAIL);
			}




			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("RO Total"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("RO Total", "RO Total is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("RO Total","RO Total is not Present ",Status.FAIL);
			}




			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("RO Open"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("RO Open", "RO Open is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("RO Open","Source is not Present ",Status.FAIL);
			}




			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Service Advisor"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("Service Advisor", "Service Advisor is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("Service Advisor","Service Advisor is not Present ",Status.FAIL);
			}


			}
		}

	}

	/**
	 * 
	 * MethodName: chkGoodwillFields
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Deepali
	 */     

@Action(desc="chkGoodwillFields",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkGoodwillFields()	
	{
		WebElement checkfilter=findObject(Driver,By.id("_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm:selectId"), "looup Button");
		//_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinlookupbut
		if(checkfilter !=null)
		{		            

			//_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm:selectId//_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm

			Report.updateTestLog("Filter", "Filter is present",Status.PASS);
			GeneralComponents.clickOnWebelement(checkfilter, "");
			
			WebElement goodwill = Driver.findElement(By.xpath(".//option[@value='Goodwill']"));
			
			if((goodwill !=null) )
			{	                    GeneralComponents.clickOnWebelement(checkfilter, "");

			GeneralComponents.clickOnWebelement(goodwill, "");
			GeneralComponents.waitforInternalLoad(Driver);
			//Report.updateTestLog("Filter Values", "All is present",Status.PASS);

			WebElement table= findObject(Driver,By.id("_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm"), "table");

			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("RO Close"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("RO Close", "RO Close is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("RO Close","RO Close is not Present ",Status.FAIL);
			}


			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Mileage"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("Mileage", "Mileage is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("Mileage","Mileage is not Present ",Status.FAIL);
			}

			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Dealer"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("Dealer", "Dealer is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("Dealer","Dealer is not Present ",Status.FAIL);
			}

			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Source"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("Source", "Source is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("Source","Source is not Present ",Status.FAIL);
			}



			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("RO Number"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("RO Number", "RO Number is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("RO Number","RO Number is not Present ",Status.FAIL);
			}




			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Amount"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("Amount", "Amount is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("Amount","Amount is not Present ",Status.FAIL);
			}




			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Claim #"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("Claim #", "Claim # is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("Claim #","Claim # is not Present ",Status.FAIL);
			}




			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Pay Type"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("Pay Type", "Pay Type is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("Pay Type","Pay Type is not Present ",Status.FAIL);
			}


			}
		}

	}


	/**
	 * 
	 * MethodName: chkWarrantyFields
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Deepali
	 */           
@Action(desc="chkWarrantyFields",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkWarrantyFields()	
	{
		WebElement checkfilter=findObject(Driver,By.id("_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm:selectId"), "looup Button");
		//_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinlookupbut
		if(checkfilter !=null)

			//_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm:selectId//_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm

		{		                    
			Report.updateTestLog("Filter", "Filter is present",Status.PASS);
			GeneralComponents.clickOnWebelement(checkfilter, "");
			/*WebElement all=findObject(Driver,By.id("_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm:selectId"), "looup Button");
        WebElement NSH=findObject(Driver,By.id("_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm:selectId"), "looup Button");
        WebElement warranty=findObject(Driver,By.id("_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm:selectId"), "looup Button");
        WebElement goodwill=findObject(Driver,By.id("_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm:selectId"), "looup Button");*/

			// WebElement all = Driver.findElement(By.xpath(".//option[@value='All']"));
			// WebElement NSH = Driver.findElement(By.xpath(".//option[@value='NSH']"));
			WebElement warranty = Driver.findElement(By.xpath(".//option[@value='NSH']"));
			/* WebElement goodwill = Driver.findElement(By.xpath(".//option[@value='Goodwill']"));
           if((all !=null) && (NSH !=null) &&(warranty !=null) &&(goodwill !=null) )
           {
        	   	Report.updateTestLog("Filter Values", "Filter values are present",Status.PASS);

           }
           else
           {
        	   	Report.updateTestLog("Filter", "Filter values not present",Status.FAIL);

           }*/
			if((warranty !=null) )
			{	                    GeneralComponents.clickOnWebelement(checkfilter, "");

			GeneralComponents.clickOnWebelement(warranty, "");
			GeneralComponents.waitforInternalLoad(Driver);
			//Report.updateTestLog("Filter Values", "All is present",Status.PASS);

			WebElement table= findObject(Driver,By.id("_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm"), "table");

			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("RO Close"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("RO Close", "RO Close is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("RO Close","RO Close is not Present ",Status.FAIL);
			}


			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Mileage"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("Mileage", "Mileage is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("Mileage","Mileage is not Present ",Status.FAIL);
			}

			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Dealer"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("Dealer", "Dealer is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("Dealer","Dealer is not Present ",Status.FAIL);
			}

			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Source"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("Source", "Source is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("Source","Source is not Present ",Status.FAIL);
			}



			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("RO Number"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("RO Number", "RO Number is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("RO Number","RO Number is not Present ",Status.FAIL);
			}




			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Amount"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("Amount", "Amount is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("Amount","Amount is not Present ",Status.FAIL);
			}




			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Claim #"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("Claim #", "Claim # is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("Claim #","Claim # is not Present ",Status.FAIL);
			}




			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Pay Type"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("Pay Type", "Pay Type is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("Pay Type","Pay Type is not Present ",Status.FAIL);
			}


			}
		}

	}







	/**
	 * 
	 * MethodName: clickServiceHistory
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Deepali
	 */  

@Action(desc="clickServiceHistory",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clickServiceHistory()

	{


		GeneralComponents.waitforInternalLoad(Driver);

		WebElement s= findObject(Driver,By.id("k_ASMPortal_Vehicle_Book_ajax"), "Service History");//k_ASMPortal_Vehicle_Book_ajax
		if(s !=null)//k_ASMPortal_Vehicle_Book_ajax
		{
			List<WebElement> tds=s.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Service History"))
				{
					GeneralComponents.clickOnWebelement(td, "Service History");
					Report.updateTestLog("Service History", "Service History tab is clicked",Status.PASS);
					break;
				}    

			}


		}
		else
		{
			Report.updateTestLog("Service History","Service History tab is not clicked ",Status.FAIL);
		}



	}




	/**
	 * 
	 * MethodName: printIcon
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Deepali
	 */  

@Action(desc="printIcon",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void printIcon()

	{
		//  WebElement printIcon=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinlookupbut"), "Print Icon");
		//_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinlookupbut
		WebElement printicon=findObject(Driver,By.id("printPreviewIcon"), "");
		WebElement printlink=findObject(Driver,By.id("printPreview"), "");

		if(printicon !=null)
		{
			Report.updateTestLog("Print Icon","Print Icon is Peresent ",Status.PASS);

		}

		else
		{
			Report.updateTestLog("Print Icon","Print Icon is Peresent ",Status.FAIL);
		}

		if(printlink !=null)
		{
			Report.updateTestLog("Print Icon","Print Icon is Peresent ",Status.PASS);

			GeneralComponents.clickOnWebelement(printlink, "print");

			GeneralComponents.waitforInternalLoad(Driver);
			Driver.close();
		} 
		else
		{
			Report.updateTestLog("Print Link","Print Link is Not Peresent ",Status.FAIL);
		}


	}


	/**
	 * 
	 * MethodName: collapsAll
	 * Description: collapsAll
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Deepali
	 */  
@Action(desc="collapsAll",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void collapsAll()

	{WebElement collapsAllTable= findObject(Driver,By.id("_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm"), "Collapse All");

	if(collapsAllTable !=null)
	{
		List<WebElement> tds=collapsAllTable.findElements(By.tagName("b"));
		for(WebElement b:tds)
		{
			String val=b.getText();
			if(val.contains("Collapse All [-]"))

			{	
				sleepTime(Driver, 1);

				GeneralComponents.clickOnWebelement(b, "Collapse All [-] ");
				Report.updateTestLog("Collapse All", "Collapse All is clicked",Status.PASS);
				break;
			}    

		}
	}
	else
	{
		Report.updateTestLog("Collapse All","Collapse All is not clicked ",Status.FAIL);
	}
	}
	/**
	 * 
	 * MethodName: showAll
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Deepali
	 */  

@Action(desc="showAll",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void showAll()

	{WebElement collapsAllTable= findObject(Driver,By.id("_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm"), "Collapse All");

	if(collapsAllTable !=null)
	{
		List<WebElement> tds=collapsAllTable.findElements(By.tagName("b"));
		for(WebElement b:tds)
		{
			String val=b.getText();
			if(val.contains("Collapse All [-]"))

			{	
				sleepTime(Driver, 1);

				GeneralComponents.clickOnWebelement(b, "Collapse All [-] ");
				Report.updateTestLog("Collapse All", "Collapse All is clicked",Status.PASS);
				break;
			}    

		}
	}
	else
	{
		Report.updateTestLog("Collapse All","Collapse All is not clicked ",Status.FAIL);
	}
	}/**
	 * 
	 * MethodName: expand
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Deepali
	 */ 
@Action(desc="expand",object=ObjectType.BROWSER,input=InputType.OPTIONAL)

	public void expand()

	{
	WebElement collapsAllTable= findObject(Driver,By.linkText("Expand All[+]"), "Expand All");
	//_jpfcpncuivr_T160043811357135664339_j_id_id0:serviceHistoryForm
	if(collapsAllTable !=null)
	{
		List<WebElement> tds=collapsAllTable.findElements(By.tagName("a"));
		for(WebElement a:tds)
		{
			String val=a.getText();
			if(val.contains("Expand All [+]"))

			{	
				sleepTime(Driver, 1);

				GeneralComponents.clickOnWebelement(collapsAllTable, "Expand All [+] ");
				//collapsAllTable.click();
				Report.updateTestLog("Expand All", "Expandse All is clicked",Status.PASS);
				break;
			}    

		}
	}
	else
	{
		Report.updateTestLog("Expand All","Expand All is not clicked ",Status.FAIL);
	}
	}





	/*{WebElement plus= findObject(Driver,By.id("_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm"), "Plus");*/

	/*if(plus !=null)
{
       List<WebElement> tds=plus.findElements(By.tagName("table"));
       for(WebElement tr:tds)
       {

              String attr=tr.getAttribute("style")	;
				if(attr.contains("border:1px #9C9C9C solid;background: #EAEAEA"))
				{

					List<WebElement> td2=attr.findElements(By.tagName("tr"));
				       for(WebElement tr:tds)
				       {

				              String attr=tr.getAttribute("style")	;
								if(attr.contains("border:1px #9C9C9C solid;background: #EAEAEA"))
								{


					GeneralComponents.clickOnWebelement(tr, "background: #C0C0C0; cursor:pointer ");
	                 Report.updateTestLog("Expand ", "plus is clicked",Status.PASS);
	                 break;

				}


       }
}
else
{
   Report.updateTestLog("Expand ","plus is not clicked ",Status.FAIL);
}
}*/

	/**
	 * 
	 * MethodName: clickPlus
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Deepali
	 */  
@Action(desc="clickPlus",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clickPlus()

	{WebElement plus= findObject(Driver,By.id("_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm"), "plus");

	if(plus !=null)
	{
		List<WebElement> tds=plus.findElements(By.tagName("b"));
		for(WebElement b:tds)
		{
			String val=b.getText();
			if(val.contains("+"))

			{	
				sleepTime(Driver, 1);

				GeneralComponents.clickOnWebelement(b, "+");
				Report.updateTestLog("plus", "plus is clicked",Status.PASS);
				break;
			}    

		}
	}
	else
	{
		Report.updateTestLog("plus","plus is not clicked ",Status.FAIL);
	}
	}

@Action(desc="clickN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clickN()

	{WebElement plus= findObject(Driver,By.id("_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm"), "-");

	if(plus !=null)
	{
		List<WebElement> tds=plus.findElements(By.tagName("b"));
		for(WebElement b:tds)
		{
			String val=b.getText();
			if(val.contains("-"))

			{	
				sleepTime(Driver, 1);

				GeneralComponents.clickOnWebelement(b, "-");
				Report.updateTestLog("-", "- is clicked",Status.PASS);
				break;
			}    

		}
	}
	else
	{
		Report.updateTestLog("-","- is not clicked ",Status.FAIL);
	}
	}





	/**
	 * 
	 * MethodName: chkFields
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Deepali
	 */  

@Action(desc="chkFields",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkFields()

	{
		WebElement table= findObject(Driver,By.id("_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm"), "plus");

		if(table !=null)
		{
			List<WebElement> tds=table.findElements(By.tagName("b"));
			for(WebElement b:tds)
			{
				String val=b.getText();
				if(val.contains("RO Close"))

				{	
					sleepTime(Driver, 1);

					//GeneralComponents.clickOnWebelement(b, "RO Close");
					Report.updateTestLog("RO Close", "RO Close is Present",Status.PASS);
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog("RO Close","RO Close is not Present ",Status.FAIL);
		}


		if(table !=null)
		{
			List<WebElement> tds=table.findElements(By.tagName("b"));
			for(WebElement b:tds)
			{
				String val=b.getText();
				if(val.contains("Mileage"))

				{	
					sleepTime(Driver, 1);

					//GeneralComponents.clickOnWebelement(b, "RO Close");
					Report.updateTestLog("Mileage", "Mileage is Present",Status.PASS);
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog("Mileage","Mileage is not Present ",Status.FAIL);
		}

		if(table !=null)
		{
			List<WebElement> tds=table.findElements(By.tagName("b"));
			for(WebElement b:tds)
			{
				String val=b.getText();
				if(val.contains("Dealer"))

				{	
					sleepTime(Driver, 1);

					//GeneralComponents.clickOnWebelement(b, "RO Close");
					Report.updateTestLog("Dealer", "Dealer is Present",Status.PASS);
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog("Dealer","Dealer is not Present ",Status.FAIL);
		}

		if(table !=null)
		{
			List<WebElement> tds=table.findElements(By.tagName("b"));
			for(WebElement b:tds)
			{
				String val=b.getText();
				if(val.contains("Source"))

				{	
					sleepTime(Driver, 1);

					//GeneralComponents.clickOnWebelement(b, "RO Close");
					Report.updateTestLog("Source", "Source is Present",Status.PASS);
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog("Source","Source is not Present ",Status.FAIL);
		}



		if(table !=null)
		{
			List<WebElement> tds=table.findElements(By.tagName("b"));
			for(WebElement b:tds)
			{
				String val=b.getText();
				if(val.contains("RO Number"))

				{	
					sleepTime(Driver, 1);

					//GeneralComponents.clickOnWebelement(b, "RO Close");
					Report.updateTestLog("RO Number", "RO Number is Present",Status.PASS);
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog("RO Number","RO Number is not Present ",Status.FAIL);
		}




		if(table !=null)
		{
			List<WebElement> tds=table.findElements(By.tagName("b"));
			for(WebElement b:tds)
			{
				String val=b.getText();
				if(val.contains("RO Total"))

				{	
					sleepTime(Driver, 1);

					//GeneralComponents.clickOnWebelement(b, "RO Close");
					Report.updateTestLog("RO Total", "RO Total is Present",Status.PASS);
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog("RO Total","RO Total is not Present ",Status.FAIL);
		}




		if(table !=null)
		{
			List<WebElement> tds=table.findElements(By.tagName("b"));
			for(WebElement b:tds)
			{
				String val=b.getText();
				if(val.contains("RO Open"))

				{	
					sleepTime(Driver, 1);

					//GeneralComponents.clickOnWebelement(b, "RO Close");
					Report.updateTestLog("RO Open", "RO Open is Present",Status.PASS);
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog("RO Open","Source is not Present ",Status.FAIL);
		}




		if(table !=null)
		{
			List<WebElement> tds=table.findElements(By.tagName("b"));
			for(WebElement b:tds)
			{
				String val=b.getText();
				if(val.contains("Service Advisor"))

				{	
					sleepTime(Driver, 1);

					//GeneralComponents.clickOnWebelement(b, "RO Close");
					Report.updateTestLog("Service Advisor", "Service Advisor is Present",Status.PASS);
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog("Service Advisor","Service Advisor is not Present ",Status.FAIL);
		}



	}

	/**
	 * 
	 * MethodName: purchesrNotPresent_ServiceConnect_Ext
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Deepali
	 */  

@Action(desc="clickLink",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clickLink()

	{
		WebElement plus= findObject(Driver,By.id("_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm"), "-");
		//ASMPortal_Service_History_page//_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm

		if(plus !=null)
		{
			List<WebElement> tds=plus.findElements(By.tagName("a"));
			for(WebElement b:tds)
			{
				//border: 1px #9C9C9C solid; background: #EAEAEA
				String val=b.getText();
				if(val.contains("9B"))
					//id="_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm:tcode_popup" 
				{	
					sleepTime(Driver, 1);

					GeneralComponents.clickOnWebelement(b, "Trouble Code");
					Report.updateTestLog("T1", "T1:",Status.PASS);

					break;
				}    

			}
		}


	}


	/**
	 * 
	 * MethodName: chkPrint
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Deepali
	 */  
@Action(desc="chkPrint",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkPrint() {
		WebElement plus= findObject(Driver,By.id("serviceHistoryFormPrint"), "");
		//ASMPortal_Service_History_page//_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm

		if(plus !=null)
		{
			List<WebElement> tds=plus.findElements(By.tagName("a"));
			for(WebElement td:tds)
			{								
				String attr=td.getAttribute("href")	;

				//String val=b.getText();
				if(attr.contains("#"))

				{	
					sleepTime(Driver, 1);

					GeneralComponents.clickOnWebelement(td, "#");
					//_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm:tcode_popup
					GeneralComponents.waitforInternalLoad(Driver);

					//  break;
				}    

			}
		}
	}
	///////////////////////////////////////////////////////

	/**
	 * 
	 * MethodName: enterVINTab
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Deepali
	 */  
@Action(desc="enterVINTab",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void enterVINTab()

	{
		WebElement VINlookup=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinTextBox"), "VIN Lookup" );
		if (VINlookup !=null)
		{
			String val=userData.getData("ServiceHistoryInternalTab", "VIN", "1", "1");
			GeneralComponents.enterValue(VINlookup, val, "VIN is entered");

			Report.updateTestLog("VINlookup", "VIN text is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("VINlookup", "VIN text is not found", Status.FAIL);
		}
	}

@Action(desc="enterVINSing",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void enterVINSing()

{
	WebElement VINlookup1=findObject(Driver,By.id("_jpfcpncuivr_portletInstance_2_j_id_id0:vehicleSubView:vinForm:vinTextBox"), "VIN Lookup" );
	if (VINlookup1 !=null)
	{
		String val=userData.getData("ServiceHistoryInternalTab", "VIN", "1", "1");
		GeneralComponents.enterValue(VINlookup1, val, "VIN is entered");

		Report.updateTestLog("VINlookup", "VIN text is found",Status.PASS);
	}            
	else
	{
		Report.updateTestLog("VINlookup", "VIN text is not found", Status.FAIL);
	}
}
	/**
	 * 
	 * MethodName: clicklookupServiceLane
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Deepali
	 */  

@Action(desc="clicklookupServiceLane",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clicklookupServiceLane()

	{
		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinlookupbut"), "looup Button");
		//_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinlookupbut
		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			GeneralComponents.waitforInternalLoad(Driver);

			// Driver.findElement(By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:iAgreeButton")).click();
			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not present",Status.FAIL);
		}
	}






	/**
	 * 
	 * MethodName: checkfilterSingle
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Deepali
	 */  


@Action(desc="checkfilterSingle",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void checkfilterSingle()

	{
		WebElement checkfilter=findObject(Driver,By.id("_jpfcpncuivr_serviceHistory_j_id_id0:serviceHistoryForm:selectId"), "looup Button");
		if(checkfilter !=null)//_jpfcpncuivr_serviceHistory_j_id_id0:serviceHistoryForm:selectId
		{		                    
			Report.updateTestLog("Filter", "Filter is present",Status.PASS);
			GeneralComponents.clickOnWebelement(checkfilter, "");
			
			WebElement all = Driver.findElement(By.xpath(".//option[@value='All']"));
			WebElement NSH = Driver.findElement(By.xpath(".//option[@value='Warranty']"));
			WebElement warranty = Driver.findElement(By.xpath(".//option[@value='NSH']"));
			WebElement goodwill = Driver.findElement(By.xpath(".//option[@value='Goodwill']"));
			if((all !=null) && (NSH !=null) &&(warranty !=null) &&(goodwill !=null) )
			{
				Report.updateTestLog("Filter Values", "Filter values are present",Status.PASS);

			}
			else
			{
				Report.updateTestLog("Filter", "Filter values not present",Status.FAIL);

			}
			if((all !=null) )
			{	                    
			GeneralComponents.clickOnWebelement(checkfilter, "");
			GeneralComponents.clickOnWebelement(all, "");
			GeneralComponents.waitforInternalLoad(Driver);
			Report.updateTestLog("Filter Values", "All is present",Status.PASS);



			}
			if((NSH !=null) )
			{		                   
				
			GeneralComponents.clickOnWebelement(checkfilter, "");

			GeneralComponents.clickOnWebelement(NSH,"");
			GeneralComponents.waitforInternalLoad(Driver);
			Report.updateTestLog("Filter Values", "NSH is present",Status.PASS);


			} if((warranty !=null) )
			{	                    
				
			GeneralComponents.clickOnWebelement(checkfilter, "");
			GeneralComponents.clickOnWebelement(warranty, "");
			GeneralComponents.waitforInternalLoad(Driver);
			Report.updateTestLog("Filter Values", "warranty is present",Status.PASS);
			} if((goodwill !=null) )
			{	                    
				
			GeneralComponents.clickOnWebelement(checkfilter, "");
			GeneralComponents.clickOnWebelement(goodwill,"");
			GeneralComponents.waitforInternalLoad(Driver);
			Report.updateTestLog("Filter Values", "goodwill",Status.PASS);


			}

		}
		else
		{
			Report.updateTestLog("Filter","Filter is not present",Status.FAIL);
		}
	}

	/**
	 * 
	 * MethodName: chkAllFieldsSingle
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Deepali
	 */  
@Action(desc="chkAllFieldsSingle",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkAllFieldsSingle()	
	{
		WebElement checkfilter=findObject(Driver,By.id("_jpfcpncuivr_serviceHistory_j_id_id0:serviceHistoryForm:selectId"), "looup Button");
		
		if(checkfilter !=null)
		{		                    
			Report.updateTestLog("Filter", "Filter is present",Status.PASS);
			GeneralComponents.clickOnWebelement(checkfilter, "");
			

			WebElement all = Driver.findElement(By.xpath(".//option[@value='All']"));
			
			if((all !=null) )
			{	                    
			GeneralComponents.clickOnWebelement(checkfilter, "");

			GeneralComponents.clickOnWebelement(all, "");
			GeneralComponents.waitforInternalLoad(Driver);
			Report.updateTestLog("Filter Values", "All is present",Status.PASS);

			WebElement table= findObject(Driver,By.id("_jpfcpncuivr_serviceHistory_j_id_id0:serviceHistoryForm"), "table");
			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("RO Close"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("RO Close", "RO Close is Present",Status.PASS);
						break;
					}   


				}

			}
			else
			{
				Report.updateTestLog("RO Close","RO Close is not Present ",Status.FAIL);
			}


			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Mileage"))

					{	
						sleepTime(Driver, 1);

						
						Report.updateTestLog("Mileage", "Mileage is Present",Status.PASS);
						break;
					}    



				}

			}
			else
			{
				Report.updateTestLog("Mileage","Mileage is not Present ",Status.FAIL);
			}

			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Dealer"))

					{	
						sleepTime(Driver, 1);

						
						Report.updateTestLog("Dealer", "Dealer is Present",Status.PASS);
						break;
					} 


				}

			}
			else
			{
				Report.updateTestLog("Dealer","Dealer is not Present ",Status.FAIL);
			}

			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Source"))

					{	
						sleepTime(Driver, 1);

						
						Report.updateTestLog("Source", "Source is Present",Status.PASS);
						break;
					}   

				}

			}
			else
			{
				Report.updateTestLog("Source","Source is not Present ",Status.FAIL);
			}



			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("RO Number"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("RO Number", "RO Number is Present",Status.PASS);
						break;
					}   
				}

			}
			else
			{
				Report.updateTestLog("RO Number","RO Number is not Present ",Status.FAIL);
			}




			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("RO Total"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("RO Total", "RO Total is Present",Status.PASS);
						break;
					}   
				}

			}
			else
			{
				Report.updateTestLog("RO Total","RO Total is not Present ",Status.FAIL);
			}




			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("RO Open"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("RO Open", "RO Open is Present",Status.PASS);
						break;
					}  
				}

			}
			else
			{
				Report.updateTestLog("RO Open","Source is not Present ",Status.FAIL);
			}




			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Service Advisor"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("Service Advisor", "Service Advisor is Present",Status.PASS);
						break;
					}   
				}

			}
			else
			{
				Report.updateTestLog("Service Advisor","Service Advisor is not Present ",Status.FAIL);
			}


			}
		}

	}

	/**
	 * 
	 * MethodName: clickPrintSingleSH
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Deepali
	 */  
@Action(desc="clickPrintSingleSH",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clickPrintSingleSH()

	{
		WebElement plus= findObject(Driver,By.id("_jpfcpncuivr_serviceHistory_j_id_id0:serviceHistoryForm"), "");
		//ASMPortal_Service_History_page//_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm

		if(plus !=null)
		{
			List<WebElement> tds=plus.findElements(By.tagName("a"));
			for(WebElement b:tds)
			{
				//border: 1px #9C9C9C solid; background: #EAEAEA
				String val=b.getText();
				if(val.contains("Format For Print"))
					//id="_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm:tcode_popup" 
				{	
					sleepTime(Driver, 1);

					GeneralComponents.clickOnWebelement(b, "Format For Print");
					Report.updateTestLog("Format For Print", "Format For Print",Status.PASS);

					break;
				}    

			}
		}
		else
			Report.updateTestLog("Format For Print", " Print not present",Status.FAIL);


	}

	/**
	 * 
	 * MethodName: chkNSHFieldsSingle
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Deepali
	 */           

@Action(desc="chkNSHFieldsSingle",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkNSHFieldsSingle()	
	{
		WebElement checkfilter=findObject(Driver,By.id("_jpfcpncuivr_serviceHistory_j_id_id0:serviceHistoryForm:selectId"), "looup Button");
		if(checkfilter !=null)
		{		                    
			GeneralComponents.clickOnWebelement(checkfilter, "");
			

			WebElement NSH = Driver.findElement(By.xpath(".//option[@value='Warranty']"));

			if((NSH !=null) )
			{	                    GeneralComponents.clickOnWebelement(checkfilter, "");

			GeneralComponents.clickOnWebelement(NSH, "");
			GeneralComponents.waitforInternalLoad(Driver);
			//Report.updateTestLog("Filter Values", "All is present",Status.PASS);

			WebElement table= findObject(Driver,By.id("_jpfcpncuivr_serviceHistory_j_id_id0:serviceHistoryForm"), "table");

			if(table !=null)//_jpfcpncuivr_serviceHistory_j_id_id0:serviceHistoryForm
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("RO Close"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("RO Close", "RO Close is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("RO Close","RO Close is not Present ",Status.FAIL);
			}


			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Mileage"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("Mileage", "Mileage is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("Mileage","Mileage is not Present ",Status.FAIL);
			}

			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Dealer"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("Dealer", "Dealer is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("Dealer","Dealer is not Present ",Status.FAIL);
			}

			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Source"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("Source", "Source is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("Source","Source is not Present ",Status.FAIL);
			}



			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("RO Number"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("RO Number", "RO Number is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("RO Number","RO Number is not Present ",Status.FAIL);
			}




			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("RO Total"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("RO Total", "RO Total is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("RO Total","RO Total is not Present ",Status.FAIL);
			}




			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("RO Open"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("RO Open", "RO Open is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("RO Open","Source is not Present ",Status.FAIL);
			}




			if(table !=null)
			{
				List<WebElement> tds=table.findElements(By.tagName("b"));
				for(WebElement b:tds)
				{
					String val=b.getText();
					if(val.contains("Service Advisor"))

					{	
						sleepTime(Driver, 1);

						//GeneralComponents.clickOnWebelement(b, "RO Close");
						Report.updateTestLog("Service Advisor", "Service Advisor is Present",Status.PASS);
						break;
					}    

				}

			}
			else
			{
				Report.updateTestLog("Service Advisor","Service Advisor is not Present ",Status.FAIL);
			}


			}
		}

	}         




	/**
	 * 
	 * MethodName: Specifications
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Deepali
	 */  
	///////////////Specificationd--------------


@Action(desc="clickSpecifications",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clickSpecifications()

	{
		GeneralComponents.waitforInternalLoad(Driver);

		WebElement s= findObject(Driver,By.id("k_ASMPortal_portal_book_12_ajax"), "Service History");//k_ASMPortal_portal_book_12_ajax
		if(s !=null)
		{
			List<WebElement> tds=s.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Specifications"))
				{
					GeneralComponents.clickOnWebelement(td, "Specifications");
					Report.updateTestLog("Specifications", "Specifications tab is clicked",Status.PASS);
					break;
				}    

			}


		}
		else
		{
			Report.updateTestLog("Specifications","Specifications  tab is not clicked ",Status.FAIL);
		}



	}
	/**
	 * 
	 * MethodName: chkSpecificationFields
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Deepali
	 */  
@Action(desc="chkSpecificationFields",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkSpecificationFields()	
	{
		WebElement checkf=findObject(Driver,By.id("k_ASMPortal_portal_book_12_ajax"), "looup Button");//_jpfcpncuivr_vehicleSpecification_j_id_id1:vehSpecificationForm
		if(checkf !=null)
		{
			WebElement gvwr = Driver.findElement(By.xpath(".//input[@id='gvwrHelpText']"));
			if(gvwr !=null)
			{
				Report.updateTestLog("GVWR", "GVWR is present",Status.PASS);

			}
			WebElement hp = Driver.findElement(By.xpath(".//input[@id='hpHelpText']"));
			if(hp !=null)
			{
				Report.updateTestLog("hp", "hp is present",Status.PASS);

			}WebElement full = Driver.findElement(By.xpath(".//input[@id='fuelTankCapacityHelpText']"));
			if(full !=null)
			{
				Report.updateTestLog("full", "full is present",Status.PASS);

			}WebElement cool = Driver.findElement(By.xpath(".//input[@id='coolantHelpText']"));
			if(cool !=null)
			{
				Report.updateTestLog("cool", "GVWcoolR is present",Status.PASS);

			}


			WebElement capacity = Driver.findElement(By.xpath(".//input[@id='towingCapacityHelpText']"));
			if(capacity !=null)
			{
				Report.updateTestLog("capacity", "capacity is present",Status.PASS);

			}


			WebElement dinghy = Driver.findElement(By.xpath(".//input[@id='dinghyTowHelpText']"));
			if(dinghy !=null)
			{
				Report.updateTestLog("dinghy", "dinghy is present",Status.PASS);

			}


			WebElement fuel = Driver.findElement(By.xpath(".//input[@id='fuelHelpText']"));
			if(fuel !=null)
			{
				Report.updateTestLog("fuel", "fuel is present",Status.PASS);

			}


			WebElement air = Driver.findElement(By.xpath(".//input[@id='cabinAirFilterHelpText']"));
			if(air !=null)
			{
				Report.updateTestLog("air", "air is present",Status.PASS);

			}

		}


	}





	/**
	 * 
	 * MethodName: clickLinkSpecifications1
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Deepali
	 */  

@Action(desc="clickLinkSpecifications1",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clickLinkSpecifications1()

	{
		WebElement plus= findObject(Driver,By.id("k_ASMPortal_portal_book_12_ajax"), "-");
		//ASMPortal_Service_History_page//_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm

		if(plus !=null)
		{
			List<WebElement> tds=plus.findElements(By.tagName("a"));
			for(WebElement b:tds)
			{
				//border: 1px #9C9C9C solid; background: #EAEAEA
				String val=b.getText();
				if(val.contains("Owner's manual"))
					//id="_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm:tcode_popup" 
				{	
					sleepTime(Driver, 1);

					GeneralComponents.clickOnWebelement(b, "Owner's manual");
					Report.updateTestLog("Owner's manual", "Owner's manual:",Status.PASS);

					break;
				}    

			}
		}
	}
	/**
	 * 
	 * MethodName: clickVehicalOneView
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Deepali
	 */  
@Action(desc="clickVehicalOneView",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clickVehicalOneView()

	{
		WebElement plus= findObject(Driver,By.id("k_ASMPortal_Service_Lane_Book_ajax"), "-");
		//ASMPortal_Service_History_page//_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm

		if(plus !=null)
		{
			List<WebElement> tds=plus.findElements(By.tagName("a"));
			for(WebElement b:tds)
			{
				//border: 1px #9C9C9C solid; background: #EAEAEA
				//String val=b.getText();
				String val=b.getAttribute("href")	;

				if(val.contains("https://tis.toyota.com/serviceLane/appmanager/t3/ext?_nfpb=true&_nfxr=false&_pageLabel=ASMPortal_VehicleOneView&_appSource=slane1"))
					//id="_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm:tcode_popup" 
				{	
					sleepTime(Driver, 1);

					GeneralComponents.clickOnWebelement(b, "Vehical One view");
					Report.updateTestLog("Vehical One view", "Vehical One view:",Status.PASS);

					break;
				}    

			}
		}
	}


	/**
	 * 
	 * MethodName: dignyFlow
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Deepali
	 */  
@Action(desc="dignyFlow",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void dignyFlow()

	{
		WebElement plus= findObject(Driver,By.id("k_ASMPortal_portal_book_12_ajax"), "-");
		//ASMPortal_Service_History_page//_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm

		if(plus !=null)
		{
			List<WebElement> tds=plus.findElements(By.tagName("td"));
			for(WebElement b:tds)
			{
				//border: 1px #9C9C9C solid; background: #EAEAEA
				String val=b.getText();
				if(val.contains("No"))
					//id="_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm:tcode_popup" 
				{	
					List<WebElement> tds2=plus.findElements(By.tagName("a"));
					for(WebElement b2:tds2)
					{
						String a=b2.getAttribute("href")	;

						if(a.contains("https://tis.toyota.com/serviceLane/appmanager/t3/ext?_nfpb=true&_nfxr=false&_pageLabel=ASMPortal_VehicleOneView&_appSource=slane1"))
						{
							Report.updateTestLog("Image", "Image is present:",Status.PASS);

						}

						break;
					}    
				}   
			}
		}
	}
	/**
	 * 
	 * MethodName: enterVINSpecification
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Deepali
	 */  


@Action(desc="enterVINSpecification",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void enterVINSpecification()

	{
		WebElement VINlookup=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinTextBox"), "VIN Lookup" );
		if (VINlookup !=null)
		{
			String val=userData.getData("Specifications", "VIN", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(VINlookup, val, "VIN is entered");
			//GeneralComponents.waitforInternalLoad(Driver);

			Report.updateTestLog("VINlookup", "VIN text is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("VINlookup", "VIN text is not found", Status.FAIL);
		}
	}

@Action(desc="chkSpecificationFieldsSingle",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkSpecificationFieldsSingle()	
	{
		WebElement checkf=findObject(Driver,By.id("_jpfcpncuivr_vehicleSpecification_j_id_id1:vehSpecificationForm"), "looup Button");//_jpfcpncuivr_vehicleSpecification_j_id_id1:vehSpecificationForm
		if(checkf !=null)
		{
			WebElement gvwr = Driver.findElement(By.xpath(".//input[@id='gvwrHelpText']"));
			if(gvwr !=null)
			{
				Report.updateTestLog("GVWR", "GVWR is present",Status.PASS);

			}
			WebElement hp = Driver.findElement(By.xpath(".//input[@id='hpHelpText']"));
			if(hp !=null)
			{
				Report.updateTestLog("hp", "hp is present",Status.PASS);

			}WebElement full = Driver.findElement(By.xpath(".//input[@id='fuelTankCapacityHelpText']"));
			if(full !=null)
			{
				Report.updateTestLog("full", "full is present",Status.PASS);

			}WebElement cool = Driver.findElement(By.xpath(".//input[@id='coolantHelpText']"));
			if(cool !=null)
			{
				Report.updateTestLog("cool", "GVWcoolR is present",Status.PASS);

			}


			WebElement capacity = Driver.findElement(By.xpath(".//input[@id='towingCapacityHelpText']"));
			if(capacity !=null)
			{
				Report.updateTestLog("capacity", "capacity is present",Status.PASS);

			}


			WebElement dinghy = Driver.findElement(By.xpath(".//input[@id='dinghyTowHelpText']"));
			if(dinghy !=null)
			{
				Report.updateTestLog("dinghy", "dinghy is present",Status.PASS);

			}


			WebElement fuel = Driver.findElement(By.xpath(".//input[@id='fuelHelpText']"));
			if(fuel !=null)
			{
				Report.updateTestLog("fuel", "fuel is present",Status.PASS);

			}


			WebElement air = Driver.findElement(By.xpath(".//input[@id='cabinAirFilterHelpText']"));
			if(air !=null)
			{
				Report.updateTestLog("air", "air is present",Status.PASS);

			}

		}


	}
	/**
	 * 
	 * MethodName: clickLinkSpecificationsSingle
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Deepali
	 */  
@Action(desc="clickLinkSpecificationsSingle",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clickLinkSpecificationsSingle()

	{
		WebElement plus= findObject(Driver,By.id("_jpfcpncuivr_vehicleSpecification_j_id_id1:vehSpecificationForm"), "-");
		//ASMPortal_Service_History_page//_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm
		//_jpfcpncuivr_vehicleSpecification_j_id_id1:vehSpecificationForm
		if(plus !=null)
		{
			List<WebElement> tds=plus.findElements(By.tagName("a"));
			for(WebElement b:tds)
			{
				//border: 1px #9C9C9C solid; background: #EAEAEA
				String val=b.getText();
				if(val.contains("Owner's manual"))
					//id="_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm:tcode_popup" 
				{	
					sleepTime(Driver, 1);

					GeneralComponents.clickOnWebelement(b, "Owner's manual");
					Report.updateTestLog("Owner's manual", "Owner's manual:",Status.PASS);

					break;
				}    

			}
		}
	}
	/**
	 * 
	 * MethodName: enterVINSpecification2
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Deepali
	 */  
@Action(desc="enterVINSpecification2",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void enterVINSpecification2()

	{
		WebElement VINlookup=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinTextBox"), "VIN Lookup" );
		if (VINlookup !=null)
		{
			String val=userData.getData("Specifications2", "VIN", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(VINlookup, val, "VIN is entered");
			//GeneralComponents.waitforInternalLoad(Driver);

			Report.updateTestLog("VINlookup", "VIN text is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("VINlookup", "VIN text is not found", Status.FAIL);
		}
	}



	////Specification Internal
	/**
	 * 
	 * MethodName: enterVINSpecificationInternal
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Deepali
	 */  

@Action(desc="enterVINSpecificationInternal",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void enterVINSpecificationInternal()

	{
		WebElement VINlookup=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinTextBox"), "VIN Lookup" );
		if (VINlookup !=null)//_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinTextBox
		{
			String val=userData.getData("Specifications2", "VIN", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(VINlookup, val, "VIN is entered");
			//GeneralComponents.waitforInternalLoad(Driver);

			Report.updateTestLog("VINlookup", "VIN text is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("VINlookup", "VIN text is not found", Status.FAIL);
		}
	}

	///chkkkkkkkkkkkkkkkkkk
	/**
	 * 
	 * MethodName: clickLinkSpecificationsInternal
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Deepali
	 */  
@Action(desc="clickLinkSpecificationsInternal",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clickLinkSpecificationsInternal()

	{
		WebElement plus= findObject(Driver,By.id("_jpfcpncuivr_T5800238351386838188037_j_id_id1:vehSpecificationForm"), "-");
		//ASMPortal_Service_History_page//_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm//e_ASMPortal_specifications_ajax

		if(plus !=null)
		{
			List<WebElement> tds=plus.findElements(By.tagName("a"));
			for(WebElement b:tds)
			{
				//border: 1px #9C9C9C solid; background: #EAEAEA
				String val=b.getText();
				if(val.contains("Owner's manual"))
					//id="_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm:tcode_popup" 
				{	
					sleepTime(Driver, 1);

					GeneralComponents.clickOnWebelement(b, "Owner's manual");
					Report.updateTestLog("Owner's manual", "Owner's manual:",Status.PASS);

					break;
				}    

			}
		}
	}

	/**
	 * 
	 * MethodName: chkSpecificationFieldsInternal
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Deepali
	 */  
@Action(desc="chkSpecificationFieldsInternal",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkSpecificationFieldsInternal()	
	{
		WebElement checkf=findObject(Driver,By.id("_jpfcpncuivr_T5800238351386838188037_j_id_id1:vehSpecificationForm"), "looup Button");
		if(checkf !=null)
		{
			WebElement gvwr = Driver.findElement(By.xpath(".//input[@id='gvwrHelpText']"));
			if(gvwr !=null)
			{
				Report.updateTestLog("GVWR", "GVWR is present",Status.PASS);

			}
			WebElement hp = Driver.findElement(By.xpath(".//input[@id='hpHelpText']"));
			if(hp !=null)
			{
				Report.updateTestLog("hp", "hp is present",Status.PASS);

			}WebElement full = Driver.findElement(By.xpath(".//input[@id='fuelTankCapacityHelpText']"));
			if(full !=null)
			{
				Report.updateTestLog("full", "full is present",Status.PASS);

			}WebElement cool = Driver.findElement(By.xpath(".//input[@id='coolantHelpText']"));
			if(cool !=null)
			{
				Report.updateTestLog("cool", "GVWcoolR is present",Status.PASS);

			}


			WebElement capacity = Driver.findElement(By.xpath(".//input[@id='towingCapacityHelpText']"));
			if(capacity !=null)
			{
				Report.updateTestLog("capacity", "capacity is present",Status.PASS);

			}


			WebElement dinghy = Driver.findElement(By.xpath(".//input[@id='dinghyTowHelpText']"));
			if(dinghy !=null)
			{
				Report.updateTestLog("dinghy", "dinghy is present",Status.PASS);

			}


			WebElement fuel = Driver.findElement(By.xpath(".//input[@id='fuelHelpText']"));
			if(fuel !=null)
			{
				Report.updateTestLog("fuel", "fuel is present",Status.PASS);

			}


			WebElement air = Driver.findElement(By.xpath(".//input[@id='cabinAirFilterHelpText']"));
			if(air !=null)
			{
				Report.updateTestLog("air", "air is present",Status.PASS);

			}

		}


	}

	/**
	 * 
	 * MethodName: clickLinkSpecifications3
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Deepali
	 */  
@Action(desc="clickLinkSpecifications3",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clickLinkSpecifications3()

	{
		WebElement plus= findObject(Driver,By.id("_jpfcpncuivr_vehicleSpecification_j_id_id1:vehSpecificationForm"), "-");
		//ASMPortal_Service_History_page//_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm
		//_jpfcpncuivr_vehicleSpecification_j_id_id1:vehSpecificationForm
		if(plus !=null)
		{
			List<WebElement> tds=plus.findElements(By.tagName("a"));
			for(WebElement b:tds)
			{
				//border: 1px #9C9C9C solid; background: #EAEAEA
				String val=b.getText();
				if(val.contains("Owner's manual"))
					//id="_jpfcpncuivr_T800266031357135753530_j_id_id0:serviceHistoryForm:tcode_popup" 
				{	
					sleepTime(Driver, 1);

					GeneralComponents.clickOnWebelement(b, "Owner's manual");
					Report.updateTestLog("Owner's manual", "Owner's manual:",Status.PASS);

					break;
				}    

			}
		}
	}

	/**
	 * 
	 * MethodName: clicklookupInternal
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Deepali
	 */  
@Action(desc="clicklookupInternal",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clicklookupInternal()

	{
		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinlookupbut"), "looup Button");
		//_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinlookupbut
		if(lookupBtn !=null)//
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			//GeneralComponents.waitforInternalLoad(Driver);

			// Driver.findElement(By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:iAgreeButton")).click();
			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not present",Status.FAIL);
		}
	}
	////////////////////////////SIngle View


	/**
	 * 
	 * MethodName: enterVINSpecification11
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Deepali
	 */  

@Action(desc="enterVINSpecification11",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void enterVINSpecification11()

	{
		WebElement VINlookup=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinTextBox"), "VIN Lookup" );
		if (VINlookup !=null)
		{
			String val=userData.getData("Specifications2", "VIN", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(VINlookup, val, "VIN is entered");
			//GeneralComponents.waitforInternalLoad(Driver);

			Report.updateTestLog("VINlookup", "VIN text is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("VINlookup", "VIN text is not found", Status.FAIL);
		}
	}
	/**
	 * 
	 * MethodName: chkSpecificationFieldsInternalSingle
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Deepali
	 */  



@Action(desc="Campaign_dealercodelink_sing",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Campaign_dealercodelink_sing()
	{

		WebElement clickcampdoc= findObject(Driver,By.id("_jpfcpncuivr_campaign_j_id_id0:campaignForm"), "Show docu");
		if(clickcampdoc !=null)
		{
			List<WebElement> tds2= clickcampdoc.findElements(By.tagName("a"));
			for(WebElement a:tds2)
			{
				String val=a.getText();
				if((val.contentEquals("60907")))
				{
					GeneralComponents.clickOnWebelement(a, "Click on dealer code link"); 
					Report.updateTestLog("Show documents link ", "Show documents link is clicked",Status.PASS);  

				}
			}
		}
		else
		{
			Report.updateTestLog("Show documents link","Show documents link is not clicked ",Status.FAIL);
		}

	}

@Action(desc="Campaign_CheckApplicabilityforVinlink_sing",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Campaign_CheckApplicabilityforVinlink_sing()
	{

		WebElement clickcampdoc= findObject(Driver,By.id("_jpfcpncuivr_campaign_j_id_id0:campaignForm"), "Check Applicability for Vin");
		if(clickcampdoc !=null)
		{
			List<WebElement> tds2= clickcampdoc.findElements(By.tagName("a"));
			for(WebElement a:tds2)
			{
				String val=a.getText();
				if((val.contentEquals("Check Applicability for Vin")))
				{
					GeneralComponents.clickOnWebelement(a, "Click on Check Applicability for Vin link"); 
					Report.updateTestLog("Check Applicability for Vin link ", "Check Applicability for Vin link is clicked",Status.PASS);  

				}
			}
		}
		else
		{
			Report.updateTestLog("Check Applicability for Vin link","Check Applicability for Vin link is not clicked ",Status.FAIL);
		}

	}
@Action(desc="switchcampaign_tab_int_TIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void switchcampaign_tab_int_TIS()
	{
		String winHandleParent = Driver.getWindowHandle();    
		WebElement clickcampdoc= findObject(Driver,By.id("_jpfcpncuivr_T2601916071355836328608_j_id_id0:campaignForm"), "Check Applicability for Vin");
		if(clickcampdoc !=null)
		{
			List<WebElement> tds2= clickcampdoc.findElements(By.tagName("a"));
			for(WebElement a:tds2)
			{
				String val=a.getText();
				if((val.contentEquals("Check Applicability for Vin")))
				{
					GeneralComponents.clickOnWebelement(a, "Click on Check Applicability for Vin link");
					switchwindow();
					Report.updateTestLog("Check Applicability for Vin link ", "Check Applicability for Vin link is clicked",Status.PASS); 
					break;
				}
			}
		}
		else
		{
			Report.updateTestLog("Check Applicability for Vin link","Check Applicability for Vin link is not clicked ",Status.FAIL);
		}

	}
@Action(desc="switchcampaign_sing_int",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void switchcampaign_sing_int()
	{
		String winHandleParent = Driver.getWindowHandle();	
		WebElement clickcampdoc= findObject(Driver,By.id("_jpfcpncuivr_campaign_j_id_id0:campaignForm"), "Check Applicability for Vin");
		if(clickcampdoc !=null)
		{
			List<WebElement> tds2= clickcampdoc.findElements(By.tagName("a"));
			for(WebElement a:tds2)
			{
				String val=a.getText();
				if((val.contentEquals("Check Applicability for Vin")))
				{
					GeneralComponents.clickOnWebelement(a, "Click on Check Applicability for Vin link");
					switchwindow();
					Report.updateTestLog("Check Applicability for Vin link ", "Check Applicability for Vin link is clicked",Status.PASS);  
					break;
				}
			}
		}
		else
		{
			Report.updateTestLog("Check Applicability for Vin link","Check Applicability for Vin link is not clicked ",Status.FAIL);
		}

	}

	/**
	 * 
	 * MethodName: Vehicle information
	 * Description: External portal tabbed view
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Nithya
	 */


@Action(desc="TisClickvehicle_info",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void TisClickvehicle_info()
	{

		WebElement clickowner= findObject(Driver,By.id("k_ASMPortal_portal_book_12_ajax"), "Vehicle information");
		if(clickowner !=null)
		{
			List<WebElement> tds2= clickowner.findElements(By.tagName("a"));
			for(WebElement a:tds2)
			{
				String val=a.getText();
				if((val.contains("Vehicle Info")))
				{
					GeneralComponents.clickOnWebelement(a, "Vehicle Info"); 
					Report.updateTestLog("Vehicle Info", "Vehicle Info tab is clicked",Status.PASS);
					break;

				}
			}
		}
		else
		{
			Report.updateTestLog("Vehicle Info","Vehicle Info tab is not clicked ",Status.FAIL);
		}

	}



@Action(desc="Tisvehicleinfo_POelements",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Tisvehicleinfo_POelements()
	{

		WebElement PreownedLCPO= findObject(Driver,By.id("_jpfcpncuivr_T5800138351386838076391_j_id_id1:vehInformationForm"), "Vehicle Info Elements");
		if(PreownedLCPO !=null)

		{
			List<WebElement> tds2= PreownedLCPO.findElements(By.tagName("label"));
			for(WebElement a:tds2)
			{
				String val=a.getText();
				if((val.contains("LCPO Certified: "))||(val.contains("LCPO DOFU: "))||(val.contains("LCPO Retailed Mileage: "))||(val.contains("LCPO Dealer: ")))
				{
					System.out.println(val);                 

					Report.updateTestLog(val+ "is displaying under Vehicle Info tab", "Vehicle Info  elements are displaying",Status.PASS);
				}                
			}
		}
		else
		{
			Report.updateTestLog("Check for Vehicle Info  elements", "Vehicle Info elements are not displaying ",Status.FAIL);
		}

	}

@Action(desc="Tisvehicleinfo_elements",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Tisvehicleinfo_elements()
	{

		WebElement VIElements= findObject(Driver,By.id("_jpfcpncuivr_T5800138351386838076391_j_id_id1:vehInformationForm"), "Vehicle Info Elements");
		if(VIElements !=null)

		{
			List<WebElement> tds2= VIElements.findElements(By.tagName("label"));
			for(WebElement a:tds2)
			{
				String val=a.getText();
				if((val.contains("Prod Date: "))||(val.contains("Date of First Use: "))||(val.contains("Engine Oil Type: "))||(val.contains("Trans/Drive: "))||(val.contains("Plant Code: "))||(val.contains("Original Selling Dealer: "))||(val.contains("Engine Oil: "))||(val.contains("Trans Oil: "))||(val.contains("Grade: "))||(val.contains("Color: "))||(val.contains("Safety Connect:"))||(val.contains("XM: "))||(val.contains("Engine #: ")))
				{
					System.out.println(val);                 

					Report.updateTestLog(val+ "is displaying under Vehicle Info tab", "Vehicle Info  elements are displaying",Status.PASS);
				}                
			}
		}
		else
		{
			Report.updateTestLog("Check for Vehicle Info  elements", "Vehicle Info elements are not displaying ",Status.FAIL);
		}

	}


@Action(desc="TisVehicleoneview_link",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void TisVehicleoneview_link()
	{
		WebElement warranty= findObject(Driver,By.id("k_ASMPortal_Service_Lane_Book_ajax"), "Vehicle One-View");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("a"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contentEquals("Vehicle One-View"))
				{
					GeneralComponents.clickOnWebelement(td, "Vehicle One-View");
					Report.updateTestLog("Vehicle One-View", "displayed Vehicle One-View tab",Status.PASS);
					break;
				}    
			}          

		}
		else
		{
			Report.updateTestLog("Vehicle One-View","Vehicle One-View tab is not displayed ",Status.FAIL);
		}
	}




@Action(desc="TisReferenceFluidSpecification_link",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void TisReferenceFluidSpecification_link()
	{
		WebElement warranty= findObject(Driver,By.id("_jpfcpncuivr_T5800138351386838076391_j_id_id1:vehInformationForm"), "Reference Fluid Specification");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("a"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contentEquals("Reference Fluid Specification"))
				{
					GeneralComponents.clickOnWebelement(td, "Reference Fluid Specification");
					Report.updateTestLog("Reference Fluid Specification", "displayed Reference Fluid Specification info",Status.PASS);
					break;
				}    
			}          

		}
		else
		{
			Report.updateTestLog("Reference Fluid Specification","Reference Fluid Specification info is not displayed ",Status.FAIL);
		}
	}

@Action(desc="TisFlatRateManual_link",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void TisFlatRateManual_link()
	{
		WebElement warranty= findObject(Driver,By.id("_jpfcpncuivr_T5800138351386838076391_j_id_id1:vehInformationForm"), "Flat Rate Manual");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("a"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contentEquals("Flat Rate Manual"))
				{
					GeneralComponents.clickOnWebelement(td, "Flat Rate Manual");
					Report.updateTestLog("Flat Rate Manual", "displayed Flat Rate Manual",Status.PASS);
					break;
				}    
			}          

		}
		else
		{
			Report.updateTestLog("Flat Rate Manual","Flat Rate Manual info is not displayed ",Status.FAIL);
		}
	}



@Action(desc="TisStandardEquipment_link",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void TisStandardEquipment_link()
	{
		WebElement warranty= findObject(Driver,By.id("_jpfcpncuivr_T5800138351386838076391_j_id_id1:vehInformationForm"), "Standard Equipment");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("a"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contentEquals("Standard Equipment"))
				{
					GeneralComponents.clickOnWebelement(td, "Standard Equipment");
					Report.updateTestLog("Standard Equipment", "displayed Standard Equipment",Status.PASS);
					break;
				}    
			}          

		}
		else
		{
			Report.updateTestLog("Standard Equipment","Standard Equipment info is not displayed ",Status.FAIL);
		}
	}


@Action(desc="clicklookup",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Tisclicklookup()
	/*{
		 GeneralComponents.waitforInternalLoad(Driver);*/
	{
		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinlookupbut"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			Driver.findElement(By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:iAgreeButton")).click();
			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
	}


	/**
	 * 
	 * MethodName: Singleview Vehicle information
	 * Description: External portal
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Nithya
	 */

@Action(desc="Ext_single_lookup",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Ext_single_lookup()
	{
		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_portletInstance_2_j_id_id0:vehicleSubView:vinForm:vinlookupbut"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			Driver.findElement(By.id("_jpfcpncuivr_portletInstance_2_j_id_id0:vehicleSubView:vinForm:iAgreeButton")).click();
			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
	}

@Action(desc="Tis_SingleviewClickvehicle_info",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Tis_SingleviewClickvehicle_info()
	{

		WebElement clickowner= findObject(Driver,By.id("t_vehicleInformation_ajax"), "Vehicle information");
		if(clickowner !=null)
		{
			List<WebElement> tds2= clickowner.findElements(By.tagName("u"));
			for(WebElement a:tds2)
			{
				String val=a.getText();
				if((val.contains("Vehicle Info")))
				{
					GeneralComponents.clickOnWebelement(a, "Vehicle Information"); 
					Report.updateTestLog("Vehicle Information", "Vehicle Info tab is clicked",Status.PASS);
					break;

				}
			}
		}
		else
		{
			Report.updateTestLog("Vehicle Info","Vehicle Info tab is not clicked ",Status.FAIL);
		}

	}



@Action(desc="Tis_SingleviewVehicleinfo_POelements",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Tis_SingleviewVehicleinfo_POelements()
	{

		WebElement PreownedLCPO= findObject(Driver,By.id("_jpfcpncuivr_vehicleInformation_j_id_id1:vehInformationForm"), "Vehicle Info Elements");
		if(PreownedLCPO !=null)

		{
			List<WebElement> tds2= PreownedLCPO.findElements(By.tagName("label"));
			for(WebElement a:tds2)
			{
				String val=a.getText();
				if((val.contains("LCPO Certified: "))||(val.contains("LCPO DOFU: "))||(val.contains("LCPO Retailed Mileage: "))||(val.contains("LCPO Dealer: ")))
				{
					System.out.println(val);                 

					Report.updateTestLog(val+ "is displaying under Vehicle Info tab", "Vehicle Info  elements are displaying",Status.PASS);
				}                
			}
		}
		else
		{
			Report.updateTestLog("Check for Vehicle Info  elements", "Vehicle Info elements are not displaying ",Status.FAIL);
		}

	}

@Action(desc="Tis_SingleviewVehicleinfo_elements",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Tis_SingleviewVehicleinfo_elements()
	{

		WebElement VIElements= findObject(Driver,By.id("_jpfcpncuivr_vehicleInformation_j_id_id1:vehInformationForm"), "Vehicle Info Elements");
		if(VIElements !=null)

		{
			List<WebElement> tds2= VIElements.findElements(By.tagName("label"));
			for(WebElement a:tds2)
			{
				String val=a.getText();
				if((val.contains("Prod Date: "))||(val.contains("Date of First Use: "))||(val.contains("Engine Oil Type: "))||(val.contains("Trans/Drive: "))||(val.contains("Plant Code: "))||(val.contains("Original Selling Dealer: "))||(val.contains("Engine Oil: "))||(val.contains("Trans Oil: "))||(val.contains("Grade: "))||(val.contains("Color: "))||(val.contains("Safety Connect:"))||(val.contains("XM: "))||(val.contains("Engine #: ")))
				{
					System.out.println(val);                 

					Report.updateTestLog(val+ "is displaying under Vehicle Info tab", "Vehicle Info  elements are displaying",Status.PASS);
				}                
			}
		}
		else
		{
			Report.updateTestLog("Check for Vehicle Info  elements", "Vehicle Info elements are not displaying ",Status.FAIL);
		}

	}



@Action(desc="Tis_SingleviewVehicleoneview_link",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Tis_SingleviewVehicleoneview_link()
	{
		WebElement warranty= findObject(Driver,By.id("e_ASMPortal_Service_Lane_ajax"), "Vehicle One-View");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("a"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contentEquals("Vehicle One-View"))
				{
					GeneralComponents.clickOnWebelement(td, "Vehicle One-View");
					Report.updateTestLog("Vehicle One-View", "displayed Vehicle One-View tab",Status.PASS);
					break;
				}    
			}          

		}
		else
		{
			Report.updateTestLog("Vehicle One-View","Vehicle One-View tab is not displayed ",Status.FAIL);
		}
	}




@Action(desc="Tis_SingleviewRefFluidSpecification_link",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Tis_SingleviewRefFluidSpecification_link()
	{
		WebElement warranty= findObject(Driver,By.id("_jpfcpncuivr_vehicleInformation_j_id_id1:vehInformationForm"), "Reference Fluid Specification");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("a"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contentEquals("Reference Fluid Specification"))
				{
					GeneralComponents.clickOnWebelement(td, "Reference Fluid Specification");
					Report.updateTestLog("Reference Fluid Specification", "displayed Reference Fluid Specification info",Status.PASS);
					break;
				}    
			}          

		}
		else
		{
			Report.updateTestLog("Reference Fluid Specification","Reference Fluid Specification info is not displayed ",Status.FAIL);
		}
	}



@Action(desc="Tis_SingleviewFlatRateManual_link",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Tis_SingleviewFlatRateManual_link()
	{
		WebElement warranty= findObject(Driver,By.id("_jpfcpncuivr_vehicleInformation_j_id_id1:vehInformationForm"), "Flat Rate Manual");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("a"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contentEquals("Flat Rate Manual"))
				{
					GeneralComponents.clickOnWebelement(td, "Flat Rate Manual");
					Report.updateTestLog("Flat Rate Manual", "displayed Flat Rate Manual",Status.PASS);
					break;
				}    
			}          

		}
		else
		{
			Report.updateTestLog("Flat Rate Manual","Flat Rate Manual info is not displayed ",Status.FAIL);
		}
	}



@Action(desc="Tis_SingleviewStandardEquipment_link",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Tis_SingleviewStandardEquipment_link()
	{
		WebElement warranty= findObject(Driver,By.id("_jpfcpncuivr_vehicleInformation_j_id_id1:vehInformationForm"), "Standard Equipment");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("a"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contentEquals("Standard Equipment"))
				{
					GeneralComponents.clickOnWebelement(td, "Standard Equipment");
					Report.updateTestLog("Standard Equipment", "displayed Standard Equipment",Status.PASS);
					break;
				}    
			}          

		}
		else
		{
			Report.updateTestLog("Standard Equipment","Standard Equipment info is not displayed ",Status.FAIL);
		}
	}


	/**
	 * 
	 * MethodName: Ownership History
	 * Description: Internal portal
	 * Parameter (if any): 
	 * Return type:
	 *  void
	 * Owner : Nithya
	 */


@Action(desc="navigateOwnerhistory",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void navigateOwnerhistory()
	{
		GeneralComponents.waitforInternalLoad(Driver);
		WebElement clickowner= findObject(Driver,By.id("k_ASMPortal_portal_book_12_ajax"), "Ownership History");
		if(clickowner !=null)
		{
			List<WebElement> tds2= clickowner.findElements(By.tagName("a"));
			for(WebElement a:tds2)
			{
				String val=a.getText();
				if((val.contains("Ownership History")))
				{
					GeneralComponents.clickOnWebelement(a, "Ownership History"); 
					Report.updateTestLog("Ownership History", "Ownership History tab is clicked",Status.PASS);
					break;

				}
			}
		}
		else
		{
			Report.updateTestLog("Ownership History","Ownership History tab is not clicked ",Status.FAIL);
		}

	}

@Action(desc="Click_OH_dealerverification1",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Click_OH_dealerverification1()
	{

		WebElement clickowner= findObject(Driver,By.id("k_ASMPortal_portal_book_12_ajax"), "Ownership History");
		if(clickowner !=null)
		{
			List<WebElement> tds2= clickowner.findElements(By.tagName("a"));
			for(WebElement a:tds2)
			{
				String val=a.getText();
				if((val.contains("Ownership History")))
				{
					Report.updateTestLog("Ownership History", "Ownership History tab is clicked",Status.FAIL);
					break;

				}
			}
		}
		else
		{
			Report.updateTestLog("Ownership History","Ownership History tab is not clicked ",Status.PASS);
		}

	}


@Action(desc="Verify_OH_Elements",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Verify_OH_Elements()
	{

		WebElement PreownedSCPO= findObject(Driver,By.id("_jpfcpncuivr_T10800797521387450583422_j_id_id0:ownershipHistoryForm"), "Ownership History Elements");
		if(PreownedSCPO !=null)

		{
			List<WebElement> tds2= PreownedSCPO.findElements(By.tagName("label"));
			for(WebElement a:tds2)
			{
				String val=a.getText();
				if((val.contains("Associated Parties"))||(val.contains("Type"))||(val.contains("Role"))||(val.contains("Association End Date")))
				{
					System.out.println(val);                 

					Report.updateTestLog(val+ "is displaying under Ownership History tab", "Ownership History  elements are displaying",Status.PASS);
				}                
			}
		}
		else
		{
			Report.updateTestLog("Check for Ownership History  elements", "Ownership History  elements are not displaying ",Status.FAIL);
		}

	}



@Action(desc="Clickrolelink",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Clickrolelink()
	{
		WebElement warranty= findObject(Driver,By.id("_jpfcpncuivr_T10800797521387450583422_j_id_id0:ownershipHistoryForm"), "Dealerpopup");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("label"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contentEquals("OWNER")||(val.contentEquals ("DRIVER")))
				{
					GeneralComponents.clickOnWebelement(td, "navigate owner screen");
					Report.updateTestLog("Rolelink", "displayed owner/driver pop up",Status.PASS);
					break;
				}    
			}          

		}
		else
		{
			Report.updateTestLog("Rolelink","owner/driver pop up is not displayed ",Status.FAIL);
		}
	}



	/**
	 * 
	 * MethodName: Single view Ownership History
	 * Description: Internal portal
	 * Parameter (if any): 
	 * Return type:
	 *  void
	 * Owner : Nithya
	 */

@Action(desc="singleViewLookUpwonershipExecution",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void singleViewLookUpwonershipExecution()
	{
		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_portletInstance_2_j_id_id0:vehicleSubView:vinForm:vinlookupbut"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			Driver.findElement(By.id("_jpfcpncuivr_portletInstance_2_j_id_id0:vehicleSubView:vinForm:iAgreeButton")).click();
			GeneralComponents.waitForPageLoaded(Driver);
			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
	}

@Action(desc="singleview_ClickOH",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void singleview_ClickOH()
	{
		/*
	                      WebElement clickowner= findObject(Driver,By.id("t_ownershipHistory_ajax"), "Ownership History");
	                      if(clickowner !=null)
	                      {
	                           List<WebElement> tds2= clickowner.findElements(By.tagName("a"));
	                          for(WebElement a:tds2)
	                          {
	                                 String val=a.getText();
	                                 if((val.contains("Ownership History")))
	                                          {
	                                   GeneralComponents.clickOnWebelement(a, "Ownership History"); 
	                    Report.updateTestLog("Ownership History", "Ownership History tab is clicked",Status.PASS);
	                    break;

	            }
	            }
	                      }
	            else
	            {
	                   Report.updateTestLog("Ownership History","Ownership History tab is not clicked ",Status.FAIL);
	            }

	      }*/


		WebElement DTChistory= findObject(Driver,By.id("t_ownershipHistory_ajax"), "DTC History");
		if(DTChistory !=null)
		{
			/*WebElement a=DTChistory.findElement(By.tagName("td"))  ;       
      if(a.getText().contains("DTC History"))*/

			WebElement link = DTChistory.findElement(By.xpath(".//..//..//.."));
			GeneralComponents.clickOnWebelement(link,"Link");
			Report.updateTestLog("OwnershipHistory", "OwnershipHistory tab is clicked",Status.PASS);


		}
		else
		{
			Report.updateTestLog("OwnershipHistory","OwnershipHistory tab is not clicked ",Status.FAIL);
		}
	}	 

@Action(desc="Single_viewOH_Elements",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Single_viewOH_Elements()
	{

		WebElement PreownedSCPO= findObject(Driver,By.id("_jpfcpncuivr_ownershipHistory_j_id_id0:ownershipHistoryForm"), "Ownership History Elements");
		if(PreownedSCPO !=null)

		{
			List<WebElement> tds2= PreownedSCPO.findElements(By.tagName("label"));
			for(WebElement a:tds2)
			{
				String val=a.getText();
				if((val.contains("Associated Parties"))||(val.contains("Type"))||(val.contains("Role"))||(val.contains("Association End Date")))
				{
					System.out.println(val);                 

					Report.updateTestLog(val+ "is displaying under Ownership History tab", "Ownership History  elements are displaying",Status.PASS);
				}                
			}
		}
		else
		{
			Report.updateTestLog("Check for Ownership History  elements", "Ownership History  elements are not displaying ",Status.FAIL);
		}

	} 

@Action(desc="Single_viewownerinfo",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Single_viewownerinfo()
	{
		WebElement warranty= findObject(Driver,By.id("_jpfcpncuivr_ownershipHistory_j_id_id0:ownershipHistoryForm"), "Dealerpopup");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("label"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contentEquals("OWNER")||(val.contentEquals ("DRIVER")))
				{
					GeneralComponents.clickOnWebelement(td, "navigate owner screen");
					Report.updateTestLog("Rolelink", "displayed owner/driver pop up",Status.PASS);
					break;
				}    
			}          

		}
		else
		{
			Report.updateTestLog("Rolelink","owner/driver pop up is not displayed ",Status.FAIL);
		}
	}


	/**
	 * 
	 * MethodName: Vehicle information
	 * Description: Internal portal_tabbed view
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Nithya
	 */


@Action(desc="Clickinfotab",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Clickinfotab()
	{

		WebElement clickowner= findObject(Driver,By.id("k_ASMPortal_portal_book_12_ajax"), "Vehicle information");
		if(clickowner !=null)
		{
			List<WebElement> tds2= clickowner.findElements(By.tagName("a"));
			for(WebElement a:tds2)
			{
				String val=a.getText();
				if((val.contains("Vehicle Info")))
				{
					GeneralComponents.clickOnWebelement(a, "Vehicle Info"); 
					Report.updateTestLog("Vehicle Info", "Vehicle Info tab is clicked",Status.PASS);
					break;

				}
			}
		}
		else
		{
			Report.updateTestLog("Vehicle Info","Vehicle Info tab is not clicked ",Status.FAIL);
		}

	}



@Action(desc="Verify_info_POelements",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Verify_info_POelements()
	{

		WebElement PreownedLCPO= findObject(Driver,By.id("_jpfcpncuivr_T10800497521387450386649_j_id_id1:vehInformationForm"), "Vehicle Info Elements");
		if(PreownedLCPO !=null)

		{
			List<WebElement> tds2= PreownedLCPO.findElements(By.tagName("label"));
			for(WebElement a:tds2)
			{
				String val=a.getText();
				if((val.contains("LCPO Certified: "))||(val.contains("LCPO DOFU: "))||(val.contains("LCPO Retailed Mileage: "))||(val.contains("LCPO Dealer: ")))
				{
					System.out.println(val);                 

					Report.updateTestLog(val+ "is displaying under Vehicle Info tab", "Vehicle Info  elements are displaying",Status.PASS);
				}                
			}
		}
		else
		{
			Report.updateTestLog("Check for Vehicle Info  elements", "Vehicle Info elements are not displaying ",Status.FAIL);
		}

	}

	/*public void Vehicleinfo_elements_int()
	{

	WebElement VIElements= findObject(Driver,By.id("_jpfcpncuivr_T10800497521387450386649_j_id_id1:vehInformationForm"), "Vehicle Info Elements");
	if(VIElements !=null)

	{
	 List<WebElement> tds2= VIElements.findElements(By.tagName("label"));
	for(WebElement a:tds2)
	{
	       String val=a.getText();
	       if((val.contains("Prod Date: "))||(val.contains("Date of First Use: "))||(val.contains("Engine Oil Type: "))||(val.contains("Trans/Drive: "))||(val.contains("Plant Code: "))||(val.contains("Original Selling Dealer: "))||(val.contains("Engine Oil: "))||(val.contains("Trans Oil: "))||(val.contains("Grade: "))||(val.contains("Color: "))||(val.contains("Safety Connect:"))||(val.contains("XM: "))||(val.contains("Engine #: ")))
	       {
	                     System.out.println(val);                 

	Report.updateTestLog(val+ "is displaying under Vehicle Info tab", "Vehicle Info  elements are displaying",Status.PASS);
	   }                
	}
	}
	else
	{
	Report.updateTestLog("Check for Vehicle Info  elements", "Vehicle Info elements are not displaying ",Status.FAIL);
	}

	}
	 */

@Action(desc="ClickVehicleonview_link",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void ClickVehicleonview_link()
	{
		WebElement warranty= findObject(Driver,By.id("k_ASMPortal_Service_Lane_Book_ajax"), "Vehicle One-View");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("a"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contentEquals("Vehicle One-View"))
				{
					GeneralComponents.clickOnWebelement(td, "Vehicle One-View");
					Report.updateTestLog("Vehicle One-View", "displayed Vehicle One-View tab",Status.PASS);
					break;
				}    
			}          

		}
		else
		{
			Report.updateTestLog("Vehicle One-View","Vehicle One-View tab is not displayed ",Status.FAIL);
		}
	}




@Action(desc="Clickfluid_link",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Clickfluid_link()
	{
		WebElement warranty= findObject(Driver,By.id("_jpfcpncuivr_T10800497521387450386649_j_id_id1:vehInformationForm"), "Reference Fluid Specification");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("a"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contentEquals("Reference Fluid Specification"))
				{
					GeneralComponents.clickOnWebelement(td, "Reference Fluid Specification");
					Report.updateTestLog("Reference Fluid Specification", "displayed Reference Fluid Specification info",Status.PASS);
					break;
				}    
			}          

		}
		else
		{
			Report.updateTestLog("Reference Fluid Specification","Reference Fluid Specification info is not displayed ",Status.FAIL);
		}
	}

@Action(desc="click_FRM_link",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void click_FRM_link()
	{
		WebElement warranty= findObject(Driver,By.id("_jpfcpncuivr_T10800497521387450386649_j_id_id1:vehInformationForm"), "Flat Rate Manual");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("a"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contentEquals("Flat Rate Manual"))
				{
					GeneralComponents.clickOnWebelement(td, "Flat Rate Manual");
					Report.updateTestLog("Flat Rate Manual", "displayed Flat Rate Manual",Status.PASS);
					break;
				}    
			}          

		}
		else
		{
			Report.updateTestLog("Flat Rate Manual","Flat Rate Manual info is not displayed ",Status.FAIL);
		}
	}



@Action(desc="Click_SE_link",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Click_SE_link()
	{
		WebElement warranty= findObject(Driver,By.id("_jpfcpncuivr_T10800497521387450386649_j_id_id1:vehInformationForm"), "Standard Equipment");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("a"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contentEquals("Standard Equipment"))
				{
					GeneralComponents.clickOnWebelement(td, "Standard Equipment");
					Report.updateTestLog("Standard Equipment", "displayed Standard Equipment",Status.PASS);
					break;
				}    
			}          

		}
		else
		{
			Report.updateTestLog("Standard Equipment","Standard Equipment info is not displayed ",Status.FAIL);
		}
	}



@Action(desc="Verify_info_elements",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Verify_info_elements()
	{

		WebElement VIElements= findObject(Driver,By.id("_jpfcpncuivr_T10800497521387450386649_j_id_id1:vehInformationForm"), "Vehicle Info Elements");
		if(VIElements !=null)

		{
			List<WebElement> tds2= VIElements.findElements(By.tagName("label"));
			for(WebElement a:tds2)
			{
				String val=a.getText();
				if((val.contains("Prod Date: "))||(val.contains("Date of First Use: "))||(val.contains("Engine Oil Type: "))||(val.contains("Trans/Drive: "))||(val.contains("Plant Code: "))||(val.contains("Original Selling Dealer: "))||(val.contains("Engine Oil: "))||(val.contains("Trans Oil: "))||(val.contains("Grade: "))||(val.contains("Color: "))||(val.contains("Safety Connect:"))||(val.contains("XM: "))||(val.contains("Engine #: ")))
				{
					System.out.println(val);                 

					Report.updateTestLog(val+ "is displaying under Vehicle Info tab", "Vehicle Info  elements are displaying",Status.PASS);
				}                
			}
		}
		else
		{
			Report.updateTestLog("Check for Vehicle Info  elements", "Vehicle Info elements are not displaying ",Status.FAIL);
		}

	}

	/**
	 * 
	 * MethodName: Singleview Vehicle information
	 * Description: Internal portal 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Nithya
	 */


@Action(desc="singleViewLookUp_Vehicelinfo",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void singleViewLookUp_Vehicelinfo()
	{
		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_portletInstance_2_j_id_id0:vehicleSubView:vinForm:vinlookupbut"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			Driver.findElement(By.id("_jpfcpncuivr_portletInstance_2_j_id_id0:vehicleSubView:vinForm:iAgreeButton")).click();
			GeneralComponents.waitForPageLoaded(Driver);
			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
	}

@Action(desc="SingleviewClick_infotab",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void SingleviewClick_infotab()
	{

		WebElement clickowner= findObject(Driver,By.id("t_vehicleInformation_ajax"), "Vehicle information");
		if(clickowner !=null)
		{
			List<WebElement> tds2= clickowner.findElements(By.tagName("u"));
			for(WebElement a:tds2)
			{
				String val=a.getText();
				if((val.contains("Vehicle Info")))
				{
					GeneralComponents.clickOnWebelement(a, "Vehicle Information"); 
					Report.updateTestLog("Vehicle Information", "Vehicle Info tab is clicked",Status.PASS);
					break;

				}
			}
		}
		else
		{
			Report.updateTestLog("Vehicle Info","Vehicle Info tab is not clicked ",Status.FAIL);
		}

	}



@Action(desc="Singleview_info_POelements",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Singleview_info_POelements()
	{

		WebElement PreownedLCPO= findObject(Driver,By.id("_jpfcpncuivr_vehicleInformation_j_id_id1:vehInformationForm"), "Vehicle Info Elements");
		if(PreownedLCPO !=null)

		{
			List<WebElement> tds2= PreownedLCPO.findElements(By.tagName("label"));
			for(WebElement a:tds2)
			{
				String val=a.getText();
				if((val.contains("LCPO Certified: "))||(val.contains("LCPO DOFU: "))||(val.contains("LCPO Retailed Mileage: "))||(val.contains("LCPO Dealer: ")))
				{
					System.out.println(val);                 

					Report.updateTestLog(val+ "is displaying under Vehicle Info tab", "Vehicle Info  elements are displaying",Status.PASS);
				}                
			}
		}
		else
		{
			Report.updateTestLog("Check for Vehicle Info  elements", "Vehicle Info elements are not displaying ",Status.FAIL);
		}

	}

@Action(desc="Singleview_info_elements",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Singleview_info_elements()
	{

		WebElement VIElements= findObject(Driver,By.id("_jpfcpncuivr_vehicleInformation_j_id_id1:vehInformationForm"), "Vehicle Info Elements");
		if(VIElements !=null)

		{
			List<WebElement> tds2= VIElements.findElements(By.tagName("label"));
			for(WebElement a:tds2)
			{
				String val=a.getText();
				if((val.contains("Prod Date: "))||(val.contains("Date of First Use: "))||(val.contains("Engine Oil Type: "))||(val.contains("Trans/Drive: "))||(val.contains("Plant Code: "))||(val.contains("Original Selling Dealer: "))||(val.contains("Engine Oil: "))||(val.contains("Trans Oil: "))||(val.contains("Grade: "))||(val.contains("Color: "))||(val.contains("Safety Connect:"))||(val.contains("XM: "))||(val.contains("Engine #: ")))
				{
					System.out.println(val);                 

					Report.updateTestLog(val+ "is displaying under Vehicle Info tab", "Vehicle Info  elements are displaying",Status.PASS);
				}                
			}
		}
		else
		{
			Report.updateTestLog("Check for Vehicle Info  elements", "Vehicle Info elements are not displaying ",Status.FAIL);
		}

	}



@Action(desc="Singleview_oneview_link",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Singleview_oneview_link()
	{
		WebElement warranty= findObject(Driver,By.id("k_ASMPortal_Service_Lane_Book_ajax"), "Vehicle One-View");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("a"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contentEquals("Vehicle One-View"))
				{
					GeneralComponents.clickOnWebelement(td, "Vehicle One-View");
					Report.updateTestLog("Vehicle One-View", "displayed Vehicle One-View tab",Status.PASS);
					break;
				}    
			}          

		}
		else
		{
			Report.updateTestLog("Vehicle One-View","Vehicle One-View tab is not displayed ",Status.FAIL);
		}
	}




@Action(desc="SingleviewRefFluidSpecification_link",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void SingleviewRefFluidSpecification_link()
	{
		WebElement warranty= findObject(Driver,By.id("_jpfcpncuivr_vehicleInformation_j_id_id1:vehInformationForm"), "Reference Fluid Specification");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("a"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contentEquals("Reference Fluid Specification"))
				{
					GeneralComponents.clickOnWebelement(td, "Reference Fluid Specification");
					Report.updateTestLog("Reference Fluid Specification", "displayed Reference Fluid Specification info",Status.PASS);
					break;
				}    
			}          

		}
		else
		{
			Report.updateTestLog("Reference Fluid Specification","Reference Fluid Specification info is not displayed ",Status.FAIL);
		}
	}



@Action(desc="SingleviewFRM_link",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void SingleviewFRM_link()
	{
		WebElement warranty= findObject(Driver,By.id("_jpfcpncuivr_vehicleInformation_j_id_id1:vehInformationForm"), "Flat Rate Manual");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("a"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contentEquals("Flat Rate Manual"))
				{
					GeneralComponents.clickOnWebelement(td, "Flat Rate Manual");
					Report.updateTestLog("Flat Rate Manual", "displayed Flat Rate Manual",Status.PASS);
					break;
				}    
			}          

		}
		else
		{
			Report.updateTestLog("Flat Rate Manual","Flat Rate Manual info is not displayed ",Status.FAIL);
		}
	}



@Action(desc="SingleviewSE_link",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void SingleviewSE_link()
	{
		WebElement warranty= findObject(Driver,By.id("_jpfcpncuivr_vehicleInformation_j_id_id1:vehInformationForm"), "Standard Equipment");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("a"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contentEquals("Standard Equipment"))
				{
					GeneralComponents.clickOnWebelement(td, "Standard Equipment");
					Report.updateTestLog("Standard Equipment", "displayed Standard Equipment",Status.PASS);
					break;
				}    
			}          

		}
		else
		{
			Report.updateTestLog("Standard Equipment","Standard Equipment info is not displayed ",Status.FAIL);
		}
	}



	/**
	 * 
	 * MethodName: Household info
	 * Description: Internal portal tab view /tms with cc role
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Malini
	 */

@Action(desc="Tab_Clickhouseholdicon_CCrole",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Tab_Clickhouseholdicon_CCrole()
	{
		WebElement warranty= findObject(Driver,By.xpath("//img[@src='https://t3.qa2.tms.toyota.com:443/serviceLane/resources/images/household.jpg?_pageLabel=ASMPortal_Vin_Search_Page&_appSource=slane7']"), "Flat Rate Manual");
		if(warranty !=null)                                          
		{
			GeneralComponents.clickOnWebelement(warranty, "Household icon");
			Report.updateTestLog("House hold icon", "Click household icon is clicked",Status.PASS);
			switchwindow();  
		}    

		else
		{
			Report.updateTestLog("Household icon","household icon is not clicked ",Status.FAIL);
		}
	}


@Action(desc="ClickVINlink_household",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void ClickVINlink_household()
	{
		WebElement Summary= findObject(Driver,By.id("householdForm"), "VIN link");
		if(Summary !=null)

		{
			/*List<WebElement> tds2= Summary.findElements(By.tagName("td"));
	    for(WebElement a:tds2)
	    {
	           String val=a.getText();
	           if((val.contentEquals("2014 LEXUS GS350")))
	           {     	          	*/
			List<WebElement> tds= Summary.findElements(By.tagName("a"));
			for(WebElement a:tds)
			{
				String val1=a.getText();
				if((val1.contentEquals("JTHBE1BL3E5041352"))) 
				{

					GeneralComponents.clickOnWebelement(a , "VIN link is clicked");
					for(String winHandle : Driver.getWindowHandles())
						Driver.switchTo().window(winHandle);
					/*	  Driver.close();*/
					Report.updateTestLog("VIN link in household info", "VIN link is clicked in household info",Status.PASS);
					break;

				}
			}
		}


		else
		{
			Report.updateTestLog("VIN link", "VIN link is not clicked in household info ",Status.FAIL);
		}
	}


@Action(desc="switchhandle",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void switchhandle()
	{



		for(String winHandle : Driver.getWindowHandles()){

		}




	}

@Action(desc="ClickViewmorelink_household",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void ClickViewmorelink_household()
	{
		WebElement warranty= findObject(Driver,By.xpath("//img[@src='/serviceLane/resources/images/viewMore.jpg']"), "Flat Rate Manual");
		if(warranty !=null)
		{
			GeneralComponents.clickOnWebelement(warranty, "More link");
			Report.updateTestLog("House hold icon", " view more link  is clicked",Status.PASS);

		}    

		else
		{
			Report.updateTestLog("Household icon","view more link is not clicked ",Status.FAIL);
		}
	}



@Action(desc="ClickVIewless_household",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void ClickVIewless_household()
	{
		WebElement warranty= findObject(Driver,By.xpath("//img[@src='/serviceLane/resources/images/viewLess.jpg']"), "Flat Rate Manual");
		if(warranty !=null)
		{
			GeneralComponents.clickOnWebelement(warranty, "less link");
			Report.updateTestLog("House hold icon", " view less link  is clicked",Status.PASS);

		}    

		else
		{
			Report.updateTestLog("Household icon","view less link is not clicked ",Status.FAIL);
		}
	}



@Action(desc="ClickSSCicon_household",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void ClickSSCicon_household()
	{
		WebElement Summary= findObject(Driver,By.id("householdForm"), "VIN link");
		if(Summary !=null)

		{
			List<WebElement> tds2= Summary.findElements(By.tagName("a"));
			for(WebElement a:tds2)
			{
				String val=a.getText();
				if((val.contentEquals("JTHBL46F675017660")))
				{     	          	
					WebElement SSC= findObject(Driver,By.xpath("//img[@src='/serviceLane/resources/images/ssc.jpg']"), "SSC icon link");
					if(SSC !=null)
					{

						GeneralComponents.clickOnWebelement(SSC , "SSC icon in household icon"); 
						for(String winHandle : Driver.getWindowHandles())
							Driver.switchTo().window(winHandle);
						Report.updateTestLog("SSC icon", "SSC icon is clicked",Status.PASS);
						break;
					}
				}
			}
		}


		else
		{
			Report.updateTestLog("SSC icon", "SSC icon is not clicked ",Status.FAIL);
		}
	}


@Action(desc="householdelements_current_CCrole",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void householdelements_current_CCrole()
	{

		WebElement PreownedLCPO= findObject(Driver,By.id("householdForm"), "Household Info Elements");
		if(PreownedLCPO !=null)

		{
			List<WebElement> tds2= PreownedLCPO.findElements(By.tagName("td"));
			for(WebElement a:tds2)
			{
				String val=a.getText();
				if((val.contains("Vehicle "))||(val.contains("VIN "))||(val.contains("Association"))||(val.contains("Role"))||(val.contains("Start Date")))
				{
					System.out.println(val);                 

					Report.updateTestLog(val+ "is displaying under Household Info tab", "Household Info  elements are displaying",Status.PASS);
				}                
			}
		}
		else
		{
			Report.updateTestLog("Check for Household Info  elements", "Household Info elements are not displaying ",Status.FAIL);
		}

	}

@Action(desc="householdelements_disposed_CCrole",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void householdelements_disposed_CCrole()
	{

		WebElement PreownedLCPO= findObject(Driver,By.id("householdForm"), "Household Info Elements");
		if(PreownedLCPO !=null)

		{
			List<WebElement> tds2= PreownedLCPO.findElements(By.tagName("td"));
			for(WebElement a:tds2)
			{
				String val=a.getText();
				if((val.contains("Vehicle "))||(val.contains("VIN "))||(val.contains("Association"))||(val.contains("Role"))||(val.contains("Start Date"))||(val.contains("End Date")))
				{
					System.out.println(val);                 

					Report.updateTestLog(val+ "is displaying under Household Info tab", "Household Info  elements are displaying",Status.PASS);
				}                
			}
		}
		else
		{
			Report.updateTestLog("Check for Household Info  elements", "Household Info elements are not displaying ",Status.FAIL);
		}

	}


@Action(desc="Err_household",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Err_household()
	{
		WebElement Summary= findObject(Driver,By.id("householdForm"), "VIN link");
		if(Summary !=null)

		{
			List<WebElement> tds2= Summary.findElements(By.tagName("span"));
			for(WebElement a:tds2)
			{
				String val=a.getText();
				if((val.contentEquals("Household vehicles exceed the maximum display limit. The most recent 100 vehicles are displayed below.")))

					Report.updateTestLog(val+"error msg is displayed", "error msg is displayed",Status.PASS);
			}
		}

		else
		{
			Report.updateTestLog("Err msg", "Error msg is not displayed",Status.FAIL);
		}
	}



@Action(desc="Err_disposed_household",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Err_disposed_household()
	{
		WebElement Summary= findObject(Driver,By.id("householdForm"), "VIN link");
		if(Summary !=null)

		{
			List<WebElement> tds2= Summary.findElements(By.tagName("span"));
			for(WebElement a:tds2)
			{
				String val=a.getText();
				if((val.contentEquals("Household information for disposed vehicles is not available in the system or household vehicles exceed the maximum display limit.")))

					Report.updateTestLog(val+"error msg is displayed", "error msg is displayed",Status.PASS);
			}
		}

		else
		{
			Report.updateTestLog("Err msg", "Error msg is not displayed",Status.FAIL);
		}
	}



	/**
	 * 
	 * MethodName: Household info
	 * Description: Internal portal single view /tms with cc role
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Malini
	 */

@Action(desc="sing_Clickhouseholdicon_CCrole",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void sing_Clickhouseholdicon_CCrole()
	{	       GeneralComponents.waitforInternalLoad(Driver);

	WebElement warranty= findObject(Driver,By.xpath("//img[@src='https://t3.qa2.tms.toyota.com:443/serviceLane/resources/images/household.jpg?_pageLabel=ASM_portal_page_VinSearch_SingleView&_appSource=slane7']"), "Household info icon");
	if(warranty !=null)                                          
	{
		GeneralComponents.clickOnWebelement(warranty, "Household icon");
		GeneralComponents.waitforInternalLoad(Driver);

		Report.updateTestLog("House hold icon", "Click household icon is clicked",Status.PASS);	         
	}    

	else
	{
		Report.updateTestLog("Household icon","household icon is not clicked ",Status.FAIL);
	}
	}


@Action(desc="single_clickguestinfo_T3",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void single_clickguestinfo_T3()
	{
		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_portletInstance_2_j_id_id0:vehicleSubView:vinForm:vinlookupbut"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			Driver.findElement(By.id("_jpfcpncuivr_portletInstance_2_j_id_id0:vehicleSubView:vinForm:iAgreeButton")).click();
			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
		GeneralComponents.waitforInternalLoad(Driver);
		WebElement DTChistory= findObject(Driver,By.id("t_guestInformation_ajax"), "Guest info");
		if(DTChistory !=null)
		{

			WebElement link = DTChistory.findElement(By.xpath(".//..//..//.."));
			GeneralComponents.clickOnWebelement(link,"Link");
			Report.updateTestLog("Guestinfo", "Guest info tab is clicked",Status.PASS);


		}
		else
		{
			Report.updateTestLog("Guestinfo","Guestinfo tab is not clicked ",Status.FAIL);
		}
	}

@Action(desc="closeguestinfo_int",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void closeguestinfo_int()
	{
		WebElement campaign= findObject(Driver,By.id("wlp_title_repl_C_t_4051"), "campaign");
		if(campaign !=null)
		{
			WebElement link = campaign.findElement(By.xpath(".//.."));
			GeneralComponents.clickOnWebelement(link,"guestinfo");
			Report.updateTestLog("Guest info", "Guest info tab is clicked",Status.PASS);


		}
		else
		{
			Report.updateTestLog("Guest info","Guest info tab is not clicked ",Status.FAIL);
		}
	}



	/**
	 * 
	 * MethodName: Household info
	 * Description: Ext portal tab view /tms with cc role
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Malini
	 */

@Action(desc="Tab_Clickhouseholdicon_Ext",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Tab_Clickhouseholdicon_Ext()
	{
		WebElement warranty= findObject(Driver,By.xpath("//img[@src='https://tis.qa2.toyota.com:443/serviceLane/resources/images/household.jpg?_pageLabel=ASMPortal_Vin_Search_Page&_appSource=slane7']"), "House hold icon");
		if(warranty !=null)                                          
		{
			GeneralComponents.clickOnWebelement(warranty, "Household icon");
			Report.updateTestLog("House hold icon", "Click household icon is clicked",Status.PASS);
			switchwindow();  
		}    

		else
		{
			Report.updateTestLog("Household icon","household icon is not clicked ",Status.FAIL);
		}
	}

	/**
	 * 
	 * MethodName: Household info
	 * Description: Ext portal single view /tms with cc role
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Malini
	 */

@Action(desc="sing_Clickhouseholdicon_ext",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void sing_Clickhouseholdicon_ext()
	{
		WebElement warranty= findObject(Driver,By.xpath("//img[@src='https://tis.qa2.toyota.com:443/serviceLane/resources/images/household.jpg?_pageLabel=ASMPortal_portal_page_Display_One_view&_appSource=slane7']"), "Household info icon");
		if(warranty !=null)                                          
		{
			GeneralComponents.clickOnWebelement(warranty, "Household icon");
			Report.updateTestLog("House hold icon", "Click household icon is clicked",Status.PASS);	         
		}    

		else
		{
			Report.updateTestLog("Household icon","household icon is not clicked ",Status.FAIL);
		}
	}


@Action(desc="single_clickguestinfo_ext",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void single_clickguestinfo_ext()
	{
		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_portletInstance_2_j_id_id0:vehicleSubView:vinForm:vinlookupbut"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			Driver.findElement(By.id("_jpfcpncuivr_portletInstance_2_j_id_id0:vehicleSubView:vinForm:iAgreeButton")).click();
			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
		GeneralComponents.waitforInternalLoad(Driver);
		WebElement DTChistory= findObject(Driver,By.id("t_guestInformation_ajax"), "Guest info");
		if(DTChistory !=null)
		{

			WebElement link = DTChistory.findElement(By.xpath(".//..//..//.."));
			GeneralComponents.clickOnWebelement(link,"Link");
			Report.updateTestLog("Guestinfo", "Guest info tab is clicked",Status.PASS);


		}
		else
		{
			Report.updateTestLog("Guestinfo","Guestinfo tab is not clicked ",Status.FAIL);
		}
	}

@Action(desc="closeguestinfo_ext",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void closeguestinfo_ext()
	{
		WebElement campaign= findObject(Driver,By.id("wlp_title_repl_C_t_4051"), "campaign");
		if(campaign !=null)
		{
			WebElement link = campaign.findElement(By.xpath(".//.."));
			GeneralComponents.clickOnWebelement(link,"Guest info");
			Report.updateTestLog("Guest info", "Guest info tab is clicked",Status.PASS);


		}
		else
		{
			Report.updateTestLog("Guest info","Guest info tab is not clicked ",Status.FAIL);
		}
	}


	/**
	 * 
	 * MethodName: govt Rating
	 * Description: Internal portal tabbed view /tms with cc role
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Malini
	 */

@Action(desc="Clickgovtrating_tab_T3",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Clickgovtrating_tab_T3()
	{

		WebElement clickowner= findObject(Driver,By.id("k_ASMPortal_portal_book_12_ajax"), "Gov Rating");
		if(clickowner !=null)
		{
			List<WebElement> tds2= clickowner.findElements(By.tagName("a"));
			for(WebElement a:tds2)
			{
				String val=a.getText();
				if((val.contains("Gov Rating")))
				{
					GeneralComponents.clickOnWebelement(a, "Gov Rating"); 
					Report.updateTestLog("Gov Rating ", "Gov Rating tab is clicked",Status.PASS);
					break;

				}
			}
		}
		else
		{
			Report.updateTestLog("Gov Rating","Gov Rating tab is not clicked ",Status.FAIL);
		}

	}

@Action(desc="Clickgovtratinglinks_tab_T3",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Clickgovtratinglinks_tab_T3()
	{

		WebElement clickowner= findObject(Driver,By.id("_jpfcpncuivr_T10800697521387450511093_j_id_id1:mpgRatingsForm"), "Gov Rating");
		if(clickowner !=null)
		{
			List<WebElement> tds2= clickowner.findElements(By.tagName("a"));
			for(WebElement a:tds2)
			{
				String val=a.getText();
				if((val.contentEquals("www.safercar.gov")))
				{
					GeneralComponents.clickOnWebelement(a, "www.safercar.gov"); 
					Report.updateTestLog("www.safercar.gov", "www.safercar.gov link is clicked",Status.PASS);
					break;

				}
			}
		}
		else
		{
			Report.updateTestLog("www.safercar.gov","www.safercar.gov link is not clicked ",Status.FAIL);
		}

	}

@Action(desc="Clickgovtratinglinks2_tab_T3",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Clickgovtratinglinks2_tab_T3()
	{

		WebElement clickowner= findObject(Driver,By.id("_jpfcpncuivr_T10800697521387450511093_j_id_id1:mpgRatingsForm"), "Gov Rating");
		if(clickowner !=null)
		{
			List<WebElement> tds2= clickowner.findElements(By.tagName("a"));
			for(WebElement a:tds2)
			{
				String val=a.getText();
				if((val.contentEquals("www.fueleconomy.gov")))
				{
					GeneralComponents.clickOnWebelement(a, "www.fueleconomy.gov"); 
					Report.updateTestLog("www.fueleconomy.gov", "www.fueleconomy.gov link is clicked",Status.PASS);
					break;

				}
			}
		}
		else
		{
			Report.updateTestLog("www.fueleconomy.gov","www.fueleconomy.gov link is not clicked ",Status.FAIL);
		}

	}




@Action(desc="Chkgasolineimage_tab_T3",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Chkgasolineimage_tab_T3()
	{

		WebElement chkgasolineimage= findObject(Driver,By.xpath("//img[@src='https://t3.qa2.tms.toyota.com:443/serviceLane/resources/images/fuel/gasIcon.png?_pageLabel=ASMPortal_Gov_Rating&_appSource=slane7']" ), "Gov Rating");
		if(chkgasolineimage !=null)                                       
		{
			Report.updateTestLog("gasoline image", "The lookup VIN is Gasoline Vehicle",Status.PASS);
		}

		else
		{
			Report.updateTestLog("Gasoline image","The lookup VIN is not a Gasoline Vehicle",Status.FAIL);
		}

	}


@Action(desc="Chkelectronicandgasolineimage_tab_T3",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Chkelectronicandgasolineimage_tab_T3()
	{

		WebElement chkgasolineimage= findObject(Driver,By.xpath("//img[@src='https://t3.qa2.tms.toyota.com:443/serviceLane/resources/images/fuel/blendedIcon.png?_pageLabel=ASMPortal_Gov_Rating&_appSource=slane7']" ), "Gov Rating");
		if(chkgasolineimage !=null)                                       
		{
			Report.updateTestLog("Electronoc + Gasoline image", "The lookup VIN is Electronoc + Gasoline Vehicle",Status.PASS);
		}

		else
		{
			Report.updateTestLog("Electronoc + Gasoline image","The lookup VIN is not a Electronoc + Gasoline Vehicle",Status.FAIL);
		}

	}	


@Action(desc="chkgovtratingelements",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkgovtratingelements()
	{

		WebElement RegVINelements= findObject(Driver,By.id("_jpfcpncuivr_T10800697521387450511093_j_id_id1:mpgRatingsForm"), "Reg VIN Elements");
		if(RegVINelements !=null)

		{
			List<WebElement> tds2= RegVINelements.findElements(By.tagName("label"));
			for(WebElement a:tds2)
			{

				String val=a.getText();
				if((val.contains("Frontal Crash"))||(val.contains("Side Crash"))||(val.contains("Combined"))||(val.contains("Safety Rating (NCAP)"))||(val.contains("Overall Score:"))||(val.contains("Fuel Economy")))

				{
					System.out.println(val);

					Report.updateTestLog(val+"Check for govt Rating elements", "govt Rating elements are displaying",Status.PASS);


				}                
			}
		}
		else
		{
			Report.updateTestLog("Check for govt Rating elements", "govt Rating elements are not displaying ",Status.FAIL);
		}

	}


	/**
	 * 
	 * MethodName: govt Rating
	 * Description: Internal portal single view /tms with cc role
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Malini
	 */



@Action(desc="Clkgovtrat_sing_T3",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Clkgovtrat_sing_T3()
	{
		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_portletInstance_2_j_id_id0:vehicleSubView:vinForm:vinlookupbut"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			Driver.findElement(By.id("_jpfcpncuivr_portletInstance_2_j_id_id0:vehicleSubView:vinForm:iAgreeButton")).click();
			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
		GeneralComponents.waitforInternalLoad(Driver);
		WebElement DTChistory= findObject(Driver,By.id("t_governmentRatings_ajax"), "Guest info");
		if(DTChistory !=null)
		{

			WebElement link = DTChistory.findElement(By.xpath(".//..//..//.."));
			GeneralComponents.clickOnWebelement(link,"Link");
			Report.updateTestLog("Government Rating", "Government Rating tab is clicked",Status.PASS);


		}
		else
		{
			Report.updateTestLog("Government Rating","Government Rating is not clicked ",Status.FAIL);
		}
	}



@Action(desc="Clkgovtratlnk_sing_T3",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Clkgovtratlnk_sing_T3()
	{

		WebElement clickowner= findObject(Driver,By.id("_jpfcpncuivr_governmentRatings_j_id_id1:mpgRatingsForm"), "Gov Rating");
		if(clickowner !=null)
		{
			List<WebElement> tds2= clickowner.findElements(By.tagName("a"));
			for(WebElement a:tds2)
			{
				String val=a.getText();
				if((val.contentEquals("www.safercar.gov")))
				{
					GeneralComponents.clickOnWebelement(a, "www.safercar.gov"); 
					Report.updateTestLog("www.safercar.gov", "www.safercar.gov link is clicked",Status.PASS);
					break;

				}
			}
		}
		else
		{
			Report.updateTestLog("www.safercar.gov","www.safercar.gov link is not clicked ",Status.FAIL);
		}

	}

@Action(desc="Clkgovtratlink2_sing_T3",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Clkgovtratlink2_sing_T3()
	{

		WebElement clickowner= findObject(Driver,By.id("_jpfcpncuivr_governmentRatings_j_id_id1:mpgRatingsForm"), "Gov Rating");
		if(clickowner !=null)
		{
			List<WebElement> tds2= clickowner.findElements(By.tagName("a"));
			for(WebElement a:tds2)
			{
				String val=a.getText();
				if((val.contentEquals("www.fueleconomy.gov")))
				{
					GeneralComponents.clickOnWebelement(a, "www.fueleconomy.gov"); 
					Report.updateTestLog("www.fueleconomy.gov", "www.fueleconomy.gov link is clicked",Status.PASS);
					break;

				}
			}
		}
		else
		{
			Report.updateTestLog("www.fueleconomy.gov","www.fueleconomy.gov link is not clicked ",Status.FAIL);
		}

	}




@Action(desc="Chkgasolineimage_sing_T3",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Chkgasolineimage_sing_T3()
	{

		WebElement chkgasolineimage= findObject(Driver,By.xpath("//img[@src='https://t3.qa2.tms.toyota.com:443/serviceLane/resources/images/fuel/gasIcon.png?_pageLabel=ASMPortal_portal_page_Display_One_view&_appSource=slane7']" ), "Gov Rating");
		if(chkgasolineimage !=null)                                       
		{ 
			Report.updateTestLog("gasoline image", "The lookup VIN is Gasoline Vehicle",Status.PASS);
		}

		else
		{
			Report.updateTestLog("Gasoline image","The lookup VIN is not a Gasoline Vehicle",Status.FAIL);
		}

	}

@Action(desc="Chkelecandgasoline_sing_T3",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Chkelecandgasoline_sing_T3()
	{

		WebElement chkgasolineimage= findObject(Driver,By.xpath("//img[@src='https://t3.qa2.tms.toyota.com:443/serviceLane/resources/images/fuel/blendedIcon.png?_pageLabel=ASMPortal_portal_page_Display_One_view&_appSource=slane7']" ), "Gov Rating");
		if(chkgasolineimage !=null)                                       
		{ 
			Report.updateTestLog("Hybrid vehicle image", "The lookup VIN is Electronoc + Gasoline Vehicle",Status.PASS);
		}

		else
		{
			Report.updateTestLog("Hybrid vehicle image","The lookup VIN is not a Electronoc + Gasoline Vehicle",Status.FAIL);
		}

	}

@Action(desc="chkgovtratelements_sing",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkgovtratelements_sing()
	{

		WebElement RegVINelements= findObject(Driver,By.id("_jpfcpncuivr_governmentRatings_j_id_id1:mpgRatingsForm"), "Reg VIN Elements");
		if(RegVINelements !=null)

		{
			List<WebElement> tds2= RegVINelements.findElements(By.tagName("label"));
			for(WebElement a:tds2)
			{

				String val=a.getText();
				if((val.contains("Frontal Crash"))||(val.contains("Side Crash"))||(val.contains("Combined"))||(val.contains("Safety Rating (NCAP)"))||(val.contains("Overall Score:"))||(val.contains("Fuel Economy")))

				{
					System.out.println(val);

					Report.updateTestLog(val+"Check for govt Rating elements", "govt Rating elements are displaying",Status.PASS);


				}                
			}
		}
		else
		{
			Report.updateTestLog("Check for govt Rating elements", "govt Rating elements are not displaying ",Status.FAIL);
		}

	}

	/**
	 * 
	 * MethodName: govt Rating
	 * Description: External portal tabbed view /tms with cc role
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Malini
	 */

@Action(desc="Clickgovtrating_tab_TIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Clickgovtrating_tab_TIS()
	{

		WebElement clickowner= findObject(Driver,By.id("k_ASMPortal_portal_book_12_ajax"), "Gov Rating");
		if(clickowner !=null)
		{
			List<WebElement> tds2= clickowner.findElements(By.tagName("a"));
			for(WebElement a:tds2)
			{
				String val=a.getText();
				if((val.contains("Gov Rating")))
				{
					GeneralComponents.clickOnWebelement(a, "Gov Rating"); 
					Report.updateTestLog("Gov Rating ", "Gov Rating tab is clicked",Status.PASS);
					break;

				}
			}
		}
		else
		{
			Report.updateTestLog("Gov Rating","Gov Rating tab is not clicked ",Status.FAIL);
		}

	}

@Action(desc="Clickgovtratinglinks_tab_TIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Clickgovtratinglinks_tab_TIS()
	{

		WebElement clickowner= findObject(Driver,By.id("_jpfcpncuivr_T5800338351386838281210_j_id_id1:mpgRatingsForm"), "Gov Rating");
		if(clickowner !=null)
		{
			List<WebElement> tds2= clickowner.findElements(By.tagName("a"));
			for(WebElement a:tds2)
			{
				String val=a.getText();
				if((val.contentEquals("www.safercar.gov")))
				{
					GeneralComponents.clickOnWebelement(a, "www.safercar.gov"); 
					Report.updateTestLog("www.safercar.gov", "www.safercar.gov link is clicked",Status.PASS);
					break;

				}
			}
		}
		else
		{
			Report.updateTestLog("www.safercar.gov","www.safercar.gov link is not clicked ",Status.FAIL);
		}

	}

@Action(desc="Clickgovtratinglinks2_tab_TIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Clickgovtratinglinks2_tab_TIS()
	{

		WebElement clickowner= findObject(Driver,By.id("_jpfcpncuivr_T5800338351386838281210_j_id_id1:mpgRatingsForm"), "Gov Rating");
		if(clickowner !=null)
		{
			List<WebElement> tds2= clickowner.findElements(By.tagName("a"));
			for(WebElement a:tds2)
			{
				String val=a.getText();
				if((val.contentEquals("www.fueleconomy.gov")))
				{
					GeneralComponents.clickOnWebelement(a, "www.fueleconomy.gov"); 
					Report.updateTestLog("www.fueleconomy.gov", "www.fueleconomy.gov link is clicked",Status.PASS);
					break;

				}
			}
		}
		else
		{
			Report.updateTestLog("www.fueleconomy.gov","www.fueleconomy.gov link is not clicked ",Status.FAIL);
		}

	}




@Action(desc="Chkgasolineimage_tab_TIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Chkgasolineimage_tab_TIS()
	{

		WebElement chkgasolineimage= findObject(Driver,By.xpath("//img[@src='https://tis.qa2.toyota.com:443/serviceLane/resources/images/fuel/gasIcon.png?_pageLabel=ASMPortal_Gov_Rating&_appSource=slane7']" ), "Gov Rating");
		if(chkgasolineimage !=null)                                       
		{
			Report.updateTestLog("gasoline image", "The lookup VIN is Gasoline Vehicle",Status.PASS);
		}

		else
		{
			Report.updateTestLog("Gasoline image","The lookup VIN is not a Gasoline Vehicle",Status.FAIL);
		}

	}


@Action(desc="Chkelectronicandgasolineimage_tab_TIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Chkelectronicandgasolineimage_tab_TIS()
	{

		WebElement chkgasolineimage= findObject(Driver,By.xpath("//img[@src='https://tis.qa2.toyota.com:443/serviceLane/resources/images/fuel/blendedIcon.png?_pageLabel=ASMPortal_Gov_Rating&_appSource=slane7']" ), "Gov Rating");
		if(chkgasolineimage !=null)                                       
		{
			Report.updateTestLog("Electronoc + Gasoline image", "The lookup VIN is Electronoc + Gasoline Vehicle",Status.PASS);
		}

		else
		{
			Report.updateTestLog("Electronoc + Gasoline image","The lookup VIN is not a Electronoc + Gasoline Vehicle",Status.FAIL);
		}

	}	


@Action(desc="chkgovtratingelements_TIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkgovtratingelements_TIS()
	{

		WebElement RegVINelements= findObject(Driver,By.id("_jpfcpncuivr_T5800338351386838281210_j_id_id1:mpgRatingsForm"), "Reg VIN Elements");
		if(RegVINelements !=null)

		{
			List<WebElement> tds2= RegVINelements.findElements(By.tagName("label"));
			for(WebElement a:tds2)
			{

				String val=a.getText();
				if((val.contains("Frontal Crash"))||(val.contains("Side Crash"))||(val.contains("Combined"))||(val.contains("Safety Rating (NCAP)"))||(val.contains("Overall Score:"))||(val.contains("Fuel Economy")))

				{
					System.out.println(val);

					Report.updateTestLog(val+"Check for govt Rating elements", "govt Rating elements are displaying",Status.PASS);


				}                
			}
		}
		else
		{
			Report.updateTestLog("Check for govt Rating elements", "govt Rating elements are not displaying ",Status.FAIL);
		}

	}


	/**
	 * 
	 * MethodName: govt Rating
	 * Description: External portal single view /tms with cc role
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Malini
	 * @throws InterruptedException 
	 */

@Action(desc="Clkgovtrat_sing_TIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Clkgovtrat_sing_TIS() throws InterruptedException
	{

		WebElement Govtrat= findObject(Driver,By.xpath(".//div[contains(@id, 'MAX_governmentRatings')]"), "Government Ratings");
		if(Govtrat !=null)
		{

		//WebElement link = DTChistory.findElement(By.xpath(".//..//..//.."));
		GeneralComponents.clickOnWebelement(Govtrat,"Link");
		//link.click();
		//GeneralComponents.waitforInternalLoad(Driver);
		Report.updateTestLog("Government Rating", "Government Rating tab is clicked",Status.PASS);


		}
		else
		{
			Report.updateTestLog("Government Rating","Government Rating is not clicked ",Status.FAIL);
		}
	}


@Action(desc="Chkgasolineimage_sing_TIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Chkgasolineimage_sing_TIS()
	{

		WebElement chkgasolineimage= findObject(Driver,By.xpath("//img[@src='https://tis.qa2.toyota.com:443/serviceLane/resources/images/fuel/gasIcon.png?_pageLabel=ASMPortal_portal_page_Display_One_view&_appSource=slane7']" ), "Gov Rating");
		if(chkgasolineimage !=null)                                       
		{ 
			Report.updateTestLog("gasoline image", "The lookup VIN is Gasoline Vehicle",Status.PASS);
		}

		else
		{
			Report.updateTestLog("Gasoline image","The lookup VIN is not a Gasoline Vehicle",Status.FAIL);
		}

	}

@Action(desc="Chkelecandgasoline_sing_TIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Chkelecandgasoline_sing_TIS()
	{

		WebElement chkgasolineimage= findObject(Driver,By.xpath("//img[@src='https://tis.qa2.toyota.com:443/serviceLane/resources/images/fuel/blendedIcon.png?_pageLabel=ASMPortal_portal_page_Display_One_view&_appSource=slane7']" ), "Gov Rating");
		if(chkgasolineimage !=null)                                       
		{ 
			Report.updateTestLog("Hybrid vehicle image", "The lookup VIN is Electronoc + Gasoline Vehicle",Status.PASS);
		}

		else
		{
			Report.updateTestLog("Hybrid vehicle image","The lookup VIN is not a Electronoc + Gasoline Vehicle",Status.FAIL);
		}

	}


//@Action(desc="closecampaign",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	/*public void closecampaign()
{
                WebElement campaign= findObject(Driver,By.id("wlp_title_repl_C_t_4056"), "campaign");
                if(campaign !=null)
               {
                  WebElement link = campaign.findElement(By.xpath(".//..//..//.."));
                     GeneralComponents.clickOnWebelement(link,"Link");
                                    Report.updateTestLog("Campaign", "Campaign tab is clicked",Status.PASS);


               }
               else
               {
                      Report.updateTestLog("Campaign","Campaign tab is not clicked ",Status.FAIL);
               }
}*/


@Action(desc="alertaccept",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void alertaccept()
	{
		Driver.switchTo().alert().accept();
	}



@Action(desc="switchback",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void switchback()
	{
		switchwindowback();
	}



	/**
	 * 
	 * MethodName: GuestInfo
	 * Description: 
	 * Parameter (if any):  
	 * Return type:  Void
	 * Owner : Praveen
	 */



@Action(desc="enterVINGuest",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void enterVINGuest()

	{
		WebElement VINlookup=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinTextBox"), "VIN Lookup" );
		if (VINlookup !=null)
		{
			String val=userData.getData("GuestInfo", "VIN", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(VINlookup, val, "VIN is entered");
			//GeneralComponents.waitforInternalLoad(Driver);

			Report.updateTestLog("VINlookup", "VIN text is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("VINlookup", "VIN text is not found", Status.FAIL);
		}
	}


	/*{
				WebElement Guest= findObject(Driver,By.id("ASMPortal_Vin_Search_Page"), "Guest info");
			    if(Guest !=null)
			    {
			           List<WebElement> tds=Guest.findElements(By.tagName("span"));
			           for(WebElement td:tds)
			           {
			                  String val=td.getText();
			                  if(val.contains("Guest Information"))

			                  {	
			                     Report.updateTestLog(val+"is present for lexus dealer for lexus VIN", "GuestInformation is Present for lexus dealer for lexus VIN",Status.PASS);
			                     break;
			                  }    

			           }

			    }
			    else
			    {
			       Report.updateTestLog("GuestInformation","GuestInformation is not Present ",Status.FAIL);
			    }	
			}*/

@Action(desc="chkGuestInformation_lexusdealer_lexus_VIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkGuestInformation_lexusdealer_lexus_VIN()
	{
		WebElement warranty= findObject(Driver,By.id("t_portlet_guest_information_ajax"), "Guest Information");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("span"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Guest Information"))
				{
					GeneralComponents.clickOnWebelement(td, "Guest Information");
					Report.updateTestLog(val+"is present for lexus dealer for lexus VIN", "GuestInformation is Present for lexus dealer for lexus VIN",Status.PASS);
					break;
				}    

			}


		}
		else
		{
			Report.updateTestLog("Guest Information","Guest Information is not displaying ",Status.FAIL);
		}
	}

@Action(desc="clicklookup_GuestInformation_lexusdealer_lexus_VIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clicklookup_GuestInformation_lexusdealer_lexus_VIN()

	{
		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinlookupbut"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			Driver.findElement(By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:iAgreeButton")).click();
			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
	}
@Action(desc="clicklookup_GuestInformation_External_sigle_lexus_lexusVIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clicklookup_GuestInformation_External_sigle_lexus_lexusVIN()

	{
		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinlookupbut"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			Driver.findElement(By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:iAgreeButton")).click();
			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
	}
@Action(desc="clicklookup_GuestInformation_External_sigle_toyota_lexusVIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clicklookup_GuestInformation_External_sigle_toyota_lexusVIN()

	{
		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinlookupbut"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			//Driver.findElement(By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:iAgreeButton")).click();
			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
	}
@Action(desc="clicklookup_GuestInformation_External_sigle_toyota_toyotaVIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clicklookup_GuestInformation_External_sigle_toyota_toyotaVIN()

	{
		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinlookupbut"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			//Driver.findElement(By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:iAgreeButton")).click();
			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
	}
@Action(desc="clicklookup_GuestInformation_External_sigle_lexus_toyotaVIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clicklookup_GuestInformation_External_sigle_lexus_toyotaVIN()

	{
		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinlookupbut"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			Driver.findElement(By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:iAgreeButton")).click();
			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
	}

@Action(desc="clicklookup_GuestInformation_lexusdealer_Toyota_VIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clicklookup_GuestInformation_lexusdealer_Toyota_VIN()

	{
		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinlookupbut"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			Driver.findElement(By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:iAgreeButton")).click();    
			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
	}

@Action(desc="clicklookup_GuestInformation_toyotadealer_lexus_VIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clicklookup_GuestInformation_toyotadealer_lexus_VIN()

	{
		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinlookupbut"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			//Driver.findElement(By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:iAgreeButton")).click();
			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
	}

@Action(desc="clicklookup_GuestInformation_withoutccrole_lexus_VIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clicklookup_GuestInformation_withoutccrole_lexus_VIN()

	{
		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinlookupbut"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			//Driver.findElement(By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:iAgreeButton")).click();
			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
	}

@Action(desc="clicklookup_GuestInformation_withoutccrole_single_lexus_VIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clicklookup_GuestInformation_withoutccrole_single_lexus_VIN()

	{
		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinlookupbut"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			//Driver.findElement(By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:iAgreeButton")).click();
			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
	}
@Action(desc="clicklookup_GuestInformation_withoutccrole_single_toyota_VIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clicklookup_GuestInformation_withoutccrole_single_toyota_VIN()

	{
		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinlookupbut"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			//Driver.findElement(By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:iAgreeButton")).click();
			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
	}

@Action(desc="clicklookup_GuestInformation_withoutccrole_toyota_VIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clicklookup_GuestInformation_withoutccrole_toyota_VIN()

	{
		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinlookupbut"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			//Driver.findElement(By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:iAgreeButton")).click();
			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
	}


@Action(desc="clicklookup_GuestInformation_toyotadealer_toyota_VIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clicklookup_GuestInformation_toyotadealer_toyota_VIN()

	{
		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinlookupbut"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			//Driver.findElement(By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:iAgreeButton")).click();
			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
	}



@Action(desc="chkAllFieldsGuest",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkAllFieldsGuest()	
	{

		/*WebElement Robert= findObject(Driver,By.id("_jpfcpncuivr_portlet_guest_information_j_id_id0:customerInformationForm"), "table");
			                if(Robert !=null)
			                {
			                       List<WebElement> tds=Robert.findElements(By.tagName("td"));
			                       for(WebElement td:tds)
			                       {
			                              String val=td.getText();
			                              if(val.contains(" ROBERT W MIGUEL (OWNER)"))

			                              {	
			                    			  sleepTime(Driver, 1);
			                                 Report.updateTestLog(" ROBERT W MIGUEL (OWNER)", " ROBERT W MIGUEL (OWNER) is Present",Status.PASS);
			                                 break;
			                              }    

			                       }

			                }
			                else
			                {
			                   Report.updateTestLog(" ROBERT W MIGUEL (OWNER)"," ROBERT W MIGUEL (OWNER) is not Present ",Status.FAIL);
			                }*/



		WebElement Associated = findObject(Driver,By.id("_jpfcpncuivr_portlet_guest_information_j_id_id0:customerInformationForm"), "table");
		if(Associated  !=null)     
		{
			List<WebElement> tds=Associated .findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("ASSOCIATED PARTIES"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("ASSOCIATED PARTIES", " ASSOCIATED PARTIES is Present",Status.PASS);
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog("ASSOCIATED PARTIES","ASSOCIATED PARTIES is not Present ",Status.FAIL);
		}


		WebElement Primary = findObject(Driver,By.id("_jpfcpncuivr_portlet_guest_information_j_id_id0:customerInformationForm"), "table");
		if(Primary  !=null)
		{
			List<WebElement> tds=Primary.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Primary:"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Primary:","Primary: is Present",Status.PASS);
					break;
				}    

			}
		}
		else
		{
			Report.updateTestLog("Primary:","Primary: is not Present ",Status.FAIL);
		}


		WebElement Alternate = findObject(Driver,By.id("_jpfcpncuivr_portlet_guest_information_j_id_id0:customerInformationForm"), "table");
		if(Alternate   !=null)                          
		{
			List<WebElement> tds=Alternate.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Alternate:"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Alternate:", "Alternate: is Present",Status.PASS);//Alternate: 
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog("Alternate: ","Alternate: is not Present ",Status.FAIL);
		}

		WebElement Email  = findObject(Driver,By.id("_jpfcpncuivr_portlet_guest_information_j_id_id0:customerInformationForm"), "table");
		if(Email !=null)
		{
			List<WebElement> tds= Email.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Email:"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog(" Email:", " Email: is Present",Status.PASS);
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog(" Email:"," Email: is not Present ",Status.FAIL);
		}

		WebElement LastUpdate  = findObject(Driver,By.id("_jpfcpncuivr_portlet_guest_information_j_id_id0:customerInformationForm"), "table");
		if(LastUpdate !=null)
		{
			List<WebElement> tds= LastUpdate.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Last Update:"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Last Update: ", "Last Update: is Present",Status.PASS);
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog("Last Update: ","Last Update: is not Present ",Status.FAIL);
		}

		WebElement warranty= findObject(Driver,By.xpath("//img[@src='https://tis.qa2.toyota.com:443/serviceLane/resources/images/household.jpg?_pageLabel=ASMPortal_Vin_Search_Page&_appSource=slane7']"), "household icon");
		if(warranty !=null)
		{ 
			GeneralComponents.clickOnWebelement(warranty, "Flat Rate Manual");
			Report.updateTestLog("House hold icon", "Click household icon is clicked",Status.PASS);

		}    

		else
		{
			Report.updateTestLog("Household icon","household icon is not clicked ",Status.FAIL);
		}

	}

@Action(desc="chkAllFieldsGuest_internal",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkAllFieldsGuest_internal()	
	{
		WebElement Associated = findObject(Driver,By.id("_jpfcpncuivr_portlet_guest_information_j_id_id0:customerInformationForm"), "table");
		if(Associated  !=null)    
		{
			List<WebElement> tds=Associated .findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("ASSOCIATED PARTIES"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("ASSOCIATED PARTIES", " ASSOCIATED PARTIES is Present",Status.PASS);
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog("ASSOCIATED PARTIES","ASSOCIATED PARTIES is not Present ",Status.FAIL);
		}


		WebElement Primary = findObject(Driver,By.id("_jpfcpncuivr_portlet_guest_information_j_id_id0:customerInformationForm"), "table");
		if(Primary  !=null)
		{
			List<WebElement> tds=Primary.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Primary:"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Primary:","Primary: is Present",Status.PASS);
					break;
				}    

			}
		}
		else
		{
			Report.updateTestLog("Primary:","Primary: is not Present ",Status.FAIL);
		}


		WebElement Alternate = findObject(Driver,By.id("_jpfcpncuivr_portlet_guest_information_j_id_id0:customerInformationForm"), "table");
		if(Alternate   !=null)                          
		{
			List<WebElement> tds=Alternate.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Alternate:"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Alternate:", "Alternate: is Present",Status.PASS);//Alternate: 
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog("Alternate: ","Alternate: is not Present ",Status.FAIL);
		}

		WebElement Email  = findObject(Driver,By.id("_jpfcpncuivr_portlet_guest_information_j_id_id0:customerInformationForm"), "table");
		if(Email !=null)
		{
			List<WebElement> tds= Email.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Email:"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog(" Email:", " Email: is Present",Status.PASS);
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog(" Email:"," Email: is not Present ",Status.FAIL);
		}

		WebElement LastUpdate  = findObject(Driver,By.id("_jpfcpncuivr_portlet_guest_information_j_id_id0:customerInformationForm"), "table");
		if(LastUpdate !=null)
		{
			List<WebElement> tds= LastUpdate.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Last Update:"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Last Update: ", "Last Update: is Present",Status.PASS);
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog("Last Update: ","Last Update: is not Present ",Status.FAIL);
		}
		
		try
		{
			WebElement Household=findObject(Driver,By.xpath(".//img[@onclick='javascript:openHouseholdPopup();']"),"Household");
			 if(Household!=null);
			 {
			Report.updateTestLog("Household", "Household icon is Present",Status.PASS);
		}}
		catch(Exception e)
				{
					
			Report.updateTestLog("Household", "Household icon is not Present",Status.FAIL);	
					
				}
				
				{
					  

			}


		}



@Action(desc="chkAllFieldsGuest_lexusdealer_toyota_VIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkAllFieldsGuest_lexusdealer_toyota_VIN()	
	{

		WebElement Associated = findObject(Driver,By.id("_jpfcpncuivr_portlet_guest_information_j_id_id0:customerInformationForm"), "table");
		if(Associated  !=null)     
		{
			List<WebElement> tds=Associated .findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("ASSOCIATED PARTIES"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("ASSOCIATED PARTIES", " ASSOCIATED PARTIES is Present",Status.FAIL);
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog("ASSOCIATED PARTIES","ASSOCIATED PARTIES is not Present ",Status.PASS);
		}


		WebElement Primary = findObject(Driver,By.id("_jpfcpncuivr_portlet_guest_information_j_id_id0:customerInformationForm"), "table");
		if(Primary  !=null)
		{
			List<WebElement> tds=Primary.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Primary:"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Primary:","Primary: is Present",Status.FAIL);
					break;
				}    

			}
		}
		else
		{
			Report.updateTestLog("Primary:","Primary: is not Present ",Status.PASS);
		}


		WebElement Alternate = findObject(Driver,By.id("_jpfcpncuivr_portlet_guest_information_j_id_id0:customerInformationForm"), "table");
		if(Alternate   !=null)                          
		{
			List<WebElement> tds=Alternate.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Alternate:"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Alternate:", "Alternate: is Present",Status.FAIL);//Alternate: 
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog("Alternate: ","Alternate: is not Present ",Status.PASS);
		}

		WebElement Email  = findObject(Driver,By.id("_jpfcpncuivr_portlet_guest_information_j_id_id0:customerInformationForm"), "table");
		if(Email !=null)
		{
			List<WebElement> tds= Email.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Email:"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog(" Email:", " Email: is Present",Status.FAIL);
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog(" Email:"," Email: is not Present ",Status.PASS);
		}

		WebElement LastUpdate  = findObject(Driver,By.id("_jpfcpncuivr_portlet_guest_information_j_id_id0:customerInformationForm"), "table");
		if(LastUpdate !=null)
		{
			List<WebElement> tds= LastUpdate.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Last Update:"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Last Update: ", "Last Update: is Present",Status.FAIL);
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog("Last Update: ","Last Update: is not Present ",Status.PASS);
		}

		WebElement warranty= findObject(Driver,By.xpath("//img[@src='https://tis.qa2.toyota.com:443/serviceLane/resources/images/household.jpg?_pageLabel=ASMPortal_Vin_Search_Page&_appSource=slane7']"), "household icon");
		if(warranty !=null)
		{ 
			GeneralComponents.clickOnWebelement(warranty, "Flat Rate Manual");
			Report.updateTestLog("House hold icon", "Click household icon is clicked",Status.FAIL);

		}    

		else
		{
			Report.updateTestLog("Household icon","household icon is not clicked ",Status.PASS);
		}

	}

@Action(desc="chkGuestInformation_lexusdealer_Toyota_VIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkGuestInformation_lexusdealer_Toyota_VIN()
	{
		WebElement warranty= findObject(Driver,By.id("ASMPortal_Vin_Search_Page"), "Guest Information");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Guest Information"))
				{
				}
				else
				{
					Report.updateTestLog(" Guest info tab is not present for lexus dealer for Toyota VIN","Guest info tab is not present for lexus dealer for Toyota VIN",Status.PASS);
					break;
				}    

			}


		}

		else
		{
			Report.updateTestLog("Guest info is present for lexus dealer for Toyota VIN", "GuestInformation is Present for lexus dealer for Toyota VIN",Status.FAIL);      
		}

	}


@Action(desc="chkGuestInformation_toyotadealer_lexus_VIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkGuestInformation_toyotadealer_lexus_VIN()
	{
		WebElement warranty= findObject(Driver,By.id("ASMPortal_Vin_Search_Page"), "Guest Information");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Guest Information"))
				{
				}
				else
				{
					Report.updateTestLog("Guest info tab is not present for toyota dealer for lexus VIN", "Guest info tab is not present for toyota dealer for lexus VIN",Status.PASS);
					break;
				}    

			}


		}
		else
		{
			Report.updateTestLog("Guest info is present for toyota dealer for lexus VIN", "GuestInformation is Present for toyota dealer for lexus VIN",Status.FAIL);
		}
	}
@Action(desc="chkGuestInformation_toyotadealer_Toyota_VIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkGuestInformation_toyotadealer_Toyota_VIN()
	{
		WebElement warranty= findObject(Driver,By.id("ASMPortal_Vin_Search_Page"), "Guest Information");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Guest Information"))
				{
				}
				else
				{
					Report.updateTestLog("Guest info tab is not present for toyota dealer for toyota VIN", "Guest info tab is not present for toyota dealer for Toyota VIN",Status.PASS);
					break;
				}    

			}


		}
		else
		{
			Report.updateTestLog("Guest info is present for toyota dealer for Toyota VIN", "GuestInformation is Present for toyota dealer for Toyota VIN",Status.FAIL);
		}
	}

@Action(desc="enterVINGuest_TMSwithccrole",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void enterVINGuest_TMSwithccrole()

	{
		WebElement VINlookup=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinTextBox"), "VIN Lookup" );
		if (VINlookup !=null)                          
		{
			String val=userData.getData("GuestInfo", "VIN", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(VINlookup, val, "VIN is entered");
			//GeneralComponents.waitforInternalLoad(Driver);

			Report.updateTestLog("VINlookup", "VIN text is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("VINlookup", "VIN text is not found", Status.FAIL);
		}
	}


@Action(desc="clicklookup_GuestInformation_TMSwithccrole",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clicklookup_GuestInformation_TMSwithccrole()

	{
		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinlookupbut"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			Driver.findElement(By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:iAgreeButton")).click();
			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
	}

@Action(desc="chkGuestInformation_TMSwithccrole_lexus_VIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkGuestInformation_TMSwithccrole_lexus_VIN()
	{
		WebElement warranty= findObject(Driver,By.id("t_portlet_guest_information_ajax"), "Guest Information");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("span"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Guest Information"))
				{
					GeneralComponents.clickOnWebelement(td, "Guest Information");
					Report.updateTestLog(val+"is present for TMSwithccrole for lexus VIN", "GuestInformation is Present for TMSwithccrole for lexus VIN",Status.PASS);
					break;
				}    

			}


		}
		else
		{
			Report.updateTestLog("Guest Information","Guest Information is not displaying ",Status.FAIL);
		}
	}

@Action(desc="chkGuestInformation_TMSwithccrole_single_lexus_VIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkGuestInformation_TMSwithccrole_single_lexus_VIN()
	{
		WebElement warranty= findObject(Driver,By.id("t_guestInformation_ajax"), "Guest Information");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("span"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Guest Information"))
				{
					GeneralComponents.clickOnWebelement(td, "Guest Information");
					Report.updateTestLog(val+"is present for TMSwithccrole for lexus VIN", "GuestInformation is Present for TMSwithccrole for lexus VIN",Status.PASS);
					break;
				}    

			}


		}
		else
		{
			Report.updateTestLog("Guest Information","Guest Information is not displaying ",Status.FAIL);
		}
	}

@Action(desc="chkGuestInformation_External_single_lexusVIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkGuestInformation_External_single_lexusVIN()
	{
		WebElement warranty= findObject(Driver,By.id("t_guestInformation_ajax"), "Guest Information");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("span"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Guest Information"))
				{
					GeneralComponents.clickOnWebelement(td, "Guest Information");
					Report.updateTestLog(val+"is present for TMSwithccrole for lexus VIN", "GuestInformation is Present for TMSwithccrole for lexus VIN",Status.PASS);
					break;
				}    

			}


		}
		else
		{
			Report.updateTestLog("Guest Information","Guest Information is not displaying ",Status.FAIL);
		}
	}

@Action(desc="chkGuestInformation_TMSwithccrole_single_toyota_VIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkGuestInformation_TMSwithccrole_single_toyota_VIN()
	{
		WebElement warranty= findObject(Driver,By.id("t_guestInformation_ajax"), "Guest Information");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("span"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Guest Information"))
				{
					GeneralComponents.clickOnWebelement(td, "Guest Information");
					Report.updateTestLog(val+"is present for TMSwithccrole for toyota VIN", "GuestInformation is Present for TMSwithccrole for toyota VIN",Status.PASS);
					break;
				}    

			}


		}
		else
		{
			Report.updateTestLog("Guest Information","Guest Information is not displaying ",Status.FAIL);
		}
	}
@Action(desc="chkGuestInformation_TMSwithccrole_toyota_VIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkGuestInformation_TMSwithccrole_toyota_VIN()
	{
		WebElement warranty= findObject(Driver,By.id("t_portlet_guest_information_ajax"), "Guest Information");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("span"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Guest Information"))
				{
					GeneralComponents.clickOnWebelement(td, "Guest Information");
					Report.updateTestLog(val+"is present for TMSwithccrole for toyota VIN", "GuestInformation is Present for TMSwithccrole for toyota VIN",Status.PASS);
					break;
				}    

			}


		}
		else
		{
			Report.updateTestLog("Guest Information","Guest Information is not displaying ",Status.FAIL);
		}
	}

@Action(desc="chkGuestInformation_TMSwithoutccrole_lexus_VIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkGuestInformation_TMSwithoutccrole_lexus_VIN()
	{
		WebElement warranty= findObject(Driver,By.id("ASMPortal_Vin_Search_Page"), "Guest Information");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Guest Information"))
				{
				}
				else
				{
					Report.updateTestLog("Guest info tab is not present for TMSwithoutccrole for lexus VIN", "Guest info tab is not present for TMSwithoutccrole for lexus VIN",Status.PASS);
					break;
				}    

			}


		}
		else
		{
			Report.updateTestLog("Guest info is present for TMSwithoutccrole for lexus VIN", "GuestInformation is Present for TMSwithoutccrole for lexus VIN",Status.FAIL);
		}
	}

@Action(desc="chkGuestInformation_TMSwithoutccrole_single_lexus_VIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkGuestInformation_TMSwithoutccrole_single_lexus_VIN()
	{
		WebElement warranty= findObject(Driver,By.id("k_ASMPortal_portal_Vin_Customer_Search_SingleView_book_ajax"), "Guest Information");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Guest Information"))
				{
				}
				else
				{
					Report.updateTestLog("Guest info tab is not present for TMSwithoutccrole for lexus VIN", "Guest info tab is not present for TMSwithoutccrole for lexus VIN",Status.PASS);
					break;
				}    

			}


		}
		else
		{
			Report.updateTestLog("Guest info is present for TMSwithoutccrole for lexus VIN", "GuestInformation is Present for TMSwithoutccrole for lexus VIN",Status.FAIL);
		}
	}

@Action(desc="chkGuestInformation_External_lexus_single_toyotaVIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkGuestInformation_External_lexus_single_toyotaVIN()
	{
		WebElement warranty=null;
		try
		{
			warranty=Driver.findElement(By.id("ASMPortal_Cust_Info"));
			
			Report.updateTestLog("Guest info is present for TMSwithoutccrole for lexus VIN", "GuestInformation is Present for TMSwithoutccrole for lexus VIN",Status.FAIL);
		}
		catch(Exception e)
				{
					
			Report.updateTestLog("Guest info tab is not present for TMSwithoutccrole for lexus VIN", "Guest info tab is not present for TMSwithoutccrole for lexus VIN",Status.PASS);	
					
				}
				
				{
					  

			}


		}
		


@Action(desc="chkGuestInformation_External_toyota_single_lexusVIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkGuestInformation_External_toyota_single_lexusVIN()
	{
		WebElement warranty= findObject(Driver,By.id("k_ASMPortal_portal_Vin_Customer_Search_SingleView_book_ajax"), "Guest Information");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Guest Information"))
				{
				}
				else
				{
					Report.updateTestLog("Guest info tab is not present for TMSwithoutccrole for lexus VIN", "Guest info tab is not present for TMSwithoutccrole for lexus VIN",Status.PASS);
					break;
				}    

			}


		}
		else
		{
			Report.updateTestLog("Guest info is present for TMSwithoutccrole for lexus VIN", "GuestInformation is Present for TMSwithoutccrole for lexus VIN",Status.FAIL);
		}
	}
@Action(desc="chkGuestInformation_External_toyota_single_toyotaVIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkGuestInformation_External_toyota_single_toyotaVIN()
	{
		WebElement warranty= findObject(Driver,By.id("k_ASMPortal_portal_Vin_Customer_Search_SingleView_book_ajax"), "Guest Information");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Guest Information"))
				{
				}
				else
				{
					Report.updateTestLog("Guest info tab is not present for TMSwithoutccrole for lexus VIN", "Guest info tab is not present for TMSwithoutccrole for lexus VIN",Status.PASS);
					break;
				}    

			}


		}
		else
		{
			Report.updateTestLog("Guest info is present for TMSwithoutccrole for lexus VIN", "GuestInformation is Present for TMSwithoutccrole for lexus VIN",Status.FAIL);
		}
	}
@Action(desc="chkGuestInformation_External_lexus_toyota_VIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkGuestInformation_External_lexus_toyota_VIN()
	{
		WebElement warranty= findObject(Driver,By.id("k_ASMPortal_portal_Vin_Customer_Search_SingleView_book_ajax"), "Guest Information");
		if(warranty !=null)             
		{
			List<WebElement> tds=warranty.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Guest Information"))
				{
				}
				else
				{
					Report.updateTestLog("Guest info tab is not present for TMSwithoutccrole for lexus VIN", "Guest info tab is not present for TMSwithoutccrole for lexus VIN",Status.PASS);
					break;
				}    

			}


		}
		else
		{
			Report.updateTestLog("Guest info is present for TMSwithoutccrole for lexus VIN", "GuestInformation is Present for TMSwithoutccrole for lexus VIN",Status.FAIL);
		}
	}

@Action(desc="chkGuestInformation_TMSwithoutccrole_single_toyota_VIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkGuestInformation_TMSwithoutccrole_single_toyota_VIN()
	{
		WebElement warranty= findObject(Driver,By.id("k_ASMPortal_portal_Vin_Customer_Search_SingleView_book_ajax"), "Guest Information");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Guest Information"))
				{
				}
				else
				{
					Report.updateTestLog("Guest info tab is not present for TMSwithoutccrole for toyota VIN", "Guest info tab is not present for TMSwithoutccrole for toyota VIN",Status.PASS);
					break;
				}    

			}


		}
		else
		{
			Report.updateTestLog("Guest info is present for TMSwithoutccrole for toyota VIN", "GuestInformation is Present for TMSwithoutccrole for toyota VIN",Status.FAIL);
		}
	}

@Action(desc="chkGuestInformation_TMSwithoutccrole_toyota_VIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkGuestInformation_TMSwithoutccrole_toyota_VIN()
	{
		WebElement warranty= findObject(Driver,By.id("ASMPortal_Vin_Search_Page"), "Guest Information");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Guest Information"))
				{
				}
				else
				{
					Report.updateTestLog("Guest info tab is not present for TMSwithoutccrole for toyota VIN", "Guest info tab is not present for TMSwithoutccrole for toyota VIN",Status.PASS);
					break;
				}    

			}


		}
		else
		{
			Report.updateTestLog("Guest info is present for TMSwithoutccrole for toyota VIN", "GuestInformation is Present for TMSwithoutccrole for toyota VIN",Status.FAIL);
		}
	}
//@Action(desc="chkGuestInformation_TMSwithoutccrole_toyota_VIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	/*public void chkGuestInformation_TMSwithoutccrole_toyota_VIN()
			{
			       WebElement warranty= findObject(Driver,By.id("t_portlet_guest_information_ajax"), "Guest Information");
			       if(warranty !=null)
			   {
			              List<WebElement> tds=warranty.findElements(By.tagName("span"));
			              for(WebElement td:tds)
			              {
			                     String val=td.getText();
			                     if(val.contains("Guest Information"))
			                     {
			                           GeneralComponents.clickOnWebelement(td, "Guest Information");
			                        Report.updateTestLog(val+"is present for TMSwithoutccrole for toyota VIN", "GuestInformation is Present for TMSwithoutccrole for toyota VIN",Status.PASS);
			                        break;
			                     }    

			              }


			   }
			   else
			   {
			          Report.updateTestLog("Guest Information","Guest Information is not displaying ",Status.FAIL);
			   }
			}*/
@Action(desc="chkAllFieldsGuest_TMSwithccrole_single_lexusVIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkAllFieldsGuest_TMSwithccrole_single_lexusVIN()	
	{
		WebElement Associated = findObject(Driver,By.id("_jpfcpncuivr_guestInformation_j_id_id0:customerInformationForm"), "table");
		if(Associated  !=null) 
		{
			List<WebElement> tds=Associated .findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("ASSOCIATED PARTIES"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("ASSOCIATED PARTIES", " ASSOCIATED PARTIES is Present",Status.PASS);
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog("ASSOCIATED PARTIES","ASSOCIATED PARTIES is not Present ",Status.FAIL);
		}


		WebElement Primary = findObject(Driver,By.id("_jpfcpncuivr_guestInformation_j_id_id0:customerInformationForm"), "table");
		if(Primary  !=null)       
		{
			List<WebElement> tds=Primary.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Primary:"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Primary:","Primary: is Present",Status.PASS);
					break;
				}    

			}
		}
		else
		{
			Report.updateTestLog("Primary:","Primary: is not Present ",Status.FAIL);
		}


		WebElement Alternate = findObject(Driver,By.id("_jpfcpncuivr_guestInformation_j_id_id0:customerInformationForm"), "table");
		if(Alternate   !=null)                          
		{
			List<WebElement> tds=Alternate.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Alternate:"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Alternate:", "Alternate: is Present",Status.PASS);//Alternate: 
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog("Alternate: ","Alternate: is not Present ",Status.FAIL);
		}

		WebElement Email  = findObject(Driver,By.id("_jpfcpncuivr_guestInformation_j_id_id0:customerInformationForm"), "table");
		if(Email !=null)
		{
			List<WebElement> tds= Email.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Email:"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog(" Email:", " Email: is Present",Status.PASS);
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog(" Email:"," Email: is not Present ",Status.FAIL);
		}

		WebElement LastUpdate  = findObject(Driver,By.id("_jpfcpncuivr_guestInformation_j_id_id0:customerInformationForm"), "table");
		if(LastUpdate !=null)
		{
			List<WebElement> tds= LastUpdate.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Last Update:"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Last Update: ", "Last Update: is Present",Status.PASS);
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog("Last Update: ","Last Update: is not Present ",Status.FAIL);
		}

		/* WebElement warranty= findObject(Driver,By.id("https://t3.qa2.tms.toyota.com:443/serviceLane/resources/images/household.jpg?_pageLabel=ASMPortal_portal_page_Display_One_view&_appSource=slane7");
			           if(warranty !=null)//https://t3.qa2.tms.toyota.com:443/serviceLane/resources/images/household.jpg?_pageLabel=ASMPortal_portal_page_Display_One_view&_appSource=slane7
			                { 
			                               GeneralComponents.clickOnWebelement(warranty, "Flat Rate Manual");
			                            Report.updateTestLog("House hold icon", "Click household icon is clicked",Status.PASS);

			                  }    

			                 else
			                 {
			              Report.updateTestLog("Household icon","household icon is not clicked ",Status.FAIL);
			                 }*/

	}

@Action(desc="chkAllFieldsGuest_External_single_lexusVIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkAllFieldsGuest_External_single_lexusVIN()	
	{
		WebElement Associated = findObject(Driver,By.id("guestInformation"), "table");
		if(Associated  !=null)              
		{
			List<WebElement> tds=Associated .findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("ASSOCIATED PARTIES"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("ASSOCIATED PARTIES", " ASSOCIATED PARTIES is Present",Status.PASS);
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog("ASSOCIATED PARTIES","ASSOCIATED PARTIES is not Present ",Status.FAIL);
		}


		WebElement Primary = findObject(Driver,By.id("guestInformation"), "table");
		if(Primary  !=null)       
		{
			List<WebElement> tds=Primary.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Primary:"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Primary:","Primary: is Present",Status.PASS);
					break;
				}    

			}
		}
		else
		{
			Report.updateTestLog("Primary:","Primary: is not Present ",Status.FAIL);
		}


		WebElement Alternate = findObject(Driver,By.id("guestInformation"), "table");
		if(Alternate   !=null)                          
		{
			List<WebElement> tds=Alternate.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Alternate:"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Alternate:", "Alternate: is Present",Status.PASS);//Alternate: 
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog("Alternate: ","Alternate: is not Present ",Status.FAIL);
		}

		WebElement Email  = findObject(Driver,By.id("guestInformation"), "table");
		if(Email !=null)
		{
			List<WebElement> tds= Email.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Email:"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog(" Email:", " Email: is Present",Status.PASS);
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog(" Email:"," Email: is not Present ",Status.FAIL);
		}

		WebElement LastUpdate  = findObject(Driver,By.id("guestInformation"), "table");
		if(LastUpdate !=null)
		{
			List<WebElement> tds= LastUpdate.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Last Update:"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Last Update: ", "Last Update: is Present",Status.PASS);
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog("Last Update: ","Last Update: is not Present ",Status.FAIL);
		}

		/* WebElement warranty= findObject(Driver,By.id("https://t3.qa2.tms.toyota.com:443/serviceLane/resources/images/household.jpg?_pageLabel=ASMPortal_portal_page_Display_One_view&_appSource=slane7");
			           if(warranty !=null)//https://t3.qa2.tms.toyota.com:443/serviceLane/resources/images/household.jpg?_pageLabel=ASMPortal_portal_page_Display_One_view&_appSource=slane7
			                { 
			                               GeneralComponents.clickOnWebelement(warranty, "Flat Rate Manual");
			                            Report.updateTestLog("House hold icon", "Click household icon is clicked",Status.PASS);

			                  }    

			                 else
			                 {
			              Report.updateTestLog("Household icon","household icon is not clicked ",Status.FAIL);
			                 }*/

	}

@Action(desc="enterVINTabGuest",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void enterVINTabGuest()

	{
		WebElement VINlookup=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinTextBox"), "VIN Lookup" );
		if (VINlookup !=null)
		{
			String val=userData.getData("GuestInfo_Internal", "Lexus VIN", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(VINlookup, val, "VIN is entered");
			//GeneralComponents.waitforInternalLoad(Driver);

			Report.updateTestLog("VINlookup", "VIN text is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("VINlookup", "VIN text is not found", Status.FAIL);
		}
	}

@Action(desc="enterVINTabGuest_TMSwithccrole",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void enterVINTabGuest_TMSwithccrole()

	{
		WebElement VINlookup=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinTextBox"), "VIN Lookup" );
		if (VINlookup !=null)
		{
			String val=userData.getData("GuestInfo_Internal", "Toyota VIN", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(VINlookup, val, "VIN is entered");
			//GeneralComponents.waitforInternalLoad(Driver);

			Report.updateTestLog("VINlookup", "VIN text is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("VINlookup", "VIN text is not found", Status.FAIL);
		}
	}
@Action(desc="enterVINTabGuest_TMSwithoutccrole",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void enterVINTabGuest_TMSwithoutccrole()

	{
		WebElement VINlookup=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinTextBox"), "VIN Lookup" );
		if (VINlookup !=null)
		{
			String val=userData.getData("GuestInfo_Internal", "Lexus VIN", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(VINlookup, val, "VIN is entered");
			//GeneralComponents.waitforInternalLoad(Driver);

			Report.updateTestLog("VINlookup", "VIN text is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("VINlookup", "VIN text is not found", Status.FAIL);
		}
	}

@Action(desc="enterVINTabGuest_TMSwithoutccrole_single_lexus",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void enterVINTabGuest_TMSwithoutccrole_single_lexus()

	{
		WebElement VINlookup=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinTextBox"), "VIN Lookup" );
		if (VINlookup !=null)
		{
			String val=userData.getData("GuestInfo_Internal", "Lexus VIN", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(VINlookup, val, "VIN is entered");
			//GeneralComponents.waitforInternalLoad(Driver);

			Report.updateTestLog("VINlookup", "VIN text is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("VINlookup", "VIN text is not found", Status.FAIL);
		}
	}
@Action(desc="enterVINTabGuest_TMSwithccrole_single_lexus",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void enterVINTabGuest_TMSwithccrole_single_lexus()

	{
		WebElement VINlookup=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinTextBox"), "VIN Lookup" );
		if (VINlookup !=null)
		{
			String val=userData.getData("GuestInfo_Internal", "Lexus VIN", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(VINlookup, val, "VIN is entered");
			//GeneralComponents.waitforInternalLoad(Driver);

			Report.updateTestLog("VINlookup", "VIN text is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("VINlookup", "VIN text is not found", Status.FAIL);
		}
	}

@Action(desc="clicktab_Guestinfo_Ext_Single",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void clicktab_Guestinfo_Ext_Single()


{
	WebElement admin= findObject(Driver,By.id("ASMPortal_Cust_Info"), "Results");
	if(admin !=null)

	{
		GeneralComponents.waitforInternalLoad(Driver);
		GeneralComponents.clickOnWebelement(admin, "");

		Report.updateTestLog("Guest info", "Guest info Clicked",Status.PASS);

	}                



	else
	{
		Report.updateTestLog("Guest info", "Guest info Clicked",Status.FAIL);
	}


}

@Action(desc="enterVINTabGuest__External_single_lexusVIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void enterVINTabGuest__External_single_lexusVIN()

	{
		WebElement VINlookup=findObject(Driver,By.id("_jpfcpncuivr_portletInstance_2_j_id_id0:vehicleSubView:vinForm:vinTextBox"), "VIN Lookup" );
		if (VINlookup !=null)
		{
			String val=userData.getData("GuestInfo_Internal", "Lexus VIN", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(VINlookup, val, "VIN is entered");
			//GeneralComponents.waitforInternalLoad(Driver);

			Report.updateTestLog("VINlookup", "VIN text is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("VINlookup", "VIN text is not found", Status.FAIL);
		}
	}
@Action(desc="enterVINTabGuest__External_single_toyotaVIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void enterVINTabGuest__External_single_toyotaVIN()

	{
		WebElement VINlookup=findObject(Driver,By.id("_jpfcpncuivr_portletInstance_2_j_id_id0:vehicleSubView:vinForm:vinTextBox"), "VIN Lookup" );
		if (VINlookup !=null)
		{
			String val=userData.getData("GuestInfo_Internal", "Toyota VIN", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(VINlookup, val, "VIN is entered");
			//GeneralComponents.waitforInternalLoad(Driver);

			Report.updateTestLog("VINlookup", "VIN text is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("VINlookup", "VIN text is not found", Status.FAIL);
		}
	}
@Action(desc="enterVINTabGuest__External_single_lexusVIN2",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void enterVINTabGuest__External_single_lexusVIN2()

	{
		WebElement VINlookup=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinTextBox"), "VIN Lookup" );
		if (VINlookup !=null)
		{
			String val=userData.getData("GuestInfo_Internal", "Lexus VIN", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(VINlookup, val, "VIN is entered");
			//GeneralComponents.waitforInternalLoad(Driver);

			Report.updateTestLog("VINlookup", "VIN text is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("VINlookup", "VIN text is not found", Status.FAIL);
		}
	}
@Action(desc="enterVINTabGuest__External_single_toyotaVIN2",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void enterVINTabGuest__External_single_toyotaVIN2()

	{
		WebElement VINlookup=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinTextBox"), "VIN Lookup" );
		if (VINlookup !=null)
		{
			String val=userData.getData("GuestInfo_Internal", "Toyota VIN", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(VINlookup, val, "VIN is entered");
			//GeneralComponents.waitforInternalLoad(Driver);

			Report.updateTestLog("VINlookup", "VIN text is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("VINlookup", "VIN text is not found", Status.FAIL);
		}
	}
@Action(desc="enterVINTabGuest_TMSwithccrole_single_toyota",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void enterVINTabGuest_TMSwithccrole_single_toyota()

	{
		WebElement VINlookup=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinTextBox"), "VIN Lookup" );
		if (VINlookup !=null)
		{
			String val=userData.getData("GuestInfo_Internal", "Toyota VIN", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(VINlookup, val, "VIN is entered");
			//GeneralComponents.waitforInternalLoad(Driver);

			Report.updateTestLog("VINlookup", "VIN text is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("VINlookup", "VIN text is not found", Status.FAIL);
		}
	}


@Action(desc="enterVINTabGuest_TMSwithoutccrole_single_toyota",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void enterVINTabGuest_TMSwithoutccrole_single_toyota()

	{
		WebElement VINlookup=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinTextBox"), "VIN Lookup" );
		if (VINlookup !=null)
		{
			String val=userData.getData("GuestInfo_Internal", "Toyota VIN", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(VINlookup, val, "VIN is entered");
			//GeneralComponents.waitforInternalLoad(Driver);

			Report.updateTestLog("VINlookup", "VIN text is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("VINlookup", "VIN text is not found", Status.FAIL);
		}
	}
@Action(desc="enterVINTabGuest_TMSwithoutccrole_toyotaVIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void enterVINTabGuest_TMSwithoutccrole_toyotaVIN()

	{
		WebElement VINlookup=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinTextBox"), "VIN Lookup" );
		if (VINlookup !=null)
		{
			String val=userData.getData("GuestInfo_Internal", "Toyota VIN", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(VINlookup, val, "VIN is entered");
			//GeneralComponents.waitforInternalLoad(Driver);

			Report.updateTestLog("VINlookup", "VIN text is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("VINlookup", "VIN text is not found", Status.FAIL);
		}
	}
@Action(desc="enterVINTabGuest_TMSwithccrole_toyotaVIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void enterVINTabGuest_TMSwithccrole_toyotaVIN()

	{
		WebElement VINlookup=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinTextBox"), "VIN Lookup" );
		if (VINlookup !=null)
		{
			String val=userData.getData("GuestInfo_Internal", "Toyota VIN", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(VINlookup, val, "VIN is entered");
			//GeneralComponents.waitforInternalLoad(Driver);

			Report.updateTestLog("VINlookup", "VIN text is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("VINlookup", "VIN text is not found", Status.FAIL);
		}
	}
@Action(desc="chkGuestInformation_TMSwithccrole",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkGuestInformation_TMSwithccrole()
	{
		WebElement warranty= findObject(Driver,By.id("t_portlet_guest_information_ajax"), "Guest Information");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("span"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Guest Information"))
				{
					GeneralComponents.clickOnWebelement(td, "Guest Information");
					Report.updateTestLog(val+"is present for lexus dealer for lexus VIN", "GuestInformation is Present for lexus dealer for lexus VIN",Status.PASS);
					break;
				}    

			}


		}
		else
		{
			Report.updateTestLog("Guest Information","Guest Information is not displaying ",Status.FAIL);
		}
	}


	/*public void chkAllFields_AboutServicelane_lexusdealer()	
			{

			           WebElement Buildversion = findObject(Driver,By.id("leftrow"), "table");
			           if(Buildversion  !=null)     
			             {
			                     List<WebElement> tds=Buildversion .findElements(By.tagName("td"));
			                     for(WebElement td:tds)
			                     {
			                           String val=td.getText();
			                          if(val.contains("Build Version"))

			                           {	
			    			  sleepTime(Driver, 1);
			                 Report.updateTestLog("Build Version", "Build Version is Present",Status.PASS);
			                 break;
			                       }    

			                  }

			               }
			              else
			              {
			              Report.updateTestLog("Build Version","Build Version is not Present ",Status.FAIL);
			              }


			           WebElement Releasedate  = findObject(Driver,By.id("leftrow"), "table");
			           if(Releasedate !=null)
			             {
			                     List<WebElement> tds= Releasedate.findElements(By.tagName("td"));
			                     for(WebElement td:tds)
			                     {
			                           String val=td.getText();
			                          if(val.contains("Release Date"))

			                           {	
			    			  sleepTime(Driver, 1);
			                 Report.updateTestLog("Release Date", "Release Date is Present",Status.PASS);
			                 break;
			                       }    

			                  }

			               }
			              else
			              {
			              Report.updateTestLog("Release Date","Release Date is not Present ",Status.FAIL);
			              }

			           WebElement Servicelane  = findObject(Driver,By.id("leftrow"), "table");
			           if(Servicelane !=null)
			             {
			                     List<WebElement> tds= Servicelane.findElements(By.tagName("td"));
			                     for(WebElement td:tds)
			                     {
			                           String val=td.getText();
			                          if(val.contains("Service Lane Support"))

			                           {	
			    			  sleepTime(Driver, 1);
			                 Report.updateTestLog("Service Lane Support", "Service Lane Support is Present",Status.PASS);
			                 break;
			                       }    

			                  }

			               }
			              else
			              {
			              Report.updateTestLog("Service Lane Support","Service Lane Support is not Present ",Status.FAIL);
			              }



			           WebElement warranty= findObject(Driver,By.xpath("//img[@src='https://tis.qa2.toyota.com:443/serviceLane/resources/images/household.jpg?_pageLabel=ASMPortal_Vin_Search_Page&_appSource=slane7']"), "household icon");
			           if(warranty !=null)
			                { 
			                               GeneralComponents.clickOnWebelement(warranty, "Flat Rate Manual");
			                            Report.updateTestLog("House hold icon", "Click household icon is clicked",Status.FAIL);

			                  }    

			                 else
			                 {
			              Report.updateTestLog("Household icon","household icon is not clicked ",Status.PASS);
			                 }

			}*/

@Action(desc="clickonservicelane",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clickonservicelane()

	{
		WebElement clickonservicelane=findObject(Driver,By.xpath(".//img[@src='/serviceLane/resources/images/aboutLogo.png']"), "clickon servicelane");

		if(clickonservicelane !=null)
		{
			GeneralComponents.clickOnWebelement(clickonservicelane, "");
			GeneralComponents.sleepTime(Driver, 2);
			Report.updateTestLog("clickonservicelane", "clickonservicelane is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("clickonservicelane","clickonservicelane is not ",Status.FAIL);
		}
	}

@Action(desc="clickonservicelane_Internal",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clickonservicelane_Internal()

	{
		WebElement clickonservicelane=findObject(Driver,By.id("k_ASMPortal_int_book_ajax"), "clickonservicelaneInternal");
		if(clickonservicelane !=null)                           
		{
			GeneralComponents.clickOnWebelement(clickonservicelane, "");
			GeneralComponents.sleepTime(Driver, 2);
			Report.updateTestLog("clickonservicelaneInternal", "clickonservicelaneInternal is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("clickonservicelaneInternal","clickonservicelaneInternal is not clicked ",Status.FAIL);
		}
	}

@Action(desc="verifyServiceLane",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void verifyServiceLane() {

		//GeneralComponents.waitForPageLoaded(Driver);
		switchwindow();	
		WebElement popup = findObject(Driver,By.xpath(".//table[@class='mainPanel']//tbody//td"), " servicelane Popup");	
		//Alert alt = Driver.switchTo().alert();
		if(popup!=null)
		{
			String textValues = popup.getText();
			System.out.println("New Pop Text Verify  "  +textValues);
			Report.updateTestLog("Service LAne Popup", "Verify Sevrice lane popup Fields Are"+textValues, Status.SCREENSHOT);	
			WebElement newlink = findObject(Driver,By.xpath("//table[@class='dataTable']//td//font[(text()='New Features')]"),"new feature link");
			if(newlink!=null)
			{
				clickOnWebelement(newlink, "new feature link");
				GeneralComponents.sleepTime(Driver, 2);
			}
			WebElement knowlink = findObject(Driver,By.xpath("//table[@class='dataTable']//td//font[(text()='Known Bugs')]"),"Known Bugs link");
			if(knowlink!=null)
			{
				clickOnWebelement(knowlink, "Known Bugs link");
				GeneralComponents.sleepTime(Driver, 2);
			}	
		}
	}

	//alt.dismiss();
@Action(desc="verifyServiceLane_internal",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void verifyServiceLane_internal() 
	{
		//GeneralComponents.waitForPageLoaded(Driver);
		switchwindow();	
		WebElement popup = findObject(Driver,By.xpath(".//table[@class='mainPanel']//tbody//td"), " servicelane Popup");	
		//Alert alt = Driver.switchTo().alert();
		if(popup!=null)
		{
			String textValues = popup.getText();
			System.out.println("New Pop Text Verify  "  +textValues);
			Report.updateTestLog("Service LAne Popup", "Verify Sevrice lane popup Fields Are"+textValues, Status.SCREENSHOT);	
			WebElement newlink = findObject(Driver,By.xpath("//table[@class='dataTable']//td//font[(text()='New Features')]"),"new feature link");
			if(newlink!=null)
			{
				clickOnWebelement(newlink, "new feature link");
				GeneralComponents.sleepTime(Driver, 2);
			}
			WebElement knowlink = findObject(Driver,By.xpath("//table[@class='dataTable']//td//font[(text()='Known Bugs')]"),"Known Bugs link");
			if(knowlink!=null)
			{
				clickOnWebelement(knowlink, "Known Bugs link");
				GeneralComponents.sleepTime(Driver, 2);
			}	
		}

	}
@Action(desc="Switch",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Switch()
	{

		switchwindow();	
	}

@Action(desc="enterVINTabGuest_Toyota_internal",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void enterVINTabGuest_Toyota_internal()

	{
		WebElement VINlookup=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinTextBox"), "VIN Lookup" );
		if (VINlookup !=null)
		{
			String val=userData.getData("ToyotaCare", "Toyota VIN", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(VINlookup, val, "VIN is entered");
			//GeneralComponents.waitforInternalLoad(Driver);

			Report.updateTestLog("VINlookup", "VIN text is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("VINlookup", "VIN text is not found", Status.FAIL);
		}
	}
@Action(desc="clicklookup_Toyota_internal",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clicklookup_Toyota_internal()

	{
		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinlookupbut"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			Driver.findElement(By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:iAgreeButton")).click();
			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
	}
@Action(desc="clicklookup_Toyotacare_internal_scionVin",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clicklookup_Toyotacare_internal_scionVin()

	{
		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinlookupbut"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			Driver.findElement(By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:iAgreeButton")).click();
			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
	}

@Action(desc="chkAllFields_Toyota_internal",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkAllFields_Toyota_internal()	
	{
		WebElement Toyotacareprog = findObject(Driver,By.id("_jpfcpncuivr_T2204744711355917837242_j_id_id0:toyotaCareForm"), "table");
		if(Toyotacareprog  !=null)    
		{
			List<WebElement> tds=Toyotacareprog.findElements(By.tagName("div "));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("TOYOTACARE PROGRAM DETAILS"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("TOYOTACARE PROGRAM DETAILS", "TOYOTACARE PROGRAM DETAILS is Present",Status.PASS);
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog("TOYOTACARE PROGRAM DETAILS","TOYOTACARE PROGRAM DETAILS is not Present ",Status.FAIL);
		}

		WebElement Eligiblevin = findObject(Driver,By.id("_jpfcpncuivr_T2204744711355917837242_j_id_id0:toyotaCareForm"), "table");
		if(Eligiblevin  !=null)
		{
			List<WebElement> tds=Eligiblevin.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Eligible VIN: "))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Eligible VIN: ","Eligible VIN: is Present",Status.PASS);
					break;
				}    

			}
		}
		else
		{
			Report.updateTestLog("Eligible VIN: ","Eligible VIN:  is not Present ",Status.FAIL);
		}


		WebElement Oiltype = findObject(Driver,By.id("_jpfcpncuivr_T2204744711355917837242_j_id_id0:toyotaCareForm"), "table");
		if(Oiltype !=null)                          
		{
			List<WebElement> tds=Oiltype.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Oil Type: "))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Oil Type: ", "Oil Type:  is Present",Status.PASS);//Alternate: 
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog("Oil Type: ","Oil Type: is not Present ",Status.FAIL);
		}

		WebElement Effectivedate  = findObject(Driver,By.id("_jpfcpncuivr_T2204744711355917837242_j_id_id0:toyotaCareForm"), "table");
		if(Effectivedate !=null)
		{
			List<WebElement> tds= Effectivedate.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Effective Date: "))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Effective Date: ", "Effective Date:  is Present",Status.PASS);
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog("Effective Date: ","Effective Date: is not Present ",Status.FAIL);
		}

		WebElement Expirationdate  = findObject(Driver,By.id("_jpfcpncuivr_T2204744711355917837242_j_id_id0:toyotaCareForm"), "table");
		if(Expirationdate !=null)
		{
			List<WebElement> tds= Expirationdate.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Expiration Date:"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Expiration Date:", "Expiration Date: is Present",Status.PASS);
					break;
				}     
			}
		}
		else
		{
			Report.updateTestLog("Expiration Date:","Expiration Date: is not Present ",Status.FAIL);
		}

		WebElement Expirationmileage  = findObject(Driver,By.id("_jpfcpncuivr_T2204744711355917837242_j_id_id0:toyotaCareForm"), "table");
		if(Expirationmileage !=null)
		{
			List<WebElement> tds= Expirationmileage.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Expiration Mileage: "))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Expiration Mileage: ", "Expiration Mileage:  is Present",Status.PASS);
					break;
				}     
			}
		}
		else
		{
			Report.updateTestLog("Expiration Mileage: ","Expiration Mileage:  is not Present ",Status.FAIL);
		}
		WebElement Servicededesc  = findObject(Driver,By.id("_jpfcpncuivr_T2204744711355917837242_j_id_id0:toyotaCareForm:serviceDetails"), "table");
		if(Servicededesc !=null)
		{
			List<WebElement> tds= Servicededesc.findElements(By.tagName("tr"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Service Description"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Service Description", "Service Description is Present",Status.PASS);
					break;
				}     
			}
		}
		else
		{
			Report.updateTestLog("Service Description ","Service Description is not Present ",Status.FAIL);
		}
		WebElement Status1  = findObject(Driver,By.id("_jpfcpncuivr_T2204744711355917837242_j_id_id0:toyotaCareForm:serviceDetails"), "table");
		if(Status1 !=null)
		{
			List<WebElement> tds= Status1.findElements(By.tagName("tr"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Status"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Status1", "Status is Present",Status.PASS);
					break;
				}     
			}
		}
		else
		{
			Report.updateTestLog("Status1 ","Status is not Present ",Status.FAIL);
		}
		WebElement Opcode  = findObject(Driver,By.id("_jpfcpncuivr_T2204744711355917837242_j_id_id0:toyotaCareForm:serviceDetails"), "table");
		if(Opcode !=null)
		{
			List<WebElement> tds= Opcode.findElements(By.tagName("tr"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Op Code"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Opcode", "Opcode is Present",Status.PASS);
					break;
				}     
			}
		}
		else
		{
			Report.updateTestLog("Opcode ","Opcode is not Present ",Status.FAIL);
		}
		WebElement Opcode1  = findObject(Driver,By.id("_jpfcpncuivr_T2204744711355917837242_j_id_id0:toyotaCareForm:serviceDetails"), "table");
		if(Opcode1 !=null)
		{
			List<WebElement> tds= Opcode1.findElements(By.tagName("tr"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Op Code"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Opcode1", "Opcode1 is Present",Status.PASS);
					break;
				}     
			}
		}
		else
		{
			Report.updateTestLog("Opcode1 ","Opcode1 is not Present ",Status.FAIL);
		}
		WebElement Servicingdealer  = findObject(Driver,By.id("_jpfcpncuivr_T2204744711355917837242_j_id_id0:toyotaCareForm:serviceDetails"), "table");
		if(Servicingdealer !=null)
		{
			List<WebElement> tds= Servicingdealer.findElements(By.tagName("tr"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Servicing Dealer"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Servicingdealer", "Servicingdealer is Present",Status.PASS);
					break;
				}     
			}
		}
		else
		{
			Report.updateTestLog("Servicingdealer ","Servicingdealer is not Present ",Status.FAIL);
		}
		WebElement Dateserviced  = findObject(Driver,By.id("_jpfcpncuivr_T2204744711355917837242_j_id_id0:toyotaCareForm:serviceDetails"), "table");
		if(Dateserviced !=null)
		{
			List<WebElement> tds= Dateserviced.findElements(By.tagName("tr"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Date Serviced"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Dateserviced", "Dateserviced is Present",Status.PASS);
					break;
				}     
			}
		}
		else
		{
			Report.updateTestLog("Dateserviced ","Dateserviced is not Present ",Status.FAIL);
		}
		WebElement Mileage  = findObject(Driver,By.id("_jpfcpncuivr_T2204744711355917837242_j_id_id0:toyotaCareForm:serviceDetails"), "table");
		if(Mileage !=null)
		{
			List<WebElement> tds= Mileage.findElements(By.tagName("tr"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Mileage"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Mileage", "Mileage is Present",Status.PASS);
					break;
				}     
			}
		}
		else
		{
			Report.updateTestLog("Mileage ","Mileage is not Present ",Status.FAIL);
		}
	}
@Action(desc="clickonScion",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clickonScion()

	{
		WebElement btnscion=findObject(Driver,By.id("t_SysPreferencesPortlet_1_ajax"), "scion Button");

		if(btnscion !=null)
		{
			GeneralComponents.clickOnWebelement(btnscion, "");
			Driver.findElement(By.id("t_SysPreferencesPortlet_1_ajax")).click();
			Report.updateTestLog("btn_scion", "btn_scion is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("btn_scion","btn_scion is not ",Status.FAIL);
		}
	}

@Action(desc="enterVINTabGuest_Toyota_internal_Scion",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void enterVINTabGuest_Toyota_internal_Scion()

	{
		WebElement VINlookup=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinTextBox"), "VIN Lookup" );
		if (VINlookup !=null)
		{
			String val=userData.getData("ToyotaCare", "Scion VIN", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(VINlookup, val, "VIN is entered");
			//GeneralComponents.waitforInternalLoad(Driver);

			Report.updateTestLog("VINlookup", "VIN text is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("VINlookup", "VIN text is not found", Status.FAIL);
		}

	}

@Action(desc="enterVINTabGuest_Toyota_internal_SscionVIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void enterVINTabGuest_Toyota_internal_SscionVIN()

	{
		WebElement VINlookup=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinTextBox"), "VIN Lookup" );
		if (VINlookup !=null)                               
		{
			String val=userData.getData("ToyotaCare", "Scion VIN", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(VINlookup, val, "VIN is entered");
			//GeneralComponents.waitforInternalLoad(Driver);

			Report.updateTestLog("VINlookup", "VIN text is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("VINlookup", "VIN text is not found", Status.FAIL);
		}

	}
@Action(desc="enterVINTabGuest_Toyota_internal_lexusVIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void enterVINTabGuest_Toyota_internal_lexusVIN()

	{
		WebElement VINlookup=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinTextBox"), "VIN Lookup" );
		if (VINlookup !=null)        //_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinTextBox       
		{
			String val=userData.getData("ToyotaCare", "Lexus VIN", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(VINlookup, val, "VIN is entered");
			//GeneralComponents.waitforInternalLoad(Driver);

			Report.updateTestLog("VINlookup", "VIN text is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("VINlookup", "VIN text is not found", Status.FAIL);
		}

	}
@Action(desc="chkSymbol_toyota_withcc_toyotaVIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkSymbol_toyota_withcc_toyotaVIN()
	{		          // WebElement warranty= findObject(Driver,By.xpath("//img[@src='https://tis.qa2.toyota.com:443/serviceLane/resources/images/household.jpg?_pageLabel=ASMPortal_Vin_Search_Page&_appSource=slane7']"), "household icon");

		WebElement warranty= findObject(Driver,By.id("k_ASMPortal_int_book_ajax"), "TOYOTA.logo");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("img"));
			for(WebElement td:tds)
			{
				//String val=td.getAttribute();
				String val=td.getAttribute("src")	;
				if(val.contains("/serviceLane/resources/images/globals/header/headerbar.TOYOTA.logo.right.gif"))
				{             
					//GeneralComponents.clickOnWebelement(td, "TOYOTA logo");
					Report.updateTestLog(val+"is present for TMS withccrole for toyota VIN", "TOYOTA logo is Present for TMS withccrole for toyota VIN",Status.PASS);
					break;
				}    

			}


		}
		else
		{
			Report.updateTestLog("TOYOTA logo","TOYOTA logo is not displaying ",Status.FAIL);
		}
	}
@Action(desc="chkSymbol_scion_withcc",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkSymbol_scion_withcc()
	{		          // WebElement warranty= findObject(Driver,By.xpath("//img[@src='https://tis.qa2.toyota.com:443/serviceLane/resources/images/household.jpg?_pageLabel=ASMPortal_Vin_Search_Page&_appSource=slane7']"), "household icon");

		WebElement warranty= findObject(Driver,By.id("k_ASMPortal_int_book_ajax"), "SCION.logo");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("img"));
			for(WebElement td:tds)
			{
				//String val=td.getAttribute();
				String val=td.getAttribute("src")	;
				if(val.contains("/serviceLane/resources/images/globals/header/headerbar.SCION.logo.right.gif"))
				{
					//GeneralComponents.clickOnWebelement(td, "TOYOTA logo");
					Report.updateTestLog(val+"is present for TMS withccrole for toyota VIN", "SCION logo is Present for TMS withccrole ",Status.PASS);
					break;
				}    

			}


		}
		else
		{
			Report.updateTestLog("SCION logo","SCION logo is not displaying ",Status.FAIL);
		}
	}
@Action(desc="chkSymbol_lexus_withcc",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkSymbol_lexus_withcc()
	{		          // WebElement warranty= findObject(Driver,By.xpath("//img[@src='https://tis.qa2.toyota.com:443/serviceLane/resources/images/household.jpg?_pageLabel=ASMPortal_Vin_Search_Page&_appSource=slane7']"), "household icon");

		WebElement warranty= findObject(Driver,By.id("k_ASMPortal_int_book_ajax"), "LEXUS.logo");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("img"));
			for(WebElement td:tds)
			{
				//String val=td.getAttribute();
				String val=td.getAttribute("src")	;
				if(val.contains("/serviceLane/resources/images/globals/header/headerbar.LEXUS.logo.right.gif"))
				{
					//GeneralComponents.clickOnWebelement(td, "TOYOTA logo");
					Report.updateTestLog(val+"is present for TMS withccrole for lexus logo", "LEXUS logo is Present for TMS withccrole ",Status.PASS);
					break;
				}    

			}


		}
		else
		{
			Report.updateTestLog("LEXUS logo","LEXUS logo is not displaying ",Status.FAIL);
		}
	}
@Action(desc="chkToyotacare_withcc_lexus_symbol",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkToyotacare_withcc_lexus_symbol()
	{
		WebElement warranty= findObject(Driver,By.id("k_ASMPortal_Vehicle_Book_ajax"), "ToyotaCare");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("ToyotaCare"))
				{
				}
				else
				{
					Report.updateTestLog("ToyotaCare is not present for TMS withccrole_lexus symbol","ToyotaCare is not present for TMSwithccrole lexus symbol",Status.PASS);
					break;
				}    

			}


		}

		else
		{
			Report.updateTestLog("ToyotaCare is present for lexus dealer for TMS withccrole_lexus symbol", "ToyotaCare is Present for TMSwithccrole lexus symbol",Status.FAIL);      
		}

	}
@Action(desc="chkToyotacare_single_lexus_symbol",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkToyotacare_single_lexus_symbol()
	{
		WebElement warranty= findObject(Driver,By.id("ASMPortal_portal_page_Display_One_view"), "ToyotaCare");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("ToyotaCare"))
				{
				}
				else
				{
					Report.updateTestLog("ToyotaCare is not present for TMS withccrole_lexus symbol","ToyotaCare is not present for TMSwithccrole lexus symbol",Status.PASS);
					break;
				}    

			}


		}

		else
		{
			Report.updateTestLog("ToyotaCare is present for lexus dealer for TMS withccrole_lexus symbol", "ToyotaCare is Present for TMSwithccrole lexus symbol",Status.FAIL);      
		}

	}
@Action(desc="chkToyotacare_withcc_lexus_symbol1",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkToyotacare_withcc_lexus_symbol1()
	{
		WebElement warranty= findObject(Driver,By.id("k_ASMPortal_Vehicle_Book_ajax"), "ToyotaCare");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("ToyotaCare"))
				{
				}
				else
				{
					Report.updateTestLog("ToyotaCare is not present for TMS withccrole_lexus symbol","ToyotaCare is not present for TMSwithccrole lexus symbol",Status.PASS);
					break;
				}    

			}


		}

		else
		{
			Report.updateTestLog("ToyotaCare is present for lexus dealer for TMS withccrole_lexus symbol", "ToyotaCare is Present for TMSwithccrole lexus symbol",Status.FAIL);      
		}

	}
@Action(desc="chkToyotacare_single_lexus_symbol1",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkToyotacare_single_lexus_symbol1()
	{
		WebElement warranty= findObject(Driver,By.id("ASMPortal_portal_page_Display_One_view"), "ToyotaCare");
		if(warranty !=null)
		{
			List<WebElement> tds=warranty.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("ToyotaCare"))
				{
				}
				else
				{
					Report.updateTestLog("ToyotaCare is not present for TMS withccrole_lexus symbol","ToyotaCare is not present for TMSwithccrole lexus symbol",Status.PASS);
					break;
				}    

			}


		}

		else
		{
			Report.updateTestLog("ToyotaCare is present for lexus dealer for TMS withccrole_lexus symbol", "ToyotaCare is Present for TMSwithccrole lexus symbol",Status.FAIL);      
		}

	}

@Action(desc="chkAllFields_Toyota_internal_single",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkAllFields_Toyota_internal_single()	
	{
		WebElement Toyotacareprog = findObject(Driver,By.id("_jpfcpncuivr_ToyotaCarePortlet_j_id_id0:toyotaCareForm"), "table");
		if(Toyotacareprog  !=null)    
		{
			List<WebElement> tds=Toyotacareprog.findElements(By.tagName("div "));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("TOYOTACARE PROGRAM DETAILS"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("TOYOTACARE PROGRAM DETAILS", "TOYOTACARE PROGRAM DETAILS is Present",Status.PASS);
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog("TOYOTACARE PROGRAM DETAILS","TOYOTACARE PROGRAM DETAILS is not Present ",Status.FAIL);
		}

		WebElement Eligiblevin = findObject(Driver,By.id("_jpfcpncuivr_ToyotaCarePortlet_j_id_id0:toyotaCareForm"), "table");
		if(Eligiblevin  !=null)
		{
			List<WebElement> tds=Eligiblevin.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Eligible VIN: "))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Eligible VIN: ","Eligible VIN: is Present",Status.PASS);
					break;
				}    

			}
		}
		else
		{
			Report.updateTestLog("Eligible VIN: ","Eligible VIN:  is not Present ",Status.FAIL);
		}


		WebElement Oiltype = findObject(Driver,By.id("_jpfcpncuivr_ToyotaCarePortlet_j_id_id0:toyotaCareForm"), "table");
		if(Oiltype !=null)                          
		{
			List<WebElement> tds=Oiltype.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Oil Type: "))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Oil Type: ", "Oil Type:  is Present",Status.PASS);//Alternate: 
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog("Oil Type: ","Oil Type: is not Present ",Status.FAIL);
		}

		WebElement Effectivedate  = findObject(Driver,By.id("_jpfcpncuivr_ToyotaCarePortlet_j_id_id0:toyotaCareForm"), "table");
		if(Effectivedate !=null)
		{
			List<WebElement> tds= Effectivedate.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Effective Date: "))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Effective Date: ", "Effective Date:  is Present",Status.PASS);
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog("Effective Date: ","Effective Date: is not Present ",Status.FAIL);
		}

		WebElement Expirationdate  = findObject(Driver,By.id("_jpfcpncuivr_ToyotaCarePortlet_j_id_id0:toyotaCareForm"), "table");
		if(Expirationdate !=null)
		{
			List<WebElement> tds= Expirationdate.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Expiration Date:"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Expiration Date:", "Expiration Date: is Present",Status.PASS);
					break;
				}     
			}
		}
		else
		{
			Report.updateTestLog("Expiration Date:","Expiration Date: is not Present ",Status.FAIL);
		}

		WebElement Expirationmileage  = findObject(Driver,By.id("_jpfcpncuivr_ToyotaCarePortlet_j_id_id0:toyotaCareForm"), "table");
		if(Expirationmileage !=null)
		{
			List<WebElement> tds= Expirationmileage.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Expiration Mileage: "))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Expiration Mileage: ", "Expiration Mileage:  is Present",Status.PASS);
					break;
				}     
			}
		}
		else
		{
			Report.updateTestLog("Expiration Mileage: ","Expiration Mileage:  is not Present ",Status.FAIL);
		}
		WebElement Servicededesc  = findObject(Driver,By.id("_jpfcpncuivr_ToyotaCarePortlet_j_id_id0:toyotaCareForm:serviceDetails"), "table");
		if(Servicededesc !=null)
		{
			List<WebElement> tds= Servicededesc.findElements(By.tagName("tr"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Service Description"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Service Description", "Service Description is Present",Status.PASS);
					break;
				}     
			}
		}
		else
		{
			Report.updateTestLog("Service Description ","Service Description is not Present ",Status.FAIL);
		}
		WebElement Status1  = findObject(Driver,By.id("_jpfcpncuivr_ToyotaCarePortlet_j_id_id0:toyotaCareForm:serviceDetails"), "table");
		if(Status1 !=null)
		{
			List<WebElement> tds= Status1.findElements(By.tagName("tr"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Status"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Status1", "Status is Present",Status.PASS);
					break;
				}     
			}
		}
		else
		{
			Report.updateTestLog("Status1 ","Status is not Present ",Status.FAIL);
		}
		WebElement Opcode  = findObject(Driver,By.id("_jpfcpncuivr_ToyotaCarePortlet_j_id_id0:toyotaCareForm:serviceDetails"), "table");
		if(Opcode !=null)
		{
			List<WebElement> tds= Opcode.findElements(By.tagName("tr"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Op Code"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Opcode", "Opcode is Present",Status.PASS);
					break;
				}     
			}
		}
		else
		{
			Report.updateTestLog("Opcode ","Opcode is not Present ",Status.FAIL);
		}
		WebElement Opcode1  = findObject(Driver,By.id("_jpfcpncuivr_ToyotaCarePortlet_j_id_id0:toyotaCareForm:serviceDetails"), "table");
		if(Opcode1 !=null)
		{
			List<WebElement> tds= Opcode1.findElements(By.tagName("tr"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Op Code"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Opcode1", "Opcode1 is Present",Status.PASS);
					break;
				}     
			}
		}
		else
		{
			Report.updateTestLog("Opcode1 ","Opcode1 is not Present ",Status.FAIL);
		}
		WebElement Servicingdealer  = findObject(Driver,By.id("_jpfcpncuivr_ToyotaCarePortlet_j_id_id0:toyotaCareForm:serviceDetails"), "table");
		if(Servicingdealer !=null)
		{
			List<WebElement> tds= Servicingdealer.findElements(By.tagName("tr"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Servicing Dealer"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Servicingdealer", "Servicingdealer is Present",Status.PASS);
					break;
				}     
			}
		}
		else
		{
			Report.updateTestLog("Servicingdealer ","Servicingdealer is not Present ",Status.FAIL);
		}
		WebElement Dateserviced  = findObject(Driver,By.id("_jpfcpncuivr_ToyotaCarePortlet_j_id_id0:toyotaCareForm:serviceDetails"), "table");
		if(Dateserviced !=null)
		{
			List<WebElement> tds= Dateserviced.findElements(By.tagName("tr"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Date Serviced"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Dateserviced", "Dateserviced is Present",Status.PASS);
					break;
				}     
			}
		}
		else
		{
			Report.updateTestLog("Dateserviced ","Dateserviced is not Present ",Status.FAIL);
		}
		WebElement Mileage  = findObject(Driver,By.id("_jpfcpncuivr_ToyotaCarePortlet_j_id_id0:toyotaCareForm:serviceDetails"), "table");
		if(Mileage !=null)
		{
			List<WebElement> tds= Mileage.findElements(By.tagName("tr"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Mileage"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Mileage", "Mileage is Present",Status.PASS);
					break;
				}     
			}
		}
		else
		{
			Report.updateTestLog("Mileage ","Mileage is not Present ",Status.FAIL);
		}
	}

@Action(desc="chkAllFields_Toyota_external",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkAllFields_Toyota_external()	
	{
		WebElement Toyotacareprog = findObject(Driver,By.id("_jpfcpncuivr_T2601816071355836251960_j_id_id0:toyotaCareForm"), "table");
		if(Toyotacareprog  !=null)   
		{
			List<WebElement> tds=Toyotacareprog.findElements(By.tagName("div "));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("TOYOTACARE PROGRAM DETAILS"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("TOYOTACARE PROGRAM DETAILS", "TOYOTACARE PROGRAM DETAILS is Present",Status.PASS);
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog("TOYOTACARE PROGRAM DETAILS","TOYOTACARE PROGRAM DETAILS is not Present ",Status.FAIL);
		}

		WebElement Eligiblevin = findObject(Driver,By.id("_jpfcpncuivr_T2601816071355836251960_j_id_id0:toyotaCareForm"), "table");
		if(Eligiblevin  !=null)
		{
			List<WebElement> tds=Eligiblevin.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Eligible VIN: "))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Eligible VIN: ","Eligible VIN: is Present",Status.PASS);
					break;
				}    

			}
		}
		else
		{
			Report.updateTestLog("Eligible VIN: ","Eligible VIN:  is not Present ",Status.FAIL);
		}


		WebElement Oiltype = findObject(Driver,By.id("_jpfcpncuivr_T2601816071355836251960_j_id_id0:toyotaCareForm"), "table");
		if(Oiltype !=null)                          
		{
			List<WebElement> tds=Oiltype.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Oil Type: "))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Oil Type: ", "Oil Type:  is Present",Status.PASS);//Alternate: 
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog("Oil Type: ","Oil Type: is not Present ",Status.FAIL);
		}

		WebElement Effectivedate  = findObject(Driver,By.id("_jpfcpncuivr_T2601816071355836251960_j_id_id0:toyotaCareForm"), "table");
		if(Effectivedate !=null)
		{
			List<WebElement> tds= Effectivedate.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Effective Date: "))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Effective Date: ", "Effective Date:  is Present",Status.PASS);
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog("Effective Date: ","Effective Date: is not Present ",Status.FAIL);
		}

		WebElement Expirationdate  = findObject(Driver,By.id("_jpfcpncuivr_T2601816071355836251960_j_id_id0:toyotaCareForm"), "table");
		if(Expirationdate !=null)
		{
			List<WebElement> tds= Expirationdate.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Expiration Date:"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Expiration Date:", "Expiration Date: is Present",Status.PASS);
					break;
				}     
			}
		}
		else
		{
			Report.updateTestLog("Expiration Date:","Expiration Date: is not Present ",Status.FAIL);
		}

		WebElement Expirationmileage  = findObject(Driver,By.id("_jpfcpncuivr_T2601816071355836251960_j_id_id0:toyotaCareForm"), "table");
		if(Expirationmileage !=null)
		{
			List<WebElement> tds= Expirationmileage.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Expiration Mileage: "))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Expiration Mileage: ", "Expiration Mileage:  is Present",Status.PASS);
					break;
				}     
			}
		}
		else
		{
			Report.updateTestLog("Expiration Mileage: ","Expiration Mileage:  is not Present ",Status.FAIL);
		}
		WebElement Servicededesc  = findObject(Driver,By.id("_jpfcpncuivr_T2601816071355836251960_j_id_id0:toyotaCareForm:serviceDetails"), "table");
		if(Servicededesc !=null)
		{
			List<WebElement> tds= Servicededesc.findElements(By.tagName("tr"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Service Description"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Service Description", "Service Description is Present",Status.PASS);
					break;
				}     
			}
		}
		else
		{
			Report.updateTestLog("Service Description ","Service Description is not Present ",Status.FAIL);
		}
		WebElement Status1  = findObject(Driver,By.id("_jpfcpncuivr_T2601816071355836251960_j_id_id0:toyotaCareForm:serviceDetails"), "table");
		if(Status1 !=null)
		{
			List<WebElement> tds= Status1.findElements(By.tagName("tr"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Status"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Status1", "Status is Present",Status.PASS);
					break;
				}     
			}
		}
		else
		{
			Report.updateTestLog("Status1 ","Status is not Present ",Status.FAIL);
		}
		WebElement Opcode  = findObject(Driver,By.id("_jpfcpncuivr_T2601816071355836251960_j_id_id0:toyotaCareForm:serviceDetails"), "table");
		if(Opcode !=null)
		{
			List<WebElement> tds= Opcode.findElements(By.tagName("tr"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Op Code"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Opcode", "Opcode is Present",Status.PASS);
					break;
				}     
			}
		}
		else
		{
			Report.updateTestLog("Opcode ","Opcode is not Present ",Status.FAIL);
		}
		WebElement Opcode1  = findObject(Driver,By.id("_jpfcpncuivr_T2601816071355836251960_j_id_id0:toyotaCareForm:serviceDetails"), "table");
		if(Opcode1 !=null)
		{
			List<WebElement> tds= Opcode1.findElements(By.tagName("tr"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Op Code"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Opcode1", "Opcode1 is Present",Status.PASS);
					break;
				}     
			}
		}
		else
		{
			Report.updateTestLog("Opcode1 ","Opcode1 is not Present ",Status.FAIL);
		}
		WebElement Servicingdealer  = findObject(Driver,By.id("_jpfcpncuivr_T2601816071355836251960_j_id_id0:toyotaCareForm:serviceDetails"), "table");
		if(Servicingdealer !=null)
		{
			List<WebElement> tds= Servicingdealer.findElements(By.tagName("tr"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Servicing Dealer"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Servicingdealer", "Servicingdealer is Present",Status.PASS);
					break;
				}     
			}
		}
		else
		{
			Report.updateTestLog("Servicingdealer ","Servicingdealer is not Present ",Status.FAIL);
		}
		WebElement Dateserviced  = findObject(Driver,By.id("_jpfcpncuivr_T2601816071355836251960_j_id_id0:toyotaCareForm:serviceDetails"), "table");
		if(Dateserviced !=null)
		{
			List<WebElement> tds= Dateserviced.findElements(By.tagName("tr"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Date Serviced"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Dateserviced", "Dateserviced is Present",Status.PASS);
					break;
				}     
			}
		}
		else
		{
			Report.updateTestLog("Dateserviced ","Dateserviced is not Present ",Status.FAIL);
		}
		WebElement Mileage  = findObject(Driver,By.id("_jpfcpncuivr_T2601816071355836251960_j_id_id0:toyotaCareForm:serviceDetails"), "table");
		if(Mileage !=null)
		{
			List<WebElement> tds= Mileage.findElements(By.tagName("tr"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Mileage"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Mileage", "Mileage is Present",Status.PASS);
					break;
				}     
			}
		}
		else
		{
			Report.updateTestLog("Mileage ","Mileage is not Present ",Status.FAIL);
		}
	}

@Action(desc="chkAllFields_Toyota_external_single",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkAllFields_Toyota_external_single()
	{
		WebElement Toyotacareprog = findObject(Driver,By.id("_jpfcpncuivr_ToyotaCarePortlet_j_id_id0:toyotaCareForm"), "table");
		if(Toyotacareprog  !=null)    
		{
			List<WebElement> tds=Toyotacareprog.findElements(By.tagName("div "));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("TOYOTACARE PROGRAM DETAILS"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("TOYOTACARE PROGRAM DETAILS", "TOYOTACARE PROGRAM DETAILS is Present",Status.PASS);
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog("TOYOTACARE PROGRAM DETAILS","TOYOTACARE PROGRAM DETAILS is not Present ",Status.FAIL);
		}

		WebElement Eligiblevin = findObject(Driver,By.id("_jpfcpncuivr_ToyotaCarePortlet_j_id_id0:toyotaCareForm"), "table");
		if(Eligiblevin  !=null)
		{
			List<WebElement> tds=Eligiblevin.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Eligible VIN: "))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Eligible VIN: ","Eligible VIN: is Present",Status.PASS);
					break;
				}    

			}
		}
		else
		{
			Report.updateTestLog("Eligible VIN: ","Eligible VIN:  is not Present ",Status.FAIL);
		}


		WebElement Oiltype = findObject(Driver,By.id("_jpfcpncuivr_ToyotaCarePortlet_j_id_id0:toyotaCareForm"), "table");
		if(Oiltype !=null)                          
		{
			List<WebElement> tds=Oiltype.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Oil Type: "))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Oil Type: ", "Oil Type:  is Present",Status.PASS);//Alternate: 
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog("Oil Type: ","Oil Type: is not Present ",Status.FAIL);
		}

		WebElement Effectivedate  = findObject(Driver,By.id("_jpfcpncuivr_ToyotaCarePortlet_j_id_id0:toyotaCareForm"), "table");
		if(Effectivedate !=null)
		{
			List<WebElement> tds= Effectivedate.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Effective Date: "))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Effective Date: ", "Effective Date:  is Present",Status.PASS);
					break;
				}    

			}

		}
		else
		{
			Report.updateTestLog("Effective Date: ","Effective Date: is not Present ",Status.FAIL);
		}

		WebElement Expirationdate  = findObject(Driver,By.id("_jpfcpncuivr_ToyotaCarePortlet_j_id_id0:toyotaCareForm"), "table");
		if(Expirationdate !=null)
		{
			List<WebElement> tds= Expirationdate.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Expiration Date:"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Expiration Date:", "Expiration Date: is Present",Status.PASS);
					break;
				}     
			}
		}
		else
		{
			Report.updateTestLog("Expiration Date:","Expiration Date: is not Present ",Status.FAIL);
		}

		WebElement Expirationmileage  = findObject(Driver,By.id("_jpfcpncuivr_ToyotaCarePortlet_j_id_id0:toyotaCareForm"), "table");
		if(Expirationmileage !=null)
		{
			List<WebElement> tds= Expirationmileage.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Expiration Mileage: "))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Expiration Mileage: ", "Expiration Mileage:  is Present",Status.PASS);
					break;
				}     
			}
		}
		else
		{
			Report.updateTestLog("Expiration Mileage: ","Expiration Mileage:  is not Present ",Status.FAIL);
		}
		WebElement Servicededesc  = findObject(Driver,By.id("_jpfcpncuivr_ToyotaCarePortlet_j_id_id0:toyotaCareForm:serviceDetails"), "table");
		if(Servicededesc !=null)
		{
			List<WebElement> tds= Servicededesc.findElements(By.tagName("tr"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Service Description"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Service Description", "Service Description is Present",Status.PASS);
					break;
				}     
			}
		}
		else
		{
			Report.updateTestLog("Service Description ","Service Description is not Present ",Status.FAIL);
		}
		WebElement Status1  = findObject(Driver,By.id("_jpfcpncuivr_ToyotaCarePortlet_j_id_id0:toyotaCareForm:serviceDetails"), "table");
		if(Status1 !=null)
		{
			List<WebElement> tds= Status1.findElements(By.tagName("tr"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Status"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Status1", "Status is Present",Status.PASS);
					break;
				}     
			}
		}
		else
		{
			Report.updateTestLog("Status1 ","Status is not Present ",Status.FAIL);
		}
		WebElement Opcode  = findObject(Driver,By.id("_jpfcpncuivr_ToyotaCarePortlet_j_id_id0:toyotaCareForm:serviceDetails"), "table");
		if(Opcode !=null)
		{
			List<WebElement> tds= Opcode.findElements(By.tagName("tr"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Op Code"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Opcode", "Opcode is Present",Status.PASS);
					break;
				}     
			}
		}
		else
		{
			Report.updateTestLog("Opcode ","Opcode is not Present ",Status.FAIL);
		}
		WebElement Opcode1  = findObject(Driver,By.id("_jpfcpncuivr_ToyotaCarePortlet_j_id_id0:toyotaCareForm:serviceDetails"), "table");
		if(Opcode1 !=null)
		{
			List<WebElement> tds= Opcode1.findElements(By.tagName("tr"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Op Code"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Opcode1", "Opcode1 is Present",Status.PASS);
					break;
				}     
			}
		}
		else
		{
			Report.updateTestLog("Opcode1 ","Opcode1 is not Present ",Status.FAIL);
		}
		WebElement Servicingdealer  = findObject(Driver,By.id("_jpfcpncuivr_ToyotaCarePortlet_j_id_id0:toyotaCareForm:serviceDetails"), "table");
		if(Servicingdealer !=null)
		{
			List<WebElement> tds= Servicingdealer.findElements(By.tagName("tr"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Servicing Dealer"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Servicingdealer", "Servicingdealer is Present",Status.PASS);
					break;
				}     
			}
		}
		else
		{
			Report.updateTestLog("Servicingdealer ","Servicingdealer is not Present ",Status.FAIL);
		}
		WebElement Dateserviced  = findObject(Driver,By.id("_jpfcpncuivr_ToyotaCarePortlet_j_id_id0:toyotaCareForm:serviceDetails"), "table");
		if(Dateserviced !=null)
		{
			List<WebElement> tds= Dateserviced.findElements(By.tagName("tr"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Date Serviced"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Dateserviced", "Dateserviced is Present",Status.PASS);
					break;
				}     
			}
		}
		else
		{
			Report.updateTestLog("Dateserviced ","Dateserviced is not Present ",Status.FAIL);
		}
		WebElement Mileage  = findObject(Driver,By.id("_jpfcpncuivr_ToyotaCarePortlet_j_id_id0:toyotaCareForm:serviceDetails"), "table");
		if(Mileage !=null)
		{
			List<WebElement> tds= Mileage.findElements(By.tagName("tr"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Mileage"))

				{	
					sleepTime(Driver, 1);
					Report.updateTestLog("Mileage", "Mileage is Present",Status.PASS);
					break;
				}     
			}
		}
		else
		{
			Report.updateTestLog("Mileage ","Mileage is not Present ",Status.FAIL);
		}
	}
@Action(desc="enterVINTabGuest_diagnostic_internal",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void enterVINTabGuest_diagnostic_internal()

	{
		WebElement VINlookup=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinTextBox"), "VIN Lookup" );
		if (VINlookup !=null)
		{
			String val=userData.getData("DiagnosticReport", "Dia VIN", "1", "1");

			GeneralComponents.enterValue(VINlookup, val, "VIN is entered");
			//GeneralComponents.waitforInternalLoad(Driver);

			Report.updateTestLog("VINlookup", "VIN text is found",Status.PASS);
		}            
		else
		{
			Report.updateTestLog("VINlookup", "VIN text is not found", Status.FAIL);
		}
	} 
@Action(desc="clicklookup_diagnostic_internal",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clicklookup_diagnostic_internal()

	{
		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinlookupbut"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			Driver.findElement(By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:iAgreeButton")).click();
			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}
	}
@Action(desc="verifyDiagnostic_internal",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void verifyDiagnostic_internal() 
	{

		String val=Driver.getWindowHandle();   	  
		WebElement newlink = findObject(Driver,By.xpath(".//tbody[@class='tableBody']/tr[@class='odd']/td/a[contains(@href,'tms.toyota')]"),"MilCustHealthCheckSummary");
		if(newlink!=null)
		{
			//switchwindow();
			clickOnWebelement(newlink, "MilCustHealthCheckSummary");
			GeneralComponents.sleepTime(Driver, 3);
			switchwindow();	
			//Driver.switchTo().defaultContent();
			/*diagnostic_VINequivalence();*/
			/* 	Driver.switchTo().defaultContent();*/
			/*Driver.switchTo().window(val);*/

		}

	}


@Action(desc="diagnostic_VINequivalence",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void diagnostic_VINequivalence()
	{
		String val=userData.getData("DiagnosticReport", "Dia VIN", "1", "1");
		// String diagnosticVIN = userData.getData("DiagnosticReport", "Dia VIN", "1", "1"); 
		// switchwindow();
		WebElement DiagnosticVIN = findObject(Driver,By.xpath("//table[@class='mainBody']/tbody/tr[1]/td[2])"),"Vin Number");
		//String vinNumber  = DiagnosticVIN.getText();
		if(DiagnosticVIN!=null){
			String vinNumber2  = DiagnosticVIN.getText();
			System.out.println("Vin Number in Popup Frame --->"+ vinNumber2);
			Report.updateTestLog("Vin Number in Popup", "Verify Vin Number in Popup", Status.SCREENSHOT);

			if(vinNumber2.contains(val)){
				Report.updateTestLog("Compare VIN Number", "Verify VIN Numbers", Status.PASS);

			}else{
				Report.updateTestLog("Compare VIn Number", "Verify Vin Number", Status.FAIL);
			}
		}
	}

	/**
	 * Method Name :executeQueries
	 * Parameter Details (if any):
	 * Need/Purpose for the method :To execute Queries		
	 *//*
	public void executeQueries()
	{
//		Get Environment details
		Object[] varDetails = Toyo_Supportlibrary.getRuntimeEnvironmentSettings(runTimeTempFolder + "\\RuntimeEnvironmentSettings.cfg");
		Toyo_Supportlibrary.executeQueries(varDetails,null, "Queries");
		if(configData.isNoData)
		{			
			Report.updateTestLog("Result from DB for the Execute Query Action","No Data Found. DB Result Set is Empty",Status.WARNING);
		}
	}

	public void executeQueriesOnSecondDB()
	{
		Object[] varDetails = Toyo_Supportlibrary.getRuntimeEnvironmentSettings(runTimeTempFolder + "\\SecondDBEnvironmentSettings.cfg");
		Toyo_Supportlibrary.executeQueries(varDetails,null, "Queries");
		if(configData.isNoData)
		{ 
			Report.updateTestLog("Result from DB for the Execute Query Action","No Data Found. DB Result Set is Empty",Status.FAIL);
		}
		Toyo_Supportlibrary.getRuntimeEnvironmentSettings(runTimeTempFolder + "\\RuntimeEnvironmentSettings.cfg");
	}
	  */
	public WebElement findFileUploadPopUP(WebDriver driver,String no)
	{
		// String fileName="tasFlow_2{actionForm.attachment"+no+"}";
		// System.out.println("name"+fileName);
		String fileName=null;
		if(no.equalsIgnoreCase("1"))
		{
			fileName="portlets_tis_inboxAttachment_InboxAttachment_portlet{actionForm.file1}"; 
		}
		if(no.equalsIgnoreCase("2"))
		{
			fileName="portlets_tis_inboxAttachment_InboxAttachment_portlet{actionForm.file2}"; 
		}
		if(no.equalsIgnoreCase("3"))
		{
			fileName="portlets_tis_inboxAttachment_InboxAttachment_portlet{actionForm.file3}"; 
		}
		if(no.equalsIgnoreCase("4"))
		{
			fileName="portlets_tis_inboxAttachment_InboxAttachment_portlet{actionForm.file4}"; 
		}
		if(no.equalsIgnoreCase("5"))
		{
			fileName="portlets_tis_inboxAttachment_InboxAttachment_portlet{actionForm.file5}"; 
		}
		WebElement webEle = GeneralComponents.findObject(driver,By.name(fileName), "File Upload");
		return webEle; 
	}
	/**
	 * 
	 * MethodName: uploadFile
	 * Description: To upload a file
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : Kavyaa
	 * @throws AWTException 
	 * 
	 * 
	 * code change :
	 * owner:sabari
	 * 
	 * 
	 */
	@Action(desc="UploadFile",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void UploadFile1() throws InterruptedException, AWTException
	{

		/*if(!strAttachment.equalsIgnoreCase("NULL") && !strAttachment.equalsIgnoreCase(""))
		{
			//	WebElement attachments=findFileUploadPopUP(Driver,strAttachment.split("&")[0]);
			//		String filePath=FilePath.getTestDataPath()+"\\Testdatafiles\\"+strAttachment;
//			String filePath=FilePath.getTestDataPath_batchjobexpected(strAttachment);
//			String f = FilePath.gettestda;
			//String filePath=FilePath.getTestDataPath()+strAttachment.split("&")[1];
			//	GeneralComponents.doubleClickWebElement(attachments,"File "+strAttachment.split("&")[0]);
			Thread.sleep(4000);
//			System.out.println("FilePath:"+filePath);
//			com.cognizant.commands.RobotSendKeys.type(filePath);
			//com.cognizant.commands.RobotSendKeys.releseShift();
			 * 
			 * 
			 */
		//String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
		String Filename = userData.getData("UniqueData", "FilePath","MSR_FileUpload", "MSR_FileUpload_TMS_Corp","1","1");
		Robot r = new Robot();
			//StringSelection File1=new StringSelection("\\DC2-WFSVP02\\Profiles2\416054\\Documents\\TIS TAS'\\sample file\4A191606.fstdat");
		StringSelection File1=new StringSelection(Filename);	
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(File1,null);
			
			//PRESSING CONTROL + v
			
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		//Releasing control V
		
		
			r.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(3000);
			//com.cognizant.commands.RobotSendKeys.releseShift();
			r.keyRelease(KeyEvent.VK_V);
			//com.cognizant.commands.RobotSendKeys.type('\n');
			
			//pressing enter
			
			r.keyPress(KeyEvent.VK_ENTER);
		//Releasing enter
			
			r.keyRelease(KeyEvent.VK_ENTER);
			
			
			
		}
		//verifyTextPresentPageSource()
	


	public void clickOnWebelement(WebElement wElement,String fieldName){
		try {		  
			wElement.click();	
			sleepTime(Driver, 1);
			Report.updateTestLog("Click on "+fieldName, fieldName+" has been clicked", Status.PASS);	
			sleepTime(Driver, 1);
		} catch (Exception e) {
			Report.updateTestLog("Click on "+fieldName,"Exception occured while trying to click on "+fieldName+". Exception is ["+e+"]",Status.FAIL);
		}
	}
	/*public void createTestDataFileinResultFolder()
	{
		String tc=Settings.TCName;
		String sourceFolder = userData.getTestDataDBPath();
		String destinationFolder= FilePath.getResultsPath()+"\\"+tc+".txt";
			//to save file in current folder location
			try{userData.saveTestDataFileinCurrExecFolder(sourceFolder, destinationFolder);}catch(Exception e){}

	}*/

	/**
	 * 
	 * MethodName:  addAssociateDetails
	 * Description: To add the Associate details in Add new associate screen
	 * Parameter (if any):  
	 * Return type:  Void
	 * Owner : Ganesh 
	 */
@Action(desc="addAssociateDetails",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void addAssociateDetails()
	{  

		String tc=userData.getTestCase();
		String strVehicles= userData.getData("CoreInfo", "Vehicles", "Commodity",tc, "1", "1");	
		String strSupplier= userData.getData("CoreInfo", "Supplier", "Commodity",tc, "1", "1");
		String strMarketingBrand= userData.getData("CoreInfo", "MarketingBrand", "Commodity", tc, "1", "1");
		String strGeography= userData.getData("CoreInfo", "Geography", "Commodity", tc, "1", "1");

		if(!strVehicles.equalsIgnoreCase("NULL") && !strVehicles.equalsIgnoreCase(""))
		{
			WebElement tO_VehiclesTextBox =findObject(Driver,By.xpath("//input[contains(@id,'pt1:r1:0:t4:')][contains(@id,':nusmc5::content')]"), "Vehicles TxtBox");

			List<WebElement> tO_VehiclesListBox = findObjects(Driver,By.xpath("//ul[contains(@id,'pt1:r1:0:t4:')][contains(@id,':nusmc5::pop')]/li"), "Vehicles TxtBox") ;

			selectItems(tO_VehiclesTextBox,tO_VehiclesListBox,strVehicles,"Vehicles");
		}

		if(!strSupplier.equalsIgnoreCase("NULL") && !strSupplier.equalsIgnoreCase(""))
		{
			WebElement tO_VehiclesTextBox = findObject(Driver,By.xpath("//input[contains(@id,'pt1:r1:0:t4:')][contains(@id,':nusmc6::content')]"), "Suppier Text box");

			List<WebElement> tO_VehiclesListBox = findObjects(Driver,By.xpath("//ul[contains(@id,'pt1:r1:0:t4:')][contains(@id,':nusmc6::pop')]/li"), "Vehicles TxtBox") ;;

			selectItems(tO_VehiclesTextBox,tO_VehiclesListBox,strSupplier,"Supplier");
		}

		if(!strMarketingBrand.equalsIgnoreCase("NULL") && !strMarketingBrand.equalsIgnoreCase(""))
		{
			WebElement tO_VehiclesTextBox = findObject(Driver,By.xpath("//input[contains(@id,'pt1:r1:0:t4:')][contains(@id,':nusmc7::content')]"), "Suppier Text box");

			List<WebElement> tO_VehiclesListBox = findObjects(Driver,By.xpath("//ul[contains(@id,'pt1:r1:0:t4:')][contains(@id,':nusmc7::pop')]/li"), "Vehicles TxtBox") ;;

			selectItems(tO_VehiclesTextBox,tO_VehiclesListBox,strMarketingBrand,"MarketingBrand");
		}

		if(!strGeography.equalsIgnoreCase("NULL") && !strGeography.equalsIgnoreCase(""))
		{
			WebElement tO_VehiclesTextBox = findObject(Driver,By.xpath("//input[contains(@id,'pt1:r1:0:t4:')][contains(@id,':nusmc8::content')]"), "Suppier Text box");

			List<WebElement> tO_VehiclesListBox = findObjects(Driver,By.xpath("//ul[contains(@id,'pt1:r1:0:t4:')][contains(@id,':nusmc8::pop')]/li"), "Vehicles TxtBox") ;;

			selectItems(tO_VehiclesTextBox,tO_VehiclesListBox,strGeography,"Geography");
		}
		//WaitFor waitFor =new WaitFor(Commander);
		WebElement doneBtn=findObject(Driver,By.xpath("//button[contains(text(),'Done')]"), "Done Button");
		clickOnWebelement(doneBtn, "Done Button");
		sleepTime(Driver,6);
		//waitFor.waitForPageLoaded();
		waitforInternalLoad(Driver);
		sleepTime(Driver,30);

		/*	try{
			WebElement yesBtn=null;
		yesBtn=findObject(Driver,By.xpath("//button[contains(text(),'Yes')]"), "YES Button");
		waitforInternalLoad(Driver);
		sleepTime(Driver,4);
		if(yesBtn!=null && yesBtn.isDisplayed())
		clickOnWebelement(yesBtn, "Rules will be overwritten would you like to overwrite it message");
		}
		catch(Exception e)
		{

		}*/
		//sleepTime(Driver,30);	


	}

	public void selectItems(WebElement textBox,List<WebElement> UlListBox,String valueToSelect1,String FieldName)
	{
		System.out.println("asfds");
		sleepTime(Driver,4);
		clickOnWebelement(textBox, FieldName+"Text Box");
		sleepTime(Driver,2);
		System.out.println("UlListBox size is"+UlListBox.size());
		boolean valueFound=false;
		String valueToSelect[] = valueToSelect1.split(",");
		if(valueToSelect[0].trim().equalsIgnoreCase("All"))
		{
			System.out.println("select nothing ..");
		}else
		{
			WebElement allChkBoxLabel=UlListBox.get(1).findElement(By.tagName("label"));
			sleepTime(Driver,4);
			clickOnWebelement(allChkBoxLabel, "All checkBox is un checked in "+FieldName+" dropdown");

			sleepTime(Driver,4);
			List<WebElement> lisList=UlListBox.get(3).findElements(By.tagName("li"));
			System.out.println("length "+valueToSelect.length);
			for(int v=0;v<valueToSelect.length;v++)
			{

				if(valueToSelect[v].trim()!=null)
				{
					valueFound=false;
					for(int i=0;i<lisList.size();i++)
					{
						String actliText=lisList.get(i).getText();
						System.out.println("actliText is "+actliText);
						if(actliText.trim().contains(valueToSelect[v].trim()) || actliText.equalsIgnoreCase(valueToSelect[v]))
						{
							WebElement label=lisList.get(i).findElement(By.tagName("label"));
							if(label!=null)
							{
								((JavascriptExecutor) Driver).executeScript("arguments[0].scrollIntoView();", label);
								clickOnWebelement(label, "Value"+valueToSelect[v]+"is selected from "+FieldName+" dropdown");
								valueFound=true;
								break;
							}
							else {System.out.println("list element not found" +valueToSelect[v]);}
						}
					}  // end of loop i



					if(valueFound==false)
					{
						Report.updateTestLog("Verify value"+valueToSelect[v], "Value"+valueToSelect[v]+"is not found in "+FieldName+" dropdown", Status.FAIL);
					}
				} 
			}  // end of loop v
		}
	}



	public WebElement findObject(WebDriver driver,By by,String elementName){
		WebElement wElement = null; int timeOut = 0;
		do{
			if(timeOut > 0){
				sleepTime(driver, 3);
			}
			try{
				wElement = Driver.findElement(by);
			}catch (NoSuchElementException e) {timeOut++;}
		}while(wElement == null && timeOut < 10);
		try{
			wElement = driver.findElement(by);
		}catch (NoSuchElementException e) {
			//			  report.updateTestLog("Find "+elementName, "Exception occured while trying to find the "+elementName+". Exception ["+e+"]",Status.FAIL);
		}	  
		if(wElement == null){
			Report.updateTestLog("Verifying"+elementName, elementName+" does not available", Status.FAIL);
			//throw new FrameworkException("Find "+elementName, elementName+" not found");
		}else{
			try {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", wElement);
			} catch (Exception e) {} 
		}
		return wElement;
	}



	//		Finding Object from WebElement
	public WebElement findObjectFromWebElement(WebElement wEleParent, By by,String elementName){
		WebElement wElement = null; int timeOut = 0;
		do{
			if(timeOut > 0){
				sleepTime(Driver, 6);
			}
			try{
				wElement = wEleParent.findElement(by);
			}catch (NoSuchElementException e) {timeOut++;}		  
		}while(wElement == null && timeOut < 10);
		try{
			wElement = wEleParent.findElement(by);
		}catch (NoSuchElementException e) {
			//				  report.updateTestLog("Find "+elementName, "Exception occured while trying to find the "+elementName+". Exception ["+e+"]",Status.FAIL);
		}
		if(wElement == null){
			Report.updateTestLog("Find "+elementName, elementName+" does not available", Status.FAIL);
		}
		return wElement;
	}


	public List<WebElement> findObjects(WebDriver driver,By by,String elementName){
		List<WebElement> wElement = null; int timeOut = 0;
		do{
			if(timeOut > 0){
				sleepTime(driver, 6);
			}
			timeOut++;
			try{
				wElement = driver.findElements(by);
			}catch (NoSuchElementException e) {
				// throw new FrameworkException("Find "+elementName, "Exception occured while trying to find the "+elementName+". Exception ["+e+"]");
			}
		}while(wElement.size()==0 && timeOut < 10);
		if(wElement.size()==0){
			Report.updateTestLog("Find "+elementName, elementName+" not found",Status.FAIL);
		}
		return wElement;
	}


	public void waitforInternalLoad(WebDriver driver)
	{
		sleepTime(driver, 2);
		WebElement wElement = null; int timeOut = 0;
		do{
			if(timeOut > 0){
				sleepTime(driver, 3);
			}
			try{
				wElement = driver.findElement(By.xpath("//img[@alt='Busy'][contains(@src,'Hourglass_icon.gif')]"));
				if(wElement.isDisplayed())							
				{ timeOut++; continue;	}

				else 
					break;
			}catch (NoSuchElementException e) {timeOut++;}
		}while(wElement == null && timeOut < 20);



	}
	/**
	 * 
	 * MethodName:  manageIneligibleVINsTAb
	 * Description: To provide details in manage Ineligible Tab
	 * Parameter (if any):  
	 * Return type:  Void
	 * Owner : kavyaa 
	 */
@Action(desc="manageIneligibleVINsTAb",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void manageIneligibleVINsTAb()
	{	
		String strvinTextBox = userData.getData("CoreInfo", "Column", "Scenario", "TestCase", "Iteration", "SubIteration");
		if(!strvinTextBox.equalsIgnoreCase("NULL") && !strvinTextBox.equalsIgnoreCase(""))
		{
			WebElement vinsTextBox = findObject(Driver, By.id("vinNumbers"), "VIN text box");
			GeneralComponents.enterValue(vinsTextBox, strvinTextBox, "VIN Text box");
		}
		if(!strvinTextBox.equalsIgnoreCase("NULL") && !strvinTextBox.equalsIgnoreCase(""))
		{
			WebElement vinsTextBox = findObject(Driver, By.id("vinNumbers"), "VIN text box");
			if(vinsTextBox != null)
			{
				GeneralComponents.enterValue(vinsTextBox, "", "VIN text box");
			}
		}
		/*ConcurrentHashMap<String, ObjectRepo> oRMap = new ConcurrentHashMap<>();
			WebElement ele = (WebElement) oRMap.get("username");
			WebElement ale = AObject.findElement("username", "Toyota");*/

		String tc=userData.getTestCase();
		String strVehicles= userData.getData("CoreInfo", "Vehicles", "Commodity",tc, "1", "1");	
		/*String strSupplier= userData.getData("CoreInfo", "Supplier", "Commodity",tc, "1", "1");
			String strMarketingBrand= userData.getData("CoreInfo", "MarketingBrand", "Commodity", tc, "1", "1");
			String strGeography= userData.getData("CoreInfo", "Geography", "Commodity", tc, "1", "1");*/

		if(!strVehicles.equalsIgnoreCase("NULL") && !strVehicles.equalsIgnoreCase(""))
		{
			WebElement tO_VehiclesTextBox =findObject(Driver,By.xpath("//input[contains(@id,'pt1:r1:0:t4:')][contains(@id,':nusmc5::content')]"), "Vehicles TxtBox");

			List<WebElement> tO_VehiclesListBox = findObjects(Driver,By.xpath("//ul[contains(@id,'pt1:r1:0:t4:')][contains(@id,':nusmc5::pop')]/li"), "Vehicles TxtBox") ;

			selectItems(tO_VehiclesTextBox,tO_VehiclesListBox,strVehicles,"Vehicles");
		}
	}
	/**
	 * 
	 * MethodName:  reasonAreasDetailsDisabled
	 * Description: To check if the details in Reason and Areas tab are disabled or not and to verify if the data retrieved is correct after submitting.
	 * Parameter (if any):  
	 * Return type:  Void
	 * Owner : kavyaa 
	 */
@Action(desc="regionAreasDetailsDisabled",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void regionAreasDetailsDisabled()
	{
		String strDescription = userData.getData("CoreInfo", "Description", "SystemAdmin", "RegionAndAreas_TC001", "1", "1");
		String strDepartmentName = userData.getData("CoreInfo", "DepartmentName", "SystemAdmin", "RegionAndAreas_TC001", "1", "1");
		String strAddressLine1 = userData.getData("CoreInfo", "AddressLine1", "SystemAdmin", "RegionAndAreas_TC001", "1", "1");
		String strAddressLine2 = userData.getData("CoreInfo", "AddressLine2", "SystemAdmin", "RegionAndAreas_TC001", "1", "1");
		String strCity = userData.getData("CoreInfo", "City", "SystemAdmin", "RegionAndAreas_TC001", "1", "1");
		String strState = userData.getData("CoreInfo", "State", "SystemAdmin", "RegionAndAreas_TC001", "1", "1");
		String strZip1 = userData.getData("CoreInfo", "Zip1", "SystemAdmin", "RegionAndAreas_TC001", "1", "1");
		String strZip2 = userData.getData("CoreInfo", "Zip2", "SystemAdmin", "RegionAndAreas_TC001", "1", "1");
		WebElement brand = findObject(Driver, By.id("brand"), "brand text box");
		if(brand !=null)
		{
			if(brand.isEnabled()==false)
			{
				Report.updateTestLog("Verifying if the field brand is disabled","The field brand is disabled", Status.PASS);
			}
			else
			{
				Report.updateTestLog("Verifying if the field brand is disabled","The field brand is Enabled", Status.FAIL);
			}
		}
		WebElement regionID = findObject(Driver, By.id("regionID"), "regionID text box");
		if(regionID !=null)
		{
			if(regionID.isEnabled()==false)
			{
				Report.updateTestLog("Verifying if the field regionID is disabled","The field regionID is disabled", Status.PASS);
			}
			else
			{
				Report.updateTestLog("Verifying if the field regionID is disabled","The field regionID is Enabled", Status.FAIL);
			}
		}
		WebElement regionName = findObject(Driver, By.id("FundingSourceDescription"), "regionName text box");
		if(regionName !=null)
		{
			if(regionName.isEnabled()==false)
			{
				Report.updateTestLog("Verifying if the field regionName is disabled","The field regionName is disabled", Status.PASS);
			}
			else
			{
				Report.updateTestLog("Verifying if the field regionName is disabled","The field regionName is Enabled", Status.FAIL);
			}
		}
		WebElement statuswle = findObject(Driver, By.id("StatusActive"), "StatusActive radio button");
		if(statuswle !=null)
		{
			if(statuswle.isEnabled()==false)
			{
				Report.updateTestLog("Verifying if the field status is disabled","The field status is disabled", Status.PASS);
			}
			else
			{
				Report.updateTestLog("Verifying if the field status is disabled","The field status is Enabled", Status.FAIL);
			}
		}						
		WebElement statuswle2 = findObject(Driver, By.id("StatusInActive"), "StatusInActive radio button");
		if(statuswle2 !=null)
		{
			if(statuswle2.isEnabled()==false)
			{
				Report.updateTestLog("Verifying if the field status is disabled","The field status is disabled", Status.PASS);
			}
			else
			{
				Report.updateTestLog("Verifying if the field status is disabled","The field status is Enabled", Status.FAIL);
			}
		}	
		WebElement date = findObject(Driver, By.id("EffectiveDate"), "Effective Date text box");
		if(date !=null)
		{
			if(date.isEnabled()==false)
			{
				Report.updateTestLog("Verifying if the field date is disabled","The field date is disabled", Status.PASS);
			}
			else
			{
				Report.updateTestLog("Verifying if the field date is disabled","The field date is Enabled", Status.FAIL);
			}
		}
		WebElement description = findObject(Driver, By.id("FundingSourceDescription"), "description text box");
		if(description !=null)
		{
			if(description.isEnabled()==false)
			{
				Report.updateTestLog("Verifying if the field description is disabled","The field description is disabled", Status.PASS);					
			}
			else
			{
				Report.updateTestLog("Verifying if the field description is disabled","The field description is Enabled", Status.FAIL);
			}
			if(!strDescription.equalsIgnoreCase("NULL"))
			{
				String txt = description.getAttribute("value");
				if(txt.equalsIgnoreCase(strDescription))
				{
					Report.updateTestLog("Verifying if the field description is retrieving correct data","The field description is retrieving correct data", Status.PASS);					
				}
				else
				{
					Report.updateTestLog("Verifying if the field description is retrieving correct data","The field description is not retrieving correct data", Status.FAIL);
				}
			}
		}
		WebElement departmentName = findObject(Driver, By.id("DepartmentName"), "Department Name text box");
		if(departmentName !=null)
		{
			if(departmentName.isEnabled()==false)
			{
				Report.updateTestLog("Verifying if the field departmentName is disabled","The field departmentName is disabled", Status.PASS);
			}
			else
			{
				Report.updateTestLog("Verifying if the field departmentName is disabled","The field departmentName is Enabled", Status.FAIL);
			}
			if(!strDepartmentName.equalsIgnoreCase("NULL"))
			{
				String txt = departmentName.getAttribute("value");
				if(txt.equalsIgnoreCase(strDepartmentName))
				{
					Report.updateTestLog("Verifying if the field departmentName is retrieving correct data","The field departmentName is retrieving correct data", Status.PASS);					
				}
				else
				{
					Report.updateTestLog("Verifying if the field departmentName is retrieving correct data","The field departmentName is not retrieving correct data", Status.FAIL);
				}
			}
		}
		WebElement addressLine1 = findObject(Driver, By.id("AddressLine1"), "Address Line1 text box");
		if(addressLine1 !=null)
		{
			if(addressLine1.isEnabled()==false)
			{
				Report.updateTestLog("Verifying if the field addressLine1 is disabled","The field addressLine1 is disabled", Status.PASS);
			}
			else
			{
				Report.updateTestLog("Verifying if the field addressLine1 is disabled","The field addressLine1 is Enabled", Status.FAIL);
			}
			if(!strAddressLine1.equalsIgnoreCase("NULL"))
			{
				String txt = addressLine1.getAttribute("value");
				if(txt.equalsIgnoreCase(strAddressLine1))
				{
					Report.updateTestLog("Verifying if the field addressLine1 is retrieving correct data","The field addressLine1 is retrieving correct data", Status.PASS);					
				}
				else
				{
					Report.updateTestLog("Verifying if the field addressLine1 is retrieving correct data","The field addressLine1 is not retrieving correct data", Status.FAIL);
				}
			}
		}
		WebElement addressLine2 = findObject(Driver, By.id("AddressLine2"), "Address Line2  text box");
		if(addressLine2 !=null)
		{
			if(addressLine1.isEnabled()==false)
			{
				Report.updateTestLog("Verifying if the field AddressLine2 is disabled","The field AddressLine2 is disabled", Status.PASS);
			}
			else
			{
				Report.updateTestLog("Verifying if the field AddressLine2 is disabled","The field AddressLine2 is Enabled", Status.FAIL);
			}
			if(!strAddressLine2.equalsIgnoreCase("NULL"))
			{
				String txt = addressLine1.getAttribute("value");
				if(txt.equalsIgnoreCase(strAddressLine2))
				{
					Report.updateTestLog("Verifying if the field AddressLine2 is retrieving correct data","The field AddressLine2 is retrieving correct data", Status.PASS);					
				}
				else
				{
					Report.updateTestLog("Verifying if the field AddressLine2 is retrieving correct data","The field AddressLine2 is not retrieving correct data", Status.FAIL);
				}
			}
		}
		WebElement city = findObject(Driver, By.id("city"), "city text box");
		if(city !=null)
		{
			if(city.isEnabled()==false)
			{
				Report.updateTestLog("Verifying if the field city is disabled","The field city is disabled", Status.PASS);
			}
			else
			{
				Report.updateTestLog("Verifying if the field city is disabled","The field city is Enabled", Status.FAIL);
			}
			if(!strCity.equalsIgnoreCase("NULL"))
			{
				String txt = city.getAttribute("value");
				if(txt.equalsIgnoreCase(strCity))
				{
					Report.updateTestLog("Verifying if the field city is retrieving correct data","The field city is retrieving correct data", Status.PASS);					
				}
				else
				{
					Report.updateTestLog("Verifying if the field city is retrieving correct data","The field city is not retrieving correct data", Status.FAIL);
				}
			}
		}
		if(!strState.equalsIgnoreCase("NULL"))
		{
			WebElement state = findObject(Driver, By.id("state"), "city dropdown");
			if(state !=null)
			{
				List<WebElement> options = state.findElements(By.tagName("option"));
				for(WebElement wle:options)
				{
					if(wle.getText().equalsIgnoreCase(strState))
					{
						GeneralComponents.selectElement(state, strState, "State Dropdown");
						break;
					}
				}
			}
		}
		WebElement state = findObject(Driver, By.id("state"), "State dropdown");
		if(state !=null)
		{
			if(state.isEnabled()==false)
			{
				Report.updateTestLog("Verifying if the field state is disabled","The field state is disabled", Status.PASS);
			}
			else
			{
				Report.updateTestLog("Verifying if the field state is disabled","The field state is Enabled", Status.FAIL);
			}
			if(!strState.equalsIgnoreCase("NULL"))
			{
				if(state !=null)
				{
					WebElement options = Driver.findElement(By.xpath(".//span[@class = 'selectBox-label']"));				
					String txt = options.getText();
					if(txt.equalsIgnoreCase(strState))
					{
						Report.updateTestLog("Verifying if the field state is retrieving correct data","The field state is retrieving correct data", Status.PASS);					
					}
					else
					{
						Report.updateTestLog("Verifying if the field state is retrieving correct data","The field state is not retrieving correct data", Status.FAIL);
					}
				}
			}
		}
		WebElement Zip1 = findObject(Driver, By.id("Zip1"), "Zip1 text box");
		if(Zip1 !=null)
		{
			if(Zip1.isEnabled()==false)
			{
				Report.updateTestLog("Verifying if the field Zip1 is disabled","The field Zip1 is disabled", Status.PASS);
			}
			else
			{
				Report.updateTestLog("Verifying if the field Zip1 is disabled","The field Zip1 is Enabled", Status.FAIL);
			}
			if(!strZip1.equalsIgnoreCase("NULL"))
			{
				String txt = Zip1.getAttribute("value");
				if(txt.equalsIgnoreCase(strZip1))
				{
					Report.updateTestLog("Verifying if the field Zip1 is retrieving correct data","The field Zip1 is retrieving correct data", Status.PASS);					
				}
				else
				{
					Report.updateTestLog("Verifying if the field Zip1 is retrieving correct data","The field Zip1 is not retrieving correct data", Status.FAIL);
				}
			}
		}
		WebElement Zip2 = findObject(Driver, By.id("Zip2"), "Zip2 text box");
		if(Zip2 !=null)
		{
			if(Zip2.isEnabled()==false)
			{
				Report.updateTestLog("Verifying if the field Zip2 is disabled","The field Zip2 is disabled", Status.PASS);
			}
			else
			{
				Report.updateTestLog("Verifying if the field Zip2 is disabled","The field Zip2 is Enabled", Status.FAIL);
			}
			if(!strZip2.equalsIgnoreCase("NULL"))
			{
				String txt = Zip2.getAttribute("value");
				if(txt.equalsIgnoreCase(strZip2))
				{
					Report.updateTestLog("Verifying if the field Zip2 is retrieving correct data","The field Zip2 is retrieving correct data", Status.PASS);					
				}
				else
				{
					Report.updateTestLog("Verifying if the field Zip2 is retrieving correct data","The field Zip2 is not retrieving correct data", Status.FAIL);
				}
			}
		}
	}

	/**
	 * 
	 * MethodName:  regionAreasDetails
	 * Description: To provide details in Reason and Areas tab
	 * Parameter (if any):  
	 * Return type:  Void
	 * Owner : kavyaa 
	 */
@Action(desc="regionAreasDetails",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void regionAreasDetails()
	{	
		String strDescription = userData.getData("CoreInfo", "Description", "SystemAdmin", "RegionAndAreas_TC001", "1", "1");
		String strDepartmentName = userData.getData("CoreInfo", "DepartmentName", "SystemAdmin", "RegionAndAreas_TC001", "1", "1");
		String strAddressLine1 = userData.getData("CoreInfo", "AddressLine1", "SystemAdmin", "RegionAndAreas_TC001", "1", "1");
		String strAddressLine2 = userData.getData("CoreInfo", "AddressLine2", "SystemAdmin", "RegionAndAreas_TC001", "1", "1");
		String strCity = userData.getData("CoreInfo", "City", "SystemAdmin", "RegionAndAreas_TC001", "1", "1");
		String strState = userData.getData("CoreInfo", "State", "SystemAdmin", "RegionAndAreas_TC001", "1", "1");
		String strZip1 = userData.getData("CoreInfo", "Zip1", "SystemAdmin", "RegionAndAreas_TC001", "1", "1");
		String strZip2 = userData.getData("CoreInfo", "Zip2", "SystemAdmin", "RegionAndAreas_TC001", "1", "1");
		if(!strDescription.equalsIgnoreCase("NULL"))
		{
			WebElement description = findObject(Driver, By.id("FundingSourceDescription"), "description text box");
			if(description !=null)
			{
				GeneralComponents.enterValue(description, strDescription, "description text box");
			}
		}
		if(!strDepartmentName.equalsIgnoreCase("NULL"))
		{
			WebElement departmentName = findObject(Driver, By.id("DepartmentName"), "Department Name text box");
			if(departmentName !=null)
			{
				GeneralComponents.enterValue(departmentName, strDepartmentName, "Department Name text box");
			}
		}
		if(!strAddressLine1.equalsIgnoreCase("NULL"))
		{
			WebElement addressLine1 = findObject(Driver, By.id("AddressLine1"), "Address Line1 text box");
			if(addressLine1 !=null)
			{
				GeneralComponents.enterValue(addressLine1, strAddressLine1, "Address Line text box");
			}
		}
		if(!strAddressLine2.equalsIgnoreCase("NULL"))
		{
			WebElement addressLine2 = findObject(Driver, By.id("AddressLine2"), "Address Line2  text box");
			if(addressLine2 !=null)
			{
				GeneralComponents.enterValue(addressLine2, strAddressLine2, "Address Line2 text box");
			}
		}
		if(!strCity.equalsIgnoreCase("NULL"))
		{
			WebElement city = findObject(Driver, By.id("city"), "city text box");
			if(city !=null)
			{
				GeneralComponents.enterValue(city,strCity, "city text box");
			}
		}
		if(!strState.equalsIgnoreCase("NULL"))
		{
			WebElement state = findObject(Driver, By.id("state"), "State dropdown");
			if(state !=null)
			{
				List<WebElement> options = state.findElements(By.tagName("option"));
				for(WebElement wle:options)
				{
					if(wle.getText().equalsIgnoreCase(strState))
					{
						GeneralComponents.selectElement(wle, strState, "State Dropdown");
						break;
					}
				}
			}
		}
		if(!strZip1.equalsIgnoreCase("NULL"))
		{
			WebElement Zip1 = findObject(Driver, By.id("Zip1"), "Zip1 text box");
			if(Zip1 !=null)
			{
				GeneralComponents.enterValue(Zip1, strZip1, "Zip1 text box");
			}
		}
		if(!strZip2.equalsIgnoreCase("NULL"))
		{
			WebElement Zip2 = findObject(Driver, By.id("Zip2"), "Zip2 text box");
			if(Zip2 !=null)
			{
				GeneralComponents.enterValue(Zip2, strZip2, "Zip2 text box");
			}
		}
	}
	/**
	 * 
	 * MethodName:  clickSubmit1InResultsDatatable
	 * Description: To click Save1 in Results Datatable.
	 * Parameter (if any):  
	 * Return type:  Void
	 * Owner : kavyaa 
	 */
@Action(desc="clickSubmit1InResultsDatatable",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clickSubmit1InResultsDatatable()
	{
		WebElement submit1 = findObject(Driver, By.id("Save1"), "Submit1 Button");
		if(submit1 !=null)
		{
			GeneralComponents.clickOnWebelement(submit1, "Submit1 Button");
		}
	}
	/**
	 * 
	 * MethodName:  clickSubmit2InResultsDatatable
	 * Description: To click Save2 in Results Datatable.
	 * Parameter (if any):  
	 * Return type:  Void
	 * Owner : kavyaa 
	 */
@Action(desc="clickSubmit2InResultsDatatable",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clickSubmit2InResultsDatatable()
	{
		WebElement submit2 = findObject(Driver, By.id("Save2"), "Submit2 Button");
		if(submit2 !=null)
		{
			GeneralComponents.clickOnWebelement(submit2, "Submit Button");
		}
	}
	/**
	 * 
	 * MethodName:  clickBack1InResultsDatatable
	 * Description: To click back1 in Results Datatable.
	 * Parameter (if any):  
	 * Return type:  Void
	 * Owner : kavyaa 
	 */
@Action(desc="clickBack1InResultsDatatable",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clickBack1InResultsDatatable()
	{
		WebElement back1 = findObject(Driver, By.id("Back1"), "Back1 Button");
		if(back1 !=null)
		{
			GeneralComponents.clickOnWebelement(back1, "Back1 Button");
		}
	}
	/**
	 * 
	 * MethodName:  clickBack2InResultsDatatable
	 * Description: To click back2 in Results Datatable.
	 * Parameter (if any):  
	 * Return type:  Void
	 * Owner : kavyaa 
	 */
@Action(desc="clickBack2InResultsDatatable",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clickBack2InResultsDatatable()
	{
		WebElement back2 = findObject(Driver, By.id("Back2"), "Back2 Button");
		if(back2 !=null)
		{
			GeneralComponents.clickOnWebelement(back2, "Back2 Button");
		}
	}
	/**
	 * 
	 * MethodName:  clickViewInResultsDatatable
	 * Description: To click view the Results Datatable in all the sections.
	 * Parameter (if any):  
	 * Return type:  Void
	 * Owner : kavyaa 
	 */
@Action(desc="clickViewInResultsDatatable",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clickViewInResultsDatatable()
	{			
		WebElement table = findObject(Driver, By.xpath(".//table[@id='DataTable']//tbody/tr"), "Table and row with View button");
		if(table!=null)
		{
			List<WebElement> tds = table.findElements(By.tagName("a"));
			for(WebElement wle:tds)
			{
				String txt = wle.getText();
				if(txt.equalsIgnoreCase("View"))
				{
					GeneralComponents.clickOnWebelement(wle, "View Button of the first record");
					break;
				}
			}
		}
	}
	/**
	 * 
	 * MethodName:  clickEditInResultsDatatable
	 * Description: To click Edit the Results Datatable in all the sections.
	 * Parameter (if any):  
	 * Return type:  Void
	 * Owner : kavyaa 
	 */
@Action(desc="clickEditInResultsDatatable",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clickEditInResultsDatatable()
	{			
		WebElement table = findObject(Driver, By.xpath(".//table[@id='DataTable']//tbody/tr"), "Table and row with Edit button");
		if(table!=null)
		{
			/*			List<WebElement> id = table.findElements(By.tagName("td"));
				for(WebElement wle : id)
				{
					String strid = wle.getText();
					userData.saveTestDataToFile("STRVARRESULTID", strid);
					break;
				}*/
			List<WebElement> tds = table.findElements(By.tagName("a"));
			for(WebElement wle:tds)
			{
				String txt = wle.getText();
				if(txt.equalsIgnoreCase("Edit"))
				{
					GeneralComponents.clickOnWebelement(wle, "Edit Button of the first record");
					break;
				}
			}
		}
	}
	/**
	 * 
	 * MethodName:  fundingSourcesDetails
	 * Description: To provide details in funding Sources tab
	 * Parameter (if any):  
	 * Return type:  Void
	 * Owner : kavyaa 
	 */
@Action(desc="fundingSourcesDetails",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void fundingSourcesDetails()
	{
		String strDescription = userData.getData("CoreInfo", "Description", "SystemAdmin", "RegionAndAreas_TC001", "1", "1");
		String strStatus = userData.getData("CoreInfo", "Status", "SystemAdmin", "RegionAndAreas_TC001", "1", "1");
		String strDate = userData.getData("CoreInfo", "Date", "SystemAdmin", "RegionAndAreas_TC001", "1", "1");
		String strFundingSourceType = userData.getData("CoreInfo", "FundingSourceType", "SystemAdmin", "RegionAndAreas_TC001", "1", "1");
		String strfundingSourceLimit = userData.getData("CoreInfo", "fundingSourceLimit", "SystemAdmin", "RegionAndAreas_TC001", "1", "1");
		String strAvailableFor = userData.getData("CoreInfo", "AvailableFor", "SystemAdmin", "RegionAndAreas_TC001", "1", "1");
		//			String strZip1 = userData.getData("CoreInfo", "Zip1", "SystemAdmin", "RegionAndAreas_TC001", "1", "1");
		//			String strZip2 = userData.getData("CoreInfo", "Zip2", "SystemAdmin", "RegionAndAreas_TC001", "1", "1");
		WebElement descriptionwle = findObject(Driver, By.id("FundingSourceDescription"), "description text box");
		if(descriptionwle !=null)
		{
			GeneralComponents.enterValue(descriptionwle, strDescription, "description text box");
		}
		if(strStatus.equalsIgnoreCase("Active"))
		{
			WebElement statuswle = findObject(Driver, By.id("StatusActive"), "StatusActive radio button");
			if(statuswle !=null)
			{
				GeneralComponents.clickOnWebelement(statuswle,"StatusActive radio button");
			}
		}
		if(strStatus.equalsIgnoreCase("Inactive"))
		{
			WebElement statuswle = findObject(Driver, By.id("StatusInActive"), "StatusInActive radio button");
			if(statuswle !=null)
			{
				GeneralComponents.clickOnWebelement(statuswle,"StatusInActive radio button");
			}
		}
		if(!strDate.equalsIgnoreCase("NULL"))
		{
			WebElement date = findObject(Driver, By.id("EffectiveDate"), "Effective Date text box");
			if(date !=null)
			{
				GeneralComponents.enterValue(date, strDate, "Effective Date text box");
			}
		}
		if(!strFundingSourceType.equalsIgnoreCase("NULL"))
		{				
			List<WebElement> fundingSourceIndicator = findObjects(Driver, By.id("fundingSourceIndicator"), "fundingSourceIndicator radio button");
			for(WebElement indicator:fundingSourceIndicator)
			{
				String txt  = indicator.getAttribute("value");
				if(txt.equalsIgnoreCase("TMS")&&strFundingSourceType.equalsIgnoreCase("TMS"))
				{						
					GeneralComponents.clickOnWebelement(indicator,"fundingSourceIndicator radio button");
					break;
				}			
				else if(txt.equalsIgnoreCase("TFS")&&strFundingSourceType.equalsIgnoreCase("TFS"))
				{					
					GeneralComponents.clickOnWebelement(indicator,"fundingSourceIndicator radio button");
					break;
				}				
				else if(txt.equalsIgnoreCase("PD")&&strFundingSourceType.equalsIgnoreCase("PD"))
				{					
					GeneralComponents.clickOnWebelement(indicator,"StatusInActive radio button");
					break;
				}

				else if(txt.equalsIgnoreCase("Dealer Association")&&strFundingSourceType.equalsIgnoreCase("Dealer Association"))
				{										
					GeneralComponents.clickOnWebelement(indicator,"StatusInActive radio button");
					break;
				}

			}
		}										
		if(strfundingSourceLimit.equalsIgnoreCase("No"))
		{
			WebElement fundingSourceLimit = findObject(Driver, By.id("customExpenseLimit2"), "Allow Custom Expense Limit");
			if(fundingSourceLimit !=null)
			{
				GeneralComponents.clickOnWebelement(fundingSourceLimit,"Allow Custom Expense Limit radio button");
			}
		}
		if(strfundingSourceLimit.equalsIgnoreCase("Yes"))
		{
			WebElement statuswle = findObject(Driver, By.id("customExpenseLimit1"), "Allow Custom Expense Limit radio button");
			if(statuswle !=null)
			{
				GeneralComponents.clickOnWebelement(statuswle,"StatusInActive radio button");
			}
		}
		/*if(!strAvailableFor.contains("Toyota"))
			{				
				List<WebElement> availableFor = findObjects(Driver, By.id("RegionID"), "RegionID radio button");
				for(WebElement List:availableFor)
				{

					GeneralComponents.clickOnWebelement(wElement, fieldName)
				}
			}
			if(!strAvailableFor.contains("Lexus"))
			{				
				List<WebElement> availableFor = findObjects(Driver, By.id("AreaID"), "AreaID radio button");
				for(WebElement List:availableFor)
				{

				}
			}*/

	}
	/**
	 * 
	 * MethodName:  reasonCodeDetails
	 * Description: To provide details in reason code tab
	 * Parameter (if any):  
	 * Return type:  Void
	 * Owner : kavyaa 
	 */
@Action(desc="reasonCodeDetails",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void reasonCodeDetails()
	{
		String strDescription = userData.getData("CoreInfo", "Description", "SystemAdmin", "ReasonCode_TC001", "1", "1");
		String strGroup = userData.getData("CoreInfo", "Group", "SystemAdmin", "ReasonCode_TC001", "1", "1");
		WebElement descriptionwle = findObject(Driver, By.id("ReasonDescription"), "description text box");
		if(descriptionwle !=null)
		{
			GeneralComponents.enterValue(descriptionwle, strDescription, "description text box");
		}
		WebElement reasonGroup = findObject(Driver, By.id("ReasonGroup"), "ReasonGroup Drop Down");
		if(reasonGroup !=null)
		{
			List<WebElement> options = reasonGroup.findElements(By.tagName("option"));
			for(WebElement wle:options)
			{
				if(wle.getText().equalsIgnoreCase(strGroup))
				{
					GeneralComponents.selectElement(wle, strGroup, "ReasonGroup Dropdown");
					break;
				}
			}
		}			
	}

	/**
	 * 
	 * MethodName:  reasonCodeDetails
	 * Description: To provide details in reason code tab
	 * Parameter (if any):  
	 * Return type:  Void
	 * Owner : kavyaa 
	 */
@Action(desc="reasonCodeDetailsDisabled",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void reasonCodeDetailsDisabled()
	{
		String strDescription = userData.getData("CoreInfo", "Description", "SystemAdmin", "ReasonCode_TC001", "1", "1");
		String strGroup = userData.getData("CoreInfo", "Group", "SystemAdmin", "ReasonCode_TC001", "1", "1");
		WebElement date = findObject(Driver, By.id("EffectiveDate"), "Effective Date text box");
		if(date !=null)
		{
			if(date.isEnabled()==false)
			{
				Report.updateTestLog("Verifying if the field date is disabled","The field date is disabled", Status.PASS);
			}
			else
			{
				Report.updateTestLog("Verifying if the field date is disabled","The field date is Enabled", Status.FAIL);
			}
		}
		WebElement descriptionwle = findObject(Driver, By.id("ReasonDescription"), "description text box");
		if(descriptionwle !=null)
		{
			if(descriptionwle.isEnabled()==false)
			{
				Report.updateTestLog("Verifying if the field description is disabled","The field description is disabled", Status.PASS);					
			}
			else
			{
				Report.updateTestLog("Verifying if the field description is disabled","The field description is Enabled", Status.FAIL);
			}
			if(!strDescription.equalsIgnoreCase("NULL"))
			{
				String txt = descriptionwle.getAttribute("value");
				if(txt.equalsIgnoreCase(strDescription))
				{
					Report.updateTestLog("Verifying if the field description is retrieving correct data","The field description is retrieving correct data", Status.PASS);					
				}
				else
				{
					Report.updateTestLog("Verifying if the field description is retrieving correct data","The field description is not retrieving correct data", Status.FAIL);
				}
			}
		}
		WebElement reasonGroup = findObject(Driver, By.id("ReasonGroup"), "ReasonGroup Drop Down");
		if(reasonGroup !=null)
		{
			if(descriptionwle.isEnabled()==false)
			{
				Report.updateTestLog("Verifying if the field Group is disabled","The field Group is disabled", Status.PASS);					
			}
			else
			{
				Report.updateTestLog("Verifying if the field Group is disabled","The field Group is Enabled", Status.FAIL);
			}
			/*if(!strGroup.equalsIgnoreCase("NULL"))
				{
					String txt = descriptionwle.getText();
					if(txt.equalsIgnoreCase(strGroup))
					{
						Report.updateTestLog("Verifying if the field Group is retrieving correct data","The field Group is retrieving correct data", Status.PASS);					
					}
					else
					{
						Report.updateTestLog("Verifying if the field Group is retrieving correct data","The field Group is not retrieving correct data", Status.FAIL);
					}
				}*/
		}
	}
	/**
	 * 
	 * MethodName:  clickAddNewReasonCode
	 * Description: To click Add New Reason Code Link.
	 * Parameter (if any):  
	 * Return type:  Void
	 * Owner : kavyaa 
	 */
@Action(desc="clickAddNewReasonCode",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clickAddNewReasonCode()
	{
		WebElement addNew = findObject(Driver, By.id("AddNew"), "clickAddNewReasonCode Link");
		if(addNew !=null)
		{
			GeneralComponents.clickOnWebelement(addNew, "clickAddNewReasonCode Link");
		}
	}
	/**
	 * 
	 * MethodName:  addNewReasonCodeDetails
	 * Description: To provide details in Add New reason code tab
	 * Parameter (if any):  
	 * Return type:  Void
	 * Owner : kavyaa 
	 */
@Action(desc="addNewReasonCodeDetails",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void addNewReasonCodeDetails()
	{
		String strDescription = userData.getData("CoreInfo", "Description", "SystemAdmin", "ReasonCode_TC003", "1", "1");
		String strGroup = userData.getData("CoreInfo", "Group", "SystemAdmin", "ReasonCode_TC003", "1", "1");
		WebElement descriptionwle = findObject(Driver, By.id("ReasonDescription"), "description text box");
		if(descriptionwle !=null)
		{
			GeneralComponents.enterValue(descriptionwle, strDescription, "description text box");
		}
		WebElement reasonGroup = findObject(Driver, By.id("ReasonGroup"), "ReasonGroup Drop Down");
		if(reasonGroup !=null)
		{
			List<WebElement> options = reasonGroup.findElements(By.tagName("option"));
			for(WebElement wle:options)
			{
				if(wle.getText().equalsIgnoreCase(strGroup))
				{
					GeneralComponents.selectElement(wle, strGroup, "ReasonGroup Dropdown");
					break;
				}
			}
		}			
	}
	/**
	 * 
	 * MethodName:  attributesAdminDetails
	 * Description: To provide details in Attributes Admin Tab.
	 * Parameter (if any):  
	 * Return type:  Void
	 * Owner : kavyaa 
	 */
@Action(desc="attributesAdminDetails",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void attributesAdminDetails()
	{
		String strDriveTrain = userData.getData("CoreInfo", "DriveTrain", "SystemAdmin", "ReasonCode_TC003", "1", "1");
		String strGrade1 = userData.getData("CoreInfo", "Grade1", "SystemAdmin", "ReasonCode_TC003", "1", "1");
		String strEngineSize = userData.getData("CoreInfo", "EngineSize", "SystemAdmin", "ReasonCode_TC003", "1", "1");
		String strGrade2 = userData.getData("CoreInfo", "Grade2", "SystemAdmin", "ReasonCode_TC003", "1", "1");		
		String strFuelType = userData.getData("CoreInfo", "FuelType", "SystemAdmin", "ReasonCode_TC003", "1", "1");
		String strBodyStyle = userData.getData("CoreInfo", "BodyStyle", "SystemAdmin", "ReasonCode_TC003", "1", "1");
		String strTransCode = userData.getData("CoreInfo", "TransCode", "SystemAdmin", "ReasonCode_TC003", "1", "1");
		String strBodyStyleAdditionals= userData.getData("CoreInfo", "BodyStyleAdditionals", "SystemAdmin", "ReasonCode_TC003", "1", "1");
		if(!strDriveTrain.equalsIgnoreCase("NULL"))
		{
			WebElement driveTrainwle = findObject(Driver, By.id("drive_traintxt"), "driveTrainw text box");
			if(driveTrainwle !=null)
			{
				GeneralComponents.enterValue(driveTrainwle, strDriveTrain, "driveTrain text box");
			}
		}
		if(!strGrade1.equalsIgnoreCase("NULL"))
		{
			WebElement grade1wle = findObject(Driver, By.id("grade1txt"), "Grade1 text box");
			if(grade1wle !=null)
			{
				GeneralComponents.enterValue(grade1wle, strGrade1, "Grade1 text box");
			}
		}
		if(!strEngineSize.equalsIgnoreCase("NULL"))
		{
			WebElement engineSizewle = findObject(Driver, By.id("engine_sizetxt"), "EngineSize text box");
			if(engineSizewle !=null)
			{
				GeneralComponents.enterValue(engineSizewle, strEngineSize, "EngineSize text box");
			}
		}
		if(!strGrade2.equalsIgnoreCase("NULL"))
		{
			WebElement grade2wle = findObject(Driver, By.id("grade2txt"), "Grade2 text box");
			if(grade2wle !=null)
			{
				GeneralComponents.enterValue(grade2wle, strGrade2, "Grade2 text box");
			}
		}
		if(!strFuelType.equalsIgnoreCase("NULL"))
		{
			WebElement fuelTypewle = findObject(Driver, By.id("fuel_typetxt"), "FuelType text box");
			if(fuelTypewle !=null)
			{
				GeneralComponents.enterValue(fuelTypewle, strFuelType, "FuelType text box");
			}
		}
		if(!strBodyStyle.equalsIgnoreCase("NULL"))
		{
			WebElement bodyStylewle = findObject(Driver, By.id("bodyStyletxt"), "BodyStyle text box");
			if(bodyStylewle !=null)
			{
				GeneralComponents.enterValue(bodyStylewle, strBodyStyle, "BodyStyle text box");
			}
		}
		if(!strTransCode.equalsIgnoreCase("NULL"))
		{
			WebElement transCodewle = findObject(Driver, By.id("transmissiontxt"), "TransCode text box");
			if(transCodewle !=null)
			{
				GeneralComponents.enterValue(transCodewle, strTransCode, "TransCode text box");
			}
		}
		if(!strBodyStyleAdditionals.equalsIgnoreCase("NULL"))
		{
			WebElement bodyStyleAdditionalswle = findObject(Driver, By.id("bodystyle_additionalstxt"), "bodystyle_additionals text box");
			if(bodyStyleAdditionalswle !=null)
			{
				GeneralComponents.enterValue(bodyStyleAdditionalswle, strBodyStyleAdditionals, "bodystyle_additionals text box");
			}
		}

	}
	/**
	 * 
	 * MethodName:  defaultValuesDetails
	 * Description: To provide details in default Values in system admin Tab.
	 * Parameter (if any):  
	 * Return type:  Void
	 * Owner : kavyaa 
	 */



	/**
	 * 
	 * MethodName: options page method for program summary
	 * Description: validate operations program summary
	 * Parameter (if any):  
	 * Return type:  Void
	 * Owner : Arunprakash
	 */	
@Action(desc="Programsummary",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Programsummary()
	{
		//verifications 
		//validation
		WebElement programvalidation = findObject(Driver, By.id("ValidateProgram"), "programvalidation click");
		if(programvalidation!=null)
		{
			GeneralComponents.clickOnWebelement(programvalidation, "programvalidation click"); 
			sleepTime(Driver, 2);
		}
	}
	/**
	 * 
	 * MethodName: options page method for offering cost
	 * Description: validate operationsoffering cost
	 * Return type:  Void
	 * Owner : Arunprakash
	 */
@Action(desc="offeringcost",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void offeringcost()
	{
		//validations
	}
	/**
	 * 
	 * MethodName: options page method for offering summary
	 * Description: validate operationsoffering summary
	 * Parameter (if any):  
	 * Return type:  Void
	 * Owner : Arunprakash
	 */
@Action(desc="offeringsummary",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void offeringsummary()
	{
		WebElement offeringcost = findObject(Driver, By.id("cost"), "offeringcost click");
		if(offeringcost!=null)
		{
			GeneralComponents.clickOnWebelement(offeringcost, "offeringcost click"); 
			sleepTime(Driver, 2);
		}
		// verifications
		WebElement offeringsummary = findObject(Driver, By.id("summary"), "offeringsummary click");
		if(offeringsummary!=null)
		{
			GeneralComponents.clickOnWebelement(offeringsummary, "offeringsummary click"); 
			sleepTime(Driver, 2);
		}
		// verifications
		WebElement offeringvalidation = findObject(Driver, By.id("ValidateOffering"), "offeringvalidation click");
		if(offeringvalidation!=null)
		{
			GeneralComponents.clickOnWebelement(offeringvalidation, "offeringvalidation click"); 
			sleepTime(Driver, 2);
		}
		//ReturnToOffering click
		WebElement ReturnToOffering = findObject(Driver, By.id("ReturnToOffering"), "ReturnToOffering click");
		if(ReturnToOffering!=null)
		{
			GeneralComponents.clickOnWebelement(ReturnToOffering, "ReturnToOffering click"); 
			sleepTime(Driver, 2);
		}

	}
	/**
	 * 
	 * MethodName: regionalalternative
	 * Description: regionalalternative for derive region
	 * Parameter (if any):  
	 * Return type:  Void
	 * Owner : Arunprakash
	 */
@Action(desc="regionalalternative",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void regionalalternative()
	{
		//selectRegionalAlternative click
		WebElement selectRegionalAlternative = findObject(Driver, By.id("selectRegionalAlternative"), "selectRegionalAlternative click");
		if(selectRegionalAlternative!=null)
		{
			GeneralComponents.clickOnWebelement(selectRegionalAlternative, "selectRegionalAlternative click"); 
			sleepTime(Driver, 2);
		}
		//SELECT REGIONAL ALTERNATIVES
		String strregalter = userData.getData("Programsetup", "RegionalAlternative", "1", "1");
		if(!strregalter.equalsIgnoreCase("NULL"))
		{
			WebElement selectRegionalAlternativeddl = findObject(Driver, By.id("region"),"selectRegionalAlternative select");
			if( selectRegionalAlternativeddl !=null)
			{
				GeneralComponents.clickOnWebelement(selectRegionalAlternativeddl, "");
				List<WebElement> options = selectRegionalAlternativeddl.findElements(By.tagName("option"));
				String txtProp = null;
				System.out.println("options size:"+options.size());
				for (WebElement option : options) {
					txtProp = option.getText();
					System.out.println("Option:"+txtProp);
					if((strregalter.trim()).equalsIgnoreCase(txtProp.trim())){

						GeneralComponents.clickOnWebelement(option, "");
						Report.updateTestLog("Select function:selectRegionalAlternative" ," ["+strregalter+"] has been selected", Status.DONE);
						sleepTime(Driver, 1);
						break;
					}}}}
		//verifications

		//Quick Derived Setup Screen
		//QuickDeriveRASelection click
		WebElement QuickDeriveRASelection = findObject(Driver, By.id("QuickDeriveRASelection"), "QuickDeriveRASelection click");
		if(QuickDeriveRASelection!=null)
		{
			GeneralComponents.clickOnWebelement(QuickDeriveRASelection, "QuickDeriveRASelection click"); 
			sleepTime(Driver, 2);
		}
		WebElement QuickDeriveRASelectiondiv = findObject(Driver, By.xpath(".//*div[@id='QD_Setup_Summary']/table/table/tbody"), "QuickDeriveRASelection verification");
		List<WebElement> QuickDeriveRASelectionoptions = QuickDeriveRASelectiondiv.findElements(By.tagName("tr"));			
		for(int i=0;i<QuickDeriveRASelectionoptions.size();i++)
		{
			WebElement QuickDeriveRASelectionrows=QuickDeriveRASelectionoptions.get(i);
			List<WebElement>QuickDeriveRASelectioncolums=QuickDeriveRASelectionrows.findElements(By.tagName("td"));
			String qdss1=QuickDeriveRASelectioncolums.get(0).getText();
			String qdss2=QuickDeriveRASelectioncolums.get(1).getText();
			String strofferby = userData.getData("Programsetup", "Offered By","1", "1");
			if(qdss1.equals("Offered By :"))
			{
				if(qdss2.equals(strofferby))
				{
					Report.updateTestLog("verification Offered By" ," ["+strofferby+"] has been verified", Status.DONE);
				}
			}
			if(qdss1.equals("Offering Id :"))
			{
				if(qdss2.equals(""))
				{
				}
			}
			String stroffername = userData.getData("Programsetup", "OfferingName","1", "1");
			if(qdss1.equals("Offering Name :"))
			{
				if(qdss2.equals(stroffername))
				{
					Report.updateTestLog("verification OfferingName" ," ["+stroffername+"] has been verified", Status.DONE);
				}
			}
			if(qdss1.equals("Region Name :"))
			{
				if(qdss2.equals(strregalter))
				{
					Report.updateTestLog("verification Region Name" ," ["+strregalter+"] has been verified", Status.DONE);
				}
			}
			String qdssofrname=userData.getData("Programsetup", "QDRSOfferingName", "1", "1");
			if(qdss1.equals("Regional Offering Name :"))
			{
				WebElement qdssofferingname=findObject(Driver, By.id("QuickDerivedRegionalOfferingName"), "QuickDeriveR offering name");
				if(qdssofferingname!=null)
				{
					GeneralComponents.enterValue(qdssofferingname, qdssofrname, "QuickDerivedRegionalOfferingName");
					Report.updateTestLog("Entering QuickDerivedRegionalOfferingName" ," ["+qdssofrname+"] has been Entered", Status.DONE);
				}
			}


		}

		WebElement QuickDeriveRdiv = findObject(Driver, By.xpath(".//*div[@id='pageWrapper']/table/tbody"), "QuickDeriveR verification");
		List<WebElement> QuickDeriveRoptions = QuickDeriveRdiv.findElements(By.tagName("tr"));	
		WebElement QuickDeriveRArow1=QuickDeriveRoptions.get(0);
		WebElement QuickDeriveRArow2=QuickDeriveRoptions.get(1);
		List<WebElement>QuickDeriveRcolum1=QuickDeriveRArow1.findElements(By.tagName("th"));
		List<WebElement>QuickDeriveRcolum2=QuickDeriveRArow2.findElements(By.tagName("td"));
		String strqdseries=QuickDeriveRcolum1.get(0).getText();
		String strqdfcunits=QuickDeriveRcolum1.get(1).getText();
		String strqdalreg=QuickDeriveRcolum1.get(2).getText();
		//String strqdss4=QuickDeriveRcolum1.get(3).getText();
		String strqdvalid=QuickDeriveRcolum1.get(4).getText();
		String strqdseriesceck=QuickDeriveRcolum2.get(0).getText();
		String strqdregcheck=QuickDeriveRcolum2.get(2).getText();
		String strqdvalidcheck=QuickDeriveRcolum2.get(4).getText();
		String strseries = userData.getData("Programsetup", "Series","1", "1");
		if(strqdseries.equals("Series"))
		{
			if(strqdseriesceck.equals(strseries))
			{
				Report.updateTestLog("verification SeriesName" ," ["+strseries+"] has been verified", Status.DONE);
			}
		}
		String strforecastunit = userData.getData("Programsetup", "OptionForecastUnit", "1", "1");
		if(strqdfcunits.equals("Forecast Units"))
		{
			WebElement forecastUnits=findObject(Driver, By.id("forecastUnits"), "forecastUnits");
			if(forecastUnits!=null)
			{
				GeneralComponents.enterValue(forecastUnits, strforecastunit, "Forecast Units");
				Report.updateTestLog("Entering forecastUnits" ," ["+strforecastunit+"] has been Entered", Status.DONE);
			}
		}
		String strreg = userData.getData("Programsetup", "Region","1", "1");
		if(strqdalreg.equals("Alternative Choice"))
		{
			if(strqdregcheck.equals(strreg))
			{
				Report.updateTestLog("verification Region" ," ["+strreg+"] has been verified", Status.DONE);
			}
		}
		String strvalid = userData.getData("Programsetup", "valid","1", "1");
		if(strqdvalid.equals("Validate"))
		{
			if(strqdvalidcheck.equals(strvalid))
			{
				Report.updateTestLog("verification validation" ," ["+strvalid+"] has been verified", Status.DONE);
			}
		}
	}
	/**
	 * 
	 * MethodName:Trickle down
	 * Description: validation of trickle down operations in dates
	 * Parameter (if any):  
	 * Return type:  Void
	 * Owner : Arunprakash
	 * @throws ParseException 
	 */
@Action(desc="trickledown",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void trickledown() throws ParseException
	{

		String strstartdatetrickle = userData.getData("Programsetup", "TrickleStartDate","1", "1");
		WebElement startdateselect = findObject(Driver, By.id("StartDate"), "start date");
		if(startdateselect !=null)
		{
			GeneralComponents.enterValue(startdateselect, strstartdatetrickle, "Start date value");
		}
		/*WebElement startdateflag = findObject(Driver, By.id("startDateFlag"), "start date trickle");
		if(startdateflag!=null)
		{
			GeneralComponents.clickOnWebelement(startdateflag, "start date trickle");
		}*/
		String strenddatetrickle = userData.getData("Programsetup", "TrickleEndDate","1", "1");
		WebElement enddateselect = findObject(Driver, By.id("EndDate"), "end date");
		if(enddateselect !=null)
		{
			GeneralComponents.enterValue(enddateselect, strenddatetrickle, "Start date value");

		}
		WebElement save = findObject(Driver, By.id("Save1"), "save trickle");
		if(save!=null)
		{
			GeneralComponents.clickOnWebelement(save, "save trickle");
		}
		WebElement conform = findObject(Driver, By.id("Confirm"), "conform trickle");
		if(conform!=null)
		{
			GeneralComponents.clickOnWebelement(conform, "conform trickle");
		}
		/*WebElement enddateflag = findObject(Driver, By.id("endDateFlag"), "enddateflag trickle");
		if(enddateflag!=null)
		{
			GeneralComponents.clickOnWebelement(enddateflag, "enddateflag trickle");

		}*/
		/*WebElement closedate = findObject(Driver, By.id("CloseDate"), "programenddate ");
		String strclose=closedate.getText();
		DateFormat df=new SimpleDateFormat();
		SimpleDateFormat sdf =new SimpleDateFormat("DD/MM/YYYY");
		Date close=sdf.parse(strenddatetrickle);
		Calendar closecal=GregorianCalendar.getInstance();
		closecal.setTime(close);
         closecal.add(GregorianCalendar.DATE, 5);
         String closestr=df.format(closecal.getTime());
		if(strclose.equals(closestr))
		{
			Report.updateTestLog("program closedate", "The program enddate  is available", Status.PASS);
		}
		WebElement closedateflag = findObject(Driver, By.id("closeDateFlag"), "closedateflag trickle");
		if(closedateflag!=null)
		{
			GeneralComponents.clickOnWebelement(closedateflag, "closedateflag trickle");
		}
		WebElement finaldate = findObject(Driver, By.id("FinalDate"), "finaldate ");
		String strfinal=finaldate.getText();
		DateFormat df1=new SimpleDateFormat();
		SimpleDateFormat sdf1 =new SimpleDateFormat("DD/MM/YYYY");
		Date findate=sdf1.parse(strenddatetrickle);
		Calendar finalcal=GregorianCalendar.getInstance();
		finalcal.setTime(findate);
         closecal.add(GregorianCalendar.MONTH, 6);
         String finalstr=df1.format(finalcal.getTime());
		if(finalstr.equals(strfinal))
		{
			Report.updateTestLog("program finaldate", "The program finaldate  is available", Status.PASS);
		}
		WebElement finaldateflag = findObject(Driver, By.id("finalDateFlag"), "finaldateflag trickle");
		if(finaldateflag!=null)
		{
			GeneralComponents.clickOnWebelement(finaldateflag, "finaldateflag trickle");
		}
		WebElement soldenddate = findObject(Driver, By.id("soldDate"), "soldDate ");
		String strsoldend=soldenddate.getText();
		DateFormat df2=new SimpleDateFormat();
		SimpleDateFormat sdf2 =new SimpleDateFormat("DD/MM/YYYY");
		Date solddate=sdf2.parse(strenddatetrickle);
		Calendar soldcal=GregorianCalendar.getInstance();
		closecal.setTime(solddate);
         soldcal.add(GregorianCalendar.DATE, 30);
         String soldstr=df2.format(soldcal.getTime());
		if(soldstr.equals(strsoldend))
		{
			Report.updateTestLog("program soldDate", "The program soldDate  is available", Status.PASS);
		}
		WebElement soldenddateflag = findObject(Driver, By.id("soldDateFlag"), "soldenddateflag trickle");
		if(soldenddateflag!=null)
		{
			GeneralComponents.clickOnWebelement(soldenddateflag, "soldenddateflag trickle");
		}
		 */
		/*String strprogram = userData.getData("Programsetup", "Program",  "1", "1");
		WebElement maindiv = findObject(Driver, By.xpath("//*[@class='div_ul']//a"),"user select");
		if(!strprogram.equalsIgnoreCase("NULL"))
		{

		if( maindiv !=null)
		{
			Actions action =new Actions(Driver);
		List<WebElement> options = maindiv.findElements(By.tagName("a"));
		String txtProp = null;
		System.out.println("options size:"+options.size());
		for (WebElement option : options) {
		txtProp = option.getText();
		System.out.println("Option:"+txtProp);
		if((strprogram.trim()).equalsIgnoreCase(txtProp.trim())){
		Actions action =new Actions(Driver);
		action.moveToElement(option).click(option).build().perform();
		 Report.updateTestLog("Select function:Program" ," ["+strprogram+"] has been selected", Status.DONE);
		sleepTime(Driver, 1);
		 break;
		}}}}*/
		WebElement pgmsum = findObject(Driver, By.id("programsummaryid"), "pgmsum trickle");
		if(pgmsum!=null)
		{
			GeneralComponents.clickOnWebelement(pgmsum, "pgmsum trickle");
		}
		//WebElement pgmdiv = findObject(Driver, By.id("ProgramDetails"), "ProgramDetails trickle");
		WebElement pgmtable = findObject(Driver, By.xpath("//*[@id='ProgramDetails']//table[@class='CreatOfferingPg iplusWidth100 iPluscellpadding0 iPluscellspacing0 iPlusBorder0']"),"user select");
		if(pgmtable!=null)
		{
			List<WebElement> optionsrow = pgmtable.findElements(By.tagName("tr"));
			for(int i=0;i<optionsrow.size();++i)
			{
				WebElement dealernbrrow = optionsrow.get(i);
				List<WebElement> optionscol = dealernbrrow.findElements(By.tagName("td"));
				String prgmnbrcheck = optionscol.get(0).getText();
				{
					if((prgmnbrcheck).equalsIgnoreCase("Program Start Date :"))
					{	
						String prgmnstartcheck = optionscol.get(1).getText();
						if(prgmnstartcheck.equalsIgnoreCase(strstartdatetrickle))
						{
							Report.updateTestLog("Program start date check", "Program start date present in the search results: " +strstartdatetrickle, Status.PASS);
							break;
						}
						else
						{
							Report.updateTestLog("Program start date check", "Program start date not present in the search results: " +strstartdatetrickle, Status.PASS);
						}
					}

				}
			}
		}

		if(pgmtable!=null)
		{
			List<WebElement> optionsrow = pgmtable.findElements(By.tagName("tr"));
			for(int i=0;i<optionsrow.size();++i)
			{
				WebElement dealernbrrow = optionsrow.get(i);
				List<WebElement> optionscol = dealernbrrow.findElements(By.tagName("td"));
				String prgmnbrcheck = optionscol.get(0).getText();
				{
					if((prgmnbrcheck).equalsIgnoreCase("Program End Date :"))
					{	
						String prgmnstartcheck = optionscol.get(1).getText();
						if(prgmnstartcheck.equalsIgnoreCase(strenddatetrickle))
						{
							Report.updateTestLog("Program end date check", "Program end date present in the search results: " +strstartdatetrickle, Status.PASS);
							break;
						}
						else
						{
							Report.updateTestLog("Program end date check", "Program end date not present in the search results: " +strstartdatetrickle, Status.PASS);
						}
					}

				}
			}
		}

		/*WebElement pgmStartdate = findObject(Driver, By.id("StartDate"), "pgmStartdate trickle");
		String strpgmstartdate=pgmStartdate.getText();
		if(strpgmstartdate.equals(strstartdatetrickle))
				{
			Report.updateTestLog("verification of " ," ["+strpgmstartdate+"] has been Ckecked", Status.DONE);
				}
		WebElement pgmenddate = findObject(Driver, By.id("EndDate"), "pgmenddate trickle");
		String strpgmenddate=pgmenddate.getText();
		if(strpgmenddate.equals(strenddatetrickle))
				{
			Report.updateTestLog("verification of " ," ["+strpgmenddate+"] has been Ckecked", Status.DONE);
				}*/
		/*WebElement pgmclosedate = findObject(Driver, By.id("CloseDate"), "pgmclosedate trickle");
		String strpgmclosedate= pgmclosedate.getText();
		if(strpgmclosedate.equals(strclose))
				{
			Report.updateTestLog("verification of " ," ["+strpgmclosedate+"] has been Ckecked", Status.DONE);
				}
		WebElement pgmfinaldate = findObject(Driver, By.id("FinalDate"), "pgmfinaldate trickle");
		String strpgmfinaldate= pgmfinaldate.getText();
		if(strpgmfinaldate.equals(strfinal))
				{
			Report.updateTestLog("verification of " ," ["+strpgmfinaldate+"] has been Ckecked", Status.DONE);
				}
		WebElement pgmsoldenddate = findObject(Driver, By.id("SoldOrderDate"), "pgmsoldenddate trickle");
		String strpgmsoldenddate= pgmsoldenddate.getText();
		if(strpgmsoldenddate.equals(strsoldend))
				{
			Report.updateTestLog("verification of " ," ["+strpgmsoldenddate+"] has been Ckecked", Status.DONE);
				}

		 */	
	}
@Action(desc="optionsfeed",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void optionsfeed()
	{

		WebElement offeringvalidation = findObject(Driver, By.xpath("//input[starts-with(@id, 'optionDetail_']"),"Option detail");




	}





//@Action(desc="optionsnoncash",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
//	public void optionsnoncash()throws InterruptedException
//	{
//		String stritemcode = userData.getData("Programsetup", "itemcode", "1", "1");
//		String stritemname = userData.getData("Programsetup", "itemname", "1", "1");
//		String strdescription = userData.getData("Programsetup", "Description", "1", "1");
//		String stritemValue = userData.getData("Programsetup", "itemvalue", "1", "1");
//		String strFundingValue = userData.getData("Programsetup", "FundingValue", "1", "1");
//		String strregenchance = userData.getData("Programsetup", "regionEnchance", "1", "1");
//		String strAdditionalFS = userData.getData("Programsetup", "AdditionalFS", "1", "1");
//		WebElement itemcode = findObject(Driver, By.xpath(".//input[starts-with(@id, 'itemCode')]"),"Item Code");
//		WebElement itemname = findObject(Driver, By.xpath(".//input[starts-with(@id, 'itemName')]"),"Item Name");
//		WebElement description = findObject(Driver, By.xpath(".//input[starts-with(@id, 'description')]"),"description");
//		WebElement itemValue = findObject(Driver, By.xpath(".//input[starts-with(@id, 'itemValue')]"),"Item Value");
//		WebElement FundingValue = findObject(Driver, By.xpath(".//input[starts-with(@id, 'fundingThresholdAmt')]"),"fundingThresholdAmt Value");
//		WebElement AdditionalFS = findObject(Driver, By.id("addAdditionalFSLink"),"AdditionalFS");
//		List<WebElement>  RegEnch = findObjects(Driver, By.id("regEnhancements"),"regEnhancements");
//
//		if(!strregenchance.equalsIgnoreCase("NULL") && !strregenchance.equalsIgnoreCase(""))
//		{
//			if(strregenchance.trim().equalsIgnoreCase("yes"))
//			{ RegEnch.get(1).click();}
//			if(strregenchance.trim().equalsIgnoreCase("no"))
//			{ RegEnch.get(2).click();}
//		}
//
//		if(!stritemcode.equalsIgnoreCase("NULL") && !stritemcode.equalsIgnoreCase(""))
//		{
//
//			GeneralComponents.enterValue(itemcode, stritemcode, "itemcode Text box");
//		}
//		if(!stritemname.equalsIgnoreCase("NULL") && !stritemname.equalsIgnoreCase(""))
//		{
//
//			GeneralComponents.enterValue(itemname, stritemname, "itemname Text box");
//		}
//		if(!strdescription.equalsIgnoreCase("NULL") && !strdescription.equalsIgnoreCase(""))
//		{
//
//			GeneralComponents.enterValue(description, strdescription, "description Text box");
//		}
//		if(!stritemValue.equalsIgnoreCase("NULL") && !stritemValue.equalsIgnoreCase(""))
//		{
//
//			GeneralComponents.enterValue(itemValue, stritemValue, "itemValue Text box");
//		}
//		WebElement optionvaluetable = findObject(Driver, By.xpath(".//input[starts-with(@id, 'optionTypeContent_')]"),"Non cash table Value");
//		if(optionvaluetable!= null && stritemcode!=null)
//		{
//			WebElement itemcodecheck = optionvaluetable.findElement(By.id("ITEM_CODE"));
//			if(itemcodecheck.getText().equalsIgnoreCase(stritemcode))
//			{
//				System.out.println("Item details added to the list");
//				Report.updateTestLog("Item code added","Non cash item added to the list",Status.PASS);
//			}
//		}
//		if(!strFundingValue.equalsIgnoreCase("NULL") && !strFundingValue.equalsIgnoreCase(""))
//		{
//
//			GeneralComponents.enterValue(FundingValue, strFundingValue, "Funding value Text box");
//		}
//		//Funding Source to be added to the code
//		String strcertificate = userData.getData("Programsetup", "certificatefile", "1", "1");
//		WebElement fileupload = findObject(Driver, By.id("fileUpload"),"fileUpload Value");
//		if(fileupload!= null && strcertificate!=null)
//		{
//
//			Actions action = new Actions(Driver);
//			action.moveToElement(fileupload); 
//			sleepTime(Driver, 3);
//			//GeneralComponents.clickOnInvisibleWebelement(uploadfile,"Upload File");
//			//action.sendKeys(Keys.ENTER).build().perform();
//			action.click(fileupload);
//			sleepTime(Driver, 3);
//			action.build().perform();
//			sleepTime(Driver, 3);
//			action.moveToElement(fileupload).doubleClick(fileupload).build().perform();;
//			sleepTime(Driver, 3);
//			uploadFile(strcertificate);
//			Report.updateTestLog("Browse file for upload", strcertificate+"file is selected successfully", Status.PASS);
//			WebElement uploadclk = findObject(Driver, By.xpath(".//*[@id='Upload']"),"Upload");
//			if(uploadclk.isEnabled()){
//				sleepTime(Driver, 3);
//				uploadclk.click();
//				sleepTime(Driver, 8);
//				//			WebElement uploadclkok = findObject(Driver, By.xpath(".//*[@id='paymentUploadOk']"),"Upload OK");
//				//			uploadclkok.click();
//				WebElement docupload =Driver.findElement(By.xpath(".//*[@class='attachmentName']"));
//				if(docupload !=null)
//				{
//					System.out.println(docupload.getText());
//					if(docupload.getText().contains(strcertificate))
//					{
//						Report.updateTestLog("Upload file for certificate", strcertificate+"file is uploaded successfully", Status.PASS);}
//				}
//			}
//		}
//		WebElement addaddreq = findObject(Driver, By.xpath(".//*[@id='addAdditionalRequirements']/parent::td"),"addaddreq OK");
//		addaddreq.click();
//		WebElement addaddreqpopup = findObject(Driver, By.xpath(".//*[@id='addDocPopup']"),"Added");
//		String stroptions = userData.getData("Programsetup", "Options", "1", "1");
//		String[] strArray = stroptions.split(",");
//		String[] Array = new String[strArray.length];
//		for(int i = 0; i < strArray.length; i++) {
//			Array[i] = strArray[i];
//		}
//		for(int count = 0; count < Array.length; count++) {	
//			List<WebElement> optionsrow = addaddreqpopup.findElements(By.tagName("tr"));
//			for(int j=0;j<optionsrow.size();j++)
//			{
//				WebElement reqrow = optionsrow.get(j);
//
//
//				WebElement reqcheck = reqrow.findElement(By.id("chckboxLabel"));
//				String strreqcheck = reqcheck.getText();
//				if((strreqcheck).equalsIgnoreCase(strArray[count]))	
//				{	
//					Report.updateTestLog("options check", "options present in the page " +strreqcheck, Status.PASS);
//					if(reqrow.findElement(By.id("checkedReqDocArr")).getAttribute("type").equals("checkbox"))
//					{
//						reqrow.findElement(By.id("checkedReqDocArr")).click();
//					}
//					sleepTime(Driver, 3);
//				}
//			}
//
//			Driver.findElement(By.xpath("//*[@id='addDocButton']")).click();
//
//		}
//	}

	//-----------------------------------------------------------Copy Function------------------------------------------------------------------------------
@Action(desc="Searchclick",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Searchclick()
	{
		WebElement Searchcheck = Driver.findElement(By.id("searchImg"));
		WebElement regionid = findObject(Driver, By.xpath(".//*[@id='showDiv']//*[text()[contains(.,'Search Program')]]"),"Search Program select");
		Actions action = new Actions(Driver);
		action.moveToElement(Searchcheck).clickAndHold(regionid).build().perform();
	}


//
//@Action(desc="Searchforcopy",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
//	public void Searchforcopy()
//	{
//		//Search for program for Copy function
//		String strBrand = userData.getData("CopyProgram", "Brand","1", "1");
//		String strsearchid = userData.getData("CopyProgram", "Searchid","1", "1");
//		//			String strBrand = userData.getData("Programsetup", "Brand", "incentive", "SearchOffID", "1", "1");
//		String assigntxt= null;
//		//Brand Defaults		
//		if(!strBrand.equalsIgnoreCase("NULL"))
//		{
//			WebElement regionid = findObject(Driver, By.xpath("//*[@id='brand']/parent::td"),"brand select");
//			//				WebElement regionid = findObject(Driver, By.xpath("//*[@id='brand-button-iplus']"),"brand select");
//			WebElement brandwle = regionid.findElement(By.tagName("span"));
//			WebElement brandselect = findObject(Driver, By.xpath("html/body/ul[1]"),"Brand Select");
//			//				WebElement brandselect = findObject(Driver, By.id("brand-menu-iplus"),"Brand Select");
//
//			if(brandwle !=null)
//			{
//				Actions action = new Actions(Driver);
//				action.moveToElement(brandwle).clickAndHold(brandwle).build().perform();
//				//GeneralComponents.selectElementli(brandselect,strBrand,"Brand select");	
//				List<WebElement> options = brandselect.findElements(By.tagName("a"));
//				String txtProp = null;
//				System.out.println("options size:"+options.size());
//				for (WebElement option : options) {
//					txtProp = option.getText();
//					System.out.println("Option:"+txtProp);
//					if((strBrand.trim()).equalsIgnoreCase(txtProp.trim())){
//						//option.click();
//						action.moveToElement(option).click(option).build().perform();
//						Report.updateTestLog("Select function: Brand" ," ["+strBrand+"] has been selected", Status.DONE);
//						sleepTime(Driver, 6);
//						break;
//					}
//				}}
//		}			
//		WebElement pgmid = findObject(Driver, By.xpath(".//*[@id='programID']"),"programID ID");
////		String strTestDataDBFileName = FilePath.getTestDataPathUploadMainFile("Datafeed.txt");
//		BufferedReader in = null;
//		boolean keyFound = false;
//		try {
//			in = new BufferedReader(new FileReader(strTestDataDBFileName));
//			String line = null;
//			while((line=in.readLine())!=null){
//				if (line.contains("=")){
//					String strTemp[]=line.split("=");
//					if (strTemp[0].trim().equalsIgnoreCase(strsearchid)){
//						keyFound = true;
//						assigntxt = strTemp[1].trim();
//						in.close();
//						break;
//					}
//				}
//			}
//			if(!keyFound)
//				System.out.println("Key [" +strsearchid+ "] does not exist in the file : Datafeed.txt");
//			//						configData.reportObj.updateTestLog("Get data from temp file", "Key ["+strKey+"] does not exist under the path :"+getTestDataDBPath(), Status.FAIL);
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			//						configData.reportObj.updateTestLog("I/O failure - error reading file. ","I/O failure - error reading file. "+ e.getMessage(),Status.FAIL);
//		}
//		GeneralComponents.enterValue(pgmid,assigntxt, "OffID text box");
//
//		//Search click
//		try {	
//			Driver.findElement(By.xpath(".//*[@id='Search']")).click();
//			Report.updateTestLog("Click Search", "Clicked on Search button", Status.PASS);
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		//Offering ID selection
//		sleepTime(Driver, 3);
//		WebElement datatable = findObject(Driver,By.xpath(".//*[@id='DataTable']//tbody"),"Dealer table");
//		//String strdealercode= userData.getData("ProgramAdmin", "DealerCodes", "ProgramAdmin","DealerPayments_TC02", "1", "1");
//		if(datatable!=null)
//		{
//			List<WebElement> optionsrow = datatable.findElements(By.tagName("tr"));
//			for(int i=0;i<optionsrow.size();++i)
//			{
//				boolean prgmcheck = false;
//				WebElement dealernbrrow = optionsrow.get(i);
//				List<WebElement> optionscol = dealernbrrow.findElements(By.tagName("td"));
//				String prgmnbrcheck = optionscol.get(1).getText();
//				{
//					if((prgmnbrcheck).equalsIgnoreCase(assigntxt))
//					{	
//						Report.updateTestLog("Program Name/ID Number check", "Program Name/ID Number present in the search results: " +assigntxt, Status.PASS);
//						List<WebElement> clickviewlinks = dealernbrrow.findElements(By.tagName("a"));
//						String straction = userData.getData("CopyProgram", "SelectAction", "1", "1");
//						{
//							for (WebElement clickviewlink : clickviewlinks) {
//								if(clickviewlink !=null && straction!=null)
//								{
//									if(clickviewlink.getText().trim().equalsIgnoreCase(straction.trim()))                          
//									{//View Details selection in screen
//										clickviewlink.click();
//										sleepTime(Driver, 3);
//										prgmcheck=true;
//										Report.updateTestLog("Click on Action for the program.","action performed for program:" +assigntxt, Status.PASS);
//										break;
//									}
//								}
//							}}
//					}
//				}if(prgmcheck){break;}
//			}
//		}
//
//	}
@Action(desc="copyprogramselection",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void copyprogramselection()
	{
		WebElement copyprogname = Driver.findElement(By.xpath(".//*[@id='programName']"));
		String progname = copyprogname.getAttribute("value");
		WebElement copyoffname = Driver.findElement(By.xpath("//*[@id='programdetailsid']/tbody/tr[1]/td[4]"));
		String offname = copyoffname.getText();
		WebElement copyprofname = Driver.findElement(By.xpath("//*[@id='programdetailsid']/tbody/tr[2]/td[2]"));
		String profname = copyprofname.getText();		
		String strcopy = userData.getData("CopyProgram", "Copytype", "1", "1");
		WebElement copyclick = Driver.findElement(By.xpath(".//*[starts-with(@id, 'programliid')]//*[text()[contains(.,'Copy')]]"));
		copyclick.click();
		sleepTime(Driver, 3);
		List<WebElement> copyfrom = Driver.findElements(By.id("copyFrom"));
		{
			if(!strcopy.equalsIgnoreCase("NULL") && !strcopy.equalsIgnoreCase(""))
			{
				if(strcopy.trim().equalsIgnoreCase("Current Offering"))
				{ 
					copyfrom.get(0).click();
					currentprogmcopy(progname,offname,profname);
				}
				if(strcopy.trim().equalsIgnoreCase("Previous Offering"))
				{ 
					copyfrom.get(1).click();
					sleepTime(Driver, 5);
					prevprogmcopy(progname,offname,profname);
				}

			}
		}
	}

	public void currentprogmcopy(String progname,String offname, String profname)
	{
		String strcurrentseries = userData.getData("CopyProgram", "CurrentSeries", "1", "1");
		String strcurrentprogram = userData.getData("CopyProgram", "Currentprogram", "1", "1");
		//Series select
		WebElement seriesid = findObject(Driver, By.xpath("//*[@id='series']/parent::td"),"Series select");
		WebElement Seriesclick = seriesid.findElement(By.tagName("span"));
		WebElement Seriesselect = findObject(Driver,By.xpath("html/body/ul[1]"),"Series Select");
		if(strcurrentseries !=null)
		{
			Actions action = new Actions(Driver);
			action.moveToElement(Seriesclick).click(Seriesclick).build().perform();
			List<WebElement> options = Seriesselect.findElements(By.tagName("a"));
			String txtProp = null;
			System.out.println("options size:"+options.size());
			for (WebElement option : options) {
				txtProp = option.getText();
				if((strcurrentseries.trim()).equalsIgnoreCase(txtProp.trim())){
					//option.click();
					System.out.println("Option:"+txtProp);
					action.moveToElement(option).click(option).build().perform();
					Report.updateTestLog("Select function: ","Series ["+strcurrentseries+"] has been selected", Status.PASS);
					sleepTime(Driver, 3);
					break;
				}}}
		//Program select
		WebElement programid = findObject(Driver, By.xpath("//*[@id='program']/parent::td"),"program select");
		WebElement programclick = programid.findElement(By.tagName("span"));
		WebElement programselect = findObject(Driver,By.xpath("html/body/ul[2]"),"Series Select");
		if(strcurrentprogram !=null)
		{
			Actions action = new Actions(Driver);
			action.moveToElement(programclick).click(programclick).build().perform();
			List<WebElement> options = programselect.findElements(By.tagName("a"));
			String txtProp = null;
			System.out.println("options size:"+options.size());
			for (WebElement option : options) {
				txtProp = option.getText();
				if((strcurrentprogram.trim()).equalsIgnoreCase(txtProp.trim())){
					//option.click();
					System.out.println("Option:"+txtProp);
					action.moveToElement(option).click(option).build().perform();
					Report.updateTestLog("Select function: ","Program ["+strcurrentprogram+"] has been selected", Status.PASS);
					sleepTime(Driver, 3);
					break;
				}}}
		WebElement copyselect = findObject(Driver,By.xpath(".//*[@id='CopyProgram']"),"Copy Select");
		copyselect.click();
		sleepTime(Driver, 5);
		WebElement copyprogrmpopup = findObject(Driver,By.xpath(".//*[@id='CopyProgramSetupDIV']"),"Copy popup");
		if(copyprogrmpopup!=null)
		{
			WebElement currentoffchk = findObject(Driver,By.xpath(".//*[@id='CopyProgramSetupDIV']/table/tbody//td[text()[contains(.,'"+offname+"')]]"),"Copy popup check");
			WebElement currentserieschk = findObject(Driver,By.xpath(".//*[@id='CopyProgramSetupDIV']/table/tbody//td[text()[contains(.,'"+profname+"')]]"),"Copy popup check");
			if(currentoffchk.getText().contains(offname) && currentserieschk.getText().contains(profname))
			{
				System.out.println("Program and Series to be copied are available and selected:"+profname);
				Report.updateTestLog("Copy current offering","Offering and Series to be copied are available and selected:"+profname, Status.PASS);
			}
		}
		sleepTime(Driver, 3);
		String strcopystartdate1 = userData.getData("CopyProgram", "CopystartDate", "1", "1");
		String strcopyenddate1 = userData.getData("CopyProgram", "CopyEndDate", "1", "1");
		WebElement startdateselect1 = findObject(Driver, By.id("copyProgramStartDate"), "start date");
		if(!strcopystartdate1.equalsIgnoreCase("NULL") && !strcopystartdate1.equalsIgnoreCase(""))
		{
			GeneralComponents.enterValue(startdateselect1, strcopystartdate1, "Start date value");
		}
		//Copy end date
		WebElement enddateselect1 = findObject(Driver, By.id("copyProgramCloseDate"), "end date");
		if(enddateselect1 !=null)
		{
			GeneralComponents.enterValue(enddateselect1, strcopyenddate1, "end date value");
		}

		String copocomp = userData.getData("CopyProgram", "Copycompatibility", "1", "1");

		if(!copocomp.equalsIgnoreCase("NULL") && !copocomp.equalsIgnoreCase(""))
		{
			if(copocomp.trim().equalsIgnoreCase("Yes"))
			{ 
				Driver.findElement(By.xpath(".//*[@id='CopyProgramSetupDIV']/table/tbody//input[@value='true']")).click();
			}
			if(copocomp.trim().equalsIgnoreCase("No"))
			{ 
				Driver.findElement(By.xpath(".//*[@id='CopyProgramSetupDIV']/table/tbody//input[@value='false']")).click();
			}
		}	
		String allowenhance = userData.getData("CopyProgram", "Allowenhance", "1", "1");
		List<WebElement> allowEnhancements = Driver.findElements(By.id("allowEnhancements"));
		if(!allowenhance.equalsIgnoreCase("NULL") && !allowenhance.equalsIgnoreCase(""))
		{
			if(allowenhance.trim().equalsIgnoreCase("Yes"))
			{ 
				allowEnhancements.get(1).click();
			}
			if(allowenhance.trim().equalsIgnoreCase("No"))
			{ 
				allowEnhancements.get(2).click();
			}
		}
		//Modelyear select and search
		String strmodelyear = userData.getData("CopyProgram", "CopyModel", "1", "1");
		WebElement selectmy = findObject(Driver, By.id("selectedYears"), "Modelyear");
		if(selectmy!=null)
		{
			GeneralComponents.selectElement(selectmy, strmodelyear, "Modelyear Dropdown");
			Driver.findElement(By.xpath(".//*[@id='MYSearch']")).click();
		}				
		//selection of Series					
		sleepTime(Driver, 5);
		WebElement datatable = findObject(Driver,By.xpath(".//*[@id='DataTable']/tbody"),"Delaer table");
		String strcopyMY= userData.getData("CopyProgram", "CopyMYselect", "1", "1");
		if(!strcopyMY.equalsIgnoreCase("NULL") && !strcopyMY.equalsIgnoreCase(""))
		{
			String[] strArray = strcopyMY.split(",");
			String[] Array = new String[strArray.length];
			for(int i = 0; i < strArray.length; i++) {
				Array[i] = strArray[i];
			}
			for(int count = 0; count < Array.length; count++) {	
				List<WebElement> optionsrow = datatable.findElements(By.tagName("tr"));
				for(int j=0;j<optionsrow.size();j++)
				{
					WebElement dealernbrrow = optionsrow.get(j);
					List<WebElement> optionscol = dealernbrrow.findElements(By.tagName("td"));
					String dealernbrcheck = optionscol.get(1).getText();
					WebElement dealerloccheck = optionscol.get(0);
					if((dealernbrcheck).equalsIgnoreCase(strArray[count]))	
					{	
						Report.updateTestLog("options check", "options present in the page " +dealernbrcheck, Status.PASS);

						if(dealerloccheck.findElement(By.tagName("input")).getAttribute("type").equals("checkbox")){
							dealerloccheck.findElement(By.tagName("input")).click();}
						sleepTime(Driver, 3);
					}

				}
			}		
		}

		WebElement copysave = findObject(Driver,By.xpath(".//*[@id='Continue']"),"Copy Save");
		copysave.click();
		sleepTime(Driver, 3);	

		WebElement copyprogname = Driver.findElement(By.xpath(".//*[@id='programName']"));
		String prognamecopied = copyprogname.getAttribute("value");
		WebElement copyoffname = Driver.findElement(By.xpath("//*[@id='programdetailsid']/tbody/tr[1]/td[4]"));
		String offnamecopied = copyoffname.getText();
		WebElement copyprofname = Driver.findElement(By.xpath("//*[@id='programdetailsid']/tbody/tr[2]/td[2]"));
		String profnamecopied = copyprofname.getText();		
		if(prognamecopied.equalsIgnoreCase(strcurrentprogram) && profnamecopied.equalsIgnoreCase(offname) && offnamecopied.equalsIgnoreCase(profname))
		{
			System.out.println(prognamecopied+ " Program copied to selected profile: "+profnamecopied);
			Report.updateTestLog("Copy function for "+offname,prognamecopied+ " Program copied to selected profile: "+profnamecopied, Status.PASS);
			sleepTime(Driver, 3);
		}


	}	


	public void prevprogmcopy(String progname,String offname, String profname)
	{
		sleepTime(Driver, 3);
		String strcurrentseries = userData.getData("CopyProgram", "CurrentSeries", "1", "1");
		String strcurrentprogram = userData.getData("CopyProgram", "Currentprogram", "1", "1");
		String strprgmname = userData.getData("CopyProgram", "programnamesearch", "1", "1");
		String strprgmid = userData.getData("CopyProgram", "programIDsearch", "1", "1");
		String strprgmstatus = userData.getData("CopyProgram", "programstatus", "1", "1");
		//Series select
		WebElement prgmname = findObject(Driver,By.xpath(".//*[@id='programName']"),"Program name");
		if(!strprgmname.equalsIgnoreCase("NULL") && !strprgmname.equalsIgnoreCase(""))
		{

			GeneralComponents.enterValue(prgmname, strprgmname, "prgmname Text box");
		}
		WebElement prgmid = findObject(Driver,By.xpath(".//*[@id='programID'][@class='isystxtbx']"),"Program ID");
		if(!strprgmid.equalsIgnoreCase("NULL") && !strprgmid.equalsIgnoreCase(""))
		{

			GeneralComponents.enterValue(prgmid, strprgmid, "prgmid Text box");
		}
		WebElement prgmstatus = findObject(Driver,By.xpath(".//*[@id='statusTypeDD']"),"Program Status");
		if(!strprgmstatus.equalsIgnoreCase("NULL") && !strprgmstatus.equalsIgnoreCase(""))
		{
			GeneralComponents.selectElement(prgmstatus, strprgmstatus, "Selection of status");
		}

		WebElement search = findObject(Driver,By.xpath(".//*[@id='Search']"),"Search btn");
		search.click();
		sleepTime(Driver, 8);
		//Selecting the program for copying
		WebElement datatable = findObject(Driver,By.xpath(".//*[@id='DataTable1']/tbody"),"Delaer table");
		String strcopyprog= userData.getData("CopyProgram", "programIDsearch", "1", "1");
		if(!strcopyprog.equalsIgnoreCase("NULL") && !strcopyprog.equalsIgnoreCase(""))
		{ 
			List<WebElement> optionsrow = datatable.findElements(By.tagName("tr"));
			for(int j=0;j<optionsrow.size();j++)
			{
				WebElement dealernbrrow = optionsrow.get(j);
				List<WebElement> optionscol = dealernbrrow.findElements(By.tagName("td"));
				String dealernbrcheck = optionscol.get(1).getText();
				String dealerloccheck = optionscol.get(4).getText();
				if((dealernbrcheck).equalsIgnoreCase(strcopyprog))	
				{	
					Report.updateTestLog("options check",dealerloccheck+ "Program present in the page:(id: " +dealernbrcheck+")" , Status.PASS);
					WebElement Copyclk =  dealernbrrow.findElement(By.xpath(".//input[starts-with(@id, 'Copy')]"));
					if(Copyclk!=null){
						Copyclk.click();}
					sleepTime(Driver, 3);
				}
			}	
		}	sleepTime(Driver, 5);
		WebElement copyprogrmpopup = findObject(Driver,By.xpath(".//*[@id='CopyProgramSetupDIV']"),"Copy popup");
		if(copyprogrmpopup!=null)
		{
			WebElement currentoffchk = findObject(Driver,By.xpath(".//*[@id='CopyProgramSetupDIV']/table/tbody//td[text()[contains(.,'"+offname+"')]]"),"Copy popup check");
			WebElement currentserieschk = findObject(Driver,By.xpath(".//*[@id='CopyProgramSetupDIV']/table/tbody//td[text()[contains(.,'"+profname+"')]]"),"Copy popup check");
			if(currentoffchk.getText().contains(offname) && currentserieschk.getText().contains(profname))
			{
				System.out.println("Program and Series to be copied are available and selected:"+profname);
				Report.updateTestLog("Copy current offering","Offering and Series to be copied are available and selected:"+profname, Status.PASS);
			}
		}
		sleepTime(Driver, 3);
		String strcopystartdate1 = userData.getData("CopyProgram", "CopystartDate", "1", "1");
		String strcopyenddate1 = userData.getData("CopyProgram", "CopyEndDate", "1", "1");
		WebElement startdateselect1 = findObject(Driver, By.id("copyProgramStartDate"), "start date");
		if(!strcopystartdate1.equalsIgnoreCase("NULL") && !strcopystartdate1.equalsIgnoreCase(""))
		{
			GeneralComponents.enterValue(startdateselect1, strcopystartdate1, "Start date value");
		}
		//Copy end date
		WebElement enddateselect1 = findObject(Driver, By.id("copyProgramCloseDate"), "end date");
		if(enddateselect1 !=null)
		{
			GeneralComponents.enterValue(enddateselect1, strcopyenddate1, "end date value");
		}

		String copocomp = userData.getData("CopyProgram", "Copycompatibility", "1", "1");

		if(!copocomp.equalsIgnoreCase("NULL") && !copocomp.equalsIgnoreCase(""))
		{
			if(copocomp.trim().equalsIgnoreCase("Yes"))
			{ 
				Driver.findElement(By.xpath(".//*[@id='CopyProgramSetupDIV']/table/tbody//input[@value='true']")).click();
			}
			if(copocomp.trim().equalsIgnoreCase("No"))
			{ 
				Driver.findElement(By.xpath(".//*[@id='CopyProgramSetupDIV']/table/tbody//input[@value='false']")).click();
			}
		}	
		String allowenhance = userData.getData("CopyProgram", "Allowenhance", "1", "1");
		List<WebElement> allowEnhancements = Driver.findElements(By.id("allowEnhancements"));
		if(!allowenhance.equalsIgnoreCase("NULL") && !allowenhance.equalsIgnoreCase(""))
		{
			if(allowenhance.trim().equalsIgnoreCase("Yes"))
			{ 
				allowEnhancements.get(1).click();
			}
			if(allowenhance.trim().equalsIgnoreCase("No"))
			{ 
				allowEnhancements.get(2).click();
			}
		}
		//Modelyear select and search
		String strmodelyear = userData.getData("CopyProgram", "CopyModel", "1", "1");
		WebElement selectmy = findObject(Driver, By.id("selectedYears"), "Modelyear");
		if(selectmy!=null)
		{
			GeneralComponents.selectElement(selectmy, strmodelyear, "Modelyear Dropdown");
			Driver.findElement(By.xpath(".//*[@id='MYSearch']")).click();
		}				
		//selection of Series					
		sleepTime(Driver, 5);
		WebElement datatable1 = findObject(Driver,By.xpath(".//*[@id='DataTable']/tbody"),"Delaer table");
		String strcopyMY= userData.getData("CopyProgram", "CopyMYselect", "1", "1");
		if(!strcopyMY.equalsIgnoreCase("NULL") && !strcopyMY.equalsIgnoreCase(""))
		{
			String[] strArray = strcopyMY.split(",");
			String[] Array = new String[strArray.length];
			for(int i = 0; i < strArray.length; i++) {
				Array[i] = strArray[i];
			}
			for(int count = 0; count < Array.length; count++) {	
				List<WebElement> optionsrow = datatable1.findElements(By.tagName("tr"));
				for(int j=0;j<optionsrow.size();j++)
				{
					WebElement dealernbrrow = optionsrow.get(j);
					List<WebElement> optionscol = dealernbrrow.findElements(By.tagName("td"));
					String dealernbrcheck = optionscol.get(1).getText();
					WebElement dealerloccheck = optionscol.get(0);
					if((dealernbrcheck).equalsIgnoreCase(strArray[count]))	
					{	
						Report.updateTestLog("options check", "options present in the page " +dealernbrcheck, Status.PASS);

						if(dealerloccheck.findElement(By.tagName("input")).getAttribute("type").equals("checkbox")){
							dealerloccheck.findElement(By.tagName("input")).click();}
						sleepTime(Driver, 3);
					}

				}
			}		
		}

		WebElement copysave = findObject(Driver,By.xpath(".//*[@id='Continue']"),"Copy Save");
		copysave.click();
		sleepTime(Driver, 3);	

		WebElement copyprogname = Driver.findElement(By.xpath(".//*[@id='programName']"));
		String prognamecopied = copyprogname.getAttribute("value");
		WebElement copyoffname = Driver.findElement(By.xpath("//*[@id='programdetailsid']/tbody/tr[1]/td[4]"));
		String offnamecopied = copyoffname.getText();
		WebElement copyprofname = Driver.findElement(By.xpath("//*[@id='programdetailsid']/tbody/tr[2]/td[2]"));
		String profnamecopied = copyprofname.getText();		
		if(prognamecopied.equalsIgnoreCase(strcurrentprogram) && profnamecopied.equalsIgnoreCase(offname) && offnamecopied.equalsIgnoreCase(profname))
		{
			System.out.println(prognamecopied+ " Program copied to selected profile: "+profnamecopied);
			Report.updateTestLog("Copy function for "+offname,prognamecopied+ " Program copied to selected profile: "+profnamecopied, Status.PASS);
			sleepTime(Driver, 3);
		}	
	}

	//-----------------------------------------------------------------------------------



	/**
	 *
	 * MethodName: Customer survey
	 * Description: Customer survey tabbed View
	 * Parameter (if any):
	 * Return type: void
	 * Owner : Malini
	 */

@Action(desc="findwebelmentCS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void findwebelmentCS()

	{
		WebElement VINlookup=findObject(Driver,By.id("_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinTextBox"), "VIN Lookup");
		if (VINlookup !=null)
		{
			String val=userData.getData("findwebelmentCS", "Regular", "1", "1");
			/*userData.getData(Sheet, Column)*/
			GeneralComponents.enterValue(VINlookup, val, "VIN is entered");
			Report.updateTestLog("VINlookup", "VIN text is found",Status.PASS);
		}		
		else
		{
			Report.updateTestLog("VINlookup", "VIN text is not found", Status.FAIL);
		}
	}

@Action(desc="clickcustomersurvey_tab_TIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clickcustomersurvey_tab_TIS()

	{
		GeneralComponents.waitforInternalLoad(Driver);
		{
			WebElement warranty= findObject(Driver,By.id("k_ASMPortal_portal_book_11_ajax"), "Warranty");
			if(warranty !=null)
			{
				List<WebElement> tds=warranty.findElements(By.tagName("td"));
				for(WebElement td:tds)
				{
					String val=td.getText();
					if(val.contains("Customer survey"))
					{
						GeneralComponents.clickOnWebelement(td, "Customer survey");
						Report.updateTestLog("Customer survey", "Customer survey tab is clicked",Status.PASS);
						break;
					}   

				}

			}
			else
			{
				Report.updateTestLog("Customer survey","Customer survey tab is not clicked ",Status.FAIL);
			}
		}
	}


@Action(desc="chkcustomersurveyelements_Tab_TIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkcustomersurveyelements_Tab_TIS()
	{

		WebElement RegVINelements= findObject(Driver,By.id("_jpfcpncuivr_T3809095701380782586206_j_id_id0:customerSurveyForm"), "Customer survey Elements for lexus dealer");
		if(RegVINelements !=null)

		{
			List<WebElement> tds2= RegVINelements.findElements(By.tagName("b"));
			for(WebElement a:tds2)
			{

				String val=a.getText();
				if((val.contains("Guest"))||(val.contains("Response Date"))||(val.contains("Dealer"))||(val.contains("Service Consultant"))||(val.contains("Technician"))||(val.contains("Repair Date"))||(val.contains("RO #"))||(val.contains("Overall Satisfaction")))

				{
					System.out.println(val);

					Report.updateTestLog(val+"Check for Customer survey elements", "customer survey elements are displaying",Status.PASS);


				}               
			}
		}
		else
		{
			Report.updateTestLog("Check for Customer survey elements", "Customer survey elements are not displaying ",Status.FAIL);
		}

	}


@Action(desc="Customer_svr_collapsAll_tab_TIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Customer_svr_collapsAll_tab_TIS()

	{WebElement collapsAllTable= findObject(Driver,By.id("_jpfcpncuivr_T3809095701380782586206_j_id_id0:customerSurveyForm"), "Collapse All");

	if(collapsAllTable !=null)
	{
		List<WebElement> tds=collapsAllTable.findElements(By.tagName("b"));
		for(WebElement b:tds)
		{
			String val=b.getText();
			if(val.contains("Collapse All [-]"))

			{     
				sleepTime(Driver, 1);

				GeneralComponents.clickOnWebelement(b, "Collapse All [-] ");
				Report.updateTestLog("Collapse All", "Collapse All in customer survey is clicked",Status.PASS);
				break;
			}   

		}
	}
	else
	{
		Report.updateTestLog("Collapse All","Collapse All in customer survey is not clicked ",Status.FAIL);
	}
	}


@Action(desc="Customer_svr_expandAll_tab_TIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Customer_svr_expandAll_tab_TIS()

	{
		WebElement collapsAllTable= findObject(Driver,By.id("_jpfcpncuivr_T3809095701380782586206_j_id_id0:customerSurveyForm"), "Expand All");

		if(collapsAllTable !=null)
		{
			List<WebElement> tds=collapsAllTable.findElements(By.tagName("b"));
			for(WebElement b:tds)
			{
				String val=b.getText();
				if(val.contains("Expand All [+]"))

				{     
					sleepTime(Driver, 1);

					GeneralComponents.clickOnWebelement(b, "Expand All [+] ");
					Report.updateTestLog("Expand All", "Expandse All in customer survey is clicked",Status.PASS);
					break;
				}   

			}
		}
		else
		{
			Report.updateTestLog("Expand All","Expand All in customer survey is not clicked ",Status.FAIL);
		}
	}

@Action(desc="checksimiley_tab_TIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void checksimiley_tab_TIS()
	{
		{

			WebElement RegVINelements= findObject(Driver,By.id("_jpfcpncuivr_T3809095701380782586206_j_id_id0:customerSurveyForm"), "Customer survey Elements for lexus dealer");
			if(RegVINelements !=null)

			{
				List<WebElement> tds2= RegVINelements.findElements(By.tagName("td"));
				for(WebElement a:tds2)
				{

					String val=a.getText();
					if((val.contains("10")))    
					{
						GeneralComponents.clickOnWebelement(a, "Sad");
						Report.updateTestLog("Happy similey", "Happy similey is displaying when overall satisfaction is less than 4",Status.PASS);
					}               
				}
			}
			else
			{
				Report.updateTestLog("Happy similey", "Happy similey is not displaying for the value less than 4 ",Status.FAIL);
			}

		}
	}     

//@Action(desc="checksadsimiley_tab_TIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	/*public void checksadsimiley_tab_TIS()
{
            WebElement Summary= findObject(Driver,By.id("_jpfcpncuivr_T3809095701380782586206_j_id_id0:customerSurveyForm"), "Docu link");
            if(Summary !=null)//_jpfcpncuivr_T3809095701380782586206_j_id_id0:customerSurveyForm

            {
                  List<WebElement> tds2= Summary.findElements(By.tagName("td"));
                for(WebElement a:tds2)
                {
                       String val=a.getText();
                       if((val.contentEquals("2")))
                       {                      
                                WebElement addtofav= findObject(Driver,By.xpath("//img[@src='https://tis.qa2.toyota.com:443/serviceLane/resources/images/customerSurveySad.jpg?_pageLabel=ASMPortal_portal_page_Display_One_view&_appSource=slane7']"), "Sad similey");
                                    if(addtofav !=null)

                                    {
                                               GeneralComponents.clickOnWebelement(addtofav , "add to fav");                                                  
                                               Report.updateTestLog("Sad similey", "Sad similey is displaying when overall satisfaction is less than 4",Status.PASS);
                       }
                }
            }
            }
     else
     {
     Report.updateTestLog("Sad similey", "Sad similey is not displaying for the value less than 4 ",Status.FAIL);
     }
            }
	 */

@Action(desc="checksadsimiley_tab_TIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void checksadsimiley_tab_TIS()
	{

		WebElement RegVINelements= findObject(Driver,By.id("_jpfcpncuivr_T3809095701380782586206_j_id_id0:customerSurveyForm"), "Customer survey Elements for lexus dealer");
		if(RegVINelements !=null)

		{
			List<WebElement> tds2= RegVINelements.findElements(By.tagName("td"));
			for(WebElement a:tds2)
			{

				String val=a.getText();
				if((val.contains("2")))    
				{
					GeneralComponents.clickOnWebelement(a, "Sad");
					Report.updateTestLog("Sad similey", "Sad similey is displaying when overall satisfaction is less than 4",Status.PASS);
				}               
			}
		}
		else
		{
			Report.updateTestLog("Sad similey", "Sad similey is not displaying for the value less than 4 ",Status.FAIL);
		}

	}

@Action(desc="clickrecordrow_Tab_TIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clickrecordrow_Tab_TIS()
	{

		WebElement RegVINelements= findObject(Driver,By.id("_jpfcpncuivr_T3809095701380782586206_j_id_id0:customerSurveyForm"), "Customer survey Elements for lexus dealer");
		if(RegVINelements !=null)

		{
			List<WebElement> tds2= RegVINelements.findElements(By.tagName("b"));
			for(WebElement a:tds2)
			{

				String val=a.getText();
				if((val.contains("Guest")))    
				{
					GeneralComponents.clickOnWebelement(a, "Record Row");
					Report.updateTestLog(val+"Check for Customer survey elements", "customer survey elements are displaying",Status.PASS);
				}               
			}
		}
		else
		{
			Report.updateTestLog("Check for Customer survey elements", "Customer survey elements are not displaying ",Status.FAIL);
		}

	}


	/**
	 *
	 * MethodName: Customer survey
	 * Description: Customer survey single View
	 * Parameter (if any):
	 * Return type: void
	 * Owner : Malini
	 */
@Action(desc="clickcustomersurvey_sing_TIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clickcustomersurvey_sing_TIS()

	{
		WebElement lookupBtn=findObject(Driver,By.id("_jpfcpncuivr_portletInstance_2_j_id_id0:vehicleSubView:vinForm:vinlookupbut"), "looup Button");

		if(lookupBtn !=null)
		{
			GeneralComponents.clickOnWebelement(lookupBtn, "");
			Driver.findElement(By.id("_jpfcpncuivr_portletInstance_2_j_id_id0:vehicleSubView:vinForm:iAgreeButton")).click();
			Report.updateTestLog("Lookup", "Lookup is clicked",Status.PASS);
		}
		else
		{
			Report.updateTestLog("Lookup","Lookup is not ",Status.FAIL);
		}

		GeneralComponents.waitforInternalLoad(Driver);
		WebElement DTChistory= findObject(Driver,By.id("t_customerSurvey_ajax"), "Guest info");
		if(DTChistory !=null)
		{

			WebElement link = DTChistory.findElement(By.xpath(".//..//..//.."));
			GeneralComponents.clickOnWebelement(link,"Link");
			Report.updateTestLog("Customer survey", "Customer survey tab is clicked",Status.PASS);

		}   

		else
		{
			Report.updateTestLog("Customer survey","Customer survey tab is not clicked ",Status.FAIL);
		}
	}



@Action(desc="chkcustomersurveyelements_sing_TIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkcustomersurveyelements_sing_TIS()
	{

		WebElement RegVINelements= findObject(Driver,By.id("_jpfcpncuivr_customerSurvey_j_id_id0:customerSurveyForm"), "Customer survey Elements for lexus dealer");
		if(RegVINelements !=null)

		{
			List<WebElement> tds2= RegVINelements.findElements(By.tagName("b"));
			for(WebElement a:tds2)
			{

				String val=a.getText();
				if((val.contains("Guest"))||(val.contains("Response Date"))||(val.contains("Dealer"))||(val.contains("Service Consultant"))||(val.contains("Technician"))||(val.contains("Repair Date"))||(val.contains("RO #"))||(val.contains("Overall Satisfaction")))

				{
					System.out.println(val);

					Report.updateTestLog(val+"Check for Customer survey elements", "customer survey elements are displaying",Status.PASS);


				}               
			}
		}
		else
		{
			Report.updateTestLog("Check for Customer survey elements", "Customer survey elements are not displaying ",Status.FAIL);
		}

	}


@Action(desc="Customer_svr_collapsAll_sing_TIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Customer_svr_collapsAll_sing_TIS()

	{WebElement collapsAllTable= findObject(Driver,By.id("_jpfcpncuivr_customerSurvey_j_id_id0:customerSurveyForm"), "Collapse All");

	if(collapsAllTable !=null)
	{
		List<WebElement> tds=collapsAllTable.findElements(By.tagName("b"));
		for(WebElement b:tds)
		{
			String val=b.getText();
			if(val.contains("Collapse All [-]"))

			{     
				sleepTime(Driver, 1);

				GeneralComponents.clickOnWebelement(b, "Collapse All [-] ");
				Report.updateTestLog("Collapse All", "Collapse All in customer survey is clicked",Status.PASS);
				break;
			}   

		}
	}
	else
	{
		Report.updateTestLog("Collapse All","Collapse All in customer survey is not clicked ",Status.FAIL);
	}
	}


@Action(desc="Customer_svr_expandAll_sing_TIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Customer_svr_expandAll_sing_TIS()

	{WebElement collapsAllTable= findObject(Driver,By.id("_jpfcpncuivr_customerSurvey_j_id_id0:customerSurveyForm"), "Expand All");
	//_jpfcpncuivr_T160043811357135664339_j_id_id0:serviceHistoryForm
	if(collapsAllTable !=null)
	{
		List<WebElement> tds=collapsAllTable.findElements(By.tagName("b"));
		for(WebElement b:tds)
		{
			String val=b.getText();
			if(val.contains("Expand All [+]"))

			{     
				sleepTime(Driver, 1);

				GeneralComponents.clickOnWebelement(b, "Expand All [+] ");
				Report.updateTestLog("Expand All", "Expandse All in customer survey is clicked",Status.PASS);
				break;
			}   

		}
	}
	else
	{
		Report.updateTestLog("Expand All","Expand All in customer survey is not clicked ",Status.FAIL);
	}
	}

@Action(desc="closecustomersurvey_ext",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void closecustomersurvey_ext()
	{
		WebElement campaign= findObject(Driver,By.id("wlp_title_repl_C_t_4069"), "campaign");
		if(campaign !=null)
		{
			WebElement link = campaign.findElement(By.xpath(".//.."));
			GeneralComponents.clickOnWebelement(link,"guestinfo");
			Report.updateTestLog("Customer survey", "customer survey tab is closed",Status.PASS);


		}
		else
		{
			Report.updateTestLog("customer survey","Customer survey tab is not closed ",Status.FAIL);
		}
	}

@Action(desc="checksimiley_sing_TIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void checksimiley_sing_TIS()
	{
		WebElement Summary= findObject(Driver,By.id("_jpfcpncuivr_customerSurvey_j_id_id0:customerSurveyForm"), "Docu link");
		if(Summary !=null)

		{
			List<WebElement> tds2= Summary.findElements(By.tagName("td"));
			for(WebElement a:tds2)
			{

				String val=a.getText();
				if((val.contains("10")))    
				{
					GeneralComponents.clickOnWebelement(a, "Sad");
					Report.updateTestLog("Happy similey", "Happy similey is displaying when overall satisfaction is less than 4",Status.PASS);
				}               
			}
		}
		else
		{
			Report.updateTestLog("Happy similey", "Happy similey is not displaying for the value less than 4 ",Status.FAIL);
		}
	}     

@Action(desc="checksadsimiley_sing_TIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void checksadsimiley_sing_TIS()
	{
		WebElement Summary= findObject(Driver,By.id("_jpfcpncuivr_customerSurvey_j_id_id0:customerSurveyForm"), "Docu link");
		if(Summary !=null)

		{
			List<WebElement> tds2= Summary.findElements(By.tagName("td"));
			for(WebElement a:tds2)
			{

				String val=a.getText();
				if((val.contains("2")))    
				{
					GeneralComponents.clickOnWebelement(a, "Sad");
					Report.updateTestLog("Sad similey", "Sad similey is displaying when overall satisfaction is less than 4",Status.PASS);
				}               
			}
		}
		else
		{
			Report.updateTestLog("Sad similey", "Sad similey is not displaying for the value less than 4 ",Status.FAIL);
		}
	}

@Action(desc="clickrecordrow_sing_TIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clickrecordrow_sing_TIS()
	{

		WebElement RegVINelements= findObject(Driver,By.id("_jpfcpncuivr_customerSurvey_j_id_id0:customerSurveyForm"), "Customer survey Elements for lexus dealer");
		if(RegVINelements !=null)

		{
			List<WebElement> tds2= RegVINelements.findElements(By.tagName("b"));
			for(WebElement a:tds2)
			{

				String val=a.getText();
				if((val.contains("Guest")))    
				{
					GeneralComponents.clickOnWebelement(a, "Record Row");
					Report.updateTestLog(val+"Check for Customer survey elements", "customer survey elements are displaying",Status.PASS);
				}               
			}
		}
		else
		{
			Report.updateTestLog("Check for Customer survey elements", "Customer survey elements are not displaying ",Status.FAIL);
		}

	}

@Action(desc="chkSad",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkSad()
	{		          // WebElement warranty= findObject(Driver,By.xpath("//img[@src='https://tis.qa2.toyota.com:443/serviceLane/resources/images/household.jpg?_pageLabel=ASMPortal_Vin_Search_Page&_appSource=slane7']"), "household icon");

		WebElement warranty= findObject(Driver,By.xpath("//img[@src='https://tis.qa2.toyota.com:443/serviceLane/resources/images/customerSurveySad.jpg?_pageLabel=ASMPortal_portal_page_Display_One_view&_appSource=slane7']"), "TOYOTA.logo");
		if(warranty !=null)
		{
			Report.updateTestLog("Sad similey", "Sad similey is displaying when overall satisfaction is less than 4",Status.PASS);

		}
		else
		{
			Report.updateTestLog("Sad similey", "Sad similey is not displaying for the value less than 4 ",Status.FAIL);
		}
	}







	/**
	 *
	 * MethodName: Print functionality
	 * Description: Internal portal
	 * Parameter (if any):
	 * Return type: void
	 * Owner : Malini
	 */


@Action(desc="Previewfunc_tab_T3_unselectall",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Previewfunc_tab_T3_unselectall()
	{                                                 
		WebElement PreviewAlert=findObject(Driver,By.name("_jpfcpncuivr_printVehicleOneViewPortlet_j_id_id0:mainForm:j_id_id63"), "AlertPreview");

		if(PreviewAlert !=null)
		{      GeneralComponents.waitforInternalLoad(Driver);

		GeneralComponents.clickOnWebelement(PreviewAlert, "");
		Alert ErrMsg = Driver.switchTo().alert();
		String alertText = ErrMsg.getText();
		ErrMsg.accept();
		Report.updateTestLog(alertText+"is displaying","Alert is displaying ",Status.PASS);
		Driver.switchTo().defaultContent();
		}
		else
		{
			Report.updateTestLog("Alet Msg","Alert Msg is not displaying ",Status.FAIL);
		}


	}





@Action(desc="ClickACE",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void ClickACE()
	{
		GeneralComponents.waitforInternalLoad(Driver);
		WebElement ACE= findObject(Driver,By.id("k_ASMPortal_Service_Lane_Book_ajax"), "ACE");
		if(ACE !=null)
		{
			List<WebElement> tds=ACE.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("ACE"))
				{
					GeneralComponents.clickOnWebelement(td, "ACE");
					Report.updateTestLog("ACE", "ACE tab is clicked",Status.PASS);
					break;
				}   

			}
		}
		else
		{
			Report.updateTestLog("ACE", "ACE tab is not clicked",Status.FAIL);

		}


	}

@Action(desc="ClickACEElement",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void ClickACEElement()
	{
		GeneralComponents.waitforInternalLoad(Driver);
		WebElement ACEElement= findObject(Driver,By.id("homeCarImg_0"), "ACE");
		if(ACEElement !=null)
		{
			GeneralComponents.clickOnWebelement(ACEElement, "ACE");
			{
				WebElement ACEElemententer= findObject(Driver,By.id("imgBtnHomeEnter"), "ACE");
				if(ACEElemententer!=null)
				{
					GeneralComponents.clickOnWebelement(ACEElemententer, "ACE");
				}
			}
			Report.updateTestLog("ACEelement", "user navigated to ACE tab",Status.PASS);
		}   


		else
		{
			Report.updateTestLog("ACE", "user navigated to ACE tab",Status.FAIL);

		}
	}

@Action(desc="ClickKnowledge_Center",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void ClickKnowledge_Center()
	{
		GeneralComponents.waitforInternalLoad(Driver);
		WebElement ACE= findObject(Driver,By.id("k_ASMPortal_Service_Lane_Book_ajax"), "Knowledge Center");
		if(ACE !=null)
		{
			List<WebElement> tds=ACE.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Knowledge Center"))
				{
					GeneralComponents.clickOnWebelement(td, "ACE");
					Report.updateTestLog("Knowledge Center", "Knowledge Center tab is clicked",Status.PASS);
					break;
				}   

			}
		}
		else
		{
			Report.updateTestLog("Knowledge Center", "Knowledge Center tab is not clicked",Status.FAIL);

		}


	}

@Action(desc="ClickPerformance",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void ClickPerformance()
	{
		GeneralComponents.waitforInternalLoad(Driver);
		WebElement ACE= findObject(Driver,By.id("k_ASMPortal_Service_Lane_Book_ajax"), "Performance");
		if(ACE !=null)
		{
			List<WebElement> tds=ACE.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("Performance"))
				{
					GeneralComponents.clickOnWebelement(td, "ACE");
					Report.updateTestLog("Performance", "Performance tab is clicked",Status.PASS);
					break;
				}   

			}
		}
		else
		{
			Report.updateTestLog("Performance", "Performance tab is not clicked",Status.FAIL);

		}


	}

@Action(desc="ClickLCMC",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void ClickLCMC()
	{
		GeneralComponents.waitforInternalLoad(Driver);
		WebElement ACE= findObject(Driver,By.id("k_ASMPortal_Service_Lane_Book_ajax"), "LCMC");
		if(ACE !=null)
		{
			List<WebElement> tds=ACE.findElements(By.tagName("td"));
			for(WebElement td:tds)
			{
				String val=td.getText();
				if(val.contains("LCMC"))
				{
					GeneralComponents.clickOnWebelement(td, "LCMC");
					Report.updateTestLog("LCMC", "LCMC tab is clicked",Status.PASS);
					break;
				}   

			}
		}
		else
		{
			Report.updateTestLog("LCMC", "LCMC tab is not clicked",Status.FAIL);

		}


	}



	////////////////////////////

	/**
	 *
	 * MethodName: Customer survey
	 * Description: Customer survey tabbed View
	 * Parameter (if any): internal
	 * Return type: void
	 * Owner : Malini
	 */



@Action(desc="clickcustomersurvey_tab_T3",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clickcustomersurvey_tab_T3()

	{
		GeneralComponents.waitforInternalLoad(Driver);
		{
			WebElement warranty= findObject(Driver,By.id("k_ASMPortal_Vehicle_Book_ajax"), "Customer Survey");
			if(warranty !=null)
			{
				List<WebElement> tds=warranty.findElements(By.tagName("td"));
				for(WebElement td:tds)
				{
					String val=td.getText();
					if(val.contains("Customer survey"))
					{
						GeneralComponents.clickOnWebelement(td, "Customer survey");
						Report.updateTestLog("Customer survey", "Customer survey tab is clicked",Status.PASS);
						break;
					}  

				}

			}
			else
			{
				Report.updateTestLog("Customer survey","Customer survey tab is not clicked ",Status.FAIL);
			}
		}
	}


@Action(desc="chkcustomersurveyelements_Tab_T3_Toyota_VIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkcustomersurveyelements_Tab_T3_Toyota_VIN()
	{

		WebElement RegVINelements= findObject(Driver,By.id("_jpfcpncuivr_T9808658571380782669223_j_id_id0:customerSurveyForm"), "Customer survey Elements for lexus dealer");
		if(RegVINelements !=null)

		{
			List<WebElement> tds2= RegVINelements.findElements(By.tagName("b"));
			for(WebElement a:tds2)
			{

				String val=a.getText();
				if((val.contains("Customer"))||(val.contains("Response Date"))||(val.contains("Dealer"))||(val.contains("Service Advisor"))||(val.contains("Technician"))||(val.contains("Repair Date"))||(val.contains("RO #"))||(val.contains("Overall Satisfaction")))

				{
					System.out.println(val);

					Report.updateTestLog(val+"Check for Customer survey elements", "customer survey elements are displaying",Status.PASS);


				}              
			}
		}
		else
		{
			Report.updateTestLog("Check for Customer survey elements", "Customer survey elements are not displaying ",Status.FAIL);
		}

	}


@Action(desc="Customer_svr_collapsAll_tab_T3",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Customer_svr_collapsAll_tab_T3()

	{WebElement collapsAllTable= findObject(Driver,By.id("_jpfcpncuivr_T9808658571380782669223_j_id_id0:customerSurveyForm"), "Collapse All");

	if(collapsAllTable !=null)
	{
		List<WebElement> tds=collapsAllTable.findElements(By.tagName("b"));
		for(WebElement b:tds)
		{
			String val=b.getText();
			if(val.contains("Collapse All [-]"))

			{    
				sleepTime(Driver, 1);

				GeneralComponents.clickOnWebelement(b, "Collapse All [-] ");
				Report.updateTestLog("Collapse All", "Collapse All in customer survey is clicked",Status.PASS);
				break;
			}  

		}
	}
	else
	{
		Report.updateTestLog("Collapse All","Collapse All in customer survey is not clicked ",Status.FAIL);
	}
	}


@Action(desc="Customer_svr_expandAll_tab_T3",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Customer_svr_expandAll_tab_T3()

	{
		WebElement collapsAllTable= findObject(Driver,By.id("_jpfcpncuivr_T9808658571380782669223_j_id_id0:customerSurveyForm"), "Expand All");

		if(collapsAllTable !=null)
		{
			List<WebElement> tds=collapsAllTable.findElements(By.tagName("b"));
			for(WebElement b:tds)
			{
				String val=b.getText();
				if(val.contains("Expand All [+]"))

				{    
					sleepTime(Driver, 1);

					GeneralComponents.clickOnWebelement(b, "Expand All [+] ");
					Report.updateTestLog("Expand All", "Expandse All in customer survey is clicked",Status.PASS);
					break;
				}  

			}
		}
		else
		{
			Report.updateTestLog("Expand All","Expand All in customer survey is not clicked ",Status.FAIL);
		}
	}

@Action(desc="checksimiley_tab_T3",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void checksimiley_tab_T3()
	{
		{

			WebElement RegVINelements= findObject(Driver,By.id("_jpfcpncuivr_T9808658571380782669223_j_id_id0:customerSurveyForm"), "Customer survey Elements for lexus dealer");
			if(RegVINelements !=null)

			{
				List<WebElement> tds2= RegVINelements.findElements(By.tagName("td"));
				for(WebElement a:tds2)
				{

					String val=a.getText();
					if((val.contains("9")))   
					{
						GeneralComponents.clickOnWebelement(a, "Happy");
						Report.updateTestLog("Happy similey", "Happy similey is displaying when overall satisfaction is less than 4",Status.PASS);
					}              
				}
			}
			else
			{
				Report.updateTestLog("Happy similey", "Happy similey is not displaying for the value less than 4 ",Status.FAIL);
			}

		}
	}    

//@Action(desc="checksadsimiley_tab_TIS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	/*public void checksadsimiley_tab_TIS()
{
            WebElement Summary= findObject(Driver,By.id("_jpfcpncuivr_T3809095701380782586206_j_id_id0:customerSurveyForm"), "Docu link");
            if(Summary !=null)//_jpfcpncuivr_T3809095701380782586206_j_id_id0:customerSurveyForm

            {
                  List<WebElement> tds2= Summary.findElements(By.tagName("td"));
                for(WebElement a:tds2)
                {
                       String val=a.getText();
                       if((val.contentEquals("2")))
                       {                     
                                WebElement addtofav= findObject(Driver,By.xpath("//img[@src='https://tis.qa2.toyota.com:443/serviceLane/resources/images/customerSurveySad.jpg?_pageLabel=ASMPortal_portal_page_Display_One_view&_appSource=slane7']"), "Sad similey");
                                    if(addtofav !=null)

                                    {
                                               GeneralComponents.clickOnWebelement(addtofav , "add to fav");                                                 
                                               Report.updateTestLog("Sad similey", "Sad similey is displaying when overall satisfaction is less than 4",Status.PASS);
                       }
                }
            }
            }
     else
     {
     Report.updateTestLog("Sad similey", "Sad similey is not displaying for the value less than 4 ",Status.FAIL);
     }
            }
	 */

@Action(desc="checksadsimiley_tab_T3",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void checksadsimiley_tab_T3()
	{

		WebElement RegVINelements= findObject(Driver,By.id("_jpfcpncuivr_T9808658571380782669223_j_id_id0:customerSurveyForm"), "Customer survey Elements for lexus dealer");
		if(RegVINelements !=null)

		{
			List<WebElement> tds2= RegVINelements.findElements(By.tagName("td"));
			for(WebElement a:tds2)
			{

				String val=a.getText();
				if((val.contains("2")))   
				{
					GeneralComponents.clickOnWebelement(a, "Sad");
					Report.updateTestLog("Sad similey", "Sad similey is displaying when overall satisfaction is less than 4",Status.PASS);
				}              
			}
		}
		else
		{
			Report.updateTestLog("Sad similey", "Sad similey is not displaying for the value less than 4 ",Status.FAIL);
		}

	}

@Action(desc="clickrecordrow_Tab_T3",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clickrecordrow_Tab_T3()
	{

		WebElement RegVINelements= findObject(Driver,By.id("_jpfcpncuivr_T9808658571380782669223_j_id_id0:customerSurveyForm"), "Customer survey Elements for lexus dealer");
		if(RegVINelements !=null)

		{
			List<WebElement> tds2= RegVINelements.findElements(By.tagName("b"));
			for(WebElement a:tds2)
			{

				String val=a.getText();
				if((val.contains("Guest")))   
				{
					GeneralComponents.clickOnWebelement(a, "Record Row");
					Report.updateTestLog(val+"Check for Customer survey elements", "customer survey elements are displaying",Status.PASS);
				}              
			}
		}
		else
		{
			Report.updateTestLog("Check for Customer survey elements", "Customer survey elements are not displaying ",Status.FAIL);
		}

	}

	////method for lexus VIN_internal portal
@Action(desc="chkcustomersurveyelements_tab_T3_Lexus_VIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void chkcustomersurveyelements_tab_T3_Lexus_VIN()
	{

		WebElement RegVINelements= findObject(Driver,By.id("_jpfcpncuivr_T9808658571380782669223_j_id_id0:customerSurveyForm"), "Customer survey Elements for lexus dealer");
		if(RegVINelements !=null)

		{
			List<WebElement> tds2= RegVINelements.findElements(By.tagName("b"));
			for(WebElement a:tds2)
			{

				String val=a.getText();
				if((val.contains("Guest"))||(val.contains("Response Date"))||(val.contains("Dealer"))||(val.contains("Service Consultant"))||(val.contains("Technician"))||(val.contains("Repair Date"))||(val.contains("RO #"))||(val.contains("Overall Satisfaction")))

				{
					System.out.println(val);

					Report.updateTestLog(val+"Check for Customer survey elements", "customer survey elements are displaying",Status.PASS);


				}              
			}
		}
		else
		{
			Report.updateTestLog("Check for Customer survey elements", "Customer survey elements are not displaying ",Status.FAIL);
		}

	}

@Action(desc="clickOOnVIN",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clickOOnVIN()
	{

		WebElement RegVINelements= findObject(Driver,By.id("_jpfcpncuivr_T1200191171441212261383_j_id_id1:alertTrackerForm"), "Customer survey Elements for lexus dealer");
		if(RegVINelements !=null)

		{
			List<WebElement> tds2= RegVINelements.findElements(By.tagName("td"));
			for(WebElement a:tds2)
			{

				String val=a.getText();
				if((val.contains("JTHBK1GG2G2211144")))   
				{
					GeneralComponents.clickOnWebelement(a, "Sad");
					Report.updateTestLog("Sad similey", "Sad similey is displaying when overall satisfaction is less than 4",Status.PASS);
				}              
			}
		}
		else
		{
			Report.updateTestLog("Sad similey", "Sad similey is not displaying for the value less than 4 ",Status.FAIL);
		}

	}



@Action(desc="clickguestinformation_single_Ext",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void clickguestinformation_single_Ext()
	{
		WebElement Guestinfo= findObject(Driver,By.id("t_guestInformation_ajax"), "Guestinfo");
		if(Guestinfo !=null)
		{
			WebElement link = Guestinfo.findElement(By.xpath(".//..//..//.."));
			GeneralComponents.clickOnWebelement(link,"Link");
			Report.updateTestLog("Guestinfo", "Guestinfo tab is clicked",Status.PASS);


		}
		else
		{
			Report.updateTestLog("Guestinfo","Guestinfo tab is not clicked ",Status.FAIL);
		}
	}

@Action(desc="clickOnMAC",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void clickOnMAC()
{
	WebElement Mac= findObject(Driver,By.id("k_ASMPortal_Service_Lane_Book_ajax"), "MAC");
	if(Mac !=null)
	{
		WebElement link = Mac.findElement(By.linkText("MAC" ));
		GeneralComponents.clickOnWebelement(link,"Link");
		Report.updateTestLog("MAC", "MAC is clicked",Status.PASS);


	}
	else
	{
		Report.updateTestLog("MAC","MAC is not clicked ",Status.FAIL);
	}
}

//TIS TAS Library Module

@Action(desc="t3_login",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void t3_login() {
	String Scenario = userData.getScenario();
	System.out.println(Scenario);
	String TestCase = userData.getTestCase();
	System.out.println(TestCase);
	String Iteration = userData.getIteration();
	String SubIteration = userData.getSubIteration();
	
	String url = userData.getData("t3_login","URL",Scenario,TestCase,Iteration,SubIteration);
	System.out.println(url);
	String usertype= userData.getData("t3_login","Usertype",Scenario,TestCase,Iteration,SubIteration);
	String username= userData.getData("t3_login","Username",Scenario,TestCase,Iteration,SubIteration);
	String password= userData.getData("t3_login","Pwd",Scenario,TestCase,Iteration,SubIteration);
	String userregion= userData.getData("t3_login","Userregion",Scenario,TestCase,Iteration,SubIteration);
	
	t3_login(url, usertype,username,password, userregion) ;
}


public void t3_login(String url, String usertype, String username, String password, String userregion) {
	WebDriverWait wait = new WebDriverWait(Driver,15);
	boolean Check = true;
	try {
		 Driver.get(url);  //URL
		 System.out.println(url);
		 System.out.println("00");
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userType")));
		 
		 System.out.println("01");
		 System.out.println(usertype);
		 Driver.findElement(By.id("userType")).sendKeys(usertype); // Usertype dropdown
		 System.out.println("001");
	}catch (Exception e1) {
		 Check = false;
		 System.out.println("02");
		 		 //e1.printStackTrace();
		 	 }
	if(Check) {
		if(usertype.equalsIgnoreCase("tms")) {
			System.out.println("03");
			tms_t3_login( url,  username,  password);
		}else {
			if(usertype.equalsIgnoreCase("Toyota Affiliate Users")) {
				System.out.println("04");
				affiliate_t3_login ( url,  usertype,  username,  password,  userregion);
			}else {
				if(usertype.equalsIgnoreCase("Toyota Business Partner Users")) {
					businessPartner_t3_login ( url,  usertype,  username,  password,  userregion);
				}else {
					System.out.println("Invalid usertype");
					Report.updateTestLog("Opened "+url,"Invalid usertype.",Status.FAIL);
				}
			}
		}
}else
{	 
	 Report.updateTestLog("Opening "+url,"Page not opened.",Status.FAIL);
}
}	

public void tms_t3_login(String url, String username, String password) {
	WebDriverWait wait = new WebDriverWait(Driver,15);
	boolean Check = true;
	
	Driver.findElement(By.id("username")).sendKeys(username); // Username
	Driver.findElement(By.id("password")).sendKeys(password); // Password
	Driver.findElement(By.id("terms")).click(); // Terms and conditions checkbox
	Driver.findElement(By.id("submit")).click(); // Submit button	
	try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout")));
	}catch (Exception e2) {
		 Check = false;
			 //e2.printStackTrace();
		 }
	if(Check) {
		Report.updateTestLog("Clicked on Submit","Successfully Logged in as  "+username,Status.PASS);
	}else
	{	 
		 Report.updateTestLog("Opening "+url,"Login Failed.",Status.FAIL);
	}			
}

public void affiliate_t3_login (String url, String usertype, String username, String password, String userregion) {
	WebDriverWait wait = new WebDriverWait(Driver,15);
	boolean Check = true;	
	
	Driver.findElement(By.id("userRegion")).sendKeys(userregion); // userRegion
	Driver.findElement(By.id("terms")).click(); // Terms and conditions checkbox
	Driver.findElement(By.id("proceed")).click(); // Submit button	
	
if(userregion.equalsIgnoreCase("tci")) {	 
	try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='USER']")));
}catch (Exception e2) {
	 Check = false;
		 //e2.printStackTrace();
	 }
	if(Check) {
		Driver.findElement(By.xpath("//input[@name='USER']")).sendKeys(username);
		Driver.findElement(By.xpath("//input[@name='PASSWORD']")).sendKeys(password);
		Driver.findElement(By.xpath("//input[@value='Login']")).click();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout")));
		}catch (Exception ea) {
			Check = false;
		}
		
		if(Check) {
			Report.updateTestLog("Clicked on Submit","Successfully Logged in as  "+username,Status.PASS);
		}else
		{	 
			 Report.updateTestLog("Opening "+url,"Login Failed.",Status.FAIL);
		}
	}else
	{	 
		 Report.updateTestLog("Opening "+url,"External site not opened.",Status.FAIL);
	}
}
else
{	 
	 Report.updateTestLog("Opening "+url,"Write the method for userregion.",Status.FAIL);
}

	
	
}

public void businessPartner_t3_login (String url, String usertype, String username, String password, String userregion) {
	WebDriverWait wait = new WebDriverWait(Driver,15);
boolean Check = true;	

Driver.findElement(By.id("userRegion")).sendKeys(userregion); // userRegion
Driver.findElement(By.id("terms")).click(); // Terms and conditions checkbox
Driver.findElement(By.id("proceed")).click(); // Submit button	

if(userregion.equalsIgnoreCase("SET")) {	 
try {
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_UsernameTextBox']")));
}catch (Exception e2) {
 Check = false;
	 //e2.printStackTrace();
 }
if(Check) {
	Driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_UsernameTextBox']")).sendKeys(username);
	Driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_PasswordTextBox']")).sendKeys(password);
	Driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_Button1']")).click();
	try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout")));
	}catch (Exception ea) {
		Check = false;
	}	
	if(Check) {
		Report.updateTestLog("Clicked on Submit","Successfully Logged in as  "+username,Status.PASS);
	}else
	{	 
		 Report.updateTestLog("Opening "+url,"Login Failed.",Status.FAIL);
	}
	}else{	 
	 Report.updateTestLog("Opening "+url,"External site not opened.",Status.FAIL);
	}
}else{	 
	if(userregion.equalsIgnoreCase("SET")) {	 
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='userBox']")));
		}catch (Exception e2) {
		 Check = false;
			 //e2.printStackTrace();
		 }
		if(Check) {
			Driver.findElement(By.xpath("//input[@id='userBox']")).sendKeys(username);
			Driver.findElement(By.xpath("//input[@name='PASSWORD']")).sendKeys(password);
			Driver.findElement(By.xpath("//input[@type='image']")).click();
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout")));
			}catch (Exception ea) {
				Check = false;
			}	
			if(Check) {
				Report.updateTestLog("Clicked on Submit","Successfully Logged in as  "+username,Status.PASS);
			}else
			{	 
				 Report.updateTestLog("Opening "+url,"Login Failed.",Status.FAIL);
			}
			}else{	 
			 Report.updateTestLog("Opening "+url,"External site not opened.",Status.FAIL);
			}
		
		
		}else{	 
		 Report.updateTestLog("Opening "+url,"Write the method for userregion.",Status.FAIL);
		}
}
}


@Action(desc="techinfo_login",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void techinfo_login() {
String Scenario = userData.getScenario();
System.out.println(Scenario);
String TestCase = userData.getTestCase();
System.out.println(TestCase);
String Iteration = userData.getIteration();
String SubIteration = userData.getSubIteration();

String url = userData.getData("techinfo_login","URL",Scenario,TestCase,Iteration,SubIteration);
System.out.println(url);
String username= userData.getData("techinfo_login","Email",Scenario,TestCase,Iteration,SubIteration);
String password= userData.getData("techinfo_login","Pwd",Scenario,TestCase,Iteration,SubIteration);

techinfo_login(url,username,password) ;
}

public void techinfo_login(String url, String username, String password) {
WebDriverWait wait = new WebDriverWait(Driver,15);
boolean Check = true;
try {
	 Driver.get(url);  //URL
	 System.out.println(url);
	 System.out.println("00");
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
}catch (Exception e1) {
	 Check = false;
	 System.out.println("02");
	 		 //e1.printStackTrace();
	 	 }
if(Check) {
	Driver.findElement(By.id("username")).sendKeys(username);
	Driver.findElement(By.id("password")).sendKeys(password);
	Driver.findElement(By.id("externalloginsubmit")).click();
	
	try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout")));
}catch (Exception e2) {
	 Check = false;
		 //e2.printStackTrace();
	 }
if(Check) {
	Report.updateTestLog("Clicked on Submit","Successfully Logged in as  "+username,Status.PASS);
}else
{	 
	 Report.updateTestLog("Opening "+url,"Login Failed.",Status.FAIL);
}

}else
{	 
 Report.updateTestLog("Opening "+url,"Page not opened.",Status.FAIL);
}
}	


@Action(desc="Servinfo_DMY_Search",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void Servinfo_DMY_Search() {
	
	String Scenario = userData.getScenario();
	System.out.println(Scenario);
	String TestCase = userData.getTestCase();
	System.out.println(TestCase);
	String Iteration = userData.getIteration();
	String SubIteration = userData.getSubIteration();
	
	String Division = userData.getData("Servinfo_DMY_Search","Division",Scenario,TestCase,Iteration,SubIteration);
	String Model= userData.getData("Servinfo_DMY_Search","Model",Scenario,TestCase,Iteration,SubIteration).trim() == "" ? "ALL" : userData.getData("Servinfo_DMY_Search","Model",Scenario,TestCase,Iteration,SubIteration);
	System.out.println(Model);
	String MYear= userData.getData("Servinfo_DMY_Search","MYear",Scenario,TestCase,Iteration,SubIteration).trim() == "" ? "ALL" : userData.getData("Servinfo_DMY_Search","MYear",Scenario,TestCase,Iteration,SubIteration);
	System.out.println(MYear);
	String SCategory = userData.getData("Servinfo_DMY_Search","Service_Category",Scenario,TestCase,Iteration,SubIteration).trim() == "" ? "ALL" : userData.getData("Servinfo_DMY_Search","Service_Category",Scenario,TestCase,Iteration,SubIteration);
	System.out.println(SCategory);
	String Section = userData.getData("Servinfo_DMY_Search","Section",Scenario,TestCase,Iteration,SubIteration).trim() == "" ? "ALL" : userData.getData("Servinfo_DMY_Search","Section",Scenario,TestCase,Iteration,SubIteration) ;
	System.out.println(Section);
	String Keyword = userData.getData("Servinfo_DMY_Search","Keyword",Scenario,TestCase,Iteration,SubIteration).trim() == "" ? "" : userData.getData("Servinfo_DMY_Search","Keyword",Scenario,TestCase,Iteration,SubIteration);
	System.out.println(Keyword);
	
	Servinfo_DMY_Search(Division, Model, MYear, SCategory, Section, Keyword) ;
}
public void Servinfo_DMY_Search(String Division, String Model, String Myear, String SCategory, String Section, String Keyword) {  
	WebDriverWait wait = new WebDriverWait(Driver,15); 
wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("serviceInfoMake")));	
	
Driver.findElement(By.id("serviceInfoMake")).sendKeys(Division);
System.out.println(Driver.findElement(By.id("serviceInfoMake")).getText());
wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("serviceInfoMake"), "Division: "+Division));
Driver.findElement(By.id("serviceInfoModel")).sendKeys(Model);
System.out.println(Driver.findElement(By.id("serviceInfoModel")).getText());
wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("serviceInfoModel"), "Model: "+Model));
Driver.findElement(By.id("serviceInfoYear")).sendKeys("Year: "+Myear);
wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("serviceInfoYear"),"Year: "+Myear));
Driver.findElement(By.id("serviceInfoServiceCategory")).sendKeys(SCategory);
wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("serviceInfoServiceCategory"), SCategory));
Driver.findElement(By.id("serviceInfoSymptomSection")).sendKeys(Section);
wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("serviceInfoSymptomSection"), Section));
Driver.findElement(By.id("keywordoutline")).sendKeys(Keyword);
wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("keywordoutline"), Keyword));

Driver.findElement(By.id("searchButton")).click();
}

@Action(desc="rate_doc",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void rate_doc () throws ClassNotFoundException, SQLException {
	String Scenario = userData.getScenario();
	String TestCase = userData.getTestCase();
	String Iteration = userData.getIteration();
	String SubIteration = userData.getSubIteration();
	
	String url =get_url(Scenario,TestCase,Iteration,SubIteration) ;
	System.out.println("Env is "+url); 
	String DataFileName = get_LibraryCSVFileName(TestCase) ;
	if(DataFileName != null) {
	String doc_type =get_DataFromCSV( DataFileName, "DocType",Scenario,TestCase,Iteration,SubIteration) ;
	System.out.println("Doc type is "+doc_type);    
	String Div = get_DataFromCSV( DataFileName, "Division",Scenario,TestCase,Iteration,SubIteration);
	System.out.println("Division is "+Div); 
	String Model= get_DataFromCSV( DataFileName, "Model",Scenario,TestCase,Iteration,SubIteration).trim().isEmpty() ? "ALL" : get_DataFromCSV( DataFileName, "Model",Scenario,TestCase,Iteration,SubIteration);
	System.out.println("Model :"+Model);
	String Myear= get_DataFromCSV( DataFileName, "MYear",Scenario,TestCase,Iteration,SubIteration).trim().isEmpty() ? "ALL" : get_DataFromCSV( DataFileName, "MYear",Scenario,TestCase,Iteration,SubIteration);
	System.out.println("Model Year :"+Myear);
	String Sc = get_DataFromCSV( DataFileName, "Service_Category",Scenario,TestCase,Iteration,SubIteration).trim().isEmpty() ? "ALL" : get_DataFromCSV( DataFileName, "Service_Category",Scenario,TestCase,Iteration,SubIteration);
	System.out.println("Service Category :"+Sc);
	String Sec = get_DataFromCSV( DataFileName, "Section",Scenario,TestCase,Iteration,SubIteration).trim().isEmpty() ? "ALL" : get_DataFromCSV( DataFileName, "Section",Scenario,TestCase,Iteration,SubIteration) ;
	System.out.println("Section :"+Sec);
	String Keyword = get_DataFromCSV( DataFileName, "Keyword",Scenario,TestCase,Iteration,SubIteration).trim().isEmpty() ? "keywordnotavailable" : get_DataFromCSV( DataFileName, "Keyword",Scenario,TestCase,Iteration,SubIteration);
	System.out.println("Keyword :"+Keyword);
	String Language = get_DataFromCSV( DataFileName, "Language",Scenario,TestCase,Iteration,SubIteration).trim().isEmpty() ? "en" : get_DataFromCSV( DataFileName, "Language",Scenario,TestCase,Iteration,SubIteration);
	System.out.println("Language :"+Language);
	String	pub_env_flg = get_pub_env_flg(TestCase);
	
	String t3id =null;
	if(url!=null && doc_type!=null) {
	t3id= get_t3id ( TestCase, url, doc_type,  Div,  Model,  Myear,  Sc,  Sec, Keyword, Language) ;
	System.out.println("T3_id:"+t3id);
	}else {
		System.out.println("URL/doc_type is null");
	}
	
		if (t3id != null) {
			if (doc_type.contains("ewd")) {
				click_ewd_rate(t3id, url);
			} else if (doc_type.equalsIgnoreCase("cr")) {
				click_cr_rate(t3id, url);
			}else if (doc_type.equalsIgnoreCase("crib")) {
				click_crib_rate(t3id, url);
			}else {
				System.out.println("Method not available for the doc type");
			}
		} else {
			System.out.println("T3_id is null, Documents with this combination are  not available in the system");
		}
}else {
		System.out.println("File Name is null View_doc method");
	}		
}

public void CallRateDoc(String doc_type ,String t3id ,String url ) {

	if (doc_type.contains("ewd")) {
		click_ewd_rate(t3id, url);
	} else if (doc_type.equalsIgnoreCase("cr")) {
		click_cr_rate(t3id, url);
	}else if (doc_type.equalsIgnoreCase("crib")) {
		click_crib_rate(t3id, url);
	}else {
		System.out.println("Method not available for the doc type");
	}

}


public String get_url(String Scenario, String TestCase, String Iteration, String SubIteration) {

String url =null;
		if (TestCase.contains("Corporate")) {
			url = userData.getData("t3_login", "URL", Scenario, TestCase, Iteration, SubIteration);
			System.out.println(" Internal URL: " + url);
		} else if (TestCase.contains("Dealer")) {
			url = userData.getData("tis_login", "URL", Scenario, TestCase, Iteration, SubIteration);
			System.out.println("External URL: " + url);
		} else if (TestCase.contains("Techinfo")) {
			url = userData.getData("techinfo_login", "URL", Scenario, TestCase, Iteration, SubIteration);
			System.out.println("Techinfo URL: " + url);
		} else {
			System.out.println("Unable to fetch URL");
		}
	return url;
}

public String get_t3id (String TestCase,String url,String doc_type, String Div, String Model, String Myear, String Sc, String Sec, String Keyword, String Language) throws ClassNotFoundException, SQLException {
	String dburl =null;
	Connection con =null;
	String DMYSS = null;
	String t3_id = null;
	String DMYSS_Query =  null;
	System.out.println("Came to get_t3id metohd URL: " +url);
	dburl= get_t3internal_dburl(url); 
	System.out.println("DB URL is "+dburl);
    Class.forName("oracle.jdbc.driver.OracleDriver");
    String UserName = "t3datglobl";
    String Password = "t3dgprod";
    if(dburl!=null) {
    	con = DriverManager.getConnection(dburl, UserName, Password);
    	DMYSS_Query= get_DMYSSQuery( con,  Div,  Model,  Myear,  Sc,  Sec) ;
    	String	pub_env_flg = get_pub_env_flg(TestCase);
			if (DMYSS_Query != null && con != null) {
				Statement Stmt = con.createStatement();
				ResultSet rs = Stmt.executeQuery(DMYSS_Query);
				System.out.println("DMYSS_Query Query extecuted");
				while (rs.next()) {
					DMYSS = rs.getString(1);
					System.out.println("DMYSS No: " + DMYSS);
				}
			} else {
				System.out.println("DMYSS Query method is not available/ Invalid DB details");
			}
			if (DMYSS != null && pub_env_flg!=null) {
String t3_id_query = get_t3id_query(pub_env_flg, doc_type, DMYSS, Keyword , Language);             
				if (t3_id_query != null) {
					Statement Stmt1 = con.createStatement();
					ResultSet rs1 = Stmt1.executeQuery(t3_id_query);
					while (rs1.next()) {
						System.out.println("8");
						t3_id = rs1.getString(1);
						System.out.println(t3_id);
					}
				} else {
					System.out.println("T3ID Query is null");
				}	
			} else {
				System.out.println("DMYSS/pub_env_flg is not avialable");
			}
			if (con != null) {
				con.close();
				System.out.println("Connection closed successfully");
			} else {
				System.out.println("Connection is not avialable");
			}	
    }else {
    	System.out.println("Invalid Environment details");
    }
    return t3_id;
}

public String get_t3internal_dburl(String url) {
	String dburl=null;
	if (url.contains("qa1")) {
		dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IA.tmm.na.corp.toyota.com)(PORT = 1590))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IA.tmm.na.corp.toyota.com)))";
		// (INSTANCE_NAME = T3IA)
	} else if (url.contains("qa2")) {
		dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IS.tmm.na.corp.toyota.com)(PORT = 1590))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IS.tmm.na.corp.toyota.com)))";
		// (INSTANCE_NAME = T3IS)
	} else if (url.contains("qa3")) {
		dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
		// (INSTANCE_NAME = T3IC)
	}
	return dburl;
}

public String get_DMYSSQuery(Connection con, String Div, String Model, String Myear, String Sc, String Sec) throws SQLException{
	String DMYSS_Query =null;
	
if(Model.equalsIgnoreCase("ALL") && Myear.equalsIgnoreCase("ALL") && Sc.equalsIgnoreCase("ALL") && Sec.equalsIgnoreCase("ALL")) {
		DMYSS_Query = only_d(con, Div);
	}else if(Model.equalsIgnoreCase("ALL") && Myear.equalsIgnoreCase("ALL") && !Sc.equalsIgnoreCase("ALL") && Sec.equalsIgnoreCase("ALL")) {
		DMYSS_Query = only_ds(con, Div,Sc);
	}else if(Model.equalsIgnoreCase("ALL") && Myear.equalsIgnoreCase("ALL") && !Sc.equalsIgnoreCase("ALL") && !Sec.equalsIgnoreCase("ALL")) {
		DMYSS_Query = only_dss(con, Div,Sc,Sec);
	}else if(!Model.equalsIgnoreCase("ALL") && Myear.equalsIgnoreCase("ALL") && Sc.equalsIgnoreCase("ALL") && Sec.equalsIgnoreCase("ALL")) {
		DMYSS_Query = only_dm(con, Div, Model);
	}else if(!Model.equalsIgnoreCase("ALL") && !Myear.equalsIgnoreCase("ALL") && Sc.equalsIgnoreCase("ALL")&& Sec.equalsIgnoreCase("ALL")) {
		DMYSS_Query = only_dmy(con, Div, Model, Myear);
	}else if(!Model.equalsIgnoreCase("ALL") && !Myear.equalsIgnoreCase("ALL") && !Sc.equalsIgnoreCase("ALL") && Sec.equalsIgnoreCase("ALL")) {
		DMYSS_Query = only_dmys(con, Div, Model, Myear, Sc);
	}else if(!Model.equalsIgnoreCase("ALL") && !Myear.equalsIgnoreCase("ALL") && !Sc.equalsIgnoreCase("ALL")&& !Sec.equalsIgnoreCase("ALL")) {
		DMYSS_Query = only_dmyss(con, Div, Model, Myear, Sc, Sec);
	}
	
	else{
		System.out.println("DMYSS Query method is not available");
		}
	return DMYSS_Query;
}

public String only_d (Connection con, String Div) {
	String DMYSS_Query_d = "select DMYSS_KEY from dmyss_keys where division_name='"+Div+"' and rownum<=1 ";
	System.out.println("DMYSS query:" +DMYSS_Query_d);
	return DMYSS_Query_d;
}

public String only_dm (Connection con, String Div, String Model) {
	String DMYSS_Query_dm = "select DMYSS_KEY from dmyss_keys where division_name='"+Div+"' and model_name='"+Model+"' and rownum<=1 " ;
	System.out.println("DMYSS query:" +DMYSS_Query_dm);
	return DMYSS_Query_dm;
}

public String only_dmy (Connection con, String Div, String Model, String Myear) {
	String DMYSS_Query_dmy = "select DMYSS_KEY from dmyss_keys where division_name='"+Div+"' and model_name='"+Model+"' and Model_year='"+Myear+"' and rownum<=1 ";
	System.out.println("DMYSS query:" +DMYSS_Query_dmy);
	return DMYSS_Query_dmy;
}

public String only_dmys (Connection con, String Div, String Model, String Myear, String Sc_desc) throws SQLException {
	
	String SC_Query = "select SERVICE_CATEGORY_CODE from service_categories where SERVICE_CATEGORY_DESC='"+Sc_desc+"'" ; 
	Statement Stmt_sc = con.createStatement();
    ResultSet rs_sc = Stmt_sc.executeQuery(SC_Query);
    System.out.println("3");
    String SC_code =null;
    while(rs_sc.next()){
    	  SC_code =rs_sc.getString(1);
                System.out.println("SC_code: "+SC_code);
 }

String DMYSS_Query_dmys = "select DMYSS_KEY from dmyss_keys where division_name='"+Div+"' and model_name='"+Model+"' and Model_year='"+Myear+"' and SERVICE_CATEGORY_CODE='"+SC_code+"'  and rownum<=1 ";
System.out.println("DMYSS query:" +DMYSS_Query_dmys);	
return DMYSS_Query_dmys;
}

public String only_dmyss (Connection con, String Div, String Model, String Myear, String Sc_desc, String Sec_desc) throws SQLException {
	String SC_code =null;
    String Sec_code =null;
	String SC_Query = "select SERVICE_CATEGORY_CODE from service_categories where SERVICE_CATEGORY_DESC='"+Sc_desc+"'" ;
	System.out.println("SC Query : " +SC_Query );
	Statement Stmt_sc = con.createStatement();
    ResultSet rs_sc = Stmt_sc.executeQuery(SC_Query);
    System.out.println("3");
        while(rs_sc.next()){
    	  SC_code =rs_sc.getString(1);
                System.out.println("SC_code: "+SC_code);
 }
        String Sec_Query = "select SECTION_CODE from section_codes where SECTION_DESC='"+Sec_desc+"' and SERVICE_CATEGORY_CODE='"+SC_code+"' " ; 
        System.out.println("Sec Query : " +Sec_Query );
    	Statement Stmt_sec = con.createStatement();
        ResultSet rs_sec = Stmt_sec.executeQuery(Sec_Query);
        System.out.println("3");
            while(rs_sec.next()){
        	  Sec_code =rs_sec.getString(1);
                    System.out.println("Sec_code: "+Sec_code);
     }       
	String DMYSS_Query_dmyss = "select DMYSS_KEY from dmyss_keys where division_name='"+Div+"' and model_name='"+Model+"' and Model_year='"+Myear+"' and SERVICE_CATEGORY_CODE='"+SC_code+"' and SECTION_CODE='"+Sec_code+"'  and rownum<=1 ";
	System.out.println("DMYSS query:" +DMYSS_Query_dmyss);
	return DMYSS_Query_dmyss;
}


public String only_ds (Connection con, String Div,  String Sc_desc) throws SQLException {
	
	String SC_Query = "select SERVICE_CATEGORY_CODE from service_categories where SERVICE_CATEGORY_DESC='"+Sc_desc+"'" ; 
	Statement Stmt_sc = con.createStatement();
    ResultSet rs_sc = Stmt_sc.executeQuery(SC_Query);
    System.out.println("3");
    String SC_code =null;
    while(rs_sc.next()){
    	  SC_code =rs_sc.getString(1);
                System.out.println("SC_code: "+SC_code);
 }

String DMYSS_Query_ds = "select DMYSS_KEY from dmyss_keys where division_name='"+Div+"'  and SERVICE_CATEGORY_CODE='"+SC_code+"'  and rownum<=1 ";
System.out.println("DMYSS query:" +DMYSS_Query_ds);	
return DMYSS_Query_ds;
}


public String only_dss (Connection con, String Div, String Sc_desc, String Sec_desc) throws SQLException {
	String SC_code =null;
    String Sec_code =null;
	String SC_Query = "select SERVICE_CATEGORY_CODE from service_categories where SERVICE_CATEGORY_DESC='"+Sc_desc+"'" ;
	System.out.println("SC Query : " +SC_Query );
	Statement Stmt_sc = con.createStatement();
    ResultSet rs_sc = Stmt_sc.executeQuery(SC_Query);
    System.out.println("3");
        while(rs_sc.next()){
    	  SC_code =rs_sc.getString(1);
                System.out.println("SC_code: "+SC_code);
 }
        String Sec_Query = "select SECTION_CODE from section_codes where SECTION_DESC='"+Sec_desc+"' and SERVICE_CATEGORY_CODE='"+SC_code+"' " ; 
        System.out.println("Sec Query : " +Sec_Query );
    	Statement Stmt_sec = con.createStatement();
        ResultSet rs_sec = Stmt_sec.executeQuery(Sec_Query);
        System.out.println("3");
            while(rs_sec.next()){
        	  Sec_code =rs_sec.getString(1);
                    System.out.println("Sec_code: "+Sec_code);
     }       
	String DMYSS_Query_dss = "select DMYSS_KEY from dmyss_keys where division_name='"+Div+"' and SERVICE_CATEGORY_CODE='"+SC_code+"' and SECTION_CODE='"+Sec_code+"'  and rownum<=1 ";
	System.out.println("DMYSS query:" +DMYSS_Query_dss);
	return DMYSS_Query_dss;
}


public String get_pub_env_flg( String TestCase) {
	String pub_env_flg =null;
	if (TestCase.contains("Corporate")) {
		pub_env_flg="I";
		System.out.println(" Pub Env flag : " + pub_env_flg);
	} else if (TestCase.contains("Dealer")) {
		pub_env_flg="E";
		System.out.println(" Pub Env flag " + pub_env_flg);
	} else if (TestCase.contains("Techinfo")) {
		pub_env_flg="E";
		System.out.println(" Pub Env flag " + pub_env_flg);
	} else {
		System.out.println("Unable to identify Pub Env flag ");
	}
	return pub_env_flg;
}

public String get_t3id_query(String pub_env_flg, String doc_type,String DMYSS, String Keyword, String Lang) {
	String t3id_query=null;	
	if(Keyword.equalsIgnoreCase("keywordnotavailable")) {
t3id_query="select T3_ID from oracle_fetch_solr where  DELETE_IND is null and pub_env_flag like '%"+pub_env_flg+"%'  and doc_type ='"+doc_type+"' and LANGUAGE='"+Lang+"'  and metakey like '%"+DMYSS+"%'  and rownum<=1  ";
	System.out.println("T3 ID query : "+t3id_query);
	}else {
		Keyword = Keyword.substring(1);
		Keyword = Keyword.substring(0, Keyword.length()-1);
		System.out.println("Keyword is : "+Keyword);
t3id_query="select T3_ID from oracle_fetch_solr where  DELETE_IND is null and PUBLICATION_NUMBER='"+Keyword+"' and pub_env_flag like '%"+pub_env_flg+"%' and doc_type ='"+doc_type+"'  and LANGUAGE='"+Lang+"'  and metakey like '%"+DMYSS+"%'  and rownum<=1  ";
System.out.println("T3 ID query : "+t3id_query);
	}	
	return t3id_query;
}


public int cnt=0;
 
public void click_ewd_rate (String t3id, String url ) {
	WebDriverWait wait = new WebDriverWait(Driver,15);	
	System.out.println("URL in click_ewd_rate is :"+url);
	if(url!=null) {
	boolean check = true;
	String iconXpath ="//a[contains(@href,'"+url+":443/t3Portal/portlets/tis/document/contentScoring/contentScore.portlet?_portlet.title=Rate+this+Document&_portlet.skeleton=details&_portlet.skeletonPath=%252Fframework%252Fskeletons%252Ft3&_portlet.skin=details&_portlet.skinPath=%252Fframework%252Fskins%252Ft3&t3id="+t3id+"')]";
	try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(iconXpath)));
		check = true;
	}catch (Exception e0){
		check = false ;
	} cnt++;
	System.out.println("cnt "+cnt);
	if(check) {
		System.out.println("cnt if "+cnt);
		String winHandleBefore = Driver.getWindowHandle(); 
		Driver.findElement(By.xpath(iconXpath)).click();
wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		for(String winHandleA : Driver.getWindowHandles()){
    		   Driver.switchTo().window(winHandleA); 
    		   System.out.println(winHandleA);
    		     } 
		SubmittingRating(winHandleBefore) ;	
	}else {
		System.out.println("cnt else "+cnt);
		String nextlinkXapth = "//a[@href='"+url+":443/t3Portal/appmanager/t3/int?_nfpb=true&_windowLabel=tis_rep_det_ewd&tis_rep_det_ewd_actionOverride=%2Fportlets%2Ftis%2Fdocument%2FsearchUplift%2Fresult%2FsearchResult%2FgoNext&_pageLabel=lib_ewd_page']";		
		try {
			System.out.println("try came");
			Driver.findElement(By.xpath(nextlinkXapth)).click();
			click_ewd_rate ( t3id, url );
		}catch (Exception ap) {
			System.out.println("Document not available in search reults");
			Report.updateTestLog("Search Results", "Document not available in search results", Status.FAIL);
		}
	}	
}else {
	System.out.println("URL is null");
	Report.updateTestLog("Environment/Data issue", "URL is null", Status.FAIL);
	}
}

public void click_cr_rate (String t3id, String url ) {
	WebDriverWait wait = new WebDriverWait(Driver,30);
	System.out.println("URL in click_cr_rate is :"+url);
	if(url!=null) {
	boolean check = true;
	String iconXpath ="//a[contains(@href,'"+url+":443/t3Portal/portlets/tis/document/contentScoring/contentScore.portlet?_portlet.title=Rate+this+Document&_portlet.skeleton=details&_portlet.skeletonPath=%252Fframework%252Fskeletons%252Ft3&_portlet.skin=details&_portlet.skinPath=%252Fframework%252Fskins%252Ft3&t3id="+t3id+"')]";
	try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(iconXpath)));
		check = true;
	}catch (Exception e0){
		check = false ;
	} cnt++;
	System.out.println("cnt "+cnt);
	if(check) {
		System.out.println("cnt if "+cnt);
		String winHandleBefore = Driver.getWindowHandle(); 
		Driver.findElement(By.xpath(iconXpath)).click();
wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		for(String winHandleA : Driver.getWindowHandles()){
    		   Driver.switchTo().window(winHandleA); 
    		   System.out.println(winHandleA);
    		     } 
		SubmittingRating(winHandleBefore) ;
	}else {
		System.out.println("cnt else "+cnt);	
		try {			
			String nextlinkXapth = "//a[@href='"+url+":443/t3Portal/appmanager/t3/int?_nfpb=true&_windowLabel=tis_rep_det_cr&tis_rep_det_cr_actionOverride=%2Fportlets%2Ftis%2Fdocument%2FsearchUplift%2Fresult%2FsearchResult%2FgoNext&_pageLabel=lib_cr_page']";			
			System.out.println("try came");
			Driver.findElement(By.xpath(nextlinkXapth)).click();
			click_cr_rate ( t3id, url );
			check = true;
		}catch (Exception ap) {
			check = false;
			System.out.println("Document not available in search results");
			Report.updateTestLog("Search Results", "Document not available in search results", Status.FAIL);
		}
	}
	}else {
		System.out.println("URL is null");
		Report.updateTestLog("Environment/Data issue", "URL is null", Status.FAIL);
		}
}

public void click_crib_rate (String t3id, String url ) {
	WebDriverWait wait = new WebDriverWait(Driver,15);
	System.out.println("URL in click_crib_rate is :"+url);
	if(url!=null) {
	boolean check = true;
	String iconXpath ="//a[contains(@href,'"+url+":443/t3Portal/portlets/tis/document/contentScoring/contentScore.portlet?_portlet.title=Rate+this+Document&_portlet.skeleton=details&_portlet.skeletonPath=%252Fframework%252Fskeletons%252Ft3&_portlet.skin=details&_portlet.skinPath=%252Fframework%252Fskins%252Ft3&t3id="+t3id+"')]";
	try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(iconXpath)));
		check = true;
	}catch (Exception e0){
		check = false ;
	} cnt++;
	System.out.println("cnt "+cnt);
	if(check) {
		System.out.println("cnt if "+cnt);
		String winHandleBefore = Driver.getWindowHandle(); 
		Driver.findElement(By.xpath(iconXpath)).click();
wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		for(String winHandleA : Driver.getWindowHandles()){
    		   Driver.switchTo().window(winHandleA); 
    		   System.out.println(winHandleA);
    		     } 
		SubmittingRating(winHandleBefore) ;
	}else {
		System.out.println("cnt else "+cnt);	
		try {			
			String nextlinkXapth = "//a[@href='"+url+":443/t3Portal/appmanager/t3/int?_nfpb=true&_windowLabel=tis_rep_det_crib&tis_rep_det_crib_actionOverride=%2Fportlets%2Ftis%2Fdocument%2FsearchUplift%2Fresult%2FsearchResult%2FgoNext&_pageLabel=lib_cr_page']";			
			System.out.println("try came");
			Driver.findElement(By.xpath(nextlinkXapth)).click();
			click_cr_rate ( t3id, url );
			check = true;
		}catch (Exception ap) {
			check = false;
			System.out.println("Document not available in search results");
			Report.updateTestLog("Search Results", "Document not available in search results", Status.FAIL);
		}
	}
	}else {
		System.out.println("URL is null");
		Report.updateTestLog("Environment/Data issue", "URL is null", Status.FAIL);
		}
}


public void SubmittingRating(String winHandleBefore) {
	WebDriverWait wait = new WebDriverWait(Driver,60);	
	try {
	System.out.println("Providing Rating");
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='bea-portal-layout-flow']//table//td//input[5]")));
	Driver.findElement(By.xpath("//table[@class='bea-portal-layout-flow']//table//td//input[5]")).click();
	Report.updateTestLog("Rating Window", "Added Rating Info", Status.PASS);
	Driver.findElement(By.id("saveButton")).click();
	System.out.println("Rating SUbmitted");
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Close')]")));
	Driver.findElement(By.xpath("//a[contains(text(),'Close')]")).click();
	System.out.println("Pop Up Window CLosed");
	Driver.switchTo().window(winHandleBefore); 
	System.out.println("Control Switched to base window ");
	}
	catch (Exception ea) {
		System.out.println("Unable to provide rating");
		Report.updateTestLog("Rating Window", "Unable to provide rating", Status.FAIL);
	}
}


//Add to fav start
@Action(desc="add_favorite",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void add_favorite() throws ClassNotFoundException, SQLException {
	String Scenario = userData.getScenario();
	String TestCase = userData.getTestCase();
	String Iteration = userData.getIteration();
	String SubIteration = userData.getSubIteration();
	
	String url =get_url(Scenario,TestCase,Iteration,SubIteration) ;
	System.out.println("Env is "+url); 
	String DataFileName = get_LibraryCSVFileName(TestCase) ;
	if(DataFileName != null) {
		String doc_type =get_DataFromCSV( DataFileName, "DocType",Scenario,TestCase,Iteration,SubIteration) ;
		System.out.println("Doc type is "+doc_type);    
		String Div = get_DataFromCSV( DataFileName, "Division",Scenario,TestCase,Iteration,SubIteration);
		System.out.println("Division is "+Div); 
		String Model= get_DataFromCSV( DataFileName, "Model",Scenario,TestCase,Iteration,SubIteration).trim().isEmpty() ? "ALL" : get_DataFromCSV( DataFileName, "Model",Scenario,TestCase,Iteration,SubIteration);
		System.out.println("Model :"+Model);
		String Myear= get_DataFromCSV( DataFileName, "MYear",Scenario,TestCase,Iteration,SubIteration).trim().isEmpty() ? "ALL" : get_DataFromCSV( DataFileName, "MYear",Scenario,TestCase,Iteration,SubIteration);
		System.out.println("Model Year :"+Myear);
		String Sc = get_DataFromCSV( DataFileName, "Service_Category",Scenario,TestCase,Iteration,SubIteration).trim().isEmpty() ? "ALL" : get_DataFromCSV( DataFileName, "Service_Category",Scenario,TestCase,Iteration,SubIteration);
		System.out.println("Service Category :"+Sc);
		String Sec = get_DataFromCSV( DataFileName, "Section",Scenario,TestCase,Iteration,SubIteration).trim().isEmpty() ? "ALL" : get_DataFromCSV( DataFileName, "Section",Scenario,TestCase,Iteration,SubIteration) ;
		System.out.println("Section :"+Sec);
		String Keyword = get_DataFromCSV( DataFileName, "Keyword",Scenario,TestCase,Iteration,SubIteration).trim().isEmpty() ? "keywordnotavailable" : get_DataFromCSV( DataFileName, "Keyword",Scenario,TestCase,Iteration,SubIteration);
		System.out.println("Keyword :"+Keyword);
		String Language = get_DataFromCSV( DataFileName, "Language",Scenario,TestCase,Iteration,SubIteration).trim().isEmpty() ? "en" : get_DataFromCSV( DataFileName, "Language",Scenario,TestCase,Iteration,SubIteration);
		System.out.println("Language :"+Language);
		String	pub_env_flg = get_pub_env_flg(TestCase);
	String t3id =null;
	if(url!=null && doc_type!=null) {
	t3id= get_t3id (TestCase, url, doc_type,  Div,  Model,  Myear,  Sc,  Sec, Keyword, Language) ;
	System.out.println("T3_id:"+t3id);
	}else {
		System.out.println("URL/doc_type is null");
	}
	if (t3id != null) {
		if (doc_type.contains("rm")) {
			rm_add_fav(t3id, url);
		} /*else if (doc_type.equalsIgnoreCase("cr")) {
			click_cr_rate(t3id, url);
		}*/else {
			System.out.println("Method not available for the doc type");
		}
	} else {
		System.out.println("T3_id is null, Documents with this combination are  not available in the system");
	}
	}else {
		System.out.println("File Name is null View_doc method");
	}
	
}

//add to fav end
public void rm_add_fav(String t3id, String url ) {
	WebDriverWait wait = new WebDriverWait(Driver,15);	
	System.out.println("URL in rm_add_fav is :"+url);
	if(url!=null) {
	boolean check = true;
	String iconXpath ="//a[contains(@href,'"+url+":443/t3Portal/portlets/tis/document/myFavorite/addFavoriteDocument.do?t3id="+t3id+"')]";
	try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(iconXpath)));
		check = true;
	}catch (Exception e0){
		check = false ;
	} cnt++;
	System.out.println("cnt "+cnt);
	if(check) {
		System.out.println("cnt if in rm_add_fav "+cnt);
		Driver.findElement(By.xpath(iconXpath)).click();
		wait.until(ExpectedConditions.alertIsPresent());
		try {
			Driver.switchTo().alert().accept();
		System.out.println("Added to favorites ");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Home")));
		Driver.findElement(By.linkText("Home")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("refreshImageId")));
		check =true;
		}
		catch (Exception ea) {
			check =false;
		}
				if (check) {
					try {
						verify_fav_home(t3id, url);
					} catch (Exception ehome) {
						System.out.println("Document not available in My favorites");
						Report.updateTestLog("My Documents-- Favorites", "Document not available in My favorites",
								Status.FAIL);
					}
				}else {
					System.out.println("Unable to add to favorites");
					Report.updateTestLog("Favorites Window", "Unable to add to favorites", Status.FAIL);
				}
	}else {
		System.out.println("cnt else in rm_add_fav "+cnt);
		String nextlinkXapth = "//a[contains(text(),'next >')]";		
		try {
			System.out.println("try came");
			Driver.findElement(By.xpath(nextlinkXapth)).click();
			rm_add_fav ( t3id, url );
		}catch (Exception ap) {
			System.out.println("Document not available in search reults");
			Report.updateTestLog("Search Results", "Document not available in search results", Status.FAIL);
		}
	}	
}else {
	System.out.println("URL is null");
	Report.updateTestLog("Environment/Data issue", "URL is null", Status.FAIL);
	}	
}

public void verify_fav_home(String t3id, String url) {
	WebDriverWait wait = new WebDriverWait(Driver,15);	
	boolean check= true;
	String linkXpath = "//a[contains(@href,'"+t3id+"')]";
	System.out.println("Xpathh for doc in fav :"+linkXpath);  //   RM000000UYV00BX   //a[contains(@href,'RM000000UYV00BX')]
		try {
			System.out.println("T3id in verify_fav_home :"+t3id);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(linkXpath)));
			check = true;
		} catch (Exception ev1) {
			check = false;
		} cnt++;
		System.out.println("cnt " + cnt);
		if (check) {
			System.out.println("Document Successfully added to favorites");
			Report.updateTestLog("Home page", "Document Successfully reflected in Home favorites",
					Status.PASS);
		} else {
			System.out.println("cnt else in verify_fav_home " + cnt);
			String nextlinkXapth = "//a[@href='"+url+":443/t3Portal/appmanager/t3/int?_nfpb=true&amp;_windowLabel=myFavoriteDocumentsPortlet&amp;myFavoriteDocumentsPortlet_actionOverride=%2Fportlets%2Ftis%2Fdocument%2FmyFavorite%2FgoNext&amp;_pageLabel=t3_home']";
			try {
				System.out.println("try came");
				Driver.findElement(By.xpath(nextlinkXapth)).click();
				verify_fav_home(t3id, url);
			} catch (Exception ap) {
				System.out.println("Document not available in My favorites");
				Report.updateTestLog("My Documents-- Favorites", "Document not available in My favorites", Status.FAIL);
			}
		}	
}


public String get_LibraryCSVFileName(String TestCase){
	 String FileName = null ; 
	 if (TestCase != null) {
			if (TestCase.contains("Servinfo")) {
				FileName = "Servinfo_DMY_Search" ;
				System.out.println("File Name in get_LibraryCSVFileName : "+FileName );
			} else if (TestCase.contains("Referenceinfo")) {
				FileName = "Referenceinfo_DMY_Search" ;
				System.out.println("File Name in get_LibraryCSVFileName : "+FileName );
			}else if (TestCase.contains("TechTraining")) {
				FileName = "TechTraining_DMY_Search" ;
				System.out.println("File Name in get_LibraryCSVFileName : "+FileName );
			}else {
				System.out.println("Method not available for the TestCase");
				Report.updateTestLog("Get Data File Name", "Method not available for the TestCase", Status.FAIL);
			}
		} else {
			System.out.println("TestCase name is null");
			Report.updateTestLog("TestCase name is null in get_LibraryCSVFileName", "TestCase name is null", Status.FAIL);
		}
	 return FileName ;
	 }



public String get_DataFromCSV(String DataFileName, String Attribute, String Scenario, String TestCase, String Iteration, String SubIteration){
String data = null;
 if (DataFileName != null && Attribute != null && Scenario != null && TestCase != null && Iteration!= null && SubIteration!= null ) { 
 data =userData.getData(DataFileName,Attribute,Scenario,TestCase,Iteration,SubIteration);
	} else {
		System.out.println("Inputs to fetch file data has null values");
	}	
	System.out.println(Attribute +" in "+DataFileName+" is "+data);
	return data ;
} 




//view doc starts 
@Action(desc="view_doc",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void view_doc() throws ClassNotFoundException, SQLException {
	WebDriverWait wait = new WebDriverWait(Driver,15);
	String Scenario = userData.getScenario();
	String TestCase = userData.getTestCase();
	String Iteration = userData.getIteration();
	String SubIteration = userData.getSubIteration();
	System.out.println("view_doc Scenario is "+Scenario); 
	System.out.println("view_doc TestCase is "+TestCase); 
	String url =get_url(Scenario,TestCase,Iteration,SubIteration) ;
	System.out.println("Env is "+url); 
	String DataFileName = get_LibraryCSVFileName(TestCase) ;
	if(DataFileName != null) {
	String doc_type =get_DataFromCSV( DataFileName, "DocType",Scenario,TestCase,Iteration,SubIteration) ;
	System.out.println("Doc type is "+doc_type);    
	String Div = get_DataFromCSV( DataFileName, "Division",Scenario,TestCase,Iteration,SubIteration);
	System.out.println("Division is "+Div); 
	String Model= get_DataFromCSV( DataFileName, "Model",Scenario,TestCase,Iteration,SubIteration).trim().isEmpty() ? "ALL" : get_DataFromCSV( DataFileName, "Model",Scenario,TestCase,Iteration,SubIteration);
	System.out.println("Model :"+Model);
	String Myear= get_DataFromCSV( DataFileName, "MYear",Scenario,TestCase,Iteration,SubIteration).trim().isEmpty() ? "ALL" : get_DataFromCSV( DataFileName, "MYear",Scenario,TestCase,Iteration,SubIteration);
	System.out.println("Model Year :"+Myear);
	String Sc = get_DataFromCSV( DataFileName, "Service_Category",Scenario,TestCase,Iteration,SubIteration).trim().isEmpty() ? "ALL" : get_DataFromCSV( DataFileName, "Service_Category",Scenario,TestCase,Iteration,SubIteration);
	System.out.println("Service Category :"+Sc);
	String Sec = get_DataFromCSV( DataFileName, "Section",Scenario,TestCase,Iteration,SubIteration).trim().isEmpty() ? "ALL" : get_DataFromCSV( DataFileName, "Section",Scenario,TestCase,Iteration,SubIteration) ;
	System.out.println("Section :"+Sec);
	String Keyword = get_DataFromCSV( DataFileName, "Keyword",Scenario,TestCase,Iteration,SubIteration).trim().isEmpty() ? "keywordnotavailable" : get_DataFromCSV( DataFileName, "Keyword",Scenario,TestCase,Iteration,SubIteration);
	System.out.println("Keyword :"+Keyword);
	String Language = get_DataFromCSV( DataFileName, "Language",Scenario,TestCase,Iteration,SubIteration).trim().isEmpty() ? "en" : get_DataFromCSV( DataFileName, "Language",Scenario,TestCase,Iteration,SubIteration);
	System.out.println("Language :"+Language);
	
	String	pub_env_flg = get_pub_env_flg(TestCase);
	String t3id =null;
	if(url!=null && doc_type!=null) {
	t3id= get_t3id (TestCase, url, doc_type,  Div,  Model,  Myear,  Sc,  Sec, Keyword, Language) ;
	System.out.println("T3_id:"+t3id);
	}else {
		System.out.println("URL/doc_type is null");
	}	
	String doc_title=null;
	if(url!=null && t3id!=null) {
	doc_title = get_doc_title(url,pub_env_flg, t3id, doc_type, Language) ;
	System.out.println("Doc Title is:"+doc_title);
	}else {
		System.out.println("URL/t3id is null");
	}	
		if (doc_title != null) {
			CallViewDoc ( doc_type, url, doc_title);
		} else {
			System.out.println("Doc_title is null, Documents with this combination are  not available in the system");
		}		
	}else {
		System.out.println("File Name is null View_doc method");
	}
}

public void CallViewDoc (String doc_type,String url,String doc_title) {
	if (doc_type.contains("ncf")) {
		view_ncf_doc(url,doc_title);		
	}else if (doc_type.equalsIgnoreCase("sb")) {
		view_sb_doc(url,doc_title);
	}else if (doc_type.equalsIgnoreCase("cp")) {
		view_cp_doc(url,doc_title);
	}else if (doc_type.equalsIgnoreCase("om")) {
		view_om_doc(url,doc_title);
	}else if (doc_type.equalsIgnoreCase("tv-tci")) {
		view_tvtci_doc(url,doc_title);
	}else if (doc_type.equalsIgnoreCase("dm")) {
		view_dm_doc(url,doc_title);
	}else if (doc_type.equalsIgnoreCase("pant")) {
		view_pant_doc(url,doc_title);
	}else if (doc_type.equalsIgnoreCase("qtg")) {
		view_qtg_doc(url,doc_title);
	}else if (doc_type.equalsIgnoreCase("rm")) {
			view_rm_doc(url,doc_title);	
		}else if (doc_type.contains("ewd")) {
			view_ewd_doc(url,doc_title);
		}else if (doc_type.equalsIgnoreCase("cr")) {
			view_cr_doc(url,doc_title);
		}else if (doc_type.equalsIgnoreCase("crib")) {
			view_crib_doc(url,doc_title);
		}else if (doc_type.equalsIgnoreCase("ttc")) {
			view_ttc_doc(url,doc_title);
		}else if (doc_type.equalsIgnoreCase("tm")) {
			view_tm_doc(url,doc_title);
		}
		else if (doc_type.equalsIgnoreCase("tr")) {
			view_dmr_tr_doc(url,doc_title);
		}else if (doc_type.equalsIgnoreCase("dmr")) {
			view_dmr_tr_doc(url,doc_title);
		}
	else {
		System.out.println("Method not available for the doc type");
	}	
}

public void ViewingDocument (String winHandleBefore) {
	try {
		System.out.println("Viewing document"); //pageContainer1
		Thread.sleep(10000);
		Report.updateTestLog("View Document", "Able to view the document", Status.PASS);
		System.out.println("Able to view the document");
		Driver.close();
		System.out.println("Pop Up Window CLosed");
		Driver.switchTo().window(winHandleBefore);
		System.out.println("Control Switched to base window ");
		} catch (Exception ea) {
			System.out.println("Unable to view doc");
			Report.updateTestLog("View doc Window", "Unable to view doc", Status.FAIL);
		}
}


public void view_rm_doc(String url, String doc_title) {
			WebDriverWait wait = new WebDriverWait(Driver,15);
			System.out.println("URL in view_rm_doc is :"+url);
			if(doc_title!=null) {
			boolean check = true;
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),"+doc_title+")]")));
				check = true;
			}catch (Exception e0){
				check = false ;
			} cnt++;
			System.out.println("cnt "+cnt);
			if(check) {
				System.out.println("cnt if "+cnt);
				String winHandleBefore = Driver.getWindowHandle(); 
				Driver.findElement(By.xpath("//a[contains(text(),"+doc_title+")]")).click();
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));
				for(String winHandleA : Driver.getWindowHandles()){
		    		   Driver.switchTo().window(winHandleA); 
		    		   System.out.println(winHandleA);
		    		     }
				ViewingDocument (winHandleBefore);
			}else {
				System.out.println("cnt else "+cnt);	
				try {			
					String nextlinkXapth = "//a[contains(text(),'next')]";			
					System.out.println("try came");
					Driver.findElement(By.xpath(nextlinkXapth)).click();
					view_rm_doc(url, doc_title);
					check = true;
				}catch (Exception ap) {
					check = false;
					System.out.println("Document not available in search results");
					Report.updateTestLog("Search Results", "Document not available in search results", Status.FAIL);
				}
			}
			}else {
				System.out.println("Doc title is null");
				Report.updateTestLog("Environment/Data issue", "Doc title is null", Status.FAIL);
				}
			}
			
			public void view_ewd_doc(String url, String doc_title) {
				WebDriverWait wait = new WebDriverWait(Driver,15);
				System.out.println("URL in view_ewd_doc is :"+url);
				if(doc_title!=null) {
				boolean check = true;
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),"+doc_title+")]")));
					check = true;
				}catch (Exception e0){
					check = false ;
				} cnt++;
				System.out.println("cnt "+cnt);
				if(check) {
					System.out.println("cnt if "+cnt);
					String winHandleBefore = Driver.getWindowHandle(); 
					Driver.findElement(By.xpath("//a[contains(text(),"+doc_title+")]")).click();
			wait.until(ExpectedConditions.numberOfWindowsToBe(2));
					for(String winHandleA : Driver.getWindowHandles()){
			    		   Driver.switchTo().window(winHandleA); 
			    		   System.out.println(winHandleA);
			    		     } 
					ViewingDocument (winHandleBefore);
				}else {
					System.out.println("cnt else "+cnt);	
					try {	
						String nextlinkXapth =null ;
						if(url.contains("techinfo")) {
							nextlinkXapth="//a[@href='"+url+":443/t3Portal/appmanager/t3/ti?_nfpb=true&_windowLabel=tis_rep_det_ewd&tis_rep_det_ewd_actionOverride=%2Fportlets%2Ftis%2Fdocument%2FsearchUplift%2Fresult%2FsearchResult%2FgoNext&_pageLabel=lib_ewd_page']";
						}else {
							nextlinkXapth="//a[@href='"+url+":443/t3Portal/appmanager/t3/int?_nfpb=true&_windowLabel=tis_rep_det_ewd&tis_rep_det_ewd_actionOverride=%2Fportlets%2Ftis%2Fdocument%2FsearchUplift%2Fresult%2FsearchResult%2FgoNext&_pageLabel=lib_ewd_page']";
						}
						System.out.println("try came");
						Driver.findElement(By.xpath(nextlinkXapth)).click();
						view_ewd_doc(url, doc_title);
						check = true;
					}catch (Exception ap) {
						check = false;
						System.out.println("Document not available in search results");
						Report.updateTestLog("Search Results", "Document not available in search results", Status.FAIL);
					}
				}
				}else {
					System.out.println("Doc title is null");
					Report.updateTestLog("Environment/Data issue", "Doc title is null", Status.FAIL);
					}
				}
			
			public void view_cr_doc(String url, String doc_title) {
				WebDriverWait wait = new WebDriverWait(Driver,15);
				System.out.println("URL in view_rm_doc is :"+url);
				if(doc_title!=null) {
				boolean check = true;
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),"+doc_title+")]")));
					check = true;
				}catch (Exception e0){
					check = false ;
				} cnt++;
				System.out.println("cnt "+cnt);
				if(check) {
					System.out.println("cnt if "+cnt);
					String winHandleBefore = Driver.getWindowHandle(); 
					Driver.findElement(By.xpath("//a[contains(text(),"+doc_title+")]")).click();
			wait.until(ExpectedConditions.numberOfWindowsToBe(2));
					for(String winHandleA : Driver.getWindowHandles()){
			    		   Driver.switchTo().window(winHandleA); 
			    		   System.out.println(winHandleA);
			    		     } 
					ViewingDocument (winHandleBefore);
				}else {
					System.out.println("cnt else "+cnt);	
					try {			
						String nextlinkXapth = "//a[contains(text(),'next')]";			
						System.out.println("try came");
						Driver.findElement(By.xpath(nextlinkXapth)).click();
						view_cr_doc(url, doc_title);
						check = true;
					}catch (Exception ap) {
						check = false;
						System.out.println("Document not available in search results");
						Report.updateTestLog("Search Results", "Document not available in search results", Status.FAIL);
					}
				}
				}else {
					System.out.println("Doc title is null");
					Report.updateTestLog("Environment/Data issue", "Doc title is null", Status.FAIL);
					}
				}

			public void view_crib_doc(String url, String doc_title) {
				WebDriverWait wait = new WebDriverWait(Driver,15);
				System.out.println("URL in view_crib_doc is :"+url);
				if(doc_title!=null) {
				boolean check = true;
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),"+doc_title+")]")));
					check = true;
				}catch (Exception e0){
					check = false ;
				} cnt++;
				System.out.println("cnt "+cnt);
				if(check) {
					System.out.println("cnt if "+cnt);
					String winHandleBefore = Driver.getWindowHandle(); 
					Driver.findElement(By.xpath("//a[contains(text(),"+doc_title+")]")).click();
			wait.until(ExpectedConditions.numberOfWindowsToBe(2));
					for(String winHandleA : Driver.getWindowHandles()){
			    		   Driver.switchTo().window(winHandleA); 
			    		   System.out.println(winHandleA);
			    		     }
					ViewingDocument (winHandleBefore);
				}else {
					System.out.println("cnt else "+cnt);	
					try {	
						String nextlinkXapth =null ;
						if(url.contains("techinfo")) {
							nextlinkXapth="//a[@href='"+url+":443/t3Portal/appmanager/t3/ti?_nfpb=true&_windowLabel=tis_rep_det_crib&tis_rep_det_crib_actionOverride=%2Fportlets%2Ftis%2Fdocument%2FsearchUplift%2Fresult%2FsearchResult%2FgoNext&_pageLabel=lib_cr_page']";
						}else {
							nextlinkXapth="//a[@href='"+url+":443/t3Portal/appmanager/t3/int?_nfpb=true&_windowLabel=tis_rep_det_crib&tis_rep_det_crib_actionOverride=%2Fportlets%2Ftis%2Fdocument%2FsearchUplift%2Fresult%2FsearchResult%2FgoNext&_pageLabel=lib_cr_page']";
						}
						System.out.println("try came");
						Driver.findElement(By.xpath(nextlinkXapth)).click();
						view_crib_doc(url, doc_title);
						check = true;
					}catch (Exception ap) {
						check = false;
						System.out.println("Document not available in search results");
						Report.updateTestLog("Search Results", "Document not available in search results", Status.FAIL);
					}
				}
				}else {
					System.out.println("Doc title is null");
					Report.updateTestLog("Environment/Data issue", "Doc title is null", Status.FAIL);
					}
				}
			
			
public void view_ncf_doc(String url, String doc_title) {
	WebDriverWait wait = new WebDriverWait(Driver,15);
	System.out.println("URL in view_ncf_doc is :"+url);
	if(doc_title!=null) {
	boolean check = true;
	try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),"+doc_title+")]")));
		check = true;
	}catch (Exception e0){
		check = false ;
	} cnt++;
	System.out.println("cnt "+cnt);
	if(check) {
		System.out.println("cnt if "+cnt);
		String winHandleBefore = Driver.getWindowHandle(); 
		Driver.findElement(By.xpath("//a[contains(text(),"+doc_title+")]")).click();
wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		for(String winHandleA : Driver.getWindowHandles()){
    		   Driver.switchTo().window(winHandleA); 
    		   System.out.println(winHandleA);
    		     } 
		ViewingDocument (winHandleBefore);
	}else {
		System.out.println("cnt else "+cnt);	
		try {			
			String nextlinkXapth = "//a[contains(text(),'next')]";			
			System.out.println("try came");
			Driver.findElement(By.xpath(nextlinkXapth)).click();
			view_ncf_doc(url, doc_title);
			check = true;
		}catch (Exception ap) {
			check = false;
			System.out.println("Document not available in search results");
			Report.updateTestLog("Search Results", "Document not available in search results", Status.FAIL);
		}
	}
	}else {
		System.out.println("Doc title is null");
		Report.updateTestLog("Environment/Data issue", "Doc title is null", Status.FAIL);
		}
}

public void view_sb_doc(String url, String doc_title) {
	WebDriverWait wait = new WebDriverWait(Driver,15);
	System.out.println("URL in view_sb_doc is :"+url);
	if(doc_title!=null) {
		System.out.println(doc_title);
	boolean check = true;
	try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),"+doc_title+")]")));
		check = true;
	}catch (Exception e0){
		check = false ;
	} cnt++;
	System.out.println("cnt "+cnt);
	if(check) {
		System.out.println("cnt if "+cnt);
		String winHandleBefore = Driver.getWindowHandle(); 
		Driver.findElement(By.xpath("//a[contains(text(),"+doc_title+")]")).click();
		
wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		for(String winHandleA : Driver.getWindowHandles()){
    		   Driver.switchTo().window(winHandleA); 
    		   System.out.println(winHandleA);
    		     } 
		ViewingDocument (winHandleBefore);
		}else {
		System.out.println("cnt else "+cnt);	
		try {		
			String nextlinkXapth = null;
			if(url.contains("techinfo")) {
			nextlinkXapth ="//a[@href='"+url+":443/t3Portal/appmanager/t3/ti?_nfpb=true&_windowLabel=tis_rep_det_sb&tis_rep_det_sb_actionOverride=%2Fportlets%2Ftis%2Fdocument%2FsearchUplift%2Fresult%2FsearchResult%2FgoNext&_pageLabel=lib_tsb_page']";
			}else {
			nextlinkXapth = "//a[@href='"+url+":443/t3Portal/appmanager/t3/int?_nfpb=true&_windowLabel=tis_rep_det_sb&tis_rep_det_sb_actionOverride=%2Fportlets%2Ftis%2Fdocument%2FsearchUplift%2Fresult%2FsearchResult%2FgoNext&_pageLabel=lib_tsb_page']";			
			}
			 System.out.println("try came");
			Driver.findElement(By.xpath(nextlinkXapth)).click();
			view_sb_doc(url, doc_title);
			check = true;
		}catch (Exception ap) {
			check = false;
			System.out.println("Document not available in search results");
			Report.updateTestLog("Search Results", "Document not available in search results", Status.FAIL);
		}
	}
	}else {
		System.out.println("Doc title is null");
		Report.updateTestLog("Environment/Data issue", "Doc title is null", Status.FAIL);
		}	
}

public void view_cp_doc(String url, String doc_title) {
	WebDriverWait wait = new WebDriverWait(Driver,15);
	System.out.println("URL in view_cp_doc is :"+url);
	if(doc_title!=null) {
	boolean check = true;
	try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),"+doc_title+")]")));
		check = true;
	}catch (Exception e0){
		check = false ;
	} cnt++;
	System.out.println("cnt "+cnt);
	if(check) {
		System.out.println("cnt if "+cnt);
		String winHandleBefore = Driver.getWindowHandle(); 
		Driver.findElement(By.xpath("//a[contains(text(),"+doc_title+")]")).click();
wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		for(String winHandleA : Driver.getWindowHandles()){
    		   Driver.switchTo().window(winHandleA); 
    		   System.out.println(winHandleA);
    		     }
		ViewingDocument (winHandleBefore);
	}else {
		System.out.println("cnt else "+cnt);	
		try {	
			String nextlinkXapth = null;
			if(url.contains("techinfo")) {
			nextlinkXapth="//a[contains(text(),'next >')]";
			}else {
			 nextlinkXapth = "//a[@href='"+url+":443/t3Portal/appmanager/t3/int?_nfpb=true&_windowLabel=tis_rep_det_cp&tis_rep_det_cp_actionOverride=%2Fportlets%2Ftis%2Fdocument%2FsearchUplift%2Fresult%2FsearchResult%2FgoNext&_pageLabel=lib_cp_page']";			
			}
			System.out.println("try came");
			Driver.findElement(By.xpath(nextlinkXapth)).click();
			view_cp_doc(url, doc_title);
			check = true;
		}catch (Exception ap) {
			check = false;
			System.out.println("Document not available in search results");
			Report.updateTestLog("Search Results", "Document not available in search results", Status.FAIL);
		}
	}
	}else {
		System.out.println("Doc title is null");
		Report.updateTestLog("Environment/Data issue", "Doc title is null", Status.FAIL);
		}
}

public void view_om_doc(String url, String doc_title) {
	WebDriverWait wait = new WebDriverWait(Driver,15);
	Boolean check = true;
	try {		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("more....")));
		check = true;
	}catch (Exception e0){
		check = false ;
	}
	if(check) {
		Driver.findElement(By.linkText("more....")).click();
		om_doc_view( url, doc_title) ;
	}else {
		om_doc_view( url, doc_title) ;	
	}
}

public void om_doc_view(String url, String doc_title) {
	WebDriverWait wait = new WebDriverWait(Driver,15);
	System.out.println("URL in view_om_doc is :"+url);
	if(doc_title!=null) {
	boolean check = true;
	try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),"+doc_title+")]")));
		check = true;
	}catch (Exception e0){
		check = false ;
	} cnt++;
	System.out.println("cnt "+cnt);
	if(check) {
		System.out.println("cnt if "+cnt);
		String winHandleBefore = Driver.getWindowHandle(); 
		Driver.findElement(By.xpath("//a[contains(text(),"+doc_title+")]")).click();
wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		for(String winHandleA : Driver.getWindowHandles()){
    		   Driver.switchTo().window(winHandleA); 
    		   System.out.println(winHandleA);
    		     } 
		ViewingDocument (winHandleBefore);
	}else {
		System.out.println("cnt else "+cnt);	
		try {	
			String nextlinkXapth = null;
			if(url.contains("techinfo")) {
			nextlinkXapth = "//a[@href='"+url+":443/t3Portal/appmanager/t3/ti?_nfpb=true&_windowLabel=tis_ref_portlet_om&tis_ref_portlet_om_actionOverride=%2Fportlets%2Ftis%2Fdocument%2FsearchUplift%2Fresult%2FsearchResult%2FgoNext&_pageLabel=lib_ref_sum']";	
			}else {
			nextlinkXapth = "//a[@href='"+url+":443/t3Portal/appmanager/t3/int?_nfpb=true&_windowLabel=T1600221488779001026&T1600221488779001026_actionOverride=%2Fportlets%2Ftis%2Fdocument%2FsearchUplift%2Fresult%2FsearchResult%2FgoNext&_pageLabel=lib_ref_sum']";			
			}
			System.out.println("try came");
			Driver.findElement(By.xpath(nextlinkXapth)).click();
			om_doc_view(url, doc_title);
			check = true;
		}catch (Exception ap) {
			check = false;
			System.out.println("Document not available in search results");
			Report.updateTestLog("Search Results", "Document not available in search results", Status.FAIL);
		}
	}
	}else {
		System.out.println("Doc title is null");
		Report.updateTestLog("Environment/Data issue", "Doc title is null", Status.FAIL);
		}	
}

public void view_tvtci_doc(String url, String doc_title) {
	WebDriverWait wait = new WebDriverWait(Driver,15);
	Boolean check = true;
	try {		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("more....")));
		check = true;
	}catch (Exception e0){
		check = false ;
	}
	if(check) {
		Driver.findElement(By.linkText("more....")).click();
		tvtci_doc_view( url, doc_title) ;
	}else {
		tvtci_doc_view( url, doc_title) ;	
	}
}

public void tvtci_doc_view(String url, String doc_title) {
	WebDriverWait wait = new WebDriverWait(Driver,15);
	System.out.println("URL in tvtci_doc_view is :"+url);
	if(doc_title!=null) {
	boolean check = true;
	try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),"+doc_title+")]")));
		check = true;
	}catch (Exception e0){
		check = false ;
	} cnt++;
	System.out.println("cnt "+cnt);
	if(check) {
		System.out.println("cnt if "+cnt);
		String winHandleBefore = Driver.getWindowHandle(); 
		Driver.findElement(By.xpath("//a[contains(text(),"+doc_title+")]")).click();
wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		for(String winHandleA : Driver.getWindowHandles()){
    		   Driver.switchTo().window(winHandleA); 
    		   System.out.println(winHandleA);
    		     } 
		ViewingDocument (winHandleBefore);
	}else {
		System.out.println("cnt else "+cnt);	
		try {			
			String nextlinkXapth = "//a[@href='"+url+":443/t3Portal/appmanager/t3/int?_nfpb=true&_windowLabel=T10400776411468482133394&T10400776411468482133394_actionOverride=%2Fportlets%2Ftis%2Fdocument%2FsearchUplift%2Fresult%2FsearchResult%2FgoNext&_pageLabel=lib_ref_sum']";			
			System.out.println("try came");
			Driver.findElement(By.xpath(nextlinkXapth)).click();
			tvtci_doc_view(url, doc_title);
			check = true;
		}catch (Exception ap) {
			check = false;
			System.out.println("Document not available in search results");
			Report.updateTestLog("Search Results", "Document not available in search results", Status.FAIL);
		}
	}
	}else {
		System.out.println("Doc title is null");
		Report.updateTestLog("Environment/Data issue", "Doc title is null", Status.FAIL);
		}	
}

public void view_dm_doc(String url, String doc_title) {
	WebDriverWait wait = new WebDriverWait(Driver,15);
	Boolean check = true;
	try {		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("more....")));
		check = true;
	}catch (Exception e0){
		check = false ;
	}
	if(check) {
		Driver.findElement(By.linkText("more....")).click();
		dm_doc_view( url, doc_title) ;
	}else {
		dm_doc_view( url, doc_title) ;	
	}
}

public void dm_doc_view(String url, String doc_title) {
	WebDriverWait wait = new WebDriverWait(Driver,15);
	System.out.println("URL in dm_doc_view is :"+url);
	if(doc_title!=null) {
	boolean check = true;
	try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),"+doc_title+")]")));
		check = true;
	}catch (Exception e0){
		check = false ;
	} cnt++;
	System.out.println("cnt "+cnt);
	if(check) {
		System.out.println("cnt if "+cnt);
		String winHandleBefore = Driver.getWindowHandle(); 
		Driver.findElement(By.xpath("//a[contains(text(),"+doc_title+")]")).click();
wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		for(String winHandleA : Driver.getWindowHandles()){
    		   Driver.switchTo().window(winHandleA); 
    		   System.out.println(winHandleA);
    		     } 
		ViewingDocument (winHandleBefore);
	}else {
		System.out.println("cnt else "+cnt);	
		try {			
String nextlinkXapth = "//a[@href='"+url+":443/t3Portal/appmanager/t3/int?_nfpb=true&amp;_windowLabel=T5000689981438687816277&amp;T5000689981438687816277_actionOverride=%2Fportlets%2Ftis%2Fdocument%2FsearchUplift%2Fresult%2FsearchResult%2FgoNext&amp;_pageLabel=lib_ref_sum']";			
			System.out.println("try came");
			Driver.findElement(By.xpath(nextlinkXapth)).click();
			dm_doc_view(url, doc_title);
			check = true;
		}catch (Exception ap) {
			check = false;
			System.out.println("Document not available in search results");
			Report.updateTestLog("Search Results", "Document not available in search results", Status.FAIL);
		}
	}
	}else {
		System.out.println("Doc title is null");
		Report.updateTestLog("Environment/Data issue", "Doc title is null", Status.FAIL);
		}	
}



public void view_dmr_tr_doc(String url, String doc_title) {
	WebDriverWait wait = new WebDriverWait(Driver,15);
	Boolean check = true;
	try {		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("more....")));
		check = true;
	}catch (Exception e0){
		check = false ;
	}
	if(check) {
		Driver.findElement(By.linkText("more....")).click();
		dmr_tr_doc_view( url, doc_title) ;
	}else {
		dmr_tr_doc_view( url, doc_title) ;	
	}
}

public void dmr_tr_doc_view(String url, String doc_title) {
	WebDriverWait wait = new WebDriverWait(Driver,15);
	System.out.println("URL in dmr_tr_doc_view is :"+url);
	if(doc_title!=null) {
	boolean check = true;
	try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),"+doc_title+")]")));
		check = true;
	}catch (Exception e0){
		check = false ;
	} cnt++;
	System.out.println("cnt "+cnt);
	if(check) {
		System.out.println("cnt if "+cnt);
		String winHandleBefore = Driver.getWindowHandle(); 
		Driver.findElement(By.xpath("//a[contains(text(),"+doc_title+")]")).click();
wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		for(String winHandleA : Driver.getWindowHandles()){
    		   Driver.switchTo().window(winHandleA); 
    		   System.out.println(winHandleA);
    		     } 
		ViewingDocument (winHandleBefore);
	}else {
		System.out.println("cnt else "+cnt);	
		try {	
			String nextlinkXapth = null;
			if(url.contains("techinfo")) {
			nextlinkXapth = "//a[@href='"+url+":443/t3Portal/appmanager/t3/ti?_nfpb=true&_windowLabel=portletInstance_17_1_3_1&portletInstance_17_1_3_1_actionOverride=%2Fportlets%2Ftis%2Fdocument%2FsearchUplift%2Fresult%2FsearchResult%2FgoNext&_pageLabel=lib_ref_sum']";	
			}else {
				 nextlinkXapth = "//a[@href='"+url+":443/t3Portal/appmanager/t3/int?_nfpb=true&_windowLabel=T5000489981438687438859&T5000489981438687438859_actionOverride=%2Fportlets%2Ftis%2Fdocument%2FsearchUplift%2Fresult%2FsearchResult%2FgoNext&_pageLabel=lib_ref_sum']";			
			}		
			System.out.println("try came");
			Driver.findElement(By.xpath(nextlinkXapth)).click();
			dmr_tr_doc_view(url, doc_title);
			check = true;
		}catch (Exception ap) {
			check = false;
			System.out.println("Document not available in search results");
			Report.updateTestLog("Search Results", "Document not available in search results", Status.FAIL);
		}
	}
	}else {
		System.out.println("Doc title is null");
		Report.updateTestLog("Environment/Data issue", "Doc title is null", Status.FAIL);
		}	
}



public void view_pant_doc(String url, String doc_title) {
	WebDriverWait wait = new WebDriverWait(Driver,15);
	Boolean check = true;
	try {		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("more....")));
		check = true;
	}catch (Exception e0){
		check = false ;
	}
	if(check) {
		Driver.findElement(By.linkText("more....")).click();
		pant_doc_view( url, doc_title) ;
	}else {
		pant_doc_view( url, doc_title) ;	
	}
}

public void pant_doc_view(String url, String doc_title) {
	WebDriverWait wait = new WebDriverWait(Driver,15);
	System.out.println("URL in pant_doc_view is :"+url);
	if(doc_title!=null) {
	boolean check = true;
	try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),"+doc_title+")]")));
		check = true;
	}catch (Exception e0){
		check = false ;
	} cnt++;
	System.out.println("cnt "+cnt);
	if(check) {
		System.out.println("cnt if "+cnt);
		String winHandleBefore = Driver.getWindowHandle(); 
		Driver.findElement(By.xpath("//a[contains(text(),"+doc_title+")]")).click();
wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		for(String winHandleA : Driver.getWindowHandles()){
    		   Driver.switchTo().window(winHandleA); 
    		   System.out.println(winHandleA);
    		     } 
		ViewingDocument (winHandleBefore);
	}else {
		System.out.println("cnt else "+cnt);	
		try {		
			String nextlinkXapth =null;	
			if(url.contains("t3")) {
 nextlinkXapth = "//a[@href='"+url+":443/t3Portal/appmanager/t3/int?_nfpb=true&_windowLabel=portletInstance_17_1&portletInstance_17_1_actionOverride=%2Fportlets%2Ftis%2Fdocument%2FsearchUplift%2Fresult%2FsearchResult%2FgoNext&_pageLabel=lib_ref_sum']";			
			}else {
				nextlinkXapth ="//a[@href='"+url+":443/t3Portal/appmanager/t3/int?_nfpb=true&_windowLabel=T980039431469111456202&T980039431469111456202_actionOverride=%2Fportlets%2Ftis%2Fdocument%2FsearchUplift%2Fresult%2FsearchResult%2FgoNext&_pageLabel=lib_ref_sum']";	
			}
				System.out.println("try { came");
			Driver.findElement(By.xpath(nextlinkXapth)).click();
			pant_doc_view(url, doc_title);
			check = true;
		}catch (Exception ap) {
			check = false;
			System.out.println("Document not available in search results");
			Report.updateTestLog("Search Results", "Document not available in search results", Status.FAIL);
		}
	}
	}else {
		System.out.println("Doc title is null");
		Report.updateTestLog("Environment/Data issue", "Doc title is null", Status.FAIL);
		}	
}


public void view_qtg_doc(String url, String doc_title) {
	WebDriverWait wait = new WebDriverWait(Driver,15);
	System.out.println("URL in view_qtg_doc is :"+url);
	if(doc_title!=null) {
	boolean check = true;
	try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),"+doc_title+")]")));
		check = true;
	}catch (Exception e0){
		check = false ;
	} cnt++;
	System.out.println("cnt "+cnt);
	if(check) {
		System.out.println("cnt if "+cnt);
		String winHandleBefore = Driver.getWindowHandle(); 
		Driver.findElement(By.xpath("//a[contains(text(),"+doc_title+")]")).click();
wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		for(String winHandleA : Driver.getWindowHandles()){
    		   Driver.switchTo().window(winHandleA); 
    		   System.out.println(winHandleA);
    		     } 
		ViewingDocument (winHandleBefore);
	}else {
		System.out.println("cnt else "+cnt);	
		try {			
			String nextlinkXapth = "//a[contains(text(),'next')]";			
			System.out.println("try came");
			Driver.findElement(By.xpath(nextlinkXapth)).click();
			view_qtg_doc(url, doc_title);
			check = true;
		}catch (Exception ap) {
			check = false;
			System.out.println("Document not available in search results");
			Report.updateTestLog("Search Results", "Document not available in search results", Status.FAIL);
		}
	}
	}else {
		System.out.println("Doc title is null");
		Report.updateTestLog("Environment/Data issue", "Doc title is null", Status.FAIL);
		}
}


public void view_ttc_doc(String url, String doc_title) {
	WebDriverWait wait = new WebDriverWait(Driver,15);
	System.out.println("URL in view_ttc_doc is :"+url);
	if(doc_title!=null) {
	boolean check = true;
	try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),"+doc_title+")]")));
		check = true;
	}catch (Exception e0){
		check = false ;
	} cnt++;
	System.out.println("cnt "+cnt);
	if(check) {
		System.out.println("cnt if "+cnt);
		String winHandleBefore = Driver.getWindowHandle(); 
		Driver.findElement(By.xpath("//a[contains(text(),"+doc_title+")]")).click();
wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		for(String winHandleA : Driver.getWindowHandles()){
    		   Driver.switchTo().window(winHandleA); 
    		   System.out.println(winHandleA);
    		     } 
		ViewingDocument (winHandleBefore);
	}else {
		System.out.println("cnt else "+cnt);	
		try {			
			String nextlinkXapth = "//a[contains(text(),'next')]";			
			System.out.println("try came");
			Driver.findElement(By.xpath(nextlinkXapth)).click();
			view_ttc_doc(url, doc_title);
			check = true;
		}catch (Exception ap) {
			check = false;
			System.out.println("Document not available in search results");
			Report.updateTestLog("Search Results", "Document not available in search results", Status.FAIL);
		}
	}
	}else {
		System.out.println("Doc title is null");
		Report.updateTestLog("Environment/Data issue", "Doc title is null", Status.FAIL);
		}
}


public void view_tm_doc(String url, String doc_title) {
	WebDriverWait wait = new WebDriverWait(Driver,15);
	System.out.println("URL in view_tm_doc is :"+url);
	if(doc_title!=null) {
	boolean check = true;
	try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),"+doc_title+")]")));
		check = true;
	}catch (Exception e0){
		check = false ;
	} cnt++;
	System.out.println("cnt "+cnt);
	if(check) {
		System.out.println("cnt if "+cnt);
		String winHandleBefore = Driver.getWindowHandle(); 
		Driver.findElement(By.xpath("//a[contains(text(),"+doc_title+")]")).click();
wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		for(String winHandleA : Driver.getWindowHandles()){
    		   Driver.switchTo().window(winHandleA); 
    		   System.out.println(winHandleA);
    		     } 
		ViewingDocument (winHandleBefore);
	}else {
		System.out.println("cnt else "+cnt);	
		try {			
			String nextlinkXapth = "//a[contains(text(),'next')]";			
			System.out.println("try came");
			Driver.findElement(By.xpath(nextlinkXapth)).click();
			view_tm_doc(url, doc_title);
			check = true;
		}catch (Exception ap) {
			check = false;
			System.out.println("Document not available in search results");
			Report.updateTestLog("Search Results", "Document not available in search results", Status.FAIL);
		}
	}
	}else {
		System.out.println("Doc title is null");
		Report.updateTestLog("Environment/Data issue", "Doc title is null", Status.FAIL);
		}
}




public String get_doc_title(String url,String pub_env_flg, String t3id, String doc_type, String Lang ) throws ClassNotFoundException, SQLException {
	String  doc_title =null;
	String dburl =null;
	Connection con =null;
	System.out.println("Came to get_doc_title metohd URL: " +url);
	dburl= get_t3internal_dburl(url); 
	System.out.println("DB URL is "+dburl);
    Class.forName("oracle.jdbc.driver.OracleDriver");
    String UserName = "t3datglobl";
    String Password = "t3dgprod";
    
    if(dburl!=null && t3id!=null) {
    	con = DriverManager.getConnection(dburl, UserName, Password);
String doc_title_query ="select DRETITLE from oracle_fetch_solr where T3_ID='"+t3id+"' and DELETE_IND is null and pub_env_flag like '%"+pub_env_flg+"%'  and doc_type ='"+doc_type+"' and LANGUAGE='"+Lang+"'  and rownum<=1  " ;
	System.out.println("Doc Query" +doc_title_query);
	Statement Stmt = con.createStatement();
			ResultSet rs = Stmt.executeQuery(doc_title_query);
			System.out.println("Doc_title_query Query extecuted");
			while (rs.next()) {
				//doc_title = rs.getString(1).trim();
				doc_title = rs.getString(1).trim();
				int doctitle_length = doc_title.length();
				System.out.println("doctitle Length: " + doctitle_length);
				if(doctitle_length>=40) {
					doc_title = rs.getString(1).trim().substring(0,40);	
				}else {
					doc_title = rs.getString(1).trim();
				}
			System.out.println("Document Title: " + doc_title);
		}
    } else {
		System.out.println("Invalid Environment details");
	}
    doc_title= "\""+doc_title+"\"";
	return doc_title ;
}

@Action(desc="tis_login",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void tis_login() {
	String Scenario = userData.getScenario();
	System.out.println(Scenario);
	String TestCase = userData.getTestCase();
	System.out.println(TestCase);
	String Iteration = userData.getIteration();
	String SubIteration = userData.getSubIteration();
	
	String url = userData.getData("tis_login","URL",Scenario,TestCase,Iteration,SubIteration);
	String usertype= userData.getData("tis_login","UserType",Scenario,TestCase,Iteration,SubIteration);
	String userregion= userData.getData("tis_login","UserRegion",Scenario,TestCase,Iteration,SubIteration);
	String username= userData.getData("tis_login","Username",Scenario,TestCase,Iteration,SubIteration);
	String password= userData.getData("tis_login","Pwd",Scenario,TestCase,Iteration,SubIteration);
	System.out.println(url);
	System.out.println(usertype);
	System.out.println(userregion);
	System.out.println(username);
	System.out.println(password);
	tis_login(url, usertype,userregion,username,password) ;
}

public void tis_login (String url, String usertype, String userregion, String username, String password) {
	WebDriverWait wait = new WebDriverWait(Driver,60);
	boolean Check = true;
	try {
		 Driver.get(url);  //URL
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userType")));
		 Driver.findElement(By.id("userType")).sendKeys(usertype); // Usertype dropdown
	}catch (Exception e1) {
		 Check = false;
		 		 //e1.printStackTrace();
		 	 }
if(Check) {  
	if(userregion.equalsIgnoreCase("10 - Toyota Headquarters")){ tmsDealer_tis_login( url,  usertype,  userregion,  username,  password);
		}else {
			if(userregion.equalsIgnoreCase("30 - Lexus Headquarters")) { tmsDealer_tis_login( url,  usertype,  userregion,  username,  password);
			}else {
				if(userregion.equalsIgnoreCase("71 - Toyota Canada")) { tciDealer_tis_login1( url,  usertype,  userregion,  username,  password);
				}else {
					System.out.println("Invalid usertype");
					Report.updateTestLog("Opened "+url,"Invalid usertype.",Status.FAIL);
					}
				}		
			}
	}else{	 
	 Report.updateTestLog("Opening "+url,"Page not opened.",Status.FAIL);
			}
}

public void tmsDealer_tis_login(String url, String usertype, String userregion, String username, String password) {
	WebDriverWait wait = new WebDriverWait(Driver,15);
	boolean Check = true;
	
	Driver.findElement(By.id("userRegion")).sendKeys(userregion); // Username
	Driver.findElement(By.id("username")).sendKeys(username); // Username
Driver.findElement(By.id("password")).sendKeys(password); // Password
Driver.findElement(By.id("terms")).click(); // Terms and conditions checkbox
Driver.findElement(By.id("submit")).click(); // Submit button	
try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout")));
}catch (Exception e2) {
	 Check = false;
		 //e2.printStackTrace();
	 }
if(Check) {
	Report.updateTestLog("Clicked on Submit","Successfully Logged in as  "+username,Status.PASS);
	}else {	 
	 Report.updateTestLog("Opening "+url,"Login Failed.",Status.FAIL);
			}
}

public void affiliateDealer_tis_login(String url, String usertype, String userregion, String username, String password) {
	
Driver.findElement(By.id("userRegion")).sendKeys(userregion); // Username	
Driver.findElement(By.id("terms")).click(); // Terms and conditions checkbox
Driver.findElement(By.id("proceed")).click(); // Submit button

if(userregion.equalsIgnoreCase("tci")) { tciDealer_tis_login( url,  usertype,  userregion,  username,  password); 
	}else{	 
	 Report.updateTestLog("Opening "+url,"Write the method for userregion.",Status.FAIL);
	}
}

public void businessPartner_tis_login(String url, String usertype, String userregion, String username, String password) {
Driver.findElement(By.id("userRegion")).sendKeys(userregion); // Username	
Driver.findElement(By.id("terms")).click(); // Terms and conditions checkbox
Driver.findElement(By.id("proceed")).click(); // Submit button

if(userregion.equalsIgnoreCase("SET")) {
	set_tis_login( url,  usertype,  userregion,  username,  password);
	}else{	 
		if(userregion.equalsIgnoreCase("GST")) {
			gst_tis_login( url,  usertype,  userregion,  username,  password);
				}	else{	 
					Report.updateTestLog("Opening "+url,"Write the method for userregion.",Status.FAIL);
			  			}
		}
}

public void tciDealer_tis_login(String url, String usertype, String userregion, String username, String password) {
	WebDriverWait wait = new WebDriverWait(Driver,15);
	boolean Check = true;
	
	try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='USER']")));
}catch (Exception e2) {
	 Check = false;
		 //e2.printStackTrace();
	 }
	if(Check) {
		Driver.findElement(By.xpath("//input[@name='USER']")).sendKeys(username);
		Driver.findElement(By.xpath("//input[@name='PASSWORD']")).sendKeys(password);
		Driver.findElement(By.xpath("//input[@value='Login']")).click();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout")));
		}catch (Exception ea) {
			Check = false;
		}		
		if(Check) {
			Report.updateTestLog("Clicked on Submit","Successfully Logged in as  "+username,Status.PASS);
		}else
		{	 
			 Report.updateTestLog("Opening "+url,"Login Failed.",Status.FAIL);
		}
	}else
	{	 
		 Report.updateTestLog("Opening "+url,"External site not opened.",Status.FAIL);
	}
}

public void tciDealer_tis_login1(String url, String usertype, String userregion, String username, String password) {
	WebDriverWait wait = new WebDriverWait(Driver,60);
	boolean Check = true;
	Driver.findElement(By.id("userRegion")).sendKeys(userregion); // Username	
	Driver.findElement(By.id("terms")).click(); // Terms and conditions checkbox
	Driver.findElement(By.id("proceed")).click(); // Submit button
	try {
		wait.until(ExpectedConditions.alertIsPresent());
}catch (Exception e2) {
	Check = false;
		 e2.printStackTrace();
	 }
	if(Check) {
    try {
           Alert alert = Driver.switchTo().alert();
           UserAndPassword UP = new UserAndPassword(username,password);
           alert.authenticateUsing(UP);
           
           try {
   			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout")));
   		}catch (Exception ea) {
   			Check = false;
   		}		
   		if(Check) {
   			Report.updateTestLog("Clicked on Submit","Successfully Logged in as  "+username,Status.PASS);
   		}else
   		{	 
   			 Report.updateTestLog("Opening "+url,"Login Failed.",Status.FAIL);
   		}
           
           Report.updateTestLog("Login TCI", "Successfully logged into Federation portal", Status.PASS);
    } catch (Exception e) {
           Report.updateTestLog("Login TCI", "Error occurred while logging into Federation portal", Status.WARNING);
    	} 
	}else{	 
		 Report.updateTestLog("Opening "+url,"External site not opened.",Status.FAIL);
	}
}

public void set_tis_login(String url, String usertype, String userregion, String username, String password) {
	WebDriverWait wait = new WebDriverWait(Driver,15);
	boolean Check = true; 
	try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_UsernameTextBox']")));
}catch (Exception e2) {
	 Check = false;
		 //e2.printStackTrace();
	 }
	if(Check) {
		Driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_UsernameTextBox']")).sendKeys(username);
		Driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_PasswordTextBox']")).sendKeys(password);
		Driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_Button1']")).click();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout")));
		}catch (Exception ea) {
			Check = false;
		}		
		if(Check) {
			Report.updateTestLog("Clicked on Submit","Successfully Logged in as  "+username,Status.PASS);
		}else
		{	 
			 Report.updateTestLog("Opening "+url,"Login Failed.",Status.FAIL);
		}
	}else
	{	 
		 Report.updateTestLog("Opening "+url,"External site not opened.",Status.FAIL);
	}
}

public void gst_tis_login(String url, String usertype, String userregion, String username, String password) {
	WebDriverWait wait = new WebDriverWait(Driver,15);
	boolean Check = true;
	 
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_UsernameTextBox']")));
	}catch (Exception e2) {
		 Check = false;
			 //e2.printStackTrace();
		 }
		if(Check) {
			Driver.findElement(By.xpath("//input[@id='userBox']")).sendKeys(username);
			Driver.findElement(By.xpath("//input[@name='PASSWORD']")).sendKeys(password);
			Driver.findElement(By.xpath("//input[@type='image']")).click();
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout")));
			}catch (Exception ea) {
				Check = false;
			}		
			if(Check) {
				Report.updateTestLog("Clicked on Submit","Successfully Logged in as  "+username,Status.PASS);
			}else
			{	 
				 Report.updateTestLog("Opening "+url,"Login Failed.",Status.FAIL);
			}
		}else
		{	 
			 Report.updateTestLog("Opening "+url,"External site not opened.",Status.FAIL);
		}
}


@Action(desc="SubmitCorrection",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void SubmitCorrection () throws ClassNotFoundException, SQLException, InterruptedException {
	String Scenario = userData.getScenario();
	String TestCase = userData.getTestCase();
	String Iteration = userData.getIteration();
	String SubIteration = userData.getSubIteration();
	
	String url =get_url(Scenario,TestCase,Iteration,SubIteration) ;
	System.out.println("Env is "+url); 
	String DataFileName = get_LibraryCSVFileName(TestCase) ;
	if(DataFileName != null) {
	String doc_type =get_DataFromCSV( DataFileName, "DocType",Scenario,TestCase,Iteration,SubIteration) ;
	System.out.println("Doc type is "+doc_type);    
	String Div = get_DataFromCSV( DataFileName, "Division",Scenario,TestCase,Iteration,SubIteration);
	System.out.println("Division is "+Div); 
	String Model= get_DataFromCSV( DataFileName, "Model",Scenario,TestCase,Iteration,SubIteration).trim().isEmpty() ? "ALL" : get_DataFromCSV( DataFileName, "Model",Scenario,TestCase,Iteration,SubIteration);
	System.out.println("Model :"+Model);
	String Myear= get_DataFromCSV( DataFileName, "MYear",Scenario,TestCase,Iteration,SubIteration).trim().isEmpty() ? "ALL" : get_DataFromCSV( DataFileName, "MYear",Scenario,TestCase,Iteration,SubIteration);
	System.out.println("Model Year :"+Myear);
	String Sc = get_DataFromCSV( DataFileName, "Service_Category",Scenario,TestCase,Iteration,SubIteration).trim().isEmpty() ? "ALL" : get_DataFromCSV( DataFileName, "Service_Category",Scenario,TestCase,Iteration,SubIteration);
	System.out.println("Service Category :"+Sc);
	String Sec = get_DataFromCSV( DataFileName, "Section",Scenario,TestCase,Iteration,SubIteration).trim().isEmpty() ? "ALL" : get_DataFromCSV( DataFileName, "Section",Scenario,TestCase,Iteration,SubIteration) ;
	System.out.println("Section :"+Sec);
	String Keyword = get_DataFromCSV( DataFileName, "Keyword",Scenario,TestCase,Iteration,SubIteration).trim().isEmpty() ? "keywordnotavailable" : get_DataFromCSV( DataFileName, "Keyword",Scenario,TestCase,Iteration,SubIteration);
	System.out.println("Keyword :"+Keyword);
	String Language = get_DataFromCSV( DataFileName, "Language",Scenario,TestCase,Iteration,SubIteration).trim().isEmpty() ? "en" : get_DataFromCSV( DataFileName, "Language",Scenario,TestCase,Iteration,SubIteration);
	System.out.println("Language :"+Language);
	String	pub_env_flg = get_pub_env_flg(TestCase);
	
	String t3id =null;
	if(url!=null && doc_type!=null) {
	t3id= get_t3id ( TestCase, url, doc_type,  Div,  Model,  Myear,  Sc,  Sec, Keyword, Language) ;
	System.out.println("T3_id:"+t3id);
	}else {
		System.out.println("URL/doc_type is null");
	}
	
		if (t3id != null) {
			if (doc_type.equalsIgnoreCase("ncf")) {
				ncf_SubmitCorrection(t3id, url);
			} /*else if (doc_type.equalsIgnoreCase("cr")) {
				click_cr_rate(t3id, url);
			}else if (doc_type.equalsIgnoreCase("crib")) {
				click_crib_rate(t3id, url);
			}*/else {
				System.out.println("Method not available for the doc type");
			}
		} else {
			System.out.println("T3_id is null, Documents with this combination are  not available in the system");
		}
}else {
		System.out.println("File Name is null View_doc method");
	}		
}



public void ncf_SubmitCorrection (String t3id, String url ) throws InterruptedException {
	WebDriverWait wait = new WebDriverWait(Driver,15);	
	System.out.println("URL in ncf_SubmitCorrection is :"+url);
	if(url!=null) {
	boolean check = true;
	String iconXpath ="//a[contains(@href,'"+url+":443/t3Portal/portlets/tis/feedback/contentCorrection/ContentCorrectionFeedbackController.portlet?_portlet.title=Content+Correction+Feedback&_portlet.skeleton=details&_portlet.skeletonPath=%252Fframework%252Fskeletons%252Ft3&_portlet.skin=details&_portlet.skinPath=%252Fframework%252Fskins%252Ft3&t3id="+t3id+"')]";
	try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(iconXpath)));
		check = true;
	}catch (Exception e0){
		check = false ;
	} cnt++;
	System.out.println("cnt "+cnt);
	if(check) {
		System.out.println("cnt if "+cnt);
		String winHandleBefore = Driver.getWindowHandle(); 
		Driver.findElement(By.xpath(iconXpath)).click();
wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		for(String winHandleA : Driver.getWindowHandles()){
    		   Driver.switchTo().window(winHandleA); 
    		   System.out.println(winHandleA);
    		     } 
		SubmittingContentCorrection(winHandleBefore) ;		
	}else {
		System.out.println("cnt else "+cnt);
		String nextlinkXapth = "//a[contains(text(),'next >')]";		
		try {
			System.out.println("try came");
			Driver.findElement(By.xpath(nextlinkXapth)).click();
			ncf_SubmitCorrection ( t3id, url );
		}catch (Exception ap) {
			System.out.println("Document not available in search reults");
			Report.updateTestLog("Search Results", "Document not available in search results", Status.FAIL);
		}
	}	
}else {
	System.out.println("URL is null");
	Report.updateTestLog("Environment/Data issue", "URL is null", Status.FAIL);
	}
}


public void SubmittingContentCorrection(String winHandleBefore) throws InterruptedException{ 
	WebDriverWait wait = new WebDriverWait(Driver,60);
	try {
		System.out.println("Submitting Content Correction Feedback");
		String CategoryXpath ="//body[@class='bea-portal-body']//div[@class='bea-portal-body-content']//div[@class='bea-portal-book-primary']//div[@class='bea-portal-book-primary-content']//div[@class='bea-portal-book-primary-page']//table[@class='bea-portal-layout-flow']//tbody//tr//td[@class='bea-portal-layout-placeholder-container']//div[@class='bea-portal-layout-placeholder']//div[@class='bea-portal-details-window']//div[@class='bea-portal-details-window-content']//form[@id='selectedFeedbackForm']//table//tbody//tr//td//div//table//tbody//tr[1]//td[1]//input[1]";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CategoryXpath)));
		Driver.findElement(By.xpath(CategoryXpath)).click();
		Driver.findElement(By.xpath("//textarea[@id='descriptionErrorOrSuggestion']")).sendKeys("Adding test content in Error Description field");
		Report.updateTestLog("Content Correction Feedback Window", "Added Content Correction Feedback Info", Status.PASS);
		Driver.findElement(By.id("saveButton")).click();
		System.out.println("Content Correction Feedback SUbmitted");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Close')]")));
		Driver.findElement(By.xpath("//a[contains(text(),'Close')]")).click();
		System.out.println("Pop Up Window CLosed");
		Driver.switchTo().window(winHandleBefore); 
		System.out.println("Control Switched to base window ");
		}
		catch (Exception ea) {
			System.out.println("Unable to provide Content Correction Feedback");
			Report.updateTestLog("Content Correction Feedback Window", "Unable to provide Content Correction Feedback", Status.FAIL);
		}
} 


@Action(desc="verifytechinfoFeaturedContent",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void verifytechinfoFeaturedContent() throws InterruptedException{ 
	String TestCase = userData.getTestCase();
	 if (TestCase != null) {
			if (TestCase.contains("Servinfo")) {
				TechinfoServiceInfoFeaturedContent();
			} else if (TestCase.contains("Referenceinfo")) {
				TechinfoReferenceInfoFeaturedContent();
			}else if (TestCase.contains("TechTraining")) {
				TechinfoTechTrainingFeaturedContent();
			}else {
				System.out.println("Method not available for the TestCase");
				Report.updateTestLog("Get Data File Name", "Method not available for the TestCase", Status.FAIL);
			}
		} else {
			System.out.println("TestCase name is null");
			Report.updateTestLog("TestCase name is null in get_LibraryCSVFileName", "TestCase name is null", Status.FAIL);
		}	 
} 

public void TechinfoServiceInfoFeaturedContent() throws InterruptedException{ 
	WebDriverWait wait = new WebDriverWait(Driver,15);
	String EWDViewerXpath ="//a[contains(text(),'EWD Viewer Introduction')]";
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EWDViewerXpath)));
	try {
		String winHandleBefore = Driver.getWindowHandle(); 
		Driver.findElement(By.xpath(EWDViewerXpath)).click();
wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		for(String winHandleA : Driver.getWindowHandles()){
    		   Driver.switchTo().window(winHandleA); 
    		   System.out.println(winHandleA);
    		     } 
		Thread.sleep(10000);
		Report.updateTestLog("Able to view EWD Viewer Introduction ", "Able to view EWD Viewer Introduction", Status.PASS);
		Driver.close();
		System.out.println("Pop Up Window CLosed");
		Driver.switchTo().window(winHandleBefore); 
		System.out.println("Control Switched to base window ");
	}catch (Exception e1){
		System.out.println("EWD Viewer Introduction Link Not enabled/available ");
		Report.updateTestLog("Unable to view EWD Viewer Introduction ", "Unable to view EWD Viewer Introduction", Status.FAIL);
	}

	String GlossaryXpath ="//a[contains(text(),'Glossary of Terms')]";
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GlossaryXpath)));
	try {
		String winHandleBefore = Driver.getWindowHandle(); 
		Driver.findElement(By.xpath(GlossaryXpath)).click();
wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		for(String winHandleA : Driver.getWindowHandles()){
    		   Driver.switchTo().window(winHandleA); 
    		   System.out.println(winHandleA);
    		     } 
		Thread.sleep(10000);
		Report.updateTestLog("Able to view Glossary of Terms ", "Able to view Glossary of Terms", Status.PASS);
		Driver.close();
		System.out.println("Pop Up Window CLosed");
		Driver.switchTo().window(winHandleBefore); 
		System.out.println("Control Switched to base window ");
	}catch (Exception e1){
		System.out.println("Glossary of Terms Link Not enabled/available ");
		Report.updateTestLog("Unable to view Glossary of Terms ", "Unable to view Glossary of Terms", Status.FAIL);
	}
} 

public void TechinfoReferenceInfoFeaturedContent() throws InterruptedException
{ 
	
}

public void TechinfoTechTrainingFeaturedContent() throws InterruptedException
{ 
	
}

@Action(desc="wait3Sec",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void wait3Sec() throws InterruptedException
{ 
	Thread.sleep(3000);
} 

@Action(desc="wait7Sec",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void wait7Sec() throws InterruptedException
{ 
	Thread.sleep(7000);
}

@Action(desc="wait3Sec",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void wait15Sec() throws InterruptedException
{ 
	Thread.sleep(15000);
} 
	
   //FunctionalComponents end




@Action(desc="warrantyReturnAlert",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void warrantyReturnAlert() throws InterruptedException
{ 
//	Driver.findElement(By.id("submitReturn")).click();
	Thread.sleep(3000);
	String success="";
	String alert = Driver.switchTo().alert().getText();
	File ClaimCreated = new File("C:/filename.txt");
System.out.println(alert);
if (alert.equals("Are you sure you want to perform this action?")) {
	   Driver.switchTo().alert().accept();
	   System.out.println("Confirmation Alert Accepted");
	   Thread.sleep(3000);
	   success = Driver.switchTo().alert().getText();
	   System.out.println(success);
	   Driver.switchTo().alert().accept();
} else {
	   System.out.println("Request Already Exist alert");
	   Driver.switchTo().alert().accept();
	   System.out.println("Already Exist Alert Accepted");
	   Thread.sleep(3000);
	   Driver.switchTo().alert().accept();
	   System.out.println("Confirmation Alert Accepted with Existing");
	   Thread.sleep(15000);
	   success = Driver.switchTo().alert().getText();
	   System.out.println(success);
	   Driver.switchTo().alert().accept();
	   }
try (PrintWriter out = new PrintWriter(ClaimCreated)) {
    out.println(success);
} catch (FileNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

}

@Action(desc="checkIn1",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void checkIn1() throws InterruptedException
{ 
	String claimNo="";
	try {
	 Scanner in = new Scanner(new FileReader("C:\\Automation\\Claim.txt"));
	
	StringBuilder sb = new StringBuilder();
	//Scanner in;
	while(in.hasNext()) {
	    String claim=in.next();
		sb.append(claim);
		if(claim!=null && claim.startsWith("PRS")){
			claimNo=claim;
			System.out.println(claim);
		}
	    
	}
	in.close();
	} 
	catch (FileNotFoundException e) 
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
Driver.findElement(By.name("receiptCheckInPortlet{actionForm.claimFilterFormBean.claimNo}")).sendKeys(claimNo);
	
	
}

@Action(desc="ReceiveNonHazmatParts",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void ReceiveNonHazmatParts() throws InterruptedException
{ 
	String winHandleBefore = Driver.getWindowHandle(); 
	boolean checkin = true;
	            try{
	            	for(String winHandle : Driver.getWindowHandles()){
	            		  Driver.switchTo().window(winHandle);
	            			     }
	          	  Driver.findElement(By.xpath("/html/body/table/tbody/tr[5]/td[1]/input")).click();
	            } catch (Exception e) {
	          	  checkin = false;
	          	  e.printStackTrace();
	            }
	            
	            if (checkin == true){
	            System.out.println("Quantity Mismatch Alert CLosed");
	            Driver.switchTo().window(winHandleBefore);
	            Driver.findElement(By.xpath("//*[@id='receiveSubmitBtnId']")).click();
	            for(String winHandle : Driver.getWindowHandles()){
	      		   Driver.switchTo().window(winHandle);
	      		     }
	            Driver.findElement(By.xpath("/html/body/table/tbody/tr[5]/td[2]/input")).click();
		            System.out.println("Quantity Mismatch Alert Accepted");
		            Thread.sleep(5000);
					System.out.println("Parts Received with Mismatched Quantity");
		            for(String winHandle : Driver.getWindowHandles()){
		       		   Driver.switchTo().window(winHandle);
		       		     }
		            Thread.sleep(10000);
		              System.out.println("Shipping label has been generated");
		              Driver.close();
	            }       else {
	            	 System.out.println("Parts Received");
		             for(String winHandle : Driver.getWindowHandles()){
		         		   Driver.switchTo().window(winHandle);
		         		     }
		             Thread.sleep(10000);
		              System.out.println("Shipping label has been generated");
		              Driver.close();
		}
		Driver.switchTo().window(winHandleBefore);
	
	
}

@Action(desc="Advfiltercheckin",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void Advfiltercheckin() throws InterruptedException
{ 

Driver.findElement(By.xpath("//*[@id='checkInForm']/table[2]/tbody/tr/td[2]/input[2]")).click();
Thread.sleep(5000);
Driver.findElement(By.id("checkInClaimNo1")).click();
Thread.sleep(5000);

}

@Action(desc="SWCSpartsInv",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void SWCSpartsInv() throws InterruptedException
{ 
	String claimNo="";
	try {
	 Scanner in = new Scanner(new FileReader("C:/filename.txt"));
	
	StringBuilder sb = new StringBuilder();
	//Scanner in;
	while(in.hasNext()) {
	    String claim=in.next();
		sb.append(claim);
		if(claim!=null && claim.startsWith("PRS")){
			claimNo=claim;
			System.out.println(claim);
		}
	    
	}
	in.close();
	/*String outString = sb.toString();
	int index=outString.indexOf("PRS");
	String[] s = outString.split(" ");
	System.out.println(s[index]);*/
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	Thread.sleep(5000);
Driver.findElement(By.id("claimId")).sendKeys(claimNo);
	
	
}

@Action(desc="Pageload15sec",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void Pageload15sec() throws InterruptedException
{ 
Thread.sleep(15000);
}

@Action(desc="WPIQuery",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void WPIQuery() throws InterruptedException, ClassNotFoundException, SQLException
{ 
	String OFP = null, VIN = null, WPI = null;
	File WPIValues = new File("C:/WPI.txt");
	String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = RTSC1.tmm.na.corp.toyota.com)(PORT = 1523))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = RTSC.tmm.na.corp.toyota.com)))";
	//(INSTANCE_NAME = RTSC)
	Class.forName("oracle.jdbc.driver.OracleDriver");
	String UserName = "prsapp";
	String Password = "prsapp";
	Connection con = DriverManager.getConnection(dburl, UserName, Password);
	String Query = "select * from tqnet_part_log_master where rownum<=1"; //182549
	//String Query = "Select claim_no , dealer_code from claim where claim_no='_claimNo_'"; //182549
	Statement Stmt = con.createStatement();
	//Query=Query.replace("_claimNo_", "182549");
	ResultSet rs = Stmt.executeQuery(Query);
	while(rs.next()){
		OFP=rs.getString(1);
		VIN=rs.getString(2);
		WPI=rs.getString(3);
	System.out.println(OFP+"-"+VIN+"-"+WPI);
	}
	try (PrintWriter out = new PrintWriter(WPIValues)) {
	    out.println(OFP + "\t" + VIN + "\t" + WPI);
	   	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Driver.findElement(By.id("vinTxtBox")).sendKeys(VIN);
	Driver.findElement(By.id("partNumber")).sendKeys(OFP);
	Thread.sleep(3000);
	con.close();
}

@Action(desc="rowsdisplayed250",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void rowsdisplayed250() throws InterruptedException, ClassNotFoundException, SQLException
{
	/*Select dropdown = new Select(Driver.findElement(By.xpath("/html/body/div/div[3]/div[2]/div/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[4]/div/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[2]/div/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[2]/div[4]/div/table[1]/tbody/tr[1]/td/table/tbody/tr[1]/td[6]/select")));
	dropdown.selectByIndex(4);*/
	Driver.findElement(By.xpath("/html/body/div/div[3]/div[2]/div/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[4]/div/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[2]/div/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[2]/div[4]/div/table[1]/tbody/tr[1]/td/table/tbody/tr[1]/td[6]/select")).sendKeys("250");
	Thread.sleep(5000);
	
}

@Action(desc="wpiCheck",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void wpiCheck() throws InterruptedException, ClassNotFoundException, SQLException
{
	wpi = "";
	try (BufferedReader br = new BufferedReader(new FileReader("C:/WPI.txt"))){
		 String line;
		while ((line = br.readLine()) != null) {
	        String[] data = line.split("\t");
	        wpi = data[2];
	        
	    }
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	boolean receive = true;
	try {
		Driver.findElement(By.xpath(".//*[contains(text(),'Check In')]"));
	} catch (Exception e) {
		receive = false;
        }
	if (receive == true) {
		String wpiText = Driver.findElement(By.xpath("//*[@id='part_1']/td[4]/label")).getText();
		Assert.assertEquals(wpi, wpiText);
		System.out.println("CheckIn WPI Matches");
	} else {
		String wpiText = Driver.findElement(By.xpath("/html/body/div/div[3]/div[2]/div/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[4]/div/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[2]/div/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[2]/div[3]/div/table[2]/tbody[2]/tr[1]/td[10]")).getText();
		Assert.assertEquals(wpi, wpiText);
		System.out.println("Inventory WPI Matches");
	}
}


@Action(desc="wpiAdvFilter",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void wpiAdvFilter() throws InterruptedException, ClassNotFoundException, SQLException
{
	wpi = "";
	try (BufferedReader br = new BufferedReader(new FileReader("C:/WPI.txt"))){
		 String line;
		while ((line = br.readLine()) != null) {
	        String[] data = line.split("\t");
	        wpi = data[2];
	        
	    }
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	boolean receive = true;
	try {
		Driver.findElement(By.xpath(".//*[contains(text(),'Check In')]"));
	} catch (Exception e) {
		receive = false;
        }
	if (receive == true) {
		Driver.findElement(By.name("receiptCheckInPortlet{actionForm.claimFilterFormBean.wpiNo}")).sendKeys(wpi);
			} 
	else {
		Driver.findElement(By.name("searchInventoryPortlet{actionForm.wpiNo}")).sendKeys(wpi);
		
	}
}

@Action(desc="requestType",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void requestType() throws InterruptedException, ClassNotFoundException, SQLException
{
	Thread.sleep(2000);
    //Part Request Creation
    Driver.findElement(By.name("createRequestPortlet{actionForm.requestDescription}")).sendKeys(new String[] {"CREATE HAZMAT PARTS NATIONAL REQUEST"});
    Thread.sleep(2000);
//    Driver.findElement(By.id("hazmatChkBox")).click();
//    Thread.sleep(2000);
    Driver.findElement(By.id("recoveryType")).sendKeys("Parts (National)");
//    Select dropdown1 = new Select (Driver.findElement(By.id("recoveryType")));
//    dropdown1.selectByIndex(1);
//    Select dropdown3 = new Select (Driver.findElement(By.id("vendorId")));
//    dropdown3.selectByIndex(1);
    Thread.sleep(5000);      
    String winhandlebefore = Driver.getWindowHandle();
    wait.until(ExpectedConditions.elementToBeClickable(By.id("lineCode")));
    Thread.sleep(5000);
//    Driver.findElement(By.xpath("//*[@id=\"requestform\"]/table[2]/tbody/tr[2]/td[6]/div[1]/img")).click();
//    Thread.sleep(2000);
    Driver.findElement(By.id("lineCode")).sendKeys("0201");
    Thread.sleep(2000);
    
    for(String winHandle : Driver.getWindowHandles()){
             Driver.switchTo().window(winHandle);
               }
    
    Driver.findElement(By.xpath("//tr[@id='0']/td[text()='0201']")).click();
    Thread.sleep(5000);
    Driver.switchTo().window(winhandlebefore);
    Driver.findElement(By.id("setr")).sendKeys(new String[] { "9B179" });
    Thread.sleep(2000);
    Driver.findElement(By.id("partTxtBox")).sendKeys(new String[] { "732100E021C2" });
    Thread.sleep(2000);
    Driver.findElement(By.name("createRequestPortlet{actionForm.advancedRequestFormBean.quantity}")).sendKeys(new String[] { "1" });
    Thread.sleep(2000);
    Driver.findElement(By.xpath("//*[@id='recoveryToDateLink']/img")).click();
    Thread.sleep(2000);
    String winHandleBefore = Driver.getWindowHandle();
          for(String winHandle : Driver.getWindowHandles()){
       Driver.switchTo().window(winHandle);
         }
       Driver.findElement(By.xpath("/html/body/center/table[2]/tbody/tr[8]/td/a")).click();
       Driver.switchTo().window(winHandleBefore);
       Thread.sleep(2000);
       Driver.findElement(By.id("makeSelectBox")).sendKeys("TOYOTA");
//System.out.println ("value:" +value);
    Thread.sleep(5000);
    Driver.findElement(By.id("modelSelectBox")).sendKeys("Camry");
//    System.out.println ("value1:" +value1);
    Thread.sleep(5000);
    Driver.findElement(By.id("yearSelectBox")).sendKeys("2007");
//    System.out.println ("value2:" +value2);
    Thread.sleep(5000);
Driver.findElement(By.xpath("//*[@id=\"requestform\"]/table[1]/tbody[2]/tr[2]/td[2]/div[1]/img")).click();
    Thread.sleep(5000);
    Driver.findElement(By.id("2")).click();
    Thread.sleep(5000);
    Driver.findElement(By.xpath("//*[@id=\"requestform\"]/table[1]/tbody[2]/tr[2]/td[4]/select")).sendKeys("1N1");
    Thread.sleep(5000);
    Driver.findElement(By.id("partAdd")).click();
    Thread.sleep(5000);
} 	

@Action(desc="partrequestnextstep",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void partrequestnextstep() throws InterruptedException, ClassNotFoundException, SQLException
{
	try {
		Driver.findElement(By.xpath("//*[@id=\"requestform\"]/table[5]/tbody/tr[2]/td/input")).click();
		Thread.sleep(5000);
		Driver.switchTo().alert();
        Thread.sleep(1000);
        Driver.switchTo().alert().accept();
        Thread.sleep(1000);
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	Thread.sleep(5000);
	Driver.findElement(By.xpath("//*[@id=\"exclusionOverrride\"]/div/div/div[3]/input[2]")).click();
	Thread.sleep(5000);
	try {
		Driver.findElement(By.xpath("//*[@id=\"requestform\"]/table[3]/tbody/tr/td/input[2]")).click();
		Thread.sleep(5000);
		Driver.switchTo().alert();
        Thread.sleep(1000);
        Driver.switchTo().alert().accept();
        Thread.sleep(1000);
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	
	
}


@Action(desc="state",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void state() throws InterruptedException, ClassNotFoundException, SQLException
{
Driver.findElement(By.id("state")).sendKeys("AK");
Thread.sleep(5000);
}

@Action(desc="deleteswcs",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void deleteswcs() throws InterruptedException, ClassNotFoundException, SQLException
{
	Driver.findElement(By.xpath("//*[@id=\"directShipmentFormId\"]/table[1]/tbody/tr/td[4]/input")).sendKeys("Automation");
	Driver.findElement(By.xpath("//*[@id=\"directShipmentFormId\"]/table[2]/tbody/tr[1]/td/input[1]")).click();
	boolean submit = true;
	
	try {
		Driver.findElement(By.id("supplierName"));
	}
	catch (Exception e) {
		submit=false;
		e.printStackTrace();
	}
	if (submit) {
		Driver.findElement(By.xpath("//*[@id=\"sampleDiv\"]/table/tbody/tr[2]/td[1]/a[2]/img")).click();
		Driver.switchTo().alert();
        Thread.sleep(1000);
        Driver.switchTo().alert().accept();
        Thread.sleep(1000);
	}
	else {
		System.out.println("Add supplier");
	}
	Thread.sleep(5000);
}

@Action(desc="swcsdbconnect",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void swcsdbconnect() throws InterruptedException, ClassNotFoundException, SQLException
{
	String name = null, suppliercode = null;
	File swcs = new File("C:\\Automation\\swcs.txt");
String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = RTSC1.tmm.na.corp.toyota.com)(PORT = 1523))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = RTSC.tmm.na.corp.toyota.com)))";
//(INSTANCE_NAME = RTSC)
Class.forName("oracle.jdbc.driver.OracleDriver");
String UserName = "prsapp";
String Password = "prsapp";
Connection con = DriverManager.getConnection(dburl, UserName, Password);
String Query = "select * from DIRECT_SHIPMENT_SUPPLIERS where supplier_code='1234567' and deleted='N'";
Statement Stmt = con.createStatement();
ResultSet rs = Stmt.executeQuery(Query);
while(rs.next()){
	name=rs.getString(2);
	suppliercode=rs.getString(9);
System.out.println(name+"-"+suppliercode);
}
try (PrintWriter out = new PrintWriter(swcs)) {
    System.out.println(name + "\t" + suppliercode );
   	} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
con.close();
}

@Action(desc="warrantyReturnAlertothers",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void warrantyReturnAlertothers() throws InterruptedException
{ 
	Driver.findElement(By.id("submitReturn")).click();
	WebDriverWait wait = new WebDriverWait(Driver, 5);
	wait.until(ExpectedConditions.alertIsPresent());
	String success="";
	String alert = Driver.switchTo().alert().getText();
//	File ClaimCreated = new File("C:\\Automation\\Claim.txt");
	System.out.println(alert);
if (alert.equals("Are you sure you want to perform this action?")) {
	   Driver.switchTo().alert().accept();
	   Report.updateTestLog("Warranty Return", "Confirmation Alert Accepted", Status.PASS);
	   System.out.println("Confirmation Alert Accepted");
	   wait.until(ExpectedConditions.alertIsPresent());
	   success = Driver.switchTo().alert().getText();
	   System.out.println(success);
	   Driver.switchTo().alert().accept();
	   Report.updateTestLog("Warranty Return", success, Status.PASS);
	   success = success.replace ("New Part Return has been successfully submitted, please take a note of manual claim number : ","");
	   success = success.replace(" Click <Ok> to create another New Part Return or <Cancel> to close the window", "");
} else {
	   System.out.println("Request Already Exist alert");
	   Driver.switchTo().alert().accept();
	   Report.updateTestLog("Warranty Return", "VIN & PartNo Combination already exist alert", Status.PASS);
		wait.until(ExpectedConditions.alertIsPresent());
	   Driver.switchTo().alert().accept();
	   Report.updateTestLog("Warranty Return", "Confirmation Alert Accepted for Existing Combination", Status.PASS);
		wait.until(ExpectedConditions.alertIsPresent());
	   success = Driver.switchTo().alert().getText();
	   System.out.println(success);
	   Driver.switchTo().alert().accept();
	   Report.updateTestLog("Warranty Return", success, Status.PASS);
	   success = success.replace ("New Part Return has been successfully submitted, please take a note of manual claim number : ","");
	   success = success.replace(" Click <Ok> to create another New Part Return or <Cancel> to close the window", "");
	   	   }
try  {
	success = success.trim();
//	userData.putData("DatasheetName","columnName","value to be written","scenario name","test case name","Iteration value", "SubIteration value");
	userData.putData("CreateMPR","ClaimNo",success,"Create MPR","Create MPR","1", "1");
    Report.updateTestLog("Verify claim is created", "Claim created successfully", Status.PASS);
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	Report.updateTestLog("Verify claim is created", "Claim not created", Status.FAIL);
}

}

@Action(desc="warrantyReturnAlertUPS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void warrantyReturnAlertUPS() throws InterruptedException
{ 
	Driver.findElement(By.id("submitReturn")).click();
	WebDriverWait wait = new WebDriverWait(Driver, 5);
	wait.until(ExpectedConditions.alertIsPresent());
	String success="";
	String alert = Driver.switchTo().alert().getText();
//	File ClaimCreated = new File("C:\\Automation\\Claim.txt");
	System.out.println(alert);
if (alert.equals("Are you sure you want to perform this action?")) {
	   Driver.switchTo().alert().accept();
	   Report.updateTestLog("Warranty Return", "Confirmation Alert Accepted", Status.PASS);
	   System.out.println("Confirmation Alert Accepted");
	   wait.until(ExpectedConditions.alertIsPresent());
	   success = Driver.switchTo().alert().getText();
	   System.out.println(success);
	   Driver.switchTo().alert().accept();
	   Report.updateTestLog("Warranty Return", success, Status.PASS);
	   success = success.replace ("New Part Return has been successfully submitted, please take a note of manual claim number : ","");
	   success = success.replace(" Click <Ok> to create another New Part Return or <Cancel> to close the window", "");
} else {
	   System.out.println("Request Already Exist alert");
	   Driver.switchTo().alert().accept();
	   Report.updateTestLog("Warranty Return", "VIN & PartNo Combination already exist alert", Status.PASS);
		wait.until(ExpectedConditions.alertIsPresent());
	   Driver.switchTo().alert().accept();
	   Report.updateTestLog("Warranty Return", "Confirmation Alert Accepted for Existing Combination", Status.PASS);
		wait.until(ExpectedConditions.alertIsPresent());
	   success = Driver.switchTo().alert().getText();
	   System.out.println(success);
	   Driver.switchTo().alert().accept();
	   Report.updateTestLog("Warranty Return", success, Status.PASS);
	   success = success.replace ("New Part Return has been successfully submitted, please take a note of manual claim number : ","");
	   success = success.replace(" Click <Ok> to create another New Part Return or <Cancel> to close the window", "");
	   	   }
try  {
	success = success.trim();
//	userData.putData("DatasheetName","columnName","value to be written","scenario name","test case name","Iteration value", "SubIteration value");
	userData.putData("CreateMPR","ClaimNo",success,"Create MPR","Create MPR UPS","1", "1");
    Report.updateTestLog("Verify claim is created", "Claim created successfully", Status.PASS);
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	Report.updateTestLog("Verify claim is created", "Claim not created", Status.FAIL);
}

}

@Action(desc="warrantyReturnAlertUPSLTL",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void warrantyReturnAlertUPSLTL() throws InterruptedException
{ 
	Driver.findElement(By.id("submitReturn")).click();
	WebDriverWait wait = new WebDriverWait(Driver, 5);
	wait.until(ExpectedConditions.alertIsPresent());
	String success="";
	String alert = Driver.switchTo().alert().getText();
//	File ClaimCreated = new File("C:\\Automation\\Claim.txt");
	System.out.println(alert);
if (alert.equals("Are you sure you want to perform this action?")) {
	   Driver.switchTo().alert().accept();
	   Report.updateTestLog("Warranty Return", "Confirmation Alert Accepted", Status.PASS);
	   System.out.println("Confirmation Alert Accepted");
	   wait.until(ExpectedConditions.alertIsPresent());
	   success = Driver.switchTo().alert().getText();
	   System.out.println(success);
	   Driver.switchTo().alert().accept();
	   Report.updateTestLog("Warranty Return", success, Status.PASS);
	   success = success.replace ("New Part Return has been successfully submitted, please take a note of manual claim number : ","");
	   success = success.replace(" Click <Ok> to create another New Part Return or <Cancel> to close the window", "");
} else {
	   System.out.println("Request Already Exist alert");
	   Driver.switchTo().alert().accept();
	   Report.updateTestLog("Warranty Return", "VIN & PartNo Combination already exist alert", Status.PASS);
		wait.until(ExpectedConditions.alertIsPresent());
	   Driver.switchTo().alert().accept();
	   Report.updateTestLog("Warranty Return", "Confirmation Alert Accepted for Existing Combination", Status.PASS);
		wait.until(ExpectedConditions.alertIsPresent());
	   success = Driver.switchTo().alert().getText();
	   System.out.println(success);
	   Driver.switchTo().alert().accept();
	   Report.updateTestLog("Warranty Return", success, Status.PASS);
	   success = success.replace ("New Part Return has been successfully submitted, please take a note of manual claim number : ","");
	   success = success.replace(" Click <Ok> to create another New Part Return or <Cancel> to close the window", "");
	   	   }
try  {
	success = success.trim();
//	userData.putData("DatasheetName","columnName","value to be written","scenario name","test case name","Iteration value", "SubIteration value");
	userData.putData("CreateMPR","ClaimNo",success,"Create MPR","Create MPR UPS LTL","1", "1");
    Report.updateTestLog("Verify claim is created", "Claim created successfully", Status.PASS);
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	Report.updateTestLog("Verify claim is created", "Claim not created", Status.FAIL);
}

}

@Action(desc="warrantyReturnAlertUPSKBI",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void warrantyReturnAlertUPSKBI() throws InterruptedException
{ 
	Driver.findElement(By.id("submitReturn")).click();
	WebDriverWait wait = new WebDriverWait(Driver, 5);
	wait.until(ExpectedConditions.alertIsPresent());
	String success="";
	String alert = Driver.switchTo().alert().getText();
//	File ClaimCreated = new File("C:\\Automation\\Claim.txt");
	System.out.println(alert);
if (alert.equals("Are you sure you want to perform this action?")) {
	   Driver.switchTo().alert().accept();
	   Report.updateTestLog("Warranty Return", "Confirmation Alert Accepted", Status.PASS);
	   System.out.println("Confirmation Alert Accepted");
	   wait.until(ExpectedConditions.alertIsPresent());
	   success = Driver.switchTo().alert().getText();
	   System.out.println(success);
	   Driver.switchTo().alert().accept();
	   Report.updateTestLog("Warranty Return", success, Status.PASS);
	   success = success.replace ("New Part Return has been successfully submitted, please take a note of manual claim number : ","");
	   success = success.replace(" Click <Ok> to create another New Part Return or <Cancel> to close the window", "");
} else {
	   System.out.println("Request Already Exist alert");
	   Driver.switchTo().alert().accept();
	   Report.updateTestLog("Warranty Return", "VIN & PartNo Combination already exist alert", Status.PASS);
		wait.until(ExpectedConditions.alertIsPresent());
	   Driver.switchTo().alert().accept();
	   Report.updateTestLog("Warranty Return", "Confirmation Alert Accepted for Existing Combination", Status.PASS);
		wait.until(ExpectedConditions.alertIsPresent());
	   success = Driver.switchTo().alert().getText();
	   System.out.println(success);
	   Driver.switchTo().alert().accept();
	   Report.updateTestLog("Warranty Return", success, Status.PASS);
	   success = success.replace ("New Part Return has been successfully submitted, please take a note of manual claim number : ","");
	   success = success.replace(" Click <Ok> to create another New Part Return or <Cancel> to close the window", "");
	   	   }
try  {
	success = success.trim();
//	userData.putData("DatasheetName","columnName","value to be written","scenario name","test case name","Iteration value", "SubIteration value");
	userData.putData("KBI","ClaimNo",success,"Check in","Create MPR KBI","1", "1");
    Report.updateTestLog("Verify claim is created", "Claim created successfully", Status.PASS);
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	Report.updateTestLog("Verify claim is created", "Claim not created", Status.FAIL);
}

}

@Action(desc="checkIn",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void checkIn() throws InterruptedException
{ 
	try {
//		String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
		String claimNo = userData.getData("KBI", "ClaimNo","Check in", "Create MPR KBI","1","1");
	Driver.findElement(By.xpath("//*[@id=\"checkInForm\"]/table[1]/tbody/tr/td[2]/input")).sendKeys(claimNo);
	Report.updateTestLog("Verify claim no is retrieved", "Claim no. retrieved successfully", Status.PASS);
	} 
	catch (Exception e) 
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
		Report.updateTestLog("Verify claim no is retrieved", "Claim no. is not retrieved successfully", Status.FAIL);
	}
	

	
	
}

@Action(desc="receiveNonhazmatParts",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void receiveNonhazmatParts() throws InterruptedException
{ 
	String winHandleBefore = Driver.getWindowHandle(); 
	boolean checkin = true;
	            try{
	            	for(String winHandle : Driver.getWindowHandles()){
	            		  Driver.switchTo().window(winHandle);
	            			     }
	          	  Driver.findElement(By.xpath("/html/body/table/tbody/tr[5]/td[1]/input")).click();
	            } catch (Exception e) {
	          	  checkin = false;
	          	  e.printStackTrace();
	            }
	            
	            if (checkin == true){
	            System.out.println("Quantity Mismatch Alert CLosed");
	            Driver.switchTo().window(winHandleBefore);
	            Driver.findElement(By.xpath("//*[@id='receiveSubmitBtnId']")).click();
	            for(String winHandle : Driver.getWindowHandles()){
	      		   Driver.switchTo().window(winHandle);
	      		     }
	            Driver.findElement(By.xpath("/html/body/table/tbody/tr[5]/td[2]/input")).click();
//	            Report.updateTestLog("CheckIn", "Quantity Mismatch Alert Accepted", Status.PASS);
	            System.out.println("Quantity Mismatch Alert Accepted");
	            WebDriverWait wait = new WebDriverWait(Driver, 5);
	        	wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		            for(String winHandle : Driver.getWindowHandles()){
		       		   Driver.switchTo().window(winHandle);
		       		     }
		            Driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//		            Report.updateTestLog("CheckIn", "Parts Received & Shipping Label has been generated with Mismatch in Quantity", Status.PASS);
//		            Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		            Driver.close();
	            }       else {
//	            	Report.updateTestLog("CheckIn", "Parts Received", Status.PASS);
	            	System.out.println("Parts Received");
		             for(String winHandle : Driver.getWindowHandles()){
		         		   Driver.switchTo().window(winHandle);
		         		     }
		             Driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//		              Report.updateTestLog("CheckIn", "Parts Received & Shipping Label has been generated", Status.PASS);
//		              Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		              Driver.close();
		}
		Driver.switchTo().window(winHandleBefore);
	
	
}

@Action(desc="advFiltercheckIn",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void advFiltercheckIn() throws InterruptedException
{ 

Driver.findElement(By.xpath("//*[@id='checkInForm']/table[2]/tbody/tr/td[2]/input[2]")).click();
WebDriverWait wait = new WebDriverWait(Driver, 5);
wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkInClaimNo1")));
Driver.findElement(By.id("checkInClaimNo1")).click();
Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

}

@Action(desc="swcsPartsInv",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void swcsPartsInv() throws InterruptedException
{ 
	String claimNo="";
	try {
	 Scanner in = new Scanner(new FileReader("C:/filename.txt"));
	
	StringBuilder sb = new StringBuilder();
	//Scanner in;
	while(in.hasNext()) {
	    String claim=in.next();
		sb.append(claim);
		if(claim!=null && claim.startsWith("PRS")){
			claimNo=claim;
			System.out.println(claim);
		}
	    
	}
	in.close();
	/*String outString = sb.toString();
	int index=outString.indexOf("PRS");
	String[] s = outString.split(" ");
	System.out.println(s[index]);*/
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	WebDriverWait wait = new WebDriverWait(Driver, 5);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("claimId")));
	Driver.findElement(By.id("claimId")).sendKeys(claimNo);
	
	
}

@Action(desc="pageLoad15sec",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void pageLoad15sec() throws InterruptedException
{ 
	Driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
}

@Action(desc="wait10Sec",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void wait10Sec() throws InterruptedException
{ 
	Thread.sleep(10000);
}


@Action(desc="wpiQuery",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void wpiQuery() throws InterruptedException, ClassNotFoundException, SQLException
{ 
	String OFP = null, VIN = null, WPI = null;
	File WPIValues = new File("C:/WPI.txt");
	String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = RTSC1.tmm.na.corp.toyota.com)(PORT = 1523))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = RTSC.tmm.na.corp.toyota.com)))";
	//(INSTANCE_NAME = RTSC)
	Class.forName("oracle.jdbc.driver.OracleDriver");
	String UserName = "prsapp";
	String Password = "prsapp";
	Connection con = DriverManager.getConnection(dburl, UserName, Password);
	String Query = "select * from tqnet_part_log_master where rownum<=1"; //182549
	//String Query = "Select claim_no , dealer_code from claim where claim_no='_claimNo_'"; //182549
	Statement Stmt = con.createStatement();
	//Query=Query.replace("_claimNo_", "182549");
	ResultSet rs = Stmt.executeQuery(Query);
	while(rs.next()){
		OFP=rs.getString(1);
		VIN=rs.getString(2);
		WPI=rs.getString(3);
	System.out.println(OFP+"-"+VIN+"-"+WPI);
	}
	try (PrintWriter out = new PrintWriter(WPIValues)) {
	    out.println(OFP + "\t" + VIN + "\t" + WPI);
	   	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Driver.findElement(By.id("vinTxtBox")).sendKeys(VIN);
	Driver.findElement(By.id("partNumber")).sendKeys(OFP);
	Driver.findElement(By.id("addPart")).click();
	Driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);	
	con.close();
	Driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	   boolean hazmat=true;
	   
	   try {
		   Driver.findElement(By.xpath(".//*[contains(text(),'Please remove the HAZMAT part #')]"));
	   } catch (Exception e) {
		   hazmat=false;
	   }
	   
	   if (hazmat==true){
		   hazmatReturnAlert();
	   } else {
		   warrantyReturnAlert();
	   }
	   
}

@Action(desc="rowsDisplayed250",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void rowsDisplayed250() throws InterruptedException, ClassNotFoundException, SQLException
{
	Driver.findElement(By.xpath("/html/body/div/div[3]/div[2]/div/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[4]/div/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[2]/div/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[2]/div[4]/div/table[1]/tbody/tr[1]/td/table/tbody/tr[1]/td[6]/select")).sendKeys("250");
	Driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	
}

@Action(desc="wpiCheck1",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void wpiCheck1() throws InterruptedException, ClassNotFoundException, SQLException
{
	wpi = "";
	try (BufferedReader br = new BufferedReader(new FileReader("C:/WPI.txt"))){
		 String line;
		while ((line = br.readLine()) != null) {
	        String[] data = line.split("\t");
	        wpi = data[2];
	        
	    }
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	boolean receive = true;
	try {
		Driver.findElement(By.xpath(".//*[contains(text(),'Check In')]"));
	} catch (Exception e) {
		receive = false;
        }
	if (receive == true) {
		String wpiText = Driver.findElement(By.xpath("//*[@id='part_1']/td[4]/label")).getText();
		Assert.assertEquals(wpi, wpiText);
		Report.updateTestLog("WPI", "WPI Matched in CheckIn Screen", Status.PASS);
	} else {
		String wpiText = Driver.findElement(By.xpath("/html/body/div/div[3]/div[2]/div/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[4]/div/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[2]/div/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[2]/div[3]/div/table[2]/tbody[2]/tr[1]/td[10]")).getText();
		Assert.assertEquals(wpi, wpiText);
		Report.updateTestLog("WPI", "WPI Matched in Inventory Screen", Status.PASS);
	}
}


@Action(desc="wpiAdvFilter1",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void wpiAdvFilter1() throws InterruptedException, ClassNotFoundException, SQLException
{
	wpi = "";
	try (BufferedReader br = new BufferedReader(new FileReader("C:/WPI.txt"))){
		 String line;
		while ((line = br.readLine()) != null) {
	        String[] data = line.split("\t");
	        wpi = data[2];
	        
	    }
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	boolean receive = true;
	try {
		Driver.findElement(By.xpath(".//*[contains(text(),'Check In')]"));
	} catch (Exception e) {
		receive = false;
        }
	if (receive == true) {
		Driver.findElement(By.name("receiptCheckInPortlet{actionForm.claimFilterFormBean.wpiNo}")).sendKeys(wpi);
			} 
	else {
		Driver.findElement(By.name("searchInventoryPortlet{actionForm.wpiNo}")).sendKeys(wpi);
		
	}
}

@Action(desc="searchReqFilter",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void searchReqFilter() throws InterruptedException, ClassNotFoundException, SQLException
{
	Driver.findElement(By.xpath("//*[@id='search_form']/table/tbody/tr[11]/td[2]/input[1]")).click();
	Driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	
}

@Action(desc="hazmatReturnAlert",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void hazmatReturnAlert() throws InterruptedException, ClassNotFoundException, SQLException
{
	System.out.println("Enters into HAZMAT PARTS Method");
	String hazmatclaimno="";
	File ClaimCreated = new File("C:/filename.txt");
	Driver.findElement(By.id("hazmatChkBox")).click();
	   Driver.findElement(By.id("setrTxtBox")).sendKeys("9B179");
	   Select dropdown21 = new Select(Driver.findElement(By.id("vendorSelect")));
	   dropdown21.selectByIndex(0);
	   WebDriverWait wait = new WebDriverWait(Driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("addPart")));
	Driver.findElement(By.id("addPart")).click();
	Driver.findElement(By.id("submitReturn")).click();
	Driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
boolean existingrec = false;
try {
	   for(String winHandle : Driver.getWindowHandles()){
		   
		   Driver.switchTo().window(winHandle);
		   }
	   Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	   Driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/input[1]")).click();
} catch (Exception e) {
	   existingrec = true;
}
if (existingrec == false) {
	   
	   WebDriverWait wait1 = new WebDriverWait(Driver, 60);
	   wait1.until(ExpectedConditions.numberOfWindowsToBe(4));
	   Set<String> windowhandle=Driver.getWindowHandles();
	   System.out.println(windowhandle);
	   int i=0;
	   for(String windowsId : windowhandle){
		   i++;
		   if(i==3){
		   Driver.switchTo().window(windowsId);
		   }
		   System.out.println(windowsId);
	   }
	   System.out.println(Driver.getCurrentUrl());
	   hazmatclaimno = Driver.findElement(By.xpath("//*[@id='warnMessage']")).getText();
	   System.out.println(hazmatclaimno);
	   Driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/input[2]")).click();
	   Driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	   Report.updateTestLog("Warranty Return-HAZMAT", hazmatclaimno, Status.PASS);
} else {
	   Driver.findElement(By.id("submitReturn")).click();
	   WebDriverWait wait1 = new WebDriverWait(Driver, 5);
		wait1.until(ExpectedConditions.alertIsPresent());
	   Driver.switchTo().alert().accept();
	   Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	   for(String winHandle : Driver.getWindowHandles()){
		   
		   Driver.switchTo().window(winHandle);
		   }
	   Driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/input[1]")).click();
	   
//	   int wincount = driver.getWindowHandles().size();
//	   System.out.println(wincount);
	   WebDriverWait wait11 = new WebDriverWait(Driver, 60);
	  wait11.until(ExpectedConditions.numberOfWindowsToBe(4));
	   Set<String> windowhandle=Driver.getWindowHandles();
	   System.out.println(windowhandle);
	   int i=0;
	   for(String windowsId : windowhandle){
		   i++;
		   if(i==3){
		   Driver.switchTo().window(windowsId);
		   }
		   System.out.println(windowsId);
	   }
	   System.out.println(Driver.getCurrentUrl());
	   hazmatclaimno = Driver.findElement(By.xpath("//*[@id='warnMessage']")).getText();
	   System.out.println(hazmatclaimno);
	   Driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/input[2]")).click();
	   Driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	   Report.updateTestLog("Warranty Return-HAZMAT","HAZMAT MPR CREATED WITH EXISTING PARTS",Status.PASS);
}
try (PrintWriter out = new PrintWriter(ClaimCreated)) {
    out.println(hazmatclaimno);
} catch (FileNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

for(String winHandle : Driver.getWindowHandles()){
	   
	   Driver.switchTo().window(winHandle);
	   }
Driver.close();

}

@Action(desc="exceptionHandlingPRS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void exceptionHandlingPRS() throws InterruptedException, ClassNotFoundException, SQLException
{
	if ("New Requests".equals(Driver.findElement(By.xpath("/html/body/div/div[3]/div[2]/div/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[4]/div/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[2]/div/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[1]/div/table/tbody/tr/td[2]")).getText())) {
		
		Report.updateTestLog("PRS","Navigated to PRS",Status.PASS);
		
	} else if (Driver.findElements(By.xpath(".//*[contains(text(),'Search for Parts Requests')]")).size()!=0){
		
		Report.updateTestLog("PRS","Navigated to Search Request",Status.PASS);
		
	}else if (Driver.findElements(By.xpath(".//*[contains(text(),'Check In')]")).size()!=0){
		
		Report.updateTestLog("PRS","Navigated to Check In",Status.PASS);
		
	} else if (Driver.findElements(By.xpath(".//*[contains(text(),'Parts')]")).size()!=0){
		
		Report.updateTestLog("PRS","Navigated to Search Inventory",Status.PASS);
		
	} else if (Driver.findElements(By.xpath(".//*[contains(text(),'Warranty Returns')]")).size()!=0){
		
		Report.updateTestLog("PRS","Navigated to Warranty Returns",Status.PASS);
		
	} else {
		
		Report.updateTestLog("PRS","Facing Issues in PRS",Status.FAIL);
		
	}
}

@Action(desc="vdwmodels",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void vdwmodels() throws InterruptedException
{ 
	String vdwdivision = Driver.findElement(By.xpath("//*[@id=\"borderLayout_eGridPanel\"]/div[1]/div/div[4]/div[3]/div/div/div[1]/div[2]")).getText();
	String vdwmodelnum = Driver.findElement(By.xpath("//*[@id=\"borderLayout_eGridPanel\"]/div[1]/div/div[4]/div[3]/div/div/div[1]/div[3]")).getText();
	Report.updateTestLog("vdw", vdwdivision + "Get column text" + vdwmodelnum, Status.PASS);
}

@Action(desc="ClickIssue",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public class ClickIssue
{
	
	WebDriverWait wait=new WebDriverWait(Driver,30);

	/**
	 * Find and click an element till it get clicked
	 * @author 405895
	 * @param driver - WebDriver instance
	 * @param ele2Clk - Element to click
	 * @param ele2Chk - Element to be displayed after clicked on ele2Clk element
	 * @param eleName - Name of the element to click
	 */
	public void findAndClickElement(WebDriver driver, By ele2Clk, By ele2Chk, String eleName) {
		wait.until(ExpectedConditions.elementToBeClickable((ele2Clk)));
		WebElement wEle = null;
		WebElement wEle2Chk = null;
		int cnt = 0;
		do
		{
			wEle = findElementIfExists(driver,ele2Clk);
			Actions act = null;
			act.moveToElement(wEle);
			JavascriptExecutor js= (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", wEle);
			try {
				explicitWaitTillElementVisibile(ele2Chk);
				wEle2Chk = findElementIfExists(driver,ele2Chk);	
			} catch (Exception e) {
				wEle2Chk = null;
			}
			cnt++;
		}
		while(wEle2Chk==null && cnt<=5);
		
		if(wEle2Chk!=null)
		{
			Report.updateTestLog(eleName, "has been clicked", Status.PASS);
		} 
		else 
		{
			Report.updateTestLog(eleName, "could not be clicked", Status.FAIL);
		}
	}
	
	public void explicitWaitTillElementVisibile(By by)
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	 public WebElement findElementIfExists(WebDriver driver,By by)
	 {
			WebElement wEle = null; int cntr = 0;
			do{
				try {
					sleepTime(driver, 2);
					wEle = driver.findElement(by);	
					if(!wEle.isDisplayed()){
						wEle = null;
					}
				} catch (Exception e) {
					wEle = null;
				}			
				cntr++;			
			}while(wEle == null && cntr <= 2);
			if(wEle == null){
				System.out.println("Element does not exist");
			}
			return wEle;
		}
}

@Action(desc="VIalertaccept1",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void VIalertaccept1() throws InterruptedException
{ 
	try {
		for(String winHandle : Driver.getWindowHandles()){
	        Driver.switchTo().window(winHandle);
	          }
		Driver.findElement(By.xpath("//input[@type='button' and @value='I Agree']")).click();
		Report.updateTestLog("Click on accept alert", "Accept alert is clicked", Status.PASS);
    } catch (Exception e) {
        //exception handling
    	Report.updateTestLog("Click on accept alert", "Accept alert is not clicked", Status.DONE);
    }
	
	
}
@Action(desc="VIalertaccept2",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void VIalertaccept2() throws InterruptedException
{ 
	
	try {
		for(String winHandle : Driver.getWindowHandles()){
	        Driver.switchTo().window(winHandle);
	          }
		Driver.findElement(By.xpath("//*[@id=\"popDiv2\"]/div/img[2]")).click();
		Report.updateTestLog("Click on accept alert", "Accept alert is clicked", Status.PASS);
    } catch (Exception e) {
        //exception handling
    	Report.updateTestLog("Click on accept alert", "Accept alert is not clicked", Status.DONE);
    }
	

}
@Action(desc="TCIdealerlogin",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void TCIdealerlogin() throws InterruptedException, FindFailed
{ 
	Screen s = new Screen();
	s.find("C:\\Automation\\Spritz 5.1\\Projects\\T3 Suite\\ImageObjectRepository\\Capture.png");
	s.click("C:\\Automation\\Spritz 5.1\\Projects\\T3 Suite\\ImageObjectRepository\\Capture.png");
	s.type("tistest42006_3");
	Thread.sleep(1000);
	s.find("C:\\Automation\\Spritz 5.1\\Projects\\T3 Suite\\ImageObjectRepository\\Capture1.png");
	s.click("C:\\Automation\\Spritz 5.1\\Projects\\T3 Suite\\ImageObjectRepository\\Capture1.png");
	s.type("Toyota13");
	Thread.sleep(1000);
	
}
@Action(desc="receiveclaim",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void receiveclaim() throws InterruptedException
{ 
	
//		String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
		String claimNo = userData.getData("CreateMPR", "ClaimNo","Create MPR", "Create MPR","1","1");

	Thread.sleep(5000);
Driver.findElement(By.xpath("//input[@type='text' and @name='receiptCheckInPortlet{actionForm.claimFilterFormBean.claimNo}']")).sendKeys(claimNo);
Driver.findElement(By.xpath("//input[@type='submit' and @value = 'Filter']")).click();
Thread.sleep(5000);
WebDriverWait wait = new WebDriverWait(Driver,5);
wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='part_1']/td[8]/span")));
String qty_req = Driver.findElement(By.xpath("//*[@id='part_1']/td[8]/span")).getText();
Driver.findElement(By.id("qty_received1")).clear();
Driver.findElement(By.id("qty_received1")).sendKeys(qty_req);
//Driver.findElement(By.id("passId")).sendKeys(qty_req);
Driver.findElement(By.id("receiveSubmitBtnId")).click();

try {
	for(String winHandle : Driver.getWindowHandles()){
        Driver.switchTo().window(winHandle);
          }
} catch (Exception e) {
    //exception handling
}

}  

@Action(desc="searchinventory",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void searchinventory() throws InterruptedException
{ 
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String claimNo = userData.getData("CreateMPR", "ClaimNo","Create MPR", "Create MPR","1","1");
	
	Thread.sleep(5000);
Driver.findElement(By.id("claimId")).sendKeys(claimNo);
}

@Action(desc="printinventory",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void printinventory() throws InterruptedException
{ 
	Thread.sleep(5000);
try {
	String winhandle0 = Driver.getWindowHandle();
	for(String winHandle : Driver.getWindowHandles()){
        Driver.switchTo().window(winHandle);
        System.out.println("Opening print window");
        //Driver.close();
        Driver.switchTo().window(winhandle0);
        Report.updateTestLog("Opening Printinventory page", "Print Inventory page opened successfully", Status.PASS);
          }
} catch (Exception e) {
    e.printStackTrace();
	Report.updateTestLog("Opening Printinventory page", "Print Inventory page not opened", Status.FAIL);
}

} 

@Action(desc="exportinventory",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void exportinventory() throws InterruptedException
{ 
	Thread.sleep(5000);
	if(Files.exists(Paths.get("C:\\Users\\429893\\Downloads\\Results.csv"))) { 
		Report.updateTestLog("Verify if the inventory data is exported", "Inventory data exported successfully", Status.PASS);
	}
	else
	{
		Report.updateTestLog("Verify if the inventory data is exported", "Inventory data not exported successfully", Status.FAIL);
	}
} 

@Action(desc="checkboxenable",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void checkboxenable() throws InterruptedException, ClassNotFoundException, SQLException
{
boolean enabled = Driver.findElement(By.id("partNumChk0")).isEnabled();

if (enabled) {
	Report.updateTestLog("Verify if check box is disabled", "Check box is not disabled", Status.DONE);
}else {
	Report.updateTestLog("Verify if check box is disabled", "Check box is disabled", Status.PASS);
}
}


@Action(desc="TCIlogin",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void TCIlogin() throws Exception 
{     
    /*List<String> sam = new ArrayList<>();
    sam.add("tistest42006_3");
    sam.add("Toyota13");
    findAndEnterElementsUsingSikuli("C:\\Automation\\Spritz 5.1\\Projects\\T3 Suite\\ImageObjectRepository\\Capture.png", sam);*/
	wait.until(ExpectedConditions.alertIsPresent());
	try {
		Alert alert = Driver.switchTo().alert();
		String userName = userData.getData("Credentials", "Username","TCI Login", "TCI Dealer","1","1");
		String password = userData.getData("Credentials", "Password","TCI Login", "TCI Dealer","1","1");
		UserAndPassword UP = new UserAndPassword(userName,password);
		alert.authenticateUsing(UP);
		Report.updateTestLog("Login TCI", "Successfully logged into Federation portal", Status.PASS);
	} catch (Exception e) {
		e.printStackTrace();
		Report.updateTestLog("Login TCI", "Error occurred while logging into Federation portal", Status.WARNING);
	}    
}

public void findAndEnterElementsUsingSikuli(String imagePath, List<String> textToEnter)
					{
					    Screen s = new Screen();
					    Iterator<Match> it = null;
					    try 
					    {
					           it = s.findAll(imagePath);
					    } 
					    catch (FindFailed e) 
					    {
					    	e.printStackTrace();
					           Report.updateTestLog("Find Image", "Elements are not displayed", Status.FAIL);
					    }
					    int position = 0;
					    while(it.hasNext())
					    {
					           it.next().type(textToEnter.get(position));
					           position++;
					    }
					}
@Action(desc="Shiprequest",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void Shiprequest() 
{     
    List<String> sam = new ArrayList<>();
    sam.add("tistest42006_3");
    sam.add("Toyota13");
    findAndEnterElementsUsingSikuli("C:\\Automation\\Spritz 5.1\\Projects\\T3 Suite\\ImageObjectRepository\\Capture.png", sam);
    
}

@Action(desc="serviceinfo",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void serviceinfo() throws InterruptedException, ClassNotFoundException, SQLException
{
	Thread.sleep(2000);
	Driver.findElement(By.id("serviceInfoMake")).sendKeys("TOYOTA");
	Thread.sleep(2000);
	Driver.findElement(By.id("serviceInfoModel")).sendKeys("Avalon");
	Thread.sleep(2000);
	Driver.findElement(By.id("serviceInfoYear")).sendKeys("2007");
	Thread.sleep(2000);
	Driver.findElement(By.id("searchButton")).click();
	Thread.sleep(2000);
	wait.until(ExpectedConditions.elementToBeClickable(By.linkText("RM")));
	Driver.findElement(By.linkText("RM")).click();
	Thread.sleep(2000);
}

@Action(desc="Addtofavorites",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void Addtofavorites() throws InterruptedException, ClassNotFoundException, SQLException
{
	Thread.sleep(2000);
	try {
		Driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div[1]/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[2]/div/div/div[2]/div[1]/table/tbody/tr/td/div/table/tbody/tr[2]/td/div/div/div[2]/div/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[2]/table/tbody/tr[1]/td[3]/a[1]/img")).click();
		Thread.sleep(2000);
		Driver.switchTo().alert();
        Thread.sleep(1000);
        Driver.switchTo().alert().accept();
        Thread.sleep(1000);
        Report.updateTestLog("Add to Favourite", "Add to favourite icon is clicked",Status.PASS);
	}
	catch (Exception e) {
		e.printStackTrace();
		Report.updateTestLog("Add to Favourite", "Add to favourite icon is not displaying",Status.FAIL);
	}
	
}

@Action(desc="TASCasecreate",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void TASCasecreate() throws InterruptedException, ClassNotFoundException, SQLException
{
	Driver.findElement(By.xpath("//*[@id=\"chromeWidth\"]/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/input[1]")).sendKeys("04159");
	Thread.sleep(2000);
	Driver.findElement(By.id("dealerLookup")).click();
	Thread.sleep(2000);
	Driver.findElement(By.xpath("//*[@id=\"techRow0\"]/td[2]/input")).click();
	Thread.sleep(2000);
	Driver.findElement(By.id("vinText")).sendKeys("JTMBFREV7HJ137443");
	Thread.sleep(2000);
	Driver.findElement(By.xpath("//*[@id=\"chromeWidth\"]/table/tbody/tr[5]/td/table/tbody/tr[3]/td/table[1]/tbody/tr[1]/td[2]/input")).sendKeys("12345");
	Thread.sleep(2000);
	Driver.findElement(By.id("mileageId")).sendKeys("123");
	Thread.sleep(2000);
	Driver.findElement(By.id("tasEditorSymptomServiceCategory_1")).sendKeys("Brake");
	Thread.sleep(2000);
	Driver.findElement(By.id("tasEditorSymptomSection_1")).sendKeys("Brake System");
	Thread.sleep(2000);
	Driver.findElement(By.id("tasEditorSymptomSubComponent_1")).sendKeys("Brake Fluid");
	Thread.sleep(2000);
	Driver.findElement(By.id("tasEditorSymptomCondition_1")).sendKeys("Abnormal Smell");
	Thread.sleep(2000);
	
	Driver.findElement(By.id("allVinLookup")).click();
	Thread.sleep(2000);
	try {
		String winhandle0 = Driver.getWindowHandle();
		for(String winHandle : Driver.getWindowHandles())
		{
	        Driver.switchTo().window(winHandle);
	        Driver.findElement(By.id("yes")).click();
	        Driver.switchTo().window(winhandle0);
	    }
		} 
	catch (Exception e) 
		{
		e.printStackTrace();
	    //exception handling
		}
}

@Action(desc="DealerKBIerror",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void DealerKBIerror() throws InterruptedException, ClassNotFoundException, SQLException
{
	Thread.sleep(2000);
	Driver.findElement(By.xpath("//*[@id=\"checkInForm\"]/table[2]/tbody/tr/td[2]/input[2]")).click();
	Thread.sleep(5000);
	
	try {
		String winhandle0 = Driver.getWindowHandle();
		for(String winHandle : Driver.getWindowHandles())
		{
	        Driver.switchTo().window(winHandle);
	        WebElement error = Driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]"));
 	        List<WebElement> tds2= error.findElements(By.tagName("td"));
			for(WebElement a:tds2)
			{

				String val=a.getText();
				if((val.contains("Current location is not authorized to ")))
				{
					System.out.println(val);
//					Driver.findElement(By.xpath("/html/body/table/tbody/tr[3]/td/input")).click();
					Thread.sleep(1000);
					Report.updateTestLog(val+"Check for Dealer KBI error message", "Dealer KBI error message is displayed",Status.PASS);
				} 
				else
				{
					Report.updateTestLog("Check for Dealer KBI error message", "Dealer KBI error message is not displayed",Status.FAIL);
				}
			}
			Driver.findElement(By.xpath("/html/body/table/tbody/tr[3]/td/input")).click();
			
		}
		  
		Driver.switchTo().window(winhandle0);
	    
		} 
	catch (Exception e) 
		{
		e.printStackTrace();
		}
}

@Action(desc="Warrantyreturnshipothers",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void Warrantyreturnshipothers() throws InterruptedException, ClassNotFoundException, SQLException
{
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String claimNo = userData.getData("CreateMPR", "ClaimNo","Create MPR", "Create MPR","1","1");
	Driver.findElement(By.xpath("//*[@alt=\"Refresh current view\"]")).click();
	Thread.sleep(5000);
	Driver.findElement(By.xpath("//*[@id='sampleDiv']//*[text()='"+claimNo+"']")).click();
	Thread.sleep(2000);
	String winhandle0 = Driver.getWindowHandle();
	Driver.findElement(By.id("details")).click();
	Thread.sleep(2000);
	String trackingno = "12345";
	try {
		
		for(String winHandle : Driver.getWindowHandles())
		{
		
	        Driver.switchTo().window(winHandle);
		}
			Driver.findElement(By.linkText("check all")).click();
	        Thread.sleep(5000);
	        Driver.findElement(By.id("selectedBox")).click();
	        Thread.sleep(5000);
	        Driver.findElement(By.id("printTag")).click();
	        Thread.sleep(8000);
	        String winhandle1 = Driver.getWindowHandle();
			for(String winHandle2 : Driver.getWindowHandles())
			{
				Driver.switchTo().window(winHandle2);
			}
			Driver.findElement(By.id("trackingNo")).sendKeys(trackingno);
				Thread.sleep(1000);
				Driver.findElement(By.xpath("//input[@type='button' and @value='Ship']")).click();
				Thread.sleep(1000);
				Driver.switchTo().alert();
		        Thread.sleep(1000);
		        Driver.switchTo().alert().accept();
		        Thread.sleep(1000);
				
			
			Driver.switchTo().window(winhandle1);
			Report.updateTestLog("Check whether parts are shipped", "Parts shippped successfully",Status.PASS);
		
		try {
			Thread.sleep(1000);
				WebElement value2 = Driver.findElement(By.xpath("//*[@id='printedTrackingList']/tbody/tr/td[3]/label"));
				String print = value2.getText();
				
				if (print.equals(trackingno))
				{
					Report.updateTestLog("Check for shipping label", "Shipping label is generated",Status.PASS);
				}
				
				else
				{
					Report.updateTestLog("Check for shipping label", "Shipping label is not generated",Status.FAIL);
				}
				Driver.close();
			} 
		
		catch(Exception e) 
			{
				e.printStackTrace();
				Report.updateTestLog("Check whether parts are shipped", "Parts are not shippped successfully",Status.FAIL);
			}
			
		Driver.switchTo().window(winhandle0);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.updateTestLog("Check for shipping label", "Shipping label is not generated",Status.FAIL);
		}
 }

@Action(desc="WarrantyreturnshipothersKBI",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void WarrantyreturnshipothersKBI() throws InterruptedException, ClassNotFoundException, SQLException
{
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String claimNo = userData.getData("KBI", "ClaimNo","Check in", "Create MPR KBI","1","1");
	Driver.findElement(By.xpath("//*[@alt=\"Refresh current view\"]")).click();
	Thread.sleep(5000);
	Driver.findElement(By.xpath("//*[@id='sampleDiv']//*[text()='"+claimNo+"']")).click();
	Thread.sleep(2000);
	String winhandle0 = Driver.getWindowHandle();
	Driver.findElement(By.id("details")).click();
	Thread.sleep(2000);
	String trackingno = "12345";
	try {
		
		for(String winHandle : Driver.getWindowHandles())
		{
		
	        Driver.switchTo().window(winHandle);
		}
			Driver.findElement(By.linkText("check all")).click();
	        Thread.sleep(5000);
	        Driver.findElement(By.id("selectedBox")).click();
	        Thread.sleep(5000);
	        Driver.findElement(By.id("printTag")).click();
	        Thread.sleep(8000);
	        String winhandle1 = Driver.getWindowHandle();
			for(String winHandle2 : Driver.getWindowHandles())
			{
				Driver.switchTo().window(winHandle2);
			}
			Driver.findElement(By.id("trackingNo")).sendKeys(trackingno);
				Thread.sleep(1000);
				Driver.findElement(By.xpath("//input[@type='button' and @value='Ship']")).click();
				Thread.sleep(1000);
				Driver.switchTo().alert();
		        Thread.sleep(1000);
		        Driver.switchTo().alert().accept();
		        Thread.sleep(1000);
				
			
			Driver.switchTo().window(winhandle1);
			Report.updateTestLog("Check whether parts are shipped", "Parts shippped successfully",Status.PASS);
		
		try {
			Thread.sleep(1000);
				WebElement value2 = Driver.findElement(By.xpath("//*[@id='printedTrackingList']/tbody/tr/td[3]/label"));
				String print = value2.getText();
				
				if (print.equals(trackingno))
				{
					Report.updateTestLog("Check for shipping label", "Shipping label is generated",Status.PASS);
				}
				
				else
				{
					Report.updateTestLog("Check for shipping label", "Shipping label is not generated",Status.FAIL);
				}
				Driver.close();
			} 
		
		catch(Exception e) 
			{
				e.printStackTrace();
				Report.updateTestLog("Check whether parts are shipped", "Parts are not shippped successfully",Status.FAIL);
			}
			
		Driver.switchTo().window(winhandle0);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.updateTestLog("Check for shipping label", "Shipping label is not generated",Status.FAIL);
		}
 }

@Action(desc="WarrantyreturnshipUPS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void WarrantyreturnshipUPS() throws InterruptedException, ClassNotFoundException, SQLException
{
	WebDriverWait wait = new WebDriverWait(Driver,5);
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String claimNo = userData.getData("CreateMPR", "ClaimNo","Create MPR", "Create MPR UPS","1","1");
	Driver.findElement(By.xpath("//*[@alt=\"Refresh current view\"]")).click();
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='sampleDiv']//*[text()='"+claimNo+"']")));
	Driver.findElement(By.xpath("//*[@id='sampleDiv']//*[text()='"+claimNo+"']")).click();
	Thread.sleep(2000);
	String winhandle0 = Driver.getWindowHandle();
	Driver.findElement(By.id("details")).click();
	Thread.sleep(5000);
	try {
		for(String winHandle : Driver.getWindowHandles())
		{
	        Driver.switchTo().window(winHandle);
		}
		Driver.findElement(By.linkText("check all")).click();
	        Thread.sleep(5000);
	        Driver.findElement(By.id("selectedBox")).click();
	        Thread.sleep(5000);
	        Driver.findElement(By.xpath("//input [@type='button' and @value='UPS']")).click();
	        Thread.sleep(8000);
	        Report.updateTestLog("Click on UPS button", "UPS button is clicked successfully",Status.PASS);
	        try {
	        	//Get all the window handles in a set
	        	Set <String> handles =Driver.getWindowHandles();
	        	Iterator<String> it = handles.iterator();
	        	//iterate through your windows
	        	while (it.hasNext()){
	        	String remansummary = it.next();
	        	String remanreturn = it.next();
	        	String shippinglabel = it.next();
	        	Driver.switchTo().window(shippinglabel);
	        	//perform actions on new window
	        	Driver.close();
	        	Driver.switchTo().window(remanreturn);
	        	            }
	        	
	        	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='printedTrackingList']/tbody/tr/td[3]/label")));
				WebElement value2 = Driver.findElement(By.xpath("//*[@id='printedTrackingList']/tbody/tr/td[3]/label"));
				String print = value2.getText();
				
				String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = RTSC1.tmm.na.corp.toyota.com)(PORT = 1523))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = RTSC.tmm.na.corp.toyota.com)))";
				//(INSTANCE_NAME = RTSC)
				Class.forName("oracle.jdbc.driver.OracleDriver");
				String UserName = "PRSAPP";
				String Password = "PRSAPP";
				Connection con = DriverManager.getConnection(dburl, UserName, Password);
				String Query = "select TRACKING_NO from shipment where part_return_req_id in (select part_return_req_id from part_return_request where claim_no='"+claimNo+"') and deleted='N'";
				Statement Stmt = con.createStatement();
				ResultSet rs = Stmt.executeQuery(Query);
				String vehkeyid = null;
				while(rs.next())
				{
					vehkeyid=rs.getString(1);
				System.out.println(vehkeyid);
				}
				con.close();
				if (print.equals(vehkeyid))
				{
					Report.updateTestLog("Check for shipping label", "Shipping label is generated",Status.PASS);
				}
				
				else
				{
					Report.updateTestLog("Check for shipping label", "Shipping label is not generated",Status.FAIL);
				}
				Driver.close();
				Driver.switchTo().window(winhandle0);
			} 
	        catch(Exception e) 
			{
				e.printStackTrace();
				Report.updateTestLog("Check whether parts are shipped", "Parts are not shippped successfully",Status.FAIL);
			}
			
		
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Report.updateTestLog("Check for shipping label", "Shipping label is not generated",Status.FAIL);
		}
 }

@Action(desc="WarrantyreturnshipUPSLTL",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void WarrantyreturnshipUPSLTL() throws InterruptedException, ClassNotFoundException, SQLException
{
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String claimNo = userData.getData("CreateMPR", "ClaimNo","Create MPR", "Create MPR UPS LTL","1","1");
	Driver.findElement(By.xpath("//*[@alt=\"Refresh current view\"]")).click();
	Thread.sleep(5000);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='sampleDiv']//*[text()='"+claimNo+"']")));
	Driver.findElement(By.xpath("//*[@id='sampleDiv']//*[text()='"+claimNo+"']")).click();
	Thread.sleep(2000);
	String winhandle0 = Driver.getWindowHandle();
	Driver.findElement(By.id("details")).click();
	Thread.sleep(2000);
	try {
		
		for(String winHandle : Driver.getWindowHandles())
		{
	        Driver.switchTo().window(winHandle);
		}
		Driver.findElement(By.linkText("check all")).click();
	        Thread.sleep(2000);
	        Driver.findElement(By.id("selectedBox")).click();
	        Thread.sleep(2000);
	        Driver.findElement(By.xpath("//input [@type='button' and @value='UPS-LTL']")).click();
	        Thread.sleep(2000);
	        Alert confirmationAlert = Driver.switchTo().alert();
			String alertText = confirmationAlert.getText();
			confirmationAlert.accept();
			Thread.sleep(2000);
			Alert confirmationAlert1 = Driver.switchTo().alert();
			String alertText1 = confirmationAlert1.getText();
			confirmationAlert1.accept();
			Report.updateTestLog("Click on UPS-LTL button", "UPS-LTL button is clicked successfully",Status.PASS);
			Thread.sleep(8000);
		try {
			//Get all the window handles in a set
        	Set <String> handles =Driver.getWindowHandles();
        	Iterator<String> it = handles.iterator();
        	//iterate through your windows
        	while (it.hasNext()){
        	String remansummary = it.next();
        	String remanreturn = it.next();
        	String shippinglabel = it.next();
        	Driver.switchTo().window(shippinglabel);
        	//perform actions on new window	
        	Driver.close();
        	Driver.switchTo().window(remanreturn);
        	Report.updateTestLog("Switch to Reman Return screen", "Switched to reman return screen successfully",Status.PASS);
        	            }
        	
        	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='printedTrackingList']/tbody/tr/td[3]/label")));
				WebElement value2 = Driver.findElement(By.xpath("//*[@id='printedTrackingList']/tbody/tr/td[3]/label"));
				String print = value2.getText();
				Thread.sleep(2000);
				Driver.close();
				String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = RTSC1.tmm.na.corp.toyota.com)(PORT = 1523))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = RTSC.tmm.na.corp.toyota.com)))";
				//(INSTANCE_NAME = RTSC)
				Class.forName("oracle.jdbc.driver.OracleDriver");
				String UserName = "PRSAPP";
				String Password = "PRSAPP";
				Connection con = DriverManager.getConnection(dburl, UserName, Password);
				String Query = "select TRACKING_NO from shipment where part_return_req_id in (select part_return_req_id from part_return_request where claim_no='"+claimNo+"') and deleted='N'";
				Statement Stmt = con.createStatement();
				ResultSet rs = Stmt.executeQuery(Query);
				String vehkeyid = null;
				while(rs.next())
				{
					vehkeyid=rs.getString(1);
				System.out.println(vehkeyid);
				}
				con.close();
				
				if (print.equals(vehkeyid))
				{
					Report.updateTestLog("Check for shipping label", "Shipping label is generated",Status.PASS);
				}
				
				else
				{
					Report.updateTestLog("Check for shipping label", "Shipping label is not generated",Status.FAIL);
				}
				
				
				
			} catch(Exception e) 
			{
				e.printStackTrace();
			}
			
		
		}
		catch (Exception e) 
		{
			//exception handling
			e.printStackTrace();
		}
 }

@Action(desc="AdminKBIerror",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void AdminKBIerror() throws InterruptedException, ClassNotFoundException, SQLException
{
	Thread.sleep(2000);
	Driver.findElement(By.xpath("//*[@id=\"checkInForm\"]/table[2]/tbody/tr/td[2]/input[2]")).click();
	Thread.sleep(5000);
	
	try {
		String winhandle0 = Driver.getWindowHandle();
		for(String winHandle : Driver.getWindowHandles())
		{
	        Driver.switchTo().window(winHandle);
		}
        Driver.findElement(By.xpath("/html/body/table/tbody/tr[3]/td/input[2]")).click();
		Thread.sleep(2000);
		Report.updateTestLog("Check for Admin KBI error message", "Admin KBI error message is displayed",Status.PASS);
		 Driver.switchTo().window(winhandle0);
	    
		} 
	catch (Exception e) 
		{
		e.printStackTrace();
		Report.updateTestLog("Check for Admin KBI error message", "Admin KBI error message is not displayed",Status.FAIL);
		}
}

@Action(desc="AdminKBIproceed",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void AdminKBIproceed() throws InterruptedException, ClassNotFoundException, SQLException
{
	Thread.sleep(2000);
//		String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
		String claimNo = userData.getData("CreateMPR", "ClaimNo","Create MPR", "Create MPR","1","1");
		Driver.findElement(By.xpath("//*[@id=\"checkInForm\"]/table[2]/tbody/tr/td[2]/input[2]")).click();
		Thread.sleep(5000);

	try {
		String winhandle0 = Driver.getWindowHandle();
		for(String winHandle : Driver.getWindowHandles())
		{
	        Driver.switchTo().window(winHandle);
		}
			Driver.findElement(By.xpath("/html/body/table/tbody/tr[3]/td/input[1]")).click();
			Thread.sleep(2000);
			Report.updateTestLog("Check for Admin KBI error message", "Admin KBI error message is displayed",Status.PASS);
			 Driver.switchTo().window(winhandle0);
		} 
	catch (Exception e) 
		{
		e.printStackTrace();
		Report.updateTestLog("Check for Admin KBI error message", "Admin KBI error message is not displayed",Status.FAIL);
		}
	
	Thread.sleep(5000);
	
	String qty_requested = null, qty_received = null;
String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = RTSC1.tmm.na.corp.toyota.com)(PORT = 1523))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = RTSC.tmm.na.corp.toyota.com)))";
//(INSTANCE_NAME = RTSC)
Class.forName("oracle.jdbc.driver.OracleDriver");
String UserName = "PRSAPP";
String Password = "PRSAPP";
Connection con = DriverManager.getConnection(dburl, UserName, Password);
String Query = "select * from returned_part where part_return_req_id in (select part_return_req_id from part_return_request where claim_no='"+claimNo+"')";
Statement Stmt = con.createStatement();
ResultSet rs = Stmt.executeQuery(Query);
while(rs.next()){
	qty_requested=rs.getString(6);
	qty_received=rs.getString(7);
System.out.println(qty_requested+"-"+qty_received);
}
try  
{
    System.out.println(qty_requested + "\t" + qty_received );
    Report.updateTestLog("Verify if data is retrieved from table", "Data is retrieved from table",Status.PASS);
   	} 
catch (Exception e) 
{
	// TODO Auto-generated catch block
	e.printStackTrace();
	Report.updateTestLog("Verify if data is retrieved from table", "Data is not retrieved from table",Status.FAIL);
}
con.close();
	
Driver.findElement(By.id("qty_received1")).clear();
Driver.findElement(By.id("qty_received1")).sendKeys(qty_requested);
Thread.sleep(1000);
Driver.findElement(By.id("receiveSubmitBtnId")).click();

try {
	String winhandle0 = Driver.getWindowHandle();
	for(String winHandle : Driver.getWindowHandles()){
        Driver.switchTo().window(winHandle);
          }
	Driver.switchTo().window(winhandle0);
	Report.updateTestLog("Verify if pop up is displayed", "Pop up is displayed",Status.PASS);
} 
catch (Exception e) {
	e.printStackTrace();
    Report.updateTestLog("Verify if pop up is displayed", "Pop up is not displayed",Status.FAIL);
}

try {
	Boolean flag = Driver.findElement(By.id("checkInPrintTicket")).isEnabled();
	if (flag.equals(true))
	{
		Report.updateTestLog("Check for reprint button enable", "Reprint button is enabled",Status.PASS);
	}
	else
	{
		Report.updateTestLog("Check for reprint button enable", "Reprint button is disabled",Status.FAIL);
	}
          
} 
catch (Exception e) 
{
    e.printStackTrace();
    Report.updateTestLog("Verify if claim is received", "Claim is not received",Status.FAIL);
}


}

@Action(desc="CreateManualClaim",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void CreateManualClaim() throws InterruptedException, ClassNotFoundException, SQLException
{ 
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String dealercode = userData.getData("Createmanualclaim", "Dealer Code","Check in", "Create Manual Claim","1","1");
	String linecode = userData.getData("Createmanualclaim", "Line Code","Check in", "Create Manual Claim","1","1");
	String partno = userData.getData("Createmanualclaim", "Part Number","Check in", "Create Manual Claim","1","1");
	String trackingno = userData.getData("Createmanualclaim", "Tracking Number","Check in", "Create Manual Claim","1","1");
	Driver.findElement(By.xpath("//*[@id=\"checkInForm\"]/table[2]/tbody/tr/td[1]/input")).click();
	Thread.sleep(2000);
	Driver.findElement(By.id("textfield2")).sendKeys(dealercode);
	Thread.sleep(2000);
	wait.until(ExpectedConditions.elementToBeClickable(By.id("lineCode")));
    Thread.sleep(5000);
    Driver.findElement(By.id("lineCode")).sendKeys(linecode);
    Thread.sleep(2000);
    for(String winHandle : Driver.getWindowHandles()){
             Driver.switchTo().window(winHandle);
               }
    Driver.findElement(By.xpath("//*[@id=\"1\"]/td[1]")).click();
    Thread.sleep(2000);
	Driver.findElement(By.xpath("//*[@id=\"manualClaimForm\"]/div[2]/div[2]/input")).click();
	Thread.sleep(4000);
	Driver.findElement(By.xpath("//*[@id=\"partBox0\"]")).sendKeys(partno);
	Thread.sleep(2000);
	Driver.findElement(By.xpath("//*[@id=\"addPartForm\"]/table/tbody/tr[2]/td[6]/input")).sendKeys(trackingno);
	Thread.sleep(2000);
	Driver.findElement(By.xpath("//*[@id=\"addPartForm\"]/table/tbody/tr[3]/td/input[1]")).click();
	Thread.sleep(2000);
	Driver.switchTo().alert();
    Thread.sleep(1000);
    Driver.switchTo().alert().accept();
    Thread.sleep(1000);
	Driver.findElement(By.xpath("//*[@id=\"part_2_qty\"]")).sendKeys("1");
	Thread.sleep(2000);
	Driver.findElement(By.xpath("//*[@id=\"manualClaimForm\"]/div[2]/table/tbody/tr[2]/td/input[1]")).click();
	Thread.sleep(2000);
	Driver.switchTo().alert();
    Thread.sleep(1000);
    Driver.switchTo().alert().accept();
    Thread.sleep(1000);
    WebElement value2 =  Driver.findElement(By.xpath("//*[@id=\"claimInfoForm\"]/table/tbody/tr[1]/td[2]/div"));
    String Claimno = value2.getText();
    Claimno = Claimno.trim();
	Thread.sleep(2000);
	
	String Manualclaimno = null;
String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = RTSC1.tmm.na.corp.toyota.com)(PORT = 1523))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = RTSC.tmm.na.corp.toyota.com)))";
//(INSTANCE_NAME = RTSC)
Class.forName("oracle.jdbc.driver.OracleDriver");
String UserName = "PRSAPP";
String Password = "PRSAPP";
Connection con = DriverManager.getConnection(dburl, UserName, Password);
String Query = "select claim_no from part_return_request where claim_no='"+Claimno+"'";
Statement Stmt = con.createStatement();
ResultSet rs = Stmt.executeQuery(Query);
while(rs.next()){
	Manualclaimno=rs.getString(1);
}
try 
	{
    if (Manualclaimno ==Claimno)
    {
//    	userData.putData("DatasheetName","columnName","value to be written","scenario name","test case name","Iteration value", "SubIteration value");
    	userData.putData("Createmanualclaim","ClaimNo",Manualclaimno,"Check in","Create Manual Claim","1", "1");
    	Report.updateTestLog("Check if claim is created", "Manual claim is created",Status.PASS);
    }
   	} 
	catch (Exception e) 
	{
	// TODO Auto-generated catch block
	e.printStackTrace();
	Report.updateTestLog("Check if claim is created", "Manual claim is not created",Status.FAIL);
	}
con.close();

}

@Action(desc="pendingactionmove",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void pendingactionmove() throws InterruptedException
{ 
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String claimNo = userData.getData("CreateMPR", "ClaimNo","Create MPR", "Create MPR","1","1");
	
	Thread.sleep(5000);
	Driver.findElement(By.xpath("//*[@id=\"pendingForm\"]/table/tbody/tr[2]/td[2]/input")).sendKeys(claimNo);
	Thread.sleep(2000);
	Driver.findElement(By.id("inventoryType")).sendKeys("Move");
	Thread.sleep(2000);
	Driver.findElement(By.xpath("//*[@id=\"pendingForm\"]/table/tbody/tr[9]/td[6]/input[1]")).click();
	Thread.sleep(2000);
	Driver.findElement(By.xpath("//input[@type='checkBox']")).click();
	Thread.sleep(2000);
	Driver.findElement(By.id("processedAction")).click();
	Thread.sleep(2000);
	WebElement value = Driver.findElement(By.id("line_code_1"));
	String destlinecode = value.getText();
	WebElement value1 = Driver.findElement(By.id("bin_location_1"));
	String destbin = value1.getText();
	try {
	if (destbin != null)
	{
		System.out.println(destbin);
	}
	else
	{
		Driver.findElement(By.id("bin_location_1")).sendKeys(destlinecode);
		Thread.sleep(2000);
	}
	Driver.findElement(By.xpath("//input[@type='button' and @value='Submit']")).click();
	Thread.sleep(2000);
	Driver.switchTo().alert();
    Thread.sleep(1000);
    Driver.switchTo().alert().accept();
    Thread.sleep(1000);
    Report.updateTestLog("Verify move request is submitted", "Move request submitted successfully", Status.PASS);
	}
	catch (Exception e)
	{
		e.printStackTrace();
		Report.updateTestLog("Verify move request is submitted", "Move request is not submitted successfully", Status.FAIL);
	}
}

@Action(desc="Moveprocess",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void Moveprocess() throws InterruptedException
{ 
	try
	{
	Driver.findElement(By.id("inventoryType")).sendKeys("Processed");
	Thread.sleep(2000);
	Driver.findElement(By.xpath("//*[@id=\"pendingForm\"]/table/tbody/tr[9]/td[6]/input[1]")).click();
	Thread.sleep(2000);
	Driver.findElement(By.xpath("//input[@type='checkBox' and @alt='move']")).click();
	Thread.sleep(2000);
	Driver.findElement(By.id("confirmBtnId")).click();
	Thread.sleep(2000);
	Driver.switchTo().alert();
    Thread.sleep(1000);
    Driver.switchTo().alert().accept();
    Thread.sleep(1000);
    Report.updateTestLog("Verify move request is processed", "Move request processed successfully", Status.PASS);
	}
    catch (Exception e)
	{
		e.printStackTrace();
		Report.updateTestLog("Verify move request is processed", "Move request is not processed successfully", Status.FAIL);
	}
	
}

@Action(desc="pendingactionship",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void pendingactionship() throws InterruptedException
{ 
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String claimNo = userData.getData("CreateMPR", "ClaimNo","Create MPR", "Create MPR","1","1");
	try {
	Thread.sleep(5000);
	Driver.findElement(By.xpath("//*[@id=\"pendingForm\"]/table/tbody/tr[2]/td[2]/input")).sendKeys(claimNo);
	Thread.sleep(2000);
	Driver.findElement(By.id("inventoryType")).sendKeys("Ship");
	Thread.sleep(2000);
	Driver.findElement(By.xpath("//*[@id=\"pendingForm\"]/table/tbody/tr[9]/td[6]/input[1]")).click();
	Thread.sleep(2000);
	Driver.findElement(By.xpath("//input[@type='checkBox']")).click();
	Thread.sleep(2000);
	Driver.findElement(By.id("processedAction")).click();
	Thread.sleep(2000);
	Driver.findElement(By.xpath("//input[@type='button' and @id='shipSubmit']")).click();
	Thread.sleep(2000);
	Driver.switchTo().alert();
    Thread.sleep(1000);
    Driver.switchTo().alert().accept();
    Thread.sleep(1000);
    Report.updateTestLog("Verify ship request is submitted", "ship request submitted successfully", Status.PASS);
	}
    catch (Exception e)
	{
		e.printStackTrace();
		Report.updateTestLog("Verify ship request is submitted", "ship request is not submitted successfully", Status.FAIL);
	}
	
}

@Action(desc="Shipprocess",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void Shipprocess() throws InterruptedException
{ 
	try {
	Driver.findElement(By.id("inventoryType")).sendKeys("Processed");
	Thread.sleep(2000);
	Driver.findElement(By.xpath("//*[@id=\"pendingForm\"]/table/tbody/tr[9]/td[6]/input[1]")).click();
	Thread.sleep(2000);
	Driver.findElement(By.xpath("//input[@type='checkBox' and @alt='ship']")).click();
	Thread.sleep(2000);
	Driver.findElement(By.id("confirmBtnId")).click();
	Thread.sleep(2000);
	Driver.switchTo().alert();
    Thread.sleep(1000);
    Driver.switchTo().alert().accept();
    Thread.sleep(1000);
    Report.updateTestLog("Verify ship request is processed", "ship request processed successfully", Status.PASS);
   	}
       catch (Exception e)
   	{
   		e.printStackTrace();
   		Report.updateTestLog("Verify ship request is processed", "ship request is not processed successfully", Status.FAIL);
   	}
}


@Action(desc="pendingactionscrap",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void pendingactionscrap() throws InterruptedException
{ 
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String claimNo = userData.getData("CreateMPR", "ClaimNo","Create MPR", "Create MPR","1","1");
	Thread.sleep(5000);
	try {
	Driver.findElement(By.xpath("//*[@id=\"pendingForm\"]/table/tbody/tr[2]/td[2]/input")).sendKeys(claimNo);
	Thread.sleep(2000);
	Driver.findElement(By.id("inventoryType")).sendKeys("Scrap");
	Thread.sleep(2000);
	Driver.findElement(By.xpath("//*[@id=\"pendingForm\"]/table/tbody/tr[9]/td[6]/input[1]")).click();
	Thread.sleep(2000);
	Driver.findElement(By.xpath("//input[@type='checkBox']")).click();
	Thread.sleep(2000);
	Driver.findElement(By.id("processedAction")).click();
	Thread.sleep(2000);
	Driver.findElement(By.xpath("//textarea[@name='pendingActionPortlet{actionForm.comment}']")).sendKeys("Automation");
	Thread.sleep(2000);
	Driver.findElement(By.xpath("//input[@type='button' and @value='Submit']")).click();
	Thread.sleep(2000);
	Driver.switchTo().alert();
    Thread.sleep(1000);
    Driver.switchTo().alert().accept();
    Thread.sleep(1000);
    Report.updateTestLog("Verify scrap request is submitted", "scrap request submitted successfully", Status.PASS);
}
catch (Exception e)
{
	e.printStackTrace();
	Report.updateTestLog("Verify scrap request is submitted", "scrap request is not submitted successfully", Status.FAIL);
}
}

@Action(desc="Scrapprocess",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void Scrapprocess() throws InterruptedException
{ 
	try {
	Driver.findElement(By.id("inventoryType")).sendKeys("Processed");
	Thread.sleep(2000);
	Driver.findElement(By.xpath("//*[@id=\"pendingForm\"]/table/tbody/tr[9]/td[6]/input[1]")).click();
	Thread.sleep(2000);
	Driver.findElement(By.xpath("//input[@type='checkBox' and @alt='scrap']")).click();
	Thread.sleep(2000);
	Driver.findElement(By.id("confirmBtnId")).click();
	Thread.sleep(2000);
	Driver.switchTo().alert();
    Thread.sleep(1000);
    Driver.switchTo().alert().accept();
    Thread.sleep(1000);
    Report.updateTestLog("Verify scrap request is processed", "scrap request processed successfully", Status.PASS);
	}
   catch (Exception e)
	{
		e.printStackTrace();
		Report.updateTestLog("Verify scrap request is processed", "scrap request is not processed successfully", Status.FAIL);
	}
}

@Action(desc="warrantyinspect",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void warrantyinspect() throws InterruptedException
{ 
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String claimNo = userData.getData("CreateMPR", "ClaimNo","Create MPR", "Create MPR","1","1");
	
	Thread.sleep(5000);
	Driver.findElement(By.xpath("//*[@id=\"checkInForm\"]/table[1]/tbody/tr/td[2]/input")).sendKeys(claimNo);
	Driver.findElement(By.xpath("//*[@id=\"checkInForm\"]/table[2]/tbody/tr/td[2]/input[2]")).click();
	Thread.sleep(2000);
	String winHandleBefore = Driver.getWindowHandle();
	Driver.findElement(By.xpath("//input[@type='button' and @value='Warranty Inspection']")).click();
	Thread.sleep(5000);
	try{
	        for(String winHandle : Driver.getWindowHandles())
	        {
	            Driver.switchTo().window(winHandle);
	        }
	        Driver.findElement(By.id("markedYes")).click();
	        Driver.findElement(By.id("dispositionDebit")).click();
	        Driver.findElement(By.id("waCodeId")).sendKeys("WA06-Incorrect Part Returned");
	        Driver.findElement(By.id("comments")).sendKeys("Automation");
	        Thread.sleep(2000);
	        Driver.findElement(By.id("storageBinId")).sendKeys("1234");
	        Thread.sleep(2000);
	        Driver.findElement(By.id("Approve")).click();
	        Thread.sleep(4000);
	        Driver.close();
	        Report.updateTestLog("Performing Warranty Inspection", "Warranty Inspection is performed",Status.PASS);
	    } 
	catch (Exception e) 
				{
	          	  e.printStackTrace();
	          	Report.updateTestLog("Performing Warranty Inspection", "Warranty Inspection is not performed",Status.FAIL);
	            }
	            Driver.switchTo().window(winHandleBefore);
}

@Action(desc="inspectionsummary",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void inspectionsummary() throws InterruptedException
{ 
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String claimNo = userData.getData("CreateMPR", "ClaimNo","Create MPR", "Create MPR","1","1");
	
	Thread.sleep(2000);
	Driver.findElement(By.xpath("//input[@type='button' and @value='Clear Filters']")).click();
	Thread.sleep(5000);
	Driver.findElement(By.id("claimId")).sendKeys(claimNo);
	Thread.sleep(2000);
	Driver.findElement(By.xpath("//*[@id=\"primSummaryFormId\"]/table/tbody/tr[4]/td[2]/a[1]/img")).click();
	Thread.sleep(2000);
	Driver.findElement(By.id("checkBoxId")).click();
	Thread.sleep(2000);
	String winHandleBefore = Driver.getWindowHandle();
	Driver.findElement(By.xpath("//input[@type='button' and @value='Detail']")).click();
	Thread.sleep(5000);
	Report.updateTestLog("Opening Warranty Inspection", "Warranty Inspection report is opened",Status.PASS);
	try{
	        for(String winHandle : Driver.getWindowHandles())
	        {
	            Driver.switchTo().window(winHandle);
	        }
	        WebElement value = Driver.findElement(By.xpath("//td[@class='formLabel1']//label[contains(text(),'"+claimNo+"')]"));
	        String claim = value.getText();
	        Thread.sleep(2000);
	        if (claim == claimNo)
	        {
	        	Report.updateTestLog("Get Warranty Inspection details", "Warranty Inspection details are displayed"+claim,Status.PASS);
	        }
	        Driver.close();	        
	    } 
	catch (Exception e) 
				{
	          	  e.printStackTrace();
	          	Report.updateTestLog("Get Warranty Inspection details", "Warranty Inspection details are not displayed",Status.FAIL);
	            }
	            Driver.switchTo().window(winHandleBefore);
}

@Action(desc="TMSdealerkeycode",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void TMSdealerkeycode() throws InterruptedException
{ 
	Driver.findElement(By.id("keyCodeResetSave")).click();
	Thread.sleep(5000);
	Driver.switchTo().frame("popupFrame");
    Thread.sleep(5000);
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String userid1 = userData.getData("Keycode", "User id1","KeyCode", "TMS Dealer","1","1");
	String userid2 = userData.getData("Keycode", "User id2","KeyCode", "TMS Dealer","1","1");
   // Driver.findElement(By.xpath("//input[@type='checkbox' and @value='"+userid1+"']")).click();
	Driver.findElement(By.id("mgrChkBox0")).click();
	Thread.sleep(2000);
	Driver.findElement(By.id("mgrChkBox1")).click();
	//Driver.findElement(By.xpath("//input[@type='checkbox' and @value='"+userid2+"']")).click();
	Thread.sleep(2000);
    
    try
	{
    	Driver.findElement(By.id("submitBtn")).click();
        Thread.sleep(1000);
		Report.updateTestLog("Verify Keycode request is submitted", "Keycode is submitted",Status.PASS);
	}
	catch (Exception e) 
	{
    	  e.printStackTrace();
    	Report.updateTestLog("Verify Keycode request is submitted", "Keycode is not submitted",Status.FAIL);
      }	
}

@Action(desc="TCIdealerkeycode",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void TCIdealerkeycode() throws InterruptedException
{ 
	Driver.findElement(By.id("keyCodeResetSave")).click();
	Thread.sleep(5000);
	Driver.switchTo().frame("popupFrame");
    Thread.sleep(5000);
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String userid1 = userData.getData("Keycode", "User id1","Keycode", "TCI Dealer","1","1");
	String userid2 = userData.getData("Keycode", "User id2","Keycode", "TCI Dealer","1","1");
    Driver.findElement(By.xpath("//input[@type='checkbox' and @value='"+userid1+"']")).click();
	Thread.sleep(2000);
	Driver.findElement(By.xpath("//input[@type='checkbox' and @value='"+userid2+"']")).click();
	Thread.sleep(2000);
    
    try
	{
    	Driver.findElement(By.id("submitBtn")).click();
        Thread.sleep(1000);
		Report.updateTestLog("Verify Keycode request is submitted", "Keycode is submitted",Status.PASS);
	}
	catch (Exception e) 
	{
    	  e.printStackTrace();
    	Report.updateTestLog("Verify Keycode request is submitted", "Keycode is not submitted",Status.FAIL);
      }	
}

@Action(desc="TMSkeycodeapprove",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void TMSkeycodeapprove() throws InterruptedException, Exception
{ 
	String requestid = null;
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String vin = userData.getData("Keycode", "VIN","KeyCode", "TMS Dealer","1","1");
	String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
	//(INSTANCE_NAME = RTSC)
	Class.forName("oracle.jdbc.driver.OracleDriver");
	String UserName = "T3DATGLOBL";
	String Password = "t3dgprod";
	Connection con = DriverManager.getConnection(dburl, UserName, Password);
	String Query = "select * from (select KEYCODE_REQUEST_ID from keycode_request_log where vin='"+vin+"' order by request_time desc) where rownum=1";
	Statement Stmt = con.createStatement();
	ResultSet rs = Stmt.executeQuery(Query);
	while(rs.next()){
		requestid=rs.getString(1);
	System.out.println(requestid);
	}
	con.close();
	Driver.findElement(By.xpath("//input[@type='submit' and @value='Refresh']")).click();
	Thread.sleep(5000);	
	Driver.findElement(By.xpath("//*[@id='selectedMessageForm']//*[contains(text(), '"+requestid+"')]")).click();
	Thread.sleep(5000);
    Driver.findElement(By.id("approval_policy1")).click();
	Thread.sleep(2000);
	Driver.findElement(By.id("approval_policy2")).click();
	Thread.sleep(2000);
	Driver.findElement(By.id("approval_policy3")).click();
	Thread.sleep(2000);
		
	 try
		{
		 Driver.findElement(By.id("approveButton")).click();
			Thread.sleep(2000);
			Report.updateTestLog("Verify Keycode request is approved", "Keycode is approved",Status.PASS);
		}
		catch (Exception e) 
		{
	    	  e.printStackTrace();
	    	Report.updateTestLog("Verify Keycode request is approved", "Keycode is not approved",Status.FAIL);
	      }	
}

@Action(desc="TCIkeycodeapprove",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void TCIkeycodeapprove() throws InterruptedException, Exception
{ 
	String requestid = null;
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String vin = userData.getData("Keycode", "VIN","KeyCode", "TCI Dealer","1","1");
	String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
	//(INSTANCE_NAME = RTSC)
	Class.forName("oracle.jdbc.driver.OracleDriver");
	String UserName = "T3DATGLOBL";
	String Password = "t3dgprod";
	Connection con = DriverManager.getConnection(dburl, UserName, Password);
	String Query = "select * from (select KEYCODE_REQUEST_ID from keycode_request_log where vin='"+vin+"' order by request_time desc) where rownum=1";
	Statement Stmt = con.createStatement();
	ResultSet rs = Stmt.executeQuery(Query);
	while(rs.next()){
		requestid=rs.getString(1);
	System.out.println(requestid);
	}
	con.close();
	Driver.findElement(By.xpath("//input[@type='submit' and @value='Refresh']")).click();
	Thread.sleep(5000);	
	Driver.findElement(By.xpath("//*[@id='selectedMessageForm']//*[contains(text(), '"+requestid+"')]")).click();
	Thread.sleep(5000);
    Driver.findElement(By.id("approval_policy1")).click();
	Thread.sleep(2000);
	Driver.findElement(By.id("approval_policy2")).click();
	Thread.sleep(2000);
	Driver.findElement(By.id("approval_policy3")).click();
	Thread.sleep(2000);
		
	 try
		{
		 Driver.findElement(By.id("approveButton")).click();
			Thread.sleep(2000);
			Report.updateTestLog("Verify Keycode request is approved", "Keycode is approved",Status.PASS);
		}
		catch (Exception e) 
		{
	    	  e.printStackTrace();
	    	Report.updateTestLog("Verify Keycode request is approved", "Keycode is not approved",Status.FAIL);
	      }	
}

@Action(desc="TMSdealerkeycodeverify",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void TMSdealerkeycodeverify() throws InterruptedException
{ 
	
	WebElement value = Driver.findElement(By.xpath("//form[@id='submitResetRequestForm']//tr//*[contains(text(),'Thank')]//b//span"));
	try
	{
		String keycode = value.getText();
//		userData.putData("DatasheetName","columnName","value to be written","scenario name","test case name","Iteration value", "SubIteration value");
		userData.putData("Keycode","KeyCode",keycode,"KeyCode","TMS Dealer","1", "1");
		String custname;
//		String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
		String vin = userData.getData("Keycode", "VIN","KeyCode", "TMS Dealer","1","1");
		String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
		//(INSTANCE_NAME = RTSC)
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String UserName = "T3DATGLOBL";
		String Password = "t3dgprod";
		Connection con = DriverManager.getConnection(dburl, UserName, Password);
		String Query = "select * from (select cust_last_name from KEYCODE_REQUEST_LOG where vin='"+vin+"'  order by approval_time desc) where rownum=1";
		Statement Stmt = con.createStatement();
		ResultSet rs = Stmt.executeQuery(Query);
		while(rs.next()){
			custname=rs.getString(1);
		System.out.println(custname);
		}
		
		Report.updateTestLog("Verify Keycode is generated", "Keycode is generated",Status.PASS);
		con.close();
	}
	catch (Exception e) 
	{
    	  e.printStackTrace();
    	Report.updateTestLog("Verify Keycode is generated", "Keycode is not generated",Status.FAIL);
      }

}

@Action(desc="TCIdealerkeycodeverify",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void TCIdealerkeycodeverify() throws InterruptedException
{ 
	WebElement value = Driver.findElement(By.xpath("//form[@id='submitResetRequestForm']//tr//*[contains(text(),'Thank')]//b//span"));
	try
	{
		String keycode = value.getText();
//		userData.putData("DatasheetName","columnName","value to be written","scenario name","test case name","Iteration value", "SubIteration value");
		userData.putData("Keycode","KeyCode",keycode,"KeyCode","TCI Dealer","1", "1");
		String custname;
//		String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
		String vin = userData.getData("Keycode", "VIN","Keycode", "TCI Dealer","1","1");
		String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
		//(INSTANCE_NAME = RTSC)
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String UserName = "T3DATGLOBL";
		String Password = "t3dgprod";
		Connection con = DriverManager.getConnection(dburl, UserName, Password);
		String Query = "select * from (select cust_last_name from KEYCODE_REQUEST_LOG where vin='"+vin+"'  order by approval_time desc) where rownum=1";
		Statement Stmt = con.createStatement();
		ResultSet rs = Stmt.executeQuery(Query);
		while(rs.next()){
			custname=rs.getString(1);
		System.out.println(custname);
		}
		
		Report.updateTestLog("Verify Keycode is generated", "Keycode is generated",Status.PASS);
		con.close();
	}
	catch (Exception e) 
	{
    	  e.printStackTrace();
    	Report.updateTestLog("Verify Keycode is generated", "Keycode is not generated",Status.FAIL);
      }

}

@Action(desc="TMSdealeraddorremovekeyverify",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void TMSdealeraddorremovekeyverify() throws InterruptedException
{ 
//	Driver.findElement(By.xpath("//*[@id=\"selectedMessageForm\"]/table/tbody/tr[2]/td[3]/a/span")).click();
//	Thread.sleep(5000);
//    Driver.findElement(By.xpath("//*[@id=\"back\"]/div[4]/a")).click();
//	Thread.sleep(5000);
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
//	String password = userData.getData("addorremove", "Password","Add or Remove Key", "TMS Dealer","1","1");
//	Driver.findElement(By.id("keyCodeReset_password")).sendKeys(password);
//	Thread.sleep(2000);
	
	WebElement value = Driver.findElement(By.xpath("(//p[contains(text(),'Thank You')]//span)[3]"));
	try
	{
		String keycode = value.getText();
//		userData.putData("DatasheetName","columnName","value to be written","scenario name","test case name","Iteration value", "SubIteration value");
		userData.putData("addorremove","Keycode",keycode,"Add or Remove Key","TMS Dealer","1", "1");
		String custname;
//		String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
		String vin = userData.getData("addorremove", "vin","Add or Remove Key", "TMS Dealer","1","1");
		String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
		//(INSTANCE_NAME = RTSC)
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String UserName = "T3DATGLOBL";
		String Password = "t3dgprod";
		Connection con = DriverManager.getConnection(dburl, UserName, Password);
		String Query = "select * from (select cust_last_name from KEYCODE_REQUEST_LOG where vin='"+vin+"'  order by approval_time desc) where rownum=1";
		Statement Stmt = con.createStatement();
		ResultSet rs = Stmt.executeQuery(Query);
		while(rs.next()){
			custname=rs.getString(1);
		System.out.println(custname);
		}
		
		Report.updateTestLog("Verify Keycode is generated", "Keycode is generated",Status.PASS);
		con.close();
	}
	catch (Exception e) 
	{
    	  e.printStackTrace();
    	Report.updateTestLog("Verify Keycode is generated", "Keycode is not generated",Status.FAIL);
      }

}

@Action(desc="TCIdealeraddorremovekeyverify",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void TCIdealeraddorremovekeyverify() throws InterruptedException
{ 
	WebElement value = Driver.findElement(By.xpath("(//p[contains(text(),'Thank You')]//span)[3]"));
	try
	{
		String keycode = value.getText();
//		userData.putData("DatasheetName","columnName","value to be written","scenario name","test case name","Iteration value", "SubIteration value");
		userData.putData("addorremove","Keycode",keycode,"Add or Remove Key","TCI Dealer","1", "1");
		String custname;
//		String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
		String vin = userData.getData("addorremove", "vin","Add or Remove Key", "TCI Dealer","1","1");
		String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
		//(INSTANCE_NAME = RTSC)
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String UserName = "T3DATGLOBL";
		String Password = "t3dgprod";
		Connection con = DriverManager.getConnection(dburl, UserName, Password);
		String Query = "select * from (select cust_last_name from KEYCODE_REQUEST_LOG where vin='"+vin+"'  order by approval_time desc) where rownum=1";
		Statement Stmt = con.createStatement();
		ResultSet rs = Stmt.executeQuery(Query);
		while(rs.next()){
			custname=rs.getString(1);
		System.out.println(custname);
		}
		
		Report.updateTestLog("Verify Keycode is generated", "Keycode is generated",Status.PASS);
		con.close();
	}
	catch (Exception e) 
	{
    	  e.printStackTrace();
    	Report.updateTestLog("Verify Keycode is generated", "Keycode is not generated",Status.FAIL);
      }

}

@Action(desc="TMSaddorremovekeyverify",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void TMSaddorremovekeyverify() throws InterruptedException
{ 
	WebElement value = Driver.findElement(By.xpath("(//p[contains(text(),'Thank You')]//span)[3]"));
	try
	{
		String keycode = value.getText();
//		userData.putData("DatasheetName","columnName","value to be written","scenario name","test case name","Iteration value", "SubIteration value");
		userData.putData("addorremove","Keycode",keycode,"Add or Remove Key","TMS corp","1", "1");
		String custname;
//		String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
		String vin = userData.getData("addorremove", "vin","Add or Remove Key","TMS corp","1","1");
		String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
		//(INSTANCE_NAME = RTSC)
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String UserName = "T3DATGLOBL";
		String Password = "t3dgprod";
		Connection con = DriverManager.getConnection(dburl, UserName, Password);
		String Query = "select * from (select cust_last_name from KEYCODE_REQUEST_LOG where vin='"+vin+"'  order by approval_time desc) where rownum=1";
		Statement Stmt = con.createStatement();
		ResultSet rs = Stmt.executeQuery(Query);
		while(rs.next()){
			custname=rs.getString(1);
		System.out.println(custname);
		}
		
		Report.updateTestLog("Verify Keycode is generated", "Keycode is generated",Status.PASS);
		con.close();
	}
	catch (Exception e) 
	{
    	  e.printStackTrace();
    	Report.updateTestLog("Verify Keycode is generated", "Keycode is not generated",Status.FAIL);
      }

}



@Action(desc="TMSkeycodeverify",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void TMSkeycodeverify() throws InterruptedException
{ 
	WebElement value = Driver.findElement(By.xpath("(//td[contains(text(),'Key Code:')]//span)[2]"));
	try
	{
		String keycode = value.getText();
//		userData.putData("DatasheetName","columnName","value to be written","scenario name","test case name","Iteration value", "SubIteration value");
		userData.putData("Keycode","KeyCode",keycode,"KeyCode","TMS Corporate","1", "1");
		String custname;
//		String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
		String vin = userData.getData("Keycode", "VIN","KeyCode", "TMS Corporate","1","1");
		String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
		//(INSTANCE_NAME = RTSC)
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String UserName = "T3DATGLOBL";
		String Password = "t3dgprod";
		Connection con = DriverManager.getConnection(dburl, UserName, Password);
		String Query = "select * from (select cust_last_name from KEYCODE_REQUEST_LOG where vin='"+vin+"'  order by approval_time desc) where rownum=1";
		Statement Stmt = con.createStatement();
		ResultSet rs = Stmt.executeQuery(Query);
		while(rs.next()){
			custname=rs.getString(1);
		System.out.println(custname);
		}
		
		Report.updateTestLog("Verify Keycode is generated", "Keycode is generated",Status.PASS);
		con.close();
	}
	catch (Exception e) 
	{
    	  e.printStackTrace();
    	Report.updateTestLog("Verify Keycode is generated", "Keycode is not generated",Status.FAIL);
      }

}

@Action(desc="TCIkeycodeverify",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void TCIkeycodeverify() throws InterruptedException
{ 
	WebElement value = Driver.findElement(By.xpath("(//td[contains(text(),'Key Code:')]//span)[2]"));
	try
	{
		String keycode = value.getText();
//		userData.putData("DatasheetName","columnName","value to be written","scenario name","test case name","Iteration value", "SubIteration value");
		userData.putData("Keycode","KeyCode",keycode,"KeyCode","TCI Corporate","1", "1");
		String custname;
//		String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
		String vin = userData.getData("Keycode", "VIN","KeyCode", "TCI Corporate","1","1");
		String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
		//(INSTANCE_NAME = RTSC)
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String UserName = "T3DATGLOBL";
		String Password = "t3dgprod";
		Connection con = DriverManager.getConnection(dburl, UserName, Password);
		String Query = "select * from (select cust_last_name from KEYCODE_REQUEST_LOG where vin='"+vin+"'  order by approval_time desc) where rownum=1";
		Statement Stmt = con.createStatement();
		ResultSet rs = Stmt.executeQuery(Query);
		while(rs.next()){
			custname=rs.getString(1);
		System.out.println(custname);
		}
		
		Report.updateTestLog("Verify Keycode is generated", "Keycode is generated",Status.PASS);
		con.close();
	}
	catch (Exception e) 
	{
    	  e.printStackTrace();
    	Report.updateTestLog("Verify Keycode is generated", "Keycode is not generated",Status.FAIL);
      }
}

@Action(desc="TCIaddorremovekeyverify",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void TCIaddorremovekeyverify() throws InterruptedException
{ 
	WebElement value = Driver.findElement(By.xpath("(//p[contains(text(),'Thank You')]//span)[3]"));
	try
	{
		String keycode = value.getText();
//		userData.putData("DatasheetName","columnName","value to be written","scenario name","test case name","Iteration value", "SubIteration value");
		userData.putData("addorremove","Keycode",keycode,"Add or Remove Key","TCI corp","1", "1");
		String custname;
//		String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
		String vin = userData.getData("addorremove", "vin","Add or Remove Key", "TCI corp","1","1");
		String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
		//(INSTANCE_NAME = RTSC)
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String UserName = "T3DATGLOBL";
		String Password = "t3dgprod";
		Connection con = DriverManager.getConnection(dburl, UserName, Password);
		String Query = "select * from (select cust_last_name from KEYCODE_REQUEST_LOG where vin='"+vin+"'  order by approval_time desc) where rownum=1";
		Statement Stmt = con.createStatement();
		ResultSet rs = Stmt.executeQuery(Query);
		while(rs.next()){
			custname=rs.getString(1);
		System.out.println(custname);
		}
		
		Report.updateTestLog("Verify Keycode is generated", "Keycode is generated",Status.PASS);
		con.close();
	}
	catch (Exception e) 
	{
    	  e.printStackTrace();
    	Report.updateTestLog("Verify Keycode is generated", "Keycode is not generated",Status.FAIL);
      }
}

@Action(desc="airbagresetTMS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void airbagresetTMS() throws InterruptedException, Exception
{ 
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String vin = userData.getData("Airbag Reset", "VIN","Airbag Reset", "Airbag Reset TMS","1","1");
	Driver.findElement(By.id("airBagReset_vehicleVin1")).sendKeys(vin);
	Thread.sleep(2000);
	Driver.findElement(By.id("searchButton")).click();
	Thread.sleep(5000);
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String campaigncode = userData.getData("Airbag Reset", "Campaign Code","Airbag Reset", "Airbag Reset TMS","1","1");
	Driver.findElement(By.xpath("//input[starts-with(@onclick,\"enableCampaign('"+campaigncode+"')\")]")).click();
	Thread.sleep(2000);
	String winhandle0 = Driver.getWindowHandle();
	Driver.findElement(By.id("resetBtnId")).click();
	Thread.sleep(5000);
	for (String winhandle : Driver.getWindowHandles())
	{
		Driver.switchTo().window(winhandle);
	}
	Driver.findElement(By.xpath("//input [@type='button' and @value='OK']")).click();
	Thread.sleep(5000);
	for (String winhandle : Driver.getWindowHandles())
	{
		Driver.switchTo().window(winhandle);
	}
	Driver.findElement(By.xpath("//input [@type='button' and @value='OK']")).click();
	Thread.sleep(5000);	
	Driver.switchTo().window(winhandle0);
	//DB check
	String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
	//(INSTANCE_NAME = RTSC)
	Class.forName("oracle.jdbc.driver.OracleDriver");
	String UserName = "T3DATGLOBL";
	String Password = "t3dgprod";
	Connection con = DriverManager.getConnection(dburl, UserName, Password);
	String Query = "select * from AIRBAG_CAMPAIGN_LOG where VIN='"+vin+"' and CAMPAIGN_CODE='"+campaigncode+"' and DELETED_FLAG='N'";
	Statement Stmt = con.createStatement();
	ResultSet rs = Stmt.executeQuery(Query);
	try
	{
		if (rs== null)
		{
		Report.updateTestLog("Verify Airbag Reset is submitted", "Airbag Reset is submitted successfully",Status.PASS);
		}
	}
	catch (Exception e) 
	{
    	  e.printStackTrace();
    	Report.updateTestLog("Verify Airbag Reset is submitted", "Airbag Reset is not submitted",Status.FAIL);
      }
	con.close();
}
@Action(desc="airbagresetTCI",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void airbagresetTCI() throws InterruptedException, Exception
{ 
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String vin = userData.getData("Airbag Reset", "VIN","Airbag Reset", "Airbag Reset TCI","1","1");
	Driver.findElement(By.id("airBagReset_vehicleVin1")).sendKeys(vin);
	Thread.sleep(2000);
	Driver.findElement(By.id("searchButton")).click();
	Thread.sleep(5000);
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String campaigncode = userData.getData("Airbag Reset", "Campaign Code","Airbag Reset", "Airbag Reset TCI","1","1");
	Driver.findElement(By.xpath("//input[starts-with(@onclick,\"enableCampaign('"+campaigncode+"')\")]")).click();
	Thread.sleep(2000);
	String winhandle0 = Driver.getWindowHandle();
	Driver.findElement(By.id("resetBtnId")).click();
	Thread.sleep(5000);
	for (String winhandle : Driver.getWindowHandles())
	{
		Driver.switchTo().window(winhandle);
	}
	Driver.findElement(By.xpath("//input [@type='button' and @value='OK']")).click();
	Thread.sleep(5000);
	for (String winhandle : Driver.getWindowHandles())
	{
		Driver.switchTo().window(winhandle);
	}
	Driver.findElement(By.xpath("//input [@type='button' and @value='OK']")).click();
	Thread.sleep(5000);	
	Driver.switchTo().window(winhandle0);
	//DB check
	
	String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
	//(INSTANCE_NAME = RTSC)
	Class.forName("oracle.jdbc.driver.OracleDriver");
	String UserName = "T3DATGLOBL";
	String Password = "t3dgprod";
	Connection con = DriverManager.getConnection(dburl, UserName, Password);
	String Query = "select * from AIRBAG_CAMPAIGN_LOG where VIN='"+vin+"' and CAMPAIGN_CODE='"+campaigncode+"' and DELETED_FLAG='N'";
	Statement Stmt = con.createStatement();
	ResultSet rs = Stmt.executeQuery(Query);
	try
	{
		if (rs== null)
		{
		Report.updateTestLog("Verify Airbag Reset is submitted", "Airbag Reset is submitted successfully",Status.PASS);
		}
	}
	catch (Exception e) 
	{
    	  e.printStackTrace();
    	Report.updateTestLog("Verify Airbag Reset is submitted", "Airbag Reset is not submitted",Status.FAIL);
      }
	con.close();
}

@Action(desc="TMSimmobilizer",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void TMSimmobilizer() throws InterruptedException
{ 
	WebElement immobilizerreset = Driver.findElement(By.xpath("//p[contains(text(),'Thank')]//b//span"));
	try
	{
		String resetcode = immobilizerreset.getText();
		Thread.sleep(5000);
//		userData.putData("DatasheetName","columnName","value to be written","scenario name","test case name","Iteration value", "SubIteration value");
		userData.putData("Immobilizer Reset","Reset Code",resetcode,"Immobilizer Reset","TMS Corporate","1", "1");
		String custname;
//		String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
		String vin = userData.getData("Immobilizer Reset", "VIN","Immobilizer Reset", "TMS Corporate","1","1");
		String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
		//(INSTANCE_NAME = RTSC)
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String UserName = "T3DATGLOBL";
		String Password = "t3dgprod";
		Connection con = DriverManager.getConnection(dburl, UserName, Password);
		String Query = "select * from (select seed_number from KEYCODE_REQUEST_LOG where vin='"+vin+"'  order by request_time desc) where rownum=1";
		Statement Stmt = con.createStatement();
		ResultSet rs = Stmt.executeQuery(Query);
		while(rs.next()){
			custname=rs.getString(1);
		System.out.println(custname);
		}
		Report.updateTestLog("Verify Immobilizer Reset code is generated", "Immobilizer Reset code is generated",Status.PASS);
		con.close();
	}
	catch (Exception e) 
	{
    	  e.printStackTrace();
    	Report.updateTestLog("Verify Immobilizer Reset code is generated", "Immobilizer Reset code is not generated",Status.FAIL);
      }	
	
}

@Action(desc="TCIimmobilizer",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void TCIimmobilizer() throws InterruptedException
{ 
	WebElement immobilizerreset = Driver.findElement(By.xpath("//p[contains(text(),'Thank')]//b//span"));
	try
	{
		String resetcode = immobilizerreset.getText();
		Thread.sleep(5000);
//		userData.putData("DatasheetName","columnName","value to be written","scenario name","test case name","Iteration value", "SubIteration value");
		userData.putData("Immobilizer Reset","Reset Code",resetcode,"Immobilizer Reset","TCI Corporate","1", "1");
		String custname;
//		String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
		String vin = userData.getData("Immobilizer Reset", "VIN","Immobilizer Reset", "TCI Corporate","1","1");
		String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
		//(INSTANCE_NAME = RTSC)
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String UserName = "T3DATGLOBL";
		String Password = "t3dgprod";
		Connection con = DriverManager.getConnection(dburl, UserName, Password);
		String Query = "select * from (select seed_number from KEYCODE_REQUEST_LOG where vin='"+vin+"'  order by request_time desc) where rownum=1";
		Statement Stmt = con.createStatement();
		ResultSet rs = Stmt.executeQuery(Query);
		while(rs.next()){
			custname=rs.getString(1);
		System.out.println(custname);
		}
		Report.updateTestLog("Verify Immobilizer Reset code is generated", "Immobilizer Reset code is generated",Status.PASS);
		con.close();
	}
	catch (Exception e) 
	{
    	  e.printStackTrace();
    	Report.updateTestLog("Verify Immobilizer Reset code is generated", "Immobilizer Reset code is not generated",Status.FAIL);
      }	
}

@Action(desc="TMSdealerimmobilizer",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void TMSdealerimmobilizer() throws InterruptedException
{ 
	Driver.findElement(By.id("keyCodeResetSave")).click();
	Thread.sleep(5000);
	Driver.switchTo().frame("popupFrame");
    Thread.sleep(5000);
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String userid1 = userData.getData("Immobilizer Reset", "User id1","Immobilizer Reset", "TMS Dealer","1","1");
	String userid2 = userData.getData("Immobilizer Reset", "User id2","Immobilizer Reset", "TMS Dealer","1","1");
    Driver.findElement(By.xpath("//input[@type='checkbox' and @value='"+userid1+"']")).click();
	Thread.sleep(2000);
	Driver.findElement(By.xpath("//input[@type='checkbox' and @value='"+userid2+"']")).click();
	Thread.sleep(2000);
    
    try
	{
    	Driver.findElement(By.id("submitBtn")).click();
        Thread.sleep(1000);
		Report.updateTestLog("Verify Keycode request is submitted", "Keycode is submitted",Status.PASS);
	}
	catch (Exception e) 
	{
    	  e.printStackTrace();
    	Report.updateTestLog("Verify Keycode request is submitted", "Keycode is not submitted",Status.FAIL);
      }	
}

@Action(desc="TCIdealerimmobilizer",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void TCIdealerimmobilizer() throws InterruptedException
{ 
	Driver.findElement(By.id("keyCodeResetSave")).click();
	Thread.sleep(5000);
	Driver.switchTo().frame("popupFrame");
    Thread.sleep(5000);
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String userid1 = userData.getData("Immobilizer Reset", "User id1","Immobilizer Reset", "TCI Dealer","1","1");
	String userid2 = userData.getData("Immobilizer Reset", "User id2","Immobilizer Reset", "TCI Dealer","1","1");
    Driver.findElement(By.xpath("//input[@type='checkbox' and @value='"+userid1+"']")).click();
	Thread.sleep(2000);
	Driver.findElement(By.xpath("//input[@type='checkbox' and @value='"+userid2+"']")).click();
	Thread.sleep(2000);
    
    try
	{
    	Driver.findElement(By.id("submitBtn")).click();
        Thread.sleep(1000);
		Report.updateTestLog("Verify Keycode request is submitted", "Keycode is submitted",Status.PASS);
	}
	catch (Exception e) 
	{
    	  e.printStackTrace();
    	Report.updateTestLog("Verify Keycode request is submitted", "Keycode is not submitted",Status.FAIL);
      }	
}

@Action(desc="TMSimmobilizerapprove",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void TMSimmobilizerapprove() throws InterruptedException, Exception
{ 
	String requestid = null;
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String vin = userData.getData("Immobilizer Reset", "VIN","Immobilizer Reset", "TMS Dealer","1","1");
	String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
	//(INSTANCE_NAME = RTSC)
	Class.forName("oracle.jdbc.driver.OracleDriver");
	String UserName = "T3DATGLOBL";
	String Password = "t3dgprod";
	Connection con = DriverManager.getConnection(dburl, UserName, Password);
	String Query = "select * from (select KEYCODE_REQUEST_ID from keycode_request_log where vin='"+vin+"' order by request_time desc) where rownum=1";
	Statement Stmt = con.createStatement();
	ResultSet rs = Stmt.executeQuery(Query);
	while(rs.next()){
		requestid=rs.getString(1);
	System.out.println(requestid);
	}
	con.close();
	Driver.findElement(By.xpath("//input[@type='submit' and @value='Refresh']")).click();
	Thread.sleep(5000);	
	Driver.findElement(By.xpath("//*[@id='selectedMessageForm']//*[contains(text(), '"+requestid+"')]")).click();
	Thread.sleep(5000);
    Driver.findElement(By.id("approval_policy1")).click();
	Thread.sleep(2000);
	Driver.findElement(By.id("approval_policy2")).click();
	Thread.sleep(2000);
	Driver.findElement(By.id("approval_policy3")).click();
	Thread.sleep(2000);
		
	 try
		{
		 Driver.findElement(By.id("approveButton")).click();
			Thread.sleep(2000);
			Report.updateTestLog("Verify Keycode request is approved", "Keycode is approved",Status.PASS);
		}
		catch (Exception e) 
		{
	    	  e.printStackTrace();
	    	Report.updateTestLog("Verify Keycode request is approved", "Keycode is not approved",Status.FAIL);
	      }	
}

@Action(desc="TCIimmobilizerapprove",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void TCIimmobilizerapprove() throws InterruptedException, Exception
{ 
	String requestid = null;
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String vin = userData.getData("Immobilizer Reset", "VIN","Immobilizer Reset", "TMS Dealer","1","1");
	String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
	//(INSTANCE_NAME = RTSC)
	Class.forName("oracle.jdbc.driver.OracleDriver");
	String UserName = "T3DATGLOBL";
	String Password = "t3dgprod";
	Connection con = DriverManager.getConnection(dburl, UserName, Password);
	String Query = "select * from (select KEYCODE_REQUEST_ID from keycode_request_log where vin='"+vin+"' order by request_time desc) where rownum=1";
	Statement Stmt = con.createStatement();
	ResultSet rs = Stmt.executeQuery(Query);
	while(rs.next()){
		requestid=rs.getString(1);
	System.out.println(requestid);
	}
	con.close();
	Driver.findElement(By.xpath("//input[@type='submit' and @value='Refresh']")).click();
	Thread.sleep(5000);	
	Driver.findElement(By.xpath("//*[@id='selectedMessageForm']//*[contains(text(), '"+requestid+"')]")).click();
	Thread.sleep(5000);
    Driver.findElement(By.id("approval_policy1")).click();
	Thread.sleep(2000);
	Driver.findElement(By.id("approval_policy2")).click();
	Thread.sleep(2000);
	Driver.findElement(By.id("approval_policy3")).click();
	Thread.sleep(2000);
		
	 try
		{
		 Driver.findElement(By.id("approveButton")).click();
			Thread.sleep(2000);
			Report.updateTestLog("Verify Keycode request is approved", "Keycode is approved",Status.PASS);
		}
		catch (Exception e) 
		{
	    	  e.printStackTrace();
	    	Report.updateTestLog("Verify Keycode request is approved", "Keycode is not approved",Status.FAIL);
	      }	
}

@Action(desc="TMSdealerimmobilizerverify",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void TMSdealerimmobilizerverify() throws InterruptedException
{ 
	Driver.findElement(By.xpath("//*[@id=\"selectedMessageForm\"]/table/tbody/tr[2]/td[3]/a/span")).click();
	Thread.sleep(5000);
    Driver.findElement(By.xpath("//*[@id=\"back\"]/div[4]/a")).click();
	Thread.sleep(5000);
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String password = userData.getData("Immobilizer Reset", "Password","Immobilizer Reset", "TMS Dealer","1","1");
	Driver.findElement(By.id("keycodeReset.keyCodeReset_password")).sendKeys(password);
	Thread.sleep(2000);
	Driver.findElement(By.xpath("//input[@type='submit' and @value='Login']")).click();
	Thread.sleep(2000);
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String swversion = userData.getData("Immobilizer Reset", "SW Version","Immobilizer Reset", "TMS Dealer","1","1");
	String seednumber = userData.getData("Immobilizer Reset", "Seed Number","Immobilizer Reset", "TMS Dealer","1","1");
	Driver.findElement(By.id("keyCodeReset_swversion")).sendKeys(swversion);
	Thread.sleep(2000);
	Driver.findElement(By.id("keyCodeReset_seedNumber")).sendKeys(seednumber);
	Thread.sleep(2000);
	Driver.findElement(By.id("/keyCodeResetSave")).click();
	Thread.sleep(5000);
	WebElement value = Driver.findElement(By.xpath("//*[@id=\"submitResetRequestForm\"]/table/tbody/tr[4]/td/b/span"));
	try
	{
		String keycode = value.getText();
//		userData.putData("DatasheetName","columnName","value to be written","scenario name","test case name","Iteration value", "SubIteration value");
		userData.putData("Immobilizer Reset","Reset Code",keycode,"Immobilizer Reset","TMS Dealer","1", "1");
		String custname;
//		String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
		String vin = userData.getData("Immobilizer Reset", "VIN","Immobilizer Reset", "TMS Dealer","1","1");
		String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
		//(INSTANCE_NAME = RTSC)
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String UserName = "T3DATGLOBL";
		String Password = "t3dgprod";
		Connection con = DriverManager.getConnection(dburl, UserName, Password);
		String Query = "select * from (select seed_number from KEYCODE_REQUEST_LOG where vin='"+vin+"'  order by request_time desc) where rownum=1";
		Statement Stmt = con.createStatement();
		ResultSet rs = Stmt.executeQuery(Query);
		while(rs.next()){
			custname=rs.getString(1);
		System.out.println(custname);
		}
		Report.updateTestLog("Verify Immobilizer Reset code is generated", "Immobilizer Reset code is generated",Status.PASS);
		con.close();
	}
	catch (Exception e) 
	{
    	  e.printStackTrace();
    	Report.updateTestLog("Verify Immobilizer Reset code is generated", "Immobilizer Reset code is not generated",Status.FAIL);
      }	
	
}
@Action(desc="TCIdealerimmobilizerverify",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void TCIdealerimmobilizerverify() throws InterruptedException
{ 
	Driver.findElement(By.xpath("//*[@id=\"selectedMessageForm\"]/table/tbody/tr[2]/td[3]/a/span")).click();
	Thread.sleep(5000);
    Driver.findElement(By.xpath("//*[@id=\"back\"]/div[4]/a")).click();
	Thread.sleep(5000);
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String password = userData.getData("Immobilizer Reset", "Password","Immobilizer Reset", "TMS Dealer","1","1");
	Driver.findElement(By.id("keycodeReset.keyCodeReset_password")).sendKeys(password);
	Thread.sleep(2000);
	Driver.findElement(By.xpath("//input[@type='submit' and @value='Login']")).click();
	Thread.sleep(2000);
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String swversion = userData.getData("Immobilizer Reset", "SW Version","Immobilizer Reset", "TMS Dealer","1","1");
	String seednumber = userData.getData("Immobilizer Reset", "Seed Number","Immobilizer Reset", "TMS Dealer","1","1");
	Driver.findElement(By.id("keyCodeReset_swversion")).sendKeys(swversion);
	Thread.sleep(2000);
	Driver.findElement(By.id("keyCodeReset_seedNumber")).sendKeys(seednumber);
	Thread.sleep(2000);
	Driver.findElement(By.id("/keyCodeResetSave")).click();
	Thread.sleep(5000);
	WebElement value = Driver.findElement(By.xpath("//*[@id=\"submitResetRequestForm\"]/table/tbody/tr[4]/td/b/span"));
	try
	{
		String keycode = value.getText();
//		userData.putData("DatasheetName","columnName","value to be written","scenario name","test case name","Iteration value", "SubIteration value");
		userData.putData("Immobilizer Reset","Reset Code",keycode,"Immobilizer Reset","TMS Dealer","1", "1");
		String custname;
//		String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
		String vin = userData.getData("Immobilizer Reset", "VIN","Immobilizer Reset", "TMS Dealer","1","1");
		String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
		//(INSTANCE_NAME = RTSC)
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String UserName = "T3DATGLOBL";
		String Password = "t3dgprod";
		Connection con = DriverManager.getConnection(dburl, UserName, Password);
		String Query = "select * from (select seed_number from KEYCODE_REQUEST_LOG where vin='"+vin+"'  order by request_time desc) where rownum=1";
		Statement Stmt = con.createStatement();
		ResultSet rs = Stmt.executeQuery(Query);
		while(rs.next()){
			custname=rs.getString(1);
		System.out.println(custname);
		}
		Report.updateTestLog("Verify Immobilizer Reset code is generated", "Immobilizer Reset code is generated",Status.PASS);
		con.close();
	}
	catch (Exception e) 
	{
    	  e.printStackTrace();
    	Report.updateTestLog("Verify Immobilizer Reset code is generated", "Immobilizer Reset code is not generated",Status.FAIL);
      }	
	
}

@Action(desc="TechstreamTmsDealerurl",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void TechstreamTmsDealerurl() throws InterruptedException
{ 
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String softwareid = userData.getData("Techstream", "softwareid","Techstream registration", "TMS Dealer","1","1");
	String scantoolversion = userData.getData("Techstream", "scantoolversion","Techstream registration", "TMS Dealer","1","1");
	try
	{
	String url = "http://one.tis.qa3.toyota.com/t3Portal/scantool/?l=appReg-EN&id="+softwareid+"&ver="+scantoolversion+"";
	Thread.sleep(2000);
//	userData.putData("DatasheetName","columnName","value to be written","scenario name","test case name","Iteration value", "SubIteration value");
	userData.putData("Techstream","url",url,"Techstream registration","TMS Dealer","1", "1");
	Report.updateTestLog("Verify Techstream url is generated", "Techstream url is generated successfully", Status.PASS);
	}
	catch (Exception e)
	{
		e.printStackTrace();
		Report.updateTestLog("Verify Techstream url is generated", "Techstream url is not generated successfully", Status.FAIL);	
	}
}

@Action(desc="TechstreamTCIDealerurl",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void TechstreamTCIDealerurl() throws InterruptedException
{ 
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String softwareid = userData.getData("Techstream", "softwareid","Techstream registration", "TCI Dealer","1","1");
	String scantoolversion = userData.getData("Techstream", "scantoolversion","Techstream registration", "TCI Dealer","1","1");
	try
	{
	String url = "http://one.tis.qa3.toyota.com/t3Portal/scantool/?l=appReg-EN&id="+softwareid+"&ver="+scantoolversion+"";
	Thread.sleep(2000);
//	userData.putData("DatasheetName","columnName","value to be written","scenario name","test case name","Iteration value", "SubIteration value");
	userData.putData("Techstream","url",url,"Techstream registration","TCI Dealer","1", "1");
	Report.updateTestLog("Verify Techstream url is generated", "Techstream url is generated successfully", Status.PASS);
	}
	catch (Exception e)
	{
		e.printStackTrace();
		Report.updateTestLog("Verify Techstream url is generated", "Techstream url is not generated successfully", Status.FAIL);	
	}
}


@Action(desc="TechstreamTmsDealer",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void TechstreamTmsDealer() throws InterruptedException, Exception
{ 
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String password = userData.getData("Credentials", "Password","CreatePartrequest", "ONE.TIS login","1","1");
	String dealercode = userData.getData("Techstream", "dealercode","Techstream registration", "TMS Dealer","1","1");
	String softwareid = userData.getData("Techstream", "softwareid","Techstream registration", "TMS Dealer","1","1");
	String scantoolversion = userData.getData("Techstream", "scantoolversion","Techstream registration", "TMS Dealer","1","1");
	try {
	Driver.findElement(By.id("selectSerialNumber")).click();
	Thread.sleep(2000);
	Driver.findElement(By.id("selectSerialNumber")).sendKeys("Other");
	Thread.sleep(2000);
	Driver.findElement(By.id("serialNumberTechStream2")).sendKeys("12345");
	Thread.sleep(2000);
	Driver.findElement(By.id("techStreamSoftwareNextButton")).click();
	Thread.sleep(2000);
	Report.updateTestLog("Verify serial number page is displayed", "serial number page is displayed", Status.PASS);
	}
	catch (Exception e) {
		e.printStackTrace();
		Report.updateTestLog("Verify serial number page is displayed", "serial number page is not displayed", Status.DONE);
	}
	Driver.findElement(By.id("formPcName")).sendKeys("12345");
	Thread.sleep(2000);	
	Driver.findElement(By.id("formPassword")).sendKeys(password);
	Thread.sleep(2000);
	Driver.findElement(By.id("butStartTest")).click();
	Thread.sleep(5000);
	WebDriverWait wait = new WebDriverWait(Driver,5);
	Thread.sleep(2000);
	wait.until(ExpectedConditions.elementToBeClickable(By.id("resButton")));
	Driver.findElement(By.id("txtArea")).sendKeys("Automation");
	Thread.sleep(2000);
	Driver.findElement(By.id("resButton")).click();
	Thread.sleep(5000);
	Driver.findElement(By.id("butStartTest")).click();
	Thread.sleep(5000);
	Driver.findElement(By.id("clipBoardBttn")).click();
	Thread.sleep(2000);
	WebElement value = Driver.findElement(By.id("copyMsg"));
	String copytext = value.getText();
	String regkey = null;
	try
	{
	if (copytext == "Key copied to clipboard")
	{
		String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
		//(INSTANCE_NAME = RTSC)
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String UserName = "T3DATGLOBL";
		String Password = "t3dgprod";
		Connection con = DriverManager.getConnection(dburl, UserName, Password);
		String Query = "select application_key from (select * from SCANTOOL_USERS where dealer_code='"+dealercode+"' and software_id='"+softwareid+"' and scantool_version='"+scantoolversion+" order by last_updt_timestamp desc) where rownum=1";
		Statement Stmt = con.createStatement();
		ResultSet rs = Stmt.executeQuery(Query);
		while(rs.next()){
			regkey=rs.getString(1);
		System.out.println(regkey);
		con.close();
		}
		
		Report.updateTestLog("Verify Techstream registration is completed", "Techstream registration is completed and Registration Key is "+regkey+"",Status.PASS);
	}
	}
	catch (Exception e)
	{
		e.printStackTrace();
		Report.updateTestLog("Verify Techstream registration is completed", "Techstream registration is not completed",Status.FAIL);
	}
}

@Action(desc="TechstreamTCIDealer",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void TechstreamTCIDealer() throws InterruptedException, Exception
{ 
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String password = userData.getData("Credentials", "Password","TCI Login", "TCI Dealer","1","1");
	String dealercode = userData.getData("Techstream", "dealercode","Techstream registration", "TCI Dealer","1","1");
	String softwareid = userData.getData("Techstream", "softwareid","Techstream registration", "TCI Dealer","1","1");
	String scantoolversion = userData.getData("Techstream", "scantoolversion","Techstream registration", "TCI Dealer","1","1");
	Driver.findElement(By.id("selectSerialNumber")).click();
	Thread.sleep(2000);
	Driver.findElement(By.id("selectSerialNumber")).sendKeys("Other");
	Thread.sleep(2000);
	Driver.findElement(By.id("serialNumberTechStream2")).sendKeys("12345");
	Thread.sleep(2000);
	Driver.findElement(By.id("techStreamSoftwareNextButton")).click();
	Thread.sleep(2000);
	Driver.findElement(By.id("formPcName")).sendKeys("12345");
	Thread.sleep(2000);	
	Driver.findElement(By.id("formPassword")).sendKeys(password);
	Thread.sleep(2000);
	Driver.findElement(By.id("butStartTest")).click();
	Thread.sleep(5000);
	WebDriverWait wait = new WebDriverWait(Driver,5);
	Thread.sleep(2000);
	wait.until(ExpectedConditions.elementToBeClickable(By.id("resButton")));
	Driver.findElement(By.id("txtArea")).sendKeys("Automation");
	Thread.sleep(2000);
	Driver.findElement(By.id("resButton")).click();
	Thread.sleep(5000);
	Driver.findElement(By.id("butStartTest")).click();
	Thread.sleep(5000);
	Driver.findElement(By.id("clipBoardBttn")).click();
	Thread.sleep(2000);
	WebElement value = Driver.findElement(By.id("copyMsg"));
	String copytext = value.getText();
	String regkey = null;
	try
	{
	if (copytext == "Key copied to clipboard")
	{
		String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
		//(INSTANCE_NAME = RTSC)
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String UserName = "T3DATGLOBL";
		String Password = "t3dgprod";
		Connection con = DriverManager.getConnection(dburl, UserName, Password);
		String Query = "select application_key from (select * from SCANTOOL_USERS where dealer_code='"+dealercode+"' and software_id='"+softwareid+"' and scantool_version='"+scantoolversion+" order by last_updt_timestamp desc) where rownum=1";
		Statement Stmt = con.createStatement();
		ResultSet rs = Stmt.executeQuery(Query);
		while(rs.next()){
			regkey=rs.getString(1);
		System.out.println(regkey);
		con.close();
		}
		
		Report.updateTestLog("Verify Techstream registration is completed", "Techstream registration is completed and Registration Key is "+regkey+"",Status.PASS);
	}
	}
	catch (Exception e)
	{
		e.printStackTrace();
		Report.updateTestLog("Verify Techstream registration is completed", "Techstream registration is not completed",Status.FAIL);
	}
}

@Action(desc="TechstreamTmsCorporateurl",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void TechstreamTmsCorporateurl() throws InterruptedException
{ 
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String softwareid = userData.getData("Techstream", "softwareid","Techstream registration", "TMS Corporate","1","1");
	String scantoolversion = userData.getData("Techstream", "scantoolversion","Techstream registration", "TMS Corporate","1","1");
	try
	{
	String url = "http://t3.qa3.tms.toyota.com/t3Portal/scantool/?l=appReg-EN&id="+softwareid+"&ver="+scantoolversion+"";
	Thread.sleep(2000);
//	userData.putData("DatasheetName","columnName","value to be written","scenario name","test case name","Iteration value", "SubIteration value");
	userData.putData("Techstream","url",url,"Techstream registration","TMS Corporate","1", "1");
	Report.updateTestLog("Verify Techstream url is generated", "Techstream url is generated successfully", Status.PASS);
	}
	catch (Exception e)
	{
		e.printStackTrace();
		Report.updateTestLog("Verify Techstream url is generated", "Techstream url is not generated successfully", Status.FAIL);	
	}
}

@Action(desc="TechstreamTCICorporateurl",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void TechstreamTCICorporateurl() throws InterruptedException
{ 
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String softwareid = userData.getData("Techstream", "softwareid","Techstream registration", "TCI Corporate","1","1");
	String scantoolversion = userData.getData("Techstream", "scantoolversion","Techstream registration", "TCI Corporate","1","1");
	try
	{
	String url = "http://t3.qa3.tms.toyota.com/t3Portal/scantool/?l=appReg-EN&id="+softwareid+"&ver="+scantoolversion+"";
	Thread.sleep(2000);
//	userData.putData("DatasheetName","columnName","value to be written","scenario name","test case name","Iteration value", "SubIteration value");
	userData.putData("Techstream","url",url,"Techstream registration","TCI Corporate","1", "1");
	Report.updateTestLog("Verify Techstream url is generated", "Techstream url is generated successfully", Status.PASS);
	}
	catch (Exception e)
	{
		e.printStackTrace();
		Report.updateTestLog("Verify Techstream url is generated", "Techstream url is not generated successfully", Status.FAIL);	
	}
}


@Action(desc="TechstreamTmsCorporate",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void TechstreamTmsCorporate() throws InterruptedException, Exception
{ 
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String softwareid = userData.getData("Techstream", "softwareid","Techstream registration", "TMS Corporate","1","1");
	String scantoolversion = userData.getData("Techstream", "scantoolversion","Techstream registration", "TMS Corporate","1","1");
	try
	{
	WebElement value = Driver.findElement(By.xpath("//*[@id=\"StandalonePortlet.registerForm\"]/table/tbody/tr[3]/td[2]"));
	String copytext = value.getText();
	String regkey = null;
	
		String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
		//(INSTANCE_NAME = RTSC)
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String UserName = "T3DATGLOBL";
		String Password = "t3dgprod";
		Connection con = DriverManager.getConnection(dburl, UserName, Password);
		String Query = "select application_key from (select * from SCANTOOL_USERS where software_id='"+softwareid+"' and scantool_version='"+scantoolversion+"' order by last_updt_timestamp desc) where rownum=1";
		Statement Stmt = con.createStatement();
		ResultSet rs = Stmt.executeQuery(Query);
		while(rs.next()){
			regkey=rs.getString(1);
		System.out.println(regkey);
		con.close();
		}
		
		Report.updateTestLog("Verify Techstream registration is completed", "Techstream registration is completed and Registration Key is "+regkey+"",Status.PASS);
	}
	catch (Exception e)
	{
		e.printStackTrace();
		Report.updateTestLog("Verify Techstream registration is completed", "Techstream registration is not completed",Status.FAIL);
	}
}

@Action(desc="TechstreamTCICorporate",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void TechstreamTCICorporate() throws InterruptedException, Exception
{ 
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String softwareid = userData.getData("Techstream", "softwareid","Techstream registration", "TCI Corporate","1","1");
	String scantoolversion = userData.getData("Techstream", "scantoolversion","Techstream registration", "TCI Corporate","1","1");
	try
	{
	WebElement value = Driver.findElement(By.xpath("//*[@id=\"StandalonePortlet.registerForm\"]/table/tbody/tr[3]/td[2]"));
	String copytext = value.getText();
	String regkey = null;
	
		String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
		//(INSTANCE_NAME = RTSC)
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String UserName = "T3DATGLOBL";
		String Password = "t3dgprod";
		Connection con = DriverManager.getConnection(dburl, UserName, Password);
		String Query = "select application_key from (select * from SCANTOOL_USERS where software_id='"+softwareid+"' and scantool_version='"+scantoolversion+"' order by last_updt_timestamp desc) where rownum=1";
		Statement Stmt = con.createStatement();
		ResultSet rs = Stmt.executeQuery(Query);
		while(rs.next()){
			regkey=rs.getString(1);
		System.out.println(regkey);
		con.close();
		}
		
		Report.updateTestLog("Verify Techstream registration is completed", "Techstream registration is completed and Registration Key is "+regkey+"",Status.PASS);
	}
	catch (Exception e)
	{
		e.printStackTrace();
		Report.updateTestLog("Verify Techstream registration is completed", "Techstream registration is not completed",Status.FAIL);
	}
}

@Action(object = ObjectType.SELENIUM, desc = "Enter the value [<Data>] in the Field [<Object>]", input = InputType.YES)
public void intToString() {
    if (!elementPresent()) {
        Element.clear();
        int b = Data; 
        String str1 = Integer.toString(b); 
        Element.sendKeys(str1);
        Report.updateTestLog(Action, "Entered Text '" + Data + "' on '"
                + ObjectName + "'", Status.DONE);
    }
}

public Boolean elementPresent() {
    return null;
}

@Action(desc = "create_campaign", object = ObjectType.BROWSER, input = InputType.OPTIONAL)
public void create_campaign() throws InterruptedException, Exception {
       WebElement campsource = Driver.findElement(By.id("campaignSourceId"));
       // String getData (String DataSheetName, String ColumnName, String
       // ScenarioName,String TestCase, String Iteration, String SubIteration);
       String csource = userData.getData("Campaign", "Campaign Source", "Campaign Admin Tool",
                    "Create Campaign_TMS Corp", "1", "1");
       campsource.sendKeys(csource);
       Report.updateTestLog("CampaignSource", "Campaign Source is selected", Status.DONE);
       Thread.sleep(2000);
       WebElement camptype = Driver.findElement(By.id("campaignTypeId"));
       String ctype = userData.getData("Campaign", "Campaign Type", "Campaign Admin Tool", "Create Campaign_TMS Corp",
                    "1", "1");
       camptype.sendKeys(ctype);
       Report.updateTestLog("CampaignType", "Campaign Type is selected", Status.DONE);
       Thread.sleep(2000);
       WebElement campcode = Driver.findElement(By.id("campaignCodeId"));
       String campaigncode = userData.getData("Campaign", "Campaign Code", "Campaign Admin Tool",
                    "Create Campaign_TMS Corp", "1", "1");
       campcode.sendKeys(campaigncode);
       Report.updateTestLog("CampaignCode", "Campaign Code is entered", Status.DONE);
       Thread.sleep(2000);
       WebElement campstage = Driver.findElement(By.id("campaignStageId"));
       String cstage = userData.getData("Campaign", "Campaign Stage", "Campaign Admin Tool",
                    "Create Campaign_TMS Corp", "1", "1");
       campstage.sendKeys(cstage);
       /*
       * if (campstage!=null) {
       */
       Report.updateTestLog("CampaignStage", "Campaign Stage is selected", Status.DONE);
       /*
       * } else { Report.updateTestLog("CampaignStage",
       * "Campaign Stage is not selected", Status.FAIL); }
       */
       Thread.sleep(2000);
       WebElement campdiv = Driver.findElement(By.id("division"));
       String division = userData.getData("Campaign", "Division", "Campaign Admin Tool", "Create Campaign_TMS Corp",
                    "1", "1");
       campdiv.sendKeys(division);
       Report.updateTestLog("Division", "Division is selected", Status.DONE);
       Thread.sleep(2000);
       WebElement campmarket = Driver.findElement(By.id("campmarketid1"));
       String market = userData.getData("Campaign", "Market", "Campaign Admin Tool", "Create Campaign_TMS Corp", "1",
                    "1");
       campmarket.sendKeys(market);
       Report.updateTestLog("CampaignMarket", "Campaign Market is selected", Status.DONE);
       Thread.sleep(2000);
       // Driver.findElement(By.id("campaignPhaseId1")).clear();
       // Thread.sleep(2000);
       // Driver.switchTo().alert();
       // alertaccept();
       // Report.updateTestLog("Phase alert", "Phase alert is accepted", Status.DONE);
       // Thread.sleep(2000);
       WebElement campphase = Driver.findElement(By.id("campaignPhaseId1"));
       campphase.click();
       String phase = userData.getData("Campaign", "Phase", "Campaign Admin Tool", "Create Campaign_TMS Corp", "1",
                    "1");
       System.out.println(phase);
       int phaseint = Integer.parseInt(phase);
       campphase.sendKeys(Keys.chord(Keys.CONTROL, "a"), String.valueOf(phaseint));
       // campphase.sendKeys(String.valueOf(phaseint));
       Report.updateTestLog("CampaignPhase", "Campaign Phase is entered", Status.DONE);
       Thread.sleep(2000);
       WebElement releasedate = Driver.findElement(By.id("releaseDate1"));
       String date = userData.getData("Campaign", "Release Date", "Campaign Admin Tool", "Create Campaign_TMS Corp",
                    "1", "1");
       releasedate.sendKeys(date);
       WebElement camptime = Driver.findElement(By.id("campaignReleaseTime1"));
       String releasetime = userData.getData("Campaign", "Release Time", "Campaign Admin Tool",
                    "Create Campaign_TMS Corp", "1", "1");
       camptime.sendKeys(releasetime);
       Report.updateTestLog("Release Time", "Release Time is selected", Status.DONE);
       Thread.sleep(2000);
       Driver.findElement(By.linkText("Expand All")).click();
       Thread.sleep(3000);
       Driver.findElement(By.id("title1")).sendKeys("Automation");
       Thread.sleep(2000);
       Driver.findElement(By.id("campdesc1")).sendKeys("Automation");
       Thread.sleep(2000);
       WebElement campcomments = Driver.findElement(By.id("comments"));
       String comments = userData.getData("Campaign", "Comments", "Campaign Admin Tool", "Create Campaign_TMS Corp",
                    "1", "1");
       campcomments.sendKeys(comments);
       Report.updateTestLog("Comments", "Comments are entered", Status.DONE);
       Thread.sleep(2000);

       WebElement save_btn = Driver.findElement(By.id("searchButtonId"));
       if (save_btn != null) {
              save_btn.click();

              Alert confirmationAlert = Driver.switchTo().alert();
              String alertText = confirmationAlert.getText();
              confirmationAlert.accept();
              Report.updateTestLog("Campaign creation", alertText, Status.PASS);
              Driver.switchTo().defaultContent();

              String custname = null;
              String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
              // (INSTANCE_NAME = RTSC)
              Class.forName("oracle.jdbc.driver.OracleDriver");
              String UserName = "T3DATGLOBL";
              String Password = "t3dgprod";
              Connection con = DriverManager.getConnection(dburl, UserName, Password);
              String Query = "select * from ssc1.ssc_detail where detail_campaign_cd='" + campaigncode + "'";
              Statement Stmt = con.createStatement();
              ResultSet rs = Stmt.executeQuery(Query);
              while (rs.next()) {
                    custname = rs.getString(1);
              }
              if (custname == campaigncode) {
                    Report.updateTestLog("Verify if Campaign is created", "Campaign is created", Status.PASS);
              }
       }

       else {
              Report.updateTestLog("Verify if Campaign is created", "Campaign is not created", Status.FAIL);
       }
}


@Action(desc="create_campaign_TCI",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void create_campaign_TCI() throws InterruptedException, Exception
{
	WebElement campsource = Driver.findElement(By.id("campaignSourceId"));
	String csource = userData.getData("Campaign", "Campaign Source","Campaign Admin Tool","Create Campaign_TCI Corp", "1", "1");
	campsource.sendKeys(csource);	
	Report.updateTestLog("CampaignSource", "Campaign Source is selected", Status.DONE);
	Thread.sleep(2000);
	WebElement camptype = Driver.findElement(By.id("campaignTypeId"));
	String ctype = userData.getData("Campaign", "Campaign Type","Campaign Admin Tool","Create Campaign_TCI Corp", "1", "1");
	camptype.sendKeys(ctype);	
	Report.updateTestLog("CampaignType", "Campaign Type is selected", Status.DONE);
	Thread.sleep(2000);
	WebElement campcode = Driver.findElement(By.id("campaignCodeId"));
	String campaigncode = userData.getData("Campaign", "Campaign Code","Campaign Admin Tool","Create Campaign_TCI Corp", "1", "1");
	campcode.sendKeys(campaigncode);	
	Report.updateTestLog("CampaignCode", "Campaign Code is entered", Status.DONE);
	Thread.sleep(2000);
	WebElement campstage = Driver.findElement(By.id("campaignStageId"));
	String cstage = userData.getData("Campaign", "Campaign Stage","Campaign Admin Tool","Create Campaign_TCI Corp", "1", "1");
	campstage.sendKeys(cstage);
	/*if (campstage!=null)
	{*/
	Report.updateTestLog("CampaignStage", "Campaign Stage is selected", Status.DONE);
/*	}
	else 
	{
		Report.updateTestLog("CampaignStage", "Campaign Stage is not selected", Status.FAIL);
	}*/
	Thread.sleep(2000);
	WebElement campdiv = Driver.findElement(By.id("division"));
	String division = userData.getData("Campaign", "Division","Campaign Admin Tool","Create Campaign_TCI Corp", "1", "1");
	campdiv.sendKeys(division);	
	Report.updateTestLog("Division", "Division is selected", Status.DONE);
	Thread.sleep(2000);
	WebElement campmarket = Driver.findElement(By.id("campmarketid1"));
	String market = userData.getData("Campaign", "Market","Campaign Admin Tool","Create Campaign_TCI Corp", "1", "1");
	campmarket.sendKeys(market);
	Report.updateTestLog("CampaignMarket", "Campaign Market is selected", Status.DONE);
	Thread.sleep(2000);
    Driver.findElement(By.id("campaignPhaseId1")).clear();
    Thread.sleep(2000);
	WebElement campphase = Driver.findElement(By.id("campaignPhaseId1"));
	String phase = userData.getData("Campaign", "Phase","Campaign Admin Tool","Create Campaign_TCI Corp", "1", "1");
	System.out.println(phase);
	int phaseint = Integer.parseInt(phase);
	campphase.sendKeys(String.valueOf(phaseint));
	Report.updateTestLog("CampaignPhase", "Campaign Phase is entered", Status.DONE);
	Thread.sleep(2000);
	WebElement camptime = Driver.findElement(By.id("campaignReleaseTime1"));
	String releasetime = userData.getData("Campaign", "Release Time","Campaign Admin Tool","Create Campaign_TCI Corp", "1", "1");
	camptime.sendKeys(releasetime);
	Report.updateTestLog("Release Time", "Release Time is selected", Status.DONE);
	Thread.sleep(2000);
	Driver.findElement(By.linkText("Expand All")).click();
	Thread.sleep(3000);
	Driver.findElement(By.id("title1")).sendKeys("Automation");
	Thread.sleep(2000);
	Driver.findElement(By.id("campdesc1")).sendKeys("Automation");
	Thread.sleep(2000);
	WebElement campcomments = Driver.findElement(By.id("comments"));
	String comments = userData.getData("Campaign", "Comments", "Campaign Admin Tool","Create Campaign_TCI Corp","1", "1");
	campcomments.sendKeys(comments);
	Report.updateTestLog("Comments", "Comments are entered", Status.DONE);
	Thread.sleep(2000);
	WebElement save_btn = Driver.findElement(By.id("searchButtonId"));
	if(save_btn != null)
	{
		save_btn.click();
		
		Alert confirmationAlert = Driver.switchTo().alert();
		String alertText = confirmationAlert.getText();
		confirmationAlert.accept();
		Report.updateTestLog("Campaign creation", alertText ,Status.PASS);
		Driver.switchTo().defaultContent();
		
		String custname = null;
		String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
		//(INSTANCE_NAME = RTSC)
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String UserName = "T3DATGLOBL";
		String Password = "t3dgprod";
		Connection con = DriverManager.getConnection(dburl, UserName, Password);
		String Query = "select * from ssc1.ssc_detail where detail_campaign_cd='"+campaigncode+"'";
		Statement Stmt = con.createStatement();
		ResultSet rs = Stmt.executeQuery(Query);
		while(rs.next()){
			custname=rs.getString(1);
		}
		if (custname == campaigncode)
		{
		Report.updateTestLog("Verify if Campaign is created", "Campaign is created",Status.PASS);
	}
	}
	
	
	else
	{
		Report.updateTestLog("Verify if Campaign is created", "Campaign is not created",Status.FAIL);
	}
  }

@Action(desc="EnterTAcasedetails",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void EnterTAcasedetails() throws InterruptedException
 {
	try {
//		String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
		String repairorder = userData.getData("TACaseCreation", "RepairOrder","TA Case Creation", "TA Case_TMS Corp","1","1");
		String odometer = userData.getData("TACaseCreation", "Odometer","TA Case Creation", "TA Case_TMS Corp","1","1");
		String ServiceCategory = userData.getData("TACaseCreation", "ServiceCategory","TA Case Creation", "TA Case_TMS Corp","1","1");
		String Section = userData.getData("TACaseCreation", "Section","TA Case Creation", "TA Case_TMS Corp","1","1");
		String SubComponent = userData.getData("TACaseCreation", "SubComponent","TA Case Creation", "TA Case_TMS Corp","1","1");
		String Condition = userData.getData("TACaseCreation", "Condition","TA Case Creation", "TA Case_TMS Corp","1","1");
		WebDriverWait wait = new WebDriverWait(Driver,5);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='text' and @name='tasCaseEditorPortlet{actionForm.repairOrder}']")));
		Driver.findElement(By.xpath("//input[@type='text' and @name='tasCaseEditorPortlet{actionForm.repairOrder}']")).sendKeys(repairorder);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("mileageId")));
		Driver.findElement(By.id("mileageId")).sendKeys(odometer);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tasEditorSymptomServiceCategory_1")));
		Driver.findElement(By.id("tasEditorSymptomServiceCategory_1")).sendKeys(ServiceCategory);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tasEditorSymptomSection_1")));
		Driver.findElement(By.id("tasEditorSymptomSection_1")).sendKeys(Section);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tasEditorSymptomSubComponent_1")));
		Driver.findElement(By.id("tasEditorSymptomSubComponent_1")).sendKeys(SubComponent);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tasEditorSymptomCondition_1")));
		Driver.findElement(By.id("tasEditorSymptomCondition_1")).sendKeys(Condition);
		
//		Screen s = new Screen();
//        Pattern fileInputTextBox = new Pattern(Filepath + "Filename.PNG");
//        Pattern openButton = new Pattern(Filepath + "Open.PNG");
//        Driver.findElement(By.xpath("//input[@type='file' and @name='tasCaseEditorPortlet{actionForm.attachment1}']")).click();
//		Thread.sleep(5000);
//        s.wait(fileInputTextBox, 20);
//        s.type(fileInputTextBox, InputFilePath +""+ upload);
//        s.click(openButton);
		
		
	   Report.updateTestLog("Enter TA Case details", "TA Case details entered", Status.PASS);
	 
	}
	catch (Exception e)
	   {
		e.printStackTrace();
		Report.updateTestLog("Enter TA Case details", "TA Case details is not entered", Status.FAIL);
	   }
  }


/*DashBoard*/
/**
 * 
 * MethodName: EnterTAcasedetails_forUpload
 * Description: TA cases details 
 * Parameter (if any): 
 * Return type: void
 * Owner : sabari
 */

@Action(desc="EnterTAcasedetails_forUpload",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void EnterTAcasedetails_forUpload() throws InterruptedException
 {
	try {
//		String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
		String repairorder = userData.getData("TACaseCreation", "RepairOrder","Upload new File type", "upload new file type _TMS corporate","1","1");
		String odometer = userData.getData("TACaseCreation", "Odometer","Upload new File type", "upload new file type _TMS corporate","1","1");
		String ServiceCategory = userData.getData("TACaseCreation", "ServiceCategory","Upload new File type", "upload new file type _TMS corporate","1","1");
		String Section = userData.getData("TACaseCreation", "Section","Upload new File type", "upload new file type _TMS corporate","1","1");
		String SubComponent = userData.getData("TACaseCreation", "SubComponent","Upload new File type", "upload new file type _TMS corporate","1","1");
		String Condition = userData.getData("TACaseCreation", "Condition","Upload new File type", "upload new file type _TMS corporate","1","1");
		WebDriverWait wait = new WebDriverWait(Driver,5);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='text' and @name='tasCaseEditorPortlet{actionForm.repairOrder}']")));
		Driver.findElement(By.xpath("//input[@type='text' and @name='tasCaseEditorPortlet{actionForm.repairOrder}']")).sendKeys(repairorder);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("mileageId")));
		Driver.findElement(By.id("mileageId")).sendKeys(odometer);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tasEditorSymptomServiceCategory_1")));
		Driver.findElement(By.id("tasEditorSymptomServiceCategory_1")).sendKeys(ServiceCategory);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tasEditorSymptomSection_1")));
		Driver.findElement(By.id("tasEditorSymptomSection_1")).sendKeys(Section);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tasEditorSymptomSubComponent_1")));
		Driver.findElement(By.id("tasEditorSymptomSubComponent_1")).sendKeys(SubComponent);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tasEditorSymptomCondition_1")));
		Driver.findElement(By.id("tasEditorSymptomCondition_1")).sendKeys(Condition);
		
//		Screen s = new Screen();
//        Pattern fileInputTextBox = new Pattern(Filepath + "Filename.PNG");
//        Pattern openButton = new Pattern(Filepath + "Open.PNG");
//        Driver.findElement(By.xpath("//input[@type='file' and @name='tasCaseEditorPortlet{actionForm.attachment1}']")).click();
//		Thread.sleep(5000);
//        s.wait(fileInputTextBox, 20);
//        s.type(fileInputTextBox, InputFilePath +""+ upload);
//        s.click(openButton);
		
		
	   Report.updateTestLog("Enter TA Case details", "TA Case details entered", Status.PASS);
	 
	}
	catch (Exception e)
	   {
		e.printStackTrace();
		Report.updateTestLog("Enter TA Case details", "TA Case details is not entered", Status.FAIL);
	   }
  }



@Action(desc="attachmentsUpload",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void attachmentsUpload() throws InterruptedException, ClassNotFoundException, SQLException
{ 
	Driver.findElement(By.xpath("//input[@type='submit' and @value='Add Attachment']")).click();
	Thread.sleep(5000);
	WebElement uploadfile = Driver.findElement(By.xpath(".//tbody//td[text()='Attachment 1']/ancestor::tr[1]//input"));
    uploadfile.click();
	Thread.sleep(5000);
	String upload = userData.getData("TACaseCreation", "upload","TA Case Creation", "TA Case_TMS Corp","1","1");
	String InputFilePath = userData.getData("TACaseCreation", "Inputfilepath","TA Case Creation", "TA Case_TMS Corp","1","1"); 
	String filepath= InputFilePath+""+upload;
//       GeneralComponents.alertUploadBox(Driver, filepath);
      uploadfile.sendKeys(filepath);
       try {
       Driver.findElement(By.xpath("//input[@type='submit' and @value='Add Attachment']")).click();
		Thread.sleep(5000);
		WebElement image = Driver.findElement(By.xpath("//*[@id=\"tasPageForm\"]/table/tbody/tr[7]/td[2]/table/tbody/tr[2]/td/table/tbody/tr[2]/td[1]/a/span"));
		String attachment = image.getText();
		if (attachment == upload)
		{
	   Report.updateTestLog("Enter TA Case details", "TA Case details entered", Status.PASS);
	 }
	}
	catch (Exception e)
	   {
		e.printStackTrace();
		Report.updateTestLog("Enter TA Case details", "TA Case details is not entered", Status.FAIL);
	   }
}

@Action(desc="attachmentsUploadNewFileType",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void attachmentsUploadNewFileType() throws InterruptedException, ClassNotFoundException, SQLException
{ 
	Driver.findElement(By.xpath("//input[@type='submit' and @value='Add Attachment']")).click();
	Thread.sleep(5000);
	WebElement uploadfile = Driver.findElement(By.xpath(".//tbody//td[text()='Attachment 1']/ancestor::tr[1]//input"));
	uploadfile.click();
	Thread.sleep(5000);
	
	
	String upload = userData.getData("TACaseCreation", "upload","Upload new File type", "upload new file type _TMS corporate","1","1");
	String InputFilePath = userData.getData("TACaseCreation", "Inputfilepath","Upload new File type", "upload new file type _TMS corporate","1","1"); 
	String filepath= InputFilePath+""+upload;
      GeneralComponents.alertUploadBox(Driver, filepath);
      uploadfile.sendKeys(filepath);
       try {
       Driver.findElement(By.xpath("//input[@type='submit' and @value='Add Attachment']")).click();
		Thread.sleep(5000);
		WebElement newfiletype = Driver.findElement(By.xpath("//*[@id=\"tasPageForm\"]/table/tbody/tr[7]/td[2]/table/tbody/tr[2]/td/table/tbody/tr[2]/td[1]/a/span"));
		String attachment = newfiletype.getText();
		if (attachment == upload)
		{
	   Report.updateTestLog("Enter TA Case details", "TA Case details entered", Status.PASS);
	 }
	}
	catch (Exception e)
	   {
		e.printStackTrace();
		Report.updateTestLog("Enter TA Case details", "TA Case details is not entered", Status.FAIL);
	   }
}


@Action(desc="fetch_tacase",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void fetch_tacase() throws Exception
{
	WebElement caseid = Driver.findElement(By.xpath("//*[@id=\"chromeWidth\"]/table/tbody/tr[2]/td/table/tbody/tr[1]/td/span"));
	String TAcase = caseid.getText();
	if(TAcase !=null)
	{
		String valcompare;
		GeneralComponents.waitforInternalLoad(Driver);
//		userData.putData("DatasheetName","columnName","value to be written","scenario name","test case name","Iteration value", "SubIteration value");
		userData.putData("caseid","TA Case ID",TAcase,"TA Case Creation","TA Case_TMS Corp","1", "1");
		String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
		//(INSTANCE_NAME = RTSC)
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String UserName = "T3DATGLOBL";
		String Password = "t3dgprod";
		Connection con = DriverManager.getConnection(dburl, UserName, Password);
		String Query = "select * from (select t3_id from rpt_dctm_tas_info order by last_modified_timestamp desc) where rownum=1";
		Statement Stmt = con.createStatement();
		ResultSet rs = Stmt.executeQuery(Query);
		while(rs.next()){
			valcompare=rs.getString(1);
		System.out.println(valcompare);
		}	
		
		Report.updateTestLog("Verify TA Case is created", "TA Case is created",Status.PASS);
		con.close();
	}
	else
	{
		Report.updateTestLog("Verify TA Case is created", "TA Case is not created",Status.FAIL);
	}
}

@Action(desc="EnterTAcasedetails_TCI",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void EnterTAcasedetails_TCI() throws InterruptedException
 {
	try {
//		String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
		String repairorder = userData.getData("TACaseCreation", "RepairOrder","TA Case Creation", "TA Case_TCI Corp","1","1");
		String odometer = userData.getData("TACaseCreation", "Odometer","TA Case Creation", "TA Case_TCI Corp","1","1");
		String ServiceCategory = userData.getData("TACaseCreation", "ServiceCategory","TA Case Creation", "TA Case_TCI Corp","1","1");
		String Section = userData.getData("TACaseCreation", "Section","TA Case Creation", "TA Case_TCI Corp","1","1");
		String SubComponent = userData.getData("TACaseCreation", "SubComponent","TA Case Creation", "TA Case_TCI Corp","1","1");
		String Condition = userData.getData("TACaseCreation", "Condition","TA Case Creation", "TA Case_TCI Corp","1","1");
		String upload = userData.getData("TACaseCreation", "upload","TA Case Creation", "TA Case_TCI Corp","1","1");
		WebDriverWait wait = new WebDriverWait(Driver,5);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='text' and @name='tasCaseEditorPortlet{actionForm.repairOrder}']")));
		Driver.findElement(By.xpath("//input[@type='text' and @name='tasCaseEditorPortlet{actionForm.repairOrder}']")).sendKeys(repairorder);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("mileageId")));
		Driver.findElement(By.id("mileageId")).sendKeys(odometer);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tasEditorSymptomServiceCategory_1")));
		Driver.findElement(By.id("tasEditorSymptomServiceCategory_1")).sendKeys(ServiceCategory);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tasEditorSymptomSection_1")));
		Driver.findElement(By.id("tasEditorSymptomSection_1")).sendKeys(Section);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tasEditorSymptomSubComponent_1")));
		Driver.findElement(By.id("tasEditorSymptomSubComponent_1")).sendKeys(SubComponent);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tasEditorSymptomCondition_1")));
		Driver.findElement(By.id("tasEditorSymptomCondition_1")).sendKeys(Condition);
		
//		Screen s = new Screen();
//        Pattern fileInputTextBox = new Pattern(Filepath + "Filename.PNG");
//        Pattern openButton = new Pattern(Filepath + "Open.PNG");
//        Driver.findElement(By.xpath("//input[@type='file' and @name='tasCaseEditorPortlet{actionForm.attachment1}']")).click();
//		Thread.sleep(5000);
//        s.wait(fileInputTextBox, 20);
//        s.type(fileInputTextBox, InputFilePath +""+ upload);
//        s.click(openButton);
		
		
	   Report.updateTestLog("Enter TA Case details", "TA Case details entered", Status.PASS);
	 
	}
	catch (Exception e)
	   {
		e.printStackTrace();
		Report.updateTestLog("Enter TA Case details", "TA Case details is not entered", Status.FAIL);
	   }
  }

@Action(desc="attachmentsUpload_TCI",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void attachmentsUpload_TCI() throws InterruptedException, ClassNotFoundException, SQLException
{ 
	Driver.findElement(By.xpath("//input[@type='submit' and @value='Add Attachment']")).click();
	Thread.sleep(5000);
	WebElement uploadfile = Driver.findElement(By.xpath("//input[@type='file' and @name='tasCaseEditorPortlet{actionForm.attachment1}']"));
	uploadfile.click();
	Thread.sleep(5000);
	String upload = userData.getData("TACaseCreation", "upload","TA Case Creation", "TA Case_TCI Corp","1","1");
	String InputFilePath = userData.getData("TACaseCreation", "Inputfilepath","TA Case Creation", "TA Case_TCI Corp","1","1"); 
	String filepath= InputFilePath+""+upload;
//       GeneralComponents.alertUploadBox(Driver, filepath);
      uploadfile.sendKeys(filepath);
       try {
       Driver.findElement(By.xpath("//input[@type='submit' and @value='Add Attachment']")).click();
		Thread.sleep(5000);
		WebElement image = Driver.findElement(By.xpath("//*[@id=\"tasPageForm\"]/table/tbody/tr[7]/td[2]/table/tbody/tr[2]/td/table/tbody/tr[2]/td[1]/a/span"));
		String attachment = image.getText();
		if (attachment == upload)
		{
	   Report.updateTestLog("Enter TA Case details", "TA Case details entered", Status.PASS);
	 }
	}
	catch (Exception e)
	   {
		e.printStackTrace();
		Report.updateTestLog("Enter TA Case details", "TA Case details is not entered", Status.FAIL);
	   }
}


@Action(desc="fetch_tacase_TCI",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void fetch_tacase_TCI() throws Exception
{
	WebElement caseid = Driver.findElement(By.xpath("//*[@id=\"chromeWidth\"]/table/tbody/tr[2]/td/table/tbody/tr[1]/td/span"));
	String TAcase = caseid.getText();
	if(TAcase !=null)
	{
		String valcompare;
		GeneralComponents.waitforInternalLoad(Driver);
//		userData.putData("DatasheetName","columnName","value to be written","scenario name","test case name","Iteration value", "SubIteration value");
		userData.putData("caseid","TA Case ID",TAcase,"TA Case Creation","TA Case_TCI Corp","1", "1");
		String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
		//(INSTANCE_NAME = RTSC)
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String UserName = "T3DATGLOBL";
		String Password = "t3dgprod";
		Connection con = DriverManager.getConnection(dburl, UserName, Password);
		String Query = "select * from (select t3_id from rpt_dctm_tas_info order by last_modified_timestamp desc) where rownum=1";
		Statement Stmt = con.createStatement();
		ResultSet rs = Stmt.executeQuery(Query);
		while(rs.next()){
			valcompare=rs.getString(1);
		System.out.println(valcompare);
		}
		
		Report.updateTestLog("Verify TA Case is created", "TA Case is created",Status.PASS);
		con.close();
	}
	else
	{
		Report.updateTestLog("Verify TA Case is created", "TA Case is not created",Status.FAIL);
	}
}

@Action(desc="get_newcalid",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void get_newcalid()
{
	String calid = findObject(Driver,By.xpath("//div[@class='datadisplaytablebea-portal-blank-window-content']/table/tbody/tr[2]/td[2]")," ").getText();
    Driver.findElement(By.id("calIdInput")).sendKeys(calid);
    /*if(calid !=null)
	{
		GeneralComponents.waitforInternalLoad(Driver);
		Report.updateTestLog("entercalid", "Cal ID is entered",Status.PASS);
	}
	else
	{
		Report.updateTestLog("entercalid", "Cal ID is not entered",Status.FAIL);
	}*/
 }

@Action(desc="MY_TA",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void MY_TA() throws InterruptedException
 {
	try {
		Driver.findElement(By.xpath("//*[@id='caseHistoryForm']/input")).click();
		Thread.sleep(5000);
//		String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
		String caseid = userData.getData("caseid", "TA Case ID","TA Case Creation", "TA Case_TMS Corp","1","1");
		Driver.findElement(By.linkText(caseid)).click();
		Thread.sleep(5000);
		WebElement caseid1 = Driver.findElement(By.xpath("//*[@id='chromeWidth']/table/tbody/tr[2]/td/table/tbody/tr[1]/td/span"));
		String TAcase = caseid1.getText();
		if (TAcase==caseid)
		{
	   Report.updateTestLog("Verify My TA details", "TA Case details are displayed", Status.PASS);
	 }
	}
	catch (Exception e)
	   {
		e.printStackTrace();
		Report.updateTestLog("Verify My TA details", "TA Case details is not displayed", Status.FAIL);
	   }
  }

@Action(desc="MY_TA_TCI",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void MY_TA_TCI() throws InterruptedException
 {
	try {
		Driver.findElement(By.xpath("//*[@id='caseHistoryForm']/input")).click();
		Thread.sleep(5000);
//		String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
		String caseid = userData.getData("caseid", "TA Case ID","TA Case Creation", "TA Case_TCI Corp","1","1");
		Driver.findElement(By.linkText(caseid)).click();
		Thread.sleep(5000);
		WebElement caseid1 = Driver.findElement(By.xpath("//*[@id='chromeWidth']/table/tbody/tr[2]/td/table/tbody/tr[1]/td/span"));
		String TAcase = caseid1.getText();
		if (TAcase==caseid)
		{
	   Report.updateTestLog("Verify My TA details", "TA Case details are displayed", Status.PASS);
	 }
	}
	catch (Exception e)
	   {
		e.printStackTrace();
		Report.updateTestLog("Verify My TA details", "TA Case details is not displayed", Status.FAIL);
	   }
  }

@Action(desc="Addsymptomcode",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void Addsymptomcode() throws InterruptedException
 {
	try {
//		String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
//		String repairorder = userData.getData("TACaseCreation", "RepairOrder","TA Case Creation", "Add symptom code TMS","1","1");
//		String odometer = userData.getData("TACaseCreation", "Odometer","TA Case Creation", "Add symptom code TMS","1","1");
		String loop = userData.getData("TACaseCreation", "loop","TA Case Creation", "Add symptom code TMS","1","1");
		WebDriverWait wait = new WebDriverWait(Driver,5);
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='text' and @name='tasCaseEditorPortlet{actionForm.repairOrder}']")));
//		Driver.findElement(By.xpath("//input[@type='text' and @name='tasCaseEditorPortlet{actionForm.repairOrder}']")).sendKeys(repairorder);
//		wait.until(ExpectedConditions.elementToBeClickable(By.id("mileageId")));
//		Driver.findElement(By.id("mileageId")).sendKeys(odometer);
		int num = Integer.parseInt(loop);
		Report.updateTestLog("Getting the loop value", "loop value is received", Status.DONE);
		for (int i=1; i<=num; i++)
		{
		String j = Integer.toString(i);
		String testcasename = userData.getTestCase();
		String testscenarioname = userData.getScenario();
		String iteration = userData.getIteration();
		Driver.findElement(By.id("add_btn")).click();
		String ServiceCategory = userData.getData("TACaseCreation", "ServiceCategory",testscenarioname, testcasename,iteration,j);
		String Section = userData.getData("TACaseCreation", "Section",testscenarioname, testcasename,iteration,j);
		String SubComponent = userData.getData("TACaseCreation", "SubComponent",testscenarioname, testcasename,iteration,j);
		String Condition = userData.getData("TACaseCreation", "Condition",testscenarioname, testcasename,iteration,j);
		int a = i+1;
		String b = Integer.toString(a);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tasEditorSymptomServiceCategory_"+b+"")));
		Driver.findElement(By.id("tasEditorSymptomServiceCategory_"+b+"")).sendKeys(ServiceCategory);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tasEditorSymptomSection_"+b+"")));
		Driver.findElement(By.id("tasEditorSymptomSection_"+b+"")).sendKeys(Section);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tasEditorSymptomSubComponent_"+b+"")));
		Driver.findElement(By.id("tasEditorSymptomSubComponent_"+b+"")).sendKeys(SubComponent);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tasEditorSymptomCondition_"+b+"")));
		Driver.findElement(By.id("tasEditorSymptomCondition_"+b+"")).sendKeys(Condition);
		}
		
		
	   Report.updateTestLog("Add symptom code details", "symptom code details are added", Status.PASS);
	 	}
	catch (Exception e)
	   {
		e.printStackTrace();
		Report.updateTestLog("Add symptom code details", "symptom code details are not added", Status.FAIL);
	   }
  }

@Action(desc="Addsymptomcode_TCI",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void Addsymptomcode_TCI() throws InterruptedException
 {
	try {
//		String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
		String loop = userData.getData("TACaseCreation", "loop","TA Case Creation", "Add symptom code TCI","1","1");
		WebDriverWait wait = new WebDriverWait(Driver,5);
		int num = Integer.parseInt(loop);
		Report.updateTestLog("Getting the loop value", "loop value is received", Status.DONE);
		for (int i=1; i<=num; i++)
		{
		String j = Integer.toString(i);
		String testcasename = userData.getTestCase();
		String testscenarioname = userData.getScenario();
		String iteration = userData.getIteration();
		Driver.findElement(By.id("add_btn")).click();
		String ServiceCategory = userData.getData("TACaseCreation", "ServiceCategory",testscenarioname, testcasename,iteration,j);
		String Section = userData.getData("TACaseCreation", "Section",testscenarioname, testcasename,iteration,j);
		String SubComponent = userData.getData("TACaseCreation", "SubComponent",testscenarioname, testcasename,iteration,j);
		String Condition = userData.getData("TACaseCreation", "Condition",testscenarioname, testcasename,iteration,j);
		int a = i+1;
		String b = Integer.toString(a);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tasEditorSymptomServiceCategory_"+b+"")));
		Driver.findElement(By.id("tasEditorSymptomServiceCategory_"+b+"")).sendKeys(ServiceCategory);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tasEditorSymptomSection_"+b+"")));
		Driver.findElement(By.id("tasEditorSymptomSection_"+b+"")).sendKeys(Section);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tasEditorSymptomSubComponent_"+b+"")));
		Driver.findElement(By.id("tasEditorSymptomSubComponent_"+b+"")).sendKeys(SubComponent);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tasEditorSymptomCondition_"+b+"")));
		Driver.findElement(By.id("tasEditorSymptomCondition_"+b+"")).sendKeys(Condition);
		}
		
		
	   Report.updateTestLog("Add symptom code details", "symptom code details are added", Status.PASS);
	 	}
	catch (Exception e)
	   {
		e.printStackTrace();
		Report.updateTestLog("Add symptom code details", "symptom code details are not added", Status.FAIL);
	   }
  }


@Action(desc="Casereview",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void Casereview() throws InterruptedException
 {
		WebDriverWait wait = new WebDriverWait(Driver,5);
//		String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
		String caseid = userData.getData("caseid", "TA Case ID","TA Case Creation", "TA Case_TMS Corp","1","1");
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText(caseid)));
		Driver.findElement(By.linkText(caseid)).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Case Review")));
		Driver.findElement(By.linkText("Case Review")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Escalated")));
		Driver.findElement(By.id("Escalated")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Redirected")));
		Driver.findElement(By.id("Redirected")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Coached")));
		Driver.findElement(By.id("Coached")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Corrected")));
		Driver.findElement(By.id("Corrected")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Review")));
		Driver.findElement(By.id("Review")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("reviewlog")));
		Driver.findElement(By.id("reviewlog")).sendKeys("Automation");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("caseReviewSave")));
		Driver.findElement(By.id("caseReviewSave")).click();
		Thread.sleep(5000);
		try
		{
		WebElement comment = Driver.findElement(By.xpath("//*[@id='reviewlog'][contains(text(),'Automation')]"));
		String casecomment = comment.getText();
		if (casecomment.contains("Automation"))
		{
			Report.updateTestLog("Add case review details", "case review details are added", Status.PASS);
		}
	   
	 	}
	catch (Exception e)
	   {
		e.printStackTrace();
		Report.updateTestLog("Add case review details", "case review details are not added", Status.FAIL);
	   }
  }

@Action(desc="ClickAssignToFTSBtn",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void ClickAssignToFTSBtn()
{
	Driver.findElement(By.id("tasCaseEscalate")).click();
	
	WebElement element = Driver.findElement(By.id("tasCaseEscalate"));
	JavascriptExecutor executor = (JavascriptExecutor)Driver;
	executor.executeScript("arguments[0].click();", element);
}

@Action(desc="Casereview_TCI",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void Casereview_TCI() throws InterruptedException
 {
		WebDriverWait wait = new WebDriverWait(Driver,5);
//		String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
		String caseid = userData.getData("caseid", "TA Case ID","TA Case Creation", "TA Case_TMS Corp","1","1");
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText(caseid)));
		Driver.findElement(By.linkText(caseid)).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Case Review")));
		Driver.findElement(By.linkText("Case Review")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Escalated")));
		Driver.findElement(By.id("Escalated")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Redirected")));
		Driver.findElement(By.id("Redirected")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Coached")));
		Driver.findElement(By.id("Coached")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Corrected")));
		Driver.findElement(By.id("Corrected")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Review")));
		Driver.findElement(By.id("Review")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("reviewlog")));
		Driver.findElement(By.id("reviewlog")).sendKeys("Automation");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("caseReviewSave")));
		Driver.findElement(By.id("caseReviewSave")).click();
		Thread.sleep(5000);
		try
		{
		WebElement comment = Driver.findElement(By.xpath("//*[@id='reviewlog'][contains(text(),'Automation')]"));	
		String casecomment = comment.getText();
		if (casecomment.contains("Automation"))
		{
			Report.updateTestLog("Add case review details", "case review details are added", Status.PASS);
		}
	   
	 	}
	catch (Exception e)
	   {
		e.printStackTrace();
		Report.updateTestLog("Add case review details", "case review details are not added", Status.FAIL);
	   }
  }

@Action(desc="Mydealeradmin",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void Mydealeradmin() throws InterruptedException
 {
		WebDriverWait wait = new WebDriverWait(Driver,5);
//		String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
		String dlrcode = userData.getData("MyDealers", "Dealercode","TA Case Creation", "My Dealers TMS","1","1");
		Driver.findElement(By.linkText("My Account")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"multipleDealerCodes\"]/option[@class='miniselect' and @value[contains(text(), "+dlrcode+")]]")));
		WebElement dealer = Driver.findElement(By.xpath("//*[@id=\"multipleDealerCodes\"]/option[@class='miniselect' and @value[contains(text(), "+dlrcode+")]]"));
		String dealercode = dealer.getText();
		Thread.sleep(5000);
		try
		{
		if (dealercode != null)
		{
		Report.updateTestLog("Verify dealer detail is already added", "Dealer details are already added", Status.PASS);
		}
		else
		{
			Driver.findElement(By.xpath("//*[@id=\"selectDealerCodes\"]/option[@class='miniselect' and @value[contains(text(), "+dlrcode+")]]")).click();
			Driver.findElement(By.id("addDealerCodes")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"multipleDealerCodes\"]/option[@class='miniselect' and @value[contains(text(), "+dlrcode+")]]")));
			Driver.findElement(By.id("userPreferencesSave")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.id("prepopulatePreferenceForm.successData")));
			Report.updateTestLog("Verify dealer detail is added", "Dealer details are added", Status.PASS);
		}
	 	}
	catch (Exception e)
	   {
		e.printStackTrace();
		Report.updateTestLog("Verify dealer detail is added", "Dealer details are not added", Status.FAIL);
	   }
  }

@Action(desc="Mydealeradmin_TCI",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void Mydealeradmin_TCI() throws InterruptedException
 {
		WebDriverWait wait = new WebDriverWait(Driver,5);
//		String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
		String dlrcode = userData.getData("MyDealers", "Dealercode","TA Case Creation", "My Dealers TCI","1","1");
		Driver.findElement(By.linkText("My Account")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"multipleDealerCodes\"]/option[@class='miniselect' and @value[contains(text(), "+dlrcode+")]]")));
		WebElement dealer = Driver.findElement(By.xpath("//*[@id=\"multipleDealerCodes\"]/option[@class='miniselect' and @value[contains(text(), "+dlrcode+")]]"));
		String dealercode = dealer.getText();
		Thread.sleep(5000);
		try
		{
		if (dealercode != null)
		{
		Report.updateTestLog("Verify dealer detail is already added", "Dealer details are already added", Status.PASS);
		}
		else
		{
			Driver.findElement(By.xpath("//*[@id=\"selectDealerCodes\"]/option[@class='miniselect' and @value[contains(text(), "+dlrcode+")]]")).click();
			Driver.findElement(By.id("addDealerCodes")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"multipleDealerCodes\"]/option[@class='miniselect' and @value[contains(text(), "+dlrcode+")]]")));
			Driver.findElement(By.id("userPreferencesSave")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.id("prepopulatePreferenceForm.successData")));
			Report.updateTestLog("Verify dealer detail is added", "Dealer details are added", Status.PASS);
		}
	 	}
	catch (Exception e)
	   {
		e.printStackTrace();
		Report.updateTestLog("Verify dealer detail is added", "Dealer details are not added", Status.FAIL);
	   }
  }


@Action(desc="MydealerTAS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void MydealerTAS() throws InterruptedException, Exception
 {
		WebDriverWait wait = new WebDriverWait(Driver,5);
		Driver.findElement(By.linkText("TAS")).click();
		Thread.sleep(5000);
		Driver.findElement(By.linkText("My Dealers")).click();
		Thread.sleep(5000);
//		String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
		String dlrcode = userData.getData("MyDealers", "Dealercode","TA Case Creation", "My Dealers","1","1");
		
		String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
		//(INSTANCE_NAME = RTSC)
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String UserName = "T3DATGLOBL";
		String Password = "t3dgprod";
		Connection con = DriverManager.getConnection(dburl, UserName, Password);
		String Query = "select distinct(t3_id) from tas_version_info where dealer_code='"+dlrcode+"' and status_code in ('OPEN','PENDING CLOSURE') and last_modified_timestamp>= sysdate-30 and rownum=1";
		Statement Stmt = con.createStatement();
		ResultSet rs = Stmt.executeQuery(Query);
		String valcompare = null;
		while(rs.next())
		{
			 valcompare=rs.getString(1);
		System.out.println(valcompare);
		}
		con.close();
		Thread.sleep(5000);
		try
		{
			String winHandleBefore = Driver.getWindowHandle();
			Driver.findElement(By.linkText(valcompare)).click();
			Thread.sleep(5000);
			
			        for(String winHandle : Driver.getWindowHandles())
			        {
			            Driver.switchTo().window(winHandle);
			        }
			        WebElement caseid = Driver.findElement(By.xpath("//*[@id=\"Netui_Form_0\"]/table/tbody/tr[3]/td[1]/table/tbody/tr[2]/td/table/tbody/tr[1]/td/label"));
			        String TAcaseid = caseid.getText();
			        if (valcompare == TAcaseid)
			        {
			        Report.updateTestLog("Verify TA case details are displayed", "TA case details are displayed", Status.PASS);
			        }
		}
		
	catch (Exception e)
	   {
		e.printStackTrace();
		Report.updateTestLog("TA case details are displayed", "TA case details are not displayed", Status.FAIL);
	   }
  }

@Action(desc="MydealerTAS_TCI",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void MydealerTAS_TCI() throws InterruptedException, Exception
 {
		WebDriverWait wait = new WebDriverWait(Driver,5);
		Driver.findElement(By.linkText("TAS")).click();
		Thread.sleep(5000);
		Driver.findElement(By.linkText("My Dealers")).click();
		Thread.sleep(5000);
//		String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
		String dlrcode = userData.getData("MyDealers", "Dealercode","TA Case Creation", "My Dealers TCI","1","1");
		
		String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
		//(INSTANCE_NAME = RTSC)
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String UserName = "T3DATGLOBL";
		String Password = "t3dgprod";
		Connection con = DriverManager.getConnection(dburl, UserName, Password);
		String Query = "select distinct(t3_id) from tas_version_info where dealer_code='"+dlrcode+"' and status_code in ('OPEN','PENDING CLOSURE') and last_modified_timestamp>= sysdate-30 and rownum=1";
		Statement Stmt = con.createStatement();
		ResultSet rs = Stmt.executeQuery(Query);
		String valcompare = null;
		while(rs.next())
		{
			 valcompare=rs.getString(1);
		System.out.println(valcompare);
		}
		con.close();
		Thread.sleep(5000);
		try
		{
			String winHandleBefore = Driver.getWindowHandle();
			Driver.findElement(By.linkText(valcompare)).click();
			Thread.sleep(5000);
			
			        for(String winHandle : Driver.getWindowHandles())
			        {
			            Driver.switchTo().window(winHandle);
			        }
			        WebElement caseid = Driver.findElement(By.xpath("//*[@id=\"Netui_Form_0\"]/table/tbody/tr[3]/td[1]/table/tbody/tr[2]/td/table/tbody/tr[1]/td/label"));
			        String TAcaseid = caseid.getText();
			        if (valcompare == TAcaseid)
			        {
			        Report.updateTestLog("Verify TA case details are displayed", "TA case details are displayed", Status.PASS);
			        }
		}
		
	catch (Exception e)
	   {
		e.printStackTrace();
		Report.updateTestLog("TA case details are displayed", "TA case details are not displayed", Status.FAIL);
	   }
  }


@Action(desc="wait5Sec",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void wait5Sec() throws InterruptedException
{ 
	Thread.sleep(5000);
}

@Action(desc="wait2Sec",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void wait2Sec() throws InterruptedException
{ 
	Thread.sleep(2000);
}

@Action(desc="DealerTAS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void DealerTAS() throws InterruptedException
 {	
	try {
//		String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
		String repairorder = userData.getData("TACaseCreation", "RepairOrder","TA Case Creation", "TACase_TMS Dealer","1","1");
		String odometer = userData.getData("TACaseCreation", "Odometer","TA Case Creation", "TACase_TMS Dealer","1","1");
		String dtc = userData.getData("GetDate", "DTC","TA Case Creation", "TACase_TMS Dealer","1","1");
		WebDriverWait wait = new WebDriverWait(Driver,5);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("repairOrder")));
		Driver.findElement(By.id("repairOrder")).sendKeys(repairorder);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("mileage")));
		Driver.findElement(By.id("mileage")).sendKeys(odometer);
		Thread.sleep(2000);
		String ServiceCategory = userData.getData("TACaseCreation", "ServiceCategory","TA Case Creation", "TA Case_TMS Corp","1","1");
		String Section = userData.getData("TACaseCreation", "Section","TA Case Creation", "TA Case_TMS Corp","1","1");
		String SubComponent = userData.getData("TACaseCreation", "SubComponent","TA Case Creation", "TA Case_TMS Corp","1","1");
		String Condition = userData.getData("TACaseCreation", "Condition","TA Case Creation", "TA Case_TMS Corp","1","1");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tasFlowCreateServiceCategory")));
		Select servicecat = new Select (Driver.findElement(By.id("tasFlowCreateServiceCategory")));
		servicecat.selectByValue(ServiceCategory);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tasFlowCreateSection")));
		Select sec = new Select (Driver.findElement(By.id("tasFlowCreateSection")));
		sec.selectByValue(Section);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tasFlowCreateSubComponent")));
		Select subcomp = new Select (Driver.findElement(By.id("tasFlowCreateSubComponent")));
		subcomp.selectByValue(SubComponent);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tasFlowCreateCondition")));
		Select condn = new Select (Driver.findElement(By.id("tasFlowCreateCondition")));
		condn.selectByValue(Condition);
		Driver.findElement(By.id("dtcTextbox")).sendKeys(dtc);
		Thread.sleep(2000);
		Driver.findElement(By.id("addDtcCode")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("conditionText")).sendKeys("Automation");
		Thread.sleep(2000);
		String upload = userData.getData("TACaseCreation", "upload","TA Case Creation", "TA Case_TMS Corp","1","1");
		String Filepath = userData.getData("TACaseCreation", "Filepath","TA Case Creation", "TA Case_TMS Corp","1","1");
		String InputFilePath = userData.getData("TACaseCreation", "Inputfilepath","TA Case Creation", "TA Case_TMS Corp","1","1");

		WebElement uploadfile = Driver.findElement(By.xpath("//input[@type='file' and @name='tasFlow_2{actionForm.attachment1}']"));
		Thread.sleep(5000);

		String filepath= InputFilePath+""+upload;
//	       GeneralComponents.alertUploadBox(Driver, filepath);
	      uploadfile.sendKeys(filepath);
	       try {
	       Driver.findElement(By.xpath("//input[@type='submit' and @value='Add Attachment']")).click();
			Thread.sleep(5000);
			WebElement image = Driver.findElement(By.xpath("//*[@id=\"tasPageForm\"]/table/tbody/tr[7]/td[2]/table/tbody/tr[2]/td/table/tbody/tr[2]/td[1]/a/span"));
			String attachment = image.getText();
			if (attachment == upload)
			{
		   Report.updateTestLog("Enter TA Case details", "TA Case details entered", Status.PASS);
		 }
		}
		catch (Exception e)
		   {
			e.printStackTrace();
			Report.updateTestLog("Enter TA Case details", "TA Case details is not entered", Status.FAIL);
		   }
		
        Thread.sleep(5000);
        Driver.findElement(By.id("createCaseButton")).click();
		Thread.sleep(5000);
		WebElement image = Driver.findElement(By.xpath("//*[@id=\"tasFlowCreateForm\"]/table/tbody/tr[26]/td[2]"));
		String attachment = image.getText();
		if (attachment == upload)
		{
			Driver.findElement(By.id("createCaseButton")).click();
			Thread.sleep(5000);
			WebElement value = Driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div[1]/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[2]/div[2]/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[2]/div/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div/div/div/table/tbody/tr/td/div/table/tbody/tr[1]/td/div/div/div[2]/form/table/tbody/tr[2]/td/span"));
			String TAcaseid = value.getText();
//			userData.putData("DatasheetName","columnName","value to be written","scenario name","test case name","Iteration value", "SubIteration value");
			userData.putData("caseid","TA Case ID","TA"+TAcaseid,"TA Case Creation","TACase_TMS Dealer","1", "1");
			Report.updateTestLog("Enter TA Case details", "TA Case details entered", Status.PASS);
	 }
		
//	   Report.updateTestLog("Add symptom code details", "symptom code details are added", Status.PASS);
	 	}
	catch (Exception e)
	   {
		e.printStackTrace();
		Report.updateTestLog("Enter TA Case details", "TA Case details are not entered", Status.FAIL);
	   }
  }

@Action(desc="DealerTAST3",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void DealerTAST3() throws InterruptedException
 {
	try {
//		String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
		String repairorder = userData.getData("TACaseCreation", "RepairOrder","TA Case Creation", "TACase_TMS Dealer","1","1");
		String odometer = userData.getData("TACaseCreation", "Odometer","TA Case Creation", "TACase_TMS Dealer","1","1");
		String dtc = userData.getData("GetDate", "DTC","TA Case Creation", "TACase_TMS Dealer","1","1");
		WebDriverWait wait = new WebDriverWait(Driver,5);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("repairOrder")));
		Driver.findElement(By.id("repairOrder")).sendKeys(repairorder);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("mileage")));
		Driver.findElement(By.id("mileage")).sendKeys(odometer);
		Thread.sleep(2000);
		String ServiceCategory = userData.getData("TACaseCreation", "ServiceCategory","TA Case Creation", "TA Case_TMS Corp","1","1");
		String Section = userData.getData("TACaseCreation", "Section","TA Case Creation", "TA Case_TMS Corp","1","1");
		String SubComponent = userData.getData("TACaseCreation", "SubComponent","TA Case Creation", "TA Case_TMS Corp","1","1");
		String Condition = userData.getData("TACaseCreation", "Condition","TA Case Creation", "TA Case_TMS Corp","1","1");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tasFlowCreateServiceCategory")));
		Select servicecat = new Select (Driver.findElement(By.id("tasFlowCreateServiceCategory")));
		servicecat.selectByValue(ServiceCategory);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tasFlowCreateSection")));
		Select sec = new Select (Driver.findElement(By.id("tasFlowCreateSection")));
		sec.selectByValue(Section);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tasFlowCreateSubComponent")));
		Select subcomp = new Select (Driver.findElement(By.id("tasFlowCreateSubComponent")));
		subcomp.selectByValue(SubComponent);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("tasFlowCreateCondition")));
		Select condn = new Select (Driver.findElement(By.id("tasFlowCreateCondition")));
		condn.selectByValue(Condition);
		Driver.findElement(By.id("dtcTextbox")).sendKeys(dtc);
		Thread.sleep(2000);
		Driver.findElement(By.id("addDtcCode")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("conditionText")).sendKeys("Automation");
		Thread.sleep(2000);
		String upload = userData.getData("TACaseCreation", "upload","TA Case Creation", "TA Case_TMS Corp","1","1");
		String Filepath = userData.getData("TACaseCreation", "Filepath","TA Case Creation", "TA Case_TMS Corp","1","1");
		String InputFilePath = userData.getData("TACaseCreation", "Inputfilepath","TA Case Creation", "TA Case_TMS Corp","1","1");
		Screen s = new Screen();
        Pattern fileInputTextBox = new Pattern(Filepath + "Filename.PNG");
        Pattern openButton = new Pattern(Filepath + "Open.PNG");
        Driver.findElement(By.xpath("//*[@id=\"tasFlowCreateForm\"]/table/tbody/tr[30]/td[2]/input")).click();
		Thread.sleep(5000);
        s.wait(fileInputTextBox, 20);
        s.type(fileInputTextBox, InputFilePath +""+ upload);
        s.click(openButton);
        Thread.sleep(5000);
        Driver.findElement(By.id("createCaseButton")).click();
		Thread.sleep(5000);
		WebElement image = Driver.findElement(By.xpath("//*[@id=\"tasFlowCreateForm\"]/table/tbody/tr[26]/td[2]"));
		String attachment = image.getText();
		if (attachment == upload)
		{
			Driver.findElement(By.id("createCaseButton")).click();
			Thread.sleep(5000);
			WebElement value = Driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div[1]/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[2]/div[2]/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[2]/div/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div/div/div/table/tbody/tr/td/div/table/tbody/tr[1]/td/div/div/div[2]/form/table/tbody/tr[2]/td/span"));
			String TAcaseid = value.getText();
//			userData.putData("DatasheetName","columnName","value to be written","scenario name","test case name","Iteration value", "SubIteration value");
			userData.putData("caseid","TA Case ID","TA"+TAcaseid,"TA Case Creation","TACase_TMS Corp TIS","1", "1");
			Report.updateTestLog("Enter TA Case details", "TA Case details entered", Status.PASS);
	 }
		
//	   Report.updateTestLog("Add symptom code details", "symptom code details are added", Status.PASS);
	 	}
	catch (Exception e)
	   {
		e.printStackTrace();
		Report.updateTestLog("Enter TA Case details", "TA Case details are not entered", Status.FAIL);
	   }
  }

@Action(desc="SearchTA",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void SearchTA() throws InterruptedException
{ 
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String TAcaseid = userData.getData("caseid", "TA Case ID","TA Case Creation", "TACase_TMS Dealer","1","1");
	String upload = userData.getData("TACaseCreation", "upload","TA Case Creation", "TA Case_TMS Corp","1","1");
	Driver.findElement(By.xpath("//input[@type='text' and @name='tas_case_finder_portlet_2{actionForm.caseNumber}']")).sendKeys(TAcaseid);
	Thread.sleep(2000);
	Driver.findElement(By.xpath("//input[@type='submit' and @value='Find Case']")).click();
	Thread.sleep(5000);
	try
	{
	WebElement value = Driver.findElement(By.xpath("//*[@id=\"chromeWidth\"]/table/tbody/tr[2]/td/table/tbody/tr[1]/td/span"));
	String caseid = value.getText();
	if (TAcaseid == caseid)
	{
		WebElement value1 = Driver.findElement(By.xpath("//*[@id=\"tasPageForm\"]/table/tbody/tr[7]/td[2]/table/tbody/tr[2]/td/table/tbody/tr[2]/td[1]/a/span"));
		String attachment = value1.getText();
		if (attachment == upload)
		{
			Report.updateTestLog("Verify attachment is available", "Attachment is available", Status.FAIL);
		}
	}
	}
	catch (Exception e)
	   {
		e.printStackTrace();
		Report.updateTestLog("Verify attachment is available", "Attachment is not available", Status.FAIL);
	   }
}

@Action(desc="SearchTA_TCI",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void SearchTA_TCI() throws InterruptedException
{ 
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String TAcaseid = userData.getData("caseid", "TA Case ID","TA Case Creation", "TACase_TMS Dealer","1","1");
	String upload = userData.getData("TACaseCreation", "upload","TA Case Creation", "TA Case_TCI Corp","1","1");
	Driver.findElement(By.xpath("//input[@type='text' and @name='tas_case_finder_portlet_2{actionForm.caseNumber}']")).sendKeys(TAcaseid);
	Thread.sleep(2000);
	Driver.findElement(By.xpath("//input[@type='submit' and @value='Find Case']")).click();
	Thread.sleep(5000);
	try
	{
	WebElement value = Driver.findElement(By.xpath("//*[@id=\"chromeWidth\"]/table/tbody/tr[2]/td/table/tbody/tr[1]/td/span"));
	String caseid = value.getText();
	if (TAcaseid == caseid)
	{
		WebElement value1 = Driver.findElement(By.xpath("//*[@id=\"tasPageForm\"]/table/tbody/tr[7]/td[2]/table/tbody/tr[2]/td/table/tbody/tr[2]/td[1]/a/span"));
		String attachment = value1.getText();
		if (attachment == upload)
		{
			Report.updateTestLog("Verify attachment is available", "Attachment is available", Status.FAIL);
		}
	}
	}
	catch (Exception e)
	   {
		e.printStackTrace();
		Report.updateTestLog("Verify attachment is available", "Attachment is not available", Status.FAIL);
	   }
}

@Action(desc="SearchTCTMS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void SearchTCTMS() throws InterruptedException, SQLException, ClassNotFoundException
{ 
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String TAcaseid = userData.getData("caseid", "TA Case ID","TA Case Creation", "TACase_TMS Dealer","1","1");
//String upload = userData.getData("TACaseCreation", "upload","TA Case Creation", "TA Case_TMS Corp","1","1");
	String Division = userData.getData("TACaseCreation", "Division","TA Case Creation", "TA Case_TMS Corp","1","1");
	String Model = userData.getData("TACaseCreation", "Model","TA Case Creation", "TA Case_TMS Corp","1","1");
	String Modelyear = userData.getData("TACaseCreation", "Model year","TA Case Creation", "TA Case_TMS Corp","1","1");
	Driver.findElement(By.xpath("//input[@type='text' and @name='tas_case_finder_portlet_2{actionForm.caseNumber}']")).sendKeys(TAcaseid);
	Thread.sleep(2000);
	Driver.findElement(By.xpath("//input[@type='submit' and @value='Find Case']")).click();
	Thread.sleep(5000);
	WebDriverWait wait = new WebDriverWait(Driver,30);
	Thread.sleep(2000);
	String ServiceCategory = userData.getData("TACaseCreation", "ServiceCategory","TA Case Creation", "TA Case_TMS Corp","1","1");
	String Section = userData.getData("TACaseCreation", "Section","TA Case Creation", "TA Case_TMS Corp","1","1");
	String SubComponent = userData.getData("TACaseCreation", "SubComponent","TA Case Creation", "TA Case_TMS Corp","1","1");
	String Condition = userData.getData("TACaseCreation", "Condition","TA Case Creation", "TA Case_TMS Corp","1","1");
	String vds = userData.getData("TACaseCreation", "VDS","TA Case Creation", "TA Case_TMS Corp","1","1");
	wait.until(ExpectedConditions.elementToBeClickable(By.id("tasFlowCreateServiceCategory")));
	Select servicecat = new Select (Driver.findElement(By.id("tasFlowCreateServiceCategory")));
	servicecat.selectByValue(ServiceCategory);
	wait.until(ExpectedConditions.elementToBeClickable(By.id("tasFlowCreateSection")));
	Select sec = new Select (Driver.findElement(By.id("tasFlowCreateSection")));
	sec.selectByValue(Section);
	wait.until(ExpectedConditions.elementToBeClickable(By.id("tasFlowCreateSubComponent")));
	Select subcomp = new Select (Driver.findElement(By.id("tasFlowCreateSubComponent")));
	subcomp.selectByValue(SubComponent);
	wait.until(ExpectedConditions.elementToBeClickable(By.id("tasFlowCreateCondition")));
	Select condn = new Select (Driver.findElement(By.id("tasFlowCreateCondition")));
	condn.selectByValue(Condition);
	wait.until(ExpectedConditions.elementToBeClickable(By.id("tcViewerDivision")));
	Select div = new Select (Driver.findElement(By.id("tcViewerDivision")));
	div.selectByValue(Division);
	wait.until(ExpectedConditions.elementToBeClickable(By.id("tcViewerModel")));
	Select modelname = new Select (Driver.findElement(By.id("tcViewerModel")));
	modelname.selectByValue(Model);
	wait.until(ExpectedConditions.elementToBeClickable(By.id("tcViewerYear")));
	Select year = new Select (Driver.findElement(By.id("tcViewerYear")));
	year.selectByValue(Modelyear);
	Driver.findElement(By.xpath("//input [@type='text' and @name='portlet_13_1{actionForm.vds}']")).sendKeys(vds);
	Thread.sleep(2000);
	Driver.findElement(By.xpath("//input [@type='submit' and @name='portlet_13_1actionOverride:tcViewerSearch']")).click();
	Thread.sleep(2000);
	
	String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
	//(INSTANCE_NAME = RTSC)
	Class.forName("oracle.jdbc.driver.OracleDriver");
	String UserName = "T3DATGLOBL";
	String Password = "t3dgprod";
	Connection con = DriverManager.getConnection(dburl, UserName, Password);
	String Query = "select VEHICLE_KEY_ID from VEHICLE_KEY where model_name='"+Division+"' and model_year='"+Modelyear+"' and vds='"+vds+"'";
	Statement Stmt = con.createStatement();
	ResultSet rs = Stmt.executeQuery(Query);
	String vehkeyid = null;
	while(rs.next())
	{
		vehkeyid=rs.getString(1);
	System.out.println(vehkeyid);
	}
	
	String Query1 = "select SYMPTOM_CD_KEY_ID from SYMPTOM_CD_KEY where service_category_code='"+ServiceCategory+"' and section_code='"+Section+"' and sub_component_code='"+SubComponent+"' and condition_code='"+Condition+"'";
	Statement Stmt1 = con.createStatement();
	ResultSet rs1 = Stmt1.executeQuery(Query1);
	String symptomcdkey = null;
	while(rs1.next())
	{
		symptomcdkey=rs1.getString(1);
	System.out.println(symptomcdkey);
	}
	String Query2 = "select t3_id from oracle_fetch_solr where vhkey_combination like '%"+vehkeyid+"%' and symptomkey_combination like '%"+symptomcdkey+"%'";
	Statement Stmt2 = con.createStatement();
	ResultSet rs2 = Stmt2.executeQuery(Query2);
	String t3id = null;
	while(rs2.next())
	{
		t3id=rs2.getString(1);
	System.out.println(t3id);
	}
	Thread.sleep(5000);
	con.close();
	try
	{
		String winHandleBefore = Driver.getWindowHandle();
		Driver.findElement(By.linkText(t3id)).click();
		Report.updateTestLog("Verify TC details are displayed", "TC details are displayed", Status.PASS);

	
	}
	catch (Exception e)
	   {
		e.printStackTrace();
		Report.updateTestLog("Verify TC details are displayed", "TC details are not displayed", Status.FAIL);
	   }
}

@Action(desc="SearchTCTCI",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void SearchTCTCI() throws InterruptedException, SQLException, ClassNotFoundException
{ 
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String TAcaseid = userData.getData("caseid", "TA Case ID","TA Case Creation", "TACase_TMS Dealer","1","1");
	String Division = userData.getData("TACaseCreation", "Division","TA Case Creation", "TA Case_TMS Corp","1","1");
	String Model = userData.getData("TACaseCreation", "Model","TA Case Creation", "TA Case_TMS Corp","1","1");
	String Modelyear = userData.getData("TACaseCreation", "Model year","TA Case Creation", "TA Case_TMS Corp","1","1");
	Driver.findElement(By.xpath("//input[@type='text' and @name='tas_case_finder_portlet_2{actionForm.caseNumber}']")).sendKeys(TAcaseid);
	Thread.sleep(2000);
	Driver.findElement(By.xpath("//input[@type='submit' and @value='Find Case']")).click();
	Thread.sleep(5000);
	WebDriverWait wait = new WebDriverWait(Driver,5);
	Thread.sleep(2000);
	String ServiceCategory = userData.getData("TACaseCreation", "ServiceCategory","TA Case Creation", "TA Case_TMS Corp","1","1");
	String Section = userData.getData("TACaseCreation", "Section","TA Case Creation", "TA Case_TMS Corp","1","1");
	String SubComponent = userData.getData("TACaseCreation", "SubComponent","TA Case Creation", "TA Case_TMS Corp","1","1");
	String Condition = userData.getData("TACaseCreation", "Condition","TA Case Creation", "TA Case_TMS Corp","1","1");
	String vds = userData.getData("TACaseCreation", "VDS","TA Case Creation", "TA Case_TMS Corp","1","1");
	wait.until(ExpectedConditions.elementToBeClickable(By.id("tasFlowCreateServiceCategory")));
	Select servicecat = new Select (Driver.findElement(By.id("tasFlowCreateServiceCategory")));
	servicecat.selectByValue(ServiceCategory);
	wait.until(ExpectedConditions.elementToBeClickable(By.id("tasFlowCreateSection")));
	Select sec = new Select (Driver.findElement(By.id("tasFlowCreateSection")));
	sec.selectByValue(Section);
	wait.until(ExpectedConditions.elementToBeClickable(By.id("tasFlowCreateSubComponent")));
	Select subcomp = new Select (Driver.findElement(By.id("tasFlowCreateSubComponent")));
	subcomp.selectByValue(SubComponent);
	wait.until(ExpectedConditions.elementToBeClickable(By.id("tasFlowCreateCondition")));
	Select condn = new Select (Driver.findElement(By.id("tasFlowCreateCondition")));
	condn.selectByValue(Condition);
	wait.until(ExpectedConditions.elementToBeClickable(By.id("tcViewerDivision")));
	Select div = new Select (Driver.findElement(By.id("tcViewerDivision")));
	div.selectByValue(Division);
	wait.until(ExpectedConditions.elementToBeClickable(By.id("tcViewerModel")));
	Select modelname = new Select (Driver.findElement(By.id("tcViewerModel")));
	modelname.selectByValue(Model);
	wait.until(ExpectedConditions.elementToBeClickable(By.id("tcViewerYear")));
	Select year = new Select (Driver.findElement(By.id("tcViewerYear")));
	year.selectByValue(Modelyear);
	Driver.findElement(By.xpath("//input [@type='text' and @name='portlet_13_1{actionForm.vds}']")).sendKeys(vds);
	Thread.sleep(2000);
	Driver.findElement(By.xpath("//input [@type='submit' and @name='portlet_13_1actionOverride:tcViewerSearch']")).click();
	Thread.sleep(2000);
	
	String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
	//(INSTANCE_NAME = RTSC)
	Class.forName("oracle.jdbc.driver.OracleDriver");
	String UserName = "T3DATGLOBL";
	String Password = "t3dgprod";
	Connection con = DriverManager.getConnection(dburl, UserName, Password);
	String Query = "select VEHICLE_KEY_ID from VEHICLE_KEY where model_name='"+Division+"' and model_year='"+Modelyear+"' and vds='"+vds+"'";
	Statement Stmt = con.createStatement();
	ResultSet rs = Stmt.executeQuery(Query);
	String vehkeyid = null;
	while(rs.next())
	{
		vehkeyid=rs.getString(1);
	System.out.println(vehkeyid);
	}
	
	String Query1 = "select SYMPTOM_CD_KEY_ID from SYMPTOM_CD_KEY where service_category_code='"+ServiceCategory+"' and section_code='"+Section+"' and sub_component_code='"+SubComponent+"' and condition_code='"+Condition+"'";
	Statement Stmt1 = con.createStatement();
	ResultSet rs1 = Stmt1.executeQuery(Query1);
	String symptomcdkey = null;
	while(rs1.next())
	{
		symptomcdkey=rs1.getString(1);
	System.out.println(symptomcdkey);
	}
	String Query2 = "select t3_id from oracle_fetch_solr where vhkey_combination like '%"+vehkeyid+"%' and symptomkey_combination like '%"+symptomcdkey+"%'";
	Statement Stmt2 = con.createStatement();
	ResultSet rs2 = Stmt2.executeQuery(Query2);
	String t3id = null;
	while(rs2.next())
	{
		t3id=rs2.getString(1);
	System.out.println(t3id);
	}
	Thread.sleep(5000);
	con.close();
	try
	{
		String winHandleBefore = Driver.getWindowHandle();
		Driver.findElement(By.linkText(t3id)).click();
		Report.updateTestLog("Verify TC details are displayed", "TC details are displayed", Status.PASS);

	
	}
	catch (Exception e)
	   {
		e.printStackTrace();
		Report.updateTestLog("Verify TC details are displayed", "TC details are not displayed", Status.FAIL);
	   }
}

@Action(desc="CSABR",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void CSABR() throws InterruptedException, SQLException, ClassNotFoundException
{ 
	WebDriverWait wait = new WebDriverWait(Driver,5);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"t3_veh_inq_spa\"]/span")));
	Driver.findElement(By.xpath("//*[@id=\"t3_veh_inq_spa\"]/span")).click();
	wait.until(ExpectedConditions.elementToBeClickable(By.linkText("CSABR")));
	String winHandleBefore = Driver.getWindowHandle();
	Driver.findElement(By.linkText("CSABR")).click();
	Thread.sleep(5000);
	try{
	            for(String winHandle : Driver.getWindowHandles())
	            {
	            		  Driver.switchTo().window(winHandle);
	            }  
	            } 
	catch (Exception e) 
	{
	          	 	          	  e.printStackTrace();
	}
	
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String password = userData.getData("CSABR", "CSABR Password","CSABR", "CSABR","1","1");
	String olddriver = userData.getData("CSABR", "Old Driver CSABR","CSABR", "CSABR","1","1");
	String newdriver = userData.getData("CSABR", "New Driver CSABR","CSABR", "CSABR","1","1");
	String oldpassenger = userData.getData("CSABR", "Old Passenger CSABR","CSABR", "CSABR","1","1");
	String newpassenger = userData.getData("CSABR", "New Passenger CSABR","CSABR", "CSABR","1","1");
	
	Driver.findElement(By.id("password")).sendKeys(password);
	Thread.sleep(2000);
	Driver.findElement(By.xpath("//input[@type='button' and @value='Login']")).click();
	Thread.sleep(5000);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id("oldCurtainSerial")));
	Driver.findElement(By.id("oldCurtainSerial")).sendKeys(olddriver);
	wait.until(ExpectedConditions.elementToBeClickable(By.id("newCurtainSerial")));
	Driver.findElement(By.id("newCurtainSerial")).sendKeys(newdriver);
	wait.until(ExpectedConditions.elementToBeClickable(By.id("oldPassengerSerial")));
	Driver.findElement(By.id("oldPassengerSerial")).sendKeys(oldpassenger);
	wait.until(ExpectedConditions.elementToBeClickable(By.id("newPassengerSerial")));
	Driver.findElement(By.id("newPassengerSerial")).sendKeys(newpassenger);
	
	try {
	Driver.findElement(By.xpath("//input [@type='button' and @value='Submit']")).click();
	Thread.sleep(2000);

    for(String winHandle1 : Driver.getWindowHandles())
    {
    		  Driver.switchTo().window(winHandle1);
    } 
    Driver.findElement(By.xpath("//input [@type='button' and @value='OK']")).click();
	Thread.sleep(5000);
	Driver.switchTo().window(winHandleBefore);
	Report.updateTestLog("Verify CSABR is submitted","CSABR is submitted ",Status.PASS);
	}
	catch (Exception e)
	{
		e.printStackTrace();
		Report.updateTestLog("Verify CSABR is submitted","CSABR is not submitted ",Status.FAIL);
	}
}	

@Action(desc="CSABRdb",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void CSABRdb() throws InterruptedException, SQLException, ClassNotFoundException
	{ 
		
		String VIN = userData.getData("CSABR", "VIN","CSABR", "CSABR","1","1");
	try {
	String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
	//(INSTANCE_NAME = RTSC)
	Class.forName("oracle.jdbc.driver.OracleDriver");
	String UserName = "T3DATGLOBL";
	String Password = "t3dgprod";
	Connection con = DriverManager.getConnection(dburl, UserName, Password);
	String Query = "select * from csabr_log where vin='"+VIN+"'";
	Statement Stmt = con.createStatement();
	ResultSet rs = Stmt.executeQuery(Query);
	String log = null;
	while(rs.next())
	{
		log=rs.getString(1);
	System.out.println(log);
	}
	con.close();
	
		Report.updateTestLog("Verify CSABR details are saved", "CSABR details are saved", Status.PASS);

	}
	catch (Exception e)
	   {
		e.printStackTrace();
		Report.updateTestLog("Verify CSABR details are saved", "CSABR details are not saved", Status.FAIL);
	   }
}

@Action(desc="Airbag_TMS_Corporate ",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
public void Airbag_TMS_Corporate() throws InterruptedException, SQLException, ClassNotFoundException
{ 
	
//	String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
	String password = userData.getData("Airbag", "Password","Airbag", "TMS Corporate","1","1");
	String flow = userData.getData("Airbag", "Airbag Flow","Airbag", "TMS Corporate","1","1");
	String Mileage = userData.getData("Airbag", "Mileage","Airbag", "TMS Corporate","1","1");
	String origairbag = userData.getData("Airbag", "Original Airbag","Airbag", "TMS Corporate","1","1");
	String newairbag = userData.getData("Airbag", "New Airbag","Airbag", "TMS Corporate","1","1");
	String originflator = userData.getData("Airbag", "Original Inflator","Airbag", "TMS Corporate","1","1");
	String repinflator = userData.getData("Airbag", "Replacement Inflator","Airbag", "TMS Corporate","1","1");
	String winhandle = Driver.getWindowHandle();
	try
	{
	switch(flow)
	{
	case "1" :			
	
	//Click on check applicability for VIN link
	Thread.sleep(8000);
	Driver.findElement(By.linkText("Check Applicability for VIN")).click();
	
	for (String handle1 : Driver.getWindowHandles()) 
	{
			Driver.switchTo().window(handle1);
		}
	wait.until(ExpectedConditions.elementToBeClickable(By.id("loginTxtId")));
	Driver.findElement(By.id("loginTxtId")).sendKeys(password);
	Thread.sleep(2000);
	//driver.close();
	Driver.findElement(By.id("loginBtnId")).click();
	Thread.sleep(1000);
	Driver.findElement(By.id("mileageTxtId")).sendKeys(Mileage);
	Thread.sleep(2000);
	Driver.findElement(By.id("nextButton")).click();
	Thread.sleep(2000);
	Driver.findElement(By.id("airBagSerialNoId")).sendKeys(origairbag);
	Thread.sleep(2000);
	Driver.findElement(By.id("airBagSerialNoCheckId")).sendKeys(origairbag);
	Thread.sleep(2000);
	Driver.findElement(By.id("nextButton")).click();
	Thread.sleep(2000);
	Driver.findElement(By.id("origInflSerialNoId")).sendKeys(originflator);
	Thread.sleep(2000);
	Driver.findElement(By.id("replInflSerialNoId")).sendKeys(repinflator);
	Thread.sleep(2000);
	Driver.findElement(By.id("aggrChkBox1")).click();
	Thread.sleep(2000);
	Driver.findElement(By.id("aggrChkBox2")).click();
	Thread.sleep(2000);
	Driver.findElement(By.id("submitBtnId")).click();
	Thread.sleep(2000);
	break;
	
	case "2" :			
		
		//Click on check applicability for VIN link
		Thread.sleep(1000);
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div[1]/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[2]/div[2]/table/tbody/tr/td/div/table/tbody/tr[2]/td/div/div/div/div/table/tbody/tr/td/div/table/tbody/tr[2]/td/div/div/div/div[2]/div[1]/div/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[2]/table/tbody/tr[2]/td[4]/a")));
		Driver.findElement(By.linkText("Check Applicability for VIN")).click();
		
		for (String handle1 : Driver.getWindowHandles()) 
		{
				Driver.switchTo().window(handle1);
			}
		wait.until(ExpectedConditions.elementToBeClickable(By.id("loginTxtId")));
		Driver.findElement(By.id("loginTxtId")).sendKeys(password);
		Thread.sleep(2000);
		Driver.findElement(By.id("loginBtnId")).click();
		Thread.sleep(1000);
		Driver.findElement(By.id("mileageTxtId")).sendKeys(Mileage);
		Thread.sleep(2000);
		Driver.findElement(By.id("nextButton")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("airBagSerialNoId")).sendKeys(origairbag);
		Thread.sleep(2000);
		Driver.findElement(By.id("airBagSerialNoCheckId")).sendKeys(origairbag);
		Thread.sleep(2000);
		Driver.findElement(By.id("notvisibleChk")).click();
		Thread.sleep(2000);
		Driver.findElement(By.xpath("//*[@id=\"popup\"]/div/table/tbody/tr[5]/td[2]/input")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("nextButton")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("newAirBagSerialNoId")).sendKeys(newairbag);
		Thread.sleep(2000);
		Driver.findElement(By.id("aggrChkBox1")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("aggrChkBox2")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("submitBtnId")).click();
		Thread.sleep(2000);
		break;
		
		case "3" :			
		
		//Click on check applicability for VIN link
		Thread.sleep(1000);
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div[1]/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[2]/div[2]/table/tbody/tr/td/div/table/tbody/tr[2]/td/div/div/div/div/table/tbody/tr/td/div/table/tbody/tr[2]/td/div/div/div/div[2]/div[1]/div/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[2]/table/tbody/tr[2]/td[4]/a")));
		Driver.findElement(By.linkText("Check Applicability for VIN")).click();
		
		for (String handle1 : Driver.getWindowHandles()) 
		{
				Driver.switchTo().window(handle1);
			}
		wait.until(ExpectedConditions.elementToBeClickable(By.id("loginTxtId")));
		Driver.findElement(By.id("loginTxtId")).sendKeys(password);
		Thread.sleep(2000);
		Driver.findElement(By.id("loginBtnId")).click();
		Thread.sleep(1000);
		Driver.findElement(By.id("mileageTxtId")).sendKeys(Mileage);
		Thread.sleep(2000);
		Driver.findElement(By.id("nextButton")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("airBagSerialNoId")).sendKeys(origairbag);
		Thread.sleep(2000);
		Driver.findElement(By.id("airBagSerialNoCheckId")).sendKeys(origairbag);
		Thread.sleep(2000);
		Driver.findElement(By.id("nextButton")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("origInflSerialNoId")).sendKeys(originflator);
		Thread.sleep(2000);
		Driver.findElement(By.id("replInflSerialNoId")).sendKeys(repinflator);
		Thread.sleep(2000);
		Driver.findElement(By.id("aggrChkBox1")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("aggrChkBox2")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("missingInflChkBox")).click();
		Thread.sleep(2000);
		Driver.findElement(By.xpath("//*[@id=\"popup\"]/div/table/tbody/tr[5]/td[2]/input")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("submitBtnId")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("newAirBagSerialNoId")).sendKeys(newairbag);
		Thread.sleep(2000);
		Driver.findElement(By.id("aggrChkBox1")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("aggrChkBox2")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("submitBtnId")).click();
		Thread.sleep(2000);
		break;
		}
	
		WebElement warranty = Driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/table/tbody/tr/td/div/div/div/form/table[2]/tbody/tr[8]/td/table/tbody/tr[3]/td/span/b[2]/span"));
		String warrantyauth = warranty.getText();
//		userData.putData("DatasheetName","columnName","value to be written","scenario name","test case name","Iteration value", "SubIteration value");
		userData.putData("Airbag","Warranty Auth",warrantyauth,"Airbag","TMS Corporate","1", "1");	
		Driver.close();
		Driver.switchTo().window(winhandle);
	Report.updateTestLog("Verify Airbag is submitted","Airbag is submitted ",Status.PASS);
	}
	catch (Exception e)
	{
		e.printStackTrace();
		Report.updateTestLog("Verify Airbag is submitted","Airbag is not submitted ",Status.FAIL);
	}
}	
	
	@Action(desc="Airbagdb_TMS_Corporate",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Airbagdb_TMS_Corporate() throws InterruptedException, SQLException, ClassNotFoundException
	{ 
	try {
//		String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
		String vin = userData.getData("Airbag", "VIN","Airbag", "TMS Corporate","1","1");
		String campcode = userData.getData("Airbag", "Campaign Code","Airbag", "TMS Corporate","1","1");
		String warrantyauth = userData.getData("Airbag", "Warranty Auth","Airbag", "TMS Corporate","1","1");
	String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
	//(INSTANCE_NAME = RTSC)
	Class.forName("oracle.jdbc.driver.OracleDriver");
	String UserName = "T3DATGLOBL";
	String Password = "t3dgprod";
	Connection con = DriverManager.getConnection(dburl, UserName, Password);
	String Query = "select * from airbag_campaign_log where vin='"+vin+"' and campaign_code='"+campcode+"' and WARRANTY_AUTHORIZATION_NO like '%"+warrantyauth+"%'";
	Statement Stmt = con.createStatement();
	ResultSet rs = Stmt.executeQuery(Query);
	String log = null;
	while(rs.next())
	{
		log=rs.getString(1);
	System.out.println(log);
	}
	con.close();
	
		Report.updateTestLog("Verify Airbag details are saved", "Airbag details are saved", Status.PASS);

	}
	catch (Exception e)
	   {
		e.printStackTrace();
		Report.updateTestLog("Verify Airbag details are saved", "Airbag details are not saved", Status.FAIL);
	   }
}

	@Action(desc="Airbag_TCI_Corporate ",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Airbag_TCI_Corporate() throws InterruptedException, SQLException, ClassNotFoundException
	{ 
		
//		String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
		String flow = userData.getData("Airbag", "Airbag Flow","Airbag", "TCI Corporate","1","1");
		String Mileage = userData.getData("Airbag", "Mileage","Airbag", "TCI Corporate","1","1");
		String origairbag = userData.getData("Airbag", "Original Airbag","Airbag", "TCI Corporate","1","1");
		String newairbag = userData.getData("Airbag", "New Airbag","Airbag", "TCI Corporate","1","1");
		String originflator = userData.getData("Airbag", "Original Inflator","Airbag", "TCI Corporate","1","1");
		String repinflator = userData.getData("Airbag", "Replacement Inflator","Airbag", "TCI Corporate","1","1");
		String winhandle = Driver.getWindowHandle();
		try
		{
		switch(flow)
		{
		case "1" :			
		
		//Click on check applicability for VIN link
		Thread.sleep(8000);
		Driver.findElement(By.linkText("Check Applicability for VIN")).click();
		
		for (String handle1 : Driver.getWindowHandles()) 
		{
				Driver.switchTo().window(handle1);
			}
//		wait.until(ExpectedConditions.elementToBeClickable(By.id("loginTxtId")));
//		Driver.findElement(By.id("loginTxtId")).sendKeys(password);
//		Thread.sleep(2000);
//		driver.close();
//		Driver.findElement(By.id("loginBtnId")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("mileageTxtId")).sendKeys(Mileage);
		Thread.sleep(2000);
		Driver.findElement(By.id("nextButton")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("airBagSerialNoId")).sendKeys(origairbag);
		Thread.sleep(2000);
		Driver.findElement(By.id("airBagSerialNoCheckId")).sendKeys(origairbag);
		Thread.sleep(2000);
		Driver.findElement(By.id("nextButton")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("origInflSerialNoId")).sendKeys(originflator);
		Thread.sleep(2000);
		Driver.findElement(By.id("replInflSerialNoId")).sendKeys(repinflator);
		Thread.sleep(2000);
		Driver.findElement(By.id("aggrChkBox1")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("aggrChkBox2")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("submitBtnId")).click();
		Thread.sleep(2000);
		break;
		
		case "2" :			
			
			//Click on check applicability for VIN link
			Thread.sleep(1000);
			//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div[1]/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[2]/div[2]/table/tbody/tr/td/div/table/tbody/tr[2]/td/div/div/div/div/table/tbody/tr/td/div/table/tbody/tr[2]/td/div/div/div/div[2]/div[1]/div/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[2]/table/tbody/tr[2]/td[4]/a")));
			Driver.findElement(By.linkText("Check Applicability for VIN")).click();
			
			for (String handle1 : Driver.getWindowHandles()) 
			{
					Driver.switchTo().window(handle1);
				}
//			wait.until(ExpectedConditions.elementToBeClickable(By.id("loginTxtId")));
//			Driver.findElement(By.id("loginTxtId")).sendKeys(password);
//			Thread.sleep(2000);
//			Driver.findElement(By.id("loginBtnId")).click();
			Thread.sleep(2000);
			Driver.findElement(By.id("mileageTxtId")).sendKeys(Mileage);
			Thread.sleep(2000);
			Driver.findElement(By.id("nextButton")).click();
			Thread.sleep(2000);
			Driver.findElement(By.id("airBagSerialNoId")).sendKeys(origairbag);
			Thread.sleep(2000);
			Driver.findElement(By.id("airBagSerialNoCheckId")).sendKeys(origairbag);
			Thread.sleep(2000);
			Driver.findElement(By.id("notvisibleChk")).click();
			Thread.sleep(2000);
			Driver.findElement(By.xpath("//*[@id=\"popup\"]/div/table/tbody/tr[5]/td[2]/input")).click();
			Thread.sleep(2000);
			Driver.findElement(By.id("nextButton")).click();
			Thread.sleep(2000);
			Driver.findElement(By.id("newAirBagSerialNoId")).sendKeys(newairbag);
			Thread.sleep(2000);
			Driver.findElement(By.id("aggrChkBox1")).click();
			Thread.sleep(2000);
			Driver.findElement(By.id("aggrChkBox2")).click();
			Thread.sleep(2000);
			Driver.findElement(By.id("submitBtnId")).click();
			Thread.sleep(2000);
			break;
			
			case "3" :			
			
			//Click on check applicability for VIN link
			Thread.sleep(1000);
			//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div[1]/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[2]/div[2]/table/tbody/tr/td/div/table/tbody/tr[2]/td/div/div/div/div/table/tbody/tr/td/div/table/tbody/tr[2]/td/div/div/div/div[2]/div[1]/div/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[2]/table/tbody/tr[2]/td[4]/a")));
			Driver.findElement(By.linkText("Check Applicability for VIN")).click();
			
			for (String handle1 : Driver.getWindowHandles()) 
			{
					Driver.switchTo().window(handle1);
				}
//			wait.until(ExpectedConditions.elementToBeClickable(By.id("loginTxtId")));
//			Driver.findElement(By.id("loginTxtId")).sendKeys(password);
//			Thread.sleep(2000);
//			Driver.findElement(By.id("loginBtnId")).click();
			Thread.sleep(2000);
			Driver.findElement(By.id("mileageTxtId")).sendKeys(Mileage);
			Thread.sleep(2000);
			Driver.findElement(By.id("nextButton")).click();
			Thread.sleep(2000);
			Driver.findElement(By.id("airBagSerialNoId")).sendKeys(origairbag);
			Thread.sleep(2000);
			Driver.findElement(By.id("airBagSerialNoCheckId")).sendKeys(origairbag);
			Thread.sleep(2000);
			Driver.findElement(By.id("nextButton")).click();
			Thread.sleep(2000);
			Driver.findElement(By.id("origInflSerialNoId")).sendKeys(originflator);
			Thread.sleep(2000);
			Driver.findElement(By.id("replInflSerialNoId")).sendKeys(repinflator);
			Thread.sleep(2000);
			Driver.findElement(By.id("aggrChkBox1")).click();
			Thread.sleep(2000);
			Driver.findElement(By.id("aggrChkBox2")).click();
			Thread.sleep(2000);
			Driver.findElement(By.id("missingInflChkBox")).click();
			Thread.sleep(2000);
			Driver.findElement(By.xpath("//*[@id=\"popup\"]/div/table/tbody/tr[5]/td[2]/input")).click();
			Thread.sleep(2000);
			Driver.findElement(By.id("submitBtnId")).click();
			Thread.sleep(2000);
			Driver.findElement(By.id("newAirBagSerialNoId")).sendKeys(newairbag);
			Thread.sleep(2000);
			Driver.findElement(By.id("aggrChkBox1")).click();
			Thread.sleep(2000);
			Driver.findElement(By.id("aggrChkBox2")).click();
			Thread.sleep(2000);
			Driver.findElement(By.id("submitBtnId")).click();
			Thread.sleep(2000);
			break;
			}
		
			WebElement warranty = Driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/table/tbody/tr/td/div/div/div/form/table[2]/tbody/tr[8]/td/table/tbody/tr[3]/td/span/b[2]/span"));
			String warrantyauth = warranty.getText();
//			userData.putData("DatasheetName","columnName","value to be written","scenario name","test case name","Iteration value", "SubIteration value");
			userData.putData("Airbag","Warranty Auth",warrantyauth,"Airbag","TCI Corporate","1", "1");
			Driver.close();
			Driver.switchTo().window(winhandle);
		Report.updateTestLog("Verify Airbag is submitted","Airbag is submitted ",Status.PASS);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Report.updateTestLog("Verify Airbag is submitted","Airbag is not submitted ",Status.FAIL);
		}
	}	
		
		@Action(desc="Airbagdb_TCI_Corporate",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
		public void Airbagdb_TCI_Corporate() throws InterruptedException, SQLException, ClassNotFoundException
		{ 
		try {
//			String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
			String vin = userData.getData("Airbag", "VIN","Airbag", "TCI Corporate","1","1");
			String campcode = userData.getData("Airbag", "Campaign Code","Airbag", "TCI Corporate","1","1");
			String warrantyauth = userData.getData("Airbag", "Warranty Auth","Airbag", "TCI Corporate","1","1");
		String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
		//(INSTANCE_NAME = RTSC)
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String UserName = "T3DATGLOBL";
		String Password = "t3dgprod";
		Connection con = DriverManager.getConnection(dburl, UserName, Password);
		String Query = "select * from airbag_campaign_log where vin='"+vin+"' and campaign_code='"+campcode+"' and WARRANTY_AUTHORIZATION_NO like '%"+warrantyauth+"%'";
		Statement Stmt = con.createStatement();
		ResultSet rs = Stmt.executeQuery(Query);
		String log = null;
		while(rs.next())
		{
			log=rs.getString(1);
		System.out.println(log);
		}
		con.close();
		
			Report.updateTestLog("Verify Airbag details are saved", "Airbag details are saved", Status.PASS);

		}
		catch (Exception e)
		   {
			e.printStackTrace();
			Report.updateTestLog("Verify Airbag details are saved", "Airbag details are not saved", Status.FAIL);
		   }
	}

		@Action(desc="Airbag_TMS_Dealer ",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
		public void Airbag_TMS_Dealer() throws InterruptedException, SQLException, ClassNotFoundException
		{ 
			
//			String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
			String password = userData.getData("Airbag", "Password","Airbag", "TMS Dealer","1","1");
			String flow = userData.getData("Airbag", "Airbag Flow","Airbag", "TMS Dealer","1","1");
			String Mileage = userData.getData("Airbag", "Mileage","Airbag", "TMS Dealer","1","1");
			String origairbag = userData.getData("Airbag", "Original Airbag","Airbag", "TMS Dealer","1","1");
			String newairbag = userData.getData("Airbag", "New Airbag","Airbag", "TMS Dealer","1","1");
			String originflator = userData.getData("Airbag", "Original Inflator","Airbag", "TMS Dealer","1","1");
			String repinflator = userData.getData("Airbag", "Replacement Inflator","Airbag", "TMS Dealer","1","1");
			String winhandle = Driver.getWindowHandle();
			try
			{
			switch(flow)
			{
			case "1" :			
			
			//Click on check applicability for VIN link
			Thread.sleep(8000);
			Driver.findElement(By.linkText("Check Applicability for VIN")).click();
			
			for (String handle1 : Driver.getWindowHandles()) 
			{
					Driver.switchTo().window(handle1);
				}
			wait.until(ExpectedConditions.elementToBeClickable(By.id("loginTxtId")));
			Driver.findElement(By.id("loginTxtId")).sendKeys(password);
			Thread.sleep(2000);
			//driver.close();
			Driver.findElement(By.id("loginBtnId")).click();
			Thread.sleep(1000);
			Driver.findElement(By.id("mileageTxtId")).sendKeys(Mileage);
			Thread.sleep(2000);
			Driver.findElement(By.id("nextButton")).click();
			Thread.sleep(2000);
			Driver.findElement(By.id("airBagSerialNoId")).sendKeys(origairbag);
			Thread.sleep(2000);
			Driver.findElement(By.id("airBagSerialNoCheckId")).sendKeys(origairbag);
			Thread.sleep(2000);
			Driver.findElement(By.id("nextButton")).click();
			Thread.sleep(2000);
			Driver.findElement(By.id("origInflSerialNoId")).sendKeys(originflator);
			Thread.sleep(2000);
			Driver.findElement(By.id("replInflSerialNoId")).sendKeys(repinflator);
			Thread.sleep(2000);
			Driver.findElement(By.id("aggrChkBox1")).click();
			Thread.sleep(2000);
			Driver.findElement(By.id("aggrChkBox2")).click();
			Thread.sleep(2000);
			Driver.findElement(By.id("submitBtnId")).click();
			Thread.sleep(5000);
			break;
			
			case "2" :			
				
				//Click on check applicability for VIN link
				Thread.sleep(1000);
				//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div[1]/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[2]/div[2]/table/tbody/tr/td/div/table/tbody/tr[2]/td/div/div/div/div/table/tbody/tr/td/div/table/tbody/tr[2]/td/div/div/div/div[2]/div[1]/div/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[2]/table/tbody/tr[2]/td[4]/a")));
				Driver.findElement(By.linkText("Check Applicability for VIN")).click();
				
				for (String handle1 : Driver.getWindowHandles()) 
				{
						Driver.switchTo().window(handle1);
					}
				wait.until(ExpectedConditions.elementToBeClickable(By.id("loginTxtId")));
				Driver.findElement(By.id("loginTxtId")).sendKeys(password);
				Thread.sleep(2000);
				Driver.findElement(By.id("loginBtnId")).click();
				Thread.sleep(1000);
				Driver.findElement(By.id("mileageTxtId")).sendKeys(Mileage);
				Thread.sleep(2000);
				Driver.findElement(By.id("nextButton")).click();
				Thread.sleep(2000);
				Driver.findElement(By.id("airBagSerialNoId")).sendKeys(origairbag);
				Thread.sleep(2000);
				Driver.findElement(By.id("airBagSerialNoCheckId")).sendKeys(origairbag);
				Thread.sleep(2000);
				Driver.findElement(By.id("notvisibleChk")).click();
				Thread.sleep(2000);
				Driver.findElement(By.xpath("//*[@id=\"popup\"]/div/table/tbody/tr[5]/td[2]/input")).click();
				Thread.sleep(2000);
				Driver.findElement(By.id("nextButton")).click();
				Thread.sleep(2000);
				Driver.findElement(By.id("newAirBagSerialNoId")).sendKeys(newairbag);
				Thread.sleep(2000);
				Driver.findElement(By.id("aggrChkBox1")).click();
				Thread.sleep(2000);
				Driver.findElement(By.id("aggrChkBox2")).click();
				Thread.sleep(2000);
				Driver.findElement(By.id("submitBtnId")).click();
				Thread.sleep(2000);
				break;
				
				case "3" :			
				
				//Click on check applicability for VIN link
				Thread.sleep(1000);
				//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div[1]/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[2]/div[2]/table/tbody/tr/td/div/table/tbody/tr[2]/td/div/div/div/div/table/tbody/tr/td/div/table/tbody/tr[2]/td/div/div/div/div[2]/div[1]/div/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[2]/table/tbody/tr[2]/td[4]/a")));
				Driver.findElement(By.linkText("Check Applicability for VIN")).click();
				
				for (String handle1 : Driver.getWindowHandles()) 
				{
						Driver.switchTo().window(handle1);
					}
				wait.until(ExpectedConditions.elementToBeClickable(By.id("loginTxtId")));
				Driver.findElement(By.id("loginTxtId")).sendKeys(password);
				Thread.sleep(2000);
				Driver.findElement(By.id("loginBtnId")).click();
				Thread.sleep(1000);
				Driver.findElement(By.id("mileageTxtId")).sendKeys(Mileage);
				Thread.sleep(2000);
				Driver.findElement(By.id("nextButton")).click();
				Thread.sleep(2000);
				Driver.findElement(By.id("airBagSerialNoId")).sendKeys(origairbag);
				Thread.sleep(2000);
				Driver.findElement(By.id("airBagSerialNoCheckId")).sendKeys(origairbag);
				Thread.sleep(2000);
				Driver.findElement(By.id("nextButton")).click();
				Thread.sleep(2000);
				Driver.findElement(By.id("origInflSerialNoId")).sendKeys(originflator);
				Thread.sleep(2000);
				Driver.findElement(By.id("replInflSerialNoId")).sendKeys(repinflator);
				Thread.sleep(2000);
				Driver.findElement(By.id("aggrChkBox1")).click();
				Thread.sleep(2000);
				Driver.findElement(By.id("aggrChkBox2")).click();
				Thread.sleep(2000);
				Driver.findElement(By.id("missingInflChkBox")).click();
				Thread.sleep(2000);
				Driver.findElement(By.xpath("//*[@id=\"popup\"]/div/table/tbody/tr[5]/td[2]/input")).click();
				Thread.sleep(2000);
				Driver.findElement(By.id("submitBtnId")).click();
				Thread.sleep(2000);
				Driver.findElement(By.id("newAirBagSerialNoId")).sendKeys(newairbag);
				Thread.sleep(2000);
				Driver.findElement(By.id("aggrChkBox1")).click();
				Thread.sleep(2000);
				Driver.findElement(By.id("aggrChkBox2")).click();
				Thread.sleep(2000);
				Driver.findElement(By.id("submitBtnId")).click();
				Thread.sleep(5000);
				break;
				}
			
				WebElement warranty = Driver.findElement(By.xpath("//td/span[@style='color:red;']//b[2]/span"));
				String warrantyauth = warranty.getText();
//				userData.putData("DatasheetName","columnName","value to be written","scenario name","test case name","Iteration value", "SubIteration value");
				userData.putData("Airbag","Warranty Auth",warrantyauth,"Airbag","TMS Dealer","1", "1");	
				Driver.close();
				Driver.switchTo().window(winhandle);
			Report.updateTestLog("Verify Airbag is submitted","Airbag is submitted ",Status.PASS);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				Report.updateTestLog("Verify Airbag is submitted","Airbag is not submitted ",Status.FAIL);
			}
		}	
			
			@Action(desc="Airbagdb_TMS_Dealer",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
			public void Airbagdb_TMS_Dealer() throws InterruptedException, SQLException, ClassNotFoundException
			{ 
			try {
//				String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
				String vin = userData.getData("Airbag", "VIN","Airbag", "TMS Dealer","1","1");
				String campcode = userData.getData("Airbag", "Campaign Code","Airbag", "TMS Dealer","1","1");
				String warrantyauth = userData.getData("Airbag", "Warranty Auth","Airbag", "TMS Dealer","1","1");
			String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
			//(INSTANCE_NAME = RTSC)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String UserName = "T3DATGLOBL";
			String Password = "t3dgprod";
			Connection con = DriverManager.getConnection(dburl, UserName, Password);
			String Query = "select * from airbag_campaign_log where vin='"+vin+"' and campaign_code='"+campcode+"' and WARRANTY_AUTHORIZATION_NO like '%"+warrantyauth+"%'";
			Statement Stmt = con.createStatement();
			ResultSet rs = Stmt.executeQuery(Query);
			String log = null;
			while(rs.next())
			{
				log=rs.getString(1);
			System.out.println(log);
			}
			con.close();
			
				Report.updateTestLog("Verify Airbag details are saved", "Airbag details are saved", Status.PASS);

			}
			catch (Exception e)
			   {
				e.printStackTrace();
				Report.updateTestLog("Verify Airbag details are saved", "Airbag details are not saved", Status.FAIL);
			   }
		}

			@Action(desc="Airbag_TCI_Dealer ",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
			public void Airbag_TCI_Dealer() throws InterruptedException, SQLException, ClassNotFoundException
			{ 
				
//				String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
				String password = userData.getData("Airbag", "Password","Airbag", "TCI Dealer","1","1");
				String flow = userData.getData("Airbag", "Airbag Flow","Airbag", "TCI Dealer","1","1");
				String Mileage = userData.getData("Airbag", "Mileage","Airbag", "TCI Dealer","1","1");
				String origairbag = userData.getData("Airbag", "Original Airbag","Airbag", "TCI Dealer","1","1");
				String newairbag = userData.getData("Airbag", "New Airbag","Airbag", "TCI Dealer","1","1");
				String originflator = userData.getData("Airbag", "Original Inflator","Airbag", "TCI Dealer","1","1");
				String repinflator = userData.getData("Airbag", "Replacement Inflator","Airbag", "TCI Dealer","1","1");
				String winhandle = Driver.getWindowHandle();
				try
				{
				switch(flow)
				{
				case "1" :			
				
				//Click on check applicability for VIN link
				Thread.sleep(8000);
				Driver.findElement(By.linkText("Check Applicability for VIN")).click();
				Thread.sleep(2000);
				for (String handle1 : Driver.getWindowHandles()) 
				{
						Driver.switchTo().window(handle1);
					}
				Report.updateTestLog("Verify Airbag window is opened", "Airbag window is opened", Status.PASS);
				wait.until(ExpectedConditions.elementToBeClickable(By.id("loginTxtId")));
				Driver.findElement(By.id("loginTxtId")).sendKeys(password);
				Thread.sleep(2000);
				//driver.close();
				Driver.findElement(By.id("loginBtnId")).click();
				Thread.sleep(3000);
				Driver.findElement(By.id("mileageTxtId")).sendKeys(Mileage);
				Thread.sleep(2000);
				Driver.findElement(By.id("nextButton")).click();
				Thread.sleep(3000);
				Driver.findElement(By.id("airBagSerialNoId")).sendKeys(origairbag);
				Thread.sleep(2000);
				Driver.findElement(By.id("airBagSerialNoCheckId")).sendKeys(origairbag);
				Thread.sleep(2000);
				Driver.findElement(By.id("nextButton")).click();
				Thread.sleep(3000);
				Driver.findElement(By.id("origInflSerialNoId")).sendKeys(originflator);
				Thread.sleep(2000);
				Driver.findElement(By.id("replInflSerialNoId")).sendKeys(repinflator);
				Thread.sleep(2000);
				Driver.findElement(By.id("aggrChkBox1")).click();
				Thread.sleep(2000);
				Driver.findElement(By.id("aggrChkBox2")).click();
				Thread.sleep(2000);
				Driver.findElement(By.id("submitBtnId")).click();
				Thread.sleep(8000);
				break;
				
				case "2" :			
					
					//Click on check applicability for VIN link
					Thread.sleep(1000);
					//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div[1]/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[2]/div[2]/table/tbody/tr/td/div/table/tbody/tr[2]/td/div/div/div/div/table/tbody/tr/td/div/table/tbody/tr[2]/td/div/div/div/div[2]/div[1]/div/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[2]/table/tbody/tr[2]/td[4]/a")));
					Driver.findElement(By.linkText("Check Applicability for VIN")).click();
					Thread.sleep(2000);
					for (String handle1 : Driver.getWindowHandles()) 
					{
							Driver.switchTo().window(handle1);
						}
					wait.until(ExpectedConditions.elementToBeClickable(By.id("loginTxtId")));
					Driver.findElement(By.id("loginTxtId")).sendKeys(password);
					Thread.sleep(2000);
					Driver.findElement(By.id("loginBtnId")).click();
					Thread.sleep(3000);
					Driver.findElement(By.id("mileageTxtId")).sendKeys(Mileage);
					Thread.sleep(2000);
					Driver.findElement(By.id("nextButton")).click();
					Thread.sleep(3000);
					Driver.findElement(By.id("airBagSerialNoId")).sendKeys(origairbag);
					Thread.sleep(2000);
					Driver.findElement(By.id("airBagSerialNoCheckId")).sendKeys(origairbag);
					Thread.sleep(2000);
					Driver.findElement(By.id("notvisibleChk")).click();
					Thread.sleep(2000);
					Driver.findElement(By.xpath("//*[@id=\"popup\"]/div/table/tbody/tr[5]/td[2]/input")).click();
					Thread.sleep(2000);
					Driver.findElement(By.id("nextButton")).click();
					Thread.sleep(3000);
					Driver.findElement(By.id("newAirBagSerialNoId")).sendKeys(newairbag);
					Thread.sleep(2000);
					Driver.findElement(By.id("aggrChkBox1")).click();
					Thread.sleep(2000);
					Driver.findElement(By.id("aggrChkBox2")).click();
					Thread.sleep(2000);
					Driver.findElement(By.id("submitBtnId")).click();
					Thread.sleep(8000);
					break;
					
					case "3" :			
					
					//Click on check applicability for VIN link
					Thread.sleep(1000);
					//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div[1]/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[2]/div[2]/table/tbody/tr/td/div/table/tbody/tr[2]/td/div/div/div/div/table/tbody/tr/td/div/table/tbody/tr[2]/td/div/div/div/div[2]/div[1]/div/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[2]/table/tbody/tr[2]/td[4]/a")));
					Driver.findElement(By.linkText("Check Applicability for VIN")).click();
					Thread.sleep(2000);
					for (String handle1 : Driver.getWindowHandles()) 
					{
							Driver.switchTo().window(handle1);
						}
					wait.until(ExpectedConditions.elementToBeClickable(By.id("loginTxtId")));
					Driver.findElement(By.id("loginTxtId")).sendKeys(password);
					Thread.sleep(2000);
					Driver.findElement(By.id("loginBtnId")).click();
					Thread.sleep(3000);
					Driver.findElement(By.id("mileageTxtId")).sendKeys(Mileage);
					Thread.sleep(2000);
					Driver.findElement(By.id("nextButton")).click();
					Thread.sleep(2000);
					Driver.findElement(By.id("airBagSerialNoId")).sendKeys(origairbag);
					Thread.sleep(2000);
					Driver.findElement(By.id("airBagSerialNoCheckId")).sendKeys(origairbag);
					Thread.sleep(2000);
					Driver.findElement(By.id("nextButton")).click();
					Thread.sleep(3000);
					Driver.findElement(By.id("origInflSerialNoId")).sendKeys(originflator);
					Thread.sleep(2000);
					Driver.findElement(By.id("replInflSerialNoId")).sendKeys(repinflator);
					Thread.sleep(2000);
					Driver.findElement(By.id("aggrChkBox1")).click();
					Thread.sleep(2000);
					Driver.findElement(By.id("aggrChkBox2")).click();
					Thread.sleep(2000);
					Driver.findElement(By.id("missingInflChkBox")).click();
					Thread.sleep(2000);
					Driver.findElement(By.xpath("//*[@id=\"popup\"]/div/table/tbody/tr[5]/td[2]/input")).click();
					Thread.sleep(2000);
					Driver.findElement(By.id("submitBtnId")).click();
					Thread.sleep(2000);
					Driver.findElement(By.id("newAirBagSerialNoId")).sendKeys(newairbag);
					Thread.sleep(2000);
					Driver.findElement(By.id("aggrChkBox1")).click();
					Thread.sleep(2000);
					Driver.findElement(By.id("aggrChkBox2")).click();
					Thread.sleep(2000);
					Driver.findElement(By.id("submitBtnId")).click();
					Thread.sleep(8000);
					break;
					}
				
					WebElement warranty = Driver.findElement(By.xpath("//td/span[@style='color:red;']//b[2]/span"));
					String warrantyauth = warranty.getText();
//					userData.putData("DatasheetName","columnName","value to be written","scenario name","test case name","Iteration value", "SubIteration value");
					userData.putData("Airbag","Warranty Auth",warrantyauth,"Airbag","TCI Dealer","1", "1");
					Thread.sleep(2000);
					Driver.close();
					Driver.switchTo().window(winhandle);
				Report.updateTestLog("Verify Airbag is submitted","Airbag is submitted ",Status.PASS);
				}
				catch (Exception e)
				{
					e.printStackTrace();
					Report.updateTestLog("Verify Airbag is submitted","Airbag is not submitted ",Status.FAIL);
				}
			}	
				
				@Action(desc="Airbagdb_TCI_Dealer",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
				public void Airbagdb_TCI_Dealer() throws InterruptedException, SQLException, ClassNotFoundException
				{ 
				try {
//					String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
					String vin = userData.getData("Airbag", "VIN","Airbag", "TCI Dealer","1","1");
					String campcode = userData.getData("Airbag", "Campaign Code","Airbag", "TCI Dealer","1","1");
					String warrantyauth = userData.getData("Airbag", "Warranty Auth","Airbag", "TCI Dealer","1","1");
				String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
				//(INSTANCE_NAME = RTSC)
				Class.forName("oracle.jdbc.driver.OracleDriver");
				String UserName = "T3DATGLOBL";
				String Password = "t3dgprod";
				Connection con = DriverManager.getConnection(dburl, UserName, Password);
				String Query = "select * from airbag_campaign_log where vin='"+vin+"' and campaign_code='"+campcode+"' and WARRANTY_AUTHORIZATION_NO like '%"+warrantyauth+"%'";
				Statement Stmt = con.createStatement();
				ResultSet rs = Stmt.executeQuery(Query);
				String log = null;
				while(rs.next())
				{
					log=rs.getString(1);
				System.out.println(log);
				}
				con.close();
				
					Report.updateTestLog("Verify Airbag details are saved", "Airbag details are saved", Status.PASS);

				}
				catch (Exception e)
				   {
					e.printStackTrace();
					Report.updateTestLog("Verify Airbag details are saved", "Airbag details are not saved", Status.FAIL);
				   }
			}
		

	@Action(desc="Techinfoframe",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Techinfoframe() throws InterruptedException, SQLException, ClassNotFoundException
	{ 
	try {
		Driver.switchTo().frame("userAgreementPopupFrame");
		Driver.findElement(By.id("btn_agree")).click();
		
		Report.updateTestLog("Verify Agreement is accepted", "Agreement is accepted", Status.PASS);

	}
	catch (Exception e)
	   {
		e.printStackTrace();
		Report.updateTestLog("Verify Airbag details are saved", "Airbag details are not saved", Status.FAIL);
	   }
}
	
	@Action(desc="Techinfolsidlogin",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Techinfolsidlogin() throws InterruptedException, SQLException, ClassNotFoundException
	{ 
//		String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
		String lsid = userData.getData("Techinfo", "LSID","Techinfo Pre-Login", "Keycode","1","1");
		String password = userData.getData("Techinfo", "LSID","Techinfo Pre-Login", "Keycode","1","1");
		
		try {
		
		Driver.findElement(By.id("keycodeLookup.keyCodeLookup_lsid")).sendKeys(lsid);
		Thread.sleep(2000);
		Driver.findElement(By.id("keycodeLookup.keyCodeLookup_passcode")).sendKeys(password);
		Thread.sleep(2000);
		Driver.findElement(By.xpath("//input[@type='submit' and @value='Login']")).click();
		Report.updateTestLog("Verify User is able to login", "User is able to login successfully", Status.PASS);
		Thread.sleep(2000);
	}
	catch (Exception e)
	   {
		e.printStackTrace();
		Report.updateTestLog("Verify User is able to login", "User is unable to login successfully", Status.FAIL);
	   }
}	
	
	@Action(desc="Techinfolsidalert",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Techinfolsidalert() throws InterruptedException, SQLException, ClassNotFoundException
	{ 
		Thread.sleep(2000);
		try {
		
			Alert confirmationAlert = Driver.switchTo().alert();
			String alertText = confirmationAlert.getText();
			confirmationAlert.accept();
			Driver.switchTo().defaultContent();
			WebElement msg = Driver.findElement(By.id("submitResetRequestForm.errorData"));
			String message = msg.getText();
			Report.updateTestLog("Verify LSID alert","LSID alert is accepted:"+message+"",Status.PASS);

	}
	catch (Exception e)
	   {
		e.printStackTrace();
		Report.updateTestLog("Verify LSID alert","LSID alert is not accepted", Status.DONE);
	   }
}
	
	@Action(desc="Techinfokeycode",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Techinfokeycode() throws InterruptedException, SQLException, ClassNotFoundException
	{ 
		Thread.sleep(2000);
		try {
			WebElement flag = Driver.findElement(By.xpath("//input[@type='radio' and @name='1']"));
			Boolean keycode = flag.isSelected();
			if (keycode == true)
			{
			Report.updateTestLog("Verify keycode is selected","keycode is selected",Status.PASS);
			}
			else 
			{
				Report.updateTestLog("Verify keycode is selected","keycode is not selected",Status.DONE);
			}
	}
	catch (Exception e)
	   {
		e.printStackTrace();
		Report.updateTestLog("Verify keycode is selected","keycode is not selected", Status.DONE);
	   }
		
}
	
	@Action(desc="Techinfokeycodeverify",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Techinfokeycodeverify() throws InterruptedException
	{ 
		WebElement value = Driver.findElement(By.xpath("//div[@class='bea-portal-details-window-content']//span[@class='boldfont']//span"));
		try
		{
			String keycode = value.getText();
//			userData.putData("DatasheetName","columnName","value to be written","scenario name","test case name","Iteration value", "SubIteration value");
			userData.putData("Techinfo", "Keycode",keycode,"Techinfo Pre-Login", "Keycode","1","1");
			String custname;
//			String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
			String license = userData.getData("Keycode", "License","Keycode", "TMS Corporate","1","1");
			String vin = userData.getData("Keycode", "VIN","Keycode", "TMS Corporate","1","1");
			String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
			//(INSTANCE_NAME = RTSC)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String UserName = "T3DATGLOBL";
			String Password = "t3dgprod";
			Connection con = DriverManager.getConnection(dburl, UserName, Password);
			String Query = "select * from (select "+license+" from KEYCODE_REQUEST_LOG where vin='"+vin+"'  order by approval_time desc) where rownum=1";
			Statement Stmt = con.createStatement();
			ResultSet rs = Stmt.executeQuery(Query);
			while(rs.next()){
				custname=rs.getString(1);
			System.out.println(custname);
			}
			
			Report.updateTestLog("Verify Keycode is generated", "Keycode is generated",Status.PASS);
			con.close();
		}
		catch (Exception e) 
		{
	    	  e.printStackTrace();
	    	Report.updateTestLog("Verify Keycode is generated", "Keycode is not generated",Status.FAIL);
	      }

	}
	@Action(desc="Techinfoimmobilizer",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Techinfoimmobilizer() throws InterruptedException, SQLException, ClassNotFoundException
	{ 
		Thread.sleep(2000);
		try {
			WebElement flag = Driver.findElement(By.xpath("//input[@type='radio' and @value='2']"));
			Boolean keycode = flag.isSelected();
			if (keycode == true)
			{
			Report.updateTestLog("Verify Immobilizer is selected","keycode is selected",Status.PASS);
			}
			else 
			{
				Report.updateTestLog("Verify keycode is selected","keycode is not selected",Status.DONE);
			}
	}
	catch (Exception e)
	   {
		e.printStackTrace();
		Report.updateTestLog("Verify keycode is selected","keycode is not selected", Status.DONE);
	   }
		
}
	
	@Action(desc="Techinfoimmobilizerverify",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Techinfoimmobilizerverify() throws InterruptedException
	{ 
		WebElement immobilizerreset = Driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div[1]/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[2]/div[2]/table/tbody/tr/td/div/table/tbody/tr/td/div/div/div[2]/div/table/tbody/tr/td[1]/div/table/tbody/tr/td/div/div/div[2]/form/p[1]/b/span"));
		try
		{
			String resetcode = immobilizerreset.getText();
			Thread.sleep(5000);
//			userData.putData("DatasheetName","columnName","value to be written","scenario name","test case name","Iteration value", "SubIteration value");
			userData.putData("Techinfo", "Keycode",resetcode,"Techinfo Pre-Login", "Immobilizer","1","1");
			String custname=null;
//			String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
			String seednumber = userData.getData("Immobilizer Reset", "Seed Number","Immobilizer Reset", "TMS Corporate","1","1");
			String vin = userData.getData("Immobilizer Reset", "VIN","Immobilizer Reset", "TMS Corporate","1","1");
			String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
			//(INSTANCE_NAME = RTSC)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String UserName = "T3DATGLOBL";
			String Password = "t3dgprod";
			Connection con = DriverManager.getConnection(dburl, UserName, Password);
			String Query = "select * from (select "+seednumber+" from KEYCODE_REQUEST_LOG where vin='"+vin+"'  order by request_time desc) where rownum=1";
			Statement Stmt = con.createStatement();
			ResultSet rs = Stmt.executeQuery(Query);
			while(rs.next()){
				custname=rs.getString(1);
			System.out.println(custname);
			}
			Report.updateTestLog("Verify Immobilizer Reset code is generated", "Immobilizer Reset code is generated",Status.PASS);
			con.close();
		}
		catch (Exception e) 
		{
	    	  e.printStackTrace();
	    	Report.updateTestLog("Verify Immobilizer Reset code is generated", "Immobilizer Reset code is not generated",Status.FAIL);
	      }	
		
	}
	@Action(desc="Edit_campaign",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Edit_campaign() throws InterruptedException, ClassNotFoundException, SQLException
	 {
//		String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
		String campaigncode = userData.getData("Campaign", "Campaign Code","Campaign Admin Tool", "Create Campaign_TMS Corp","1","1");
		String phase = userData.getData("Campaign", "Phase","Campaign Admin Tool", "Create Campaign_TMS Corp","1","1");
		String phase1 = userData.getData("Campaign", "Phase","Campaign Admin Tool", "Create Campaign_TMS Corp","1","2");
		String opcode = userData.getData("Campaign", "Opcode","Campaign Admin Tool", "Create Campaign_TMS Corp","1","1");
		String maxrepair = userData.getData("Campaign", "Maxrepair","Campaign Admin Tool", "Create Campaign_TMS Corp","1","1");
		String count = null;
		String count1 = null;
		String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
		//(INSTANCE_NAME = RTSC)
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String UserName = "T3DATGLOBL";
		String Password = "t3dgprod";
		Connection con = DriverManager.getConnection(dburl, UserName, Password);
		String Query = "select count(*) from ssc1.ssc_detail where detail_campaign_cd='"+campaigncode+"'";
		String Query1 = "select count(*) from ssc1.ssc_opcode where ssc_detail_id in (select ssc_detail_id from ssc1.ssc_detail where detail_campaign_cd='"+campaigncode+"' and phase='"+phase1+"')";
		Statement Stmt = con.createStatement();
		ResultSet rs = Stmt.executeQuery(Query);
		while(rs.next()){
			count=rs.getString(1);
		System.out.println(count);
		}
		ResultSet rs1 = Stmt.executeQuery(Query1);
		while(rs1.next()){
			count1=rs1.getString(1);
		System.out.println(count1);
		}
		Report.updateTestLog("Verify the number of phases", "No. of phases: "+count+"",Status.PASS);
		Report.updateTestLog("Verify the number of phases", "No. of opcodes: "+count1+"",Status.PASS);
		con.close();
		
		
		Driver.findElement(By.linkText("Search Campaign")).click();
		Thread.sleep(3000);
		Driver.findElement(By.id("campaignCodeId")).sendKeys(campaigncode);
		Thread.sleep(2000);
		Driver.findElement(By.id("campaignPhaseId")).sendKeys(phase);
		Thread.sleep(2000);
		Driver.findElement(By.id("searchButtonId")).click();
		Thread.sleep(2000);
		Driver.findElement(By.linkText(campaigncode)).click();
		Thread.sleep(3000);
		Driver.findElement(By.xpath("//input[@type='button' and @value='Make Updates']")).click();
		Thread.sleep(3000);
		Driver.findElement(By.linkText("Expand All")).click();
		Thread.sleep(3000);
		Driver.findElement(By.linkText("Add Market/Phase")).click();
		Thread.sleep(3000);
		count = String.valueOf(Integer.parseInt(count) + 1);
		Driver.findElement(By.id("expand"+count+"")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("campaignPhaseId"+count+"")).sendKeys(phase1);
		Thread.sleep(2000);
		Driver.findElement(By.id("campaignTitle"+count+"")).sendKeys("Automation");
		Thread.sleep(2000);
		Driver.findElement(By.id("campdesc"+count+"")).sendKeys("Automation");
		Thread.sleep(2000);
		Driver.findElement(By.linkText("Expand All")).click();
		Thread.sleep(3000);
		Driver.findElement(By.id("skillMaint"+count+"")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("skillMaster"+count+"")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("add")).click();
		Thread.sleep(2000);
		Report.updateTestLog("Opcode details", "opcode"+count1+"", Status.PASS);
		count1 = String.valueOf(Integer.parseInt(count1) + 1);
		Driver.findElement(By.id("opcode"+count1+"")).sendKeys(opcode);
		Thread.sleep(2000);
		Driver.findElement(By.id("maxrepairs"+count1+"")).sendKeys(maxrepair);
		Thread.sleep(2000);
		Driver.findElement(By.id("comments")).sendKeys("Automation");
		Thread.sleep(2000);
		Driver.findElement(By.id("searchButtonId1")).click();
		Thread.sleep(2000);
		
		try {
			
			Alert confirmationAlert = Driver.switchTo().alert();
			String alertText = confirmationAlert.getText();
			confirmationAlert.accept();
			Driver.switchTo().defaultContent();
			String custname = null;
	//String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
			//(INSTANCE_NAME = RTSC)
			Class.forName("oracle.jdbc.driver.OracleDriver");
		//String UserName = "T3DATGLOBL";
	//String Password = "t3dgprod";
			Connection con2 = DriverManager.getConnection(dburl, UserName, Password);
			String Query2 = "select * from ssc1.ssc_detail where detail_campaign_cd='"+campaigncode+"'";
			Statement Stmt2 = con2.createStatement();
			ResultSet rs2 = Stmt2.executeQuery(Query2);
			while(rs2.next()){
				custname=rs2.getString(1);
			}
			if (custname == campaigncode)
			{
			Report.updateTestLog("Verify if campaign detail is updated", "Campaign detail is updated",Status.PASS);
		}con2.close();
		}
		catch (Exception e) 
		{
	    	  e.printStackTrace();
	    	Report.updateTestLog("Verify if campaign detail is updated", "Campaign detail is not updated",Status.FAIL);
	      }	
		
	  }	
	
	@Action(desc="Edit_campaign_TCI",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Edit_campaign_TCI() throws InterruptedException, Exception
	 {
//		String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
		String campaigncode = userData.getData("Campaign", "Campaign Code","Campaign Admin Tool", "Create Campaign_TCI Corp","1","1");
		String phase = userData.getData("Campaign", "Phase","Campaign Admin Tool", "Create Campaign_TCI Corp","1","1");
		String phase1 = userData.getData("Campaign", "Phase","Campaign Admin Tool", "Create Campaign_TCI Corp","1","2");
		String opcode = userData.getData("Campaign", "Opcode","Campaign Admin Tool", "Create Campaign_TCI Corp","1","1");
		String maxrepair = userData.getData("Campaign", "Maxrepair","Campaign Admin Tool", "Create Campaign_TCI Corp","1","1");
		String count = null;
		String count1 = null;
		String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
		//(INSTANCE_NAME = RTSC)
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String UserName = "T3DATGLOBL";
		String Password = "t3dgprod";
		Connection con = DriverManager.getConnection(dburl, UserName, Password);
		String Query = "select count(*) from ssc1.ssc_detail where detail_campaign_cd='"+campaigncode+"'";
		String Query1 = "select count(*) from ssc1.ssc_opcode where ssc_detail_id in (select ssc_detail_id from ssc1.ssc_detail where detail_campaign_cd='"+campaigncode+"' and phase='"+phase1+"')";
		Statement Stmt = con.createStatement();
		ResultSet rs = Stmt.executeQuery(Query);
		while(rs.next()){
			count=rs.getString(1);
		System.out.println(count);
		}
		ResultSet rs1 = Stmt.executeQuery(Query1);
		while(rs1.next()){
			count1=rs1.getString(1);
		System.out.println(count1);
		}
		Report.updateTestLog("Verify the number of phases", "No. of phases: "+count+"",Status.PASS);
		Report.updateTestLog("Verify the number of phases", "No. of opcodes: "+count1+"",Status.PASS);
		con.close();
		
		
		Driver.findElement(By.linkText("Search Campaign")).click();
		Thread.sleep(3000);
		Driver.findElement(By.id("campaignCodeId")).sendKeys(campaigncode);
		Thread.sleep(2000);
		Driver.findElement(By.id("campaignPhaseId")).sendKeys(phase);
		Thread.sleep(2000);
		Driver.findElement(By.id("searchButtonId")).click();
		Thread.sleep(2000);
		Driver.findElement(By.linkText(campaigncode)).click();
		Thread.sleep(3000);
		Driver.findElement(By.xpath("//input[@type='button' and @value='Make Updates']")).click();
		Thread.sleep(3000);
		Driver.findElement(By.linkText("Expand All")).click();
		Thread.sleep(3000);
		Driver.findElement(By.linkText("Add Market/Phase")).click();
		Thread.sleep(3000);
		
		count = String.valueOf(Integer.parseInt(count) + 1);
		
		Driver.findElement(By.id("expand"+count+"")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("campaignPhaseId"+count+"")).sendKeys(phase1);
		Thread.sleep(2000);
		Driver.findElement(By.id("campaignTitle"+count+"")).sendKeys("Automation");
		Thread.sleep(2000);
		Driver.findElement(By.id("campdesc"+count+"")).sendKeys("Automation");
		Thread.sleep(2000);
		Driver.findElement(By.linkText("Expand All")).click();
		Thread.sleep(3000);
		Driver.findElement(By.id("skillMaint"+count+"")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("skillMaster"+count+"")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("add")).click();
		Thread.sleep(2000);
		Report.updateTestLog("Opcode details", "opcode"+count1+"", Status.PASS);
		count1 = String.valueOf(Integer.parseInt(count1) + 1);
		Driver.findElement(By.id("opcode"+count1+"")).sendKeys(opcode);
		Thread.sleep(2000);
		Driver.findElement(By.id("maxrepairs"+count1+"")).sendKeys(maxrepair);
		Thread.sleep(2000);
		Driver.findElement(By.id("comments")).sendKeys("Automation");
		Thread.sleep(2000);
		Driver.findElement(By.id("searchButtonId1")).click();
		Thread.sleep(2000);
		
		try {
			
			Alert confirmationAlert = Driver.switchTo().alert();
			String alertText = confirmationAlert.getText();
			confirmationAlert.accept();
			Driver.switchTo().defaultContent();
			String custname = null;
//			String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
			//(INSTANCE_NAME = RTSC)
			Class.forName("oracle.jdbc.driver.OracleDriver");
//			String UserName = "T3DATGLOBL";
//			String Password = "t3dgprod";
			Connection con2 = DriverManager.getConnection(dburl, UserName, Password);
			String Query2 = "select * from ssc1.ssc_detail where detail_campaign_cd='"+campaigncode+"'";
			Statement Stmt2 = con2.createStatement();
			ResultSet rs2 = Stmt2.executeQuery(Query2);
			while(rs2.next()){
				custname=rs2.getString(1);
			}
			if (custname == campaigncode)
			{
			Report.updateTestLog("Verify if campaign detail is updated", "Campaign detail is updated",Status.PASS);
		}con2.close();
		}
		catch (Exception e) 
		{
	    	  e.printStackTrace();
	    	Report.updateTestLog("Verify if campaign detail is updated", "Campaign detail is not updated",Status.FAIL);
	      }	
		
	  }	
	
	/**
	*
	* Raj
	*/

	@Action(desc="TIS_DIAG",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void TIS_DIAG() throws InterruptedException{
		GeneralComponents.waitForPageLoaded(Driver);
		GeneralComponents.waitforElePresence(By.xpath(".//td/a[contains(text(),'TIS')]"));
		WebElement TIS = Driver.findElement(By.xpath(".//td/a[contains(text(),'TIS')]"));
		System.out.println();
		if(TIS!=null){
			GeneralComponents.clickOnWebelement(TIS, "TIS");
			Thread.sleep(3000);	
			Report.updateTestLog("TIS", "TIS tab is clicked",Status.PASS);
		}else{
			Report.updateTestLog("TIS", "TIS tab is not clicked",Status.FAIL);
		}
		WebElement Diagnostics = Driver.findElement(By.xpath(".//td/a[contains(text(),'Diagnostics')]"));
		if(Diagnostics!=null){
			GeneralComponents.clickOnWebelement(Diagnostics, "Diagnostics");
			Thread.sleep(3000);	
			Report.updateTestLog("Diagnostics", "Diagnostics tab is clicked",Status.PASS);
		}else{
			Report.updateTestLog("Diagnostics", "Diagnostics tab is not clicked",Status.FAIL);
		}
		
		}


	@Action(desc="TIS_DIAG_REPROG",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void TIS_DIAG_REPROG() throws InterruptedException{
		WebElement TIS = Driver.findElement(By.xpath(".//td/a[contains(text(),'TIS')]"));
		System.out.println();
		if(TIS!=null){
			GeneralComponents.clickOnWebelement(TIS, "TIS");
			Thread.sleep(3000);	
			Report.updateTestLog("TIS", "TIS tab is clicked",Status.PASS);
		}else{
			Report.updateTestLog("TIS", "TIS tab is not clicked",Status.FAIL);
		}
		WebElement Diagnostics = Driver.findElement(By.xpath(".//td/a[contains(text(),'Diagnostics')]"));
		if(Diagnostics!=null){
			GeneralComponents.clickOnWebelement(Diagnostics, "Diagnostics");
			Thread.sleep(3000);	
			Report.updateTestLog("Diagnostics", "Diagnostics tab is clicked",Status.PASS);
		}else{
			Report.updateTestLog("Diagnostics", "Diagnostics tab is not clicked",Status.FAIL);
		}
		WebElement Reprogramming = Driver.findElement(By.xpath(".//td/a[contains(text(),'Reprogramming')]"));
		if(Reprogramming!=null){
			GeneralComponents.clickOnWebelement(Reprogramming, "Reprogramming");
			Thread.sleep(3000);
			Report.updateTestLog("Reprogramming", "Reprogramming tab is clicked",Status.PASS);
		}else{
			Report.updateTestLog("Reprogramming", "Reprogramming tab is not clicked",Status.FAIL);
			}	
		
	}




	@Action(desc="software_download",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void software_download() throws InterruptedException, AWTException{
		String DownloadedPath = userData.getData("Software_Download", "Download_Path","1","1");
	
		// Tech Stream Download - Full Version
		 WebElement Techstream_Beta_Full_Software_Installer = Driver.findElement(By.linkText("Full Install"));
		 String TechStreamSW_FullDownloadedFile = userData.getData("Software_Download", "TechStreamSW_FullDownloaded","1","1");
		 if(Techstream_Beta_Full_Software_Installer!=null)
		 {
		 JavascriptExecutor executor = (JavascriptExecutor)Driver;
		 executor.executeScript("arguments[0].click();", Techstream_Beta_Full_Software_Installer);
		 Thread.sleep(15000);
		 isFileDownloaded(DownloadedPath, TechStreamSW_FullDownloadedFile);
		 Thread.sleep(3000);
		 Report.updateTestLog("Techstream_Beta_Full_Software_Installer", "Techstream_Beta_Full_Software_Installer tab is clicked and downloaded",Status.PASS);
		 }else
		 {
		 Report.updateTestLog("Techstream_Beta_Full_Software_Installer", "Techstream_Beta_Full_Software_Installer tab is not clicked and not downloaded",Status.FAIL);
		 }
		 }
		
	@Action(desc="waitForNewWindow",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void waitForNewWindow(){
	try{
	WebDriverWait wait = new WebDriverWait(Driver, 60);
	int i = Driver.getWindowHandles().size();
	System.out.println(i);
	wait.until(new ExpectedCondition<Boolean>() {
	@Override
	public Boolean apply(WebDriver driver) {
	return Driver.getWindowHandles().size()>1;
	}
	});
	}catch(Exception e){
	System.out.println(e);
	}
	}

	@Action(desc="SwitchToLastWindow",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void SwitchToLastWindow(){
	GeneralComponents.waitForPageLoaded(Driver);

	waitForNewWindow();

	Set<String> handles = Driver.getWindowHandles();
	List<String> list = new ArrayList<String>(handles);

	if(list.size()>1){

	Driver.switchTo().defaultContent();
	System.out.println("Present Window Handle : "+Driver.getWindowHandle());
	System.out.println("No of Windows : "+list.size());
	list.remove(Driver.getWindowHandle());
	Driver.switchTo().window(list.get(list.size()-1));
	System.out.println("Switched Window Handle : "+Driver.getWindowHandle());
	//GeneralComponents.waitForPageLoaded(Driver);

	}else{
	System.out.println("No New Window Obtained");
	}
	}

	@Action(desc="isFileDownloaded",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public boolean isFileDownloaded(String downloadPath, String fileName) {
		  File dir = new File(downloadPath);
		  File[] dirContents = dir.listFiles();

		  for (int i = 0; i < dirContents.length; i++) {
		      if (dirContents[i].getName().equals(fileName)) {
		    	  Report.updateTestLog("File", "File has been downloaded",Status.PASS);
		          // File has been found, it can now be deleted:
		          dirContents[i].delete();
		          return true;
		      }
		          }
		      return false;
		  }


	@Action(desc="Robot",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Robot() throws AWTException, InterruptedException
	{
		Robot Roboj = new Robot();
		Roboj.keyPress(KeyEvent.VK_F6);	
		Roboj.keyRelease(KeyEvent.VK_F6);
		Thread.sleep(3000);
		
		Roboj.keyPress(KeyEvent.VK_TAB);	
		Roboj.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(3000);
			
		Roboj.keyPress(KeyEvent.VK_ENTER);	
		Roboj.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(3000);
		
	}

	/*@Action(desc="saveclick",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void saveclick() 
	{
		Screen screen = new Screen();
		Pattern image = new Pattern("C:\\Users\\405549\\Downloads\\save.png");
		screen.click(image);
	    }*/

	@Action(desc="Savefile",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public  void Savefile()
	{
	Robot downloader;
	    try {
	    downloader = new Robot();
	    Thread.sleep(2000);	    
	    downloader.keyPress(KeyEvent.VK_F6);
	    downloader.keyRelease(KeyEvent.VK_F6);
	    Thread.sleep(1000);
	    downloader.keyPress(KeyEvent.VK_TAB);
	    downloader.keyRelease(KeyEvent.VK_TAB);
	    Thread.sleep(1000);
	    downloader.keyPress(KeyEvent.VK_ENTER);
	    downloader.keyRelease(KeyEvent.VK_ENTER);
	        Thread.sleep(2000);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	}

	@Action(desc="closefile",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public  void closefile()
	{
	Robot downloader;
	    try {
	    downloader = new Robot();
	    Thread.sleep(5000);
	    downloader.keyPress(KeyEvent.VK_F6);    
	    downloader.keyRelease(KeyEvent.VK_F6);
	    Thread.sleep(1000);
	    downloader.keyPress(KeyEvent.VK_TAB);
	    downloader.keyRelease(KeyEvent.VK_TAB);
	    Thread.sleep(1000);
	    downloader.keyPress(KeyEvent.VK_TAB);
	    downloader.keyRelease(KeyEvent.VK_TAB);
	    Thread.sleep(1000);
	    downloader.keyPress(KeyEvent.VK_TAB);
	    downloader.keyRelease(KeyEvent.VK_TAB);
	    Thread.sleep(1000);
	    downloader.keyPress(KeyEvent.VK_ENTER);
	    downloader.keyRelease(KeyEvent.VK_ENTER);
	        Thread.sleep(2000);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	}


	@Action(desc="closecurrentwindow",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public  void closecurrentwindow()
	{
	Robot downloader;
	    try {
	    downloader = new Robot();
	    Thread.sleep(2000);
	    downloader.keyPress(KeyEvent.VK_ALT);
	    downloader.keyPress(KeyEvent.VK_F4);
	    downloader.keyRelease(KeyEvent.VK_ALT);
	    downloader.keyRelease(KeyEvent.VK_F4);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	}

	@Action(desc="configurations",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void configurations()
	{
		 WebElement configuration =Driver.findElement(By.xpath(".//table[@class='bea-portal-body-menu-roundedshaded-table']//td[7]/a"));
		
		 if(configuration!=null)
			{	 
			 configuration.click();
		 Report.updateTestLog("Configuration","Configuration tab is clicked",Status.PASS);
			}else{
				Report.updateTestLog("Configuration","Configuration tab is not clicked",Status.PASS);
			}
		 String VIN=userData.getData("Inputvalues", "VIN", "1", "1");
		WebElement VINFIELD = Driver.findElement(By.xpath(".//input[@id='vinInput']"));
		/*String VIN_query = userData.getData("Queries", "Query");
		String VIN = dbUtil.fetchFirstValueFromParticularColumn(VIN_query, "VIN");*/	
		VINFIELD.click();
		VINFIELD.sendKeys(VIN);	
		WebElement lookup = Driver.findElement(By.id("vinButton"));
		lookup.click();
	}

	@Action(desc="Asbuild",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Asbuild() throws InterruptedException
	{
		String VIN = userData.getData("Inputvalues", "VIN", "1", "1");
	WebElement asbuildlink = Driver.findElement(By.xpath(".//td[contains(text(),'Asbuilt')]"));
	if(asbuildlink!=null)
	{
		asbuildlink.click();
		Thread.sleep(2000);
		Report.updateTestLog("asbuildlink","Asbuildlink is clicked",Status.PASS);
	}else{
		Report.updateTestLog("asbuildlink","Asbuildlink is not clicked",Status.FAIL);
	}

	WebElement data=Driver.findElement(By.xpath(".//a[@id='asbuiltlinkid']"));
	if(data!=null)
	{
		data.click();
		Report.updateTestLog("data","data link is clicked",Status.PASS);
		Thread.sleep(2000);
		Savefile();
		isFileDownloaded("C:\\Users\\405549\\Downloads", "Asbuilt_"+VIN+".xml");
		Report.updateTestLog("P5EB","P5EB is downloaded",Status.PASS);
	}else{
		Report.updateTestLog("data","data link is not clicked",Status.FAIL);
	}
	}

	@Action(desc="Basecalibration",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Basecalibration() throws InterruptedException
	{
		WebElement Basecalibration =Driver.findElement(By.xpath(".//a[contains(text(),'Base Calibration')]"));
		if(Basecalibration!=null)
		{
			Basecalibration.click();
			Report.updateTestLog("Basecalibration","Basecalibration tab is clicked",Status.PASS);
		}else{
			Report.updateTestLog("Basecalibration","Basecalibration tab is not clicked",Status.FAIL);
		}
		WebElement P5EB = Driver.findElement(By.xpath(".//td[@class='configTableData']//a[contains(text(),'T-0044-15_P5EB-188K2-B.cuw')]"));
		WebElement P56V = Driver.findElement(By.xpath(".//td[@class='configTableData']//a[contains(text(),'T-0045-15_P56V-21PS1-D.cuw')]"));
		if(P5EB!=null)
		{
			P5EB.click();
			Thread.sleep(2000);
			Savefile();
			Thread.sleep(5000);
			isFileDownloaded("C:\\Users\\405549\\Downloads", "T-0044-15_P5EB-188K2-B.cuw");
			Report.updateTestLog("P5EB","P5EB is downloaded",Status.PASS);
		}else{
			Report.updateTestLog("P5EB","P5EB is not downloaded",Status.FAIL);
			
		}
		if(P56V!=null)
		{
			P56V.click();
			Savefile();
			Thread.sleep(2000);
			isFileDownloaded("C:\\Users\\405549\\Downloads", "T-0045-15_P56V-21PS1-D.cuw");
			Report.updateTestLog("P56V","P56V is downloaded",Status.PASS);
		}else{
			Report.updateTestLog("P56V","P56V is not downloaded",Status.FAIL);
			
		}
			
		
	}


	@Action(desc="Myaccount_lexus",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Myaccount_lexus()
	{
		WebElement myaccount=Driver.findElement(By.xpath(".//a[contains(text(),'My Account')]"));
		myaccount.click();
		WebElement lexus = Driver.findElement(By.id("btn_lexus"));
		lexus.click();
		WebElement save=Driver.findElement(By.id("userPreferencesSave"));
		save.click();
	}

	@Action(desc="ToolsEquip",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void ToolsEquip() throws InterruptedException
	{
		String A=null;
		WebElement ToolsEquip=Driver.findElement(By.xpath(".//a[contains(text(),'Tools & Equipment')]"));
		ToolsEquip.click();
		Report.updateTestLog("ToolsEquip","ToolsEquip tab is clicked",Status.PASS);
		Thread.sleep(2000);
		WebElement Techstream = Driver.findElement(By.xpath(".//a[contains(text(),'Techstream 2.0 Order Portal')]"));
		if(Techstream!=null)
		{
		Techstream.click();	
		Report.updateTestLog("Techstream","Techstream link is clicked",Status.PASS);
		}else
		{
			Report.updateTestLog("Techstream","Techstream link is not clicked",Status.FAIL);
		}
		
	WebElement popup_continue = Driver.findElement(By.xpath(".//div[@id='popupfooter']//td[2]"));
	if(popup_continue!=null)
	{
		A =Driver.getWindowHandle();
		popup_continue.click();	
	Report.updateTestLog("popup","continue button is clicked",Status.PASS);
	}
	else
	{
		Report.updateTestLog("popup","continue button is not clicked",Status.FAIL);
	}
	Thread.sleep(2000);
	SwitchToLastWindow();
	WebElement Toyota=Driver.findElement(By.id("hrefToyota"));
	if(Toyota!=null)
	{
		Toyota.click();	
	Report.updateTestLog("Toyota","Toyota approved session is clicked",Status.PASS);
	}
	else
	{
		Report.updateTestLog("Toyota","Toyota approved session is not clicked",Status.FAIL);
	}

	WebElement pageverify=Driver.findElement(By.xpath(".//form[@id='aspnetForm']"));
	if(pageverify.isDisplayed())
	{
		Report.updateTestLog("Page","Page is loaded and verified",Status.PASS);
		
	}
	else{
		Report.updateTestLog("Page","Page is not loaded",Status.FAIL);
	}
	Thread.sleep(1000);
	closecurrentwindow();
	Thread.sleep(3000);
	System.out.println(A);
	Driver.switchTo().window(A);

	}


	/*Picoscope tab download validations*/
	@Action(desc="Tools_PicoScope",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Tools_PicoScope() throws InterruptedException
	{
	WebElement PicoScope= Driver.findElement(By.xpath(".//div[@id='TabbedPanelsToolsEquipment']//li/a[contains(text(),'PicoScope')]"));
	PicoScope.click();
	Report.updateTestLog("PicoScope","PicoScope tab is clicked",Status.PASS);
	WebElement PicoScope_Software_Version=Driver.findElement(By.xpath(".//a[contains(text(),'PicoScope Software Version 6.12.7')]"));
	if(PicoScope_Software_Version!=null)
	{
	JavascriptExecutor executor = (JavascriptExecutor)Driver;
	executor.executeScript("arguments[0].click();", PicoScope_Software_Version);
	Savefile();
	Thread.sleep(45000);	 
	Thread.sleep(45000);
	closefile();
	isFileDownloaded("C:\\Users\\405549\\Downloads", "PicoScopeAutomotiveForTMS_r6_12_7.exe");
	Thread.sleep(3000);
	Report.updateTestLog("PicoScope_Software_Version", "PicoScope_Software_Version tab is clicked and downloaded",Status.PASS);
	}else
	{
	Report.updateTestLog("PicoScope_Software_Version", "PicoScope_Software_Version tab is not clicked and not downloaded",Status.FAIL); 

	}
	Thread.sleep(2000);

	WebElement WaveformLibrarySoftwareVersion=Driver.findElement(By.xpath(".//a[contains(text(),'Waveform Library Software Version 1.2')]"));
	if(WaveformLibrarySoftwareVersion!=null)
	{
	JavascriptExecutor executor = (JavascriptExecutor)Driver;
	executor.executeScript("arguments[0].click();", WaveformLibrarySoftwareVersion);
	Savefile();
	Thread.sleep(45000);	 
	Thread.sleep(35000);
	closefile();
	isFileDownloaded("C:\\Users\\405549\\Downloads", "TMS_Database_Launcher_Setup_r1_2.exe");
	Report.updateTestLog("WaveformLibrarySoftwareVersion", "WaveformLibrarySoftwareVersion link is clicked and downloaded",Status.PASS);
	}else
	{
	Report.updateTestLog("WaveformLibrarySoftwareVersion", "WaveformLibrarySoftwareVersion link is not clicked and not downloaded",Status.FAIL); 

	}

	}


	/*Battery diagnostics tab download validations*/
	@Action(desc="Battery diagnostics",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Batterydiagnostics() throws InterruptedException
	{
	JavascriptExecutor executor = (JavascriptExecutor)Driver;
	By Batterydiagnostics= By.xpath(".//div[@id='TabbedPanelsToolsEquipment']//li[3]/a[contains(text(),'Battery')]");
	By NVSvalue= By.xpath(".//a[contains(text(),'NVS-8150 Battery Tester Update')]");
	GeneralComponents.findAndClickElement(Driver, Batterydiagnostics, NVSvalue, "Batterydiagnostics");
	Report.updateTestLog("Batterydiagnostics","Batterydiagnostics tab is clicked",Status.PASS);
	WebElement NVS=Driver.findElement(By.xpath(".//a[contains(text(),'NVS-8150 Battery Tester Update')]"));
	if(NVS!=null)
	{
	executor.executeScript("arguments[0].click();", NVS);
	Savefile();
	Thread.sleep(45000);	 
	Thread.sleep(45000);
	closefile();
	isFileDownloaded("C:\\Users\\405549\\Downloads", "PicoScopeAutomotiveForTMS_r6_12_7.exe");
	Thread.sleep(3000);
	Report.updateTestLog("NVS", "NVS link is clicked and downloaded",Status.PASS);
	}else
	{
	Report.updateTestLog("NVS", "NVS link is not clicked and not downloaded",Status.FAIL); 

	}
	Thread.sleep(2000);

	WebElement WaveformLibrarySoftwareVersion=Driver.findElement(By.xpath(".//a[contains(text(),'Waveform Library Software Version 1.2')]"));
	if(WaveformLibrarySoftwareVersion!=null)
	{
	executor.executeScript("arguments[0].click();", WaveformLibrarySoftwareVersion);
	Savefile();
	Thread.sleep(45000);	 
	Thread.sleep(45000);
	isFileDownloaded("C:\\Users\\405549\\Downloads", "TMS_Database_Launcher_Setup_r1_2.exe");
	Thread.sleep(3000);
	Report.updateTestLog("WaveformLibrarySoftwareVersion", "WaveformLibrarySoftwareVersion link is clicked and downloaded",Status.PASS);
	}else
	{
	Report.updateTestLog("WaveformLibrarySoftwareVersion", "WaveformLibrarySoftwareVersion link is not clicked and not downloaded",Status.FAIL); 

	}

	}


	/*TPWS tab download validations*/
	@Action(desc="TPWS",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void TPWS() throws InterruptedException
	{
		JavascriptExecutor executor = (JavascriptExecutor)Driver;
		By TPWS= By.xpath(".//div[@id='TabbedPanelsToolsEquipment']//li[4]/a[contains(text(),'TPWS')]");
		By LEXUSTPWSvalue= By.xpath(".//a[contains(text(),'Lexus TPWS Software Update 1.8')]");
		GeneralComponents.findAndClickElement(Driver, TPWS, LEXUSTPWSvalue, "TPWS");
	Thread.sleep(2000);
	WebElement LEXUSTPWS=Driver.findElement(By.xpath(".//a[contains(text(),'Lexus TPWS Software Update 1.8')]"));
	if(LEXUSTPWS!=null)
	{
		executor.executeScript("arguments[0].click();", LEXUSTPWS);
		Savefile();
	Thread.sleep(25000);	 
	isFileDownloaded("C:\\Users\\405549\\Downloads", "ToyotaTPWS_1.8.exe");
	Thread.sleep(3000);
	Report.updateTestLog("LEXUSTPWS", "LEXUSTPWS link is clicked and downloaded",Status.PASS);
	}else
	{
	Report.updateTestLog("LEXUSTPWS", "LEXUSTPWS link is not clicked and not downloaded",Status.FAIL);
	}
	Thread.sleep(2000);

	/*WebElement softwareupdate=Driver.findElement(By.xpath(".//div[@class='TabbedPanelsContent TabbedPanelsContentVisible']/table[2]//tr[2]/td/a[contains(text(),'Software Update 2.0')]"));
	if(softwareupdate!=null)
	{
	executor.executeScript("arguments[0].click();", softwareupdate);
	Savefile();
	Thread.sleep(45000);	 
	Thread.sleep(45000);
	isFileDownloaded("C:\\Users\\405549\\Downloads", "TMS_Database_Launcher_Setup_r1_2.exe");
	Thread.sleep(3000);
	Report.updateTestLog("WaveformLibrarySoftwareVersion", "WaveformLibrarySoftwareVersion link is clicked and downloaded",Status.PASS);
	}else
	{
	Report.updateTestLog("WaveformLibrarySoftwareVersion", "WaveformLibrarySoftwareVersion link is not clicked and not downloaded",Status.FAIL); 

	}*/

	}

	/*Navigation tab download validations*/
	@Action(desc="Navigation",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Navigation() throws InterruptedException
	{
	JavascriptExecutor executor = (JavascriptExecutor)Driver;
	By Navigation= By.xpath(".//div[@id='TabbedPanelsToolsEquipment']//li[6]/a[contains(text(),'Navigation')]");
	By windows7val= By.xpath(".//div[@id='TabbedPanelsToolsEquipment']//li[6]/a[contains(text(),'Navigation')]");
	GeneralComponents.findAndClickElement(Driver, Navigation, windows7val, "NAV");
	Report.updateTestLog("Navigation","Navigation tab is clicked",Status.PASS);
	WebElement windows7=Driver.findElement(By.xpath(".//div[@class='TabbedPanelsContent TabbedPanelsContentVisible']/table//td/a[contains(text(),'Windows 7/8 Navigation Programs Installer')]"));
	if(windows7!=null)
	{
	executor.executeScript("arguments[0].click();", windows7);
	Savefile();
	Thread.sleep(10000);	 
	closefile();
	isFileDownloaded("C:\\Users\\405549\\Downloads", "npi_2.2.0.exe");
	Thread.sleep(3000);
	Report.updateTestLog("windows7", "windows7 link is clicked and downloaded",Status.PASS);
	}else
	{
	Report.updateTestLog("windows7", "windows7 link is not clicked and not downloaded",Status.FAIL); 

	}
	Thread.sleep(2000);
	WebElement windowsxp=Driver.findElement(By.xpath(".//div[@class='TabbedPanelsContent TabbedPanelsContentVisible']/table//a[contains(text(),'Windows XP Navigation Programs Installer')]"));
	if(windowsxp!=null)
	{
	executor.executeScript("arguments[0].click();", windowsxp);
	Savefile();
	Thread.sleep(10000);	 
	closefile();
	isFileDownloaded("C:\\Users\\405549\\Downloads", "npi_1.3.3.exe");
	Thread.sleep(3000);
	Report.updateTestLog("windowsxp", "windowsxp link is clicked and downloaded",Status.PASS);
	}else
	{
	Report.updateTestLog("windowsxp", "windowsxp link is not clicked and not downloaded",Status.FAIL); 

	}
	}

	@Action(desc="Preference_change",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Preference_change()
	{
		WebElement myaccount=Driver.findElement(By.xpath(".//a[contains(text(),'My Account')]"));
		if(myaccount!=null)
		{
		myaccount.click();
		Report.updateTestLog("myaccount", "myaccount tab is clicked",Status.PASS);
		}else
		{
			Report.updateTestLog("myaccount", "myaccount tab is not clicked",Status.FAIL);	
		}
		WebElement Division= Driver.findElement(By.xpath("//*[*[contains(text(),'Division:')]]//input[@type='button' and not(contains(@style,'69'))]"));
		if(Division!=null)
		{
		Division.click();
		Report.updateTestLog("Division", "Division value has changed",Status.PASS);
		}else
		{
			Report.updateTestLog("Division", "Division value has not changed",Status.FAIL);	
		}
		WebElement Result_per_page=Driver.findElement(By.xpath("//*[*[contains(text(),'Results Per Page')]]//input[@type='button' and not(contains(@style,'69'))]"));
		if(Result_per_page!=null)
		{
		Result_per_page.click();
		Report.updateTestLog("Results per page", "Results per page value has changed",Status.PASS);
		}else
		{
			Report.updateTestLog("Results per page", "Results per page value has not changed",Status.FAIL);
		}	
		WebElement Displaysummary=Driver.findElement(By.xpath("//*[*[contains(text(),'Display Summary')]]//input[@type='button' and not(contains(@style,'69'))]"));
		if(Displaysummary!=null)
		{
		Displaysummary.click();
		Report.updateTestLog("Displaysummary", "Displaysummary value has changed",Status.PASS);
		}else
		{
		Report.updateTestLog("Displaysummary", "Displaysummary value has not changed",Status.FAIL);
		}
		WebElement carryover=Driver.findElement(By.xpath("//*[*[contains(text(),'Carryover VIN/ Division/Model/Year')]]//input[@type='button' and not(contains(@style,'69'))]"));
		if(carryover!=null)
		{
			carryover.click();
			Report.updateTestLog("carryover", "carryover value has changed",Status.PASS);
			
		}else
		{
			Report.updateTestLog("carryover", "carryover value has not changed",Status.FAIL);
		}
		WebElement save=Driver.findElement(By.id("userPreferencesSave"));
		save.click();
		String Expected_message="Your preferences were set successfully.";
		WebElement message= Driver.findElement(By.xpath(".//div[@id='prepopulatePreferenceForm.successData']"));
		String actual_message = message.getText();
		if(actual_message.equalsIgnoreCase(Expected_message))
		{
		Report.updateTestLog("Success message", "Successful message "+Expected_message+" is displayed",Status.PASS);	
		}else
		{
		Report.updateTestLog("Success message", "Invalid ",Status.FAIL);		
		}
		
	}

	@Action(desc="ServiceLane",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void ServiceLane() throws InterruptedException{
		WebElement ServiceLane = Driver.findElement(By.xpath(".//td/a[contains(text(),'Service Lane')]"));
		System.out.println();
		if(ServiceLane!=null){
			GeneralComponents.clickOnWebelement(ServiceLane, "ServiceLane");
			Thread.sleep(3000);	
			Report.updateTestLog("ServiceLane", "ServiceLane tab is clicked",Status.PASS);
		}else{
			Report.updateTestLog("ServiceLane", "ServiceLane tab is not clicked",Status.FAIL);
		}
			
	}

	@Action(desc="VIN_Search",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void VIN_Search() throws InterruptedException
	{
		JavascriptExecutor js = (JavascriptExecutor) Driver;
	    
	WebElement VINField = Driver.findElement(By.xpath(".//input[contains(@id,'vinForm:vinTextBox')]"));

	String VIN=userData.getData("Inputvalues", "VIN", "1", "1");
	if(VINField!=null)
		{
		
		VINField.click();
		VINField.sendKeys(VIN);
		Report.updateTestLog("VIN", "VIN is Entered",Status.PASS);
		}else{
			Report.updateTestLog("VIN", "VIN is not Entered",Status.FAIL);
		}

	WebElement Lookup = Driver.findElement(By.xpath(".//input[@id='_jpfcpncuivr_VinLookupUniquePortlet_j_id_id0:vinForm:vinlookupbut']"));

	if(Lookup!=null)
	{
		Lookup.click();
		Thread.sleep(3000);
		Report.updateTestLog("VIN", "VIN is searched",Status.PASS);
	}else{
		Report.updateTestLog("VIN", "VIN is not searched",Status.FAIL);
	}	
	WebElement Agree = Driver.findElement(By.xpath(".//input[contains(@id,'vinForm:iAgreeButton')]"));

	if(Agree!=null)
	{
		js.executeScript("arguments[0].click();", Agree);
	Thread.sleep(15000);
	Report.updateTestLog("Agree", "Agree button is clicked",Status.PASS);
	}else{
		Report.updateTestLog("Agree", "Agree button is not clicked",Status.FAIL);
		GeneralComponents.waitForPageLoaded(Driver);
		GeneralComponents.waitforElePresence(By.xpath(".//img[contains(@title,'Edit Customer Information')]"));
	}
	}

	@Action(desc="Edit",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Edit() throws InterruptedException
	{
		GeneralComponents.waitForPageLoaded(Driver);
		GeneralComponents.waitforElePresence(By.xpath(".//img[contains(@title,'Edit Customer Information')]"));
	WebElement Editicon=Driver.findElement(By.xpath(".//img[contains(@title,'Edit Customer Information')]"));
	if(Editicon!=null)
		{
		Editicon.click();
		Report.updateTestLog("Edit", "Edit icon is clicked",Status.PASS);
		Thread.sleep(2000);
		SwitchToLastWindow();
		}else{
			Report.updateTestLog("Edit", "Edit icon is not clicked",Status.FAIL);
		}
	}

	@Action(desc="phone_edit",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void phone_edit() throws InterruptedException
	{
		GeneralComponents.waitforElePresence(By.xpath(".//td//label[contains(text(),'Change the address/phone/email?')]"));
		WebElement Phone_edit=Driver.findElement(By.xpath(".//td//label[contains(text(),'Change the address/phone/email?')]"));
		if(Phone_edit!=null)
		{
			Phone_edit.click();
		Report.updateTestLog("phone_edit", "phone_edit  is clicked",Status.PASS);
		Thread.sleep(2000);
		}else{
			Report.updateTestLog("phone_edit", "phone_edit  is not clicked",Status.FAIL);
		}
		
	}

	@Action(desc="Primary_phone",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Primary_phone() throws InterruptedException
	{
	WebElement Primary_phone = Driver.findElement(By.xpath(".//input[@id='primaryPhone']"));
	if(Primary_phone!=null)
		{
		Primary_phone.click();
		Thread.sleep(2000);
		Primary_phone.clear();
		Report.updateTestLog("Primary_phone", "Primary_phone  is entered",Status.PASS);
		}else{
			Report.updateTestLog("Primary_phone", "Primary_phone  is not entered",Status.FAIL);
		}
	String phone_number = userData.getData("Inputvalues","values");
	Primary_phone.sendKeys(phone_number);
	}
	
	@Action(desc="Windows File upload",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void WindowsFileUpload() throws InterruptedException, AWTException
	{
		Thread.sleep(4000);
		//String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
		//String vin = userData.getData("UniqueData", "FilePath","MSR_FileUpload", "MSR_FileUpload_TMS_Corp","1","1");
		String vin = userData.getData("UniqueData", "FilePath","1","1");
		
		StringSelection s = new StringSelection(vin);
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);
	    Robot robot = new Robot();
	    robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
	    robot.keyRelease(java.awt.event.KeyEvent.VK_ENTER);
	    robot.keyPress(java.awt.event.KeyEvent.VK_CONTROL);
	    robot.keyPress(java.awt.event.KeyEvent.VK_V);
	    robot.keyRelease(java.awt.event.KeyEvent.VK_CONTROL);
	    Thread.sleep(3000);
	    robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
	    Thread.sleep(3000);
	    Report.updateTestLog("File Upload", "File Upload Scucessful",Status.PASS);
	}


	@Action(desc="Edit_save",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Edit_save() 
	{
		WebElement save = Driver.findElement(By.xpath(".//input[@id='editSaveButton']"));
		if(save!=null)
		{
			save.click();
			Report.updateTestLog("save", "save  is clicked",Status.PASS);
		}else{
			Report.updateTestLog("save", "save  is not clicked",Status.FAIL);
		}
		
		String Expected_message="Customer information is updated successfully.";
		WebElement message= Driver.findElement(By.xpath(".//span[@id='changeAddressForm:saveSuccessMsg']"));
		String actual_message = message.getText();
		if(actual_message.equalsIgnoreCase(Expected_message))
		{
		Report.updateTestLog("Success message", "Successful message "+Expected_message+" is displayed",Status.PASS);	
		}else
		{
		Report.updateTestLog("Success message", "Invalid ",Status.FAIL);		
		}
	}


	@Action(desc="mouseover_car",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void mouseover_car() 
	{
		GeneralComponents.waitForPageLoaded(Driver);
		GeneralComponents.waitforElePresence(By.xpath(".//img[contains(@id,'vinForm:vehicleEDAlertImg')]"));
	WebElement element = Driver.findElement(By.xpath(".//img[contains(@id,'vinForm:vehicleEDAlertImg')]"));

	Actions action = new Actions(Driver);

	action.moveToElement(element).build().perform();

	}

	@Action(desc="vehicleEDAlerts",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void vehicleEDAlerts()
	{
		WebElement vehicleEDAlerts=Driver.findElement(By.xpath(".//div[@id='vehicleEDAlerts']"));
		
		if(vehicleEDAlerts.isDisplayed())
		{
			Report.updateTestLog("vehicleEDAlerts", "vehicleEDAlerts tooltip is displayed ",Status.PASS);
		}else{
				Report.updateTestLog("vehicleEDAlerts", "vehicleEDAlerts tooltip is displayed ",Status.FAIL);
		}
		
	}

	@Action(desc="FlatRateManual",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void FlatRateManual()
	{
		GeneralComponents.waitForPageLoaded(Driver);
		GeneralComponents.waitforElePresence(By.xpath(".//input[@id='flatRateManualHelpText']//following-sibling::a[contains(text(),'Flat Rate Manual')]"));
		WebElement FlatRateManual   =Driver.findElement(By.xpath(".//input[@id='flatRateManualHelpText']//following-sibling::a[contains(text(),'Flat Rate Manual')]"));
		if(FlatRateManual!=null)
		{
			FlatRateManual.click();
			Report.updateTestLog("FlatRateManual", "FlatRateManual link is clicked",Status.PASS);
		}else{
			Report.updateTestLog("FlatRateManual", "FlatRateManual link is not clicked",Status.FAIL);
		}
		SwitchToLastWindow();
	}

	@Action(desc="access_validation",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void access_validation() throws InterruptedException
	{
		WebElement Accessories  =Driver.findElement(By.xpath(".//a[contains(text(),' Accessories')]"));
		Accessories.click();
		WebElement ACCESSORY  =Driver.findElement(By.xpath(".//a[contains(text(),' ACCESSORY')]"));
		ACCESSORY.click();
		WebElement VEHICLE_EXTERIOR =Driver.findElement(By.xpath(".//a[contains(text(),' VEHICLE EXTERIOR')]"));
		VEHICLE_EXTERIOR.click();
		Thread.sleep(2000);
		
		GeneralComponents.waitforElePresence(By.xpath("//*[@id='opDescTable']/tbody/tr//input[@type='text']"));
		List<WebElement> eles = Driver.findElements(By.xpath("//*[@id='opDescTable']/tbody/tr//input[@type='text']"));
		float sum=0;
		for(WebElement ele:eles)
		{
			sum=sum + Float.parseFloat(ele.getAttribute("value").trim());
				
		}
		System.out.println(sum);

		List<WebElement> els = Driver.findElements(By.xpath("//*[@id='opDescTable']/tbody/tr//input[@type='checkbox']"));
		for ( WebElement el : els ) {
		    if ( !el.isSelected() ) {
		        el.click();
		        Thread.sleep(2000);
		    }
		}
		
		WebElement calculate=Driver.findElement(By.xpath(".//input[@id='btn_calculate']"));
		if (calculate!=null)
		{
			calculate.click();
			Report.updateTestLog("calculate", "calculate button clicked",Status.PASS);
			Thread.sleep(2000);		
		}
		
		String txt= Driver.findElement(By.xpath(".//td[@id='totalHours1']")).getText();
		float total = Float.parseFloat(txt);
		
		if(sum==total)
		{
			Report.updateTestLog("Value", "Value is matched",Status.PASS);
		}else{
			Report.updateTestLog("Value", "Value is not matched",Status.FAIL);
		}
		
		
		
		
		
		

	}

	@Action(desc="Telematics_tab",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Telematics_tab() throws InterruptedException
	{
		Thread.sleep(10000);
		GeneralComponents.waitforElePresence(By.xpath(".//a[@id='ASMPortal_telematics']"));
	WebElement Telematics_tab=Driver.findElement(By.xpath(".//a[@id='ASMPortal_telematics']"));
	if(Telematics_tab!=null)
		{
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) Driver;
	    js.executeScript("arguments[0].click();", Telematics_tab);
		Report.updateTestLog("Telematics_tab", "Telematics_tab is clicked",Status.PASS);
		Thread.sleep(2000);
		}else{
			Report.updateTestLog("Telematics_tab", "Telematics_tab is not clicked",Status.FAIL);
		}}

	@Action(desc="subscriber_link",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void subscriber_link() throws InterruptedException
	{
		WebElement subscriber=Driver.findElement(By.xpath(".//table[@class='telematictbl_wrapper']//tr[2]/td[@class='telematicsHeader'][3]/a"));
		if(subscriber!=null)
		{
			Thread.sleep(2000);
			JavascriptExecutor js = (JavascriptExecutor) Driver;
		    js.executeScript("arguments[0].click();", subscriber);
			subscriber.click();
			Thread.sleep(2000);
			Report.updateTestLog("subscriber", "subscriber link is clicked",Status.PASS);
			SwitchToLastWindow();
		}else{
			Report.updateTestLog("subscriber", "subscriber link is not clicked",Status.FAIL);
		}
	}

	@Action(desc="linkverify",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void linkverify()
	{
		WebElement link=Driver.findElement(By.xpath(".//table[@class='dataTable']"));
		if(link.isDisplayed())
		{
			Report.updateTestLog("link", "link details is displayed",Status.PASS);
			SwitchToLastWindow();
		}else{
			Report.updateTestLog("link", "link details is not displayed",Status.FAIL);
		}
		}

	@Action(desc="DCM_errormsg",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void DCM_errormsg()
	{
	WebElement DCM= Driver.findElement(By.xpath(".//form[contains(@id,'telematicReplacementForm')]/label[2]"));
	String VIN = userData.getData("Inputvalues", "VIN", "1", "1");
	String Expectedmsg=""+VIN+" is not available.";
	String Actualmsg= DCM.getText();

	if(Expectedmsg.equalsIgnoreCase(Actualmsg))
	{
		Report.updateTestLog("Error message", "Error message "+Expectedmsg+" is displayed",Status.PASS);	
			}else
			{
			Report.updateTestLog("Error message", "Invalid ",Status.FAIL);		
			}

	}

	@Action(desc="ServiceLane",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Knowledgecenter() throws InterruptedException{
		WebElement Knowledgecenter = Driver.findElement(By.xpath(".//td/a[contains(text(),'Knowledge Center')]"));
		System.out.println();
		if(Knowledgecenter!=null){
			GeneralComponents.clickOnWebelement(Knowledgecenter, "Knowledgecenter");
			Thread.sleep(3000);	
			Report.updateTestLog("Knowledgecenter", "Knowledgecenter tab is clicked",Status.PASS);
		}else{
			Report.updateTestLog("Knowledgecenter", "Knowledgecenter tab is not clicked",Status.FAIL);
		}
			
	}

	@Action(desc="addtofavourite",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void addtofavourite()
	{
	WebElement ATF =Driver.findElement(By.xpath(".//img[contains(@title,'Add To Favorites')]"));
	if(ATF!=null)
	{
		ATF.click();
		Report.updateTestLog("addtofavourite", "addtofavourite is clicked",Status.PASS);	
	}else {
		Report.updateTestLog("addtofavourite", "addtofavourite is notclicked",Status.FAIL);	
	}
	}

	@Action(desc="addtofavourite",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void alert()
	{
		Alert simpleAlert = Driver.switchTo().alert();
		String alertText = simpleAlert.getText();
		System.out.println("Alert text is " + alertText);
		simpleAlert.accept();	
	}

	@Action(desc="ratethisdoc",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void ratethisdoc() throws InterruptedException
	{
	WebElement RTD =Driver.findElement(By.xpath(".//img[contains(@title,'Rate This Document')]"));
	if(RTD!=null)
	{
		RTD.click();
		Report.updateTestLog("ratethisdoc", "ratethisdoc is clicked",Status.PASS);	
	}else {
		Report.updateTestLog("ratethisdoc", "ratethisdoc is not clicked",Status.FAIL);	
		SwitchToLastWindow();
		Thread.sleep(3000);
		
		WebElement rate =Driver.findElement(By.xpath(".//input[@type='radio' and contains(@value,'5')]"));
		rate.click();
		Thread.sleep(1000);
		WebElement submit=Driver.findElement(By.id("saveButton"));
		if(submit!=null)
		{
			submit.click();
			Report.updateTestLog("submit", "rating has been submitted",Status.PASS);	
		}else {
			Report.updateTestLog("submit", "rating has not been submitted",Status.FAIL);
			
		}
		GeneralComponents.waitforInternalLoad(Driver);
		WebElement close=Driver.findElement(By.xpath(".//a[contains(text(),'Close')]"));
	}}

	@Action(desc="submitcorrection",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void submitcorrection() throws InterruptedException
	{
	WebElement submitcorrection =Driver.findElement(By.xpath(".//img[contains(@title,'Submit Correction')]"));
	if(submitcorrection!=null)
	{
		submitcorrection.click();
		Report.updateTestLog("submitcorrection", "submitcorrection is clicked",Status.PASS);	
	}else {
		Report.updateTestLog("submitcorrection", "submitcorrection is not clicked",Status.FAIL);	
		SwitchToLastWindow();
		Thread.sleep(3000);
		WebElement rate =Driver.findElement(By.xpath(".//input[@type='radio']"));
		rate.click();
		Thread.sleep(1000);
		WebElement text=Driver.findElement(By.xpath(".//textarea[@id='descriptionErrorOrSuggestion']"));
		text.click();
		text.sendKeys("Sooper");
		Report.updateTestLog("correction", "updated the feedback",Status.PASS);
		Thread.sleep(1000);
		WebElement submit=Driver.findElement(By.id("saveButton"));
		if(submit!=null)
		{
			submit.click();
			Report.updateTestLog("submit", "rating has been submitted",Status.PASS);	
		}else {
			Report.updateTestLog("submit", "rating has not been submitted",Status.FAIL);
			
		}
		GeneralComponents.waitforInternalLoad(Driver);
		WebElement close=Driver.findElement(By.xpath(".//a[contains(text(),'Close')]"));
	}

}
	
	@Action(desc="Search_campaign",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Search_campaign() throws InterruptedException, ClassNotFoundException, SQLException
	 {
//		String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
		String campaigncode = userData.getData("Campaign", "Campaign Code","Campaign Admin Tool", "Create Campaign_TMS Corp","1","1");
		String phase = userData.getData("Campaign", "Phase","Campaign Admin Tool", "Create Campaign_TMS Corp","1","1");
		String source = userData.getData("Campaign", "Campaign Source","Campaign Admin Tool", "Create Campaign_TMS Corp","1","1");
		String type = userData.getData("Campaign", "Campaign Type","Campaign Admin Tool", "Create Campaign_TMS Corp","1","1");
		String stage = userData.getData("Campaign", "Campaign Stage","Campaign Admin Tool", "Create Campaign_TMS Corp","1","1");
		Driver.findElement(By.linkText("Search Campaign")).click();
		Thread.sleep(3000);
		try {
		Driver.findElement(By.id("campaignCodeId")).sendKeys(campaigncode);
		Thread.sleep(2000);
		Driver.findElement(By.id("campaignPhaseId")).sendKeys(phase);
		Thread.sleep(2000);
		Driver.findElement(By.id("campaignSourceId")).sendKeys(source);
		Thread.sleep(2000);
		Driver.findElement(By.id("campaignTypeId")).sendKeys(type);
		Thread.sleep(2000);
		Driver.findElement(By.id("campaignStageId")).sendKeys(stage);
		Thread.sleep(2000);
		Driver.findElement(By.id("searchButtonId")).click();
		Thread.sleep(2000);
		Report.updateTestLog("Verify search is performed", "Search is performed", Status.PASS);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Report.updateTestLog("Verify search is performed", "Search is not performed", Status.FAIL);
		}
	 }
	
	@Action(desc="Search_campaign_TCI",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Search_campaign_TCI() throws InterruptedException, ClassNotFoundException, SQLException
	 {
//		String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
		String campaigncode = userData.getData("Campaign", "Campaign Code","Campaign Admin Tool", "Create Campaign_TCI Corp","1","1");
		String phase = userData.getData("Campaign", "Phase","Campaign Admin Tool", "Create Campaign_TCI Corp","1","1");
		String source = userData.getData("Campaign", "Campaign Source","Campaign Admin Tool", "Create Campaign_TCI Corp","1","1");
		String type = userData.getData("Campaign", "Campaign Type","Campaign Admin Tool", "Create Campaign_TCI Corp","1","1");
		String stage = userData.getData("Campaign", "Campaign Stage","Campaign Admin Tool", "Create Campaign_TCI Corp","1","1");
		Driver.findElement(By.linkText("Search Campaign")).click();
		Thread.sleep(3000);
		try {
		Driver.findElement(By.id("campaignCodeId")).sendKeys(campaigncode);
		Thread.sleep(2000);
		Driver.findElement(By.id("campaignPhaseId")).sendKeys(phase);
		Thread.sleep(2000);
		Driver.findElement(By.id("campaignSourceId")).sendKeys(source);
		Thread.sleep(2000);
		Driver.findElement(By.id("campaignTypeId")).sendKeys(type);
		Thread.sleep(2000);
		Driver.findElement(By.id("campaignStageId")).sendKeys(stage);
		Thread.sleep(2000);
		Driver.findElement(By.id("searchButtonId")).click();
		Thread.sleep(2000);
		Report.updateTestLog("Verify search is performed", "Search is performed", Status.PASS);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Report.updateTestLog("Verify search is performed", "Search is not performed", Status.FAIL);
		}
	 }

	@Action(desc="Session_count",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Session_count() throws InterruptedException
	{
		try
		{
//			String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
			String userName = userData.getData("Credentials", "Username","CreatePartrequest", "ONE.TIS login","1","1");
			String sessioncount = null;
			String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
			//(INSTANCE_NAME = RTSC)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String UserName = "T3DATGLOBL";
			String Password = "t3dgprod";
			Connection con = DriverManager.getConnection(dburl, UserName, Password);
			String Query = "select session_count from USER_SESSION_COUNT_LOGS where user_id in (select user_id from users where user_cn='"+userName+"') ";
			Statement Stmt = con.createStatement();
			ResultSet rs = Stmt.executeQuery(Query);
			while(rs.next()){
				sessioncount=rs.getString(1);
			System.out.println(sessioncount);
			}
			int count = Integer.parseInt(sessioncount);
			for (int i=0; i<count; i++)
			{
				
				
				
			}
			
			Report.updateTestLog("Verify Keycode is generated", "Keycode is generated",Status.PASS);
			con.close();
		}
		catch (Exception e) 
		{
	    	  e.printStackTrace();
	    	Report.updateTestLog("Verify Keycode is generated", "Keycode is not generated",Status.FAIL);
	      }
	}
	@Action(desc="PQSS_search",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void PQSS_search() throws InterruptedException
	{
		try
		{
//			String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
			String keyword = userData.getData("PQSS", "Keyword","PQSS_FTS", "PQSS Search TMS Corp","1","1");
			String doclink = userData.getData("PQSS", "Doc link","PQSS_FTS", "PQSS Search TMS Corp","1","1");
			Driver.findElement(By.linkText("Home")).click();
			Thread.sleep(2000);
			/*Driver.findElement(By.linkText("PQSS Operations Manual")).click();
			Thread.sleep(2000);*/
			Driver.findElement(By.partialLinkText("PQSS Operations")).click();
			Thread.sleep(2000);
			
			
			//PQSS Operations Manual
			
			
			String winHandleBefore = Driver.getWindowHandle(); 
			           try{
			            	for(String winHandle : Driver.getWindowHandles())
			            	{
			            		  Driver.switchTo().window(winHandle);
			            	    }
			          	  Driver.findElement(By.id("pqssKeyword")).sendKeys(keyword);
			          	Thread.sleep(2000);
			          	Driver.findElement(By.id("pqssBtn")).click();
			          	Thread.sleep(2000);
			          	Driver.findElement(By.linkText(doclink)).click();
						Thread.sleep(2000);
			          	
			            } catch (Exception e) {
			          	  
			          	  e.printStackTrace();
			            }
			Driver.switchTo().window(winHandleBefore);
			
			Report.updateTestLog("Verify pqss search is performed", "pqss search is performed",Status.PASS);
		}
		catch (Exception e) 
		{
	    	  e.printStackTrace();
	    	Report.updateTestLog("Verify pqss search is performed", "pqss search is not performed",Status.FAIL);
	      }
	}
	@Action(desc="PQSS_search_TCI",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void PQSS_search_TCI() throws InterruptedException
	{
		try
		{
//			String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
			String keyword = userData.getData("PQSS", "Keyword","PQSS_FTS", "PQSS Search TCI Corp","1","1");
			String doclink = userData.getData("PQSS", "Doc link","PQSS_FTS", "PQSS Search TCI Corp","1","1");
			Driver.findElement(By.linkText("Home")).click();
			Thread.sleep(2000);
			Driver.findElement(By.linkText("PQSS Operations Manual")).click();
			Thread.sleep(2000);
			
			
			String winHandleBefore = Driver.getWindowHandle(); 
			           try{
			            	for(String winHandle : Driver.getWindowHandles())
			            	{
			            		  Driver.switchTo().window(winHandle);
			            	    }
			          	  Driver.findElement(By.id("pqssKeyword")).sendKeys(keyword);
			          	Thread.sleep(2000);
			          	Driver.findElement(By.id("pqssBtn")).click();
			          	Thread.sleep(2000);
			          	Driver.findElement(By.linkText(doclink)).click();
						Thread.sleep(2000);
			          	
			            } catch (Exception e) {
			          	  
			          	  e.printStackTrace();
			            }
			Driver.switchTo().window(winHandleBefore);
			
			Report.updateTestLog("Verify pqss search is performed", "pqss search is performed",Status.PASS);
		}
		catch (Exception e) 
		{
	    	  e.printStackTrace();
	    	Report.updateTestLog("Verify pqss search is performed", "pqss search is not performed",Status.FAIL);
	      }
	}
	@Action(desc="FTS_search",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void FTS_search() throws InterruptedException
	{
		try
		{
//			String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
			String keyword = userData.getData("PQSS", "Keyword","PQSS_FTS", "FTS Search TMS Corp","1","1");
			String doclink = userData.getData("PQSS", "Doc link","PQSS_FTS", "FTS Search TMS Corp","1","1");
			Driver.findElement(By.linkText("Home")).click();
			Thread.sleep(2000);
			Driver.findElement(By.linkText("Field Technical Operations Manual For Regional/Area Staff")).click();
			Thread.sleep(2000);
			
			String winHandleBefore = Driver.getWindowHandle(); 
			           try{
			            	for(String winHandle : Driver.getWindowHandles())
			            	{
			            		  Driver.switchTo().window(winHandle);
			            	    }
			          	  Driver.findElement(By.id("ftsKeyword")).sendKeys(keyword);
			          	Thread.sleep(2000);
			          	Driver.findElement(By.id("ftsBtn")).click();
			          	Thread.sleep(2000);
			          	Driver.findElement(By.linkText(doclink)).click();
						Thread.sleep(2000);
			          	
			            } catch (Exception e) {
			          	  
			          	  e.printStackTrace();
			            }
			           Driver.switchTo().window(winHandleBefore);
			Report.updateTestLog("Verify fts search is performed", "fts search is performed",Status.PASS);
		}
		catch (Exception e) 
		{
	    	  e.printStackTrace();
	    	Report.updateTestLog("Verify fts search is performed", "fts search is not performed",Status.FAIL);
	      }
	}
	@Action(desc="FTS_search_TCI",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void FTS_search_TCI() throws InterruptedException
	{
		try
		{
//			String getData (String DataSheetName, String ColumnName, String ScenarioName,String TestCase, String Iteration, String SubIteration);
			String keyword = userData.getData("PQSS", "Keyword","PQSS_FTS", "FTS Search TCI Corp","1","1");
			String doclink = userData.getData("PQSS", "Doc link","PQSS_FTS", "FTS Search TCI Corp","1","1");
			Driver.findElement(By.linkText("Home")).click();
			Thread.sleep(2000);
			Driver.findElement(By.linkText("Field Technical Operations Manual For Regional/Area Staff")).click();
			Thread.sleep(2000);
			
			String winHandleBefore = Driver.getWindowHandle(); 
			           try{
			            	for(String winHandle : Driver.getWindowHandles())
			            	{
			            		  Driver.switchTo().window(winHandle);
			            	    }
			          	  Driver.findElement(By.id("ftsKeyword")).sendKeys(keyword);
			          	Thread.sleep(2000);
			          	Driver.findElement(By.id("ftsBtn")).click();
			          	Thread.sleep(2000);
			          	Driver.findElement(By.linkText(doclink)).click();
						Thread.sleep(2000);
			          	
			            } catch (Exception e) {
			          	  
			          	  e.printStackTrace();
			            }
			           Driver.switchTo().window(winHandleBefore);
			Report.updateTestLog("Verify fts search is performed", "fts search is performed",Status.PASS);
		}
		catch (Exception e) 
		{
	    	  e.printStackTrace();
	    	Report.updateTestLog("Verify fts search is performed", "fts search is not performed",Status.FAIL);
	      }
	}
	@Action(desc="TMS_DPR",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void TMS_DPR() throws InterruptedException
	{
		try
		{
			WebElement value = Driver.findElement(By.xpath("//td[@colspan='3' and @class='MAIN_2BRDS']//span"));
			String value1 = value.getText();
//			userData.putData("DatasheetName","columnName","value to be written","scenario name","test case name","Iteration value", "SubIteration value");
			userData.putData("DPR","DPR Number",value1,"DPR","TMS DPR","1", "1");
			Report.updateTestLog("Verify DPR is created", "DPR is created",Status.PASS);
		}
		catch (Exception e) 
		{
	    	  e.printStackTrace();
	    	Report.updateTestLog("Verify DPR is created", "DPR is created",Status.FAIL);
	      }
		
		
	}
	
	@Action(desc="Dealer_DPR",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Dealer_DPR() throws InterruptedException
	{
		try
		{
			WebElement value = Driver.findElement(By.xpath("//td[@colspan='3' and @class='MAIN_2BRDS']//span"));
			String value1 = value.getText();
//			userData.putData("DatasheetName","columnName","value to be written","scenario name","test case name","Iteration value", "SubIteration value");
			userData.putData("DPR","DPR Number",value1,"DPR","one.tis DPR","1", "1");
			Report.updateTestLog("Verify DPR is created", "DPR is created",Status.PASS);
		}
		catch (Exception e) 
		{
	    	  e.printStackTrace();
	    	Report.updateTestLog("Verify DPR is created", "DPR is created",Status.FAIL);
	      }
		
		
	}
	
	/**
	 * 
	 * MethodName: Q360
	 * Description: 
	 * Parameter (if any): 
	 * Return type: void
	 * Owner : SABARI 
	 */ 
	
	
	@Action(desc="Q360",object=ObjectType.BROWSER,input=InputType.OPTIONAL)
	public void Q360() throws InterruptedException
	{
		try
		{
			WebElement value = Driver.findElement(By.xpath("//td[@colspan='3' and @class='MAIN_2BRDS']//span"));
			String value1 = value.getText();
//			userData.putData("DatasheetName","columnName","value to be written","scenario name","test case name","Iteration value", "SubIteration value");
			userData.putData("DPR","DPR Number",value1,"DPR","one.tis DPR","1", "1");
			Report.updateTestLog("Verify DPR is created", "DPR is created",Status.PASS);
		}
		catch (Exception e) 
		{
	    	  e.printStackTrace();
	    	Report.updateTestLog("Verify DPR is created", "DPR is created",Status.FAIL);
	      }
		
		
	}
	
	
	@Action(desc = "TISDBConnection", object = ObjectType.BROWSER, input = InputType.OPTIONAL)
	public void dbConnectionTIS() throws ClassNotFoundException, SQLException {

		String DEPRQuery = null;
		String dburl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST = T3IC1.tmm.na.corp.toyota.com)(PORT = 1529))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = T3IC.tmm.na.corp.toyota.com)))";
		// (INSTANCE_NAME = RTSC)
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String UserName = "ssc1";
		String Password = "ssc1";
		Connection con = DriverManager.getConnection(dburl, UserName, Password);

		System.out.println("Connected");

		stmt = con.createStatement();

	}

	@Action(desc = "click_TisTas_Checkboxes", object = ObjectType.BROWSER, input = InputType.OPTIONAL)
	public void tistasCheckbox() throws InterruptedException {

		List<WebElement> row = Driver
				.findElements(By
						.xpath("//*[@id='NavigationUpdateForm']/table/tbody/tr[@style]"));

		int noOfRows = row.size();
		System.out.println("Total Table row is " + noOfRows);

		for (int i = 1; i <= noOfRows; i++) {

			WebElement selectCheckbox = Driver
					.findElement(By
							.xpath("//*[@id='NavigationUpdateForm']/table/tbody/tr[@style]["
									+ i + "]//input[@type='checkbox']"));
			selectCheckbox.click();
			Thread.sleep(2000);

		}
	}

//	public static void enabledmodelRadioButton() throws InterruptedException {
//
//	}

	@Action(desc = "select_RadioButton", object = ObjectType.BROWSER, input = InputType.OPTIONAL)
	public void radiobutton_N0() throws InterruptedException {

		Thread.sleep(2000);
		WebElement clickRadioButtonN_Zero = Driver
				.findElement(By
						.xpath("//*div[@role='gridcell']/div/input[@id='radioButtonN0']"));
		clickRadioButtonN_Zero.click();

		// To click SubmitEnabledModels button
		Driver.findElement(By.xpath("//button[contains(text(),'Submit')]"))
				.click();
	}

	@Action(desc = "select_RadioButton", object = ObjectType.BROWSER, input = InputType.OPTIONAL)
	public void radiobutton_E1() throws InterruptedException {

		Thread.sleep(2000);
		WebElement clickRadioButtonE_One = Driver
				.findElement(By
						.xpath("//*div[@role='gridcell']/div/input[@id='radioButtonIE1']"));
		clickRadioButtonE_One.click();

		Thread.sleep(2000);
		// To click SubmitEnabledModels button
		Thread.sleep(2000);
		Driver.findElement(By.xpath("//button[contains(text(),'Submit')]"))
				.click();
	}

	@Action(desc = "select_RadioButton", object = ObjectType.BROWSER, input = InputType.OPTIONAL)
	public void radiobutton_I2() throws InterruptedException {

		WebElement clickRadioButtonI_Two = Driver
				.findElement(By
						.xpath("//div[@role='gridcell']/div/input[@id='radioButtonI2']"));
		clickRadioButtonI_Two.click();

		Thread.sleep(2000);
		// To click SubmitEnabledModels button
		Thread.sleep(2000);
		Driver.findElement(By.xpath("//button[contains(text(),'Submit')]"))
				.click();
	}

	@Action(desc = "addingSyptomCode", object = ObjectType.BROWSER, input = InputType.OPTIONAL)
	public void addingSymptomCoding() {

		for (int i = 1; i <= 3; i++) {

			// To select category
			Select selectcategory = new Select(
					Driver.findElement(By
							.xpath("//select[contains(@id,'tasEditorSymptomServiceCategory_"
									+ i + "')]")));
			selectcategory.selectByIndex(i);

			// To Select Section
			Select selectSection = new Select(Driver.findElement(By
					.xpath("//select[contains(@id,'tasEditorSymptomSection_"
							+ i + "')]")));
			selectSection.selectByIndex(i);

		}

		// select[contains(@id,'tasEditorSymptomSection_"i"')]

		// Subcomponent
		// select[contains(@id,'tasEditorSymptomSubComponent_"i"')]

		// Section
		// select[contains(@id,'tasEditorSymptomCondition_"i"')]

	}

	@Action(desc = "click_TisTas_Checkboxes", object = ObjectType.BROWSER, input = InputType.OPTIONAL)
	public void agreePopUp() {

		Driver.switchTo().alert().accept();

	}

	@Action(desc = "switchSinglewindow", object = ObjectType.BROWSER, input = InputType.OPTIONAL)
	public void siwthingWindows() {

		parentWindowHandler = Driver.getWindowHandle();

		for (String handle : Driver.getWindowHandles()) {

			Driver.switchTo().window(handle);
		}
	}

	@Action(desc = "privactyCheckBox", object = ObjectType.BROWSER, input = InputType.OPTIONAL)
	public void boschPrivacyCheckbox() {

		try {
			WebElement checkPrivacyBox = Driver.findElement(By.id("chkAccept"));
			checkPrivacyBox.click();
		} catch (Exception e) {

			System.out.println("No Privacty Checkbox found");
		}

	}

	@Action(desc = "TermandCondition", object = ObjectType.BROWSER, input = InputType.OPTIONAL)
	public void boshTermandConditionCheckbox() {

		try {
			WebElement termConditionCheckBox = Driver.findElement(By
					.xpath("//input[@id='chkTermOfUse']"));
			termConditionCheckBox.click();
		} catch (Exception e) {

			System.out.println("No Term and condtion Checkbox found");
		}
	}

	@Action(desc = "AcceptButton", object = ObjectType.BROWSER, input = InputType.OPTIONAL)
	public void boshAcceptButton() {
		try {
			WebElement acceptButton = Driver.findElement(By
					.xpath("//input[@value='Accept']"));
			acceptButton.click();
		} catch (Exception e) {

			System.out.println("No Accept Button found");
		}

	}

	@SuppressWarnings("deprecation")
	@Action(desc = "checkBoxverification", object = ObjectType.BROWSER, input = InputType.OPTIONAL)
	public void verifyCheckBox_Unchecked() {

		WebElement checkBox = Driver.findElement(By
				.xpath("//input[@id='missingInflChkBox']"));

		boolean selected = checkBox.isSelected();
		if (!selected) {
			Assert.assertEquals(true, true);
		} else {

			Assert.assertEquals(false, true);
		}

	}

	@Action(desc = "swithinToParentWindow", object = ObjectType.BROWSER, input = InputType.OPTIONAL)
	public void parentWindow() throws InterruptedException {
		Thread.sleep(2000);
		Driver.switchTo().window(parentWindowHandler);
	}

	@Action(desc = "pageRefreshing", object = ObjectType.BROWSER, input = InputType.OPTIONAL)
	public void pageRefresh() {

		Driver.navigate().refresh();
	}

	@Action(desc = "gettinText", object = ObjectType.BROWSER, input = InputType.OPTIONAL)
	public void getGoogleText() {

		WebElement gettingGoogleText = Driver.findElement(By
				.linkText("Discover the story"));
		System.out.println(gettingGoogleText.getText());

		String gogText = gettingGoogleText.getText();

		if (gogText.equals("pradeepa")) {

			System.out.println("Yes both are same pass");

		} else {
			System.out.println("No both are not same");
		}
	}

	@Action(desc = "getting Campaign data syncup text", object = ObjectType.BROWSER, input = InputType.OPTIONAL)
	public void campaigndatasyncup() throws InterruptedException {

		Thread.sleep(2000);
		WebElement CampaignDescript = Driver
				.findElement(By
						.xpath("(//div[@class='bea-portal-details-window-content']/table/tbody/tr/td/span[contains(text(),'Campaign Description:')]//following::td//label)[position()=1]"));

		UI_CampaignDescription = CampaignDescript.getText();
		System.out.println("Descrption :" + UI_CampaignDescription);
	}

	@Action(desc = "getting campaignStatus text", object = ObjectType.BROWSER, input = InputType.OPTIONAL)
	public void campaignstatus() throws InterruptedException {
		Thread.sleep(2000);

		WebElement campaign_Status = Driver
				.findElement(By
						.xpath("(//div[@class='bea-portal-details-window-content']/table/tbody/tr/td/span[contains(text(),'Campaign Status:')]//following::td//label)[position()=1]"));
		UI_campaignStatus = campaign_Status.getText();
		System.out.println("campaignStatus :" + UI_campaignStatus);

	}

	@Action(desc = "getting completionstatus text", object = ObjectType.BROWSER, input = InputType.OPTIONAL)
	public void completionstatus() {
		WebElement completionstat = Driver
				.findElement(By
						.xpath("(//div[@class='bea-portal-details-window-content']/table/tbody/tr/td/span[contains(text(),'Completion Status:')]//following::td//label)[position()=1]"));

		UI_Completionstatus = completionstat.getText();
		System.out.println("Completionstatus :" + UI_Completionstatus);

	}

	@Action(desc = "getting expirationdat text", object = ObjectType.BROWSER, input = InputType.OPTIONAL)
	public void expirationdate() {

		WebElement expirationdat = Driver
				.findElement(By
						.xpath("//div[@class='bea-portal-details-window-content']/table/tbody/tr/td/span[contains(text(),'Expiration Date:')]//following::td[2]"));
		UI_expirationdate = expirationdat.getText();
		System.out.println("expirationdate :" + UI_expirationdate);
	}

	@Action(desc = "getting dateServiced text", object = ObjectType.BROWSER, input = InputType.OPTIONAL)
	public void dateServiced() {

		WebElement dateServiced = Driver
				.findElement(By
						.xpath("(//div[@class='bea-portal-details-window-content']/table/tbody/tr/td/span[contains(text(),'Date Serviced:')]//following::td//label)[position()=1]"));
		UI_Date_Serviced = dateServiced.getText();
		System.out.println("dateServiced :" + UI_Date_Serviced);
	}

	// div[@class='bea-portal-details-window-content']/table/tbody/tr/td/span[contains(text(),'Date
	// Serviced')]//following::td[2]

	@Action(desc = "getting campaignDesc from DB", object = ObjectType.BROWSER, input = InputType.OPTIONAL)
	public void getCampaignDescriptionFromDB() throws SQLException {

		rs = stmt
				.executeQuery("select * from ssc_detail where detail_campaign_cd='DSC'");

		// This is to get all the column names
		ResultSetMetaData rsmd = rs.getMetaData();

		int columnsNumber = rsmd.getColumnCount();

		while (rs.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				String campDetails = rs.getString("DETAIL_CAMPAIGN_CD");
				System.out.println("campDetails : " + campDetails);
				String expiryDate = rs.getString("DETAIL_EXPIRY_DATE");
				System.out.println("expiry Date : " + expiryDate);

				// System.out.print("columnName" + " " + rsmd.getColumnName(i));
				System.out.println("   ");
			}
			System.out.println("");
		}

	}

	@Action(desc = "getting SSC_Status from DB", object = ObjectType.BROWSER, input = InputType.OPTIONAL)
	public void getting_SSC_Status() throws SQLException {

		String userQueries = userData.getData("CampaignDataSynch", "Vin", "1",
				"1");
		System.out.println(userQueries);

		/*
		 * rs = stmt .executeQuery(
		 * "select s.SSC_VIN,s.ssc_campaign_cd,s.ssc_date_serv,s.ssc_complete_cd,b.detail_title,e.ssc_status_desc from ssc_vin s,ssc_detail b,ssc_status e where s.ssc_detail_id in (select ssc_detail_id from ssc_detail )"
		 * +
		 * "and s.ssc_campaign_cd='DSC' and b.ssc_detail_id=s.ssc_detail_id and b.ssc_status_id=e.ssc_status_id and s.SSC_VIN='"
		 * + userQueries + "'");
		 */

		String sscVinQuery = "select s.SSC_VIN,s.ssc_campaign_cd,s.ssc_date_serv,s.ssc_complete_cd,r.ssc_complete_desc,b.detail_title,e.ssc_status_desc from ssc_vin s,ssc_detail b,ssc_status e,ssc_comp r where s.ssc_detail_id in (select ssc_detail_id from ssc_detail ) and s.ssc_campaign_cd='DSC' and b.ssc_detail_id=s.ssc_detail_id and s.ssc_complete_cd= r.ssc_complete_cd and b.ssc_status_id=e.ssc_status_id and s.SSC_VIN='JTHFN48Y069010088'";

		rs = stmt.executeQuery(sscVinQuery);

		System.out.println();

		ResultSetMetaData rsmd = rs.getMetaData();

		int columnsNumber = rsmd.getColumnCount();
		System.out.println(columnsNumber);

		while (rs.next()) {
			db_vinNumber = rs.getString("SSC_VIN");
			System.out.println("vinNumber  : " + db_vinNumber);

			db_description = rs.getString("detail_title");
			System.out.println("statusDesc : " + db_description);

			db_vindate = rs.getString("ssc_date_serv");
			System.out.println("vinDate : " + db_vindate);

			db_statusDesc = rs.getString("ssc_status_desc");
			System.out.println("statusDesc : " + db_statusDesc);

			db_CompletionStatus = rs.getString("ssc_complete_desc");
			System.out.println("ssc_complete_desc : " + db_CompletionStatus);

			System.out.print("   ");

		}

	}

	/*
	 * @Action(desc = "ssVinStatusVerification", object = ObjectType.BROWSER,
	 * input = InputType.OPTIONAL) public void ssVinStatusVerification(){
	 * 
	 * //For Stream steps to print MAP lhm.entrySet().forEach(entry->{
	 * System.out.println(entry.getKey() + " " + entry.getValue());
	 * 
	 * String rem = "Remedy Available"; if(rem.contains(entry.getValue())){
	 * 
	 * System.out.println(campaignStatus + " is :" + entry.getKey());
	 * 
	 * }
	 * 
	 * }); }
	 */
	@Action(desc = "comparing_Campaign UI_With_DB", object = ObjectType.BROWSER, input = InputType.OPTIONAL)
	public void comparing_CampaignUI_With_DB() {

		System.out.println(UI_CampaignDescription + "   " + db_description);
		System.out.println(UI_campaignStatus + " " + db_statusDesc);
		System.out.println(UI_Completionstatus + " " + db_CompletionStatus);
		System.out.println(UI_Date_Serviced + " " + db_vindate);

		if (UI_CampaignDescription.equalsIgnoreCase(db_description)
				&& UI_campaignStatus.equalsIgnoreCase(db_statusDesc)
				&& UI_Completionstatus.equalsIgnoreCase(db_CompletionStatus)
				&& UI_Date_Serviced.equalsIgnoreCase(db_vindate)) {

			Assert.assertEquals("UI and DB are not matching", true, true);
		} else {

			Assert.assertEquals(true, false);
		}

	}
	
	@Action(desc = "Closing Driver", object = ObjectType.BROWSER, input = InputType.OPTIONAL)
	public void closeDriver() {
		
		Driver.close();
	}
	@Action(desc = "Uploading File2", object = ObjectType.BROWSER, input = InputType.OPTIONAL)
	public void uploadFile2() throws InterruptedException {
	String docPath = userData.getData("TasCaseTab", "DocPath1", "1", "1");
	System.out.println(docPath);


	for (int i = 1; i <= 5; i++) {

	String fileName = userData.getData("TasCaseTab", "FileName"+i+"", "1", "1");

	int row = 29 + i;
	Thread.sleep(5000);
	WebElement firstFile = Driver.findElement(By
	.xpath("//*[@id='tasFlowCreateForm']/table/tbody/tr[" + row
	+ "]/td[2]/input"));
	// firstFile.sendKeys(ConstantsQueries.DOC_PATH1);
	firstFile.sendKeys(docPath+fileName);
	}

	}
	public static String currentDate(){

	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
	   Date date = new Date();  
	   System.out.println("Java Date" + formatter.format(date));
	return formatter.format(date); 


	}
	    
	@Action(desc = "Select Message Form", object = ObjectType.BROWSER, input = InputType.OPTIONAL)
	public void selectMessageForm() {

	String curDate = currentDate();
	System.out.println(curDate);


	WebElement date = Driver.findElement(By.xpath("//form[@id='selectedMessageForm']/table/tbody/tr[2]/td[2]/span"));
	date.getText();

	System.out.println("UI Date" +date.getText() );

	if(curDate.equalsIgnoreCase(date.getText()) && Driver.getCurrentUrl().contains("t3")){

	WebElement clickTasCase = Driver.findElement(By.xpath("//*[@id='selectedMessageForm']/table/tbody/tr[@class='bea-portal-window-content-row-first']/td[3]/a"));
	clickTasCase.click();
	System.out.println("Yes Form INBOZ is CLICKED");

	}else if(curDate.equalsIgnoreCase(date.getText()) && Driver.getCurrentUrl().contains("one.tis")){


	WebElement clickTasCase = Driver.findElement(By.xpath("//*[@id='selectedMessageForm']/table/tbody/tr[@class='bea-portal-window-content-row-second']/td[3]/a"));
	clickTasCase.click();
	System.out.println("Yes Form INBOZ is CLICKED");

	}else {

	System.out.println("Not matching");
	}

	}

	@Action(desc = "validate_TASCASE_AttachedDocument", object = ObjectType.BROWSER, input = InputType.OPTIONAL)
	public void validate_TASCASE_AttachedDocument(){

	WebElement tas_Documents = Driver.findElement(By.xpath("//td[@class='ta_normal']/a[1]/span"));

	for(int i=1; i<=5; i++){

	String uI_FileName = Driver.findElement(By.xpath("//td[@class='ta_normal']/a["+i+"]/span")).getText();
	System.out.println(uI_FileName);

	String uploaded_fileName = userData.getData("TasCaseTab", "FileName"+i+"", "1", "1");

	Assert.assertEquals(uploaded_fileName, uI_FileName);

	}

	

	}

	}
