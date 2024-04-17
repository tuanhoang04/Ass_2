package a2_2201040161;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

import utils.NotPossibleException;
/**
 * A program that captures data about PC objects and displays
 * a report about them on the console.
 */
public class PCProg {
	private static final Object YES = "Y";
	private Set<PC> objs;

	/**
	 * Initialise this to have an empty set of PCs
	 */
	public PCProg() {
		objs = new Set<>();
	}

	/**
	 * If <tt>objs</tt> is not empty, displays a text-based tabular
	 * report on <tt>objs</tt> to the standard console.
	 * Displays nothing if <tt>objs</tt> is empty.
	 *
	 * @return this report if <tt>objs</tt> is not empty or <tt>null</tt> otherwise.
	 */
	public String displayReport() {
		if (!objs.isEmpty()) {
			Vector<PC> pcs = objs.getElements();
			PCReport reportObj = new PCReport();
			return reportObj.displayReport(pcs.toArray(new PC[0]));
		} else {
			return null;
		}
	}

	public void createObjects(){
		PCFactory pf = PCFactory.getInstance();
		Scanner sc = new Scanner(System.in);
		boolean con = true;
		while(con) {
			System.out.print("Please type in the model of the PC: ");
			String model = sc.nextLine();
			System.out.print("Please type in the PC's year of manufacture: ");
			int year = sc.nextInt();
			sc.nextLine();
			System.out.print("Please type in the name of the manufacturer: ");
			String manufacturer = sc.nextLine();
			Set<String> components = new Set<>();
			boolean comps = true;
			while (comps) {
				System.out.print("Please type in the name of a component (press Enter to stop): ");
				String a = sc.nextLine();
				if(a.isEmpty()){
					comps=false;
				}else{
					components.insert(a.trim());
				}
			}
			objs.insert(pf.createPC(model,year,manufacturer,components));
			System.out.print("Do you want to continue adding another PC? [Y/N]");
			String yn = sc.nextLine();
			if(yn.equals("no")||yn.equals("N")||yn.equals("No")||yn.equals("NO")||yn.equals("n")){
				con=false;
			}
		}
	}

	public Vector<PC> getObjects(){
		return objs.getElements();
	}

	/**
	 * Saves report to a file <tt>pcs.txt</tt> in the program's working directory.
	 */
	public void saveReport(String report) {
		String fileName = "pcs.txt";
		try (PrintWriter pw = new PrintWriter(fileName)) {
			pw.println(report);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Initializes an instance of <tt>PCProg</tt>.
	 * Create objects from data entered by the user.
	 * Display a report on the objects.
	 * Prompt user to save report to file. If user answers "Y", save report.
	 * Otherwise, end program.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PCProg prog = new PCProg();
		try {
			// create objects
			prog.createObjects();
			// display report
			String report = prog.displayReport();
			System.out.println(report);
			if (report != null) {
				// prompt user to save report
				System.out.println("Save report to file? [Y/N]");
				String toSave = sc.nextLine();
				if (toSave.equals("Y")||toSave.equals("y")){
					prog.saveReport(report);
					System.out.println("report saved");
				}
			}
		} catch (NotPossibleException e) {
			System.err.printf("%s: %s%n", e, e.getMessage());
		}
		System.out.println("~END~");
	}
}
