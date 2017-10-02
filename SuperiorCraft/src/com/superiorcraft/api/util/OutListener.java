package com.superiorcraft.api.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

import org.bukkit.Bukkit;

public class OutListener {
	
	private PipedInputStream pipeIn;
	private PrintStream s;
	
	public OutListener() {
		setup();
	}
	
	private void setup() {
		PipedOutputStream pipeOut = new PipedOutputStream();
		try {
			pipeIn = new PipedInputStream(pipeOut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s = System.out;
		System.setOut(new PrintStream(pipeOut));
	}
	
	public void listen() {
		try {
			if (pipeIn.available() != 0) {
				Bukkit.broadcastMessage("new message: " + pipeIn.read());
				s.println(pipeIn.read());
			}
		} catch (IOException e) {e.printStackTrace();}
	}
	
}
