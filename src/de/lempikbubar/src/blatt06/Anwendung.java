package de.lempikbubar.src.blatt06;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import de.lempikbubar.src.config.Statics;
import de.lempikbubar.src.exception.ParameterException;

public class Anwendung {

	/**
	 * Implementierung des IntervalScheduling-Algorithmus
	 * 
	 * @param intervals
	 * @return Interval Schedule
	 */
	public static ArrayList<Interval> intervalScheduling(ArrayList<Interval> intervals) {

		ArrayList<Interval> results = new ArrayList<>();
		int laenge = intervals.size();
		results.add(intervals.get(0));
		int j = 0;

		for (int i = 1; i < laenge; i++) {
			if (intervals.get(i).getStart() >= intervals.get(j).getEnd()) {
				results.add(intervals.get(i));
				j = i;
			}
		}

		return results;
	}

	/**
	 * Implementierug des Lateness Algorithmus
	 * 
	 * @param jobs
	 * @return Lateness Schedule
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
	

	public static void main(String[] args) {

		try {

			if (args.length == 2) { // frage genau zwei Argumente ab [Interval/Lateness][Pfad+Dateiname]

				String type = args[0], path = args[1];

				if (type.equals("Interval")) {
					
					// Lese Werte aus Datei für Interval Scheduling
					ArrayList<Interval> intervals = AnwendungUtil.readIntervals(path);
					System.out.println("Es wurden " + intervals.size() + " Zeilen mit folgendem Inhalt gelesen:");
					AnwendungUtil.printIntervals(intervals);
					
					System.out.println("sortiert:");
					Collections.sort(intervals); // Sortiere anhand des festgelegten Kriteriums für Intervalle
					AnwendungUtil.printIntervals(intervals);

					System.out.println("Berechnetes Intervallscheduling:");
					intervals = intervalScheduling(intervals);	
					AnwendungUtil.printIntervals(intervals);

				} else if (type.equals("Lateness")) {
					// Lese Werte aus Datei für Lateness Scheduling
					ArrayList<Job> jobs = AnwendungUtil.readJobs(path);
					System.out.println("Es wurden " + jobs.size() + " Zeilen mit folgendem Inhalt gelesen:");
					AnwendungUtil.printJobs(jobs);

					System.out.println("sortiert:");
					Collections.sort(jobs); // Sortiere anhand des festgelegten Kriteriums für Jobs
					AnwendungUtil.printJobs(jobs);

					System.out.println("Berechnetes Latenessscheduling:");
					int[] erg = latenessScheduling(jobs);
					AnwendungUtil.printResult(erg);

					System.out.println("Berechnete maximale Verspätung: " + AnwendungUtil.getMaxLateness(jobs,erg));
				} else {
					throw new ParameterException( Statics.BLATT06_PARAMETER_EXCEPTION );
				}

			} else {
				throw new ParameterException( Statics.BLATT06_PARAMETER_EXCEPTION );
			}
		} catch (ParameterException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Datei wurde nicht gefunden! Bitte Pfadangabe überprüfen!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
