/**This is the sql script that sets up the schema for a semester's marks database**/

CREATE TABLE Staff
	(
		StaffID INT NOT NULL,
		FirstName VARCHAR(24) NOT NULL,
		LastName VARCHAR(24) NOT NULL,
		
		PRIMARY KEY (StaffID)
	);

CREATE TABLE Student
	(
		StudentID INT NOT NULL,
		FirstName VARCHAR(30) NOT NULL,
		LastName VARCHAR(30) NOT NULL,
		DissTitle VARCHAR(300),
		Discipline VARCHAR(5),
		Mark DECIMAL,
		Grade VARCHAR(2),
		
		PRIMARY KEY (StudentID)
	);
	
CREATE TABLE Supervises
	(
		StudentID INT NOT NULL,
		StaffID INT NOT NULL,
	
		PRIMARY KEY (StudentID, StaffID),
		FOREIGN KEY (StudentID) REFERENCES Student (StudentID),
		FOREIGN KEY (StaffID) REFERENCES Staff (StaffID)
	);
	
CREATE TABLE Unit
	(
		UnitCode VARCHAR(8) NOT NULL,
		UnitName VARCHAR(50) NOT NULL,
		Points INT NOT NULL,
		
		PRIMARY KEY (UnitCode)
	);
	
CREATE TABLE Discipline
	(
		DisciplineName VARCHAR(5) NOT NULL,
		Unit1Code VARCHAR(8),
		Unit2Code VARCHAR(8),
		Unit3Code VARCHAR(8),
		Unit4Code VARCHAR(8),
		
		PRIMARY KEY (DisciplineName),
		FOREIGN KEY (Unit1Code) REFERENCES Unit (UnitCode),
		FOREIGN KEY (Unit2Code) REFERENCES Unit (UnitCode),
		FOREIGN KEY (Unit3Code) REFERENCES Unit (UnitCode),
		FOREIGN KEY (Unit4Code) REFERENCES Unit (UnitCode)
	);
	
CREATE TABLE Assessment
	(
		AssessmentID INT NOT NULL,
		AssessmentName VARCHAR(30) NOT NULL,
		UnitCode VARCHAR(8) NOT NULL,
		UnitPercent INT NOT NULL,
		
		PRIMARY KEY (AssessmentID),
		FOREIGN KEY (UnitCode) REFERENCES Unit (UnitCode)
	);
	
CREATE TABLE SubAssessment
	(
		SubAssessmentID INT NOT NULL,
		SubAssessmentName VARCHAR(30) NOT NULL,
		AssessmentID INT NOT NULL,
		AssessmentPercent INT NOT NULL,
		MaxMarks INT NOT NULL,
		
		PRIMARY KEY (SubAssessmentID),
		FOREIGN KEY (AssessmentID) REFERENCES Assessment (AssessmentID)
	);
	
CREATE TABLE SubAssessmentMark
	(
		MarkID INT NOT NULL,
		Mark DECIMAL NOT NULL,
		MarkerID INT NOT NULL,
		StudentID INT NOT NULL,
		SubAssessmentID INT NOT NULL,
		InsideRange INT,
		
		PRIMARY KEY (MarkID),
		FOREIGN KEY (MarkerID) REFERENCES Staff (StaffID),
		FOREIGN KEY (StudentID) REFERENCES Student (StudentID),
		FOREIGN KEY (SubAssessmentID) REFERENCES SubAssessment (SubAssessmentID)
	);
	
CREATE TABLE AssessmentMark
	(
		MarkID INT NOT NULL,
		Mark DECIMAL NOT NULL,
		StudentID INT NOT NULL,
		AssessmentID INT NOT NULL,
		
		PRIMARY KEY (MarkID),
		FOREIGN KEY (StudentID) REFERENCES Student (StudentID),
		FOREIGN KEY (AssessmentID) REFERENCES Assessment (AssessmentID)
	);
	
CREATE TABLE UnitMark
	(
		MarkID INT NOT NULL,
		Mark DECIMAL NOT NULL,
		Grade VARCHAR(2),
		StudentID INT NOT NULL,
		UnitCode VARCHAR(8) NOT NULL,
		
		PRIMARY KEY (MarkID),
		FOREIGN KEY (StudentID) REFERENCES Student (StudentID),
		FOREIGN KEY (UnitCode) REFERENCES Unit (UnitCode)
	);
	
CREATE TABLE Information
	(	
		Period INT NOT NULL,
		LastBackup TIMESTAMP,
		AutoBackupSchedule CHAR NOT NULL,
		AutoBackupDue TIMESTAMP,
		LastRestoreDate TIMESTAMP,
		LastRestoreFrom VARCHAR(25),
		VersionSuperseded VARCHAR(25)
	);