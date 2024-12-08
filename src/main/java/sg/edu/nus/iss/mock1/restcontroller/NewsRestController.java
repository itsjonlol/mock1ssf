package sg.edu.nus.iss.mock1.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.mock1.model.News;
import sg.edu.nus.iss.mock1.service.NewsService;


@RestController
public class NewsRestController {

    @Autowired
    NewsService newsService;
    
    @GetMapping("/news/{id}")
    public ResponseEntity<String> getNewsJsonString(@PathVariable("id") String id) {
        
        if (!newsService.checkIfNewsExist(id)){
            JsonObject errorJson = Json.createObjectBuilder()
                                       .add("error","Cannot find news article " + id)
                                       .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).header("Content-Type", "application/json").body(errorJson.toString());
            
        }
       
        // News news = newsService.getSavedNewsById(id);
        News news = newsService.getSavedNewsById2(id);
        JsonObject responseJson = Json.createObjectBuilder()
                              .add("id",news.getId())
                              .add("title",news.getTitle())
                              .add("body",news.getBody())
                              .add("published_on",news.getPublished())
                              .add("url",news.getUrl())
                              .add("imageurl",news.getImageUrl())
                              .add("tags",news.getTags())
                              .add("categories",news.getCategories())
                              .build();
        return ResponseEntity.status(HttpStatus.OK).header("Content-Type", "application/json").body(responseJson.toString());
        
    }
    // if want to give news as response
    @GetMapping("/news2/{id}")
    public ResponseEntity<News> getNewsJsonString2(@PathVariable("id") String id) {
        
        // if (!newsService.checkIfNewsExist(id)){
        //     JsonObject errorJson = Json.createObjectBuilder()
        //                                .add("error","Cannot find news article " + id)
        //                                .build();
        //     return ResponseEntity.status(HttpStatus.NOT_FOUND).header("Content-Type", "application/json").body(errorJson.toString());
            
        // }
        
       
        // News news = newsService.getSavedNewsById(id);
        News news = newsService.getSavedNewsById2(id);
        
        return ResponseEntity.status(HttpStatus.OK).header("Content-Type", "application/json").body(news);
        
    }
    //if giving object as response
    // @GetMapping("/{id}")
    // public ResponseEntity<Object> getNewsById(@PathVariable String id){
        
    //         News news = newsService.getsNewsById(id);
    //         if (!news.getId().equals("not found")){
    //             return ResponseEntity.ok().body(news);
    //         }   

    //         Map<String, String> error = new HashMap<>();
    //         String errorMsg = "Cannot find news article "+id;
    //         error.put("error", errorMsg);
            
    //         return ((BodyBuilder) ResponseEntity.notFound()).body(error);

    // }
    

}
