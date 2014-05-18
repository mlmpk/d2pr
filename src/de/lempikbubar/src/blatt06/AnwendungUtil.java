package de.lempikbubar.src.blatt06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Beherbergt einige Hilfsfunktionen zur Anzeige von Intervallen und Jobs
 * @author mlempik
 *
 */
public class AnwendungUtil {

	/**
	 * Liest Intervalle aus Datei aus 
	 * @param path
	 * @return ArrayList vom Typ Interval
	 * @throws IOException
	 */
	public static ArrayList<Interval> readIntervals(String path) 	throws IOException {
		ArrayList<Interval> list = new ArrayList<>();

		BufferedReader in = new BufferedReader(new FileReader(path));
		String zeile = null;
		while ((zeile = in.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(zeile, ",");
			int start = Integer.parseInt(st.nextToken());
			int ende = Integer.parseInt(st.nextToken());
			Interval ivall = new Interval(start, ende);
			list.add(ivall);
		}
		in.close();

		return list;
	}

	/**
	 * Liest Jobs aus Datei aus
	 * @param path
	 * @return ArrayList vom Typ Job
	 * @throws IOException
	 */
	public static ArrayList<Job> readJobs(String path) throws IOException {
		ArrayList<Job> list = new ArrayList<>();

		BufferedReader in = new BufferedReader(new FileReader(path));
		String zeile = null;
		while ((zeile = in.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(zeile, ",");
			int start = Integer.parseInt(st.nextToken());
			int deadline = Integer.parseInt(st.nextToken());
			Job job = new Job(start, deadline);
			list.add(job);
		}
		in.close();

		return list;
	}

	/**
	 * Einfache Ausgabe der Jobs auf der Konsole
	 * @param array
	 */
	public static void printJobs(ArrayList<Job> array) {

		System.out.print("[");

		for (Job s : (ArrayList<Job>) array) {
			System.out.print(s.toString());
		}

		System.out.println("]");

	}

	/**
	 * Einfache Ausgabe der Intervalle auf der Konsole
	 * @param array
	 */
	public static void printIntervals(ArrayList<Interval> array) {

		System.out.print("[");

		for (Interval s : (ArrayList<Interval>) array) {
			System.out.print(s.toString());
		}

		System.out.println("]");

	}

	/**
	 * Einfache Ausgabe des Lateness Schedules auf der Konsole
	 * @param array
	 */
	public static void printResult(int[] array) {

		System.out.print("[");
		for (int s : array) {
			System.out.print("[" + s + "]");
		}
		System.out.println("]");

	}

	/**
	 * Berechnet aus der Differenz von z=Summe aller Intervalllängen und (Deadline - Intervalllänge des letzten Jobs)
	 * @param jobs
	 * @param erg
	 * @return
	 */
	public static int getMaxLateness(ArrayList<Job> jobs, int[] erg) {

		Job lastJob = jobs.get(jobs.size() - 1);
		int z = erg[erg.length - 1]; // Summe aller Intervalllängen
		return z - (lastJob.getDeadline() - lastJob.getDauer());
	}

}
