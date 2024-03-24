package com.example.movies;

public class Movie {

    private String m_title;
    private String m_type;
    private String m_year;
    private String m_imdb;
    private String m_poster;

    public Movie(String title, String type, String year, String imdb, String poster) {
        this.m_title = title;
        this.m_type = type;
        this.m_year = year;
        this.m_imdb = imdb;
        this.m_poster = poster;
    }

    public String getM_title() {
        return m_title;
    }

    public String getM_type() {
        return m_type;
    }

    public String getM_year() {
        return m_year;
    }

    public String getM_imdb() {
        return m_imdb;
    }

    public String getM_poster() {
        return m_poster;
    }
}
