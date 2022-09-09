package projects;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import projects.entity.Project;
import projects.exception.DbException;
import projects.service.ProjectsService; 


public class Packages {

	private Scanner scanner = new Scanner(System.in) ;
			private ProjectsService projectsService = new ProjectsService(); 
	
	// @formatter:0ff
	private List<String> operations = List.of(
			"1) Add a project"
			); 
	//@formatter:on
	

	public static void main(String[] args) {
		
new Packages().displayMenu(); 

	}
	private void displayMenu() { 
		boolean done = false; 
		
		while(!done) { 
					
			try {
				int operation = getOperation(); 
				
				switch(operation) { 
			case -1:
			done = exitMenu(); 
			break; 
			
			case 1: 
				addProject(); 
				break;
				
				
				default:
				System.out.println("\n" + operation + " is not valid. Try again. "); 
			break; 	
			}
			}catch(Exception e) {
				System.out.println("\nError: " + e.toString() + " Try again."); 
				
			}
		}
	}
	
	private void addProject() {
		String projectName = getStringInput("Enter the project name: "); 
		BigDecimal estimatedHours = getDecimalInput("Enter the estimated hours: "); 
		BigDecimal actualHours = getDecimalInput("Enter the actual hours: "); 
		Integer difficulty = getIntInput("Enter the project difficulty (1-5): "); 
		String notes = getStringInput("Enter the project notes: "); 
		
		Project project = new Project(); 
		
		project.setProjectName(projectName);
		project.setEstimatedHours(estimatedHours); 
		project.setActualHours(actualHours); 
		project.setDifficulty(difficulty);
		project.setNotes(notes);
		
		Project dbProject = projectsService.addProject(project); 
		System.out.println("You have successfully created project: \n" + dbProject);
	}
	private BigDecimal getDecimalInput(String prompt) {
	String input = getStringInput(prompt); 
		
		if(Objects.isNull(input)) { 
			return null; 
		} 
		try { 
			return new BigDecimal(input).setScale(2); 
		}
		catch(NumberFormatException e) { 
			throw new DbException(input + " is not a valid decimal number."); 
		}
		
	}

		// 4b I had to add lines 83 & 84 to get rid of the BigDecimal underline in line 74: 
		
	private BigDecimal BigDecimal(String input) {
				return null;
		
		// end here
	}
// video notes: 
	
//private void createTables() { 
//		projectsService.createAndPopulateTables(); 
//		System.out.println("\nTables create and populated!"); 
//	}
	// end video notes. 
	
	
	private boolean exitMenu() {
System.out.println("\nExiting the menu."); 
		return true;
	}
	private int getOperation() {
		printOperations();
		Integer op = getIntInput("Enter an menu selection (press Enter to quit)");
		
		return Objects.isNull(op) ? -1 : op; 
	}
	private void printOperations() {
		System.out.println(); 
		System.out.println("Here is what you can do:"); 
		
		operations.forEach(op -> System.out.println("    " + op)); 
		
	}
	
	private Integer getIntInput(String prompt) { 
		String input = getStringInput(prompt); 
		
		if(Objects.isNull(input)) { 
			return null; 
		} 
		try { 
			// homework shows this line vs the color line from video: which one? 
			//return  Integer.valueof(input);  
			return Integer.parseInt(input); 
		}
		catch(NumberFormatException e) { 
			throw new DbException(input + " is not a valid number."); 
			
		}
	}
// this part is not on homework: 
		private Double getDoubleInput(String prompt) { 
			String input = getStringInput(prompt); 
			
			if(Objects.isNull(input)) { 
				return null; 
			} 
			try { 
				return Double.parseDouble(input); 
			}
			catch(NumberFormatException e) { 
				throw new DbException(input + " is not a valid number."); 
				
			}
			// end of the video notes. 
	}
	
	private String getStringInput(String prompt) { 
		System.out.print(prompt +  ": "); 
		String input = scanner.nextLine(); 
		
		return input.isBlank() ? null : input.trim(); 
	}
}
