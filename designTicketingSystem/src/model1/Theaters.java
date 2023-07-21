package model1;

import java.util.List;

public class Theaters {
    private String theaterName;
    private Address theaterAdderss;
    private List<Screens> screensList;
    private List<Movie> activeMovies;

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public Address getTheaterAdderss() {
        return theaterAdderss;
    }

    public void setTheaterAdderss(Address theaterAdderss) {
        this.theaterAdderss = theaterAdderss;
    }

    public List<Screens> getScreensList() {
        return screensList;
    }

    public void setScreensList(List<Screens> screensList) {
        this.screensList = screensList;
    }

    public List<Movie> getActiveMovies() {
        return activeMovies;
    }

    public void setActiveMovies(List<Movie> activeMovies) {
        this.activeMovies = activeMovies;
    }
}
