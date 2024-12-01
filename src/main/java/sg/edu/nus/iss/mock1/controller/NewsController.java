package sg.edu.nus.iss.mock1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.mock1.model.News;
import sg.edu.nus.iss.mock1.service.NewsService;

@Controller

public class NewsController {

    @Autowired
    NewsService newsService;

    // @GetMapping("")
    // @ResponseBody
    // public List<News> getNews() {
    //     List<News> newsList = newsService.getArticles();
    //     return newsList;
    // }
    @GetMapping("/")
    public String getNewsArticles(Model model) {
        List<News> newsList = newsService.getArticles();
        News news = new News();
        // model.addAttribute("newsIndividual")
        model.addAttribute("news",news);
        model.addAttribute("newsList",newsList);
        return "news_page";

    }
    // @PostMapping("/articles")
    // public String saveArticles2(@RequestBody MultiValueMap<String,String> form) {
    //     List<String> selectedNewsIds = form.get("selectedNewsIds");
    //     List<News> savedArticles = new ArrayList();
        
    //     for (String id : selectedNewsIds) {
    //         News savedNews = newsService.getNewsById(id);
    //         savedArticles.add(savedNews);

    //     }
    //     newsService.saveArticles(savedArticles);
        

    //     return "redirect:/";
    // }
    @PostMapping("/articles")
    public String saveArticles(@RequestParam(required=false,name="selectedNewsIds") List<String> selectedNewsIds) {
        
        if(selectedNewsIds !=null) {

            List<News> savedArticles = new ArrayList<>();
            for (String id : selectedNewsIds) {
                News savedNews = newsService.getNewsById(id);
                savedArticles.add(savedNews);
            }
            
            newsService.saveArticles(savedArticles);
        }
       
        

        return "redirect:/";
    }
    
}
