package me.yling.springboot14.controllers;

import me.yling.springboot14.models.Director;
import me.yling.springboot14.models.Movie;
import me.yling.springboot14.repositories.DirectorRepository;
import me.yling.springboot14.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    DirectorRepository directorRepository;

    @Autowired
    MovieRepository movieRepository;

    @GetMapping("/")
    public String addDirector (Model model)
    {
        model.addAttribute("newDirector", new Director());
        model.addAttribute("adddirectormessage", "Add director");
        return "adddirector";
    }

    @PostMapping("/adddirector")
    public String postDirector(@ModelAttribute("newDirector") Director director)
    {
        directorRepository.save(director);
        return "confirmd";
    }

    @RequestMapping("/showd")
    public String showDirector (Model model)
    {
        model.addAttribute("directors", directorRepository.findAll());
        return "showd";
    }

    @RequestMapping("/addmovie/{id}")
    public String addMovie (@PathVariable("id") long id, Model model)
    {

        Director finddir =directorRepository.findOne(id);
        model.addAttribute("finddir", finddir);

        Movie onemov = new Movie();
        onemov.setDirector(finddir);
        model.addAttribute("newMovie", onemov );
//
//        System.out.println(id);
//        System.out.println(finddir.getId());

        return "addmovie4dir";
    }


    @PostMapping("/addmovie4dir")
    public @ResponseBody  String postMovie4d (@ModelAttribute ("newMovie") Movie onemov, @ModelAttribute ("director") Director finddir)
    {
//        System.out.println(onemov.getTitle());
//        System.out.println(finddir.getId());

        movieRepository.save(onemov);
        return "Movie Added.";
    }


    @RequestMapping("/showm")
    public String listMovie (Model model)
    {
        model.addAttribute("directors", directorRepository.findAll());
        return "showm";
    }

    @RequestMapping("/listmovie/{id}")
    public String listMovie (@PathVariable("id") long id, Model model)
    {
        model.addAttribute("directors", directorRepository.findOne(id));
        return "showm";
    }








//    @RequestMapping("/")
//    public String index (Model model)
//    {
//        //create a director
//        Director director = new Director();
//        director.setName("Stephen Bullock");
//        director.setGenre("Sci Fi");
//
//        //create a movie
//        Movie movie = new Movie();
//        movie.setTitle("Star Movie");
//        movie.setYear(2017);
//        movie.setDescription("About Stars...");
//
//        //add the movie to an empty list
//        Set<Movie> movies = new HashSet<Movie>();
//        movies.add(movie);
//
//        movie = new Movie();
//        movie.setTitle("DeathStar Ewoks");
//        movie.setYear(2011);
//        movie.setDescription("About Ewoks on the DeathStar...");
//        movies.add(movie);
//
//        //add the list of the movie to the director's movie list
//        director.setMovies(movies);
//
//        //Save the director to the database
//        directorRepository.save(director);
//
//        //Grad all the directors from the database and send them to the template
//        model.addAttribute("directors", directorRepository.findAll());
//        return "index";
//
//    }



}
