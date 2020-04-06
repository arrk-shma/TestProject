package trials;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Second {

	public static void main(String[] args) {
	
		// pretty print
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Staff staff = createStaffObject();

        // Java objects to String
        String json = gson.toJson(staff);

        //System.out.println(json);

        // Java objects to File
        try (FileWriter writer = new FileWriter(".\\staff.json")) {
            gson.toJson(staff, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

	}
	
	
	   private static Staff createStaffObject() {

	        Staff staff = new Staff();

	        staff.setName("Shailendra");
	        staff.setAge(41);
	        staff.setPosition(new String[]{"TestManager", "Lead", "SDET"});
	        Map<String, BigDecimal> salary = new HashMap() {{
	            put("2010", new BigDecimal(10000));
	            put("2012", new BigDecimal(12000));
	            put("2018", new BigDecimal(14000));
	        }};
	        staff.setSalary(salary);
	        staff.setSkills(Arrays.asList("java", "ManualTesting", "SDET", "Selenium"));

	        return staff;

	    }

}
