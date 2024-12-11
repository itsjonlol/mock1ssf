package sg.edu.nus.iss.mock1.model;

import java.util.List;

public class Frontpage {
    
    private List<News> News;
    

    public Frontpage() {

    }
    
    

    

    



    public Frontpage(List<sg.edu.nus.iss.mock1.model.News> news) {
        News = news;
    }









    public List<News> getNews() {
        return News;
    }

    public void setNews(List<News> news) {
        News = news;
    }



   
    
    
}
