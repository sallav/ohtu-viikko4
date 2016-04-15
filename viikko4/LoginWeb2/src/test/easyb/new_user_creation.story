import ohtu.*
import ohtu.authentication.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver

description """A new user account can be created if a proper unused username and a proper password are given"""

scenario "creation succesfull with correct username and password", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver()
        driver.get("http://localhost:8090")
        element = driver.findElement(By.linkText("register new user"))    
        element.click()
    }
 
    when 'a valid username and password are entered', {
        element = driver.findElement(By.name("username"))
        element.sendKeys("Hannamari")
        element = driver.findElement(By.name("password"))
        element.sendKeys("joopajoo30")
        element = driver.findElement(By.name("passwordConfirmation"))
        element.sendKeys("joopajoo30")
        element = driver.findElement(By.linkText("add")
        element.submit()
    }

    then 'new user is registered to system', {
        driver.getPageSource().contains("Welcome to Ohtu App!").shouldBe true
    }
}

scenario "can login with succesfully generated account", {
    given 'command new user is selected', {
        element = driver.findElement(By.linkText("continue to application mainpage"))    
        element.click()
        element = driver.findElement(By.linkText("logout"))    
        element.click()
        element = driver.findElement(By.linkText("login"))    
        element.click()
    }
 
    when 'a valid username and password are entered', {
        element = driver.findElement(By.name("username"))
        element.sendKeys("Hannamari")
        element = driver.findElement(By.name("password"))
        element.sendKeys("joopajoo30")
        element = driver.findElement(By.linkText("login"))      
        element.submit()  
    }

    then  'new credentials allow logging in to system', {
        driver.getPageSource().contains("Welcome to Ohtu App!").shouldBe true
    }
}

scenario "creation fails with correct username and too short password", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver()
        driver.get("http://localhost:8090")
        element = driver.findElement(By.linkText("register new user"))    
        element.click()
    }
    when 'a valid username and too short password are entered', {
        element = driver.findElement(By.name("username"))
        element.sendKeys("Anuliisa")
        element = driver.findElement(By.name("password"))
        element.sendKeys("jee1")
        element = driver.findElement(By.name("passwordConfirmation"))
        element.sendKeys("jee1")
        element = driver.findElement(By.linkText("add")
        element.submit()
    }
    then 'new user is not be registered to system',{
        driver.getPageSource().contains("must contain one character that is not a letter length greater or equal to 8").shouldBe true
    }
}

scenario "creation fails with correct username and pasword consisting of letters", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver()
        driver.get("http://localhost:8090")
        element = driver.findElement(By.linkText("register new user"))    
        element.click()
    }
    when 'a valid username and password consisting of letters are entered', {
        element = driver.findElement(By.name("username"))
        element.sendKeys("Jaakko")
        element = driver.findElement(By.name("password"))
        element.sendKeys("jeejeejee")
        element = driver.findElement(By.name("passwordConfirmation"))
        element.sendKeys("jeejeejee")
        element = driver.findElement(By.linkText("add")
        element.submit()
    }
    then 'new user is not be registered to system', {
        driver.getPageSource().contains("must contain one character that is not a letter").shouldBe true
    }
}

scenario "creation fails with too short username and valid pasword", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver()
        driver.get("http://localhost:8090")
        element = driver.findElement(By.linkText("register new user"))    
        element.click()
    }
    when 'a too sort username and valid password are entered', {
        element = driver.findElement(By.name("username"))
        element.sendKeys("JJ")
        element = driver.findElement(By.name("password"))
        element.sendKeys("jeejee8")
        element = driver.findElement(By.name("passwordConfirmation"))
        element.sendKeys("jeejee8")
        element = driver.findElement(By.linkText("add")
        element.submit()
    }
    then 'new user is not be registered to system', {
        driver.getPageSource().contains("length 5-10").shouldBe true
    }
}

scenario "creation fails with already taken username and valid pasword", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver()
        driver.get("http://localhost:8090")
        element = driver.findElement(By.linkText("register new user"))    
        element.click()
    }
    when 'a already taken username and valid password are entered', {
        element = driver.findElement(By.name("username"))
        element.sendKeys("Pekka")
        element = driver.findElement(By.name("password"))
        element.sendKeys("joujou13")
        element = driver.findElement(By.name("passwordConfirmation"))
        element.sendKeys("joujou13")
        element = driver.findElement(By.linkText("add")
        element.submit()
    }
    then 'new user is not be registered to system', {
        driver.getPageSource().contains("username or password invalid").shouldBe true
    }
}

scenario "can not login with account that is not succesfully created", {
    given 'command new user is selected', {
        element = driver.findElement(By.linkText("continue to application mainpage"))    
        element.click()
        element = driver.findElement(By.linkText("logout"))    
        element.click()
        element = driver.findElement(By.linkText("login"))    
        element.click()
    }
    when 'a invalid username/password are entered', {
        element = driver.findElement(By.name("username"))
        element.sendKeys("Pekka")
        element = driver.findElement(By.name("password"))
        element.sendKeys("joujou13")
        element = driver.findElement(By.linkText("login"))      
        element.submit()  
    }
    then  'new credentials do not allow logging in to system', {
        driver.getPageSource().contains("wrong username or password").shouldBe true
    }
}