import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenerateRandomFilm {


    public static void main(String[] args) throws IOException {

        Logger log = Logger.getLogger(GenerateRandomFilm.class);

        final Document document = Jsoup.connect("https://www.imdb.com/chart/top/").get();

        List<String> filmToWatch = new ArrayList<>();

        for (Element row : document.select("table.chart.full-width tr")) {

            final String title = row.select(".titleColumn").text();
            final String rating = row.select(".imdbRating").text();

            filmToWatch.add(title + " -> " + rating);

        }

        Collections.shuffle(filmToWatch);
        log.info(filmToWatch.get(0));

        filmToWatch.stream().
                filter(String -> String.contains("(199"))
                .forEach(System.out::println);
    }
}
