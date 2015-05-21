package hw7;
public class Board {

    public static final int SIZE = 8;
    // You can call this variable by Board.SIZE.

	private Piece[][] pieces;
    private boolean isFireTurn;

    public Board() {
        pieces = new Piece[SIZE][SIZE];
        isFireTurn = true;
    }

    /** Makes a custom Board. Not a completely safe operation because you could do
    * some bad stuff here, but this is for the purposes of testing out hash
    * codes so let's forgive the author. 
    */
    public Board(Piece[][] pieces) {
        this.pieces = pieces;
        isFireTurn = true;
    }

	@Override
	public boolean equals(Object o) {
        return true; // YOUR CODE HERE
	}
	
	public void switchTurns(){
		isFireTurn = !isFireTurn;
	}

    @Override
    public int hashCode() {

    	int hash = 0;
    	int startIndex = 0;
    	int endIndex = 0;
    	for(int i = 0; i < 31; i += 1){
    		//int groupBit = 0;  		
    		endIndex = (int)((SIZE * SIZE)/31.0 * i);
    		for(int j = startIndex; j <= endIndex; j += 1){
    			int row = (int)Math.floor(j / SIZE);
    			int col = j % SIZE;
    			//System.out.println(j + " " + row + " " + col);
    			if(pieces[row][col] != null){
    				//groupBit = groupBit ^ pieces[row][col].hashCode();
    				//System.out.print(pieces[row][col].hashCode());
    				hash += (pieces[row][col].hashCode() + 1) << i;
    			}
    		}
    		startIndex = endIndex + 1;
    		//groupBit = groupBit % 2; 	
    		//hash <<= 1;
    		//hash += groupBit;
    	}
    	int firebit = 0;
    	if(isFireTurn)
    		firebit = 1;
    	
		hash <<= 1;
		hash += firebit;
		
        return hash; // YOUR CODE HERE
        
        //I wonder if this would work just as well...
		//yep, this passes all unit tests... pete's sake...
        //return pieces.hashCode() + firebit;
    }
}