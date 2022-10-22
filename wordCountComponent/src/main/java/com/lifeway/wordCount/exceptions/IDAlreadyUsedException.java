package com.lifeway.wordCount.exceptions;

public class IDAlreadyUsedException extends Exception
{

	public IDAlreadyUsedException()
	{
		super();
	}

	public IDAlreadyUsedException(String arg0)
	{
		super(arg0);
		
	}

	public IDAlreadyUsedException(Throwable arg0)
	{
		super(arg0);
	}

	public IDAlreadyUsedException(String arg0, Throwable arg1)
	{
		super(arg0, arg1);
	}

	public IDAlreadyUsedException(String arg0, Throwable arg1, boolean arg2, boolean arg3)
	{
		super(arg0, arg1, arg2, arg3);
	}

}
