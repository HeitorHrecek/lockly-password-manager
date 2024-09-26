CREATE TABLE tbPassword (
		passwordId INT NOT NULL,
		userId INT NOT NULL,
		name VARCHAR(255) NOT NULL UNIQUE,
		content VARCHAR(255) NOT NULL,
		CONSTRAINT passwordId_PK PRIMARY KEY (passwordId, userId),
		CONSTRAINT tbPassword_userId_FK FOREIGN KEY (userId) REFERENCES tbUser (userId)
);

CREATE TABLE tbPasswordFolder (
		passwordFolderId INT NOT NULL,
		userId INT NOT NULL,
		folderId int NOT NULL,
		name VARCHAR(30) NOT NULL UNIQUE,
		content VARCHAR(30) NOT NULL,
		CONSTRAINT passwordFolderId_PK PRIMARY KEY (passwordFolderId, userId),
		CONSTRAINT tbPasswordFolder_userId_FK FOREIGN KEY (userId) REFERENCES tbUser (userId),
        CONSTRAINT folderId_FK FOREIGN KEY (folderId) REFERENCES tbFolder (folderId)
);