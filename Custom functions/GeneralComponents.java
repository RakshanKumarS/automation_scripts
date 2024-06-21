package com.cognizant.businessComponents;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.Alert;

//import com.cognizant.support.Toyo_Supportlibrary;
//import com.cognizant.support.Toyo_Supportlibrary;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.cognizant.cognizantits.engine.commands.Command;
import com.cognizant.cognizantits.engine.commands.galenCommands.Report;
import com.cognizant.cognizantits.engine.core.CommandControl;
import com.cognizant.cognizantits.engine.reporting.impl.html.bdd.FeatureReport.Element;
import com.cognizant.cognizantits.engine.support.Status;


public class GeneralComponents extends Command{
	
	  public GeneralComponents(CommandControl cc) {
	        super(cc);
	  }
	  
	  public String alertMsg = null;
		public int cntr = 1;
		public void highlightElement(WebDriver driver,WebElement element) {
	        try {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
				        element, "color: green; border: 2px solid green;");
				//js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
				 //       element, "");
			} catch (Exception e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
		}
		public void enterValue(WebElement wElement, String value, String fieldName){
			 try {
				  wElement.clear();
//				  highlightElement(Driver,wElement);
				  wElement.sendKeys(value);
				  Report.updateTestLog("Enter "+fieldName, fieldName+" ["+value+"] has been entered", Status.DONE);
				  sleepTime(Driver, 1);
			} catch (Exception e) {
				Report.updateTestLog("Enter "+fieldName,"Exception occured while trying to enter "+fieldName+" ["+value+"]. Exception is ["+e+"]",Status.FAIL);
			}
		  }

		public void sleep()
		{
			//Settings setting = new Settings();
			String tc=userData.getTestCase();
			//String testCase=""+setting.TestCase;;
			String sleepTime= userData.getData("CoreInfo", "SleepTime", "Commodity",tc, "1", "1");
			int time = Integer.parseInt(sleepTime);
			 sleepTime(Driver,time );
		}
		public void sleepTime(WebDriver Driver,int time )
		
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
		
		
		
