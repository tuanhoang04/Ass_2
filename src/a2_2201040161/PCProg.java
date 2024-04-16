package a2_2201040161;

import static utils.TextIO.getln;
import static utils.TextIO.putln;
import static utils.TextIO.writeFile;
import static utils.TextIO.writeStandardOutput;

import java.util.Vector;
import utils.DomainConstraint;
import utils.NotPossibleException;
import utils.TextIO;

/**
 * @overview PCProg is a program that captures data about PC objects and
 *           displays a report about them on the console.
 * 
 * @attributes objs Set<PC>
 * 
 * @object A typical PCProg is {c1,...,cn} where c1,...,cn are pcs
 * 
 * @abstract_properties mutable(objs)=true /\ optional(objs)=false
 * 
 * @author dmle
 */
public class PCProg {
	private static final Object YES = "Y";
	@DomainConstraint(mutable = true, optional = false)
	private Set<PC> objs;

	/**
	 * @effects initialise this to have an empty set of PCs
	 */
	public PCProg() {
		objs = new Set<>();
	}

	/**
	 * @effects if objs is not empty display to the standard console a text-based
	 *          tabular report on objs return this report else display nothing and
	 *          return null
	 */
	public String displayReport() {
		if (objs.size() > 0) {
			Vector<PC> pcs = objs.getElements();
			PCReport reportObj = new PCReport();
			return reportObj.displayReport(pcs.toArray(new PC[pcs.size()]));
		} else {
			return null;
		}
	}

	/**
	 * @effects save report to a file pcs.txt in the same directory as the program's
	 */
	public void saveReport(String report) {
		String fileName = "pcs.txt";
		writeFile(fileName);
		putln(report);
		writeStandardOutput();
	}

	public void createObjects(){
		PCFactory pf = PCFactory.getInstance();
		boolean con = true;
		while(con) {
			TextIO.put("Please type in the model of the PC: ");
			String model = TextIO.getlnString();
			TextIO.put("Please type in the PC's year of manufacture: ");
			int year = TextIO.getlnInt();
			TextIO.put("Please type in the name of the manufacturer: ");
			String manufacturer = TextIO.getlnString();
			Set<String> components = new Set<>();
			boolean comps = true;
			while (comps) {
				TextIO.put("Please type in the name of a component (press Enter to stop): ");
				String a = TextIO.getlnString();
				if(a.isEmpty()){
					comps=false;
				}else{
					components.insert(a);
				}
			}
			objs.insert(pf.createPC(model,year,manufacturer,components));
			TextIO.put("Do you want to continue adding another PC? [Y/N]");
			String yn = TextIO.getlnString();
			if(yn.equals("no")||yn.equals("N")||yn.equals("No")||yn.equals("NO")||yn.equals("n")){
				con=false;
			}
		}
	}

	public Vector<PC> getObjects(){
		return objs.getElements();
	}

	/**
	 * The run method
	 * 
	 * @effects initialise an instance of PCProg create objects from data entered by
	 *          the user display a report on the objects prompt user to save report
	 *          to file if user answers "Y" save report else end
	 */
	public static void main(String[] args) {
		//
		PCProg prog = new PCProg();

		// create objects
		try {
			prog.createObjects();
			// display report
			String report = prog.displayReport();
			System.out.println(report);
			if (report != null) {
				// prompt user to save report
				putln("Save report to file? [Y/N]");
				String toSave = getln();
				if (toSave.equals("Y")||toSave.equals("y")) {
					prog.saveReport(report);
					putln("report saved");
				}
			}

		} catch (NotPossibleException e) {
			System.err.printf("%s: %s%n", e, e.getMessage());
		}
		putln("~END~");
	}

}
