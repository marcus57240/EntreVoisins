package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**initiating a new Arraylist, using a "for" loop, to define that this new list
     * have to display neighbours set as "favorite" ONLY, by checking the "favorite" boolean
     * parameter of each neighbour, using their positions.*/
    public List<Neighbour> getFavoritesNeighbours() {
        List<Neighbour> favorites = new ArrayList<>();

        for(int i =0; i < neighbours.size(); i++) {
            Neighbour neighbour = neighbours.get(i);
            if (neighbour.isFavorite()) {
                favorites.add(neighbour);
            }
        }
        return favorites;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**this method will be called when FAB button is clicked, to change the neighbour's favorite status
     * from true into false (become no-favorite)
     * @param neighbour
     */
    @Override
    public void deleteFavoriteNeighbour(Neighbour neighbour) {
        int position = neighbours.indexOf(neighbour);
        neighbours.get(position).setFavorite(false);
    }
    /**this method will be called when FAB button is clicked, to change the neighbour's favorite status
     * from false into true (become favorite)
     * @param neighbour
     */
    @Override
    public void addFavoriteNeighbour(Neighbour neighbour) {
        int position = neighbours.indexOf(neighbour);
        neighbours.get(position).setFavorite(true);
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
