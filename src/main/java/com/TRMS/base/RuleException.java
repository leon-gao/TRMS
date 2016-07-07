package com.TRMS.base;

public class RuleException extends RuntimeException {

    // serial version id
    private static final long serialVersionUID = 9049160567189835471L;

    public RuleException()  
    {  
        super();  
    }  
  
    public RuleException(String message, Throwable cause)  
    {  
        super(message, cause);  
    }  
  
    public RuleException(String message)  
    {  
        super(message);  
    }  
  
    public RuleException(Throwable cause)  
    {  
        super(cause);  
    }  
}
