package model.interfaces;

/**
 * <pre>
 * IMPORTANT: implementing classes must provide the following constructor
 * 
 *    <b>public DieImpl(int number, int value, int numFaces) throws IllegalArgumentException</b>
 *    
 * {@literal @}param number - the number of this die in a pair (i.e. die 1 or 2)
 * {@literal @}param value - the face value of this die as it rolls (between 1 .. numFaces)
 * {@literal @}param numFaces - the number of faces of this die (i.e. the maximum value)
 * {@literal @}throws IllegalArgumentException if: 
 *    number {@literal <} 1 || {@literal >} 2 
 *    || value is {@literal <} 1 || value {@literal >} numFaces 
 *    || numFaces {@literal <} 1
 * </pre> 
 */
public interface Die
{
   /**
    * number of faces (standard casino dice have 6) Dungeons and Dragons dice have more!
    */
   public static final int NUM_FACES = 6;

   /**
    * @return the number of the die in the pair as an int i.e. 1 for Die 1 and 2 for Die 2
    * 
    * NOTE: This only distinguishes the two die so we can roll them at different speeds
    * not to be confused with <b>value</b> which represents the actual face value of the die as it rolls
    */
   public int getNumber();

   /**
    * @return a value between 1 and numFaces
    */
   public int getValue();

   /**
    * @return the number of faces 
    */
   public int getNumFaces();

   /**
    * @param die - another Die to compare with
    * @return true if both the value and numFaces are equal (does not compare die number)
    */
   public abstract boolean equals(Die die);

   /**
    * <pre> <b>NOTE:</b> this is the java.lang.Object @Override
    * 
    * the implementation should cast and call through to the type checked method above</pre>
    * 
    * @param die - another Die to compare with
    * @return true if both the value and numFaces are equal (does not compare die number)
    */
   @Override
   public abstract boolean equals(Object die);

   /**
    * <b>NOTE:</b> if equals() is true then generated hashCode should also be equal
    * 
    * @return generated hash code (used by various JCF Collections)
    * 
    */
   @Override
   public abstract int hashCode();

   /**
   * @return <pre> A human readable String that lists the face value of this Die instance
   * as a WORD not a digit (see OutputTrace.pdf) 
   * 
   * <b>NOTE:</b> Must use "proper naming" case i.e. First letter capitalised       
   * e.g. "One", "Two", "Three" etc.
   * 
   * For simplicity for values greater than 9 you can just output the string "{@literal >} Nine"
   * </pre>
   */
   @Override
   public abstract String toString();
}
