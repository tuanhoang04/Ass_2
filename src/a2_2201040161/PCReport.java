package a2_2201040161;

public class PCReport {
    public String displayReport(PC[] objs) {
        StringBuilder sb = new StringBuilder();
        sb.append("---------------------------------------------------------------------------------------------------\n");
        sb.append("                                      PCPROG REPORT                                              \n");
        int rowNum = 1;
        for (PC obj : objs) {
            sb.append("---------------------------------------------------------------------------------------------------\n");
            String components = obj.getComps().toString().length() > 54 ? obj.getComps().toString().substring(0, 50) + "...]" : obj.getComps().toString();
            sb.append(String.format("%3d%20s%6d%15s %s\n", rowNum, obj.getModel(), obj.getYear(), obj.getManufacturer(), components));
            rowNum++;
        }
        sb.append("---------------------------------------------------------------------------------------------------\n");
        return sb.toString();
    }
}