package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private List<Neighbour> mFavorites = new ArrayList<>();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    public List<Neighbour> getFavoritesNeighbours() {
        return mFavorites;
      /*  List<Neighbour> localFavorites = new ArrayList<>();

        for(int i =0; i < neighbours.size(); i++) {
            Neighbour neighbour = neighbours.get(i);
            if (neighbour.isFavorite()) {
                localFavorites.add(neighbour);
            }
        }
        return localFavorites;
*/
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    @Override
    public void deleteFavoriteNeighbour(Neighbour neighbour) {
        neighbour.setFavorite(false);
        mFavorites.remove(neighbour);
    }

    @Override
    public void addFavoriteNeighbour(Neighbour neighbour) {
        neighbour.setFavorite(true);
        mFavorites.add(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }
}
