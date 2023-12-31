package org.pneditor.petrinet.adapters.group1;

import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.models.group1.NoExistingObjectException;
import org.pneditor.petrinet.models.group1.Place;

/**
 * This class represents an adapter for Petri net places. It adapts between a general Petri net place and a specific
 * implementation in the context of a larger system.
 */
public class PlaceAdapter extends AbstractPlace {

    // The underlying Petri net place
    private Place place;
    private ArcAdapter inArc;
    private ArcAdapter outArc;

    /**
     * Constructor for PlaceAdapter. It creates an adapter for a Petri net place based on the specified label.
     *
     * @param label The label for the place
     */
    public PlaceAdapter(String label) {
        super(label);
        // Initializes the underlying Petri net place
        this.place = new Place();
    }

    /**
     * Gets the underlying Petri net place.
     *
     * @return The underlying Petri net place
     */
    public Place getPlace() {
        return place;
    }

    /**
     * Sets the underlying Petri net place.
     *
     * @param place The Petri net place to be set
     */
    public void setPlace(Place place) {
        this.place = place;
    }

    /**
     * Adds a token to the place, incrementing the token count.
     */
    public void addToken() {
        place.setNbTokens(place.getNbTokens() + 1);
        try {
            TransitionAdapter endTransitionAdapter = (TransitionAdapter) outArc.getEnd();
            if (this.getTokens() < outArc.getMultiplicity()) {
                endTransitionAdapter.setIsFireable(false);
            } else {
                endTransitionAdapter.setIsFireable(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes a token from the place, decrementing the token count.
     */
    public void removeToken() {
        place.setNbTokens(place.getNbTokens() - 1);
        try {
            TransitionAdapter endTransitionAdapter = (TransitionAdapter) outArc.getEnd();
            if (this.getTokens() < outArc.getMultiplicity()) {
                endTransitionAdapter.setIsFireable(false);
            } else {
                endTransitionAdapter.setIsFireable(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the current number of tokens in the place.
     *
     * @return The number of tokens in the place
     */
    public int getTokens() {
        return place.getNbTokens();
    }

    /**
     * Sets the number of tokens in the place.
     *
     * @param tokens The number of tokens to be set
     */
    public void setTokens(int tokens) {
        place.setNbTokens(tokens);
    }

    /**
     * Checks if the object is a place.
     *
     * @return Always returns true for places
     */
    @Override
    public boolean isPlace() {
        return true;
    }

	public void setInArc(ArcAdapter inArc) {
		this.inArc = inArc;
        this.place.setInArc(inArc.getArc());
	}
	
	public void setOutArc(ArcAdapter outArc) {
		this.outArc = outArc;
        this.place.setOutArc(outArc.getArc());
	}
	
	public ArcAdapter getInArc() {
		return this.inArc;
	}
	
	public ArcAdapter getOutArc() {
		return this.outArc;
	}
}