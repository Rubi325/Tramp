package Janken;

public abstract class Player {
	protected String name;
	
	public String getName(){
        return this.name;
    }

    public abstract void setName();

    public abstract Hands nextHand(int [] a,int turn);
	
	
}
