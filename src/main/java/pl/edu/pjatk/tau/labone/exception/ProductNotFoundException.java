/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.pjatk.tau.labone.exception;

/**
 *
 * @author s14646
 */
public class ProductNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1997753363232804009L;

    public ProductNotFoundException()
    {
    }

    public ProductNotFoundException(String message)
    {
        super(message);
    }

    public ProductNotFoundException(Throwable cause)
    {
        super(cause);
    }

    public ProductNotFoundException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public ProductNotFoundException(String message, Throwable cause, 
                                       boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
