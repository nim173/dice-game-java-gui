package model.interfaces;

/**
 * <pre>
 * Assignment interface for Further Programming representing a pair of Dice in the game
 * 
 * <b>IMPORTANT:</b> To facilitate testing, your implementation of this class should be <b>model.DicePairImpl</b>
 * i.e. a class named DicePairImpl residing in the model package
 * The implementing class should be immutable since this interface specifies no setter methods
 * The implementing class should provide a <b>no argument constructor</b> which creates the two dice 
 * with <b>randomly generated</b> values using the specified constructor of DieImpl i.e.
 *    <b>public DieImpl(int number, int value, int numFaces) throws IllegalArgumentException</b>
 * 
 * @author Caspar Ryan
 * </pre>
 */
public interface DicePair extends Comparable<DicePair>
{
   /**
    * @return the first Die of this pair
    */
   public Die getDie1();

   /**
    * @return the second Die of this pair
    */
   public Die getDie2();

   /**
    * @return the total value of the two dice (i.e. the sum of the values of die1 and die2)
    */
   public int getTotal();

   /**
    * @param dicePair - another DicePair to compare with
    * @return true if both Dice are equal according to {@link Die#equals(Die)}
    *           NOTE: order is enforced 3,6 is != 6,3
    */
   public abstract boolean equals(DicePair dicePair);

   /**
    * <pre> <b>NOTE:</b> this is the java.lang.Object {@literal @}Override
    * 
    * its implementation should cast and call through to the type checked method above
    * 
    * @param dicePair - another DicePair to compare with
    * @return true if both Dice are equal according to {@link Die#equals(Die)} 
    * </pre>
    */
   @Override
   public abstract boolean equals(Object dicePair);

   /**
    * <b>NOTE:</b> if equals() is true then generated hashCode should also be equal
    * 
    * @return generated hash code (used by various JCF Collections)
    * 
    */
   @Override
   public abstract int hashCode();

   /**
    * @return <pre>a human readable String that lists the values of this DicePair
    *         instance (see OutputTrace.pdf) 
    * 
    * This method should use {@link Die#toString()} where appropriate as part of the formatted output
    *         
    * e.g. "Dice 1: Five,  Dice 2: One .. Total: 6"
    * </pre>
    */
   @Override
   public abstract String toString();

   /**
    * Used to compare the totals of two DicePairs for {@literal <}, {@literal >} or equality
    * See API docs of java.lang.Comparable{@literal <}T{@literal >} for details
    */
   @Override
   public int compareTo(DicePair dicePair);
}