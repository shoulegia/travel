package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;

public interface FavouriteDao {
    public Favorite isFavourite(int uid,int rid);

    Integer countFav(int rid);

    Integer addFavourite(int uid, int rid);
}
