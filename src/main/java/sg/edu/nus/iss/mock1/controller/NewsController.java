package sg.edu.nus.iss.mock1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import sg.edu.nus.iss.mock1.model.News;
import sg.edu.nus.iss.mock1.service.NewsService;

@Controller

public class NewsController {

    @Autowired
    NewsService newsService;

    
    @GetMapping("/")
    public String getNewsArticles(Model model) {
        List<News> newsList = newsService.getArticles();
        
        model.addAttribute("newsList",newsList);
        return "news_page";

    }

    @PostMapping("/articles")
    public String saveArticles2(@RequestBody(required=false) MultiValueMap<String,String> form) {



        List<String> ids = form.get("selectedNewsIds");
        
        if (form!=null) {
            List<News> savedArticles = new ArrayList<>();

            for ( int i = 0; i<ids.size();i++) {
                News news = new News();
                news.setId(form.get("selectedNewsIds").get(i));
                news.setImageUrl(form.get("imageUrl").get(i));
                news.setTitle(form.get("title").get(i));
                news.setBody(form.get("body").get(i));
                news.setCategories(form.get("categories").get(i));
                news.setTags(form.get("tags").get(i));
                news.setPublished(Integer.parseInt(form.get("published").get(i)));
                news.setUrl(form.get("url").get(i));

                savedArticles.add(news);
            }

            newsService.saveArticles2(savedArticles);
        }

        
             return "redirect:/";
    }
    

   
    
        

   //if want to use requestparams
    // @PostMapping("/articles")
    // public String saveArticles(@RequestParam(required=false,name="selectedNewsIds") List<String> selectedNewsIds) {
    //     //if use String instead of List<String>, will be concantacenated for multiple ids
       
    //     if(selectedNewsIds !=null) {

    //         List<News> savedArticles = new ArrayList<>();
    //         for (String id : selectedNewsIds) {
    //             News savedNews = newsService.getNewsById(id);
                
    //             savedArticles.add(savedNews);
    //         }
            
    //         // newsService.saveArticles(savedArticles);
    //         newsService.saveArticles2(savedArticles);
    //     }
       
        

    //     return "redirect:/";
    // }
    //if want to send the whole news over
    // @PostMapping("/save")
    // public String saveArticle(@RequestParam(value = "id", required = false) List<String> ids,
    //                             @RequestParam(value="imageUrl", required = false) List<String> imageUrls,
    //                             @RequestParam(value = "title", required = false) List<String> titles,
    //                             @RequestParam(value = "body", required = false) List<String> bodies,
    //                             @RequestParam(value = "categories", required = false) List<String> categories,
    //                             @RequestParam(value = "tags", required = false) List<String> tags,
    //                             @RequestParam(value = "publishedOn", required = false) List<String> publishedOn,
    //                             @RequestParam("url") List<String> urls){
    //     if (ids!=null){
    //         List<News> newsList = new ArrayList<>();
    //         for (int i = 0 ; i<ids.size() ; i++){
    //             News news = new News();
    //             news.setId(ids.get(i));
    //             news.setBody(bodies.get(i));
    //             news.setCategories(categories.get(i));
    //             news.setImageUrl(imageUrls.get(i));
    //             news.setPublishedOn(publishedOn.get(i));
    //             news.setTags(tags.get(i));
    //             news.setTitle(titles.get(i));
    //             news.setUrl(urls.get(i));
    //             newsList.add(news);
    //         }
            
    //         newsService.saveArticles(newsList);
    //     }
    //     return "redirect:/";
    // }

    // if want to use multivaluemap
    
    
}