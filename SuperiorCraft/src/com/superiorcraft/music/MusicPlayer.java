package com.superiorcraft.music;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.Note.Tone;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.superiorcraft.SuperiorCraft;
import com.superiorcraft.api.util.ServerUtil;

public class MusicPlayer {
	
	public static class MusicThread extends BukkitRunnable {
		
		int note = 0;
		
		@Override
		public void run() {
			/*for (Player p : ServerUtil.getPlayers()) {
				//p.playNote(p.getLocation(), Instrument.GUITAR, Note.natural(1, Tone.A));
				//p.getWorld().playSound(p.getLocation(), Sound.BLOCK_NOTE_GUITAR, 10, 1);
			}*/
			
			for (Player p : ServerUtil.getPlayers()) {
				p.playNote(p.getLocation(), Instrument.PIANO, MusicPlayer.translateMusicFileToNotes("test", 1).get(note));
				p.playNote(p.getLocation(), Instrument.PIANO, MusicPlayer.translateMusicFileToNotes("test", 2).get(note));
				note++;
				if (MusicPlayer.translateMusicFileToNotes("test", 1).size() == note) {
					note = 0;
				}
			}
		}
		
	}

	public static List<String> readMusicFile(String file) {
		try {
			return Files.readAllLines(Paths.get(SuperiorCraft.plugin.getDataFolder().getAbsolutePath(), file + ".mcmus"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static List<Note> translateMusicFileToNotes(String file, int line) {
		ArrayList<Note> notes = new ArrayList<Note>();
		for (String s : readMusicFile(file).get(line).split(" ")) {
			//System.out.println(s);
			//System.out.println(Integer.valueOf(String.valueOf(s.charAt(1))));
			notes.add(Note.natural(Integer.valueOf(String.valueOf(s.charAt(1))), Tone.valueOf(String.valueOf(s.charAt(0)))));
		}
		return notes;
	}
	
}
