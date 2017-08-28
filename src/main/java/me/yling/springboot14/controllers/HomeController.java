package me.yling.springboot14.controllers;

import me.yling.springboot14.models.Director;
import me.yling.springboot14.models.Movie;
import me.yling.springboot14.repositories.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    DirectorRepository directorRepository;

    @GetMapping("/adddirector")
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
        return "listd";
    }

    @GetMapping("/addmovie")
    public String addMovie (Model model)
    {
        model.addAttribute("newMovie", new Movie());
        model.addAttribute("addmoviemessage", "Add movie");
        return "addmovie";
    }

    @PostMapping("/addmovie")
    public String postMovie(@ModelAttribute("newMovie") Movie movie, @ModelAttribute("director") Director finddir, Model model)
    {
        Set<Movie> movies = new HashSet<Movie>();
        finddir.setMovies(movies);
        directorRepository.save(finddir);
        model.addAttribute("directors", directorRepository.findAll());
        return "index";
    }

//    @GetMapping("/")
//    public String listIndex(Model model)
//    {
//
//       Director finddir = new Director();
//        //add the list of the movie to the director's movie list
//        finddir.setMovies(movies);
//
//        //Save the director to the database
//        directorRepository.save(finddir);
//
//        //Grad all the directors from the database and send them to the template
//        model.addAttribute("directors", directorRepository.findAll());
//        return "index";
//    }


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
