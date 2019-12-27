package cn.itcast.travel.service;

public interface FavoriteService {

    public boolean isFavourite(int uid,Integer rid);

    Boolean addFavourite(int uid, int rid);
}
