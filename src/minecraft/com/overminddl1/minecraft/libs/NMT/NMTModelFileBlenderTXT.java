package com.overminddl1.minecraft.libs.NMT;

import java.net.MalformedURLException;

public class NMTModelFileBlenderTXT extends NMTModelFile
{
	public NMTModelFileBlenderTXT(NMTModelRenderer nmtmodelrenderer, String mdlFile) throws MalformedURLException
	{
		super(nmtmodelrenderer, mdlFile);
	}

	@Override
	protected NMTModelFile getInstance()
	{
		try
		{
			return new NMTModelFileBlenderTXT(renderer, modelName);
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected NMTModelFile parseFile()
	{
		return null;
	}

	@Override
	protected String[] getExtensions()
	{
		return new String[] { "txt", "Data.txt" };
	}

	@Override
	protected String getModelFormat()
	{
		return "BlenderTXT";
	}
}
