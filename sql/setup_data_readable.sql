INSERT INTO Unit VALUES
	('ANHB7407/2', 'Project Proposal & Seminar', 6),
	('ANHB7405/1', 'Assignment & Final Seminar', 6),
	('ANHB7409/3', 'Research Dissertation & Viva', 36),
	
	('PHYL7404/01', 'Honours Written and Oral Skills Parts I & II', 12),
	('PHYL7406/03', 'Honours Research Project Parts I & II', 36);

INSERT INTO Discipline VALUES
	('ANHB', 'ANHB7407/2'), 
	('ANHB', 'ANHB7405/1'),
	('ANHB', 'ANHB7409/3'),
	
	('PHYL', 'PHYL7404/01'),
	('PHYL', 'PHYL7406/03');

INSERT INTO Assessment(AssessmentName, UnitCode, UnitPercent) VALUES
	('Proposal Seminar', 'ANHB7407/2', 20),
	('Project Proposal', 'ANHB7407/2', 80),
	('Newspaper Article', 'ANHB7405/1', 20),
	('Final Seminar', 'ANHB7405/1', 80),
	('Dissertation', 'ANHB7409/3', 80),
	('Supervisor Assessment', 'ANHB7409/3', 7),
	('Viva', 'ANHB7409/3', 13),
	
	('Proposal Seminar', 'PHYL7404/01', 10),
	('Project Proposal', 'PHYL7404/01', 40),
	('Newspaper Article', 'PHYL7404/01', 10),
	('Final Seminar', 'PHYL7404/01', 40),
	('Dissertation', 'PHYL7406/03', 80),
	('Supervisor Assessment', 'PHYL7406/03', 7),
	('Viva', 'PHYL7406/03', 13);
	
INSERT INTO SubAssessment(SubAssessmentName, AssessmentID, AssessmentPercent, MaxMarks) VALUES
	('SubAssessment 1', 1, 50, 100),
	('SubAssessment 2', 1, 50, 100),
	('SubAssessment 1', 2, 50, 100),
	('SubAssessment 2', 2, 50, 100),
	('SubAssessment 1', 3, 50, 100),
	('SubAssessment 2', 3, 50, 100),
	('SubAssessment 1', 4, 50, 100),
	('SubAssessment 2', 4, 50, 100),
	('SubAssessment 1', 5, 50, 100),
	('SubAssessment 2', 5, 50, 100),
	('SubAssessment 1', 6, 50, 100),
	('SubAssessment 2', 6, 50, 100),
	('SubAssessment 1', 7, 50, 100),
	('SubAssessment 2', 7, 50, 100),
	('SubAssessment 1', 8, 50, 100),
	('SubAssessment 2', 8, 50, 100),
	('SubAssessment 1', 9, 50, 100),
	('SubAssessment 2', 9, 50, 100),
	('SubAssessment 1', 10, 50, 100),
	('SubAssessment 2', 10, 50, 100),
	('SubAssessment 1', 11, 50, 100),
	('SubAssessment 2', 11, 50, 100),
	('SubAssessment 1', 12, 50, 100),
	('SubAssessment 2', 12, 50, 100),
	('SubAssessment 1', 13, 50, 100),
	('SubAssessment 2', 13, 50, 100),
	('SubAssessment 1', 14, 50, 100),
	('SubAssessment 2', 14, 50, 100);