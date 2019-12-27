package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavouriteDao;
import cn.itcast.travel.dao.impl.FavouriteDaoImpl;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.service.FavoriteService;

public class FavouriteServiceImpl implements FavoriteService {
    private FavouriteDao favouriteDao = new FavouriteDaoImpl();

    @Override
    public boolean isFavourite(int uid, Integer rid) {
        Favorite favourite = favouriteDao.isFavourite(uid, rid);
        if (favourite != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean addFavourite(int uid, int rid) {
        Integer integer = favouriteDao.addFavourite(uid, rid);
        if (integer == 0) {
            return false;
        } else {
            return true;
        }
    }
}
