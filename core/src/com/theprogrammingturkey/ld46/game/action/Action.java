package com.theprogrammingturkey.ld46.game.action;

public class Action
{
	private String action;
	private ICallback callback;

	public Action(String action, ICallback callback)
	{
		this.action = action;
		this.callback = callback;
	}

	public String getAction()
	{
		return action;
	}

	public void onClick()
	{
		callback.onClick();
	}

	public interface ICallback
	{
		void onClick();
	}
}