		 public WebElement findObject(WebDriver Driver,By by,String elementName){
			  WebElement wElement = null; int timeOut = 0;
			  do{
				  if(timeOut > 0){
					sleepTime(Driver, 3);
				  }
				  try{
						wElement = Driver.findElement(by);
				  }catch (NoSuchElementException e) {timeOut++;}
			  }while(wElement == null && timeOut < 10);
			  try{
					wElement = Driver.findElement(by);
			  }catch (NoSuchElementException e) {
//				  Report.updateTestLog("Find "+elementName, "Exception occured while trying to find the "+elementName+". Exception ["+e+"]",Status.FAIL);
			  }	  
			  if(wElement == null){
				  Report.updateTestLog("Verifying"+elementName, elementName+" does not available", Status.FAIL);
				  //throw new FrameworkException("Find "+elementName, elementName+" not found");
			  }else{
				  try {
						((JavascriptExecutor) Driver).executeScript("arguments[0].scrollIntoView();", wElement);
					} catch (Exception e) {} 
			  }
			return wElement;
		  }
		  
		 
		  
//			Finding Object from WebElement
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
//					  Report.updateTestLog("Find "+elementName, "Exception occured while trying to find the "+elementName+". Exception ["+e+"]",Status.FAIL);
				  }
				  if(wElement == null){
					  Report.updateTestLog("Find "+elementName, elementName+" does not available", Status.FAIL);
				  }
				return wElement;
			}

			
			
			public void waitforInternalLoad(WebDriver Driver)
			{
				sleepTime(Driver, 1);
					  WebElement wElement = null; int timeOut = 0;
					  do{
						  if(timeOut > 0){
							sleepTime(Driver, 1);
						  }
						  try{
								wElement = Driver.findElement(By.xpath("//img[@alt='Busy'][contains(@src,'Hourglass_icon.gif')]"));
								if(wElement.isDisplayed())							
								{ timeOut++; continue;	}
								
								else 
									break;
						  }catch (NoSuchElementException e) {timeOut++;}
					  }while(wElement == null && timeOut < 20);
					 
					 
					
				  }
			/**
			 * Method Name :waitForobjecttodisappear
			 * Parameter Details (if any):
			 * Need/Purpose for the method :To wait till object disappears		
			 */
			
			
			public void waitForobjecttodisappear(WebElement wle,Integer seconds){
			        for (int second = 0;; second++) {
			        if (second >= seconds)
			            System.out.println("Waiting for object");
			        try {
//			            if (!("block".equals(wle.getCssValue("display"))
			            		if(!(wle.isDisplayed()))
			            		{
			                break;
			            		}
			            		} catch (Exception e) {
			        }
			        }
			    }
			 /**
			 * Method Name :executeQueries
			 * Parameter Details (if any):
			 * Need/Purpose for the method :To execute Queries		
			 */
			/*
			public void executeQueries()
			{  
				Toyo_Supportlibrary toyoSuppLib=new Toyo_Supportlibrary();
				Toyo_Supportlibrary.ctTestCase=Settings.TCName;
				Toyo_Supportlibrary.ctScenario=Settings.TCScenario;			
				
				
				toyoSuppLib.createTestDataFolder();
				toyoSuppLib.DeleteFile(toyoSuppLib.getTestDataDBPath());
				String strText = "Test data from oracle data base for test case " + Toyo_Supportlibrary.ctTestCase;
				toyoSuppLib.appendToTextFile(strText, toyoSuppLib.getTestDataDBPath());	
				
//				Get Environment details
				Object[] varDetails = Toyo_Supportlibrary.getRuntimeEnvironmentSettings(toyoSuppLib.runTimeTempFolder + "\\RuntimeEnvironmentSettings.cfg");
				toyoSuppLib.executeQueries(varDetails,null, "Queries");
				if(Toyo_Supportlibrary.configData==null)
				{			
					Report.updateTestLog("Result from DB for the Execute Query Action","No Data Found. DB Result Set is Empty",Status.FAIL);
					toyoSuppLib=null;
				}
				toyoSuppLib=null;
			}
			*/
		//New
					
				/**
				 * Method Name :getRandomNumberFrom
				 * Parameter Details (if any):
				 * Need/Purpose for the method :To generate random int number		
				 */	
				public int getRandomNumberFrom(int min, int max) {
			        Random foo = new Random();
			        int randomNumber = foo.nextInt((max + 1) - min) + min;
			        return randomNumber;
			    }
				
			/**
			 * Method Name :sortArrayValue
			 * Parameter Details (if any):
			 * Need/Purpose for the method :Sort String array using sort method		
			 */		  	  
			  @SuppressWarnings({ "rawtypes" })
				public ArrayList sortArrayValue(ArrayList<String> pList){
					 java.util.Collections.sort(pList);
			         return pList;
				}
				  
	 
			//Finding Object
				public List<WebElement> findObjectList(WebDriver Driver,By by,String elementName){
				  List<WebElement> wElement = null; int timeOut = 0;
				  do{
					  if(timeOut > 0){
						  sleepTime(Driver, 6);
					  }
					  try{
							wElement = Driver.findElements(by);
					  }catch (NoSuchElementException e) {timeOut++;}
				  }while(wElement == null && timeOut < 10);
				  try{
						wElement = Driver.findElements(by);
				  }catch (NoSuchElementException e) {
//					  Report.updateTestLog("Find "+elementName, "Exception occured while trying to find the "+elementName+". Exception ["+e+"]",Status.FAIL);
				  }	  
				  if(wElement == null){
					  Report.updateTestLog("Verifying"+elementName, elementName+" does not available", Status.FAIL);
				  }
				  return wElement;
				}
				
				//Finding Object
					public List<WebElement> findObjectList(WebElement wEle,By by,String elementName){
					  List<WebElement> wElement = null; int timeOut = 0;
					  do{
						  if(timeOut > 0){
							  sleepTime(Driver, 6);
						  }
						  try{
								wElement = wEle.findElements(by);
						  }catch (NoSuchElementException e) {timeOut++;}
					  }while(wElement == null && timeOut < 10);
					  try{
							wElement = wEle.findElements(by);
					  }catch (NoSuchElementException e) {
//						  Report.updateTestLog("Find "+elementName, "Exception occured while trying to find the "+elementName+". Exception ["+e+"]",Status.FAIL);
					  }	  
					  if(wElement == null){
						  Report.updateTestLog("Verifying"+elementName, elementName+" does not available", Status.FAIL);
					  }
					  return wElement;
					}
			 
				public boolean isElementPresent(WebDriver Driver,By by)
				{
				  	int timedOut = 10, cntr  =0;WebElement wEle = null;
					do {
						if(cntr > 0){
							sleepTime(Driver, 6);
						}
						try {
							wEle = Driver.findElement(by);
						} catch (Exception e) {
							cntr++;
						}
					} while (wEle == null && cntr <= timedOut);
					if(wEle != null)
						return true;
					else
						return false;
			  }
			  
				/*
				public void acceptAlert(WebDriver driver){
					Toyo_Supportlibrary toyo_Supportlibrary = new Toyo_Supportlibrary();
			         sleepTime(driver, 10);            
			         try {
			                     alertMsg =  driver.switchTo().alert().getText();       
			                     toyo_Supportlibrary.saveTestDataToFile ("STRVARALERTMSG"+cntr,alertMsg);
			                     System.out.println("Alert Msg:"+alertMsg);
			              driver.switchTo().alert().accept();
			       } catch (Exception e) {
			              // TODO Auto-generated catch block
			              e.printStackTrace();
			       }
			         cntr++;
			  }
*/
				
			  public void selectElement(WebElement wElement, String value, String fieldName){
				  try {
					  Select select = new Select(wElement);
					  
					  select.selectByVisibleText(value);
					  Report.updateTestLog("Select "+fieldName, fieldName+" ["+value+"] has been selected", Status.DONE);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Report.updateTestLog("Select "+fieldName, "Exception occured while trying to select "+fieldName+" ["+value+"]. Exception is ["+e+"]", Status.WARNING);
					}
				 }
			  //New code for select-clickandhold
			  public void selectElementbyclicknhold(WebElement wElement, String value, String fieldName){
			  //WebElement statedrpdwn = findObject(Driver, By.xpath(".//a[@class = 'selectBox FormFieldRequired selectBox-dropdown']"), "State dropdown");
              if(wElement !=null)
              {
//                   GeneralComponents.clickDropDownByList(statedrpdwn,strState);
                     Actions action = new Actions(Driver);
                     action.moveToElement(wElement).clickAndHold(wElement).build().perform();
//                   GeneralComponents.clickOnWebelement(statedrpdwn, "State dropdown");
                     WebElement ulwle = findObject(Driver, By.xpath(".//ul[contains(@class,'selectBox-dropdown-menu')]"), " dropdown");
                     List<WebElement> list = ulwle.findElements(By.tagName("li"));
                     for(WebElement li: list)
                     {
                            String text = li.getText();
                            if(text.equalsIgnoreCase(value))
                            {
                                   WebElement hyperlink = li.findElement(By.tagName("a"));
//                          highlightElement(Driver, hyperlink);                          
                                   action.moveToElement(hyperlink).click(hyperlink).build().perform();
//                                 GeneralComponents.clickOnWebelement(li, "State");
                                   break;
                            }
                     }
              }
        }


			  public void selectElementByValue(WebElement wElement, String value, String fieldName){	  
				  List<WebElement> options = wElement.findElements(By.tagName("option"));
				  String txtProp = null;
				  boolean txtFound = false;
				  try {
					for (WebElement option : options) {
						txtProp = option.getAttribute("value").trim();
						System.out.println("options size:"+options.size());
						System.out.println("option:"+txtProp);
					    if(value.toUpperCase().equals(txtProp.toUpperCase())){
					      option.click();
					      Report.updateTestLog("Select "+fieldName, fieldName+" ["+value+"] has been selected", Status.DONE);
					      sleepTime(Driver, 1);
					      txtFound = true;
					      break;
					    }
					 }
				} catch (Exception e) {
					Report.updateTestLog("Select "+fieldName, "Exception occured while trying to select "+fieldName+" ["+value+"]. Exception is ["+e+"]", Status.WARNING);
				}
				if(!txtFound)
					Report.updateTestLog("Select "+fieldName, "Option - ["+value+"] does not exist in "+fieldName+" list", Status.FAIL);
				
			  }
			  
			  public void selectElementByText(WebElement wElement, String value, String fieldName){	  
				  List<WebElement> options = wElement.findElements(By.tagName("option"));
				  String txtProp = null;
				  boolean txtFound = false;
				  try {
					  System.out.println("options size:"+options.size());
					for (WebElement option : options) {
						txtProp = option.getText();
						
					    if((value.toUpperCase().trim()).equals(txtProp.toUpperCase().trim())){
					      option.click();
					      System.out.println("option:"+txtProp);
					      Report.updateTestLog("Select function","["+value+"] has been selected", Status.DONE);
					      sleepTime(Driver, 1);
					      txtFound = true;
					      break;
					    }
					 }
				} catch (Exception e) {
					Report.updateTestLog("Select "+fieldName, "Exception occured while trying to select "+fieldName+" ["+value+"]. Exception is ["+e+"]", Status.WARNING);
				}
				if(!txtFound)
					Report.updateTestLog("Select "+fieldName, "Option - ["+value+"] does not exist in "+fieldName+" list", Status.FAIL);
				
			  }		
			  public void selectElementfromli(WebElement wElement, String value, String fieldName){	  
				    List<WebElement> options = wElement.findElements(By.tagName("a"));
				  String txtProp = null;
				  boolean txtFound = false;
				  try {
					  Actions actionnew = new Actions(Driver);
					  System.out.println("options size:"+options.size());
					for (WebElement option : options) {
						txtProp = option.getText();
						
					    if((value.toUpperCase().trim()).equals(txtProp.toUpperCase().trim())){
					    	System.out.println("Option:"+txtProp);
					      //option.click();
					    	actionnew.moveToElement(option).click(option).build().perform();
			                Report.updateTestLog("Select function: ", "["+value+"] has been selected", Status.PASS);
					      sleepTime(Driver, 1);
					      txtFound = true;
					      break;
					    }
					 }
				} catch (Exception e) {
					Report.updateTestLog("Select "+fieldName, "Exception occured while trying to select "+fieldName+" ["+value+"]. Exception is ["+e+"]", Status.WARNING);
				}
				if(!txtFound)
					Report.updateTestLog("Select "+fieldName, "Option - ["+value+"] does not exist in "+fieldName+" list", Status.FAIL);
				
			  }
			  public void selectElementfromList(WebElement wElement, String value, String fieldName){	  
				  List<WebElement> options = wElement.findElements(By.tagName("li"));
				  String txtProp = null;
				  boolean txtFound = false;
				  try {
					for (WebElement option : options) {
						
							WebElement optiontxt = option.findElement(By.tagName("a"));
							//txtProp = optiontxt.getText();
							txtProp = optiontxt.getAttribute("innerText");
						
						
						System.out.println("options size:"+options.size());
						System.out.println("option:"+txtProp);
					    if(value.toUpperCase().trim().equals(txtProp.toUpperCase().trim())){
					      option.click();
					      Actions action=new Actions(Driver);
						  	action.moveToElement(wElement).clickAndHold(wElement).build().perform();
					      sleepTime(Driver, 1);
						
					      Report.updateTestLog("Select option"," ["+value+"] has been selected", Status.PASS);
					      sleepTime(Driver, 1);
					      txtFound = true;
					      break;
					    }}
					 
				} catch (Exception e) {
					Report.updateTestLog("Select "+fieldName, "Exception occured while trying to select "+fieldName+" ["+value+"]. Exception is ["+e+"]", Status.WARNING);
				}
				if(!txtFound)
					Report.updateTestLog("Select "+fieldName, "Option - ["+value+"] does not exist in "+fieldName+" list", Status.FAIL);
				
			  }
			  public void clickOnWebelementUsingActions(WebElement wElement,String fieldName){
					 try {		
						
						Actions action=new Actions(Driver);
						  	action.moveToElement(wElement).click(wElement).build().perform();
						  	sleepTime(Driver, 1);
						 Report.updateTestLog("Click on [ "+fieldName+"]", "["+fieldName+"] has been clicked", Status.PASS);	
						 sleepTime(Driver, 1);
					} catch (Exception e) {
						Report.updateTestLog("Click on "+fieldName,"Exception occured while trying to click on "+fieldName+". Exception is ["+e+"]",Status.FAIL);
					}
				  }

			  public void highlight(WebElement wElement)
			  {
				  try {		
						
						/* ((JavascriptExecutor) Driver).executeScript("arguments[0].scrollIntoView();", wElement);
						 ((JavascriptExecutor) Driver).executeScript("arguments[0].style.border='1px solid blue'", wElement);*/
					  
					   JavascriptExecutor js = (JavascriptExecutor) Driver;
				        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", wElement, " outline:" + "#f00" + " solid 2px;");
				  }catch(Exception e){}
			  }
			  
			  private void highlightElement(WebElement element, String color) {
			        JavascriptExecutor js = (JavascriptExecutor) Driver;
			        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, " outline:" + color + " solid 2px;");
			    }

			    public void highlightElement(WebElement element) {
			        highlightElement(element, "#f00");
			    }  
			  
			  public void findAndClicklinkByText(WebDriver Driver,String txt){
				  WebElement wElement = null; int timeOut = 0;
				  do{
					  if(timeOut > 0){
						  sleepTime(Driver, 6);
					  }
					  try{
							wElement = Driver.findElement(By.partialLinkText(txt));
					  }catch (NoSuchElementException e) {timeOut++;}		  
				  }while(wElement == null && timeOut < 10);		
				  try{
						wElement = Driver.findElement(By.partialLinkText(txt));
						if(wElement != null){
							  try{
									wElement = Driver.findElement(By.partialLinkText(txt));
									wElement.click();
									try{
										wElement.click();
									}catch(Exception e){}
									txt = txt.replace("'", "");
									Report.updateTestLog("Click on link "+txt, txt+" link has been clicked", Status.DONE);
							  }catch (NoSuchElementException e) {
								  Report.updateTestLog("Click on "+txt+" link", "Exception occured while trying to Click on "+txt+" link. Exception ["+e+"]",Status.FAIL);
							  }
						  }
				  }catch (NoSuchElementException e) {
//					  Report.updateTestLog("Find "+txt+" link", "Exception occured while trying to find the "+txt+" link. Exception ["+e+"]",Status.FAIL);
				  }
				  if(wElement == null){
					  txt = txt.replace("'", "");
					  Report.updateTestLog("Find "+txt+" link", "Link "+txt+" not found",Status.FAIL);
				  }
			  }   
			  
