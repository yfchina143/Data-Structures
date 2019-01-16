package projects.avlg.exceptions;

/**
 *<p> <tt>InvalidBalanceException</tt> is an {@link Exception} that is thrown by the constructor
 * of {@link projects.avlg.AVLGTree}
 *
 * <p>You should <b>not</b> edit this class! It is given to you as a resource for your project.</p>

 * @author <a href="https://github.com/jasonfil">Jason Filippou</a>
 *
 * @see EmptyTreeException
*/
public class InvalidBalanceException extends Exception {
    public InvalidBalanceException(String msg){
        super(msg);
    }
}
