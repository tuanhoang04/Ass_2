package a2_2201040161;

public class PCReport {
    public String displayReport(PC[] objs) {
        StringBuilder sb = new StringBuilder();
        sb.append("---------------------------------------------------------------------------------------------------\n" +
                "                                      PCPROG REPORT                                              \n");
        int rowNum = 1;
        for (PC obj : objs) {
            sb.append("---------------------------------------------------------------------------------------------------\n");
            sb.append(String.format("%3d %95s\n",rowNum,obj.toString()));
            rowNum++;
        }
        sb.append("---------------------------------------------------------------------------------------------------\n");
        return sb.toString();
    }
}