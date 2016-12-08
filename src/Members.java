public abstract class Members 
{
    //Abstract class to extend members of common properties (Such as Athletes/Officials)
	private String ID;
    private String name;
    private int age;
    private String state;
	
    //Constructor
  	public Members(String ID, String name, int age, String state ) 
  	{
  		this.ID = ID;
  		this.name = name;
  		this.age = age;
  		this.state = state;  		
  	}
  	
  	public void getAll()
  	{
  		System.out.println(ID + name + age + state);
  	}

  	//getters & setters:
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
      	
}
