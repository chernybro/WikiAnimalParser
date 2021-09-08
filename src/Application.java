import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Application {
    static ArrayList<Animal> animalsList = new ArrayList<>(38000); // всего ~38к страниц, так явно быстрей
    static Document html;
    static int i = 0;
    public static void main(String[] args)  {

        html = createHTML("https://ru.wikipedia.org/w/index.php?title=Категория:Животные_по_алфавиту&pageuntil=Азиатский+страус#mw-pages");
        parseLinksWithAnimalPages();

        AnimalSet animals = new AnimalSet(animalsList);
        //Gson json = new GsonBuilder().setPrettyPrinting().create();
        Gson json = new GsonBuilder().create();

        writeJsonToFile(json, animals);
    }

    private static void writeJsonToFile(Gson json, AnimalSet animals) {
        File dir = new File("data");
        if (!dir.exists())
            dir.mkdir();

        FileWriter jsonFile;
        try {
            jsonFile = new FileWriter("data/animals1.json");
            jsonFile.write(json.toJson(animals));
            jsonFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Document createHTML(String url) {
        Document html = null;
        try {
            html = Jsoup.connect(url).maxBodySize(80000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // System.out.println(html.title());
        return html;
    }

    private static void parseLinksWithAnimalPages() {
        i++;
        Elements pages = html.select("a[title$=Категория:Животные по алфавиту]");
        Element page = pages.last();
        //System.out.println("https://ru.wikipedia.org" + page.attr("href"));

        parseAllRefsFromOnePage("https://ru.wikipedia.org" + page.attr("href"));
        html = createHTML("https://ru.wikipedia.org" + page.attr("href"));
        System.out.println(i);
        if (i == 186) {System.out.println("https://ru.wikipedia.org" + page.attr("href")); return;}
        if (i == 30) System.out.println("https://ru.wikipedia.org" + page.attr("href"));
        if (i == 60) System.out.println("https://ru.wikipedia.org" + page.attr("href"));
        if (i == 90) System.out.println("https://ru.wikipedia.org" + page.attr("href"));
        if (i == 120) System.out.println("https://ru.wikipedia.org" + page.attr("href"));
        if (i == 150) System.out.println("https://ru.wikipedia.org" + page.attr("href"));
        parseLinksWithAnimalPages();

    }

    private static void parseAllRefsFromOnePage(String url) {
        int j = 0;
        Document animalPages = createHTML(url);

        Elements els = animalPages.select("div[class=\"mw-category-group\"]");
        Element el = els.first();
        els = el.select("a");
        for (var item: els) {
            //System.out.println("https://ru.wikipedia.org" + item.attr("href"));
            parseSinglePage("https://ru.wikipedia.org" + item.attr("href"));
            System.out.println(j++);
        }
    }

    private static void parseSinglePage(String url) {
        Document animalPage = createHTML(url);
        String name = animalPage.selectFirst("h1").text(); // название животного
        Elements els = animalPage.select("div[class=\"ts-Taxonomy-rang-row\"]");
        Animal animal;
        ArrayList<String> s = new ArrayList<>();
        for (int i = 0;i < 8; i++) s.add("undefined");
        for (var item: els) {
                if (item.text().contains("Царство")) s.set(0, item.text());
                if (item.text().contains("Тип")) s.set(1, item.text());
                if (item.text().contains("Класс")) s.set(2, item.text());
                if (item.text().contains("Отряд")) s.set(3, item.text());
                if (item.text().contains("Семейство")) s.set(4, item.text());
                if (item.text().contains("Род")) s.set(5, item.text());
                if (item.text().contains("Вид")) s.set(6, item.text());
        }
        if (s.get(6).contains("Вид")) {
            animal = new Animal(name, s.get(0), s.get(1), s.get(2), s.get(3), s.get(4), s.get(5), s.get(6), url);
            animalsList.add(animal);
            //System.out.println(animal);
        }
    }



}
