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
	
INSERT INTO Student VALUES 
	(20001234,'Bob', 'Jones', 'Mr', 'How to succeed in business without really trying', 'ANHB', 0, null),
	(20005678,'Harry', 'Smith', 'Mr', 'The cultural effect of the paradigm of mental health in modern society', 'PHYSIOLOGY', 0, null),
	(20112233,'Regina', 'George', 'Ms', 'How to make sick people better', 'ANHB', 0, null),
	(20002222,'Lucy', 'Smith', 'Ms', 'The effect of overshadowing older brothers', 'PHYSIOLOGY', 0, null);
			
INSERT INTO Staff VALUES
	(100012345,'Lee', 'Black'),
	(100067890,'Stephanie', 'Myer'),
	(100055555,'Ronald', 'Bruce'),
	(100066666,'Mean', 'Marker'),
	(100099999,'Notsupervising', 'Anyone');
	
INSERT INTO Supervises VALUES
	(20001234, 100012345),
	(20005678, 100012345),
	(20112233, 100067890),
	(20112233, 100055555),
	(20002222, 100055555),
	(20002222, 100066666);
	
INSERT INTO SubAssessmentMark  VALUES
	(81, 100055555, 20001234, 1, 1, 'it thought it was quite good'),
	(60, 100066666, 20001234, 1, 1, 'it was terrible'),
	(79, 100099999, 20001234, 1, 1, 'it was objectively pretty good'),
	(86, 100012345, 20112233, 1, 1, 'interesting read'),
	
	(82, 100055555, 20001234, 2, 1, 'it thought it was quite good again'),
	(58, 100066666, 20001234, 2, 1, 'it was still terrible'),
	(76, 100099999, 20001234, 2, 1, 'it was objectively pretty good, but not as good as the other half'),
	(85, 100012345, 20112233, 2, 1, 'interesting listen'),
	
	(75, 100067890, 20001234, 3, 1, 'report'),
	(85, 100012345, 20112233, 3, 1, 'report'),
	(75, 100067890, 20001234, 4, 1, 'report'),
	(85, 100012345, 20112233, 4, 1, 'report'),
	(75, 100067890, 20001234, 5, 1, 'report'),
	(85, 100012345, 20112233, 5, 1, 'report'),
	(75, 100067890, 20001234, 6, 1, 'report'),
	(85, 100012345, 20112233, 6, 1, 'report'),
	(75, 100067890, 20001234, 7, 1, 'report'),
	(85, 100012345, 20112233, 7, 1, 'report'),
	(75, 100067890, 20001234, 8, 1, 'report'),
	(85, 100012345, 20112233, 8, 1, 'report'),
	(75, 100067890, 20001234, 9, 1, 'report'),
	(85, 100012345, 20112233, 9, 1, 'report'),
	(75, 100067890, 20001234, 10, 1, 'report'),
	(85, 100012345, 20112233, 10, 1, 'report'),
	(75, 100067890, 20001234, 11, 1, 'report'),
	(85, 100012345, 20112233, 11, 1, 'report'),
	(75, 100067890, 20001234, 12, 1, 'report'),
	(85, 100012345, 20112233, 12, 1, 'report'),
	(75, 100067890, 20001234, 13, 1, 'report'),
	(85, 100012345, 20112233, 13, 1, 'report'),
	(75, 100067890, 20001234, 14, 1, 'report'),
	(85, 100012345, 20112233, 14, 1, 'report'),
	
	(87, 100055555, 20005678, 15, 1, 'it was spectacular'),
	(70, 100066666, 20005678, 15, 1, 'it was okay'),
	(79, 100099999, 20005678, 15, 1, 'nice attention to detail'),
	(68, 100012345, 20002222, 15, 1, 'fascinating read'),
	
	(82, 100055555, 20005678, 16, 1, 'still okay'),
	(70, 100066666, 20005678, 16, 1, 'not bad I suppose'),
	(76, 100099999, 20005678, 16, 1, 'enjoyed thoroughly'),
	(67, 100012345, 20002222, 16, 1, 'fascinating listen'),
	
	(80, 100067890, 20005678, 17, 1, 'report'),
	(65, 100012345, 20002222, 17, 1, 'report'),
	(80, 100067890, 20005678, 18, 1, 'report'),
	(65, 100012345, 20002222, 18, 1, 'report'),
	(80, 100067890, 20005678, 19, 1, 'report'),
	(65, 100012345, 20002222, 19, 1, 'report'),
	(80, 100067890, 20005678, 20, 1, 'report'),
	(65, 100012345, 20002222, 20, 1, 'report'),
	(80, 100067890, 20005678, 21, 1, 'report'),
	(65, 100012345, 20002222, 21, 1, 'report'),
	(80, 100067890, 20005678, 22, 1, 'report'),
	(65, 100012345, 20002222, 22, 1, 'report'),
	(80, 100067890, 20005678, 23, 1, 'report'),
	(65, 100012345, 20002222, 23, 1, 'report'),
	(80, 100067890, 20005678, 24, 1, 'report'),
	(65, 100012345, 20002222, 24, 1, 'report'),
	(80, 100067890, 20005678, 25, 1, 'report'),
	(65, 100012345, 20002222, 25, 1, 'report'),
	(80, 100067890, 20005678, 26, 1, 'report'),
	(65, 100012345, 20002222, 26, 1, 'report'),
	(80, 100067890, 20005678, 27, 1, 'report'),
	(65, 100012345, 20002222, 27, 1, 'report'),
	(80, 100067890, 20005678, 28, 1, 'report'),
	(65, 100012345, 20002222, 28, 1, 'report');
	