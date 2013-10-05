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
	
	('PHYSIOLOGY', 'PHYL7404/01'),
	('PHYSIOLOGY', 'PHYL7406/03');

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
	('Dissertation', 'APHYL7406/03', 80),
	('Supervisor Assessment', 'PHYL7406/03', 7),
	('Viva', 'PHYL7406/03', 13);