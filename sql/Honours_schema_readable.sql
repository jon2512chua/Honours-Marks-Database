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
		Title VARCHAR(6),
		DissTitle VARCHAR(300),
		Discipline VARCHAR(20),
		Mark DECIMAL,
		Grade VARCHAR(2),

		PRIMARY KEY (StudentID)
	);

CREATE TABLE Supervises
	(
		StudentID INT NOT NULL,
		StaffID INT NOT NULL,

		PRIMARY KEY (StudentID, StaffID),
		FOREIGN KEY (StudentID) REFERENCES Student (StudentID) ON DELETE CASCADE,
		FOREIGN KEY (StaffID) REFERENCES Staff (StaffID) ON DELETE CASCADE
	);

CREATE TABLE Unit
	(
		UnitCode VARCHAR(12) NOT NULL,
		UnitName VARCHAR(50) NOT NULL,
		Points INT NOT NULL,

		PRIMARY KEY (UnitCode)
	);

CREATE TABLE Discipline
	(
		DisciplineName VARCHAR(20) NOT NULL,
		UnitCode VARCHAR(12) NOT NULL,

		PRIMARY KEY (DisciplineName, UnitCode),
		FOREIGN KEY (UnitCode) REFERENCES Unit (UnitCode) ON DELETE CASCADE
	);


CREATE TABLE Assessment
	(
		AssessmentID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
		AssessmentName VARCHAR(30) NOT NULL,
		UnitCode VARCHAR(12) NOT NULL,
		UnitPercent INT NOT NULL,

		PRIMARY KEY (AssessmentID),
		FOREIGN KEY (UnitCode) REFERENCES Unit (UnitCode) ON DELETE CASCADE
	);

CREATE TABLE SubAssessment
	(
		SubAssessmentID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
		SubAssessmentName VARCHAR(30) NOT NULL,
		AssessmentID INT NOT NULL,
		AssessmentPercent INT NOT NULL,
		MaxMarks INT NOT NULL,

		PRIMARY KEY (SubAssessmentID),
		FOREIGN KEY (AssessmentID) REFERENCES Assessment (AssessmentID) ON DELETE CASCADE
	);

CREATE TABLE SubAssessmentMark
	(
		Mark DECIMAL NOT NULL,
		MarkerID INT NOT NULL,
		StudentID INT NOT NULL,
		SubAssessmentID INT NOT NULL,
		InsideRange INT,
		Report LONG VARCHAR,

		PRIMARY KEY (MarkerID, StudentID, SubAssessmentID),
		FOREIGN KEY (MarkerID) REFERENCES Staff (StaffID) ON DELETE CASCADE,
		FOREIGN KEY (StudentID) REFERENCES Student (StudentID) ON DELETE CASCADE,
		FOREIGN KEY (SubAssessmentID) REFERENCES SubAssessment (SubAssessmentID) ON DELETE CASCADE
	);

CREATE TABLE AssessmentMark
	(
		Mark DECIMAL NOT NULL,
		StudentID INT NOT NULL,
		AssessmentID INT NOT NULL,

		PRIMARY KEY (StudentID, AssessmentID),
		FOREIGN KEY (StudentID) REFERENCES Student (StudentID) ON DELETE CASCADE,
		FOREIGN KEY (AssessmentID) REFERENCES Assessment (AssessmentID) ON DELETE CASCADE
	);

CREATE TABLE UnitMark
	(
		Mark DECIMAL NOT NULL,
		Grade VARCHAR(2),
		StudentID INT NOT NULL,
		UnitCode VARCHAR(12) NOT NULL,

		PRIMARY KEY (StudentID, UnitCode),
		FOREIGN KEY (StudentID) REFERENCES Student (StudentID) ON DELETE CASCADE,
		FOREIGN KEY (UnitCode) REFERENCES Unit (UnitCode) ON DELETE CASCADE
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