import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Clock;
import java.time.Instant;
import java.util.logging.SocketHandler;


public class FirstTests {

    WebDriver driver;

    public void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
    }

    @Before //warunki początkowe, wykona sie przed każdą metodą test
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        //ustawiamy property ze wskazaniem na chrome którego użyjemy
        driver = new ChromeDriver(); //otworzy nam przegladarkę
    }


    @Test //kroki testowe - po prostu test do wykonania
    public void firstTest() {
        driver.get("https://dev.to");
        WebElement sideBarVideo = driver.findElement(By.xpath("/html/body/div[9]/div/div/div[1]/aside/nav[1]/div/a[3]"));
        highlightElement(sideBarVideo);
        // znajdz element week
//        sideBarVideo.click();
    }

    @Test
    public void openFirstVideoPage() {
        driver.get("https://dev.to");
        WebElement sideBarVideo = driver.findElement(By.partialLinkText("Videos"));
        highlightElement(sideBarVideo);
        sideBarVideo.click();
        //przechodzimy na strone z video
        //powinnismy poczekac na załadowanie nowej strony
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe("https://dev.to/videos"));
        WebElement firstVideo = driver.findElement(By.className("video-image"));
        highlightElement(firstVideo);
        firstVideo.click();
    }


    @Test
    public void secondTest() {
        System.out.println("drugi test");
    }

    @Test //zadanie1
    public void zeroTest() {
        driver.get("https://dev.to");
        WebElement postBtn = driver.findElement(By.cssSelector("#articles-list > header > h1"));
        postBtn.click();

    }

    //wejdź na stronę dev.to
    //kliknąć w podcast
    //wybrać pierswszy podcast- pobiorę nazwę pierwszego podcastu z listy
    //sprawdzić czy jestem na odpowiedniej stronie- czy tytuł podcastu zgadza się
    //sprawdzic czy mogę nacisnąć play

    @Test
    public void selectFirstPodcast(){
        driver.get("https://dev.to");
        WebElement podcasts = driver.findElement(By.partialLinkText("Podcasts"));
        podcasts.click();
        WebElement firstPodcast = driver.findElement(By.cssSelector(".content > h3:first-child"));
        String podcastTitleFromList = firstPodcast.getText();
        String firstPodcastFromListLink = driver.findElement(By.cssSelector("#substories > a:first-child")).getAttribute("href");
//
//        WebElement linkToPodcast = driver.findElement(By.cssSelector("#substories > a:first-child"));
//        String linkToPodcastHref = linkToPodcast.getAttribute("href");
//
        firstPodcast.click();
       
        WebElement podcastTitle = driver.findElement(By.cssSelector(".title > h1:nth-child(2)"));
        String podcastTitleText = podcastTitle.getText();
        assertTrue(podcastTitleFromList.contains(podcastTitleText));
    }

    private void assertTrue(boolean contains) {
    }

//    @After
//    public void tearDown(){
//        driver.quit();
//        System.out.println("po każdej etodzie testowej");
//    }


}