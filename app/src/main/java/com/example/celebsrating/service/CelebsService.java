package com.example.celebsrating.service;

import com.example.celebsrating.beans.Celeb;
import com.example.celebsrating.dao.IRepository;
import java.util.ArrayList;
import java.util.List;

public class CelebsService implements IRepository<Celeb> {

    private List<Celeb> celebList;
    private static CelebsService instance;

    private CelebsService() {
        celebList = new ArrayList<>();
        loadDefaultData();
    }

    public static CelebsService getInstance() {
        if (instance == null)
            instance = new CelebsService();
        return instance;
    }

    private void loadDefaultData() {
        celebList.add(new Celeb("Emma Watson",
                "https://upload.wikimedia.org/wikipedia/commons/7/7f/Emma_Watson_2013.jpg",
                4.5f));
        celebList.add(new Celeb("Tom Cruise",
                "https://upload.wikimedia.org/wikipedia/commons/3/33/Tom_Cruise_by_Gage_Skidmore_2.jpg",
                4.2f));
        celebList.add(new Celeb("Scarlett Johansson",
                "https://upload.wikimedia.org/wikipedia/commons/6/60/Scarlett_Johansson_2010.jpg",
                4.7f));
        celebList.add(new Celeb("Leonardo DiCaprio",
                "https://upload.wikimedia.org/wikipedia/commons/2/25/Leonardo_DiCaprio_2010.jpg",
                4.8f));
        celebList.add(new Celeb("Angelina Jolie",
                "https://upload.wikimedia.org/wikipedia/commons/a/ad/Angelina_Jolie_2_June_2014_%28cropped%29.jpg",
                4.6f));
    }

    @Override
    public boolean create(Celeb o) { return celebList.add(o); }

    @Override
    public boolean update(Celeb o) {
        for (Celeb c : celebList) {
            if (c.getId() == o.getId()) {
                c.setFullName(o.getFullName());
                c.setPhotoUrl(o.getPhotoUrl());
                c.setRating(o.getRating());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Celeb o) { return celebList.remove(o); }

    @Override
    public Celeb findById(int id) {
        for (Celeb c : celebList)
            if (c.getId() == id) return c;
        return null;
    }

    @Override
    public List<Celeb> findAll() { return celebList; }
}