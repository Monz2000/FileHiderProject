package Model;

public class Data {
	
    

	private String name;
    private int FileId;
	private String email;
	
	private String FileName;
	
	private String FilePath;
	
	public Data(String name, int fileId, String email, String FileName, String FilePath) {
		
		this.name = name;
		this.FileId = fileId;
		this.email = email;
		this.FileName = FileName;
		this.FilePath = FilePath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return FileId;
	}

	public void setId(int id) {
		this.FileId = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFileName() {
		return FileName;
	}

	public void setFileName(String FileName) {
		this.FileName = FileName;
	}

	public String getFilePath() {
		return FilePath;
	}

	public void setFilePath(String FilePath) {
		this.FilePath = FilePath;
	}

}
