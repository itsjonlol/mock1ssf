package sg.edu.nus.iss.mock1.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.mock1.constant.ConstantVar;
import sg.edu.nus.iss.mock1.constant.Url;
import sg.edu.nus.iss.mock1.model.News;
import sg.edu.nus.iss.mock1.repo.NewsRepo;

@Service
public class NewsService {

    @Autowired
    NewsRepo newsRepo;

    RestTemplate restTemplate = new RestTemplate();

    public List<News> getArticles() {

        

        ResponseEntity<String> responseJsonEntity = restTemplate.getForEntity(Url.crpytoUrl,String.class);   
        String responseJsonString = responseJsonEntity.getBody();

        InputStream is = new ByteArrayInputStream(responseJsonString.getBytes());
        JsonReader reader = Json.createReader(is);
        JsonObject responseJson = reader.readObject();
        JsonArray dataJsonArray = responseJson.getJsonArray("Data");
        List<News> newsList = new ArrayList<>();

        for (int i = 0; i<dataJsonArray.size();i++) {
            JsonObject individualNewsJson = dataJsonArray.getJsonObject(i);
            News news = new News();
            news.setId(individualNewsJson.getString("id"));
            // Integer published = individualNewsJson.getInt("published_on");
            // Long publishedLong= (long) published;
            // news.setPublished(publishedLong);
            news.setPublished(individualNewsJson.getInt("published_on"));
            news.setTitle(individualNewsJson.getString("title"));
            news.setUrl(individualNewsJson.getString("url"));
            news.setImageUrl(individualNewsJson.getString("imageurl"));
            news.setBody(individualNewsJson.getString("body"));
            news.setTags(individualNewsJson.getString("tags"));
            news.setCategories(individualNewsJson.getString("categories"));
            newsList.add(news);
        }

       
        return newsList;
    }
    
    public News getNewsById(String id) {
        List<News> newsList = this.getArticles();
        
        for (News news : newsList) {
            if (news.getId().equals(id)) {
                return news;
            }
        }
        return null;

    }
    public void saveArticles(List<News> savedArticles) {
        
        for (News news : savedArticles) {
            newsRepo.setHash(ConstantVar.redisKey, news.getId(), news.toString());

        }
        
        
    }

    public News getSavedNewsById(String id) {
        String rawData = newsRepo.getValueFromHash(ConstantVar.redisKey, id);
        String[] data = rawData.split("~~~");
        
        String idNews = data[0];
        Integer published = Integer.parseInt(data[1]);
        String title = data[2];
        String url =data[3];
        String imageUrl = data[4];
        String body = data[5];
        String tags = data[6];
        String categories = data[7];
        
        News news = new News();
        news.setId(idNews);
        news.setPublished(published);
        news.setTitle(title);
        news.setUrl(url);
        news.setImageUrl(imageUrl);
        news.setBody(body);
        news.setTags(tags);
        news.setCategories(categories);

        return news;
    }

    public Boolean checkIfNewsExist(String id) {
        return newsRepo.hasKey(ConstantVar.redisKey, id);

    }
    
}
