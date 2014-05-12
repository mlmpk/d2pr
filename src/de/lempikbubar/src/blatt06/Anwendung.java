package de.lempikbubar.src.blatt06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

import de.lempikbubar.src.config.Statics;
import de.lempikbubar.src.exception.ParameterException;

public class Anwendung {

	/**
	 * Implementierung des IntervalScheduling-Algorithmus
	 * 
	 * @param intervals
	 * @return
	 */
	public static ArrayList<Interval> intervalScheduling(ArrayList<Interval> intervals) {

		ArrayList<Interval> results = new ArrayList<>();
		int laenge = intervals.size();
		int j = 1;

		for (int i = 0; i < laenge; i++) {
			if (intervals.get(i).getStart() >= intervals.get(j).getEnd()) {
				results.add(intervals.get(i));
				j = i;
			}
		}

		return results;
	}

	/**
	 * Implementierug des Lateness Algorithmus
	 * @param jobs
	 * @return
	 */
	public static int[] latenessScheduling(ArrayList<Job> jobs) {

		int laenge = jobs.size();
		int[] arr = new int[laenge];
		int z = 0;
		for (int i = 0; i < laenge; i++) {
			arr[i] = z;
			z = z + jobs.get(i).getDauer();
		}

		return arr;
	}

	/**
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	private static ArrayList<Interval> readFileAndCreateArray(String path) throws IOException {
		// TODO: make it generic !!
		ArrayList<Interval> list = new ArrayList<>();
		
		BufferedReader in = new BufferedReader(new FileReader(path));
		String zeile = null;
		while ((zeile = in.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(zeile,",");
			int start = Integer.parseInt(st.nextToken());
			int ende = Integer.parseInt(st.nextToken());
			Interval ivall = new Interval(start,ende);
			list.add(ivall);
		}
		in.close();
		
		return list;
	}
	
	
	/**
	 * Gibt die Ergebnisliste aus
	 * @param erwartet eine Liste vom Typ ArrayList<Interval>
	 */
	private static void printResult(ArrayList<Interval> array){
	
		System.out.print("[");
		for (Interval s : (ArrayList<Interval>) array) {
			System.out.print(s.toString());
		}
		System.out.println("]");

	}
	
//	private static void printResult(int[] array){
//		
//		System.out.print("[");
//		for (int s : array) {
//			System.out.print("["+s+"]");
//		}
//		System.out.println("]");
//
//	}
	
	public static void main(String[] args) {

		try {

			if (args.length == 2) {
				
				String type = args[0];
				String path = args[1];

				if (type.equals("Interval")) {
					
					ArrayList<Interval> intervals = (ArrayList<Interval>) readFileAndCreateArray(path);
					printResult(intervals);
					
					intervals = intervalScheduling(intervals);
					
					Collections.sort(intervals);
					printResult(intervals);
					
				} else if (type.equals("Lateness")) {
//					ArrayList<Interval> jobs = null;
//					printResult(jobs);
				} else {
					throw new ParameterException(Statics.BLATT06_PARAMETER_EXCEPTION);
				}

			} else {
				throw new ParameterException(Statics.BLATT06_PARAMETER_EXCEPTION);
			}
		} catch (ParameterException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Datei wurde nicht gefunden! Bitte Pfadangabe überprüfen!");
		} catch (Exception e) {
			System.out.println("Oh oh, das hätte nicht passieren dürfen. Überprüfe die Syntax!");
		}

	}
}