//				 Find Webelement by linkText
				  
				public boolean isTextPresent(WebDriver Driver,String textPattern)
				{		
					String txt = null;
					try {
						txt = Driver.findElement(By.cssSelector("BODY")).getText();
						System.out.println(txt);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						txt = "NULL";
						e.printStackTrace();
					}
					if(txt.contains(textPattern)) {
						return true;
					} else {
						return false;
					}
				}
				
				public boolean isTextPresent(WebDriver Driver,WebElement wEle, String textPattern)
				{		
					String txt = null;
					try {
						txt = wEle.findElement(By.cssSelector("BODY")).getText();
//						System.out.println(txt);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						txt = "NULL";
						e.printStackTrace();
					}
					if(txt.contains(textPattern)) {
						return true;
					} else {
						return false;
					}
				}	
				
				public int getRowOrColCntOfTable(WebElement wEle,By by, String tableName){
					int rowCnt = 0;
					try {
						rowCnt = wEle.findElements(by).size();
					} catch (Exception e) {
						//throw new FrameworkException("Get Row//Columncount of table "+tableName, "Exception occured while trying to get Row//Columncount of table "+tableName+". Exception ["+e+"]");
					}
					if(rowCnt == 0){
						Report.updateTestLog("Get Row//Columncount of table ", "Rowcount is Zero", Status.FAIL);
					}
					return rowCnt;
				}
				
				public String getInnerTextOfCell(WebDriver Driver,String xpathExpr, int rowIndex, int colIndex){
					String innerTxt = Driver.findElement(By.xpath(xpathExpr+"/tr['$rowIndex']/td['$colIndex']")).getText();
					return innerTxt;
				}
				
				public String getInnerTextOfCell(WebElement wEle, int rowIndex, int colIndex){
					String innerTxt = wEle.findElement(By.xpath("/tr['$rowIndex']/td['$colIndex']")).getText();
//					String innerTxt = wEle.findElement(By.xpath("./tr["+rowIndex+"]/td["+colIndex+"]")).getText();
					return innerTxt;
				}
						
				public void waitForPageLoaded(WebDriver Driver) {
				     ExpectedCondition<Boolean> expectation = new
				    	ExpectedCondition<Boolean>() {
				        public Boolean apply(WebDriver Driver) {
				          return ((JavascriptExecutor)Driver).executeScript("return document.readyState").equals("complete");
				        }
				      };

				     Wait<WebDriver> wait = new WebDriverWait(Driver,2000);
				      try {
				              wait.until(expectation);
				      } catch(Throwable error) {
				             Report.updateTestLog("Timeout waiting for Page Load Request to complete.","Timeout waiting for Page Load Request to complete.",Status.FAIL);
				      }
				 } 
				
				public WebElement findObjectIfExists(WebDriver Driver,By by,String elementName){
					  WebElement wElement = null; int timeOut = 0;
					  do{
						  if(timeOut > 0){
							  sleepTime(Driver, 3);
						  }
						  try{
								wElement = Driver.findElement(by);
						  }catch (NoSuchElementException e) {timeOut++;}
					  }while(wElement == null && timeOut < 3);
					  try{
							wElement = Driver.findElement(by);
					  }catch (NoSuchElementException e) {
						//  Report.updateTestLog("Find "+elementName, "Exception occured while trying to find the "+elementName+". Exception ["+e+"]",Status.DONE);
					  }	  
					 /* if(wElement == null){
						  throw new FrameworkException("Find "+elementName, elementName+" not found");
					  }*/
					return wElement;
				  }
				
							public void getAllTbls(WebDriver Driver){
					List<WebElement> tbtEle = findObjectList(Driver,By.tagName("Table"), "Table");
					System.out.println("Table Ln:"+tbtEle.size());
					int t = 0;
//					for(WebElement tbl : tbtEle){
					for(int i = 0; i < tbtEle.size(); i++){
						System.out.println("Table "+i+"Txt is------>"+tbtEle.get(i).getText());
						System.out.println("**********************************************");
						/*List<WebElement> trEle = tbtEle.get(i).findElements(By.tagName("tr"));
						int r = 0;
						for(WebElement tr : trEle){
							try {
								System.out.println("TR : "+r+"------>"+tr.getText());
							} catch (Exception e) {}
							r++;
						}
						t++;*/
					}
				}
				
				public WebElement findNextLinkFromParent(WebElement element,By by)
			    {
			        int timedOut = 4, cntr  =0;WebElement wEle = null;
			        do {
			              if(cntr > 0){
			            	  sleepTime(Driver, 6);
			              }
			              try {
			                    wEle = element.findElement(by);
			              } catch (Exception e) {
			                    cntr++;
			              }
			        } while (wEle == null && cntr <= timedOut);
			        if(wEle != null)
			              return wEle;
			        else
			              return null;
			    }
				
				public List<WebElement> findObjects(WebDriver Driver,By by,String elementName){
			        List<WebElement> wElement = null; int timeOut = 0;
				    do{
				          if(timeOut > 0){
				        	  sleepTime(Driver, 6);
				          }
				          timeOut++;
				          try{
				              wElement = Driver.findElements(by);
				          }catch (NoSuchElementException e) {
				              // throw new FrameworkException("Find "+elementName, "Exception occured while trying to find the "+elementName+". Exception ["+e+"]");
				          }
				    }while(wElement.size()==0 && timeOut < 10);
				    if(wElement.size()==0){
				          
				    }
				  return wElement;
				}

				public void verifyExpectedActual(String expected,String Actual,String FieldName)
				{
					if(expected.equalsIgnoreCase(Actual))
					{
						Report.updateTestLog("verify "+FieldName,FieldName+" ["+expected+"] is  displaying as expected in UI", Status.PASS);	
					}else
					{
						Report.updateTestLog("verify "+FieldName, FieldName+" does not available in UI. Expected  value is ["+expected+"] but actual value is  ["+Actual+"]", Status.FAIL);
					}
					
				}
				
				public void verifyExpectedActualContains(String expected,String Actual,String FieldName)
				{
					if(Actual.contains(expected))
					{
						Report.updateTestLog("verify "+FieldName,FieldName+" ["+Actual+"] is  displaying as expected in UI", Status.PASS);	
					}else
					{
						Report.updateTestLog("verify "+FieldName, FieldName+" does not available in UI. Expected  value is ["+expected+"] but actual value is  ["+Actual+"]", Status.FAIL);
					}
					
				}
				  
				  
			  public void findAndClicklinkByText(WebElement ele,String txt){
				  WebElement wElement = null; int timeOut = 0;
				  do{
					  if(timeOut > 0){
						  sleepTime(Driver, 6);
					  }
					  try{
							wElement = ele.findElement(By.linkText(txt));
					  }catch (NoSuchElementException e) {timeOut++;}		  
				  }while(wElement == null && timeOut < 10);		
				  try{
						wElement = ele.findElement(By.linkText(txt));
						if(wElement != null){
							  try{
									wElement = ele.findElement(By.linkText(txt));
									wElement.click();						
							  }catch (NoSuchElementException e) {
								  //throw new FrameworkException("Click on "+txt+" link", "Exception occured while trying to Click on "+txt+" link. Exception ["+e+"]");
							  }
						  }
				  }catch (NoSuchElementException e) {
					  //throw new FrameworkException("Find "+txt+" link", "Exception occured while trying to find the "+txt+" link. Exception ["+e+"]");
				  }
			  }
				  
			  public String formatDateUIParam(String strDate,String INPUTFORMAT,String OUTPUTFORMAT) {
					SimpleDateFormat inFmt;
					SimpleDateFormat outFmt;
					Date date = new Date();
					outFmt = new SimpleDateFormat(OUTPUTFORMAT);
					try {
						inFmt = new SimpleDateFormat(INPUTFORMAT);
						date = inFmt.parse(strDate);
					} catch (Exception e) {
						Report.updateTestLog("Exception", "Exception while formating date", Status.FAIL);
					}
					return outFmt.format(date);
				}
				  
			  public List<WebElement> findObjectsFromWebElement(WebElement wEleParent, By by,String elementName){
				  List<WebElement> wElement = null; int timeOut = 0;
				  do{
					  if(timeOut > 0){
						  sleepTime(Driver, 6);
					  }
					  try{
							wElement = wEleParent.findElements(by);
					  }catch (NoSuchElementException e) {timeOut++;}		  
				  }while(wElement == null && timeOut < 10);
				  try{
						wElement = wEleParent.findElements(by);
				  }catch (NoSuchElementException e) {
					  Report.updateTestLog("Find "+elementName, "Exception occured while trying to find the "+elementName+". Exception ["+e+"]",Status.FAIL);
				  }
				  if(wElement == null){
					  //throw new FrameworkException("Find "+elementName, elementName+" not found");
				  }
				return wElement;
			  }
				  
			  public boolean isTextPresentBold(WebDriver Driver,String textPattern)
				{	
					System.out.println("bold text is"+Driver.findElement(By.cssSelector("b")).getText());
					if(Driver.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(textPattern)) {
						return true;
					} else {
						return false;
					}
				}
				  	 	  	
			 public void dismissAlertDialog(WebDriver Driver) {	       
			        Alert alert = Driver.switchTo().alert();	       
			        alert.dismiss();
			        Report.updateTestLog("Close Alert Box","Alert Box has been dismissed " , Status.DONE);	 
			   }
			  
			 /* public void acceptAlert(WebDriver Driver){
				  Toyo_Supportlibrary Toyo_Supportlibrary = new Toyo_Supportlibrary(scriptHelper);
				  sleepTime(Driver, 10);		  
				  try {
						alertMsg =  Driver.switchTo().alert().getText();	
						Toyo_Supportlibrary.saveTestDataToFile ("STRVARALERTMSG"+cntr,alertMsg);
						System.out.println("Alert Msg:"+alertMsg);
					Driver.switchTo().alert().accept();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  cntr++;
			  }*/

			   public void doubleClickWebElement(WebElement ele,String fieldName)
				  {
					 
					  try {
						  Actions action=new Actions(Driver);
						  action.doubleClick(ele).perform();
						 // action.keyDown(element, theKey)
						  Report.updateTestLog("Click on "+fieldName, fieldName+" has been double clicked", Status.PASS);	
					} catch (Exception e) {
						//throw new FrameworkException("Click on "+fieldName,"Exception occured while trying to double click on "+fieldName+". Exception is ["+e+"]");
					} 
				  }
				 public boolean isTextPresentPageSource(String textPattern)
				{		
					
					System.out.println("source is"+Driver.getPageSource());
					if(Driver.getPageSource().contains(textPattern)) {
						return true;
					} else {
						return false;
					}
				} 
				  
				public void iterateTable(WebElement tableElement){
					int r = 0, c = 0;
					List<WebElement> trEles = tableElement.findElements(By.tagName("tr"));
					System.out.println("Table Row Cnt:"+trEles.size());
					for(WebElement eachTR : trEles){
						try {
							String colTxt = eachTR.getText();
							System.out.println("Row txt "+r+" is :"+colTxt);
							List<WebElement> colEles = eachTR.findElements(By.tagName("td"));
							System.out.println("Col Cnt of Row "+r+" is :"+colEles.size());
							c = 0;
							for(WebElement eachCol : colEles){					
//								System.out.println("Col Txt :"+c+"--------->"+colTxt);
								try {
									System.out.println("Row "+r+" Col "+c+" value is :"+eachCol.getText());
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								c++;
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("*****************************************************************");
						r++;
					}
				}
				
				public WebElement getExpTRElement(WebElement tableElement, int rowIndex){
					int r = 0;
					WebElement eachTR = null;
					List<WebElement> trEles = tableElement.findElements(By.tagName("tr"));
					System.out.println("Table Row Cnt:"+trEles.size());
					try {
						eachTR = trEles.get(rowIndex);
					} catch (Exception e) {}	
					System.out.println("*****************************************************************");
					return eachTR;
				}
					
				
				public List<WebElement> getExpTRElement(WebElement tableElement, String rowText){
					int r = 0;
					String txtProp = null;
					List<WebElement> trEles = tableElement.findElements(By.tagName("tr"));
					List<WebElement> expTREle = tableElement.findElements(By.tagName("tr"));
					expTREle.clear();
					System.out.println("Table Row Cnt:"+trEles.size());
					for(WebElement eachTR : trEles){
						try {
							txtProp = eachTR.getText();
							System.out.println("TXT:"+txtProp);
							if(txtProp.contains(rowText)){
								expTREle.add(r, eachTR);
								r++;
							}
						}catch(Exception e){}
					}
					System.out.println("*****************************************************************");
					return expTREle;
				}
				
				public List<WebElement> getExpTRElement(WebDriver Driver, String rowText){
					int r = 0;
					String txtProp = null;
					List<WebElement> trEles = Driver.findElements(By.tagName("tr"));
					List<WebElement> expTREle = Driver.findElements(By.tagName("tr"));
					expTREle.clear();
					System.out.println("Table Row Cnt:"+trEles.size());
					for(WebElement eachTR : trEles){
						try {
							txtProp = eachTR.getText();
							System.out.println("TXT:"+txtProp);
							if(txtProp.contains(rowText)){
								expTREle.add(r, eachTR);
								r++;
							}
						}catch(Exception e){}
					}
					System.out.println("*****************************************************************");
					return expTREle;
				}
					
				public String getExpTDText(WebElement tableElement, int rowIndex, int colIndex){
					String txtProp = null;
					WebElement eachTR = null;
					List<WebElement> trEles = tableElement.findElements(By.tagName("tr"));
//					System.out.println("Table Row Cnt:"+trEles.size());
					try {
						eachTR = trEles.get(rowIndex);
						try {
							List<WebElement> colEles = eachTR.findElements(By.tagName("td"));
//							System.out.println("Col Cnt of Row "+r+" is :"+colEles.size());
							try {
								txtProp = colEles.get(colIndex).getText();
//								System.out.println("Row "+rowIndex+" Col "+colIndex+" value is :"+txtProp);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} catch (Exception e) {}
					} catch (Exception e) {}
					System.out.println("*****************************************************************");
					return txtProp;
				}
				
				public String getExpTDText(WebElement tableElement, int colIndex){
					String txtProp = null;
					try {
						List<WebElement> colEles = tableElement.findElements(By.tagName("td"));
//						System.out.println("Col Cnt of Row "+r+" is :"+colEles.size());
						try {
							txtProp = colEles.get(colIndex).getText();
//							System.out.println("Row "+rowIndex+" Col "+colIndex+" value is :"+txtProp);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (Exception e) {}
					System.out.println("*****************************************************************");
					return txtProp;
				}
				
				public WebElement findCellWebElement(WebElement tableElement, int rowIndex, int colIndex, By by){
//					WebElement  = null;
					WebElement eachTR = null, eachCol = null, wEle = null;
					List<WebElement> trEles = tableElement.findElements(By.tagName("tr"));
//					System.out.println("Table Row Cnt:"+trEles.size());
					try {
						eachTR = trEles.get(rowIndex);
						try {
							List<WebElement> colEles = eachTR.findElements(By.tagName("td"));
//							System.out.println("Col Cnt of Row "+r+" is :"+colEles.size());
							try {
								eachCol = colEles.get(colIndex);
								wEle = eachCol.findElement(by);
								return wEle;
//								System.out.println("Row "+rowIndex+" Col "+colIndex+" value is :"+txtProp);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} catch (Exception e) {}
					} catch (Exception e) {}
					System.out.println("*****************************************************************");
					return wEle;
				}
				
						
				public boolean verifySelectedListValue(WebElement webEle, String expValue){ 
				      String listUI=null;
			          List<WebElement> optionList= webEle.findElements(By.tagName("option"));
			          for(WebElement valueUI :optionList)
			          {
			                 if(valueUI.getAttribute("value").equals(webEle.getAttribute("value"))) 
			                 {
			                	 listUI = valueUI.getText().toString();
			                     System.out.println("List Value UI :" + listUI);  
			                     break;
			                  }
			          }
			           if(listUI.trim().contains(expValue.trim()))
			        	   return true;
			           else
			            return false;	           
				  }

				public List<WebElement> getExpectedTable(WebDriver Driver, String txtStartswith){
					List<WebElement> tbtEle = findObjectList(Driver,By.tagName("Table"), "Table");
					List<WebElement> expTbtEle = findObjectList(Driver,By.tagName("Table"), "Table");
//					System.out.println("Table Ln:"+tbtEle.size());
					int t = 0; String txtProp = null;
					expTbtEle.clear();
					for(int i = 0; i < tbtEle.size(); i++){
						txtProp = tbtEle.get(i).getText();
//						System.out.println("Table "+i+"Txt is------>"+txtProp);
						if(txtProp.startsWith(txtStartswith)){
							expTbtEle.add(tbtEle.get(i));
							break;
						}
						
					}
					return expTbtEle;
				}
				
				public List<WebElement> getExpectedTableWithTxt(WebDriver Driver, String txtContains){
					List<WebElement> tbtEle = findObjectList(Driver,By.tagName("Table"), "Table");
					List<WebElement> expTbtEle = findObjectList(Driver,By.tagName("Table"), "Table");
//					System.out.println("Table Ln:"+tbtEle.size());
					int t = 0; String txtProp = null;
					expTbtEle.clear();
					for(int i = 0; i < tbtEle.size(); i++){
						txtProp = tbtEle.get(i).getText();
//						System.out.println("Table "+i+"Txt is------>"+txtProp);
						if(txtProp.contains(txtContains)){
							expTbtEle.add(tbtEle.get(i));
							break;
						}
						
					}
					return expTbtEle;
				}
				
				public List<WebElement> getExpectedTableWithTxt(WebElement wEle, String txtContains){
					List<WebElement> tbtEle = findObjectList(wEle,By.tagName("Table"), "Table");
					List<WebElement> expTbtEle = findObjectList(wEle,By.tagName("Table"), "Table");
//					System.out.println("Table Ln:"+tbtEle.size());
					int t = 0; String txtProp = null;
					expTbtEle.clear();
					for(int i = 0; i < tbtEle.size(); i++){
						txtProp = tbtEle.get(i).getText();
//						System.out.println("Table "+i+"Txt is------>"+txtProp);
						if(txtProp.contains(txtContains)){
							expTbtEle.add(tbtEle.get(i));
							break;
						}
						
					}
					return expTbtEle;
				}

				public void getAllTbls(WebElement wEle){
					List<WebElement> tbtEle = findObjectList(wEle,By.tagName("Table"), "Table");
					System.out.println("Table Ln:"+tbtEle.size());
					int t = 0;
//					for(WebElement tbl : tbtEle){
					for(int i = 0; i < tbtEle.size(); i++){
						System.out.println("Table "+i+"Txt is------>"+tbtEle.get(i).getText());
						/*List<WebElement> trEle = tbtEle.get(i).findElements(By.tagName("tr"));
						int r = 0;
						for(WebElement tr : trEle){
							try {
								System.out.println("TR : "+r+"------>"+tr.getText());
							} catch (Exception e) {}
							r++;
						}
						t++;*/
					}
				}
				
				public List<WebElement> getExpectedTable(WebElement wEle, String txtStartswith){
					List<WebElement> tbtEle = findObjectList(wEle,By.tagName("Table"), "Table");
					List<WebElement> expTbtEle = findObjectList(wEle,By.tagName("Table"), "Table");
//					System.out.println("Table Ln:"+tbtEle.size());
					int t = 0; String txtProp = null;
					expTbtEle.clear();
					for(int i = 0; i < tbtEle.size(); i++){
						txtProp = tbtEle.get(i).getText();
//						System.out.println("Table "+i+"Txt is------>"+txtProp);
						if(txtProp.startsWith(txtStartswith)){
							expTbtEle.add(tbtEle.get(i));
						}
						
					}
					return expTbtEle;
				}
				
				/*public void selectElement_NottheExpValue(WebElement wElement, String value, String fieldName){
					Toyo_Supportlibrary Toyo_Supportlibrary = new Toyo_Supportlibrary(ScriptHelper);
					String txt = null;
					  List<WebElement> options = wElement.findElements(By.tagName("option"));
					  try {
						for (WebElement option : options) {
							txt = option.getText();
						    if(!value.toUpperCase().equals(option.getText().toUpperCase())){
						      option.click();
						      Report.updateTestLog("Select "+fieldName, fieldName+" ["+txt+"] has been selected", Status.SCREENSHOT);
						      sleepTime(Driver, 2);
						      Toyo_Supportlibrary.saveTestDataToFile("STRVARSELECTEDVALUE", txt);
						      break;
						    }
						 }
					} catch (Exception e) {
						throw new FrameworkException("Select "+fieldName,"Exception occured while trying to select "+fieldName+" other than ["+value+"]. Exception is ["+e+"]");
					}
					sleepTime(Driver, 5);
				  }*/
				  
				   public String readyState() {
			          return (String) ((JavascriptExecutor)Driver).executeScript("return document.readyState");
			        }
				  public void clickOnWebelementDone(WebElement wElement,String fieldName){
						 try {
							  wElement.click();
							  Report.updateTestLog("Click on "+fieldName, fieldName+" has been clicked", Status.DONE);	
							  try {
								Thread.sleep(1000);
							} catch (Exception e) {}
						} catch (Exception e) {
							//throw new FrameworkException("Click on "+fieldName,"Exception occured while trying to click on "+fieldName+". Exception is ["+e+"]");
						}
					  }
				  
				  

				  public List<WebElement> findObjectsIfExists(WebDriver Driver,By by,String elementName){
					  List<WebElement> wElement = null; int timeOut = 0;
					  do{
						  if(timeOut > 0){
							try {
								Thread.sleep(3000);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						  }
						  try{
								wElement = Driver.findElements(by);
						  }catch (NoSuchElementException e) {timeOut++;}
					  }while(wElement == null && timeOut < 6);
					  try{
							wElement = Driver.findElements(by);
					  }catch (NoSuchElementException e) {
						//  Report.updateTestLog("Find "+elementName, "Exception occured while trying to find the "+elementName+". Exception ["+e+"]",Status.DONE);
					  }	  
					 /* if(wElement == null){
						  throw new FrameworkException("Find "+elementName, elementName+" not found");
					  }*/
					return wElement;
				  } 
				  
				  
				  public WebElement findObject1(WebDriver Driver,By by,String elementName){
					  WebElement wElement = null; int timeOut = 0;
					  do{
						  if(timeOut > 0){
							sleepTime(Driver, 6);
						  }
						  try{
								wElement = Driver.findElement(by);
						  }catch (NoSuchElementException e) {timeOut++;}
					  }while(wElement == null && timeOut < 10);
					  try{
							wElement = Driver.findElement(by);
					  }catch (NoSuchElementException e) {
//						  Report.updateTestLog("Find "+elementName, "Exception occured while trying to find the "+elementName+". Exception ["+e+"]",Status.FAIL);
					  }		  
					return wElement;
				  }
				//----------------------POORNA----------------------
				  public List<WebElement> findObjectsIfExistsFromWebElement(WebElement ele,WebDriver Driver,By by,String elementName){
					  List<WebElement> wElement = null; int timeOut = 0;
					  do{
						  if(timeOut > 0){
							try {
								Thread.sleep(3000);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						  }
						  try{
								wElement = ele.findElements(by);
						  }catch (NoSuchElementException e) {timeOut++;}
					  }while(wElement == null && timeOut < 6);
					  try{
							wElement = ele.findElements(by);
					  }catch (NoSuchElementException e) {
						//  Report.updateTestLog("Find "+elementName, "Exception occured while trying to find the "+elementName+". Exception ["+e+"]",Status.DONE);
					  }	  
					 /* if(wElement == null){
						  throw new FrameworkException("Find "+elementName, elementName+" not found");
					  }*/
					return wElement;
				  } 
				  
				  /**
					 * 
						 * MethodName: readAllAttributes
						 * Description: To read all attributes of any webelemnet
						 * Parameter (if any): webelement for which we need to find properties.
						 * Return type: 
						 * Owner : 
					 */
					public  void readAllAttributes(WebElement element,String objectName)
					{
						System.out.println("---------------------------"+objectName+"-----------------------------");
						ArrayList parentAttributes = (ArrayList)  
								((JavascriptExecutor)Driver).executeScript(
				       "var s = []; var attrs = arguments[0].attributes; for (var  i= 0; i<attrs.length;i++) { var a = attrs[i]; s.push(a.name + ':' + a.value); } ; return s;", element);
				     for (Object o : parentAttributes) {
				       System.out.println(o);
				     }
				     
				     System.out.println("---------------------------"+objectName+"-----------------------------");
					}
					
				  public boolean verifyCheckBox(WebElement wEle, String checkedProp, String fieldName){
					  boolean ischecked = false;
					  String checkedPropUI = "";		 
					  
					  try{
						  checkedPropUI = wEle.getAttribute("checked").toString();
					  }catch(Exception e){
						  checkedPropUI = "NULL";
					  }
					  if(checkedProp.equalsIgnoreCase("TRUE")){
						  if(!checkedPropUI.equalsIgnoreCase("NULL") && !checkedPropUI.equalsIgnoreCase(""))
							  ischecked = true;
						  else
							  ischecked = false; 
					  }else if(checkedProp.equalsIgnoreCase("FALSE")){
						  if(checkedPropUI.equalsIgnoreCase("NULL") || checkedPropUI.equalsIgnoreCase(""))
							  ischecked = true;
						  else
							  ischecked = false; 
					  }
					  
					  return ischecked;
					  
				  }
				  
				  public void moveToWebEle(WebElement wEle){
					  try {
							((JavascriptExecutor) Driver).executeScript("arguments[0].scrollIntoView();", wEle);
						} catch (Exception e) {} 
				  }
				  
				  public List<WebElement> getExpTDElement_TxtStartsWith(WebElement tableElement, String rowText){
						int r = 0;
						String txtProp = null;
						List<WebElement> trEles = tableElement.findElements(By.tagName("td"));
						List<WebElement> expTREle = tableElement.findElements(By.tagName("td"));
						expTREle.clear();
						System.out.println("Table Column Cnt:"+trEles.size());
						for(WebElement eachTR : trEles){
							try {
								txtProp = eachTR.getText();
//								System.out.println("TXT:"+txtProp);
								if(txtProp.startsWith(rowText)){
									expTREle.add(r, eachTR);
									r++;
								}
							}catch(Exception e){}
						}
						System.out.println("*****************************************************************");
						return expTREle;
					}
				  
				  
				  //---------------------Aror2----------------------
					public  WebElement findlinkByText(WebDriver Driver,String txt) throws Exception{
						  WebElement wElement = null; int timeOut = 0;
						  do{
							  if(timeOut > 0){
								  sleepTime(Driver, 6);
							  }
							  try{
									wElement = Driver.findElement(By.linkText(txt));
							  }catch (NoSuchElementException e) {timeOut++;}
						  }while(wElement == null && timeOut < 10);	
						  try{
								wElement = Driver.findElement(By.linkText(txt));
						  }catch (NoSuchElementException e) {
							  //throw new FrameworkException("Find "+txt+" link", "Exception occured while trying to find the "+txt+" link. Exception ["+e+"]");
						  }
						return wElement;
					  }
					
					public  WebElement findHeaderSection(WebDriver Driver,String txt){
						String xpath=null;
						  WebElement wElement = null; int timeOut = 0;
						  do{
							  if(timeOut > 0){
								  sleepTime(Driver, 6);
							  }
							  try{
								  xpath ="//div[@class='af_menuBar_content']//a[contains(text(),'"+txt+"')]";
									wElement = Driver.findElement(By.xpath(xpath));
							  }catch (NoSuchElementException e) {timeOut++;}
						  }while(wElement == null && timeOut < 10);	
						  try{
								wElement = Driver.findElement(By.xpath(xpath));
						  }catch (NoSuchElementException e) {
//							  throw new FrameworkException("Find "+txt+" link", "Exception occured while trying to find the "+txt+" link. Exception ["+e+"]");
						  }
						return wElement;
					  }
					
					
					
					
					 public void clickOnInvisibleWebelement(WebElement wElement,String fieldName){
						 try {		
							 
							 JavascriptExecutor js = (JavascriptExecutor)Driver; 
							 js.executeScript("arguments[0].click();", wElement);  
							  //wElement.click();	
							  sleepTime(Driver, 1);
							 Report.updateTestLog("Click on "+fieldName, fieldName+" has been clicked", Status.PASS);	
							 sleepTime(Driver, 1);
						} catch (Exception e) {
							Report.updateTestLog("Click on "+fieldName,"Exception occured while trying to click on "+fieldName+". Exception is ["+e+"]",Status.FAIL);
						}
					  }
					 
					 public WebElement findBtnObject(WebDriver Driver,String text_value)
						{		
						 WebElement webElemnt=findObject(Driver,By.xpath("//a[text(),'"+text_value+"']"), "Button " +text_value);		
						 return webElemnt;
						}
					 
					 
					 public WebElement findBtnObjectBytext(WebDriver Driver,String text_value)
						{		
						 //WebElement webElemnt=findObject(Driver,By.xpath(".//*[@text='"+text_value+"']"), "Button " +text_value);		
						 WebElement webElemnt=findObject(Driver,By.xpath("//*[contains(text(),'"+text_value+"')]"), "Button " +text_value);	
						//a[contains(text(),'Add New Associate')]
						 return webElemnt;
						}
					
					 
					  
//					 Find Webelement by passing text as Input
					public  WebElement findByText(WebDriver Driver,String txt){
						  WebElement wElement = null; int timeOut = 0;
						  do{
							  if(timeOut > 0){
								  sleepTime(Driver, 6);
							  }
							  try{
									wElement = Driver.findElement(By.xpath(".//*[contains(text(),'"+txt+"')]"));
							  }catch (NoSuchElementException e) {timeOut++;}
						  }while(wElement == null && timeOut < 10);	
						  try{
							  wElement = Driver.findElement(By.xpath(".//*[contains(text(),'"+txt+"')]"));
						  }catch (NoSuchElementException e) {
//							  throw new FrameworkException("Find "+txt+" link", "Exception occured while trying to find the "+txt+" link. Exception ["+e+"]");
						  }
						return wElement;
					  }
					
					/**
					 * 
					* MethodName:  testdataforFile
					* Description: To select the classification  for the offering communication create.
					* Parameter (if any):  
					* Return type:  Void
					* Owner : Badri 
					 */
					
//					public static String testdataforFile(String strKey, String File,String assigntxt){
//					String strActualValue = null;
//						String strTestDataDBFileName = FilePath.getTestDataPathUploadMainFile(File);
//						BufferedReader in = null;
//						boolean keyFound = false;
//						try {
//							in = new BufferedReader(new FileReader(strTestDataDBFileName));
//							String line = null;
//							while((line=in.readLine())!=null){
//								if (line.contains("=")){
//									String strTemp[]=line.split("=");
//									if (strTemp[0].trim().equalsIgnoreCase(strKey)){
//										keyFound = true;
//										strActualValue = strTemp[1].trim();
//										assigntxt = strActualValue;
//											in.close();
//											break;
//									}
//								}
//								
//							}
//							
//							
//							if(!keyFound)
//								System.out.println("Key ["+strKey+"] does not exist under the path :"+strTestDataDBFileName);
////								configData.reportObj.updateTestLog("Get data from temp file", "Key ["+strKey+"] does not exist under the path :"+getTestDataDBPath(), Status.FAIL);
//						}
//						catch (Exception e) {
//								e.printStackTrace();
////								configData.reportObj.updateTestLog("I/O failure - error reading file. ","I/O failure - error reading file. "+ e.getMessage(),Status.FAIL);
//						}
//						return assigntxt;
//					}
									
				
					public void appendToTextFile(String strText,String strFilePath)
				    {
						try
				        {
							String fileName = strFilePath;
				            BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
				                       
				            out.write(strText);
				            out.write("\r\n");
				            out.close();
				        }catch (IOException e){
				        } 
				    }
//					public void addToTextFile(String key, String value,String strFilePath) 
//				    {
//							String strActualValue = null;
//							String strTestDataDBFileName = FilePath.getTestDataPathUploadMainFile(strFilePath);
//							BufferedReader in = null;
//							
//							boolean keyFound = false;
//							try {
//								in = new BufferedReader(new FileReader(strTestDataDBFileName));
//								String line = null;
//								while((line=in.readLine())!=null){
//									if (line.contains("=")){
//										String strTemp[]=line.split("=");
//										if (strTemp[0].trim().equalsIgnoreCase(key)){
//											keyFound = true;
//											strActualValue = strTemp[1].trim();
//											System.out.println(strActualValue+ " ID is already available in the path " +strFilePath);
//											strActualValue = strActualValue.replace(strActualValue, value);
//										
//											//											WebElement html= Driver.findElement(By.tagName("html"));
////											html.sendKeys(Keys.chord(Keys.CONTROL,Keys.S));
//											in.close();
//											break;
//										}
//									}}
//							 
//				        }catch (IOException e){
//				        } 
//				    
//				    }

					//Function to put Test Data to TestResultsText file
//					public void putTestDataToFile(String key, String value, String File)
//					{
//						String strActualValue = null;
//						String strTestDataDBFileName = FilePath.getTestDataPathUploadMainFile(File);
//						BufferedReader in = null;
//						boolean keyFound = false;
//						try {
//							in = new BufferedReader(new FileReader(strTestDataDBFileName));
//							String line = null;
//							while((line=in.readLine())!=null){
//								if (line.contains("=")){
//									String strTemp[]=line.split("=");
//									if (strTemp[0].trim().equalsIgnoreCase(key)){
//										keyFound = true;
//										strActualValue = strTemp[1].trim();
//										System.out.println(strActualValue+ " ID is already available in the path " +strTestDataDBFileName);
//										in.close();
//										break;
//									}
//								}}
//							}
//						catch(IOException e){
//						if(!keyFound)
//							System.out.println("Key ["+key+"] does not exist under the path :"+strTestDataDBFileName);
//						String strText = key + " = " + value;
//						String fileName = strTestDataDBFileName;
//						appendToTextFile(strText,fileName);
////							configData.reportObj.updateTestLog("Get data from temp file", "Key ["+strKey+"] does not exist under the path :"+getTestDataDBPath(), Status.FAIL);
//					}
//						//For new addition				
//						if(keyFound)
//						{
//							addToTextFile(key,value,File);
//						}
//					
//						
//						
//					}
					/* public void addVar(String key, String val) {

					        if (runTimeVars.containsKey(key)) {
					            System.err.println("runTimeVars already contains " + key + ".Forcing change to " + val);
					           
					            System.out.println("Already contains " + key);

					        }
					        System.out.println("Adding to runTimeVars " + key + ":" + val);
					       
					        runTimeVars.put(key, val);

					    }*/

				    public void getCellValue() {
				        String strValue = Data;
				        String[] userInput = strValue.split(",");
				        String tableDetails=userInput[0];
				        String variable=userInput[1];
				        
				        String[] splitVal = tableDetails.split(";");
				        int RowNo = Integer.parseInt(splitVal[0]);
				        int ColNo = Integer.parseInt(splitVal[1]);

				        String vCellValue = null;

				        List<WebElement> allRows = Element.findElements(By.tagName("tr"));
				        if (!(allRows.size() == 0)) {
				            List<WebElement> Cells = allRows.get(RowNo).findElements(By.tagName("td"));
				            List<WebElement> cellHdrs = allRows.get(RowNo).findElements(By.tagName("th"));
				            if (!(Cells.size() == 0)) {
				                vCellValue = Cells.get(ColNo).getText();
				                addVar(variable, vCellValue);
				                Report.updateTestLog("GetCellValue-Function", "Table cell value "+ vCellValue +" has been stored into "+variable, Status.PASS);
				            } else if (!(cellHdrs.size() == 0)) {
				                vCellValue = Cells.get(ColNo).getText();
				                Report.updateTestLog("GetCellValue-Function", "Table cell value "+ vCellValue +" has been stored into "+variable, Status.PASS);
				            } else {
				                Report.updateTestLog("GetCellValue - Function", "Table Column size is zero", Status.FAIL);
				                vCellValue = null;
				            }
				        } else {
				            Report.updateTestLog("GetCellValue - Function", "Table Row size is zero", Status.FAIL);
				            vCellValue = null;
				        }
				    }

				    public void getColCount() {
				        String inputData = Data;
				        String[] userInput = inputData.split(",");
				        String vRowNo=userInput[0];
				        String variable=userInput[1];
				        
				        int RowNo = Integer.parseInt(vRowNo);

				        int intColCount = 0;

				        List<WebElement> allRows = Element.findElements(By.tagName("tr"));
				        if (!(allRows.size() == 0)) {
				            List<WebElement> Cells = allRows.get(RowNo).findElements(By.tagName("td"));
				            List<WebElement> cellHdrs = allRows.get(RowNo).findElements(By.tagName("th"));
				            if (!(Cells.size() == 0)) {
				                intColCount = Cells.size();               
				                addVar(variable, String.valueOf(intColCount));
				                Report.updateTestLog("GetColCount-Function", "Table row has '"+intColCount+"' columns, stored in "+variable, Status.PASS);
				            } else if (!(cellHdrs.size() == 0)) {
				                intColCount = cellHdrs.size();
				                addVar(variable, String.valueOf(intColCount));
				                Report.updateTestLog("GetColCount-Function", "Table row has '"+intColCount+"' columns, stored in "+variable, Status.PASS);
				            } else {
				                intColCount = 0;
				                Report.updateTestLog("GetColCount - Function", "Table Column size is zero", Status.FAIL);
				            }
				        } else {
				            Report.updateTestLog("GetColCount - Function", "Table column size is zero", Status.FAIL);
				            intColCount = 0;
				        }

				    }

				    public void getRowCount() {
				        int intRowCount = 0;
				        String variable=Data;
				        
				        List<WebElement> allRows = Element.findElements(By.tagName("tr"));
				        if(!(allRows.size()==0))
				        {
				        	intRowCount = allRows.size();
				        	addVar(variable, String.valueOf(intRowCount));
				        	Report.updateTestLog("getRowCount-Function", "Table has '"+intRowCount+"' rows, stored in variable "+variable, Status.PASS);
				        }
				        else {
				            Report.updateTestLog("getRowCount-Function", "Table Row size is zero", Status.FAIL);
				        }
				    }

				    
				    public void getRowNumber() {
				        String userInput = Data;
				        String[] input = userInput.split(",");
				        String CellValue=input[0];
				        String variable=input[1];
				        
				        int rowCount = 0;
				        int rtnValue = 0;

				        List<WebElement> allRows = Element.findElements(By.tagName("tr"));

				        for (WebElement row : allRows) {
				            List<WebElement> cells = row.findElements(By.tagName("td"));
				            List<WebElement> cellHdrs = row.findElements(By.tagName("th"));
				            if (!(cells.size() == 0)) {
				                for (WebElement col : cells) {
				                    if (col.getText().equals(CellValue.trim())) {
				                        rtnValue = rowCount;
				                        addVar(variable, String.valueOf(rtnValue));
				                    	Report.updateTestLog("getRowCount-Function", "Desired data is in'"+rtnValue+"' row, stored in variable "+variable, Status.PASS);
				                        break;
				                    }
				                }
				            } else if (!(cellHdrs.size() == 0)) {
				                for (WebElement col : cellHdrs) {
				                    if (col.getText().equals(CellValue.trim())) {
				                        rtnValue = rowCount;
				                        addVar(variable, String.valueOf(rtnValue));
				                    	Report.updateTestLog("getRowCount-Function", "Desired data is in'"+rtnValue+"' row, stored in variable "+variable, Status.PASS);
				                        break;
				                    }
				                   
				                }
				            }
				            else
				             	  Report.updateTestLog("getRowCount-Function", "Table doesn't have the desired data ", Status.FAIL);
				            if (!(rtnValue == 0)) {
				                break;
				            } else {
				                rowCount++;
				            }
				        }
				  
				    }

				    public void getColNumber() {
				    	 String userInput = Data;
				         String[] input = userInput.split(",");
				         String CellValue=input[0];
				         String variable=input[1];

				        int colCount = 0;
				        int rtnValue = 0;
				        List<WebElement> allRows = Element.findElements(By.tagName("tr"));

				        for (WebElement row : allRows) {
				            colCount = 0;
				            List<WebElement> cells = row.findElements(By.tagName("td"));
				            List<WebElement> cellHdrs = row.findElements(By.tagName("th"));
				            if (!(cells.size() == 0)) {
				                for (WebElement col : cells) {
				                    if (col.getText().equals(CellValue.trim())) {
				                        rtnValue = colCount;
				                        addVar(variable, String.valueOf(rtnValue));
				                    	Report.updateTestLog("getRowCount-Function", "Desired data is in'"+rtnValue+"' column, stored in variable "+variable, Status.PASS);
				                        break;
				                    }
				                    colCount++;
				                }
				            } else if (!(cellHdrs.size() == 0)) {
				                for (WebElement col : cells) {
				                    if (col.getText().equals(CellValue.trim())) {
				                        rtnValue = colCount;
				                        addVar(variable, String.valueOf(rtnValue));
				                    	Report.updateTestLog("getRowCount-Function", "Desired data is in'"+rtnValue+"' column, stored in variable "+variable, Status.PASS);
				                        break;
				                    }
				                    colCount++;
				                }
				            }
				            else
				             	  Report.updateTestLog("getRowCount-Function", "Table doesn't have the desired data ", Status.FAIL);
				            if (!(rtnValue == 0)) {
				                break;
				            }
				        }
				    
				    
				    
				    
				    }
					
				   

					public void zoomIn(WebDriver Driver)
					{
						//JavaScriptExecutor js= (JavaScriptExecutor)Driver;
						//((JavascriptExecutor) Driver).executeScript("document.body.style.zoom='"+percentage+"'");
						WebElement html= Driver.findElement(By.tagName("html"));
						html.sendKeys(Keys.chord(Keys.CONTROL,Keys.SUBTRACT));
					}
					
					public void zoomOut(WebDriver Driver)
					{
						//JavaScriptExecutor js= (JavaScriptExecutor)Driver;
						//((JavascriptExecutor) Driver).executeScript("document.body.style.zoom='"+percentage+"'");
						WebElement html= Driver.findElement(By.tagName("html"));
						html.sendKeys(Keys.chord(Keys.CONTROL,"0"));
					}
					
					public void findAndEnterUsingSikuli(String filePath, String valueToEnter)
					{
					                Screen s=new Screen();
					                Pattern pat=new Pattern(filePath);
					                try {
										s.wait(pat, 5);
										s.type(pat,valueToEnter);
									} catch (FindFailed e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
					}					
					public void alertUploadBox(WebDriver Driver,String filepath) throws InterruptedException
                    {
						Driver.findElement(By.xpath("//input[@type='file' and @name='tasCaseEditorPortlet{actionForm.attachment1}']")).click();
						Thread.sleep(5000);   
						WebDriverWait wait = new WebDriverWait(Driver, 60);
                           wait.until(ExpectedConditions.alertIsPresent());
                   		
                    try
                    {
                    Alert alert = Driver.switchTo().alert();
                    alert.sendKeys(filepath);
                    alert.accept();
                    System.out.println("File has been selected");
                    Robot r;
                    try {
                    r = new Robot();
                    r.keyPress(KeyEvent.VK_ENTER);
                    r.keyRelease(KeyEvent.VK_ENTER);
                    System.out.println("File name is displayed");
                    } catch (AWTException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    }
                    }
                    catch (UnhandledAlertException u)
                    {
                    u.printStackTrace();
                    }
                    }

					public void waitforElePresence(By by)
					{ 

						(new WebDriverWait(Driver, 40)).until(ExpectedConditions.visibilityOfElementLocated(by));
					}
					
					public WebElement findElementIfExists(WebDriver driver, By by, String fieldName) {
						WebElement wEle = null;
						int cntr = 0;
						do {
							try {
								Thread.sleep(2000);
								// sleepTime(2);
								wEle = driver.findElement(by);
								if (!wEle.isDisplayed()) {
									wEle = null;
								}
							} catch (Exception e) {
								wEle = null;
							}
							cntr++;
						} while (wEle == null && cntr <= 5);
						if (wEle == null) {
							Report.updateTestLog("Find " + fieldName, fieldName + " does not exist", Status.SCREENSHOT);
						}
						return wEle;

					} 
					
					private void impWaitTillPresntAndVisibleNOFailReport(WebDriver driver, By ele2Chk) {
						// TODO Auto-generated method stub

					} 
					
					public void findAndClickElement(WebDriver driver, By ele2Clk, By ele2Chk, String eleName)
				       {
				              WebElement wel=Driver.findElement(ele2Clk);     
				              JavascriptExecutor js = (JavascriptExecutor) Driver;
				              js.executeScript("arguments[0].style.border='3px solid red'", wel);
				              WebDriverWait wait = new WebDriverWait(Driver, 2);
				              wait.until(ExpectedConditions.elementToBeClickable((ele2Clk)));
				              WebElement wEle = null;
				              WebElement wEle2Chk = null;
				              int cnt = 0;
				              do {
				                     wEle = findElementIfExists(driver, ele2Clk, eleName);
				                     Actions action=new Actions(Driver);
				                     action.moveToElement(wEle);
				                     JavascriptExecutor js1 = (JavascriptExecutor) driver;
				                     js1.executeScript("arguments[0].click();", wEle);
				                     try {
				                           impWaitTillPresntAndVisibleNOFailReport(driver,ele2Chk);
				                           wEle2Chk = findElementIfExists(driver, ele2Chk, eleName);
				                     } catch (Exception e) {
				                           wEle2Chk = null;
				                     }

				                     if (wEle2Chk == null) {
				                           wEle.click();
				                     }
				                     cnt++;
				              } while (wEle2Chk == null && cnt <= 5);

				              if (wEle2Chk != null) {
				                     Report.updateTestLog(eleName, eleName+ "  has been clicked", Status.PASS);
				              } else
				                     if(cnt == 5 && wEle2Chk == null )
				                     {
				                           Report.updateTestLog(eleName, "could not be clicked", Status.FAIL);
				                     }

				              sleepTime(Driver, 1);
				       }

						
					}


